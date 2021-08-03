delete from sys_para where PARA_CODE='CHB_VAL_SPLIT_STR';
INSERT INTO sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
VALUES ('SVR', 'CHB_VAL_SPLIT_STR', '估值表', null, null, '中债导入估值表中文件名称拆分分隔符', 'system', '', sysdate, 'zyf', sysdate, null, '01');
