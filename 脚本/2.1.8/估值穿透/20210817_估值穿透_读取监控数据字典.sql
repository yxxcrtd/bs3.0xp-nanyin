delete from sys_datadict_list where dict_code='RSK_OUTS_VALTABLE' and item_value='11';
insert into SYS_DATADICT_LIST(DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('RSK_OUTS_VALTABLE','11','存在自动加券失败的资产,具体资产和字段详见错误详情','11','0','system','',sysdate, 'system',sysdate);