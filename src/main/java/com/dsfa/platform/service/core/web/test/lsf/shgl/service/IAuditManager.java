package com.dsfa.platform.service.core.web.test.lsf.shgl.service;


import com.dsfa.platform.service.core.exception.PlatformCoreException;
import org.springframework.transaction.annotation.Transactional;

/**
 * 审核(Audit)管理业务
 */
public interface IAuditManager {

    /**
     * 提交审核操作
     * @param id 主键(personId)
     * @throws PlatformCoreException
     */
    @Transactional
    void commit(String id) throws PlatformCoreException;

    /**
     * step1.对用户提交的信息做检查,重点检查想要申请的账户信息
     * step2.通过账户信息,将账户信息连同基本信息入账户信息库
     * step3.修改审核状态
     * @param id 主键(personId)
     * @throws PlatformCoreException
     */
    @Transactional
    void admit(String id) throws PlatformCoreException;

    /**
     * 不同意提交的审核
     *
     * @param id
     * @return
     * @throws PlatformCoreException
     */
    @Transactional
    void unAdmit(String id) throws PlatformCoreException;
}
