delete from sys_batch_job where BATCH_SEQ_NO='inv2' and TASK_ID='110002';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('inv2', '110002', 'unistart', '', sysdate, 'unistart', sysdate);

