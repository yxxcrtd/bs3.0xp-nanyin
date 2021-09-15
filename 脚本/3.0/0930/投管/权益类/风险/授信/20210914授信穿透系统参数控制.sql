
delete from SYS_PARA where PARA_CODE = 'credit_penetrate_mode';
INSERT INTO SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RSK', 'credit_penetrate_mode', '01', '01', null, '授信穿透模式(01 穿透 02 不穿透)', 'system', null, TIMESTAMP '2020-02-25 00:00:00', 'system', TIMESTAMP '2020-02-25 00:00:00');
