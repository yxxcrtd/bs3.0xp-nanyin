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
create index uk_sys_prebatch_result on sys_prebatch_result (task_id, EXECUTE_DATE, institution_code);
create index uk2_sys_prebatch_result on sys_prebatch_result (BATCH_SEQ_NO,task_id);
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
create index index_sys_prebatch_detail on sys_prebatch_result_detail (result_seq_no, task_id);

create index index2_sys_prebatch_detail on sys_prebatch_result_detail (BATCH_SEQ_NO);
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
--create index index_sys_prebatch_task_add on sys_prebatch_task_add (group_id, task_id);
-- Create/Recreate primary, unique and foreign key constraints
alter table sys_prebatch_task_add
  add constraint pk_sys_prebatch_task_add primary key (group_id, task_id);

----


--------------   菜单权限
   SELECT A.*,ROWID FROM  sys_menu A where page_id = 'SysInstRegBankCode001';
   SELECT a.*,rowid FROM sys_menu a where a.parent_menu_id = 'SYSTEM_001_006';
      SELECT a.*,rowid FROM sys_menu a where a.menu_id = 'SYSTEM_001_006_011';

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_006_011', 'SYS', null, 'SYSTEM_001_006', 'SysPrebatchResult001', 12, '02', '批前检查', 'system', null, '22-FEB-21 09.27.48.000000 AM', 'system', '22-FEB-21 09.27.48.000000 AM');

   SELECT A.*,ROWID FROM   sys_page a where page_id = 'SysPrebatchResult001';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysPrebatchResult001', 'SYS', '/invest/prebatch/SysPrebatchResult001', 'invest/prebatch/SysPrebatchResult001', 'SYSTEM_001_006_011', null, null, '01', '02', '批前检查', 'system', null, '24-MAY-19 12.00.00.000000 AM', 'system', '24-MAY-19 12.00.00.000000 AM');

   SELECT A.*,ROWID FROM  sys_page_element A where page_id = 'SysPrebatchResult001' ;

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('SysPrebatchResult001', 'SysBatchStart001_btn_add', '01', '01', null, '启动', 'system', null, '26-JUL-20 09.20.29.000000 PM', 'system', '26-JUL-20 09.20.29.000000 PM', 'COM');




-----


insert into sys_batch_grp (SEQ_NO, SYSTEM_CODE, BATCH_NAME, DETAIL, PRE_CONDITION, PRE_BATCH_GROUP, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_HANDWORK)
values ( lower(sys_guid()) , 'TAMS', '批前检查', '批前检查', null, null, 'system', null, sysdate, 'system', sysdate, '01');


insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
select 'SYS', 'PREBATCH_GROUP_ID', a.seq_no
, '', null, '批前检查任务组id', 'system', null, sysdate, 'system', sysdate, null, '02'
 from sys_batch_grp a where BATCH_NAME = '批前检查';



----  增加一个批  需要加的数据

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88600', 'INV', '流程中产品定义', null, 'processProductTask', 1, 'system', null, sysdate, 'system', sysdate);


 insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 select  a.seq_no,'88600'
 , '01', null, 'system', null, sysdate, 'system', sysdate
  from sys_batch_grp a where BATCH_NAME = '批前检查';


insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88600', 'system', null, sysdate, 'system', sysdate
  from sys_batch_grp a where BATCH_NAME = '批前检查';



------- 增加多及法人 过滤
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.processproduct.dao.ProcessProductMapper.selectProcessProductList', '5', '02', 'instCode', null, 'prebatch', 'processProductTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);
