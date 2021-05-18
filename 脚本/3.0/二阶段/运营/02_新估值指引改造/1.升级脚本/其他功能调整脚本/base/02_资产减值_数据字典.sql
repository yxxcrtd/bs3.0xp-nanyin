/** add by yanya on 20210106 交易方向数据字典增加减值清理**/

delete FROM sys_datadict_list t WHERE t.dict_code = 'PS' and t.item_code  in ('T75','T76','T77');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T75', '清理核销', 75, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T76', '其他应收款本金-清理核销', 76, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PS', 'T77', '其他应收款利息-清理核销', 77, '0', 'system', null, sysdate, 'system', sysdate);

