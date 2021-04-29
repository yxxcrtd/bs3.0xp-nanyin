/**修改理财产品转型情况月度统计表的菜单代码*/
update sys_page set menu_id='REPORT_001_003_099' where page_id='SvrTransMonthReport001';


/**投资者身份信息*/
delete  from sys_page where page_id in ('EquityInfoRegist001','EquityInfo001','EquityInfo002');
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('EquityInfoRegist001','SVR','/svreport/submitmanagement/equityinforegist/EquityInfoRegist001','report/submitmanagement/equityinforegist/EquityInfoRegist001','REPORT_001_003_011',null,null,'02','02','投资者身份信息登记','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('EquityInfo002','REP','/svreport/submitmaintenance/equityinfo/EquityInfo002','report/submitmaintenance/equityinfo/EquityInfo002',null,null,null,'02','02','投资者身份信息新建','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('EquityInfo001','REP','/svreport/submitmaintenance/equityinfo/EquityInfo001','report/submitmaintenance/equityinfo/EquityInfo001','REPORT_001_001_006',null,null,'02','02','投资者身份信息','system',null,sysdate,'system',sysdate);

delete  from sys_page_element where page_id in ('EquityInfoRegist001','EquityInfo001','EquityInfo002');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfo001', 'EquityInfo001_btn_delete', '01', '02', null, '删除', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfo001', 'EquityInfo001_btn_export', '01', '02', null, '导出', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfo001', 'EquityInfo001_btn_import', '01', '02', null, '导入', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfo001', 'EquityInfo001_btn_search', '01', '02', null, '查询', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfoRegist001', 'EquityInfoRegist001_btn_cancelManual', '01', '02', null, '取消手工处理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfoRegist001', 'EquityInfoRegist001_btn_chbexport', '01', '02', null, '导出中债Excel', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfoRegist001', 'EquityInfoRegist001_btn_checkData', '01', '02', null, '数据检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfoRegist001', 'EquityInfoRegist001_btn_export', '01', '02', null, '导出查询结果', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfoRegist001', 'EquityInfoRegist001_btn_exportDel', '01', '02', null, '导出删除模板', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfoRegist001', 'EquityInfoRegist001_btn_import', '01', '02', null, '导入', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfoRegist001', 'EquityInfoRegist001_btn_manual', '01', '02', null, '手工处理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfoRegist001', 'EquityInfoRegist001_btn_search', '01', '02', null, '查询', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfoRegist001', 'EquityInfoRegist001_btn_submit', '01', '02', null, '报送', 'system', null, sysdate, 'system', sysdate);


/**投资者持有*/
delete  from sys_page where page_id in ('InvestorsHoldInfo001','InvestorsHoldInfo002','InvestorsHoldInfoRegist001');
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('InvestorsHoldInfo002','REP','/svreport/submitmaintenance/investorsholdinfo/InvestorsHoldInfo002','report/submitmaintenance/investorsholdinfo/InvestorsHoldInfo002',null,null,null,'02','02','投资者持有信息新建','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('InvestorsHoldInfoRegist001','SVR','/svreport/submitmanagement/investorsholdinforegist/InvestorsHoldInfoRegist001','report/submitmanagement/investorsholdinforegist/InvestorsHoldInfoRegist001','REPORT_001_003_012',null,null,'02','02','投资者持有信息登记','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('InvestorsHoldInfo001','REP','/svreport/submitmaintenance/investorsholdinfo/InvestorsHoldInfo001','report/submitmaintenance/investorsholdinfo/InvestorsHoldInfo001','REPORT_001_001_007',null,null,'02','02','投资者持有信息','system',null,sysdate,'system',sysdate);

delete  from sys_page_element where page_id in ('InvestorsHoldInfo001','InvestorsHoldInfo002','InvestorsHoldInfoRegist001');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfo001', 'InvestorsHoldInfo001_btn_add', '01', '02', null, '新建', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfo001', 'InvestorsHoldInfo001_btn_delete', '01', '02', null, '删除', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfo001', 'InvestorsHoldInfo001_btn_export', '01', '02', null, '导出', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfo001', 'InvestorsHoldInfo001_btn_import', '01', '02', null, '导入', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfo001', 'InvestorsHoldInfo001_btn_reset', '01', '02', null, '重置', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfo001', 'InvestorsHoldInfo001_btn_search', '01', '02', null, '查询', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfoRegist001', 'InvestorsHoldInfoRegist001_btn_cancelManual', '01', '02', null, '取消手工处理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfoRegist001', 'InvestorsHoldInfoRegist001_btn_chbexport', '01', '02', null, '导出中债Excel', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfoRegist001', 'InvestorsHoldInfoRegist001_btn_checkData', '01', '02', null, '数据检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfoRegist001', 'InvestorsHoldInfoRegist001_btn_export', '01', '02', null, '导出查询结果', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfoRegist001', 'InvestorsHoldInfoRegist001_btn_import', '01', '02', null, '导入', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfoRegist001', 'InvestorsHoldInfoRegist001_btn_manual', '01', '02', null, '手工处理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfoRegist001', 'InvestorsHoldInfoRegist001_btn_reset', '01', '02', null, '重置', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfoRegist001', 'InvestorsHoldInfoRegist001_btn_search', '01', '02', null, '查询', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfoRegist001', 'InvestorsHoldInfoRegist001_btn_submit', '01', '02', null, '报送', 'system', null, sysdate, 'system', sysdate);


/**投资者明细*/
delete  from sys_page where page_id in ('SvrEquityBusiInfoRegist001','SvrEquityBusinessDetails001','SvrEquityBusinessDetails002');
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEquityBusiInfoRegist001','SVR','/svreport/submitmanagement/equitybusiinforegist/SvrEquityBusiInfoRegist001','report/submitmanagement/equitybusiinforegist/EquityBusiInfoRegist001','REPORT_001_003_013',null,null,'02','02','投资者明细信息登记','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEquityBusinessDetails001','SVR','/svreport/submitmaintenance/businessdetailed/SvrEquityBusinessDetails001','report/submitmaintenance/businessdetailed/EquityBusinessDetails001','REPORT_001_001_009',null,null,'02','02','投资者明细信息','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEquityBusinessDetails002','SVR','/svreport/submitmaintenance/businessdetailed/SvrEquityBusinessDetails002','report/submitmaintenance/businessdetailed/EquityBusinessDetails002',null,null,null,'02','02','投资者明细信息新建','system',null,sysdate,'system',sysdate);

delete  from sys_page_element where page_id in ('SvrEquityBusiInfoRegist001','SvrEquityBusinessDetails001','SvrEquityBusinessDetails002');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityBusinessDetails001', 'SvrEquityBusinessDetails001_btn_delete', '01', '02', null, '删除', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityBusinessDetails001', 'SvrEquityBusinessDetails001_btn_export', '01', '02', null, '导出', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityBusinessDetails001', 'SvrEquityBusinessDetails001_btn_import', '01', '02', null, '导入', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityBusinessDetails001', 'SvrEquityBusinessDetails001_btn_reset', '01', '02', null, '重置', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityBusinessDetails001', 'SvrEquityBusinessDetails001_btn_search', '01', '02', null, '查询', 'system', null, sysdate, 'system', sysdate);


/**变形转换*/
delete from  SYS_PAGE  where page_id in ('SvrTransformRule001','SvrTransformRule002');
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrTransformRule001','SVR','/svreport/submitmaintenance/transformrule/SvrTransformRule001','report/submitmaintenance/transformrule/TransformRule001','REPORT_001_001_010',null,null,'02','02','变形转换规则','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrTransformRule002','SVR','/svreport/submitmaintenance/transformrule/SvrTransformRule002','report/submitmaintenance/transformrule/TransformRule002',null,null,null,'02','02','变形转换规则新建','system',null,sysdate,'system',sysdate);

/**识别标识合并*/
delete  from  SYS_PAGE  where page_id='SvrInvestorMerger001';
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrInvestorMerger001','SVR','/svreport/investormerger/InvestorMerger001','report/investormerger/InvestorMerger001','REPORT_001_001_008',null,null,'02','02','识别标识合并','system',null,sysdate,'system',sysdate);

/**报送前检查*/
delete  from SYS_PAGE  where page_id='CheckReport001';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)values ('CheckReport001', 'REP', '/svreport/submitexamination/checkreport/CheckReport001', 'report/submitexamination/checkreport/CheckReport001', 'REPORT_001_002_001', null, null, '02', '02', '报送前检查', 'system', null, sysdate, 'system', sysdate);

delete  from sys_page_element  where page_id='CheckReport001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CheckReport001', 'CheckReport001_btn_search', '01', '02', null, '查询', 'system', null, sysdate, 'system', sysdate);


/**延时报送检查*/
delete  from SYS_PAGE  where page_id='DelayVerification001';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)values ('DelayVerification001', 'REP', '/svreport/submitexamination/delayverification/DelayVerification001', 'report/submitexamination/delayverification/DelayVerification001', 'REPORT_001_002_002', null, null, '02', '02', '延时报送检查', 'system', null, sysdate, 'system', sysdate);

delete  from sys_page_element  where page_id='DelayVerification001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DelayVerification001', 'DelayVerification001_btn_search', '01', '02', null, '查询', 'system', null, sysdate, 'system', sysdate);


/**投资者份额检查*/
delete  from  SYS_PAGE  where page_id='SvrEquityShareCheck001';
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEquityShareCheck001','SVR','/svreport/queryanalysis/equitysharecheck/SvrEquityShareCheck001','report/queryanalysis/equitysharecheck/EquityShareCheck001','REPORT_001_002_005',null,null,'02','02','投资者份额检查','system',null,sysdate,'system',sysdate);

delete  from  sys_page_element  where page_id='SvrEquityShareCheck001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityShareCheck001', 'SvrEquityShareCheck001_btn_clear', '01', '02', null, '重置', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityShareCheck001', 'SvrEquityShareCheck001_btn_export', '01', '02', null, '导出', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityShareCheck001', 'SvrEquityShareCheck001_btn_search', '01', '02', null, '查询', 'system', null, sysdate, 'system', sysdate);


/**产品份额检查*/
delete  from  SYS_PAGE  where page_id='SvrProShareCheck001';
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProShareCheck001','SVR','/svreport/queryanalysis/prosharecheck/SvrProShareCheck001','report/queryanalysis/prosharecheck/ProShareCheck001','REPORT_001_002_006',null,null,'02','02','产品份额检查','system',null,sysdate,'system',sysdate);

/**投资者质量核查*/
delete  from  SYS_PAGE  where page_id='SvrInvestorsQualityCheck001';
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrInvestorsQualityCheck001','SVR','/svreport/submitexamination/investorsqualitycheck/SvrInvestorsQualityCheck001','report/submitexamination/investorsqualitycheck/InvestorsQualityCheck001','REPORT_001_002_006',null,null,'02','02','投资者质量核查','system',null,sysdate,'system',sysdate);

delete  from  sys_page_element  where page_id='SvrInvestorsQualityCheck001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrInvestorsQualityCheck001', 'SvrInvestorsQualityCheck001_btn_search', '01', '02', null, '查询', 'system', null, sysdate, 'system', sysdate);

/**中债报送Excel下载*/
delete  from  SYS_PAGE  where page_id='ChbSubinfoBatchLog001';
insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)values ('ChbSubinfoBatchLog001', 'SVR', '/svreport/system/subinfobatchlog/ChbSubinfoBatchLog001', 'report/system/subinfobatchlog/ChbSubinfoBatchLog001', 'REPORT_001_006_009', null, null, '02', '02', '中债报送Excel下载', 'system', null, sysdate, 'system', sysdate);

delete  from  sys_page_element  where page_id='ChbSubinfoBatchLog001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ChbSubinfoBatchLog001', 'ChbSubinfoBatchLog001_btn_export', '01', '02', null, '导出', 'system', null, sysdate, 'system', sysdate);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ChbSubinfoBatchLog001', 'ChbSubinfoBatchLog001_btn_search', '01', '02', null, '查询', 'system', null, sysdate, 'system', sysdate);

/**产品登记编码*/
delete  from  sys_page  where  PAGE_ID='ProRegistCodeManage003';
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values 
('ProRegistCodeManage003','REP','/svreport/system/proregistcodemanage/ProRegistCodeManage003','report/system/proregistcodemanage/ProRegistCodeManage003',null,null,null,'02','02','产品登记编码','system',null,sysdate,'system',sysdate);

/**上游接口文件读取处理监控*/
delete  from  sys_page  where  PAGE_ID='ReportUpInterfMonitor001';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportUpInterfMonitor001', 'REP', '/svreport/system/upinterfmonitor/ReportUpInterfMonitor001', 'report/system/upinterfmonitor/ReportUpInterfMonitor001', 'REPORT_001_006_004', null, null, '02', '02', '上游接口文件读取处理监控', 'system', null, sysdate, 'system', sysdate);
