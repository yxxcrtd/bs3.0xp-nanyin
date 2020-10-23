--add by lilan 20201012 统一批里增加产品台账报表自动生成 start/
delete sys_batch_job where BATCH_SEQ_NO='inv3' and TASK_ID='112201';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('inv3', '112201', 'unistart', '', sysdate, 'unistart', sysdate);
--add by lilan 20201012 统一批里增加产品台账报表自动生成 end/