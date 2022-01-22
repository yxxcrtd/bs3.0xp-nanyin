--人行资产类型增加减值
delete  from sys_datadict_list where DICT_CODE ='SVR_PBC_ASSET_TYPE_1' and ITEM_CODE ='P14';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_1', 'P14', '资产减值准备', 14, '0', 'system', null, sysdate, 'system', sysdate);

delete  from sys_datadict_list where DICT_CODE ='SVR_PBC_ASSET_TYPE_2' and ITEM_CODE in ('Z01','Z02','Z03','Z04','Z05','Z06','Z07','Z08','Z09'，'Z10');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_2', 'Z01', '存款 ', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_2', 'Z02', '可转让存单', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_2', 'Z03', '债务证券', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_2', 'Z04', '除回购和拆借外贷款', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_2', 'Z05', '回购和拆借', 5, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_2', 'Z06', '资产收益权 ', 6, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_2', 'Z07', '股权及特定目的载体份额', 7, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_2', 'Z08', '金融衍生品', 8, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_2', 'Z09', '应收账款', 9, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_2', 'Z10', '其他', 10, '0', 'system', null, sysdate, 'system', sysdate);

delete  from sys_datadict_list where DICT_CODE ='SVR_PBC_ASSET_TYPE_3' and ITEM_CODE in ('Z01','Z02','Z03','Z04','Z05','Z06','Z07','Z08','Z09'，'Z10');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Z01', '存款 ', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Z02', '可转让存单', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Z03', '债务证券', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Z04', '除回购和拆借外贷款', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Z05', '回购和拆借', 5, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Z06', '资产收益权 ', 6, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Z07', '股权及特定目的载体份额', 7, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Z08', '金融衍生品', 8, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Z09', '应收账款', 9, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Z10', '其他', 10, '0', 'system', null, sysdate, 'system', sysdate);