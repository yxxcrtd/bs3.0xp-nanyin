delete from sys_menu where MENU_ID = 'SETTLE_MANAGEMENT_001_004_002';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_MANAGEMENT_001_004_002', 'STL', null, 'SETTLE_MANAGEMENT_001_004', 'SettleDataClear001', 2, '02', '数据清算处理', 'system', 'system', sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'SettleDataClear001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SettleDataClear001', 'STL', '/invest/basicdata/dataclear/SettleDataClear001', 'invest/basicdata/dataclear/SettleDataClear001', 'SETTLE_MANAGEMENT_001_004_002', null, null, '02', '02', '数据清算处理', 'system', null, sysdate, 'system', sysdate);

-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SETTLE_BATCH_RESULT') ;
    if num > 0 then
        execute immediate 'drop table SETTLE_BATCH_RESULT';
    end if;
end;
/
create table SETTLE_BATCH_RESULT
(
  seqno        VARCHAR2(32) not null,
  deal_date    DATE,
  portfolio_id VARCHAR2(50),
  sec_type     VARCHAR2(50),
  task_result  VARCHAR2(50),
  success_msg  CLOB,
  failure_msg  CLOB,
  warning_msg  CLOB,
  start_time   TIMESTAMP(6) not null,
  end_time     TIMESTAMP(6) not null,
  create_user  VARCHAR2(20) not null,
  create_dept  VARCHAR2(32),
  create_time  TIMESTAMP(6) not null,
  update_user  VARCHAR2(20) not null,
  update_time  TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SETTLE_BATCH_RESULT
  is '数据清算结果表';
-- Add comments to the columns 
comment on column SETTLE_BATCH_RESULT.seqno
  is '流水号';
comment on column SETTLE_BATCH_RESULT.deal_date
  is '执行日期';
comment on column SETTLE_BATCH_RESULT.portfolio_id
  is '组合代码';
comment on column SETTLE_BATCH_RESULT.sec_type
  is '证券品种';
comment on column SETTLE_BATCH_RESULT.task_result
  is '批处理执行结果（NOR：正常结束、EXP：异常结束、WARN：警告）';
comment on column SETTLE_BATCH_RESULT.success_msg
  is '成功信息';
comment on column SETTLE_BATCH_RESULT.failure_msg
  is '失败信息';
comment on column SETTLE_BATCH_RESULT.warning_msg
  is '警告信息';
comment on column SETTLE_BATCH_RESULT.create_user
  is '创建人';
comment on column SETTLE_BATCH_RESULT.create_dept
  is '创建部门';
comment on column SETTLE_BATCH_RESULT.create_time
  is '创建时间';
comment on column SETTLE_BATCH_RESULT.update_user
  is '更新人';
comment on column SETTLE_BATCH_RESULT.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SETTLE_BATCH_RESULT
  add constraint PK_SETTLE_BATCH_RESULT primary key (SEQNO)
  using index 
  ;
  
  
  -- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SETTLE_BATCH_PARA') ;
    if num > 0 then
        execute immediate 'drop table SETTLE_BATCH_PARA';
    end if;
end;
/
create table SETTLE_BATCH_PARA
(
  seqno        VARCHAR2(32) not null,
  deal_date    DATE,
  portfolio_id VARCHAR2(50),
  sec_type     VARCHAR2(50),
  create_user  VARCHAR2(20) not null,
  create_dept  VARCHAR2(32),
  create_time  TIMESTAMP(6) not null,
  update_user  VARCHAR2(20) not null,
  update_time  TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SETTLE_BATCH_PARA
  is '数据清算参数表';
-- Add comments to the columns 
comment on column SETTLE_BATCH_PARA.seqno
  is '流水号';
comment on column SETTLE_BATCH_PARA.deal_date
  is '执行日期';
comment on column SETTLE_BATCH_PARA.portfolio_id
  is '组合代码';
comment on column SETTLE_BATCH_PARA.sec_type
  is '证券品种';
comment on column SETTLE_BATCH_PARA.create_user
  is '创建人';
comment on column SETTLE_BATCH_PARA.create_dept
  is '创建部门';
comment on column SETTLE_BATCH_PARA.create_time
  is '创建时间';
comment on column SETTLE_BATCH_PARA.update_user
  is '更新人';
comment on column SETTLE_BATCH_PARA.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SETTLE_BATCH_PARA
  add constraint PK_SETTLE_BATCH_PARA primary key (SEQNO)
  using index 
  ;

