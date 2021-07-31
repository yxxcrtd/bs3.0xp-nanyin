
-- 系统参数，是否展示资产四五级分类
delete  from sys_para where PARA_CODE = 'entrustAmtUnit';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV', 'entrustAmtUnit', '02', '01', null, '01 券面总额单位为元，02券面总额单位为万元', 'system', null, sysdate, 'system', sysdate);
