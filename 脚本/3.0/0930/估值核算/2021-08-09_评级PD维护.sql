drop table MOD_GRADE_PD_CONFIG cascade constraints;

/*==============================================================*/
/* Table: MOD_GRADE_PD_CONFIG                                   */
/*==============================================================*/
create table MOD_GRADE_PD_CONFIG 
(
   SEQNO                VARCHAR2(50)         not null,
   VOUCH_YEAR           NUMBER(10),
   GRADE_ORG_ID         VARCHAR2(50),
   O_TYPE               VARCHAR2(50),
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

comment on column MOD_GRADE_PD_CONFIG.GRADE_ORG_ID is
'评级机构（数据字典 评级机构）';

comment on column MOD_GRADE_PD_CONFIG.O_TYPE is
'评级类型（数据字典 评级类型）';

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
