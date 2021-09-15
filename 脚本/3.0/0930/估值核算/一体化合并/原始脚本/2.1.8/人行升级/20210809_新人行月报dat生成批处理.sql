DELETE FROM SYS_BATCH_TASK WHERE BEAN_NAME = 'svrNewPbcReportDatFileTaskService';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3057', 'SVR', '新人行月报Dat文件生成批处理', null, 'svrNewPbcReportDatFileTaskService', 3260, 'system', null, sysdate, 'system', sysdate);

