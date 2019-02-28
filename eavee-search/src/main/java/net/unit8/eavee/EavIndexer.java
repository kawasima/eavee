package net.unit8.eavee;

import net.unit8.eavee.provider.EavAttributeProvider;

import java.util.List;

public class EavIndexer {
    public void createIndex(EavAttributeProvider provider, String type) {
        List<? extends EavField> fields = provider.get(type);
        fields.stream()
                .filter(EavField::isSearchable);
    }
}
