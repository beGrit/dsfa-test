package com.dsfa.platform.service.core.kit.checker.impl;

import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.kit.checker.DataChecker;
import com.dsfa.platform.service.core.web.test.lsf.jsgl.domain.Actor;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(value = "ActorChecker")
public class ActorChecker implements DataChecker {
    private static final String namespace = "test_lsf_jsgl.";

    @Override
    public void checkAll(PersistData persistData) throws PlatformCoreException {
        String name = persistData.getStr(namespace + "name");
        String sn = persistData.getStr(namespace + "sn");
        String enableStatus = persistData.getStr(namespace + "isenabled");

        checkName(name);
        checkSN(sn);
        checkEnableStatus(enableStatus);
    }

    @Override
    public void checkRequired(PersistData persistData) throws PlatformCoreException {
        checkRequireStatus(persistData);
    }

    @Override
    public void checkUnRequired(PersistData persistData) throws PlatformCoreException {

    }

    @Override
    public void customCheck(PersistData persistData) throws PlatformCoreException {
        Actor actor = Actor.getInstance(persistData, persistData.getNamespace());
        checkRequireStatus(persistData);
        checkAll(persistData);
    }

    /**
     * 检查必填性
     *
     * @throws PlatformCoreException
     */
    public void checkRequireStatus(PersistData persistData) throws PlatformCoreException {
        String name = persistData.getStr(namespace + "name");
        String sn = persistData.getStr(namespace + "sn");
        String enableStatus = persistData.getStr(namespace + "isenabled");

        if (name == null || "".equals(name)) {
            throw PlatformCoreException.create(500, "name字段必填");
        }
        if (sn == null || "".equals(sn)) {
            throw PlatformCoreException.create(500, "name字段必填");
        }
        if (enableStatus == null || "".equals(enableStatus)) {
            throw PlatformCoreException.create(500, "name字段必填");
        }
    }

    public void checkName(String name) throws PlatformCoreException { // 校验规则: XXX管理员
        if (name == null || "".equals(name)) {
            return;
        }
        String p = "^([\\u4e00-\\u9fa5]){2,7}(\\u7ba1)(\\u7406)(\\u5458)$";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            String firstChar = matcher.group(1);
            if (firstChar.equals("_")) {
                throw PlatformCoreException.create(500, "姓名首字母不能是_");
            }
        } else {
            throw PlatformCoreException.create(500, "姓名校验格式有误: XXX管理员");
        }
    }

    public void checkSN(String SN) throws PlatformCoreException {
        if (SN == null || "".equals(SN)) {
            return;
        }
    }

    public void checkEnableStatus(String status) throws PlatformCoreException {
        if (status == null || "".equals(status)) {
            return;
        }
    }
}
