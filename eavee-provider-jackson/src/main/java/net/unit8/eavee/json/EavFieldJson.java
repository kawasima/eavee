package net.unit8.eavee.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.unit8.eavee.EavField;
import net.unit8.eavee.EavFormat;
import net.unit8.eavee.EavFormatType;

public class EavFieldJson implements EavField {
    private Long id;

    private String name;

    private String type;

    @JsonProperty("field_format")
    private String fieldFormat;

    private String description;

    private String regexp;

    @JsonProperty("min_length")
    private Integer minLength;

    @JsonProperty("max_length")
    private Integer maxLength;

    @JsonProperty("is_required")
    private Boolean required;

    @JsonProperty("is_filter")
    private Boolean filter;

    @JsonProperty("is_searchable")
    private Boolean searchable;

    @JsonProperty("is_multiple")
    private Boolean multiple;

    @JsonProperty("default_value")
    private String defaultValue;

    @JsonProperty("visible")
    private String visible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EavFormat getFieldFormat() {
        return EavFormatType.valueOf(fieldFormat).getInstance();
    }

    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = fieldFormat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Boolean isRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean isFilter() {
        return filter;
    }

    public void setFilter(Boolean filter) {
        this.filter = filter;
    }

    public Boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public Boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }
}
