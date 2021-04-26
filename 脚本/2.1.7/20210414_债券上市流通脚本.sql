delete from sys_menu where menu_id = 'INVEST_MANAGEMENT_001_003_032';
Insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('INVEST_MANAGEMENT_001_003_032','TRD',null,'INVEST_MANAGEMENT_001_003','TrdBondMarketTrade001',32,'02','债券上市流通','system',null,sysdate,'system',sysdate);
	
delete from sys_page where page_id in ('TrdBondMarketTrade001','TrdBondMarketTrade002');
Insert into sys_page (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdBondMarketTrade001','TRD','/invest/trade/bondmarkettrade/TrdBondMarketTrade001','invest/trade/bondmarkettrade/TrdBondMarketTrade001',null,null,null,'02','01','债券上市流通','system',null,sysdate,'system',sysdate);
Insert into sys_page (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdBondMarketTrade002','TRD','/invest/trade/bondmarkettrade/TrdBondMarketTrade002','invest/trade/bondmarkettrade/TrdBondMarketTrade002',null,null,null,'02','01','债券上市流通新建','system',null,sysdate,'system',sysdate);
						
						
delete from SYS_PAGE_ELEMENT where page_id in('TrdBondMarketTrade001','TrdBondMarketTrade002') ;
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('TrdBondMarketTrade001','TrdBondMarketTrade001_btn_add','01','01',null,'新建','system',null,sysdate,'system',sysdate,'C');
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('TrdBondMarketTrade001','TrdBondMarketTrade001_btn_reset','01','02',null,'重置','system',null,sysdate,'system',sysdate,'C');
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('TrdBondMarketTrade001','TrdBondMarketTrade001_btn_revoke','01','01',null,'撤销','system',null,sysdate,'system',sysdate,'C');
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('TrdBondMarketTrade001','TrdBondMarketTrade001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');

Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('TrdBondMarketTrade002','TrdBondMarketTrade002_btn_save','01','02',null,'保存','system',null,sysdate,'system',sysdate,'C');
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG) values ('TrdBondMarketTrade002','TrdBondMarketTrade002_btn_submit','01','02',null,'提交','system',null,sysdate,'system',sysdate,'C');


delete from SYS_WFL_BUSINESS_MODULE where page_id = 'TrdBondMarketTrade002';
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('TrdBondMarketTrade002','TrdBondMarketTrade001_btn_revoke','oneApproval','system',null,sysdate,'system',sysdate,'ALL','02');
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('TrdBondMarketTrade002','TrdBondMarketTrade002_btn_save','oneApproval','system',null,sysdate,'system',sysdate,'ALL','02');
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('TrdBondMarketTrade002','TrdBondMarketTrade002_btn_submit','oneApproval','system',null,sysdate,'system',sysdate,'ALL','02');


--金融产品附属表新增字段
ALTER TABLE FIN_PRODUCT_ADD DROP COLUMN IS_ISSUE_ASSET;
ALTER TABLE FIN_PRODUCT_ADD ADD IS_ISSUE_ASSET VARCHAR2(2);
COMMENT ON COLUMN FIN_PRODUCT_ADD.IS_ISSUE_ASSET IS '是否发行标的' ;

ALTER TABLE FIN_PRODUCT_ADD DROP COLUMN APPEAR_MARKET_ID;
ALTER TABLE FIN_PRODUCT_ADD ADD APPEAR_MARKET_ID VARCHAR2(50);
COMMENT ON COLUMN FIN_PRODUCT_ADD.APPEAR_MARKET_ID IS '上市代码' ;