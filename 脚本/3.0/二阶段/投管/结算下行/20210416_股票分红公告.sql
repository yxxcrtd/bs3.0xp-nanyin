delete from sys_menu where MENU_ID = 'BASICDATA_MANAGEMENT_001_002_025';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASICDATA_MANAGEMENT_001_002_025', 'MST', null, 'BASICDATA_MANAGEMENT_001_002', 'MstShareBonusNotice001', 16, '02', '股票分红公告', 'system', 'system', sysdate, 'system', sysdate);


delete from sys_page where page_id = 'MstShareBonusNotice001';
delete from sys_page where page_id = 'MstShareBonusNotice002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstShareBonusNotice001', 'MST', '/invest/basicdata/mstsharebonusnotice/MstShareBonusNotice001', 'invest/basicdata/mstsharebonusnotice/MstShareBonusNotice001', 'BASICDATA_MANAGEMENT_001_002_025', null, null, '02', '01', '股票分红公告', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstShareBonusNotice002', 'MST', '/invest/basicdata/mstsharebonusnotice/MstShareBonusNotice002', 'invest/basicdata/mstsharebonusnotice/MstShareBonusNotice002', 'BASICDATA_MANAGEMENT_001_002_025', null, null, '02', '01', '股票分红公告新建', 'system', null, sysdate, 'system', sysdate);

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'MstShareBonusNotice002';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstShareBonusNotice002', 'MstShareBonusNotice001_btn_delete', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstShareBonusNotice002', 'MstShareBonusNotice002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstShareBonusNotice002', 'MstShareBonusNotice002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate);

--数据字典
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'MST28', '股票分红公告', 928, '0', 'system', null, sysdate, 'system', sysdate);


-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('MST_SHARE_BONUS_NOTICE') ;
    if num > 0 then
        execute immediate 'drop table MST_SHARE_BONUS_NOTICE' ;
    end if;
end;
/
create table MST_SHARE_BONUS_NOTICE
(
  shares_id             VARCHAR2(32) not null,
  reg_date              DATE not null,
  del_date              DATE,
  devident_date         DATE,
  redshare_listing_date DATE,
  per_devident_amt      NUMBER(30,14),
  per_devident_cash     NUMBER(30,14),
  create_user           VARCHAR2(20) not null,
  create_dept           VARCHAR2(32),
  create_time           TIMESTAMP(6) not null,
  update_user           VARCHAR2(20) not null,
  update_time           TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table MST_SHARE_BONUS_NOTICE
  is '股票分红公告';
-- Add comments to the columns 
comment on column MST_SHARE_BONUS_NOTICE.shares_id
  is '股票代码，市场代码';
comment on column MST_SHARE_BONUS_NOTICE.reg_date
  is '权益登记日';
comment on column MST_SHARE_BONUS_NOTICE.del_date
  is '除权除息日';
comment on column MST_SHARE_BONUS_NOTICE.devident_date
  is '派息日';
comment on column MST_SHARE_BONUS_NOTICE.redshare_listing_date
  is '红股上市日';
comment on column MST_SHARE_BONUS_NOTICE.per_devident_amt
  is '每股送转';
comment on column MST_SHARE_BONUS_NOTICE.per_devident_cash
  is '每股派息';
comment on column MST_SHARE_BONUS_NOTICE.create_user
  is '创建人';
comment on column MST_SHARE_BONUS_NOTICE.create_dept
  is '创建部门';
comment on column MST_SHARE_BONUS_NOTICE.create_time
  is '创建时间';
comment on column MST_SHARE_BONUS_NOTICE.update_user
  is '更新人';
comment on column MST_SHARE_BONUS_NOTICE.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table MST_SHARE_BONUS_NOTICE
  add constraint PK_MST_SHARE_BONUS_NOTICE primary key (SHARES_ID, REG_DATE)
  using index 
  ;

























