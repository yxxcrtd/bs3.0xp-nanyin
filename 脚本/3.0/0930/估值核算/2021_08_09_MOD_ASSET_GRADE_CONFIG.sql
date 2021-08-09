-- Create table
create table MOD_ASSET_GRADE_CONFIG
(
  seqno             VARCHAR2(50) not null,
  asset_type        VARCHAR2(50),
  asset_detail_type VARCHAR2(50),
  grade_org_id      VARCHAR2(50),
  o_type            VARCHAR2(50),
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
);
-- Add comments to the table 
comment on table MOD_ASSET_GRADE_CONFIG
  is '资产大类评级配置';
-- Add comments to the columns 
comment on column MOD_ASSET_GRADE_CONFIG.seqno
  is '流水号';
comment on column MOD_ASSET_GRADE_CONFIG.asset_type
  is '资产类型(证券品种_模型)';
comment on column MOD_ASSET_GRADE_CONFIG.asset_detail_type
  is '资产细分类(数据字典 证券细分类_模型)';
comment on column MOD_ASSET_GRADE_CONFIG.grade_org_id
  is '评级机构（数据字典 评级机构）';
comment on column MOD_ASSET_GRADE_CONFIG.o_type
  is '评级类型（数据字典 评级类型_模型）';
comment on column MOD_ASSET_GRADE_CONFIG.create_user
  is '创建人';
comment on column MOD_ASSET_GRADE_CONFIG.create_dept
  is '创建部门';
comment on column MOD_ASSET_GRADE_CONFIG.create_time
  is '创建时间';
comment on column MOD_ASSET_GRADE_CONFIG.update_user
  is '更新人';
comment on column MOD_ASSET_GRADE_CONFIG.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table MOD_ASSET_GRADE_CONFIG add constraint PK_MOD_ASSET_GRADE_CONFIG primary key (SEQNO);
