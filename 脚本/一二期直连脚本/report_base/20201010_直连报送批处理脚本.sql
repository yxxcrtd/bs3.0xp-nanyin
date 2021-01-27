/**从业人员信息接口接入脚本添加*/
delete  from SYS_BATCH_TASK  where BEAN_NAME='svrEmployeeInterfTaskService'; 
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3006','SVR','从业人员信息登记报送',null,'svrEmployeeInterfTaskService',3040,'system',null,sysdate,'system',sysdate);

/**产品申报登记报送批处理脚本添加*/
delete  from SYS_BATCH_TASK  where BEAN_NAME='svrSdProdDeclareTaskService'; 
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3100','SVR','产品申报登记直连报送批处理',null,'svrSdProdDeclareTaskService',3100,'system',null,
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

/**产品发行登记报送批处理脚本添加*/
delete  from SYS_BATCH_TASK  where BEAN_NAME='svrSdProdIssueTaskService'; 
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3101','SVR','产品发行信息登记直连报送批处理',null,'svrSdProdIssueTaskService',3100,'system',null,
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

/**产品募集总量登记报送批处理脚本添加*/
delete  from SYS_BATCH_TASK  where BEAN_NAME='svrSdProdRaisetotTaskService'; 
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3102','SVR','产品募集总量信息登记直连报送批处理',null,'svrSdProdRaisetotTaskService',3100,'system',null,
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

/**产品存续期登记报送批处理脚本添加*/
delete  from SYS_BATCH_TASK  where BEAN_NAME='svrSdProdDealTaskService'; 
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3103','SVR','产品存续期信息登记直连报送批处理',null,'svrSdProdDealTaskService',3100,'system',null,
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

/**产品终止登记报送批处理脚本添加*/
delete  from SYS_BATCH_TASK  where BEAN_NAME='svrSdProdTerminationTaskService'; 
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3104','SVR','产品终止信息登记直连报送批处理',null,'svrSdProdTerminationTaskService',3100,'system',null,
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));

/**从业人员登记报送批处理脚本添加*/
delete  from SYS_BATCH_TASK  where BEAN_NAME='svrSdEmployeeInfoTaskService'; 
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3099','SVR','从业人员登记直连报送批处理',null,'svrSdEmployeeInfoTaskService',3100,'system',null,
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',
to_timestamp('09-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));


DELETE FROM SYS_BATCH_TASK 
WHERE BEAN_NAME in ('sdBotAssetPosRegTaskService','sdAssetValRegTaskService','sdAssetInfoRegTaskService','sdAssetDealRegTaskService');
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3105','SVR','底层资产持仓信息登记直连报送批处理',null,'sdBotAssetPosRegTaskService',3260,'system',null,sysdate,'system',sysdate);
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3106','SVR','资产估值信息登记直连报送批处理',null,'sdAssetValRegTaskService',3260,'system',null,sysdate,'system',sysdate);
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3107','SVR','资产要素信息登记直连报送批处理',null,'sdAssetInfoRegTaskService',3260,'system',null,sysdate,'system',sysdate);
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3108','SVR','资产交易信息登记直连报送批处理',null,'sdAssetDealRegTaskService',3261,'system',null,sysdate,'system',sysdate);


DELETE FROM SYS_PARA WHERE PARA_CODE='SEND_CB_DATA_COUNT_PER_TIME_FIRST';
Insert into SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SVR','SEND_CB_DATA_COUNT_PER_TIME_FIRST','50000',' ',null,'中债一二期直连报送每批次处理数据条数','system',null,sysdate,'system',sysdate);

commit;