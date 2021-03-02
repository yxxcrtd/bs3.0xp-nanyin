ALTER TABLE chb_asset_info ADD chb_ISSUER_ID VARCHAR2(32);
COMMENT ON COLUMN chb_asset_info.chb_ISSUER_ID IS '发行人Id' ;

ALTER TABLE chb_asset_info ADD chb_MANAGER_ID VARCHAR2(32);
COMMENT ON COLUMN chb_asset_info.chb_MANAGER_ID IS '管理人Id' ;

ALTER TABLE chb_asset_info ADD chb_FINANCIER_ID VARCHAR2(32);
COMMENT ON COLUMN chb_asset_info.chb_FINANCIER_ID IS '融资人Id' ;

ALTER TABLE chb_asset_info ADD PERPETUAL_FLAG VARCHAR2(50);
COMMENT ON COLUMN chb_asset_info.PERPETUAL_FLAG IS '是否永续' ;

ALTER TABLE chb_asset_info ADD OPTION_FLAG VARCHAR2(50);
COMMENT ON COLUMN chb_asset_info.OPTION_FLAG IS '是否含权' ;


ALTER TABLE chb_asset_info ADD IS_BJSZQ VARCHAR2(50);
COMMENT ON COLUMN chb_asset_info.IS_BJSZQ IS '是否北金所债权' ;

ALTER TABLE mst_customer_info_add ADD INDUSTRY3 VARCHAR2(50);
COMMENT ON COLUMN mst_customer_info_add.INDUSTRY3 IS '行业三级分类' ;

ALTER TABLE mst_customer_info_add ADD INDUSTRY4 VARCHAR2(50);
COMMENT ON COLUMN mst_customer_info_add.INDUSTRY4 IS '行业四级分类' ;

-- Create table
create table CHB_PRD_RATE
(
  seqno            CHAR(36) not null,
  prod_id          VARCHAR2(50) not null,
  change_date      DATE,
  profit_type       VARCHAR2(50),
  change_type       VARCHAR2(50),
  prod_yield     NUMBER(30,14),
  prod_benchmark_rate     NUMBER(30,14),
  unit_value_total NUMBER(30,14),
  unit_value         NUMBER(30,14),
  seven_day_rate         NUMBER(30,14),
  org_code         VARCHAR2(20),
  dept_code        VARCHAR2(20),
  status           VARCHAR2(50),
  rev_date         DATE,
  data_source      VARCHAR2(20),
  update_user      VARCHAR2(20),
  update_time      TIMESTAMP(6),
  create_user      VARCHAR2(20),
  create_time      TIMESTAMP(6),
  remark           VARCHAR2(1000),
  ccy              VARCHAR2(50) default 'CNY' not null
);

-- Add comments to the table
comment on table CHB_PRD_RATE
  is '产品利率维护表';
-- Add comments to the columns
comment on column CHB_PRD_RATE.seqno
  is '交易编号';
comment on column CHB_PRD_RATE.prod_id
  is '产品代码';
comment on column CHB_PRD_RATE.change_date
  is '变动日期';
comment on column CHB_PRD_RATE.profit_type
  is '产品收益类型:01净值型,02预期收益型,03	货币型';
  comment on column CHB_PRD_RATE.change_type
    is '变动类型';
comment on column CHB_PRD_RATE.prod_yield
  is '产品最新收益率';
  comment on column CHB_PRD_RATE.prod_benchmark_rate
  is '产品最新业绩基准';
comment on column CHB_PRD_RATE.unit_value_total
  is '单位累计净值';
comment on column CHB_PRD_RATE.unit_value
  is '单位净值';
  comment on column CHB_PRD_RATE.seven_day_rate
  is '七日年化收益率';
comment on column CHB_PRD_RATE.org_code
  is '机构代码';
comment on column CHB_PRD_RATE.dept_code
  is '部门代码';
comment on column CHB_PRD_RATE.status
  is '数据状态';
comment on column CHB_PRD_RATE.rev_date
  is '撤销日';
comment on column CHB_PRD_RATE.data_source
  is '数据来源';
comment on column CHB_PRD_RATE.update_user
  is '更新人';
comment on column CHB_PRD_RATE.update_time
  is '更新时间';
comment on column CHB_PRD_RATE.create_user
  is '创建人';
comment on column CHB_PRD_RATE.create_time
  is '创建时间';
comment on column CHB_PRD_RATE.remark
  is '备注';
comment on column CHB_PRD_RATE.ccy
  is '币种';
alter table CHB_PRD_RATE
  add constraint CHB_PRD_RATE_PK primary key (SEQNO);


  -- Create table
  create table CHB_ASSET_RATE
  (
    seqno               CHAR(36) not null,
    asset_id            VARCHAR2(50) not null,
    finprod_type2       VARCHAR2(50),
    profit_type         VARCHAR2(50),
    change_date         DATE,
    asset_rate         NUMBER(30,14),
    asset_bonusReinvest_rate NUMBER(30,14),
    close_price_full    NUMBER(30,14),
    cost_amt           NUMBER(30,14),
    trade_id            VARCHAR2(32),
    org_code            VARCHAR2(20),
    dept_code           VARCHAR2(20),
    status              VARCHAR2(50),
    rev_date            DATE,
    data_source         VARCHAR2(20),
    update_user         VARCHAR2(20),
    update_time         TIMESTAMP(6),
    create_user         VARCHAR2(20),
    create_time         TIMESTAMP(6),
    remark              VARCHAR2(1000),
    ccy                 VARCHAR2(50) default 'CNY' not null,
    scino　　　　　　　　　VARCHAR2(32)
  );

  -- Add comments to the table
  comment on table CHB_ASSET_RATE
    is '资产利率维护表';
  -- Add comments to the columns
  comment on column CHB_ASSET_RATE.seqno
    is '交易编号';
  comment on column CHB_ASSET_RATE.asset_id
    is '资产代码';
  comment on column CHB_ASSET_RATE.finprod_type2
    is '资产类型';
    comment on column CHB_ASSET_RATE.profit_type
      is '资产收益类型';
  comment on column CHB_ASSET_RATE.change_date
    is '变动日期';
  comment on column CHB_ASSET_RATE.asset_rate
    is '资产最新收益率';
  comment on column CHB_ASSET_RATE.asset_bonusReinvest_rate
    is '资产分红或红利再投单位净值';
  comment on column CHB_ASSET_RATE.close_price_full
    is '收盘价';
  comment on column CHB_ASSET_RATE.cost_amt
    is '成本';
  comment on column CHB_ASSET_RATE.trade_id
     is '交易编号';
  comment on column CHB_ASSET_RATE.org_code
    is '机构代码';
  comment on column CHB_ASSET_RATE.dept_code
    is '部门代码';
  comment on column CHB_ASSET_RATE.status
    is '数据状态';
  comment on column CHB_ASSET_RATE.rev_date
    is '撤销日';
  comment on column CHB_ASSET_RATE.data_source
    is '数据来源';
  comment on column CHB_ASSET_RATE.update_user
    is '更新人';
  comment on column CHB_ASSET_RATE.update_time
    is '更新时间';
  comment on column CHB_ASSET_RATE.create_user
    is '创建人';
  comment on column CHB_ASSET_RATE.create_time
    is '创建时间';
  comment on column CHB_ASSET_RATE.remark
    is '备注';
  comment on column CHB_ASSET_RATE.ccy
    is '币种';
  comment on column CHB_ASSET_RATE.scino
    is '资金账户流水号';
  -- Create/Recreate primary, unique and foreign key constraints
  alter table CHB_ASSET_RATE
    add constraint CHB_ASSET_RATE_PK primary key (SEQNO);

