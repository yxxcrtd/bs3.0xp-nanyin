delete from  SYS_PAGE where page_id = 'CfetsBondDealQuotedPrice004';
insert into SYS_PAGE  values ('CfetsBondDealQuotedPrice004','CFETS','/invest/bankuplink/bond/CfetsBondDealQuotedPrice004','invest/bankuplink/bond/CfetsBondDealQuotedPrice004',null,null,null,'02','01','CFETS多组合现券买卖对话报价','system',null,sysdate,'system',sysdate);

delete from  SYS_PAGE where page_id = 'CfetsQuotedCommon001';
insert into SYS_PAGE  values ('CfetsQuotedCommon001','CFETS','/invest/bankuplink/common/CfetsQuotedCommon001','invest/bankuplink/common/CfetsQuotedCommon001',null,null,null,'02','01','CFETS多组合分配页面','system',null,sysdate,'system',sysdate);


delete from  SYS_WFL_BUSINESS_MODULE where page_id = 'CfetsBondDealQuotedPrice004';
insert into SYS_WFL_BUSINESS_MODULE VALUES('CfetsBondDealQuotedPrice004', 'CfetsBondDealQuotedPrice004_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate,'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE VALUES('CfetsBondDealQuotedPrice004', 'CfetsBondDealQuotedPrice004_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate,'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE values ('CfetsBondDealQuotedPrice004','CfetsBondDealQuotedPrice004_btn_done','oneApproval','system','0001',sysdate, 'system',sysdate,'All','02');


delete from SYS_PAGE_DICTLIST where page_id='CfetsQuotedCommon001' and DICT_ID = 'DISTRIBUTION' ;
insert into SYS_PAGE_DICTLIST  values ('CfetsQuotedCommon001','999999','DISTRIBUTION','139',null,null,'system',null,sysdate,'system',sysdate,'');
insert into SYS_PAGE_DICTLIST  values ('CfetsQuotedCommon001','999999','DISTRIBUTION','143',null,null,'system',null,sysdate,'system',sysdate,'');



delete from SYS_PAGE_DICTLIST where page_id='CfetsBondDealQuotedPrice004' and DICT_ID = 'ACCOUNT_SPEED';
insert into SYS_PAGE_DICTLIST  values ('CfetsBondDealQuotedPrice004','999999','ACCOUNT_SPEED','01',null,null,'system',null,sysdate,'system',sysdate,'');
insert into SYS_PAGE_DICTLIST  values ('CfetsBondDealQuotedPrice004','999999','ACCOUNT_SPEED','02',null,null,'system',null,sysdate,'system',sysdate,'');


delete from SYS_PAGE_DICTLIST where page_id='CfetsBondDealQuotedPrice004' and DICT_ID = 'PS';
insert into SYS_PAGE_DICTLIST  values ('CfetsBondDealQuotedPrice004','999999','PS','T01',null,null,'system',null,sysdate,'system',sysdate,'');
insert into SYS_PAGE_DICTLIST  values ('CfetsBondDealQuotedPrice004','999999','PS','T02',null,null,'system',null,sysdate,'system',sysdate,'');

delete from SYS_PAGE_DICTLIST where page_id='CfetsBondDealQuotedPrice004' and DICT_ID = 'SETT_MODE';
insert into SYS_PAGE_DICTLIST  values ('CfetsBondDealQuotedPrice004','999999','SETT_MODE','01',null,null,'system',null,sysdate,'system',sysdate,'');
insert into SYS_PAGE_DICTLIST  values ('CfetsBondDealQuotedPrice004','999999','SETT_MODE','02',null,null,'system',null,sysdate,'system',sysdate,'');
insert into SYS_PAGE_DICTLIST  values ('CfetsBondDealQuotedPrice004','999999','SETT_MODE','03',null,null,'system',null,sysdate,'system',sysdate,'');
insert into SYS_PAGE_DICTLIST  values ('CfetsBondDealQuotedPrice004','999999','SETT_MODE','07',null,null,'system',null,sysdate,'system',sysdate,'');

delete from SYS_PAGE_DICTLIST where page_id='CfetsBondDealQuotedPrice001' and DICT_ID = 'ACCOUNT_SPEED';
insert into SYS_PAGE_DICTLIST  values ('CfetsBondDealQuotedPrice001','999999','ACCOUNT_SPEED','01',null,null,'system',null,sysdate,'system',sysdate,'');
insert into SYS_PAGE_DICTLIST  values ('CfetsBondDealQuotedPrice001','999999','ACCOUNT_SPEED','02',null,null,'system',null,sysdate,'system',sysdate,'');


DELETE FROM SYS_PAGE where PAGE_ID='CfetsBondDealQuotedPrice005';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('CfetsBondDealQuotedPrice005', 'CFETS', '/invest/bankuplink/bond/CfetsBondDealQuotedPrice005', 'invest/bankuplink/bond/CfetsBondDealQuotedPrice005', null, null, null, '02', '01', 'CFETS多组合现券买卖请求报价', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_WFL_BUSINESS_MODULE where PAGE_ID='CfetsBondDealQuotedPrice005';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME,INSTITUTION_CODE ,IS_SEND_UORG )
VALUES ('CfetsBondDealQuotedPrice005', 'CfetsBondDealQuotedPrice005_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate,'ALL','02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME,INSTITUTION_CODE ,IS_SEND_UORG)
VALUES ('CfetsBondDealQuotedPrice005', 'CfetsBondDealQuotedPrice005_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate,'ALL','02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME,INSTITUTION_CODE ,IS_SEND_UORG)
VALUES ('CfetsBondDealQuotedPrice005', 'CfetsBondDealQuotedPrice005_btn_done', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate,'ALL','02');
