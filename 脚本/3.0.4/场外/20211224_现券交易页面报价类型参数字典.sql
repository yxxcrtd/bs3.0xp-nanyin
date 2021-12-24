--现券交易页面 报价类型参数字典
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'IbuQuoteRepo004';

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('IbuQuoteRepo004', 'IbuQuoteRepo004_btn_revoke', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('IbuQuoteRepo004', 'IbuQuoteRepo004_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('IbuQuoteRepo004', 'IbuQuoteRepo004_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
