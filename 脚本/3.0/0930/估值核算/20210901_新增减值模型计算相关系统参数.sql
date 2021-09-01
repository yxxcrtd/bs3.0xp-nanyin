--新增减值模型PD计算相关系统参数
--增加系统参数：国债、政策性金融债、地方政府债兜底评级结果
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'BOND_BOTTOM_RATE_1';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'BOND_BOTTOM_RATE_1', '04', '01', '01,02,03,04', '国债、政策性金融债、地方政府债债券类型兜底评级结果(01:AA-,02:AA,03:AA+,04:AAA)', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');

--增加系统参数：除国债、政策性金融债、地方政府债外其他债券兜底评级结果
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'BOND_BOTTOM_RATE_2';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'BOND_BOTTOM_RATE_2', '01', '01', '01,02,03,04', '除国债、政策性金融债、地方政府债外债券类型兜底评级结果(01:AA-,02:AA,03:AA+,04:AAA)', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');

--增加系统参数：其他债权类资产兜底评级结果
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'DELT_RIGHT_BOTTOM_RATE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'DELT_RIGHT_BOTTOM_RATE', '01', '01', '01,02,03,04', '其他债权类资产兜底评级结果(01:AA-,02:AA,03:AA+,04:AAA)', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');

--增加系统参数：存款类资产兜底评级结果
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'DEPOSITE_BOTTOM_RATE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'DEPOSITE_BOTTOM_RATE', '01', '01', '01,02,03,04', '存款类资产兜底评级结果(01:AA-,02:AA,03:AA+,04:AAA)', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');

--增加系统参数：回购资产兜底评级结果
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'REPO_FINANCIER_RATE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'REPO_FINANCIER_RATE', '01', '01', '01,02,03,04', '回购类资产兜底评级结果(01:AA-,02:AA,03:AA+,04:AAA)', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');

--新增减值模型LGD计算相关系统参数
--增加系统参数：LGD违约损失小数位
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'SYS_PARA_LGD_DECIMAL_DIGIT';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'SYS_PARA_LGD_DECIMAL_DIGIT', '6', '01', '', 'LGD违约损失小数位', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');
--增加系统参数：LGD违约损失小数位
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'SYS_PARA_LGD_DIGIT_TYPE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'SYS_PARA_LGD_DIGIT_TYPE', '03', '01', '01,02,03', 'LGD违约损失小数位处理方式：01:进位，02:舍位，03:四舍五入', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');
