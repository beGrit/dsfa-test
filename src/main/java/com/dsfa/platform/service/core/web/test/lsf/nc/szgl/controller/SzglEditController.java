package com.dsfa.platform.service.core.web.test.lsf.nc.szgl.controller;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.web.test.lsf.nc.kcgl.dto.KcTeacherDTO;
import com.dsfa.platform.service.core.web.test.lsf.nc.szgl.service.ITeacherService;
import com.dsfa.platform.starter.meta.core.base.DsfaBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName SzglEditController
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/21
 **/
public class SzglEditController extends DsfaBaseController {

    @Autowired
    ITeacherService teacherService;

    @GetMapping(value = "kcList")
    public Result getKCList(@RequestParam String id) {
        List<KcTeacherDTO> data = teacherService.getTeachingKCList(id);
        return success(data);
    }
}
