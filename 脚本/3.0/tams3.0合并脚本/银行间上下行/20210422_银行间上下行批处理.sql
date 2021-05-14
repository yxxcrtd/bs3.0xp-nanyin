-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('INTERF_CFETS_CUSTOMER_TMP') ;
    if num > 0 then
        execute immediate 'drop table INTERF_CFETS_CUSTOMER_TMP';
    end if;
end;
/
create table INTERF_CFETS_CUSTOMER_TMP
(
  party_id          VARCHAR2(32) not null,
  party_customer_id VARCHAR2(32) not null,
  party_abbr        VARCHAR2(200),
  party_name        VARCHAR2(200),
  party_abbr_en     VARCHAR2(200),
  party_name_en     VARCHAR2(200),
  remark            VARCHAR2(1000),
  create_user       VARCHAR2(20) not null,
  create_time       TIMESTAMP(6) not null,
  create_dept       VARCHAR2(32),
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table INTERF_CFETS_CUSTOMER_TMP
  is '外汇交易中心_客户信息_银行机构';
-- Add comments to the columns 
comment on column INTERF_CFETS_CUSTOMER_TMP.party_id
  is '交易成员ID，21位编号';
comment on column INTERF_CFETS_CUSTOMER_TMP.party_customer_id
  is '交易机构ID，6位编号';
comment on column INTERF_CFETS_CUSTOMER_TMP.party_abbr
  is '交易成员简称';
comment on column INTERF_CFETS_CUSTOMER_TMP.party_name
  is '交易成员全称';
comment on column INTERF_CFETS_CUSTOMER_TMP.party_abbr_en
  is '交易成员英文简称';
comment on column INTERF_CFETS_CUSTOMER_TMP.party_name_en
  is '交易成员英文全称';
comment on column INTERF_CFETS_CUSTOMER_TMP.remark
  is '备注';
comment on column INTERF_CFETS_CUSTOMER_TMP.create_user
  is '创建人';
comment on column INTERF_CFETS_CUSTOMER_TMP.create_time
  is '创建时间';
comment on column INTERF_CFETS_CUSTOMER_TMP.create_dept
  is '创建部门';
comment on column INTERF_CFETS_CUSTOMER_TMP.update_user
  is '更新人';
comment on column INTERF_CFETS_CUSTOMER_TMP.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTERF_CFETS_CUSTOMER_TMP
  add constraint PK_INTERF_CFETS_CUSTOMER_TMP primary key (PARTY_ID)
  using index 
  ;
  
  
  

  
-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('INTERF_CFETS_PARTY_RELA_TMP') ;
    if num > 0 then
        execute immediate 'drop table INTERF_CFETS_PARTY_RELA_TMP';
    end if;
end;
/
create table INTERF_CFETS_PARTY_RELA_TMP
(
  rela_id            VARCHAR2(32) not null,
  mparty_customer_id VARCHAR2(32),
  sparty_customer_id VARCHAR2(32),
  create_user        VARCHAR2(20) not null,
  create_time        TIMESTAMP(6) not null,
  create_dept        VARCHAR2(32),
  update_user        VARCHAR2(20) not null,
  update_time        TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table INTERF_CFETS_PARTY_RELA_TMP
  is '外汇交易中心_客户信息_银行机构关系';
-- Add comments to the columns 
comment on column INTERF_CFETS_PARTY_RELA_TMP.rela_id
  is '关系代码';
comment on column INTERF_CFETS_PARTY_RELA_TMP.mparty_customer_id
  is '主交易机构ID，6位编号';
comment on column INTERF_CFETS_PARTY_RELA_TMP.sparty_customer_id
  is '从交易机构ID，6位编号';
comment on column INTERF_CFETS_PARTY_RELA_TMP.create_user
  is '创建人';
comment on column INTERF_CFETS_PARTY_RELA_TMP.create_time
  is '创建时间';
comment on column INTERF_CFETS_PARTY_RELA_TMP.create_dept
  is '创建部门';
comment on column INTERF_CFETS_PARTY_RELA_TMP.update_user
  is '更新人';
comment on column INTERF_CFETS_PARTY_RELA_TMP.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTERF_CFETS_PARTY_RELA_TMP
  add constraint PK_INTERF_CFETS_PARTY_RELA_TMP primary key (RELA_ID)
  using index 
  ;
  
  
  
  
  
  
 -- Create table
 declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('INTERF_CFETS_TRADER_TMP') ;
    if num > 0 then
        execute immediate 'drop table INTERF_CFETS_TRADER_TMP';
    end if;
end;
/
create table INTERF_CFETS_TRADER_TMP
(
  trader_id         VARCHAR2(50) not null,
  party_customer_id VARCHAR2(32),
  trader_name       VARCHAR2(1000),
  telphone_no       VARCHAR2(50),
  tel_no            VARCHAR2(50),
  fax_no            VARCHAR2(50),
  msn               VARCHAR2(50),
  email             VARCHAR2(50),
  address           VARCHAR2(1000),
  post_code         VARCHAR2(50),
  remark            VARCHAR2(1000),
  create_user       VARCHAR2(20) not null,
  create_time       TIMESTAMP(6) not null,
  create_dept       VARCHAR2(32),
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table INTERF_CFETS_TRADER_TMP
  is '外汇交易中心_客户信息_银行交易员';
-- Add comments to the columns 
comment on column INTERF_CFETS_TRADER_TMP.trader_id
  is '交易员ID';
comment on column INTERF_CFETS_TRADER_TMP.party_customer_id
  is '交易机构ID，6位编号';
comment on column INTERF_CFETS_TRADER_TMP.trader_name
  is '交易员姓名';
comment on column INTERF_CFETS_TRADER_TMP.telphone_no
  is '座机、手机号码';
comment on column INTERF_CFETS_TRADER_TMP.tel_no
  is '联系电话';
comment on column INTERF_CFETS_TRADER_TMP.fax_no
  is '传真';
comment on column INTERF_CFETS_TRADER_TMP.msn
  is 'MSN';
comment on column INTERF_CFETS_TRADER_TMP.email
  is '电子邮箱';
comment on column INTERF_CFETS_TRADER_TMP.address
  is '地址';
comment on column INTERF_CFETS_TRADER_TMP.post_code
  is '邮政编号';
comment on column INTERF_CFETS_TRADER_TMP.remark
  is '备注';
comment on column INTERF_CFETS_TRADER_TMP.create_user
  is '创建人';
comment on column INTERF_CFETS_TRADER_TMP.create_time
  is '创建时间';
comment on column INTERF_CFETS_TRADER_TMP.create_dept
  is '创建部门';
comment on column INTERF_CFETS_TRADER_TMP.update_user
  is '更新人';
comment on column INTERF_CFETS_TRADER_TMP.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INTERF_CFETS_TRADER_TMP
  add constraint PK_INTERF_CFETS_TRADER_TMP primary key (TRADER_ID)
  using index 
  ;

  
delete from sys_datadict_list where DICT_CODE = 'COUNTER_TYPE' and ITEM_CODE = '06';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COUNTER_TYPE', '06', '非法人机构', 6, '0', 'system', null, sysdate, 'system', sysdate);

delete from sys_batch_task where TASK_ID = '20003';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('20003', 'INV', 'CFETS银行成员关联信息表数据更新', null, 'cfetsCustomerRelaTask', 2, 'system', null, sysdate, 'system', sysdate, null);



