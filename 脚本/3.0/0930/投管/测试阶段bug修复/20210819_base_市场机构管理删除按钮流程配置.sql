
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'MstMarketInstitutions002' and ELEMENT_ID = 'MstMarketInstitutions001_btn_delete';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('MstMarketInstitutions002', 'MstMarketInstitutions001_btn_delete', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
