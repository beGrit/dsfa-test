package com.dsfa.platform.service.core.web.test.lsf.rygl.model;

import com.dsfa.platform.service.core.web.test.lsf.rygl.model.base.BasePerson;
import com.dsfa.platform.starter.db.jfinal.tablebind.TableBind;
import com.dsfa.platform.starter.meta.base.DsfaBaseModel;

import java.io.Serializable;
@TableBind(
        tableName = "test_lsf_rygl",
        pkName = "test_lsf_rygl_id"
)
public class Person extends BasePerson<Person> implements Serializable {
    public static final Person DAO = (Person) (new Person().dao());

    public Person() {
    }
}
