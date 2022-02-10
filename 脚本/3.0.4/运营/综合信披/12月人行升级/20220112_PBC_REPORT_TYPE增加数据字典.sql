delete from sys_datadict_list where DICT_CODE='PBC_REPORT_TYPE' and ITEM_CODE='4_1';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('PBC_REPORT_TYPE', '4_1', '资管产品资产配置情况统计表', 15, '0', 'system', null, sysdate, 'system', sysdate, null);
