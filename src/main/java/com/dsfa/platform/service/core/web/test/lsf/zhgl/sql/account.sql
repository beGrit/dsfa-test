#namespace("test.lsf.zhgl.account.sql")

    ### 更具 test_lsf_zhgl_id(主键) 获得账户信息
    #sql("findById")
        select
            *
        from
            test_lsf_zhgl zhgl
        where
                zhgl.test_lsf_zhgl_id = #para(id) and zhgl.ds_deleted = '0'
    #end

    ### 根据 用户id(外键) 判断是否存在记录
    #sql("exitsByPersonId")
        select
               COUNT(*)
        from
             test_lsf_zhgl zhgl
        where
             zhgl.peopleid = #para(personid) and zhgl.ds_deleted = '0'
    #end

    ### 更新启用状态操作
    #sql("updateEnabledStatus")
        update
            test_lsf_zhgl zhgl
        set
            zhgl.isenabled_value = #para(newEnabledValue),
            zhgl.isenabled_text = #para(newEnabledText)
        where
            zhgl.test_lsf_zhgl_id = #para(id)

            and zhgl.isenabled_value = #para(oldEnabledValue)
            and zhgl.isenabled_text = #para(oldEnabledText)
    #end


    ### 重置密码操作
    #sql("resetPassword")
        update
            test_lsf_zhgl zhgl
        set
            zhgl.password = '123456a'
        where
            zhgl.test_lsf_zhgl_id = #para(test_lsf_zhgl_id)

            and
            zhgl.isenabled_value = '0'
            and zhgl.isenabled_text = '未启用'

    #end

    ### 查看指定用户名的记录条数
    #sql("countRecordByAccountName")
        select
             COUNT(*)
        from
             test_lsf_zhgl zhgl
        where
             zhgl.accountname = #para(accountname) and zhgl.ds_deleted = '0'
    #end

    ### 插入操作
    #sql("insertOne")
        insert into
            test_lsf_zhgl
        set
            `test_lsf_zhgl_id` = #para(test_lsf_zhgl_id),
            `accountname` = #para(accountName),
            `password` = #para(password),
            `nickName` = #para(nickName),
            `birth` = #para(birth),
            `sex_text` = #para(sexText),
            `sex_value` = #para(sexValue),
            `avatar` = #para(avatar),
            `id` = #para(id),
            `name` = #para(name),
            `department_value` = #para(departmentValue),
            `department_text` = #para(departmentText),
            `isenabled_value` = #para(isenabledValue),
            `isenabled_text` = #para(isenabledText),
            `peopleid` = #para(peopleId)
        #end
#end