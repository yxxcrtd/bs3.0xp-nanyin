--系统菜单表
delete from SYS_MENU where PAGE_ID in ('TrdProjectNetInvestTrade001');
INSERT INTO SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES 
('INVEST_MANAGEMENT_001_003_034','TRD',NULL,'INVEST_MANAGEMENT_001_003','TrdProjectNetInvestTrade001',34,'02','权益类资产交易','system',NULL,sysdate,'system',sysdate)
;


--------------------------------权益类资产交易画面表------------------------------------------------------------
delete from SYS_PAGE where PAGE_ID in ('TrdProjectNetInvestTrade001','TrdProjectNetInvestTrade002','TrdProjectNetInvestTrade003','TrdProjectNetInvestTrade004','TrdProjectNetInvestTrade005');
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values ('TrdProjectNetInvestTrade001','TRD','/invest/trade/projectnet/TrdProjectNetInvestTrade001','invest/trade/projectnet/TrdProjectNetInvestTrade001','INVEST_MANAGEMENT_001_003_034','','','02','01','权益类资产投资交易查询','system','',sysdate, 'system',sysdate);
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values ('TrdProjectNetInvestTrade002','TRD','/invest/trade/projectnet/TrdProjectNetInvestTrade002','invest/trade/projectnet/TrdProjectNetInvestTrade002','','','','02','01','权益类资产投资交易新建','system','',sysdate, 'system',sysdate);
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values ('TrdProjectNetInvestTrade003','TRD','/invest/trade/projectnet/TrdProjectNetInvestTrade003','invest/trade/projectnet/TrdProjectNetInvestTrade003','','','','02','01','权益类资产投资交易确认','system','',sysdate, 'system',sysdate);
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values ('TrdProjectNetInvestTrade004','TRD','/invest/trade/projectnet/TrdProjectNetInvestTrade004','invest/trade/projectnet/TrdProjectNetInvestTrade004','','','','02','01','权益类资产投资多组合交易新建','system','',sysdate, 'system',sysdate);
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
 values ('TrdProjectNetInvestTrade005','TRD','/invest/trade/projectnet/TrdProjectNetInvestTrade005','invest/trade/projectnet/TrdProjectNetInvestTrade005','','','','02','01','权益类资产投资多组合交易确认','system','',sysdate, 'system',sysdate);


-------------------------------权益类资产交易画面元素表 ----------------------------------------------------------------
delete from SYS_PAGE_ELEMENT where PAGE_ID in ('TrdProjectNetInvestTrade001','TrdProjectNetInvestTrade002');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetInvestTrade001','TrdProjectNetInvestTrade001_btn_add','01','01','','新建','system','',sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetInvestTrade001','TrdProjectNetInvestTrade001_btn_export','01','01','','导出','system','',sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetInvestTrade001','TrdProjectNetInvestTrade001_btn_reset','01','02','','重置','system','',sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetInvestTrade001','TrdProjectNetInvestTrade001_btn_revoke','01','01','','撤销','system','',sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetInvestTrade001','TrdProjectNetInvestTrade001_btn_search','01','01','','查询','system','',sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetInvestTrade002','TrdProjectNetInvestTrade002_btn_save','01','02','','保存','system','',sysdate, 'system',sysdate, null);
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
 values ('TrdProjectNetInvestTrade002','TrdProjectNetInvestTrade002_btn_submit','01','02','','提交','system','',sysdate, 'system',sysdate, null);


----------------------------流程模板 -------------------------------------------------------------------------------
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID in('TrdProjectNetInvestTrade002','TrdProjectNetInvestTrade003');
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('TrdProjectNetInvestTrade002','TrdProjectNetInvestTrade002_btn_save','processForBackCheck','system','0001',sysdate, 'system',sysdate, 'ALL', '02');
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('TrdProjectNetInvestTrade002','TrdProjectNetInvestTrade002_btn_submit','processForBackCheck','system','0001',sysdate, 'system',sysdate, 'ALL', '02');
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('TrdProjectNetInvestTrade002','TrdProjectNetInvestTrade001_btn_revoke','processForBackCheck','system','0001',sysdate, 'system',sysdate, 'ALL', '02');
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('TrdProjectNetInvestTrade003','TrdProjectNetInvestTrade003_btn_save','oneApproval','system','0001',sysdate, 'system',sysdate, 'ALL', '02');
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('TrdProjectNetInvestTrade003','TrdProjectNetInvestTrade003_btn_submit','oneApproval','system','0001',sysdate, 'system',sysdate, 'ALL', '02');
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) 
 values ('TrdProjectNetInvestTrade003','TrdProjectNetInvestTrade001_btn_revoke','oneApproval','system','0001',sysdate, 'system',sysdate, 'ALL', '02');


-------------------------- 002 画面 限制交易方向 ------------------------------------------------------------------
DELETE FROM SYS_PAGE_DICTLIST WHERE PAGE_ID in('TrdProjectNetInvestTrade002','TrdProjectNetInvestTrade001') AND DICT_ID in('PS');
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','PS','T01','买入','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','PS','T02','卖出','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','PS','T04','申购','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','PS','T06','赎回','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','PS','T01','买入','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','PS','T02','卖出','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','PS','T04','申购','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','PS','T06','赎回','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','PS','T05','申购确认','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','PS','T19','赎回确认','','admin','',sysdate, 'admin',sysdate, null);


-- 限制资产分类下拉框枚举值
DELETE FROM SYS_PAGE_DICTLIST WHERE PAGE_ID  IN('TrdProjectNetInvestTrade002','TrdProjectNetInvestTrade001') AND DICT_ID in ('CUSTOM_ASSET_TYPE_TWO', 'CUSTOM_ASSET_TYPE_THREE', 'CUSTOM_ASSET_TYPE_FOUR', 'CUSTOM_ASSET_TYPE_FIVE');
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','CUSTOM_ASSET_TYPE_TWO','04','权益类投资','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','CUSTOM_ASSET_TYPE_TWO','18','私募基金','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','CUSTOM_ASSET_TYPE_TWO','04','权益类投资','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','CUSTOM_ASSET_TYPE_TWO','18','私募基金','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','CUSTOM_ASSET_TYPE_THREE','42','股权','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','CUSTOM_ASSET_TYPE_THREE','66','私募股权投资基金','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','CUSTOM_ASSET_TYPE_THREE','42','股权','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','CUSTOM_ASSET_TYPE_THREE','66','私募股权投资基金','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','CUSTOM_ASSET_TYPE_FOUR','084','未上市公司股权','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','CUSTOM_ASSET_TYPE_FOUR','136','其他类基金','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','CUSTOM_ASSET_TYPE_FOUR','084','未上市公司股权','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','CUSTOM_ASSET_TYPE_FOUR','136','其他类基金','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','CUSTOM_ASSET_TYPE_FIVE','152','未上市公司股权','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade002','999999','CUSTOM_ASSET_TYPE_FIVE','157','产业基金','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','CUSTOM_ASSET_TYPE_FIVE','152','未上市公司股权','','admin','',sysdate, 'admin',sysdate, null);
insert into SYS_PAGE_DICTLIST (PAGE_ID,SUB_PAGE_ID,DICT_ID,DICT_ITEM_VAL,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) 
 values ('TrdProjectNetInvestTrade001','999999','CUSTOM_ASSET_TYPE_FIVE','157','产业基金','','admin','',sysdate, 'admin',sysdate, null);


--数据字典
delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'TRD77';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES 
('COMMON_BUSINESS_TYPE','TRD77','权益类资产交易',77,'0','system',NULL,sysdate,'system',sysdate)
;


--------------------------- 增加平安业务类型 -------------------------------------------------------------------------
delete from SYS_DATADICT_LIST where DICT_CODE = 'PINAN_BUSINESS_TYPE' AND ITEM_CODE in('155','156','157','158','159','160','161');
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
 values ('PINAN_BUSINESS_TYPE','155','权益类资产_申购申请','155','0','system','',sysdate, 'system',sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
 values ('PINAN_BUSINESS_TYPE','156','权益类资产_申购确认','156','0','system','',sysdate, 'system',sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
 values ('PINAN_BUSINESS_TYPE','157','权益类资产_赎回申请','157','0','system','',sysdate, 'system',sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
 values ('PINAN_BUSINESS_TYPE','158','权益类资产_赎回确认','158','0','system','',sysdate, 'system',sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
 values ('PINAN_BUSINESS_TYPE','159','权益类资产_赎回到账','159','0','system','',sysdate, 'system',sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
 values ('PINAN_BUSINESS_TYPE','160','权益类资产_买入','160','0','system','',sysdate, 'system',sysdate);
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
 values ('PINAN_BUSINESS_TYPE','161','权益类资产_卖出','161','0','system','',sysdate, 'system',sysdate);