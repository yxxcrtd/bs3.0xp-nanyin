delete from DIS_TRU_BANK where TRU_NAME='南京银行股份有限公司';
insert into DIS_TRU_BANK (TRU_UUID, TRU_NAME, TRU_SIMPLE_NAME, DATA_STATUS, CREATE_USER, CREATE_TIME,UPDATE_USER,UPDATE_TIME,CREATE_DEPT) 
	values ('27025-53F3-74F8-E055-000000000001', '南京银行股份有限公司', '南京银行', 'E', 'system',sysdate, 'system',sysdate,null);

 
delete from sys_datadict where DICT_CODE='DIS_INDEX_YEAR' ;
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_INDEX_YEAR', 'DIS', '指标年度', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='DIS_INDEX_YEAR';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2020', '2020', 1, '2022', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2021', '2021', 2, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2022', '2022', 3, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2023', '2023', 4, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2024', '2024', 5, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2025', '2025', 6, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2026', '2026', 7, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2027', '2027', 8, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2028', '2028', 9, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2029', '2029', 10, '2022', 'system', null, sysdate, 'system', sysdate, null);	

delete from sys_datadict where DICT_CODE='DIS_INDEX_PERIOD' ;
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_INDEX_PERIOD', 'DIS', '指标周期', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='DIS_INDEX_PERIOD';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'Q1', '一季度', 1, 'TY', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'Q2', '二季度', 2, 'TY', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'Q3', '三季度', 3, 'TY', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'Q4', '四季度', 4, 'TY', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'HY', '半年', 5, 'TY', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'TY', '全年', 6, 'TY', 'system', null, sysdate, 'system', sysdate, null);	

