
-- add by zhouqiang 2022-07-25 系统参数：导入时单个excel文件允许的最大行数 start
delete from SYS_PARA where SYSTEM_CODE='DIS' and PARA_CODE='IMPORT_EXCEL_ROW_COUNT';
Insert into SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,UPDATE_FLG)
values ('DIS','IMPORT_EXCEL_ROW_COUNT','50000','01','整数','导入时单个excel文件允许的最大行数','system',null,sysdate,'system',sysdate,null,'01');

--合作机构维护页面菜单配置
delete from SYS_MENU where MENU_ID='REPORT_006_004_005' and SYSTEM_CODE='DIS';
delete from SYS_PAGE where PAGE_ID in('ProdPartner001','ProdPartner002');
Insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('REPORT_006_004_005','DIS',null,'REPORT_006_004','ProdPartner001',5,'02','合作机构信息维护','system',null,sysdate,'system',sysdate);

Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('ProdPartner001','DIS','/disclosure/information/config/prodpartner/ProdPartner001','disclosure/information/config/prodpartner/ProdPartner001','REPORT_006_004_005',null,null,'02','01','合作机构信息维护','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('ProdPartner002','DIS','/disclosure/information/config/prodpartner/ProdPartner002','disclosure/information/config/prodpartner/ProdPartner002',null,null,null,'02','01','合作机构信息新建','system',null,sysdate,'system',sysdate);


-- 合作机构维护页面控件流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'ProdPartner002';
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('ProdPartner002','ProdPartner001_btn_delete','oneApproval','system','0001',sysdate,'system',sysdate,'ALL','02');
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('ProdPartner002','ProdPartner002_btn_save','oneApproval','system','0001',sysdate,'system',sysdate,'ALL','02');
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('ProdPartner002','ProdPartner002_btn_submit','oneApproval','system','0001',sysdate,'system',sysdate,'ALL','02');

--产品资产组合情况页面菜单配置
delete from SYS_MENU where MENU_ID='REPORT_006_004_007' and SYSTEM_CODE='DIS';
delete from SYS_PAGE where PAGE_ID in('ProdAssetInfo001','ProdAssetInfo002');
Insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('REPORT_006_004_007','DIS',null,'REPORT_006_004','ProdAssetInfo001',7,'02','产品资产组合情况维护','system',null,sysdate,'system',sysdate);

Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('ProdAssetInfo001','DIS','/disclosure/information/config/prodassetinfo/ProdAssetInfo001','disclosure/information/config/prodassetinfo/ProdAssetInfo001','REPORT_006_004_007',null,null,'02','01','产品资产组合情况维护','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('ProdAssetInfo002','DIS','/disclosure/information/config/prodassetinfo/ProdAssetInfo002','disclosure/information/config/prodassetinfo/ProdAssetInfo002',null,null,null,'02','01','产品资产组合新建','system',null,sysdate,'system',sysdate);

-- 产品资产组合情况页面控件流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'ProdAssetInfo002';
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('ProdAssetInfo002','ProdAssetInfo001_btn_delete','oneApproval','system','0001',sysdate,'system',sysdate,'ALL','02');
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('ProdAssetInfo002','ProdAssetInfo002_btn_save','oneApproval','system','0001',sysdate,'system',sysdate,'ALL','02');
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('ProdAssetInfo002','ProdAssetInfo002_btn_submit','oneApproval','system','0001',sysdate,'system',sysdate,'ALL','02');

--共通业务类型添加：合作机构信息维护、产品资产组合情况维护
delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE in('DIS05','DIS06');
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE)
values ('COMMON_BUSINESS_TYPE','DIS05','合作机构信息维护',299,'0','system',null,sysdate,'system',sysdate,null);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE)
values ('COMMON_BUSINESS_TYPE','DIS06','产品资产组合情况维护',300,'0','system',null,sysdate,'system',sysdate,null);


-- add by zhouqiang 20220725  end