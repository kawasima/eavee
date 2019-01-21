package net.unit8.eavee;

public interface EavField {
    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    String getType();
    void setType(String type);

    String getFieldFormat();
    void setFieldFormat(String fieldFormat);

    String getDescription();
    void setDescription(String description);

    String getRegexp();
    void setRegexp(String regexp);

    Integer getMinLength();
    void setMinLength(Integer minLength);

    Integer getMaxLength();
    void setMaxLength(Integer maxLength);

    Boolean isRequired();
    void setRequired(Boolean required);

    Boolean isFilter();
    void setFilter(Boolean filter);

    Boolean isSearchable();
    void setSearchable(Boolean searchable);

    Boolean isMultiple();
    void setMultiple(Boolean multiple);

    String getDefaultValue();
    void setDefaultValue(String defaultValue);

    String getVisible();
    void setVisible(String visible);
}
