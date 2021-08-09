drop table BOK_ASSET_MARKET_INFO;
-- Create table
create table BOK_ASSET_MARKET_INFO
(
  seq_no           VARCHAR2(32),
  bookset_id       VARCHAR2(50),
  happen_date      DATE,
  book_date        DATE,
  finprod_id       VARCHAR2(50),
  finprod_type     VARCHAR2(50),
  market_code      VARCHAR2(32),
  inv_aim          VARCHAR2(50),
  value_source     VARCHAR2(50),
  value_date       DATE,
  market_price     NUMBER(30,14),
  scheme_id        VARCHAR2(50),
  scheme_detail_id VARCHAR2(50),
  data_source        VARCHAR2(50),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null
);
-- Add comments to the table 
comment on table BOK_ASSET_MARKET_INFO
  is '资产行情信息表';
comment on column BOK_ASSET_MARKET_INFO.seq_no
  is '流水号';
comment on column BOK_ASSET_MARKET_INFO.bookset_id
  is '账套代码';
comment on column BOK_ASSET_MARKET_INFO.happen_date
  is '发生日期';
comment on column BOK_ASSET_MARKET_INFO.book_date
  is '入账日期';
comment on column BOK_ASSET_MARKET_INFO.finprod_id
  is '金融产品代码';
comment on column BOK_ASSET_MARKET_INFO.finprod_type
  is '金融产品类型';
comment on column BOK_ASSET_MARKET_INFO.inv_aim
  is '投资目的';
comment on column BOK_ASSET_MARKET_INFO.value_source
  is '估值来源';
comment on column BOK_ASSET_MARKET_INFO.value_date
  is '行情日期';
comment on column BOK_ASSET_MARKET_INFO.market_price
  is '行情价格';
comment on column BOK_ASSET_MARKET_INFO.scheme_id
  is '估值方案代码';
comment on column BOK_ASSET_MARKET_INFO.scheme_detail_id
  is '估值方案明细代码';
comment on column BOK_ASSET_MARKET_INFO.data_source
  is '数据来源';
comment on column BOK_ASSET_MARKET_INFO.create_user
  is '创建人';
comment on column BOK_ASSET_MARKET_INFO.create_dept
  is '创建部门';
comment on column BOK_ASSET_MARKET_INFO.create_time
  is '创建时间';
comment on column BOK_ASSET_MARKET_INFO.update_user
  is '更新人';
comment on column BOK_ASSET_MARKET_INFO.update_time
  is '更新时间';

alter table BOK_ASSET_MARKET_INFO add constraint PK_BOK_ASSET_MARKET_INFO primary key (seq_no);
create index Index_BOK_ASSET_MARKET_INFO on BOK_ASSET_MARKET_INFO (BOOKSET_ID, HAPPEN_DATE, BOOK_DATE);