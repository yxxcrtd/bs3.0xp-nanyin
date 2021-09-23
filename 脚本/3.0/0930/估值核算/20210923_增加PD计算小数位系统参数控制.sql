--新增减值模型PD计算相关系统参数
--增加系统参数：PD违约损失小数位
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'SYS_PARA_PD_DIGIT';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'SYS_PARA_PD_DIGIT', '6', '01', '-1,4,6', 'PD违约概率结果保留小数位：-1：全精度 4：保留4位小数 6：保留6位小数', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');
--增加系统参数：PD违约损失小数位
delete from  SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'SYS_PARA_PD_DIGIT_TYPE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'SYS_PARA_PD_DIGIT_TYPE', '03', '01', '01,02,03', 'PD违约概率结果保留小数位尾数处理方式：01，进位；02，舍位；03，四舍五入', 'system', null, SYSDATE, 'system', SYSDATE, null, '01');
