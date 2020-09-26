/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/8/14 星期二 10:29:27                       */
/*==============================================================*/

/*==============================================================*/
/* Table: VAL_INTERFLOG                                         */
/*==============================================================*/
create table VAL_INTERFLOG 
(
   TMP_NAME             VARCHAR(50)          not null,
   FILE_NAME            VARCHAR(1000)        not null,
   EX_DATE              TIMESTAMP            not null,
   NUM                  NUMERIC(10)          not null,
   IS_CHECK             VARCHAR(50)          not null,
   DETAIL               VARCHAR(1000),
   REMARK               VARCHAR(1000),
   constraint PK_VAL_INTERFLOG primary key (TMP_NAME, FILE_NAME, EX_DATE, NUM)
);

comment on table VAL_INTERFLOG is
'接口-日志表';

comment on column VAL_INTERFLOG.TMP_NAME is
'模板名称';

comment on column VAL_INTERFLOG.FILE_NAME is
'文件名称';

comment on column VAL_INTERFLOG.EX_DATE is
'处理日期';

comment on column VAL_INTERFLOG.NUM is
'错误所在文件行号';

comment on column VAL_INTERFLOG.IS_CHECK is
'Y:是;N:否';

comment on column VAL_INTERFLOG.DETAIL is
'校验内容';

comment on column VAL_INTERFLOG.REMARK is
'备注';

/*==============================================================*/
/* Table: VAL_INTERF_ACTIONLOG                                  */
/*==============================================================*/
create table VAL_INTERF_ACTIONLOG 
(
   SEQNO                VARCHAR(32)          not null,
   BUSI_DATE            DATE,
   ORDERNO              NUMERIC(10),
   TASK_ENAME           VARCHAR(50),
   START_TIME           TIMESTAMP,
   END_TIME             TIMESTAMP,
   STATUS               VARCHAR(50),
   EXEC_RESULT          VARCHAR(32),
   LAST_TIME            NUMERIC(10),
   DETAIL_ACTION        CLOB,
   CREATE_TIME          TIMESTAMP,
   CREATE_USER          VARCHAR(20),
   CREATE_DEPT          VARCHAR(20),
   UPDATE_TIME          TIMESTAMP,
   UPDATE_USER          VARCHAR(20),
   constraint PK_VAL_INTERF_ACTIONLOG primary key (SEQNO)
);

comment on table VAL_INTERF_ACTIONLOG is
'接口-行为日志';

comment on column VAL_INTERF_ACTIONLOG.SEQNO is
'非业务主键：UUID';

comment on column VAL_INTERF_ACTIONLOG.BUSI_DATE is
'业务日期';

comment on column VAL_INTERF_ACTIONLOG.ORDERNO is
'批次号';

comment on column VAL_INTERF_ACTIONLOG.TASK_ENAME is
'任务组英文名称';

comment on column VAL_INTERF_ACTIONLOG.START_TIME is
'开始时间';

comment on column VAL_INTERF_ACTIONLOG.END_TIME is
'结束时间';

comment on column VAL_INTERF_ACTIONLOG.STATUS is
'执行状态 00-未执行 01-开始执行（执行） 02-执行成功 03-执行失败 04-手动停止';

comment on column VAL_INTERF_ACTIONLOG.EXEC_RESULT is
'执行结果';

comment on column VAL_INTERF_ACTIONLOG.LAST_TIME is
'执行时长，单位为:S';

comment on column VAL_INTERF_ACTIONLOG.DETAIL_ACTION is
'执行明细';

comment on column VAL_INTERF_ACTIONLOG.CREATE_TIME is
'创建时间';

comment on column VAL_INTERF_ACTIONLOG.CREATE_USER is
'创建人';

comment on column VAL_INTERF_ACTIONLOG.CREATE_DEPT is
'创建部门';

comment on column VAL_INTERF_ACTIONLOG.UPDATE_TIME is
'更新时间';

comment on column VAL_INTERF_ACTIONLOG.UPDATE_USER is
'更新人';

/*==============================================================*/
/* Table: VAL_INTERF_SERVER_INFO                                */
/*==============================================================*/
create table VAL_INTERF_SERVER_INFO 
(
   SERVERIP             VARCHAR(32)          not null,
   SERVERPORT           VARCHAR(32)          not null,
   USERNAME             VARCHAR(32)          not null,
   PASSWORD             VARCHAR(32)          not null,
   SERVERTYPE           VARCHAR(32)          not null,
   STATUS               VARCHAR(50),
   SYSTEMID             VARCHAR(50)          not null,
   REMOTE_PATH          VARCHAR(32),
   LOCAL_PATH           VARCHAR(32),
   RS                   VARCHAR(50),
   constraint PK_VAL_INTERF_SERVER_INFO primary key (SERVERIP, SYSTEMID)
);

comment on table VAL_INTERF_SERVER_INFO is
'接口-接口服务器配置表';

comment on column VAL_INTERF_SERVER_INFO.SERVERIP is
'服务器IP';

comment on column VAL_INTERF_SERVER_INFO.SERVERPORT is
'服务器端口号';

comment on column VAL_INTERF_SERVER_INFO.USERNAME is
'用户名';

comment on column VAL_INTERF_SERVER_INFO.PASSWORD is
'服务器登录密码';

comment on column VAL_INTERF_SERVER_INFO.SERVERTYPE is
'服务器登录类型';

comment on column VAL_INTERF_SERVER_INFO.STATUS is
'可用：Y ；不可用：N';

comment on column VAL_INTERF_SERVER_INFO.SYSTEMID is
'资管系统：ZG';

comment on column VAL_INTERF_SERVER_INFO.REMOTE_PATH is
'远程路径';

comment on column VAL_INTERF_SERVER_INFO.LOCAL_PATH is
'本地路径';

comment on column VAL_INTERF_SERVER_INFO.RS is
'发送:S 和 接收:R';

/*==============================================================*/
/* Table: VAL_INTERF_TASK                                       */
/*==============================================================*/
create table VAL_INTERF_TASK 
(
   TASK_ID              VARCHAR(32)          not null,
   BEAN_NAME            VARCHAR(200),
   TASK_NAME            VARCHAR(200),
   ORDERNO              NUMBER(4,0),
   STATUS               CHAR(1 BYTE)         not null,
   RS                   CHAR(1 BYTE)         not null,
   SYSTEMID             VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_VAL_INTERF_TASK primary key (TASK_ID)
);

comment on table VAL_INTERF_TASK is
'接口批次信息';

comment on column VAL_INTERF_TASK.TASK_ID is
'任务ID';

comment on column VAL_INTERF_TASK.BEAN_NAME is
'bean名称';

comment on column VAL_INTERF_TASK.TASK_NAME is
'批次名称';

comment on column VAL_INTERF_TASK.ORDERNO is
'批次序号';

comment on column VAL_INTERF_TASK.STATUS is
'数据有效状态';

comment on column VAL_INTERF_TASK.RS is
'S：发送；R：接收';

comment on column VAL_INTERF_TASK.SYSTEMID is
'资管系统：ZG';

comment on column VAL_INTERF_TASK.CREATE_USER is
'创建人';

comment on column VAL_INTERF_TASK.CREATE_TIME is
'创建时间';

comment on column VAL_INTERF_TASK.UPDATE_USER is
'更新人';

comment on column VAL_INTERF_TASK.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: VAL_INTERF_TEMPLATE                                   */
/*==============================================================*/
create table VAL_INTERF_TEMPLATE 
(
   TMP_C_NAME           VARCHAR(100)         not null,
   TMP_E_NAME           VARCHAR(50)          not null,
   FILE_PATH            VARCHAR(100),
   ORDERNO              NUMERIC(10)          not null,
   TMP_TYPE             VARCHAR(12)          not null,
   SYSTEMID             VARCHAR(50)          not null,
   constraint PK_VAL_INTERF_TEMPLATE primary key (TMP_E_NAME)
);

comment on table VAL_INTERF_TEMPLATE is
'接口-接口模版管理表';

comment on column VAL_INTERF_TEMPLATE.TMP_C_NAME is
'模版中文名称';

comment on column VAL_INTERF_TEMPLATE.TMP_E_NAME is
'模版英文名称';

comment on column VAL_INTERF_TEMPLATE.FILE_PATH is
'文件存储目录';

comment on column VAL_INTERF_TEMPLATE.ORDERNO is
'文件接入顺序，批处理按顺序依次接入';

comment on column VAL_INTERF_TEMPLATE.TMP_TYPE is
'模板类型';

comment on column VAL_INTERF_TEMPLATE.SYSTEMID is
'系统类型';

/*==============================================================*/
/* Table: VAL_TEMP_COL_CHECK_INFO                               */
/*==============================================================*/
create table VAL_TEMP_COL_CHECK_INFO 
(
   TMP_C_NAME           VARCHAR(100)         not null,
   TMP_E_NAME           VARCHAR(50)          not null,
   COL_C_NAME           VARCHAR(50)          not null,
   COL_E_NAME           VARCHAR(32)          not null,
   COL_TYPE             VARCHAR(2),
   IS_NULL              VARCHAR(2),
   MAX_LENGTH           NUMERIC(10),
   IS_EXIST             VARCHAR(2),
   constraint PK_VAL_TEMP_COL_CHECK_INFO primary key (TMP_E_NAME, COL_E_NAME)
);

