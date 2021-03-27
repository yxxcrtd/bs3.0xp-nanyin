DELETE FROM SYS_BATCH_TASK WHERE BEAN_NAME = 'svrInBottomAstPositionTaskService';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3093','SVR','底层资产持仓信息生成批处理',null,'svrInBottomAstPositionTaskService',3100,'system',null,sysdate,'system',sysdate);
