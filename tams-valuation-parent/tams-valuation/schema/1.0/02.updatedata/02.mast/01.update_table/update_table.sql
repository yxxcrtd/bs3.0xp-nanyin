/**ADD                                             2018-09-20                                             START**/

/**【详细设计-债券计提摊销.xlsx】设计变更修改，增加表。  add by yangjian 2018-09-20  start**/
/**创建表[账务汇总表（账套）]中[处理序列]的序列**/
/**注意：若该序列已经存在，无需重新创建。避免id重复**/
create sequence bok_summary_bookorder_sequence
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

/**创建表[账务汇总表（账套）]**/
drop table BOK_SUMMARY_TABLE ;
create table BOK_SUMMARY_TABLE 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   HAPPEN_DATE          DATE                 not null,
   BOOK_DATE            DATE                 not null,
   BOK_DETAIL_ID        VARCHAR(32)          not null,
   BOOK_SUMMARY_ORDER   NUMERIC(20)          not null,
   BOOKSET_DATE         DATE,
   POS_AMT              NUMERIC(30, 2),
   INT_RATE             NUMERIC(30, 14),
   DAILYDSC_YIELD       NUMERIC(30, 14),
   TDY_INTINCEXP_ADD    NUMERIC(30, 2),
   TDY_INTINCEXP        NUMERIC(30, 2),
   FINPROD_TYPE         VARCHAR(50),
   VDATE                DATE,
   MDATE                DATE,
   CHL_ID               VARCHAR(32),
   INV_AIM              VARCHAR(50),
   TDY_POSITION         NUMERIC(30, 14),
   TDY_COST_AMT         NUMERIC(30, 2),
   TDY_FLOAT_INGPL      NUMERIC(30, 2),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SUMMARY_TABLE primary key (BOOKSET_ID, HAPPEN_DATE, BOOK_DATE, BOK_DETAIL_ID, BOOK_SUMMARY_ORDER)
);

comment on table BOK_SUMMARY_TABLE is
'账务汇总表（账套）';

comment on column BOK_SUMMARY_TABLE.BOOKSET_ID is
'账套代码';

comment on column BOK_SUMMARY_TABLE.HAPPEN_DATE is
'发生日期';

comment on column BOK_SUMMARY_TABLE.BOOK_DATE is
'入账日期';

comment on column BOK_SUMMARY_TABLE.BOK_DETAIL_ID is
'账务明细代码';

comment on column BOK_SUMMARY_TABLE.BOOK_SUMMARY_ORDER is
'处理序列';

comment on column BOK_SUMMARY_TABLE.BOOKSET_DATE is
'账套日期';

comment on column BOK_SUMMARY_TABLE.POS_AMT is
'当前持仓';

comment on column BOK_SUMMARY_TABLE.INT_RATE is
'计提利率';

comment on column BOK_SUMMARY_TABLE.DAILYDSC_YIELD is
'日摊销收益率';

comment on column BOK_SUMMARY_TABLE.TDY_INTINCEXP_ADD is
'当日发生应计利息';

comment on column BOK_SUMMARY_TABLE.TDY_INTINCEXP is
'当日累计应计利息余额';

comment on column BOK_SUMMARY_TABLE.FINPROD_TYPE is
'金融产品类型';

comment on column BOK_SUMMARY_TABLE.VDATE is
'起息日';

comment on column BOK_SUMMARY_TABLE.MDATE is
'到期日';

comment on column BOK_SUMMARY_TABLE.CHL_ID is
'通道代码';

comment on column BOK_SUMMARY_TABLE.INV_AIM is
'投资目的';

comment on column BOK_SUMMARY_TABLE.TDY_POSITION is
'当日计提份额';

comment on column BOK_SUMMARY_TABLE.TDY_COST_AMT is
'当日成本';

comment on column BOK_SUMMARY_TABLE.TDY_FLOAT_INGPL is
'当日公允价值变动';

comment on column BOK_SUMMARY_TABLE.CREATE_USER is
'创建人';

comment on column BOK_SUMMARY_TABLE.CREATE_DEPT is
'创建部门';

comment on column BOK_SUMMARY_TABLE.CREATE_TIME is
'创建时间';

comment on column BOK_SUMMARY_TABLE.UPDATE_USER is
'更新人';

comment on column BOK_SUMMARY_TABLE.UPDATE_TIME is
'更新时间';
/**【详细设计-债券计提摊销.xlsx】设计变更修改，增加表。  add by yangjian 2018-09-20  end**/

/**ADD                                             2018-09-19                                              END**/

/**ADD                                             2018-09-25                                              START**/
create table FIN_TRADE_PRODUCT_ADD 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   BRANCH               NUMERIC(10)          not null,
   REGIST_ORG           VARCHAR(32),
   PLEDGE_SEC_NUM       NUMERIC(10),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_TRADE_PRODUCT_ADD primary key (FINPROD_ID, FINPROD_TYPE, BRANCH)
);

comment on table FIN_TRADE_PRODUCT_ADD is
'交易类金融产品附属表';

comment on column FIN_TRADE_PRODUCT_ADD.FINPROD_ID is
'金融产品代码';

comment on column FIN_TRADE_PRODUCT_ADD.FINPROD_TYPE is
'金融产品类型';

comment on column FIN_TRADE_PRODUCT_ADD.BRANCH is
'分支序号';

comment on column FIN_TRADE_PRODUCT_ADD.REGIST_ORG is
'登记托管机构';

comment on column FIN_TRADE_PRODUCT_ADD.PLEDGE_SEC_NUM is
'押品只数';

comment on column FIN_TRADE_PRODUCT_ADD.CREATE_USER is
'创建人';

comment on column FIN_TRADE_PRODUCT_ADD.CREATE_DEPT is
'创建部门';

comment on column FIN_TRADE_PRODUCT_ADD.CREATE_TIME is
'创建时间';

comment on column FIN_TRADE_PRODUCT_ADD.UPDATE_USER is
'更新人';

comment on column FIN_TRADE_PRODUCT_ADD.UPDATE_TIME is
'更新时间';

ALTER TABLE FIN_PRODUCT ADD REGIST_ORG VARCHAR2(50);
COMMENT ON COLUMN FIN_PRODUCT.REGIST_ORG IS '登记托管机构';
/**ADD                                             2018-09-25                                              END**/


/**ADD                                             2018-09-26                                              START**/
/**serverinfo 添加服务字段   leipeng  start*/
ALTER TABLE VAL_INTERF_SERVER_INFO ADD SERVERNAME VARCHAR2(32);
comment on column VAL_INTERF_SERVER_INFO.SERVERNAME
  is '服务名称(数据库SID)';
/**serverinfo 添加服务字段   leipeng end*/

/**ADD                                             2018-09-26                                              END**/

/**ADD                                             2018-09-27                                              START**/

/**接口-模板字段校验信息配置表 增加数据字典字段 add by wujie start**/
alter table VAL_TEMP_COL_CHECK_INFO add dict_code VARCHAR2(50);
comment on column VAL_TEMP_COL_CHECK_INFO.dict_code
  is '数据字典';
/**接口-模板字段校验信息配置表 增加数据字典字段 add by wujie end**/
  
/**ADD                                             2018-09-27                                              END**/

/**ADD                                             2018-09-30                                              START**/

/**现金流利率信息表：首次重置日、指定重置日期字段类型变更 add by wujie start**/
alter table FIN_CASH_INT_INFO modify first_reset_date date;
alter table FIN_CASH_INT_INFO modify specify_reset_date VARCHAR2(200);
/**现金流利率信息表：首次重置日、指定重置日期字段类型变更 add by wujie end**/

/**ADD                                             2018-09-30                                              END**/

/**ADD                                             2018-10-08                                              START**/
/**统计分析账套汇总表：添加金融产品代码字段 add by niuqianyu start**/
ALTER TABLE BOK_STAT_BOK_POSITION ADD FINPROD_ID VARCHAR2(32);
comment on column BOK_STAT_BOK_POSITION.FINPROD_ID is
'金融产品代码';
/**统计分析账套汇总表：添加金融产品代码字段 add by niuqianyu end**/
/**ADD                                             2018-10-08                                              END**/

/**ADD                                             2018-10-09                                              START**/
/**新加：账套估值汇总表（账套） add by chumi start**/
drop table BOK_VAL_SUMMARY ;

/*==============================================================*/
/* Table: BOK_VAL_SUMMARY                                       */
/*==============================================================*/
create table BOK_VAL_SUMMARY 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   FINPROD_ID           VARCHAR(32)          not null,
   BOOK_DATE            DATE                 not null,
   BOOK_ORDER           NUMERIC(20)          not null,
   BOOKSET_DATE         DATE,
   PROFIT_TYPE          VARCHAR(50),
   PROFIT_AMT           NUMERIC(30, 2),
   CAPITAL              NUMERIC(30, 2),
   NET_ASSET_VALUE_BEF  NUMERIC(30, 2),
   NET_ASSET_VALUE      NUMERIC(30, 2),
   NET_UNIT_VALUE_BEF   NUMERIC(30, 14),
   NET_UNIT_VALUE       NUMERIC(30, 14),
   P_YIELD_BEF          NUMERIC(30, 14),
   P_YIELD              NUMERIC(30, 14),
   TDY_YIELD_BEF        NUMERIC(30, 14),
   TDY_YIELD            NUMERIC(30, 14),
   YIELD_TERM_BEF       NUMERIC(30, 14),
   YIELD_TERM           NUMERIC(30, 14),
   YIELD_7_BEF          NUMERIC(30, 14),
   YIELD_7              NUMERIC(30, 14),
   TOT_BOUNS_AMT        NUMERIC(30, 2),
   TDY_BOUNS_AMT        NUMERIC(30, 2),
   TOT_BOUNS_NET        NUMERIC(30, 14),
   TDY_BOUNS_NET        NUMERIC(30, 14),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VAL_SUMMARY primary key (BOOKSET_ID, FINPROD_ID, BOOK_DATE, BOOK_ORDER)
);

comment on table BOK_VAL_SUMMARY is
'账套估值汇总表（账套）';

comment on column BOK_VAL_SUMMARY.BOOKSET_ID is
'账套代码';

comment on column BOK_VAL_SUMMARY.FINPROD_ID is
'金融产品代码';

comment on column BOK_VAL_SUMMARY.BOOK_DATE is
'入账日期';

comment on column BOK_VAL_SUMMARY.BOOK_ORDER is
'账套代码';

comment on column BOK_VAL_SUMMARY.BOOKSET_DATE is
'账套日期';

comment on column BOK_VAL_SUMMARY.PROFIT_TYPE is
'收益类型';

comment on column BOK_VAL_SUMMARY.PROFIT_AMT is
'实收资本';

comment on column BOK_VAL_SUMMARY.CAPITAL is
'实收资本';

comment on column BOK_VAL_SUMMARY.NET_ASSET_VALUE_BEF is
'费前资产净值';

comment on column BOK_VAL_SUMMARY.NET_ASSET_VALUE is
'资产净值';

comment on column BOK_VAL_SUMMARY.NET_UNIT_VALUE_BEF is
'费前单位净值';

comment on column BOK_VAL_SUMMARY.NET_UNIT_VALUE is
'单位净值';

comment on column BOK_VAL_SUMMARY.P_YIELD_BEF is
'万份收益_费前';

comment on column BOK_VAL_SUMMARY.P_YIELD is
'万份收益';

comment on column BOK_VAL_SUMMARY.TDY_YIELD_BEF is
'当日年化收益率_费前';

comment on column BOK_VAL_SUMMARY.TDY_YIELD is
'当日年化收益率';

comment on column BOK_VAL_SUMMARY.YIELD_TERM_BEF is
'周期年化收益率_费前';

comment on column BOK_VAL_SUMMARY.YIELD_TERM is
'周期年化收益率';

comment on column BOK_VAL_SUMMARY.YIELD_7_BEF is
'当日7日年化收益率_费前';

comment on column BOK_VAL_SUMMARY.YIELD_7 is
'当日7日年化收益率';

comment on column BOK_VAL_SUMMARY.TOT_BOUNS_AMT is
'累计分红金额';

comment on column BOK_VAL_SUMMARY.TDY_BOUNS_AMT is
'当日分红金额';

comment on column BOK_VAL_SUMMARY.TOT_BOUNS_NET is
'累计分红净值';

comment on column BOK_VAL_SUMMARY.TDY_BOUNS_NET is
'当日分红净值';

comment on column BOK_VAL_SUMMARY.CREATE_USER is
'创建人';

comment on column BOK_VAL_SUMMARY.CREATE_DEPT is
'创建部门';

comment on column BOK_VAL_SUMMARY.CREATE_TIME is
'创建时间';

comment on column BOK_VAL_SUMMARY.UPDATE_USER is
'更新人';

comment on column BOK_VAL_SUMMARY.UPDATE_TIME is
'更新时间';

/**新加：账套估值汇总表（账套） add by chumi end**/

/**科目余额表增加部分字段 add by caoguo start**/
alter table BOK_BALANCE add TDY_AMT_FLAG VARCHAR2(50);
comment on column BOK_BALANCE.TDY_AMT_FLAG is
'当日发生额方向';
alter table BOK_BALANCE add TDY_CO_FLAG VARCHAR2(50);
comment on column BOK_BALANCE.TDY_CO_FLAG is
'结转当日发生额方向';
alter table BOK_BALANCE add TDY_PUR_O_D_ULAMT NUMERIC(30, 2);
comment on column BOK_BALANCE.TDY_PUR_O_D_ULAMT is
'原币申购未实现平准金借';
alter table BOK_BALANCE add TDY_RED_O_D_ULAMT NUMERIC(30, 2);
comment on column BOK_BALANCE.TDY_RED_O_D_ULAMT is
'原币赎回未实现平准金借';
alter table BOK_BALANCE add TDY_PUR_O_C_ULAMT NUMERIC(30, 2);
comment on column BOK_BALANCE.TDY_PUR_O_C_ULAMT is
'原币申购未实现平准金贷';
alter table BOK_BALANCE add TDY_RED_O_C_ULAMT NUMERIC(30, 2);
comment on column BOK_BALANCE.TDY_RED_O_C_ULAMT is
'原币赎回未实现平准金贷';
alter table BOK_BALANCE add TDY_PUR_B_D_ULAMT NUMERIC(30, 2);
comment on column BOK_BALANCE.TDY_PUR_B_D_ULAMT is
'本位币申购未实现平准金借';
alter table BOK_BALANCE add TDY_RED_B_D_ULAMT NUMERIC(30, 2);
comment on column BOK_BALANCE.TDY_RED_B_D_ULAMT is
'本位币赎回未实现平准金借';
alter table BOK_BALANCE add TDY_PUR_B_C_ULAMT NUMERIC(30, 2);
comment on column BOK_BALANCE.TDY_PUR_B_C_ULAMT is
'本位币申购未实现平准金贷';
alter table BOK_BALANCE add TDY_RED_B_C_ULAMT NUMERIC(30, 2);
comment on column BOK_BALANCE.TDY_RED_B_C_ULAMT is
'本位币赎回未实现平准金贷';

/**科目余额表增加部分字段 add by caoguo end**/

/**账务汇总表（账套）增加两个字段 add by chenggaoling start**/
alter table BOK_SUMMARY_TABLE add END_DAYS_1 NUMERIC(20);
comment on column BOK_SUMMARY_TABLE.END_DAYS_1 is
'剩余期限';
alter table BOK_SUMMARY_TABLE add END_DAYS_2 NUMERIC(20);
comment on column BOK_SUMMARY_TABLE.END_DAYS_2 is
'剩余存续期限';
/**账务汇总表（账套）增加两个字段 add by chenggaoling end**/
/**ADD                                             2018-10-09                                              END**/

/**ADD                                             2018-10-10                                              START**/
/**账套表  增加特殊核算模型 字段 add by caoguo start**/
alter table BOK_BOOKSET add BOK_SPEC_MODE VARCHAR2(50);
comment on column BOK_BOOKSET.BOK_SPEC_MODE is
'特殊核算模型，普通分层核算、收益分层核算，普通情况为空';
/**账套表  增加特殊核算模型 字段 add by caoguo end**/

--标的类金融产品扩展表 代月增加 add by wangzhiang
alter table FIN_PRODUCT_EXT add PROFIT_FLAG VARCHAR2(50);
comment on column FIN_PRODUCT_EXT.PROFIT_FLAG is
'收益标识，非保本浮动收益型、保本浮动、保证收益型';
/**ADD                                             2018-10-10                                              END**/

/**ADD                                             2018-10-11                                              START**/
/**标的类金融产品表  增加是否摊销到行权日 字段 add by chenggaoling start**/
alter table FIN_PRODUCT add IS_TO_EXE_DATE VARCHAR2(50);
comment on column FIN_PRODUCT.IS_TO_EXE_DATE is
'是否摊销到行权日';
/**标的类金融产品表  增加是否摊销到行权日 字段 add by chenggaoling end**/

/**场外金融产品行情 增加录入方式 add by wujie on 20181011 start**/
  alter table FIN_PRODUCT_VALUATION add input_type VARCHAR2(50);
comment on column FIN_PRODUCT_VALUATION.input_type
  is '录入方式，手工维护、接口导入';
  
/**标的类金融产品扩展表增加投资组合代码、是否核算、是否分层核算  add by wujie on 20181011 start**/

alter table FIN_PRODUCT_EXT add portfolio_id VARCHAR2(32);
alter table FIN_PRODUCT_EXT add is_account VARCHAR2(50);
alter table FIN_PRODUCT_EXT add is_layer_account VARCHAR2(50);

comment on column FIN_PRODUCT_EXT.portfolio_id
  is '投资组合代码';
comment on column FIN_PRODUCT_EXT.is_account
  is '是否核算';
comment on column FIN_PRODUCT_EXT.is_layer_account
  is '是否分层核算';
/**标的类金融产品扩展表增加投资组合代码、是否核算、是否分层核算  add by wujie on 20181011 end**/
  
--清算账户表新增一个SWIFT_CODE银行国际代码 wanggengyun 
alter table STL_CAPACCT_INFO add SWIFT_CODE VARCHAR2(200);
comment on column STL_CAPACCT_INFO.SWIFT_CODE is '银行国际代码';

/**账务汇总表（账套） 增加一个字段 [剩余期限用成本] add by chenggaoling start**/
alter table BOK_SUMMARY_TABLE add END_DAYS_COST_AMT NUMERIC(30, 14);
comment on column BOK_SUMMARY_TABLE.END_DAYS_COST_AMT is
'剩余期限用成本';
/**账务汇总表（账套） 增加一个字段 [剩余期限用成本] add by chenggaoling end**/

/**场外金融产品行情 增加录入方式 add by wujie on 20181011 end**/
--更新金融产品交易-交易状态类型
comment on column TRD_PRODUCT_DEAL.trade_status
  is '交易状态 01:新建,02:修改,03:撤销删除';
  
/**ADD                                             2018-10-11                                              END**/
/**ADD                                             2018-10-11                                              Begin**/

/**ADD                                             2018-10-12                                              END**/
  
/**ADD                                             2018-10-15                                              START**/
/**估值表数据（BOK_VAL_TABLE_DATA）增加【行情日期】字段 add by chenggaoling start**/
alter table BOK_VAL_TABLE_DATA add MARKET_VAL_DATE DATE;
comment on column BOK_VAL_TABLE_DATA.MARKET_VAL_DATE is
'行情日期';
/**估值表数据（BOK_VAL_TABLE_DATA）增加【行情日期】字段 add by chenggaoling end**/
  
/**账务汇总表（账套）（BOK_SUMMARY_TABLE）增加【业务明细代码】、【行情日期】字段 add by chenggaoling start**/
alter table BOK_SUMMARY_TABLE add BUSI_ID VARCHAR2(32);
comment on column BOK_SUMMARY_TABLE.BUSI_ID is
'业务明细代码';
alter table BOK_SUMMARY_TABLE add VAL_DATE DATE;
comment on column BOK_SUMMARY_TABLE.VAL_DATE is
'行情日期';
/**账务汇总表（账套）（BOK_SUMMARY_TABLE）增加【业务明细代码】、【行情日期】字段 add by chenggaoling end**/



/**金融产品交易（TRD_PRODUCT_DEAL）增加【登记托管机构】字段 add by chenggaoling start**/
alter table TRD_PRODUCT_DEAL add REGIST_ORG VARCHAR2(50);
comment on column TRD_PRODUCT_DEAL.REGIST_ORG is
'登记托管机构';
/**金融产品交易（TRD_PRODUCT_DEAL）增加【登记托管机构】字段 add by chenggaoling end**/


/**账套表增加账套全称字段 add by wujie on 20181015 start**/
alter table BOK_BOOKSET add bookset_fullname VARCHAR(200);
comment on column BOK_BOOKSET.bookset_fullname
  is '账套全称';
/**账套表增加账套全称字段 add by wujie on 20181015 end**/
  
/**ADD                                             2018-10-15                                              END**/

/**ADD                                             2018-10-16                                              START**/
/**统计分析账套汇总表添加 计提利率 字段add by wangzhiang  start**/
alter table BOK_STAT_BOK_POSITION add INT_RATE NUMERIC(30, 14);
comment on column BOK_STAT_BOK_POSITION.INT_RATE is
'计提利率';
/**统计分析账套汇总表添加 计提利率 字段 add by wangzhiang  end**/
/**ADD                                             2018-10-16                                              end**/

/**ADD                                             2018-10-17                                              start**/
/**交易类金融产品表增加三个字段  交易场所和交易日历 收益类型字段add by chumi  start**/
alter table FIN_TRADE_PRODUCT add PROFIT_TYPE VARCHAR2(50);
comment on column FIN_TRADE_PRODUCT.PROFIT_TYPE is
'收益类型，净值、预期收益、货币';
alter table FIN_TRADE_PRODUCT add TRADE_MARKET VARCHAR2(50);
comment on column FIN_TRADE_PRODUCT.TRADE_MARKET is
'交易场所';
alter table FIN_TRADE_PRODUCT add CALENDAR_ID VARCHAR2(32);
comment on column FIN_TRADE_PRODUCT.CALENDAR_ID is
'交易日历';
/**交易类金融产品表增加了三字段  交易场所和交易日历 收益类型 add by chumi  end**/

/**ADD                                             2018-10-17                                              end**/

/**ADD                                             2018-10-18                                              start**/
/**入账信息表 添加 投资目的获取方式字段	 add by wangzhiang  start**/
alter table BOK_DEAL_ACCOUNTING add INV_AIM_METHOD VARCHAR2(50);
comment on column BOK_DEAL_ACCOUNTING.INV_AIM_METHOD is
'投资目的获取方式';
/**入账信息表 添加 投资目的获取方式字段   add by wangzhiang  end**/

/**增值税汇总余额 注释脚本	 add by wangzhiang  start**/
comment on table TAX_BOK_BALANCE_SUM is
'增值税汇总余额';

comment on column TAX_BOK_BALANCE_SUM.SEQ_NO is
'流水号';

comment on column TAX_BOK_BALANCE_SUM.BOOKSET_ID is
'账套代码';

comment on column TAX_BOK_BALANCE_SUM.BOOKSET_DATE is
'账套日期';

comment on column TAX_BOK_BALANCE_SUM.HAPPEN_DATE is
'发生日期';

comment on column TAX_BOK_BALANCE_SUM.BOOK_DATE is
'入账日期';

comment on column TAX_BOK_BALANCE_SUM.O_CCY is
'币种';

comment on column TAX_BOK_BALANCE_SUM.VAT_TYPE is
'增值税类型';

comment on column TAX_BOK_BALANCE_SUM.TAX_P_TYPE is
'确认标识';

comment on column TAX_BOK_BALANCE_SUM.INCOME_AMT_ALL is
'计税收入余额';

comment on column TAX_BOK_BALANCE_SUM.HAPPEN_AMT is
'当日发生额';

comment on column TAX_BOK_BALANCE_SUM.TAX_AMT_SUM is
'计税发生额汇总';

comment on column TAX_BOK_BALANCE_SUM.TAX_AMT is
'增值税金额';

comment on column TAX_BOK_BALANCE_SUM.BOOK_ORDER is
'处理序列';

comment on column TAX_BOK_BALANCE_SUM.CREATE_USER is
'创建人';

comment on column TAX_BOK_BALANCE_SUM.CREATE_DEPT is
'创建部门';

comment on column TAX_BOK_BALANCE_SUM.CREATE_TIME is
'创建时间';

comment on column TAX_BOK_BALANCE_SUM.UPDATE_USER is
'更新人';

comment on column TAX_BOK_BALANCE_SUM.UPDATE_TIME is
'更新时间';
/**增值税汇总余额 注释脚本	 add by wangzhiang  end**/

/**增值税账务明细余额 注释脚本	 add by wangzhiang  start**/
comment on table TAX_BOK_BALANCE is
'增值税账务明细余额';

comment on column TAX_BOK_BALANCE.SEQ_NO is
'流水号';

comment on column TAX_BOK_BALANCE.BOOKSET_ID is
'账套代码';

comment on column TAX_BOK_BALANCE.BOOKSET_DATE is
'账套日期';

comment on column TAX_BOK_BALANCE.BOK_DETAIL_ID is
'账务明细代码';

comment on column TAX_BOK_BALANCE.HAPPEN_DATE is
'发生日期';

comment on column TAX_BOK_BALANCE.BOOK_DATE is
'入账日期';

comment on column TAX_BOK_BALANCE.O_CCY is
'币种';

comment on column TAX_BOK_BALANCE.VAT_TYPE is
'增值税类型';

comment on column TAX_BOK_BALANCE.TAX_P_TYPE is
'确认标识';

comment on column TAX_BOK_BALANCE.CALC_TYPE is
'计税方式';

comment on column TAX_BOK_BALANCE.INCOME_SOURCE is
'发生场景';

comment on column TAX_BOK_BALANCE.INCOME_AMT_ALL is
'计税收入余额';

comment on column TAX_BOK_BALANCE.INCOME_HAPPEN_AMT is
'当日计税收入发生额';

comment on column TAX_BOK_BALANCE.CARRY_AMT is
'当日结转累计发生额';

comment on column TAX_BOK_BALANCE.HAPPEN_AMT is
'当日发生额';

comment on column TAX_BOK_BALANCE.TAX_AMT is
'增值税金额';

comment on column TAX_BOK_BALANCE.BOOK_ORDER is
'处理序列';

comment on column TAX_BOK_BALANCE.CREATE_USER is
'创建人';

comment on column TAX_BOK_BALANCE.CREATE_DEPT is
'创建部门';

comment on column TAX_BOK_BALANCE.CREATE_TIME is
'创建时间';

comment on column TAX_BOK_BALANCE.UPDATE_USER is
'更新人';

comment on column TAX_BOK_BALANCE.UPDATE_TIME is
'更新时间';
/**增值税账务明细余额 注释脚本	 add by wangzhiang  end**/

/**增值税账务明细流水 注释脚本	 add by wangzhiang  start**/
comment on table TAX_BOK_DETAIL is
'增值税账务明细流水';

comment on column TAX_BOK_DETAIL.SEQ_NO is
'流水号';

comment on column TAX_BOK_DETAIL.BOOKSET_ID is
'账套代码';

comment on column TAX_BOK_DETAIL.BOOKSET_DATE is
'账套日期';

comment on column TAX_BOK_DETAIL.BOK_SEQNO is
'账务明细流水';

comment on column TAX_BOK_DETAIL.BOK_DETAIL_ID is
'账务明细代码';

comment on column TAX_BOK_DETAIL.HAPPEN_DATE is
'发生日期';

comment on column TAX_BOK_DETAIL.BOOK_DATE is
'入账日期';

comment on column TAX_BOK_DETAIL.BOOK_ID is
'凭证编号';

comment on column TAX_BOK_DETAIL.BOOK_ORDER is
'处理序列';

comment on column TAX_BOK_DETAIL.O_CCY is
'币种';

comment on column TAX_BOK_DETAIL.HAPPEN_AMT is
'发生含税销售额';

comment on column TAX_BOK_DETAIL.TAX_AMT is
'当日计税发生额';

comment on column TAX_BOK_DETAIL.BAL_AMT is
'计税余额发生额';

comment on column TAX_BOK_DETAIL.CARRY_AMT is
'当日结转发生额';

comment on column TAX_BOK_DETAIL.VAT_TYPE is
'增值税类型';

comment on column TAX_BOK_DETAIL.TAX_P_TYPE is
'确认标识';

comment on column TAX_BOK_DETAIL.CALC_TYPE is
'计税方式';

comment on column TAX_BOK_DETAIL.TAX_RATE is
'税率';

comment on column TAX_BOK_DETAIL.TAX_REG_ID is
'税则代码';

comment on column TAX_BOK_DETAIL.FEE_TYPE is
'免税收入类型';

comment on column TAX_BOK_DETAIL.INCOME_SOURCE is
'发生场景';

comment on column TAX_BOK_DETAIL.MDATE is
'结转区间截止日';

comment on column TAX_BOK_DETAIL.CREATE_USER is
'创建人';

comment on column TAX_BOK_DETAIL.CREATE_DEPT is
'创建部门';

comment on column TAX_BOK_DETAIL.CREATE_TIME is
'创建时间';

comment on column TAX_BOK_DETAIL.UPDATE_USER is
'更新人';

comment on column TAX_BOK_DETAIL.UPDATE_TIME is
'更新时间';
/**增值税账务明细流水 注释脚本	 add by wangzhiang  end**/

/**税额记账分类配置 注释脚本	 add by wangzhiang  start**/
comment on table TAX_BOK_CONFIG is
'税额记账分类配置';

comment on column TAX_BOK_CONFIG.CONFIG_ID is
'配置代码';

comment on column TAX_BOK_CONFIG.TABLE_ID is
'记账场景(会计事件编码)12';

comment on column TAX_BOK_CONFIG.SUBJECT_NATURE is
'发生额科目性质';

comment on column TAX_BOK_CONFIG.VAT_TYPE is
'增值税类型';

comment on column TAX_BOK_CONFIG.TAX_P_TYPE is
'确认类型';

comment on column TAX_BOK_CONFIG.INCOME_SOURCE is
'发生场景';

comment on column TAX_BOK_CONFIG.IN_OUT is
'增项/减项';

comment on column TAX_BOK_CONFIG.CALC_TYPE is
'计算方式';

comment on column TAX_BOK_CONFIG.CD_FLAG is
'发生科目借贷方向';

comment on column TAX_BOK_CONFIG.CALC_SCALE is
'占比方式';

comment on column TAX_BOK_CONFIG.CALC_SNATURE is
'占比科目科目性质';

comment on column TAX_BOK_CONFIG.CALC_BASE is
'占比基数';

comment on column TAX_BOK_CONFIG.IS_CARRT is
'金额计入方式';

comment on column TAX_BOK_CONFIG.INT_REPAY_HANDLE is
'处理条件';

comment on column TAX_BOK_CONFIG.CREATE_USER is
'创建人';

comment on column TAX_BOK_CONFIG.CREATE_DEPT is
'创建部门';

comment on column TAX_BOK_CONFIG.CREATE_TIME is
'创建时间';

comment on column TAX_BOK_CONFIG.UPDATE_USER is
'更新人';

comment on column TAX_BOK_CONFIG.UPDATE_TIME is
'更新时间';

/**税额记账分类配置 注释脚本	 add by wangzhiang  end**/

/**纳税申报表 注释脚本	 add by wangzhiang  start**/
comment on table TAX_DECLARE_SUM_INFO is
'纳税申报表';

comment on column TAX_DECLARE_SUM_INFO.SEQ_ID is
'流水号';

comment on column TAX_DECLARE_SUM_INFO.DECLARE_DATE is
'征期';

comment on column TAX_DECLARE_SUM_INFO.DECLARE_TYPE is
'汇总维度';

comment on column TAX_DECLARE_SUM_INFO.SUBJECT_NATURE is
'账套代码';

comment on column TAX_DECLARE_SUM_INFO.SIGN_FLAG is
'收益标识';

comment on column TAX_DECLARE_SUM_INFO.A1 is
'应税利息及红利收入-不含税';

comment on column TAX_DECLARE_SUM_INFO.B1 is
'利息及红利收入增值税';

comment on column TAX_DECLARE_SUM_INFO.A2 is
'金融商品转让损益';

comment on column TAX_DECLARE_SUM_INFO.B2 is
'金融商品转让增值税';

comment on column TAX_DECLARE_SUM_INFO.B3 is
'金融商品转让入账增值税';

comment on column TAX_DECLARE_SUM_INFO.FREE_36FILE is
'免税收入-36号文件';

comment on column TAX_DECLARE_SUM_INFO.FREE_46FILE is
'免税收入-46号文件';

comment on column TAX_DECLARE_SUM_INFO.FREE_70FILE is
'免税收入-70号文件';

comment on column TAX_DECLARE_SUM_INFO.FREE_SEC is
'免税收入-国债及地方债';

comment on column TAX_DECLARE_SUM_INFO.C1 is
'本期应交增值税产品划转到银行增值税';

comment on column TAX_DECLARE_SUM_INFO.C2 is
'本期应交附加税费产品划转到银行附加税费';

comment on column TAX_DECLARE_SUM_INFO.C3 is
'本期应交增值税银行划转到税局金融商品转让增值税';

comment on column TAX_DECLARE_SUM_INFO.C4 is
'本期应交增值税银行划转到税局增值税';

comment on column TAX_DECLARE_SUM_INFO.C5 is
'本期应交附加税费银行划转到税局附加税费';

comment on column TAX_DECLARE_SUM_INFO.E1 is
'本期留存收益变动额';

comment on column TAX_DECLARE_SUM_INFO.E2 is
'期初留存收益';

comment on column TAX_DECLARE_SUM_INFO.E3 is
'期末留存收益';

comment on column TAX_DECLARE_SUM_INFO.CREATE_USER is
'创建人';

comment on column TAX_DECLARE_SUM_INFO.CREATE_DEPT is
'创建部门';

comment on column TAX_DECLARE_SUM_INFO.CREATE_TIME is
'创建时间';

comment on column TAX_DECLARE_SUM_INFO.UPDATE_USER is
'更新人';

comment on column TAX_DECLARE_SUM_INFO.UPDATE_TIME is
'更新时间';

comment on column TAX_DECLARE_SUM_INFO.DECLARE_YEAR is
'年份';

/**纳税申报表 注释脚本	 add by wangzhiang  end**/
/**ADD                                             2018-10-18                                              end**/

/**ADD                                             2018-10-19                                              start**/
--修改表名 by 王之昂
drop table ACCT_LAYERING_INFO ;
drop table BOK_ACCT_LAYERING_INFO ;

/*==============================================================*/
/* Table: BOK_ACCT_LAYERING_INFO                                */
/*==============================================================*/
create table BOK_ACCT_LAYERING_INFO 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   LAYERING_TYPE        VARCHAR(50)          not null,
   LAYERING_ID          VARCHAR(32)          not null,
   F_LAYERING_ID        VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_ACCT_LAYERING_INFO primary key (BOOKSET_ID, LAYERING_TYPE, LAYERING_ID)
);

comment on table BOK_ACCT_LAYERING_INFO is
'核算分层信息';

comment on column BOK_ACCT_LAYERING_INFO.BOOKSET_ID is
'账套代码';

comment on column BOK_ACCT_LAYERING_INFO.LAYERING_TYPE is
'分层类别';

comment on column BOK_ACCT_LAYERING_INFO.LAYERING_ID is
'分层代码';

comment on column BOK_ACCT_LAYERING_INFO.F_LAYERING_ID is
'上层代码';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_USER is
'创建人';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_DEPT is
'创建部门';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_TIME is
'创建时间';

comment on column BOK_ACCT_LAYERING_INFO.UPDATE_USER is
'更新人';

comment on column BOK_ACCT_LAYERING_INFO.UPDATE_TIME is
'更新时间';

--修改表名 by 王之昂
drop table ACCT_SUBJECT_INFO ;
drop table BOK_ACCT_SUBJECT_INFO ;

/*==============================================================*/
/* Table: BOK_ACCT_SUBJECT_INFO                                 */
/*==============================================================*/
create table BOK_ACCT_SUBJECT_INFO 
(
   ACCT_SUBJECT_ID      VARCHAR(32)          not null,
   ACCT_SUBJECT_TYPE    VARCHAR(50)          not null,
   ACCT_SUBJECT_NAME    VARCHAR(200),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_ACCT_SUBJECT_INFO primary key (ACCT_SUBJECT_ID, ACCT_SUBJECT_TYPE)
);

comment on table BOK_ACCT_SUBJECT_INFO is
'核算主体信息';

comment on column BOK_ACCT_SUBJECT_INFO.ACCT_SUBJECT_ID is
'核算主体代码';

comment on column BOK_ACCT_SUBJECT_INFO.ACCT_SUBJECT_TYPE is
'核算主体类别，理财产品，投资组合、部门、银行等';

comment on column BOK_ACCT_SUBJECT_INFO.ACCT_SUBJECT_NAME is
'核算主体名称';

comment on column BOK_ACCT_SUBJECT_INFO.CREATE_USER is
'创建人';

comment on column BOK_ACCT_SUBJECT_INFO.CREATE_DEPT is
'创建部门';

comment on column BOK_ACCT_SUBJECT_INFO.CREATE_TIME is
'创建时间';

comment on column BOK_ACCT_SUBJECT_INFO.UPDATE_USER is
'更新人';

comment on column BOK_ACCT_SUBJECT_INFO.UPDATE_TIME is
'更新时间';

drop table TRD_RECLASS_FAIL_INFO ;

/*==============================================================*/
/* Table: TRD_RECLASS_FAIL_INFO                                 */
/*==============================================================*/
create table TRD_RECLASS_FAIL_INFO 
(
   SEQ_NO               VARCHAR(32)          not null,
   TRADE_ID             VARCHAR(32),
   BOOKSET_ID           VARCHAR(32),
   FINPROD_ID           VARCHAR(32),
   FINPROD_TYPE         VARCHAR(50),
   INV_AIM              DATE,
   HAPPEN_DATE          VARCHAR(50),
   REMARK               VARCHAR(500),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TRD_RECLASS_FAIL_INFO primary key (SEQ_NO)
);

comment on table TRD_RECLASS_FAIL_INFO is
'重分类未成功资产';

comment on column TRD_RECLASS_FAIL_INFO.SEQ_NO is
'业务编号';

comment on column TRD_RECLASS_FAIL_INFO.TRADE_ID is
'账套代码';

comment on column TRD_RECLASS_FAIL_INFO.BOOKSET_ID is
'账套代码';

comment on column TRD_RECLASS_FAIL_INFO.FINPROD_ID is
'金融产品代码';

comment on column TRD_RECLASS_FAIL_INFO.FINPROD_TYPE is
'金融产品类型';

comment on column TRD_RECLASS_FAIL_INFO.INV_AIM is
'投资目的';

comment on column TRD_RECLASS_FAIL_INFO.HAPPEN_DATE is
'发生日';

comment on column TRD_RECLASS_FAIL_INFO.REMARK is
'备注';

comment on column TRD_RECLASS_FAIL_INFO.CREATE_USER is
'创建人';

comment on column TRD_RECLASS_FAIL_INFO.CREATE_DEPT is
'创建部门';

comment on column TRD_RECLASS_FAIL_INFO.CREATE_TIME is
'创建时间';

comment on column TRD_RECLASS_FAIL_INFO.UPDATE_USER is
'更新人';

comment on column TRD_RECLASS_FAIL_INFO.UPDATE_TIME is
'更新时间';



/**ADD                                             2018-10-19                                              end**/

/**ADD                                             2018-10-22                                              start**/
/**标的类金融产品 修改字段：是否摊销到行权日->含权债摊销日,新增字段：到期赎回价add by chenggaoling  start**/
alter table FIN_PRODUCT drop column IS_TO_EXE_DATE;
alter table FIN_PRODUCT add EXE_DATE VARCHAR2(50);
comment on column FIN_PRODUCT.EXE_DATE is
'含权债摊销日';
alter table FIN_PRODUCT add MATE_PRICE NUMERIC(30, 14);
comment on column FIN_PRODUCT.MATE_PRICE is
'到期赎回价';
/**标的类金融产品 修改字段：是否摊销到行权日->含权债摊销日,新增字段：到期赎回价add by chenggaoling  end**/
/**ADD                                             2018-10-22                                              end**/

/**ADD                                             2018-10-23                                              START**/
/**统计分析账套汇总表添加 计提利率 字段add by wangzhiang  start**/
alter table BOK_STAT_BOK_POSITION add TDY_PROFIT_AMT NUMERIC(30, 2);
comment on column BOK_STAT_BOK_POSITION.TDY_PROFIT_AMT is
'当日应付利润';
alter table BOK_VAL_SUMMARY add TDY_PROFIT_AMT NUMERIC(30, 2);
comment on column BOK_VAL_SUMMARY.TDY_PROFIT_AMT is
'当日应付利润';
alter table BOK_STAT_BOK_POSITION add TOT_PROFIT_AMT NUMERIC(30, 2);
comment on column BOK_STAT_BOK_POSITION.TOT_PROFIT_AMT is
'累计应付利润';
alter table BOK_VAL_SUMMARY add TOT_PROFIT_AMT NUMERIC(30, 2);
comment on column BOK_VAL_SUMMARY.TOT_PROFIT_AMT is
'累计应付利润';

/** add by zhangwei 20181023 税务方案 SEQNO start **/
-- 税务方案 SEQNO
DROP SEQUENCE TAX_SEQUENCE;
CREATE SEQUENCE TAX_SEQUENCE INCREMENT BY 1 START WITH 100000  MAXVALUE 100000000 MINVALUE 1 NOCYCLE
CACHE 20 NOORDER;
/** add by zhangwei 20181023 税务方案 SEQNO  end **/

/**统计分析账套汇总表添加 计提利率 字段 add by wangzhiang  end**/
/**ADD                                             2018-10-23                                              end**/

/**ADD                                             2018-10-24                                              START**/
/**标的类金融产品扩展表添加 面值 字段 add by chengzhong  start**/
alter table FIN_PRODUCT_EXT add FACE_VALUE NUMERIC(30, 14);
comment on column FIN_PRODUCT_EXT.FACE_VALUE is
'面值';
/**标的类金融产品扩展表添加 面值 字段 add by chengzhong  end**/
/**ADD                                             2018-10-24                                         END**/
 
/**接口-委外行情-基础数据接口 增加VAL_NFUND_RATE add by huwei  2018-10-24 start**/
 drop table VAL_NFUND_RATE ;

/*==============================================================*/
/* Table: VAL_NFUND_RATE                                        */
/*==============================================================*/
create table VAL_NFUND_RATE 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   PUBLISH_DATE         DATE                 not null,
   VAL_DATE             DATE                 not null,
   VALUATION            NUMBER(30,14)        not null,
   DATA_ACTION_SIGN     VARCHAR(50)          not null
);

comment on table VAL_NFUND_RATE is
'委外(非公募基金）行情';

comment on column VAL_NFUND_RATE.FINPROD_ID is
'金融产品代码';

comment on column VAL_NFUND_RATE.FINPROD_TYPE is
'金融产品类型';

comment on column VAL_NFUND_RATE.PUBLISH_DATE is
'公布日期';

comment on column VAL_NFUND_RATE.VAL_DATE is
'估值日期';

comment on column VAL_NFUND_RATE.VALUATION is
'估值';

comment on column VAL_NFUND_RATE.DATA_ACTION_SIGN is
'数据动作标志';
/**接口-委外行情-基础数据接口 增加VAL_NFUND_RATE add by huwei 2018-10-24  start**/      

/**预期收益接口增加 金融产品类型 产品简称 字段 add by dongjun on 20181024 start**/
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME  IN 
(
'ValPrdFeeInfoC',
'ValPrdFeeBaseinfo',
'ValPrdFeeInfoN'
) AND COL_E_NAME = 'prodAbbr';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('货币型产品费用基础信息','ValPrdFeeInfoC','产品代码','prodAbbr','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','产品代码','prodAbbr','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('净值型产品费用基础信息','ValPrdFeeInfoN','产品代码','prodAbbr','1','N','50','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME  IN 
(
'ValPrdBaseInfo',
'ValPrdPayRule',
'ValPrdAccrualPlan',
'ValPrdFeeBaseinfo',
'ValPrdPayPlan',
'ValPrdRateAdjustInfo',
'ValPrdPriceAdjustInfo'
) AND COL_E_NAME = 'finprodType';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息计划','ValPrdAccrualPlan','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付费计划','ValPrdPayPlan','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValAccountInfo' AND COL_E_NAME = 'largePayno';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('资金账户信息','ValAccountInfo','大额支付系统号','largePayno','1','N','50','','1');
/**预期收益接口增加 金融产品类型 产品简称 字段 add by dongjun on 20181024 end**/

/**ADD                                             2018-10-25                                              START**/
/** 债券利率调整信息添加计息基础、利率类型字段 add by dongjun on 20181025 end**/
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValSecRateAdjust' AND COL_E_NAME 
IN ('basis','intType');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券利率调整信息','ValSecRateAdjust','计息基础','basis','1','Y','50','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券利率调整信息','ValSecRateAdjust','利率类型','intType','1','Y','50','INT_TYPE','1');
/** 债券利率调整信息添加计息基础、利率类型字段 add by dongjun on 20181025 end**/


/**文档信息  BOK_VALBAL_TABLE_STATUS add by weipeng  2018/10/25 start**/
alter table BOK_VAL_TABLE_DATA add BUSI_ID VARCHAR2(32);
comment on column BOK_VAL_TABLE_DATA.BUSI_ID is
'分层代码';
/**文档信息  BOK_VALBAL_TABLE_STATUS add by weipeng  2018/10/25 end**/


drop table BOK_SUMMARY_TABLE ;
drop table BOK_STAT_DET_POSIITON ;

/*==============================================================*/
/* Table: BOK_STAT_DET_POSIITON                                 */
/*==============================================================*/
create table BOK_STAT_DET_POSIITON 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   HAPPEN_DATE          DATE                 not null,
   BOOK_DATE            DATE                 not null,
   BOK_DETAIL_ID        VARCHAR(32)          not null,
   BOOK_SUMMARY_ORDER   NUMERIC(20)          not null,
   BOOKSET_DATE         DATE,
   POS_AMT              NUMERIC(30, 2),
   INT_RATE             NUMERIC(30, 14),
   DAILYDSC_YIELD       NUMERIC(30, 14),
   TDY_INTINCEXP_ADD    NUMERIC(30, 2),
   TDY_INTINCEXP        NUMERIC(30, 2),
   FINPROD_TYPE         VARCHAR(50),
   VDATE                DATE,
   MDATE                DATE,
   CHL_ID               VARCHAR(32),
   INV_AIM              VARCHAR(50),
   TDY_POSITION         NUMERIC(30, 14),
   TDY_COST_AMT         NUMERIC(30, 2),
   TDY_FLOAT_INGPL      NUMERIC(30, 2),
   END_DAYS_1           NUMERIC(20),
   END_DAYS_2           NUMERIC(20),
   END_DAYS_COST_AMT    NUMERIC(30, 14),
   BUSI_ID              VARCHAR(32),
   VAL_DATE             DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_STAT_DET_POSIITON primary key (BOOKSET_ID, HAPPEN_DATE, BOOK_DATE, BOK_DETAIL_ID, BOOK_SUMMARY_ORDER)
);

comment on table BOK_STAT_DET_POSIITON is
'统计分析明细表（账务明细）';

comment on column BOK_STAT_DET_POSIITON.BOOKSET_ID is
'账套代码';

comment on column BOK_STAT_DET_POSIITON.HAPPEN_DATE is
'发生日期';

comment on column BOK_STAT_DET_POSIITON.BOOK_DATE is
'入账日期';

comment on column BOK_STAT_DET_POSIITON.BOK_DETAIL_ID is
'账务明细代码';

comment on column BOK_STAT_DET_POSIITON.BOOK_SUMMARY_ORDER is
'处理序列';

comment on column BOK_STAT_DET_POSIITON.BOOKSET_DATE is
'账套日期';

comment on column BOK_STAT_DET_POSIITON.POS_AMT is
'当前持仓';

comment on column BOK_STAT_DET_POSIITON.INT_RATE is
'计提利率';

comment on column BOK_STAT_DET_POSIITON.DAILYDSC_YIELD is
'日摊销收益率';

comment on column BOK_STAT_DET_POSIITON.TDY_INTINCEXP_ADD is
'当日发生应计利息';

comment on column BOK_STAT_DET_POSIITON.TDY_INTINCEXP is
'当日累计应计利息余额';

comment on column BOK_STAT_DET_POSIITON.FINPROD_TYPE is
'金融产品类型';

comment on column BOK_STAT_DET_POSIITON.VDATE is
'起息日';

comment on column BOK_STAT_DET_POSIITON.MDATE is
'到期日';

comment on column BOK_STAT_DET_POSIITON.CHL_ID is
'通道代码';

comment on column BOK_STAT_DET_POSIITON.INV_AIM is
'投资目的';

comment on column BOK_STAT_DET_POSIITON.TDY_POSITION is
'当日计提份额';

comment on column BOK_STAT_DET_POSIITON.TDY_COST_AMT is
'当日成本';

comment on column BOK_STAT_DET_POSIITON.TDY_FLOAT_INGPL is
'当日公允价值变动';

comment on column BOK_STAT_DET_POSIITON.END_DAYS_1 is
'剩余期限';

comment on column BOK_STAT_DET_POSIITON.END_DAYS_2 is
'剩余存续期限';

comment on column BOK_STAT_DET_POSIITON.END_DAYS_COST_AMT is
'剩余期限用成本';

comment on column BOK_STAT_DET_POSIITON.BUSI_ID is
'业务明细代码';

comment on column BOK_STAT_DET_POSIITON.VAL_DATE is
'行情日期';

comment on column BOK_STAT_DET_POSIITON.CREATE_USER is
'创建人';

comment on column BOK_STAT_DET_POSIITON.CREATE_DEPT is
'创建部门';

comment on column BOK_STAT_DET_POSIITON.CREATE_TIME is
'创建时间';

comment on column BOK_STAT_DET_POSIITON.UPDATE_USER is
'更新人';

comment on column BOK_STAT_DET_POSIITON.UPDATE_TIME is
'更新时间';

/**接口-存款类资产-基础信息接口  add by wangqidong 2018-10-25  start**/ 
drop table VAL_BOT_AST_INFO ;

/*==============================================================*/
/* Table: VAL_BOT_AST_INFO                                      */
/*==============================================================*/
create table VAL_BOT_AST_INFO 
(
   ASSET_ID             VARCHAR(32)          not null,
   ASSET_NAME           VARCHAR(50)          not null,
   DATA_ACTION_SIGN     VARCHAR(50)          not null,
   PRIN_AGENT           VARCHAR(50)          not null,
   CCY                  VARCHAR(50)          not null,
   ASSET_CLASS          VARCHAR(50),
   PRIN_TYPE            VARCHAR(50)          not null,
   VAL_CLAS             VARCHAR(50)          not null,
   CAPITAL              NUMERIC(30, 2)       not null,
   VDATE                DATE                 not null,
   MDATE                DATE                 not null,
   IN_OUT               VARCHAR(50)          not null,
   TRADE_MARKET         VARCHAR(50)          not null,
   CAPI_PROF_FEATURE    VARCHAR(50)          not null,
   BASIS                VARCHAR(50)          not null,  
   RATE_TYPE            VARCHAR(50)          not null,
   INIT_RATE            NUMERIC(30, 14)      not null,
   LINK_RATE            VARCHAR(50),
   RATIO                NUMERIC(30, 14), 
   RATE_MAX             NUMERIC(30, 14),
   RATE_MIN             NUMERIC(30, 14),
   INT_RATE_ID          VARCHAR(32),
   SPREAD_RATE          NUMERIC(30, 14)
);

comment on table VAL_BOT_AST_INFO is
'存款类资产（映射资管的固收类资产）定义基础信息';

comment on column VAL_BOT_AST_INFO.ASSET_ID is
'资产代码';

comment on column VAL_BOT_AST_INFO.ASSET_NAME is
'资产名称';

comment on column VAL_BOT_AST_INFO.DATA_ACTION_SIGN is
'新增、修改、删除';

comment on column VAL_BOT_AST_INFO.PRIN_AGENT is
'资产发行方';

comment on column VAL_BOT_AST_INFO.CCY is
'币种';

comment on column VAL_BOT_AST_INFO.ASSET_CLASS is
'资产类型';

comment on column VAL_BOT_AST_INFO.PRIN_TYPE is
'存款类型';

comment on column VAL_BOT_AST_INFO.VAL_CLAS is
'核算分类';

comment on column VAL_BOT_AST_INFO.CAPITAL is
'本金金额';

comment on column VAL_BOT_AST_INFO.VDATE is
'起息日';

comment on column VAL_BOT_AST_INFO.MDATE is
'到期日';

comment on column VAL_BOT_AST_INFO.IN_OUT is
'境内外';

comment on column VAL_BOT_AST_INFO.TRADE_MARKET is
'交易场所';

comment on column VAL_BOT_AST_INFO.CAPI_PROF_FEATURE is
'本金收益特征';

comment on column VAL_BOT_AST_INFO.BASIS is
'计息基础';

comment on column VAL_BOT_AST_INFO.RATE_TYPE is
'利率类型';

comment on column VAL_BOT_AST_INFO.INIT_RATE is
'初始利率';

comment on column VAL_BOT_AST_INFO.LINK_RATE is
'挂钩利率';

comment on column VAL_BOT_AST_INFO.RATIO is
'系数';

comment on column VAL_BOT_AST_INFO.RATE_MAX is
'利率上限';

comment on column VAL_BOT_AST_INFO.RATE_MIN is
'利率下限';

comment on column VAL_BOT_AST_INFO.INT_RATE_ID is
'浮动利率编号';

comment on column VAL_BOT_AST_INFO.SPREAD_RATE is
'利差';
/**接口-存款类资产-基础信息接口  add by wangqidong 2018-10-25  end**/

/**接口-存款类资产-还本计划接口  add by wangqidong 2018-10-25  start**/
drop table VAL_BOT_AST_CAP_PLAN ;

-- Create table
create table VAL_BOT_AST_CAP_PLAN
(
  asset_id         VARCHAR2(32) not null,
  order_no         NUMBER(30,4) not null,
  data_action_sign VARCHAR2(50) not null,
  repay_date       DATE not null,
  pay_date         DATE not null,
  repay_amt        NUMBER(30,14) not null,
  int_amt          NUMBER(30,14) not null
);
-- Add comments to the table 
comment on table VAL_BOT_AST_CAP_PLAN
  is '存款类资产还本计划';
-- Add comments to the columns 
comment on column VAL_BOT_AST_CAP_PLAN.asset_id
  is '资产代码';
comment on column VAL_BOT_AST_CAP_PLAN.order_no
  is '现金流序号';
comment on column VAL_BOT_AST_CAP_PLAN.data_action_sign
  is '数据动作标志';
comment on column VAL_BOT_AST_CAP_PLAN.repay_date
  is '还本日';
comment on column VAL_BOT_AST_CAP_PLAN.pay_date
  is '支付日';
comment on column VAL_BOT_AST_CAP_PLAN.repay_amt
  is '还本金额';
comment on column VAL_BOT_AST_CAP_PLAN.int_amt
  is '利息金额';
-- Create/Recreate primary, unique and foreign key constraints 
alter table VAL_BOT_AST_CAP_PLAN
  add constraint VAL_BOT_AST_CAP_PLAN primary key (ASSET_ID);

/**接口-存款类资产-还本计划接口  add by wangqidong 2018-10-25  end**/

/**接口-存款类资产-还本付息规则接口  add by wangqidong 2018-10-25  start**/
drop table VAL_BOT_AST_REPAY_CAP_INT ;

-- Create table
create table VAL_BOT_AST_REPAY_CAP_INT
(
  asset_id         VARCHAR2(32) not null,
  data_action_sign VARCHAR2(50) not null,
  deal_type        VARCHAR2(50) not null,
  eff_date         DATE not null,
  is_int           VARCHAR2(50) not null,
  pay_cycle        VARCHAR2(50),
  cap_cycle        VARCHAR2(50),
  tday_rule        VARCHAR2(50) not null,
  calc_rule        VARCHAR2(50) not null,
  first_pay_date   DATE
);
-- Add comments to the table 
comment on table VAL_BOT_AST_REPAY_CAP_INT
  is '存款类资产还本付息规则';
-- Add comments to the columns 
comment on column VAL_BOT_AST_REPAY_CAP_INT.asset_id
  is '资产代码';
comment on column VAL_BOT_AST_REPAY_CAP_INT.data_action_sign
  is '数据动作标志';
comment on column VAL_BOT_AST_REPAY_CAP_INT.deal_type
  is '交易类型';
comment on column VAL_BOT_AST_REPAY_CAP_INT.eff_date
  is '生效日';
comment on column VAL_BOT_AST_REPAY_CAP_INT.is_int
  is '还本是否带息';
comment on column VAL_BOT_AST_REPAY_CAP_INT.pay_cycle
  is '付息频率';
comment on column VAL_BOT_AST_REPAY_CAP_INT.cap_cycle
  is '还本频率';
comment on column VAL_BOT_AST_REPAY_CAP_INT.tday_rule
  is '营业日准则';
comment on column VAL_BOT_AST_REPAY_CAP_INT.calc_rule
  is '推算方式';
comment on column VAL_BOT_AST_REPAY_CAP_INT.first_pay_date
  is '首次付息日';
-- Create/Recreate primary, unique and foreign key constraints 
alter table VAL_BOT_AST_REPAY_CAP_INT
  add constraint VAL_BOT_AST_REPAY_CAP_INT primary key (ASSET_ID);
/**接口-存款类资产-还本付息规则接口  add by wangqidong 2018-10-25  end**/

/**ADD                                             2018-10-25                                              END**/

/**ADD                                             2018-10-26                                              START**/
/**账务明细代码表增加 分支序号 字段 add by wangzhiang start**/
alter table BOK_DETAIL_ID add BRANCH NUMERIC(10) default '0' not null;
comment on column BOK_DETAIL_ID.BRANCH is
'分支序号，无分支序号的存0';
/**账务明细代码表增加 分支序号 字段  add by wangzhiang end**/

/**核算分层信息表重建 add by wangzhiang start**/
drop table BOK_ACCT_LAYERING_INFO ;

/*==============================================================*/
/* Table: BOK_ACCT_LAYERING_INFO                                */
/*==============================================================*/
create table BOK_ACCT_LAYERING_INFO 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   LAYERING_ID          VARCHAR(60)          not null,
   BOK_DETAIL_ID        VARCHAR(32),
   BUSI_ID              VARCHAR(32),
   BUSI_DETAIL_TYPE     VARCHAR(50)          not null,
   BRANCH               NUMERIC(10),
   LEV                  NUMERIC(10),
   CUR_LEV              NUMERIC(10),
   S_BRANCH             NUMERIC(10),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_ACCT_LAYERING_INFO primary key (BOOKSET_ID, LAYERING_ID, BUSI_DETAIL_TYPE)
);

comment on table BOK_ACCT_LAYERING_INFO is
'核算分层信息';

comment on column BOK_ACCT_LAYERING_INFO.BOOKSET_ID is
'账套代码';

comment on column BOK_ACCT_LAYERING_INFO.LAYERING_ID is
'分层代码';

comment on column BOK_ACCT_LAYERING_INFO.BOK_DETAIL_ID is
'账务明细代码';

comment on column BOK_ACCT_LAYERING_INFO.BUSI_ID is
'业务明细代码';

comment on column BOK_ACCT_LAYERING_INFO.BUSI_DETAIL_TYPE is
'业务明细类型';

comment on column BOK_ACCT_LAYERING_INFO.BRANCH is
'分支序号';

comment on column BOK_ACCT_LAYERING_INFO.LEV is
'级别序号';

comment on column BOK_ACCT_LAYERING_INFO.CUR_LEV is
'当前级别';

comment on column BOK_ACCT_LAYERING_INFO.S_BRANCH is
'下级最新序号';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_USER is
'创建人';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_DEPT is
'创建部门';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_TIME is
'创建时间';

comment on column BOK_ACCT_LAYERING_INFO.UPDATE_USER is
'更新人';

comment on column BOK_ACCT_LAYERING_INFO.UPDATE_TIME is
'更新时间';

/**核算分层信息表重建 add by wangzhiang end**/

/**ADD                                             2018-10-26                                              END**/


/**净值下发附件上传下载 add by ruanhongming start**/
drop table BOK_FILE_TRANSFER ;

/*==============================================================*/
/* Table: BOK_FILE_TRANSFER                                       */
/*==============================================================*/
-- Create table
create table BOK_FILE_TRANSFER
(
  seq_no       VARCHAR2(32) not null,
  finprod_id   VARCHAR2(32) not null,
  finprod_name VARCHAR2(500),
  val_date     TIMESTAMP(6) not null,
  val_bal_flag VARCHAR2(50) not null,
  file_name    VARCHAR2(50) not null,
  file_path    VARCHAR2(50) not null,
  upload_date  TIMESTAMP(6) not null,
  create_user  VARCHAR2(20) not null,
  create_dept  VARCHAR2(32),
  create_time  TIMESTAMP(6) not null,
  update_user  VARCHAR2(20) not null,
  update_time  TIMESTAMP(6) not null,
  constraint BOK_FILE_TRANSFER primary key (SEQ_NO)
);
comment on table BOK_FILE_TRANSFER  is '净值下发附件上传下载';

comment on column BOK_FILE_TRANSFER.seq_no
  is 'uuid';
comment on column BOK_FILE_TRANSFER.finprod_id
  is '产品代码';
comment on column BOK_FILE_TRANSFER.finprod_name
  is '产品名称';
comment on column BOK_FILE_TRANSFER.val_date
  is '估值日期';
comment on column BOK_FILE_TRANSFER.val_bal_flag
  is '估值表余额表标志';
comment on column BOK_FILE_TRANSFER.file_name
  is '附件名称';
comment on column BOK_FILE_TRANSFER.file_path
  is '附件路径';
comment on column BOK_FILE_TRANSFER.upload_date
  is '上传时间';
comment on column BOK_FILE_TRANSFER.create_user
  is '创建人';
comment on column BOK_FILE_TRANSFER.create_dept
  is '创建部门';
comment on column BOK_FILE_TRANSFER.create_time
  is '创建时间';
comment on column BOK_FILE_TRANSFER.update_user
  is '更新人';
comment on column BOK_FILE_TRANSFER.update_time
  is '更新时间';
/**净值下发附件上传下载 add by ruanhongming end**/

/**ADD                                             2018-10-26                                              END**/

  
/**ADD                                             2018-10-28                                              START**/
--修改表表结构 by wujie
drop table BOK_ACCT_LAYERING_INFO ;

/*==============================================================*/
/* Table: BOK_ACCT_LAYERING_INFO                                */
/*==============================================================*/
create table BOK_ACCT_LAYERING_INFO 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   LAYERING_ID          VARCHAR(60)          not null,
   F_LAYERING_ID        VARCHAR(60),
   BOK_DETAIL_ID        VARCHAR(32),
   BUSI_ID              VARCHAR(32),
   BUSI_DETAIL_TYPE     VARCHAR(50)          not null,
   BRANCH               NUMERIC(10),
   LEV                  NUMERIC(10),
   CUR_LEV              VARCHAR(200),
   S_BRANCH             NUMERIC(10),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_ACCT_LAYERING_INFO primary key (BOOKSET_ID, LAYERING_ID, BUSI_DETAIL_TYPE)
);

comment on table BOK_ACCT_LAYERING_INFO is
'核算分层信息';

comment on column BOK_ACCT_LAYERING_INFO.BOOKSET_ID is
'账套代码';

comment on column BOK_ACCT_LAYERING_INFO.LAYERING_ID is
'分层代码';

comment on column BOK_ACCT_LAYERING_INFO.F_LAYERING_ID is
'上层代码';

comment on column BOK_ACCT_LAYERING_INFO.BOK_DETAIL_ID is
'账务明细代码';

comment on column BOK_ACCT_LAYERING_INFO.BUSI_ID is
'业务明细代码';

comment on column BOK_ACCT_LAYERING_INFO.BUSI_DETAIL_TYPE is
'业务明细类型';

comment on column BOK_ACCT_LAYERING_INFO.BRANCH is
'分支序号';

comment on column BOK_ACCT_LAYERING_INFO.LEV is
'级别序号';

comment on column BOK_ACCT_LAYERING_INFO.CUR_LEV is
'当前级别';

comment on column BOK_ACCT_LAYERING_INFO.S_BRANCH is
'下级最新序号';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_USER is
'创建人';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_DEPT is
'创建部门';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_TIME is
'创建时间';

comment on column BOK_ACCT_LAYERING_INFO.UPDATE_USER is
'更新人';

comment on column BOK_ACCT_LAYERING_INFO.UPDATE_TIME is
'更新时间';


/**ADD                                             2018-10-28                                              END**/

/**ADD                                             2018-10-29                                              start**/
--历史表增加索引凭证编号
drop index "Index_20";
/*==============================================================*/
/* Index: "Index_20"                                            */
/*==============================================================*/
create index "Index_20" on BOK_BOOKDETAIL_ALL (
   BOOK_ID ASC
);
--当前表增加索引凭证编号
drop index "Index_21";
/*==============================================================*/
/* Index: "Index_21"                                            */
/*==============================================================*/
create index "Index_21" on BOK_BOOKDETAIL_CURRENT (
   BOOK_ID ASC
);

/**文档信息  BOK_FILE_INFO add by lishicheng  2018/10/29 begin**/
drop table BOK_DOC_INFO ;
drop table BOK_FILE_INFO ;

/*==============================================================*/
/* Table: BOK_FILE_INFO                                         */
/*==============================================================*/
create table BOK_FILE_INFO 
(
   FILE_ID              VARCHAR(32)          not null,
   FILE_TYPE            VARCHAR(50),
   FILE_NAME            VARCHAR(300),
   FILE_PATH            VARCHAR(500),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_FILE_INFO primary key (FILE_ID)
);

comment on table BOK_FILE_INFO is
'文件信息';

comment on column BOK_FILE_INFO.FILE_ID is
'文件ID';

comment on column BOK_FILE_INFO.FILE_TYPE is
'文件类型';

comment on column BOK_FILE_INFO.FILE_NAME is
'文件名称';

comment on column BOK_FILE_INFO.FILE_PATH is
'文件路径';

comment on column BOK_FILE_INFO.CREATE_USER is
'创建人';

comment on column BOK_FILE_INFO.CREATE_DEPT is
'创建部门';

comment on column BOK_FILE_INFO.CREATE_TIME is
'创建时间';

comment on column BOK_FILE_INFO.UPDATE_USER is
'更新人';

comment on column BOK_FILE_INFO.UPDATE_TIME is
'更新时间';

/**文档信息  BOK_FILE_INFO add by lishicheng  2018/10/25 end**/


/**账务明细代码表分支序号字段可以为空 add by wujie start**/
alter table BOK_DETAIL_ID modify branch null;
/**账务明细代码表分支序号字段可以为空 add by wujie end**/



/**ADD                                             2018-10-29                                              end**/

/**ADD                                             2018-10-30                                              start**/
/**统计分析账套汇总表 增加了 [是否子产品]  [分层代码] 字段 add by wangzhiang start**/
alter table BOK_STAT_BOK_POSITION add IS_SUB_PRD VARCHAR2(50);
comment on column BOK_STAT_BOK_POSITION.IS_SUB_PRD is
'是否子产品';
alter table BOK_STAT_BOK_POSITION add LAYERING_ID VARCHAR2(60);
comment on column BOK_STAT_BOK_POSITION.LAYERING_ID is
'分层代码，子产品存分层代码，母产品存核算主体代码';
/**统计分析账套汇总表 增加了 [是否子产品]  [分层代码] 字段 add by wangzhiang end**/

/**增加 净值确认附件信息 表 add by wangzhiang start**/
drop table BOK_NETVALUE_CFM_FILE ;

/*==============================================================*/
/* Table: BOK_NETVALUE_CFM_FILE                                 */
/*==============================================================*/
create table BOK_NETVALUE_CFM_FILE 
(
   FINPROD_ID           VARCHAR(32)          not null,
   VAL_DATE             DATE                 not null,
   VAL_BAL_FLAG         VARCHAR(50)          not null,
   FILE_ID              VARCHAR(32)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_NETVALUE_CFM_FILE primary key (FINPROD_ID, VAL_DATE, VAL_BAL_FLAG, FILE_ID)
)
partition by list
 (VAL_DATE)
 (partition BOOKSET_ID_DEFAULT
 values (DEFAULT));

comment on table BOK_NETVALUE_CFM_FILE is
'估值表余额表附件信息';

comment on column BOK_NETVALUE_CFM_FILE.FINPROD_ID is
'产品代码';

comment on column BOK_NETVALUE_CFM_FILE.VAL_DATE is
'估值日期';

comment on column BOK_NETVALUE_CFM_FILE.VAL_BAL_FLAG is
'估值表余额表标志';

comment on column BOK_NETVALUE_CFM_FILE.FILE_ID is
'文件ID';

comment on column BOK_NETVALUE_CFM_FILE.CREATE_USER is
'创建人';

comment on column BOK_NETVALUE_CFM_FILE.CREATE_DEPT is
'创建部门';

comment on column BOK_NETVALUE_CFM_FILE.CREATE_TIME is
'创建时间';

comment on column BOK_NETVALUE_CFM_FILE.UPDATE_USER is
'更新人';

comment on column BOK_NETVALUE_CFM_FILE.UPDATE_TIME is
'更新时间';

/**增加 净值确认附件信息 表 add by wangzhiang end**/
/**ADD                                             2018-10-30                                              end**/

/**ADD                                             2018-11-01                                              start**/
/**add by qujianzhong 表的类金融产品扩展表加字段：面值 start**/
alter table FIN_PRODUCT_EXT add price NUMBER(30,14);
comment on column FIN_PRODUCT_EXT.price is '面值';
/**add by qujianzhong 表的类金融产品扩展表加字段：面值 end**/

--核算分层表便跟-wanggengyun
drop table BOK_ACCT_LAYERING_INFO ;

/*==============================================================*/
/* Table: BOK_ACCT_LAYERING_INFO                                */
/*==============================================================*/
create table BOK_ACCT_LAYERING_INFO 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   LAYERING_ID          VARCHAR(60)          not null,
   F_LAYERING_ID        VARCHAR(60),
   BOK_DETAIL_ID        VARCHAR(32),
   BUSI_ID              VARCHAR(32),
   BUSI_DETAIL_TYPE     VARCHAR(50)          not null,
   VDATE                DATE,
   MDATE                DATE,
   CALENDAR_ID          VARCHAR(32),
   BRANCH               NUMERIC(10),
   LEV                  VARCHAR(200),
   CUR_LEV              NUMERIC(10),
   S_BRANCH             NUMERIC(10),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_ACCT_LAYERING_INFO primary key (BOOKSET_ID, LAYERING_ID, BUSI_DETAIL_TYPE)
);

comment on table BOK_ACCT_LAYERING_INFO is
'核算分层信息';

comment on column BOK_ACCT_LAYERING_INFO.BOOKSET_ID is'账套代码';

comment on column BOK_ACCT_LAYERING_INFO.LAYERING_ID is'分层代码';

comment on column BOK_ACCT_LAYERING_INFO.F_LAYERING_ID is'上层代码';

comment on column BOK_ACCT_LAYERING_INFO.BOK_DETAIL_ID is'账务明细代码';

comment on column BOK_ACCT_LAYERING_INFO.BUSI_ID is'业务明细代码';

comment on column BOK_ACCT_LAYERING_INFO.BUSI_DETAIL_TYPE is'业务明细类型';

comment on column BOK_ACCT_LAYERING_INFO.VDATE is'起息日';

comment on column BOK_ACCT_LAYERING_INFO.MDATE is'到期日';

comment on column BOK_ACCT_LAYERING_INFO.CALENDAR_ID is'交易日历';

comment on column BOK_ACCT_LAYERING_INFO.BRANCH is'分支序号';

comment on column BOK_ACCT_LAYERING_INFO.LEV is'级别序号';

comment on column BOK_ACCT_LAYERING_INFO.CUR_LEV is'当前级别';

comment on column BOK_ACCT_LAYERING_INFO.S_BRANCH is'下级最新序号';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_USER is'创建人';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_DEPT is'创建部门';

comment on column BOK_ACCT_LAYERING_INFO.CREATE_TIME is'创建时间';

comment on column BOK_ACCT_LAYERING_INFO.UPDATE_USER is'更新人';

comment on column BOK_ACCT_LAYERING_INFO.UPDATE_TIME is'更新时间';


/**ADD                                             2018-11-01                                              end**/
/**ADD                                             2018-11-05                                              begin**/
--账务明细代码，核算分层信息表分支序号字段 默认成0
update BOK_ACCT_LAYERING_INFO a set a.branch=0 where a.branch is null;
update BOK_DETAIL_ID a set a.branch=0 where a.branch is null;
alter table BOK_ACCT_LAYERING_INFO modify branch default 0 not null;
alter table BOK_DETAIL_ID modify branch default 0 not null;
/**ADD                                             2018-11-05                                              end**/

/**ADD                                             2018-11-07                                              begin**/
/**ADD by chengzhong for 帐套资产负债信息表增加分层代码为主键     2018-11-07  begin**/
alter table BOK_STAT_BOK_POSITION drop constraint PK_BOK_STAT_BOK_POSITION;
DROP INDEX PK_BOK_STAT_BOK_POSITION;
update BOK_STAT_BOK_POSITION set LAYERING_ID = BOOKSET_ID where LAYERING_ID is null;
alter table BOK_STAT_BOK_POSITION add constraint PK_BOK_STAT_BOK_POSITION primary key(BOOKSET_ID, HAPPEN_DATE, BOOK_DATE, LAYERING_ID);
/**add by dongxiangyang 创建表批处理检查日志表 start**/
drop table BOK_CHECK_LOG;
create table BOK_CHECK_LOG(
SEQ_NO     VARCHAR(32),
TASK_ID   VARCHAR(32),
BOOKSET_ID  VARCHAR(32),
HAPPEN_DATE  DATE,
BOOK_DATE   DATE,
BOOKSET_DATE  DATE,
CHECK_DATE  DATE,
LOG_INFO    CLOB,
CHECK_TYPE  VARCHAR(32)
);
comment on column BOK_CHECK_LOG.SEQ_NO is
'日志信息ID';
comment on column BOK_CHECK_LOG.TASK_ID is
'任务代码';
comment on column BOK_CHECK_LOG.BOOKSET_ID is
'账套代码';
comment on column BOK_CHECK_LOG.HAPPEN_DATE is
'发生日';
comment on column BOK_CHECK_LOG.BOOK_DATE is
'入账日';
comment on column BOK_CHECK_LOG.BOOKSET_DATE is
'账套日期';
comment on column BOK_CHECK_LOG.CHECK_DATE is
'检查日期';
comment on column BOK_CHECK_LOG.LOG_INFO is
'日志信息';
comment on column BOK_CHECK_LOG.CHECK_TYPE is
'批检类型';  
/**add by dongxiangyang 创建表批处理检查日志表 end**/
/**ADD                                             2018-11-07 **/

/**ADD                                             2018-11-09                                              start**/
/**add by daiyue 新增TAX_TURNED_BALANCE（增值税待抵扣增值额）表  start**/
drop table TAX_TURNED_BALANCE cascade constraints;

/*==============================================================*/
/* Table: TAX_TURNED_BALANCE                                    */
/*==============================================================*/
create table TAX_TURNED_BALANCE 
(
   SEQ_NO               VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   BOK_DETAIL_ID        VARCHAR(32),
   O_CCY                VARCHAR(50),
   CB_AMT               NUMERIC(30, 4),
   INCOME_AMT           NUMERIC(30, 4),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_TURNED_BALANCE primary key (SEQ_NO)
);

comment on table TAX_TURNED_BALANCE is
'增值税待抵扣增值额';

comment on column TAX_TURNED_BALANCE.SEQ_NO is
'流水号';

comment on column TAX_TURNED_BALANCE.BOOKSET_ID is
'账套代码';

comment on column TAX_TURNED_BALANCE.BOK_DETAIL_ID is
'账务明细代码';

comment on column TAX_TURNED_BALANCE.O_CCY is
'币种';

comment on column TAX_TURNED_BALANCE.CB_AMT is
'待抵扣资产份额';

comment on column TAX_TURNED_BALANCE.INCOME_AMT is
'待抵扣增值额';

comment on column TAX_TURNED_BALANCE.CREATE_USER is
'创建人';

comment on column TAX_TURNED_BALANCE.CREATE_DEPT is
'创建部门';

comment on column TAX_TURNED_BALANCE.CREATE_TIME is
'创建时间';

comment on column TAX_TURNED_BALANCE.UPDATE_USER is
'更新人';

comment on column TAX_TURNED_BALANCE.UPDATE_TIME is
'更新时间';

/**add by daiyue 新增TAX_TURNED_BALANCE（增值税待抵扣增值额）表 end**/

/**add by daiyue 新增TAX_DEDUCTED_BALANCE(增值税已抵扣增值额) 表  start**/
drop table TAX_DEDUCTED_BALANCE cascade constraints;

/*==============================================================*/
/* Table: TAX_DEDUCTED_BALANCE                                  */
/*==============================================================*/
create table TAX_DEDUCTED_BALANCE 
(
   SEQ_NO               VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32),
   BOOKSET_DATE         DATE,
   BOK_SEQNO            VARCHAR(32),
   BOK_DETAIL_ID        VARCHAR(32),
   HAPPEN_DATE          DATE,
   BOOK_DATE            DATE,
   BOOK_ID              VARCHAR(32),
   BOOK_ORDER           NUMERIC(20),
   O_CCY                VARCHAR(50),
   CB_AMT               NUMERIC(30, 2),
   CB_BALANCE           NUMERIC(30, 2),
   TAX_AMT              NUMERIC(30, 2),
   BAL_AMT              NUMERIC(30, 2),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TAX_DEDUCTED_BALANCE primary key (SEQ_NO)
);

comment on table TAX_DEDUCTED_BALANCE is
'增值税已抵扣增值额';

comment on column TAX_DEDUCTED_BALANCE.SEQ_NO is
'流水号';

comment on column TAX_DEDUCTED_BALANCE.BOOKSET_ID is
'账套代码';

comment on column TAX_DEDUCTED_BALANCE.BOOKSET_DATE is
'账套日期';

comment on column TAX_DEDUCTED_BALANCE.BOK_SEQNO is
'账务明细流水';

comment on column TAX_DEDUCTED_BALANCE.BOK_DETAIL_ID is
'账务明细代码';

comment on column TAX_DEDUCTED_BALANCE.HAPPEN_DATE is
'发生日期';

comment on column TAX_DEDUCTED_BALANCE.BOOK_DATE is
'入账日期';

comment on column TAX_DEDUCTED_BALANCE.BOOK_ID is
'凭证编号';

comment on column TAX_DEDUCTED_BALANCE.BOOK_ORDER is
'处理序列';

comment on column TAX_DEDUCTED_BALANCE.O_CCY is
'币种';

comment on column TAX_DEDUCTED_BALANCE.CB_AMT is
'待抵扣资产份额发生额';

comment on column TAX_DEDUCTED_BALANCE.CB_BALANCE is
'待抵扣资产份额余额';

comment on column TAX_DEDUCTED_BALANCE.TAX_AMT is
'待抵扣增值额发生额';

comment on column TAX_DEDUCTED_BALANCE.BAL_AMT is
'待抵扣增值税余额';

comment on column TAX_DEDUCTED_BALANCE.CREATE_USER is
'创建人';

comment on column TAX_DEDUCTED_BALANCE.CREATE_DEPT is
'创建部门';

comment on column TAX_DEDUCTED_BALANCE.CREATE_TIME is
'创建时间';

comment on column TAX_DEDUCTED_BALANCE.UPDATE_USER is
'更新人';

comment on column TAX_DEDUCTED_BALANCE.UPDATE_TIME is
'更新时间';

/**add by daiyue 新增TAX_DEDUCTED_BALANCE(增值税已抵扣增值额) 表 end**/

/**add by daiyue 核算分层信息表增加 分层名称 字段  start**/
alter table BOK_ACCT_LAYERING_INFO add LAYERING_NAME VARCHAR2(200);
comment on column BOK_ACCT_LAYERING_INFO.LAYERING_NAME is
'分层名称';
/**add by daiyue 核算分层信息表增加 分层名称 字段 end**/

/**add by chumi 手工凭证交易表增加 分支序号 字段  start**/
alter table TRD_VOUCH_DEAL add BRANCH NUMERIC(10);
comment on column TRD_VOUCH_DEAL.BRANCH is
'分支序号';
/**add by chumi 手工凭证交易表增加 分支序号 字段  end**/
/**ADD                                             2018-11-09                                              end**/

/**ADD                                             2018-11-12                                              start**/
/**add by caoguo 结算指令表增加 指令状态、原指令编号 字段  start**/
alter table STL_ORDERS add STATUS VARCHAR2(50);
comment on column STL_ORDERS.STATUS is
'指令状态，初始、撤销';
alter table STL_ORDERS add P_ORDER_ID VARCHAR2(32);
comment on column STL_ORDERS.P_ORDER_ID is
'原指令编号，撤销时存被撤销的指令编号';
/**add by caoguo 结算指令表增加 指令状态、原指令编号 字段   end**/

/**ADD                                             2018-11-12                                              end**/
/**ADD                                             2018-11-13                                              start**/

--added by wangzhiang 20181113 新增影子定价方案 start
drop table BOK_SHADOW_PRICE_DETAIL cascade constraints;

/*==============================================================*/
/* Table: BOK_SHADOW_PRICE_DETAIL                               */
/*==============================================================*/
create table BOK_SHADOW_PRICE_DETAIL 
(
   SCHEME_DETAIL_ID     VARCHAR(32)          not null,
   SCHEME_ID            VARCHAR(32)          not null,
   TRADE_MARKET         VARCHAR(50),
   SEC_TYPE             VARCHAR(50),
   SEC_DETAIL_TYPE      VARCHAR(50),
   VALUATION_PRICE      VARCHAR(50),
   VALUATION_PRICE_2    VARCHAR(50),
   QUOTA_DIGIT          NUMERIC(10),
   QUOTA_DEC_TYPE       VARCHAR(50),
   VALUATION_DATE       VARCHAR(50),
   VDATE                DATE,
   MDATE                DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SHADOW_PRICE_DETAIL primary key (SCHEME_DETAIL_ID)
);

comment on table BOK_SHADOW_PRICE_DETAIL is
'影子定价方案明细';

comment on column BOK_SHADOW_PRICE_DETAIL.SCHEME_DETAIL_ID is
'方案明细代码';

comment on column BOK_SHADOW_PRICE_DETAIL.SCHEME_ID is
'方案代码';

comment on column BOK_SHADOW_PRICE_DETAIL.TRADE_MARKET is
'交易市场';

comment on column BOK_SHADOW_PRICE_DETAIL.SEC_TYPE is
'证券品种';

comment on column BOK_SHADOW_PRICE_DETAIL.SEC_DETAIL_TYPE is
'证券细分类';

comment on column BOK_SHADOW_PRICE_DETAIL.VALUATION_PRICE is
'估值市价';

comment on column BOK_SHADOW_PRICE_DETAIL.VALUATION_PRICE_2 is
'估值第二市价';

comment on column BOK_SHADOW_PRICE_DETAIL.QUOTA_DIGIT is
'行情位数';

comment on column BOK_SHADOW_PRICE_DETAIL.QUOTA_DEC_TYPE is
'行情尾数处理';

comment on column BOK_SHADOW_PRICE_DETAIL.VALUATION_DATE is
'行情日期';

comment on column BOK_SHADOW_PRICE_DETAIL.VDATE is
'开始日';

comment on column BOK_SHADOW_PRICE_DETAIL.MDATE is
'结束日';

comment on column BOK_SHADOW_PRICE_DETAIL.CREATE_USER is
'创建人';

comment on column BOK_SHADOW_PRICE_DETAIL.CREATE_DEPT is
'创建部门';

comment on column BOK_SHADOW_PRICE_DETAIL.CREATE_TIME is
'创建时间';

comment on column BOK_SHADOW_PRICE_DETAIL.UPDATE_USER is
'更新人';

comment on column BOK_SHADOW_PRICE_DETAIL.UPDATE_TIME is
'更新时间';

--added by wangzhiang 20181113 新增影子定价方案 end 
--added by wangzhiang 20181113 修改附件表名称 start 
drop table BOK_NETVALUE_CFM_FILE cascade constraints;
drop table BOK_VALBAL_TABLE_FILE cascade constraints;

/*==============================================================*/
/* Table: BOK_VALBAL_TABLE_FILE                                 */
/*==============================================================*/
create table BOK_VALBAL_TABLE_FILE 
(
   FINPROD_ID           VARCHAR(32)          not null,
   VAL_DATE             DATE                 not null,
   VAL_BAL_FLAG         VARCHAR(50)          not null,
   FILE_ID              VARCHAR(32)          not null,
   FILE_TYPE            VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_VALBAL_TABLE_FILE primary key (FINPROD_ID, VAL_DATE, VAL_BAL_FLAG, FILE_ID)
);

comment on table BOK_VALBAL_TABLE_FILE is
'估值表余额表上传文件信息';

comment on column BOK_VALBAL_TABLE_FILE.FINPROD_ID is
'产品代码';

comment on column BOK_VALBAL_TABLE_FILE.VAL_DATE is
'估值日期';

comment on column BOK_VALBAL_TABLE_FILE.VAL_BAL_FLAG is
'估值表余额表标志';

comment on column BOK_VALBAL_TABLE_FILE.FILE_ID is
'文件ID';

comment on column BOK_VALBAL_TABLE_FILE.FILE_TYPE is
'文件类型';

comment on column BOK_VALBAL_TABLE_FILE.CREATE_USER is
'创建人';

comment on column BOK_VALBAL_TABLE_FILE.CREATE_DEPT is
'创建部门';

comment on column BOK_VALBAL_TABLE_FILE.CREATE_TIME is
'创建时间';

comment on column BOK_VALBAL_TABLE_FILE.UPDATE_USER is
'更新人';

comment on column BOK_VALBAL_TABLE_FILE.UPDATE_TIME is
'更新时间';

--added by wangzhiang 20181113 修改附件表名称 end 
--added by wangzhiang 20181113 删除无用字段 start 
alter table BOK_STAT_BOK_POSITION drop COLUMN TDY_PROFIT_AMT;
alter table BOK_VAL_SUMMARY drop COLUMN TDY_PROFIT_AMT;
--added by wangzhiang 20181113 删除无用字段 end 
--added by wangzhiang 20181113 新增摊销配置表 start 
drop table BOK_AMO_CONFIG cascade constraints;

/*==============================================================*/
/* Table: BOK_AMO_CONFIG                                        */
/*==============================================================*/
create table BOK_AMO_CONFIG 
(
   BOOKSET_ID           VARCHAR(32)          not null,
   BUSI_ID              VARCHAR(32)          not null,
   CDATE                DATE                 not null,
   IS_RECAL             VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_AMO_CONFIG primary key (BOOKSET_ID, BUSI_ID, CDATE)
);

comment on table BOK_AMO_CONFIG is
'摊销重算配置';

comment on column BOK_AMO_CONFIG.BOOKSET_ID is
'账套代码';

comment on column BOK_AMO_CONFIG.BUSI_ID is
'业务明细代码';

comment on column BOK_AMO_CONFIG.CDATE is
'日期';

comment on column BOK_AMO_CONFIG.IS_RECAL is
'是否重算';

comment on column BOK_AMO_CONFIG.CREATE_USER is
'创建人';

comment on column BOK_AMO_CONFIG.CREATE_DEPT is
'创建部门';

comment on column BOK_AMO_CONFIG.CREATE_TIME is
'创建时间';

comment on column BOK_AMO_CONFIG.UPDATE_USER is
'更新人';

comment on column BOK_AMO_CONFIG.UPDATE_TIME is
'更新时间';
--added by wangzhiang 20181113 新增摊销配置表 end 

/**ADD                                             2018-11-13                                              end**/
/**ADD                                             2018-11-15                                              start**/
/**ADD by chendijun 2018-11-15 START**/
/**新增和修改STL_ORDERS字段**/
alter table STL_ORDERS drop COLUMN STATUS;
alter table STL_ORDERS drop COLUMN ORDER_STATUS;
alter table STL_ORDERS add ORDER_STATUS VARCHAR2(50) default '01';
comment on column STL_ORDERS.ORDER_STATUS is
'指令状态，初始，撤销';
alter table STL_ORDERS add F_ORDER_ID VARCHAR2(50);
comment on column STL_ORDERS.F_ORDER_ID is
'关联系统指令编号';
/**ADD by chendijun 2018-11-15 END **/
/**ADD                                             2018-11-15                                              end**/
/**ADD                                             2018-11-16                                              start**/
/**ADD by wangzhiang 2018-11-16 START**/
alter table BOK_VAL_TABLE_DATA modify BOOKSET_NAME VARCHAR2(200);
alter table BOK_BAL_TABLE_DATA modify BOOKSET_NAME VARCHAR2(200);
/**ADD by wangzhiang 2018-11-16 END **/
/**ADD                                             2018-11-16                                              end**/

/**ADD                                             2018-11-19                                              start**/
/**add by caoguo  金融产品交易表增加 交易市场 字段start**/
alter table TRD_PRODUCT_DEAL add TRADE_MARKET_MODE VARCHAR2(50);
comment on column TRD_PRODUCT_DEAL.TRADE_MARKET_MODE is
'交易市场，一级市场、二级市场';
/**add by caoguo  金融产品交易表增加 交易市场 字段end**/
/**ADD                                             2018-11-19                                              end**/

/**ADD                                             2018-11-21                                             start**/

alter table val_interf_server_info drop primary key;
alter table val_interf_server_info add primary key (serverip, systemid, rs);


--added by yangjian 【产品估值表对账】与【产品余额表对账】功能集成   20181121  start
-- 1.方案明细表
drop table BOK_SCHEME_INFO_DETAIL ;
create table BOK_SCHEME_INFO_DETAIL 
(
   SCH_ID               VARCHAR(32)          not null,
   ERROR_NUMBER         NUMERIC(30, 14)      not null,
   MY_START_ROW_NO      NUMERIC(10)          not null,
   OTHERSIDE_START_ROW_NO NUMERIC(10)          not null,
   MY_END_ROW_NO        NUMERIC(10),
   OTHERSIDE_END_ROW_NO NUMERIC(10),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SCHEME_INFO_DETAIL primary key (SCH_ID)
);
comment on table BOK_SCHEME_INFO_DETAIL is '方案明细表';
comment on column BOK_SCHEME_INFO_DETAIL.SCH_ID is '方案代码';
comment on column BOK_SCHEME_INFO_DETAIL.ERROR_NUMBER is '对账误差设置';
comment on column BOK_SCHEME_INFO_DETAIL.MY_START_ROW_NO is '本方数据起始行';
comment on column BOK_SCHEME_INFO_DETAIL.OTHERSIDE_START_ROW_NO is '对方数据起始行';
comment on column BOK_SCHEME_INFO_DETAIL.MY_END_ROW_NO is '本方数据结束行';
comment on column BOK_SCHEME_INFO_DETAIL.OTHERSIDE_END_ROW_NO is '对方数据结束行';
comment on column BOK_SCHEME_INFO_DETAIL.CREATE_USER is '创建人';
comment on column BOK_SCHEME_INFO_DETAIL.CREATE_DEPT is '创建部门';
comment on column BOK_SCHEME_INFO_DETAIL.CREATE_TIME is '创建时间';
comment on column BOK_SCHEME_INFO_DETAIL.UPDATE_USER is '更新人';
comment on column BOK_SCHEME_INFO_DETAIL.UPDATE_TIME is '更新时间';

--2.对账方案结构映射表
drop table BOK_SCHEME_STRUCT_CONFIG;
create table BOK_SCHEME_STRUCT_CONFIG 
(
   SEQ_NO               VARCHAR(32)          not null,
   SCHEME_ID            VARCHAR(32)          not null,
   MY_COL_ID            VARCHAR(32),
   MY_COL_REMARK        VARCHAR2(200),
   OTHERSIDE_COL_ID     VARCHAR(32),
   OTHERSIDE_COL_REMARK VARCHAR2(200),
   BLOCK_TYPE           VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SCHEME_STRUCT_CONFIG primary key (SEQ_NO)
);
comment on table BOK_SCHEME_STRUCT_CONFIG is
'对账方案结构映射表';
comment on column BOK_SCHEME_STRUCT_CONFIG.SEQ_NO is
'流水号';
comment on column BOK_SCHEME_STRUCT_CONFIG.SCHEME_ID is
'方案代码';
comment on column BOK_SCHEME_STRUCT_CONFIG.MY_COL_ID is
'本方列ID（在数据字典固化）';
comment on column BOK_SCHEME_STRUCT_CONFIG.MY_COL_REMARK is
'本方列说明（在数据字典固化）';
comment on column BOK_SCHEME_STRUCT_CONFIG.OTHERSIDE_COL_ID is
'对方列ID';
comment on column BOK_SCHEME_STRUCT_CONFIG.OTHERSIDE_COL_REMARK is
'对方列说明';
comment on column BOK_SCHEME_STRUCT_CONFIG.BLOCK_TYPE is
'区块类型 科目行 汇总行';
comment on column BOK_SCHEME_STRUCT_CONFIG.CREATE_USER is
'创建人';
comment on column BOK_SCHEME_STRUCT_CONFIG.CREATE_DEPT is
'创建部门';
comment on column BOK_SCHEME_STRUCT_CONFIG.CREATE_TIME is
'创建时间';
comment on column BOK_SCHEME_STRUCT_CONFIG.UPDATE_USER is
'更新人';
comment on column BOK_SCHEME_STRUCT_CONFIG.UPDATE_TIME is
'更新时间';

--3.对账方案科目映射表
drop table BOK_SCHEME_SUBJECT_CONFIG;
create table BOK_SCHEME_SUBJECT_CONFIG 
(
   SCHEME_ID            VARCHAR(32)          not null,
   MY_SUBJECT_ID        VARCHAR2(200)        not null,
   OTHERSIDE_SUBJECT_ID VARCHAR2(200)        not null,
   OTHERSIDE_SUBJECT_NAME VARCHAR2(200),
   IS_GEN_SUBS          VARCHAR(50),
   BLOCK_TYPE           VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SCHEME_SUBJECT_CONFIG primary key (SCHEME_ID, MY_SUBJECT_ID, OTHERSIDE_SUBJECT_ID)
);
comment on table BOK_SCHEME_SUBJECT_CONFIG is
'对账方案科目映射表';
comment on column BOK_SCHEME_SUBJECT_CONFIG.SCHEME_ID is
'方案代码';
comment on column BOK_SCHEME_SUBJECT_CONFIG.MY_SUBJECT_ID is
'本系统科目号';
comment on column BOK_SCHEME_SUBJECT_CONFIG.OTHERSIDE_SUBJECT_ID is
'对方系统科目号';
comment on column BOK_SCHEME_SUBJECT_CONFIG.OTHERSIDE_SUBJECT_NAME is
'对方系统科目名称';
comment on column BOK_SCHEME_SUBJECT_CONFIG.IS_GEN_SUBS is
'是否自动生成子科目（对方系统） 参照数据字典';
comment on column BOK_SCHEME_SUBJECT_CONFIG.BLOCK_TYPE is
'区块类型 明细行 汇总行';
comment on column BOK_SCHEME_SUBJECT_CONFIG.CREATE_USER is
'创建人';
comment on column BOK_SCHEME_SUBJECT_CONFIG.CREATE_DEPT is
'创建部门';
comment on column BOK_SCHEME_SUBJECT_CONFIG.CREATE_TIME is
'创建时间';
comment on column BOK_SCHEME_SUBJECT_CONFIG.UPDATE_USER is
'更新人';
comment on column BOK_SCHEME_SUBJECT_CONFIG.UPDATE_TIME is
'更新时间';

--4.对账方案资产映射表
drop table BOK_SCHEME_ASSET_CONFIG;
create table BOK_SCHEME_ASSET_CONFIG 
(
   SCHEME_ID            VARCHAR(32)          not null,
   MY_ASSET_ID          VARCHAR(32)          not null,
   OTHERSIDE_ASSET_ID   VARCHAR(32)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_SCHEME_ASSET_CONFIG primary key (SCHEME_ID, MY_ASSET_ID, OTHERSIDE_ASSET_ID)
);
comment on table BOK_SCHEME_ASSET_CONFIG is
'对账方案资产映射表';
comment on column BOK_SCHEME_ASSET_CONFIG.SCHEME_ID is
'方案代码';
comment on column BOK_SCHEME_ASSET_CONFIG.MY_ASSET_ID is
'本系统资产代码';
comment on column BOK_SCHEME_ASSET_CONFIG.OTHERSIDE_ASSET_ID is
'对方系统资产代码';
comment on column BOK_SCHEME_ASSET_CONFIG.CREATE_USER is
'创建人';
comment on column BOK_SCHEME_ASSET_CONFIG.CREATE_DEPT is
'创建部门';
comment on column BOK_SCHEME_ASSET_CONFIG.CREATE_TIME is
'创建时间';
comment on column BOK_SCHEME_ASSET_CONFIG.UPDATE_USER is
'更新人';
comment on column BOK_SCHEME_ASSET_CONFIG.UPDATE_TIME is
'更新时间';

--5.产品对账行结果表
drop table BOK_PRODUCT_ROW_RESULT;
create table BOK_PRODUCT_ROW_RESULT 
(
   COMPARE_ID           VARCHAR(32)          not null,
   PROD_ID              VARCHAR(32)          not null,
   SCHEME_ID            VARCHAR(32)          not null,
   COMPARE_DATE         DATE                 not null,
   COMPARE_TYPE         VARCHAR(50)          not null,
   COMPARE_ROWNO        NUMERIC(10)          not null,
   MY_SUBJECT_ID        VARCHAR2(200),
   MY_SUBJECT_NAME      VARCHAR2(200),
   OTHERSIDE_SUBJECT_ID VARCHAR2(200),
   OTHERSIDE_SUBJECT_NAME VARCHAR2(200),
   REMARK               VARCHAR(1000),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_PRODUCT_ROW_RESULT primary key (COMPARE_ID)
);
comment on table BOK_PRODUCT_ROW_RESULT is
'产品对账行结果表';
comment on column BOK_PRODUCT_ROW_RESULT.COMPARE_ID is
'对账结果代码';
comment on column BOK_PRODUCT_ROW_RESULT.PROD_ID is
'产品代码';
comment on column BOK_PRODUCT_ROW_RESULT.SCHEME_ID is
'方案代码';
comment on column BOK_PRODUCT_ROW_RESULT.COMPARE_DATE is
'估值日';
comment on column BOK_PRODUCT_ROW_RESULT.COMPARE_TYPE is
'对比类型,本系统对比对方系统,对方系统对比本系统';
comment on column BOK_PRODUCT_ROW_RESULT.COMPARE_ROWNO is
'对比行号';
comment on column BOK_PRODUCT_ROW_RESULT.MY_SUBJECT_ID is
'本系统科目号';
comment on column BOK_PRODUCT_ROW_RESULT.MY_SUBJECT_NAME is
'本系统科目名称';
comment on column BOK_PRODUCT_ROW_RESULT.OTHERSIDE_SUBJECT_ID is
'对方系统科目号';
comment on column BOK_PRODUCT_ROW_RESULT.OTHERSIDE_SUBJECT_NAME is
'对方系统科目名称';
comment on column BOK_PRODUCT_ROW_RESULT.REMARK is
'备注';
comment on column BOK_PRODUCT_ROW_RESULT.CREATE_USER is
'创建人';
comment on column BOK_PRODUCT_ROW_RESULT.CREATE_DEPT is
'创建部门';
comment on column BOK_PRODUCT_ROW_RESULT.CREATE_TIME is
'创建时间';
comment on column BOK_PRODUCT_ROW_RESULT.UPDATE_USER is
'更新人';
comment on column BOK_PRODUCT_ROW_RESULT.UPDATE_TIME is
'更新时间';

--6.产品对账列结果表
drop table BOK_PRODUCT_COL_RESULT;
create table BOK_PRODUCT_COL_RESULT 
(
   COMPARE_ID           VARCHAR(32)          not null,
   COMPARE_COL          VARCHAR(32)          not null,
   MY_DATA              VARCHAR2(200),
   OTHERSIDE_DATA       VARCHAR2(200),
   NEEDCOMPARE          VARCHAR2(20),
   DIFFERENCE           VARCHAR2(200),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_PRODUCT_COL_RESULT primary key (COMPARE_ID, COMPARE_COL)
);
comment on table BOK_PRODUCT_COL_RESULT is
'产品对账列结果表';
comment on column BOK_PRODUCT_COL_RESULT.COMPARE_ID is
'对账结果代码';
comment on column BOK_PRODUCT_COL_RESULT.COMPARE_COL is
'对比列 映射结构映射表的本方列ID';
comment on column BOK_PRODUCT_COL_RESULT.MY_DATA is
'本系统数值';
comment on column BOK_PRODUCT_COL_RESULT.OTHERSIDE_DATA is
'对方系统数值';
comment on column BOK_PRODUCT_COL_RESULT.NEEDCOMPARE is
'是否比较差异';
comment on column BOK_PRODUCT_COL_RESULT.DIFFERENCE is
'差异结果';
comment on column BOK_PRODUCT_COL_RESULT.CREATE_USER is
'创建人';
comment on column BOK_PRODUCT_COL_RESULT.CREATE_DEPT is
'创建部门';
comment on column BOK_PRODUCT_COL_RESULT.CREATE_TIME is
'创建时间';
comment on column BOK_PRODUCT_COL_RESULT.UPDATE_USER is
'更新人';
comment on column BOK_PRODUCT_COL_RESULT.UPDATE_TIME is
'更新时间';
--added by yangjian 【产品估值表对账】与【产品余额表对账】功能集成   20181121  end



/**金融产品标的信息(债券借贷标的信息) add by wujie on 20181121 start**/
drop table FIN_DEAL_TARGET cascade constraints;

/*==============================================================*/
/* Table: FIN_DEAL_TARGET                                       */
/*==============================================================*/
create table FIN_DEAL_TARGET 
(
   FINPROD_ID           VARCHAR(32)          not null,
   FINPROD_TYPE         VARCHAR(50)          not null,
   BRANCH               NUMERIC(10)          not null,
   PLEDGE_FINPROD_ID    VARCHAR(32)          not null,
   PLEDGE_FINPROD_TYPE  VARCHAR(50)          not null,
   PLEDGE_FACE_VALUE    NUMERIC(30, 2),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_DEAL_TARGET primary key (FINPROD_ID, FINPROD_TYPE, BRANCH, PLEDGE_FINPROD_ID, PLEDGE_FINPROD_TYPE)
);

comment on table FIN_DEAL_TARGET is
'金融产品标的信息(债券借贷标的信息)';

comment on column FIN_DEAL_TARGET.FINPROD_ID is
'金融产品代码';

comment on column FIN_DEAL_TARGET.FINPROD_TYPE is
'金融产品类型';

comment on column FIN_DEAL_TARGET.BRANCH is
'分支序号';

comment on column FIN_DEAL_TARGET.PLEDGE_FINPROD_ID is
'质押金融产品代码';

comment on column FIN_DEAL_TARGET.PLEDGE_FINPROD_TYPE is
'质押金融产品类型';

comment on column FIN_DEAL_TARGET.PLEDGE_FACE_VALUE is
'质押面值';

comment on column FIN_DEAL_TARGET.CREATE_USER is
'创建人';

comment on column FIN_DEAL_TARGET.CREATE_DEPT is
'创建部门';

comment on column FIN_DEAL_TARGET.CREATE_TIME is
'创建时间';

comment on column FIN_DEAL_TARGET.UPDATE_USER is
'更新人';

comment on column FIN_DEAL_TARGET.UPDATE_TIME is
'更新时间';

/**金融产品标的信息(债券借贷标的信息) add by wujie on 20181121 end**/
/**ADD                                             2018-11-21                                             end**/
/**ADD                                             2018-11-22                                             bein**/
/**修改估值表数据中分层代码字段名 add by weipeng  2018/11/22 start**/
alter table BOK_VAL_TABLE_DATA rename column BUSI_ID to LAYERING_ID ;
/**修改估值表数据中分层代码字段名 add by weipeng  2018/11/22 end**/


/** 税务参数-参数设置 add by zhangwei 20181122 start **/
-- 方案配置值信息表 添加生效日
alter table BOK_SCHEME_CONFIG_VAL add eff_date DATE;
comment on column BOK_SCHEME_CONFIG_VAL.eff_date is '生效日';
/** 税务参数-参数设置 add by zhangwei 20181122 end **/
/**ADD                                             2018-11-22                                             end**/


/**ADD                                             2018-11-23                                             start**/
alter table val_interf_server_info drop primary key;
drop index PK_VAL_INTERF_SERVER_INFO;
alter table val_interf_server_info add constraint PK_VAL_INTERF_SERVER_INFO primary key (serverip, systemid, rs);

/**ADD                                             2018-11-23                                             end**/



/**ADD                                             2018-11-28                                             start**/
/**表【对账方案科目映射表】允许字段【区块类型】为空。 add by yangjian 2018-11-28 start**/
 alter table BOK_SCHEME_SUBJECT_CONFIG modify BLOCK_TYPE null;
/**表【对账方案科目映射表】允许字段【区块类型】为空。 add by yangjian 2018-11-28 end**/
 
 
 /**ADD                                             2018-11-28                                             end**/

/**ADD                                             2018-11-30                                             start**/
/**add by chengzhong 新建 资产类科目场景关联表 start**/
drop table VAL_SUBJECT_TABLEID cascade constraints;

/*==============================================================*/
/* Table: VAL_SUBJECT_TABLEID                                   */
/*==============================================================*/
create table VAL_SUBJECT_TABLEID 
(
   SUBJECT_HEAD         VARCHAR(32)          not null,
   TABLEID              VARCHAR(20),
   ASSETS_NAME          VARCHAR(200),
   constraint PK_VAL_SUBJECT_TABLEID primary key (SUBJECT_HEAD)
);

comment on table VAL_SUBJECT_TABLEID is
'资产类科目场景关联表';

comment on column VAL_SUBJECT_TABLEID.SUBJECT_HEAD is
'科目号头部';

comment on column VAL_SUBJECT_TABLEID.TABLEID is
'资产对应买入场景代码';

comment on column VAL_SUBJECT_TABLEID.ASSETS_NAME is
'备注';

/**add by chengzhong 新建 资产类科目场景关联表 end**/
/**ADD                                             2018-11-30                                             end**/

/**ADD                                             2018-12-03                                             start**/
/**add by caoguo 新增表【账务明细辅助】 start**/
drop table BOK_BOOKDETAIL_CURRENT_EXT cascade constraints;

/*==============================================================*/
/* Table: BOK_BOOKDETAIL_CURRENT_EXT                            */
/*==============================================================*/
create table BOK_BOOKDETAIL_CURRENT_EXT 
(
   SEQNO                VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32)          not null,
   HAPPEN_DATE          DATE                 not null,
   BOOK_DATE            DATE                 not null,
   BOOKSET_DATE         DATE,
   F_SEQNO              VARCHAR(32)          not null,
   BOOK_SUMMARY_ORDER   NUMERIC(20)          not null,
   BOK_DETAIL_ID        VARCHAR(32)          not null,
   BUSI_ID              VARCHAR(32),
   ACCR_DATE            DATE,
   TDY_INTINCEXP        NUMERIC(30, 2),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BOOKDETAIL_CURRENT_EXT primary key (SEQNO)
);

comment on table BOK_BOOKDETAIL_CURRENT_EXT is
'账务明细辅助表';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.SEQNO is
'流水';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.BOOKSET_ID is
'账套代码';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.HAPPEN_DATE is
'发生日期';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.BOOK_DATE is
'入账日期';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.BOOKSET_DATE is
'账套日期';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.F_SEQNO is
'账务明细流水';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.BOOK_SUMMARY_ORDER is
'处理序列';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.BOK_DETAIL_ID is
'账务明细代码';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.BUSI_ID is
'业务明细代码';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.ACCR_DATE is
'计提日';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.TDY_INTINCEXP is
'当日累计应计利息余额';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.CREATE_USER is
'创建人';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.CREATE_DEPT is
'创建部门';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.CREATE_TIME is
'创建时间';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.UPDATE_USER is
'更新人';

comment on column BOK_BOOKDETAIL_CURRENT_EXT.UPDATE_TIME is
'更新时间';

/**add by caoguo 新增表【账务明细辅助】 end**/
/**ADD                                             2018-12-03                                             end**/

/**ADD                                             2018-12-04                                             start**/
/** 修改主键 方案配置值信息表 - 税务方案 - 参数管理  add by zhangwei 20181204 start**/
alter table BOK_SCHEME_CONFIG_VAL drop constraint PK_BOK_SCHEME_CONFIG_VAL ;
drop index PK_BOK_SCHEME_CONFIG_VAL;
alter table BOK_SCHEME_CONFIG_VAL add constraint PK_BOK_SCHEME_CONFIG_VAL primary key(SEQ_ID);
/** 修改主键 方案配置值信息表 - 税务方案 - 参数管理  add by zhangwei 20181204 end**/


/** 修改sequence为12位  add by wangbo 20181204 start**/
alter sequence BOOK_SEQUENCE MAXVALUE 999999999999;
alter sequence ORDER_SEQUENCE MAXVALUE 999999999999;
alter sequence PD_SEQUENCE MAXVALUE 999999999999;
alter sequence TAX_SEQUENCE MAXVALUE 999999999999;
/** 修改sequence为12位  add by wangbo   20181204 end**/

/**add by chumi 摊销重算配置表  删除是否重算字段、增加重算方式、实际日利率字段 start**/
alter table BOK_AMO_CONFIG drop column IS_RECAL;
alter table BOK_AMO_CONFIG add RECAL_TYPE VARCHAR2(50) not null;
comment on column BOK_AMO_CONFIG.RECAL_TYPE is
'重算方式';
alter table BOK_AMO_CONFIG add DAILYDSC_YIELD NUMERIC(30, 14);
comment on column BOK_AMO_CONFIG.DAILYDSC_YIELD is
'实际日利率';
/**add by chumi 摊销重算配置表  删除是否重算字段、增加重算方式、实际日利率字段  end**/
/**ADD                                             2018-12-04                                             end**/

/**ADD                                             2018-12-07                                             start**/
/** 提交人：杨剑  日期：2018-12-07 start**/
--【设计变更：货基红利再投冲减应收红利逻辑修改】要求，创建表'账务明细辅助表'。
drop table BOK_BOOKDETAIL_CURRENT_EXT;
create table BOK_BOOKDETAIL_CURRENT_EXT 
(
   SEQNO                VARCHAR(32)          not null,
   BOOKSET_ID           VARCHAR(32)          not null,
   HAPPEN_DATE          DATE                 not null,
   BOOK_DATE            DATE                 not null,
   BOOKSET_DATE         DATE,
   F_SEQNO              VARCHAR(32)          not null,
   BOOK_SUMMARY_ORDER   NUMERIC(20)          not null,
   BOK_DETAIL_ID        VARCHAR(32)          not null,
   BUSI_ID              VARCHAR(32),
   ACCR_DATE            DATE,
   TDY_INTINCEXP        NUMERIC(30, 2),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_BOOKDETAIL_CURRENT_EXT primary key (SEQNO)
);
comment on table BOK_BOOKDETAIL_CURRENT_EXT is
'账务明细辅助表';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.SEQNO is
'流水';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.BOOKSET_ID is
'账套代码';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.HAPPEN_DATE is
'发生日期';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.BOOK_DATE is
'入账日期';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.BOOKSET_DATE is
'账套日期';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.F_SEQNO is
'账务明细流水';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.BOOK_SUMMARY_ORDER is
'处理序列';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.BOK_DETAIL_ID is
'账务明细代码';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.BUSI_ID is
'业务明细代码';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.ACCR_DATE is
'计提日';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.TDY_INTINCEXP is
'当日累计应计利息余额';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.CREATE_USER is
'创建人';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.CREATE_DEPT is
'创建部门';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.CREATE_TIME is
'创建时间';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.UPDATE_USER is
'更新人';
comment on column BOK_BOOKDETAIL_CURRENT_EXT.UPDATE_TIME is
'更新时间';
/** 提交人：杨剑  日期：2018-12-07 end**/  

/** 提交人：璩建忠  日期：2018-12-07 start**/ 
--增加利润表表样配置表
drop table BOK_PROFIT_REP_CONFIG ;

create table BOK_PROFIT_REP_CONFIG 
(
   PROJ_CODE            VARCHAR(100)         not null,
   PROJ_NAME            VARCHAR(500),
   PROJ_LEVEL           NUMERIC(10),
   FORMULA              VARCHAR(1000),
   SHOW_FLG             VARCHAR(50),
   SHOW_ORDER           NUMERIC(10),
   SUBJECT_NO           VARCHAR(32),
   CRDR_FLG             VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_PROFIT_REP_CONFIG primary key (PROJ_CODE)
);

comment on table BOK_PROFIT_REP_CONFIG is
'利润表表样配置表';

comment on column BOK_PROFIT_REP_CONFIG.PROJ_CODE is
'项目编码';

comment on column BOK_PROFIT_REP_CONFIG.PROJ_NAME is
'项目名';

comment on column BOK_PROFIT_REP_CONFIG.PROJ_LEVEL is
'项目级别，0-明细项，1-汇总项';

comment on column BOK_PROFIT_REP_CONFIG.FORMULA is
'计算公式';

comment on column BOK_PROFIT_REP_CONFIG.SHOW_FLG is
'是否是报表展示项';

comment on column BOK_PROFIT_REP_CONFIG.SHOW_ORDER is
'报表展示序号';

comment on column BOK_PROFIT_REP_CONFIG.SUBJECT_NO is
'科目号';

comment on column BOK_PROFIT_REP_CONFIG.CRDR_FLG is
'期待方向，C-贷方，D-借方';

comment on column BOK_PROFIT_REP_CONFIG.CREATE_USER is
'创建人';

comment on column BOK_PROFIT_REP_CONFIG.CREATE_DEPT is
'创建部门';

comment on column BOK_PROFIT_REP_CONFIG.CREATE_TIME is
'创建时间';

comment on column BOK_PROFIT_REP_CONFIG.UPDATE_USER is
'更新人';

comment on column BOK_PROFIT_REP_CONFIG.UPDATE_TIME is
'更新时间';
/** 提交人：璩建忠  日期：2018-12-07 end**/ 
/**ADD                                             2018-12-07                                             end**/

/**ADD                                             2018-12-15                                             start**/
/** 提交人：王之昂  日期：2018-12-15 start 批处理匹配关系表追加UK,如果该行脚本报错，则该表数据有重复项，需删除重复项后重新执行**/ 
alter table BOK_MATCH_RLAT
   drop unique (MATCH_TYPE, ACCT_STANDARD, SOURCE, TARGET) cascade;

alter table BOK_MATCH_RLAT
   add constraint AK_KEY_1_BOK_MATC unique (MATCH_TYPE, ACCT_STANDARD, SOURCE, TARGET);
/** 提交人：王之昂  日期：2018-12-15 end 批处理匹配关系表追加UK,如果该行脚本报错，则该表数据有重复项，需删除重复项后重新执行**/ 

/**ADD                                             2018-12-15                                             end**/
   
/**ADD                                             2018-12-18                                             start**/
/** 提交人：董俊  日期：2018-12-18 start **/ 
ALTER TABLE VAL_INTERF_SERVER_INFO ADD  SERVERNAME  VARCHAR(32);
comment on column VAL_INTERF_SERVER_INFO.SERVERNAME is
'服务名称(数据库SID)';
/** 提交人：董俊  日期：2018-12-18 end **/ 
/**ADD                                             2018-12-18                                             end**/

/**ADD                                             2018-12-24                                             start**/
/** 提交人：吴洁  20181224 Remark：数据移植对应科目关系 start**/
drop table TRS_SUBJECT_RELATION;
create table TRS_SUBJECT_RELATION
(
  seqno            VARCHAR2(32) not null,
  bookset_id       VARCHAR2(32) not null,
  book_type        VARCHAR2(50),
  bln_area         VARCHAR2(50),
  busi_detail_type VARCHAR2(50),
  profit_type      VARCHAR2(50),
  inv_aim          VARCHAR2(50),
  cb_subno         VARCHAR2(32),
  yslx_subno       VARCHAR2(32),
  lxtz_subno       VARCHAR2(32),
  gyjzbd_subno     VARCHAR2(32),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
);
-- Add comments to the table 
comment on table TRS_SUBJECT_RELATION
  is '数据移植科目对应关系';
-- Add comments to the columns 
comment on column TRS_SUBJECT_RELATION.seqno
  is '流水';
comment on column TRS_SUBJECT_RELATION.bookset_id
  is '账套代码';
comment on column TRS_SUBJECT_RELATION.book_type
  is '核算分类';
comment on column TRS_SUBJECT_RELATION.bln_area
  is '境内外';
comment on column TRS_SUBJECT_RELATION.busi_detail_type
  is '业务明细类型，存金融产品类型、现金流类型或者资金账户类型';
comment on column TRS_SUBJECT_RELATION.profit_type
  is '收益类型，净值、预期收益、货币';
comment on column TRS_SUBJECT_RELATION.inv_aim
  is '投资目的';
comment on column TRS_SUBJECT_RELATION.cb_subno
  is '成本科目';
comment on column TRS_SUBJECT_RELATION.yslx_subno
  is '应收利息科目';
comment on column TRS_SUBJECT_RELATION.lxtz_subno
  is '利息调整科目';
comment on column TRS_SUBJECT_RELATION.gyjzbd_subno
  is '公允价值变动科目';
comment on column TRS_SUBJECT_RELATION.create_user
  is '创建人';
comment on column TRS_SUBJECT_RELATION.create_dept
  is '创建部门';
comment on column TRS_SUBJECT_RELATION.create_time
  is '创建时间';
comment on column TRS_SUBJECT_RELATION.update_user
  is '更新人';
comment on column TRS_SUBJECT_RELATION.update_time
  is '更新时间';
alter table TRS_SUBJECT_RELATION
  add constraint PK_TRS_SUBJECT_RELATION primary key (SEQNO);
/** 提交人：吴洁  20181224 Remark：数据移植对应科目关系 end**/  
/**ADD                                             2018-12-24                                             end**/
  
  
 /**ADD                                             2018-12-25                                             start**/
 /** 提交人：dongjun  Remark：批处理检查日志 start**/
drop table BOK_CHECK_LOG cascade constraints;
create table BOK_CHECK_LOG 
(
   SEQ_NO               VARCHAR(32)          not null,
   TASK_ID              VARCHAR(32),
   BOOKSET_ID           VARCHAR(32),
   HAPPEN_DATE          DATE,
   BOOK_DATE            DATE,
   BOOKSET_DATE         DATE,
   CHECK_DATE           DATE,
   LOG_INFO             CLOB,
   CHECK_TYPE           VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_CHECK_LOG primary key (SEQ_NO)
);

comment on table BOK_CHECK_LOG is
'批处理检查日志';

comment on column BOK_CHECK_LOG.SEQ_NO is
'日志信息ID';

comment on column BOK_CHECK_LOG.TASK_ID is
'任务代码';

comment on column BOK_CHECK_LOG.BOOKSET_ID is
'账套代码';

comment on column BOK_CHECK_LOG.HAPPEN_DATE is
'发生日';

comment on column BOK_CHECK_LOG.BOOK_DATE is
'入账日';

comment on column BOK_CHECK_LOG.BOOKSET_DATE is
'账套日期';

comment on column BOK_CHECK_LOG.CHECK_DATE is
'检查日期';

comment on column BOK_CHECK_LOG.LOG_INFO is
'日志信息';

comment on column BOK_CHECK_LOG.CHECK_TYPE is
'批检类型';

comment on column BOK_CHECK_LOG.CREATE_USER is
'创建人';

comment on column BOK_CHECK_LOG.CREATE_DEPT is
'创建部门';

comment on column BOK_CHECK_LOG.CREATE_TIME is
'创建时间';

comment on column BOK_CHECK_LOG.UPDATE_USER is
'更新人';

comment on column BOK_CHECK_LOG.UPDATE_TIME is
'更新时间';
 /** 提交人：dongjun  Remark：批处理检查日志 end**/
 /**ADD                                             2018-12-25                                             end**/


 /**ADD                                             2018-12-27                                             start**/
 /** 提交人：魏鹏   start**/
--文件上传表新增字段 上传状态
ALTER TABLE bok_file_gen ADD  UPLOAD_STATUS  VARCHAR(32);
comment on column bok_file_gen.UPLOAD_STATUS is
'上传状态（01 已上传,02未上传）';
/** 提交人：魏鹏   end**/
 /**ADD                                             2018-12-27                                             end**/