
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
delete from sys_datadict where DICT_CODE='DIS_DATA_STATUS';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_DATA_STATUS', 'DIS', '数据状态', 'DIS_DATA_STATUS', '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='DIS_DATA_STATUS';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_STATUS', '01', '有效', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_STATUS', '02', '删除', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
-- "共通业务类型" 字典信息

delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='DISPLAN';

INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'DISPLAN', '产品计划排期', 355, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"产品计划排期" 流程模板
delete from sys_wfl_business_module where PAGE_ID='DisProdPlanManage002';

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
delete from sys_datadict where DICT_CODE='DIS_BONUS_MODE';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_BONUS_MODE', 'DIS', '分红方式', 'DIS_BONUS_MODE', '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='DIS_BONUS_MODE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_BONUS_MODE', '01', '份额分红后净值为1', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_BONUS_MODE', '02', '到期兑付', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
-- "共通业务类型" 字典信息

delete from sys_datadict_list where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='DISBond';

INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'DISBond', '产品分红到期计划', 354, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"产品分红到期计划" 流程模板
delete from sys_wfl_business_module where PAGE_ID='ProdBonusplanManager002';

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
delete from sys_datadict_list where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='DISFEEDISCOUNT';

INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'DISFEEDISCOUNT', '产品费率优惠', 356, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"产品费率优惠" 流程模板
delete from sys_wfl_business_module where PAGE_ID='ProdFeediscount002';

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
       'REPORT_006_004_015', NULL, NULL, '02', '01', '产品基本信息', 'system', NULL, sysdate, 'system', sysdate);

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
delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='DISPROD';

INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'DISPROD', '产品基本信息', 356, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"产品基本信息" 流程模板
delete from sys_wfl_business_module where PAGE_ID='ProdInfo002';

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
delete from sys_datadict where DICT_CODE='DIS_PROFIT_TYPE';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_PROFIT_TYPE', 'DIS', '产品运作方式', 'DIS_PROFIT_TYPE', '02', 'system', null, sysdate, 'system', sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE='DIS_PROFIT_TYPE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROFIT_TYPE', '01', '开放式净值型', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROFIT_TYPE', '02', '开放式非净值型', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROFIT_TYPE', '03', '封闭式净值型', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROFIT_TYPE', '04', '封闭式非净值型', 4, '0', 'system', null, sysdate, 'system', sysdate, null);


-- 新增"产品状态"字典信息
delete from sys_datadict where DICT_CODE='DIS_PROD_STATUS';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_PROD_STATUS', 'DIS', '产品状态', 'DIS_PROD_STATUS', '02', 'system', null, sysdate, 'system', sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE='DIS_PROD_STATUS';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_STATUS', '01', '运行中', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_STATUS', '02', '已到期', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_STATUS', '03', '已销户', 3, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"募集方式"字典信息
delete from sys_datadict where DICT_CODE='DIS_RAISE_TYPE';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_RAISE_TYPE', 'DIS', '募集方式', 'DIS_RAISE_TYPE', '02', 'system', null, sysdate, 'system', sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE='DIS_RAISE_TYPE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_RAISE_TYPE', '01', '公募', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_RAISE_TYPE', '02', '私募', 2, '0', 'system', null, sysdate, 'system', sysdate, null);


-- 新增"产品类型"字典信息
delete from sys_datadict where DICT_CODE='DIS_PROD_TYPE';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_PROD_TYPE', 'DIS', '产品类型', 'DIS_PROD_TYPE', '02', 'system', null, sysdate, 'system', sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE='DIS_PROD_TYPE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_TYPE', 'N', '净值型产品', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_TYPE', 'M', '现管类产品', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"投资性质"字典信息
delete from sys_datadict where DICT_CODE='DIS_INVEST_TYPE';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_INVEST_TYPE', 'DIS', '投资性质', 'DIS_INVEST_TYPE', '02', 'system', null, sysdate, 'system', sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE='DIS_INVEST_TYPE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INVEST_TYPE', '01', '固定收益类', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INVEST_TYPE', '02', '权益类', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INVEST_TYPE', '03', '商品及衍生品类', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INVEST_TYPE', '04', '混合类', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 新增"发行状态"字典信息
delete from sys_datadict where DICT_CODE='DIS_ISSUE_STATUS';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_ISSUE_STATUS', 'DIS', '发行状态', 'DIS_ISSUE_STATUS', '02', 'system', null, sysdate, 'system', sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE='DIS_ISSUE_STATUS';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_ISSUE_STATUS', '01', '发行成功', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_ISSUE_STATUS', '02', '发行失败', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_ISSUE_STATUS', '03', '确认发行', 3, '0', 'system', null, sysdate, 'system', sysdate, null);


-- 新增"行情代码"字典信息
delete from sys_datadict where DICT_CODE='QUOTA_CODE';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QUOTA_CODE', 'DIS', '行情代码', 'QUOTA_CODE', '02', 'system', null, sysdate, 'system', sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE='QUOTA_CODE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('QUOTA_CODE', '01', '中证500指数收益率', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('QUOTA_CODE', '02', '中债新综合全价指数收益率', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('QUOTA_CODE', '03', '沪深300指数收益率', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('QUOTA_CODE', '04', '中债新综合财富指数（1-3年）收益率', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

-- add by dingrui 20220726  end



-- add by dingrui 20220727  start
-- 新增"信披数据状态"字典信息
delete from sys_datadict where DICT_CODE='DIS_DATA_STATUS_ED';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_DATA_STATUS_ED', 'DIS', '信披数据状态', 'DIS_DATA_STATUS_ED', '02', 'system', null, sysdate, 'system', sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE='DIS_DATA_STATUS_ED';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_STATUS_ED', 'E', '有效', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_STATUS_ED', 'D', '删除', 2, '0', 'system', null, sysdate, 'system', sysdate, null);



-- 新增"发行方式与产品运作方式联动"字典信息
delete  from SYS_DATADICT_LIST_LINK where DICT_CODE = 'DIS_PROFIT_TYPE' and ITEM_CODE = '01' and LINKED_DICT_CODE = 'ISSUING_WAY' and LINKED_ITEM_CODE = '01';
delete  from SYS_DATADICT_LIST_LINK where DICT_CODE = 'DIS_PROFIT_TYPE' and ITEM_CODE = '02' and LINKED_DICT_CODE = 'ISSUING_WAY' and LINKED_ITEM_CODE = '01';
delete  from SYS_DATADICT_LIST_LINK where DICT_CODE = 'DIS_PROFIT_TYPE' and ITEM_CODE = '03' and LINKED_DICT_CODE = 'ISSUING_WAY' and LINKED_ITEM_CODE = '02';
delete  from SYS_DATADICT_LIST_LINK where DICT_CODE = 'DIS_PROFIT_TYPE' and ITEM_CODE = '04' and LINKED_DICT_CODE = 'ISSUING_WAY' and LINKED_ITEM_CODE = '02';
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '01', 'ISSUING_WAY', '01', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '02', 'ISSUING_WAY', '01', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '03', 'ISSUING_WAY', '02', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '04', 'ISSUING_WAY', '02', 'system', null, sysdate, 'system', sysdate);

-- 新增"产品类型与产品运作方式联动"字典信息
delete  from SYS_DATADICT_LIST_LINK where DICT_CODE = 'DIS_PROFIT_TYPE' and ITEM_CODE = '01' and LINKED_DICT_CODE = 'DIS_PROD_TYPE' and LINKED_ITEM_CODE = 'N';
delete  from SYS_DATADICT_LIST_LINK where DICT_CODE = 'DIS_PROFIT_TYPE' and ITEM_CODE = '02' and LINKED_DICT_CODE = 'DIS_PROD_TYPE' and LINKED_ITEM_CODE = 'N';
delete  from SYS_DATADICT_LIST_LINK where DICT_CODE = 'DIS_PROFIT_TYPE' and ITEM_CODE = '02' and LINKED_DICT_CODE = 'DIS_PROD_TYPE' and LINKED_ITEM_CODE = 'M';
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '01', 'DIS_PROD_TYPE', 'N', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '02', 'DIS_PROD_TYPE', 'N', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '02', 'DIS_PROD_TYPE', 'M', 'system', null, sysdate, 'system', sysdate);

-- add by dingrui 20220727  end

-- add by sunjie 20220728 start
-- 科目余额信息 菜单页面脚本
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_020';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_004_020', 'DIS', null, 'REPORT_006_004', 'SubBalanceInfoPro001', 2, '03', '科目余额信息维护', 'system', null,sysdate, 'system', sysdate);
delete from SYS_PAGE where PAGE_ID like '%SubBalanceInfoPro00%';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SubBalanceInfoPro001', 'DIS', '/disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001', 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro001', 'REPORT_006_004_020', null, null, '02', '01', '科目余额信息维护', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SubBalanceInfoPro002', 'DIS', '/disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002', 'disclosure/information/config/subbalanceinfopro/SubBalanceInfoPro002', null, null, null, '02', '01', '科目余额信息新建', 'system', null, sysdate, 'system', sysdate);

-- 科目余额信息 页面控件流程模板设置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID like '%SubBalanceInfoPro00%';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('SubBalanceInfoPro002', 'SubBalanceInfoPro001_btn_delete', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('SubBalanceInfoPro002', 'SubBalanceInfoPro002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('SubBalanceInfoPro002', 'SubBalanceInfoPro002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

-- 科目余额信息 业务数据字典增加
delete from SYS_DATADICT_LIST where DICT_CODE ='COMMON_BUSINESS_TYPE' and ITEM_CODE = 'DISK';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('COMMON_BUSINESS_TYPE', 'DISK', '科目余额信息维护', 356, '0', 'system', null, sysdate, 'system', sysdate, null);

--  add by sunjie 20220728 end


--  add by dingrui 20220728 start
-- 产品基本信息 添加 页面字典绑定
delete from sys_page_dictlist where page_id='ProdInfo001' and DICT_ID='MAINTAIN_STATUS';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ProdInfo001', '999999', 'MAINTAIN_STATUS', 'A', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ProdInfo001', '999999', 'MAINTAIN_STATUS', 'D', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ProdInfo001', '999999', 'MAINTAIN_STATUS', 'E', null, null, 'system', null, sysdate, 'system', sysdate, null);


-- 新增"产品类型"字典信息
delete from sys_datadict where DICT_CODE='DIS_PROD_TYPE' and DICT_CNNAME ='产品类型';
delete from sys_datadict_list where DICT_CODE='DIS_PROD_TYPE' and ITEM_CODE ='N' and ITEM_VALUE ='净值型产品';
delete from sys_datadict_list where DICT_CODE='DIS_PROD_TYPE' and ITEM_CODE ='M' and ITEM_VALUE ='现管类产品';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_PROD_TYPE', 'DIS', '产品类型', 'DIS_PROD_TYPE', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_TYPE', '01', '净值型产品', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_PROD_TYPE', '04', '现管类产品', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
--  add by dingrui 20220728 end


-- add by zhouqiang 2022-07-31 代销机构维护相关脚本 start

--共通业务类型添加：代销机构维护
delete from SYS_DATADICT_LIST where DICT_CODE='COMMON_BUSINESS_TYPE' and ITEM_CODE='DISAGENCY';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE)
values ('COMMON_BUSINESS_TYPE','DISAGENCY','代销机构维护',300,'0','system',null,sysdate,'system',sysdate,null);

--代销机构页面菜单配置
delete from SYS_MENU where MENU_ID='REPORT_006_004_018' and SYSTEM_CODE='DIS';
delete from SYS_PAGE where PAGE_ID in('SaleAgency001','SaleAgency002');
Insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('REPORT_006_004_018','DIS',null,'REPORT_006_004','SaleAgency001',18,'02','代销机构维护','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('SaleAgency001','DIS','/disclosure/information/config/saleagency/SaleAgency001','disclosure/information/config/saleagency/SaleAgency001','REPORT_006_004_018',null,null,'02','01','代销机构维护','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('SaleAgency002','DIS','/disclosure/information/config/saleagency/SaleAgency002','disclosure/information/config/saleagency/SaleAgency002',null,null,null,'02','01','代销机构产品关联关系维护','system',null,sysdate,'system',sysdate);

--添加维护类型数据字典
delete from SYS_DATADICT where DICT_CODE='DIS_SALEAGENCY_MAINTENANCE_TYPE' and SYSTEM_CODE='DIS';
delete from SYS_DATADICT_LIST where DICT_CODE='DIS_SALEAGENCY_MAINTENANCE_TYPE';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('DIS_SALEAGENCY_MAINTENANCE_TYPE','DIS','维护类型','DIS_SALEAGENCY_MAINTENANCE_TYPE','02','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE)
values ('DIS_SALEAGENCY_MAINTENANCE_TYPE','01','代销机构',1,'0','system',null,sysdate,'system',sysdate,null);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE)
values ('DIS_SALEAGENCY_MAINTENANCE_TYPE','02','理财产品',2,'0','system',null,sysdate,'system',sysdate,null);

--页面流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID='SaleAgency002';
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('SaleAgency002','SaleAgency001_btn_delete','oneApproval','system','0001',sysdate,'system',sysdate,'ALL','02');
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('SaleAgency002','SaleAgency002_btn_save','oneApproval','system','0001',sysdate,'system',sysdate,'ALL','02');
Insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE,IS_SEND_UORG) values ('SaleAgency002','SaleAgency002_btn_submit','oneApproval','system','0001',sysdate,'system',sysdate,'ALL','02');
-- add by zhouqiang 2022-07-31 代销机构维护相关脚本 end


-- add by dingrui 2022-08-01  start
-- "产品类型"与“发行方式”联动 字典信息
delete  from SYS_DATADICT_LIST_LINK where DICT_CODE = 'DIS_PROFIT_TYPE' and ITEM_CODE = '01' and LINKED_DICT_CODE = 'DIS_PROD_TYPE' and LINKED_ITEM_CODE = '01';
delete  from SYS_DATADICT_LIST_LINK where DICT_CODE = 'DIS_PROFIT_TYPE' and ITEM_CODE = '02' and LINKED_DICT_CODE = 'DIS_PROD_TYPE' and LINKED_ITEM_CODE = '01';
delete  from SYS_DATADICT_LIST_LINK where DICT_CODE = 'DIS_PROFIT_TYPE' and ITEM_CODE = '02' and LINKED_DICT_CODE = 'DIS_PROD_TYPE' and LINKED_ITEM_CODE = '04';

INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '01', 'DIS_PROD_TYPE', '01', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '02', 'DIS_PROD_TYPE', '01', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '02', 'DIS_PROD_TYPE', '04', 'system', null, sysdate, 'system', sysdate);
-- add by dingrui 2022-08-01  end

commit ;

--  add  信息披露->非标债权类资产明细维护  by  marui start
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

--  add 信息披露->非标债权类资产明细维护  by marui  end



-- add by dingrui 2022-08-02  start
-- 新增 "产品分红到期计划" 画面
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ProdBonusplanManager001');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ProdBonusplanManager001', 'DIS', '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager001',
       'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager001', 'REPORT_006_004_006', NULL, NULL,
       '02', '01', '产品分红到期计划', 'system', NULL, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ProdBonusplanManager002');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ProdBonusplanManager002', 'DIS', '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002',
       'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002', '', NULL, NULL,
       '02', '01', '产品分红到期计划新增', 'system', NULL, sysdate, 'system', sysdate);
-- add by dingrui 2022-08-02  end


-- add by dingrui 2022-08-03  start
-- 新增"产品排期计划" 菜单
DELETE FROM SYS_MENU WHERE SYS_MENU.MENU_ID = 'REPORT_006_004' AND SYS_MENU.REMARK = '信披数据维护';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004', 'DIS', null, 'REPORT_006', '', 1, '02', '信披数据维护', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_MENU WHERE SYS_MENU.MENU_ID = 'REPORT_006_004_001' and page_id='DisProdPlanManage001';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004_001', 'DIS', null, 'REPORT_006_004', 'DisProdPlanManage001', 1, '02', '产品排期计划', 'system', null, SYSDATE, 'system', SYSDATE);

-- 新增 "产品排期计划" 画面
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('DisProdPlanManage001');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('DisProdPlanManage001', 'DIS', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage001', 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001',
       'REPORT_006_004_001', NULL, NULL, '02', '01', '产品排期计划', 'system', NULL, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('DisProdPlanManage002');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('DisProdPlanManage002', 'DIS', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage002', 'disclosure/information/config/disprodplanmanage/DisProdPlanManage002',
       '', NULL, NULL, '02', '01', '产品排期计划新增', 'system', NULL, sysdate, 'system', sysdate);
-- add by dingrui 2022-08-03  end


-- add by sunjie 20220803 隐藏主动提醒脚本 start
update sys_para set PARAM_VALUE = '02' where PARA_CODE = 'is_show_remind';
-- add by sunjie 20220803 隐藏主动提醒脚本 end

-- add by sunjie 20220803 数据状态下拉框枚举值 start
delete from SYS_PAGE_DICTLIST where PAGE_ID like '%ProElementAdj00%' and DICT_ID = 'MAINTAIN_STATUS';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('ProElementAdj001', '999999', 'MAINTAIN_STATUS', 'D', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('ProElementAdj001', '999999', 'MAINTAIN_STATUS', 'E', null, null, 'system', null, sysdate, 'system', sysdate, null);
-- add by sunjie 20220803 数据状态下拉框枚举值 end




-- add by dingrui 20220804 报表数据校验信息 start
-- 新增"报表数据校验" 菜单

DELETE FROM SYS_MENU WHERE SYS_MENU.MENU_ID = 'REPORT_006_003_005' AND SYS_MENU.REMARK = '报表数据校验';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_003_005', 'DIS', null, 'REPORT_006_003', 'ReportCheck001', 5, '02', '报表数据校验', 'system', null, SYSDATE, 'system', SYSDATE);

-- 新增 "报表数据校验" 画面
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ReportCheck001');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ReportCheck001', 'DIS', '/disclosure/information/config/repreportcheck/ReportCheck001', 'disclosure/information/config/repreportcheck/ReportCheck001',
       'REPORT_006_003_005', NULL, NULL, '02', '01', '报表数据校验', 'system', NULL, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ReportCheck002');
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ReportCheck002', 'DIS', '/disclosure/information/config/repreportcheck/ReportCheck002', 'disclosure/information/config/repreportcheck/ReportCheck002',
       '', NULL, NULL, '02', '01', '指标阀值配置', 'system', NULL, sysdate, 'system', sysdate);


-- 新增"报表数据校验" 画面按钮
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'ReportCheck001';
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ReportCheck001', 'ReportCheck001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ReportCheck001', 'ReportCheck001_btn_tips', '01', '02', null, '不再提示', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ReportCheck001', 'ReportCheck001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ReportCheck001', 'ReportCheck001_btn_set', '01', '01', null, '指标阀值配置', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ReportCheck001', 'ReportCheck001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);


-- 新增"报表数据类别"字典信息
delete from  sys_datadict where DICT_CODE='DIS_REPORT_STYLE';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_REPORT_STYLE', 'DIS', '报表数据类别', 'DIS_REPORT_STYLE', '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='DIS_REPORT_STYLE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_REPORT_STYLE', '01', '发行公告', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_REPORT_STYLE', '02', '运行公告', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_REPORT_STYLE', '03', '到期公告', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_REPORT_STYLE', '04', '临时公告', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_REPORT_STYLE', '05', '销售公告', 5, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_REPORT_STYLE', '06', '托管公告', 6, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_REPORT_STYLE', '99', '其他公告', 7, '0', 'system', null, sysdate, 'system', sysdate, null);

-- add by dingrui 20220804 报表数据校验信息 end


--信披单户自动打包功能脚本  add by zhouqiang start
delete from SYS_PARA where PARA_CODE in('SFTP_DIS_HOST','SFTP_DIS_PORT','SFTP_DIS_USERNAME','SFTP_DIS_PASWORD','SFTP_DIS_FPATH');
Insert into SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('VAL','SFTP_DIS_HOST','192.168.60.236','0',null,'发送信披单据主机ip','system',null,sysdate,'system',sysdate);
Insert into SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('VAL','SFTP_DIS_PORT','22','0',null,'发送信披单据服务器端口号','system',null,sysdate,'system',sysdate);
Insert into SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('VAL','SFTP_DIS_USERNAME','root','0',null,'发送信披单据服务器用户名','system',null,sysdate,'system',sysdate);
Insert into SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('VAL','SFTP_DIS_PASWORD','joyin!619','0',null,'发送信披单据服务器密码','system',null,sysdate,'system',sysdate);
Insert into SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('VAL','SFTP_DIS_FPATH','/home/dis/','0',null,'发送信披单据服务器地址','system',null,sysdate,'system',sysdate);
--批处理
delete from SYS_BATCH_TASK where task_id='100401';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept)
values ('100401','VAL','自动披露批处理','','infoDiscAutoPackAndSendService','1104','system',sysdate, 'system',sysdate, '');
update SYS_BATCH_TASK set TASK_NAME='自动披露批处理(已废弃)' where TASK_ID='1004';
--信披单户自动打包功能脚本  add by zhouqiang end

-- add by sunjie 20220809 巨额赎回信息数据状态下拉框改成ED start
delete from SYS_PAGE_DICTLIST where PAGE_ID like '%HugeInfoPro00%' and DICT_ID = 'MAINTAIN_STATUS';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('HugeInfoPro001', '999999', 'MAINTAIN_STATUS', 'D', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('HugeInfoPro001', '999999', 'MAINTAIN_STATUS', 'E', null, null, 'system', null, sysdate, 'system', sysdate, null);
-- add by sunjie 20220809 巨额赎回信息数据状态下拉框改成ED end


--报表披露管理-披露功能不走流程 add by zhouqiang
update SYS_WFL_BUSINESS_MODULE set MODULE_ID='noneApproval' where PAGE_ID='ReportRevealManage001' and ELEMENT_ID='ReportRevealManage001_btn_submit';


-- 新增"合作机构信息维护" 画面按钮 add by zhouqiang
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'ProdPartner001';
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdPartner001', 'ProdPartner001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdPartner001', 'ProdPartner001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdPartner001', 'ProdPartner001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdPartner001', 'ProdPartner001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdPartner001', 'ProdPartner001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdPartner001', 'ProdPartner001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdPartner001', 'ProdPartner001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

-- 新增"产品资产信息维护" 画面按钮 add by zhouqiang
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'ProdAssetInfo001';
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdAssetInfo001', 'ProdAssetInfo001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdAssetInfo001', 'ProdAssetInfo001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdAssetInfo001', 'ProdAssetInfo001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdAssetInfo001', 'ProdAssetInfo001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdAssetInfo001', 'ProdAssetInfo001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdAssetInfo001', 'ProdAssetInfo001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('ProdAssetInfo001', 'ProdAssetInfo001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);



-- 新增"代销机构维护" 画面按钮 add by zhouqiang
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SaleAgency001';
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('SaleAgency001', 'SaleAgency001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('SaleAgency001', 'SaleAgency001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('SaleAgency001', 'SaleAgency001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('SaleAgency001', 'SaleAgency001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('SaleAgency001', 'SaleAgency001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);
insert into Sys_Page_Element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
values ('SaleAgency001', 'SaleAgency001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

-- add by kongzhifang 20220812 行情代码数据字典更新映射关系
update sys_datadict_list set item_value = '中债新综合全价指数收益率' where dict_code = 'QUOTA_CODE' and item_code = '01';
update sys_datadict_list set item_value = '沪深300指数收益率' where dict_code = 'QUOTA_CODE' and item_code = '02';
update sys_datadict_list set item_value = '中债新综合财富指数（1-3年）收益率' where dict_code = 'QUOTA_CODE' and item_code = '03';
update sys_datadict_list set item_value = '中证500指数收益率' where dict_code = 'QUOTA_CODE' and item_code = '04';

-- 新增"产品费用交易信息维护" 画面按钮 add by shengning 20220813
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'SvrProdFeeDealInterf001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SvrProdFeeDealInterf001', 'SvrProdFeeDealInterf001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SvrProdFeeDealInterf001', 'SvrProdFeeDealInterf001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SvrProdFeeDealInterf001', 'SvrProdFeeDealInterf001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SvrProdFeeDealInterf001', 'SvrProdFeeDealInterf001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SvrProdFeeDealInterf001', 'SvrProdFeeDealInterf001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SvrProdFeeDealInterf001', 'SvrProdFeeDealInterf001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SvrProdFeeDealInterf001', 'SvrProdFeeDealInterf001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);

-- 新增"投资账户信息维护" 画面按钮 add by shengning 20220813
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'RepInvestAccount001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepInvestAccount001', 'RepInvestAccount001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepInvestAccount001', 'RepInvestAccount001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepInvestAccount001', 'RepInvestAccount001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepInvestAccount001', 'RepInvestAccount001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepInvestAccount001', 'RepInvestAccount001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepInvestAccount001', 'RepInvestAccount001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepInvestAccount001', 'RepInvestAccount001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);

-- 新增"产品基础指标信息维护" 画面按钮 add by shengning 20220813
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'RepProdIndex001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepProdIndex001', 'RepProdIndex001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepProdIndex001', 'RepProdIndex001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepProdIndex001', 'RepProdIndex001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepProdIndex001', 'RepProdIndex001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepProdIndex001', 'RepProdIndex001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepProdIndex001', 'RepProdIndex001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepProdIndex001', 'RepProdIndex001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);

-- 新增"产品超额业绩报酬信息维护" 画面按钮 add by shengning 20220813
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'RepFloatFee001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepFloatFee001', 'RepFloatFee001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepFloatFee001', 'RepFloatFee001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepFloatFee001', 'RepFloatFee001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepFloatFee001', 'RepFloatFee001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepFloatFee001', 'RepFloatFee001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepFloatFee001', 'RepFloatFee001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepFloatFee001', 'RepFloatFee001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);

-- 新增"关联交易情况维护" 画面按钮 add by shengning 20220813
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'RepRelDeal001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepRelDeal001', 'RepRelDeal001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepRelDeal001', 'RepRelDeal001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepRelDeal001', 'RepRelDeal001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepRelDeal001', 'RepRelDeal001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepRelDeal001', 'RepRelDeal001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepRelDeal001', 'RepRelDeal001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('RepRelDeal001', 'RepRelDeal001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);

-- add by kongzhifang 20220812 NYLCXP-557 产品运作方式数据字典更新映射关系
update sys_datadict_list set item_value = '封闭式净值型' where dict_code = 'DIS_PROFIT_TYPE' and item_code = '01';
update sys_datadict_list set item_value = '封闭式非净值型' where dict_code = 'DIS_PROFIT_TYPE' and item_code = '02';
update sys_datadict_list set item_value = '开放式净值型' where dict_code = 'DIS_PROFIT_TYPE' and item_code = '03';
update sys_datadict_list set item_value = '开放式非净值型' where dict_code = 'DIS_PROFIT_TYPE' and item_code = '04';



-- 新增"产品不成立信息维护" 画面按钮 add by sunjie 20220815
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ProFailureInfo001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProFailureInfo001', 'ProFailureInfo001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProFailureInfo001', 'ProFailureInfo001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProFailureInfo001', 'ProFailureInfo001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProFailureInfo001', 'ProFailureInfo001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProFailureInfo001', 'ProFailureInfo001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProFailureInfo001', 'ProFailureInfo001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProFailureInfo001', 'ProFailureInfo001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);


-- 新增"产品要素调整" 画面按钮 add by sunjie 20220815
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'ProElementAdj001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProElementAdj001', 'ProElementAdj001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProElementAdj001', 'ProElementAdj001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProElementAdj001', 'ProElementAdj001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProElementAdj001', 'ProElementAdj001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProElementAdj001', 'ProElementAdj001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProElementAdj001', 'ProElementAdj001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('ProElementAdj001', 'ProElementAdj001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);


-- 新增"巨额赎回信息" 画面按钮 add by sunjie 20220815
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'HugeInfoPro001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('HugeInfoPro001', 'HugeInfoPro001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('HugeInfoPro001', 'HugeInfoPro001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('HugeInfoPro001', 'HugeInfoPro001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('HugeInfoPro001', 'HugeInfoPro001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('HugeInfoPro001', 'HugeInfoPro001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('HugeInfoPro001', 'HugeInfoPro001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('HugeInfoPro001', 'HugeInfoPro001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);




-- 新增"科目余额信息" 画面按钮 add by sunjie 20220815
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'SubBalanceInfoPro001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SubBalanceInfoPro001', 'SubBalanceInfoPro001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SubBalanceInfoPro001', 'SubBalanceInfoPro001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SubBalanceInfoPro001', 'SubBalanceInfoPro001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SubBalanceInfoPro001', 'SubBalanceInfoPro001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SubBalanceInfoPro001', 'SubBalanceInfoPro001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SubBalanceInfoPro001', 'SubBalanceInfoPro001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('SubBalanceInfoPro001', 'SubBalanceInfoPro001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);


-- 新增"非标债券类资产维护" 画面按钮 add by sunjie 20220815
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'UnClaimAssetDetail001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('UnClaimAssetDetail001', 'UnClaimAssetDetail001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('UnClaimAssetDetail001', 'UnClaimAssetDetail001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('UnClaimAssetDetail001', 'UnClaimAssetDetail001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('UnClaimAssetDetail001', 'UnClaimAssetDetail001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('UnClaimAssetDetail001', 'UnClaimAssetDetail001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('UnClaimAssetDetail001', 'UnClaimAssetDetail001_TABROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('UnClaimAssetDetail001', 'UnClaimAssetDetail001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);

--新增"信披合规留痕管理" 画面按钮 add by shengning 2022-08-15
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'InfoCompliance001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('InfoCompliance001', 'InfoCompliance001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('InfoCompliance001', 'InfoCompliance001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('InfoCompliance001', 'infoCompliance001_btn_update', '01', '01', null, '开发状态变更', 'system', null, sysdate, 'system', sysdate, 'C', null);

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES ('InfoCompliance001', 'InfoCompliance001_btn_filter', '01', '02', null, '筛选', 'system', null, sysdate, 'system', sysdate, 'C', null);


-- 我的主页只显示 披露主页 add by sunjie 20220816 start
update SYS_PAGE set IS_HP_SELECT ='02' where PAGE_ID in('PbcPrdBaseinfoEndSubNew001','PbcPrdBaseinfoSubNew001','PbcPrdAssetDebtSubPreview','PbcPrdAssetDebtSub','SvrG06MonthReport001','SvrBusinessHome','SvreportHome002','PaActiveRemind001','PbcPrdBaseinfoSub','PbcPrdBaseinfoEndSub'
                                                       ,'PbcPrdCashRaiseSub','PbcPrdInitRaiseSub','PbcMonthReport001','RiskFactor003','SysDataProcessMark001','MstCustomerNature001','SysWflBusinessModule001','SysWflBusinessModule002','Workflow001','SvrChbBotAstPositionRegist001'
                                                       ,'SysPrebatchResult001','SysPrebatchResult002','SysBatchStart001','ValBatchStart001','SysBatchLog001','SysBatchMonitor001','SysBatchProcess001','CustomerReportShow001','CustomerReportConfig001','SvreportHome001','SysDataDict001'
                                                       ,'PbcSendZipDatFile','SysInstitution001','WorkflowMonitor001','SellMonthReport001','SvrTransMonthReport001','SvrSubsidiaMonthReport001','SysUser001','InterfaceLog001','SysParaSet001','SysActionLog001','SysActorAssign001'
                                                       ,'SysActorRole001','AssetTypeSetting001','AssetTypeSetting002','SvrChbAstValueRegist001','PbcPrdAccountSub','PbcPrdAccountEndSub','SysDepartment001','SvrMonthReport001');
-- 我的主页只显示 披露主页 add by sunjie 20220816 end

--NYLCXP-702 产品基本信息页面产品运作方式联动调整 add by kongzhifang 2022-08-17
update sys_datadict_list_link set item_code = '01' where dict_code = 'DIS_PROFIT_TYPE' and linked_dict_code = 'ISSUING_WAY' and item_code = '04';
update sys_datadict_list_link set item_code = '02' where dict_code = 'DIS_PROFIT_TYPE' and linked_dict_code = 'ISSUING_WAY' and item_code = '03';
update sys_datadict_list_link set item_code = '03' where dict_code = 'DIS_PROFIT_TYPE' and linked_dict_code = 'ISSUING_WAY' and item_code = '02';
update sys_datadict_list_link set item_code = '04' where dict_code = 'DIS_PROFIT_TYPE' and linked_dict_code = 'ISSUING_WAY' and item_code = '01';

update sys_datadict_list_link set item_code = '03' where dict_code = 'DIS_PROFIT_TYPE' and linked_dict_code = 'DIS_PROD_TYPE' and item_code = '01';
update sys_datadict_list_link set item_code = '04' where dict_code = 'DIS_PROFIT_TYPE' and linked_dict_code = 'DIS_PROD_TYPE' and item_code = '02';

-- NYLCXP-741 信息披露菜单放置路径及导航请根据需求提供文档调整 add by sunjie 20220818 start
delete from SYS_MENU where MENU_ID ='REPORT_006_003';
update SYS_MENU set ORDER_NO =1 , REMARK = '信披合规管理' WHERE MENU_ID = 'REPORT_006_005'; --信披合规管理二级菜单

delete from SYS_MENU where MENU_ID = 'REPORT_006_001_005';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_005_100', 'DIS', null, 'REPORT_006_005', 'ReportPageManage001', 2, '02', '模板启用管理', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'REPORT_006_005_100' where PAGE_ID = 'ReportPageManage001';

update SYS_MENU set ORDER_NO =2  WHERE MENU_ID = 'REPORT_006_001'; --报表配置二级菜单
delete from SYS_MENU where MENU_ID = 'REPORT_006_002_003';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_001_100', 'DIS', null, 'REPORT_006_001', 'ReportRule001', 50, '02', '报表规则设置', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'REPORT_006_001_100' where PAGE_ID = 'ReportRule001';

delete from SYS_MENU where MENU_ID = 'REPORT_006_002_001';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_001_101', 'DIS', null, 'REPORT_006_001', 'ReportWorkManage001', 50, '02', '报表作业管理', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'REPORT_006_001_101' where PAGE_ID = 'ReportWorkManage001';

delete from SYS_MENU where MENU_ID = 'REPORT_006_002_002';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_001_102', 'DIS', null, 'REPORT_006_001', 'ReportWorkMonitor001', 50, '02', '报表作业监控', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'REPORT_006_001_102' where PAGE_ID = 'ReportWorkMonitor001';

update SYS_MENU set ORDER_NO =3 , REMARK = '数据维护' WHERE MENU_ID = 'REPORT_006_004';--数据维护二级菜单

delete from SYS_MENU where MENU_ID = 'REPORT_006_001_003';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_004_100', 'DIS', null, 'REPORT_006_004', 'ReportContentEnum001', 50, '02', '报表指标管理', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'REPORT_006_004_100' where PAGE_ID = 'ReportContentEnum001';

update SYS_MENU set ORDER_NO =4  WHERE MENU_ID = 'REPORT_006_002'; --报表管理二级菜单
delete from SYS_MENU where MENU_ID = 'REPORT_006_003_001';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_002_100', 'DIS', null, 'REPORT_006_002', 'RepDetailsShow001', 1, '02', '报表生成管理', 'system', null, sysdate, 'system', sysdate);

delete from SYS_MENU where MENU_ID = 'REPORT_006_003_005';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_002_101', 'DIS', null, 'REPORT_006_002', 'ReportCheck001', 2, '02', '报表数据校验', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'REPORT_006_002_101' where PAGE_ID = 'ReportCheck001';

delete from SYS_MENU where MENU_ID = 'REPORT_006_003_002';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_002_102', 'DIS', null, 'REPORT_006_002', 'ReportConfirmManage001', 3, '02', '报表确认管理', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'REPORT_006_002_102' where PAGE_ID = 'ReportConfirmManage001';

delete from SYS_MENU where MENU_ID = 'REPORT_006_003_003';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_002_103', 'DIS', null, 'REPORT_006_002', 'ReportRevealManage001', 4, '02', '报表披露管理', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'REPORT_006_002_103' where PAGE_ID = 'ReportRevealManage001';

delete from SYS_MENU where MENU_ID = 'REPORT_006_003_004';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_002_004', 'DIS', null, 'REPORT_006_002', 'EstimateReveal001', 5, '02', '预计披露', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'REPORT_006_002_004' where PAGE_ID = 'EstimateReveal001';

update SYS_MENU set ORDER_NO =1 , REMARK = '权限管理' WHERE MENU_ID = 'SYSTEM_001_001';
update SYS_MENU set ORDER_NO =3 , REMARK = '投组批处理' WHERE MENU_ID = 'SYSTEM_001_003';

delete from SYS_MENU where MENU_ID = 'SYSTEM_001_005_001';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SYSTEM_001_003_100', 'DIS', null, 'SYSTEM_001_003', 'Workflow001', 1, '02', '工作流配置', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'SYSTEM_001_003_100' where PAGE_ID = 'Workflow001';

delete from SYS_MENU where MENU_ID = 'SYSTEM_001_004_001';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SYSTEM_001_003_101', 'DIS', null, 'SYSTEM_001_003', 'SysDataDict001', 2, '02', '数据字典管理', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'SYSTEM_001_003_101' where PAGE_ID = 'SysDataDict001';

delete from SYS_MENU where MENU_ID = 'SYSTEM_001_005_002';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SYSTEM_001_003_102', 'DIS', null, 'SYSTEM_001_003', 'SysWflBusinessModule001', 3, '02', '工作流管理', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'SYSTEM_001_003_102' where PAGE_ID = 'SysWflBusinessModule001';

update SYS_MENU set ORDER_NO =4 , REMARK = '批处理配置' WHERE MENU_ID = 'SYSTEM_001_005';
delete from SYS_MENU where MENU_ID = 'SYSTEM_001_003_004';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SYSTEM_001_005_100', 'DIS', null, 'SYSTEM_001_005', 'SysBatchProcess001', 3, '02', '批处理配置', 'system', null, sysdate, 'system', sysdate);
update SYS_PAGE set MENU_ID = 'SYSTEM_001_005_100' where PAGE_ID = 'SysBatchProcess001';
update SYS_MENU set ORDER_NO =5   WHERE MENU_ID = 'SYSTEM_001_004';

delete from SYS_MENU where MENU_ID ='SYSTEM_001_012';--删除自定义报表二级菜单
-- NYLCXP-741 信息披露菜单放置路径及导航请根据需求提供文档调整 add by sunjie 20220818 end

-- NYLCXP-875 产品排期计划待办事项查看业务类型不正确 add by kongzhifang 2022-08-19
update sys_datadict_list set item_value = '产品排期计划' where dict_code  = 'COMMON_BUSINESS_TYPE' and item_code = 'DISPLAN';

-- NYLCXP-897 产品类型和产品运作方式联动调整 add by kongzhifang 2022-08-19
delete from sys_datadict_list_link where dict_code = 'DIS_PROFIT_TYPE' and linked_dict_code = 'DIS_PROD_TYPE';

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '01', 'DIS_PROD_TYPE', '01', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '03', 'DIS_PROD_TYPE', '01', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '03', 'DIS_PROD_TYPE', '04', 'system', NULL, sysdate, 'system', sysdate);

delete from sys_datadict_list_link where dict_code = 'DIS_PROFIT_TYPE' and linked_dict_code = 'ISSUING_WAY';

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '04', 'ISSUING_WAY', '01', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '03', 'ISSUING_WAY', '01', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '02', 'ISSUING_WAY', '02', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DIS_PROFIT_TYPE', '01', 'ISSUING_WAY', '02', 'system', NULL, sysdate, 'system', sysdate);


-- NYLCXP-746 工作流管理画面使用 SysFuncWflMatch001而不是SysWflBusinessModule001
UPDATE SYS_MENU SET PAGE_ID = 'SysFuncWflMatch001' WHERE MENU_ID = 'SYSTEM_001_003_102';
UPDATE SYS_PAGE SET MENU_ID = 'SYSTEM_001_003_102' WHERE PAGE_ID = 'SysFuncWflMatch001';
UPDATE SYS_PAGE SET MENU_ID = null WHERE PAGE_ID = 'SysWflBusinessModule001';

-- NYLCXP-477 行为日志记录正确的前提是画面和画面元素表基础数据正确  添加报表编辑画面保存按钮
INSERT INTO SYS_PAGE_ELEMENT
(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG, PARENT_ELEMENT_ID)
VALUES('RepEdit001', 'btnSaveReport', '01', '02', NULL, '保存', 'system', NULL, sysdate, 'system', sysdate, 'COM', NULL);

-- 密码错误次数过多导致的用户锁定时间不允许为0
UPDATE SYS_PARA SET PARAM_VALUE = '1800' WHERE PARA_CODE = 'login_fail_lock_time';

commit;