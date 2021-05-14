 -- sppi 测试
delete from SYS_MENU where menu_id = 'INVEST_MANAGEMENT_001_001_013';
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INVEST_MANAGEMENT_001_001_013', 'VAL', null, 'INVEST_MANAGEMENT_001_001', 'SppiTest001', 11, '02', 'SPPI测试', 'system', null, systimestamp, 'system', systimestamp);

delete from SYS_PAGE where page_id in ('SppiTest001', 'SppiTest002', 'SppiTemplateBondDtt', 'SppiTemplateBondKpmg', 'SppiTemplateBondPwc', 'SppiTemplateNotBondDtt', 'SppiTemplateNotBondKpmg', 'SppiTemplateNotBondPwc');
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTest001', 'VAL', '/invest/asset/sppi/SppiTest001', 'invest/asset/sppi/SppiTest001', 'INVEST_MANAGEMENT_001_001_013', null, null, '02', '02', 'SPPI测试', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTest002', 'VAL', '/invest/asset/sppi/SppiTest002', 'invest/asset/sppi/SppiTest002', null, null, null, '02', '01', 'SPPI测试虚拟新建页面地址', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateBondDtt', 'VAL', '/invest/asset/sppi/SppiTemplateBondDtt', 'invest/asset/sppi/SppiTemplateBondDtt', null, null, null, '02', '01', '债券类资产SPPI测试-德勤模板', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateBondKpmg', 'VAL', '/invest/asset/sppi/SppiTemplateBondKpmg', 'invest/asset/sppi/SppiTemplateBondKpmg', null, null, null, '02', '01', '债券类资产SPPI测试-毕马威模板', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateBondPwc', 'VAL', '/invest/asset/sppi/SppiTemplateBondPwc', 'invest/asset/sppi/SppiTemplateBondPwc', null, null, null, '02', '01', '债券类资产SPPI测试-普华永道模板', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateNotBondDtt', 'VAL', '/invest/asset/sppi/SppiTemplateNotBondDtt', 'invest/asset/sppi/SppiTemplateNotBondDtt', null, null, null, '02', '01', '非标准化债权资产SPPI测试-德勤模板', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateNotBondKpmg', 'VAL', '/invest/asset/sppi/SppiTemplateNotBondKpmg', 'invest/asset/sppi/SppiTemplateNotBondKpmg', null, null, null, '02', '01', '非标准化债权资产SPPI测试-毕马威模板', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateNotBondPwc', 'VAL', '/invest/asset/sppi/SppiTemplateNotBondPwc', 'invest/asset/sppi/SppiTemplateNotBondPwc', null, null, null, '02', '01', '非标准化债权资产SPPI测试-普华永道模板', 'system', null, systimestamp, 'system', systimestamp);

delete from sys_page where PAGE_ID = 'SppiTest003';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTest003', 'VAL', '/invest/asset/sppi/SppiTest003', 'invest/asset/sppi/SppiTest003', '', null, null, '02', '02', 'SPPI测试查看-中债', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE_ELEMENT where ELEMENT_ID = 'SppiTest002_btn_submit';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateBondPwc', 'SppiTest002_btn_submit', '01', '02', null, '提交', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateBondDtt', 'SppiTest002_btn_submit', '01', '02', null, '提交', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateBondKpmg', 'SppiTest002_btn_submit', '01', '02', null, '提交', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateNotBondPwc', 'SppiTest002_btn_submit', '01', '02', null, '提交', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateNotBondDtt', 'SppiTest002_btn_submit', '01', '02', null, '提交', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTemplateNotBondKpmg', 'SppiTest002_btn_submit', '01', '02', null, '提交', 'system', null, systimestamp, 'system', systimestamp);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTest002', 'SppiTest002_btn_submit', '01', '02', null, '提交', 'system', null, systimestamp, 'system', systimestamp);

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

delete from sys_menu where menu_id='VALUATION_ACCOUNTING_001_003_007' and PAGE_ID = 'TrdAssetLossBalance001';

insert into sys_menu 
(MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, 
IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_ACCOUNTING_001_003_007', 'VAL', null, 'VALUATION_ACCOUNTING_001_003', 
'TrdAssetLossBalance001', 7, '02', '资产减值余额查询', 'system', null, date'2020-11-18', 
'system', date'2020-11-18');


-- 计划类增加投资目的
/**==========菜单:估值核算-财务管理-资产减值管理 add by lby 20201010 start==========**/
delete from sys_menu sm where sm.menu_id = 'VALUATION_ACCOUNTING_001_003_006';

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_ACCOUNTING_001_003_006', 'VAL', null, 'VALUATION_ACCOUNTING_001_003', 'TrdAssetLoss001', 6, '02', '资产减值管理', 'system', null, sysdate, 'system', sysdate);
/**========== add by lby 20201010 end==========**/

/**============菜单：基础数据管理-行情管理-项目类/计划类利率型估值行情 add by lby 20201020 start============**/
delete from sys_menu sm where sm.page_id = 'MstRateAssetQuotationInfo001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASICDATA_MANAGEMENT_001_002_025', 'MST', null, 'BASICDATA_MANAGEMENT_001_002', 'MstRateAssetQuotationInfo001', 17, '02', '项目类/计划类利率型估值行情', 'system', null, sysdate, 'system', sysdate);
/**============菜单：基础数据管理-行情管理-项目类/计划类利率型估值行情 add by lby 20201020 end============**/

/**============画面：基础数据管理-行情管理-项目类/计划类利率型估值行情--查询 add by lby 20201020 start============**/
delete from sys_page sp where sp.page_id = 'MstRateAssetQuotationInfo001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstRateAssetQuotationInfo001', 'MST', '/invest/basicdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001', 'invest/basicdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001', 'BASICDATA_MANAGEMENT_001_002_025', null, null, '02', '01', '项目类/计划类利率型估值行情 ', 'system', null, sysdate, 'system', sysdate);
/**============画面：基础数据管理-行情管理-项目类/计划类利率型估值行情--查询 add by lby 20201020 end============**/

/**============画面：基础数据管理-行情管理-项目类/计划类利率型估值行情--新建 add by lby 20201022 start============**/
delete from sys_page sp where sp.page_id = 'MstRateAssetQuotationInfo002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstRateAssetQuotationInfo002', 'MST', '/invest/basicdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo002', 'invest/basicdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo002', null, null, null, '02', '01', '项目类/计划类利率型估值行情新建 ', 'system', null, sysdate, 'system', sysdate);
/**============画面：基础数据管理-行情管理-项目类/计划类利率型估值行情--新建 add by lby 20201022 end============**/


delete from SYS_MENU where MENU_ID = 'ASSET_DURATION_MANAGEMENT_001_001_018' and PAGE_ID = 'TrdAssetLossClear001';
Insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ASSET_DURATION_MANAGEMENT_001_001_018','TRD',null,'ASSET_DURATION_MANAGEMENT_001_001','TrdAssetLossClear001',18,'02','资产减值清理','system',null,sysdate,'system',sysdate);

delete from SYS_PAGE where page_id ='TrdAssetLossClear001';
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear001','TRD','/invest/period/trdassetlossclear/TrdAssetLossClear001','invest/period/trdassetlossclear/TrdAssetLossClear001','ASSET_DURATION_MANAGEMENT_001_001_018',null,null,'02','01','资产减值清理','system',null,sysdate,'system',sysdate);

delete from SYS_PAGE where page_id ='TrdAssetLossClear002';
Insert into sys_page (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear002','TRD','/invest/period/trdassetlossclear/TrdAssetLossClear002','invest/period/trdassetlossclear/TrdAssetLossClear002','ASSET_DURATION_MANAGEMENT_001_001_020',null,null,'02','01','资产减值清理新建','system',null,sysdate,'system',sysdate);

delete from SYS_PAGE where page_id ='TrdAssetLossClear003';
Insert into sys_page (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear003','TRD','/invest/period/trdassetlossclear/TrdAssetLossClear003','invest/period/trdassetlossclear/TrdAssetLossClear003','ASSET_DURATION_MANAGEMENT_001_001_020',null,null,'02','01','资产减值双击查看','system',null,sysdate,'system',sysdate);

/**======= 菜单添加：估值减值优化升级 ADD BY lby 2020.11.16 start======**/
delete from sys_menu sm where sm.menu_id = 'SYSTEM_001_006_010' AND SM.PAGE_ID = 'ValUpVersion001';

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_006_010', 'SYS', null, 'SYSTEM_001_006', 'ValUpVersion001', 11, '02', '估值减值优化升级', 'system', null, sysdate, 'system', sysdate);
/**======= 菜单添加：估值减值优化升级 ADD BY lby 2020.11.16 end======**/

/**======= 画面：估值减值优化升级 ADD BY lby 2020.11.16 start======**/
delete from sys_page sp where sp.page_id = 'ValUpVersion001';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValUpVersion001', 'VAL', '/valuation/system/valupversion/ValUpVersion001', 'valuation/system/valupversion/ValUpVersion001', 'SYSTEM_001_006_010', null, null, '02', '01', '估值减值优化升级', 'system', null, SYSDATE, 'system', SYSDATE);
/**======= 画面：估值减值优化升级 ADD BY lby 2020.11.16 end======**/


/**======= 画面：估值减值优化升级 ADD BY lby 2020.11.17 start======**/
delete from sys_page sp where sp.page_id = 'ValUpVersion002';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValUpVersion002', 'VAL', '/valuation/system/valupversion/ValUpVersion002', 'valuation/system/valupversion/ValUpVersion002', null, null, null, '02', '01', '估值减值优化升级', 'system', null, SYSDATE, 'system', SYSDATE);
/**======= 画面：估值减值优化升级 ADD BY lby 2020.11.17 end======**/


