package com.dsfa.platform.service.core.web.test.lsf.zhgl.service.impl;

import com.dsfa.platform.sdk.common.Result;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.metadata.AccountEnableStatus;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.IAccountManager;
import com.dsfa.platform.starter.db.jfinal.kit.Kv;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.Db;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.Record;
import com.dsfa.platform.starter.db.jfinal.plugin.activerecord.SqlPara;
import com.dsfa.platform.starter.web.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class AccountManagerImpl extends BaseService implements IAccountManager {
    private static final String SQL_KEY = "test.lsf.zhgl.account.sql.";

    @Override
    public Result enableAccount(String id) throws PlatformCoreException {
        this.updateEnabledStatus(AccountEnableStatus.NOT_ENABLED, AccountEnableStatus.ENABLED, id);
        return success();
    }

    @Override
    public Result disableAccount(String id) throws PlatformCoreException {
        this.updateEnabledStatus(AccountEnableStatus.ENABLED, AccountEnableStatus.NOT_ENABLED, id);
        return success();
    }

    @Override
    public Result resetPassword(String id) throws PlatformCoreException {
        AccountEnableStatus accountEnableStatus = getEnabledStatus(id);
        if (accountEnableStatus == AccountEnableStatus.NOT_ENABLED) { // 未启用状态
            SqlPara sqlPara = Db.getSqlPara(SQL_KEY + "resetPassword",
                    Kv.create().set("test_lsf_zhgl_id", id));
            Db.update(sqlPara);
        } else {
            throw PlatformCoreException.create(500, "账户未在未启用状态");
        }
        return success(Kv.create().set("newPassword", "123456a"));
    }

    @Override
    public boolean isRegistered(String personId) {
        SqlPara sqlPara = Db.getSqlPara(SQL_KEY + "exitsByPersonId", Kv.by("personid", personId));
        Record record = Db.findFirst(sqlPara);
        if (record == null) {
            return false;
        } else {
            Long count = record.get("COUNT(*)");
            if (count == 0L) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public Result getRandomAccountName() {
        String res = "";
        boolean control = true;
        while (control) {
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
                //输出字母还是数字
                if ("char".equals(charOrNum)) {
                    //输出是大写字母还是小写字母
                    int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                    res += (char) (random.nextInt(26) + temp);
                } else if ("num".equals(charOrNum)) {
                    res += String.valueOf(random.nextInt(10));
                }
            }
            if (this.isAccountNameDuplicate(res)) {
                res = "";
            } else {
                control = false;
            }
        }
        return success(Kv.create().set("accountname", res));
    }

    public int updateEnabledStatus(AccountEnableStatus oldStatus, AccountEnableStatus newStatus, String id) {
        Kv kv = Kv.create();
        kv.set("newEnabledValue", newStatus.getValue());
        kv.set("newEnabledText", newStatus.getText());
        kv.set("oldEnabledValue", oldStatus.getValue());
        kv.set("oldEnabledText", oldStatus.getText());
        kv.set("id", id);
        SqlPara sqlPara = Db.getSqlPara(SQL_KEY + "updateEnabledStatus", kv);
        int rtn = Db.update(sqlPara);
        return rtn;
    }

    /**
     * 判断用户名是否重复
     *
     * @param accountName 指定的需要校验的用户名
     * @return false(表示用户名不重复) true (表示用户名重复)
     */
    public boolean isAccountNameDuplicate(String accountName) {
        SqlPara sqlPara = Db.getSqlPara(SQL_KEY + "countRecordByAccountName", Kv.by("accountname", accountName));
        Record record = Db.findFirst(sqlPara);
        Long count = record.getLong("COUNT(*)");
        if (count == 0L) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取当前账户的启用状态
     *
     * @param id accountId(主键)
     * @return null(代表参数有问题) AccountEnableStatus
     */
    public AccountEnableStatus getEnabledStatus(String id) {
        SqlPara sqlPara = Db.getSqlPara(SQL_KEY + "findById", Kv.by("id", id));
        Record record = Db.findFirst(sqlPara);
        if (record == null) {
            return null;
        }
        int value = Integer.parseInt(record.get("isenabled_value"));
        if (value == 0) {
            return AccountEnableStatus.NOT_ENABLED;
        } else {
            return AccountEnableStatus.ENABLED;
        }
    }
}
