  drop table sys_page_element_helpdoc;
    -- Create table
create table sys_page_element_helpdoc
(
  page_id     VARCHAR2(50) not null,
  element_id  VARCHAR2(50) not null,
  href     VARCHAR2(512),
  alt        VARCHAR2(256),
  imagesrc varchar2(128),
  target_type varchar2(8) default '1' not null,
  create_user VARCHAR2(32) not null,
  create_dept VARCHAR2(32),
  create_time TIMESTAMP(6) not null,
  update_user VARCHAR2(32) not null,
  update_time TIMESTAMP(6) not null
);
-- Add comments to the table 
comment on table sys_page_element_helpdoc
  is '画面帮助手册';
-- Add comments to the columns 
comment on column sys_page_element_helpdoc.page_id
  is '画面代码';
comment on column sys_page_element_helpdoc.element_id
  is '要素代码';
comment on column sys_page_element_helpdoc.href
  is '链接路径：http://必填';
  comment on column sys_page_element_helpdoc.alt
  is '悬浮提示';
    comment on column sys_page_element_helpdoc.imagesrc
  is '图片路径';
    comment on column sys_page_element_helpdoc.target_type
  is '默认1:id配置,暂为备用字段';
comment on column sys_page_element_helpdoc.create_user
  is '创建人';
comment on column sys_page_element_helpdoc.create_dept
  is '创建部门';
comment on column sys_page_element_helpdoc.create_time
  is '创建时间';
comment on column sys_page_element_helpdoc.update_user
  is '更新人';
comment on column sys_page_element_helpdoc.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table sys_page_element_helpdoc
  add constraint PK_sys_page_element_helpdoc primary key (PAGE_ID, ELEMENT_ID);
     