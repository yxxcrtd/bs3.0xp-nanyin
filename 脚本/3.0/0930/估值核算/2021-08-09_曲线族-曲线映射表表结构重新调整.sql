

drop table MOD_CURVE_MAPPING;
-- Create table
create table MOD_CURVE_MAPPING
(
  seq_no            VARCHAR2(50) not null,
  curve_group_code  VARCHAR2(50) not null,
  curve_group_name  VARCHAR2(50),
  curve_code        VARCHAR2(50) not null,
  curve_type        VARCHAR2(50) not null,
  curve_name        VARCHAR2(50),
  grade_result      VARCHAR2(50),
  grade_result_name VARCHAR2(50),
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
);

-- Add comments to the table 
comment on table MOD_CURVE_MAPPING
  is '曲线族-曲线映射表';
-- Add comments to the columns 
comment on column MOD_CURVE_MAPPING.seq_no
  is '流水号';
comment on column MOD_CURVE_MAPPING.curve_group_code
  is '曲线族代码';
comment on column MOD_CURVE_MAPPING.curve_group_name
  is '曲线组名称，企业债、国债收益率曲线';
comment on column MOD_CURVE_MAPPING.curve_code
  is '曲线代码';
comment on column MOD_CURVE_MAPPING.curve_type
  is '曲线类型';
comment on column MOD_CURVE_MAPPING.curve_name
  is '曲线名称';
comment on column MOD_CURVE_MAPPING.grade_result
  is '评级结果';
comment on column MOD_CURVE_MAPPING.grade_result_name
  is '评级结果名称';
comment on column MOD_CURVE_MAPPING.create_user
  is '创建人';
comment on column MOD_CURVE_MAPPING.create_dept
  is '创建部门';
comment on column MOD_CURVE_MAPPING.create_time
  is '创建时间';
comment on column MOD_CURVE_MAPPING.update_user
  is '更新人';
comment on column MOD_CURVE_MAPPING.update_time
  is '更新时间';
