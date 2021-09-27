--收益率曲线管理tab页
--删除收益率曲线管理-待开发
delete from sys_menu where MENU_ID='ASSET_MANAGEMENT_001_003_007';
delete from sys_menu where MENU_ID='ASSET_MANAGEMENT_001_003_010';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ASSET_MANAGEMENT_001_003_010', 'MST', null, 'ASSET_MANAGEMENT_001_003', 'MstcurveTabManage001', 7, '2', '收益率曲线管理', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID='MstcurveTabManage001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstcurveTabManage001', 'MST', '/valuation/basicdata/mstcurvedefmanage/MstcurveTabManage001', 'valuation/basicdata/mstcurvetabmanage/MstcurveTabManage001', 'ASSET_MANAGEMENT_001_003_010', null, null, '02', '01', '收益率曲线管理', 'system', null, sysdate, 'system', sysdate);

--曲线来源与曲线类型的联动
DELETE from sys_datadict_list_link  where dict_code='CURVE_TYPE' and linked_dict_code='CURVE_SOURCE';
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CURVE_TYPE', 'MA', 'CURVE_SOURCE', '01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CURVE_TYPE', 'SP', 'CURVE_SOURCE', '01', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CURVE_TYPE', 'FM', 'CURVE_SOURCE', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CURVE_TYPE', 'SP', 'CURVE_SOURCE', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CURVE_TYPE', 'FS', 'CURVE_SOURCE', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CURVE_TYPE', 'MA', 'CURVE_SOURCE', '02', 'system', null, sysdate, 'system', sysdate);

--曲线族表定义
create table MST_CURVE_GROUP
(
  curve_group_code        VARCHAR2(32) not null,
  curve_group_name        VARCHAR2(200),
  value_source      VARCHAR2(50),
  remark            VARCHAR2(1000),
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
);

comment on table MST_CURVE_GROUP
  is '曲线族定义';
comment on column MST_CURVE_GROUP.curve_group_code
  is '曲线代码';
comment on column MST_CURVE_GROUP.curve_group_name
  is '曲线名称';
comment on column MST_CURVE_GROUP.value_source
  is '数据来源，数据字典，VALUATION_SOURCE';
comment on column MST_CURVE_GROUP.remark
  is '备注';
comment on column MST_CURVE_GROUP.create_user
  is '创建人';
comment on column MST_CURVE_GROUP.create_dept
  is '创建部门';
comment on column MST_CURVE_GROUP.create_time
  is '创建时间';
comment on column MST_CURVE_GROUP.update_user
  is '更新人';
comment on column MST_CURVE_GROUP.update_time
  is '更新时间';

--曲线族管理数据流
delete from  sys_wfl_business_module t   where page_id='MstCurveGroupManage002';

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('MstCurveGroupManage002', 'MstCurveGroupManage001_btn_delete', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('MstCurveGroupManage002', 'MstCurveGroupManage002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('MstCurveGroupManage002', 'MstCurveGroupManage002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

delete from  sys_page t   where page_id='MstCurveGroupManage002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstCurveGroupManage002', 'MST', '/valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage002', 'valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage002', null, null, null, '02', '01', '收益率曲线族管理详情', 'system', null, sysdate, 'system', sysdate);

delete from  sys_page t   where page_id='MstCurveGroupManage001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstCurveGroupManage001', 'MST', '/valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage001', 'valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage001', null, null, null, '02', '01', '收益率曲线族管理', 'system', null, sysdate, 'system', sysdate);