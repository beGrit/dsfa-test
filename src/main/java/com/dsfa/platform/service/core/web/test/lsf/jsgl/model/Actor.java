package com.dsfa.platform.service.core.web.test.lsf.jsgl.model;

import com.dsfa.platform.starter.meta.base.DsfaBaseModel;

import java.io.Serializable;

public class Actor extends DsfaBaseModel<Actor> implements Serializable {
    public static final Actor DAO = (Actor) (new Actor()).dao();

    public Actor() {
    }
}
