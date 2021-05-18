-- sppI 测试
delete from SYS_WFL_BUSINESS_MODULE where ELEMENT_ID = 'SppiTest002_btn_submit';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateBondPwc', 'SppiTest002_btn_submit', 'oneApproval', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateBondDtt', 'SppiTest002_btn_submit', 'oneApproval', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateBondKpmg', 'SppiTest002_btn_submit', 'oneApproval', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateNotBondPwc', 'SppiTest002_btn_submit', 'oneApproval', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateNotBondDtt', 'SppiTest002_btn_submit', 'oneApproval', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateNotBondKpmg', 'SppiTest002_btn_submit', 'oneApproval', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTest002', 'SppiTest002_btn_submit', 'oneApproval', 'system', null, systimestamp, 'system', systimestamp);
-- 资产减值
/** ===============业务流程模板:资产减值管理 add by lby 20201013 start=============== **/
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TrdAssetLoss002';                                                                                                                                                                                                    
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLoss002','TrdAssetLoss002_btn_save','oneApproval','system',null,sysdate, 'system',sysdate);                                                                                                                               
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLoss002','TrdAssetLoss002_btn_submit','oneApproval','system',null,sysdate, 'system',sysdate); 
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLoss002','TrdAssetLoss001_btn_revoke','oneApproval','system',null,sysdate, 'system',sysdate); 
/** ===============业务流程模板:资产减值管理 add by lby 20201013 end=============== **/



/** ===============业务流程模板:项目类/计划类利率型估值行情 add by lby 20201023 start=============== **/
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'MstRateAssetQuotationInfo002';                                                                                                                                                                                                    
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstRateAssetQuotationInfo002','MstRateAssetQuotationInfo002_btn_save','oneApproval','system',null,sysdate, 'system',sysdate);                                                                                                                               
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstRateAssetQuotationInfo002','MstRateAssetQuotationInfo002_btn_submit','oneApproval','system',null,sysdate, 'system',sysdate);
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstRateAssetQuotationInfo002','MstRateAssetQuotationInfo002_btn_delete','oneApproval','system',null,sysdate, 'system',sysdate); 
/** ===============业务流程模板:项目类/计划类利率型估值行情 add by lby 20201023 end=============== **/

delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TrdAssetLossClear002' and ELEMENT_ID = 'TrdAssetLossClear002_btn_submit';
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear002','TrdAssetLossClear002_btn_submit','oneApproval','system',null,systimestamp,'system',systimestamp);

delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TrdAssetLossClear002' and ELEMENT_ID = 'TrdAssetLossClear002_btn_save';
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear002','TrdAssetLossClear002_btn_save','oneApproval','system',null,systimestamp,'system',systimestamp);

delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TrdAssetLossClear003' and ELEMENT_ID = 'TrdAssetLossClear001_btn_revoke';
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear003','TrdAssetLossClear001_btn_revoke','oneApproval','system',null,systimestamp,'system',systimestamp);

delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TrdAssetLossClear002' and ELEMENT_ID = 'TrdAssetLossClear001_btn_revoke';
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear002','TrdAssetLossClear001_btn_revoke','oneApproval','system',null,systimestamp,'system',systimestamp);

delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TrdAssetLossClear001' and ELEMENT_ID = 'TrdAssetLossClear001_btn_revoke';
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear001','TrdAssetLossClear001_btn_revoke','oneApproval','system',null,systimestamp,'system',systimestamp);
