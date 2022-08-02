
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


-- add by dingrui 20220726  start
-- 新增"产品计划排期" 菜单
DELETE FROM SYS_MENU WHERE SYS_MENU.MENU_ID = 'REPORT_006_004' AND SYS_MENU.REMARK = '信披数据维护';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004', 'DIS', null, 'REPORT_006', '', 1, '02', '信披数据维护', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_MENU WHERE SYS_MENU.MENU_ID = 'REPORT_006_004_001' AND SYS_MENU.REMARK = '产品计划排期';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004_001', 'DIS', null, 'REPORT_006_004', 'DisProdPlanManage001', 1, '02', '产品计划排期', 'system', null, SYSDATE, 'system', SYSDATE);

-- 新增 "产品计划排期" 画面
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('DisProdPlanManage001');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('DisProdPlanManage001', 'DIS', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage001', 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001',
       'REPORT_006_004_001', NULL, NULL, '02', '01', '产品计划排期', 'system', NULL, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('DisProdPlanManage002');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('DisProdPlanManage002', 'DIS', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage002', 'disclosure/information/config/disprodplanmanage/DisProdPlanManage002',
       '', NULL, NULL, '02', '01', '产品计划排期新增', 'system', NULL, sysdate, 'system', sysdate);


-- 新增"产品计划排期" 画面按钮
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'DisProdPlanManage001';
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('DisProdPlanManage001', 'DisProdPlanManage001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('DisProdPlanManage001', 'DisProdPlanManage001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('DisProdPlanManage001', 'DisProdPlanManage001_btn_design', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('DisProdPlanManage001', 'DisProdPlanManage001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('DisProdPlanManage001', 'DisProdPlanManage001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('DisProdPlanManage001', 'DisProdPlanManage001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('DisProdPlanManage001', 'DisProdPlanManage001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

-- 新增"数据状态"字典信息
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_DATA_STATUS', 'DIS', '数据状态', 'DIS_DATA_STATUS', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_STATUS', '01', '有效', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_STATUS', '02', '删除', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
-- "共通业务类型" 字典信息
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'DISPLAN', '产品计划排期', 355, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"产品计划排期" 流程模板
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('DisProdPlanManage002', 'DisProdPlanManage002_btn_submit', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('DisProdPlanManage002', 'DisProdPlanManage002_btn_save', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('DisProdPlanManage002', 'DisProdPlanManage001_btn_delete', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');



-- 新增"产品分红到期计划" 菜单
DELETE FROM SYS_MENU WHERE SYS_MENU.MENU_ID = 'REPORT_006_004_006' AND SYS_MENU.REMARK = '产品分红到期计划';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004_006', 'DIS', null, 'REPORT_006_004', 'ProdBonusplanManager001', 1, '02', '产品分红到期计划', 'system', null, SYSDATE, 'system', SYSDATE);

-- 新增 "产品分红到期计划" 画面
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ProdBonusplanManager001');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ProdBonusplanManager001', 'DIS', '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager001',
       'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager001', 'REPORT_006_004_001', NULL, NULL,
       '02', '01', '产品计划排期', 'system', NULL, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ProdBonusplanManager002');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ProdBonusplanManager002', 'DIS', '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002',
       'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002', '', NULL, NULL,
       '02', '01', '产品计划排期新增', 'system', NULL, sysdate, 'system', sysdate);


-- 新增"产品分红到期计划" 画面按钮
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'ProdBonusplanManager001';
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdBonusplanManager001', 'ProdBonusplanManager001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdBonusplanManager001', 'ProdBonusplanManager001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdBonusplanManager001', 'ProdBonusplanManager001_btn_design', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdBonusplanManager001', 'ProdBonusplanManager001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdBonusplanManager001', 'ProdBonusplanManager001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdBonusplanManager001', 'ProdBonusplanManager001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdBonusplanManager001', 'ProdBonusplanManager001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

-- 新增"分红方式"字典信息
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_BONUS_MODE', 'DIS', '分红方式', 'DIS_BONUS_MODE', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_BONUS_MODE', '01', '份额分红后净值为1', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_BONUS_MODE', '02', '到期兑付', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
-- "共通业务类型" 字典信息
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'DISBond', '产品分红到期计划', 354, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"产品分红到期计划" 流程模板
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ProdBonusplanManager002', 'ProdBonusplanManager002_btn_submit', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ProdBonusplanManager002', 'ProdBonusplanManager002_btn_save', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ProdBonusplanManager002', 'ProdBonusplanManager001_btn_delete', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');



-- 新增"产品费率优惠" 菜单

DELETE FROM SYS_MENU WHERE SYS_MENU.MENU_ID = 'REPORT_006_004_013' AND SYS_MENU.REMARK = '产品费率优惠';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004_013', 'DIS', null, 'REPORT_006_004', 'ProdFeediscount001', 13, '02', '产品费率优惠', 'system', null, SYSDATE, 'system', SYSDATE);

-- 新增 "产品费率优惠" 画面
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ProdFeediscount001');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ProdFeediscount001', 'DIS', '/disclosure/information/config/prodfeediscount/ProdFeediscount001', 'disclosure/information/config/prodfeediscount/ProdFeediscount001',
       'REPORT_006_004_013', NULL, NULL, '02', '01', '产品费率优惠', 'system', NULL, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ProdFeediscount002');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ProdFeediscount002', 'DIS', '/disclosure/information/config/prodfeediscount/ProdFeediscount002', 'disclosure/information/config/prodfeediscount/ProdFeediscount002',
       '', NULL, NULL, '02', '01', '产品计划排期新增', 'system', NULL, sysdate, 'system', sysdate);


-- 新增"产品费率优惠" 画面按钮
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'ProdFeediscount001';
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdFeediscount001', 'ProdFeediscount001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdFeediscount001', 'ProdFeediscount001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdFeediscount001', 'ProdFeediscount001_btn_design', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdFeediscount001', 'ProdFeediscount001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdFeediscount001', 'ProdFeediscount001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdFeediscount001', 'ProdFeediscount001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdFeediscount001', 'ProdFeediscount001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);


-- "共通业务类型" 字典信息
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'DISFEEDISCOUNT', '产品费率优惠', 356, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"产品费率优惠" 流程模板
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ProdFeediscount002', 'ProdFeediscount002_btn_submit', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ProdFeediscount002', 'ProdFeediscount002_btn_save', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ProdFeediscount002', 'ProdFeediscount001_btn_delete', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');



-- 新增"产品基本信息 " 菜单
DELETE FROM SYS_MENU WHERE SYS_MENU.MENU_ID = 'REPORT_006_004_015' AND SYS_MENU.REMARK = '产品基本信息';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004_015', 'DIS', null, 'REPORT_006_004', 'ProdInfo001', 1, '02', '产品基本信息', 'system', null, SYSDATE, 'system', SYSDATE);


-- 新增 "产品基本信息" 画面
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ProdInfo001');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ProdInfo001', 'DIS', '/disclosure/information/config/repprodinfo/ProdInfo001', 'disclosure/information/config/repprodinfo/ProdInfo001',
       'REPORT_006_004_001', NULL, NULL, '02', '01', '产品基本信息', 'system', NULL, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ProdInfo002');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ProdInfo002', 'DIS', '/disclosure/information/config/repprodinfo/ProdInfo002', 'disclosure/information/config/repprodinfo/ProdInfo002',
       '', NULL, NULL, '02', '01', '产品基本信息新增', 'system', NULL, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ProdInfo003');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ProdInfo003', 'DIS', '/disclosure/information/config/repprodinfo/ProdInfo003', 'disclosure/information/config/repprodinfo/ProdInfo003',
       '', NULL, NULL, '02', '01', '产品发行状态管理', 'system', NULL, sysdate, 'system', sysdate);

-- 新增"产品基本信息" 画面按钮
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'ProdInfo001';
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdInfo001', 'ProdInfo001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdInfo001', 'ProdInfo001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdInfo001', 'ProdInfo001_btn_design', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdInfo001', 'ProdInfo001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdInfo001', 'ProdInfo001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdInfo001', 'ProdInfo001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdInfo001', 'ProdInfo001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);




-- "共通业务类型" 字典信息
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'DISPROD', '产品基本信息', 356, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"产品基本信息" 流程模板
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ProdInfo002', 'ProdInfo002_btn_submit', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ProdInfo002', 'ProdInfo002_btn_save', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ProdInfo002', 'ProdInfo001_btn_delete', 'oneApproval', 'system', null, SYSDATE, 'system', SYSDATE, 'ALL', '02');

-- 新增"是否是分级产品"字典信息
-- insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
-- values ('GRADE_PROD_TYPE', 'DIS', '是否是分级产品', 'GRADE_PROD_TYPE', '02', 'system', null, sysdate, 'system', sysdate);
-- insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
-- values ('GRADE_PROD_TYPE', '01', '是', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
-- insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
-- values ('GRADE_PROD_TYPE', '02', '否', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
-- 新增"产品运作方式"字典信息
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_PROFIT_TYPE', 'DIS', '产品运作方式', 'DIS_PROFIT_TYPE', '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROFIT_TYPE', '01', '开放式净值型', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROFIT_TYPE', '02', '开放式非净值型', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROFIT_TYPE', '03', '封闭式净值型', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROFIT_TYPE', '04', '封闭式非净值型', 4, '0', 'system', null, sysdate, 'system', sysdate, null);


-- 新增"产品状态"字典信息
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_PROD_STATUS', 'DIS', '产品状态', 'DIS_PROD_STATUS', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_STATUS', '01', '运行中', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_STATUS', '02', '已到期', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_STATUS', '03', '已销户', 3, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"募集方式"字典信息
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_RAISE_TYPE', 'DIS', '募集方式', 'DIS_RAISE_TYPE', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_RAISE_TYPE', '01', '公募', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_RAISE_TYPE', '02', '私募', 2, '0', 'system', null, sysdate, 'system', sysdate, null);


-- 新增"产品类型"字典信息
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_PROD_TYPE', 'DIS', '产品类型', 'DIS_PROD_TYPE', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_TYPE', 'N', '净值型产品', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_TYPE', 'M', '现管类产品', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"投资性质"字典信息
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_INVEST_TYPE', 'DIS', '投资性质', 'DIS_INVEST_TYPE', '02', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INVEST_TYPE', '01', '固定收益类', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INVEST_TYPE', '02', '权益类', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INVEST_TYPE', '03', '商品及衍生品类', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INVEST_TYPE', '04', '混合类', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"发行状态"字典信息
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_ISSUE_STATUS', 'DIS', '发行状态', 'DIS_ISSUE_STATUS', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_ISSUE_STATUS', '01', '发行成功', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_ISSUE_STATUS', '02', '发行失败', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_ISSUE_STATUS', '03', '确认发行', 3, '0', 'system', null, sysdate, 'system', sysdate, null);


-- 新增"行情代码"字典信息
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTA_CODE', 'DIS', '行情代码', 'QUOTA_CODE', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('QUOTA_CODE', '01', '中证500指数收益率', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('QUOTA_CODE', '02', '中债新综合全价指数收益率', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('QUOTA_CODE', '03', '沪深300指数收益率', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('QUOTA_CODE', '04', '中债新综合财富指数（1-3年）收益率', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

-- add by dingrui 20220726  end

<!--  add  信息披露->非标债权类资产明细维护  by  marui start -->
-- 菜单配置
delete from SYS_MENU where PAGE_ID = 'UnClaimAssetDetail001';
insert into SYS_MENU  (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('REPORT_006_004_004','DIS','','REPORT_006_004','UnClaimAssetDetail001','4','02','非标债权类资产明细维护','system','',sysdate, 'system',sysdate);

-- 画面配置
delete  from sys_page where PAGE_ID in ('UnClaimAssetDetail001','UnClaimAssetDetail002');
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('UnClaimAssetDetail001','DIS','/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001','disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail001','REPORT_006_004_004','','','02','01','非标债权类资产维护','system','',sysdate, 'system',sysdate);
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('UnClaimAssetDetail002','DIS','/disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002','disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002','','','','02','01','非标债权类资产维护新建','system','',sysdate, 'system',sysdate);

-- 流程配置
delete from sys_wfl_business_module where PAGE_ID = 'UnClaimAssetDetail002';
insert into sys_wfl_business_module (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG)
values ('UnClaimAssetDetail002','UnClaimAssetDetail001_btn_delete','oneApproval','system','',sysdate,'system',sysdate,'ALL','02');
insert into sys_wfl_business_module (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG)
values ('UnClaimAssetDetail002','UnClaimAssetDetail002_btn_save','oneApproval','system','',sysdate,'system',sysdate,'ALL','02');
insert into sys_wfl_business_module (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG)
values ('UnClaimAssetDetail002','UnClaimAssetDetail002_btn_submit','oneApproval','system','',sysdate,'system',sysdate,'ALL','02');

-- "共通业务类型" 字典信息
delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE in('DISNOSTD');
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'DISNOSTD', '非标债权类资产明细维护', 298, '0', 'system', null, sysdate, 'system', sysdate, null);

<!--  add 信息披露->非标债权类资产明细维护  by marui  end-->