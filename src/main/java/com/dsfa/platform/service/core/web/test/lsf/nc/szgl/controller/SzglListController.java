package com.dsfa.platform.service.core.web.test.lsf.nc.szgl.controller;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.web.test.lsf.nc.szgl.service.ITeacherService;
import com.dsfa.platform.starter.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName SzglListController
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/20
 **/
@RestController
@RequestMapping("/test/lsf/nc/szgl/szList")
public class SzglListController extends BaseController {
    @Autowired
    ITeacherService teacherService;

    @GetMapping(value = "one")
    public Result findOneById(@RequestParam("id") String id) {
        return success(teacherService.findById(id));
    }

    @PostMapping("/enable")
    public Result enable(@RequestParam String id) {
        teacherService.enable(id);
        return success();
    }

    @PostMapping("/disable")
    public Result disable(@RequestParam String id) {
        teacherService.disable(id);
        return success();
    }

    @PostMapping(value = "top")
    public Result top(@RequestParam("id") String id) {
        teacherService.top(id);
        return success();
    }

    @PostMapping(value = "sync")
    public Result sync(@RequestParam("id") String id) {
        teacherService.sync(id);
        return success();
    }
}
