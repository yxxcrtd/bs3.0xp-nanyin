/*==============================================================*/
/* DBMS name:      ORACLE Version 12g                           */
/* Created on:     2021年8月3日                       */
/*==============================================================*/

/*==============================================================*/
/* Table: MOD_GRADE_PD_CONFIG                                       */
/*==============================================================*/
create table MOD_GRADE_PD_CONFIG
(
   SEQNO                VARCHAR2(50)         not null,
   VOUCH_YEAR           NUMBER(10),
   GRADE_RESULT         VARCHAR2(50),
   FORWARD_YEAR_PD      NUMBER(30,6),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_GRADE_PD_CONFIG primary key (SEQNO)
);

comment on table MOD_GRADE_PD_CONFIG is
'评级PD配置';

comment on column MOD_GRADE_PD_CONFIG.SEQNO is
'流水号';

comment on column MOD_GRADE_PD_CONFIG.VOUCH_YEAR is
'年份';

comment on column MOD_GRADE_PD_CONFIG.GRADE_RESULT is
'评级结果（数据字典 评级结果）';

comment on column MOD_GRADE_PD_CONFIG.FORWARD_YEAR_PD is
'前瞻前年度边际';

comment on column MOD_GRADE_PD_CONFIG.CREATE_USER is
'创建人';

comment on column MOD_GRADE_PD_CONFIG.CREATE_DEPT is
'创建部门';

comment on column MOD_GRADE_PD_CONFIG.CREATE_TIME is
'创建时间';

comment on column MOD_GRADE_PD_CONFIG.UPDATE_USER is
'更新人';

comment on column MOD_GRADE_PD_CONFIG.UPDATE_TIME is
'更新时间';


/*==============================================================*/
/* Table: MOD_CONFIG_USEAGE                                       */
/*==============================================================*/
create table MOD_CONFIG_USEAGE
(
  seqno        VARCHAR2(50) not null,
  config_seqno VARCHAR2(50) not null,
  config_type  VARCHAR2(50) not null,
  use_process  VARCHAR2(50) not null,
  create_user  VARCHAR2(20) not null,
  create_dept  VARCHAR2(32),
  create_time  TIMESTAMP(6) not null,
  update_user  VARCHAR2(20) not null,
  update_time  TIMESTAMP(6) not null
)
comment on table MOD_CONFIG_USEAGE
  is '减值配置使用情况表';
comment on column MOD_CONFIG_USEAGE.seqno
  is '流水号';
comment on column MOD_CONFIG_USEAGE.config_seqno
  is '配置流水号';
comment on column MOD_CONFIG_USEAGE.config_type
  is '配置类型（数据字典-配置类型_模型）';
comment on column MOD_CONFIG_USEAGE.use_process
  is '使用过程（数据字典-减值批处理过程_模型）';
comment on column MOD_CONFIG_USEAGE.create_user
  is '创建人';
comment on column MOD_CONFIG_USEAGE.create_dept
  is '创建部门';
comment on column MOD_CONFIG_USEAGE.create_time
  is '创建时间';
comment on column MOD_CONFIG_USEAGE.update_user
  is '更新人';
comment on column MOD_CONFIG_USEAGE.update_time
  is '更新时间';
