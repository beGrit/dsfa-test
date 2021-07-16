package com.dsfa.platform.service.core.web.test.lsf.shgl.metadata;

/**
 * 审核状态
 */
public enum AuditStatus {
    NOT_COMMIT(0, "未提交"),
    COMMIT(1, "审核中"),
    ADMIT(2, "同意"),
    NOT_ADMIT(3, "不同意"),
    UNKNOWN(10, "未知");

    private Integer value;
    private String text;

    AuditStatus(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
