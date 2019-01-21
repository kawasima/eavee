package net.unit8.eavee.provider;

import net.unit8.eavee.EavField;

import java.util.List;

public interface EavAttributeProvider {
    List<? extends EavField> get(String type);
}
