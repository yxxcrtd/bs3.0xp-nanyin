-- Add/modify columns 
alter table MOD_ASEET_MAPPING modify input1 not null;
alter table MOD_ASEET_MAPPING modify input2 not null;
alter table MOD_ASEET_MAPPING modify output1 not null;
alter table MOD_ASEET_MAPPING modify output2 not null;
alter table MOD_ASEET_MAPPING modify output3 not null;
alter table MOD_ASEET_MAPPING modify output4 not null;
alter table MOD_ASEET_MAPPING modify output5 not null;

drop index MOD_ASEET_MAPPING_INDEX;
drop index MOD_ASEET_MAPPING_INDEX1;
-- Create/Recreate indexes 
create index MOD_ASEET_MAPPING_INDEX on MOD_ASEET_MAPPING (MAPPING_TYPE, INPUT1, INPUT2);
create index MOD_ASEET_MAPPING_INDEX1 on MOD_ASEET_MAPPING (MAPPING_TYPE, OUTPUT5);
  
  
-- Add/modify columns 
alter table MOD_CURVE_MAPPING modify curve_group_code not null;
alter table MOD_CURVE_MAPPING modify curve_code not null;
alter table MOD_CURVE_MAPPING modify curve_type not null;
alter table MOD_CURVE_MAPPING modify grade_result not null;

drop index MOD_CURVE_MAPPING_INDEX;
-- Create/Recreate indexes 
create index MOD_CURVE_MAPPING_INDEX on MOD_CURVE_MAPPING (CURVE_GROUP_CODE, CURVE_TYPE, GRADE_RESULT);
  
-- Add/modify columns 
alter table MOD_CURVE_CONFIG modify curve_group_code not null;
alter table MOD_CURVE_CONFIG modify curve_type not null;
alter table MOD_CURVE_CONFIG modify issmooth not null;

drop index MOD_CURVE_CONFIG_INDEX;
drop index MOD_CURVE_CONFIG_INDEX1;
-- Create/Recreate indexes 
create index MOD_CURVE_CONFIG_INDEX on MOD_CURVE_CONFIG (SEC_TYPE_MODEL, SEC_DETAIL_TYPE_MODEL);
create index MOD_CURVE_CONFIG_INDEX1 on MOD_CURVE_CONFIG (SEC_TYPE_MODEL, SEC_DETAIL_TYPE_MODEL, FINPROD_ID);
  
  
-- Add/modify columns 
alter table MOD_BINTREE_CONFIG modify finprod_id not null;
alter table MOD_BINTREE_CONFIG modify stock_bordsttus not null;
alter table MOD_BINTREE_CONFIG modify bintree_step not null;
alter table MOD_BINTREE_CONFIG modify curve_code not null;

drop index MOD_BINTREE_CONFIG_INDEX;
-- Create/Recreate indexes 
create index MOD_BINTREE_CONFIG_INDEX on MOD_BINTREE_CONFIG (FINPROD_ID);
  
  
-- Add/modify columns 
alter table MOD_RISK_PREMIUM modify sec_detail_type_model not null;
alter table MOD_RISK_PREMIUM modify finprod_id not null;
alter table MOD_RISK_PREMIUM modify grade_source not null;
alter table MOD_RISK_PREMIUM modify grade_result not null;
alter table MOD_RISK_PREMIUM modify basedate not null;
alter table MOD_RISK_PREMIUM modify spread not null;
alter table MOD_RISK_PREMIUM modify b_fullprice not null;
alter table MOD_RISK_PREMIUM modify mod_method not null;

drop index MOD_RISK_PREMIUM_INDEX;
-- Create/Recreate indexes 
create index MOD_RISK_PREMIUM_INDEX on MOD_RISK_PREMIUM (FINPROD_ID, BASEDATE, MOD_METHOD);
  
  
-- Add/modify columns 
alter table MOD_STOCKDIVITRATE_CONFIG modify finprod_id not null;
alter table MOD_STOCKDIVITRATE_CONFIG modify stock_intrate not null;

drop index MOD_STOCKDIVITRATE_CONFIG_INDEX;
-- Create/Recreate indexes 
create index MOD_STOCKDIVITRATE_CONFIG_INDEX on MOD_STOCKDIVITRATE_CONFIG (FINPROD_ID);

drop index MOD_CURVE_RESULT_DETAIL_INDEX;
drop table MOD_CURVE_RESULT_DETAIL;
-- Create table
create table MOD_CURVE_RESULT_DETAIL
(
  seqno        VARCHAR2(50) not null,
  result_seqno VARCHAR2(50) not null,
  finprod_id   VARCHAR2(50) not null,
  cdate        DATE not null,
  cash_date    DATE not null,
  period       NUMBER(30,12),
  cash_amt     NUMBER(30,2),
  dis_rate     NUMBER(30,8),
  dis_factor   NUMBER(30,2),
  dis_cash_amt NUMBER(30,2),
  create_user  VARCHAR2(20) not null,
  create_dept  VARCHAR2(32),
  create_time  TIMESTAMP(6) not null,
  update_user  VARCHAR2(20) not null,
  update_time  TIMESTAMP(6) not null
);
-- Add comments to the table 
comment on table MOD_CURVE_RESULT_DETAIL
  is '模型估值结果明细';
-- Add comments to the columns 
comment on column MOD_CURVE_RESULT_DETAIL.seqno
  is '序号';
comment on column MOD_CURVE_RESULT_DETAIL.result_seqno
  is '结果表序号';
comment on column MOD_CURVE_RESULT_DETAIL.finprod_id
  is '金融产品代码';
comment on column MOD_CURVE_RESULT_DETAIL.cdate
  is '日期';
comment on column MOD_CURVE_RESULT_DETAIL.cash_date
  is '现金流日期';
comment on column MOD_CURVE_RESULT_DETAIL.period
  is '期限（年化）';
comment on column MOD_CURVE_RESULT_DETAIL.cash_amt
  is '现金流金额';
comment on column MOD_CURVE_RESULT_DETAIL.dis_rate
  is '折现利率';
comment on column MOD_CURVE_RESULT_DETAIL.dis_factor
  is '折现因子';
comment on column MOD_CURVE_RESULT_DETAIL.dis_cash_amt
  is '折算后金额';
comment on column MOD_CURVE_RESULT_DETAIL.create_user
  is '创建人';
comment on column MOD_CURVE_RESULT_DETAIL.create_dept
  is '创建部门';
comment on column MOD_CURVE_RESULT_DETAIL.create_time
  is '创建时间';
comment on column MOD_CURVE_RESULT_DETAIL.update_user
  is '更新人';
comment on column MOD_CURVE_RESULT_DETAIL.update_time
  is '更新时间';
-- Create/Recreate indexes 
create index MOD_CURVE_RESULT_DETAIL_INDEX on MOD_CURVE_RESULT_DETAIL (FINPROD_ID, CDATE);
-- Create/Recreate primary, unique and foreign key constraints 
alter table MOD_CURVE_RESULT_DETAIL add constraint PK_MOD_CURVE_RESULT_DETAIL primary key (SEQNO)
  using index ;
