delete from  MST_BUSINESS_ID_RULE where SEQ_NO = '0601';
insert into MST_BUSINESS_ID_RULE (SEQ_NO, COMMON_BUSINESS_TYPE, TYPE_1, TYPE_2, TYPE_3, TYPE_4, TYPE_5, TYPE_6, BUSINESS_ID_RULE_TYPE, PREFIX_NAME, PREFIX_SEQUENCE_NAME, PREFIX_SEQUENCE_LEN, POSTFIX_NAME, POSTFIX_SEQUENCE_NAME, POSTFIX_SEQUENCE_LEN, DATE_RULE_TYPE, BUSINESS_ID_SERVICE_NAME, BUSINESS_ID_METHOD_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('0601', 'FINPROD02', null, null, null, null, null, null, '03', 'PRDPLAN02_', 'PRDPLAN_TACTIC_SEQ', 12, null, null, null, null, null, null, 'system', null, sysdate, 'system', sysdate);

-- Create table
create table PRD_TEMPLATE
(
  template_id      VARCHAR2(32) not null,
  template_name    VARCHAR2(100) not null,
  profit_type      VARCHAR2(50),
  template_content CLOB,
  remark           VARCHAR2(1000),
  create_user      VARCHAR2(20) not null,
  create_dept      VARCHAR2(32),
  create_time      TIMESTAMP(6) not null,
  update_user      VARCHAR2(20) not null,
  update_time      TIMESTAMP(6) not null,
  deal_mode        VARCHAR2(50),
  operation_type   VARCHAR2(50),
  profit_flag      VARCHAR2(50)
);

-- Add comments to the table 
comment on table PRD_TEMPLATE
  is '产品模板';
-- Add comments to the columns 
comment on column PRD_TEMPLATE.template_id
  is '模板代码';
comment on column PRD_TEMPLATE.template_name
  is '模板名称';
comment on column PRD_TEMPLATE.profit_type
  is '收益类型';
comment on column PRD_TEMPLATE.template_content
  is '模板内容';
comment on column PRD_TEMPLATE.remark
  is '备注';
comment on column PRD_TEMPLATE.create_user
  is '创建人';
comment on column PRD_TEMPLATE.create_dept
  is '创建部门';
comment on column PRD_TEMPLATE.create_time
  is '创建时间';
comment on column PRD_TEMPLATE.update_user
  is '更新人';
comment on column PRD_TEMPLATE.update_time
  is '更新时间';
comment on column PRD_TEMPLATE.deal_mode
  is '处理模式';
comment on column PRD_TEMPLATE.operation_type
  is '发行方式';
comment on column PRD_TEMPLATE.profit_flag
  is '产品收益标识';
-- Create/Recreate primary, unique and foreign key constraints 
alter table PRD_TEMPLATE
  add constraint PK_PRD_TEMPLATE primary key (TEMPLATE_ID, TEMPLATE_NAME);
 
  
