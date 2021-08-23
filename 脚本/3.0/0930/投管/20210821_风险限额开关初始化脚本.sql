delete from sys_para where PARA_CODE = 'risk_math_switch' and SYSTEM_CODE = 'RSK';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('RSK', 'risk_math_switch', '01', null, null, '风险试算开关', 'system', null, sysdate, 'ld', sysdate, null, '01');

delete from sys_para where PARA_CODE = 'pageRedisFlag' and SYSTEM_CODE = 'RSK';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('RSK', 'pageRedisFlag', 'Y', null, null, '风险画面功能Redis更新开关', 'system', null, sysdate, 'cdj', sysdate, null, '01');

delete from sys_para where PARA_CODE = 'TRD_CHECK_RISK_APPROVAL' and SYSTEM_CODE = 'TRD';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('TRD', 'TRD_CHECK_RISK_APPROVAL', '01', null, null, '是否触发风险审批开关 01：开；02：关', 'system', null, sysdate, 'cdj', sysdate, null, '01');