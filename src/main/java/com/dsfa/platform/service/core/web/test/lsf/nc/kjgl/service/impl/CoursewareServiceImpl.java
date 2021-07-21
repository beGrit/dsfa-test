package com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.service.impl;

import com.dsfa.platform.sdk.common.kit.StrKit;
import com.dsfa.platform.sdk.json.JSONArray;
import com.dsfa.platform.sdk.json.JSONObject;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.model.Kjk;
import com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.model.KjkTeachers;
import com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.service.ICoursewareService;
import com.dsfa.platform.service.core.web.test.lsf.nc.szgl.service.ITeacherService;
import com.dsfa.platform.starter.meta.core.ext.MetaPoint;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ICoursewareServiceImpl
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/20
 **/
@Service
public class CoursewareServiceImpl implements ICoursewareService {
    @Autowired
    ITeacherService teacherService;

    @Override
    public void enable(String id) throws PlatformCoreException {
        if (StrKit.isBlank(id)) {
            throw PlatformCoreException.create(410, "参数错误");
        }
        Kjk course = Kjk.dao.findById(id);
        if (course == null) {
            throw PlatformCoreException.create(404, "id 有误");
        } else {
            String oldStatus = course.getEnabledStatusValue();
            if (oldStatus != null && oldStatus.equals("0")) { // 状态为未启用
                course.setEnabledStatusValue("1");
                course.setEnabledStatusText("已启用");
                course.update();
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
        Kjk course = Kjk.dao.findById(id);
        if (course == null) {
            throw PlatformCoreException.create(404, "id 有误");
        } else {
            String oldStatus = course.getEnabledStatusValue();
            if (oldStatus != null && oldStatus.equals("1")) { // 状态为已启用
                course.setEnabledStatusValue("0");
                course.setEnabledStatusText("未启用");
                course.update();
            } else {
                throw PlatformCoreException.create(410, "启用状态更改失败");
            }
        }
    }

    @Override
    public void beforePersistData(MetaPoint metaPoint, PersistData persistData) throws PlatformCoreException {
        // 表单校验操作,验证字段是否合法
        checkCourseware(metaPoint, persistData);

        // 级联操作,转移状态
        updateTeachers(metaPoint, persistData);
    }

    /**
     * 校验课件表单信息
     * @param metaPoint
     * @param persistData
     * @throws PlatformCoreException
     */
    public void checkCourseware(MetaPoint metaPoint, PersistData persistData) throws PlatformCoreException {
        // 校验姓名

    }

    /**
     * 变更课件下的教师列表,需要更新教师表的信息
     * @param metaPoint
     * @param persistData
     * @throws PlatformCoreException
     */
    public void updateTeachers(MetaPoint metaPoint, PersistData persistData) throws PlatformCoreException { // 转移课程下教师列表的状态
        String namespace = persistData.getNamespace().replace(".", "_");
        JSONArray array = persistData.getJSONArray(namespace + "_" + "teachers");

        // part1.更新teacher的信息
        String kcId = persistData.getStr(namespace + "." + namespace + "_id");
        if (kcId == null) {
            // 新增请求
            return;
        }
        List<KjkTeachers> oldTeachers = KjkTeachers.dao.find("select * from test_lsf_nc_kjk_teachers");
        Map<String, Boolean> oldIds = new HashMap<>(); // 课件下旧的老师们
        for (KjkTeachers teacher : oldTeachers) {
            if (teacher.getTestLsfNcKjkId().equals(kcId) && StrKit.equals(teacher.getDsDeleted(), "0")) {
                oldIds.put(teacher.getTeacherId(), true);
            }
        }

        Map<String, Boolean> newIds = new HashMap<>(); // 课件下新的老师们
        for (int i = 0; i < array.size(); i++) {
            JSONObject data = array.getJSONObject(i);
            String teacherId = data.getString(namespace + "_" + "teachers" + "." + "teacher_id");
            newIds.put(teacherId, true);
        }

        List<String> removeIdList = new ArrayList<>(); // 需要在课件中删除的老师们
        List<String> addIdList = new ArrayList<>(); // 需要在课件中新增的老师们

        for (String id : newIds.keySet()) { // 填充removeIdList
            boolean b = oldIds.containsKey(id);
            if (!b) { // 添加到新老师列表
                addIdList.add(id);
            }
        }
        for (String id : oldIds.keySet()) { // 填充newIdList
            boolean b = newIds.containsKey(id);
            if (!b) { // 添加到删除老师列表
                removeIdList.add(id);
            }
        }

        teacherService.batchTeach(addIdList, kcId);
        teacherService.batchNotTeach(removeIdList, kcId);
    }
}
