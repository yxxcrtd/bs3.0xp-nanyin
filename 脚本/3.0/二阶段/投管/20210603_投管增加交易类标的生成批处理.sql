DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '10035' AND BEAN_NAME = 'assetIntdateCarryForwardTask';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('10035', 'INV', '交易类标的生成批处理', null, 'assetIntdateCarryForwardTask', 8, 'system', null, '15-7月 -19 02.25.56.000000 下午', 'system', '15-7月 -19 02.25.56.000000 下午', null);
