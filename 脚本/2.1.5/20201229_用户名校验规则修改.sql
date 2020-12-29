--长度在1到20个字符之间,字符为字母大小写、数字以及下划线
delete from sys_para where system_code = 'SVR' and para_code = 'username_rule';
INSERT INTO SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG) VALUES ('SVR', 'username_rule', '^[a-zA-Z0-9_]{1,20}$', '01', '正则表达式', '用户名合法性验证规则', 'system', null, sysdate, 'system', sysdate, null, '02');
