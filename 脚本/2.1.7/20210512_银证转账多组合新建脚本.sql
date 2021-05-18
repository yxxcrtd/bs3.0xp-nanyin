/*银证转账多组合脚本 add by zhuruqing 20201104 start*/
DELETE FROM SYS_PAGE WHERE PAGE_ID = 'PtlFundAllocation003';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlFundAllocation003', 'PTL', '/invest/portfolio/ptlfundallocation/PtlFundAllocation003', '/invest/portfolio/ptlfundallocation/PtlFundAllocation003', null, null, null, '03', '01', '银证转账多组合新建', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'PtlFundAllocation003';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, INSTITUTION_CODE, IS_SEND_UORG, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlFundAllocation003', 'PtlFundAllocation003_btn_save', 'ALL', '02', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, INSTITUTION_CODE, IS_SEND_UORG, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlFundAllocation003', 'PtlFundAllocation003_btn_submit', 'ALL', '02', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);
/*银证转账多组合脚本 add by zhuruqing 20201104 end*/


/*银证转账下拉框枚举 by caizhi  20201105 start*/
DELETE FROM SYS_PAGE_DICTLIST WHERE PAGE_ID='PtlFundAllocation003';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlFundAllocation003', '999999', 'ORDER_TYPE', '1001', '银证转账', null, 'admin', null, sysdate, 'admin', sysdate);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlFundAllocation003', '999999', 'ORDER_TYPE', '1002', '证银转账', null, 'admin', null, sysdate, 'admin', sysdate);
/*银证转账下拉框枚举 by caizhi  20201105 end*/ 
