package net.unit8.eavee;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EavConverter<T extends EavValue> {
    private final Class<T> entityClass;
    private final List<EavField> fields;

    public EavConverter(Class<T> entityClass, List<EavField> fields) {
        validateEntityClass(entityClass);

        this.entityClass = entityClass;
        this.fields = fields;
    }

    public Map<String, Object> toMap(List<T> values) {
        return values.stream()
                .collect(Collectors.toMap(
                        e -> e.getField().getName(),
                        e -> e.getField().getFieldFormat().formatValue(e.getValue())
                ));
    }

    public List<T> toEntities(Map<String, Object> eavs) {
        return eavs
                .entrySet()
                .stream()
                .map(e -> {
                    EavField field =  fields.stream()
                            .filter(f -> f.getName().equals(e.getKey()))
                            .findAny()
                            .orElse(null);
                    try {
                        T obj = entityClass.getConstructor().newInstance();
                        obj.setField(field);
                        obj.setValue(e.getValue());
                        return obj;
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                        throw new RuntimeException("Unreachable", ex);
                    }
                })
                .collect(Collectors.toList());
    }

    private void validateEntityClass(Class<T> entityClass) {
        try {
            Constructor<T> constructor = entityClass.getConstructor();
            constructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("EAV Entity must have a default constructor: " + entityClass);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalArgumentException("EAV Entity cannot be instantiated: " + entityClass);
        }
    }
}
