delete from sys_datadict_list t where t.DICT_CODE='ORG_TYPE';
delete from sys_datadict t where t.DICT_CODE='ORG_TYPE';
delete from sys_datadict_list t where t.DICT_CODE='ORG_CLASS';
delete from SYS_DATADICT where DICT_CODE = 'ORG_CLASS';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ORG_CLASS','RSK','机构类型_风险',null,'02','system',null,to_timestamp('2020-03-20 11:19:51','yyyy-MM-dd HH24:MI:SS'),'system',to_timestamp('2020-03-20 11:19:51','yyyy-MM-dd HH24:MI:SS'));

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ORG_CLASS', '01', '银行金融机构', 1, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ORG_CLASS', '02', '企业', 2, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ORG_CLASS', '03', '非银金融机构', 3, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ORG_CLASS', '04', '政府机构', 4, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ORG_CLASS', '05', '私募基金管理人', 5, '0', 'system', null, sysdate, 'system', sysdate); 

