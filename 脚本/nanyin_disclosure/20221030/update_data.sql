delete from DIS_TRU_BANK where TRU_NAME='南京银行股份有限公司';
insert into DIS_TRU_BANK (TRU_UUID, TRU_NAME, TRU_SIMPLE_NAME, DATA_STATUS, CREATE_USER, CREATE_TIME,UPDATE_USER,UPDATE_TIME,CREATE_DEPT) 
	values ('27025-53F3-74F8-E055-000000000001', '南京银行股份有限公司', '南京银行', 'E', 'system',sysdate, 'system',sysdate,null);

 
delete from sys_datadict where DICT_CODE='DIS_INDEX_YEAR' ;
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_INDEX_YEAR', 'DIS', '指标年度', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='DIS_INDEX_YEAR';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2020', '2020', 1, '2022', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2021', '2021', 2, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2022', '2022', 3, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2023', '2023', 4, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2024', '2024', 5, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2025', '2025', 6, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2026', '2026', 7, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2027', '2027', 8, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2028', '2028', 9, '2022', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_YEAR', '2029', '2029', 10, '2022', 'system', null, sysdate, 'system', sysdate, null);	

delete from sys_datadict where DICT_CODE='DIS_INDEX_PERIOD' ;
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DIS_INDEX_PERIOD', 'DIS', '指标周期', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='DIS_INDEX_PERIOD';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'Q1', '一季度', 1, 'TY', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'Q2', '二季度', 2, 'TY', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'Q3', '三季度', 3, 'TY', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'Q4', '四季度', 4, 'TY', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'HY', '半年', 5, 'TY', 'system', null, sysdate, 'system', sysdate, null);	
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_INDEX_PERIOD', 'TY', '全年', 6, 'TY', 'system', null, sysdate, 'system', sysdate, null);	

-- 产品净值信息（估值）菜单页面
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_019';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_004_019', 'SVR', null, 'REPORT_006_004', 'RepProdIndexGz001', 12, '02', '产品净值信息(估值)', 'system', null, sysdate, 'system',sysdate);

delete from SYS_PAGE where PAGE_ID like '%RepProdIndexGz00%';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepProdIndexGz001', 'SVR', '/disclosure/information/config/repProdIndexGz/RepProdIndexGz001', 'disclosure/information/config/repProdIndexGz/RepProdIndexGz001', 'REPORT_006_004_019', null, null, '02', '01', '产品净值信息(估值)', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepProdIndexGz002', 'SVR', '/disclosure/information/config/repProdIndexGz/RepProdIndexGz002', 'disclosure/information/config/repProdIndexGz/RepProdIndexGz002', null, null, null, '02', '01', '产品净值信息(估值)编辑', 'system', null, SYSDATE, 'system', SYSDATE);

-- 产品净值信息（估值）流程模板配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'RepProdIndexGz002';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('RepProdIndexGz002', 'RepProdIndex002_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('RepProdIndexGz002', 'RepProdIndex002_btn_save', 'oneApproval', 'system', '0001',sysdate, 'system',sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('RepProdIndexGz002', 'RepProdIndex001_btn_delete', 'noneApproval', 'system', '0001',sysdate, 'shan', sysdate, 'ALL', '02');

-- add by zhengxiaolian 2022-10-11 报表复核相关页面 start
delete from sys_menu a where a.page_id='EstimateReveal001';

delete from sys_menu a where a.page_id='ReportReview001';

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002_104', 'DIS', null, 'REPORT_006_002', 'ReportReview001', 4, '02', '报表复核管理', 'system', null, sysdate, 'system', sysdate);

delete from sys_page a where a.page_id='ReportReview001';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportReview001', 'DIS', '/disclosure/information/reveal/reportreview/ReportReview001', 'disclosure/information/reveal/reportreview/ReportReview001', 'REPORT_006_002_104', null, null, '02', '02', '报表复核管理', 'system', null, sysdate, 'system', sysdate);

delete from sys_menu a where a.page_id='ReportReviewTg001';

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002_105', 'DIS', null, 'REPORT_006_002', 'ReportReviewTg001', 5, '02', '托管复核管理', 'system', null, sysdate, 'system', sysdate);

delete from sys_page a where a.page_id='ReportReviewTg001';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportReviewTg001', 'DIS', '/disclosure/information/reveal/reportreviewtg/ReportReviewTg001', 'disclosure/information/reveal/reportreviewtg/ReportReviewTg001', 'REPORT_006_002_105', null, null, '02', '02', '托管复核管理', 'system', null, sysdate, 'system', sysdate);

update sys_menu a set a.order_no='6' where a.menu_id='REPORT_006_002_103';

delete from sys_page a where a.page_id='ReportConfirmManage005';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportConfirmManage005', 'DIS', '/disclosure/information/reveal/reportconfirm/ReportConfirmManage005', 'disclosure/information/reveal/reportconfirm/ReportConfirmManage005', null, null, null, '02', '01', '确认并推送复核', 'system', null, sysdate, 'system', sysdate);
-- add by zhengxiaolian 2022-10-11 报表复核相关页面 end

-- add by zhengxiaolian 2022-10-11 复核状态数据字典 start
delete from sys_datadict where DICT_CODE='REVIEW_STATUS' ;

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVIEW_STATUS', 'DIS', '复核状态', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='REVIEW_STATUS' ;

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REVIEW_STATUS', '01', '待复核', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REVIEW_STATUS', '02', '已推送披露', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REVIEW_STATUS', '03', '已推送托管', 3, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REVIEW_STATUS', '04', '被托管退回', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REVIEW_STATUS', '05', '被披露退回', 5, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REVIEW_STATUS', '06', '退回确认', 6, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-10-11 复核状态数据字典 end

-- add by zhengxiaolian 2022-10-11 托管复核状态数据字典 start
delete from sys_datadict where DICT_CODE='REVIEW_TG_STATUS' ;

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REVIEW_TG_STATUS', 'DIS', '托管复核状态', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='REVIEW_TG_STATUS' ;

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REVIEW_TG_STATUS', '01', '待复核', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REVIEW_TG_STATUS', '02', '已推送披露', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REVIEW_TG_STATUS', '03', '被披露退回', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-10-11 托管复核状态数据字典 end

--add by zhengxiaolian 2022-10-11 复核页面枚举值修改
delete from sys_page_dictlist where PAGE_ID='ReportReview001' and DICT_ID='REVIEW_STATUS';

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportReview001', '999999', 'REVIEW_STATUS', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportReview001', '999999', 'REVIEW_STATUS', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportReview001', '999999', 'REVIEW_STATUS', '03', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportReview001', '999999', 'REVIEW_STATUS', '04', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportReview001', '999999', 'REVIEW_STATUS', '05', null, null, 'system', null, sysdate, 'system', sysdate, null);
--add by zhengxiaolian 2022-10-11 复核页面枚举值修改

-- 业务类型名称更改 2022-10-13
UPDATE SYS_DATADICT_LIST set ITEM_VALUE = '产品净值信息' where DICT_CODE = 'COMMON_BUSINESS_TYPE' AND ITEM_CODE = 'SVR13';

-- add by zhengxiaolian 2022-10-14 托管行选择页面 start
delete from sys_page a where a.page_id='ReportReview002';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportReview002', 'DIS', '/disclosure/information/reveal/reportreview/ReportReview002', 'disclosure/information/reveal/reportreview/ReportReview002', null, null, null, '02', '01', '托管行选择', 'system', null, sysdate, 'system', sysdate);
-- add by zhengxiaolian 2022-10-14 托管行选择页面 end

-- 产品排期计划主产品搜索树页面 2022-10-14
delete from SYS_PAGE where PAGE_ID = 'DisProdPlanManage003';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DisProdPlanManage003', 'DIS', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage003', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage003', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null,SYSDATE, 'system', SYSDATE);

-- NYLCXP-1419【产品净值信息（估值）】审批流程中，显示的业务类型为产品净值信息
delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'SVR16';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('COMMON_BUSINESS_TYPE', 'SVR16', '产品净值信息_估值', 1400, '0', 'system', null, sysdate, 'system', sysdate, null);

-- 产品排期计划 数据状态下拉框去除待处理
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'DisProdPlanManage001' and DICT_ID = 'MAINTAIN_STATUS';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('DisProdPlanManage001', '999999', 'MAINTAIN_STATUS', 'C', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('DisProdPlanManage001', '999999', 'MAINTAIN_STATUS', 'E', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('DisProdPlanManage001', '999999', 'MAINTAIN_STATUS', 'D', null, null, 'system', null, sysdate, 'system', sysdate, null);

-- 产品费用交易信息维护 数据状态下拉框去除待复核
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'SvrProdFeeDealInterf001' and DICT_ID = 'MAINTAIN_STATUS';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SvrProdFeeDealInterf001', '999999', 'MAINTAIN_STATUS', 'A', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SvrProdFeeDealInterf001', '999999', 'MAINTAIN_STATUS', 'D', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('SvrProdFeeDealInterf001', '999999', 'MAINTAIN_STATUS', 'E', null, null, 'system', null, sysdate, 'system', sysdate, null);


-- add by zhengxiaolian 2022-10-18 关联交易情况维护 数据状态下拉框去除待复核 start
delete from sys_page_dictlist where page_id='RepRelDeal001' and DICT_ID='MAINTAIN_STATUS';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RepRelDeal001', '999999', 'MAINTAIN_STATUS', 'A', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RepRelDeal001', '999999', 'MAINTAIN_STATUS', 'D', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RepRelDeal001', '999999', 'MAINTAIN_STATUS', 'E', null, null, 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-10-18 关联交易情况维护 数据状态下拉框去除待复核 end

-- 产品分红计算 脚本 add by sunjie 2022/10/19 start
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_024';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_004_024', 'SVR', null, 'REPORT_006_004', 'BonusCalist001', 12, '02', '产品分红计算清单', 'system', null, sysdate, 'system',sysdate);

delete from SYS_PAGE where PAGE_ID like '%BonusCalist00%';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BonusCalist001', 'SVR', '/disclosure/information/config/bonuscalist/BonusCalist001', 'disclosure/information/config/bonuscalist/BonusCalist001', 'REPORT_006_004_024', null, null, '02', '01', '产品分红计算清单', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BonusCalist002', 'SVR', '/disclosure/information/config/bonuscalist/BonusCalist002', 'disclosure/information/config/bonuscalist/BonusCalist002', null, null, null, '02', '01', '产品分红计算清单新建', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BonusCalist003', 'SVR', '/disclosure/information/config/bonuscalist/BonusCalist003', 'disclosure/information/config/bonuscalist/BonusCalist003', null, null, null, '02', '01', '产品分红计算条件', 'system', null, SYSDATE, 'system', SYSDATE);

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'BonusCalist002';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('BonusCalist002', 'BonusCalist002_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('BonusCalist002', 'BonusCalist002_btn_save', 'oneApproval', 'system', '0001',sysdate, 'system',sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('BonusCalist002', 'BonusCalist001_btn_delete', 'noneApproval', 'system', '0001',sysdate, 'shan', sysdate, 'ALL', '02');

-- 业务类型数据字典增加 产品分红计算清单维护
delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'DISB';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('COMMON_BUSINESS_TYPE', 'DISB', '产品分红计算清单维护', 366, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
-- 产品分红计算 脚本 add by sunjie 2022/10/19 end


-- 投资账户信息维护 数据状态下拉框去除待复核
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'RepInvestAccount001' and DICT_ID = 'MAINTAIN_STATUS';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('RepInvestAccount001', '999999', 'MAINTAIN_STATUS', 'A', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('RepInvestAccount001', '999999', 'MAINTAIN_STATUS', 'D', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('RepInvestAccount001', '999999', 'MAINTAIN_STATUS', 'E', null, null, 'system', null, sysdate, 'system', sysdate, null);


-- 产品超额业绩报酬信息维护 数据状态下拉框去除待复核
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'RepFloatFee001' and DICT_ID = 'MAINTAIN_STATUS';
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('RepFloatFee001', '999999', 'MAINTAIN_STATUS', 'A', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('RepFloatFee001', '999999', 'MAINTAIN_STATUS', 'D', null, null, 'system', null, sysdate, 'system', sysdate, null);
INSERT INTO SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('RepFloatFee001', '999999', 'MAINTAIN_STATUS', 'E', null, null, 'system', null, sysdate, 'system', sysdate, null);

-- 科目余额信息 添加 页面字典绑定
delete from sys_page_dictlist where page_id='SubBalanceInfoPro001' and DICT_ID='MAINTAIN_STATUS';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SubBalanceInfoPro001', '999999', 'MAINTAIN_STATUS', 'A', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SubBalanceInfoPro001', '999999', 'MAINTAIN_STATUS', 'D', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SubBalanceInfoPro001', '999999', 'MAINTAIN_STATUS', 'E', null, null, 'system', null, sysdate, 'system', sysdate, null);

-- 产品分红到期计划主产品搜索树页面
delete from SYS_PAGE where PAGE_ID = 'ProdBonusplanManager003';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ProdBonusplanManager003', 'DIS', '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager003', '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager003', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null,SYSDATE, 'system', SYSDATE);

-- 产品基本信息子产品名称搜索树页面
delete from SYS_PAGE where PAGE_ID = 'DisProdPlanManage004';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('DisProdPlanManage004', 'DIS', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage004', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage004', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null,SYSDATE, 'system', SYSDATE);

-- 产品分红到期计划子产品名称搜索树页面
delete from SYS_PAGE where PAGE_ID = 'ProdBonusplanManager004';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ProdBonusplanManager004', 'DIS', '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager004', '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager004', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null,SYSDATE, 'system', SYSDATE);

-- 巨额赎回信息维护子产品名称搜索树页面
delete from SYS_PAGE where PAGE_ID = 'HugeInfoPro003';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('HugeInfoPro003', 'DIS', '/disclosure/information/config/hugeinfopro/HugeInfoPro003', '/disclosure/information/config/hugeinfopro/HugeInfoPro003', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null,SYSDATE, 'system', SYSDATE);

-- 报表确认管理主代码搜索树页面
delete from SYS_PAGE where PAGE_ID = 'ReportConfirmManage006';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('ReportConfirmManage006', 'DIS', '/disclosure/information/reveal/reportconfirm/ReportConfirmManage006', '/disclosure/information/reveal/reportconfirm/ReportConfirmManage006', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null,SYSDATE, 'system', SYSDATE);

--增加手工批量生成报表的菜单:chenxc
--INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BatchGenReport001', 'DIS', '/disclosure/information/reveal/genreport/BatchGenReport001', '/disclosure/information/reveal/genreport/BatchGenReport001', 'REPORT_006_002_106', '02', '02', '报表批量生成', 'system', sysdate, 'system', sysdate);
--INSERT INTO SYS_MENU(MENU_ID, SYSTEM_CODE, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_002_106', 'DIS', 'REPORT_006_002', 'BatchGenReport001', '6', '02', '报表批量生成', 'system', sysdate, 'system', sysdate);

-- add by zhengxiaolian 2022-10-24 产品树形选择页面 start
delete from SYS_PAGE where PAGE_ID = 'ReportContentEnum004';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportContentEnum004', 'DIS', '/disclosure/information/config/reportcontentenum/ReportContentEnum004', 'disclosure/information/config/reportcontentenum/ReportContentEnum004', null, null, null, '02', '02', '报表文本描述产品树', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'RepProdIndex003';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RepProdIndex003', 'DIS', '/disclosure/information/config/repProdIndex/RepProdIndex003', 'disclosure/information/config/repProdIndex/RepProdIndex003', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'DisProdPlanManage004';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DisProdPlanManage004', 'DIS', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage004', 'disclosure/information/config/disprodplanmanage/DisProdPlanManage004', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'ProdBonusplanManager003';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ProdBonusplanManager003', 'DIS', '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager003', 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager003', null, null, null, '02', '02', '报表文本描述产品树', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'DisProdPlanManage003';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DisProdPlanManage003', 'DIS', '/disclosure/information/config/disprodplanmanage/DisProdPlanManage003', 'disclosure/information/config/disprodplanmanage/DisProdPlanManage003', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'ProdBonusplanManager004';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ProdBonusplanManager004', 'DIS', '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager004', 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager004', null, null, null, '02', '02', '报表文本描述产品树', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'HugeInfoPro003';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('HugeInfoPro003', 'DIS', '/disclosure/information/config/hugeinfopro/HugeInfoPro003', 'disclosure/information/config/hugeinfopro/HugeInfoPro003', null, null, null, '02', '02', '报表文本描述产品树', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'ReportConfirmManage006';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportConfirmManage006', 'DIS', '/disclosure/information/reveal/reportconfirm/ReportConfirmManage006', 'disclosure/information/reveal/reportconfirm/ReportConfirmManage006', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null, sysdate, 'system', sysdate);
-- add by zhengxiaolian 2022-10-24 产品树形选择页面 end

--增加手工批量生成报表的菜单:chenxc
delete from SYS_PAGE where PAGE_ID='BatchGenReport001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('BatchGenReport001', 'DIS', '/disclosure/information/reveal/genreport/BatchGenReport001', 'disclosure/information/reveal/genreport/BatchGenReport001', 'REPORT_006_002_106', '02', '02', '报表批量生成', 'system', sysdate, 'system', sysdate);

delete from SYS_MENU where MENU_ID='REPORT_006_002_106';
INSERT INTO SYS_MENU(MENU_ID, SYSTEM_CODE, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_002_106', 'DIS', 'REPORT_006_002', 'BatchGenReport001', '6', '02', '报表批量生成', 'system', sysdate, 'system', sysdate)

--删除半年报：枚举值中没有下半年的问题
delete from sys_datadict_list where dict_code='HALF_YEAR' and item_code='02';

-- 到期规则修改 add by zhengxiaolian 2022-10-26 start
delete from REP_RULE where  ID in('D0000000000000000020','D0000000000000000021');
insert into REP_RULE values ('D0000000000000000020','到期公告（公募）_规则','到期公告（公募）','CP_001_003_001','A02','','14','02','0','01','','02','1','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','01','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
insert into REP_RULE values ('D0000000000000000021','到期报告（私募）_规则','到期报告（私募）','CP_001_003_002','A02','','14','02','0','01','','02','1','01','09:00:00','01','16','','','','docx','','/joyintech/disclosure/download/','02','01','system','',to_date('2022/07/21','yyyy/MM/dd'),'system',to_date('2022/07/21','yyyy/MM/dd'),'','01');
-- 到期规则修改 add by zhengxiaolian 2022-10-26 end

update REP_INFO R set R.Home_Page_Type='01' where r.rep_id in('CP_001_002_003','CP_001_002_004');
update REP_INFO R set R.Home_Page_Type='02' where r.rep_id in('CP_001_002_005','CP_001_002_006');
update REP_INFO R set R.Home_Page_Type='03' where r.rep_id in('CP_001_001_001');
update REP_INFO R set R.Home_Page_Type='04' where r.rep_id in('CP_001_003_001','CP_001_003_002');
update REP_INFO R set R.Home_Page_Type='05' where r.rep_id in('CP_001_002_007','CP_001_002_008');
update REP_INFO R set R.Home_Page_Type='06' where r.rep_id in('CP_001_002_015','CP_001_002_016');
update REP_INFO R set R.Home_Page_Type='07' where r.rep_id in('CP_001_002_017','CP_001_002_018','CP_001_002_019','CP_001_002_020');
update REP_INFO R set R.Home_Page_Type='08' where r.rep_id in('CP_001_002_009','CP_001_002_010','CP_001_002_011','CP_001_002_012','CP_001_002_013','CP_001_002_014');

-- 产品净值信息删除走流程 add by zhengxiaolian 2022-11-02 start
update sys_wfl_business_module a set a.module_id='oneApproval' where a.page_id='RepProdIndexGz002' and a.element_id='RepProdIndex001_btn_delete';
update sys_wfl_business_module a set a.module_id='oneApproval' where a.page_id='RepProdIndex002' and a.element_id='RepProdIndex001_btn_delete';
-- 产品净值信息删除走流程 add by zhengxiaolian 2022-11-02 end

--产品净值信息子查询树 2022-11-03 wanglei
delete from SYS_PAGE where PAGE_ID = 'RepProdIndex004';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RepProdIndex004', 'DIS', '/disclosure/information/config/repProdIndex/RepProdIndex004', 'disclosure/information/config/repProdIndex/RepProdIndex004', 'null', null, null, '02', '02', '报表文本描述产品树', 'system', null, sysdate, 'system', sysdate);

-- 报表删除分红计算清单 add by zhengxiaolian 2022-11-03 start
delete from rep_reportinfo where REPORTID='CP_001_007_001';
delete from REP_INFO where REP_ID='CP_001_007_001';
delete from REP_SCREEN where REP_ID='CP_001_007_001';
delete from rep_tree where TREE_ID in('CP_001_007','CP_001_007_001');
delete from REP_RULE where REP_ID ='CP_001_007_001';
-- 报表删除分红计算清单 add by zhengxiaolian 2022-11-03 end

--  add by zhuhc 20221103 start
-- 产品分红计算清单 添加 页面字典绑定
delete from sys_page_dictlist where page_id='BonusCalist001' and DICT_ID='MAINTAIN_STATUS';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('BonusCalist001', '999999', 'MAINTAIN_STATUS', 'A', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('BonusCalist001', '999999', 'MAINTAIN_STATUS', 'D', null, null, 'system', null, sysdate, 'system', sysdate, null);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('BonusCalist001', '999999', 'MAINTAIN_STATUS', 'E', null, null, 'system', null, sysdate, 'system', sysdate, null);
--  add by zhuhc 20221103 end


-- 页面菜单配置 add by zhengxiaolian 2022-11-05 start
delete from sys_menu;

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001', 'DIS', 'icon-xinxipilou', null, null, 1, '02', '信披合规管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_002', 'DIS', 'icon-xinxipilou', null, null, 2, '02', '报表配置', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_003', 'DIS', 'icon-xinxipilou', null, null, 3, '02', '基础数据维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_007', 'DIS', 'icon-xinxipilou', null, null, 4, '02', '分红数据维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_004', 'DIS', 'icon-xinxipilou', null, null, 5, '02', '定期报告数据维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_005', 'DIS', 'icon-xinxipilou', null, null, 6, '02', '产品数据维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006', 'DIS', 'icon-xinxipilou', null, null, 7, '02', '报表管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001', 'SYS', 'jyicon-system-config', null, null, 17, '2', '系统管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001', 'DIS', null, 'REPORT_001', 'InfoCompliance001', 1, '02', '信披合规留痕管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_002', 'DIS', null, 'REPORT_001', 'ReportPageManage001', 2, '02', '模板启用/禁用管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_002_001', 'DIS', null, 'REPORT_002', 'ReportModelManage001', 1, '02', '报表模板管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_002_002', 'DIS', null, 'REPORT_002', 'ReportMenuPowerConfig001', 2, '02', '报表权限管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_002_003', 'DIS', null, 'REPORT_002', 'ReportTreeMenuConfiguration001', 3, '02', '报表集合管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_002_004', 'DIS', null, 'REPORT_002', 'ReportRule001', 4, '02', '报表规则设置', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_002_005', 'DIS', null, 'REPORT_002', 'ReportWorkManage001', 5, '02', '报表作业管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_002_006', 'DIS', null, 'REPORT_002', 'ReportWorkMonitor001', 6, '02', '报表作业监控', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_003_001', 'DIS', null, 'REPORT_003', 'ProdInfo001', 1, '02', '产品基本信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_003_002', 'DIS', null, 'REPORT_003', 'DisProdPlanManage001', 2, '02', '产品排期计划', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_003_005', 'SVR', null, 'REPORT_003', 'RepProdIndex001', 5, '02', '产品净值信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_003_007', 'SVR', null, 'REPORT_003', 'SvrProdFeeDealInterf001', 7, '02', '产品费用交易信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_007_001', 'DIS', null, 'REPORT_007', 'ProdBonusplanManager001', 1, '02', '产品分红到期计划', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_007_002', 'SVR', null, 'REPORT_007', 'RepProdIndexGz001', 2, '02', '产品净值信息(估值)', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_007_003', 'DIS', null, 'REPORT_007', 'SubBalanceInfoPro001', 3, '03', '科目余额信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_007_004', 'SVR', null, 'REPORT_007', 'BonusCalist001', 4, '02', '产品分红计算清单', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_003_009', 'SVR', null, 'REPORT_003', 'RepFloatFee001', 9, '02', '产品超额业绩报酬信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_004_001', 'DIS', null, 'REPORT_004', 'Performance001', 1, '03', '产品业绩比较基准维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_004_002', 'DIS', null, 'REPORT_004', 'UnClaimAssetDetail001', 2, '02', '非标债权类资产明细维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_004_003', 'DIS', null, 'REPORT_004', 'ProdPartner001', 3, '02', '合作机构信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_004_004', 'DIS', null, 'REPORT_004', 'ProdAssetInfo001', 4, '02', '产品资产组合情况维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_004_005', 'SVR', null, 'REPORT_004', 'RepInvestAccount001', 5, '02', '投资账户信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_004_006', 'SVR', null, 'REPORT_004', 'RepRelDeal001', 6, '02', '关联交易情况维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_004_007', 'DIS', null, 'REPORT_004', 'ReportContentEnum001', 7, '02', '报表文本描述', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_005_001', 'DIS', null, 'REPORT_005', 'ProElementAdj001', 1, '02', '产品要素调整', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_005_002', 'DIS', null, 'REPORT_005', 'ProdFeediscount001', 2, '02', '产品费率优惠', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_005_003', 'DIS', null, 'REPORT_005', 'ProFailureInfo001', 3, '02', '产品不成立信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_005_004', 'DIS', null, 'REPORT_005', 'HugeInfoPro001', 4, '03', '巨额赎回信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_005_005', 'DIS', null, 'REPORT_005', 'SaleAgency001', 5, '02', '代销机构维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001', 'DIS', null, 'REPORT_006', 'RepDetailsShow001', 1, '02', '报表生成管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002', 'DIS', null, 'REPORT_006', 'ReportCheck001', 2, '02', '报表数据校验', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_003', 'DIS', null, 'REPORT_006', 'ReportConfirmManage001', 3, '02', '报表确认管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_004', 'DIS', null, 'REPORT_006', 'ReportReview001', 4, '02', '报表复核管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_005', 'DIS', null, 'REPORT_006', 'ReportReviewTg001', 5, '02', '托管复核管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_006', 'DIS', null, 'REPORT_006', 'ReportRevealManage001', 6, '02', '报表披露管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_007', 'DIS', null, 'REPORT_006', 'BatchGenReport001', 7, '02', '报表批量生成', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_001', 'SYS', null, 'SYSTEM_001', null, 1, '2', '权限管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_002', 'SYS', null, 'SYSTEM_001', null, 2, '2', '日志管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_001_001', 'SYS', null, 'SYSTEM_001_001', 'SysInstitution001', 1, '2', '机构管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_001_002', 'SYS', null, 'SYSTEM_001_001', 'SysUser001', 2, '2', '用户管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_001_003', 'SYS', null, 'SYSTEM_001_001', 'SysActorAssign001', 3, '2', '角色分配', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_001_004', 'SYS', null, 'SYSTEM_001_001', 'SysActorRole001', 4, '2', '角色权限管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_001_005', 'SYS', null, 'SYSTEM_001_001', 'SysDepartment001', 5, '2', '部门管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_002_001', 'SYS', null, 'SYSTEM_001_002', 'SysActionLog001', 1, '2', '行为日志', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_002_002', 'SYS', null, 'SYSTEM_001_002', 'WorkflowMonitor001', 2, '2', '流程监控', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_002_004', 'SYS', null, 'SYSTEM_001_002', 'SysDataProcessMark001', 4, '2', '历史数据日志', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_002_005', 'SYS', null, 'SYSTEM_001_002', 'SysBatchLog001', 5, '2', '批处理日志', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_003', 'SYS', null, 'SYSTEM_001', null, 3, '2', '投组批处理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_003_002', 'SYS', null, 'SYSTEM_001_003', 'SystemBatchQuartzConfig001', 3, '2', '定时任务管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_003_005', 'SYS', null, 'SYSTEM_001_003', 'SysBatchMonitor001', 6, '2', '批处理监控', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_004', 'SYS', null, 'SYSTEM_001', null, 5, '2', '系统参数管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_004_002', 'SYS', null, 'SYSTEM_001_004', 'SysParaSet001', 2, '2', '系统参数管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_005', 'SYS', null, 'SYSTEM_001', null, 4, '2', '批处理配置', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_003_006', 'SYS', null, 'SYSTEM_001_003', 'SysDate001', 7, '2', '平台日调整', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_003_003', 'SYS', null, 'SYSTEM_001_003', 'SysBatchStart001', 6, '02', '批处理启动', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_003_100', 'DIS', null, 'SYSTEM_001_003', 'Workflow001', 1, '02', '工作流配置', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_003_101', 'DIS', null, 'SYSTEM_001_003', 'SysDataDict001', 2, '02', '数据字典管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_003_102', 'DIS', null, 'SYSTEM_001_003', 'SysFuncWflMatch001', 3, '02', '工作流管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_005_100', 'DIS', null, 'SYSTEM_001_005', 'SysBatchProcess001', 3, '02', '批处理配置', 'system', null, sysdate, 'system', sysdate);
-- 页面菜单配置 add by zhengxiaolian 2022-11-05 end

--  add by zhuhc 20221107 start
-- 页面菜单配置 产品费用交易信息维护变更到分红数据维护菜单下
DELETE FROM sys_menu WHERE MENU_ID='REPORT_003_007';
DELETE FROM sys_menu WHERE MENU_ID='REPORT_007_005';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_007_005', 'SVR', null, 'REPORT_007', 'SvrProdFeeDealInterf001', 5, '02', '产品费用交易信息维护', 'system', null, sysdate, 'system', sysdate);
--  add by zhuhc 20221107 end
