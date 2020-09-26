delete from SYS_BATCH_JOB where batch_seq_no = 'inv3' and task_id = '110002';
delete from SYS_BATCH_JOB where batch_seq_no = 'inv2' and task_id = '110002';
INSERT INTO SYS_BATCH_JOB (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('inv3','110002','unistart','',sysdate,'unistart',sysdate);
