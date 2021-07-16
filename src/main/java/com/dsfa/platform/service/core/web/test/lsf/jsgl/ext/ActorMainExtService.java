package com.dsfa.platform.service.core.web.test.lsf.jsgl.ext;

import com.dsfa.platform.sdk.json.JSONArray;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;

public interface ActorMainExtService {
    /**
     * 校验权限列表的逻辑合理性
     * @param dataList
     * @throws PlatformCoreException
     */
    void checkRoleList(JSONArray dataList) throws PlatformCoreException;

    /**
     * 校验用户列表的逻辑合理性
     * @param dataList
     * @throws PlatformCoreException
     */
    void checkAccountList(JSONArray dataList) throws PlatformCoreException;

    void initialActorOrder(PersistData persistData) throws PlatformCoreException;

    void checkRoleList(PersistData persistData) throws PlatformCoreException;

    void checkAccountList(PersistData persistData) throws PlatformCoreException;
}
