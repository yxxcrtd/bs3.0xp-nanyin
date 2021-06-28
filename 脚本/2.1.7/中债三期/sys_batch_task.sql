delete sys_batch_task where TASK_ID in('3004','3000','3001','3002','3003','3072','3073','3075','3090','3067','3068','3071','3069');

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3004', 'REP', '报送前检查自动检查', null, 'checkReportAutoSendCheckService', 3004, 'system', sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3000', 'SVR', '中债上游接口批处理', null, 'cnBondUpInterfService', 3000, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3001', 'SVR', '投资者身份信息数据自动检查报送', null, 'equityInfoAutoSendCBService', 3001, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3002', 'SVR', '投资者持有信息数据自动生成批处理', null, 'generateInvestorsHoldInfoAutoSendCBService', 3002, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3003', 'SVR', '投资者持有信息数据自动检查报送', null, 'investorsHoldInfoRegistAutoSendCBService', 3003, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3072', 'REP', '投资者持有信息登记手工处理', null, 'investorsHoldInfoRegistAutoManualCBService', 3070, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3073', 'REP', '投资者身份信息登记手工处理', null, 'equityInfoRegistAutoManualCBService', 3070, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3075', 'SVR', '中债投资者明细登记批处理导出', null, 'equityBusiInfoExportService', 3070, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3090', 'SVR', '数据归档批处理', null, 'reportDataBackupService', 3090, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3067', 'REP', '投资者明细信息登记手工处理', null, 'equityBusiInfoRegistAutoManualCBService', 3250, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3068', 'REP', '投资者明细信息数据检查批处理', null, 'equityBusiInfoRegistManualCheckService', 3250, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3071', 'REP', '投资者明细信息数据自动检查报送', null, 'equityBusiInfoAutoSendCBService', 3250, 'system', null, sysdate, 'system', sysdate);

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3069', 'REP', '投资者明细信息数据归档批处理', null, 'equityBusiInfoDataBackupService', 3300, 'system', null, sysdate, 'system', sysdate);

