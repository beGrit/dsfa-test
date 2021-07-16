#namespace("test.lsf.jsgl.actor.sql")
    #sql("updateEnabledStatus")
        update
            test_lsf_jsgl jsgl
        set
            jsgl.isenabled_text = #para(newStatusText),
            jsgl.isenabled_value = #para(newStatusValue)
        where
            jsgl.test_lsf_jsgl_id = #para(id)
            and
            jsgl.isenabled_text = #para(oldStatusText)
            and
            jsgl.isenabled_value = #para(oldStatusValue)
            and
            jsgl.ds_deleted = 0
    #end
#end
