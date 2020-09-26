/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/7/15 星期日 下午 6:31:18                     */
/*==============================================================*/
drop table SYS_DATE cascade constraints;
drop table COM_CUST_REP cascade constraints;
drop table COM_CUST_REP_PANEL cascade constraints;
drop table COM_CUST_REP_PANEL_LINK cascade constraints;
drop table COM_CUST_REP_PANEL_PARA cascade constraints;
drop table COM_EXCEL_IMP_RULE cascade constraints;
drop table COM_EXCEL_IMP_RULE_DTL cascade constraints;
drop table COM_EXCEL_IMP_TARGET cascade constraints;
drop table COM_EXCEL_RULE_CUSTOM_SQL cascade constraints;
drop table COM_EXCEL_RULE_DATA cascade constraints;
drop table COM_EXCEL_RULE_DATA_DETAIL cascade constraints;
drop table COM_EXCEL_RULE_MASTER_DATA cascade constraints;
/*==============================================================*/
/* Table: SYS_DATE                                              */
/*==============================================================*/
create table SYS_DATE 
(
   CUR_DATE             DATE                 not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SYS_DATE primary key (CUR_DATE, SYSTEM_CODE)
);
comment on table SYS_DATE is
'当前系统时间';
comment on column SYS_DATE.CUR_DATE is
'当前系统时间';
comment on column SYS_DATE.SYSTEM_CODE is
'系统代码（默认是ALL）';
comment on column SYS_DATE.CREATE_USER is
'创建人';
comment on column SYS_DATE.CREATE_DEPT is
'创建部门';
comment on column SYS_DATE.CREATE_TIME is
'创建时间';
comment on column SYS_DATE.UPDATE_USER is
'更新人';
comment on column SYS_DATE.UPDATE_TIME is
'更新时间';
/*==============================================================*/
/* Table: COM_CUST_REP                                          */
/*==============================================================*/
create table COM_CUST_REP 
(
   REPORT_ID            VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   REPORT_NAME          VARCHAR(300)         not null,
   INPUT_SQL            CLOB                 not null,
   SHOW_PATTERN         VARCHAR(50)          not null,
   DASHBOARD_LOWER_LIMIT NUMERIC(30, 14),
   DASHBOARD_UPPER_LIMIT NUMERIC(30, 14),
   DASHBOARD_WARN       NUMERIC(30, 14),
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_COM_CUST_REP primary key (REPORT_ID)
);
comment on table COM_CUST_REP is
'报表定义';
comment on column COM_CUST_REP.REPORT_ID is
'报表代码';
comment on column COM_CUST_REP.SYSTEM_CODE is
'系统代码';
comment on column COM_CUST_REP.REPORT_NAME is
'报表名称';
comment on column COM_CUST_REP.INPUT_SQL is
'SQL';
comment on column COM_CUST_REP.SHOW_PATTERN is
'展示方式';
comment on column COM_CUST_REP.DASHBOARD_LOWER_LIMIT is
'仪表盘下限值';
comment on column COM_CUST_REP.DASHBOARD_UPPER_LIMIT is
'仪表盘上限值';
comment on column COM_CUST_REP.DASHBOARD_WARN is
'仪表盘预警值';
comment on column COM_CUST_REP.REMARK is
'备注';
comment on column COM_CUST_REP.CREATE_USER is
'创建人';
comment on column COM_CUST_REP.CREATE_TIME is
'创建时间';
comment on column COM_CUST_REP.UPDATE_USER is
'更新人';
comment on column COM_CUST_REP.UPDATE_TIME is
'更新时间';
/*==============================================================*/
/* Table: COM_CUST_REP_PANEL                                    */
/*==============================================================*/
create table COM_CUST_REP_PANEL 
(
   PANEL_ID             VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   PANEL_NAME           VARCHAR(300)         not null,
   PANEL_PATTERN        VARCHAR(50)          not null,
   REMARK               VARCHAR(600),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_COM_CUST_REP_PANEL primary key (PANEL_ID)
);
comment on table COM_CUST_REP_PANEL is
'报表面板';
comment on column COM_CUST_REP_PANEL.PANEL_ID is
'面板代码';
comment on column COM_CUST_REP_PANEL.SYSTEM_CODE is
'系统代码';
comment on column COM_CUST_REP_PANEL.PANEL_NAME is
'面板名称';
comment on column COM_CUST_REP_PANEL.PANEL_PATTERN is
'面板展示方式';
comment on column COM_CUST_REP_PANEL.REMARK is
'备注';
comment on column COM_CUST_REP_PANEL.CREATE_USER is
'创建人';
comment on column COM_CUST_REP_PANEL.CREATE_TIME is
'创建时间';
comment on column COM_CUST_REP_PANEL.UPDATE_USER is
'更新人';
comment on column COM_CUST_REP_PANEL.UPDATE_TIME is
'更新时间';
/*==============================================================*/
/* Table: COM_CUST_REP_PANEL_LINK                               */
/*==============================================================*/
create table COM_CUST_REP_PANEL_LINK 
(
   PANEL_ID             VARCHAR(32)          not null,
   REPORT_ID            VARCHAR(32)          not null,
   REPORT_POSITION      DECIMAL(2, 0)        not null,
   STATUS               VARCHAR(50)          not null,
   constraint PK_COM_CUST_REP_PANEL_LINK primary key (PANEL_ID, REPORT_ID)
);
comment on table COM_CUST_REP_PANEL_LINK is
'报表面板报表关联';
comment on column COM_CUST_REP_PANEL_LINK.PANEL_ID is
'面板代码';
comment on column COM_CUST_REP_PANEL_LINK.REPORT_ID is
'报表代码';
comment on column COM_CUST_REP_PANEL_LINK.REPORT_POSITION is
'报表位置';
comment on column COM_CUST_REP_PANEL_LINK.STATUS is
'数据状态';
/*==============================================================*/
/* Table: COM_CUST_REP_PANEL_PARA                               */
/*==============================================================*/
create table COM_CUST_REP_PANEL_PARA 
(
   USER_ID              VARCHAR(32)          not null,
   PANEL_ID             VARCHAR(32)          not null,
   REPORT_ID            VARCHAR(32)          not null,
   PARA_CODE            VARCHAR(100)         not null,
   PARA_VALUE           VARCHAR(100)         not null,
   constraint PK_COM_CUST_REP_PANEL_PARA primary key (PANEL_ID, REPORT_ID, USER_ID, PARA_CODE)
);
comment on table COM_CUST_REP_PANEL_PARA is
'报表面板参数';
comment on column COM_CUST_REP_PANEL_PARA.USER_ID is
'用户代码';
comment on column COM_CUST_REP_PANEL_PARA.PANEL_ID is
'面板代码';
comment on column COM_CUST_REP_PANEL_PARA.REPORT_ID is
'报表代码';
comment on column COM_CUST_REP_PANEL_PARA.PARA_CODE is
'参数名称';
comment on column COM_CUST_REP_PANEL_PARA.PARA_VALUE is
'参数值';
/*==============================================================*/
/* Table: COM_EXCEL_IMP_RULE                                    */
/*==============================================================*/
create table COM_EXCEL_IMP_RULE 
(
   RULE_ID              VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   RULE_NAME            VARCHAR(200)         not null,
   TARGET_TABLE         VARCHAR(200)         not null,
   FILE_PREFIX          VARCHAR(200),
   SHEET_PREFIX         VARCHAR(200),
   ORDER_NO             VARCHAR(10),
   FILE_FINGERPRINT     VARCHAR(32),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_COM_EXCEL_IMP_RULE primary key (RULE_ID)
);
comment on table COM_EXCEL_IMP_RULE is
'Excel导入规则';
comment on column COM_EXCEL_IMP_RULE.RULE_ID is
'规则代码';
comment on column COM_EXCEL_IMP_RULE.SYSTEM_CODE is
'系统代码';
comment on column COM_EXCEL_IMP_RULE.RULE_NAME is
'规则名称';
comment on column COM_EXCEL_IMP_RULE.TARGET_TABLE is
'目标表名';
comment on column COM_EXCEL_IMP_RULE.FILE_PREFIX is
'匹配文件名';
comment on column COM_EXCEL_IMP_RULE.SHEET_PREFIX is
'匹配SHEET名';
comment on column COM_EXCEL_IMP_RULE.ORDER_NO is
'规则排列顺序';
comment on column COM_EXCEL_IMP_RULE.FILE_FINGERPRINT is
'文件指纹';
comment on column COM_EXCEL_IMP_RULE.CREATE_USER is
'创建人';
comment on column COM_EXCEL_IMP_RULE.CREATE_TIME is
'创建时间';
comment on column COM_EXCEL_IMP_RULE.UPDATE_USER is
'更新人';
comment on column COM_EXCEL_IMP_RULE.UPDATE_TIME is
'更新时间';
/*==============================================================*/
/* Table: COM_EXCEL_IMP_RULE_DTL                                */
/*==============================================================*/
create table COM_EXCEL_IMP_RULE_DTL 
(
   SEQ_NO               VARCHAR(32)          not null,
   RULE_ID              VARCHAR(32)          not null,
   COLUMN_NO            VARCHAR(10)          not null,
   COLUMN_NAME          VARCHAR(200),
   TARGET_COLUMN        VARCHAR(200)         not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_COM_EXCEL_IMP_RULE_DTL primary key (SEQ_NO)
);
comment on table COM_EXCEL_IMP_RULE_DTL is
'Excel导入规则详情';
comment on column COM_EXCEL_IMP_RULE_DTL.SEQ_NO is
'流水号';
comment on column COM_EXCEL_IMP_RULE_DTL.RULE_ID is
'规则代码';
comment on column COM_EXCEL_IMP_RULE_DTL.COLUMN_NO is
'EXCEL列序号';
comment on column COM_EXCEL_IMP_RULE_DTL.COLUMN_NAME is
'EXCEL列名';
comment on column COM_EXCEL_IMP_RULE_DTL.TARGET_COLUMN is
'目标字段';
comment on column COM_EXCEL_IMP_RULE_DTL.CREATE_USER is
'创建人';
comment on column COM_EXCEL_IMP_RULE_DTL.CREATE_TIME is
'创建时间';
comment on column COM_EXCEL_IMP_RULE_DTL.UPDATE_USER is
'更新人';
comment on column COM_EXCEL_IMP_RULE_DTL.UPDATE_TIME is
'更新时间';
/*==============================================================*/
/* Table: COM_EXCEL_IMP_TARGET                                  */
/*==============================================================*/
create table COM_EXCEL_IMP_TARGET 
(
   SEQ_NO               VARCHAR(32)          not null,
   SYSTEM_CODE          VARCHAR(50)          default 'ALL' not null,
   TARGET_TABLE         VARCHAR(256)         not null,
   TARGET_TABLE_NAME    VARCHAR(256)         not null,
   IMP_HANDLE_SERVICE_NAME VARCHAR(256),
   IMP_HANDLE_DAO_NAME VARCHAR(256),
   IMP_HANDLE_ENTITY_NAME VARCHAR(256),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_COM_EXCEL_IMP_TARGET primary key (SEQ_NO)
);
comment on table COM_EXCEL_IMP_TARGET is
'Excel导入目标表管理';
comment on column COM_EXCEL_IMP_TARGET.SEQ_NO is
'流水号';
comment on column COM_EXCEL_IMP_TARGET.SYSTEM_CODE is
'系统代码';
comment on column COM_EXCEL_IMP_TARGET.TARGET_TABLE is
'目标表物理名';
comment on column COM_EXCEL_IMP_TARGET.TARGET_TABLE_NAME is
'目标表逻辑名';
comment on column COM_EXCEL_IMP_TARGET.IMP_HANDLE_SERVICE_NAME is
'目标表导入处理service名';
comment on column COM_EXCEL_IMP_TARGET.IMP_HANDLE_DAO_NAME is
'目标表导入处理Dao名';
comment on column COM_EXCEL_IMP_TARGET.IMP_HANDLE_ENTITY_NAME is
'目标表导入处理Entity名';
comment on column COM_EXCEL_IMP_TARGET.CREATE_USER is
'创建人';
comment on column COM_EXCEL_IMP_TARGET.CREATE_TIME is
'创建时间';
comment on column COM_EXCEL_IMP_TARGET.UPDATE_USER is
'更新人';
comment on column COM_EXCEL_IMP_TARGET.UPDATE_TIME is
'更新时间';
create table COM_EXCEL_RULE_CUSTOM_SQL  ( 
   RULE_ID VARCHAR(32) not null,
   SYSTEM_CODE VARCHAR(50) not null,
   TARGET_COLUMN VARCHAR(200) not null,
   CUSTOM_SQL CLOB not null,
   CREATE_USER VARCHAR(20) not null,
   CREATE_DEPT VARCHAR(32),
   CREATE_TIME TIMESTAMP not null,
   UPDATE_USER VARCHAR(20) not null,
   UPDATE_TIME TIMESTAMP not null,
   primary key (RULE_ID,TARGET_COLUMN)
);
comment on table COM_EXCEL_RULE_CUSTOM_SQL is 'Excel导入自定义SQL规则';
comment on column COM_EXCEL_RULE_CUSTOM_SQL.RULE_ID  is '规则代码';
comment on column COM_EXCEL_RULE_CUSTOM_SQL.SYSTEM_CODE  is '系统代码 参考数据字典';
comment on column COM_EXCEL_RULE_CUSTOM_SQL.TARGET_COLUMN  is '目标字段';
comment on column COM_EXCEL_RULE_CUSTOM_SQL.CUSTOM_SQL  is '自定义SQL';
comment on column COM_EXCEL_RULE_CUSTOM_SQL.CREATE_USER  is '创建人';
comment on column COM_EXCEL_RULE_CUSTOM_SQL.CREATE_DEPT  is '创建部门';
comment on column COM_EXCEL_RULE_CUSTOM_SQL.CREATE_TIME  is '创建时间';
comment on column COM_EXCEL_RULE_CUSTOM_SQL.UPDATE_USER  is '更新人';
comment on column COM_EXCEL_RULE_CUSTOM_SQL.UPDATE_TIME  is '更新时间';
create table COM_EXCEL_RULE_DATA  ( 
   RULE_ID VARCHAR(32) not null,
   SYSTEM_CODE VARCHAR(50) not null,
   COLUMN_NO VARCHAR(10) not null,
   COLUMN_NAME VARCHAR(200),
   TARGET_COLUMN VARCHAR(200) not null,
   RULE_DETAIL_ID VARCHAR(32) not null,
   CREATE_USER VARCHAR(20) not null,
   CREATE_DEPT VARCHAR(32),
   CREATE_TIME TIMESTAMP not null,
   UPDATE_USER VARCHAR(20) not null,
   UPDATE_TIME TIMESTAMP not null,
   primary key (RULE_ID,COLUMN_NO,TARGET_COLUMN)
);
comment on table COM_EXCEL_RULE_DATA is 'Excel导入数据转化规则';
comment on column COM_EXCEL_RULE_DATA.RULE_ID  is '规则代码';
comment on column COM_EXCEL_RULE_DATA.SYSTEM_CODE  is '系统代码 参考数据字典';
comment on column COM_EXCEL_RULE_DATA.COLUMN_NO  is '列序号';
comment on column COM_EXCEL_RULE_DATA.COLUMN_NAME  is '列名称';
comment on column COM_EXCEL_RULE_DATA.TARGET_COLUMN  is '目标字段';
comment on column COM_EXCEL_RULE_DATA.RULE_DETAIL_ID  is '规则明细代码';
comment on column COM_EXCEL_RULE_DATA.CREATE_USER  is '创建人';
comment on column COM_EXCEL_RULE_DATA.CREATE_DEPT  is '创建部门';
comment on column COM_EXCEL_RULE_DATA.CREATE_TIME  is '创建时间';
comment on column COM_EXCEL_RULE_DATA.UPDATE_USER  is '更新人';
comment on column COM_EXCEL_RULE_DATA.UPDATE_TIME  is '更新时间';
create table COM_EXCEL_RULE_DATA_DETAIL  ( 
   RULE_DETAIL_ID VARCHAR(32) not null,
   SYSTEM_CODE VARCHAR(50) not null,
   COLUMN_VALUE VARCHAR(600) not null,
   TARGET_VALUE VARCHAR(600) not null,
   CREATE_USER VARCHAR(20) not null,
   CREATE_DEPT VARCHAR(32),
   CREATE_TIME TIMESTAMP not null,
   UPDATE_USER VARCHAR(20) not null,
   UPDATE_TIME TIMESTAMP not null,
   primary key (RULE_DETAIL_ID,COLUMN_VALUE)
);
comment on table COM_EXCEL_RULE_DATA_DETAIL is 'Excel导入数据转化规则明细';
comment on column COM_EXCEL_RULE_DATA_DETAIL.RULE_DETAIL_ID  is '规则明细代码';
comment on column COM_EXCEL_RULE_DATA_DETAIL.SYSTEM_CODE  is '系统代码 参考数据字典';
comment on column COM_EXCEL_RULE_DATA_DETAIL.COLUMN_VALUE  is '源值';
comment on column COM_EXCEL_RULE_DATA_DETAIL.TARGET_VALUE  is '目标值';
comment on column COM_EXCEL_RULE_DATA_DETAIL.CREATE_USER  is '创建人';
comment on column COM_EXCEL_RULE_DATA_DETAIL.CREATE_DEPT  is '创建部门';
comment on column COM_EXCEL_RULE_DATA_DETAIL.CREATE_TIME  is '创建时间';
comment on column COM_EXCEL_RULE_DATA_DETAIL.UPDATE_USER  is '更新人';
comment on column COM_EXCEL_RULE_DATA_DETAIL.UPDATE_TIME  is '更新时间';
create table COM_EXCEL_RULE_MASTER_DATA  ( 
   RULE_ID VARCHAR(32) not null,
   SYSTEM_CODE VARCHAR(50) not null,
   COLUMN_NO VARCHAR(10) not null,
   COLUMN_NAME VARCHAR(200),
   TARGET_COLUMN VARCHAR(200) not null,
   MASTER_DATA_TABLE VARCHAR(256) not null,
   MASTER_DATA_ID_COLUMN VARCHAR(256) not null,
   MASTER_DATA_NAME_COLUMN VARCHAR(256) not null,
   CREATE_USER VARCHAR(20) not null,
   CREATE_DEPT VARCHAR(32),
   CREATE_TIME TIMESTAMP not null,
   UPDATE_USER VARCHAR(20) not null,
   UPDATE_TIME TIMESTAMP not null,
   primary key (RULE_ID,COLUMN_NO,TARGET_COLUMN)
);
comment on table COM_EXCEL_RULE_MASTER_DATA is 'Excel导入基础数据规则';
comment on column COM_EXCEL_RULE_MASTER_DATA.RULE_ID  is '规则代码';
comment on column COM_EXCEL_RULE_MASTER_DATA.SYSTEM_CODE  is '系统代码 参考数据字典';
comment on column COM_EXCEL_RULE_MASTER_DATA.COLUMN_NO  is '列序号';
comment on column COM_EXCEL_RULE_MASTER_DATA.COLUMN_NAME  is '列名称';
comment on column COM_EXCEL_RULE_MASTER_DATA.TARGET_COLUMN  is '目标字段';
comment on column COM_EXCEL_RULE_MASTER_DATA.MASTER_DATA_TABLE  is '基础数据表名';
comment on column COM_EXCEL_RULE_MASTER_DATA.MASTER_DATA_ID_COLUMN  is '基础数据表ID项目物理名';
comment on column COM_EXCEL_RULE_MASTER_DATA.MASTER_DATA_NAME_COLUMN  is '基础数据表NAME项目物理名';
comment on column COM_EXCEL_RULE_MASTER_DATA.CREATE_USER  is '创建人';
comment on column COM_EXCEL_RULE_MASTER_DATA.CREATE_DEPT  is '创建部门';
comment on column COM_EXCEL_RULE_MASTER_DATA.CREATE_TIME  is '创建时间';
comment on column COM_EXCEL_RULE_MASTER_DATA.UPDATE_USER  is '更新人';
comment on column COM_EXCEL_RULE_MASTER_DATA.UPDATE_TIME  is '更新时间';