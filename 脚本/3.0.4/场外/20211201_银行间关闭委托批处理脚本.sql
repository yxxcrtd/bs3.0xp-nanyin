delete from sys_batch_task where TASK_ID ='10120';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('10120', 'INV', '银行间关闭委托状态更新(前后端分离)', '', 'cfetsEntrustStatusTask', 12, 'system', '', sysdate, 'system', sysdate, '');
