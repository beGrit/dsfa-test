package com.dsfa.platform.service.core.web.test.lsf.zhgl.service.impl;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.kit.checker.impl.AccountChecker;
import com.dsfa.platform.service.core.web.test.lsf.rygl.model.Person;
import com.dsfa.platform.service.core.web.test.lsf.rygl.service.IPersonManager;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.metadata.AccountEnableStatus;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.model.Account;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.RegisterService;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.IAccountManager;
import com.dsfa.platform.starter.db.jfinal.kit.Kv;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.Db;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.Record;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.SqlPara;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import com.dsfa.platform.starter.web.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterServiceImpl extends BaseService implements RegisterService {
    @Autowired
    IAccountManager accountManagementService;

    @Autowired
    IPersonManager personManager;

    @Autowired
    AccountChecker accountChecker;

    private static final String ACCOUNT_SQL_KEY = "test.lsf.zhgl.account.sql.";
    private static final String namespace = "test_lsf_zhgl.";

    @Override
    public Result register(PersistData registerForm) throws PlatformCoreException {
        try {
            this.canRegister(registerForm);
        } catch (PlatformCoreException e) {
            throw e;
        }
        return success();
    }

    @Override
    public void canRegister(PersistData registerForm) throws PlatformCoreException {
        if (registerForm.get("_id") == null) { // 判断是否是新增请求
            String peopleId = (String) registerForm.get(namespace + "peopleid");
            if (isRegistered(peopleId)) { // 判断用户是否已经注册过账号
                throw PlatformCoreException.create(500, "该用户已经申请过账号了");
            }
        }
        boolean b = accountManagementService.isAccountNameDuplicate(registerForm.getStr(namespace + "accountname"));
        if (b) {
            throw PlatformCoreException.create(500, "该账户名已被人使用了");
        }
    }

    private boolean isRegistered(String personId) {

        SqlPara sqlPara = Db.getSqlPara(ACCOUNT_SQL_KEY + "exitsByPersonId", Kv.by("personid", personId));
        Record record = Db.findFirst(sqlPara);

        Long count = record.get("COUNT(*)");
        if (count == 0L) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void canRegister(String personId) throws PlatformCoreException {
        Person person = personManager.findOneById(personId);

        String accountName = person.getStr("accountname");
        String nickName = person.getStr("nickname");

        // 校验账户字段信息
        if (accountName == null) {
            throw PlatformCoreException.create(500, "用户名不能为空");
        }
        accountChecker.checkAccountName(accountName);
        accountChecker.checkNickName(nickName);

        // 判断账户是否可用
        boolean b = accountManagementService.isAccountNameDuplicate(accountName);
        if (b) {
            throw PlatformCoreException.create(500, "该账户名已被人使用了");
        }
    }

    @Override
    public int register(String personId) throws PlatformCoreException {
        canRegister(personId);
        Person person = personManager.findOneById(personId);
        Kv condition = Kv.create();

        /**
         * 基础外来字段转移
         */
        condition.set("name", person.getStr("name"));
        condition.set("id", person.getStr("id"));
        condition.set("avatar", person.getStr("avatar"));
        condition.set("accountName", person.getStr("accountname"));
        condition.set("nickName", person.getStr("nickname"));
        condition.set("birth", person.getStr("birth"));
        condition.set("sexText", person.getStr("sex_text"));
        condition.set("sexValue", person.getStr("sex_value"));
        condition.set("departmentText", person.getStr("department_text"));
        condition.set("departmentValue", person.getStr("department_value"));

        /**
         * 外键添加
         */
        condition.set("peopleId", person.getStr("test_lsf_rygl_id"));

        /**
         * 新添字段
         */
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        condition.set("test_lsf_zhgl_id", uuidStr);
        condition.set("password", "123456a");
        condition.set("isenabledValue", AccountEnableStatus.NOT_ENABLED.getValue());
        condition.set("isenabledText", AccountEnableStatus.NOT_ENABLED.getText());

        SqlPara sqlPara = Account.DAO.getSqlPara(ACCOUNT_SQL_KEY + "insertOne", condition);
        int update = Db.update(sqlPara);
        return update;
    }
}
