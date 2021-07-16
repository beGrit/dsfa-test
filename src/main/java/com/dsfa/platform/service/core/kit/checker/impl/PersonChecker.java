package com.dsfa.platform.service.core.kit.checker.impl;

import com.dsfa.platform.sdk.json.JSONObject;
import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.kit.factory.BirthFactory;
import com.dsfa.platform.service.core.kit.checker.DataChecker;
import com.dsfa.platform.service.core.web.test.lsf.jsgl.domain.Actor;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(value = "PersonChecker")
public class PersonChecker implements DataChecker {
    private static final String namespace = "test_lsf_rygl.";

    @Override
    public void checkAll(PersistData json) throws PlatformCoreException {
        checkRequired(json);
        checkUnRequired(json);
    }

    @Override
    public void checkRequired(PersistData json) throws PlatformCoreException {
        JSONObject data = json.getData();
        String name = data.getString(namespace + "name");
        String id = data.getString(namespace + "id");
        String birth = data.getString(namespace + "birth");
        JSONObject sex = data.getJSONObject(namespace + "sex");
        JSONObject department = data.getJSONObject(namespace + "department");

        // 判空操作
        if (name == null || "".equals(name)) {
            throw PlatformCoreException.create(500, "姓名字段为空");
        }
        if (id == null || "".equals(id)) {
            throw PlatformCoreException.create(500, "身份证字段为空");
        }
        if (birth == null || "".equals(birth)) {
            throw PlatformCoreException.create(500, "出生年月字段为空");
        }

        checkName(name);
        checkId(id);
        checkBirth(birth);
        checkSex(sex);
        checkDepartment(department);
    }

    @Override
    public void checkUnRequired(PersistData json) throws PlatformCoreException {
        JSONObject data = json.getData();
        String tel = data.getString(namespace + "contacttelphone");
        String contactAddress = data.getString(namespace + "contactaddress");
        JSONObject nationality = data.getJSONObject(namespace + "nationality");

        checkTel(tel);
        checkContactAddress(contactAddress);
        checkNationality(nationality);
    }

    @Override
    public void customCheck(PersistData json) throws PlatformCoreException {
        try {
            this.checkRequired(json);
            this.checkUnRequired(json);
        } catch (PlatformCoreException e) {
            throw e;
        }
    }

    /**
     * 校验姓名
     *
     * @param name
     * @return
     */
    private void checkName(String name) throws PlatformCoreException { // 由汉字组成(2-7位)
        if (name == null || "".equals(name)) {
            return;
        }
        String p = "^([\\u4e00-\\u9fa5]){2,7}$";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            String firstChar = matcher.group(1);
            if (firstChar.equals("_")) {
                throw PlatformCoreException.create(500, "姓名首字母不能是_");
            }
        } else {
            throw PlatformCoreException.create(500, "姓名校验格式有误");
        }
    }

    /**
     * 校验身份证信息
     *
     * @return
     */
    private void checkId(String IDNumber) throws PlatformCoreException {
        if (IDNumber == null || "".equals(IDNumber)) {
            return;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression =
                "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|"
                        + "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        boolean matches = IDNumber.matches(regularExpression);
        // 判断第18位校验值
        if (matches) {
            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    // 前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    // 这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase()
                            .equals(String.valueOf(idCardLast).toUpperCase())) {
                        // 正确
                    } else {
                        System.out.println("身份证最后一位:" + String.valueOf(idCardLast).toUpperCase()
                                + "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                        throw PlatformCoreException.create(500, "身份证最后一位有误");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    throw PlatformCoreException.create(500, "身份证信息有误");
                }
            } else {
                throw PlatformCoreException.create(500, "身份证信息有误");
            }
        }
    }

    /**
     * 校验出生年月
     *
     * @param birth
     * @return
     */
    private void checkBirth(String birth) throws PlatformCoreException {
        if (birth == null || "".equals(birth)) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed;
        try {
            parsed = sdf.parse(birth);
        } catch (Exception e) {
            throw PlatformCoreException.create(500, "出生年月字段格式有误: yyyy-MM-dd");
        }
        boolean b1 = parsed.after(BirthFactory.getOldest());
        boolean b2 = parsed.before(BirthFactory.getYoungest());
        if (!b1 || !b2) {
            throw PlatformCoreException.create(500, "未在法定工龄范围内");
        }
    }

    /**
     * 校验联系电话
     *
     * @param str
     * @throws PlatformCoreException
     */
    private void checkTel(String str) throws PlatformCoreException { // 11位数字组成的号码
        if (str == null || "".equals(str)) {
            return;
        }
        String p = "^\\d{11}$";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return;
        } else {
            throw PlatformCoreException.create(500, "联系电话字段有误");
        }
    }

    /**
     * 校验联系地址
     *
     * @param address
     * @throws PlatformCoreException
     */
    private void checkContactAddress(String address) throws PlatformCoreException {
        if (address == null || "".equals(address)) {
            return;
        }
    }

    /**
     * 校验性别
     *
     * @param kv
     * @throws PlatformCoreException
     */
    private void checkSex(JSONObject kv) throws PlatformCoreException {
        if (kv == null) {
            return;
        }
    }

    /**
     * 校验部门
     *
     * @param kv
     * @throws PlatformCoreException
     */
    private void checkDepartment(JSONObject kv) throws PlatformCoreException {
        if (kv == null) {
            return;
        }
    }

    /**
     * 校验民族
     *
     * @param kv
     * @throws PlatformCoreException
     */
    private void checkNationality(JSONObject kv) throws PlatformCoreException {
        if (kv == null) {
            return;
        }
    }
}
