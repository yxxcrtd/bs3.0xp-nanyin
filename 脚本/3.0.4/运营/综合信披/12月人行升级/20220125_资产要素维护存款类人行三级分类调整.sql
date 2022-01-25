--资产要素维护存款类人行三级分类调整
delete from sys_datadict_list_link a where a.dict_code='SVR_PBC_ASSET_TYPE_3' and a.item_code in('C01','C02');

delete from  sys_datadict_list_link where DICT_CODE ='SVR_PBC_ASSET_TYPE_3' and ITEM_CODE in('D01','D02','D03','D04');

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'D01', 'SVR_PBC_ASSET_TYPE_2', 'C01', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'D02', 'SVR_PBC_ASSET_TYPE_2', 'C01', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'D03', 'SVR_PBC_ASSET_TYPE_2', 'C02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'D04', 'SVR_PBC_ASSET_TYPE_2', 'C02', 'system', null, sysdate, 'system', sysdate);