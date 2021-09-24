--证券品种与市价来源联动
delete from sys_datadict_list_link where DICT_CODE='VALUATION_PRICE' and LINKED_DICT_CODE='SEC_TYPE' and LINKED_ITEM_CODE='13';
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_PRICE', '13', 'SEC_TYPE', '13', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_PRICE', 'CBC', 'SEC_TYPE', '13', 'system', null, sysdate, 'system', sysdate);
