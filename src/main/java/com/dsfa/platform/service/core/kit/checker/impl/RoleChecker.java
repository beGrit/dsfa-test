package com.dsfa.platform.service.core.kit.checker.impl;

import com.dsfa.platform.sdk.json.JSONObject;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.kit.checker.DataChecker;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.stereotype.Component;

@Component(value = "roleChecker")
public class RoleChecker implements DataChecker {
    private static final String namespace = "test.lsf.qxgl.";

    @Override
    public void checkAll(PersistData json) throws PlatformCoreException {

    }

    @Override
    public void checkRequired(PersistData json) throws PlatformCoreException {
        JSONObject data = json.getData();

        String name = data.getString(namespace + "name");
        String sn = data.getString(namespace + "sn");
        JSONObject isEnabled = data.getJSONObject(namespace + "isenabled");

        if (name == null || "".equals(name)) {
            throw PlatformCoreException.create(500, "必填字段： name");
        }
        if (sn == null || "".equals(sn)) {
            throw PlatformCoreException.create(500, "必填字段： sn");
        }
        if (isEnabled == null) {
            throw PlatformCoreException.create(500, "必填字段： isenabled");
        }

        checkName(name);
        checkSN(sn);
        checkIsEnabled(isEnabled);
    }

    @Override
    public void checkUnRequired(PersistData json) throws PlatformCoreException {

    }

    @Override
    public void customCheck(PersistData json) throws PlatformCoreException {

    }

    /**
     * 校验权限名
     * @param name
     * @throws PlatformCoreException
     */
    private void checkName(String name) throws PlatformCoreException {
        if (name == null || "".equals(name)) {
            return;
        }
    }

    /**
     * 校验权限编号
     * @param sn
     * @throws PlatformCoreException
     */
    private void checkSN(String sn) throws PlatformCoreException {
        if (sn == null || "".equals(sn)) {
            return;
        }

    }

    /**
     * 校验权限是否有效
     * @param kv
     * @throws PlatformCoreException
     */
    private void checkIsEnabled(JSONObject kv) throws PlatformCoreException {
        if (kv == null) {
            return;
        }
    }
}
