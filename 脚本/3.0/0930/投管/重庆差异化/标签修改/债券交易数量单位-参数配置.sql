
-- 系统参数，债券交易数量单位手/张
delete  from sys_para where PARA_CODE = 'bond_traded_unit';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV', 'bond_traded_unit', '01', null, '债券交易数量单位，值域为01-张；02-手', 'system', null, sysdate, 'system', sysdate