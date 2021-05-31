--存款类资产类型：新增数据项【03 同业借款】
DELETE  FROM SYS_DATADICT_LIST WHERE DICT_CODE='DEPOSIT_TYPE' AND ITEM_CODE='03';
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('DEPOSIT_TYPE','03','同业借款','1','0','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');


--共通业务类型增加拆借类资产和回购类资产，数据权限授权机构表新增数据时使用
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'V33';
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'V34';
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'V35';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('COMMON_BUSINESS_TYPE', 'V33', '拆借类资产', 33, '0', 'system', null, sysdate, 'system', sysdate, 'ALL');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('COMMON_BUSINESS_TYPE', 'V34', '回购类资产', 34, '0', 'system', null, sysdate, 'system', sysdate, 'ALL');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('COMMON_BUSINESS_TYPE', 'V35', '债券借贷标的信息', 35, '0', 'system', null, sysdate, 'system', sysdate, 'ALL');


DELETE FROM  SYS_DATADICT WHERE DICT_CODE ='SHENSHU_SPEED';
DELETE FROM  SYS_DATADICT_LIST sdl WHERE DICT_CODE ='SHENSHU_SPEED';
insert into SYS_DATADICT values ('SHENSHU_SPEED','VAL','申购赎回确认速度','','02','system','',to_date('2021/03/23', 'yyyy/mm/dd'), 'system',to_date('2021/03/23', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('SHENSHU_SPEED','00','T+0','1','0','system','',to_date('2021/03/23', 'yyyy/mm/dd'), 'system',to_date('2021/03/23', 'yyyy/mm/dd'), 'ALL');
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('SHENSHU_SPEED','01','T+1','2','0','system','',to_date('2021/03/23', 'yyyy/mm/dd'), 'system',to_date('2021/03/23', 'yyyy/mm/dd'), 'ALL');
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('SHENSHU_SPEED','02','T+2','3','0','system','',to_date('2021/03/23', 'yyyy/mm/dd'), 'system',to_date('2021/03/23', 'yyyy/mm/dd'), 'ALL');

