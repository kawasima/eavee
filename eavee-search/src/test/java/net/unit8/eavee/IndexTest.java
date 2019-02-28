package net.unit8.eavee;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.PullImageResultCallback;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IndexTest {
    private static final Logger LOG = LoggerFactory.getLogger(IndexTest.class);

    private static DockerClient docker;
    private String esContainerId;
    private String esAddress;

    @BeforeAll
    public static void setupDockerClient() {
        docker = DockerClientBuilder.getInstance().build();
        try {
            docker.pullImageCmd("elasticsearch:6.5.4")
                    .exec(new PullImageResultCallback()).awaitCompletion();
        } catch (InterruptedException e) {
            throw new IllegalStateException("timeout");
        }
    }

    @BeforeEach
    public void startRedis() {
        CreateContainerResponse containerResponse = docker
                .createContainerCmd("elasticsearch:6.5.4")
                .withEnv("discovery.format=single-node")
                .withExposedPorts(ExposedPort.tcp(9200))
                .exec();
        esContainerId = containerResponse.getId();
        docker.startContainerCmd(esContainerId)
                .exec();
        esAddress = docker.inspectContainerCmd(esContainerId).exec()
                .getNetworkSettings()
                .getNetworks()
                .get("bridge")
                .getIpAddress();
    }

    @Test
    public void test() throws IOException  {
        RetryPolicy retryPolicy = new RetryPolicy()
                .retryOn(ConnectException.class)
                .withDelay(5, TimeUnit.SECONDS)
                .withMaxRetries(5);

        try (RestHighLevelClient client = new RestHighLevelClient(
                        RestClient.builder(
                                new HttpHost(esAddress, 9200, "http")))) {

            CreateIndexRequest request = new CreateIndexRequest("test");
            request.mapping("_doc", Map.of(
                    "_doc", Map.of(
                            "properties", Map.of(
                                    "message", Map.of("type", "text")))
            ));
            CreateIndexResponse response = Failsafe.with(retryPolicy)
                    .onRetry(t -> LOG.info("retry"))
                    .get(() -> client.indices().create(request, RequestOptions.DEFAULT));
            LOG.info("created index {}", response.index());
        }
    }

    @AfterEach
    public void removeDockerContainer() {
        docker.stopContainerCmd(esContainerId).exec();
        docker.removeContainerCmd(esContainerId).exec();

    }
}
