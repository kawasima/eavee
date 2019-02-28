package net.unit8.eavee;

public class MyField implements EavField {
    private Long id;
    private String name;
    private String type;
    private EavFormat fieldFormat;
    private String description;
    private String regexp;
    private Integer maxLength;
    private Integer minLength;
    private boolean required;
    private boolean filter;
    private boolean searchable;
    private boolean visible;
    private boolean multiple;
    private String defaultValue;


    private MyField() {

    }

    public static class Builder {
        private final MyField field;
        public Builder() {
            field = new MyField();
        }

        public Builder name(String name) {
            field.name = name;
            return this;
        }
        public <T> Builder fieldFormat(EavFormat<T> format) {
            field.fieldFormat = format;
            return this;
        }

        public Builder regexp(String regexp) {
            field.regexp = regexp;
            return this;
        }

        public Builder maxLength(Integer maxLength){
            field.maxLength = maxLength;
            return this;
        }

        public Builder minLength(Integer minLength){
            field.minLength = minLength;
            return this;
        }

        public MyField build() {
            return field;
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public <T> EavFormat<T> getFieldFormat() {
        return fieldFormat;
    }

    @Override
    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = EavFormatType.valueOf(fieldFormat).getInstance();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getRegexp() {
        return regexp;
    }

    @Override
    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    @Override
    public Integer getMinLength() {
        return minLength;
    }

    @Override
    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    @Override
    public Integer getMaxLength() {
        return maxLength;
    }

    @Override
    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Boolean isRequired() {
        return required;
    }

    @Override
    public void setRequired(Boolean required) {
        this.required = required;
    }

    @Override
    public Boolean isFilter() {
        return filter;
    }

    @Override
    public void setFilter(Boolean filter) {
        this.filter = filter;
    }

    @Override
    public Boolean isSearchable() {
        return searchable;
    }

    @Override
    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    @Override
    public Boolean isMultiple() {
        return multiple;
    }

    @Override
    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String getVisible() {
        return null;
    }

    @Override
    public void setVisible(String visible) {

    }

    @Override
    public String toString() {
        return "MyField{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", fieldFormat=" + fieldFormat +
                ", description='" + description + '\'' +
                ", regexp='" + regexp + '\'' +
                ", maxLength=" + maxLength +
                ", minLength=" + minLength +
                ", required=" + required +
                ", filter=" + filter +
                ", searchable=" + searchable +
                ", visible=" + visible +
                ", multiple=" + multiple +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}
