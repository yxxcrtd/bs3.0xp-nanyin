
--LGD违约损失小数位参数调整
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'SYS_PARA_LGD_DECIMAL_DIGIT';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'SYS_PARA_LGD_DECIMAL_DIGIT', '6', '01', '-1,4,6', 'LGD违约损失率结果保留小数位：-1：全精度 4：保留4位小数 6：保留6位小数', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');

--新增减值模型LGD计算相关系统参数
--增加系统参数：LGD违约损失小数位
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'SYS_PARA_ECL_RATIO_DIGIT';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'SYS_PARA_ECL_RATIO_DIGIT', '6', '01', '-1,4,6', 'ECL预期信用损失比例结果保留小数位：-1：全精度 4：保留4位小数 6：保留6位小数', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');
--增加系统参数：LGD违约损失小数位
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'SYS_PARA_ECL_RATIO_DIGIT_TYPE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'SYS_PARA_ECL_RATIO_DIGIT_TYPE', '03', '01', '01,02,03', 'ECL预期信用损失比例结果保留小数位尾数处理方式：01，进位；02，舍位；03，四舍五入', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');
