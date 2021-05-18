-------------------------------------柜台信息维护------------------------------------------------------
delete from sys_menu where menu_id = 'BASICDATA_MANAGEMENT_001_001_017';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASICDATA_MANAGEMENT_001_001_017', 'MST', null, 'BASICDATA_MANAGEMENT_001_001', 'MstSaleDepartment001', 17, '02', '柜台信息维护', 'system', 'system', sysdate, 'system', sysdate);

delete from sys_page where page_id = 'MstSaleDepartment001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSaleDepartment001', 'MST', '/invest/basicdata/mstsaledepartment/MstSaleDepartment001', 'invest/basicdata/mstsaledepartment/MstSaleDepartment001', 'BASICDATA_MANAGEMENT_001_001_017', null, null, '02', '01', '柜台信息维护', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where page_id = 'MstSaleDepartment002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSaleDepartment002', 'MST', '/invest/basicdata/mstsaledepartment/MstSaleDepartment002', 'invest/basicdata/mstsaledepartment/MstSaleDepartment002', null, null, null, '02', '01', '柜台信息维护', 'system', null, sysdate, 'system', sysdate);

--SYS_PAGE_ELEMENT
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'MstSaleDepartment001';
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'MstSaleDepartment002';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSaleDepartment001', 'MstSaleDepartment001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSaleDepartment001', 'MstSaleDepartment001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSaleDepartment001', 'MstSaleDepartment001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSaleDepartment001', 'MstSaleDepartment001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSaleDepartment001', 'MstSaleDepartment001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSaleDepartment002', 'MstSaleDepartment001_btn_save', '01', '02', null, '保存', 'system', null, sysdate, 'system', sysdate);

--字典值
delete from sys_datadict where DICT_CODE = 'DECLARE_UNIT';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DECLARE_UNIT', 'TG', '申报单位', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'DECLARE_UNIT';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DECLARE_UNIT', '1', '手', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DECLARE_UNIT', '2', '张', 1, '0', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict where DICT_CODE = 'SPECIAL_HANDLE_TYPE';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SPECIAL_HANDLE_TYPE', 'TG', '特殊处理业务', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'SPECIAL_HANDLE_TYPE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SPECIAL_HANDLE_TYPE', '1', '质押出入库', 1, '0', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict where DICT_CODE = 'COUNTER_INTER_TYPE';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COUNTER_INTER_TYPE', 'TG', '柜台接口类型', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'COUNTER_INTER_TYPE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COUNTER_INTER_TYPE', '1', '恒生', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COUNTER_INTER_TYPE', '2', '金证w版', 2, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COUNTER_INTER_TYPE', '3', '金仕达', 3, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COUNTER_INTER_TYPE', '4', '金证u版', 4, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COUNTER_INTER_TYPE', '5', 'ctp', 5, '0', 'system', null, sysdate, 'system', sysdate);

-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('MST_SALE_DEPARTMENT') ;
    if num > 0 then
        execute immediate 'drop table MST_SALE_DEPARTMENT' ;
    end if;
end;
/
create table MST_SALE_DEPARTMENT
(
  sale_depid   VARCHAR2(32) not null,
  sale_depname VARCHAR2(50),
  broker_id    VARCHAR2(50),
  ip_addr      VARCHAR2(50),
  port_num     VARCHAR2(50),
  contact_addr VARCHAR2(1000),
  contact_tel  VARCHAR2(50),
  fax_no       VARCHAR2(50),
  ip_addr2     VARCHAR2(50),
  port_num2    VARCHAR2(50),
  declare_unit VARCHAR2(50),
  is_netreturn VARCHAR2(50),
  handle_type  VARCHAR2(50),
  inter_type   VARCHAR2(50),
  create_user  VARCHAR2(20) not null,
  create_dept  VARCHAR2(32),
  create_time  TIMESTAMP(6) not null,
  update_user  VARCHAR2(20) not null,
  update_time  TIMESTAMP(6) not null,
  sh_seatno    VARCHAR2(32),
  sz_seatno    VARCHAR2(32),
  serverinfo   VARCHAR2(1024),
  zj_seatno    VARCHAR2(32),
  sq_seatno    VARCHAR2(32)
)
;
-- Add comments to the table 
comment on table MST_SALE_DEPARTMENT
  is '营业部信息表';
-- Add comments to the columns 
comment on column MST_SALE_DEPARTMENT.sale_depid
  is '营业部代码';
comment on column MST_SALE_DEPARTMENT.sale_depname
  is '营业部名称';
comment on column MST_SALE_DEPARTMENT.broker_id
  is '所属券商';
comment on column MST_SALE_DEPARTMENT.ip_addr
  is 'IP地址';
comment on column MST_SALE_DEPARTMENT.port_num
  is '端口';
comment on column MST_SALE_DEPARTMENT.contact_addr
  is '联系地址';
comment on column MST_SALE_DEPARTMENT.contact_tel
  is '联系电话';
comment on column MST_SALE_DEPARTMENT.fax_no
  is '传真号码';
comment on column MST_SALE_DEPARTMENT.ip_addr2
  is '备用IP地址';
comment on column MST_SALE_DEPARTMENT.port_num2
  is '备用端口';
comment on column MST_SALE_DEPARTMENT.declare_unit
  is '申报单位';
comment on column MST_SALE_DEPARTMENT.is_netreturn
  is '是否净价回报';
comment on column MST_SALE_DEPARTMENT.handle_type
  is '特殊处理业务';
comment on column MST_SALE_DEPARTMENT.inter_type
  is '柜台接口类型';
comment on column MST_SALE_DEPARTMENT.create_user
  is '创建人';
comment on column MST_SALE_DEPARTMENT.create_dept
  is '创建部门';
comment on column MST_SALE_DEPARTMENT.create_time
  is '创建时间';
comment on column MST_SALE_DEPARTMENT.update_user
  is '更新人';
comment on column MST_SALE_DEPARTMENT.update_time
  is '更新时间';
comment on column MST_SALE_DEPARTMENT.sh_seatno
  is '上交所席位号';
comment on column MST_SALE_DEPARTMENT.sz_seatno
  is '深交所席位号';
comment on column MST_SALE_DEPARTMENT.serverinfo
  is '柜台参数，柜台系统连接串';
comment on column MST_SALE_DEPARTMENT.zj_seatno
  is '中金所席位号';
comment on column MST_SALE_DEPARTMENT.sq_seatno
  is '上期所席位号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table MST_SALE_DEPARTMENT
  add constraint PK_MST_SALE_DEPARTMENT primary key (SALE_DEPID)
  using index 
  ;

alter table MST_CUSTOMER_INFO ADD IS_FUTURE_COM VARCHAR(50);
COMMENT ON COLUMN MST_CUSTOMER_INFO.IS_FUTURE_COM
IS '是否期货公司';

------------------------------------------------席位信息管理----------------------------------------------------
delete from sys_menu where MENU_ID = 'BASICDATA_MANAGEMENT_001_001_018';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASICDATA_MANAGEMENT_001_001_018', 'MST', null, 'BASICDATA_MANAGEMENT_001_001', 'MstSeatInfo001', 18, '02', '席位信息管理', 'system', 'system', sysdate, 'system', sysdate);

delete from sys_page where page_id = 'MstSeatInfo001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo001', 'MST', '/invest/basicdata/mstseatinfo/MstSeatInfo001', 'invest/basicdata/mstseatinfo/MstSeatInfo001', 'BASICDATA_MANAGEMENT_001_001_018', null, null, '02', '01', '席位信息管理', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where page_id = 'MstSeatInfo002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo002', 'MST', '/invest/basicdata/mstseatinfo/MstSeatInfo002', 'invest/basicdata/mstseatinfo/MstSeatInfo002', null, null, null, '02', '01', '席位信息新建', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'MstSeatInfo001';
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'MstSeatInfo002';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo001', 'MstSeatInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo001', 'MstSeatInfo001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo001', 'MstSeatInfo001_btn_clear', '01', '02', null, '重置', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo001', 'MstSeatInfo001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo001', 'MstSeatInfo001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo001', 'MstSeatInfo001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo002', 'MstSeatInfo001_btn_save', '01', '02', null, '保存', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo002', 'MstSeatInfo001_btn_submit', '01', '02', null, '提交', 'system', null, sysdate, 'system', sysdate);


delete from SYS_PAGE_DICTLIST where PAGE_ID = 'MstSeatInfo002';
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo002', '999999', 'TRADE_PLACE', '03', null, null, 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo002', '999999', 'TRADE_PLACE', '04', null, null, 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo002', '999999', 'TRADE_PLACE', '12', null, null, 'system', null, sysdate, 'system', sysdate);

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'MstSeatInfo002';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo002', 'MstSeatInfo001_btn_delete', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo002', 'MstSeatInfo002_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstSeatInfo002', 'MstSeatInfo002_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate);

--数据字典
delete from sys_datadict_list where dict_code = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'MST26';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'MST26', '席位信息管理', 926, '0', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'TRADE_PLACE' and ITEM_CODE = '12';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TRADE_PLACE', '12', '中国金融期货交易所', 11, '0', 'system', null, sysdate, 'system', sysdate);

-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('MST_SEAT') ;
    if num > 0 then
        execute immediate 'drop table MST_SEAT' ;
    end if;
end;
/
create table MST_SEAT
(
  seat_id      VARCHAR2(50) not null,
  customer_id  VARCHAR2(32),
  trade_market VARCHAR2(50),
  create_user  VARCHAR2(20) not null,
  create_dept  VARCHAR2(32),
  create_time  TIMESTAMP(6) not null,
  update_user  VARCHAR2(20) not null,
  update_time  TIMESTAMP(6) not null,
  remark       VARCHAR2(1024)
)
;
-- Add comments to the table 
comment on table MST_SEAT
  is '席位';
-- Add comments to the columns 
comment on column MST_SEAT.seat_id
  is '席位号';
comment on column MST_SEAT.customer_id
  is '券商代码';
comment on column MST_SEAT.trade_market
  is '交易市场';
comment on column MST_SEAT.create_user
  is '创建人';
comment on column MST_SEAT.create_dept
  is '创建部门';
comment on column MST_SEAT.create_time
  is '创建时间';
comment on column MST_SEAT.update_user
  is '更新人';
comment on column MST_SEAT.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table MST_SEAT
  add constraint PK_MST_SEAT primary key (SEAT_ID)
  using index 
  ;


----------------------------------------------------------标准券再折算率维护------------------------------------------
delete from sys_menu where MENU_ID = 'BASICDATA_MANAGEMENT_001_001_019';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASICDATA_MANAGEMENT_001_001_019', 'MST', null, 'BASICDATA_MANAGEMENT_001_001', 'StandardCouponReConversionRate001', 19, '02', '标准券再折算率维护', 'system', 'system', sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'StandardCouponReConversionRate001';
delete from sys_page where PAGE_ID = 'StandardCouponReConversionRate002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('StandardCouponReConversionRate001', 'MST', '/invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate001', 'invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate001', 'BASICDATA_MANAGEMENT_001_001_019', null, null, '02', '01', '标准券再折算率维护', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('StandardCouponReConversionRate002', 'MST', '/invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate002', 'invest/basicdata/standardcouponreconversionrate/StandardCouponReConversionRate002', 'BASICDATA_MANAGEMENT_001_001_019', null, null, '02', '01', '标准券再折算率维护新建', 'system', null, sysdate, 'system', sysdate);


delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'StandardCouponReConversionRate001';
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'StandardCouponReConversionRate002';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('StandardCouponReConversionRate001', 'StandardCouponReConversionRate001_btn_delete', 'oneApproval', 'system', null, sysdate, 'admin', sysdate);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('StandardCouponReConversionRate002', 'StandardCouponReConversionRate001_btn_delete', 'oneApproval', 'system', null, sysdate, 'admin', sysdate);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('StandardCouponReConversionRate002', 'StandardCouponReConversionRate002_btn_delete', 'oneApproval', 'system', null, sysdate, 'admin', sysdate);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('StandardCouponReConversionRate002', 'StandardCouponReConversionRate002_btn_save', 'oneApproval', 'system', null, sysdate, 'admin', sysdate);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('StandardCouponReConversionRate002', 'StandardCouponReConversionRate002_btn_submit', 'oneApproval', 'system', null, sysdate, 'admin', sysdate);

--数据字典
delete from sys_datadict where DICT_CODE = 'All_BROKER';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('All_BROKER', 'SYS', '所有', null, '03', 'system', null, sysdate, 'system', sysdate);


delete from sys_datadict_list where DICT_CODE = 'All_BROKER';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('All_BROKER', 'BROKER', '所有', 11, '0', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'SCRCR01';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'SCRCR01', '标准券再折算率', 1, '0', 'system', null, sysdate, 'system', sysdate);


-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('STD_BOND_MORE_CONVRATE_INFO') ;
    if num > 0 then
        execute immediate 'drop table STD_BOND_MORE_CONVRATE_INFO' ;
    end if;
end;
/
create table STD_BOND_MORE_CONVRATE_INFO
(
  broker_id     VARCHAR2(50) not null,
  acct_no       VARCHAR2(50) not null,
  more_convrate NUMBER(30,14) not null,
  create_user   VARCHAR2(20) not null,
  create_dept   VARCHAR2(32),
  create_time   TIMESTAMP(6) not null,
  update_user   VARCHAR2(20) not null,
  update_time   TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table STD_BOND_MORE_CONVRATE_INFO
  is '标准券再折算率信息';
-- Add comments to the columns 
comment on column STD_BOND_MORE_CONVRATE_INFO.broker_id
  is '券商代码';
comment on column STD_BOND_MORE_CONVRATE_INFO.acct_no
  is '资金账号';
comment on column STD_BOND_MORE_CONVRATE_INFO.more_convrate
  is '再折算率';
comment on column STD_BOND_MORE_CONVRATE_INFO.create_user
  is '创建人';
comment on column STD_BOND_MORE_CONVRATE_INFO.create_dept
  is '创建部门';
comment on column STD_BOND_MORE_CONVRATE_INFO.create_time
  is '创建时间';
comment on column STD_BOND_MORE_CONVRATE_INFO.update_user
  is '更新人';
comment on column STD_BOND_MORE_CONVRATE_INFO.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table STD_BOND_MORE_CONVRATE_INFO
  add constraint PK_STD_BOND_MORE_CONVRATE_INFO primary key (BROKER_ID, ACCT_NO)
  using index 
  ;
  
  
---页面草稿
delete from SYS_TRASH_NOT_TARGET_PAGE where SEQ_NO = 'A96EA0A5352F043FE0538C5AA8C0281A';
insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID)
values ('A96EA0A5352F043FE0538C5AA8C0281A', 'StandardCouponReConversionRate002');

delete from SYS_TRASH_NOT_TARGET_PAGE where SEQ_NO = 'A96EA0A5352F043FE0538C5AA8C0282A';
insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID)
values ('A96EA0A5352F043FE0538C5AA8C0282A', 'MstSeatInfo002');

delete from SYS_TRASH_NOT_TARGET_PAGE where SEQ_NO = 'A96EA0A5352F043FE0538C5AA8C0283A';
insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID)
values ('A96EA0A5352F043FE0538C5AA8C0283A', 'MstSaleDepartment002');
















