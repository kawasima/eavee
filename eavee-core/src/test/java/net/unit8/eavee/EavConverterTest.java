package net.unit8.eavee;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class EavConverterTest {
    @Test
    public void test() {
        List<EavField> fields = List.of(
                new MyField.Builder()
                        .name("email")
                        .build());

        Map<String, Object> map = Map.of(
                "email", "kawasima@example.com",
                "name",  "kawasima",
                "age", 100
        );

        System.out.println(new EavConverter<>(MyEavValue.class, fields).toEntities(map));
    }
}
