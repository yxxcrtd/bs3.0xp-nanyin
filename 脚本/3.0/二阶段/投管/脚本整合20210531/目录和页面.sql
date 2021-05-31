 -- sppi 测试
delete from SYS_PAGE where page_id in ('SppiTest001', 'SppiTest002', 'SppiTemplateBondDtt', 'SppiTemplateBondKpmg', 'SppiTemplateBondPwc', 'SppiTemplateNotBondDtt', 'SppiTemplateNotBondKpmg', 'SppiTemplateNotBondPwc');
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SppiTest001', 'VAL', '/invest/asset/sppi/SppiTest001', 'invest/asset/sppi/SppiTest001', 'ASSET_MANAGEMENT_001_002_011', null, null, '02', '02', 'SPPI测试', 'system', null, systimestamp, 'system', systimestamp);

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
/**============画面：基础数据管理-行情管理-项目类/计划类利率型估值行情--新建 add by lby 20201022 end============**/

delete from SYS_PAGE where page_id ='TrdAssetLossClear001';
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear001','TRD','/invest/period/trdassetlossclear/TrdAssetLossClear001','invest/period/trdassetlossclear/TrdAssetLossClear001','QS_MANAGEMENT_001_005_020',null,null,'02','01','资产减值清理','system',null,sysdate,'system',sysdate);

delete from SYS_PAGE where page_id ='TrdAssetLossClear002';
Insert into sys_page (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear002','TRD','/invest/period/trdassetlossclear/TrdAssetLossClear002','invest/period/trdassetlossclear/TrdAssetLossClear002','QS_MANAGEMENT_001_005_020',null,null,'02','01','资产减值清理新建','system',null,sysdate,'system',sysdate);

delete from SYS_PAGE where page_id ='TrdAssetLossClear003';
Insert into sys_page (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('TrdAssetLossClear003','TRD','/invest/period/trdassetlossclear/TrdAssetLossClear003','invest/period/trdassetlossclear/TrdAssetLossClear003','QS_MANAGEMENT_001_005_020',null,null,'02','01','资产减值双击查看','system',null,sysdate,'system',sysdate);



