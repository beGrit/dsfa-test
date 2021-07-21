package com.dsfa.platform.service.core.web.test.lsf.nc.szgl.service.impl;

import com.dsfa.platform.sdk.common.kit.StrKit;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.nc.kcgl.dto.KcTeacherDTO;
import com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.model.KjkTeachers;
import com.dsfa.platform.service.core.web.test.lsf.nc.szgl.dto.TeacherDto;
import com.dsfa.platform.service.core.web.test.lsf.nc.szgl.model.Szgl;
import com.dsfa.platform.service.core.web.test.lsf.nc.szgl.model.Teacher;
import com.dsfa.platform.service.core.web.test.lsf.nc.szgl.service.ITeacherService;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import com.dsfa.platform.starter.web.base.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ITeacherServiceImpl
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/20
 **/
@Service
public class TeacherServiceImpl extends BaseService implements ITeacherService {

    @Override
    public void beforePersistData(MetaPoint metaPoint, PersistData persistData) throws PlatformCoreException {
        // 校验数据

    }

    @Override
    public TeacherDto findById(String id) throws PlatformCoreException {
        if (!StrKit.isNotBlank(id)) {
            throw PlatformCoreException.create(416, "id有误,请求范围无效");
        }
        Teacher teacher = (Teacher) Teacher.DAO.findById(id);
        if (teacher == null) {
            throw PlatformCoreException.create(404, "未找到相关教师");
        }
        TeacherDto teacherDto = new TeacherDto();
        BeanUtils.copyProperties(teacher, teacherDto);
        return teacherDto;
    }

    @Override
    public void top(String id) throws PlatformCoreException {
        Szgl teacher = Szgl.dao.findById(id);
        if (teacher == null || teacher.getDsDeleted().equals("1")) { // 不存在或者被删除了
            throw PlatformCoreException.create(404, "id 有误");
        } else {
            String topValue = teacher.getStatusTopValue();
            if (topValue.equals("0")) {
                teacher.setStatusTopValue("1");
                teacher.setStatusTopText("是");
                teacher.update();
            } else {
                throw PlatformCoreException.create(410, "更新置顶状态失败");
            }
        }
    }

    @Override
    public void sync(String id) throws PlatformCoreException {
        List<KjkTeachers> teachers = KjkTeachers.dao.find("select * from test_lsf_nc_kjk_teachers");
        KjkTeachers teacher1 = null;
        for (KjkTeachers teacher : teachers) {
            String teacherId = teacher.getTeacherId();
            if (teacherId != null && teacher.getDeleted().equals("0")) {
                if (teacherId.equals(id)) {
                    teacher1 = teacher;
                    break;
                }
            }
        }
        if (teacher1 == null) {
            throw PlatformCoreException.create(404, "同步失败,未找到相关人员");
        } else {
            Szgl teacher2 = Szgl.dao.findById(id);
            try {
                // 字段copy
                BeanUtils.copyProperties(teacher2, teacher1);
            } catch (Exception e) {
                throw PlatformCoreException.create(500, "拷贝字段失败");
            }
            teacher1.update();
        }
    }

    @Override
    public void enable(String id) throws PlatformCoreException {
        if (StrKit.isBlank(id)) {
            throw PlatformCoreException.create(410, "参数错误");
        }
        Szgl teacher = Szgl.dao.findById(id);
        if (teacher == null) {
            throw PlatformCoreException.create(404, "id 有误");
        } else {
            String oldStatus = teacher.getStatusValue();
            if (oldStatus != null && oldStatus.equals("0")) { // 状态为未启用
                teacher.setStatusValue("1");
                teacher.setStatusText("已启用");
                teacher.update();
            } else {
                throw PlatformCoreException.create(410, "启用状态更改失败");
            }
        }
    }

    @Override
    public void disable(String id) throws PlatformCoreException {
        if (StrKit.isBlank(id)) {
            throw PlatformCoreException.create(410, "参数错误");
        }
        Szgl teacher = Szgl.dao.findById(id);
        if (teacher == null) {
            throw PlatformCoreException.create(404, "id 有误");
        } else {
            String oldStatus = teacher.getStatusValue();
            if (oldStatus != null && oldStatus.equals("1")) { // 状态为已启用
                teacher.setStatusValue("0");
                teacher.setStatusText("未启用");
                teacher.update();
            } else {
                throw PlatformCoreException.create(410, "启用状态更改失败");
            }
        }
    }

    @Override
    public void batchNotTeach(List<String> teacherIDList, String kcId) throws PlatformCoreException { // idList中的教师需要解除与课程kcID的关系
        if (teacherIDList == null || teacherIDList.size() == 0) {
            return;
        } else {
            List<Szgl> teachers = Szgl.dao.find("select * from test_lsf_nc_szgl");
            List<Szgl> targetList = new ArrayList<>();
            for (int i = 0; i < teachers.size(); i++) {
                Szgl teacher = teachers.get(i);
                for (int j = 0; j < teacherIDList.size(); j++) {
                    String id2 = teacherIDList.get(j);
                    if (StrKit.equals(teacher.getTestLsfNcSzglId(), id2)) {
                        targetList.add(teacher);
                    }
                }
            }

            for (Szgl teacher : targetList) { // 批量更新
                int oldNum = Integer.parseInt(teacher.getNumOfLeasson());

                // 修改教师中课程数量字段的状态
                teacher.setNumOfLeasson(String.valueOf(oldNum - 1));
                teacher.update();
            }
        }
    }

    @Override
    public void batchTeach(List<String> teacherIDList, String kcId) throws PlatformCoreException { // idList中的教师需要增加与课程kcId的关系
        if (teacherIDList == null || teacherIDList.size() == 0) {
            return;
        } else {
            List<Szgl> teachers = Szgl.dao.find("select * from test_lsf_nc_szgl");
            List<Szgl> targetList = new ArrayList<>();
            for (int i = 0; i < teachers.size(); i++) {
                Szgl teacher = teachers.get(i);
                for (int j = 0; j < teacherIDList.size(); j++) {
                    String id2 = teacherIDList.get(j);
                    if (StrKit.equals(teacher.getTestLsfNcSzglId(), id2)) {
                        targetList.add(teacher);
                    }
                }
            }

            for (Szgl teacher : targetList) { // 批量更新
                int oldNum = Integer.parseInt(teacher.getNumOfLeasson());

                // 修改教师中课程数量字段的状态
                teacher.setNumOfLeasson(String.valueOf(oldNum + 1));
                teacher.update();
            }
        }
    }

    @Override
    public List<KcTeacherDTO> getTeachingKCList(String id) throws PlatformCoreException {
        return null;
    }
}
