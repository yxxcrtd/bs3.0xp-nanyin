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
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BatchGenReport001', 'DIS', '/disclosure/information/reveal/genreport/BatchGenReport001', '/disclosure/information/reveal/genreport/BatchGenReport001', 'REPORT_006_002_106', '02', '02', '报表批量生成', 'system', sysdate, 'system', sysdate);
INSERT INTO SYS_MENU(MENU_ID, SYSTEM_CODE, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_002_106', 'DIS', 'REPORT_006_002', 'BatchGenReport001', '6', '02', '报表批量生成', 'system', sysdate, 'system', sysdate);

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
