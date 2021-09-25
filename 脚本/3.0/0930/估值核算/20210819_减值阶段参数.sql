--减值阶段参数管理的数据字典五级分类
DELETE FROM sys_datadict WHERE DICT_CODE = 'FIVE_CLASS_CLASSIFICATION';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FIVE_CLASS_CLASSIFICATION', 'RSK', '五级分类', 'FIVE_CLASS_CLASSIFICATION', '02', 'system', null, sysdate, 'system', sysdate);

DELETE FROM sys_datadict_list WHERE DICT_CODE = 'FIVE_CLASS_CLASSIFICATION';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FIVE_CLASS_CLASSIFICATION', '01', '正常', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FIVE_CLASS_CLASSIFICATION', '02', '关注', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FIVE_CLASS_CLASSIFICATION', '03', '次级', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FIVE_CLASS_CLASSIFICATION', '04', '可疑', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FIVE_CLASS_CLASSIFICATION', '05', '损失', 5, '0', 'system', null, sysdate, 'system', sysdate);