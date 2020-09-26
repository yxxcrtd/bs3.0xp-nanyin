-- Create table
create table TM_APP_AUDIT_TAB_CONFIT
(
  id            VARCHAR2(32) not null,
  business_type VARCHAR2(32),
  tab_name      VARCHAR2(32),
  tab_id        VARCHAR2(32),
  tm_createtime TIMESTAMP(6) not null,
  tm_creater    VARCHAR2(10) not null,
  tm_orgid      VARCHAR2(20) not null,
  tm_updatetime TIMESTAMP(6),
  tm_updater    VARCHAR2(10),
  tm_effectflag CHAR(1) not null,
  tm_field1     VARCHAR2(20),
  tm_field2     VARCHAR2(50),
  tm_field3     VARCHAR2(100)
);
 
-- Add comments to the table 
comment on table TM_APP_AUDIT_TAB_CONFIT
  is 'APP审批详情配置表';
-- Add comments to the columns 
comment on column TM_APP_AUDIT_TAB_CONFIT.business_type
  is '业务类型';
comment on column TM_APP_AUDIT_TAB_CONFIT.tab_name
  is '导航栏名称（展示tab）';
comment on column TM_APP_AUDIT_TAB_CONFIT.tab_id
  is 'tabid';
comment on column TM_APP_AUDIT_TAB_CONFIT.tm_creater
  is '创建人';
comment on column TM_APP_AUDIT_TAB_CONFIT.tm_orgid
  is '所属机构ID';
comment on column TM_APP_AUDIT_TAB_CONFIT.tm_updatetime
  is '修改日期';
comment on column TM_APP_AUDIT_TAB_CONFIT.tm_updater
  is '修改人';
comment on column TM_APP_AUDIT_TAB_CONFIT.tm_effectflag
  is '有效标识 "E" - 有效 "D" - 失效';
comment on column TM_APP_AUDIT_TAB_CONFIT.tm_field1
  is '备用字段1';
comment on column TM_APP_AUDIT_TAB_CONFIT.tm_field2
  is '备用字段2';
comment on column TM_APP_AUDIT_TAB_CONFIT.tm_field3
  is '备用字段3';
