--add by lilan 删除主动提醒数据修改提醒天数，统一在主动提醒配置表设置的（sys_activereminder_set）
delete sys_para where para_code='R9S1_DAYS';