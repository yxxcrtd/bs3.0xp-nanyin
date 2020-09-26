delete from sys_datadict_list where dict_code='INTPAY_PERIOD' and ITEM_CODE='03';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INTPAY_PERIOD', '03', '申购同频', 3, '0', 'system', null, sysdate, 'system', sysdate);
