---- 临时文件 之后整理后 删除

drop table sys_prebatch_result;

create table sys_prebatch_result
(
  seq_no                   VARCHAR2(32) not null,
  BATCH_SEQ_NO   VARCHAR2(64),
  task_id VARCHAR2(32),
  BEAN_NAME      VARCHAR2(512),
  check_date            DATE,
  EXECUTE_DATE      DATE,
  TASK_STATUS      VARCHAR2(8),
  DETAIL_COUNT      number(8),
  START_MODE   VARCHAR2(8),
  Start_time    TIMESTAMP(6),
  End_time  TIMESTAMP(6),
  stop_flag varchar2(2),
   institution_code  VARCHAR2(32),
  create_user   VARCHAR2(32) not null,
  create_dept   VARCHAR2(32),
  create_time   TIMESTAMP(6) not null,
  update_user   VARCHAR2(32) not null,
  update_time   TIMESTAMP(6) not null
  )
;
-- Add comments to the table
comment on table sys_prebatch_result
  is '批前检查结果表';
-- Add comments to the columns
comment on column sys_prebatch_result.BATCH_SEQ_NO
  is '批处理执行id';
comment on column sys_prebatch_result.task_id
  is 'taskid即检查项';
comment on column sys_prebatch_result.BEAN_NAME
  is 'Service别名';
  comment on column sys_prebatch_result.check_DATE
  is '检查日';
comment on column sys_prebatch_result.EXECUTE_DATE
  is '执行日';
comment on column sys_prebatch_result.TASK_STATUS
  is '检查状态';
  comment on column sys_prebatch_result.DETAIL_COUNT
  is '详情条数';
comment on column sys_prebatch_result.START_MODE
  is '启动模式';
comment on column sys_prebatch_result.Start_time
  is '开始时间';
comment on column sys_prebatch_result.End_time
  is '结束时间';
comment on column sys_prebatch_result.institution_code
  is '机构代码';
  comment on column sys_prebatch_result.stop_Flag
  is '是否卡批00:不卡  01:卡批';
comment on column sys_prebatch_result.create_user
  is '创建人';
comment on column sys_prebatch_result.create_dept
  is '创建部门';
comment on column sys_prebatch_result.create_time
  is '创建时间';
comment on column sys_prebatch_result.update_user
  is '更新人';
comment on column sys_prebatch_result.update_time
  is '更新时间';
-- Create/Recreate indexes
create index idx_sys_prebatch_result on sys_prebatch_result (task_id, EXECUTE_DATE, institution_code);
create index idx2_sys_prebatch_result on sys_prebatch_result (BATCH_SEQ_NO,task_id);
-- Create/Recreate primary, unique and foreign key constraints
alter table sys_prebatch_result
  add constraint pk_sys_prebatch_result primary key (SEQ_NO);



------

drop table sys_prebatch_result_detail;

create table sys_prebatch_result_detail
(
  seq_no                  VARCHAR2(32) not null,
  result_seq_no       VARCHAR2(32) ,
  BATCH_SEQ_NO   VARCHAR2(32),
  task_id VARCHAR2(32),
  Finprod_id      VARCHAR2(64),
  Finprod_name      VARCHAR2(512),
  portfolio_Id      VARCHAR2(64),
  portfolio_name   VARCHAR2(512),
  Trade_id  VARCHAR2(32),
  totle_trade_id  VARCHAR2(32),
  trade_amt   number(30,2),
  Launch_user   VARCHAR2(32),
  next_user  VARCHAR2(2048),
  remark      varchar2(2048),
  create_user   VARCHAR2(32) not null,
  create_dept   VARCHAR2(32),
  create_time   TIMESTAMP(6) not null,
  update_user   VARCHAR2(32) not null,
  update_time   TIMESTAMP(6) not null
  )
;
-- Add comments to the table
comment on table sys_prebatch_result_detail
  is '批前检查结果明细表';
-- Add comments to the columns
comment on column sys_prebatch_result_detail.SEQ_NO
  is '主键id';
  comment on column sys_prebatch_result_detail.result_seq_no
  is '批处理结果id';
comment on column sys_prebatch_result_detail.BATCH_SEQ_NO
  is '批处理执行id';
comment on column sys_prebatch_result_detail.task_id
  is 'taskid即检查项';
comment on column sys_prebatch_result_detail.Finprod_id
  is 'Finprod_id';
comment on column sys_prebatch_result_detail.Finprod_name
  is '资产名称';
  comment on column sys_prebatch_result_detail.portfolio_Id
  is '组合id';
comment on column sys_prebatch_result_detail.portfolio_name
  is '组合名称';
comment on column sys_prebatch_result_detail.Trade_id
  is '业务代码';
comment on column sys_prebatch_result_detail.totle_trade_id
  is '业务totleid';
comment on column sys_prebatch_result_detail.trade_amt
  is '金额';
comment on column sys_prebatch_result_detail.Launch_user
  is '启动用户';
comment on column sys_prebatch_result_detail.next_user
  is '当前处理人';
  comment on column sys_prebatch_result_detail.remark
  is '备注';
comment on column sys_prebatch_result_detail.create_user
  is '创建人';
comment on column sys_prebatch_result_detail.create_dept
  is '创建部门';
comment on column sys_prebatch_result_detail.create_time
  is '创建时间';
comment on column sys_prebatch_result_detail.update_user
  is '更新人';
comment on column sys_prebatch_result_detail.update_time
  is '更新时间';
-- Create/Recreate indexes
create index idx_sys_prebatch_detail on sys_prebatch_result_detail (result_seq_no, task_id);

create index idx2_sys_prebatch_detail on sys_prebatch_result_detail (BATCH_SEQ_NO);
-- Create/Recreate primary, unique and foreign key constraints
alter table sys_prebatch_result_detail
  add constraint pk_sys_prebatch_result_detail primary key (SEQ_NO);



drop table sys_prebatch_task_add;
create table sys_prebatch_task_add
(
  group_id                  VARCHAR2(32) not null,
  task_id       VARCHAR2(32) not null,
  stop_Flag   VARCHAR2(2),
  remark      varchar2(2048),
  create_user   VARCHAR2(32) not null,
  create_dept   VARCHAR2(32),
  create_time   TIMESTAMP(6) not null,
  update_user   VARCHAR2(32) not null,
  update_time   TIMESTAMP(6) not null
  )
;
-- Add comments to the table
comment on table sys_prebatch_task_add
  is '批前检查任务附属表';
-- Add comments to the columns
comment on column sys_prebatch_task_add.group_id
  is '任务组id';
  comment on column sys_prebatch_task_add.task_id
  is '批处理任务id';
comment on column sys_prebatch_task_add.stop_Flag
  is '是否卡批00:不卡  01:卡批';
comment on column sys_prebatch_task_add.remark
  is '备注';
comment on column sys_prebatch_task_add.create_user
  is '创建人';
comment on column sys_prebatch_task_add.create_dept
  is '创建部门';
comment on column sys_prebatch_task_add.create_time
  is '创建时间';
comment on column sys_prebatch_task_add.update_user
  is '更新人';
comment on column sys_prebatch_task_add.update_time
  is '更新时间';
-- Create/Recreate indexes
alter table sys_prebatch_task_add
  add constraint pk_sys_prebatch_task_add primary key (group_id, task_id);
----