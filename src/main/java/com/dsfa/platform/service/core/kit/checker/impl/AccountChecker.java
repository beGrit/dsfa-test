package com.dsfa.platform.service.core.kit.checker.impl;

import com.dsfa.platform.sdk.json.JSONObject;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.kit.checker.DataChecker;
import com.dsfa.platform.service.core.web.test.lsf.rygl.model.Person;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(value = "accountChecker")
public class AccountChecker implements DataChecker {
    private static final String namespace = "test_lsf_zhgl.";

    @Autowired
    PersonChecker personChecker;

    @Override
    public void checkAll(PersistData json) throws PlatformCoreException {

    }

    @Override
    public void checkRequired(PersistData json) throws PlatformCoreException {
        JSONObject data = json.getData();

        String accountName = data.getString(namespace + "accountname");
        String password = data.getString(namespace + "password");
        String nickName = data.getString(namespace + "nickname");
        JSONObject isEnabled = data.getJSONObject(namespace + "isenabled");

        if (accountName == null || "".equals(accountName)) {
            throw PlatformCoreException.create(500, "必填字段:accountname");
        }
        if (password == null || "".equals(password)) {
            throw PlatformCoreException.create(500, "必填字段:password");
        }
        if (nickName == null || "".equals(nickName)) {
            throw PlatformCoreException.create(500, "必填字段:nickname");
        }

        this.checkAccountName(accountName);
        this.checkPassword(password);
        this.checkNickName(nickName);
        this.checkIsEnabled(isEnabled);
    }

    @Override
    public void checkUnRequired(PersistData json) throws PlatformCoreException {
    }

    @Override
    public void customCheck(PersistData persistData) throws PlatformCoreException {
        checkRequired(persistData);
        checkUnRequired(persistData);
    }

    public void checkAccountName(String accountName) throws PlatformCoreException { // 由数字组成，长度6-11位
        if (accountName == null || "".equals(accountName)) {
            return;
        } else {
            String p = "^\\d{6,11}$";
            Pattern pattern = Pattern.compile(p);
            Matcher matcher = pattern.matcher(accountName);
            if (matcher.matches()) {
                return;
            } else {
                throw PlatformCoreException.create(500, "账户校验格式有误：(由数字组成，长度6-11位)");
            }
        }
    }

    public void checkPassword(String password) throws PlatformCoreException { // 由数字和字母组成，长度6-11位
        if (password == null || "".equals(password)) {
            return;
        } else {
            String p = "^(?:\\d|[a-zA-Z]){6,11}$";
            Pattern pattern = Pattern.compile(p);
            Matcher matcher = pattern.matcher(password);
            if (matcher.matches()) {
                return;
            } else {
                throw PlatformCoreException.create(500, "密码校验格式有误：( 由数字和字母组成，长度6-11位)");
            }
        }
    }

    public void checkNickName(String nickName) throws PlatformCoreException {
        if (nickName == null || "".equals(nickName)) {
            return;
        } else {
            String p = "^(\\w)(?:\\w){3,6}$";
            Pattern pattern = Pattern.compile(p);
            Matcher matcher = pattern.matcher(nickName);
            if (matcher.matches()) {
                String firstChar = matcher.group(1);
                if (firstChar.equals("_")) {
                    throw PlatformCoreException.create(500, "昵称首字母不能是_");
                }
            } else {
                throw PlatformCoreException.create(500, "昵称校验格式有误:(由字母、数字、下划线组成(4-7位)，首字母不能市下划线开头)");
            }
        }
    }

    public void checkIsEnabled(JSONObject kv) throws PlatformCoreException {

    }

    public void checkRequired(Person person) throws PlatformCoreException {

        String accountName = person.getStr("accountname");
        String password = person.getStr("password");
        String nickName = person.getStr("nickname");

        if (accountName == null || "".equals(accountName)) {
            throw PlatformCoreException.create(500, "必填字段:accountname");
        }
        if (password == null || "".equals(password)) {
            throw PlatformCoreException.create(500, "必填字段:password");
        }
        if (nickName == null || "".equals(nickName)) {
            throw PlatformCoreException.create(500, "必填字段:nickname");
        }

        this.checkAccountName(accountName);
        this.checkPassword(password);
        this.checkNickName(nickName);
    }
}
