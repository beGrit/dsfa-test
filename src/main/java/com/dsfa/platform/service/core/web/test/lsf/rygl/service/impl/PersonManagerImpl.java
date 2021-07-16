package com.dsfa.platform.service.core.web.test.lsf.rygl.service.impl;

import com.dsfa.platform.service.core.web.test.lsf.rygl.model.Person;
import com.dsfa.platform.service.core.web.test.lsf.rygl.service.IPersonManager;
import com.dsfa.platform.starter.db.jfinal.kit.Kv;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.Db;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.SqlPara;
import com.dsfa.platform.starter.meta.api.oua2.pojo.po.DsfaOuaUser;
import com.dsfa.platform.starter.web.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonManagerImpl extends BaseService implements IPersonManager {
    private static final String SQL_KEY = "test.lsf.rygl.person.sql.";

    @Override
    public Person findOneById(String id) {
        Person person = Person.DAO.findByIdLoadColumns(id, "name");
        /*
            SqlPara sqlPara = Db.getSqlPara(SQL_KEY + "findById", Kv.by("id", id));
            Person person = Person.DAO.findFirst(sqlPara);
        */
        new Person().setId("07f5e3e5f4534ae0aa76317a5d4d33a6").setName("bbb").save();
        String name = person.getName();
        return person;
    }
}
