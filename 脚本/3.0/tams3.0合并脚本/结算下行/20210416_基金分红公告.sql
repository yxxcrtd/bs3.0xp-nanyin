delete from sys_menu where MENU_ID = 'BASICDATA_MANAGEMENT_001_002_026';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BASICDATA_MANAGEMENT_001_002_026', 'MST', null, 'BASICDATA_MANAGEMENT_001_002', 'MstFundBonusNotice001', 17, '02', '基金分红公告', 'system', 'system', sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'MstFundBonusNotice001';
delete from sys_page where PAGE_ID = 'MstFundBonusNotice002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstFundBonusNotice001', 'MST', '/invest/basicdata/mstfundbonusnotice/MstFundBonusNotice001', 'invest/basicdata/mstfundbonusnotice/MstFundBonusNotice001', 'BASICDATA_MANAGEMENT_001_002_026', null, null, '02', '01', '基金分红公告', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstFundBonusNotice002', 'MST', '/invest/basicdata/mstfundbonusnotice/MstFundBonusNotice002', 'invest/basicdata/mstfundbonusnotice/MstFundBonusNotice002', 'BASICDATA_MANAGEMENT_001_002_026', null, null, '02', '01', '基金分红公告新建', 'system', null, sysdate, 'system', sysdate);

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'MstFundBonusNotice002';
insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstFundBonusNotice002', 'MstFundBonusNotice001_btn_delete', 'oneApproval', 'system', null, '28-8月 -20 09.43.04.000000 上午', 'system', '28-8月 -20 09.43.04.000000 上午');

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstFundBonusNotice002', 'MstFundBonusNotice002_btn_save', 'oneApproval', 'system', null, '28-8月 -20 09.43.04.000000 上午', 'system', '28-8月 -20 09.43.04.000000 上午');

insert into SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstFundBonusNotice002', 'MstFundBonusNotice002_btn_submit', 'oneApproval', 'system', null, '28-8月 -20 09.43.04.000000 上午', 'system', '28-8月 -20 09.43.04.000000 上午');


--数据字典
delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'MST27';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'MST27', '基金分红公告', 927, '0', 'system', null, sysdate, 'system', sysdate);

-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('MST_FUND_BONUS_NOTICE') ;
    if num > 0 then
        execute immediate 'drop table MST_FUND_BONUS_NOTICE' ;
    end if;
end;
/
create table MST_FUND_BONUS_NOTICE
(
  fund_id               VARCHAR2(32) not null,
  reg_date              DATE not null,
  del_date              DATE,
  devident_date         DATE,
  unit_bonus_unit_value NUMBER(30,14),
  create_user           VARCHAR2(20) not null,
  create_dept           VARCHAR2(32),
  create_time           TIMESTAMP(6) not null,
  update_user           VARCHAR2(20) not null,
  update_time           TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table MST_FUND_BONUS_NOTICE
  is '基金分红公告';
-- Add comments to the columns 
comment on column MST_FUND_BONUS_NOTICE.fund_id
  is '股票代码，市场代码';
comment on column MST_FUND_BONUS_NOTICE.reg_date
  is '权益登记日';
comment on column MST_FUND_BONUS_NOTICE.del_date
  is '除权日';
comment on column MST_FUND_BONUS_NOTICE.devident_date
  is '派息日';
comment on column MST_FUND_BONUS_NOTICE.unit_bonus_unit_value
  is '单位分红净值';
comment on column MST_FUND_BONUS_NOTICE.create_user
  is '创建人';
comment on column MST_FUND_BONUS_NOTICE.create_dept
  is '创建部门';
comment on column MST_FUND_BONUS_NOTICE.create_time
  is '创建时间';
comment on column MST_FUND_BONUS_NOTICE.update_user
  is '更新人';
comment on column MST_FUND_BONUS_NOTICE.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table MST_FUND_BONUS_NOTICE
  add constraint PK_MST_FUND_BONUS_NOTICE primary key (FUND_ID, REG_DATE)
  using index 
  ;


delete from SYS_TRASH_NOT_TARGET_PAGE where SEQ_NO = 'A96EA0A5352F043FE0538C5AA8C0286A';
insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID)
values ('A96EA0A5352F043FE0538C5AA8C0286A', 'MstFundBonusNotice002');



