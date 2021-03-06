package net.unit8.eavee;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import net.unit8.eavee.provider.JsonEavAttributeProvider;
import net.unit8.eavee.validator.EavAttribute;

import java.util.Map;

public class Person {
    private Long id;

    @EavAttribute(value = "user_profile", provider = JsonEavAttributeProvider.class)
    private Map<String, Object> profiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonAnyGetter
    public Map<String, Object> getProfiles() {
        return profiles;
    }

    @JsonAnySetter
    public void setProfiles(Map<String, Object> profiles) {
        this.profiles = profiles;
    }
}
