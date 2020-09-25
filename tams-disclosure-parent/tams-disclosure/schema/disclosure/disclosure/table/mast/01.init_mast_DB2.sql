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
DROP table REP_REVEAL;
drop table REP_INSTALL_FILE;
drop table REP_INSTALL_LOG;
drop table REP_INSTALL_LOG_DETAIL;
drop table REP_RULE;
drop table REP_JOB;
drop table REP_JOB_RANGE;
drop table REP_JOB_EXCLUDE_PRO;
drop table REP_SCREEN;
drop table REP_TASK_DEATIL;


-- Create table
create table REP_SCREEN
(
  SCREEN_ID     VARCHAR(32) not null,
  REP_ID        VARCHAR(32) not null,
  SCREEN_TYPE   VARCHAR(10),
  SCREEN_KEY    VARCHAR(50),
  SCREEN_NAME   VARCHAR(100),
  SCREEN_VALUE  VARCHAR(100),
  SCREEN_STATUS VARCHAR(10),
  CREATE_USER   VARCHAR(20) not null,
  CREATE_TIME   TIMESTAMP not null,
  UPDATE_USER   VARCHAR(20) not null,
  UPDATE_TIME   TIMESTAMP not null,
  CREATE_DEPT   VARCHAR(32),
  SCREEN_INDEX  INTEGER,
  constraint PK_REP_SCREEN primary key (SCREEN_ID)
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
  STATUS      VARCHAR(10),
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



--==============================================================
-- Table: REP_TASK_DEATIL
--==============================================================
create table REP_TASK_DEATIL
(
   TASKDATE        VARCHAR(32)            not null,
   TASKFLAG         VARCHAR(32)            not null,
   FLAG              VARCHAR(100)
);

comment on table REP_TASK_DEATIL is
'定时任务标志表';

comment on column REP_TASK_DEATIL.TASKDATE is
'定时日期';

comment on column REP_TASK_DEATIL.TASKFLAG is
'定时状态,0运行,1待运行';


  
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
  INDEX_DETAIL VARCHAR(500),
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
comment on column REP_INDEX_SUB.INDEX_DETAIL
  is '产品范围说明';  
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
  REPORT_CATEGORY      VARCHAR(20),
  REP_ORDER     INTEGER,
  CREATE_USER   VARCHAR(20) not null,
  CREATE_DEPT   VARCHAR(32),
  CREATE_TIME   TIMESTAMP not null,
  UPDATE_USER   VARCHAR(20) not null,
  UPDATE_TIME   TIMESTAMP not null,
  constraint PK_REP_INFO primary key (REP_ID)
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
comment on column REP_INFO.REPORT_CATEGORY is
'报表类别';
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
--  ,
--  CREATE_USER   VARCHAR(20) not null,
--  CREATE_DEPT   VARCHAR(32),
--  CREATE_TIME   TIMESTAMP not null,
--  UPDATE_USER   VARCHAR(20) not null,
--  UPDATE_TIME   TIMESTAMP not null,
--  constraint PK_REP_TREE primary key (TREE_ID)
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
--comment on column REP_TREE.CREATE_USER
--  is '创建人';
--comment on column REP_TREE.CREATE_DEPT
--  is '创建部门';
--comment on column REP_TREE.CREATE_TIME
--  is '创建时间';
--comment on column REP_TREE.UPDATE_USER
--  is '更新人';
--comment on column REP_TREE.UPDATE_TIME
--  is '更新时间'; 

--==============================================================
-- Table: REP_INSTALL_LOG
--==============================================================
create table REP_INSTALL_LOG
(
   ID                   VARCHAR(32)            not null,
   JOB_ID               VARCHAR(32),
   JOB_SEQ              VARCHAR(100),
   JOB_NAME             VARCHAR(100),
   END_DATE             TIMESTAMP,
   START_DATE           TIMESTAMP,
   EXEC_DATE            DATE,
   LASTEXEC_DATE        TIMESTAMP,
   FLAG                 VARCHAR(10),
   EXEC_DETAIL          VARCHAR(500),
   EXEC_STATUS          VARCHAR(10),
   EXEC_RESULT          VARCHAR(10),
   STATUS               VARCHAR(10),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_INSTALL_LOG_ primary key (ID)
);

comment on table REP_INSTALL_LOG is
'批处理日志表主表';

comment on column REP_INSTALL_LOG.ID is
'流水';

comment on column REP_INSTALL_LOG.JOB_ID is
'作业编号';

comment on column REP_INSTALL_LOG.JOB_SEQ is
'用于标识报表与作业批次的关系';

comment on column REP_INSTALL_LOG.JOB_NAME is
'作业名称';

comment on column REP_INSTALL_LOG.END_DATE is
'开始时间';

comment on column REP_INSTALL_LOG.START_DATE is
'结束时间';

comment on column REP_INSTALL_LOG.EXEC_DATE is
'执行日期';

comment on column REP_INSTALL_LOG.LASTEXEC_DATE is
'最新执行时间';

comment on column REP_INSTALL_LOG.FLAG is
'日志类型';

comment on column REP_INSTALL_LOG.EXEC_DETAIL is
'执行状态';

comment on column REP_INSTALL_LOG.EXEC_STATUS is
'执行状态';

comment on column REP_INSTALL_LOG.STATUS is
'状态';

comment on column REP_INSTALL_LOG.CREATE_USER is
'创建人';

comment on column REP_INSTALL_LOG.CREATE_DEPT is
'创建部门';

comment on column REP_INSTALL_LOG.CREATE_TIME is
'创建时间';

comment on column REP_INSTALL_LOG.UPDATE_USER is
'更新人';

comment on column REP_INSTALL_LOG.UPDATE_TIME is
'更新时间';





create table REP_REVEAL
(
   ID                   VARCHAR(32)            not null,
   REP_ID               VARCHAR(32),
   REPORT_NAME          VARCHAR(200),
   REPORT_CATEGORY      VARCHAR(200),
   PROD_NAME            VARCHAR(100),
   PRODID               VARCHAR(100),
   END_DATE             DATE,
   START_DATE           DATE,
   SYS_FILENAME         VARCHAR(150),
   SYS_FILEPATH         VARCHAR(150),
   SYS_FILENAME_DATE    TIMESTAMP,
   FILEPATH             VARCHAR(150),
   TIME                 TIMESTAMP,
   FILENAME             VARCHAR(150),
   ANNEX                VARCHAR(150),
   ANNEX_PATH           VARCHAR(150),
   CONFIRM_STATUS       VARCHAR(10),
   CONFIRM_USER         VARCHAR(50),
   CONFIRM_DATE         TIMESTAMP,
   REVEAL_STATUS        VARCHAR(10),
   REVEAL_DATE          TIMESTAMP,
   REVEAL_END_DATE      TIMESTAMP,
   REVEAL_USER          VARCHAR(50),
   IS_REVEAL            VARCHAR(10),
   IS_CONFIRM           VARCHAR(10),
   DETAIL               VARCHAR(1000),
   REVEAL_CHANNEL       VARCHAR(100),
   STATUS               VARCHAR(10),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_REVEAL_ID primary key (ID)
);

comment on table REP_REVEAL is
'信息披露';

comment on column REP_REVEAL.ID is
'ID';

comment on column REP_REVEAL.REP_ID is
'报表编号';

comment on column REP_REVEAL.REPORT_NAME is
'报表名称';

comment on column REP_REVEAL.REPORT_CATEGORY is
'报表类别';

comment on column REP_REVEAL.PROD_NAME is
'产品名称';

comment on column REP_REVEAL.PRODID is
'产品编号';

comment on column REP_REVEAL.END_DATE is
'结束日';

comment on column REP_REVEAL.START_DATE is
'开始日';

comment on column REP_REVEAL.SYS_FILENAME is
'生成文件名称';

comment on column REP_REVEAL.SYS_FILEPATH is
'生成文件路径';

comment on column REP_REVEAL.SYS_FILENAME_DATE is
'生成文件时间';

comment on column REP_REVEAL.FILEPATH is
'人工生成路径';

comment on column REP_REVEAL.TIME is
'人工上传时间';

comment on column REP_REVEAL.FILENAME is
'人工文件名称';

comment on column REP_REVEAL.ANNEX is
'附件名称';

comment on column REP_REVEAL.ANNEX_PATH is
'附件路径';

comment on column REP_REVEAL.CONFIRM_STATUS is
'确认状态';

comment on column REP_REVEAL.CONFIRM_USER is
'确认人';

comment on column REP_REVEAL.CONFIRM_DATE is
'确认时间';

comment on column REP_REVEAL.REVEAL_STATUS is
'披露状态';

comment on column REP_REVEAL.REVEAL_DATE is
'披露时间';

comment on column REP_REVEAL.REVEAL_END_DATE is
'披露截止日';

comment on column REP_REVEAL.REVEAL_USER is
'披露人';

comment on column REP_REVEAL.IS_REVEAL is
'是否自动披露';

comment on column REP_REVEAL.IS_CONFIRM is
'是否自动确认';

comment on column REP_REVEAL.DETAIL is
'上传说明';

comment on column REP_REVEAL.REVEAL_CHANNEL is
'披露渠道';

comment on column REP_REVEAL.STATUS is
'状态';

comment on column REP_REVEAL.CREATE_USER is
'创建人';

comment on column REP_REVEAL.CREATE_DEPT is
'创建部门';

comment on column REP_REVEAL.CREATE_TIME is
'创建时间';

comment on column REP_REVEAL.UPDATE_USER is
'更新人';

comment on column REP_REVEAL.UPDATE_TIME is
'更新时间';


--==============================================================
-- Table: REP_INSTALL_LOG_DETAIL
--==============================================================
create table REP_INSTALL_LOG_DETAIL
(
   ID                   VARCHAR(32)            not null,
   JOB_ID               VARCHAR(32),
   JOB_SEQ              VARCHAR(100),
   PRODID               VARCHAR(100),
   PROD_NAME            VARCHAR(500),
   END_DATE             DATE,
   START_DATE           DATE,
   REVEAL_CHANNEL       VARCHAR(100),
   REVEAL_END_DATE      TIMESTAMP,
   REP_TYPE             VARCHAR(10),
   REP_ID               VARCHAR(32),
   REP_NAME             VARCHAR(500),
   FILE_TYPE            VARCHAR(10),
   IS_CONFIRM           VARCHAR(10),
   IS_REVEAL            VARCHAR(10),
   FLAG                 VARCHAR(10),
   EXEC_STATUS          VARCHAR(10),
   STATUS               VARCHAR(10),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_INSTALL_LOG_DETAIL primary key (ID)
);

comment on table REP_INSTALL_LOG_DETAIL is
'批处理日志表详情';

comment on column REP_INSTALL_LOG_DETAIL.ID is
'流水';

comment on column REP_INSTALL_LOG_DETAIL.JOB_ID is
'作业编号';

comment on column REP_INSTALL_LOG_DETAIL.JOB_SEQ is
'用于标识报表与作业批次的关系';

comment on column REP_INSTALL_LOG_DETAIL.PRODID is
'产品编号';

comment on column REP_INSTALL_LOG_DETAIL.PROD_NAME is
'产品名称';

comment on column REP_INSTALL_LOG_DETAIL.END_DATE is
'结束日';

comment on column REP_INSTALL_LOG_DETAIL.START_DATE is
'开始日';

comment on column REP_INSTALL_LOG_DETAIL.REVEAL_CHANNEL is
'披露渠道';

comment on column REP_INSTALL_LOG_DETAIL.REVEAL_END_DATE is
'披露截止日';

comment on column REP_INSTALL_LOG_DETAIL.REP_TYPE is
'报表类型(报表、集合)';

comment on column REP_INSTALL_LOG_DETAIL.REP_ID is
'报表编号';

comment on column REP_INSTALL_LOG_DETAIL.REP_NAME is
'报表名称';

comment on column REP_INSTALL_LOG_DETAIL.FILE_TYPE is
'生成文件类型';

comment on column REP_INSTALL_LOG_DETAIL.IS_CONFIRM is
'是否自动确认';

comment on column REP_INSTALL_LOG_DETAIL.IS_REVEAL is
'是否自动披露';

comment on column REP_INSTALL_LOG_DETAIL.FLAG is
'日志类型';

comment on column REP_INSTALL_LOG_DETAIL.EXEC_STATUS is
'执行状态';

comment on column REP_INSTALL_LOG_DETAIL.STATUS is
'状态';

comment on column REP_INSTALL_LOG_DETAIL.CREATE_USER is
'创建人';

comment on column REP_INSTALL_LOG_DETAIL.CREATE_DEPT is
'创建部门';

comment on column REP_INSTALL_LOG_DETAIL.CREATE_TIME is
'创建时间';

comment on column REP_INSTALL_LOG_DETAIL.UPDATE_USER is
'更新人';

comment on column REP_INSTALL_LOG_DETAIL.UPDATE_TIME is
'更新时间';



--==============================================================
-- Table: REP_RULE
--==============================================================
create table REP_RULE
(
   ID                   VARCHAR(100)            not null,
   RULE_NAME               VARCHAR(200),
   REP_ID               VARCHAR(100),
   REP_NAME               VARCHAR(200),
   RCF_TYPE             VARCHAR(10)            not null,
   RCF_VALUE            VARCHAR(100),
   BASIS_DATE_TYPE      VARCHAR(100),
   MAKE_TYPE            VARCHAR(10),
   MAKE_VALUE           VARCHAR(10),
   MAKE_DATE_TYPE       VARCHAR(10),
   MAKE_TIME            VARCHAR(10),
   REVEAL_TYPE          VARCHAR(10),
   REVEAL_VALUE         VARCHAR(10),
   REVEAL_DATE_TYPE     VARCHAR(10),
   REVEAL_TIME          VARCHAR(10),
   DATA_TYPE            VARCHAR(10),
   DATA_RANGE           VARCHAR(10),
   DATA_BEGIN           VARCHAR(10),
   DATA_END             VARCHAR(10),
   DATA_VALUE           VARCHAR(50)            not null,
   FILE_OUT_TYPE        VARCHAR(50),
   REVEAL_CHANNEL       VARCHAR(50)            not null,
   FTP_ID               VARCHAR(100)           not null,
   IS_CONFIRM           VARCHAR(10),
   IS_REVEAL            VARCHAR(10),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   DETAIL               VARCHAR(2000)          not null,
   constraint P_REP_RULE_ID primary key (ID)
);

comment on table REP_RULE is
'作业规则表';

comment on column REP_RULE.ID is
'规则编号';

comment on column REP_RULE.RULE_NAME is
'规则名称';

comment on column REP_RULE.REP_ID is
'报表模板';

comment on column REP_RULE.REP_NAME is
'报表名称';

comment on column REP_RULE.RCF_TYPE is
'披露频率类型';

comment on column REP_RULE.RCF_VALUE is
'频率频率值';

comment on column REP_RULE.BASIS_DATE_TYPE is
'基准日类型';

comment on column REP_RULE.MAKE_TYPE is
'生成时点类型';

comment on column REP_RULE.MAKE_VALUE is
'生成时点值';

comment on column REP_RULE.MAKE_DATE_TYPE is
'生成时点日期类型';

comment on column REP_RULE.MAKE_TIME is
'生成时点时间';

comment on column REP_RULE.REVEAL_TYPE is
'披露时点类型';

comment on column REP_RULE.REVEAL_VALUE is
'披露时点值';

comment on column REP_RULE.REVEAL_DATE_TYPE is
'披露时点日期类型';

comment on column REP_RULE.REVEAL_TIME is
'披露时点时间';

comment on column REP_RULE.DATA_TYPE is
'报表数据类型';

comment on column REP_RULE.DATA_RANGE is
'取数范围';

comment on column REP_RULE.DATA_BEGIN is
'取数开始日';

comment on column REP_RULE.DATA_END is
'取数结束日';

comment on column REP_RULE.DATA_VALUE is
'取数范围值';

comment on column REP_RULE.FILE_OUT_TYPE is
'输出格式';

comment on column REP_RULE.REVEAL_CHANNEL is
'披露渠道';

comment on column REP_RULE.FTP_ID is
'文件输出地址';

comment on column REP_RULE.IS_CONFIRM is
'是否自动确认';

comment on column REP_RULE.IS_REVEAL is
'是否自动披露';

comment on column REP_RULE.CREATE_USER is
'创建人';

comment on column REP_RULE.CREATE_DEPT is
'创建部门';

comment on column REP_RULE.CREATE_TIME is
'创建时间';

comment on column REP_RULE.UPDATE_USER is
'更新人';

comment on column REP_RULE.UPDATE_TIME is
'更新时间';

comment on column REP_RULE.DETAIL is
'说明';


--==============================================================
-- Table: REP_JOB
--==============================================================
create table REP_JOB
(
   JOB_ID               VARCHAR(100)            not null,
   JOB_NAME             VARCHAR(200),
   LOOP_DATE_TYPE       VARCHAR(100),
   LOOP_DATE_VALUE      VARCHAR(100),
   LOOP_DATE_DETAIL     VARCHAR(100),
   START_DATE           VARCHAR(100),
   END_DATE             VARCHAR(100),
   RULE_ID              VARCHAR(100),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_JOB_ID primary key (JOB_ID)
);

comment on table REP_JOB is
'报表作业';

comment on column REP_JOB.JOB_ID is
'作业编号';

comment on column REP_JOB.JOB_NAME is
'作业名称';

comment on column REP_JOB.LOOP_DATE_TYPE is
'时间样式类型';

comment on column REP_JOB.LOOP_DATE_VALUE is
'时间样式值';

comment on column REP_JOB.LOOP_DATE_DETAIL is
'时间样式描述';

comment on column REP_JOB.START_DATE is
'作业开始日';

comment on column REP_JOB.END_DATE is
'作业结束日';

comment on column REP_JOB.RULE_ID is
'规则编号';

comment on column REP_JOB.CREATE_USER is
'创建人';

comment on column REP_JOB.CREATE_DEPT is
'创建部门';

comment on column REP_JOB.CREATE_TIME is
'创建时间';

comment on column REP_JOB.UPDATE_USER is
'更新人';

comment on column REP_JOB.UPDATE_TIME is
'更新时间';


--==============================================================
-- Table: REP_JOB_RANGE
--==============================================================
create table REP_JOB_RANGE
(
   ID                   VARCHAR(32)            not null,
   JOB_ID               VARCHAR(32),
   FLAG                 VARCHAR(100),
   RULE_TYPE1           VARCHAR(100),
   RULE_TYPE2           VARCHAR(100),
   RULE_TYPE3           VARCHAR(100),
   RULE_TYPE4           VARCHAR(300),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_JOB_RANGE_ID primary key (ID)
);

comment on table REP_JOB_RANGE is
'报表作业范围';

comment on column REP_JOB_RANGE.ID is
'ID';

comment on column REP_JOB_RANGE.JOB_ID is
'作业编号';

comment on column REP_JOB_RANGE.FLAG is
'规则类型';

comment on column REP_JOB_RANGE.RULE_TYPE1 is
'规则1(枚举)';

comment on column REP_JOB_RANGE.RULE_TYPE2 is
'规则2(符号)';

comment on column REP_JOB_RANGE.RULE_TYPE3 is
'规则3(值类型)';

comment on column REP_JOB_RANGE.RULE_TYPE4 is
'规则4(值)';

comment on column REP_JOB_RANGE.CREATE_USER is
'创建人';

comment on column REP_JOB_RANGE.CREATE_DEPT is
'创建部门';

comment on column REP_JOB_RANGE.CREATE_TIME is
'创建时间';

comment on column REP_JOB_RANGE.UPDATE_USER is
'更新人';

comment on column REP_JOB_RANGE.UPDATE_TIME is
'更新时间';




--==============================================================
-- Table: REP_JOB_EXCLUDE_PRO
--==============================================================
create table REP_JOB_EXCLUDE_PRO
(
   ID                   VARCHAR(32)            not null,
   JOB_ID               VARCHAR(32),
   PROD_ID              VARCHAR(100),
   PROD_NAME            VARCHAR(100),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_JOB_EXCLUDE_PRO_ID primary key (ID)
);

comment on table REP_JOB_EXCLUDE_PRO is
'报表作业屏蔽产品';

comment on column REP_JOB_EXCLUDE_PRO.ID is
'ID';

comment on column REP_JOB_EXCLUDE_PRO.JOB_ID is
'作业编号';

comment on column REP_JOB_EXCLUDE_PRO.PROD_ID is
'产品编号';

comment on column REP_JOB_EXCLUDE_PRO.PROD_NAME is
'产品名称';

comment on column REP_JOB_EXCLUDE_PRO.CREATE_USER is
'创建人';

comment on column REP_JOB_EXCLUDE_PRO.CREATE_DEPT is
'创建部门';

comment on column REP_JOB_EXCLUDE_PRO.CREATE_TIME is
'创建时间';

comment on column REP_JOB_EXCLUDE_PRO.UPDATE_USER is
'更新人';

comment on column REP_JOB_EXCLUDE_PRO.UPDATE_TIME is
'更新时间';


--==============================================================
-- Table: REP_INSTALL_FILE
--==============================================================
create table REP_INSTALL_FILE
(
   ID                   VARCHAR(32)            not null,
   JOB_ID               VARCHAR(32),
   JOB_SEQ              VARCHAR(100),
   PRODID               VARCHAR(100),
   PROD_NAME            VARCHAR(200),
   END_DATE             DATE,
   START_DATE           DATE,
   REVEAL_CHANNEL       VARCHAR(100),
   REVEAL_END_DATE      TIMESTAMP,
   REP_TYPE             VARCHAR(10),
   REP_ID               VARCHAR(32),
   REP_NAME             VARCHAR(200),
   FILE_TYPE            VARCHAR(10),
   IS_CONFIRM           VARCHAR(10),
   IS_REVEAL            VARCHAR(10),
   STATUS               VARCHAR(10),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_INSTALL_FILE primary key (ID)
);

comment on table REP_INSTALL_FILE is
'批处理组装信息表';

comment on column REP_INSTALL_FILE.ID is
'流水';

comment on column REP_INSTALL_FILE.JOB_ID is
'作业编号';

comment on column REP_INSTALL_FILE.JOB_SEQ is
'用于标识报表与作业批次的关系';

comment on column REP_INSTALL_FILE.PRODID is
'产品编号';

comment on column REP_INSTALL_FILE.PROD_NAME is
'产品名称';

comment on column REP_INSTALL_FILE.END_DATE is
'结束日';

comment on column REP_INSTALL_FILE.START_DATE is
'开始日';

comment on column REP_INSTALL_FILE.REVEAL_CHANNEL is
'披露渠道';

comment on column REP_INSTALL_FILE.REVEAL_END_DATE is
'披露截止日';

comment on column REP_INSTALL_FILE.REP_TYPE is
'报表类型(报表、集合)';

comment on column REP_INSTALL_FILE.REP_ID is
'报表编号';

comment on column REP_INSTALL_FILE.REP_NAME is
'报表名称';

comment on column REP_INSTALL_FILE.FILE_TYPE is
'生成文件类型';

comment on column REP_INSTALL_FILE.IS_CONFIRM is
'是否自动确认';

comment on column REP_INSTALL_FILE.IS_REVEAL is
'是否自动披露';

comment on column REP_INSTALL_FILE.STATUS is
'状态';

comment on column REP_INSTALL_FILE.CREATE_USER is
'创建人';

comment on column REP_INSTALL_FILE.CREATE_DEPT is
'创建部门';

comment on column REP_INSTALL_FILE.CREATE_TIME is
'创建时间';

comment on column REP_INSTALL_FILE.UPDATE_USER is
'更新人';

comment on column REP_INSTALL_FILE.UPDATE_TIME is
'更新时间';



--==============================================================
-- Table: REP_FTP_DETAIL
--==============================================================
create table REP_FTP_DETAIL
(
   ID                   VARCHAR(32)            not null,
   PROD_ID              VARCHAR(32),
   REPORT_NAME          VARCHAR(32),
   STATR_DATE           DATE,
   END_DATE             DATE,
   FILE_NAME            VARCHAR(100),
   FILE_OK              VARCHAR(100),
   DATE                 DATE,
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_FTP_PATH_ID primary key (ID)
);

comment on table REP_FTP_DETAIL is
'上传文件记录';

comment on column REP_FTP_DETAIL.ID is
'主键';

comment on column REP_FTP_DETAIL.PROD_ID is
'披露产品编号';

comment on column REP_FTP_DETAIL.REPORT_NAME is
'披露报表名称';

comment on column REP_FTP_DETAIL.STATR_DATE is
'报告期开始日';

comment on column REP_FTP_DETAIL.END_DATE is
'报告期结束日';

comment on column REP_FTP_DETAIL.FILE_NAME is
'披露文件名称';

comment on column REP_FTP_DETAIL.FILE_OK is
'披露确认文件状态';

comment on column REP_FTP_DETAIL."DATE" is
'上传时间';

comment on column REP_FTP_DETAIL.CREATE_USER is
'创建人';

comment on column REP_FTP_DETAIL.CREATE_DEPT is
'创建部门';

comment on column REP_FTP_DETAIL.CREATE_TIME is
'创建时间';

comment on column REP_FTP_DETAIL.UPDATE_USER is
'更新人';

comment on column REP_FTP_DETAIL.UPDATE_TIME is
'更新时间';















/*****************************************************************/

drop table REP_PROD_INFO;
drop table REP_VAL_TABLE_DATA;
drop table REP_BAL_TABLE_DATA;
drop table REP_PROD_INDEX;
drop table REP_ASSET_INFO;
drop table REP_BOK_SUBJECT;
drop table REP_PROD_FEE_DEAL;
drop table REP_BOOK_RELATION;

--==============================================================
-- Table: REP_PROD_INFO
--==============================================================
create table REP_PROD_INFO
(
   PROD_ID              VARCHAR(32)            not null,
   PROD_RE              VARCHAR(100),
   PROD_NAME            VARCHAR(100)           not null,
   PROD_ABBR            VARCHAR(100),
   RAISE_MIN            VARCHAR(10),
   RAISE_MAX            VARCHAR(10),
   RAISE_STRDATE        DATE,
   RAISE_ENDDATE        DATE,
   RAISE_CCY            VARCHAR(10),
   SET_DATE             DATE,
   VDATE                DATE,
   FIRST_OPEN_DAY       DATE,
   MDATE                DATE,
   ACT_MDATE            DATE,
   TERM                 VARCHAR(10),
   WINDUP_DATE          DATE,
   RAISE_AMT            NUMERIC(30,4),
   ISSUE_MODE           VARCHAR(100),
   PROFIT_TYPE          VARCHAR(100)           not null,
   PROD_TYPE            VARCHAR(100),
   ISSUE_STATUS         VARCHAR(100)           not null,
   RAISE_TYPE           VARCHAR(100)           not null,
   RISK_LEVEL           VARCHAR(100),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_RULE_ID primary key (PROD_ID)
);

comment on table REP_PROD_INFO is
'产品信息';

comment on column REP_PROD_INFO.PROD_ID is
'产品代码';

comment on column REP_PROD_INFO.PROD_RE is
'产品登记编码';

comment on column REP_PROD_INFO.PROD_NAME is
'产品名称';

comment on column REP_PROD_INFO.PROD_ABBR is
'产品简称';

comment on column REP_PROD_INFO.RAISE_MIN is
'募集下限';

comment on column REP_PROD_INFO.RAISE_MAX is
'募集上限';

comment on column REP_PROD_INFO.RAISE_STRDATE is
'募集开始日';

comment on column REP_PROD_INFO.RAISE_ENDDATE is
'募集结束日';

comment on column REP_PROD_INFO.RAISE_CCY is
'募集币种';

comment on column REP_PROD_INFO.SET_DATE is
'产品成立日';

comment on column REP_PROD_INFO.VDATE is
'产品起息日';

comment on column REP_PROD_INFO.FIRST_OPEN_DAY is
'首次开放日';

comment on column REP_PROD_INFO.MDATE is
'产品到期日';

comment on column REP_PROD_INFO.ACT_MDATE is
'实际到期日';

comment on column REP_PROD_INFO.TERM is
'产品期限';

comment on column REP_PROD_INFO.WINDUP_DATE is
'产品清盘日';

comment on column REP_PROD_INFO.ISSUE_MODE is
'发行方式';

comment on column REP_PROD_INFO.PROFIT_TYPE is
'收益类型';

comment on column REP_PROD_INFO.PROD_TYPE is
'产品类型';

comment on column REP_PROD_INFO.ISSUE_STATUS is
'发行状态';

comment on column REP_PROD_INFO.RAISE_TYPE is
'募集方式';

comment on column REP_PROD_INFO.RISK_LEVEL is
'产品风险等级';

comment on column REP_PROD_INFO.CREATE_USER is
'创建人';

comment on column REP_PROD_INFO.CREATE_DEPT is
'创建部门';

comment on column REP_PROD_INFO.CREATE_TIME is
'创建时间';

comment on column REP_PROD_INFO.UPDATE_USER is
'更新人';

comment on column REP_PROD_INFO.UPDATE_TIME is
'更新时间';





--==============================================================
-- Table: REP_VAL_TABLE_DATA
--==============================================================
create table REP_VAL_TABLE_DATA
(
   SEQ_NO               VARCHAR(32)            not null,
   PROD_ID              VARCHAR(32),
   PROD_NAME            VARCHAR(200),
   PROFIT_TYPE          VARCHAR(50),
   VAL_DATE             DATE,
   DETAIL_DIST          VARCHAR(50),
   LAYERING_ID          VARCHAR(60),
   SUBJECT_NO           VARCHAR(300),
   SUBJECT_NAME         VARCHAR(500),
   FSUBJECT_ID          VARCHAR(32),
   NUM_AMT              NUMERIC(30, 2),
   UNIT_COST            NUMERIC(30, 2),
   O_CCY                VARCHAR(50),
   B_CCY                VARCHAR(50),
   EX_RATE              NUMERIC(30, 14),
   O_COST               NUMERIC(30, 2),
   B_COST               NUMERIC(30, 2),
   B_COST_PERCENT       NUMERIC(30, 14),
   CLOSE_PRICE          NUMERIC(30, 14),
   O_MARKET_VALUE       NUMERIC(30, 2),
   B_MARKET_VALUE       NUMERIC(30, 2),
   B_VALUE_PERCENT      NUMERIC(30, 14),
   O_VALUE_INCREMENT    NUMERIC(30, 2),
   B_VALUE_INCREMENT    NUMERIC(30, 2),
   SEC_STATE            VARCHAR(50),
   EQUITY_INFO          VARCHAR(200),
   BAL_FLAG             VARCHAR(50),
   SHADOW_PRICE_VALUE   NUMERIC(30, 2),
   MARKET_VAL_DATE      DATE,
   INPUT_TYPE           VARCHAR(50)            not null,
   INPUT_USER           VARCHAR(20)            not null,
   ADAPTER_ID           VARCHAR(20)            not null,
   STATUS               VARCHAR(10),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_INSTALL_LOG_ primary key (SEQ_NO)
);

comment on table REP_VAL_TABLE_DATA is
'估值表数据';

comment on column REP_VAL_TABLE_DATA.SEQ_NO is
'流水号';

comment on column REP_VAL_TABLE_DATA.PROD_ID is
'产品代码';

comment on column REP_VAL_TABLE_DATA.PROD_NAME is
'产品名称
';

comment on column REP_VAL_TABLE_DATA.PROFIT_TYPE is
'收益类型
';

comment on column REP_VAL_TABLE_DATA.VAL_DATE is
'估值日期
';

comment on column REP_VAL_TABLE_DATA.DETAIL_DIST is
'明细区分
';

comment on column REP_VAL_TABLE_DATA.LAYERING_ID is
'分层代码
';

comment on column REP_VAL_TABLE_DATA.SUBJECT_NO is
'科目号';

comment on column REP_VAL_TABLE_DATA.SUBJECT_NAME is
'科目名称';

comment on column REP_VAL_TABLE_DATA.FSUBJECT_ID is
'上级科目号';

comment on column REP_VAL_TABLE_DATA.NUM_AMT is
'数量';

comment on column REP_VAL_TABLE_DATA.UNIT_COST is
'单位成本';

comment on column REP_VAL_TABLE_DATA.O_CCY is
'原币币种';

comment on column REP_VAL_TABLE_DATA.B_CCY is
'本币币种';

comment on column REP_VAL_TABLE_DATA.EX_RATE is
'汇率';

comment on column REP_VAL_TABLE_DATA.O_COST is
'原币成本';

comment on column REP_VAL_TABLE_DATA.B_COST is
'本币成本';

comment on column REP_VAL_TABLE_DATA.B_COST_PERCENT is
'本币成本占比';

comment on column REP_VAL_TABLE_DATA.CLOSE_PRICE is
'行情';

comment on column REP_VAL_TABLE_DATA.O_MARKET_VALUE is
'原币市值';

comment on column REP_VAL_TABLE_DATA.B_MARKET_VALUE is
'本币市值';

comment on column REP_VAL_TABLE_DATA.B_VALUE_PERCENT is
'本币市值占比';

comment on column REP_VAL_TABLE_DATA.O_VALUE_INCREMENT is
'原币估值增值';

comment on column REP_VAL_TABLE_DATA.B_VALUE_INCREMENT is
'本币估值增值';

comment on column REP_VAL_TABLE_DATA.SEC_STATE is
'证券状态';

comment on column REP_VAL_TABLE_DATA.EQUITY_INFO is
'权益信息';

comment on column REP_VAL_TABLE_DATA.BAL_FLAG is
'余额方向';

comment on column REP_VAL_TABLE_DATA.SHADOW_PRICE_VALUE is
'影子价格估值';

comment on column REP_VAL_TABLE_DATA.MARKET_VAL_DATE is
'行情日期';

comment on column REP_VAL_TABLE_DATA.INPUT_TYPE is
'导入方法';

comment on column REP_VAL_TABLE_DATA.INPUT_USER is
'导入用户';

comment on column REP_VAL_TABLE_DATA.ADAPTER_ID is
'适配器ID';

comment on column REP_VAL_TABLE_DATA.STATUS is
'状态';

comment on column REP_VAL_TABLE_DATA.CREATE_USER is
'创建人';

comment on column REP_VAL_TABLE_DATA.CREATE_DEPT is
'创建部门';

comment on column REP_VAL_TABLE_DATA.CREATE_TIME is
'创建时间';

comment on column REP_VAL_TABLE_DATA.UPDATE_USER is
'更新人';

comment on column REP_VAL_TABLE_DATA.UPDATE_TIME is
'更新时间';




--==============================================================
-- Table: REP_BAL_TABLE_DATA
--==============================================================
create table REP_BAL_TABLE_DATA
(
   SEQ_NO               VARCHAR(32)            not null,
   PROD_ID              VARCHAR(32),
   PROD_NAME            VARCHAR(200),
   BAL_DATE             DATE,
   SUBJECT_NO           VARCHAR(300),
   SUBJECT_NAME         VARCHAR(500),
   O_CCY                VARCHAR(50),
   O_OPEN_BAL_FLAG      VARCHAR(50),
   O_OPEN_BALANCE       NUMERIC(30, 2),
   O_OPEN_BALANCE_D     NUMERIC(30, 2),
   O_OPEN_BALANCE_C     NUMERIC(30, 2),
   O_HAPPEN_AMT_D       NUMERIC(30, 2),
   O_HAPPEN_AMT_C       NUMERIC(30, 2),
   O_END_BAL_FLAG       VARCHAR(50),
   O_END_BALANCE        NUMERIC(30, 2),
   O_END_BALANCE_D      NUMERIC(30, 2),
   O_END_BALANCE_C      NUMERIC(30, 2),
   B_CCY                VARCHAR(50),
   B_OPEN_BAL_FLAG      VARCHAR(50),
   B_OPEN_BALANCE       NUMERIC(30, 2),
   B_OPEN_BALANCE_D     NUMERIC(30, 2),
   B_OPEN_BALANCE_C     NUMERIC(30, 2),
   B_HAPPEN_AMT_D       NUMERIC(30, 2),
   B_HAPPEN_AMT_C       NUMERIC(30, 2),
   B_END_BAL_FLAG       VARCHAR(50),
   B_END_BALANCE        NUMERIC(30, 2),
   B_END_BALANCE_D      NUMERIC(30, 2),
   B_END_BALANCE_C      NUMERIC(30, 2),
   INPUT_TYPE           VARCHAR(50)            not null,
   INPUT_USER           VARCHAR(20)            not null,
   ADAPTER_ID           VARCHAR(20)            not null,
   STATUS               VARCHAR(10),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_INSTALL_LOG_ primary key (SEQ_NO)
);

comment on table REP_BAL_TABLE_DATA is
'余额表';

comment on column REP_BAL_TABLE_DATA.SEQ_NO is
'流水号';

comment on column REP_BAL_TABLE_DATA.PROD_ID is
'产品代码';

comment on column REP_BAL_TABLE_DATA.PROD_NAME is
'产品名称
';

comment on column REP_BAL_TABLE_DATA.BAL_DATE is
'余额日期';

comment on column REP_BAL_TABLE_DATA.SUBJECT_NO is
'科目号';

comment on column REP_BAL_TABLE_DATA.SUBJECT_NAME is
'科目名称';

comment on column REP_BAL_TABLE_DATA.O_CCY is
'原币币种';

comment on column REP_BAL_TABLE_DATA.O_OPEN_BAL_FLAG is
'原币期初余额方向';

comment on column REP_BAL_TABLE_DATA.O_OPEN_BALANCE is
'原币期初余额';

comment on column REP_BAL_TABLE_DATA.O_OPEN_BALANCE_D is
'原币期初借方余额';

comment on column REP_BAL_TABLE_DATA.O_OPEN_BALANCE_C is
'原币期初贷方余额';

comment on column REP_BAL_TABLE_DATA.O_HAPPEN_AMT_D is
'原币本期借方发生额';

comment on column REP_BAL_TABLE_DATA.O_HAPPEN_AMT_C is
'原币本期贷方发生额';

comment on column REP_BAL_TABLE_DATA.O_END_BAL_FLAG is
'原币期末余额方向';

comment on column REP_BAL_TABLE_DATA.O_END_BALANCE is
'原币期末余额';

comment on column REP_BAL_TABLE_DATA.O_END_BALANCE_D is
'原币期末借方余额';

comment on column REP_BAL_TABLE_DATA.O_END_BALANCE_C is
'原币期末贷方余额';

comment on column REP_BAL_TABLE_DATA.B_CCY is
'本币币种';

comment on column REP_BAL_TABLE_DATA.B_OPEN_BAL_FLAG is
'本币期初余额方向';

comment on column REP_BAL_TABLE_DATA.B_OPEN_BALANCE is
'本币期初余额';

comment on column REP_BAL_TABLE_DATA.B_OPEN_BALANCE_D is
'本币期初借方余额';

comment on column REP_BAL_TABLE_DATA.B_OPEN_BALANCE_C is
'本币期初贷方余额';

comment on column REP_BAL_TABLE_DATA.B_HAPPEN_AMT_D is
'本币本期借方发生额';

comment on column REP_BAL_TABLE_DATA.B_HAPPEN_AMT_C is
'本币本期贷方发生额';

comment on column REP_BAL_TABLE_DATA.B_END_BAL_FLAG is
'本币期末余额方向';

comment on column REP_BAL_TABLE_DATA.B_END_BALANCE is
'本币期末余额';

comment on column REP_BAL_TABLE_DATA.B_END_BALANCE_D is
'本币期末借方余额';

comment on column REP_BAL_TABLE_DATA.B_END_BALANCE_C is
'本币期末贷方余额';

comment on column REP_BAL_TABLE_DATA.INPUT_TYPE is
'导入方法';

comment on column REP_BAL_TABLE_DATA.INPUT_USER is
'导入用户';

comment on column REP_BAL_TABLE_DATA.ADAPTER_ID is
'适配器ID';

comment on column REP_BAL_TABLE_DATA.STATUS is
'状态';

comment on column REP_BAL_TABLE_DATA.CREATE_USER is
'创建人';

comment on column REP_BAL_TABLE_DATA.CREATE_DEPT is
'创建部门';

comment on column REP_BAL_TABLE_DATA.CREATE_TIME is
'创建时间';

comment on column REP_BAL_TABLE_DATA.UPDATE_USER is
'更新人';

comment on column REP_BAL_TABLE_DATA.UPDATE_TIME is
'更新时间';




--==============================================================
-- Table: REP_PROD_INDEX
--==============================================================
create table REP_PROD_INDEX
(
   SEQ_NO               VARCHAR(32)            not null,
   PROD_ID              VARCHAR(32),
   VALUE_DATE           DATE,
   CAPITAL              NUMERIC(30, 2),
   PROFIT_NO            NUMERIC(30, 2),
   PROFIT_YES           NUMERIC(30, 2),
   ASSET_TOTAL_COST     NUMERIC(30, 2),
   ASSET_TOTAL_MARKET   NUMERIC(30, 2),
   DEBT_TOTAL           NUMERIC(30, 2),
   ASSET_VALUE_COST     NUMERIC(30, 2),
   ASSET_VALUE_MARKET   NUMERIC(30, 2),
   UNIT_VALUE           NUMERIC(30,14),
   UNIT_VALUE_T         NUMERIC(30,14),
   UNIT_VALUE_Y         NUMERIC(30,14),
   DEVIATION_AMT        NUMERIC(30, 2),
   DEVIATION            NUMERIC(30,14),
   PROFIT_TDY           NUMERIC(30, 2),
   PROFIT_TOTAL         NUMERIC(30,14),
   W_YIELD              NUMERIC(30,14),
   W_YIELD_YEAR         NUMERIC(30,14),
   W_YIELD_TOTAL        NUMERIC(30,14),
   YIELD_TDY            NUMERIC(30,14),
   YIELD_7              NUMERIC(30,14),
   YIELD_TOTAL          NUMERIC(30,14),
   YIELD_E              NUMERIC(30,14),
   AVERAGE_MDATE        NUMERIC(5,0),
   SURPLUS_TERM         NUMERIC(5,0),
   S_RATE               NUMERIC(30,14),
   T_RATE               NUMERIC(30,14),
   M_RATE               NUMERIC(30,14),
   INPUT_TYPE           VARCHAR(50)            not null,
   INPUT_USER           VARCHAR(20)            not null,
   ADAPTER_ID           VARCHAR(20)            not null,
   STATUS               VARCHAR(10),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   M_FEE                NUMERIC(30, 2),
   F_FEE                NUMERIC(30, 2),
   F_RATE               NUMERIC(30,14),
   S_FEE                NUMERIC(30, 2),
   T_FEE                NUMERIC(30, 2),
   constraint P_REP_INSTALL_LOG_ primary key (SEQ_NO)
);

comment on table REP_PROD_INDEX is
'产品指标表';

comment on column REP_PROD_INDEX.SEQ_NO is
'流水号';

comment on column REP_PROD_INDEX.PROD_ID is
'产品代码';

comment on column REP_PROD_INDEX.VALUE_DATE is
'估值日';

comment on column REP_PROD_INDEX.CAPITAL is
'实收资本';

comment on column REP_PROD_INDEX.PROFIT_NO is
'损益平准金未实现';

comment on column REP_PROD_INDEX.PROFIT_YES is
'损益平准金已实现';

comment on column REP_PROD_INDEX.ASSET_TOTAL_COST is
'资产类合计(按成本）';

comment on column REP_PROD_INDEX.ASSET_TOTAL_MARKET is
'资产类合计(按市值）';

comment on column REP_PROD_INDEX.DEBT_TOTAL is
'负债类合计';

comment on column REP_PROD_INDEX.ASSET_VALUE_COST is
'产品资产净值(按成本）';

comment on column REP_PROD_INDEX.ASSET_VALUE_MARKET is
'产品资产净值(按市值）';

comment on column REP_PROD_INDEX.UNIT_VALUE is
'产品单位净值';

comment on column REP_PROD_INDEX.UNIT_VALUE_T is
'产品累计单位净值';

comment on column REP_PROD_INDEX.UNIT_VALUE_Y is
'产品昨日单位净值';

comment on column REP_PROD_INDEX.DEVIATION_AMT is
'偏离金额';

comment on column REP_PROD_INDEX.DEVIATION is
'偏离度%';

comment on column REP_PROD_INDEX.PROFIT_TDY is
'当日客户收益';

comment on column REP_PROD_INDEX.PROFIT_TOTAL is
'累计客户收益';

comment on column REP_PROD_INDEX.W_YIELD is
'万份收益';

comment on column REP_PROD_INDEX.W_YIELD_YEAR is
'本年累积万份收益';

comment on column REP_PROD_INDEX.W_YIELD_TOTAL is
'从成立以来累计万份收益';

comment on column REP_PROD_INDEX.YIELD_TDY is
'本日年化收益率%';

comment on column REP_PROD_INDEX.YIELD_7 is
'7日年化收益率%';

comment on column REP_PROD_INDEX.YIELD_TOTAL is
'累计年化收益率%';

comment on column REP_PROD_INDEX.YIELD_E is
'预期收益率%';

comment on column REP_PROD_INDEX.AVERAGE_MDATE is
'投资组合平均到期日';

comment on column REP_PROD_INDEX.SURPLUS_TERM is
'剩余存续期';

comment on column REP_PROD_INDEX.S_RATE is
'销售手续费率%';

comment on column REP_PROD_INDEX.T_RATE is
'托管费率%';

comment on column REP_PROD_INDEX.M_RATE is
'管理费率%';

comment on column REP_PROD_INDEX.INPUT_TYPE is
'导入方法';

comment on column REP_PROD_INDEX.INPUT_USER is
'导入用户';

comment on column REP_PROD_INDEX.ADAPTER_ID is
'适配器ID';

comment on column REP_PROD_INDEX.STATUS is
'状态';

comment on column REP_PROD_INDEX.CREATE_USER is
'创建人';

comment on column REP_PROD_INDEX.CREATE_DEPT is
'创建部门';

comment on column REP_PROD_INDEX.CREATE_TIME is
'创建时间';

comment on column REP_PROD_INDEX.UPDATE_USER is
'更新人';

comment on column REP_PROD_INDEX.UPDATE_TIME is
'更新时间';

comment on column REP_PROD_INDEX.M_FEE is
'管理费';

comment on column REP_PROD_INDEX.F_FEE is
'浮动管理费';

comment on column REP_PROD_INDEX.F_RATE is
'浮动管理费率%';

comment on column REP_PROD_INDEX.S_FEE is
'销售手续费';

comment on column REP_PROD_INDEX.T_FEE is
'托管费';




--==============================================================
-- Table: REP_ASSET_INFO
--==============================================================
create table REP_ASSET_INFO
(
   ASSET_ID             VARCHAR(20)
             not null,
   MARKET_ID            VARCHAR(20),
   ASSET_NAME           VARCHAR(600),
   ISSUER               VARCHAR(20),
   IDATE                DATE,
   ISSUE_PRICE          NUMERIC(30, 14),
   ISSUE_AMT            NUMERIC(30, 2),
   CCY                  VARCHAR(50),
   VDATE                DATE,
   MDATE                DATE,
   ACTMDATE             DATE,
   TRADE_MARKET         VARCHAR(50),
   REGIST_ORG           VARCHAR(50),
   INPUT_TYPE           VARCHAR(50)            not null,
   INPUT_USER           VARCHAR(20)            not null,
   ADAPTER_ID           VARCHAR(20)            not null,
   STATUS               VARCHAR(10),
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_INSTALL_LOG_ primary key (ASSET_ID)
);

comment on table REP_ASSET_INFO is
'资产信息';

comment on column REP_ASSET_INFO.ASSET_ID is
'资产代码';

comment on column REP_ASSET_INFO.MARKET_ID is
'市场代码';

comment on column REP_ASSET_INFO.ASSET_NAME is
'资产名称';

comment on column REP_ASSET_INFO.ISSUER is
'发行人';

comment on column REP_ASSET_INFO.IDATE is
'发行日';

comment on column REP_ASSET_INFO.ISSUE_PRICE is
'发行价';

comment on column REP_ASSET_INFO.ISSUE_AMT is
'发行规模';

comment on column REP_ASSET_INFO.CCY is
'币种';

comment on column REP_ASSET_INFO.VDATE is
'起息日';

comment on column REP_ASSET_INFO.MDATE is
'到期日';

comment on column REP_ASSET_INFO.ACTMDATE is
'实际到期日';

comment on column REP_ASSET_INFO.TRADE_MARKET is
'交易场所';

comment on column REP_ASSET_INFO.REGIST_ORG is
'登记托管机构';

comment on column REP_ASSET_INFO.INPUT_TYPE is
'导入方法';

comment on column REP_ASSET_INFO.INPUT_USER is
'导入用户';

comment on column REP_ASSET_INFO.ADAPTER_ID is
'适配器ID';

comment on column REP_ASSET_INFO.STATUS is
'状态';

comment on column REP_ASSET_INFO.CREATE_USER is
'创建人';

comment on column REP_ASSET_INFO.CREATE_DEPT is
'创建部门';

comment on column REP_ASSET_INFO.CREATE_TIME is
'创建时间';

comment on column REP_ASSET_INFO.UPDATE_USER is
'更新人';

comment on column REP_ASSET_INFO.UPDATE_TIME is
'更新时间';





--==============================================================
-- Table: REP_BOK_SUBJECT
--==============================================================
create table REP_BOK_SUBJECT
(
   BOOKSET_ID           VARCHAR(32)            not null,
   SUBJECT_NO           VARCHAR(32)            not null,
   SUBJECT_NAME         VARCHAR(100)           not null,
   FSUBJECT_ID          VARCHAR(32),
   BAL_FLAG             VARCHAR(50),
   SUBJECT_TYPE         VARCHAR(50)            not null,
   SUBJECT_LEVEL        NUMERIC(10)            not null,
   ACC_QUA_FLAG         VARCHAR(50),
   ACC_INT_FLAG         VARCHAR(50),
   OVERDRAWN_FLAG       VARCHAR(50),
   GEN_DETSUB_FLAG      VARCHAR(50),
   BASE_SUBJECT_NATURE  VARCHAR(100),
   INV_AIM              VARCHAR(50),
   SEC_MANAGE_ACCT_ID   VARCHAR(32),
   INPUT_TYPE           VARCHAR(50)            not null,
   INPUT_USER           VARCHAR(20)            not null,
   ADAPTER_ID           VARCHAR(20)            not null,
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_RULE_ID primary key (BOOKSET_ID)
);

comment on table REP_BOK_SUBJECT is
'账套科目
';

comment on column REP_BOK_SUBJECT.BOOKSET_ID is
'科目体系ID';

comment on column REP_BOK_SUBJECT.SUBJECT_NO is
'科目号';

comment on column REP_BOK_SUBJECT.SUBJECT_NAME is
'科目名称';

comment on column REP_BOK_SUBJECT.FSUBJECT_ID is
'上级科目号';

comment on column REP_BOK_SUBJECT.BAL_FLAG is
'余额方向';

comment on column REP_BOK_SUBJECT.SUBJECT_TYPE is
'科目类型';

comment on column REP_BOK_SUBJECT.SUBJECT_LEVEL is
'科目级别';

comment on column REP_BOK_SUBJECT.ACC_QUA_FLAG is
'是否核算数量';

comment on column REP_BOK_SUBJECT.ACC_INT_FLAG is
'是否计息';

comment on column REP_BOK_SUBJECT.OVERDRAWN_FLAG is
'是否允许透支';

comment on column REP_BOK_SUBJECT.GEN_DETSUB_FLAG is
'是否生成四级科目';

comment on column REP_BOK_SUBJECT.BASE_SUBJECT_NATURE is
'基础科目性质';

comment on column REP_BOK_SUBJECT.INV_AIM is
'投资目的';

comment on column REP_BOK_SUBJECT.SEC_MANAGE_ACCT_ID is
'证券管理户代码
';

comment on column REP_BOK_SUBJECT.INPUT_TYPE is
'导入方法';

comment on column REP_BOK_SUBJECT.INPUT_USER is
'导入用户';

comment on column REP_BOK_SUBJECT.ADAPTER_ID is
'适配器ID
';

comment on column REP_BOK_SUBJECT.CREATE_USER is
'创建人';

comment on column REP_BOK_SUBJECT.CREATE_DEPT is
'创建部门';

comment on column REP_BOK_SUBJECT.CREATE_TIME is
'创建时间';

comment on column REP_BOK_SUBJECT.UPDATE_USER is
'更新人';

comment on column REP_BOK_SUBJECT.UPDATE_TIME is
'更新时间';



--==============================================================
-- Table: REP_PROD_FEE_DEAL
--==============================================================
create table REP_PROD_FEE_DEAL
(
   TRADE_ID             VARCHAR(32)            not null,
   PS                   VARCHAR(10),
   PROD_ID              VARCHAR(20),
   DEAL_TYPE            VARCHAR(10),
   DEAL_DATE            DATE,
   PROD_TYPE            VARCHAR(10),
   FEE_ID               VARCHAR(20),
   FEE_NAME             VARCHAR(200),
   FEE_TYPE             VARCHAR(10),
   SECFEE_TYPE          VARCHAR(10),
   PAY_DATE             DATE,
   PAID_FEE             DECIMAL(30,4),
   STATUS               VARCHAR(10),
   INPUT_TYPE           VARCHAR(50)            not null,
   INPUT_USER           VARCHAR(20)            not null,
   ADAPTER_ID           VARCHAR(20)            not null,
   CREATE_USER          VARCHAR(50)            not null,
   CREATE_DEPT          VARCHAR(50),
   CREATE_TIME          TIMESTAMP              not null,
   UPDATE_USER          VARCHAR(50)            not null,
   UPDATE_TIME          TIMESTAMP              not null,
   constraint P_REP_INSTALL_LOG_ primary key (TRADE_ID)
);

comment on table REP_PROD_FEE_DEAL is
'产品端费用交易表';

comment on column REP_PROD_FEE_DEAL.TRADE_ID is
'交易编号';

comment on column REP_PROD_FEE_DEAL.PS is
'收支方向';

comment on column REP_PROD_FEE_DEAL.PROD_ID is
'产品代码';

comment on column REP_PROD_FEE_DEAL.DEAL_TYPE is
'交易类型';

comment on column REP_PROD_FEE_DEAL.DEAL_DATE is
'交易日';

comment on column REP_PROD_FEE_DEAL.PROD_TYPE is
'产品类型';

comment on column REP_PROD_FEE_DEAL.FEE_ID is
'费用代码';

comment on column REP_PROD_FEE_DEAL.FEE_NAME is
'费用名称';

comment on column REP_PROD_FEE_DEAL.FEE_TYPE is
'费用类型';

comment on column REP_PROD_FEE_DEAL.SECFEE_TYPE is
'费用二级类型';

comment on column REP_PROD_FEE_DEAL.PAY_DATE is
'支付日';

comment on column REP_PROD_FEE_DEAL.PAID_FEE is
'实付费用';

comment on column REP_PROD_FEE_DEAL.STATUS is
'状态';

comment on column REP_PROD_FEE_DEAL.INPUT_TYPE is
'导入方法';

comment on column REP_PROD_FEE_DEAL.INPUT_USER is
'导入用户';

comment on column REP_PROD_FEE_DEAL.ADAPTER_ID is
'适配器ID';

comment on column REP_PROD_FEE_DEAL.CREATE_USER is
'创建人';

comment on column REP_PROD_FEE_DEAL.CREATE_DEPT is
'创建部门';

comment on column REP_PROD_FEE_DEAL.CREATE_TIME is
'创建时间';

comment on column REP_PROD_FEE_DEAL.UPDATE_USER is
'更新人';

comment on column REP_PROD_FEE_DEAL.UPDATE_TIME is
'更新时间';
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
--==============================================================
-- Table: REP_CHECK_ERRMSG
--==============================================================    
DROP TABLE REP_CHECK_ERRMSG;
create table REP_CHECK_ERRMSG
(
  MSGID VARCHAR(32) not null,
  ERRORMSG VARCHAR(1000),
  CREATE_USER VARCHAR(50),
  CREATE_DEPT VARCHAR(50),
  CREATE_TIME TIMESTAMP,
  UPDATE_USER VARCHAR(50),
  UPDATE_TIME TIMESTAMP,
  constraint PK_REP_CHECK_ERRMSG primary key (MSGID)
);
-- Add comments to the table 
comment on table REP_CHECK_ERRMSG
  is '报表生成校验消息';
-- Add comments to the columns 
comment on column REP_CHECK_ERRMSG.MSGID
  is '校验消息编号';
comment on column REP_CHECK_ERRMSG.ERRORMSG
  is '校验消息';
comment on column REP_CHECK_ERRMSG.CREATE_USER
  is '创建人';
comment on column REP_CHECK_ERRMSG.CREATE_DEPT
  is '创建部门';
comment on column REP_CHECK_ERRMSG.CREATE_TIME
  is '创建时间';
comment on column REP_CHECK_ERRMSG.UPDATE_USER
  is '更新人';
comment on column REP_CHECK_ERRMSG.UPDATE_TIME
  is '更新时间';
 
/*指标管理增加字段：是否被引用 zhangyf 20190108 start*/
ALTER TABLE REP_INDEX_PRIMARY ADD IS_QUOTE VARCHAR(2) with default '02';
comment on column REP_INDEX_PRIMARY.IS_QUOTE is '是否被引用';
/*指标管理增加字段：是否被引用 zhangyf 20190108 end*/

/*jasper改造:报表模板信息表 zhangyf 20190227 start*/
drop table "REP_REPORTINFO";
CREATE TABLE "REP_REPORTINFO" 
(	
	"ID" DECIMAL(10,0) NOT NULL, 
	"REPORTID" VARCHAR(255 ), 
	"URI" VARCHAR(255 ), 
	"FOLDER" VARCHAR(255 ), 
	"REPOSITORY" VARCHAR(255 ), 
	"REPORTDESC" VARCHAR(255 ), 
	"DATASOURCE" VARCHAR(255 ), 
	"GENERATOR" VARCHAR(255 ), 
	"USERNAME" VARCHAR(255 ), 
	"USERPWD" VARCHAR(255 ), 
	"REPORTPARAMS" VARCHAR(255 ),
	"CREATE_DEPT" VARCHAR(32),
	"CREATE_USER" VARCHAR(20),
	"CREATE_TIME" TIMESTAMP(6),
	"UPDATE_USER" VARCHAR(20),
	"UPDATE_TIME" TIMESTAMP(6),
	constraint PK_REP_CHECK_ERRMSG primary key (ID)
);
/*jasper改造:报表模板信息表 zhangyf 20190227 end*