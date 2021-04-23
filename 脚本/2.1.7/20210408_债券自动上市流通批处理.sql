--自动上市流通批处理
delete from sys_batch_task where TASK_ID='11112';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('11112', 'INV', '债券自动上市流通批处理', '', 'busiAutoAddSecInfoTask', 50, 'system', '', sysdate, 'system', sysdate);

delete from sys_batch_job where BATCH_SEQ_NO ='inv3' and TASK_ID = '11112';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('inv3', '11112', 'unistart', '', sysdate, 'unistart', sysdate); 
 