
/*==============================================================*/
/* 表结构改动                                                   */
/*==============================================================*/

/*组合费用头寸 新增字段：券商代码,席位号*/
ALTER TABLE PTL_FEE_POSITION ADD CUSTOMER_ID VARCHAR2(32);
COMMENT ON COLUMN PTL_FEE_POSITION.CUSTOMER_ID IS '券商代码';

ALTER TABLE PTL_FEE_POSITION ADD SEAT_ID VARCHAR2(50);
COMMENT ON COLUMN PTL_FEE_POSITION.SEAT_ID IS '席位号';

/*金融产品交易表：席位号*/
ALTER TABLE TRD_PRODUCT_DEAL ADD SEAT_ID VARCHAR2(50);
COMMENT ON COLUMN TRD_PRODUCT_DEAL.SEAT_ID IS '席位号';

/*证券账户表: 股东代码,券商名称,交易编码*/
alter table STL_SEC_ACCT_INFO add(BOND_ID VARCHAR2(50));
COMMENT ON COLUMN "STL_SEC_ACCT_INFO"."BOND_ID" IS '股东代码';

alter table STL_SEC_ACCT_INFO add(BROKER_NAME VARCHAR2(100));
COMMENT ON COLUMN "STL_SEC_ACCT_INFO"."BROKER_NAME" IS '券商名称';

alter table STL_SEC_ACCT_INFO add trade_no VARCHAR2(50);
comment on column STL_SEC_ACCT_INFO.trade_no is '交易编码';
