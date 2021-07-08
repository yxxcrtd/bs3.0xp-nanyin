/**资产减值升级时删除了手工凭证-计提后得批处理，需要给恢复回来**/
DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '204';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('204','VAL','手工凭证批处理-计提后','','manualVoucherTask','83','system',to_date('2021/02/24', 'yyyy/mm/dd'), 'system',to_date('2021/02/24', 'yyyy/mm/dd'), '');

DELETE FROM SYS_BATCH_JOB WHERE TASK_ID = '204' AND BATCH_SEQ_NO = '6f8803b3aa6fbaffe050007f01003cad';
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','204','system','',to_date('2021/2/24', 'yyyy/mm/dd'), 'system',to_date('2021/2/24', 'yyyy/mm/dd'));

/**删除203冗余配置**/
DELETE FROM bok_match_rlat t WHERE t.source = '203' AND MATCH_TYPE = '01' AND ACCT_STANDARD = 'ALL' AND TARGET = '23';
DELETE FROM bok_match_rlat t WHERE t.source = '204';
insert into bok_match_rlat (MATCH_TYPE, ACCT_STANDARD, SOURCE, TARGET, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('01', 'ALL', '204', '23', 'system', null, sysdate, 'system', sysdate);
