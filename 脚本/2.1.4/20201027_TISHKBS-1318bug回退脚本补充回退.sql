delete from  sys_datadict_list_link where dict_code = 'SVR_OPERATEMODE_DECLARE' and LINKED_DICT_CODE = 'SVR_PROD_PROFIT_TYPE'
   and ITEM_CODE in ('02', '04') and LINKED_ITEM_CODE = '03';
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_OPERATEMODE_DECLARE', '02', 'SVR_PROD_PROFIT_TYPE', '03', 'system', '', sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_OPERATEMODE_DECLARE', '04', 'SVR_PROD_PROFIT_TYPE', '03', 'system', '', sysdate, 'system', sysdate);