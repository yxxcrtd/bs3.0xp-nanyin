/**add by wujie on 20180909 start**/
-- 1. 创建临时表...

drop table CMONEYMARKETFINCOME;

create table CMONEYMARKETFINCOME 
(
   OBJECT_ID            VARCHAR2(100)        not null,
   S_INFO_WINDCODE      VARCHAR2(40),
   F_INFO_BGNDATE       VARCHAR2(8),
   F_INFO_ENDDATE       VARCHAR2(8),
   F_INFO_UNITYIELD     NUMBER(20,5),
   F_INFO_YEARLYROE     NUMBER(20,4),
   ANN_DATE             VARCHAR2(8),
   OPDATE               DATE,
   OPMODE               VARCHAR2(1)
);

comment on column CMONEYMARKETFINCOME.OBJECT_ID is
'对象ID';

comment on column CMONEYMARKETFINCOME.S_INFO_WINDCODE is
'Wind代码';

comment on column CMONEYMARKETFINCOME.F_INFO_BGNDATE is
'起始日期';

comment on column CMONEYMARKETFINCOME.F_INFO_ENDDATE is
'截止日期';

comment on column CMONEYMARKETFINCOME.F_INFO_UNITYIELD is
'单位净值';

comment on column CMONEYMARKETFINCOME.F_INFO_YEARLYROE is
'七日年化收益率';

comment on column CMONEYMARKETFINCOME.ANN_DATE is
'公告日期';

comment on column CMONEYMARKETFINCOME.OPDATE is
'更新时间';

comment on column CMONEYMARKETFINCOME.OPMODE is
'更新模式';



drop table SHIBORPRICES;

create table SHIBORPRICES
(
  object_id       VARCHAR2(200),
  s_info_windcode VARCHAR2(40),
  trade_dt        VARCHAR2(8),
  b_info_rate     NUMBER(20,4),
  opdate          DATE,
  opmode          VARCHAR2(1)
);
comment on table SHIBORPRICES
  is 'Shibor行情';
comment on column SHIBORPRICES.object_id
  is '对象ID';
comment on column SHIBORPRICES.s_info_windcode
  is 'Wind代码';
comment on column SHIBORPRICES.trade_dt
  is '交易日期';
comment on column SHIBORPRICES.b_info_rate
  is '利率(%)';
comment on column SHIBORPRICES.opdate
  is '更新时间';
comment on column SHIBORPRICES.opmode
  is '更新模式';



drop table CBONDANALYSISCNBD;

create table CBONDANALYSISCNBD
(
  object_id                    VARCHAR2(100),
  s_info_windcode              VARCHAR2(40),
  trade_dt                     VARCHAR2(8),
  b_anal_matu_cnbd             NUMBER(20,4),
  b_anal_dirty_cnbd            NUMBER(20,4),
  b_anal_accrint_cnbd          NUMBER(20,4),
  b_anal_net_cnbd              NUMBER(20,4),
  b_anal_yield_cnbd            NUMBER(20,4),
  b_anal_modidura_cnbd         NUMBER(20,4),
  b_anal_cnvxty_cnbd           NUMBER(20,4),
  b_anal_vobp_cnbd             NUMBER(20,4),
  b_anal_sprdura_cnbd          NUMBER(20,4),
  b_anal_sprcnxt_cnbd          NUMBER(20,4),
  b_anal_accrintclose_cnbd     NUMBER(20,4),
  b_anal_price                 NUMBER(20,4),
  b_anal_netprice              NUMBER(20,4),
  b_anal_yield                 NUMBER(20,4),
  b_anal_modifiedduration      NUMBER(20,4),
  b_anal_convexity             NUMBER(20,4),
  b_anal_bpvalue               NUMBER(20,4),
  b_anal_sduration             NUMBER(20,4),
  b_anal_scnvxty               NUMBER(20,4),
  b_anal_interestduration_cnbd NUMBER(20,4),
  b_anal_interestcnvxty_cnbd   NUMBER(20,4),
  b_anal_interestduration      NUMBER(20,4),
  b_anal_interestcnvxty        NUMBER(20,4),
  b_anal_price_cnbd            NUMBER(20,4),
  b_anal_bpyield               NUMBER(20,4),
  b_anal_exchange              VARCHAR2(40),
  b_anal_credibility           VARCHAR2(40),
  opdate                       DATE,
  opmode                       VARCHAR2(1)
);
comment on table CBONDANALYSISCNBD
  is '中债登估值';
comment on column CBONDANALYSISCNBD.object_id
  is '对象ID';
comment on column CBONDANALYSISCNBD.s_info_windcode
  is 'Wind代码';
comment on column CBONDANALYSISCNBD.trade_dt
  is '交易日期';
comment on column CBONDANALYSISCNBD.b_anal_matu_cnbd
  is '待偿期(年)';
comment on column CBONDANALYSISCNBD.b_anal_dirty_cnbd
  is '估价全价';
comment on column CBONDANALYSISCNBD.b_anal_accrint_cnbd
  is '应计利息';
comment on column CBONDANALYSISCNBD.b_anal_net_cnbd
  is '估价净价';
comment on column CBONDANALYSISCNBD.b_anal_yield_cnbd
  is '估价收益率(%)';
comment on column CBONDANALYSISCNBD.b_anal_modidura_cnbd
  is '估价修正久期';
comment on column CBONDANALYSISCNBD.b_anal_cnvxty_cnbd
  is '估价凸性';
comment on column CBONDANALYSISCNBD.b_anal_vobp_cnbd
  is '估价基点价值';
comment on column CBONDANALYSISCNBD.b_anal_sprdura_cnbd
  is '估价利差久期';
comment on column CBONDANALYSISCNBD.b_anal_sprcnxt_cnbd
  is '估价利差凸性';
comment on column CBONDANALYSISCNBD.b_anal_accrintclose_cnbd
  is '日终应计利息';
comment on column CBONDANALYSISCNBD.b_anal_price
  is '市场全价';
comment on column CBONDANALYSISCNBD.b_anal_netprice
  is '市场净价';
comment on column CBONDANALYSISCNBD.b_anal_yield
  is '市场收益率(%)';
comment on column CBONDANALYSISCNBD.b_anal_modifiedduration
  is '市场修正久期';
comment on column CBONDANALYSISCNBD.b_anal_convexity
  is '市场凸性';
comment on column CBONDANALYSISCNBD.b_anal_bpvalue
  is '市场基点价值';
comment on column CBONDANALYSISCNBD.b_anal_sduration
  is '市场利差久期';
comment on column CBONDANALYSISCNBD.b_anal_scnvxty
  is '市场利差凸性';
comment on column CBONDANALYSISCNBD.b_anal_interestduration_cnbd
  is '估价利率久期';
comment on column CBONDANALYSISCNBD.b_anal_interestcnvxty_cnbd
  is '估价利率凸性';
comment on column CBONDANALYSISCNBD.b_anal_interestduration
  is '市场利率久期';
comment on column CBONDANALYSISCNBD.b_anal_interestcnvxty
  is '市场利率凸性';
comment on column CBONDANALYSISCNBD.b_anal_price_cnbd
  is '日终估价全价';
comment on column CBONDANALYSISCNBD.b_anal_bpyield
  is '点差收益率(%)';
comment on column CBONDANALYSISCNBD.b_anal_exchange
  is '流通场所';
comment on column CBONDANALYSISCNBD.b_anal_credibility
  is '可信度';
comment on column CBONDANALYSISCNBD.opdate
  is '更新时间';
comment on column CBONDANALYSISCNBD.opmode
  is '更新模式';



drop table CBONDBENCHMARK;

create table CBONDBENCHMARK
(
  object_id        VARCHAR2(200) not null,
  trade_dt         VARCHAR2(8),
  b_info_benchmark VARCHAR2(10),
  b_info_rate      NUMBER(20,4),
  opdate           DATE,
  opmode           VARCHAR2(1)
);
comment on table CBONDBENCHMARK
  is '法定存贷款利率';
comment on column CBONDBENCHMARK.object_id
  is '对象ID';
comment on column CBONDBENCHMARK.trade_dt
  is '交易日期';
comment on column CBONDBENCHMARK.b_info_benchmark
  is '存贷款利率类型';
comment on column CBONDBENCHMARK.b_info_rate
  is '利率(%)';
comment on column CBONDBENCHMARK.opdate
  is '更新时间';
comment on column CBONDBENCHMARK.opmode
  is '更新模式';


drop table CHINAMUTUALFUNDNAV;

create table CHINAMUTUALFUNDNAV
(
  object_id                 VARCHAR2(100) not null,
  f_info_windcode           VARCHAR2(40),
  ann_date                  VARCHAR2(8),
  price_date                VARCHAR2(8),
  f_nav_unit                NUMBER(20,4),
  f_nav_accumulated         NUMBER(20,4),
  f_nav_divaccumulated      NUMBER(20,4),
  f_nav_adjfactor           NUMBER(20,6),
  crncy_code                VARCHAR2(10),
  f_prt_netasset            NUMBER(20,4),
  f_asset_mergedsharesornot NUMBER(1),
  netasset_total            NUMBER(20,4),
  f_nav_adjusted            NUMBER(18,4),
  opdate                    DATE,
  opmode                    VARCHAR2(8)
);

comment on column CHINAMUTUALFUNDNAV.object_id
  is '对象ID';
comment on column CHINAMUTUALFUNDNAV.f_info_windcode
  is 'Wind代码';
comment on column CHINAMUTUALFUNDNAV.ann_date
  is '公告日期';
comment on column CHINAMUTUALFUNDNAV.price_date
  is '截止日期';
comment on column CHINAMUTUALFUNDNAV.f_nav_unit
  is '单位净值';
comment on column CHINAMUTUALFUNDNAV.f_nav_accumulated
  is '累计净值';
comment on column CHINAMUTUALFUNDNAV.f_nav_divaccumulated
  is '累计分红';
comment on column CHINAMUTUALFUNDNAV.f_nav_adjfactor
  is '复权因子';
comment on column CHINAMUTUALFUNDNAV.crncy_code
  is '货币代码';
comment on column CHINAMUTUALFUNDNAV.f_prt_netasset
  is '资产净值';
comment on column CHINAMUTUALFUNDNAV.f_asset_mergedsharesornot
  is '是否合计数据';
comment on column CHINAMUTUALFUNDNAV.netasset_total
  is '合计资产净值';
comment on column CHINAMUTUALFUNDNAV.f_nav_adjusted
  is '复权单位净值';
comment on column CHINAMUTUALFUNDNAV.opdate
  is '更新时间';
comment on column CHINAMUTUALFUNDNAV.opmode
  is '更新模式';




-- 2. 相关配置脚本

DELETE FROM VAL_INTERF_TEMPLATE WHERE TMP_E_NAME='CMoneyMarketDailyFIncome';

DELETE FROM VAL_INTERF_TEMPLATE WHERE TMP_E_NAME='CMoneyMarketFincome';
insert into VAL_INTERF_TEMPLATE values ('货币基金净值收益','CMoneyMarketFincome','HqParFundInfo','100104','05','ZX','system','',
sysdate,'system',sysdate);



DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='CMoneyMarketDailyFIncome';

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='CMoneyMarketFincome';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('货币基金净值收益','CMoneyMarketFincome','Wind代码','sInfoWindcode','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('货币基金净值收益','CMoneyMarketFincome','截止日期','fInfoEnddate','2','Y','10','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('货币基金净值收益','CMoneyMarketFincome','净值','fInfoUnityield','3','Y','30','1');


DELETE FROM VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='CMoneyMarketDailyFIncome';

DELETE FROM VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='CMoneyMarketFincome';
insert into VAL_INTERF_DTO_MATCH values ('货币基金每天净值收益','CMoneyMarketFincome','HqParFundInfo','sInfoWindcode','Wind代码','zqdm','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('货币基金每天净值收益','CMoneyMarketFincome','HqParFundInfo','fInfoEnddate','截止日期','dataDate','估值日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('货币基金每天净值收益','CMoneyMarketFincome','HqParFundInfo','fInfoUnityield','净值','netValue','证券净值','system','',sysdate,'system',sysdate);



delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='CBondAnalysisCNBD';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','Wind代码','sInfoWindcode','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','交易日期','tradeDt','2','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','待偿期(年)','bAnalMatuCnbd','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价全价','bAnalDirtyCnbd','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价净价','bAnalNetCnbd','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价修正久期','bAnalModiduraCnbd','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价凸性','bAnalCnvxtyCnbd','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价基点价值','bAnalVobpCnbd','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价利差久期','bAnalSprduraCnbd','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价利差凸性','bAnalSprcnxtCnbd','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价利率久期','bAnalInterestdurationCnbd','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价利率凸性','bAnalInterestcnvxtyCnbd','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价收益率(%)','bAnalYieldCnbd','3','Y','30','1');


commit;