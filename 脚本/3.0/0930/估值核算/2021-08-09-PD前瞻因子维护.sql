drop table MOD_PD_FACTOR_CONFIG ;
create table MOD_PD_FACTOR_CONFIG
(
  seqno             VARCHAR2(50) not null,
  vouch_year        VARCHAR2(10),
  vouch_quarter     VARCHAR2(50),
  optimistic_factor NUMBER(30,6),
  basic_factor      NUMBER(30,6),
  pessimism_factor  NUMBER(30,6),
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
);
-- Add comments to the table 
comment on table MOD_PD_FACTOR_CONFIG
  is '前瞻因子配置';
-- Add comments to the columns 
comment on column MOD_PD_FACTOR_CONFIG.seqno
  is '流水号';
comment on column MOD_PD_FACTOR_CONFIG.vouch_year
  is '年份';
comment on column MOD_PD_FACTOR_CONFIG.vouch_quarter
  is '季度';
comment on column MOD_PD_FACTOR_CONFIG.optimistic_factor
  is '乐观因子';
comment on column MOD_PD_FACTOR_CONFIG.basic_factor
  is '基础因子';
comment on column MOD_PD_FACTOR_CONFIG.pessimism_factor
  is '悲观因子';
comment on column MOD_PD_FACTOR_CONFIG.create_user
  is '创建人';
comment on column MOD_PD_FACTOR_CONFIG.create_dept
  is '创建部门';
comment on column MOD_PD_FACTOR_CONFIG.create_time
  is '创建时间';
comment on column MOD_PD_FACTOR_CONFIG.update_user
  is '更新人';
comment on column MOD_PD_FACTOR_CONFIG.update_time
  is '更新时间';



drop table MOD_PD_WEIGHT_CONFIG ;
create table MOD_PD_WEIGHT_CONFIG 
(
   SEQNO                VARCHAR2(50)         not null,
   OPTIMISTIC_WEIGHT    NUMBER(22,10),
   BASIC_WEIGHT         NUMBER(22,10),
   PESSIMISM_WEIGHT     NUMBER(22,10),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null
);

comment on table MOD_PD_WEIGHT_CONFIG is
'前瞻因子权重配置';

comment on column MOD_PD_WEIGHT_CONFIG.SEQNO is
'流水号';

comment on column MOD_PD_WEIGHT_CONFIG.OPTIMISTIC_WEIGHT is
'乐观权重';

comment on column MOD_PD_WEIGHT_CONFIG.BASIC_WEIGHT is
'基础权重';

comment on column MOD_PD_WEIGHT_CONFIG.PESSIMISM_WEIGHT is
'悲观权重';

comment on column MOD_PD_WEIGHT_CONFIG.CREATE_USER is
'创建人';

comment on column MOD_PD_WEIGHT_CONFIG.CREATE_DEPT is
'创建部门';

comment on column MOD_PD_WEIGHT_CONFIG.CREATE_TIME is
'创建时间';

comment on column MOD_PD_WEIGHT_CONFIG.UPDATE_USER is
'更新人';

comment on column MOD_PD_WEIGHT_CONFIG.UPDATE_TIME is
'更新时间';

drop table MOD_CONFIG_USEAGE ;
create table MOD_CONFIG_USEAGE 
(
   SEQNO                VARCHAR2(50)         not null,
   CONFIG_SEQNO         VARCHAR2(50),
   CONFIG_TYPE          VARCHAR2(50),
   USE_PROCESS          VARCHAR2(50),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
);

comment on table MOD_CONFIG_USEAGE is
'减值配置使用情况表';

comment on column MOD_CONFIG_USEAGE.SEQNO is
'流水号';

comment on column MOD_CONFIG_USEAGE.CONFIG_SEQNO is
'配置流水号';

comment on column MOD_CONFIG_USEAGE.CONFIG_TYPE is
'配置类型（数据字典-配置类型_模型）';

comment on column MOD_CONFIG_USEAGE.USE_PROCESS is
'使用过程（数据字典-减值批处理过程_模型）';

comment on column MOD_CONFIG_USEAGE.CREATE_USER is
'创建人';

comment on column MOD_CONFIG_USEAGE.CREATE_DEPT is
'创建部门';

comment on column MOD_CONFIG_USEAGE.CREATE_TIME is
'创建时间';

comment on column MOD_CONFIG_USEAGE.UPDATE_USER is
'更新人';

comment on column MOD_CONFIG_USEAGE.UPDATE_TIME is
'更新时间';
commit;