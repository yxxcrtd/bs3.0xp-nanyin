/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/11/26 			                        */
/*==============================================================*/


DROP TABLE REP_INDEX_PRIMARY cascade constraints;
DROP TABLE REP_INDEX_SUB cascade constraints;
DROP TABLE REP_INFO cascade constraints;
DROP TABLE REP_JOB cascade constraints;
DROP TABLE REP_MONITOR cascade constraints;
DROP TABLE REP_POWER cascade constraints;
DROP TABLE REP_REVEAL cascade constraints;
DROP TABLE REP_SCREEN cascade constraints;
DROP TABLE REP_TREE cascade constraints;
DROP TABLE REP_BOOK_RELATION cascade constraints;


-- Create table REP_INDEX_PRIMARY
create table REP_INDEX_PRIMARY
(
  ID             VARCHAR2(32) not null,
  INDEX_NAME     VARCHAR2(2000),
  INDEX_DESCRIBE VARCHAR2(2000),
  CREATE_USER    VARCHAR2(20) not null,
  CREATE_TIME    TIMESTAMP(6) not null,
  UPDATE_USER    VARCHAR2(20) not null,
  UPDATE_TIME    TIMESTAMP(6) not null,
  CREATE_DEPT    VARCHAR2(32),
  PROD_ID        VARCHAR2(150)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16
    next 8
    minextents 1
    maxextents unlimited
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table REP_INDEX_PRIMARY
  add constraint PK_REP_INDEX_PRIMARY primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  
  
  
  
  -- Create table  REP_INDEX_SUB
create table REP_INDEX_SUB
(
  ID           VARCHAR2(32) not null,
  PRI_ID       VARCHAR2(50),
  INDEX_NAME   VARCHAR2(50),
  INDEX_VALUE  VARCHAR2(2000),
  INDEX_TYPE   VARCHAR2(20),
  INDEX_DATE   DATE,
  CREATE_DEPT  VARCHAR2(32),
  CREATE_USER  VARCHAR2(20) not null,
  CREATE_TIME  TIMESTAMP(6) not null,
  UPDATE_USER  VARCHAR2(20) not null,
  UPDATE_TIME  TIMESTAMP(6) not null,
  AUDIT_STATUS VARCHAR2(20),
  INDEX_FILE   VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16
    next 8
    minextents 1
    maxextents unlimited
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table REP_INDEX_SUB
  add constraint PK_REP_INDEX_SUB primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


  -- Create table REP_INFO
create table REP_INFO
(
  REP_ID        VARCHAR2(32) not null,
  TEMPLATE_ID   VARCHAR2(50),
  TEMPLATE_PATH VARCHAR2(150),
  TEMPLATE_NAME VARCHAR2(200),
  REPORT_NAME   VARCHAR2(150),
  REPORT_TYPE   VARCHAR2(10),
  CREATE_USER   VARCHAR2(20) not null,
  CREATE_DEPT   VARCHAR2(32),
  CREATE_TIME   TIMESTAMP(6) not null,
  UPDATE_USER   VARCHAR2(20) not null,
  UPDATE_TIME   TIMESTAMP(6) not null,
  REP_ORDER     NUMBER(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16
    next 8
    minextents 1
    maxextents unlimited
  );
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table REP_INFO
  add constraint PK_REP_INFO primary key (REP_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

  
  -- Create table
create table REP_POWER
(
  ID        VARCHAR2(32) not null,
  TYPE      VARCHAR2(32) not null,
  REPORT_ID VARCHAR2(100),
  ROLE_NAME VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 8
    minextents 1
    maxextents unlimited
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

  
  
  -- Create table REP_REVEAL
create table REP_REVEAL
(
  ID             VARCHAR2(32) not null,
  REP_ID         VARCHAR2(32),
  PROD_NAME      VARCHAR2(100),
  PRODID         VARCHAR2(100),
  END_DATE       DATE,
  START_DATE     DATE,
  SYS_FILENAME   VARCHAR2(150),
  SYS_FILEPATH   VARCHAR2(150),
  FILEPATH       VARCHAR2(150),
  FILENAME       VARCHAR2(150),
  CONFIRM_STATUS VARCHAR2(10),
  REVEAL_STATUS  VARCHAR2(10),
  STATUS         VARCHAR2(10),
  CREATE_USER    VARCHAR2(50) not null,
  CREATE_DEPT    VARCHAR2(50),
  CREATE_TIME    TIMESTAMP(6) not null,
  UPDATE_USER    VARCHAR2(50) not null,
  UPDATE_TIME    TIMESTAMP(6) not null,
  REVEAL_DATE    DATE,
  CONFIRM_DATE   DATE,
  REPORT_NAME    VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table REP_REVEAL
  is '信息披露';
-- Add comments to the columns 
comment on column REP_REVEAL.ID
  is 'ID';
comment on column REP_REVEAL.REP_ID
  is '报表编号';
comment on column REP_REVEAL.PROD_NAME
  is '产品名称';
comment on column REP_REVEAL.PRODID
  is '产品编号';
comment on column REP_REVEAL.END_DATE
  is '结束日';
comment on column REP_REVEAL.START_DATE
  is '开始日';
comment on column REP_REVEAL.SYS_FILENAME
  is '生成文件名称';
comment on column REP_REVEAL.SYS_FILEPATH
  is '生成文件路径';
comment on column REP_REVEAL.FILEPATH
  is '人工生成路径';
comment on column REP_REVEAL.FILENAME
  is '人工文件名称';
comment on column REP_REVEAL.CONFIRM_STATUS
  is '确认状态';
comment on column REP_REVEAL.REVEAL_STATUS
  is '披露状态';
comment on column REP_REVEAL.STATUS
  is '状态';
comment on column REP_REVEAL.CREATE_USER
  is '创建人';
comment on column REP_REVEAL.CREATE_DEPT
  is '创建部门';
comment on column REP_REVEAL.CREATE_TIME
  is '创建时间';
comment on column REP_REVEAL.UPDATE_USER
  is '更新人';
comment on column REP_REVEAL.UPDATE_TIME
  is '更新时间';
comment on column REP_REVEAL.REVEAL_DATE
  is '披露日';
comment on column REP_REVEAL.CONFIRM_DATE
  is '确认日';
-- Create/Recreate primary, unique and foreign key constraints 
alter table REP_REVEAL
  add constraint PK_REP_REVEAL primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

  
  -- Create table
create table REP_SCREEN
(
  SCREEN_ID     VARCHAR2(32) not null,
  REP_ID        VARCHAR2(32) not null,
  SCREEN_TYPE   VARCHAR2(10),
  SCREEN_KEY    VARCHAR2(50),
  SCREEN_NAME   VARCHAR2(100),
  SCREEN_VALUE  VARCHAR2(100),
  SCREEN_STATUS VARCHAR2(10),
  CREATE_USER   VARCHAR2(20) not null,
  CREATE_TIME   TIMESTAMP(6) not null,
  UPDATE_USER   VARCHAR2(20) not null,
  UPDATE_TIME   TIMESTAMP(6) not null,
  CREATE_DEPT   VARCHAR2(32),
  SCREEN_INDEX  NUMBER(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16
    next 8
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table REP_SCREEN
  is '查询信息配置表';
-- Add comments to the columns 
comment on column REP_SCREEN.SCREEN_ID
  is '主键ID';
comment on column REP_SCREEN.REP_ID
  is '报表ID';
comment on column REP_SCREEN.SCREEN_TYPE
  is '查询条件类型';
comment on column REP_SCREEN.SCREEN_KEY
  is '查询条件key';
comment on column REP_SCREEN.SCREEN_NAME
  is '查询条件名称';
comment on column REP_SCREEN.SCREEN_VALUE
  is '查询条件值';
comment on column REP_SCREEN.SCREEN_STATUS
  is '查询条件状态';
comment on column REP_SCREEN.CREATE_USER
  is '创建人';
comment on column REP_SCREEN.CREATE_TIME
  is '创建时间';
comment on column REP_SCREEN.UPDATE_USER
  is '更新人';
comment on column REP_SCREEN.UPDATE_TIME
  is '更新时间';
comment on column REP_SCREEN.CREATE_DEPT
  is '创建部门';
comment on column REP_SCREEN.SCREEN_INDEX
  is '查询条件顺序';
-- Create/Recreate primary, unique and foreign key constraints 
alter table REP_SCREEN
  add constraint PK_REP_SCREEN primary key (SCREEN_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

  
  -- Create table REP_TREE
create table REP_TREE
(
  TREE_ID   VARCHAR2(32) not null,
  P_ID      VARCHAR2(32),
  TREE_NAME VARCHAR2(150),
  FLAG      VARCHAR2(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 8
    minextents 1
    maxextents unlimited
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

  
  
  -- Create table
create table REP_MONITOR
(
  JOB_ID     VARCHAR2(32) not null,
  JOB_STATUS VARCHAR2(32),
  PRO_ID     VARCHAR2(100),
  PRO_NAME   VARCHAR2(100),
  END_DATE   DATE,
  NEXT_DATE  DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table REP_MONITOR
  is '报表监控';
-- Add comments to the columns 
comment on column REP_MONITOR.JOB_ID
  is '作业编号';
comment on column REP_MONITOR.JOB_STATUS
  is '运行状态';
comment on column REP_MONITOR.PRO_ID
  is '产品名称';
comment on column REP_MONITOR.PRO_NAME
  is '产品编号';
comment on column REP_MONITOR.END_DATE
  is '上一次运行时间';
comment on column REP_MONITOR.NEXT_DATE
  is '下一次运行时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table REP_MONITOR
  add constraint PK_REP_MONITOR primary key (JOB_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
--==============================================================
-- Table: REP_BOOK_RELATION
--==============================================================
CREATE TABLE
    REP_BOOK_RELATION
    (
        DICT_CODE VARCHAR(50) NOT NULL,
        SUBJECT_NO VARCHAR(50) NOT NULL,
        SUBJECT_LEVEL DECIMAL(10,0) NOT NULL,
        DICT_VALUE VARCHAR(300) NOT NULL,
        FLAG VARCHAR(2),
        CREATE_USER VARCHAR(20) NOT NULL,
        CREATE_DEPT VARCHAR(32),
        CREATE_TIME TIMESTAMP NOT NULL,
        UPDATE_USER VARCHAR(20) NOT NULL,
        UPDATE_TIME TIMESTAMP NOT NULL,
        CONSTRAINT REP_BOOK_RELATION PRIMARY KEY (DICT_CODE, SUBJECT_NO)
    );
COMMENT ON TABLE REP_BOOK_RELATION
IS
    '统计资产科目映射表';
COMMENT ON COLUMN REP_BOOK_RELATION.DICT_CODE
IS
    '资产编号';
COMMENT ON COLUMN REP_BOOK_RELATION.SUBJECT_NO
IS
    '客户号';
COMMENT ON COLUMN REP_BOOK_RELATION.SUBJECT_LEVEL
IS
    '科目级别';
COMMENT ON COLUMN REP_BOOK_RELATION.DICT_VALUE
IS
    '资产名称';
COMMENT ON COLUMN REP_BOOK_RELATION.FLAG
IS
    '加减标志 01为加 02 为减';
COMMENT ON COLUMN REP_BOOK_RELATION.CREATE_USER
IS
    '创建人';
COMMENT ON COLUMN REP_BOOK_RELATION.CREATE_DEPT
IS
    '创建部门';
COMMENT ON COLUMN REP_BOOK_RELATION.CREATE_TIME
IS
    '创建时间';
COMMENT ON COLUMN REP_BOOK_RELATION.UPDATE_USER
IS
    '更新人';
COMMENT ON COLUMN REP_BOOK_RELATION.UPDATE_TIME
IS
    '更新时间';
    
/*jasper改造 zhangyf 20190220 start*/
drop table "REP_REPORTINFO";
CREATE TABLE "REP_REPORTINFO" 
   (	
   "ID" NUMBER(10,0) NOT NULL ENABLE, 
	"REPORTID" VARCHAR2(255 BYTE), 
	"URI" VARCHAR2(255 BYTE), 
	"FOLDER" VARCHAR2(255 BYTE), 
	"REPOSITORY" VARCHAR2(255 BYTE), 
	"REPORTDESC" VARCHAR2(255 BYTE), 
	"DATASOURCE" VARCHAR2(255 BYTE), 
	"GENERATOR" VARCHAR2(255 BYTE), 
	"USERNAME" VARCHAR2(255 BYTE), 
	"USERPWD" VARCHAR2(255 BYTE), 
	"REPORTPARAMS" VARCHAR2(255 BYTE),
  	"CREATE_DEPT" VARCHAR2(32),
  	"CREATE_USER" VARCHAR2(20),
  	"CREATE_TIME" TIMESTAMP(6),
  	"UPDATE_USER" VARCHAR2(20),
  	"UPDATE_TIME" TIMESTAMP(6),
	 PRIMARY KEY ("ID")
   );
/*jasper改造 zhangyf 20190220 end*/
   
/*投资组合详细表 zhangyf 20190228 start*/
DROP TABLE REP_INVESTDETAIL;
CREATE TABLE REP_INVESTDETAIL
(
		SEQNO VARCHAR2(50) NOT NULL,
		PROD_ID VARCHAR2(50) NOT NULL,
		VAL_DATE DATE,
		ASSET_TYPE_CODE VARCHAR2(50),
		ASSETS_VALUE_NET DECIMAL(30,14),
		ASSETS_VALUE_NET_SCALE DECIMAL(30,14),
		IS_SCALE_OUT CHAR(1),
		STATUS CHAR(10),
		CREATE_USER VARCHAR2(20),
		CREATE_DEPT VARCHAR2(32),
		CREATE_TIME TIMESTAMP,
		UPDATE_USER VARCHAR2(20),
		UPDATE_TIME TIMESTAMP,
    CONSTRAINT REP_INVESTDETAIL PRIMARY KEY (SEQNO)
);
COMMENT ON TABLE REP_INVESTDETAIL IS '投资组合详细表';
COMMENT ON COLUMN REP_INVESTDETAIL.SEQNO IS '主键';
COMMENT ON COLUMN REP_INVESTDETAIL.PROD_ID IS '产品代码';
COMMENT ON COLUMN REP_INVESTDETAIL.VAL_DATE IS '估值日';
COMMENT ON COLUMN REP_INVESTDETAIL.ASSET_TYPE_CODE IS '资产类别代码';
COMMENT ON COLUMN REP_INVESTDETAIL.ASSETS_VALUE_NET IS '资产估值';
COMMENT ON COLUMN REP_INVESTDETAIL.ASSETS_VALUE_NET_SCALE IS '资产估值占组合比例';
COMMENT ON COLUMN REP_INVESTDETAIL.IS_SCALE_OUT IS '是否超限';
COMMENT ON COLUMN REP_INVESTDETAIL.STATUS IS '状态';
COMMENT ON COLUMN REP_INVESTDETAIL.CREATE_USER IS '创建人';
COMMENT ON COLUMN REP_INVESTDETAIL.CREATE_DEPT IS '创建部门';
COMMENT ON COLUMN REP_INVESTDETAIL.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN REP_INVESTDETAIL.UPDATE_USER IS '更新人';
COMMENT ON COLUMN REP_INVESTDETAIL.UPDATE_TIME IS '更新时间';
/*投资组合详细表 zhangyf 20190228 end*/