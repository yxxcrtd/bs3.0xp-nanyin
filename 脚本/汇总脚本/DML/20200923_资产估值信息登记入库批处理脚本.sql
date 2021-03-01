/**资产估值信息登记生成批处理脚本添加*/
delete  from SYS_BATCH_TASK  where TASK_ID='3089'; 
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3089','SVR','资产估值信息登记生成批处理',null,'svrInAstValInfoTaskService',3100,'system',null,to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
