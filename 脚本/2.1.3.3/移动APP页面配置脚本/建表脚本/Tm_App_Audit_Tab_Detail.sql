-- Create table
create table TM_APP_AUDIT_TAB_DETAIL
(
  id            VARCHAR2(32) not null,
  tab_config_id VARCHAR2(32),
  key           VARCHAR2(100) not null,
  title         VARCHAR2(3000),
  tm_createtime TIMESTAMP(6) not null,
  tm_creater    VARCHAR2(10) not null,
  tm_orgid      VARCHAR2(20) not null,
  tm_updatetime TIMESTAMP(6),
  tm_updater    VARCHAR2(10),
  tm_effectflag CHAR(1) not null,
  tm_field1     VARCHAR2(1000),
  tm_field2     VARCHAR2(1000),
  tm_field3     VARCHAR2(1000),
  orderby       INTEGER
);
 
-- Add comments to the table 
comment on table TM_APP_AUDIT_TAB_DETAIL
  is 'APP审批详情明细配置表';
-- Add comments to the columns 
comment on column TM_APP_AUDIT_TAB_DETAIL.tm_createtime
  is '创建时间';
comment on column TM_APP_AUDIT_TAB_DETAIL.tm_creater
  is '创建人';
comment on column TM_APP_AUDIT_TAB_DETAIL.tm_orgid
  is '所属机构ID';
comment on column TM_APP_AUDIT_TAB_DETAIL.tm_updatetime
  is '修改日期';
comment on column TM_APP_AUDIT_TAB_DETAIL.tm_updater
  is '修改人';
comment on column TM_APP_AUDIT_TAB_DETAIL.tm_effectflag
  is '有效标识 "E" - 有效 "D" - 失效';
comment on column TM_APP_AUDIT_TAB_DETAIL.tm_field1
  is '备用字段1';
comment on column TM_APP_AUDIT_TAB_DETAIL.tm_field2
  is '备用字段2';
comment on column TM_APP_AUDIT_TAB_DETAIL.tm_field3
  is '备用字段3';
comment on column TM_APP_AUDIT_TAB_DETAIL.orderby
  is '排序';
