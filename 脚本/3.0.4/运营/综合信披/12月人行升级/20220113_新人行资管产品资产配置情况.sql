-- Create table
drop table PBC_REPORT_ASSET_CONFIG_DAT;
create table PBC_REPORT_ASSET_CONFIG_DAT
(
  detailuuid   CHAR(36) not null,
  reporttype   VARCHAR2(50),
  propertycode VARCHAR2(50),
  balance      NUMBER(30,2),
  reportuuid   CHAR(36)
);
-- Add comments to the table 
comment on table PBC_REPORT_ASSET_CONFIG_DAT
  is '新人行资管产品资产配置情况';
-- Add comments to the columns 
comment on column PBC_REPORT_ASSET_CONFIG_DAT.detailuuid
  is '明细统计uuid';
comment on column PBC_REPORT_ASSET_CONFIG_DAT.reporttype
  is '报表类型 4_1 资管产品资产配置情况';
comment on column PBC_REPORT_ASSET_CONFIG_DAT.propertycode
  is '属性代码';
comment on column PBC_REPORT_ASSET_CONFIG_DAT.balance
  is '余额';
comment on column PBC_REPORT_ASSET_CONFIG_DAT.reportuuid
  is '主表关联id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PBC_REPORT_ASSET_CONFIG_DAT  add constraint PBC_REPORT_ASSET_CONFIG_DAT_PK primary key (DETAILUUID);
