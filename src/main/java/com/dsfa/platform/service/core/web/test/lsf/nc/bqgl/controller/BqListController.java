package com.dsfa.platform.service.core.web.test.lsf.nc.bqgl.controller;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.web.test.lsf.nc.bqgl.service.ITagService;
import com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.service.ICoursewareService;
import com.dsfa.platform.starter.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BqListController
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/21
 **/
@RestController
@RequestMapping("/test/lsf/nc/bqgl/bqList")
public class BqListController extends BaseController {
    @Autowired
    ITagService tagService;

    @PostMapping("/enable")
    public Result enable(@RequestParam String id) {
        tagService.enable(id);
        return success();
    }

    @PostMapping("/disable")
    public Result disable(@RequestParam String id) {
        tagService.disable(id);
        return success();
    }
}
