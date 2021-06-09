
-- 资产减值
--/** ===============业务流程模板:资产减值管理 add by lby 20201013 start=============== **/
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TrdAssetLoss002';                                                                                                                                                                                                    
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLoss002','TrdAssetLoss002_btn_save','oneApproval','system',null,sysdate, 'system',sysdate);                                                                                                                               
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLoss002','TrdAssetLoss002_btn_submit','oneApproval','system',null,sysdate, 'system',sysdate); 
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLoss002','TrdAssetLoss001_btn_revoke','oneApproval','system',null,sysdate, 'system',sysdate); 
--/** ===============业务流程模板:资产减值管理 add by lby 20201013 end=============== **/



--/** ===============业务流程模板:项目类/计划类利率型估值行情 add by lby 20201023 start=============== **/
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'ComRateAssetQuotationInfo002';                                                                                                                                                                                                    
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ComRateAssetQuotationInfo002','ComRateAssetQuotationInfo002_btn_save','oneApproval','system',null,sysdate, 'system',sysdate);                                                                                                                               
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ComRateAssetQuotationInfo002','ComRateAssetQuotationInfo002_btn_submit','oneApproval','system',null,sysdate, 'system',sysdate);
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ComRateAssetQuotationInfo002','ComRateAssetQuotationInfo002_btn_delete','oneApproval','system',null,sysdate, 'system',sysdate); 