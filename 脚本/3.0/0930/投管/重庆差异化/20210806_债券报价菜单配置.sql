delete FROM sys_page where page_id in ('CfetsBondDealQuotedPriceMain','CfetsBondDealQuotedPriceSingle','CfetsBondDealQuotedPriceSingle001','CfetsBondDealQuotedPriceSingle002','CfetsBondDealQuotedPriceMuti');


insert into sys_page values ('CfetsBondDealQuotedPriceMain','TRD','/invest/bankuplink/bond/CfetsBondDealQuotedPriceMain',
'invest/bankuplink/bond/CfetsBondDealQuotedPriceMain',null,null,null,'02','01','CFETS现券买卖报价(新)','system',null,date'2021-08-06','system',date'2021-08-06');
insert into sys_page values ('CfetsBondDealQuotedPriceSingle','TRD','/invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle',
'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle',null,null,null,'02','01','债券单组合交易下单','system',null,date'2021-08-06','system',date'2021-08-06');
insert into sys_page values ('CfetsBondDealQuotedPriceSingle001','TRD','/invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001',
'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001',null,null,null,'02','01','债券单组合对话报价','system',null,date'2021-08-06','system',date'2021-08-06');
insert into sys_page values ('CfetsBondDealQuotedPriceSingle002','TRD','/invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002',
'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002',null,null,null,'02','01','债券单组合请求报价','system',null,date'2021-08-06','system',date'2021-08-06');
insert into sys_page values ('CfetsBondDealQuotedPriceMuti','TRD','/invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti',
'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti',null,null,null,'02','01','债券多组合交易下单','system',null,date'2021-08-06','system',date'2021-08-06');
insert into sys_page values ('CfetsBondDealQuotedPriceMuti001','TRD','/invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001',
'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001',null,null,null,'02','01','债券多组合对话报价','system',null,date'2021-08-06','system',date'2021-08-06');
insert into sys_page values ('CfetsBondDealQuotedPriceMuti002','TRD','/invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002',
'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002',null,null,null,'02','01','债券多组合请求报价','system',null,date'2021-08-06','system',date'2021-08-06');


delete FROM sys_menu where menu_id = 'IB_DEAL_MANAGEMENT_001_002_004' and page_id = 'CfetsBondDealQuotedPriceMain';
insert into sys_menu values ('IB_DEAL_MANAGEMENT_001_002_004','STL',null,'IB_DEAL_MANAGEMENT_001_002','CfetsBondDealQuotedPriceMain',4,'02',
'CFETS现券买卖报价(新)','system','',date'2021-08-10','system',date'2021-08-10');



DELETE FROM sys_wfl_business_module WHERE PAGE_ID IN ('CfetsBondDealQuotedPriceSingle001','CfetsBondDealQuotedPriceSingle002','CfetsBondDealQuotedPriceMuti001','CfetsBondDealQuotedPriceMuti002');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceSingle001', 'CfetsBondDealQuotedPriceSingle001_btn_bondRevoke', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceSingle001', 'CfetsBondDealQuotedPriceSingle001_btn_done', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceSingle001', 'CfetsBondDealQuotedPriceSingle001_btn_save', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceSingle001', 'CfetsBondDealQuotedPriceSingle001_btn_submit', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceSingle002', 'CfetsBondDealQuotedPriceSingle002_btn_bondRevoke', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceSingle002', 'CfetsBondDealQuotedPriceSingle002_btn_done', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceSingle002', 'CfetsBondDealQuotedPriceSingle002_btn_save', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceSingle002', 'CfetsBondDealQuotedPriceSingle002_btn_submit', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceMuti001', 'CfetsBondDealQuotedPriceMuti001_btn_done', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'All', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceMuti001', 'CfetsBondDealQuotedPriceMuti001_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceMuti001', 'CfetsBondDealQuotedPriceMuti001_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceMuti002', 'CfetsBondDealQuotedPriceMuti002_btn_done', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceMuti002', 'CfetsBondDealQuotedPriceMuti002_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsBondDealQuotedPriceMuti002', 'CfetsBondDealQuotedPriceMuti002_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');



delete from sys_page_dictlist where page_id in ('CfetsBondDealQuotedPriceSingle001','CfetsBondDealQuotedPriceSingle002');
insert into sys_page_dictlist values ('CfetsBondDealQuotedPriceSingle001','999999','ACCOUNT_SPEED','01','','','system','',sysdate,'system',sysdate,'');
insert into sys_page_dictlist values ('CfetsBondDealQuotedPriceSingle001','999999','ACCOUNT_SPEED','02','','','system','',sysdate,'system',sysdate,'');
insert into sys_page_dictlist values ('CfetsBondDealQuotedPriceSingle002','999999','ACCOUNT_SPEED','01','','','system','',sysdate,'system',sysdate,'');
insert into sys_page_dictlist values ('CfetsBondDealQuotedPriceSingle002','999999','ACCOUNT_SPEED','02','','','system','',sysdate,'system',sysdate,'');
insert into sys_page_dictlist values ('CfetsBondDealQuotedPriceSingle001','999999','SETT_MODE','01','','','system','',sysdate,'system',sysdate,'');
insert into sys_page_dictlist values ('CfetsBondDealQuotedPriceSingle001','999999','SETT_MODE','02','','','system','',sysdate,'system',sysdate,'');
insert into sys_page_dictlist values ('CfetsBondDealQuotedPriceSingle001','999999','SETT_MODE','03','','','system','',sysdate,'system',sysdate,'');
insert into sys_page_dictlist values ('CfetsBondDealQuotedPriceSingle002','999999','SETT_MODE','01','','','system','',sysdate,'system',sysdate,'');
insert into sys_page_dictlist values ('CfetsBondDealQuotedPriceSingle002','999999','SETT_MODE','02','','','system','',sysdate,'system',sysdate,'');
insert into sys_page_dictlist values ('CfetsBondDealQuotedPriceSingle002','999999','SETT_MODE','03','','','system','',sysdate,'system',sysdate,'');


-- 上行接口参数
delete from sys_para where para_code = 'IS_NEED_UPLINK';
insert into sys_para values ('SYS','IS_NEED_UPLINK','N','','','是否打开上行接口','system','',sysdate,'system',sysdate,'','01');