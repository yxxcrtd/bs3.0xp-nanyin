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


--------------   菜单权限
 
 delete from sys_menu where page_id = 'SysPrebatchResult001';

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_006_011', 'SYS', null, 'SYSTEM_001_006', 'SysPrebatchResult001', 12, '02', '批前检查', 'system', null, '22-FEB-21 09.27.48.000000 AM', 'system', '22-FEB-21 09.27.48.000000 AM');

   
    delete from sys_page where page_id in ( 'SysPrebatchResult001','SysPrebatchResult002');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysPrebatchResult001', 'SYS', '/invest/prebatch/SysPrebatchResult001', 'invest/prebatch/SysPrebatchResult001', 'SYSTEM_001_006_011', null, null, '01', '02', '批前检查', 'system', null, '24-MAY-19 12.00.00.000000 AM', 'system', '24-MAY-19 12.00.00.000000 AM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysPrebatchResult002', 'SYS', '/invest/prebatch/SysPrebatchResult002', 'invest/prebatch/SysPrebatchResult002', null, null, null, '01', '02', '批前检查明细', 'system', null, '24-MAY-19 12.00.00.000000 AM', 'system', '24-MAY-19 12.00.00.000000 AM');


     delete from  sys_page_element A where page_id = 'SysPrebatchResult001' ;

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('SysPrebatchResult001', 'SysBatchStart001_btn_add', '01', '01', null, '启动', 'system', null, sysdate, 'system', sysdate, 'COM');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('SysPrebatchResult001', 'SysBatchStart001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('SysPrebatchResult001', 'SysBatchStart001_TBLROW_edit', '01', '01', null, '详情', 'system', null, sysdate, 'system', sysdate, 'COM');



-- 001页面多级法人过滤

  delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.prebatch.dao.SysPrebatchResult001Mapper.findPage';

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.prebatch.dao.SysPrebatchResult001Mapper.findPage', '1', '02', 'INSTITUTION_CODE', null, 'INV', 'SysPrebatchResult001', 'admin', null, sysdate, 'admin', sysdate, '01', 'base99.INSTITUTION_CODE belongInstitutionCode', null);



-----
delete from sys_batch_grp where BATCH_NAME = '批前检查';

insert into sys_batch_grp (SEQ_NO, SYSTEM_CODE, BATCH_NAME, DETAIL, PRE_CONDITION, PRE_BATCH_GROUP, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_HANDWORK)
values ( 'preBatchCheckGrp' , 'TAMS', '批前检查', '批前检查', null, null, 'system', null, sysdate, 'system', sysdate, '01');



delete from sys_para where para_code = 'PREBATCH_GROUP_ID';

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
select 'SYS', 'PREBATCH_GROUP_ID', a.seq_no
, '', null, '批前检查任务组id', 'system', null, sysdate, 'system', sysdate, null, '02'
 from sys_batch_grp a where BATCH_NAME = '批前检查';



----  增加一个批  需要加的数据

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88600', 'INV', '流程中产品定义', null, 'processProductTask', 1, 'system', null, sysdate, 'system', sysdate);


 insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 select  a.seq_no,'88600', '00', null, 'system', null, sysdate, 'system', sysdate
  from sys_batch_grp a where BATCH_NAME = '批前检查';

insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88600', 'system', null, sysdate, 'system', sysdate
  from sys_batch_grp a where BATCH_NAME = '批前检查';

--添加字典值配置
delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88600';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88601', '流程中产品定义', 1, '0', 'system', null,sysdate, 'system',sysdate, null);


------- 增加多及法人 过滤
delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.processproduct.dao.ProcessProductMapper.selectProcessProductList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.processproduct.dao.ProcessProductMapper.selectProcessProductList', '5', '02', 'instCode', null, 'prebatch', 'processProductTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

--流程中产品交易
delete from sys_batch_task where TASK_ID = '88601';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88601', 'INV', '流程中产品交易', null, 'processProductTradeTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88601';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88601', '00', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_batch_job where TASK_ID = '88601';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88601', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
------- 增加多及法人 过滤
delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.processproducttrade.dao.ProcessProductTradeMapper.selectProcessProductTradeList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.processproducttrade.dao.ProcessProductTradeMapper.selectProcessProductTradeList', '5', '02', 'instCode', null, 'prebatch', 'processProductTradeTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

--流程中的资产定义
delete from sys_batch_task where TASK_ID = '88602';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88602', 'INV', '流程中资产定义', null, 'processAssetTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88602';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88602', '01', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88602';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88602', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
--资产定义由于部分资产不需要数据隔离，隔离在内部进行

--流程中的投资交易
delete from sys_batch_task where TASK_ID = '88603';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88603', 'INV', '流程中投资交易', null, 'processAssetTradeTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88603';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88603', '00', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88603';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88603', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
------- 增加多及法人 过滤
delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.processassettrade.dao.ProcessAssetTradeMapper.selectProcessAssetTradeList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.processassettrade.dao.ProcessAssetTradeMapper.selectProcessAssetTradeList', '5', '02', 'instCode', null, 'prebatch', 'processAssetTradeTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);


--流程中的清算指令
delete from sys_batch_task where TASK_ID = '88604';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88604', 'INV', '流程中清算指令', null, 'processSettleOrderTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88604';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88604', '01', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88604';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88604', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
------- 增加多及法人 过滤
delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.processsettleorder.dao.ProcessSettleOrderMapper.selectProcessSettleOrderList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.processsettleorder.dao.ProcessSettleOrderMapper.selectProcessSettleOrderList', '5', '02', 'instCode', null, 'prebatch', 'processSettleOrderTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);


--流程中的手工凭证
delete from sys_batch_task where TASK_ID = '88605';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88605', 'INV', '流程中手工凭证', null, 'processVoucherTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88605';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88605', '01', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88605';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88605', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';
------- 增加多及法人 过滤
delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.processvoucher.dao.ProcessVoucherMapper.selectProcessVoucherList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.processvoucher.dao.ProcessVoucherMapper.selectProcessVoucherList', '5', '02', 'instCode', null, 'prebatch', 'processVoucherTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

--添加字典值配置
delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88601';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88601', '流程中产品交易', 2, '0', 'system', null,sysdate, 'system',sysdate, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88602';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88602', '流程中资产定义', 3, '0', 'system', null,sysdate, 'system',sysdate, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88603';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88603', '流程中投资交易', 4, '0', 'system', null,sysdate, 'system',sysdate, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88604';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88604', '流程中清算指令', 5, '0', 'system', null,sysdate, 'system',sysdate, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88605';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88605', '流程中手工凭证', 6, '0', 'system', null,sysdate, 'system',sysdate, null);

--追加-估值方法方案检查
delete from sys_batch_task where TASK_ID = '88606';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88606', 'INV', '估值方案配置', null, 'valuationSchemeTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88606';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88606', '00', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88606';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88606', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.valuationscheme.dao.ValuationSchemeMapper.selectValuationSchemeList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.valuationscheme.dao.ValuationSchemeMapper.selectValuationSchemeList', '5', '02', 'instCode', null, 'prebatch', 'valuationSchemeTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88606';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88606', '估值方案配置', 7, '0', 'system', null,sysdate, 'system',sysdate, null);

--追加-组合账户资金负头寸
delete from sys_batch_task where TASK_ID = '88607';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88607', 'INV', '产品现金负头寸', null, 'capitalPositionTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88607';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88607', '00', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88607';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88607', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.capitalposition.dao.CapitalPositionMapper.selectCapitalPositionList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.capitalposition.dao.CapitalPositionMapper.selectCapitalPositionList', '5', '02', 'instCode', null, 'prebatch', 'capitalPositionTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88607';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88607', '产品现金负头寸', 8, '0', 'system', null,sysdate, 'system',sysdate, null);

--追加-资产行情数据
delete from sys_batch_task where TASK_ID = '88608';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88608', 'INV', '资产行情数据', null, 'assetMarketDataTask', 1, 'system', null, sysdate, 'system', sysdate);

delete from sys_prebatch_task_add where TASK_ID = '88608';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88608', '01', null, 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88608';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select a.seq_no,'88608', 'system', null, sysdate, 'system', sysdate
from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from data_auth_mapper_config where sql_id = 'com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dao.AssetMarketDataMapper.getAssetMarketDataResult';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dao.AssetMarketDataMapper.getAssetMarketDataResult', '5', '02', 'instCode', null, 'prebatch', 'assetMarketDataTask', 'admin', null,
sysdate, 'admin', sysdate, null, null, null);

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88608';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('CHECK_POINT', '88608', '资产行情数据', 9, '0', 'system', null,sysdate, 'system',sysdate, null);




---- 88610

delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88610';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CHECK_POINT', '88610', '产品到期未确认', 10, '0', 'system', null, sysdate, 'system', sysdate, null);

 delete from sys_batch_task where TASK_ID = '88610';

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88610', 'INV', '产品到期未确认', null, 'productDueNoConfirmTask', 1, 'system', null, sysdate, 'system', sysdate);

 delete from sys_prebatch_task_add  where TASK_ID = '88610';

 insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 select  a.seq_no,'88610'
 , '00', null, 'system', null, sysdate, 'system', sysdate
  from sys_batch_grp a where BATCH_NAME = '批前检查';

delete from sys_batch_job where TASK_ID = '88610';

insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88610', 'system', null, sysdate, 'system', sysdate
  from sys_batch_grp a where BATCH_NAME = '批前检查';
  

delete from data_auth_mapper_config where sql_id in ('com.joyintech.tams.invest.batch.prebatchcheck.productduenoconfirm.dao.ProductDueNoConfirmMapper.selectProductListSY','com.joyintech.tams.invest.batch.prebatchcheck.productduenoconfirm.dao.ProductDueNoConfirmMapper.selectProductListJZ');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.productduenoconfirm.dao.ProductDueNoConfirmMapper.selectProductListSY', '5', '01', null, 'SELECT base99.* FROM( %s ) base99 LEFT JOIN data_auth_notify_inst dani99 ON dani99.BUSINESS_ID = base99.portfolioId AND dani99.MODEL_TYPE = ''02'' AND dani99.DATA_TYPE = ''FINPROD04'' WHERE  dani99.institution_code IN %s', 'prebatch', 'productDueNoConfirmTask', 'admin', null, sysdate, 'admin', sysdate, null, null, null);

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.productduenoconfirm.dao.ProductDueNoConfirmMapper.selectProductListJZ', '5', '01', null, 'SELECT base99.* FROM( %s ) base99 LEFT JOIN data_auth_notify_inst dani99 ON dani99.BUSINESS_ID = base99.portfolioId AND dani99.MODEL_TYPE = ''02'' AND dani99.DATA_TYPE = ''FINPROD04'' WHERE  dani99.institution_code IN %s', 'prebatch', 'productDueNoConfirmTask', 'admin', null, sysdate, 'admin', sysdate, null, null, null);

--- 88611
delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88611';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CHECK_POINT', '88611', '净值型产品除权日未确认分红金额', 10, '0', 'system', null, sysdate, 'system', sysdate, null);

 delete from sys_batch_task where TASK_ID = '88611';

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88611', 'INV', '净值型产品除权日未确认分红金额', null, 'bonusRegisteNoComfirmTask', 11, 'system', null, sysdate, 'system', sysdate);

 delete from sys_prebatch_task_add  where TASK_ID  = '88611';
 insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 select  a.seq_no,'88611'
 , '00', null, 'system', null, sysdate, 'system', sysdate
  from sys_batch_grp a where BATCH_NAME = '批前检查';
  
  delete from sys_batch_job where TASK_ID = '88611';

insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88611', 'system', null, sysdate, 'system', sysdate
  from sys_batch_grp a where BATCH_NAME = '批前检查';
  
  delete from data_auth_mapper_config where sql_id in ('com.joyintech.tams.invest.batch.prebatchcheck.bonusregistenocomfirm.dao.BonusRegisteNoComfirmMapper.selProductRegisteNoconfirm');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.bonusregistenocomfirm.dao.BonusRegisteNoComfirmMapper.selProductRegisteNoconfirm', '5', '01', null, 'SELECT base99.* FROM( %s ) base99 LEFT JOIN data_auth_notify_inst dani99 ON dani99.BUSINESS_ID = base99.portfolioId AND dani99.MODEL_TYPE = ''02'' AND dani99.DATA_TYPE = ''FINPROD04'' WHERE  dani99.institution_code IN %s', 'prebatch', 'bonusRegisteNoComfirmTask', 'admin', null, sysdate, 'admin', sysdate, null, null, null);


----  88612
delete from SYS_DATADICT_LIST where dict_code ='CHECK_POINT' and item_code= '88612';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CHECK_POINT', '88612', '资产交易/存续期未确认', 10, '0', 'system', null, sysdate, 'system', sysdate, null);

 delete from sys_batch_task where TASK_ID = '88612';

insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('88612', 'INV', '资产交易/存续期未确认', null, 'tradeDurationNoConfirmTask', 12, 'system', null, sysdate, 'system', sysdate);

 delete from sys_prebatch_task_add  where TASK_ID  = '88611';
 insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 select  a.seq_no,'88612'
 , '00', null, 'system', null, sysdate, 'system', sysdate
  from sys_batch_grp a where BATCH_NAME = '批前检查';

  delete from sys_batch_job where TASK_ID = '88612';
insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88612', 'system', null, sysdate, 'system', sysdate
  from sys_batch_grp a where BATCH_NAME = '批前检查';
  
  

  delete from data_auth_mapper_config where sql_id in ('com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectCKDurationList','com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectCJDurationList'
  ,'com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectJHLLLXDurationList'
  ,'com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectXMLDurationList',
  'com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectZQJDDurationList','com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectJZXJHJJTradeList');
----  存款类 存续期
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectCKDurationList', '5', '01', null, 'SELECT base99.* FROM( %s ) base99 LEFT JOIN data_auth_notify_inst dani99 ON dani99.BUSINESS_ID = base99.finprodId AND dani99.MODEL_TYPE = ''02'' AND dani99.DATA_TYPE = ''FINASSET02'' WHERE  dani99.institution_code IN %s', 'prebatch', 'tradeDurationNoConfirmTask', 'admin', null, sysdate, 'admin',sysdate, null, null, null);

-----   拆借
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectCJDurationList', '5', '01', null, 'SELECT base99.* FROM( %s ) base99 LEFT JOIN data_auth_notify_inst dani99 ON dani99.BUSINESS_ID = base99.portfolioId AND dani99.MODEL_TYPE = ''02'' AND dani99.DATA_TYPE = ''PTL01'' WHERE  dani99.institution_code IN %s', 'prebatch', 'tradeDurationNoConfirmTask', 'admin', null, sysdate, 'admin',sysdate, null, null, null);


-----   计划类利率型  存续期
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectJHLLLXDurationList', '5', '01', null, 'SELECT base99.* FROM( %s ) base99 LEFT JOIN data_auth_notify_inst dani99 ON dani99.BUSINESS_ID = base99.finprodId AND dani99.MODEL_TYPE = ''02'' AND dani99.DATA_TYPE = ''FINASSET05'' WHERE  dani99.institution_code IN %s', 'prebatch', 'tradeDurationNoConfirmTask', 'admin', null, sysdate, 'admin',sysdate, null, null, null);


-----   项目类  存续期
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectXMLDurationList', '5', '01', null, 'SELECT base99.* FROM( %s ) base99 LEFT JOIN data_auth_notify_inst dani99 ON dani99.BUSINESS_ID = base99.finprodId AND dani99.MODEL_TYPE = ''02'' AND dani99.DATA_TYPE = ''FINASSET06'' WHERE  dani99.institution_code IN %s', 'prebatch', 'tradeDurationNoConfirmTask', 'admin', null, sysdate, 'admin',sysdate, null, null, null);

-----   债券借贷  存续期
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectZQJDDurationList', '5', '01', null, 'SELECT base99.* FROM( %s ) base99 LEFT JOIN data_auth_notify_inst dani99 ON dani99.BUSINESS_ID = base99.portfolioId AND dani99.MODEL_TYPE = ''02'' AND dani99.DATA_TYPE = ''PTL01'' WHERE  dani99.institution_code IN %s', 'prebatch', 'tradeDurationNoConfirmTask', 'admin', null, sysdate, 'admin',sysdate, null, null, null);

-----   现金分红 赎回到账  交易  
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper.selectJZXJHJJTradeList', '5', '01', null, 'SELECT base99.* FROM( %s ) base99 LEFT JOIN data_auth_notify_inst dani99 ON dani99.BUSINESS_ID = base99.portfolioId AND dani99.MODEL_TYPE = ''02'' AND dani99.DATA_TYPE = ''PTL01'' WHERE  dani99.institution_code IN %s', 'prebatch', 'tradeDurationNoConfirmTask', 'admin', null, sysdate, 'admin',sysdate, null, null, null);

