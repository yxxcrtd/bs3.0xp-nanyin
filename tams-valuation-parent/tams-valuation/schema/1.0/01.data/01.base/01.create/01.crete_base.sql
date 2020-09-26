/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/7/15 星期日 下午 6:31:18                     */
/*==============================================================*/


drop table SYS_ACTIONLOG cascade constraints;

drop table SYS_BATCHGRP_EXEC_INFO cascade constraints;

drop table SYS_BATCH_EXEC_INFO cascade constraints;

drop table SYS_BATCH_EXEC_LOG cascade constraints;

drop table SYS_BATCH_GRP cascade constraints;

drop table SYS_BATCH_GRP_PRDLINK cascade constraints;

drop table SYS_BATCH_GRP_PRE_CON cascade constraints;

drop table SYS_BATCH_JOB cascade constraints;

drop table SYS_BATCH_PRD_GRP cascade constraints;

drop table SYS_BATCH_QUARTZ_CONFIG cascade constraints;

drop table SYS_BATCH_TASK cascade constraints;

drop table SYS_DATADICT cascade constraints;

drop table SYS_DATADICT_LIST cascade constraints;

drop table SYS_DATADICT_LIST_LINK cascade constraints;

drop table SYS_DATA_PROCESS_MARK cascade constraints;

drop table SYS_DATA_PROCESS_MARK_HIS cascade constraints;

drop table SYS_DATA_ROLE_MODULE cascade constraints;

drop table SYS_DEPARTMENT cascade constraints;

drop table SYS_HOME_PAGE_CONFIG cascade constraints;

drop table SYS_INSTITUTION cascade constraints;

drop table SYS_MENU cascade constraints;

drop table SYS_MQ_BEAN_PROPREF cascade constraints;

drop table SYS_MQ_MESSAGE_LOG cascade constraints;

drop table SYS_PAGE cascade constraints;

drop table SYS_PAGE_DICTLIST cascade constraints;

drop table SYS_PAGE_ELEMENT cascade constraints;

drop table SYS_PARA cascade constraints;

drop table SYS_ROLE cascade constraints;

drop table SYS_ROLE_MODULE cascade constraints;

drop table SYS_UPLOAD_FILE_INFO cascade constraints;

drop table SYS_USER cascade constraints;

drop table SYS_USER_HOME_PAGE_CONFIG cascade constraints;

drop table SYS_USER_ROLE cascade constraints;

drop table SYS_USER_STYLE cascade constraints;

drop table SYS_WFL_AGENT cascade constraints;

drop table SYS_WFL_BUSINESS_LIST cascade constraints;

drop table SYS_WFL_BUSINESS_MODULE cascade constraints;

drop table SYS_WFL_BUSINESS_NODE cascade constraints;

drop table SYS_WFL_MODULE cascade constraints;

drop table SYS_KET_JOB cascade constraints;

drop table SYS_KET_TASK cascade constraints;

drop table SYS_KET_JOB_CONFIG cascade constraints;

drop table SYS_KET_JOB_EXECUTE cascade constraints;

drop table SYS_KET_TASK_EXECUTE cascade constraints;

drop table SYS_KET_EXECUTE_LOG cascade constraints;

drop table SYS_BATCH_GRP_PRI cascade constraints;

drop table SYS_BATCH_DIMENSION_CONFIG cascade constraints;

drop table SYS_BATCH_DIMENSION_VALUE cascade constraints;

drop table SYS_BATCH_JOB_INST cascade constraints;

/*==============================================================*/
/* Table: SYS_ACTIONLOG                                         */
/*==============================================================*/
create table SYS_ACTIONLOG 
(
   LOG_ID               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   USER_NAME            VARCHAR(20)          not null,
   PAGE_ID              VARCHAR(50),
   ELEMENT_ID           VARCHAR(50),
   LOG_INFO             VARCHAR(200),
   LOG_DETAIL_INFO      CLOB,
   LOG_DETAIL_INFO_SEARCH CLOB,
   START_TIME           TIMESTAMP,
   END_TIME             TIMESTAMP,
   CLIENT_IP            VARCHAR(32),
   BROWSER_INFO         VARCHAR(1000),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_ACTIONLOG primary key (LOG_ID)
);

comment on table SYS_ACTIONLOG is
'系统用户操作日志';

comment on column SYS_ACTIONLOG.LOG_ID is
'日志编号';

comment on column SYS_ACTIONLOG.SYSTEM_CODE is
'系统代码';

comment on column SYS_ACTIONLOG.USER_NAME is
'用户名';

comment on column SYS_ACTIONLOG.PAGE_ID is
'画面代码';

comment on column SYS_ACTIONLOG.ELEMENT_ID is
'要素代码';

comment on column SYS_ACTIONLOG.LOG_INFO is
'用户操作结果';

comment on column SYS_ACTIONLOG.LOG_DETAIL_INFO is
'用户操作结果明细';

comment on column SYS_ACTIONLOG.LOG_DETAIL_INFO_SEARCH is
'用户查询操作时的查询条件明细';

comment on column SYS_ACTIONLOG.START_TIME is
'操作开始时间';

comment on column SYS_ACTIONLOG.END_TIME is
'操作结束时间';

comment on column SYS_ACTIONLOG.CLIENT_IP is
'客户端IP地址';

comment on column SYS_ACTIONLOG.BROWSER_INFO is
'客户端浏览器信息';

comment on column SYS_ACTIONLOG.CREATE_USER is
'创建人';

comment on column SYS_ACTIONLOG.CREATE_DEPT is
'创建部门';

comment on column SYS_ACTIONLOG.CREATE_TIME is
'创建时间';

comment on column SYS_ACTIONLOG.UPDATE_USER is
'更新人';

comment on column SYS_ACTIONLOG.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_BATCHGRP_EXEC_INFO                                */
/*==============================================================*/
create table SYS_BATCHGRP_EXEC_INFO 
(
   SEQ_NO               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   BATCH_SEQ_NO         VARCHAR(3000)        not null,
   PRD_GROUP_SEQ_NO     VARCHAR(3000),
   START_DATE           DATE                 not null,
   END_DATE             DATE                 not null,
   DETAIL_NAME          VARCHAR(200),
   OTHER_PARAM1         VARCHAR(200),
   OTHER_PARAM2         VARCHAR(200),
   OTHER_PARAM3         VARCHAR(200),
   OTHER_PARAM4         VARCHAR(200),
   OTHER_PARAM5         VARCHAR(200),
   STATUS               VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCHGRP_EXEC_INFO primary key (SEQ_NO)
);

comment on table SYS_BATCHGRP_EXEC_INFO is
'批处理任务组执行信息';

comment on column SYS_BATCHGRP_EXEC_INFO.SEQ_NO is
'批处理任务组执行ID';

comment on column SYS_BATCHGRP_EXEC_INFO.SYSTEM_CODE is
'系统代码';

comment on column SYS_BATCHGRP_EXEC_INFO.BATCH_SEQ_NO is
'批处理任务所属任务组';

comment on column SYS_BATCHGRP_EXEC_INFO.PRD_GROUP_SEQ_NO is
'产品组';

comment on column SYS_BATCHGRP_EXEC_INFO.START_DATE is
'批处理任务启动开始日期';

comment on column SYS_BATCHGRP_EXEC_INFO.END_DATE is
'批处理任务启动截止日期';

comment on column SYS_BATCHGRP_EXEC_INFO.DETAIL_NAME is
'批处理任务组执行名称';

comment on column SYS_BATCHGRP_EXEC_INFO.OTHER_PARAM1 is
'其他参数1';

comment on column SYS_BATCHGRP_EXEC_INFO.OTHER_PARAM2 is
'其他参数2';

comment on column SYS_BATCHGRP_EXEC_INFO.OTHER_PARAM3 is
'其他参数3';

comment on column SYS_BATCHGRP_EXEC_INFO.OTHER_PARAM4 is
'其他参数4';

comment on column SYS_BATCHGRP_EXEC_INFO.OTHER_PARAM5 is
'其他参数5';

comment on column SYS_BATCHGRP_EXEC_INFO.STATUS is
'批处理执行状态（UNR：未执行,RUN：执行中,END：正常结束，EXP：异常结束）';

comment on column SYS_BATCHGRP_EXEC_INFO.CREATE_USER is
'创建人';

comment on column SYS_BATCHGRP_EXEC_INFO.CREATE_DEPT is
'创建部门';

comment on column SYS_BATCHGRP_EXEC_INFO.CREATE_TIME is
'创建时间';

comment on column SYS_BATCHGRP_EXEC_INFO.UPDATE_USER is
'更新人';

comment on column SYS_BATCHGRP_EXEC_INFO.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_BATCH_EXEC_INFO                                   */
/*==============================================================*/
create table SYS_BATCH_EXEC_INFO 
(
   SEQ_NO               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   BATCH_EXEDETAIL_SEQ_NO VARCHAR(32)          not null,
   TASK_ID              VARCHAR(32)          not null,
   BEAN_NAME            VARCHAR(3000)        not null,
   EXECUTE_DATE         DATE                 not null,
   TASK_STATUS          VARCHAR(50)          not null,
   START_MODE           VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCH_EXEC_INFO primary key (SEQ_NO)
);

comment on table SYS_BATCH_EXEC_INFO is
'批处理任务执行信息';

comment on column SYS_BATCH_EXEC_INFO.SEQ_NO is
'批处理任务执行信息ID';

comment on column SYS_BATCH_EXEC_INFO.SYSTEM_CODE is
'系统代码';

comment on column SYS_BATCH_EXEC_INFO.BATCH_EXEDETAIL_SEQ_NO is
'批处理任务组执行ID';

comment on column SYS_BATCH_EXEC_INFO.TASK_ID is
'任务代码';

comment on column SYS_BATCH_EXEC_INFO.BEAN_NAME is
'Service别名';

comment on column SYS_BATCH_EXEC_INFO.EXECUTE_DATE is
'批处理开始日';

comment on column SYS_BATCH_EXEC_INFO.TASK_STATUS is
'执行状态（UNR：未执行,RUN：执行中,END：正常结束，EXP：异常结束）';

comment on column SYS_BATCH_EXEC_INFO.START_MODE is
'启动模式';

comment on column SYS_BATCH_EXEC_INFO.CREATE_USER is
'创建人';

comment on column SYS_BATCH_EXEC_INFO.CREATE_DEPT is
'创建部门';

comment on column SYS_BATCH_EXEC_INFO.CREATE_TIME is
'创建时间';

comment on column SYS_BATCH_EXEC_INFO.UPDATE_USER is
'更新人';

comment on column SYS_BATCH_EXEC_INFO.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_BATCH_EXEC_LOG                                    */
/*==============================================================*/
create table SYS_BATCH_EXEC_LOG 
(
   SEQ_NO               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   BATCH_EXEDETAIL_SEQ_NO VARCHAR(32)          not null,
   LOG_INFO             CLOB,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCH_EXEC_LOG primary key (SEQ_NO)
);

comment on table SYS_BATCH_EXEC_LOG is
'批处理任务执行日志';

comment on column SYS_BATCH_EXEC_LOG.SEQ_NO is
'批处理任务执行信息ID';

comment on column SYS_BATCH_EXEC_LOG.SYSTEM_CODE is
'系统代码';

comment on column SYS_BATCH_EXEC_LOG.BATCH_EXEDETAIL_SEQ_NO is
'批处理任务组执行ID';

comment on column SYS_BATCH_EXEC_LOG.LOG_INFO is
'日志详细信息';

comment on column SYS_BATCH_EXEC_LOG.CREATE_USER is
'创建人';

comment on column SYS_BATCH_EXEC_LOG.CREATE_DEPT is
'创建部门';

comment on column SYS_BATCH_EXEC_LOG.CREATE_TIME is
'创建时间';

comment on column SYS_BATCH_EXEC_LOG.UPDATE_USER is
'更新人';

comment on column SYS_BATCH_EXEC_LOG.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_BATCH_GRP                                         */
/*==============================================================*/
create table SYS_BATCH_GRP 
(
   SEQ_NO               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   BATCH_NAME           VARCHAR(50)          not null,
   DETAIL               VARCHAR(200),
   PRE_CONDITION        VARCHAR(1000),
   PRE_BATCH_GROUP      VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCH_GRP primary key (SEQ_NO)
);

comment on table SYS_BATCH_GRP is
'批处理任务组';

comment on column SYS_BATCH_GRP.SEQ_NO is
'批处理任务组ID';

comment on column SYS_BATCH_GRP.SYSTEM_CODE is
'系统代码';

comment on column SYS_BATCH_GRP.BATCH_NAME is
'批处理任务组名称';

comment on column SYS_BATCH_GRP.DETAIL is
'批处理任务组详情';

comment on column SYS_BATCH_GRP.PRE_CONDITION is
'批处理组前置条件';

comment on column SYS_BATCH_GRP.PRE_BATCH_GROUP is
'父批处理组';

comment on column SYS_BATCH_GRP.CREATE_USER is
'创建人';

comment on column SYS_BATCH_GRP.CREATE_DEPT is
'创建部门';

comment on column SYS_BATCH_GRP.CREATE_TIME is
'创建时间';

comment on column SYS_BATCH_GRP.UPDATE_USER is
'更新人';

comment on column SYS_BATCH_GRP.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_BATCH_GRP_PRDLINK                                 */
/*==============================================================*/
create table SYS_BATCH_GRP_PRDLINK 
(
   GROUP_SEQ            VARCHAR(32)          not null,
   PRODUCT_ID           VARCHAR(32)          not null,
   PRODUCT_NAME         VARCHAR(200)         not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCH_GRP_PRDLINK primary key (GROUP_SEQ, PRODUCT_ID)
);

comment on table SYS_BATCH_GRP_PRDLINK is
'批处理产品组产品关联';

comment on column SYS_BATCH_GRP_PRDLINK.GROUP_SEQ is
'批处理产品组ID';

comment on column SYS_BATCH_GRP_PRDLINK.PRODUCT_ID is
'产品代码';

comment on column SYS_BATCH_GRP_PRDLINK.PRODUCT_NAME is
'产品名称';

comment on column SYS_BATCH_GRP_PRDLINK.CREATE_USER is
'创建人';

comment on column SYS_BATCH_GRP_PRDLINK.CREATE_DEPT is
'创建部门';

comment on column SYS_BATCH_GRP_PRDLINK.CREATE_TIME is
'创建时间';

comment on column SYS_BATCH_GRP_PRDLINK.UPDATE_USER is
'更新人';

comment on column SYS_BATCH_GRP_PRDLINK.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_BATCH_GRP_PRE_CON                                 */
/*==============================================================*/
create table SYS_BATCH_GRP_PRE_CON 
(
   CONDITION_KEY        VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   CONDITION_NAME       VARCHAR(200)         not null,
   VALUE                VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCH_GRP_PRE_CON primary key (CONDITION_KEY)
);

comment on table SYS_BATCH_GRP_PRE_CON is
'批处理组前置条件';

comment on column SYS_BATCH_GRP_PRE_CON.CONDITION_KEY is
'批处理组前置条件ID';

comment on column SYS_BATCH_GRP_PRE_CON.SYSTEM_CODE is
'系统代码';

comment on column SYS_BATCH_GRP_PRE_CON.CONDITION_NAME is
'条件名称';

comment on column SYS_BATCH_GRP_PRE_CON.VALUE is
'条件完成结果（0: false, 1: true）';

comment on column SYS_BATCH_GRP_PRE_CON.CREATE_USER is
'创建人';

comment on column SYS_BATCH_GRP_PRE_CON.CREATE_DEPT is
'创建部门';

comment on column SYS_BATCH_GRP_PRE_CON.CREATE_TIME is
'创建时间';

comment on column SYS_BATCH_GRP_PRE_CON.UPDATE_USER is
'更新人';

comment on column SYS_BATCH_GRP_PRE_CON.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_BATCH_JOB                                         */
/*==============================================================*/
create table SYS_BATCH_JOB 
(
   BATCH_SEQ_NO         VARCHAR(32)          not null,
   TASK_ID              VARCHAR(32)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCH_JOB primary key (BATCH_SEQ_NO, TASK_ID)
);

comment on table SYS_BATCH_JOB is
'批处理任务组任务关联';

comment on column SYS_BATCH_JOB.BATCH_SEQ_NO is
'批处理任务组';

comment on column SYS_BATCH_JOB.TASK_ID is
'批处理任务';

comment on column SYS_BATCH_JOB.CREATE_USER is
'创建人';

comment on column SYS_BATCH_JOB.CREATE_DEPT is
'创建部门';

comment on column SYS_BATCH_JOB.CREATE_TIME is
'创建时间';

comment on column SYS_BATCH_JOB.UPDATE_USER is
'更新人';

comment on column SYS_BATCH_JOB.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_BATCH_PRD_GRP                                     */
/*==============================================================*/
create table SYS_BATCH_PRD_GRP 
(
   SEQ_NO               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   GROUP_NAME           VARCHAR(200)         not null,
   DETAIL               VARCHAR(200),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCH_PRD_GRP primary key (SEQ_NO)
);

comment on table SYS_BATCH_PRD_GRP is
'批处理产品组';

comment on column SYS_BATCH_PRD_GRP.SEQ_NO is
'批处理产品组ID';

comment on column SYS_BATCH_PRD_GRP.SYSTEM_CODE is
'系统代码';

comment on column SYS_BATCH_PRD_GRP.GROUP_NAME is
'批处理产品组名称';

comment on column SYS_BATCH_PRD_GRP.DETAIL is
'批处理产品组详情';

comment on column SYS_BATCH_PRD_GRP.CREATE_USER is
'创建人';

comment on column SYS_BATCH_PRD_GRP.CREATE_DEPT is
'创建部门';

comment on column SYS_BATCH_PRD_GRP.CREATE_TIME is
'创建时间';

comment on column SYS_BATCH_PRD_GRP.UPDATE_USER is
'更新人';

comment on column SYS_BATCH_PRD_GRP.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_BATCH_QUARTZ_CONFIG                               */
/*==============================================================*/
create table SYS_BATCH_QUARTZ_CONFIG 
(
   CONFIG_ID            VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   PRD_GRP_ID           VARCHAR(32),
   TASK_GRP_ID          VARCHAR(32),
   QUARTS_CONFIG        VARCHAR(50)          not null,
   QUARTS_CONFIG_DESC   VARCHAR(200)         not null,
   REMARK               VARCHAR(200),
   OPERATE_TYPE         VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCH_QUARTZ_CONFIG primary key (CONFIG_ID)
);

comment on table SYS_BATCH_QUARTZ_CONFIG is
'系统定时任务配置';

comment on column SYS_BATCH_QUARTZ_CONFIG.CONFIG_ID is
'配置编号';

comment on column SYS_BATCH_QUARTZ_CONFIG.SYSTEM_CODE is
'系统代码';

comment on column SYS_BATCH_QUARTZ_CONFIG.PRD_GRP_ID is
'产品组代码';

comment on column SYS_BATCH_QUARTZ_CONFIG.TASK_GRP_ID is
'任务组代码';

comment on column SYS_BATCH_QUARTZ_CONFIG.QUARTS_CONFIG is
'定时配置';

comment on column SYS_BATCH_QUARTZ_CONFIG.QUARTS_CONFIG_DESC is
'定时配置说明';

comment on column SYS_BATCH_QUARTZ_CONFIG.REMARK is
'备注';

comment on column SYS_BATCH_QUARTZ_CONFIG.OPERATE_TYPE is
'操作类型';

comment on column SYS_BATCH_QUARTZ_CONFIG.CREATE_USER is
'创建人';

comment on column SYS_BATCH_QUARTZ_CONFIG.CREATE_DEPT is
'创建部门';

comment on column SYS_BATCH_QUARTZ_CONFIG.CREATE_TIME is
'创建时间';

comment on column SYS_BATCH_QUARTZ_CONFIG.UPDATE_USER is
'更新人';

comment on column SYS_BATCH_QUARTZ_CONFIG.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_BATCH_TASK                                        */
/*==============================================================*/
create table SYS_BATCH_TASK 
(
   TASK_ID              VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   TASK_NAME            VARCHAR(200),
   FTASK_ID             VARCHAR(32),
   BEAN_NAME            VARCHAR(200),
   TASK_PRI             NUMERIC(10),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_BATCH_TASK primary key (TASK_ID)
);

comment on table SYS_BATCH_TASK is
'批处理任务';

comment on column SYS_BATCH_TASK.TASK_ID is
'任务代码';

comment on column SYS_BATCH_TASK.SYSTEM_CODE is
'系统代码';

comment on column SYS_BATCH_TASK.TASK_NAME is
'任务名称';

comment on column SYS_BATCH_TASK.FTASK_ID is
'父任务代码';

comment on column SYS_BATCH_TASK.BEAN_NAME is
'批处理业务名称';

comment on column SYS_BATCH_TASK.TASK_PRI is
'任务优先级';

comment on column SYS_BATCH_TASK.CREATE_USER is
'创建人';

comment on column SYS_BATCH_TASK.CREATE_DEPT is
'创建部门';

comment on column SYS_BATCH_TASK.CREATE_TIME is
'创建时间';

comment on column SYS_BATCH_TASK.UPDATE_USER is
'更新人';

comment on column SYS_BATCH_TASK.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_DATADICT                                          */
/*==============================================================*/
create table SYS_DATADICT 
(
   DICT_CODE            VARCHAR(50)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   DICT_CNNAME          VARCHAR(200)         not null,
   DICT_ENNAME          VARCHAR(200),
   UPDATE_FLG           VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_DATADICT primary key (DICT_CODE)
);

comment on table SYS_DATADICT is
'数据字典';

comment on column SYS_DATADICT.DICT_CODE is
'字典代码';

comment on column SYS_DATADICT.SYSTEM_CODE is
'系统代码';

comment on column SYS_DATADICT.DICT_CNNAME is
'字典中文名称';

comment on column SYS_DATADICT.DICT_ENNAME is
'字典英文名称';

comment on column SYS_DATADICT.UPDATE_FLG is
'用户可更改标识';

comment on column SYS_DATADICT.CREATE_USER is
'创建人';

comment on column SYS_DATADICT.CREATE_DEPT is
'创建部门';

comment on column SYS_DATADICT.CREATE_TIME is
'创建时间';

comment on column SYS_DATADICT.UPDATE_USER is
'更新人';

comment on column SYS_DATADICT.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_DATADICT_LIST                                     */
/*==============================================================*/
create table SYS_DATADICT_LIST 
(
   DICT_CODE            VARCHAR(50)          not null,
   ITEM_CODE            VARCHAR(50)          not null,
   ITEM_VALUE           VARCHAR(200)         not null,
   ORDER_NO             NUMERIC(10)          not null,
   DEFAULT_VALUE        VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_DATADICT_LIST primary key (DICT_CODE, ITEM_CODE)
);

comment on table SYS_DATADICT_LIST is
'数据字典明细';

comment on column SYS_DATADICT_LIST.DICT_CODE is
'字典代码';

comment on column SYS_DATADICT_LIST.ITEM_CODE is
'数据代码';

comment on column SYS_DATADICT_LIST.ITEM_VALUE is
'参数值';

comment on column SYS_DATADICT_LIST.ORDER_NO is
'排列顺序';

comment on column SYS_DATADICT_LIST.DEFAULT_VALUE is
'默认值';

comment on column SYS_DATADICT_LIST.CREATE_USER is
'创建人';

comment on column SYS_DATADICT_LIST.CREATE_DEPT is
'创建部门';

comment on column SYS_DATADICT_LIST.CREATE_TIME is
'创建时间';

comment on column SYS_DATADICT_LIST.UPDATE_USER is
'更新人';

comment on column SYS_DATADICT_LIST.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_DATADICT_LIST_LINK                                */
/*==============================================================*/
create table SYS_DATADICT_LIST_LINK 
(
   DICT_CODE            VARCHAR(50)          not null,
   ITEM_CODE            VARCHAR(50)          not null,
   LINKED_DICT_CODE     VARCHAR(50)          not null,
   LINKED_ITEM_CODE     CHAR(50)             not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_DATADICT_LIST_LINK primary key (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE)
);

comment on table SYS_DATADICT_LIST_LINK is
'数据字典明细关联';

comment on column SYS_DATADICT_LIST_LINK.DICT_CODE is
'字典代码';

comment on column SYS_DATADICT_LIST_LINK.ITEM_CODE is
'数据代码';

comment on column SYS_DATADICT_LIST_LINK.LINKED_DICT_CODE is
'关联字典代码';

comment on column SYS_DATADICT_LIST_LINK.LINKED_ITEM_CODE is
'关联数据代码';

comment on column SYS_DATADICT_LIST_LINK.CREATE_USER is
'创建人';

comment on column SYS_DATADICT_LIST_LINK.CREATE_DEPT is
'创建部门';

comment on column SYS_DATADICT_LIST_LINK.CREATE_TIME is
'创建时间';

comment on column SYS_DATADICT_LIST_LINK.UPDATE_USER is
'更新人';

comment on column SYS_DATADICT_LIST_LINK.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_DATA_PROCESS_MARK                                 */
/*==============================================================*/
create table SYS_DATA_PROCESS_MARK 
(
   SEQ_NO               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   PAGE_ID              VARCHAR(50),
   BUSINESS_DATA_PK1    VARCHAR(50),
   BUSINESS_DATA_PK2    VARCHAR(50),
   BUSINESS_DATA_PK3    VARCHAR(50),
   BUSINESS_DATA_PK4    VARCHAR(50),
   BUSINESS_DATA_PK5    VARCHAR(50),
   BUSINESS_DATA_PK6    VARCHAR(50),
   BUSINESS_DATA_PK7    VARCHAR(50),
   BUSINESS_DATA_PK8    VARCHAR(50),
   BUSINESS_DATA_PK9    VARCHAR(50),
   BUSINESS_DATA_PK10   VARCHAR(50),
   HIS_NO               NUMERIC(10),
   BUSINESS_DATA        CLOB,
   WORKFLOW_ID          VARCHAR(32),
   STATUS               VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_DATA_PROCESS_MARK primary key (SEQ_NO)
)
partition by range(WORKFLOW_ID)(
	PARTITION SYS_DATA_PROCESS_MARK_PART1 VALUES LESS THAN ('00000000000000000000000001000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART2 VALUES LESS THAN ('00000000000000000000000002000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART3 VALUES LESS THAN ('00000000000000000000000003000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART4 VALUES LESS THAN ('00000000000000000000000004000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART5 VALUES LESS THAN ('00000000000000000000000005000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART6 VALUES LESS THAN ('00000000000000000000000006000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART7 VALUES LESS THAN ('00000000000000000000000007000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART8 VALUES LESS THAN ('00000000000000000000000008000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART9 VALUES LESS THAN ('00000000000000000000000009000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART10 VALUES LESS THAN ('00000000000000000000000010000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART11 VALUES LESS THAN ('00000000000000000000000011000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART12 VALUES LESS THAN ('00000000000000000000000012000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART13 VALUES LESS THAN ('00000000000000000000000013000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART14 VALUES LESS THAN ('00000000000000000000000014000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART15 VALUES LESS THAN ('00000000000000000000000015000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART16 VALUES LESS THAN ('00000000000000000000000016000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART17 VALUES LESS THAN ('00000000000000000000000017000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART18 VALUES LESS THAN ('00000000000000000000000018000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART19 VALUES LESS THAN ('00000000000000000000000019000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART20 VALUES LESS THAN ('00000000000000000000000020000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART21 VALUES LESS THAN ('00000000000000000000000021000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART22 VALUES LESS THAN ('00000000000000000000000022000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART23 VALUES LESS THAN ('00000000000000000000000023000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART24 VALUES LESS THAN ('00000000000000000000000024000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART25 VALUES LESS THAN ('00000000000000000000000025000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART26 VALUES LESS THAN ('00000000000000000000000026000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART27 VALUES LESS THAN ('00000000000000000000000027000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART28 VALUES LESS THAN ('00000000000000000000000028000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART29 VALUES LESS THAN ('00000000000000000000000029000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART30 VALUES LESS THAN ('00000000000000000000000030000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART31 VALUES LESS THAN ('00000000000000000000000031000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART32 VALUES LESS THAN ('00000000000000000000000032000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART33 VALUES LESS THAN ('00000000000000000000000033000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART34 VALUES LESS THAN ('00000000000000000000000034000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART35 VALUES LESS THAN ('00000000000000000000000035000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART36 VALUES LESS THAN ('00000000000000000000000036000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART37 VALUES LESS THAN ('00000000000000000000000037000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART38 VALUES LESS THAN ('00000000000000000000000038000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART39 VALUES LESS THAN ('00000000000000000000000039000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART40 VALUES LESS THAN ('00000000000000000000000040000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART41 VALUES LESS THAN ('00000000000000000000000041000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART42 VALUES LESS THAN ('00000000000000000000000042000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART43 VALUES LESS THAN ('00000000000000000000000043000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART44 VALUES LESS THAN ('00000000000000000000000044000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART45 VALUES LESS THAN ('00000000000000000000000045000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART46 VALUES LESS THAN ('00000000000000000000000046000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART47 VALUES LESS THAN ('00000000000000000000000047000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART48 VALUES LESS THAN ('00000000000000000000000048000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART49 VALUES LESS THAN ('00000000000000000000000049000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART50 VALUES LESS THAN ('00000000000000000000000050000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART51 VALUES LESS THAN ('00000000000000000000000051000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART52 VALUES LESS THAN ('00000000000000000000000052000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART53 VALUES LESS THAN ('00000000000000000000000053000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART54 VALUES LESS THAN ('00000000000000000000000054000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART55 VALUES LESS THAN ('00000000000000000000000055000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART56 VALUES LESS THAN ('00000000000000000000000056000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART57 VALUES LESS THAN ('00000000000000000000000057000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART58 VALUES LESS THAN ('00000000000000000000000058000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART59 VALUES LESS THAN ('00000000000000000000000059000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART60 VALUES LESS THAN ('00000000000000000000000060000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART61 VALUES LESS THAN ('00000000000000000000000061000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART62 VALUES LESS THAN ('00000000000000000000000062000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART63 VALUES LESS THAN ('00000000000000000000000063000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART64 VALUES LESS THAN ('00000000000000000000000064000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART65 VALUES LESS THAN ('00000000000000000000000065000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART66 VALUES LESS THAN ('00000000000000000000000066000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART67 VALUES LESS THAN ('00000000000000000000000067000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART68 VALUES LESS THAN ('00000000000000000000000068000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART69 VALUES LESS THAN ('00000000000000000000000069000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART70 VALUES LESS THAN ('00000000000000000000000070000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART71 VALUES LESS THAN ('00000000000000000000000071000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART72 VALUES LESS THAN ('00000000000000000000000072000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART73 VALUES LESS THAN ('00000000000000000000000073000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART74 VALUES LESS THAN ('00000000000000000000000074000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART75 VALUES LESS THAN ('00000000000000000000000075000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART76 VALUES LESS THAN ('00000000000000000000000076000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART77 VALUES LESS THAN ('00000000000000000000000077000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART78 VALUES LESS THAN ('00000000000000000000000078000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART79 VALUES LESS THAN ('00000000000000000000000079000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART80 VALUES LESS THAN ('00000000000000000000000080000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART81 VALUES LESS THAN ('00000000000000000000000081000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART82 VALUES LESS THAN ('00000000000000000000000082000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART83 VALUES LESS THAN ('00000000000000000000000083000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART84 VALUES LESS THAN ('00000000000000000000000084000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART85 VALUES LESS THAN ('00000000000000000000000085000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART86 VALUES LESS THAN ('00000000000000000000000086000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART87 VALUES LESS THAN ('00000000000000000000000087000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART88 VALUES LESS THAN ('00000000000000000000000088000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART89 VALUES LESS THAN ('00000000000000000000000089000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART90 VALUES LESS THAN ('00000000000000000000000090000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART91 VALUES LESS THAN ('00000000000000000000000091000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART92 VALUES LESS THAN ('00000000000000000000000092000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART93 VALUES LESS THAN ('00000000000000000000000093000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART94 VALUES LESS THAN ('00000000000000000000000094000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART95 VALUES LESS THAN ('00000000000000000000000095000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART96 VALUES LESS THAN ('00000000000000000000000096000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART97 VALUES LESS THAN ('00000000000000000000000097000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART98 VALUES LESS THAN ('00000000000000000000000098000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART99 VALUES LESS THAN ('00000000000000000000000099000000'),
PARTITION SYS_DATA_PROCESS_MARK_PART100 VALUES LESS THAN ('00000000000000000000000100000000')
);

comment on table SYS_DATA_PROCESS_MARK is
'数据留痕（以工作流代码分区）';

comment on column SYS_DATA_PROCESS_MARK.SEQ_NO is
'流水号';

comment on column SYS_DATA_PROCESS_MARK.SYSTEM_CODE is
'系统代码';

comment on column SYS_DATA_PROCESS_MARK.PAGE_ID is
'画面代码';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA_PK1 is
'业务主键1';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA_PK2 is
'业务主键2';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA_PK3 is
'业务主键3';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA_PK4 is
'业务主键4';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA_PK5 is
'业务主键5';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA_PK6 is
'业务主键6';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA_PK7 is
'业务主键7';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA_PK8 is
'业务主键8';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA_PK9 is
'业务主键9';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA_PK10 is
'业务主键10';

comment on column SYS_DATA_PROCESS_MARK.HIS_NO is
'履历番号';

comment on column SYS_DATA_PROCESS_MARK.BUSINESS_DATA is
'画面JSON数据';

comment on column SYS_DATA_PROCESS_MARK.WORKFLOW_ID is
'工作流代码';

comment on column SYS_DATA_PROCESS_MARK.STATUS is
'数据状态';

comment on column SYS_DATA_PROCESS_MARK.CREATE_USER is
'创建人';

comment on column SYS_DATA_PROCESS_MARK.CREATE_DEPT is
'创建部门';

comment on column SYS_DATA_PROCESS_MARK.CREATE_TIME is
'创建时间';

comment on column SYS_DATA_PROCESS_MARK.UPDATE_USER is
'更新人';

comment on column SYS_DATA_PROCESS_MARK.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_DATA_PROCESS_MARK_HIS                             */
/*==============================================================*/
create table SYS_DATA_PROCESS_MARK_HIS 
(
   SEQ_NO               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   PAGE_ID              VARCHAR(50),
   BUSINESS_DATA_PK1    VARCHAR(50),
   BUSINESS_DATA_PK2    VARCHAR(50),
   BUSINESS_DATA_PK3    VARCHAR(50),
   BUSINESS_DATA_PK4    VARCHAR(50),
   BUSINESS_DATA_PK5    VARCHAR(50),
   BUSINESS_DATA_PK6    VARCHAR(50),
   BUSINESS_DATA_PK7    VARCHAR(50),
   BUSINESS_DATA_PK8    VARCHAR(50),
   BUSINESS_DATA_PK9    VARCHAR(50),
   BUSINESS_DATA_PK10   VARCHAR(50),
   HIS_NO               NUMERIC(10),
   BUSINESS_DATA        CLOB,
   WORKFLOW_ID          VARCHAR(32),
   STATUS               VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_DATA_PROCESS_MARK_HIS primary key (SEQ_NO)
)
partition by range(WORKFLOW_ID)(
	PARTITION SYS_DATA_PROCESS_MARK_HIS1 VALUES LESS THAN ('00000000000000000000000001000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS2 VALUES LESS THAN ('00000000000000000000000002000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS3 VALUES LESS THAN ('00000000000000000000000003000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS4 VALUES LESS THAN ('00000000000000000000000004000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS5 VALUES LESS THAN ('00000000000000000000000005000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS6 VALUES LESS THAN ('00000000000000000000000006000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS7 VALUES LESS THAN ('00000000000000000000000007000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS8 VALUES LESS THAN ('00000000000000000000000008000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS9 VALUES LESS THAN ('00000000000000000000000009000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS10 VALUES LESS THAN ('00000000000000000000000010000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS11 VALUES LESS THAN ('00000000000000000000000011000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS12 VALUES LESS THAN ('00000000000000000000000012000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS13 VALUES LESS THAN ('00000000000000000000000013000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS14 VALUES LESS THAN ('00000000000000000000000014000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS15 VALUES LESS THAN ('00000000000000000000000015000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS16 VALUES LESS THAN ('00000000000000000000000016000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS17 VALUES LESS THAN ('00000000000000000000000017000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS18 VALUES LESS THAN ('00000000000000000000000018000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS19 VALUES LESS THAN ('00000000000000000000000019000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS20 VALUES LESS THAN ('00000000000000000000000020000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS21 VALUES LESS THAN ('00000000000000000000000021000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS22 VALUES LESS THAN ('00000000000000000000000022000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS23 VALUES LESS THAN ('00000000000000000000000023000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS24 VALUES LESS THAN ('00000000000000000000000024000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS25 VALUES LESS THAN ('00000000000000000000000025000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS26 VALUES LESS THAN ('00000000000000000000000026000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS27 VALUES LESS THAN ('00000000000000000000000027000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS28 VALUES LESS THAN ('00000000000000000000000028000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS29 VALUES LESS THAN ('00000000000000000000000029000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS30 VALUES LESS THAN ('00000000000000000000000030000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS31 VALUES LESS THAN ('00000000000000000000000031000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS32 VALUES LESS THAN ('00000000000000000000000032000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS33 VALUES LESS THAN ('00000000000000000000000033000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS34 VALUES LESS THAN ('00000000000000000000000034000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS35 VALUES LESS THAN ('00000000000000000000000035000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS36 VALUES LESS THAN ('00000000000000000000000036000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS37 VALUES LESS THAN ('00000000000000000000000037000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS38 VALUES LESS THAN ('00000000000000000000000038000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS39 VALUES LESS THAN ('00000000000000000000000039000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS40 VALUES LESS THAN ('00000000000000000000000040000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS41 VALUES LESS THAN ('00000000000000000000000041000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS42 VALUES LESS THAN ('00000000000000000000000042000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS43 VALUES LESS THAN ('00000000000000000000000043000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS44 VALUES LESS THAN ('00000000000000000000000044000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS45 VALUES LESS THAN ('00000000000000000000000045000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS46 VALUES LESS THAN ('00000000000000000000000046000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS47 VALUES LESS THAN ('00000000000000000000000047000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS48 VALUES LESS THAN ('00000000000000000000000048000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS49 VALUES LESS THAN ('00000000000000000000000049000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS50 VALUES LESS THAN ('00000000000000000000000050000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS51 VALUES LESS THAN ('00000000000000000000000051000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS52 VALUES LESS THAN ('00000000000000000000000052000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS53 VALUES LESS THAN ('00000000000000000000000053000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS54 VALUES LESS THAN ('00000000000000000000000054000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS55 VALUES LESS THAN ('00000000000000000000000055000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS56 VALUES LESS THAN ('00000000000000000000000056000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS57 VALUES LESS THAN ('00000000000000000000000057000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS58 VALUES LESS THAN ('00000000000000000000000058000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS59 VALUES LESS THAN ('00000000000000000000000059000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS60 VALUES LESS THAN ('00000000000000000000000060000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS61 VALUES LESS THAN ('00000000000000000000000061000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS62 VALUES LESS THAN ('00000000000000000000000062000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS63 VALUES LESS THAN ('00000000000000000000000063000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS64 VALUES LESS THAN ('00000000000000000000000064000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS65 VALUES LESS THAN ('00000000000000000000000065000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS66 VALUES LESS THAN ('00000000000000000000000066000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS67 VALUES LESS THAN ('00000000000000000000000067000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS68 VALUES LESS THAN ('00000000000000000000000068000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS69 VALUES LESS THAN ('00000000000000000000000069000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS70 VALUES LESS THAN ('00000000000000000000000070000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS71 VALUES LESS THAN ('00000000000000000000000071000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS72 VALUES LESS THAN ('00000000000000000000000072000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS73 VALUES LESS THAN ('00000000000000000000000073000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS74 VALUES LESS THAN ('00000000000000000000000074000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS75 VALUES LESS THAN ('00000000000000000000000075000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS76 VALUES LESS THAN ('00000000000000000000000076000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS77 VALUES LESS THAN ('00000000000000000000000077000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS78 VALUES LESS THAN ('00000000000000000000000078000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS79 VALUES LESS THAN ('00000000000000000000000079000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS80 VALUES LESS THAN ('00000000000000000000000080000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS81 VALUES LESS THAN ('00000000000000000000000081000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS82 VALUES LESS THAN ('00000000000000000000000082000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS83 VALUES LESS THAN ('00000000000000000000000083000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS84 VALUES LESS THAN ('00000000000000000000000084000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS85 VALUES LESS THAN ('00000000000000000000000085000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS86 VALUES LESS THAN ('00000000000000000000000086000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS87 VALUES LESS THAN ('00000000000000000000000087000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS88 VALUES LESS THAN ('00000000000000000000000088000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS89 VALUES LESS THAN ('00000000000000000000000089000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS90 VALUES LESS THAN ('00000000000000000000000090000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS91 VALUES LESS THAN ('00000000000000000000000091000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS92 VALUES LESS THAN ('00000000000000000000000092000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS93 VALUES LESS THAN ('00000000000000000000000093000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS94 VALUES LESS THAN ('00000000000000000000000094000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS95 VALUES LESS THAN ('00000000000000000000000095000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS96 VALUES LESS THAN ('00000000000000000000000096000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS97 VALUES LESS THAN ('00000000000000000000000097000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS98 VALUES LESS THAN ('00000000000000000000000098000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS99 VALUES LESS THAN ('00000000000000000000000099000000'),
PARTITION SYS_DATA_PROCESS_MARK_HIS100 VALUES LESS THAN ('00000000000000000000000100000000')
);

comment on table SYS_DATA_PROCESS_MARK_HIS is
'数据留痕历史表（以工作流代码分区）';

comment on column SYS_DATA_PROCESS_MARK_HIS.SEQ_NO is
'流水号';

comment on column SYS_DATA_PROCESS_MARK_HIS.SYSTEM_CODE is
'系统代码';

comment on column SYS_DATA_PROCESS_MARK_HIS.PAGE_ID is
'画面代码';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA_PK1 is
'业务主键1';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA_PK2 is
'业务主键2';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA_PK3 is
'业务主键3';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA_PK4 is
'业务主键4';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA_PK5 is
'业务主键5';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA_PK6 is
'业务主键6';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA_PK7 is
'业务主键7';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA_PK8 is
'业务主键8';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA_PK9 is
'业务主键9';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA_PK10 is
'业务主键10';

comment on column SYS_DATA_PROCESS_MARK_HIS.HIS_NO is
'履历番号';

comment on column SYS_DATA_PROCESS_MARK_HIS.BUSINESS_DATA is
'画面JSON数据';

comment on column SYS_DATA_PROCESS_MARK_HIS.WORKFLOW_ID is
'工作流代码';

comment on column SYS_DATA_PROCESS_MARK_HIS.STATUS is
'数据状态';

comment on column SYS_DATA_PROCESS_MARK_HIS.CREATE_USER is
'创建人';

comment on column SYS_DATA_PROCESS_MARK_HIS.CREATE_DEPT is
'创建部门';

comment on column SYS_DATA_PROCESS_MARK_HIS.CREATE_TIME is
'创建时间';

comment on column SYS_DATA_PROCESS_MARK_HIS.UPDATE_USER is
'更新人';

comment on column SYS_DATA_PROCESS_MARK_HIS.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_DATA_ROLE_MODULE                                  */
/*==============================================================*/
create table SYS_DATA_ROLE_MODULE 
(
   ROLE_ID              VARCHAR(32)          not null,
   INSTITUTION_CODE     VARCHAR(32)          not null,
   DEPT_CODE            VARCHAR(32)          not null,
   ROLE_DATA_TYPE       VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_DATA_ROLE_MODULE primary key (ROLE_ID, INSTITUTION_CODE, DEPT_CODE, ROLE_DATA_TYPE)
);

comment on table SYS_DATA_ROLE_MODULE is
'数据权限管理';

comment on column SYS_DATA_ROLE_MODULE.ROLE_ID is
'角色代码';

comment on column SYS_DATA_ROLE_MODULE.INSTITUTION_CODE is
'机构代码';

comment on column SYS_DATA_ROLE_MODULE.DEPT_CODE is
'部门代码（没有部门的时候，99固定）';

comment on column SYS_DATA_ROLE_MODULE.ROLE_DATA_TYPE is
'数据权限类型';

comment on column SYS_DATA_ROLE_MODULE.CREATE_USER is
'创建人';

comment on column SYS_DATA_ROLE_MODULE.CREATE_DEPT is
'创建部门';

comment on column SYS_DATA_ROLE_MODULE.CREATE_TIME is
'创建时间';

comment on column SYS_DATA_ROLE_MODULE.UPDATE_USER is
'更新人';

comment on column SYS_DATA_ROLE_MODULE.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_DEPARTMENT                                        */
/*==============================================================*/
create table SYS_DEPARTMENT 
(
   DEPT_CODE            VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   DEPT_NAME            VARCHAR(200)         not null,
   DEPT_FUN             VARCHAR(50),
   INSTITUTION_CODE     VARCHAR(32)          not null,
   UPPER_DEPT_CODE      VARCHAR(32),
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_DEPARTMENT primary key (DEPT_CODE)
);

comment on table SYS_DEPARTMENT is
'内部职能部门';

comment on column SYS_DEPARTMENT.DEPT_CODE is
'部门代码';

comment on column SYS_DEPARTMENT.SYSTEM_CODE is
'系统代码';

comment on column SYS_DEPARTMENT.DEPT_NAME is
'部门名称';

comment on column SYS_DEPARTMENT.DEPT_FUN is
'部门职能';

comment on column SYS_DEPARTMENT.INSTITUTION_CODE is
'机构代码';

comment on column SYS_DEPARTMENT.UPPER_DEPT_CODE is
'上级部门';

comment on column SYS_DEPARTMENT.REMARK is
'备注';

comment on column SYS_DEPARTMENT.CREATE_USER is
'创建人';

comment on column SYS_DEPARTMENT.CREATE_DEPT is
'创建部门';

comment on column SYS_DEPARTMENT.CREATE_TIME is
'创建时间';

comment on column SYS_DEPARTMENT.UPDATE_USER is
'更新人';

comment on column SYS_DEPARTMENT.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_HOME_PAGE_CONFIG                                  */
/*==============================================================*/
create table SYS_HOME_PAGE_CONFIG 
(
   CONFIG_ID            VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   CONFIG_NAME          VARCHAR(200)         not null,
   SHARE_TYPE           VARCHAR(50)          not null,
   SHARE_TARGET         CLOB,
   LAYOUT_STYLE         CLOB,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_HOME_PAGE_CONFIG primary key (CONFIG_ID)
);

comment on table SYS_HOME_PAGE_CONFIG is
'主页配置';

comment on column SYS_HOME_PAGE_CONFIG.CONFIG_ID is
'配置ID';

comment on column SYS_HOME_PAGE_CONFIG.SYSTEM_CODE is
'系统代码';

comment on column SYS_HOME_PAGE_CONFIG.CONFIG_NAME is
'主页配置名称';

comment on column SYS_HOME_PAGE_CONFIG.SHARE_TYPE is
'分享类型';

comment on column SYS_HOME_PAGE_CONFIG.SHARE_TARGET is
'分享对象';

comment on column SYS_HOME_PAGE_CONFIG.LAYOUT_STYLE is
'布局样式';

comment on column SYS_HOME_PAGE_CONFIG.CREATE_USER is
'创建人';

comment on column SYS_HOME_PAGE_CONFIG.CREATE_DEPT is
'创建部门';

comment on column SYS_HOME_PAGE_CONFIG.CREATE_TIME is
'创建时间';

comment on column SYS_HOME_PAGE_CONFIG.UPDATE_USER is
'更新人';

comment on column SYS_HOME_PAGE_CONFIG.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_INSTITUTION                                       */
/*==============================================================*/
create table SYS_INSTITUTION 
(
   INSTITUTION_CODE     VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   INSTITUTION_NAME     VARCHAR(200)         not null,
   INSTITUTION_TYPE     VARCHAR(50)          not null,
   UPPER_INSTITUTION_CODE VARCHAR(32),
   INSITITUTE_CODE      VARCHAR(32),
   ASS_PEOPLE           VARCHAR(20),
   ASS_PHONE            VARCHAR(20),
   GRD_INFO             VARCHAR(50),
   PROVINCE             VARCHAR(50),
   CITY                 VARCHAR(50),
   COUNTIES             VARCHAR(50),
   OTHER                VARCHAR(200),
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_INSTITUTION primary key (INSTITUTION_CODE)
);

comment on table SYS_INSTITUTION is
'内部机构';

comment on column SYS_INSTITUTION.INSTITUTION_CODE is
'机构代码';

comment on column SYS_INSTITUTION.SYSTEM_CODE is
'系统代码';

comment on column SYS_INSTITUTION.INSTITUTION_NAME is
'机构名称';

comment on column SYS_INSTITUTION.INSTITUTION_TYPE is
'机构类型';

comment on column SYS_INSTITUTION.UPPER_INSTITUTION_CODE is
'上级机构代码';

comment on column SYS_INSTITUTION.INSITITUTE_CODE is
'组织机构代码（9位或者16位社会信用代码）';

comment on column SYS_INSTITUTION.ASS_PEOPLE is
'联系人';

comment on column SYS_INSTITUTION.ASS_PHONE is
'联系电话';

comment on column SYS_INSTITUTION.GRD_INFO is
'层级信息';

comment on column SYS_INSTITUTION.PROVINCE is
'省份';

comment on column SYS_INSTITUTION.CITY is
'城市';

comment on column SYS_INSTITUTION.COUNTIES is
'县区';

comment on column SYS_INSTITUTION.OTHER is
'其他';

comment on column SYS_INSTITUTION.REMARK is
'备注';

comment on column SYS_INSTITUTION.CREATE_USER is
'创建人';

comment on column SYS_INSTITUTION.CREATE_DEPT is
'创建部门';

comment on column SYS_INSTITUTION.CREATE_TIME is
'创建时间';

comment on column SYS_INSTITUTION.UPDATE_USER is
'更新人';

comment on column SYS_INSTITUTION.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_MENU                                              */
/*==============================================================*/
create table SYS_MENU 
(
   MENU_ID              VARCHAR(50)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   ICON                 VARCHAR(200),
   PARENT_MENU_ID       VARCHAR(50),
   PAGE_ID              VARCHAR(50),
   ORDER_NO             NUMERIC(10),
   IS_SPREAD            VARCHAR(50),
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_MENU primary key (MENU_ID)
);

comment on table SYS_MENU is
'菜单';

comment on column SYS_MENU.MENU_ID is
'菜单代码';

comment on column SYS_MENU.SYSTEM_CODE is
'系统代码';

comment on column SYS_MENU.ICON is
'菜单图标';

comment on column SYS_MENU.PARENT_MENU_ID is
'亲菜单代码';

comment on column SYS_MENU.PAGE_ID is
'画面代码（菜单对应的入口画面）';

comment on column SYS_MENU.ORDER_NO is
'排列顺序';

comment on column SYS_MENU.IS_SPREAD is
'是否默认展开';

comment on column SYS_MENU.REMARK is
'备注';

comment on column SYS_MENU.CREATE_USER is
'创建人';

comment on column SYS_MENU.CREATE_DEPT is
'创建部门';

comment on column SYS_MENU.CREATE_TIME is
'创建时间';

comment on column SYS_MENU.UPDATE_USER is
'更新人';

comment on column SYS_MENU.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_MQ_BEAN_PROPREF                                   */
/*==============================================================*/
create table SYS_MQ_BEAN_PROPREF 
(
   SRC_CLASS_NAME       VARCHAR(2000)        not null,
   DEST_CLASS_NAME      VARCHAR(2000)        not null,
   SRC_PROPERTY         VARCHAR(200)         not null,
   DEST_PROPERTY        VARCHAR(200)         not null,
   constraint PK_SYS_MQ_BEAN_PROPREF primary key (SRC_CLASS_NAME, DEST_CLASS_NAME, SRC_PROPERTY, DEST_PROPERTY)
);

comment on table SYS_MQ_BEAN_PROPREF is
'实体属性映射（ActiveMQ用）';

comment on column SYS_MQ_BEAN_PROPREF.SRC_CLASS_NAME is
'源实体类名';

comment on column SYS_MQ_BEAN_PROPREF.DEST_CLASS_NAME is
'目标实体类名';

comment on column SYS_MQ_BEAN_PROPREF.SRC_PROPERTY is
'源属性';

comment on column SYS_MQ_BEAN_PROPREF.DEST_PROPERTY is
'目标属性';

/*==============================================================*/
/* Table: SYS_MQ_MESSAGE_LOG                                    */
/*==============================================================*/
create table SYS_MQ_MESSAGE_LOG 
(
   MESSAGE_ID           VARCHAR(32)          not null,
   DEST_SYSTEM          VARCHAR(32)          not null,
   SRC_SYSTEM           VARCHAR(32)          not null,
   BUSINESS_MODE        VARCHAR(50)          not null,
   OPERATION_TYPE       VARCHAR(50)          not null,
   ENTITY_CLASS_NAME    VARCHAR(200)         not null,
   BUSINESS_ENTITY_JSON CLOB                 not null,
   REPLY_ENTITY_CLASS_NAME VARCHAR(200),
   REPLY_ENTITY_JSON    CLOB,
   SEND_STATUS          VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_MQ_MESSAGE_LOG primary key (MESSAGE_ID)
);

comment on table SYS_MQ_MESSAGE_LOG is
'推送消息日志（ActiveMQ用）';

comment on column SYS_MQ_MESSAGE_LOG.MESSAGE_ID is
'消息编号';

comment on column SYS_MQ_MESSAGE_LOG.DEST_SYSTEM is
'目标系统';

comment on column SYS_MQ_MESSAGE_LOG.SRC_SYSTEM is
'源系统';

comment on column SYS_MQ_MESSAGE_LOG.BUSINESS_MODE is
'业务类型';

comment on column SYS_MQ_MESSAGE_LOG.OPERATION_TYPE is
'操作类型';

comment on column SYS_MQ_MESSAGE_LOG.ENTITY_CLASS_NAME is
'业务实体类名';

comment on column SYS_MQ_MESSAGE_LOG.BUSINESS_ENTITY_JSON is
'业务实体';

comment on column SYS_MQ_MESSAGE_LOG.REPLY_ENTITY_CLASS_NAME is
'回复消息类名';

comment on column SYS_MQ_MESSAGE_LOG.REPLY_ENTITY_JSON is
'回复消息实体';

comment on column SYS_MQ_MESSAGE_LOG.SEND_STATUS is
'发送状态';

comment on column SYS_MQ_MESSAGE_LOG.CREATE_USER is
'创建人';

comment on column SYS_MQ_MESSAGE_LOG.CREATE_TIME is
'创建时间';

comment on column SYS_MQ_MESSAGE_LOG.UPDATE_USER is
'更新人';

comment on column SYS_MQ_MESSAGE_LOG.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_PAGE                                              */
/*==============================================================*/
create table SYS_PAGE 
(
   PAGE_ID              VARCHAR(50)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   PAGE_URL             VARCHAR(200)         not null,
   PAGE_FILE            VARCHAR(200),
   MENU_ID              VARCHAR(50),
   HEIGHT               VARCHAR(50),
   WIDTH                VARCHAR(50),
   IS_HP_SELECT         VARCHAR(50)          not null,
   IS_NEED_WORKFLOW     VARCHAR(50)          not null,
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_PAGE primary key (PAGE_ID)
);

comment on table SYS_PAGE is
'画面';

comment on column SYS_PAGE.PAGE_ID is
'画面代码（菜单对应的入口画面）';

comment on column SYS_PAGE.SYSTEM_CODE is
'系统代码';

comment on column SYS_PAGE.PAGE_URL is
'URL';

comment on column SYS_PAGE.PAGE_FILE is
'画面文件';

comment on column SYS_PAGE.MENU_ID is
'菜单代码';

comment on column SYS_PAGE.HEIGHT is
'高度';

comment on column SYS_PAGE.WIDTH is
'宽度';

comment on column SYS_PAGE.IS_HP_SELECT is
'是否是主页配置可选对象';

comment on column SYS_PAGE.IS_NEED_WORKFLOW is
'是否需要工作流';

comment on column SYS_PAGE.REMARK is
'备注';

comment on column SYS_PAGE.CREATE_USER is
'创建人';

comment on column SYS_PAGE.CREATE_DEPT is
'创建部门';

comment on column SYS_PAGE.CREATE_TIME is
'创建时间';

comment on column SYS_PAGE.UPDATE_USER is
'更新人';

comment on column SYS_PAGE.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_PAGE_DICTLIST                                     */
/*==============================================================*/
create table SYS_PAGE_DICTLIST 
(
   PAGE_ID              VARCHAR(50)          not null,
   DICT_ID              VARCHAR(50)          not null,
   DICT_ITEM_VAL        VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_PAGE_DICTLIST primary key (PAGE_ID, DICT_ID, DICT_ITEM_VAL)
);

comment on table SYS_PAGE_DICTLIST is
'画面数据字典明细关联';

comment on column SYS_PAGE_DICTLIST.PAGE_ID is
'画面代码（菜单对应的入口画面）';

comment on column SYS_PAGE_DICTLIST.DICT_ID is
'数据字典代码';

comment on column SYS_PAGE_DICTLIST.DICT_ITEM_VAL is
'数据字典明细值';

comment on column SYS_PAGE_DICTLIST.CREATE_USER is
'创建人';

comment on column SYS_PAGE_DICTLIST.CREATE_DEPT is
'创建部门';

comment on column SYS_PAGE_DICTLIST.CREATE_TIME is
'创建时间';

comment on column SYS_PAGE_DICTLIST.UPDATE_USER is
'更新人';

comment on column SYS_PAGE_DICTLIST.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_PAGE_ELEMENT                                      */
/*==============================================================*/
create table SYS_PAGE_ELEMENT 
(
   PAGE_ID              VARCHAR(50)          not null,
   ELEMENT_ID           VARCHAR(50)          not null,
   ELEMENT_TYPE         VARCHAR(50),
   IS_CONTROL           VARCHAR(50),
   TRANSFER_PAGE_ID     VARCHAR(50),
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_PAGE_ELEMENT primary key (PAGE_ID, ELEMENT_ID)
);

comment on table SYS_PAGE_ELEMENT is
'画面要素';

comment on column SYS_PAGE_ELEMENT.PAGE_ID is
'画面代码（菜单对应的入口画面）';

comment on column SYS_PAGE_ELEMENT.ELEMENT_ID is
'要素代码';

comment on column SYS_PAGE_ELEMENT.ELEMENT_TYPE is
'要素类型';

comment on column SYS_PAGE_ELEMENT.IS_CONTROL is
'是否受权限控制';

comment on column SYS_PAGE_ELEMENT.TRANSFER_PAGE_ID is
'迁移先画面代码';

comment on column SYS_PAGE_ELEMENT.REMARK is
'备注';

comment on column SYS_PAGE_ELEMENT.CREATE_USER is
'创建人';

comment on column SYS_PAGE_ELEMENT.CREATE_DEPT is
'创建部门';

comment on column SYS_PAGE_ELEMENT.CREATE_TIME is
'创建时间';

comment on column SYS_PAGE_ELEMENT.UPDATE_USER is
'更新人';

comment on column SYS_PAGE_ELEMENT.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_PARA                                              */
/*==============================================================*/
create table SYS_PARA 
(
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   PARA_CODE            VARCHAR(50)          not null,
   PARAM_VALUE          VARCHAR(200)         not null,
   DATA_TYPE            VARCHAR(50),
   DATA_SCOPE           VARCHAR(100),
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_PARA primary key (SYSTEM_CODE, PARA_CODE)
);

comment on table SYS_PARA is
'系统参数';

comment on column SYS_PARA.SYSTEM_CODE is
'系统代码';

comment on column SYS_PARA.PARA_CODE is
'参数代码';

comment on column SYS_PARA.PARAM_VALUE is
'参数值';

comment on column SYS_PARA.DATA_TYPE is
'数据类型(01:文字列,02:下拉框,03:日期)';

comment on column SYS_PARA.DATA_SCOPE is
'选择范围';

comment on column SYS_PARA.REMARK is
'备注';

comment on column SYS_PARA.CREATE_USER is
'创建人';

comment on column SYS_PARA.CREATE_DEPT is
'创建部门';

comment on column SYS_PARA.CREATE_TIME is
'创建时间';

comment on column SYS_PARA.UPDATE_USER is
'更新人';

comment on column SYS_PARA.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE 
(
   ROLE_ID              VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   ROLE_NAME            VARCHAR(200)         not null,
   ROLE_POS             VARCHAR(50),
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_ROLE primary key (ROLE_ID)
);

comment on table SYS_ROLE is
'角色';

comment on column SYS_ROLE.ROLE_ID is
'角色代码';

comment on column SYS_ROLE.SYSTEM_CODE is
'系统代码';

comment on column SYS_ROLE.ROLE_NAME is
'角色名称';

comment on column SYS_ROLE.ROLE_POS is
'角色岗位';

comment on column SYS_ROLE.REMARK is
'备注';

comment on column SYS_ROLE.CREATE_USER is
'创建人';

comment on column SYS_ROLE.CREATE_DEPT is
'创建部门';

comment on column SYS_ROLE.CREATE_TIME is
'创建时间';

comment on column SYS_ROLE.UPDATE_USER is
'更新人';

comment on column SYS_ROLE.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_ROLE_MODULE                                       */
/*==============================================================*/
create table SYS_ROLE_MODULE 
(
   ROLE_ID              VARCHAR(32)          not null,
   PAGE_ID              VARCHAR(50)          not null,
   ELEMENT_ID           VARCHAR(50)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_ROLE_MODULE primary key (ROLE_ID, PAGE_ID, ELEMENT_ID)
);

comment on table SYS_ROLE_MODULE is
'角色权限管理';

comment on column SYS_ROLE_MODULE.ROLE_ID is
'角色代码';

comment on column SYS_ROLE_MODULE.PAGE_ID is
'画面代码';

comment on column SYS_ROLE_MODULE.ELEMENT_ID is
'要素代码';

comment on column SYS_ROLE_MODULE.CREATE_USER is
'创建人';

comment on column SYS_ROLE_MODULE.CREATE_DEPT is
'创建部门';

comment on column SYS_ROLE_MODULE.CREATE_TIME is
'创建时间';

comment on column SYS_ROLE_MODULE.UPDATE_USER is
'更新人';

comment on column SYS_ROLE_MODULE.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_UPLOAD_FILE_INFO                                  */
/*==============================================================*/
create table SYS_UPLOAD_FILE_INFO 
(
   SEQ_NO               VARCHAR(32)          not null,
   FILE_NAME            VARCHAR(256)         not null,
   PATH                 VARCHAR(200),
   BUSINESS_ID          VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_UPLOAD_FILE_INFO primary key (SEQ_NO)
);

comment on table SYS_UPLOAD_FILE_INFO is
'上传文件信息';

comment on column SYS_UPLOAD_FILE_INFO.SEQ_NO is
'流水号';

comment on column SYS_UPLOAD_FILE_INFO.FILE_NAME is
'文件名';

comment on column SYS_UPLOAD_FILE_INFO.PATH is
'路径';

comment on column SYS_UPLOAD_FILE_INFO.BUSINESS_ID is
'业务代码';

comment on column SYS_UPLOAD_FILE_INFO.CREATE_USER is
'创建人';

comment on column SYS_UPLOAD_FILE_INFO.CREATE_TIME is
'创建时间';

comment on column SYS_UPLOAD_FILE_INFO.UPDATE_USER is
'更新人';

comment on column SYS_UPLOAD_FILE_INFO.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER 
(
   USER_ID              VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   USER_NAME            VARCHAR(20)          not null,
   REAL_NAME            VARCHAR(200)         not null,
   MAIL_ADDR            VARCHAR(50),
   PHONE_NUM            VARCHAR(20),
   PASSWORD             VARCHAR(64)          not null,
   SALT                 CHAR(32)             not null,
   DEPT_CODE            VARCHAR(32),
   SSO_USER_ID          VARCHAR(32),
   MOB_NUM              VARCHAR(20),
   WECHAT_NUM           VARCHAR(20),
   ADMIN_FLAG           VARCHAR(50),
   LAST_PASS_DATE       DATE,
   IS_FROZEN            VARCHAR(50),
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_USER primary key (USER_ID)
);

comment on table SYS_USER is
'用户';

comment on column SYS_USER.USER_ID is
'用户代码';

comment on column SYS_USER.SYSTEM_CODE is
'系统代码';

comment on column SYS_USER.USER_NAME is
'用户名称';

comment on column SYS_USER.REAL_NAME is
'真实姓名';

comment on column SYS_USER.MAIL_ADDR is
'邮件地址';

comment on column SYS_USER.PHONE_NUM is
'电话';

comment on column SYS_USER.PASSWORD is
'密码';

comment on column SYS_USER.SALT is
'密码盐';

comment on column SYS_USER.DEPT_CODE is
'部门代码（有部门是部门，没有部门是机构）';

comment on column SYS_USER.SSO_USER_ID is
'SSO用户名';

comment on column SYS_USER.MOB_NUM is
'手机号';

comment on column SYS_USER.WECHAT_NUM is
'微信号';

comment on column SYS_USER.ADMIN_FLAG is
'管理员标识';

comment on column SYS_USER.LAST_PASS_DATE is
'上次密码修改时间';

comment on column SYS_USER.IS_FROZEN is
'是否冻结';

comment on column SYS_USER.REMARK is
'备注';

comment on column SYS_USER.CREATE_USER is
'创建人';

comment on column SYS_USER.CREATE_DEPT is
'创建部门';

comment on column SYS_USER.CREATE_TIME is
'创建时间';

comment on column SYS_USER.UPDATE_USER is
'更新人';

comment on column SYS_USER.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_USER_HOME_PAGE_CONFIG                             */
/*==============================================================*/
create table SYS_USER_HOME_PAGE_CONFIG 
(
   USER_ID              VARCHAR(32)          not null,
   CONFIG_ID            VARCHAR(32)          not null,
   ORDER_NO             NUMERIC(10)          not null,
   VISIBILITY           VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_USER_HOME_PAGE_CONFIG primary key (USER_ID, CONFIG_ID)
);

comment on table SYS_USER_HOME_PAGE_CONFIG is
'用户主页配置';

comment on column SYS_USER_HOME_PAGE_CONFIG.USER_ID is
'用户ID';

comment on column SYS_USER_HOME_PAGE_CONFIG.CONFIG_ID is
'配置ID';

comment on column SYS_USER_HOME_PAGE_CONFIG.ORDER_NO is
'排列顺序';

comment on column SYS_USER_HOME_PAGE_CONFIG.VISIBILITY is
'是否显示';

comment on column SYS_USER_HOME_PAGE_CONFIG.CREATE_USER is
'创建人';

comment on column SYS_USER_HOME_PAGE_CONFIG.CREATE_DEPT is
'创建部门';

comment on column SYS_USER_HOME_PAGE_CONFIG.CREATE_TIME is
'创建时间';

comment on column SYS_USER_HOME_PAGE_CONFIG.UPDATE_USER is
'更新人';

comment on column SYS_USER_HOME_PAGE_CONFIG.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_USER_ROLE                                         */
/*==============================================================*/
create table SYS_USER_ROLE 
(
   USER_ID              VARCHAR(32)          not null,
   ROLE_ID              VARCHAR(32)          not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_USER_ROLE primary key (ROLE_ID, USER_ID)
);

comment on table SYS_USER_ROLE is
'用户角色';

comment on column SYS_USER_ROLE.USER_ID is
'用户代码';

comment on column SYS_USER_ROLE.ROLE_ID is
'角色代码';

comment on column SYS_USER_ROLE.CREATE_USER is
'创建人';

comment on column SYS_USER_ROLE.CREATE_DEPT is
'创建部门';

comment on column SYS_USER_ROLE.CREATE_TIME is
'创建时间';

comment on column SYS_USER_ROLE.UPDATE_USER is
'更新人';

comment on column SYS_USER_ROLE.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_USER_STYLE                                        */
/*==============================================================*/
create table SYS_USER_STYLE 
(
   USER_STYLE_ID        VARCHAR(32)          not null,
   USER_ID              VARCHAR(32)          not null,
   STYLE_CODE           VARCHAR(50)          not null,
   PAGE_ID              VARCHAR(50),
   ELEMENT_ID           VARCHAR(50),
   USER_STYLE           CLOB,
   ORDER_NO             NUMERIC(10),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_USER_STYLE primary key (USER_STYLE_ID)
);

comment on table SYS_USER_STYLE is
'用户列表样式存储';

comment on column SYS_USER_STYLE.USER_STYLE_ID is
'用户样式存储ID';

comment on column SYS_USER_STYLE.USER_ID is
'用户名称';

comment on column SYS_USER_STYLE.STYLE_CODE is
'样式代码';

comment on column SYS_USER_STYLE.PAGE_ID is
'画面代码';

comment on column SYS_USER_STYLE.ELEMENT_ID is
'列表代码';

comment on column SYS_USER_STYLE.USER_STYLE is
'STYLE';

comment on column SYS_USER_STYLE.ORDER_NO is
'排列顺序';

comment on column SYS_USER_STYLE.CREATE_USER is
'创建人';

comment on column SYS_USER_STYLE.CREATE_DEPT is
'创建部门';

comment on column SYS_USER_STYLE.CREATE_TIME is
'创建时间';

comment on column SYS_USER_STYLE.UPDATE_USER is
'更新人';

comment on column SYS_USER_STYLE.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_WFL_AGENT                                         */
/*==============================================================*/
create table SYS_WFL_AGENT 
(
   USER_NAME            VARCHAR(20)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   AGENT_NAME           VARCHAR(20)          not null,
   START_DATE           DATE                 not null,
   END_DATE             DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_WFL_AGENT primary key (USER_NAME, START_DATE)
);

comment on table SYS_WFL_AGENT is
'业务流程代办人';

comment on column SYS_WFL_AGENT.USER_NAME is
'用户名称';

comment on column SYS_WFL_AGENT.SYSTEM_CODE is
'系统代码';

comment on column SYS_WFL_AGENT.AGENT_NAME is
'代办人用户名称';

comment on column SYS_WFL_AGENT.START_DATE is
'有效开始日';

comment on column SYS_WFL_AGENT.END_DATE is
'有效结束日';

comment on column SYS_WFL_AGENT.CREATE_USER is
'创建人';

comment on column SYS_WFL_AGENT.CREATE_DEPT is
'创建部门';

comment on column SYS_WFL_AGENT.CREATE_TIME is
'创建时间';

comment on column SYS_WFL_AGENT.UPDATE_USER is
'更新人';

comment on column SYS_WFL_AGENT.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_WFL_BUSINESS_LIST                                 */
/*==============================================================*/
create table SYS_WFL_BUSINESS_LIST 
(
   SEQ_NO               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   WORKFLOW_ID          VARCHAR(64),
   PAGE_ID              VARCHAR(50),
   ELEMENT_ID           VARCHAR(50),
   CRT_USER             VARCHAR(20),
   CRT_DATE             DATE,
   CUR_USER             VARCHAR(20),
   CUR_NODE_ID          VARCHAR(255),
   NEXT_USER            VARCHAR(20),
   BUSINESS_TYPE        VARCHAR(50),
   TRADE_ID             VARCHAR(64),
   TITLE                VARCHAR(600)         not null,
   ACTION_TYPE          VARCHAR(50),
   PROCESS_STATE        VARCHAR(50),
   CANCEL_FLG           VARCHAR(50),
   SIGN_FLG             VARCHAR(50),
   COMPLETE_FLG         VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_WFL_BUSINESS_LIST primary key (SEQ_NO)
);

comment on table SYS_WFL_BUSINESS_LIST is
'业务流程';

comment on column SYS_WFL_BUSINESS_LIST.SEQ_NO is
'流程代码';

comment on column SYS_WFL_BUSINESS_LIST.SYSTEM_CODE is
'系统代码';

comment on column SYS_WFL_BUSINESS_LIST.WORKFLOW_ID is
'流程实例代码';

comment on column SYS_WFL_BUSINESS_LIST.PAGE_ID is
'画面代码（菜单对应的入口画面）';

comment on column SYS_WFL_BUSINESS_LIST.ELEMENT_ID is
'要素代码';

comment on column SYS_WFL_BUSINESS_LIST.CRT_USER is
'任务创建人';

comment on column SYS_WFL_BUSINESS_LIST.CRT_DATE is
'任务创建日期';

comment on column SYS_WFL_BUSINESS_LIST.CUR_USER is
'当前步骤处理人';

comment on column SYS_WFL_BUSINESS_LIST.CUR_NODE_ID is
'当前节点代码';

comment on column SYS_WFL_BUSINESS_LIST.NEXT_USER is
'下一步骤处理人';

comment on column SYS_WFL_BUSINESS_LIST.BUSINESS_TYPE is
'业务类型';

comment on column SYS_WFL_BUSINESS_LIST.TRADE_ID is
'业务编号';

comment on column SYS_WFL_BUSINESS_LIST.TITLE is
'流程标题';

comment on column SYS_WFL_BUSINESS_LIST.ACTION_TYPE is
'操作类型';

comment on column SYS_WFL_BUSINESS_LIST.PROCESS_STATE is
'流程状态';

comment on column SYS_WFL_BUSINESS_LIST.CANCEL_FLG is
'是否可作废';

comment on column SYS_WFL_BUSINESS_LIST.SIGN_FLG is
'是否已签收';

comment on column SYS_WFL_BUSINESS_LIST.COMPLETE_FLG is
'是否已完成';

comment on column SYS_WFL_BUSINESS_LIST.CREATE_USER is
'创建人';

comment on column SYS_WFL_BUSINESS_LIST.CREATE_DEPT is
'创建部门';

comment on column SYS_WFL_BUSINESS_LIST.CREATE_TIME is
'创建时间';

comment on column SYS_WFL_BUSINESS_LIST.UPDATE_USER is
'更新人';

comment on column SYS_WFL_BUSINESS_LIST.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_WFL_BUSINESS_MODULE                               */
/*==============================================================*/
create table SYS_WFL_BUSINESS_MODULE 
(
   PAGE_ID              VARCHAR(50)          not null,
   ELEMENT_ID           VARCHAR(50)          not null,
   MODULE_ID            VARCHAR(64),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_WFL_BUSINESS_MODULE primary key (PAGE_ID, ELEMENT_ID)
);

comment on table SYS_WFL_BUSINESS_MODULE is
'业务流程模板';

comment on column SYS_WFL_BUSINESS_MODULE.PAGE_ID is
'画面代码（菜单对应的入口画面）';

comment on column SYS_WFL_BUSINESS_MODULE.ELEMENT_ID is
'要素代码';

comment on column SYS_WFL_BUSINESS_MODULE.MODULE_ID is
'模板代码';

comment on column SYS_WFL_BUSINESS_MODULE.CREATE_USER is
'创建人';

comment on column SYS_WFL_BUSINESS_MODULE.CREATE_DEPT is
'创建部门';

comment on column SYS_WFL_BUSINESS_MODULE.CREATE_TIME is
'创建时间';

comment on column SYS_WFL_BUSINESS_MODULE.UPDATE_USER is
'更新人';

comment on column SYS_WFL_BUSINESS_MODULE.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_WFL_BUSINESS_NODE                                 */
/*==============================================================*/
create table SYS_WFL_BUSINESS_NODE 
(
   SEQ_NO               VARCHAR(32)          not null,
   HIS_NO               NUMERIC(10)          not null,
   WORKFLOW_ID          VARCHAR(64)          not null,
   HI_USER              VARCHAR(2500),
   NODE_ID              VARCHAR(255),
   NODE_NAME            VARCHAR(200),
   RESULT               VARCHAR(64),
   OPINIONS             VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_WFL_BUSINESS_NODE primary key (SEQ_NO, WORKFLOW_ID, HIS_NO)
);

comment on table SYS_WFL_BUSINESS_NODE is
'业务流程节点';

comment on column SYS_WFL_BUSINESS_NODE.SEQ_NO is
'流程代码';

comment on column SYS_WFL_BUSINESS_NODE.HIS_NO is
'履历番号';

comment on column SYS_WFL_BUSINESS_NODE.WORKFLOW_ID is
'流程实例代码';

comment on column SYS_WFL_BUSINESS_NODE.HI_USER is
'处理人';

comment on column SYS_WFL_BUSINESS_NODE.NODE_ID is
'节点代码';

comment on column SYS_WFL_BUSINESS_NODE.NODE_NAME is
'节点名称';

comment on column SYS_WFL_BUSINESS_NODE.RESULT is
'处理结果';

comment on column SYS_WFL_BUSINESS_NODE.OPINIONS is
'处理意见';

comment on column SYS_WFL_BUSINESS_NODE.CREATE_USER is
'创建人';

comment on column SYS_WFL_BUSINESS_NODE.CREATE_DEPT is
'创建部门';

comment on column SYS_WFL_BUSINESS_NODE.CREATE_TIME is
'创建时间';

comment on column SYS_WFL_BUSINESS_NODE.UPDATE_USER is
'更新人';

comment on column SYS_WFL_BUSINESS_NODE.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_WFL_MODULE                                        */
/*==============================================================*/
create table SYS_WFL_MODULE 
(
   SEQ_NO               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   MODULE_ID            VARCHAR(64)          not null,
   NODE_ID              VARCHAR(255),
   VALUE_TYPE           VARCHAR(20),
   VALUE_DATA           VARCHAR(1000),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_WFL_MODULE primary key (SEQ_NO)
);

comment on table SYS_WFL_MODULE is
'流程模板';

comment on column SYS_WFL_MODULE.SEQ_NO is
'流水号';

comment on column SYS_WFL_MODULE.SYSTEM_CODE is
'系统代码';

comment on column SYS_WFL_MODULE.MODULE_ID is
'模板代码';

comment on column SYS_WFL_MODULE.NODE_ID is
'节点代码';

comment on column SYS_WFL_MODULE.VALUE_TYPE is
'数据类型（user:用户、role:角色、condition:条件）';

comment on column SYS_WFL_MODULE.VALUE_DATA is
'数据值';

comment on column SYS_WFL_MODULE.CREATE_USER is
'创建人';

comment on column SYS_WFL_MODULE.CREATE_DEPT is
'创建部门';

comment on column SYS_WFL_MODULE.CREATE_TIME is
'创建时间';

comment on column SYS_WFL_MODULE.UPDATE_USER is
'更新人';

comment on column SYS_WFL_MODULE.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SYS_KET_JOB                                        */
/*==============================================================*/
create table SYS_KET_JOB  ( 
   JOB_ID VARCHAR(32) not null,
   JOB_NAME VARCHAR(200) not null,
   PRE_CONDITION VARCHAR(2000),
   DESCRIPTION VARCHAR(200),
   IS_TIME VARCHAR(50),
   CREATE_USER VARCHAR(20) not null,
   CREATE_DEPT VARCHAR(32),
   CREATE_TIME TIMESTAMP not null,
   UPDATE_USER VARCHAR(20) not null,
   UPDATE_TIME TIMESTAMP not null,
   constraint PK_SYS_KET_JOB primary key (JOB_ID)
);

comment on table SYS_KET_JOB is '作业组';
comment on column SYS_KET_JOB.JOB_ID  is '系统UUID，作业组编号';
comment on column SYS_KET_JOB.JOB_NAME  is '作业组名称';
comment on column SYS_KET_JOB.PRE_CONDITION  is '前置条件';
comment on column SYS_KET_JOB.DESCRIPTION  is '作业组描述';
comment on column SYS_KET_JOB.IS_TIME  is '是否定时 参考数据字典';
comment on column SYS_KET_JOB.CREATE_USER  is '创建人';
comment on column SYS_KET_JOB.CREATE_DEPT  is '创建部门';
comment on column SYS_KET_JOB.CREATE_TIME  is '创建时间';
comment on column SYS_KET_JOB.UPDATE_USER  is '更新人';
comment on column SYS_KET_JOB.UPDATE_TIME  is '更新时间';

/*==============================================================*/
/* Table: SYS_KET_TASK                                        */
/*==============================================================*/
create table SYS_KET_TASK  ( 
   TASK_ID VARCHAR(32) not null,
   TASK_NAME VARCHAR(200) not null,
   PRIORITY NUMERIC(4) not null,
   FILE_ID VARCHAR(256),
   DESCRIPTION VARCHAR(200),
   JOB_ID VARCHAR(32) not null,
   CREATE_USER VARCHAR(20) not null,
   CREATE_DEPT VARCHAR(32),
   CREATE_TIME TIMESTAMP not null,
   UPDATE_USER VARCHAR(20) not null,
   UPDATE_TIME TIMESTAMP not null,
   constraint PK_SYS_KET_TASK primary key (TASK_ID)
);
comment on table SYS_KET_TASK is '作业项';
comment on column SYS_KET_TASK.TASK_ID  is '系统UUID';
comment on column SYS_KET_TASK.TASK_NAME  is '作业名称';
comment on column SYS_KET_TASK.PRIORITY  is '优先级';
comment on column SYS_KET_TASK.FILE_ID  is '文件编号';
comment on column SYS_KET_TASK.DESCRIPTION  is '描述';
comment on column SYS_KET_TASK.JOB_ID  is '作业组编号';
comment on column SYS_KET_TASK.CREATE_USER  is '创建人';
comment on column SYS_KET_TASK.CREATE_DEPT  is '创建部门';
comment on column SYS_KET_TASK.CREATE_TIME  is '创建时间';
comment on column SYS_KET_TASK.UPDATE_USER  is '更新人';
comment on column SYS_KET_TASK.UPDATE_TIME  is '更新时间';

/*==============================================================*/
/* Table: SYS_KET_JOB_CONFIG                                        */
/*==============================================================*/
create table SYS_KET_JOB_CONFIG  ( 
   CONFIG_ID VARCHAR(32) not null,
   JOB_ID VARCHAR(32) not null,
   EXP VARCHAR(50) not null,
   EXP_DESC VARCHAR(200),
   DESCRIPTION VARCHAR(200),
   CREATE_USER VARCHAR(20) not null,
   CREATE_DEPT VARCHAR(32),
   CREATE_TIME TIMESTAMP not null,
   UPDATE_USER VARCHAR(20) not null,
   UPDATE_TIME TIMESTAMP not null,
   constraint PK_SYS_KET_JOB_CONFIG primary key (CONFIG_ID)
);
comment on table SYS_KET_JOB_CONFIG is '定时配置';
comment on column SYS_KET_JOB_CONFIG.CONFIG_ID  is '系统UUID';
comment on column SYS_KET_JOB_CONFIG.JOB_ID  is '作业组编号';
comment on column SYS_KET_JOB_CONFIG.EXP  is '表达式';
comment on column SYS_KET_JOB_CONFIG.EXP_DESC  is '表达式说明';
comment on column SYS_KET_JOB_CONFIG.DESCRIPTION  is '描述';
comment on column SYS_KET_JOB_CONFIG.CREATE_USER  is '创建人';
comment on column SYS_KET_JOB_CONFIG.CREATE_DEPT  is '创建部门';
comment on column SYS_KET_JOB_CONFIG.CREATE_TIME  is '创建时间';
comment on column SYS_KET_JOB_CONFIG.UPDATE_USER  is '更新人';
comment on column SYS_KET_JOB_CONFIG.UPDATE_TIME  is '更新时间';

/*==============================================================*/
/* Table: SYS_KET_JOB_EXECUTE                                        */
/*==============================================================*/
create table SYS_KET_JOB_EXECUTE  ( 
   EXECUTE_ID VARCHAR(32) not null,
   JOB_ID VARCHAR(32) not null,
   RUN_STATUS VARCHAR(50) not null,
   EXECUTE_STIME TIMESTAMP not null,
   EXECUTE_ETIME TIMESTAMP,
   CREATE_USER VARCHAR(20) not null,
   CREATE_DEPT VARCHAR(32),
   CREATE_TIME TIMESTAMP not null,
   UPDATE_USER VARCHAR(20) not null,
   UPDATE_TIME TIMESTAMP not null,
   constraint PK_SYS_KET_JOB_EXECUTE primary key (EXECUTE_ID)
);
comment on table SYS_KET_JOB_EXECUTE is '作业组执行';
comment on column SYS_KET_JOB_EXECUTE.EXECUTE_ID  is '系统UUID';
comment on column SYS_KET_JOB_EXECUTE.JOB_ID  is '作业组编号';
comment on column SYS_KET_JOB_EXECUTE.RUN_STATUS  is '执行状态(参照数据字典)';
comment on column SYS_KET_JOB_EXECUTE.EXECUTE_STIME  is '执行开始时间';
comment on column SYS_KET_JOB_EXECUTE.EXECUTE_ETIME  is '执行结束时间';
comment on column SYS_KET_JOB_EXECUTE.CREATE_USER  is '创建人';
comment on column SYS_KET_JOB_EXECUTE.CREATE_DEPT  is '创建部门';
comment on column SYS_KET_JOB_EXECUTE.CREATE_TIME  is '创建时间';
comment on column SYS_KET_JOB_EXECUTE.UPDATE_USER  is '更新人';
comment on column SYS_KET_JOB_EXECUTE.UPDATE_TIME  is '更新时间';

/*==============================================================*/
/* Table: SYS_KET_TASK_EXECUTE                                        */
/*==============================================================*/
create table SYS_KET_TASK_EXECUTE  ( 
   EXECUTE_ID VARCHAR(32) not null,
   JOB_ID VARCHAR(32) not null,
   TASK_ID VARCHAR(32) not null,
   RUN_STATUS VARCHAR(50) not null,
   EXECUTE_STIME TIMESTAMP not null,
   EXECUTE_ETIME TIMESTAMP,
   CREATE_USER VARCHAR(20) not null,
   CREATE_DEPT VARCHAR(32),
   CREATE_TIME TIMESTAMP not null,
   UPDATE_USER VARCHAR(20) not null,
   UPDATE_TIME TIMESTAMP not null,
   constraint PK_SYS_KET_TASK_EXECUTE primary key (EXECUTE_ID)
);
comment on table SYS_KET_TASK_EXECUTE is '作业项执行';
comment on column SYS_KET_TASK_EXECUTE.EXECUTE_ID  is '系统UUID';
comment on column SYS_KET_TASK_EXECUTE.JOB_ID  is '作业组编号';
comment on column SYS_KET_TASK_EXECUTE.TASK_ID  is '作业编号';
comment on column SYS_KET_TASK_EXECUTE.RUN_STATUS  is '执行状态(参照数据字典)';
comment on column SYS_KET_TASK_EXECUTE.EXECUTE_STIME  is '执行开始时间';
comment on column SYS_KET_TASK_EXECUTE.EXECUTE_ETIME  is '执行结束时间';
comment on column SYS_KET_TASK_EXECUTE.CREATE_USER  is '创建人';
comment on column SYS_KET_TASK_EXECUTE.CREATE_DEPT  is '创建部门';
comment on column SYS_KET_TASK_EXECUTE.CREATE_TIME  is '创建时间';
comment on column SYS_KET_TASK_EXECUTE.UPDATE_USER  is '更新人';
comment on column SYS_KET_TASK_EXECUTE.UPDATE_TIME  is '更新时间';

/*==============================================================*/
/* Table: SYS_KET_EXECUTE_LOG                                        */
/*==============================================================*/
create table SYS_KET_EXECUTE_LOG  ( 
   LOG_ID VARCHAR(32) not null,
   EXECUTE_ID VARCHAR(32) not null,
   FILE_ID VARCHAR(32) not null,
   CREATE_USER VARCHAR(20) not null,
   CREATE_DEPT VARCHAR(32),
   CREATE_TIME TIMESTAMP not null,
   UPDATE_USER VARCHAR(20) not null,
   UPDATE_TIME TIMESTAMP not null,
   constraint PK_SYS_KET_EXECUTE_LOG primary key (LOG_ID)
);
comment on table SYS_KET_EXECUTE_LOG is 'KETTLE执行日志';
comment on column SYS_KET_EXECUTE_LOG.LOG_ID  is '系统UUID';
comment on column SYS_KET_EXECUTE_LOG.EXECUTE_ID  is '执行作业组编号';
comment on column SYS_KET_EXECUTE_LOG.FILE_ID  is '系统外部UUID';
comment on column SYS_KET_EXECUTE_LOG.CREATE_USER  is '创建人';
comment on column SYS_KET_EXECUTE_LOG.CREATE_DEPT  is '创建部门';
comment on column SYS_KET_EXECUTE_LOG.CREATE_TIME  is '创建时间';
comment on column SYS_KET_EXECUTE_LOG.UPDATE_USER  is '更新人';
comment on column SYS_KET_EXECUTE_LOG.UPDATE_TIME  is '更新时间';


CREATE TABLE
    SYS_BATCH_GRP_PRI
    (
        DETAIL_SEQ_NO VARCHAR2(32) NOT NULL,
        GROUP_SEQ_NO VARCHAR2(32) NOT NULL,
        EXECUTE_DATE DATE NOT NULL,
        PRI NUMBER(10),
        CREATE_TIME TIMESTAMP(6) NOT NULL,
        UPDATE_TIME TIMESTAMP(6) NOT NULL,
        CREATE_DEPT VARCHAR2(32),
        CREATE_USER VARCHAR2(20) NOT NULL,
        UPDATE_USER VARCHAR2(20) NOT NULL,
        PRIMARY KEY (DETAIL_SEQ_NO, GROUP_SEQ_NO)
    );
COMMENT ON TABLE SYS_BATCH_GRP_PRI
IS
    '任务组优先级';
COMMENT ON COLUMN SYS_BATCH_GRP_PRI.DETAIL_SEQ_NO
IS
    '批处理任务组执行ID';
COMMENT ON COLUMN SYS_BATCH_GRP_PRI.GROUP_SEQ_NO
IS
    '任务组实例ID';
COMMENT ON COLUMN SYS_BATCH_GRP_PRI.EXECUTE_DATE
IS
    '最大维度的日期（页面选择的账套日或自动批时的账套日）';
COMMENT ON COLUMN SYS_BATCH_GRP_PRI.PRI
IS
    '优先级';
COMMENT ON COLUMN SYS_BATCH_GRP_PRI.CREATE_TIME
IS
    '创建时间';
COMMENT ON COLUMN SYS_BATCH_GRP_PRI.UPDATE_TIME
IS
    '更新时间';
COMMENT ON COLUMN SYS_BATCH_GRP_PRI.CREATE_DEPT
IS
    '创建部门';
COMMENT ON COLUMN SYS_BATCH_GRP_PRI.CREATE_USER
IS
    '创建人';
COMMENT ON COLUMN SYS_BATCH_GRP_PRI.UPDATE_USER
IS
    '更新人';
	
	
CREATE TABLE
    SYS_BATCH_DIMENSION_CONFIG
    (
        SEQ_NO VARCHAR2(32) NOT NULL,
        DIMENSION_NAME VARCHAR2(200),
        PRI NUMBER(10),
        REMARK VARCHAR2(600),
        CREATE_TIME TIMESTAMP(6) NOT NULL,
        UPDATE_TIME TIMESTAMP(6) NOT NULL,
        CREATE_DEPT VARCHAR2(32),
        CREATE_USER VARCHAR2(20) NOT NULL,
        UPDATE_USER VARCHAR2(20) NOT NULL,
        PRIMARY KEY (SEQ_NO)
    );
COMMENT ON TABLE SYS_BATCH_DIMENSION_CONFIG
IS
    '维度设置表';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_CONFIG.SEQ_NO
IS
    '任务组实例ID';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_CONFIG.DIMENSION_NAME
IS
    '维度名';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_CONFIG.PRI
IS
    '优先级';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_CONFIG.REMARK
IS
    '备注';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_CONFIG.CREATE_TIME
IS
    '创建时间';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_CONFIG.UPDATE_TIME
IS
    '更新时间';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_CONFIG.CREATE_DEPT
IS
    '创建部门';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_CONFIG.CREATE_USER
IS
    '创建人';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_CONFIG.UPDATE_USER
IS
    '更新人';
	
CREATE TABLE
    SYS_BATCH_DIMENSION_VALUE
    (
        GROUP_INS_SEQ_NO VARCHAR2(32) NOT NULL,
        DIMENSION_SEQ_NO VARCHAR2(32) NOT NULL,
        DIMENSION_VALUE VARCHAR2(200) NOT NULL,
        CREATE_TIME TIMESTAMP(6) NOT NULL,
        UPDATE_TIME TIMESTAMP(6) NOT NULL,
        CREATE_DEPT VARCHAR2(32),
        CREATE_USER VARCHAR2(20) NOT NULL,
        UPDATE_USER VARCHAR2(20) NOT NULL,
        PRIMARY KEY (GROUP_INS_SEQ_NO, DIMENSION_SEQ_NO)
    );
COMMENT ON TABLE SYS_BATCH_DIMENSION_VALUE
IS
    '维度值表';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_VALUE.GROUP_INS_SEQ_NO
IS
    '任务组实例ID';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_VALUE.DIMENSION_SEQ_NO
IS
    '维度名';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_VALUE.DIMENSION_VALUE
IS
    '纬度值';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_VALUE.CREATE_TIME
IS
    '创建时间';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_VALUE.UPDATE_TIME
IS
    '更新时间';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_VALUE.CREATE_DEPT
IS
    '创建部门';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_VALUE.CREATE_USER
IS
    '创建人';
COMMENT ON COLUMN SYS_BATCH_DIMENSION_VALUE.UPDATE_USER
IS
    '更新人';
	
CREATE TABLE
    SYS_BATCH_JOB_INST
    (
        BATCH_SEQ_NO VARCHAR2(32) NOT NULL,
        TASK_ID VARCHAR2(32) NOT NULL,
        CREATE_USER VARCHAR2(20) NOT NULL,
        CREATE_DEPT VARCHAR2(32),
        CREATE_TIME TIMESTAMP(6) NOT NULL,
        UPDATE_USER VARCHAR2(20) NOT NULL,
        UPDATE_TIME TIMESTAMP(6) NOT NULL,
        CONSTRAINT PK_SYS_BATCH_JOB_INST PRIMARY KEY (BATCH_SEQ_NO, TASK_ID)
    );
COMMENT ON TABLE SYS_BATCH_JOB_INST
IS
    '批处理任务组实例与任务关联';
COMMENT ON COLUMN SYS_BATCH_JOB_INST.BATCH_SEQ_NO
IS
    '批处理任务组';
COMMENT ON COLUMN SYS_BATCH_JOB_INST.TASK_ID
IS
    '批处理任务';
COMMENT ON COLUMN SYS_BATCH_JOB_INST.CREATE_USER
IS
    '创建人';
COMMENT ON COLUMN SYS_BATCH_JOB_INST.CREATE_DEPT
IS
    '创建部门';
COMMENT ON COLUMN SYS_BATCH_JOB_INST.CREATE_TIME
IS
    '创建时间';
COMMENT ON COLUMN SYS_BATCH_JOB_INST.UPDATE_USER
IS
    '更新人';
COMMENT ON COLUMN SYS_BATCH_JOB_INST.UPDATE_TIME
IS
    '更新时间';
	
DROP SEQUENCE WFL_SEQUENCE;
CREATE SEQUENCE WFL_SEQUENCE INCREMENT BY 1 START WITH 1 MAXVALUE 100000000 MINVALUE 1 NOCYCLE
CACHE 20 NOORDER;