--redis是否需要重复跑批开关
delete from SYS_PARA where PARA_CODE = 'is_repeated_reidsbatch';
INSERT INTO SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, UPDATE_FLG, INSTITUTION_CODE) VALUES ('RSK', 'is_repeated_reidsbatch', '01', null, null, 'redis是否需要重复跑批', 'system', null, TIMESTAMP '2019-11-19 00:00:00', 'jl', TIMESTAMP '2020-06-10 15:01:43', '01', null);
