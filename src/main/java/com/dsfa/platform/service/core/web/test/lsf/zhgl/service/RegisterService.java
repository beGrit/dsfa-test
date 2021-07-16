package com.dsfa.platform.service.core.web.test.lsf.zhgl.service;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.model.Account;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注册业务
 */
public interface RegisterService {
    /**
     * 注册服务
     * @param registerForm
     * @return
     * @throws PlatformCoreException
     */
    @Transactional
    Result register(PersistData registerForm) throws PlatformCoreException;

    /**
     * 判断是否可注册账号
     * @param registerForm
     * @throws PlatformCoreException
     */
    void canRegister(PersistData registerForm) throws PlatformCoreException;

    /**
     *
     * @param personId
     * @throws PlatformCoreException
     */
    void canRegister(String personId) throws PlatformCoreException;

    /**
     * 为用户在后台注册(在账户表新增一条数据)
     * @param personId
     * @return
     * @throws PlatformCoreException
     */
    @Transactional
    int register(String personId) throws PlatformCoreException;
}
