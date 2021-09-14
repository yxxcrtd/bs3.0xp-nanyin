delete from  sys_datadict_list where dict_code ='EXEC_MODE' and item_code in ('06','07');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_MODE', '06', '欧式', 1, '0', 'system', null, sysdate, 'system',sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_MODE', '07', '美式', 2, '0', 'system', null, sysdate, 'system', sysdate);