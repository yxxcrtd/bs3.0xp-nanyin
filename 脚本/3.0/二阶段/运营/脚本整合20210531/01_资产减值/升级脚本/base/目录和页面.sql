

-- 资产减值

delete from sys_page where page_id='TrdAssetLossBalance001';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, 
IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdAssetLossBalance001', 'VAL', 
'/valuation/trdassetlossmanage/trdassetlossbalance/TrdAssetLossBalance001', 
'valuation/trdassetlossmanage/trdassetlossbalance/TrdAssetLossBalance001', 
'VALUATION_ACCOUNTING_001_003_007', null, null, '02', '02', '资产减值余额查询', 
'system', null, date'2020-11-18', 'system', date'2020-11-18');

delete from sys_page where page_id='TrdAssetLossBalance002';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, 
IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdAssetLossBalance002', 'VAL', 
'/valuation/trdassetlossmanage/trdassetlossbalance/TrdAssetLossBalance002', 
'valuation/trdassetlossmanage/trdassetlossbalance/TrdAssetLossBalance002', 
'VALUATION_ACCOUNTING_001_003_007', null, null, '02', '02', '资产减值余额明细', 
'system', null, date'2020-11-18', 'system', date'2020-11-18');

delete from sys_page where page_id='FinProductLossStage001';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, 
IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductLossStage001', 'VAL', 
'/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage001', 
'valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage001', 
'VALUATION_ACCOUNTING_001_003_005', null, null, '02', '02', '资产减值阶段管理', 
'system', null, date'2020-11-18', 'system', date'2020-11-18');

delete from sys_page where page_id='FinProductLossStage002';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, 
IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductLossStage002', 'VAL', 
'/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage002', 
'valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage002', 
'VALUATION_ACCOUNTING_001_003_005', null, null, '02', '02', '资产减值阶段管理新建', 
'system', null, date'2020-11-18', 'system', date'2020-11-18');

/**==========画面:估值核算-财务管理-资产减值管理 add by lby 20201010 start==========**/
delete from sys_page sp where sp.page_id = 'TrdAssetLoss001';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdAssetLoss001', 'VAL', '/valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss001', 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss001', 'VALUATION_ACCOUNTING_001_003_006', null, null, '02', '01', '资产减值管理', 'system', null, sysdate, 'system', sysdate);
/**========== add by lby 20201010 end==========**/


/**==========画面:估值核算-财务管理-资产减值管理--减值新建 add by lby 20201012 start==========**/
delete from sys_page sp where sp.page_id = 'TrdAssetLoss002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdAssetLoss002', 'TRD', '/valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss002', 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss002', null, null, null, '02', '01', '减值新建', 'system', null, sysdate, 'system', sysdate);
/**========== add by lby 20201012  end==========**/

/**==========画面:估值核算-财务管理-资产减值管理--减值新建--减值产品预览 add by lby 20201014 start==========**/
delete from sys_page sp where sp.page_id = 'TrdAssetLoss003';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdAssetLoss003', 'TRD', '/valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss003', 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss003', null, null, null, '02', '01', '减值产品预览', 'system', null, sysdate, 'system', sysdate);
/**========== add by lby 20201014  end==========**/

/**==========画面:估值核算-财务管理-资产减值管理--减值新建--减值产品新建列表 add by lby 20201014 start==========**/
delete from sys_page sp where sp.page_id = 'TrdAssetLoss004';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdAssetLoss004', 'TRD', '/valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss004', 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss004', null, null, null, '02', '01', '减值产品新建列表', 'system', null, sysdate, 'system', sysdate);
/**========== add by lby 20201014  end==========**/


/**============画面：基础数据管理-行情管理-项目类/计划类利率型估值行情--查询 add by lby 20201020 start============**/
delete from sys_page sp where sp.page_id = 'ComRateAssetQuotationInfo001';
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ComRateAssetQuotationInfo001', 'MST', '/common/basicdata/marketdata/mstrateassetquotationinfo/ComRateAssetQuotationInfo001', 'common/basicdata/marketdata/mstrateassetquotationinfo/ComRateAssetQuotationInfo001', 'ASSET_MANAGEMENT_001_003_017', NULL, NULL, '02', '01', '项目类/计划类利率型估值行情', 'system', NULL, sysdate, 'system', sysdate);
/**============画面：基础数据管理-行情管理-项目类/计划类利率型估值行情--查询 add by lby 20201020 end============**/

/**============画面：基础数据管理-行情管理-项目类/计划类利率型估值行情--新建 add by lby 20201022 start============**/
delete from sys_page sp where sp.page_id = 'ComRateAssetQuotationInfo002';
INSERT INTO SYS_PAGE
(PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('ComRateAssetQuotationInfo002', 'MST', '/common/basicdata/marketdata/mstrateassetquotationinfo/ComRateAssetQuotationInfo002', 'common/basicdata/marketdata/mstrateassetquotationinfo/ComRateAssetQuotationInfo002', NULL, NULL, NULL, '02', '01', '项目类/计划类利率型估值行情', 'system', NULL, sysdate, 'system', sysdate);