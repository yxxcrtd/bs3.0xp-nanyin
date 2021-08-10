
delete from mst_calendar_info where CALENDAR_ID in ('13','14','15');
INSERT INTO mst_calendar_info (CALENDAR_ID, CALENDAR_NAME, OTHER_ID, IS_TRADE_CALENDAR, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('13', '沪港通交易日历', null, 'N', null, 'system', null, sysdate, 'system', sysdate);
INSERT INTO mst_calendar_info (CALENDAR_ID, CALENDAR_NAME, OTHER_ID, IS_TRADE_CALENDAR, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('14', '深港通交易日历', null, 'N', null, 'system', null, sysdate, 'system', sysdate);
INSERT INTO mst_calendar_info (CALENDAR_ID, CALENDAR_NAME, OTHER_ID, IS_TRADE_CALENDAR, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('15', '沪港通'||'&'||'深港通交易日历', null, 'N', null, 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'TRADE_PLACE' and ITEM_CODE = '13';
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('TRADE_PLACE', '13', '香港联合交易所', 13, '0', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_dictlist where PAGE_ID = 'FinStockInfo001' and DICT_ID = 'SH_OR_ZH' and DICT_ITEM_VAL in ('01','02','04');
delete from sys_page_dictlist where PAGE_ID = 'FinStockInfo002' and DICT_ID = 'SH_OR_ZH' and DICT_ITEM_VAL in ('01','02','04');
delete from sys_page_dictlist where PAGE_ID = 'FinStockInfo001' and DICT_ID = 'TRADE_PLACE' and DICT_ITEM_VAL in ('13');
delete from sys_page_dictlist where PAGE_ID = 'FinStockInfo002' and DICT_ID = 'TRADE_PLACE' and DICT_ITEM_VAL in ('13');
delete from sys_page_dictlist where PAGE_ID = 'FinStockInfo001' and DICT_ID = 'CCY' and DICT_ITEM_VAL in ('HKD');
delete from sys_page_dictlist where PAGE_ID = 'FinStockInfo002' and DICT_ID = 'CCY' and DICT_ITEM_VAL in ('HKD');
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FinStockInfo002', '999999', 'SH_OR_ZH', '01', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FinStockInfo002', '999999', 'SH_OR_ZH', '02', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FinStockInfo002', '999999', 'SH_OR_ZH', '04', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FinStockInfo002', '999999', 'TRADE_PLACE', '13', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FinStockInfo001', '999999', 'SH_OR_ZH', '01', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FinStockInfo001', '999999', 'SH_OR_ZH', '02', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FinStockInfo001', '999999', 'SH_OR_ZH', '04', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FinStockInfo001', '999999', 'TRADE_PLACE', '13', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FinStockInfo001', '999999', 'CCY', 'HKD', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FinStockInfo002', '999999', 'CCY', 'HKD', null, null, 'admin', null, sysdate, 'admin', sysdate, null);

delete from sys_datadict_list_link where DICT_CODE = 'BOARD' and ITEM_CODE = 'BD01' and LINKED_DICT_CODE = 'TRADE_PLACE' and LINKED_ITEM_CODE = '13';
delete from sys_datadict_list_link where DICT_CODE = 'BOARD' and ITEM_CODE = 'BD04' and LINKED_DICT_CODE = 'TRADE_PLACE' and LINKED_ITEM_CODE = '13';
INSERT INTO sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BOARD', 'BD01', 'TRADE_PLACE', '13', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BOARD', 'BD04', 'TRADE_PLACE', '13', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'SH_OR_ZH' and ITEM_CODE = '04';
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SH_OR_ZH', '04', '沪港通'||'&'||'深港通', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

alter table fin_product_add drop column MIN_SALE_UNIT;
alter table fin_product_add add  MIN_SALE_UNIT  number;
comment on column fin_product_add.MIN_SALE_UNIT is '最小买卖单位';



----证券账户管理新增港股通优化改造
delete from sys_datadict where DICT_CODE = 'Y_N_HKSTOCKC';
delete from sys_datadict_list where DICT_CODE = 'Y_N_HKSTOCKC';
INSERT INTO sys_datadict(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('Y_N_HKSTOCKC','TG','是否开通港股通', null, '02', 'system', null, sysdate, 'system', sysdate);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('Y_N_HKSTOCKC', '01', '是', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('Y_N_HKSTOCKC', '02', '否', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

alter table stl_sec_acct_info drop column  Y_N_HKSTOCKC;
alter table stl_sec_acct_info add  Y_N_HKSTOCKC varchar(50);
comment on column stl_sec_acct_info.Y_N_HKSTOCKC is '是否开通港股通';
