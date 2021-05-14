/*==============================================================*/
/* Table: BOK_BALANCE_AGAIN_COLLECT_LOG                         */
/* Date: 20201229					                            */
/* author: lby					                                */
/*==============================================================*/
-- Drop table
/*drop table BOK_BALANCE_AGAIN_COLLECT_LOG;*/
-- Create table
create table BOK_BALANCE_AGAIN_COLLECT_LOG
(
  BOOKSET_ID     VARCHAR2(32),
  CDATE          DATE,
  create_user    VARCHAR2(20) not null,
  create_dept    VARCHAR2(32),
  create_time    TIMESTAMP(6) not null,
  update_user    VARCHAR2(20) not null,
  update_time    TIMESTAMP(6) not null
);
-- Add comments to the table 
comment on table BOK_BALANCE_AGAIN_COLLECT_LOG
  is '科目余额汇总信息记录表';
-- Add comments to the columns 
comment on column BOK_BALANCE_AGAIN_COLLECT_LOG.BOOKSET_ID
  is '套账代码';
comment on column BOK_BALANCE_AGAIN_COLLECT_LOG.CDATE
  is '重汇结束日';
comment on column BOK_BALANCE_AGAIN_COLLECT_LOG.create_user
  is '创建人';
comment on column BOK_BALANCE_AGAIN_COLLECT_LOG.create_dept
  is '创建部门';
comment on column BOK_BALANCE_AGAIN_COLLECT_LOG.create_time
  is '创建时间';
comment on column BOK_BALANCE_AGAIN_COLLECT_LOG.update_user
  is '更新人';
comment on column BOK_BALANCE_AGAIN_COLLECT_LOG.update_time
  is '更新时间';
  
