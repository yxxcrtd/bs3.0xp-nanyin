--估值模型股息增长率配置 库表
drop table MOD_STOCKDIVITRATE_CONFIG cascade constraints;

/*==============================================================*/
/* Table: MOD_STOCKDIVITRATE_CONFIG                             */
/*==============================================================*/
-- Create table
create table MOD_STOCKDIVITRATE_CONFIG
(
  seqno                 VARCHAR2(32) not null,
  finprod_id            VARCHAR2(50),
  finprod_name          VARCHAR2(50),
  sec_type_model        VARCHAR2(50) not null,
  sec_detail_type_model VARCHAR2(50) not null,
  stock_intrate         NUMBER(30,14),
  create_user           VARCHAR2(20) not null,
  create_dept           VARCHAR2(32),
  create_time           TIMESTAMP(6) not null,
  update_user           VARCHAR2(20) not null,
  update_time           TIMESTAMP(6) not null
);
-- Add comments to the table 
comment on table MOD_STOCKDIVITRATE_CONFIG
  is '估值模型股息增长率配置';
-- Add comments to the columns 
comment on column MOD_STOCKDIVITRATE_CONFIG.seqno
  is '流水号';
comment on column MOD_STOCKDIVITRATE_CONFIG.finprod_id
  is '金融产品代码，存储上游系统内部代码，比如金融产品代码';
comment on column MOD_STOCKDIVITRATE_CONFIG.finprod_name
  is '金融产品名称';
comment on column MOD_STOCKDIVITRATE_CONFIG.sec_type_model
  is '资产类型';
comment on column MOD_STOCKDIVITRATE_CONFIG.sec_detail_type_model
  is '资产细分类';
comment on column MOD_STOCKDIVITRATE_CONFIG.stock_intrate
  is '预期股息增长率';
comment on column MOD_STOCKDIVITRATE_CONFIG.create_user
  is '创建人';
comment on column MOD_STOCKDIVITRATE_CONFIG.create_dept
  is '创建部门';
comment on column MOD_STOCKDIVITRATE_CONFIG.create_time
  is '创建时间';
comment on column MOD_STOCKDIVITRATE_CONFIG.update_user
  is '更新人';
comment on column MOD_STOCKDIVITRATE_CONFIG.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table MOD_STOCKDIVITRATE_CONFIG add constraint PK_MOD_STOCKDIVITRATE_CONFIG primary key (SEQNO) using index;
