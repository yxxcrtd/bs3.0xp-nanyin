create table MOD_STANDARD_GRADE_MAPPING 
(
   SEQ_NO               VARCHAR2(50)         not null,
   GRADE_INST           VARCHAR2(50),
   O_TYPE               VARCHAR2(50),
   GRADE_RESULT         VARCHAR2(50),
   GRADE_STANDARD_RESULT VARCHAR2(50),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null
);

comment on table MOD_STANDARD_GRADE_MAPPING is
'标准评级映射表';

comment on column MOD_STANDARD_GRADE_MAPPING.SEQ_NO is
'流水号';

comment on column MOD_STANDARD_GRADE_MAPPING.GRADE_INST is
'评级机构';

comment on column MOD_STANDARD_GRADE_MAPPING.O_TYPE is
'评级类型';

comment on column MOD_STANDARD_GRADE_MAPPING.GRADE_RESULT is
'外部评级结果';

comment on column MOD_STANDARD_GRADE_MAPPING.GRADE_STANDARD_RESULT is
'标准评级结果';

comment on column MOD_STANDARD_GRADE_MAPPING.CREATE_USER is
'创建人';

comment on column MOD_STANDARD_GRADE_MAPPING.CREATE_DEPT is
'创建部门';

comment on column MOD_STANDARD_GRADE_MAPPING.CREATE_TIME is
'创建时间';

comment on column MOD_STANDARD_GRADE_MAPPING.UPDATE_USER is
'更新人';

comment on column MOD_STANDARD_GRADE_MAPPING.UPDATE_TIME is
'更新时间';
