???prompt Importing table sys_datadict...
set feedback off
set define off
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXCHANGE_TYPE', 'SYS', '交换机类型', 'EXCHANGE_TYPE', '02', 'system', null, '07-MAY-19 12.00.00.000000 AM', 'system', '21-APR-21 12.00.00.000000 AM');

prompt Done.
