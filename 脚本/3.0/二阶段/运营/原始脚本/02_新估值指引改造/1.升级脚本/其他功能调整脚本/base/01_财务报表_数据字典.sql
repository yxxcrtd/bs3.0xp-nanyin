--三表优化增加报表类型数据字典
delete FROM sys_datadict t WHERE t.dict_code = 'REPORT_TYPE_EXPORT';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_TYPE_EXPORT', 'VAL', '报表类型', null, '02', 'system', null, sysdate, 'system', sysdate);



delete FROM sys_datadict_list t WHERE t.dict_code = 'REPORT_TYPE_EXPORT';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_TYPE_EXPORT', '01', '资产负债表', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_TYPE_EXPORT', '02', '利润表', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_TYPE_EXPORT', '03', '所有者权益表', 3, '0', 'system', null, sysdate, 'system', sysdate);

