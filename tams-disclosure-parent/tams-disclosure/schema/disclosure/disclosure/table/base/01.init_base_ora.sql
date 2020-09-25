/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/7/15 星期日 下午 6:31:18                     */
/*==============================================================*/

DROP TABLE REP_FTP_PATH;
DROP TABLE REP_INDEX_PRIMARY;
DROP TABLE REP_INDEX_SUB;
DROP TABLE REP_INFO;
DROP TABLE REP_POWER;
DROP TABLE REP_TREE;



-- Create table
create table REP_FTP_PATH
(
  ID          VARCHAR(32) not null,
  NAME        VARCHAR(32),
  PASSWORD    VARCHAR(100),
  USERNAME    VARCHAR(100),
  PATH        VARCHAR(100),
  PROT        VARCHAR(100),
  IP          VARCHAR(100),
  CREATE_USER VARCHAR(50) not null,
  CREATE_DEPT VARCHAR(50),
  CREATE_TIME TIMESTAMP not null,
  UPDATE_USER VARCHAR(50) not null,
  UPDATE_TIME TIMESTAMP not null,
   constraint PK_REP_FTP_PATH primary key (ID)
);
-- Add comments to the table 
comment on table REP_FTP_PATH
  is '披露地址';
-- Add comments to the columns 
comment on column REP_FTP_PATH.ID
  is '披露地址编号';
comment on column REP_FTP_PATH.NAME
  is '披露地址名称';
comment on column REP_FTP_PATH.PASSWORD
  is '密码';
comment on column REP_FTP_PATH.USERNAME
  is '用户名';
comment on column REP_FTP_PATH.PATH
  is '目录';
comment on column REP_FTP_PATH.PROT
  is '端口号';
comment on column REP_FTP_PATH.IP
  is '主机地址';
comment on column REP_FTP_PATH.CREATE_USER
  is '创建人';
comment on column REP_FTP_PATH.CREATE_DEPT
  is '创建部门';
comment on column REP_FTP_PATH.CREATE_TIME
  is '创建时间';
comment on column REP_FTP_PATH.UPDATE_USER
  is '更新人';
comment on column REP_FTP_PATH.UPDATE_TIME
  is '更新时间';


  
-- Create table
create table REP_INDEX_PRIMARY
(
  ID             VARCHAR(32) not null,
  INDEX_NAME     VARCHAR(200),
  INDEX_DESCRIBE VARCHAR(2000),
  PROD_ID        VARCHAR(150),
  CREATE_USER    VARCHAR(20) not null,
  CREATE_TIME    TIMESTAMP not null,
  UPDATE_USER    VARCHAR(20) not null,
  UPDATE_TIME    TIMESTAMP not null,
  CREATE_DEPT    VARCHAR(32),
  constraint PK_REP_INDEX_PRIMARY primary key (ID)
);
-- Add comments to the table 
comment on table REP_INDEX_PRIMARY
  is '自定义指标主表';
-- Add comments to the columns 
comment on column REP_INDEX_PRIMARY.ID
  is '主键ID';
comment on column REP_INDEX_PRIMARY.INDEX_NAME
  is '指标名称';
comment on column REP_INDEX_PRIMARY.INDEX_DESCRIBE
  is '指标描述';
comment on column REP_INDEX_PRIMARY.CREATE_USER
  is '创建人';
comment on column REP_INDEX_PRIMARY.CREATE_TIME
  is '创建时间';
comment on column REP_INDEX_PRIMARY.UPDATE_USER
  is '更新人';
comment on column REP_INDEX_PRIMARY.UPDATE_TIME
  is '更新时间';
comment on column REP_INDEX_PRIMARY.CREATE_DEPT
  is '创建部门';


  
-- Create table
create table REP_INDEX_SUB
(
  ID           VARCHAR(32) not null,
  PRI_ID       VARCHAR(50),
  INDEX_NAME   VARCHAR(50),
  INDEX_VALUE  VARCHAR(2000),
  INDEX_TYPE   VARCHAR(20),
  INDEX_DATE   DATE,
  INDEX_FILE   VARCHAR(100),
  CREATE_DEPT  VARCHAR(32),
  CREATE_USER  VARCHAR(20) not null,
  CREATE_TIME  TIMESTAMP not null,
  UPDATE_USER  VARCHAR(20) not null,
  UPDATE_TIME  TIMESTAMP not null,
  AUDIT_STATUS VARCHAR(20),
  constraint PK_REP_INDEX_SUB primary key (ID)
);
-- Add comments to the table 
comment on table REP_INDEX_SUB
  is '自定义指标维表';
-- Add comments to the columns 
comment on column REP_INDEX_SUB.ID
  is '主键ID';
comment on column REP_INDEX_SUB.PRI_ID
  is '指标外键';
comment on column REP_INDEX_SUB.INDEX_NAME
  is '指标名称';
comment on column REP_INDEX_SUB.INDEX_VALUE
  is '指标值';
comment on column REP_INDEX_SUB.INDEX_TYPE
  is '指标类型';
comment on column REP_INDEX_SUB.INDEX_DATE
  is '生效日期';
comment on column REP_INDEX_SUB.CREATE_DEPT
  is '创建部门';
comment on column REP_INDEX_SUB.CREATE_USER
  is '创建人';
comment on column REP_INDEX_SUB.CREATE_TIME
  is '创建时间';
comment on column REP_INDEX_SUB.UPDATE_USER
  is '更新人';
comment on column REP_INDEX_SUB.UPDATE_TIME
  is '更新时间';
comment on column REP_INDEX_SUB.AUDIT_STATUS
  is '审核状态';


  
-- Create table
create table REP_INFO
(
  REP_ID        VARCHAR(32) not null,
  TEMPLATE_ID   VARCHAR(50),
  TEMPLATE_PATH VARCHAR(150),
  TEMPLATE_NAME VARCHAR(200),
  REPORT_NAME   VARCHAR(150),
  REPORT_TYPE   VARCHAR(10),
  REP_ORDER     INTEGER,
  CREATE_USER   VARCHAR(20) not null,
  CREATE_DEPT   VARCHAR(32),
  CREATE_TIME   TIMESTAMP not null,
  UPDATE_USER   VARCHAR(20) not null,
  UPDATE_TIME   TIMESTAMP not null,
  constraint PK_REP_INFO primary key (REP_ID)
)

-- Add comments to the table 
comment on table REP_INFO
  is '报表信息表';
-- Add comments to the columns 
comment on column REP_INFO.REP_ID
  is '报表ID';
comment on column REP_INFO.TEMPLATE_ID
  is '模板编号';
comment on column REP_INFO.TEMPLATE_PATH
  is '模板目录';
comment on column REP_INFO.REPORT_NAME
  is '报表名称';
comment on column REP_INFO.REPORT_TYPE
  is '报表状态';
comment on column REP_INFO.CREATE_USER
  is '创建人';
comment on column REP_INFO.CREATE_DEPT
  is '创建部门';
comment on column REP_INFO.CREATE_TIME
  is '创建时间';
comment on column REP_INFO.UPDATE_USER
  is '更新人';
comment on column REP_INFO.UPDATE_TIME
  is '更新时间';


  
-- Create table
create table REP_POWER
(
  ID        VARCHAR(32) not null,
  TYPE      VARCHAR(32) not null,
  REPORT_ID VARCHAR(100),
  ROLE_NAME VARCHAR(100)
);
-- Add comments to the table 
comment on table REP_POWER
  is '报表权限';
-- Add comments to the columns 
comment on column REP_POWER.ID
  is 'ID';
comment on column REP_POWER.TYPE
  is '类型';
comment on column REP_POWER.REPORT_ID
  is '报表ID';

  
-- Create table
create table REP_TREE
(
  TREE_ID   VARCHAR(32) not null,
  P_ID      VARCHAR(32),
  TREE_NAME VARCHAR(150),
  FLAG      VARCHAR(10)
);
-- Add comments to the table 
comment on table REP_TREE
  is '报表展示树';
-- Add comments to the columns 
comment on column REP_TREE.TREE_ID
  is 'ID';
comment on column REP_TREE.P_ID
  is '父ID';
comment on column REP_TREE.TREE_NAME
  is '名称';
