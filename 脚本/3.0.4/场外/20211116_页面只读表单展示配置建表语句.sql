drop table CFG_READONLY_FORM_SHOW;
-- Create table
create table CFG_READONLY_FORM_SHOW
(
  pagecode  VARCHAR2(36) not null,
  dtcode    VARCHAR2(36) not null,
  spcdcode  VARCHAR2(36) default '999999' not null,
  colcode   VARCHAR2(30) not null,
  colname   VARCHAR2(60) not null,
  dictcode  VARCHAR2(30),
  seqno     INTEGER default 0 not null,
  extendcfg VARCHAR2(200) default '',
  status    INTEGER default 1 not null,
  remark1   NUMBER default 0,
  remark2   VARCHAR2(50) default '',
  remark3   VARCHAR2(50) default ''
);
-- Add comments to the table 
comment on table CFG_READONLY_FORM_SHOW
  is '页面只读表单展示配置';
-- Add comments to the columns 
comment on column CFG_READONLY_FORM_SHOW.pagecode
  is '页面编码';
comment on column CFG_READONLY_FORM_SHOW.dtcode
  is '页面区域编码';
comment on column CFG_READONLY_FORM_SHOW.spcdcode
  is '扩展条件编码,无条件默认999999,多个条件用逗号分隔';
comment on column CFG_READONLY_FORM_SHOW.colcode
  is '字段代码';
comment on column CFG_READONLY_FORM_SHOW.colname
  is '字段名称';
comment on column CFG_READONLY_FORM_SHOW.seqno
  is '顺序号';
comment on column CFG_READONLY_FORM_SHOW.extendcfg
  is '扩张配置';
comment on column CFG_READONLY_FORM_SHOW.status
  is '可用状态(0:不可用,1:可用)';
comment on column CFG_READONLY_FORM_SHOW.remark1
  is '备注1';
comment on column CFG_READONLY_FORM_SHOW.remark2
  is '备注2';
comment on column CFG_READONLY_FORM_SHOW.remark3
  is '备注3';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CFG_READONLY_FORM_SHOW
  add primary key (PAGECODE, DTCODE, SPCDCODE, COLCODE);

