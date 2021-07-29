
-- 系统参数，是否展示资产四五级分类
delete  from sys_para where PARA_CODE = 'IS_SHOW_TYPE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV', 'IS_SHOW_TYPE', '02', '01', null, '是否展示资产四五级分类，01：展示，02：不展示', 'system', null, sysdate, 'system', sysdate);
