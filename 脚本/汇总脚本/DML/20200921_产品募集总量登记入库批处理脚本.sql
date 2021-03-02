/**产品募集总量登记生成批处理脚本添加*/
delete  from SYS_BATCH_TASK  where TASK_ID='3085'; 
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3085','SVR','产品募集总量登记生成批处理',null,'svrInProdRaisetotInfoTaskService',3100,'system',null,to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
