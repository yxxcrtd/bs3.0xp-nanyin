-- Create table
drop table PBC_REPORT_ASSET_CONFIGURATION;
create table PBC_REPORT_ASSET_CONFIGURATION
(
  detailuuid   CHAR(36) not null,
  reporttype   VARCHAR2(50),
  propertycode VARCHAR2(50),
  balance      NUMBER(30,2),
  reportuuid   CHAR(36)
);
-- Add comments to the table 
comment on table PBC_REPORT_ASSET_CONFIGURATION
  is '资管产品资产配置情况';
-- Add comments to the columns 
comment on column PBC_REPORT_ASSET_CONFIGURATION.detailuuid
  is '明细统计uuid';
comment on column PBC_REPORT_ASSET_CONFIGURATION.reporttype
  is '报表类型 4_1 资管产品资产配置情况';
comment on column PBC_REPORT_ASSET_CONFIGURATION.propertycode
  is '属性代码';
comment on column PBC_REPORT_ASSET_CONFIGURATION.balance
  is '余额';
comment on column PBC_REPORT_ASSET_CONFIGURATION.reportuuid
  is '主表关联id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_REPORT_ASSET_CONFIGURATION  add constraint PBC_REPORT_ASSET_CONFIG_PK primary key (DETAILUUID);
