#namespace("test.lsf.rygl.person.sql")

    ### 更具 test_lsf_rygl(主键) 获得账户信息
    #sql("findById")
        select
            *
        from
            test_lsf_rygl rygl
        where
                rygl.test_lsf_rygl_id = #para(id) and rygl.ds_deleted = '0'
    #end
    #sql("updateStatus")
        update
            test_lsf_rygl rygl
        set
            status_value = #para(statusValue),
            status_text = #para(statusText)
        where
            rygl.test_lsf_rygl_id = #para(id)
            and status_value = #para(oldStatusValue)
            and status_text = #para(oldStatusText)
    #end

#end