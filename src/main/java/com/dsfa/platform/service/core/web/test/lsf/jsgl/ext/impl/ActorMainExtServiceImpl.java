package com.dsfa.platform.service.core.web.test.lsf.jsgl.ext.impl;

import com.dsfa.platform.sdk.json.JSONArray;
import com.dsfa.platform.sdk.json.JSONObject;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.jsgl.ext.ActorMainExtService;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActorMainExtServiceImpl implements ActorMainExtService {
    @Override
    public void checkRoleList(JSONArray dataList) throws PlatformCoreException {
        if (dataList == null || dataList.size() == 0) {
            return;
        } else {
            checkSubListDuplication("test_lsf_jsgl_rolelist", "roleid", dataList);
        }
    }

    @Override
    public void checkAccountList(JSONArray dataList) throws PlatformCoreException {
        if (dataList == null || dataList.size() == 0) {
            return;
        } else {
            checkSubListDuplication("test_lsf_jsgl_accountlist", "accountid", dataList);
        }
    }

    @Override
    public void initialActorOrder(PersistData persistData) {
    }

    @Override
    public void checkRoleList(PersistData persistData) throws PlatformCoreException {
        String dataPrefix = persistData.getNamespace().replace(".", "_");
        JSONArray roleList = persistData.getData().getJSONArray(dataPrefix + "_" + "rolelist");
        this.checkRoleList(roleList);
    }

    @Override
    public void checkAccountList(PersistData persistData) throws PlatformCoreException {
        String dataPrefix = persistData.getNamespace().replace(".", "_");
        JSONArray accountList = persistData.getData().getJSONArray(dataPrefix + "_" + "accountlist");
        this.checkAccountList(accountList);
    }

    /**
     * 校验字表中是否有重复的项目
     * @param pid      字表标识
     * @param fid      字表外键字段标识
     * @param dataList 字表列表
     */
    private void checkSubListDuplication(String pid, String fid, JSONArray dataList) {
        // 判断是否有重复，不做删减
        Map<String, Boolean> map = new HashMap<>();
        boolean b = true;
        for (Object obj : dataList) {
            JSONObject item = (JSONObject) obj;
            String fullKey = pid + "." + fid;
            String val = item.getString(fullKey);
            if (map.containsKey(val)) {
                b = false;
                break;
            } else {
                map.put(val, true);
            }
        }
        if (!b) {
            throw PlatformCoreException.create(500, "表单中 " + pid + " 有重复项！");
        }
    }
}
