package com.dsfa.platform.service.core.web.test.lsf.jsgl.domain;

import com.dsfa.platform.starter.meta.core.model.persist.PersistData;

public class Actor {
    // 主键
    private String id;

    private String name;
    private String isEnabled;
    private String sn;

    public static Actor getInstance(PersistData persistData, String namespace) {
        namespace = namespace.replace(".", "_");
        Actor actor = new Actor();
        actor.name = persistData.getStr(namespace + "." + "name");
        actor.isEnabled = persistData.getStr(namespace + "." + "isenabled");
        actor.sn = persistData.getStr(namespace + "." + "sn");
        actor.id = persistData.getStr(namespace + "." + namespace + "_id");
        return actor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
