delete from sys_para where SYSTEM_CODE = 'INV' and PARA_CODE = 'CMDS_PATH' and REMARK = 'CMDS接口地址';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('INV', 'CMDS_PATH', 'ws://192.168.90.208:8888/dsp/cmds', null, null, 'CMDS接口地址', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');

