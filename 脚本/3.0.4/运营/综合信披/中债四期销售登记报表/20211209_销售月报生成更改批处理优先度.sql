--销售信息月度统计表生成批处理配置
DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '3060';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('3060', 'VAL', '销售信息月度统计表生成', null, 'svrMonthCollectInterfTaskService', 3300, 'system', null, sysdate, 'system', sysdate, null);
