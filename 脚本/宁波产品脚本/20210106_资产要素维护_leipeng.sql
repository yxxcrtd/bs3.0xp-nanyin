DELETE FROM SYS_MENU WHERE PAGE_ID IN ('AssetInfoDetail001','AssetInfoDetail002');
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_008', 'REP', null, 'REPORT_001_003', 'AssetInfoDetail001', 8, null, '资产基本信息维护', 'system', null, SysDate, 'system', SysDate);


DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('AssetInfoDetail001','AssetInfoDetail002');
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetInfoDetail001', 'SVR', '/chb/assetinfodetail/AssetInfoDetail001', 'report/chb/assetinfodetail/AssetInfoDetail001', 'REPORT_001_003_008', null, null, '02', '02', '资产基本信息维护', 'system', null, sysdate, 'system', sysdate);


insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetInfoDetail002', 'SVR', '/chb/assetinfodetail/AssetInfoDetail002', 'report/chb/assetinfodetail/AssetInfoDetail002', null, null, null, '02', '02', '资产基本信息维护详情', 'system', null, sysdate, 'system', sysdate);


-- Create table
create table CHB_ASSET_INFO_ERRODATA
(
  datauuid        CHAR(36) not null,
  asset_code      VARCHAR2(500),
  asset_name      VARCHAR2(500),
  asset_type      VARCHAR2(500),
  asset_type2     VARCHAR2(500),
  vdate           VARCHAR2(500),
  mdate           VARCHAR2(500),
  org_asset_type1 VARCHAR2(500),
  org_asset_type2 VARCHAR2(500),
  org_asset_type3 VARCHAR2(500),
  org_asset_type4 VARCHAR2(500),
  org_asset_type5 VARCHAR2(500),
  org_data        CLOB,
  err_msg         CLOB,
  status          VARCHAR2(10),
  update_user     VARCHAR2(20),
  create_user     VARCHAR2(32),
  update_time     TIMESTAMP(6),
  create_time     TIMESTAMP(6),
  workflow_id     VARCHAR2(20),
  org_code        VARCHAR2(20),
  dept_code       VARCHAR2(32),
  create_dept     VARCHAR2(32)
);
-- Add comments to the table 
comment on table CHB_ASSET_INFO_ERRODATA
  is '资产基本信息接口检查不通过数据';
-- Add comments to the columns 
comment on column CHB_ASSET_INFO_ERRODATA.datauuid
  is 'uuid主键';
comment on column CHB_ASSET_INFO_ERRODATA.asset_code
  is '行内资产负债编码';
comment on column CHB_ASSET_INFO_ERRODATA.asset_name
  is '资产名称'; 
comment on column CHB_ASSET_INFO_ERRODATA.asset_type
  is '资产类型';
comment on column CHB_ASSET_INFO_ERRODATA.asset_type2
  is '资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL';
comment on column CHB_ASSET_INFO_ERRODATA.vdate
  is '起息日';
comment on column CHB_ASSET_INFO_ERRODATA.mdate
  is '到期日';
comment on column CHB_ASSET_INFO_ERRODATA.org_asset_type1
  is '上游资产一级分类';
comment on column CHB_ASSET_INFO_ERRODATA.org_asset_type2
  is '上游资产二级分类';
comment on column CHB_ASSET_INFO_ERRODATA.org_asset_type3
  is '上游资产三级分类';
comment on column CHB_ASSET_INFO_ERRODATA.org_asset_type4
  is '上游资产四级分类';
comment on column CHB_ASSET_INFO_ERRODATA.org_asset_type5
  is '上游资产五级分类';
comment on column CHB_ASSET_INFO_ERRODATA.org_data
  is '原始数据';
comment on column CHB_ASSET_INFO_ERRODATA.err_msg
  is '错误信息';
comment on column CHB_ASSET_INFO_ERRODATA.status
  is '有效状态';
comment on column CHB_ASSET_INFO_ERRODATA.update_user
  is '更新人';
comment on column CHB_ASSET_INFO_ERRODATA.create_user
  is '创建人';
comment on column CHB_ASSET_INFO_ERRODATA.update_time
  is '更新时间';
comment on column CHB_ASSET_INFO_ERRODATA.create_time
  is '创建时间';
comment on column CHB_ASSET_INFO_ERRODATA.workflow_id
  is '工作流代码';
comment on column CHB_ASSET_INFO_ERRODATA.org_code
  is '机构代码';
comment on column CHB_ASSET_INFO_ERRODATA.dept_code
  is '部门代码';
comment on column CHB_ASSET_INFO_ERRODATA.create_dept
  is '创建部门';

alter table CHB_ASSET_INFO_ERRODATA
  add constraint CHB_ASSET_INFO_ERRODATA_PK primary key (DATAUUID);
