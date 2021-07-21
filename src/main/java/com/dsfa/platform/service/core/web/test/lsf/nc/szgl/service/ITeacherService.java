package com.dsfa.platform.service.core.web.test.lsf.nc.szgl.service;

import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.nc.kcgl.dto.KcTeacherDTO;
import com.dsfa.platform.service.core.web.test.lsf.nc.szgl.dto.TeacherDto;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @InterfaceName ITeacherService
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/20
 **/
public interface ITeacherService {
    void beforePersistData(MetaPoint metaPoint, PersistData persistData) throws PlatformCoreException;

    TeacherDto findById(String id) throws PlatformCoreException;

    /**
     * 修改置顶状态
     * @param id
     * @throws PlatformCoreException
     */
    @Transactional
    void top(String id) throws PlatformCoreException;

    /**
     * 同步信息到课件冗余字段中
     * @param id
     * @throws PlatformCoreException
     */
    @Transactional
    void sync(String id) throws PlatformCoreException;

    @Transactional
    void enable(String id) throws PlatformCoreException;

    @Transactional
    void disable(String id)throws PlatformCoreException;

    /**
     * 一些教师不教某门课了
     * @param teacherIDList
     * @param kcId
     * @throws PlatformCoreException
     */
    @Transactional
    void batchNotTeach(List<String> teacherIDList, String kcId) throws PlatformCoreException;

    /**
     * 一些教师开始教某门课了
     * @param teacherIDList
     * @param kcId
     * @throws PlatformCoreException
     */
    @Transactional
    void batchTeach(List<String> teacherIDList, String kcId) throws PlatformCoreException;

    /**
     * 根据教师id获取其现在涉及的课程
     * @param id 教师id
     * @return
     * @throws PlatformCoreException
     */
    @Transactional
    List<KcTeacherDTO> getTeachingKCList(String id) throws PlatformCoreException;
}
