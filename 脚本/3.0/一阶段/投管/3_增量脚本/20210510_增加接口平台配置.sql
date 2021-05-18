DELETE FROM SYS_PARA WHERE PARA_CODE IN ('url_interf_place','gpcode','tgcode');
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('INF', 'url_interf_place', 'http://192.168.90.208:1666/dsp/datarouter/http/json', '01', null, '接口平台url', 'system', null, '23-JUL-19 12.00.00.000000 AM', 'admin', '23-JUL-19 12.00.00.000000 AM', null, '01');

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('INF', 'gpcode', 'TAMS30', '01', null, '接口平台组号', 'system', null, '23-JUL-19 12.00.00.000000 AM', 'admin', '23-JUL-19 12.00.00.000000 AM', null, '01');

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('INF', 'tgcode', 'trigger_http_tams30', '01', null, '接口平台触发器编号', 'system', null, '23-JUL-19 12.00.00.000000 AM', 'admin', '23-JUL-19 12.00.00.000000 AM', null, '01');
