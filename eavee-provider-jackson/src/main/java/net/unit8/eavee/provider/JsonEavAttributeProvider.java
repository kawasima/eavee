package net.unit8.eavee.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.unit8.eavee.EavField;
import net.unit8.eavee.json.EavFieldJson;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonEavAttributeProvider implements EavAttributeProvider {
    private Map<String, List<EavFieldJson>> attributes;

    public JsonEavAttributeProvider() {
        ObjectMapper mapper = new ObjectMapper();
        List<EavFieldJson> fields = Optional.ofNullable(JsonEavAttributeProvider.class.getResource("/META-INF/eavee-fields.json"))
                .map(url -> {
                    try {
                        return (List<EavFieldJson>) mapper.readValue(url, new TypeReference<List<EavFieldJson>>() {
                        });
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .orElseThrow(() -> new RuntimeException(""));
        attributes = fields.stream()
                .collect(Collectors.groupingBy(
                        EavFieldJson::getType
                ));

    }

    public List<? extends EavField> get(String type) {
        return attributes.get(type);
    }
}
