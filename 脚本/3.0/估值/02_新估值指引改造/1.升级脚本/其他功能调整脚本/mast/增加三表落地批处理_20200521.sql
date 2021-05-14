--2020年5月21日 weipeng:增加批处理任务：三表落地批处理
delete from SYS_BATCH_TASK t where t.task_id = '900' and t.bean_name = 'threeTablesLandingTask';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('900','VAL','三表落地批处理','','threeTablesLandingTask','900','system',to_date('2019/3/7', 'yyyy/mm/dd'), 'system',to_date('2019/3/7', 'yyyy/mm/dd'), '');
