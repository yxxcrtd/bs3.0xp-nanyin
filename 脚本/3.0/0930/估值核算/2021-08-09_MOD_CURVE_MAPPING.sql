drop table MOD_CURVE_MAPPING ;
create table MOD_CURVE_MAPPING 
(
   SEQ_NO               VARCHAR2(50)         not null,
   CURVE_GROUP_CODE     CHAR(10),
   CURVE_GROUP_NAME     VARCHAR2(50),
   CURVE_CODE           VARCHAR2(50),
   CURVE_TYPE           CHAR(10),
   CURVE_NAME           VARCHAR2(50),
   GRADE_RESULT         VARCHAR2(50),
   GRADE_RESULT_NAME    VARCHAR2(50),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_CURVE_MAPPING primary key (SEQ_NO)
);

comment on table MOD_CURVE_MAPPING is
'曲线族-曲线映射表';

comment on column MOD_CURVE_MAPPING.SEQ_NO is
'流水号';

comment on column MOD_CURVE_MAPPING.CURVE_GROUP_CODE is
'曲线族代码';

comment on column MOD_CURVE_MAPPING.CURVE_GROUP_NAME is
'曲线组名称，企业债、国债收益率曲线';

comment on column MOD_CURVE_MAPPING.CURVE_CODE is
'曲线代码';

comment on column MOD_CURVE_MAPPING.CURVE_TYPE is
'曲线类型';

comment on column MOD_CURVE_MAPPING.CURVE_NAME is
'曲线名称';

comment on column MOD_CURVE_MAPPING.GRADE_RESULT is
'评级结果';

comment on column MOD_CURVE_MAPPING.GRADE_RESULT_NAME is
'评级结果名称';

comment on column MOD_CURVE_MAPPING.CREATE_USER is
'创建人';

comment on column MOD_CURVE_MAPPING.CREATE_DEPT is
'创建部门';

comment on column MOD_CURVE_MAPPING.CREATE_TIME is
'创建时间';

comment on column MOD_CURVE_MAPPING.UPDATE_USER is
'更新人';

comment on column MOD_CURVE_MAPPING.UPDATE_TIME is
'更新时间';
