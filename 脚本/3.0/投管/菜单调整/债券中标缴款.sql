--债券中标缴款
delete from SYS_PAGE where PAGE_ID in ('EtrBondBiddingResultIb001', 'EtrBondBiddingResultEx001');
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 VALUES ('EtrBondBiddingResultIb001', 'TRD', '/invest/entrust/etrbondbiddingresult/EtrBondBiddingResultIb001', 'invest/entrust/etrbondbiddingresult/EtrBondBiddingResult001', 'DEAL_MANAGEMENT_001_002_002', null, null, '02', '01', '债券中标缴款', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 VALUES ('EtrBondBiddingResultEx001', 'TRD', '/invest/entrust/etrbondbiddingresult/EtrBondBiddingResultEx001', 'invest/entrust/etrbondbiddingresult/EtrBondBiddingResult001', 'DEAL_MANAGEMENT_001_003_002', null, null, '02', '01', '债券中标缴款', 'system', null, sysdate, 'system', sysdate);


delete from SYS_PAGE_DICTLIST where PAGE_ID = 'EtrBondBiddingResultIb001';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrBondBiddingResultIb001', '999999', 'PAYMENT_STATUS', '02', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrBondBiddingResultIb001', '999999', 'TRADE_PLACE', '01', null, null, 'admin', null, sysdate, 'admin', sysdate, null);

delete from SYS_PAGE_DICTLIST where PAGE_ID = 'EtrBondBiddingResultEx001';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrBondBiddingResultEx001', '999999', 'PAYMENT_STATUS', '02', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrBondBiddingResultEx001', '999999', 'TRADE_PLACE', '03', null, null, 'admin', null, sysdate, 'admin', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EtrBondBiddingResultEx001', '999999', 'TRADE_PLACE', '04', null, null, 'admin', null, sysdate, 'admin', sysdate, null);


delete from SYS_PAGE_ELEMENT where PAGE_ID = 'EtrBondBiddingResultIb001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('EtrBondBiddingResultIb001', 'EtrBondBiddingResultIb001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('EtrBondBiddingResultIb001', 'EtrBondBiddingResultIb001_btn_payment', '01', '01', null, '分销缴款', 'system', null, sysdate, 'system', sysdate, 'C');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('EtrBondBiddingResultIb001', 'EtrBondBiddingResultIb001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM');

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'EtrBondBiddingResultEx001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('EtrBondBiddingResultEx001', 'EtrBondBiddingResultEx001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('EtrBondBiddingResultEx001', 'EtrBondBiddingResultEx001_btn_payment', '01', '01', null, '分销缴款', 'system', null, sysdate, 'system', sysdate, 'C');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('EtrBondBiddingResultEx001', 'EtrBondBiddingResultEx001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM');
