/** add by dongjun on 20181115 start **/
drop table CBONDEODPRICES cascade constraints;

/*==============================================================*/
/* Table: CBONDEODPRICES                                        */
/*==============================================================*/
create table CBONDEODPRICES 
(
   OBJECT_ID            VARCHAR2(100),
   S_INFO_WINDCODE      VARCHAR2(40),
   TRADE_DT             VARCHAR2(8),
   CRNCY_CODE           VARCHAR2(10),
   S_DQ_PRECLOSE        NUMBER(20,4),
   S_DQ_OPEN            NUMBER(20,4),
   S_DQ_HIGH            NUMBER(20,4),
   S_DQ_LOW             NUMBER(20,4),
   S_DQ_CLOSE           NUMBER(20,4),
   S_DQ_CHANGE          NUMBER(20,4),
   S_DQ_PCTCHANGE       NUMBER(20,4),
   S_DQ_VOLUME          NUMBER(20,4),
   S_DQ_AMOUNT          NUMBER(20,4),
   S_DQ_AVGPRICE        NUMBER(20,4),
   S_DQ_TRADESTATUS     VARCHAR2(10),
   OPDATE               DATE,
   OPMODE               VARCHAR2(1)
);

comment on column CBONDEODPRICES.OBJECT_ID is
'对象ID';

comment on column CBONDEODPRICES.S_INFO_WINDCODE is
'Wind代码';

comment on column CBONDEODPRICES.TRADE_DT is
'交易日期';

comment on column CBONDEODPRICES.CRNCY_CODE is
'货币代码';

comment on column CBONDEODPRICES.S_DQ_PRECLOSE is
'昨收盘价(元)';

comment on column CBONDEODPRICES.S_DQ_OPEN is
'开盘价(元)';

comment on column CBONDEODPRICES.S_DQ_HIGH is
'最高价(元)';

comment on column CBONDEODPRICES.S_DQ_LOW is
'最低价(元)';

comment on column CBONDEODPRICES.S_DQ_CLOSE is
'收盘价(元)';

comment on column CBONDEODPRICES.S_DQ_CHANGE is
'涨跌(元)';

comment on column CBONDEODPRICES.S_DQ_PCTCHANGE is
'涨跌幅(%)';

comment on column CBONDEODPRICES.S_DQ_VOLUME is
'成交量(手)';

comment on column CBONDEODPRICES.S_DQ_AMOUNT is
'成交金额(千元)';

comment on column CBONDEODPRICES.S_DQ_AVGPRICE is
'均价(VWAP)';

comment on column CBONDEODPRICES.S_DQ_TRADESTATUS is
'交易状态';

comment on column CBONDEODPRICES.OPDATE is
'更新时间';

comment on column CBONDEODPRICES.OPMODE is
'更新模式';

drop table CBONDPRICESNET cascade constraints;

/*==============================================================*/
/* Table: CBONDPRICESNET                                        */
/*==============================================================*/
create table CBONDPRICESNET 
(
   OBJECT_ID            VARCHAR2(100),
   S_INFO_WINDCODE      VARCHAR2(40),
   TRADE_DT             VARCHAR2(8),
   B_DQ_OPEN            NUMBER(20,8),
   B_DQ_HIGH            NUMBER(20,8),
   B_DQ_LOW             NUMBER(20,8),
   B_DQ_ORIGINCLOSE     NUMBER(20,8),
   B_DQ_VOLUME          NUMBER(20,8),
   B_DQ_AMOUNT          NUMBER(20,8),
   OPDATE               DATE,
   OPMODE               VARCHAR2(1)
);

comment on table CBONDPRICESNET is
'CBondPricesNet';

comment on column CBONDPRICESNET.OBJECT_ID is
'对象ID';

comment on column CBONDPRICESNET.S_INFO_WINDCODE is
'Wind代码';

comment on column CBONDPRICESNET.TRADE_DT is
'交易日期';

comment on column CBONDPRICESNET.B_DQ_OPEN is
'开盘价(元)';

comment on column CBONDPRICESNET.B_DQ_HIGH is
'最高价(元)';

comment on column CBONDPRICESNET.B_DQ_LOW is
'最低价(元)';

comment on column CBONDPRICESNET.B_DQ_ORIGINCLOSE is
'收盘价(元)';

comment on column CBONDPRICESNET.B_DQ_VOLUME is
'成交量(手)';

comment on column CBONDPRICESNET.B_DQ_AMOUNT is
'成交金额(千元)';

comment on column CBONDPRICESNET.OPDATE is
'更新时间';

comment on column CBONDPRICESNET.OPMODE is
'更新模式';

drop table CBONDPRICES cascade constraints;

/*==============================================================*/
/* Table: CBONDPRICES                                           */
/*==============================================================*/
create table CBONDPRICES 
(
   OBJECT_ID            VARCHAR2(100),
   S_INFO_WINDCODE      VARCHAR2(40),
   TRADE_DT             VARCHAR2(8),
   B_DQ_OPEN            NUMBER(20,8),
   B_DQ_HIGH            NUMBER(20,8),
   B_DQ_LOW             NUMBER(20,8),
   B_DQ_ORIGINCLOSE     NUMBER(20,8),
   B_DQ_VOLUME          NUMBER(20,8),
   B_DQ_AMOUNT          NUMBER(20,8),
   OPDATE               DATE,
   OPMODE               VARCHAR2(1)
);

comment on table CBONDPRICES is
'CBondPrices';

comment on column CBONDPRICES.OBJECT_ID is
'对象ID';

comment on column CBONDPRICES.S_INFO_WINDCODE is
'Wind代码';

comment on column CBONDPRICES.TRADE_DT is
'交易日期';

comment on column CBONDPRICES.B_DQ_OPEN is
'开盘价(元)';

comment on column CBONDPRICES.B_DQ_HIGH is
'最高价(元)';

comment on column CBONDPRICES.B_DQ_LOW is
'最低价(元)';

comment on column CBONDPRICES.B_DQ_ORIGINCLOSE is
'收盘价(元)';

comment on column CBONDPRICES.B_DQ_VOLUME is
'成交量(手)';

comment on column CBONDPRICES.B_DQ_AMOUNT is
'成交金额(千元)';

comment on column CBONDPRICES.OPDATE is
'更新时间';

comment on column CBONDPRICES.OPMODE is
'更新模式';

drop table CBONDANALYSISSHC cascade constraints;

/*==============================================================*/
/* Table: CBONDANALYSISSHC                                      */
/*==============================================================*/
create table CBONDANALYSISSHC 
(
   OBJECT_ID            VARCHAR2(100),
   S_INFO_WINDCODE      VARCHAR2(40),
   TRADE_DT             VARCHAR2(8),
   B_ANAL_MATU_CNBD     NUMBER(20,4),
   B_ANAL_DIRTY_CNBD    NUMBER(20,4),
   B_ANAL_ACCRINT_CNBD  NUMBER(20,4),
   B_ANAL_NET_CNBD      NUMBER(20,4),
   B_ANAL_YIELD_CNBD    NUMBER(20,4),
   B_ANAL_MODIDURA_CNBD NUMBER(20,4),
   B_ANAL_CNVXTY_CNBD   NUMBER(20,4),
   B_ANAL_VOBP_CNBD     NUMBER(20,4),
   B_ANAL_SPRDURA_CNBD  NUMBER(20,4),
   B_ANAL_SPRCNXT_CNBD  NUMBER(20,4),
   B_ANAL_PRICE         NUMBER(20,4),
   B_ANAL_NETPRICE      NUMBER(20,4),
   B_ANAL_YIELD         NUMBER(20,4),
   B_ANAL_MODIFIEDDURATION NUMBER(20,4),
   B_ANAL_CONVEXITY     NUMBER(20,4),
   B_ANAL_BPVALUE       NUMBER(20,4),
   B_ANAL_SDURATION     NUMBER(20,4),
   B_ANAL_SCNVXTY       NUMBER(20,4),
   B_ANAL_INTERESTDURATION_CNBD NUMBER(20,4),
   B_ANAL_INTERESTCNVXTY_CNBD NUMBER(20,4),
   B_ANAL_INTERESTDURATION NUMBER(20,4),
   B_ANAL_INTERESTCNVXTY NUMBER(20,4),
   B_ANAL_PRICE_CNBD    NUMBER(20,4),
   B_ANAL_BPYIELD       NUMBER(20,4),
   B_ANAL_SURPLUSCAPITAL NUMERIC(20, 4),
   B_ANAL_EXCHANGE      VARCHAR2(40),
   B_ANAL_CREDIBILITY   VARCHAR2(40),
   OPDATE               DATE,
   OPMODE               VARCHAR2(1)
);

comment on table CBONDANALYSISSHC is
'CBondAnalysisSHC';

comment on column CBONDANALYSISSHC.OBJECT_ID is
'对象ID';

comment on column CBONDANALYSISSHC.S_INFO_WINDCODE is
'Wind代码';

comment on column CBONDANALYSISSHC.TRADE_DT is
'交易日期';

comment on column CBONDANALYSISSHC.B_ANAL_MATU_CNBD is
'待偿期(年)';

comment on column CBONDANALYSISSHC.B_ANAL_DIRTY_CNBD is
'估价全价';

comment on column CBONDANALYSISSHC.B_ANAL_ACCRINT_CNBD is
'日间应计利息';

comment on column CBONDANALYSISSHC.B_ANAL_NET_CNBD is
'估价净价';

comment on column CBONDANALYSISSHC.B_ANAL_YIELD_CNBD is
'估价收益率(%)';

comment on column CBONDANALYSISSHC.B_ANAL_MODIDURA_CNBD is
'估价修正久期';

comment on column CBONDANALYSISSHC.B_ANAL_CNVXTY_CNBD is
'估价凸性';

comment on column CBONDANALYSISSHC.B_ANAL_VOBP_CNBD is
'估价基点价值';

comment on column CBONDANALYSISSHC.B_ANAL_SPRDURA_CNBD is
'估价利差久期';

comment on column CBONDANALYSISSHC.B_ANAL_SPRCNXT_CNBD is
'估价利差凸性';

comment on column CBONDANALYSISSHC.B_ANAL_PRICE is
'市场全价';

comment on column CBONDANALYSISSHC.B_ANAL_NETPRICE is
'市场净价';

comment on column CBONDANALYSISSHC.B_ANAL_YIELD is
'市场收益率(%)';

comment on column CBONDANALYSISSHC.B_ANAL_MODIFIEDDURATION is
'市场修正久期';

comment on column CBONDANALYSISSHC.B_ANAL_CONVEXITY is
'市场凸性';

comment on column CBONDANALYSISSHC.B_ANAL_BPVALUE is
'市场基点价值';

comment on column CBONDANALYSISSHC.B_ANAL_SDURATION is
'市场利差久期';

comment on column CBONDANALYSISSHC.B_ANAL_SCNVXTY is
'市场利差凸性';

comment on column CBONDANALYSISSHC.B_ANAL_INTERESTDURATION_CNBD is
'估价利率久期';

comment on column CBONDANALYSISSHC.B_ANAL_INTERESTCNVXTY_CNBD is
'估价利率凸性';

comment on column CBONDANALYSISSHC.B_ANAL_INTERESTDURATION is
'市场利率久期';

comment on column CBONDANALYSISSHC.B_ANAL_INTERESTCNVXTY is
'市场利率凸性';

comment on column CBONDANALYSISSHC.B_ANAL_PRICE_CNBD is
'日终估价全价';

comment on column CBONDANALYSISSHC.B_ANAL_BPYIELD is
'日终应计利息';

comment on column CBONDANALYSISSHC.B_ANAL_SURPLUSCAPITAL is
'剩余本金';

comment on column CBONDANALYSISSHC.B_ANAL_EXCHANGE is
'流通场所';

comment on column CBONDANALYSISSHC.B_ANAL_CREDIBILITY is
'可信度';

comment on column CBONDANALYSISSHC.OPDATE is
'更新时间';

comment on column CBONDANALYSISSHC.OPMODE is
'更新模式';

drop table CBONDANALYSISCSI cascade constraints;

/*==============================================================*/
/* Table: CBONDANALYSISCSI                                      */
/*==============================================================*/
create table CBONDANALYSISCSI 
(
   OBJECT_ID            VARCHAR2(100),
   S_INFO_WINDCODE      VARCHAR2(40),
   TRADE_DT             VARCHAR2(8),
   B_INFO_TYPE          VARCHAR2(50),
   B_INFO_LISTINGMARKETNUMBERS NUMBER(20,4),
   B_ANAL_DIRTY_CSI     NUMBER(20,4),
   B_ANAL_YIELD_CSI     NUMBER(20,4),
   B_ANAL_MODIDURA_CSI  NUMBER(20,4),
   B_ANAL_CNVXTY_CSI    NUMBER(20,4),
   B_ANAL_NET_CSI       NUMBER(20,4),
   B_ANAL_ACCRINT_CSI   NUMBER(20,4),
   OPDATE               DATE,
   OPMODE               VARCHAR2(1)
);

comment on table CBONDANALYSISCSI is
'CBondAnalysisCSI';

comment on column CBONDANALYSISCSI.OBJECT_ID is
'对象ID';

comment on column CBONDANALYSISCSI.S_INFO_WINDCODE is
'Wind代码';

comment on column CBONDANALYSISCSI.TRADE_DT is
'交易日期';

comment on column CBONDANALYSISCSI.B_INFO_TYPE is
'信用类型';

comment on column CBONDANALYSISCSI.B_INFO_LISTINGMARKETNUMBERS is
'发行市场数';

comment on column CBONDANALYSISCSI.B_ANAL_DIRTY_CSI is
'计算价格';

comment on column CBONDANALYSISCSI.B_ANAL_YIELD_CSI is
'计算收益率(%)';

comment on column CBONDANALYSISCSI.B_ANAL_MODIDURA_CSI is
'修正久期';

comment on column CBONDANALYSISCSI.B_ANAL_CNVXTY_CSI is
'凸性';

comment on column CBONDANALYSISCSI.B_ANAL_NET_CSI is
'净价';

comment on column CBONDANALYSISCSI.B_ANAL_ACCRINT_CSI is
'应计利息';

comment on column CBONDANALYSISCSI.OPDATE is
'更新时间';

comment on column CBONDANALYSISCSI.OPMODE is
'更新模式';

drop table CHINACLOSEDFUNDEODPRICE cascade constraints;

/*==============================================================*/
/* Table: CHINACLOSEDFUNDEODPRICE                               */
/*==============================================================*/
create table CHINACLOSEDFUNDEODPRICE 
(
   OBJECT_ID            VARCHAR2(100),
   S_INFO_WINDCODE      VARCHAR2(40),
   TRADE_DT             VARCHAR2(8),
   CRNCY_CODE           VARCHAR2(10),
   S_DQ_PRECLOSE        NUMBER(20,4),
   S_DQ_OPEN            NUMBER(20,4),
   S_DQ_HIGH            NUMBER(20,4),
   S_DQ_LOW             NUMBER(20,4),
   S_DQ_CLOSE           NUMBER(20,4),
   S_DQ_CHANGE          NUMBER(20,4),
   S_DQ_PCTCHANGE       NUMBER(20,4),
   S_DQ_VOLUME          NUMBER(20,4),
   S_DQ_AMOUNT          NUMBER(20,4),
   S_DQ_ADJPRECLOSE     NUMBER(20,4),
   S_DQ_ADJOPEN         NUMBER(20,4),
   S_DQ_ADJHIGH         NUMBER(20,4),
   S_DQ_ADJLOW          NUMBER(20,4),
   S_DQ_ADJCLOSE        NUMBER(20,4),
   S_DQ_ADJFACTOR       NUMBER(20,6),
   TRADES_COUNT         NUMBER(20,4),
   DISCOUNT_RATE        NUMBER(20,6),
   OPDATE               DATE,
   OPMODE               VARCHAR2(1)
);

comment on table CHINACLOSEDFUNDEODPRICE is
'ChinaClosedFundEODPrice';

comment on column CHINACLOSEDFUNDEODPRICE.OBJECT_ID is
'对象ID';

comment on column CHINACLOSEDFUNDEODPRICE.S_INFO_WINDCODE is
'Wind代码';

comment on column CHINACLOSEDFUNDEODPRICE.TRADE_DT is
'交易日期';

comment on column CHINACLOSEDFUNDEODPRICE.CRNCY_CODE is
'货币代码';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_PRECLOSE is
'昨收盘价(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_OPEN is
'开盘价(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_HIGH is
'最高价(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_LOW is
'最低价(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_CLOSE is
'收盘价(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_CHANGE is
'涨跌(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_PCTCHANGE is
'涨跌幅(%)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_VOLUME is
'成交量(手)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_AMOUNT is
'成交金额(千元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_ADJPRECLOSE is
'复权昨收盘价(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_ADJOPEN is
'复权开盘价(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_ADJHIGH is
'复权最高价(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_ADJLOW is
'复权最低价(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_ADJCLOSE is
'复权收盘价(元)';

comment on column CHINACLOSEDFUNDEODPRICE.S_DQ_ADJFACTOR is
'复权因子';

comment on column CHINACLOSEDFUNDEODPRICE.TRADES_COUNT is
'成交笔数';

comment on column CHINACLOSEDFUNDEODPRICE.DISCOUNT_RATE is
'贴水率（%）';

comment on column CHINACLOSEDFUNDEODPRICE.OPDATE is
'更新时间';

comment on column CHINACLOSEDFUNDEODPRICE.OPMODE is
'更新模式';
/** add by dongjun on 20181115 end **/