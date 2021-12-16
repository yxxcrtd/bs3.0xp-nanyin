delete from sys_para where para_code='IS_SUPPORT_CSTP_FLAG_BOND';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('COM', 'IS_SUPPORT_CSTP_FLAG_BOND', 'Y', '01', null, '是否支持CSTP下行接口(Y:是，N:否)_债券', 'system', null, sysdate, 'xq', sysdate, null, '01');
delete from sys_para where para_code='IS_SUPPORT_CSTP_FLAG_REPO';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('COM', 'IS_SUPPORT_CSTP_FLAG_REPO', 'Y', '01', null, '是否支持CSTP下行接口(Y:是，N:否)_回购', 'system', null, sysdate, 'xq', sysdate, null, '01');
