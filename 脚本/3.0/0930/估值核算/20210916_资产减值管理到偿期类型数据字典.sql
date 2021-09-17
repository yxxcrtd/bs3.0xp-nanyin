-- 资产减值管理到偿期类型数据字典
delete from sys_datadict where DICT_CODE='PRRIOD_TYPE';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PRRIOD_TYPE', 'VAL', '代偿期类型', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='PRRIOD_TYPE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('PRRIOD_TYPE', '01', '行权', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('PRRIOD_TYPE', '02', '到期', 2, '0', 'system', null, sysdate, 'system', sysdate, null);




