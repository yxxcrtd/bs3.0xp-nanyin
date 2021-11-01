delete from sys_para where SYSTEM_CODE = 'INV' and PARA_CODE = 'CFETS_ETR_REFRESH_TIME' and REMARK = '本币业务指令自动刷新时间(s)';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('INV', 'CFETS_ETR_REFRESH_TIME', '10', null, null, '本币业务指令自动刷新时间(s)', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');
