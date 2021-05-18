drop table SPPI_TEST cascade constraints;
drop table SPPI_TEST_QA cascade constraints;

/*==============================================================*/
/* Table: SPPI_TEST                                             */
/*==============================================================*/
create table SPPI_TEST 
(
   SPPI_TEST_ID         VARCHAR2(32)         not null,
   FINPROD_ID           VARCHAR2(50)         not null,
   SPPI_TMP_TYPE        VARCHAR2(32)         not null,
   SPPI_TMP_NO          VARCHAR2(32)         not null,
   TEST_DATE            DATE                 not null,
   TEST_STATUS          CHAR(1)              default '0' not null,
   DEBT_TOOL_DESC       VARCHAR2(1024),
   DEBT_LAYER_DESC      VARCHAR2(1024),
   INVEST_TYPE_DESC     VARCHAR2(200),
   MANAGE_TYPE_DESC     VARCHAR2(200),
   REMARK               VARCHAR(1000),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SPPI_TEST primary key (SPPI_TEST_ID)
);

comment on table SPPI_TEST is
'SPPI测试记录主表';

comment on column SPPI_TEST.SPPI_TEST_ID is
'SPPI测试ID';

comment on column SPPI_TEST.FINPROD_ID is
'金融产品代码（标的代码）';

comment on column SPPI_TEST.SPPI_TMP_TYPE is
'SPPI测试模板类型：
01：债券类资产SPPI测试模板
02：非标准化债权资产SPPI测试模板
根据PINAN_ASSET_TYPE（TYPE_8）值区分：
债券类资产(TYPE_8=F01) --> 01
项目类资产(TYPE_8=F06)、计划类利率型资产(TYPE_8=F03) --> 02';

comment on column SPPI_TEST.SPPI_TMP_NO is
'测试模板编号
01：普华永道(PwC)
02：德勤(DTT)
03：毕马威(KPMG)
';

comment on column SPPI_TEST.TEST_DATE is
'测算日期，默认采用平台日';

comment on column SPPI_TEST.TEST_STATUS is
'SPPI测试状态：0未测试、1测试中、2测试通过、3测试不通过';

comment on column SPPI_TEST.DEBT_TOOL_DESC is
'债务工具介绍';

comment on column SPPI_TEST.DEBT_LAYER_DESC is
'债务分层情况';

comment on column SPPI_TEST.INVEST_TYPE_DESC is
'投资类型';

comment on column SPPI_TEST.MANAGE_TYPE_DESC is
'交易对手名称及其对该资产的管理方式';

comment on column SPPI_TEST.REMARK is
'备注';

comment on column SPPI_TEST.CREATE_USER is
'创建人';

comment on column SPPI_TEST.CREATE_DEPT is
'创建部门';

comment on column SPPI_TEST.CREATE_TIME is
'创建时间';

comment on column SPPI_TEST.UPDATE_USER is
'更新人';

comment on column SPPI_TEST.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: SPPI_TEST_QA                                          */
/*==============================================================*/
create table SPPI_TEST_QA 
(
   SPPI_TEST_ID         VARCHAR2(32)         not null,
   FINPROD_ID           VARCHAR2(50)         not null,
   QUESTION_NO          VARCHAR2(50)         not null,
   ANSWER_TYPE          CHAR(2),
   ANSWER_VALUE         VARCHAR2(400),
   REMARK               VARCHAR(1000),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_SPPI_TEST_QA primary key (SPPI_TEST_ID, FINPROD_ID, QUESTION_NO)
);

comment on table SPPI_TEST_QA is
'SPPI测试问题记录';

comment on column SPPI_TEST_QA.SPPI_TEST_ID is
'SPPI测试ID';

comment on column SPPI_TEST_QA.FINPROD_ID is
'金融产品代码（标的代码）';

comment on column SPPI_TEST_QA.QUESTION_NO is
'问题代码，对应模板界面HTML上的FORM项的name';

comment on column SPPI_TEST_QA.ANSWER_TYPE is
'值类型，01：下拉框，02：文本框，03：radioBox';

comment on column SPPI_TEST_QA.ANSWER_VALUE is
'问题值，若为下拉框值，则为编码值；若为文本框值，则为汉字内容';

comment on column SPPI_TEST_QA.REMARK is
'备注，可以填问题描述';

comment on column SPPI_TEST_QA.CREATE_USER is
'创建人';

comment on column SPPI_TEST_QA.CREATE_DEPT is
'创建部门';

comment on column SPPI_TEST_QA.CREATE_TIME is
'创建时间';

comment on column SPPI_TEST_QA.UPDATE_USER is
'更新人';

comment on column SPPI_TEST_QA.UPDATE_TIME is
'更新时间';

-- Add/modify columns 
alter table FIN_PRODUCT add SPPI_TEST_STATUS CHAR(1) default '0';
-- Add comments to the columns 
comment on column FIN_PRODUCT.SPPI_TEST_STATUS
  is 'SPPI测试状态：0未测试、1测试中、2测试通过、3测试不通过';
  
  -- Add/modify columns 
alter table SPPI_TEST add hide_ids VARCHAR2(2000);
-- Add comments to the columns 
comment on column SPPI_TEST.hide_ids
  is '隐藏的HTML元素ID，多个用逗号隔开';
  
drop table SPPI_IMPORT_DATA ;


create table SPPI_IMPORT_DATA
(
  BOND_ID        VARCHAR2(32) not null,
  BOND_ABBR      VARCHAR2(50),
  IS_SPPI_PASS      VARCHAR2(200),
  REASON             VARCHAR2(200),
  BID_PRICE_CON       VARCHAR2(200),
  COST_VALUE         VARCHAR2(50),
  OTHER_CONDITION		VARCHAR2(200),
  IMPORT_DATE 			TIMESTAMP(6) not null,
  create_user         VARCHAR2(20) not null,
  create_dept         VARCHAR2(32),
  create_time         TIMESTAMP(6) not null,
  update_user         VARCHAR2(20) not null,
  update_time         TIMESTAMP(6) not null,
  constraint SPPI_IMPORT_DATA_PK primary key(BOND_ID)
);
-- Add comments to the table 
comment on table SPPI_IMPORT_DATA
  is 'SPPI测试导入数据';
-- Add comments to the columns 
comment on column SPPI_IMPORT_DATA.BOND_ID
  is '债券代码';
comment on column SPPI_IMPORT_DATA.BOND_ABBR
  is '债券简称';
comment on column SPPI_IMPORT_DATA.IS_SPPI_PASS
  is '是否通过SPPI测试';
comment on column SPPI_IMPORT_DATA.REASON
  is '未通过原因';
comment on column SPPI_IMPORT_DATA.BID_PRICE_CON
  is '使权不重大的购入价条件';
comment on column SPPI_IMPORT_DATA.COST_VALUE
  is '权的价值';
comment on column SPPI_IMPORT_DATA.create_user
  is '创建人';
comment on column SPPI_IMPORT_DATA.create_dept
  is '创建部门';
comment on column SPPI_IMPORT_DATA.create_time
  is '创建时间';
comment on column SPPI_IMPORT_DATA.update_user
  is '更新人';
comment on column SPPI_IMPORT_DATA.update_time
  is '更新时间';
comment on column SPPI_IMPORT_DATA.OTHER_CONDITION
  is '其他条件';
comment on column SPPI_IMPORT_DATA.IMPORT_DATE
  is '导入时间';