DELETE FROM SYS_MENU WHERE MENU_ID = 'REPORT_001_007' OR PARENT_MENU_ID = 'REPORT_001_007';
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_007', 'REP', null, 'REPORT_001', null, 7, '02', '综合台账', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_007_001', 'REP', null, 'REPORT_001_007', 'ChbMonthReportDetailProd001', 1, '02', 'G06产品端明细台账', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_007_002', 'REP', null, 'REPORT_001_007', 'ChbMonthReportDetailAsset001', 2, '02', 'G06资产端明细台账（穿透前）', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_007_003', 'REP', null, 'REPORT_001_007', 'ChbMonthReportDetailAssetB001', 3, '02', 'G06资产端明细台账（穿透后）', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_007_004', 'REP', null, 'REPORT_001_007', 'PbcMonthReportDetailProd001', 4, '02', '人行月报产品端明细台账', 'system', null, SYSDATE, 'system', SYSDATE);
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_007_005', 'REP', null, 'REPORT_001_007', 'PbcMonthReportDetailAsset001', 5, '02', '人行月报资产端明细台账', 'system', null, SYSDATE, 'system', SYSDATE);



DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('ChbMonthReportDetailAsset001','ChbMonthReportDetailAssetB001','ChbMonthReportDetailProd001','PbcMonthReportDetailAsset001','PbcMonthReportDetailProd001');
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ChbMonthReportDetailAsset001', 'REP', '/chb/monthreportdetail/ChbMonthReportDetailAsset001', 'report/chb/monthreportdetail/ChbMonthReportDetailAsset001', 'REPORT_001_007_002', null, null, '02', '02', 'G06资产端明细台账（穿透前）', 'system', null, sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ChbMonthReportDetailAssetB001', 'REP', '/chb/monthreportdetail/ChbMonthReportDetailAssetB001', 'report/chb/monthreportdetail/ChbMonthReportDetailAssetB001', 'REPORT_001_007_003', null, null, '02', '02', 'G06资产端明细台账（穿透后）', 'system', null, sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ChbMonthReportDetailProd001', 'REP', '/chb/monthreportdetail/ChbMonthReportDetailProd001', 'report/chb/monthreportdetail/ChbMonthReportDetailProd001', 'REPORT_001_007_001', null, null, '02', '02', 'G06产品端明细台账', 'system', null, sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcMonthReportDetailProd001', 'REP', '/pbc/classic/monthreportdetail/PbcMonthReportDetailProd001', 'report/pbc/classic/monthreportdetail/PbcMonthReportDetailProd001', 'REPORT_001_007_004', null, null, '02', '02', '人行月报产品端明细台账', 'system', null, sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PbcMonthReportDetailAsset001', 'REP', '/pbc/classic/monthreportdetail/PbcMonthReportDetailAsset001', 'report/pbc/classic/monthreportdetail/PbcMonthReportDetailAsset001', 'REPORT_001_007_005', null, null, '02', '02', '人行月报资产端明细台账', 'system', null, sysdate, 'system', sysdate);


DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_MONTH_REPORT_DETAIL';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT11', '1.1 现金及银行存款', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT12', '1.2 现金及银行存款', 2, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT13', '1.3 拆放同业及债券买入返售', 3, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT14', '1.4 债券', 4, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT15', '1.5 非标准化债权类资产', 5, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT16', '1.6 权益类投资', 6, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT17', '1.7 金融衍生品', 7, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT18', '1.8 代客境外理财投资QDII', 8, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT19', '1.9 商品类资产', 9, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT110', '1.10 另类资产', 10, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT111', '1.11 公募基金', 11, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT112', '1.12 私募基金', 12, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT113', '1.13 资产管理产品', 13, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT114', '1.14 委外投资——协议方式', 14, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT115', '1.15 合计', 15, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT21', '2.1 同业拆入及卖出回购', 16, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT22', '2.2 其他(负债）', 17, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT23', '2.3 合计', 18, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT31', '3.1 创业投资基金和政府出资产业投资基金', 19, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT32', '3.2 银行资本补充工具', 20, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_MONTH_REPORT_DETAIL', 'AMT33', '3.3 穿透后投向为非标准化债权类资产的行业归属', 21, null, 'system', null, SYSDATE, 'system', SYSDATE, null);

DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_PBC_DATA_TYPE';
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_PBC_DATA_TYPE', 'SVR', '人行月报详情数据类型', null, '02', 'system', null, SYSDATE, 'system', SYSDATE);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_PBC_DATA_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_PBC_DATA_TYPE', '01', '01 募集余额（表1-1、1-4、3-1）', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_PBC_DATA_TYPE', '02', '02 当期募集发生额（表1-2、3-2）', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_PBC_DATA_TYPE', '03', '03 当期兑付发生额（表1-2）', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);


DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_OPEN_TYPE';
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_OPEN_TYPE', 'SVR', '开放类型', null, '02', 'system', null, SYSDATE, 'system', SYSDATE);
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_OPEN_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_OPEN_TYPE', '01', '现金管理类产品', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_OPEN_TYPE', '02', '封闭期在半年以上的定期开放式产品', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_OPEN_TYPE', '99', '其他', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);


DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_TERM_TYPE';
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_TERM_TYPE', 'SVR', '人行月度明细_期限类型', null, '02', 'system', null, SYSDATE, 'system', SYSDATE);
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_TERM_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE', '01', '1个月（含）以下', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE', '02', '1-3个月（含）', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE', '03', '3-6个月（含）', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE', '04', '6-12个月（含）', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE', '05', '1-2年（含）', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE', '06', '2-3年（含）', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE', '07', '3年以上', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE', '99', '无固定期限', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);


DELETE FROM SYS_DATADICT WHERE DICT_CODE = 'SVR_TERM_TYPE1';
insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_TERM_TYPE1', 'SVR', '中债月度明细_期限类型', null, '02', 'system', null, SYSDATE, 'system', SYSDATE);
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_TERM_TYPE1';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE1', 'A', '90天以内', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE1', 'B', '90天（含）-6个月(含)', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE1', 'C', '6-12个月（含）', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE1', 'D', '1-3年（含）', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_TERM_TYPE1', 'E', '3年以上', 1, null, 'system', null, SYSDATE, 'system', SYSDATE, null);


-- 删除语句先注释
DROP TABLE CHB_MONTH_PRD_POSITIONDETAIL;
DROP TABLE CHB_MONTH_ASSET_POSITIONDETAIL;

create table CHB_MONTH_PRD_POSITIONDETAIL
(
  posuuid           VARCHAR2(36) not null,
  prodid            VARCHAR2(150),
  TERM             VARCHAR2(10),
  detailNo         VARCHAR2(50),
  ccy               VARCHAR2(3),
  raisemode         VARCHAR2(2),
  ivstorientation   VARCHAR2(2),
  termtype          VARCHAR2(20),
  termdays         NUMBER(24,0),
  open_type         VARCHAR2(2),
  operate_mode      VARCHAR2(2),
  profit_type       VARCHAR2(2),
  sale_target       VARCHAR2(50),
  is_transfer         VARCHAR2(2),
  is_fhxgcp           VARCHAR2(2),
  is_yllccp           VARCHAR2(2),
  con_prd_traget    VARCHAR2(2),
  ord_identified    VARCHAR2(2),
  dirtyamtsum       NUMBER(24,2),
  cleanamtsum       NUMBER(24,2),
  redeemamt         NUMBER(24,2),
  terminationamt    NUMBER(24,2),
  endamtsum_oth     NUMBER(24,2),
  customeramt       NUMBER(24,2),
  redeemintamt      NUMBER(24,2),
  terminationintamt NUMBER(24,2),
  cashamt           NUMBER(24,2),
  durationflag      VARCHAR2(2),
  vdate             DATE,
  mdate             DATE,
  openduration      VARCHAR2(2),
  org_code          VARCHAR2(20),
  dept_code         VARCHAR2(20),
  cash_mana_cate    VARCHAR2(2),
  payamtsum         NUMBER(24,2),
  closetermday      NUMBER(24,2),
  bankamt         NUMBER(24,2),
  trustfeeamt     NUMBER(24,2),
  salefeeamt      NUMBER(24,2),
  otherfeeamt     NUMBER(24,2),
  manafeeamt      NUMBER(24,2),
  floatfeeamt     NUMBER(24,2),
  is_new_prd        VARCHAR2(2),
  yield_e           NUMBER(24,2)
);
-- Add comments to the table
comment on table CHB_MONTH_PRD_POSITIONDETAIL
  is '银行月度报表产品头寸';
-- Add comments to the columns
comment on column CHB_MONTH_PRD_POSITIONDETAIL.prodid
  is '产品代码';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.TERM
  is '期次';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.ccy
  is '币种 CNY:人民币/USD:美元/EUR 欧元/QTBZ 其他币种';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.raisemode
  is '募集方式 01：公募 02：私募';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.open_type
  is '开放类型 01：现金管理类产品 02：封闭期在半年以上的定期开放式产品 99 : 其他';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.ivstorientation
  is '投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.termtype
  is '期限品种';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.termdays
  is '期限（天）';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.is_transfer
  is '是否母行划转产品';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.is_fhxgcp
  is '是否合规新产品';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.is_yllccp
  is '是否养老理财产品';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.operate_mode
  is '运行方式01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.profit_type
  is '产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.sale_target
  is '客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.con_prd_traget
  is '结构性产品挂钩标的';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.ord_identified
  is '投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.dirtyamtsum
  is '本期总募集金额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.cleanamtsum
  is '本期净募集金额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.redeemamt
  is '本期赎回本金';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.terminationamt
  is '提前终止/到期本金';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.endamtsum_oth
  is '期末余额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.customeramt
  is '本期客户实现收益总额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.redeemintamt
  is '本期赎回利息';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.terminationintamt
  is '本期提前终止/到期 利息';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.cashamt
  is '本期现金分红/中间付息';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.bankamt
  is '本期银行实现收益总额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.trustfeeamt
  is '本期本机构托管费';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.salefeeamt
  is '本期本机构销售服务费';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.otherfeeamt
  is '本期本机构其他费用';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.manafeeamt
  is '本期本机构固定管理';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.floatfeeamt
  is '本期浮动费/中收';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.durationflag
  is '存续状态 01存续/02终止/03未起息';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.vdate
  is '起息日';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.mdate
  is '到期日';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.openduration
  is '开放期限 01每日/02 180天以上/03 其他';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.org_code
  is '机构代码';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.dept_code
  is '部门代码';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.cash_mana_cate
  is '是否现金管理类';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.payamtsum
  is '本期兑付金额';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.closetermday
  is '封闭式产品期限';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.is_new_prd
  is '是否新产品';
comment on column CHB_MONTH_PRD_POSITIONDETAIL.yield_e
  is '加权率';
-- Create/Recreate primary, unique and foreign key constraints
alter table CHB_MONTH_PRD_POSITIONDETAIL
  add constraint CHB_MONTH_PRD_POSIDETAIL_PK primary key (POSUUID);



create table CHB_MONTH_ASSET_POSITIONDETAIL(
  posuuid          VARCHAR2(36) not null,
  TERM             VARCHAR2(10),
  detailNo         VARCHAR2(50),
  invest_variety   VARCHAR2(4),
  asset_code       VARCHAR2(50),
  assettype        VARCHAR2(4) not null,
  prod_id          VARCHAR2(150),
  is_fhxgcp        VARCHAR2(2),
  assetvalue_a     NUMBER(28,6),
  assetvalue_b     NUMBER(28,6),
  vdate             DATE,
  mdate             DATE,
  openPlan             DATE,
  SUBJECT_NAME     VARCHAR2(300),
  rival_party      VARCHAR2(300),
  rate             NUMBER(28,6),
  ccy              VARCHAR2(4),
  industry1        VARCHAR2(4),
  industry2        VARCHAR2(10),
  industry3        VARCHAR2(15),
  industry4        VARCHAR2(20),
  delay_pay_amt NUMBER(30,2),
  cost_amt    NUMBER(30,2),
  float_ingpl NUMBER(30,2),
  intincexp   NUMBER(30,2),
  INTADJEXP_ADD NUMBER(30,2),
  LOSS_AMT      NUMBER(30,2),
  in_asset_type    VARCHAR2(2),
  pppflag          VARCHAR2(4),
  monit_type       VARCHAR2(2),
  manage_type      VARCHAR2(2),
  is_govern_fund1  VARCHAR2(2),
  privatefund_flag VARCHAR2(2),
  privateprd_flag  VARCHAR2(2),
  bondtostock_flag VARCHAR2(2),
  CREDIT_GRADE     VARCHAR2(50),
  MAIN_GRADE       VARCHAR2(50),
  TYPE_FINAL       VARCHAR2(50),
  org_asset_type1         VARCHAR2(50),
  org_asset_type2         VARCHAR2(50),
  org_asset_type3         VARCHAR2(50),
  rating           VARCHAR2(4),
  assettype2       VARCHAR2(50),
  org_code         VARCHAR2(20),
  dept_code        VARCHAR2(20),
  SUNASSETTYPE     VARCHAR2(50),
  SUNASSETTYPE2     VARCHAR2(50),
  old_asset_code   VARCHAR2(50)
);



-- Add comments to the table
comment on table CHB_MONTH_ASSET_POSITIONDETAIL
  is '月度报表资产头寸';
-- Add comments to the columns
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.posuuid
  is '头寸编号';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.term
  is '统计日期';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.detailNo
  is '月报分类';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.invest_variety
  is '投资品种';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.asset_code
  is '资产ID';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.assettype
  is '资产一级分类';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.prod_id
  is '产品代码';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.is_fhxgcp
  is '是否合规新产品';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.vdate
  is '资产起息日';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.mdate
  is '资产到期日';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.assetvalue_a
  is '穿透前资产价值';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.assetvalue_b
  is '穿透后资产价值';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.industry1
  is '所属行业';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.cost_amt
  is '当日成本';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.float_ingpl
  is '当日公允价值变动';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.intincexp
  is '当日累计应计利息余额';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.delay_pay_amt
  is '待清算款';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.in_asset_type
  is '行类一级分类';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.pppflag
  is '公共PPP';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.monit_type
  is '是否投向地方政府平台';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.manage_type
  is '管理方式';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.is_govern_fund1
  is '是否政府投资基金';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.privatefund_flag
  is '是否投向投向“金融资产投资公司或其附属机构发行的私募股权投资基金';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.privateprd_flag
  is '是否投向金融资产投资公司发行的私募资产管理产品';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.bondtostock_flag
  is '是否投向市场化债转股相关产品的部分';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.rating
  is '评级结果';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.assettype2
  is '资产二级分类';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.org_code
  is '机构代码';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.dept_code
  is '部门代码';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.old_asset_code
  is '穿透前原始资产代码';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.is_fhxgcp
  is '是否合规新产品';
comment on column CHB_MONTH_ASSET_POSITIONDETAIL.rival_party
  is '交易对手名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CHB_MONTH_ASSET_POSITIONDETAIL
  add constraint CHB_MONTH_ASSET_POSDETAIL_PK primary key (POSUUID);



DROP TABLE PBC_MONTH_PRD_POSITIONDETAIL;
-- Create table
create table PBC_MONTH_PRD_POSITIONDETAIL
(
  posuuid         CHAR(36) not null,
  prodid          VARCHAR2(50),
  org_code        VARCHAR2(50),
  is_constracte   VARCHAR2(2),
  transfer_type   VARCHAR2(2),
  buy_place       VARCHAR2(50),
  operate_mode    VARCHAR2(2),
  ord_identified    VARCHAR2(10),
  raisemode       VARCHAR2(2),
  ivstorientation VARCHAR2(2),
  manage_type     VARCHAR2(2),
  fiduciary_duty  VARCHAR2(2),
  durationflag    VARCHAR2(2),
  is_market_fund  VARCHAR2(2),
  vdate           DATE,
  mdate             DATE,
  paystatus       VARCHAR2(2),
  term             VARCHAR2(10),
  ccy             VARCHAR2(3),
  customer_type2  VARCHAR2(50),
  endamtsum_oth   NUMBER(24,2),
  dirtyamtsum     NUMBER(24,2),
  redamt          NUMBER(24,2),
  end_days        NUMBER(30,4),
  endqtysum       NUMBER(30,5),
  dirtyqty        NUMBER(30,5),
  redqty          NUMBER(30,5),
  redcost         NUMBER(24,2),
  endcost         NUMBER(30,2),
  cash_mana_cate    VARCHAR2(2),
  is_new_prd        VARCHAR2(2),
  termtype          VARCHAR2(20),
  termdays         NUMBER(24,0),
  open_type         VARCHAR2(2),
  profit_type       VARCHAR2(2),
  sale_target       VARCHAR2(50),
  is_transfer         VARCHAR2(2),
  is_fhxgcp           VARCHAR2(2),
  is_yllccp           VARCHAR2(2)
);
-- Add comments to the table
comment on table PBC_MONTH_PRD_POSITIONDETAIL
  is '人行月报产品头寸明细';
-- Add comments to the columns
comment on column PBC_MONTH_PRD_POSITIONDETAIL.posuuid
  is '主键uuid';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.prodid
  is '产品代码';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.org_code
  is '机构代码，产品所属的机构代码';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.is_constracte
  is '是否分级产品 01 是/02 否';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.transfer_type
  is '转让类型：01 收益权转让/99 其他';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.buy_place
  is '销售区域';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.ord_identified
  is '投资者类别 01自然人/02非金融机构/03银行金融机构/04其他金融机构';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.operate_mode
  is '运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 ';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.raisemode
  is '募集方式： 01公募/02私募';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.ivstorientation
  is '投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.manage_type
  is '管理方式：01 集合/02 单一';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.fiduciary_duty
  is '受托职责：01 主动管理/02 被动管理';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.durationflag
  is '存续状态 01存续/02终止/03未起息';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.is_market_fund
  is '货币市场基金：01是/02否';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.vdate
  is '起息日，结合存续状态获得新发产品（起息日在本期=03）';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.paystatus
  is '兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.termdays
  is '合同期限：0表示无固定期限，其他值根据期限所属区间映射';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.ccy
  is '存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.customer_type2
  is '客户二级类型，枚举值参见CUSTOMER_TYPE2';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.endamtsum_oth
  is '募集余额，截止月末的余额';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.dirtyamtsum
  is '当期募集金额，本月内的募集金额';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.redamt
  is '当期兑付金额，本月内兑付的金额';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.end_days
  is '产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.endqtysum
  is '期末份额';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.dirtyqty
  is '当期募集份额';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.redqty
  is '当期兑付份额';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.redcost
  is '当期到期本金';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.endcost
  is '剩余成本';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.cash_mana_cate
  is '是否现金管理类';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.is_new_prd
  is '是否新产品';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.is_transfer
  is '是否母行划转产品';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.is_fhxgcp
  is '是否合规新产品';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.is_yllccp
  is '是否养老理财产品';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.profit_type
  is '产品收益方式01 保证收益/02 保本浮动收益/03 非保本浮动收益';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.sale_target
  is '客户类型01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.open_type
  is '开放类型 01：现金管理类产品 02：封闭期在半年以上的定期开放式产品 99 : 其他';
comment on column PBC_MONTH_PRD_POSITIONDETAIL.termtype
  is '期限品种';

-- Create/Recreate primary, unique and foreign key constraints
alter table PBC_MONTH_PRD_POSITIONDETAIL
  add constraint PBC_MONTH_PRD_POSIDETAIL_PK primary key (POSUUID);

DROP TABLE PBC_MONTH_ASSET_POSITIONDETAIL;

-- Create table
create table PBC_MONTH_ASSET_POSITIONDETAIL
(
  posuuid       CHAR(36) not null,
  prodid        VARCHAR2(50),
  term             VARCHAR2(10),
  detailNo         VARCHAR2(50),
  detailNo2         VARCHAR2(50),
  position_type VARCHAR2(50),
  chl_id        VARCHAR2(36),
  asset_code    VARCHAR2(300),
  asset_type1   VARCHAR2(50),
  asset_type2   VARCHAR2(50),
  asset_type3   VARCHAR2(50),
  SUBJECT_NAME     VARCHAR2(300),
  rival_party      VARCHAR2(300),
  rate             NUMBER(28,6),
  ccy              VARCHAR2(4),
  amt           NUMBER(30,2),
  int_amt       NUMBER(30,4),
  end_days      NUMBER(30,4),
  delay_pay_amt NUMBER(30,2),
  cost_amt    NUMBER(30,2),
  float_ingpl NUMBER(30,2),
  intincexp   NUMBER(30,2),
  INTADJEXP_ADD NUMBER(30,2),
  LOSS_AMT      NUMBER(30,2),
  bal_flag      VARCHAR2(50),
  vdate             DATE,
  mdate             DATE,
  openPlan             DATE,
  industry      VARCHAR2(10),
  industry_level2      VARCHAR2(10),
  party_type1            VARCHAR2(50),
  party_type3            VARCHAR2(50),
  party_type2            VARCHAR2(50),
  org_asset_type1         VARCHAR2(50),
  org_asset_type2         VARCHAR2(50),
  org_asset_type3         VARCHAR2(50),
  buy_place       VARCHAR2(50),
  borrower_type                  VARCHAR2(10),
  industry_info                  VARCHAR2(10),
  enterprise_scale               VARCHAR2(40),
  borrower_city                  VARCHAR2(50),
  assets_invest                  VARCHAR2(50),
  each_proportion                VARCHAR2(50)
);
-- Add comments to the table
comment on table PBC_MONTH_ASSET_POSITIONDETAIL
  is '人行报表资产头寸';
-- Add comments to the columns
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.posuuid
  is '主键uuid';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.prodid
  is '产品代码';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.position_type
  is '类型，枚举值：01 资产、02 负债、03 科目余额';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.chl_id
  is '通道代码';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.asset_code
  is '资产代码，回购、拆借存的是原始交易编号；类型=01和02该字段会有值，存的是公开市场代码，类型=03时存科目号';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.asset_type1
  is '资产一级分类，人行资产分类';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.asset_type2
  is '资产二级分类，人行资产分类';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.asset_type3
  is '资产三级分类，人行资产分类';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.ccy
  is '币种，如CNY等';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.amt
  is '金额:资产负债余额（成本法估值的资产存的是摊余成本总额，市值法估值的资产存的是市价净价总额）';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.int_amt
  is '累计利息';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.end_days
  is '剩余期限:0表示无固定期限';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.SUBJECT_NAME
  is '主体名称';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.rival_party
  is '交易对手名称';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.rate
  is '利率';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.bal_flag
  is '科目余额方向:类型=03时，字段存值，C：贷方，表示负债，D：借方，表示资产';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.vdate
  is '资产起息日';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.mdate
  is '资产到期日';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.openPlan
  is '最近开放计划结束日/行权日';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.industry
  is '发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.industry_level2
  is '发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.party_type1
  is '发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.party_type3
  is '发行机构类型（按技术领域划分）01 高新技术企业/99 其他';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.party_type2
  is '发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.buy_place
  is '除回购和拆借外贷款所属地区';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.cost_amt
  is '当日成本';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.float_ingpl
  is '当日公允价值变动';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.intincexp
  is '当日累计应计利息余额';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.delay_pay_amt
  is '待清算款';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.org_asset_type1
  is '投管一级分类';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.org_asset_type1
  is '投管一级分类';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.org_asset_type1
  is '投管一级分类';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.assets_invest
  is '贷款-基础资产投向分类';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.each_proportion
  is '贷款-各比例';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.borrower_type
  is '贷款-借款人类型';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.industry_info
  is '贷款-行业信息';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.borrower_city
  is '贷款-借款人地区(市)';
comment on column PBC_MONTH_ASSET_POSITIONDETAIL.enterprise_scale
  is '贷款-企业规模';
-- Create/Recreate primary, unique and foreign key constraints
alter table PBC_MONTH_ASSET_POSITIONDETAIL add constraint PBC_MONTH_ASSET_POSIDETAIL_PK primary key (POSUUID);
