package com.dsfa.platform.service.core.web.test.lsf.nc.bqgl.service.impl;

import com.dsfa.platform.sdk.common.kit.StrKit;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.nc.bqgl.model.Bqgl;
import com.dsfa.platform.service.core.web.test.lsf.nc.bqgl.service.ITagService;
import com.dsfa.platform.service.core.web.test.lsf.nc.kjgl.model.Kjk;
import org.springframework.stereotype.Service;

/**
 * @ClassName ITagServiceImpl
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/21
 **/
@Service
public class TagServiceImpl implements ITagService {

    @Override
    public void enable(String id) throws PlatformCoreException {
        if (StrKit.isBlank(id)) {
            throw PlatformCoreException.create(410, "参数错误");
        }
        Bqgl tag = Bqgl.dao.findById(id);
        if (tag == null) {
            throw PlatformCoreException.create(404, "id 有误");
        } else {
            String oldStatus = tag.getStatusValue();
            if (oldStatus != null && oldStatus.equals("0")) { // 状态为未启用
                tag.setStatusValue("1");
                tag.setStatusText("已启用");
                tag.update();
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
        Bqgl tag = Bqgl.dao.findById(id);
        if (tag == null) {
            throw PlatformCoreException.create(404, "id 有误");
        } else {
            String oldStatus = tag.getStatusValue();
            if (oldStatus != null && oldStatus.equals("1")) { // 状态为未启用
                tag.setStatusValue("0");
                tag.setStatusText("未启用");
                tag.update();
            } else {
                throw PlatformCoreException.create(410, "启用状态更改失败");
            }
        }
    }
}
