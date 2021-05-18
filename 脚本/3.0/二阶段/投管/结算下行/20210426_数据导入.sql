delete from sys_menu where MENU_ID = 'SETTLE_MANAGEMENT_001_004';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_MANAGEMENT_001_004', 'STL', null, 'SETTLE_MANAGEMENT_001', null, 2, '02', '日终清结算', 'system', 'system', sysdate, 'system', sysdate);

delete from sys_menu where PAGE_ID = 'SettleDataImport001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_MANAGEMENT_001_004_001', 'STL', null, 'SETTLE_MANAGEMENT_001_004', 'SettleDataImport001', 1, '02', '数据导入', 'system', 'system', sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'SettleDataImport001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SettleDataImport001', 'STL', '/invest/basicdata/dataimport/SettleDataImport001', 'invest/basicdata/dataimport/SettleDataImport001', 'SETTLE_MANAGEMENT_001_004_001', null, null, '02', '02', '数据导入', 'system', null, sysdate, 'system', sysdate);



--数据字典
delete from sys_datadict_list where DICT_CODE = 'TASK_RESULT' and ITEM_CODE = 'WARN';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TASK_RESULT', 'WARN', '警告', 3, '0', 'system', null, sysdate, 'system', sysdate);


-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SETTLE_TRANS_RESULT') ;
    if num > 0 then
        execute immediate 'drop table SETTLE_TRANS_RESULT';
    end if;
end;
/
create table SETTLE_TRANS_RESULT
(
  seqno          VARCHAR2(32) not null,
  import_date    DATE,
  portfolio_id   VARCHAR2(50),
  task_id        VARCHAR2(32),
  data_file_name VARCHAR2(256),
  task_result    VARCHAR2(50),
  success_msg    CLOB,
  failure_msg    CLOB,
  warning_msg    CLOB,
  original_data  CLOB,
  start_time     TIMESTAMP(6) not null,
  end_time       TIMESTAMP(6) not null,
  create_user    VARCHAR2(20) not null,
  create_dept    VARCHAR2(32),
  create_time    TIMESTAMP(6) not null,
  update_user    VARCHAR2(20) not null,
  update_time    TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SETTLE_TRANS_RESULT
  is '成交数据明细导入结果表';
-- Add comments to the columns 
comment on column SETTLE_TRANS_RESULT.seqno
  is '流水号';
comment on column SETTLE_TRANS_RESULT.import_date
  is '导入日期';
comment on column SETTLE_TRANS_RESULT.portfolio_id
  is '组合代码';
comment on column SETTLE_TRANS_RESULT.task_id
  is '任务代码';
comment on column SETTLE_TRANS_RESULT.data_file_name
  is '数据文件';
comment on column SETTLE_TRANS_RESULT.task_result
  is '批处理执行结果（NOR：正常结束、EXP：异常结束、WARN：警告）';
comment on column SETTLE_TRANS_RESULT.success_msg
  is '成功信息';
comment on column SETTLE_TRANS_RESULT.failure_msg
  is '失败信息';
comment on column SETTLE_TRANS_RESULT.warning_msg
  is '警告信息';
comment on column SETTLE_TRANS_RESULT.original_data
  is '原始数据';
comment on column SETTLE_TRANS_RESULT.create_user
  is '创建人';
comment on column SETTLE_TRANS_RESULT.create_dept
  is '创建部门';
comment on column SETTLE_TRANS_RESULT.create_time
  is '创建时间';
comment on column SETTLE_TRANS_RESULT.update_user
  is '更新人';
comment on column SETTLE_TRANS_RESULT.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SETTLE_TRANS_RESULT
  add constraint PK_SETTLE_TRANS_RESULT primary key (SEQNO)
  using index 
  ;



-- Create table
declare
      num   number;
begin
    select count(1) into num from user_tables where table_name = upper('SETTLE_TRANS_PARA') ;
    if num > 0 then
        execute immediate 'drop table SETTLE_TRANS_PARA';
    end if;
end;
/
create table SETTLE_TRANS_PARA
(
  seqno          VARCHAR2(32) not null,
  import_date    DATE,
  data_file_name VARCHAR2(256),
  portfolio_id   VARCHAR2(50),
  task_id        VARCHAR2(32),
  create_user    VARCHAR2(20) not null,
  create_dept    VARCHAR2(32),
  create_time    TIMESTAMP(6) not null,
  update_user    VARCHAR2(20) not null,
  update_time    TIMESTAMP(6) not null
)
;
-- Add comments to the table 
comment on table SETTLE_TRANS_PARA
  is '成交数据明细导入参数表';
-- Add comments to the columns 
comment on column SETTLE_TRANS_PARA.seqno
  is '流水号';
comment on column SETTLE_TRANS_PARA.import_date
  is '导入日期';
comment on column SETTLE_TRANS_PARA.data_file_name
  is '数据文件';
comment on column SETTLE_TRANS_PARA.portfolio_id
  is '组合代码';
comment on column SETTLE_TRANS_PARA.task_id
  is '任务代码';
comment on column SETTLE_TRANS_PARA.create_user
  is '创建人';
comment on column SETTLE_TRANS_PARA.create_dept
  is '创建部门';
comment on column SETTLE_TRANS_PARA.create_time
  is '创建时间';
comment on column SETTLE_TRANS_PARA.update_user
  is '更新人';
comment on column SETTLE_TRANS_PARA.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SETTLE_TRANS_PARA
  add constraint PK_SETTLE_TRANS_PARA primary key (SEQNO)
  using index 
;
























