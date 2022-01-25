--修改人行资产二级分类S05企业债券下的资产三级分类
delete from sys_datadict_list_link a where a.dict_code='SVR_PBC_ASSET_TYPE_3' and a.item_code in('S05');

delete from  sys_datadict_list_link where DICT_CODE ='SVR_PBC_ASSET_TYPE_3' and ITEM_CODE in('Y01','Y02','Y03');

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Y01', 'SVR_PBC_ASSET_TYPE_2', 'S05', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Y02', 'SVR_PBC_ASSET_TYPE_2', 'S05', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_ASSET_TYPE_3', 'Y03', 'SVR_PBC_ASSET_TYPE_2', 'S05', 'system', null, sysdate, 'system', sysdate);
