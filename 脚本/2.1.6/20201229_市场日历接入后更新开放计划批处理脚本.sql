/*批处理任务：市场日历接入后更新产品开放计划*/
delete from SYS_BATCH_TASK where TASK_ID = '10111';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('10111','INV','市场日历接入后更新开放计划',null,'prdOpenPlanUpdateTask',40,'system',null,sysdate,'system',sysdate);

/*新增系统参数：市场日历更新联动更新的产品开发计划日期（年）*/
delete from SYS_PARA where SYSTEM_CODE = 'INV' and PARA_CODE = 'openplan_update_year';
Insert into SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('INV','openplan_update_year','2018','01',null,'市场日历更新联动更新的产品开放计划日期（年）','system',null,sysdate,'admin',sysdate);


