--ZGBSS-557建议修改未来计划批处理和日历更新批处理名称
delete from SYS_BATCH_TASK where TASK_ID = '10111';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('10111','INV','市场日历接入后计划更新',null,'prdOpenPlanUpdateTask',40,'system',null,sysdate,'system',sysdate);
delete from SYS_BATCH_TASK where TASK_ID = '10101';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('10101','INV','产品未来计划批处理',null,'susPrdFutureOpenPlanGenTask',25,'system',null,sysdate,'system',sysdate);
