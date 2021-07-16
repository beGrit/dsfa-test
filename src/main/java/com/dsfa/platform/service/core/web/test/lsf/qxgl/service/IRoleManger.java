package com.dsfa.platform.service.core.web.test.lsf.qxgl.service;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.exception.PlatformCoreException;

public interface IRoleManger {
    /**
     * 判断是否是根节点
     * @param id
     * @return
     * @throws PlatformCoreException
     */
    Result isRoot(String id) throws PlatformCoreException;
}
