delete from SYS_DATADICT where dict_code ='ACCOUNT_ORDER';
insert into SYS_DATADICT (dict_code, system_code, dict_cnname, dict_enname, update_flg, create_user, create_dept, create_time, update_user, update_time)
values ('ACCOUNT_ORDER', 'VAL', '记账顺序', null, '02', 'system', null, to_timestamp('25-05-2019 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'system', to_timestamp('25-05-2019 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
delete from SYS_DATADICT_LIST where dict_code ='ACCOUNT_ORDER';
insert into SYS_DATADICT_LIST (dict_code, item_code, item_value, order_no, default_value, create_user, create_dept, create_time, update_user, update_time)
values ('ACCOUNT_ORDER', '01', '交易前', 1, '0', 'system', null, to_timestamp('25-05-2019 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'system', to_timestamp('25-05-2019 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_DATADICT_LIST (dict_code, item_code, item_value, order_no, default_value, create_user, create_dept, create_time, update_user, update_time)
values ('ACCOUNT_ORDER', '02', '计提后', 2, '0', 'system', null, to_timestamp('25-05-2019 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'system', to_timestamp('25-05-2019 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
delete from SYS_BATCH_TASK where task_id = '203';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_dept, create_time, update_user, update_time)
values ('203', 'VAL', '手工凭证批处理-计提后', null, 'manualVoucherTask', 83, 'system', null, to_timestamp('25-05-2019 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'system', to_timestamp('25-05-2019 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'));
delete from sys_batch_job where BATCH_SEQ_NO='6f8803b3aa6fbaffe050007f01003cad' and  task_id = '203';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('6f8803b3aa6fbaffe050007f01003cad', '203', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
delete from BOK_MATCH_RLAT where MATCH_TYPE='01' and SOURCE='203' and TARGET='23';
insert into BOK_MATCH_RLAT (MATCH_TYPE, ACCT_STANDARD, SOURCE, TARGET, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('01', 'ALL', '203', '23', 'system', null, '10-9月 -18 12.00.00.000000 上午', 'system', '29-8月 -18 12.00.00.000000 上午');
commit;