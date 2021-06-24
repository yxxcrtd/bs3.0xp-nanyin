delete from sys_para where PARA_CODE = 'etrlimit_switch_is_total';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('ETR', 'etrlimit_switch_is_total', 'Y', null, null, '银行间多组合指令是否汇总授权,Y指令金额汇总授权，N指令金额单独授权，', 'system', null, sysdate, 'admin', sysdate, null, '01');
