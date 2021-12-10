delete from sys_prebatch_task_add where GROUP_ID='preBatchCheckGrp' and TASK_ID='88615';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('preBatchCheckGrp', '88615', '01', '', 'system', '', sysdate, 'system', sysdate);

delete from sys_batch_task where TASK_ID='88615';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88615', 'INV', '日终是否自动交收', '', 'cfetsNoSettleTask', 13, 'system', '', sysdate, 'system', sysdate);

delete from sys_batch_job where BATCH_SEQ_NO='preBatchCheckGrp' and TASK_ID ='88615';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('preBatchCheckGrp', '88615', 'system', '', sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE ='CHECK_POINT' and ITEM_CODE='88615';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CHECK_POINT', '88615', '日终是否自动交收', 13, '0', 'system', '', sysdate, 'system', sysdate, '');