delete from SYS_BATCH_JOB where BATCH_SEQ_NO='inv3' and TASK_ID in ('10018','10019');
insert into SYS_BATCH_JOB (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('inv3', '10018', 'unistart', null, sysdate, 'unistart', sysdate);
insert into SYS_BATCH_JOB (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('inv3', '10019', 'unistart', null, sysdate, 'unistart', sysdate);