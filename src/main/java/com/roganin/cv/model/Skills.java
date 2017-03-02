package com.roganin.cv.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Skills {
    JAVA("java"), JS("js"), PYTHON("python");

    private String skill;

    Skills(String skill) {
        this.skill = skill;
    }

    @JsonCreator
    public static Skills fromString(String key) {
        return key == null
                ? null
                : Skills.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String getSkill() {
        return skill;
    }
}
