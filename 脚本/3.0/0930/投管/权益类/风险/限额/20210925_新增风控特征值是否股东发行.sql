delete from sys_datadict_list where DICT_CODE = 'RISK_EVENT_CHARA_TYPE' and ITEM_CODE = 'isShareHolder';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RISK_EVENT_CHARA_TYPE', 'isShareHolder', '是否股东发行', 32, '0', 'system', null, sysdate, 'system', sysdate, null);