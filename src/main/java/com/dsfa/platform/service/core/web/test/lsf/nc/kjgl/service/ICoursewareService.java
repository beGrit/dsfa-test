package com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.service;

import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;

/**
 * @ClassName ICoursewareService
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/20
 **/
public interface ICoursewareService {
    /**
     * 启用课件
     * @param id
     * @throws PlatformCoreException
     */
    void enable(String id) throws PlatformCoreException;

    /**
     * 禁用课件
     * @param id
     * @throws PlatformCoreException
     */
    void disable(String id) throws PlatformCoreException;

    /**
     * 处理保存前置事件
     * @param metaPoint
     * @param persistData
     * @throws PlatformCoreException
     */
    void beforePersistData(MetaPoint metaPoint, PersistData persistData) throws PlatformCoreException;
}
