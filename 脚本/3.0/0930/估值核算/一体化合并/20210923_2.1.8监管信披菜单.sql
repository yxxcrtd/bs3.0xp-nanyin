
delete from sys_menu a where a.menu_id in ('REPORT_001','REPORT_001_001','REPORT_001_003','REPORT_001_004','REPORT_001_005','REPORT_001_002','REPORT_001_006','REPORT_001_001_002','REPORT_001_003_001','REPORT_001_003_002','REPORT_001_003_003','REPORT_001_003_004','REPORT_001_003_005','REPORT_001_003_006','REPORT_001_003_007','REPORT_001_003_008','REPORT_001_003_009','REPORT_001_003_010','REPORT_001_004_001','REPORT_001_004_002','REPORT_001_004_003','REPORT_001_004_004','REPORT_001_004_005','REPORT_001_004_006','REPORT_001_004_007','REPORT_001_005_001','REPORT_001_001_003','REPORT_001_001_005','REPORT_001_003_099','REPORT_001_001_009','REPORT_001_001_010','REPORT_001_001_011','REPORT_001_005_002','REPORT_001_002_003','REPORT_001_001_001','REPORT_001_001_006','REPORT_001_001_007','REPORT_001_001_008','REPORT_001_001_012','REPORT_001_001_013','REPORT_001_002_001','REPORT_001_002_002','REPORT_001_002_005','REPORT_001_002_006','REPORT_001_002_007','REPORT_001_003_012','REPORT_001_003_013','REPORT_001_003_014','REPORT_001_003_000','REPORT_001_006_001','REPORT_001_006_002','REPORT_001_001_015','REPORT_001_006_003','REPORT_001_006_004','REPORT_001_006_005','REPORT_001_006_006','REPORT_001_006_007','REPORT_001_006_008','REPORT_001_006_009','REPORT_001_006_010','REPORT_001_002_008','REPORT_001_004_008','REPORT_001_004_011','REPORT_001_004_012');

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001', 'REP', 'icon-reportmanage', null, null, 10, '02', '监管报送', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001', 'REP', null, 'REPORT_001', null, 1, '02', '业务信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003', 'REP', null, 'REPORT_001', null, 3, '02', '中债报送信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004', 'SVR', null, 'REPORT_001', null, 4, '02', '人行报送信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_005', 'REP', null, 'REPORT_001', null, 5, '02', '银保监信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_002', 'REP', null, 'REPORT_001', null, 2, '02', '报送检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006', 'REP', null, 'REPORT_001', null, 6, '02', '报送管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_002', 'REP', null, 'REPORT_001_001', 'SvrOperationalInfo001', 1, '02', '统一数据维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_001', 'SVR', null, 'REPORT_001_003', 'SvrProdPersonIssue001', 1, '02', '产品发行登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_002', 'SVR', null, 'REPORT_001_003', 'SvrProdDurationReg001', 2, '02', '产品存续期登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_003', 'SVR', null, 'REPORT_001_003', 'SvrRaiseTotalRegist001', 3, '02', '产品募集总量登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_004', 'SVR', null, 'REPORT_001_003', 'SvrPerProDeclareReg001', 4, '02', '产品申报登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_005', 'SVR', null, 'REPORT_001_003', 'SvrProdTerminationRegist001', 5, '02', '产品终止登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_006', 'SVR', null, 'REPORT_001_003', 'SvrAssetInfoRegist001', 6, '02', '资产要素登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_007', 'SVR', null, 'REPORT_001_003', 'SvrChbAstValueRegist001', 7, '02', '资产估值信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_008', 'SVR', null, 'REPORT_001_003', 'SvrChbBotAstPositionRegist001', 8, '02', '底层资产持仓登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_009', 'SVR', null, 'REPORT_001_003', 'SvrInvestTradeRegist001', 9, '02', '交易信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_010', 'SVR', null, 'REPORT_001_003', 'SvrMonthReport001', 10, '02', '银行月度报表登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_001', 'SVR', null, 'REPORT_001_004', 'PbcPrdBaseinfoSub', 1, '02', 'PIB1产品基本信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_002', 'SVR', null, 'REPORT_001_004', 'PbcPrdInitRaiseSub', 2, '02', 'PIB2产品起始募集信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_003', 'SVR', null, 'REPORT_001_004', 'PbcPrdBaseinfoEndSub', 3, '02', 'PIE1产品终止信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_004', 'SVR', null, 'REPORT_001_004', 'PbcPrdCashRaiseSub', 4, '02', 'PCD1产品资金募集信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_005', 'SVR', null, 'REPORT_001_004', 'PbcPrdAccountSub', 5, '02', 'PPB1资产池基本信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_006', 'SVR', null, 'REPORT_001_004', 'PbcPrdAccountEndSub', 6, '02', 'PPE1资产池终止信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_007', 'SVR', null, 'REPORT_001_004', 'PbcPrdAssetDebtSub', 7, '02', 'PVD1资产负债信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_005_001', 'REP', null, 'REPORT_001_005', 'SvrG06MonthReport001', 1, '02', 'G06', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_003', 'REP', null, 'REPORT_001_001', 'loanplan', 12, '02', '贷款明细信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_005', 'REP', null, 'REPORT_001_001', 'PbcCapAssetInfo001', 11, '02', '人行资金信托代码管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_099', 'SVR', null, 'REPORT_001_003', 'SvrTransMonthReport001', 11, '02', '理财产品转型情况月度统计表', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_009', 'REP', null, 'REPORT_001_001', 'ProPeoplebankcodeManagement001', 13, '02', '人行产品资产池代码管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_010', 'REP', null, 'REPORT_001_001', 'AssetInfoDetail001', 3, '2', '资产基本信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_011', 'REP', null, 'REPORT_001_001', 'AssetTypeSetting001', 4, '2', '资产/负债类别配置', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_005_002', 'REP', null, 'REPORT_001_005', 'InvestmentsInfo001', 2, '02', '表内外投资业务情况统计表', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_002_003', 'SVR', null, 'REPORT_001_002', 'ErrorMessage001', 6, '02', '数据接入检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_001', 'REP', null, 'REPORT_001_001', 'ProRegistCodeManage001', 10, '02', '产品登记编码管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_006', 'REP', null, 'REPORT_001_001', 'EquityInfo001', 5, '02', '投资者身份信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_007', 'REP', null, 'REPORT_001_001', 'InvestorsHoldInfo001', 6, '02', '投资者持有信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_008', 'SVR', null, 'REPORT_001_001', 'SvrEquityBusinessDetails001', 7, null, '投资者明细信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_012', 'REP', null, 'REPORT_001_001', 'SvrInvestorMerger001', 8, '02', '识别标识合并', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_013', 'SVR', null, 'REPORT_001_001', 'SvrTransformRule001', 9, null, '变形转换规则', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_002_001', 'REP', null, 'REPORT_001_002', 'CheckReport001', 5, '02', '报送前检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_002_002', 'REP', null, 'REPORT_001_002', 'DelayVerification001', 1, '02', '延时报送检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_002_005', 'SVR', null, 'REPORT_001_002', 'SvrEquityShareCheck001', 4, null, '投资者份额检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_002_006', 'SVR', null, 'REPORT_001_002', 'SvrProShareCheck001', 3, null, '产品份额检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_002_007', 'SVR', null, 'REPORT_001_002', 'SvrInvestorsQualityCheck001', 2, null, '投资者质量核查', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_012', 'REP', null, 'REPORT_001_003', 'EquityInfoRegist001', 12, '02', '投资者身份信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_013', 'REP', null, 'REPORT_001_003', 'InvestorsHoldInfoRegist001', 13, '02', '投资者持有信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_014', 'SVR', null, 'REPORT_001_003', 'SvrEquityBusiInfoRegist001', 14, null, '投资者明细信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_000', 'SVR', null, 'REPORT_001_003', 'SvrEmployeeInfoReg001', 0, '02', '从业人员登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_001', 'REP', null, 'REPORT_001_006', 'RegTimePoints001', 2, '02', '监管报送时点维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_002', 'REP', null, 'REPORT_001_006', 'PbcReportFileManage001', 9, null, '人行报送文件管理（资金信托）', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_015', 'REP', null, 'REPORT_001_001', 'SvrSubOperationalInfo001', 2, '02', '中债excel数据导入', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_003', 'REP', null, 'REPORT_001_006', 'ManuallyGeneratingPbcData001', 8, null, '手工生成人行报送文件', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_004', 'REP', null, 'REPORT_001_006', 'ReportUpInterfMonitor001', 1, '02', '上游接口文件读取处理监控', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_005', 'REP', null, 'REPORT_001_006', 'DebtSubmitInfo001', 7, '02', '报送情况统计及反馈', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_006', 'REP', null, 'REPORT_001_006', 'ReportStatusInfo001', 3, '02', '发送状态信息查询', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_007', 'REP', null, 'REPORT_001_006', 'ChbFeedbackFileManagement001', 4, '02', '中债反馈文件管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_008', 'REP', null, 'REPORT_001_006', 'ChbSubinfoBatchLog001', 5, '02', '中债三期大数据量报送文件下载', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_009', 'REP', null, 'REPORT_001_006', 'AutBatchProResultQuery001', 6, '02', '自动报送批处理结果查询', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_006_010', 'REP', null, 'REPORT_001_006', 'PbcSendZipDatFile', 10, '02', '人行报送文件管理（资产管理）', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_002_008', 'SVR', null, 'REPORT_001_002', 'SvrTotalCheck001', 8, null, '总体核查', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_008', 'SVR', null, 'REPORT_001_004', 'PbcMonthReport001', 8, '02', 'MI统计模板数据', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_011', 'SVR', null, 'REPORT_001_004', 'PbcPrdBaseinfoSubNew001', 11, '02', 'PIB产品基本信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_004_012', 'SVR', null, 'REPORT_001_004', 'PbcPrdBaseinfoEndSubNew001', 12, '02', 'PIE产品终止信息', 'system', null, sysdate, 'system', sysdate);

delete from  sys_page a  where a.page_id in ('ErrorMessage001','PbcPrdAccountSub','PbcPrdBaseinfoSub','PbcPrdInitRaiseSub','PbcPrdCashRaiseSub','PbcPrdAccountEndSub','PbcPrdBaseinfoEndSub','PbcPrdAssetDebtSub','SvrG06MonthReport001','SvrTotalCheck001','SvrOperationalInfo001','SvrProdPersonIssue001','SvrPerProDeclareReg001','SvrInvestTradeRegist001','SvrRaiseTotalRegist001','SvrProdDurationReg001','SvrProdTerminationRegist001','SvrChbBotAstPositionRegist001','SvrChbAstValueRegist001','SvrAssetInfoRegist001','SvrMonthReport001','ProRegistCodeManage001','PbcCapAssetInfo001','loanplan','SvrTransMonthReport001','ProPeoplebankcodeManagement001','InvestmentsInfo001','AssetTypeSetting001','EquityInfoRegist001','EquityInfo001','InvestorsHoldInfoRegist001','InvestorsHoldInfo001','SvrEquityBusiInfoRegist001','SvrEquityBusinessDetails001','SvrTransformRule001','SvrInvestorMerger001','CheckReport001','DelayVerification001','SvrEquityShareCheck001','SvrProShareCheck001','SvrInvestorsQualityCheck001','ChbFeedbackFileManagement001','ChbSubinfoBatchLog001','SvrEmployeeInfoReg001','ManuallyGeneratingPbcData001','SvrSubOperationalInfo001','PbcReportFileManage001','RegTimePoints001','ReportStatusInfo001','ReportUpInterfMonitor001','DebtSubmitInfo001','AutBatchProResultQuery001','PbcSendZipDatFile','PbcMonthReport001','PbcPrdBaseinfoEndSubNew001','PbcPrdBaseinfoSubNew001','AssetInfoDetail001');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ErrorMessage001', 'REP', '/chb/errormessage/ErrorMessage001', 'report/errormessage/ErrorMessage001', 'REPORT_001_002_003', null, null, '02', '02', '数据检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcPrdAccountSub', 'SVR', '/pbc/prdacccountsub/PbcPrdAccountSub', 'report/pbc/classic/prdacccountsub/PbcPrdAccountSub', 'REPORT_001_004_005', null, null, '01', '01', '资产池基本信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcPrdBaseinfoSub', 'SVR', '/pbc/prdbaseinfosub/PbcPrdBaseinfoSub', 'report/pbc/classic/prdbaseinfosub/PbcPrdBaseinfoSub', 'REPORT_001_004_001', null, null, '01', '01', '产品基本信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcPrdInitRaiseSub', 'SVR', '/pbc/prdinitraisesub/PbcPrdInitRaiseSub', 'report/pbc/classic/prdinitraisesub/PbcPrdInitRaiseSub', 'REPORT_001_004_002', null, null, '01', '01', '产品起始募集信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcPrdCashRaiseSub', 'SVR', '/pbc/prdcashraisesub/PbcPrdCashRaiseSub', 'report/pbc/classic/prdcashraisesub/PbcPrdCashRaiseSub', 'REPORT_001_004_004', null, null, '01', '01
', '产品资金募集信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcPrdAccountEndSub', 'SVR', '/pbc/prdaccountendsub/PbcPrdAccountEndSub', 'report/pbc/classic/prdaccountendsub/PbcPrdAccountEndSub', 'REPORT_001_004_006', null, null, '01', '01
', '资产池终止信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcPrdBaseinfoEndSub', 'SVR', '/pbc/prdinfoendsub/PbcPrdBaseinfoEndSub', 'report/pbc/classic/prdinfoendsub/PbcPrdBaseinfoEndSub', 'REPORT_001_004_003', null, null, '01', '01', '产品终止信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcPrdAssetDebtSub', 'SVR', '/pbc/prdassetdebt/PbcPrdAssetDebtSub', 'report/pbc/classic/prdassetdebt/PbcPrdAssetDebtSub', 'REPORT_001_004_007', null, null, '01', '01', '资产负债信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrG06MonthReport001', 'SVR', '/chb/monthreport/ChbG06MonthReport001', 'report/chb/monthreport/ChbG06MonthReport001', 'REPORT_001_005_001', null, null, '01', '01', 'G06', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrTotalCheck001', 'SVR', '/chb/datacheck/totalcheck/TotalCheck001', 'report/chb/datacheck/totalcheck/TotalCheck001', 'REPORT_002003', null, null, '02', '02', '总体核查', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrOperationalInfo001', 'SYS', '/common/infomanagement/SvrOperationalInfo001', 'report/common/infomanagement/SvrOperationalInfo001', 'REPORT_001_001_002', null, null, '02', '02', '统一数据维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrProdPersonIssue001', 'SVR', '/chb/submitmanagement/prodpersonissue/ProdPersonIssue001', 'report/chb/prodpersonissue/ProdPersonIssue001', 'REPORT_001_003_001', null, null, '02', '02', '产品发行登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrPerProDeclareReg001', 'SVR', '/chb/perprodeclarereg/PerProDeclareReg001', 'report/chb/perprodeclarereg/PerProDeclareReg001', 'REPORT_001_003_004', null, null, '02', '02', '产品申报登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrInvestTradeRegist001', 'SVR', '/chb/investtraderegist/InvestTradeRegist001', 'report/chb/investtraderegist/InvestTradeRegist001', 'REPORT_001_003_009', null, null, '02', '02', '交易信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrRaiseTotalRegist001', 'SVR', 'chb/prodraisetotalregist/RaiseTotalRegist001', 'report/chb/prodraisetotalregist/RaiseTotalRegist001', 'REPORT_001_003_003', null, null, '02', '02', '产品募集总量登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrProdDurationReg001', 'SVR', '/chb/proddurationreg/ProdDurationReg001', 'report/chb/proddurationreg/ProdDurationReg001', 'REPORT_001_003_002', null, null, '02', '02', '产品存续期登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrProdTerminationRegist001', 'SVR', 'chb/prodterminationregist/ProdTerminationRegist001', 'report/chb/prodterminationregist/ProdTerminationRegist001', 'REPORT_001_003_005', null, null, '02', '02', '产品终止登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrChbBotAstPositionRegist001', 'SVR', '/chb/botastpositionregist/ChbBotAstPositionRegist001', 'report/chb/botastpositionregist/ChbBotAstPositionRegist001', 'REPORT_001_003_008', null, null, '01', '01', '底层资产持仓登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrChbAstValueRegist001', 'SVR', '/chb/astvalueregist/ChbAstValueRegist001', 'report/chb/astvalueregist/ChbAstValueRegist001', 'REPORT_001_003_007', null, null, '01', '01', '资产估值信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrAssetInfoRegist001', 'SVR', '/chb/assetinforegist/AssetInfoRegist001', 'report/chb/assetinforegist/AssetInfoRegist001', 'REPORT_001_003_006', null, null, '02', '02', '资产要素登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrMonthReport001', 'SVR', '/chb/monthreport/ChbMonthReport001', 'report/chb/monthreport/ChbMonthReport001', 'REPORT_001_003_010', null, null, '01', '01', '银行月度报表登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ProRegistCodeManage001', 'REP', '/svreport/system/proregistcodemanage/ProRegistCodeManage001', 'report/system/proregistcodemanage/ProRegistCodeManage001', 'REPORT_001_001_001', null, null, '02', '02', '产品登记编码管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcCapAssetInfo001', 'REP', '/svreport/system/systempbccapassetinfo/PbcCapAssetInfo001', 'report/system/systempbccapassetinfo/PbcCapAssetInfo001', 'REPORT_001_001_005', null, null, '02', '02', '人行资金信托代码管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('loanplan', 'REP', '/common/loaninfomanagement/OperationalLoanInfo001/loanplan', 'report/common/loaninfomanagement/OperationalLoanInfo001', 'REPORT_001_001_003', null, null, '02', '02', '贷款明细信息维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrTransMonthReport001', 'SVR', '/chb/transmonthreport/ChbTransMonthReport001', 'report/chb/transmonthreport/ChbTransMonthReport001', 'REPORT_001_003_099', null, null, '01', '01', '理财产品转型情况月度统计表', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ProPeoplebankcodeManagement001', 'REP', '/svreport/system/peoplebankcodepoolmanagement/ProPeoplebankcodeManagement001', 'report/system/peoplebankcodepoolmanagement/ProPeoplebankcodeManagement001', 'REPORT_001_001_009', null, null, '02', '02', '人行产品资产池代码管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestmentsInfo001', 'REP', '/invest/report/regulatoryreporting/investmentsinfo/InvestmentsInfo001', 'invest/report/regulatoryreporting/investmentsinfo/InvestmentsInfo001', 'REPORT_001_005_002', null, null, '02', '02', '表内外投资业务情况统计表', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetTypeSetting001', 'SVR', '/chb/assettypesetting/AssetTypeSetting001', 'report/chb/assettypesetting/AssetTypeSetting001', 'REPORT_001_001_011', null, null, '01', '01', '资产/负债类别配置', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfoRegist001', 'SVR', '/svreport/submitmanagement/equityinforegist/EquityInfoRegist001', 'report/submitmanagement/equityinforegist/EquityInfoRegist001', 'REPORT_001_003_012', null, null, '02', '02', '投资者身份信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EquityInfo001', 'REP', '/svreport/submitmaintenance/equityinfo/EquityInfo001', 'report/submitmaintenance/equityinfo/EquityInfo001', 'REPORT_001_001_006', null, null, '02', '02', '投资者身份信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfoRegist001', 'SVR', '/svreport/submitmanagement/investorsholdinforegist/InvestorsHoldInfoRegist001', 'report/submitmanagement/investorsholdinforegist/InvestorsHoldInfoRegist001', 'REPORT_001_003_013', null, null, '02', '02', '投资者持有信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InvestorsHoldInfo001', 'REP', '/svreport/submitmaintenance/investorsholdinfo/InvestorsHoldInfo001', 'report/submitmaintenance/investorsholdinfo/InvestorsHoldInfo001', 'REPORT_001_001_007', null, null, '02', '02', '投资者持有信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityBusiInfoRegist001', 'SVR', '/svreport/submitmanagement/equitybusiinforegist/SvrEquityBusiInfoRegist001', 'report/submitmanagement/equitybusiinforegist/EquityBusiInfoRegist001', 'REPORT_001_003_014', null, null, '02', '02', '投资者明细信息登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityBusinessDetails001', 'SVR', '/svreport/submitmaintenance/businessdetailed/SvrEquityBusinessDetails001', 'report/submitmaintenance/businessdetailed/EquityBusinessDetails001', 'REPORT_001_001_008', null, null, '02', '02', '投资者明细信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrTransformRule001', 'SVR', '/svreport/submitmaintenance/transformrule/SvrTransformRule001', 'report/submitmaintenance/transformrule/TransformRule001', 'REPORT_001_001_013', null, null, '02', '02', '变形转换规则', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrInvestorMerger001', 'SVR', '/svreport/investormerger/InvestorMerger001', 'report/investormerger/InvestorMerger001', 'REPORT_001_001_012', null, null, '02', '02', '识别标识合并', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CheckReport001', 'REP', '/svreport/submitexamination/checkreport/CheckReport001', 'report/submitexamination/checkreport/CheckReport001', 'REPORT_001_002_001', null, null, '02', '02', '报送前检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DelayVerification001', 'REP', '/svreport/submitexamination/delayverification/DelayVerification001', 'report/submitexamination/delayverification/DelayVerification001', 'REPORT_001_002_002', null, null, '02', '02', '延时报送检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEquityShareCheck001', 'SVR', '/svreport/queryanalysis/equitysharecheck/SvrEquityShareCheck001', 'report/queryanalysis/equitysharecheck/EquityShareCheck001', 'REPORT_001_002_005', null, null, '02', '02', '投资者份额检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrProShareCheck001', 'SVR', '/svreport/queryanalysis/prosharecheck/SvrProShareCheck001', 'report/queryanalysis/prosharecheck/ProShareCheck001', 'REPORT_001_002_006', null, null, '02', '02', '产品份额检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrInvestorsQualityCheck001', 'SVR', '/svreport/submitexamination/investorsqualitycheck/InvestorsQualityCheck001', 'report/submitexamination/investorsqualitycheck/InvestorsQualityCheck001', 'REPORT_001_002_007', null, null, '02', '02', '投资者质量核查', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ChbFeedbackFileManagement001', 'REP', '/svreport/system/chbfeedbackfilemanagement/ChbFeedbackFileManagement001', 'report/system/chbfeedbackfilemanagement/ChbFeedbackFileManagement001', 'REPORT_001_006_007', null, null, '02', '02', '中债反馈文件管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ChbSubinfoBatchLog001', 'REP', '/svreport/system/subinfobatchlog/ChbSubinfoBatchLog001', 'report/system/subinfobatchlog/ChbSubinfoBatchLog001', 'REPORT_001_006_008', null, null, '02', '02', '中债报送Excel下载', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrEmployeeInfoReg001', 'SVR', 'chb/employeeinforegist/EmployeeInfoReg001', 'report/chb/employeeinforegist/EmployeeInfoReg001', 'REPORT_001_003_000', null, null, '02', '02', '从业人员登记', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ManuallyGeneratingPbcData001', 'SYS', '/pbc/classic/manuallygeneratingpbcdata/ManuallyGeneratingPbcData001', 'report/pbc/classic/manuallygeneratingpbcdata/ManuallyGeneratingPbcData001', 'REPORT_001_006_003', null, null, '02', '02', '手工生成人行报送文件', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrSubOperationalInfo001', 'SYS', '/common/submanagement/SvrSubOperationalInfo001', 'report/common/submanagement/SvrSubOperationalInfo001', 'REPORT_001_001_015', null, null, '02', '02', '中债excel数据导入', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcReportFileManage001', 'SYS', '/pbc/classic/pbcreportfilemanage/PbcReportFileManage001', 'report/pbc/classic/pbcreportfilemanage/PbcReportFileManage001', 'REPORT_001_006_002', null, null, '02', '02', '人行报送文件管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RegTimePoints001', 'REP', '/svreport/system/regtimepoints/RegTimePoints001', 'report/system/regtimepoints/RegTimePoints001', 'REPORT_001_006_001', null, null, '02', '02', '监管报送时点维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportStatusInfo001', 'REP', '/svreport/system/reportstatusinfo/ReportStatusInfo001', 'report/system/reportstatusinfo/ReportStatusInfo001', 'REPORT_001_006_006', null, null, '02', '02', '发送状态信息查询', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportUpInterfMonitor001', 'REP', '/svreport/system/upinterfmonitor/ReportUpInterfMonitor001', 'report/system/upinterfmonitor/ReportUpInterfMonitor001', 'REPORT_001_006_004', null, null, '02', '02', '上游接口文件读取处理监控', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DebtSubmitInfo001', 'REP', '/svreport/system/debtsubmitinfo/DebtSubmitInfo001', 'report/system/debtsubmitinfo/DebtSubmitInfo001', 'REPORT_001_006_005', null, null, '02', '02', '中债报送信息反馈', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AutBatchProResultQuery001', 'REP', '/svreport/system/autbatchproresultquery/AutBatchProResultQuery001', 'report/system/autbatchproresultquery/AutBatchProResultQuery001', 'REPORT_001_006_009', null, null, '02', '02', '自动报送批处理结果查询', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcSendZipDatFile', 'SVR', '/pbc/pbcsendzipdatfile/PbcSendZipDatFile', 'report/pbc/classic/pbcsendzipdatfile/PbcSendZipDatFile', 'REPORT_001_006_010', null, null, '01', '01', '新人行报送文件管理表', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcMonthReport001', 'SVR', '/svreport/pbc/PbcMonthReport001', 'report/pbc/PbcMonthReport001', 'REPORT_001_004_008', null, null, '01', '01', '人行资管产品月度统计报表', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcPrdBaseinfoEndSubNew001', 'SVR', '/pbc/prdinfoendsubnew/PbcPrdBaseinfoEndSubNew001', 'report/pbc/classic/prdinfoendsubnew/PbcPrdBaseinfoEndSubNew', 'REPORT_001_004_012', null, null, '01', '01', 'PIB产品终止信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcPrdBaseinfoSubNew001', 'SVR', '/pbc/prdbaseinfosubnew/PbcPrdBaseinfoSubNew001', 'report/pbc/classic/prdbaseinfosubnew/PbcPrdBaseinfoSubNew', 'REPORT_001_004_011', null, null, '01', '01', 'PIB产品基本信息', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetInfoDetail001', 'SVR', '/chb/assetinfodetail/AssetInfoDetail001', 'report/chb/assetinfodetail/AssetInfoDetail001', 'REPORT_001_001_010', null, null, '02', '01', '资产基本信息维护', 'system', null, sysdate, 'system', sysdate);

delete from sys_menu a where a.menu_id in ('REPORT_006','REPORT_006_001','REPORT_006_002','REPORT_006_003','REPORT_006_001_004','REPORT_006_001_005','REPORT_006_001_002','REPORT_006_001_001','REPORT_006_001_003','REPORT_006_002_003','REPORT_006_002_001','REPORT_006_002_002','REPORT_006_003_004','REPORT_006_003_001','REPORT_006_003_002','REPORT_006_003_003');

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006', 'DIS', 'icon-xinxipilou', null, null, 11, '02', '信息披露', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001', 'DIS', null, 'REPORT_006', null, 1, '02', '报表配置', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001_001', 'DIS', null, 'REPORT_006_001', 'ReportTreeMenuConfiguration001', 4, '02', '报表集合管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001_002', 'DIS', null, 'REPORT_006_001', 'ReportMenuPowerConfig001', 3, '02', '报表权限管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001_003', 'DIS', null, 'REPORT_006_001', 'ReportContentEnum001', 5, '02', '报表指标管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001_004', 'DIS', null, 'REPORT_006_001', 'ReportModelManage001', 1, '02', '报表模板管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_001_005', 'DIS', null, 'REPORT_006_001', 'ReportPageManage001', 2, '02', '报表页面管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002', 'DIS', null, 'REPORT_006', null, 2, '02', '报表管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002_001', 'DIS', null, 'REPORT_006_002', 'ReportWorkManage001', 2, '02', '报表作业管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002_002', 'DIS', null, 'REPORT_006_002', 'ReportWorkMonitor001', 3, '02', '报表作业监控', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_002_003', 'DIS', null, 'REPORT_006_002', 'ReportRule001', 1, '02', '报表规则设置', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_003', 'DIS', null, 'REPORT_006', null, 3, '02', '报表披露', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_003_001', 'DIS', null, 'REPORT_006_003', 'RepDetailsShow001', 2, '02', '报表生成管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_003_002', 'DIS', null, 'REPORT_006_003', 'ReportConfirmManage001', 3, '02', '报表确认管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_003_003', 'DIS', null, 'REPORT_006_003', 'ReportRevealManage001', 4, '02', '报表披露管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_003_004', 'DIS', null, 'REPORT_006_003', 'EstimateReveal001', 1, '02', '预计披露', 'system', null, sysdate, 'system', sysdate);

delete from   sys_page a  where a.page_id in ('EstimateReveal001','RepDetailsShow001','ReportConfirmManage001','ReportContentEnum001','ReportMenuPowerConfig001','ReportModelManage001','ReportPageManage001','ReportRevealManage001','ReportRule001','ReportTreeMenuConfiguration001','ReportWorkManage001','ReportWorkMonitor001');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EstimateReveal001', 'DIS', '/disclosure/information/manage/estimatereveal/EstimateReveal001', 'disclosure/information/manage/estimatereveal/EstimateReveal001', 'REPORT_006_003_004', null, null, '02', '02', '预计披露', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RepDetailsShow001', 'DIS', '/disclosure/information/reveal/detailshow/RepDetailsShow001', 'disclosure/information/reveal/detailshow/RepDetailsShow001', 'REPORT_006_003_001', null, null, '02', '02', '报表生成管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportConfirmManage001', 'DIS', '/disclosure/information/reveal/reportconfirm/ReportConfirmManage001', 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001', 'REPORT_006_003_002', null, null, '02', '01', '报表确认管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportContentEnum001', 'DIS', '/disclosure/information/config/reportcontentenum/ReportContentEnum001', 'disclosure/information/config/reportcontentenum/ReportContentEnum001', 'REPORT_006_001_003', null, null, '02', '02', '报表指标管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportMenuPowerConfig001', 'DIS', '/disclosure/information/reportpower/ReportMenuPowerConfig001', 'disclosure/information/reportpower/ReportMenuPowerConfig001', 'REPORT_006_001_002', null, null, '02', '02', '报表权限管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportModelManage001', 'DIS', '/disclosure/information/config/reportmodelmanage/ReportModelManage001', 'disclosure/information/config/reportmodelmanage/ReportModelManage001', 'REPORT_006_001_004', null, null, '02', '02', '报表模板管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportPageManage001', 'DIS', '/disclosure/information/reportpage/ReportPageManage001', 'disclosure/information/reportpage/ReportPageManage001', 'REPORT_006_001_005', null, null, '02', '02', '报表页面管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportRevealManage001', 'DIS', '/disclosure/information/reveal/reportreveal/ReportRevealManage001', 'disclosure/information/reveal/reportreveal/ReportRevealManage001', 'REPORT_006_003_003', null, null, '02', '01', '报表披露管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportRule001', 'DIS', '/disclosure/information/manage/workrule/ReportRule001', 'disclosure/information/manage/workrule/ReportRule001', 'REPORT_006_002_003', null, null, '02', '01', '报表规则设置', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportTreeMenuConfiguration001', 'DIS', '/disclosure/information/config/reporttree/ReportTreeMenuConfiguration001', 'disclosure/information/config/reporttree/ReportTreeMenuConfiguration001', 'REPORT_006_001_001', null, null, '02', '01', '报表集合管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportWorkManage001', 'DIS', '/disclosure/information/manage/workmanage/ReportWorkManage001', 'disclosure/information/manage/workmanage/ReportWorkManage001', 'REPORT_006_002_001', null, null, '02', '01', '报表作业管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ReportWorkMonitor001', 'DIS', '/disclosure/information/manage/workmonitor/ReportWorkMonitor001', 'disclosure/information/manage/workmonitor/ReportWorkMonitor001', 'REPORT_006_002_002', null, null, '02', '01', '报表作业监控', 'system', null, sysdate, 'system', sysdate);