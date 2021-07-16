package com.dsfa.platform.service.core.web.test.lsf.zhgl.service;

import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;

public interface PasswordDEService {
    /**
     * 密码加密（自定义策略）
     * @param str
     * @return
     * @throws PlatformCoreException
     */
    String encode(String str) throws PlatformCoreException;

    /**
     * 密码解密
     * @param str
     * @return
     * @throws PlatformCoreException
     */
    String decode(String str) throws PlatformCoreException;

    /**
     * 加密并修改表单
     * @param persistData
     * @return
     * @throws PlatformCoreException
     */
    String encode(PersistData persistData) throws PlatformCoreException;

    /**
     * 解密并修改表单
     * @param persistData
     * @return
     * @throws PlatformCoreException
     */
    String decode(PersistData persistData) throws PlatformCoreException;
}
