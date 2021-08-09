drop index "Index_9";
drop table MOD_ECL_RESULT_PROCESS cascade constraints;
-- Create table
create table MOD_ECL_RESULT_PROCESS
(
  seqno                   VARCHAR2(50) not null,
  prod_id                 VARCHAR2(50) not null,
  prod_name               VARCHAR2(200),
  portfolio_id            VARCHAR2(50),
  asset_id                VARCHAR2(50) not null,
  asset_name              VARCHAR2(200),
  market_id               VARCHAR2(50) not null,
  type_8                  VARCHAR2(50),
  type_9                  VARCHAR2(50),
  type_10                 VARCHAR2(50),
  type_11                 VARCHAR2(50),
  type_12                 VARCHAR2(50),
  model_asset_type        VARCHAR2(50),
  model_asset_detail_type VARCHAR2(50),
  mdate                   DATE,
  update_time             TIMESTAMP(6) not null,
  financier               VARCHAR2(50),
  grade_org_id            VARCHAR2(50),
  issuer                  VARCHAR2(50),
  actual_credit           VARCHAR2(50),
  sponsor                 VARCHAR2(50),
  risk_level_five         VARCHAR2(50),
  trade_id                VARCHAR2(50),
  trade_date              DATE,
  ps                      VARCHAR2(50),
  share_amt               NUMBER(30,4),
  trade_amt               NUMBER(30,4),
  yield                   NUMBER(30,14),
  paper_rate              NUMBER(30,14),
  plan_end_date           DATE,
  is_clean                VARCHAR2(50),
  dec_date                DATE not null,
  o_type                  VARCHAR2(50),
  result_01               VARCHAR2(50),
  result_02               VARCHAR2(50),
  result_issuer           VARCHAR2(50),
  result_financier        VARCHAR2(50),
  result_sponsor          VARCHAR2(50),
  result_actual_credit    VARCHAR2(50),
  o_type_final            VARCHAR2(50),
  org_id_final            VARCHAR2(50),
  is_fin_licence          VARCHAR2(50),
  result_deal             VARCHAR2(50),
  result_current          VARCHAR2(50),
  prin_overdue_day        NUMBER(20,2),
  int_overdue_day         NUMBER(20,2),
  fin_stage               VARCHAR2(50),
  ema_share_amt           NUMBER(30,4),
  ead                     NUMBER(30,4),
  pre_prospective_pd      NUMBER(30,6),
  prospective_pd          NUMBER(30,6),
  lgd                     NUMBER(30,6),
  pre_prospective_ecl     NUMBER(30,4),
  prospective_ecl         NUMBER(30,4),
  issue_amt               NUMBER(30,2),
  vdate                   DATE,
  coupon_species          VARCHAR2(50),
  issue_price             NUMBER(30,14),
  process                 VARCHAR2(50) not null,
  create_user             VARCHAR2(20) not null,
  create_dept             VARCHAR2(32),
  create_time             TIMESTAMP(6) not null,
  update_user             VARCHAR2(20) not null,
  is_contain_pledge       VARCHAR2(50)
);
-- Add comments to the table 
comment on table MOD_ECL_RESULT_PROCESS
  is '减值结果过程表';
-- Add comments to the columns 
comment on column MOD_ECL_RESULT_PROCESS.seqno
  is '序列号';
comment on column MOD_ECL_RESULT_PROCESS.prod_id
  is '产品代码';
comment on column MOD_ECL_RESULT_PROCESS.prod_name
  is '产品名称';
comment on column MOD_ECL_RESULT_PROCESS.portfolio_id
  is '投资组合代码（有子组合是子组合代码，没有子组合是母组合代码）';
comment on column MOD_ECL_RESULT_PROCESS.asset_id
  is '资产代码';
comment on column MOD_ECL_RESULT_PROCESS.asset_name
  is '资产名称';
comment on column MOD_ECL_RESULT_PROCESS.market_id
  is '市场代码';
comment on column MOD_ECL_RESULT_PROCESS.type_8
  is 'TYPE_8';
comment on column MOD_ECL_RESULT_PROCESS.type_9
  is 'TYPE_9';
comment on column MOD_ECL_RESULT_PROCESS.type_10
  is 'TYPE_10';
comment on column MOD_ECL_RESULT_PROCESS.type_11
  is 'TYPE_11';
comment on column MOD_ECL_RESULT_PROCESS.type_12
  is 'TYPE_12';
comment on column MOD_ECL_RESULT_PROCESS.model_asset_type
  is '模型资产类型';
comment on column MOD_ECL_RESULT_PROCESS.model_asset_detail_type
  is '模型资产细分类';
comment on column MOD_ECL_RESULT_PROCESS.mdate
  is '到期日';
comment on column MOD_ECL_RESULT_PROCESS.update_time
  is '更新时间';
comment on column MOD_ECL_RESULT_PROCESS.financier
  is '融资人';
comment on column MOD_ECL_RESULT_PROCESS.grade_org_id
  is '评级机构';
comment on column MOD_ECL_RESULT_PROCESS.issuer
  is '发行人';
comment on column MOD_ECL_RESULT_PROCESS.actual_credit
  is '实际授信主体';
comment on column MOD_ECL_RESULT_PROCESS.sponsor
  is '担保人';
comment on column MOD_ECL_RESULT_PROCESS.risk_level_five
  is '风险五级分类';
comment on column MOD_ECL_RESULT_PROCESS.trade_id
  is '交易编号';
comment on column MOD_ECL_RESULT_PROCESS.trade_date
  is '交易日期';
comment on column MOD_ECL_RESULT_PROCESS.ps
  is '交易方向';
comment on column MOD_ECL_RESULT_PROCESS.share_amt
  is '交易份额';
comment on column MOD_ECL_RESULT_PROCESS.trade_amt
  is '交易金额';
comment on column MOD_ECL_RESULT_PROCESS.yield
  is '到期收益率';
comment on column MOD_ECL_RESULT_PROCESS.paper_rate
  is '票面利率';
comment on column MOD_ECL_RESULT_PROCESS.plan_end_date
  is '计划截止日';
comment on column MOD_ECL_RESULT_PROCESS.is_clean
  is '是否结清';
comment on column MOD_ECL_RESULT_PROCESS.dec_date
  is '减值日期';
comment on column MOD_ECL_RESULT_PROCESS.o_type
  is '评级类型';
comment on column MOD_ECL_RESULT_PROCESS.result_01
  is '债项评级结果（最新）
债券主体评级结果（最新）
发行人评级结果（最新）
融资人评级结果（最新）
担保人评级结果（最新）
实际授信主体评级结果（最新）
买入时点评级结果
最新评级结果
债项评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.result_02
  is '债券主体评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.result_issuer
  is '发行人评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.result_financier
  is '融资人评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.result_sponsor
  is '担保人评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.result_actual_credit
  is '实际授信主体评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.o_type_final
  is '最终使用评级类型';
comment on column MOD_ECL_RESULT_PROCESS.org_id_final
  is '最终使用主体机构';
comment on column MOD_ECL_RESULT_PROCESS.is_fin_licence
  is '主体是否持有金融牌照';
comment on column MOD_ECL_RESULT_PROCESS.result_deal
  is '买入时点评级结果';
comment on column MOD_ECL_RESULT_PROCESS.result_current
  is '最新评级结果';
comment on column MOD_ECL_RESULT_PROCESS.prin_overdue_day
  is '本金逾期天数';
comment on column MOD_ECL_RESULT_PROCESS.int_overdue_day
  is '利息逾期天数';
comment on column MOD_ECL_RESULT_PROCESS.fin_stage
  is '减值阶段';
comment on column MOD_ECL_RESULT_PROCESS.ema_share_amt
  is '移动平均后份额';
comment on column MOD_ECL_RESULT_PROCESS.ead
  is 'EAD违约风险暴露';
comment on column MOD_ECL_RESULT_PROCESS.pre_prospective_pd
  is '前瞻前PD违约概率%';
comment on column MOD_ECL_RESULT_PROCESS.prospective_pd
  is '前瞻后PD违约概率%';
comment on column MOD_ECL_RESULT_PROCESS.lgd
  is 'LGD违约损失率%';
comment on column MOD_ECL_RESULT_PROCESS.pre_prospective_ecl
  is '前瞻前ECL预期信用损失';
comment on column MOD_ECL_RESULT_PROCESS.prospective_ecl
  is '前瞻后ECL预期信用损失';
comment on column MOD_ECL_RESULT_PROCESS.issue_amt
  is '发行规模';
comment on column MOD_ECL_RESULT_PROCESS.vdate
  is '起息日';
comment on column MOD_ECL_RESULT_PROCESS.coupon_species
  is '息票品种';
comment on column MOD_ECL_RESULT_PROCESS.issue_price
  is '发行价';
comment on column MOD_ECL_RESULT_PROCESS.process
  is '过程';
comment on column MOD_ECL_RESULT_PROCESS.create_user
  is '创建人';
comment on column MOD_ECL_RESULT_PROCESS.create_dept
  is '创建部门';
comment on column MOD_ECL_RESULT_PROCESS.create_time
  is '创建时间';
comment on column MOD_ECL_RESULT_PROCESS.update_user
  is '更新人';
comment on column MOD_ECL_RESULT_PROCESS.is_contain_pledge
  is '是否含有抵质押物';
-- Create/Recreate indexes 
/*==============================================================*/
/* Index: "Index_9"                                             */
/*==============================================================*/
create index "Index_9" on MOD_ECL_RESULT_PROCESS (
   DEC_DATE ASC,
   PROCESS ASC
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table MOD_ECL_RESULT_PROCESS add constraint PK_MOD_ECL_RESULT_PROCESS primary key (SEQNO);


delete from SYS_BATCH_TASK t where t.BEAN_NAME='eadCalCulationBatchTask';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('2043', 'MODEL', '减值模型-EAD计算批处理', null, 'eadCalCulationBatchTask', 30, 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), '02');



commit;