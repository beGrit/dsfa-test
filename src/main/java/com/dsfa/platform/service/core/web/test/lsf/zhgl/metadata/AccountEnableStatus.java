package com.dsfa.platform.service.core.web.test.lsf.zhgl.metadata;

public enum AccountEnableStatus {
    NOT_ENABLED(0, "未启用"),
    ENABLED(1, "已启用");

    private Integer value;
    private String text;

    AccountEnableStatus(Integer value, String text) {
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
