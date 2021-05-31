--数据字典：基础科目性质分组  新增数据项【增值税损益科目查询】
delete from sys_datadict_list sdl where sdl.dict_code = 'GROUP_ID' and sdl.item_code = 'TAX_LXSR_KMCX';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('GROUP_ID', 'TAX_LXSR_KMCX', '增值税损益科目查询', 20, '0', 'system', null, sysdate, 'system', sysdate);