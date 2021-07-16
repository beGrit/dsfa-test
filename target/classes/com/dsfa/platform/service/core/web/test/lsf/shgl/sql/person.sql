#namespace("test.lsf.shgl.person.sql")

    #sql("findStausValueById")
        select
            rygl.status_value
        from
            test_lsf_rygl rygl
        where
            rygl.test_lsf_rygl_id = #para(id)
            and ds_deleted = 0
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
            and ds_deleted = 0
    #end

#end