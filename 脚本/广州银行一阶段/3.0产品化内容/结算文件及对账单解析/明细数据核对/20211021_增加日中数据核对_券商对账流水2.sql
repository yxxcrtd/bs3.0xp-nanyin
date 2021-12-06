drop table STL_SEC_RECON_DEAL;
create table STL_SEC_RECON_DEAL
(
    SEQ_NO         VARCHAR(50) not null
        primary key,
    TRADE_ID       VARCHAR(50),
    INSTRUCTION_ID VARCHAR(50),
    CUSTOMER_ID    VARCHAR(50),
    SALE_DEPID     VARCHAR(50),
    TRADE_NO       VARCHAR(50),
    SEAT_NUMBER    VARCHAR(50),
    PORTFOLIO_ID   VARCHAR(50),
    FINPROD_ID     VARCHAR(50),
    MARKET_ID      VARCHAR(50),
    FINPROD_TYPE   VARCHAR(50),
    FINPROD_NAME   VARCHAR(50),
    TRADE_MARKET   VARCHAR(50),
    TRADE_PLAT     VARCHAR(50),
    TRADE_DATE     DATE,
    SETTLE_DATE    DATE,
    ACCOUNT        VARCHAR(50),
    SEC_ACCT_ID    VARCHAR(50),
    PS             VARCHAR(50),
    SETTLEMEN_TYPE VARCHAR(50),
    ORDER_DATE     DATE,
    CCY            VARCHAR(50),
    TERM_TYPE      VARCHAR(50),
    STAMP_FEE      NUMBER(30, 2),
    JSF_AMT        NUMBER(30, 2),
    GHF_AMT        NUMBER(30, 2),
    ZGF_AMT        NUMBER(30, 2),
    SXF_AMT        NUMBER(30, 2),
    SETTLE_FEE     NUMBER(30, 2),
    COMMISSION_AMT NUMBER(30, 2),
    OTHER_FEEAMT   NUMBER(30, 2),
    TRADE_AMT      NUMBER(30, 2),
    TRADE_NUM      NUMBER(30, 2),
    HAPPEN_AMT     NUMBER(30, 2),
    PRICE          NUMBER(30, 2),
    INT_AMT        NUMBER(30, 2),
    TRADE_FEE      NUMBER(30, 2),
    CREATE_USER    VARCHAR(20) not null,
    CREATE_DEPT    VARCHAR(32),
    CREATE_TIME    DATE         not null,
    UPDATE_USER    VARCHAR(20) not null,
    UPDATE_TIME    DATE         not null,
    HK_MARKET      VARCHAR(32)
);
comment on table STL_SEC_RECON_DEAL is '券商对账单';
comment on column STL_SEC_RECON_DEAL.SEQ_NO is '券商对账单流水号';
comment on column STL_SEC_RECON_DEAL.TRADE_ID is '成交编号';
comment on column STL_SEC_RECON_DEAL.INSTRUCTION_ID is '委托编号';
comment on column STL_SEC_RECON_DEAL.CUSTOMER_ID is '券商代码';
comment on column STL_SEC_RECON_DEAL.SALE_DEPID is '营业部代码';
comment on column STL_SEC_RECON_DEAL.TRADE_NO is '股东代码';
comment on column STL_SEC_RECON_DEAL.SEAT_NUMBER is '席位号';
comment on column STL_SEC_RECON_DEAL.PORTFOLIO_ID is '组合代码';
comment on column STL_SEC_RECON_DEAL.FINPROD_ID is '金融产品代码';
comment on column STL_SEC_RECON_DEAL.MARKET_ID is '市场代码';
comment on column STL_SEC_RECON_DEAL.FINPROD_TYPE is '金融产品类型';
comment on column STL_SEC_RECON_DEAL.FINPROD_NAME is '金融产品名称';
comment on column STL_SEC_RECON_DEAL.TRADE_MARKET is '交易场所';
comment on column STL_SEC_RECON_DEAL.TRADE_PLAT is '交易平台';
comment on column STL_SEC_RECON_DEAL.TRADE_DATE is '交易日期';
comment on column STL_SEC_RECON_DEAL.SETTLE_DATE is '交割日期';
comment on column STL_SEC_RECON_DEAL.ACCOUNT is '资产账户';
comment on column STL_SEC_RECON_DEAL.SEC_ACCT_ID is '证券账户';
comment on column STL_SEC_RECON_DEAL.PS is '交易方向';
comment on column STL_SEC_RECON_DEAL.SETTLEMEN_TYPE is '交收方式';
comment on column STL_SEC_RECON_DEAL.ORDER_DATE is '委托日期';
comment on column STL_SEC_RECON_DEAL.CCY is '交易币种';
comment on column STL_SEC_RECON_DEAL.TERM_TYPE is '期限品种';
comment on column STL_SEC_RECON_DEAL.STAMP_FEE is '印花税';
comment on column STL_SEC_RECON_DEAL.JSF_AMT is '经手费';
comment on column STL_SEC_RECON_DEAL.GHF_AMT is '过户费';
comment on column STL_SEC_RECON_DEAL.ZGF_AMT is '证管费';
comment on column STL_SEC_RECON_DEAL.SXF_AMT is '手续费';
comment on column STL_SEC_RECON_DEAL.SETTLE_FEE is '结算费';
comment on column STL_SEC_RECON_DEAL.COMMISSION_AMT is '佣金';
comment on column STL_SEC_RECON_DEAL.OTHER_FEEAMT is '其他费用';
comment on column STL_SEC_RECON_DEAL.TRADE_AMT is '成交金额';
comment on column STL_SEC_RECON_DEAL.TRADE_NUM is '成交数量';
comment on column STL_SEC_RECON_DEAL.HAPPEN_AMT is '清算金额';
comment on column STL_SEC_RECON_DEAL.PRICE is '成交价格';
comment on column STL_SEC_RECON_DEAL.INT_AMT is '利息金额';
comment on column STL_SEC_RECON_DEAL.TRADE_FEE is '交易规费';
comment on column STL_SEC_RECON_DEAL.CREATE_USER is '创建人';
comment on column STL_SEC_RECON_DEAL.CREATE_DEPT is '创建部门';
comment on column STL_SEC_RECON_DEAL.CREATE_TIME is '创建时间';
comment on column STL_SEC_RECON_DEAL.UPDATE_USER is '更新人';
comment on column STL_SEC_RECON_DEAL.UPDATE_TIME is '更新时间';
comment on column STL_SEC_RECON_DEAL.HK_MARKET is '港股通市场';