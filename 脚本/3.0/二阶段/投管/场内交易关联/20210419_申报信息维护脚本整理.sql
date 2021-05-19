/*==============================================================*/
/* 字典及字典明细                                               */
/*==============================================================*/

/*字典：金融产品其他代码类型*/
delete from sys_datadict where dict_code = 'FINPROD_OTH_CODE_TYPE';
insert into sys_datadict(dict_code, system_code, dict_cnname, dict_enname, update_flg, create_user, create_dept, create_time, update_user, update_time)
values ('FINPROD_OTH_CODE_TYPE', 'TG', '金融产品其他代码类型', null, '02', 'system', null, sysdate, 'system', sysdate);

/*字典明细：金融产品其他代码类型*/
delete from sys_datadict_list where DICT_CODE = 'FINPROD_OTH_CODE_TYPE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FINPROD_OTH_CODE_TYPE', '01', '回售代码', 1, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FINPROD_OTH_CODE_TYPE', '02', '质押代码', 2, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FINPROD_OTH_CODE_TYPE', '03', '可转债信用申购代码', 3, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FINPROD_OTH_CODE_TYPE', '04', '可转债转股代码', 4, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FINPROD_OTH_CODE_TYPE', '05', '配债代码', 5, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FINPROD_OTH_CODE_TYPE', '06', '配股代码', 6, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FINPROD_OTH_CODE_TYPE', '07', '新股申购代码', 7, '0', 'system', null, sysdate, 'system', sysdate);

/*==============================================================*/
/* 菜单                                                         */
/*==============================================================*/

/*菜单：申报信息维护*/
delete from sys_menu where MENU_ID = 'BASICDATA_MANAGEMENT_001_001_016';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASICDATA_MANAGEMENT_001_001_016', 'MST', null, 'BASICDATA_MANAGEMENT_001_001', 'AssetDeclarationCodeMaintenance001', 16, '02', '申报信息维护', 'system', null, sysdate, 'system', sysdate);


/*==============================================================*/
/* 页面                                                         */
/*==============================================================*/

/*页面：申报信息维护*/
delete from sys_page where PAGE_ID = 'AssetDeclarationCodeMaintenance001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetDeclarationCodeMaintenance001', 'MST', '/invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance001', 'invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance001', 'BASICDATA_MANAGEMENT_001_001_016', null, null, '02', '01', '资产申报代码维护', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'AssetDeclarationCodeMaintenance002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetDeclarationCodeMaintenance002', 'MST', '/invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance002', 'invest/basicdata/assetdeclarationcodemaintenance/AssetDeclarationCodeMaintenance002', null, null, null, '02', '01', '资产申报代码新建', 'system', null, sysdate, 'system', sysdate);

/*菜单权限：申报信息维护*/
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetDeclarationCodeMaintenance001' and ELEMENT_ID = 'AssetDeclarationCodeMaintenance001_btn_search';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetDeclarationCodeMaintenance001', 'AssetDeclarationCodeMaintenance001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetDeclarationCodeMaintenance001' and ELEMENT_ID = 'AssetDeclarationCodeMaintenance001_btn_clear';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetDeclarationCodeMaintenance001', 'AssetDeclarationCodeMaintenance001_btn_clear', '01', '02', null, '重置', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetDeclarationCodeMaintenance001' and ELEMENT_ID = 'AssetDeclarationCodeMaintenance001_btn_add';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetDeclarationCodeMaintenance001', 'AssetDeclarationCodeMaintenance001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetDeclarationCodeMaintenance001' and ELEMENT_ID = 'AssetDeclarationCodeMaintenance001_TBLROW_edit';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetDeclarationCodeMaintenance001', 'AssetDeclarationCodeMaintenance001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetDeclarationCodeMaintenance001' and ELEMENT_ID = 'AssetDeclarationCodeMaintenance001_btn_delete';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetDeclarationCodeMaintenance001', 'AssetDeclarationCodeMaintenance001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetDeclarationCodeMaintenance001' and ELEMENT_ID = 'AssetDeclarationCodeMaintenance001_btn_import';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetDeclarationCodeMaintenance001', 'AssetDeclarationCodeMaintenance001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate);


delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetDeclarationCodeMaintenance001' and ELEMENT_ID = 'AssetDeclarationCodeMaintenance001_btn_export';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetDeclarationCodeMaintenance001', 'AssetDeclarationCodeMaintenance001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetDeclarationCodeMaintenance002' and ELEMENT_ID = 'AssetDeclarationCodeMaintenance001_btn_save';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetDeclarationCodeMaintenance002', 'AssetDeclarationCodeMaintenance001_btn_save', '01', '02', null, '保存', 'system', null, sysdate, 'system', sysdate);


/*==============================================================*/
/* 建表                                                         */
/*==============================================================*/

-- Create table
/*金融产品其他代码表*/
drop table FIN_PRODUCT_OTH_CODE;
create table FIN_PRODUCT_OTH_CODE
(
  finprod_id          VARCHAR2(50) not null,
  branch              NUMBER(10) not null,
  oth_type            VARCHAR2(50) not null,
  oth_code_type       VARCHAR2(50),
  oth_code            VARCHAR2(32),
  oth_name            VARCHAR2(200),
  declared_price      NUMBER(30,14),
  declared_start_date DATE,
  declared_end_date   DATE,
  remark              VARCHAR2(1000),
  create_user         VARCHAR2(20) not null,
  create_dept         VARCHAR2(32),
  create_time         TIMESTAMP(6) not null,
  update_user         VARCHAR2(20) not null,
  update_time         TIMESTAMP(6) not null,
  constraint PK_FIN_PRODUCT_OTH_CODE primary key (FINPROD_ID, BRANCH,OTH_TYPE)
);
-- Add comments to the table 
comment on table FIN_PRODUCT_OTH_CODE
  is '金融产品其他代码，除二级交易码以外的其他代码，比如回售代码、质押代码、可转债信用申购代码、可转债转股代码、配债代码、配股代码等';
-- Add comments to the columns 
comment on column FIN_PRODUCT_OTH_CODE.finprod_id
  is '金融产品代码';
comment on column FIN_PRODUCT_OTH_CODE.branch
  is '分支序号';
comment on column FIN_PRODUCT_OTH_CODE.oth_type
  is '业务类型
01：回售
02：标准券出入库
03：可转债信用申购
04：可转债转股
05：配债
06：配股
07：新股申购';
comment on column FIN_PRODUCT_OTH_CODE.oth_code_type
  is '业务类型（暂时恢复，废弃）';
comment on column FIN_PRODUCT_OTH_CODE.oth_code
  is '申报代码';
comment on column FIN_PRODUCT_OTH_CODE.oth_name
  is '申报资产名称';
comment on column FIN_PRODUCT_OTH_CODE.declared_price
  is '申报价格';
comment on column FIN_PRODUCT_OTH_CODE.declared_start_date
  is '申报起始日';
comment on column FIN_PRODUCT_OTH_CODE.declared_end_date
  is '申报终止日';
comment on column FIN_PRODUCT_OTH_CODE.remark
  is '备注';
comment on column FIN_PRODUCT_OTH_CODE.create_user
  is '创建人';
comment on column FIN_PRODUCT_OTH_CODE.create_dept
  is '创建部门';
comment on column FIN_PRODUCT_OTH_CODE.create_time
  is '创建时间';
comment on column FIN_PRODUCT_OTH_CODE.update_user
  is '更新人';
comment on column FIN_PRODUCT_OTH_CODE.update_time
  is '更新时间';
