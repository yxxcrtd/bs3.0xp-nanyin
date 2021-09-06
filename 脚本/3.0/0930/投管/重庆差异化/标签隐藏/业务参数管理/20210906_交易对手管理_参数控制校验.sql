delete  from sys_para where PARA_CODE = 'ISChongQing';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV', 'ISChongQing', 'N', '01', null, 'Y：是重庆投资交易系统，N：不是重庆投资交易系统', 'system', null, sysdate, 'system', sysdate);