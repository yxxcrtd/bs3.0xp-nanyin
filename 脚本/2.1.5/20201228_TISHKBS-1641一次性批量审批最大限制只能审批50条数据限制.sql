--TISHKBS-1641
--- 【代办事项】一次性批量审批最大限制只能审批50条数据限制

DELETE sys_para  WHERE para_code = 'multi_approval_max';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('ALL', 'multi_approval_max', '110', null, null, '批量审批单次允许的最大条数', 'admin', null, SYSDATE, 'admin', sysdate, NULL, '01');
