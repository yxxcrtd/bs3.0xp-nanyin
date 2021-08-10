DELETE FROM SYS_BATCH_TASK WHERE BEAN_NAME = 'svrNewPbcSinglePrdReportTaskService';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3055', 'SVR', '新人行单产品报表生成', null, 'svrNewPbcSinglePrdReportTaskService', 3250, 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_BATCH_TASK WHERE BEAN_NAME = 'svrNewPbcDatFileTaskService';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('3056', 'SVR', '新人行Dat文件生成批处理', null, 'svrNewPbcDatFileTaskService', 3260, 'system', null, sysdate, 'system', sysdate);

