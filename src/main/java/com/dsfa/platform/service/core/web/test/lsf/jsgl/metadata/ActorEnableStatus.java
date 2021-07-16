package com.dsfa.platform.service.core.web.test.lsf.jsgl.metadata;

public enum ActorEnableStatus {
    DISABLED(0, "无效"),
    ENABLED(1, "有效");

    private Integer value;
    private String text;

    ActorEnableStatus(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
