delete from sys_para where PARA_CODE = 'is_need_preoccupy' and SYSTEM_CODE = 'ETR';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ETR', 'is_need_preoccupy', 'Y', null, null, '是否需要预占功能（Y:需要、否则不需要）', 'system', null, sysdate, 'dzw', sysdate);