-- 资产大类评级配置表
create table MOD_ASSET_GRADE_CONFIG
(
  seqno             VARCHAR2(50) not null,
  asset_type        VARCHAR2(50),
  asset_detail_type VARCHAR2(50),
  grade_org_id      VARCHAR2(50),
  o_type            VARCHAR2(50),
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
);
-- Add comments to the table
comment on table MOD_ASSET_GRADE_CONFIG
  is '资产大类评级配置';
-- Add comments to the columns
comment on column MOD_ASSET_GRADE_CONFIG.seqno
  is '流水号';
comment on column MOD_ASSET_GRADE_CONFIG.asset_type
  is '资产类型(证券品种_模型)';
comment on column MOD_ASSET_GRADE_CONFIG.asset_detail_type
  is '资产细分类(数据字典 证券细分类_模型)';
comment on column MOD_ASSET_GRADE_CONFIG.grade_org_id
  is '评级机构（数据字典 评级机构）';
comment on column MOD_ASSET_GRADE_CONFIG.o_type
  is '评级类型（数据字典 评级类型_模型）';
comment on column MOD_ASSET_GRADE_CONFIG.create_user
  is '创建人';
comment on column MOD_ASSET_GRADE_CONFIG.create_dept
  is '创建部门';
comment on column MOD_ASSET_GRADE_CONFIG.create_time
  is '创建时间';
comment on column MOD_ASSET_GRADE_CONFIG.update_user
  is '更新人';
comment on column MOD_ASSET_GRADE_CONFIG.update_time
  is '更新时间';

--估值模型曲线配置表
drop table MOD_CURVE_CONFIG;
create table MOD_CURVE_CONFIG
(
   SEQNO                VARCHAR2(32)         not null,
   SEC_TYPE_MODEL       VARCHAR2(50)         not null,
   SEC_DETAIL_TYPE_MODEL VARCHAR2(50)         not null,
   FINPROD_ID           VARCHAR2(50),
   FINPROD_NAME         VARCHAR2(50),
   CURVE_GROUP_CODE     VARCHAR2(50),
   CURVE_TYPE           VARCHAR2(50),
   ISSMOOTH             VARCHAR2(50),
   SMOOTHSECTION        VARCHAR2(20),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null
);
comment on table MOD_CURVE_CONFIG is
'估值模型曲线配置';
comment on column MOD_CURVE_CONFIG.SEQNO is
'流水号';
comment on column MOD_CURVE_CONFIG.SEC_TYPE_MODEL is
'证券品种（数据字典.证券品种_模型）';
comment on column MOD_CURVE_CONFIG.SEC_DETAIL_TYPE_MODEL is
'证券细分类（数据字典.证券细分类_模型）';
comment on column MOD_CURVE_CONFIG.FINPROD_ID is
'金融产品代码，存储上游系统内部代码，比如金融产品代码';
comment on column MOD_CURVE_CONFIG.FINPROD_NAME is
'金融产品名称';
comment on column MOD_CURVE_CONFIG.CURVE_GROUP_CODE is
'曲线族代码（数据字典.曲线族）';
comment on column MOD_CURVE_CONFIG.CURVE_TYPE is
'曲线类型（数据字典.曲线类型）';
comment on column MOD_CURVE_CONFIG.ISSMOOTH is
'是否平滑曲线(数据字典.是否）';
comment on column MOD_CURVE_CONFIG.SMOOTHSECTION is
'平滑区间(数据字典.平滑区间）';
comment on column MOD_CURVE_CONFIG.CREATE_USER is
'创建人';
comment on column MOD_CURVE_CONFIG.CREATE_DEPT is
'创建部门';
comment on column MOD_CURVE_CONFIG.CREATE_TIME is
'创建时间';
comment on column MOD_CURVE_CONFIG.UPDATE_USER is
'更新人';
comment on column MOD_CURVE_CONFIG.UPDATE_TIME is
'更新时间';

-- 曲线族-曲线映射表
drop table MOD_CURVE_MAPPING ;
create table MOD_CURVE_MAPPING
(
   SEQ_NO               VARCHAR2(50)         not null,
   CURVE_GROUP_CODE     CHAR(10),
   CURVE_GROUP_NAME     VARCHAR2(50),
   CURVE_CODE           VARCHAR2(50),
   CURVE_TYPE           CHAR(10),
   CURVE_NAME           VARCHAR2(50),
   GRADE_RESULT         VARCHAR2(50),
   GRADE_RESULT_NAME    VARCHAR2(50),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_CURVE_MAPPING primary key (SEQ_NO)
);

comment on table MOD_CURVE_MAPPING is
'曲线族-曲线映射表';
comment on column MOD_CURVE_MAPPING.SEQ_NO is
'流水号';
comment on column MOD_CURVE_MAPPING.CURVE_GROUP_CODE is
'曲线族代码';
comment on column MOD_CURVE_MAPPING.CURVE_GROUP_NAME is
'曲线组名称，企业债、国债收益率曲线';
comment on column MOD_CURVE_MAPPING.CURVE_CODE is
'曲线代码';
comment on column MOD_CURVE_MAPPING.CURVE_TYPE is
'曲线类型';
comment on column MOD_CURVE_MAPPING.CURVE_NAME is
'曲线名称';
comment on column MOD_CURVE_MAPPING.GRADE_RESULT is
'评级结果';
comment on column MOD_CURVE_MAPPING.GRADE_RESULT_NAME is
'评级结果名称';
comment on column MOD_CURVE_MAPPING.CREATE_USER is
'创建人';
comment on column MOD_CURVE_MAPPING.CREATE_DEPT is
'创建部门';
comment on column MOD_CURVE_MAPPING.CREATE_TIME is
'创建时间';
comment on column MOD_CURVE_MAPPING.UPDATE_USER is
'更新人';
comment on column MOD_CURVE_MAPPING.UPDATE_TIME is
'更新时间';

--前瞻因子配置表
drop table MOD_PD_FACTOR_CONFIG ;
create table MOD_PD_FACTOR_CONFIG
(
  seqno             VARCHAR2(50) not null,
  vouch_year        VARCHAR2(10),
  vouch_quarter     VARCHAR2(50),
  optimistic_factor NUMBER(30,6),
  basic_factor      NUMBER(30,6),
  pessimism_factor  NUMBER(30,6),
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
);
-- Add comments to the table
comment on table MOD_PD_FACTOR_CONFIG
  is '前瞻因子配置';
-- Add comments to the columns
comment on column MOD_PD_FACTOR_CONFIG.seqno
  is '流水号';
comment on column MOD_PD_FACTOR_CONFIG.vouch_year
  is '年份';
comment on column MOD_PD_FACTOR_CONFIG.vouch_quarter
  is '季度';
comment on column MOD_PD_FACTOR_CONFIG.optimistic_factor
  is '乐观因子';
comment on column MOD_PD_FACTOR_CONFIG.basic_factor
  is '基础因子';
comment on column MOD_PD_FACTOR_CONFIG.pessimism_factor
  is '悲观因子';
comment on column MOD_PD_FACTOR_CONFIG.create_user
  is '创建人';
comment on column MOD_PD_FACTOR_CONFIG.create_dept
  is '创建部门';
comment on column MOD_PD_FACTOR_CONFIG.create_time
  is '创建时间';
comment on column MOD_PD_FACTOR_CONFIG.update_user
  is '更新人';
comment on column MOD_PD_FACTOR_CONFIG.update_time
  is '更新时间';

--前瞻因子权重配置表

drop table MOD_PD_WEIGHT_CONFIG ;
create table MOD_PD_WEIGHT_CONFIG
(
   SEQNO                VARCHAR2(50)         not null,
   OPTIMISTIC_WEIGHT    NUMBER(22,10),
   BASIC_WEIGHT         NUMBER(22,10),
   PESSIMISM_WEIGHT     NUMBER(22,10),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null
);

comment on table MOD_PD_WEIGHT_CONFIG is
'前瞻因子权重配置';
comment on column MOD_PD_WEIGHT_CONFIG.SEQNO is
'流水号';
comment on column MOD_PD_WEIGHT_CONFIG.OPTIMISTIC_WEIGHT is
'乐观权重';
comment on column MOD_PD_WEIGHT_CONFIG.BASIC_WEIGHT is
'基础权重';
comment on column MOD_PD_WEIGHT_CONFIG.PESSIMISM_WEIGHT is
'悲观权重';
comment on column MOD_PD_WEIGHT_CONFIG.CREATE_USER is
'创建人';
comment on column MOD_PD_WEIGHT_CONFIG.CREATE_DEPT is
'创建部门';
comment on column MOD_PD_WEIGHT_CONFIG.CREATE_TIME is
'创建时间';
comment on column MOD_PD_WEIGHT_CONFIG.UPDATE_USER is
'更新人';
comment on column MOD_PD_WEIGHT_CONFIG.UPDATE_TIME is
'更新时间';

--减值配置使用情况表
drop table MOD_CONFIG_USEAGE ;
create table MOD_CONFIG_USEAGE
(
   SEQNO                VARCHAR2(50)         not null,
   CONFIG_SEQNO         VARCHAR2(50),
   CONFIG_TYPE          VARCHAR2(50),
   USE_PROCESS          VARCHAR2(50),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null
);

comment on table MOD_CONFIG_USEAGE is
'减值配置使用情况表';
comment on column MOD_CONFIG_USEAGE.SEQNO is
'流水号';
comment on column MOD_CONFIG_USEAGE.CONFIG_SEQNO is
'配置流水号';
comment on column MOD_CONFIG_USEAGE.CONFIG_TYPE is
'配置类型（数据字典-配置类型_模型）';
comment on column MOD_CONFIG_USEAGE.USE_PROCESS is
'使用过程（数据字典-减值批处理过程_模型）';
comment on column MOD_CONFIG_USEAGE.CREATE_USER is
'创建人';
comment on column MOD_CONFIG_USEAGE.CREATE_DEPT is
'创建部门';
comment on column MOD_CONFIG_USEAGE.CREATE_TIME is
'创建时间';
comment on column MOD_CONFIG_USEAGE.UPDATE_USER is
'更新人';
commit;

--标的类金融产品附属表添加是否含有利率调整权字段
alter table FIN_PRODUCT_ADD add is_rate_adjust varchar2(5);
comment on column FIN_PRODUCT_ADD.is_rate_adjust is
'是否含有利率调整权 ''N''-否，''Y''-是';

--二叉树模型参数配置表
drop table MOD_BINTREE_CONFIG ;
create table MOD_BINTREE_CONFIG
(
   SEQNO                VARCHAR2(32)         not null,
   FINPROD_ID           VARCHAR2(50),
   FINPROD_NAME         VARCHAR2(50),
   STOCK_BORDSTTUS      VARCHAR2(50),
   STOCK_ID             VARCHAR2(50),
   COMP_ORG             VARCHAR2(50),
   MARKET_MULTI         VARCHAR2(50),
   COMP_ORGNAME         VARCHAR2(50),
   COMP_STOCKID         VARCHAR2(50),
   FINPROD_PROIT        NUMERIC(30, 2),
   FINPROD_NETASSET     NUMERIC(30, 2),
   SMOOTHSECTION        NUMERIC(30, 2),
   BINTREE_STEP         INT,
   CURVE_CODE           NUMERIC(30, 0),
   CREATE_USER          VARCHAR2(50)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null
);

comment on table MOD_BINTREE_CONFIG is
'二叉树模型参数配置';
comment on column MOD_BINTREE_CONFIG.SEQNO is
'流水号';
comment on column MOD_BINTREE_CONFIG.FINPROD_ID is
'金融产品代码，存储上游系统内部代码，比如金融产品代码';
comment on column MOD_BINTREE_CONFIG.FINPROD_NAME is
'金融产品名称';
comment on column MOD_BINTREE_CONFIG.STOCK_BORDSTTUS is
'标的公司上市状态';
comment on column MOD_BINTREE_CONFIG.STOCK_ID is
'标的股票代码';
comment on column MOD_BINTREE_CONFIG.COMP_ORG is
'可比公司';
comment on column MOD_BINTREE_CONFIG.MARKET_MULTI is
'市场乘数方法，数据字典.市场乘数方法';
comment on column MOD_BINTREE_CONFIG.COMP_ORGNAME is
'可比公司名称';
comment on column MOD_BINTREE_CONFIG.COMP_STOCKID is
'可比公司股票代码';
comment on column MOD_BINTREE_CONFIG.FINPROD_PROIT is
'标的公司每股收益';
comment on column MOD_BINTREE_CONFIG.FINPROD_NETASSET is
'标的公司每股净资产';
comment on column MOD_BINTREE_CONFIG.SMOOTHSECTION is
'连续股息率';
comment on column MOD_BINTREE_CONFIG.BINTREE_STEP is
'二叉树步数';
comment on column MOD_BINTREE_CONFIG.CURVE_CODE is
'无风险曲线代码';
comment on column MOD_BINTREE_CONFIG.CREATE_USER is
'创建人';
comment on column MOD_BINTREE_CONFIG.CREATE_DEPT is
'创建部门';
comment on column MOD_BINTREE_CONFIG.CREATE_TIME is
'创建时间';
comment on column MOD_BINTREE_CONFIG.UPDATE_USER is
'更新人';
comment on column MOD_BINTREE_CONFIG.UPDATE_TIME is
'更新时间';

--估值模型风险溢价数据表
drop table MOD_RISK_PREMIUM;
create table MOD_RISK_PREMIUM
(
   SEQNO                   varchar(32)                    not null,
   SEC_TYPE_MODEL          varchar(50)                    not null,
   SEC_DETAIL_TYPE_MODEL   varchar(50)                    not null,
   FINPROD_ID              varchar(50)                    null,
   FINPROD_NAME            varchar(50)                    null,
   GRADE_SOURCE            varchar(50)                    null,
   GRADE_RESULT            varchar(50)                    null,
   BASEDATE                date                           null,
   SPREAD                  number                         null,
   SPREAD_ADJ              number                         null,
   B_FULLPRICE             number(30,14)                  null,
   B_QTY                   number(30,14)                  null,
   MOD_METHOD              varchar(50)                  null,
   CREATE_USER             varchar(20)                    not null,
   CREATE_DEPT             varchar(32)                    null,
   CREATE_TIME             timestamp                      not null,
   UPDATE_USER             varchar(20)                    not null,
   UPDATE_TIME             timestamp                      not null,
   constraint PK_MOD_RISK_PREMIUM primary key (SEQNO)
);

comment on table MOD_RISK_PREMIUM is
'估值模型风险溢价数据';
comment on column MOD_RISK_PREMIUM.SEQNO is
'流水号';
comment on column MOD_RISK_PREMIUM.SEC_TYPE_MODEL is
'证券品种（数据字典.证券品种_模型）';
comment on column MOD_RISK_PREMIUM.SEC_DETAIL_TYPE_MODEL is
'证券细分类（数据字典.证券细分类_模型）';
comment on column MOD_RISK_PREMIUM.FINPROD_ID is
'金融产品代码，存储上游系统内部代码，比如金融产品代码';
comment on column MOD_RISK_PREMIUM.FINPROD_NAME is
'金融产品名称';
comment on column MOD_RISK_PREMIUM.GRADE_SOURCE is
'评级来源';
comment on column MOD_RISK_PREMIUM.GRADE_RESULT is
'评级结果';
comment on column MOD_RISK_PREMIUM.BASEDATE is
'基准日';
comment on column MOD_RISK_PREMIUM.SPREAD is
'风险溢价';
comment on column MOD_RISK_PREMIUM.SPREAD_ADJ is
'风险溢价调整';
comment on column MOD_RISK_PREMIUM.B_FULLPRICE is
'买入全价';
comment on column MOD_RISK_PREMIUM.B_QTY is
'买入数量';
comment on column MOD_RISK_PREMIUM.MOD_METHOD is
'估值模型方法';
comment on column MOD_RISK_PREMIUM.CREATE_USER is
'创建人';
comment on column MOD_RISK_PREMIUM.CREATE_DEPT is
'创建部门';
comment on column MOD_RISK_PREMIUM.CREATE_TIME is
'创建时间';
comment on column MOD_RISK_PREMIUM.UPDATE_USER is
'更新人';
comment on column MOD_RISK_PREMIUM.UPDATE_TIME is
'更新时间';

--估值模型-风险溢价数据表调整字段必填项
alter table MOD_RISK_PREMIUM modify sec_detail_type_model null;
alter table MOD_RISK_PREMIUM modify grade_source null;
alter table MOD_RISK_PREMIUM modify grade_result null;
alter table MOD_RISK_PREMIUM modify spread null;

--估值减值批处理相关表
--批处理请求参数表
drop table MOD_VAL_BATCH_PARAMS ;
create table MOD_VAL_BATCH_PARAMS
(
    SEQNO            VARCHAR(32)  not null
        constraint MOD_VAL_BATCH_PARAMS_PK
            unique,
    BATCH_EXEC_SEQNO VARCHAR(32)  not null,
    FINPROD_ID       VARCHAR(50)  not null,
    EXEC_DATE        TIMESTAMP(6) not null,
    CREATE_USER      VARCHAR(20)  not null,
    CREATE_DEPT      VARCHAR(32),
    CREATE_TIME      TIMESTAMP(6) not null,
    UPDATE_USER      VARCHAR(20)  not null,
    UPDATE_TIME      TIMESTAMP(6) not null
);

comment on column MOD_VAL_BATCH_PARAMS.SEQNO is '序号';
comment on column MOD_VAL_BATCH_PARAMS.BATCH_EXEC_SEQNO is '批处理对应序号';
comment on column MOD_VAL_BATCH_PARAMS.FINPROD_ID is '资产代码';
comment on column MOD_VAL_BATCH_PARAMS.EXEC_DATE is '执行日期';
comment on column MOD_VAL_BATCH_PARAMS.CREATE_USER is '创建人';
comment on column MOD_VAL_BATCH_PARAMS.CREATE_DEPT is '创建部门';
comment on column MOD_VAL_BATCH_PARAMS.CREATE_TIME is '创建时间';
comment on column MOD_VAL_BATCH_PARAMS.UPDATE_USER is '更新人';
comment on column MOD_VAL_BATCH_PARAMS.UPDATE_TIME is '更新时间';

--批处理日志表
drop table MOD_BATCH_LOGS purge;
create table MOD_BATCH_LOGS
(
    SEQNO            VARCHAR(32)  not null,
    BATCH_EXEC_SEQNO VARCHAR(32)  not null,
    FINPROD_ID       VARCHAR(50)  not null,
    TASK_ID          VARCHAR(32)  not null,
    EXEC_DETAIL      CLOB,
    EXEC_STATUS      VARCHAR(10),
    CREATE_USER      VARCHAR(20)  not null,
    CREATE_DEPT      VARCHAR(32),
    CREATE_TIME      TIMESTAMP(6) not null,
    UPDATE_USER      VARCHAR(20)  not null,
    UPDATE_TIME      TIMESTAMP(6) not null
);

comment on column MOD_BATCH_LOGS.SEQNO is '序号';
comment on column MOD_BATCH_LOGS.BATCH_EXEC_SEQNO is '批处理对应序号';
comment on column MOD_BATCH_LOGS.FINPROD_ID is '资产代码';
comment on column MOD_BATCH_LOGS.EXEC_DETAIL is '消息详情';
comment on column MOD_BATCH_LOGS.EXEC_STATUS is '执行状态';
comment on column MOD_BATCH_LOGS.CREATE_USER is '创建人';
comment on column MOD_BATCH_LOGS.CREATE_DEPT is '创建部门';
comment on column MOD_BATCH_LOGS.CREATE_TIME is '创建时间';
comment on column MOD_BATCH_LOGS.UPDATE_USER is '更新人';
comment on column MOD_BATCH_LOGS.UPDATE_TIME is '更新时间';
comment on column MOD_BATCH_LOGS.TASK_ID is '任务ID';
create index MOD_BATCH_LOGS1
    on MOD_BATCH_LOGS (BATCH_EXEC_SEQNO, TASK_ID, FINPROD_ID);

-- 批处理服务器信息配置表
drop table SYS_BATCH_SERVER_INFO;
create table SYS_BATCH_SERVER_INFO
(
    SEQ_NO         VARCHAR2(32)      not null
        constraint PK_SYS_BATCH_SERVER_INFO
            primary key,
    PROGRAM_ID     NUMBER,
    SERVER_NAME    VARCHAR2(50)      not null,
    SERVER_ADDRESS VARCHAR2(32)      not null,
    PORT           NUMBER            not null,
    STATUS         VARCHAR2(10)      not null,
    SSH_PORT       NUMBER default 22 not null,
    SSH_USER       VARCHAR2(50),
    SSH_PASSWORD   VARCHAR2(200),
    COMMAND        VARCHAR2(200),
    START_TIME     TIMESTAMP(6),
    STOP_TIME      TIMESTAMP(6),
    RESTART_TIME   TIMESTAMP(6),
    VERSION        NUMBER default 1  not null
);

comment on table SYS_BATCH_SERVER_INFO is '批处理服务器信息配置';
comment on column SYS_BATCH_SERVER_INFO.SEQ_NO is '序列号';
comment on column SYS_BATCH_SERVER_INFO.PROGRAM_ID is 'PID';
comment on column SYS_BATCH_SERVER_INFO.SERVER_NAME is '批处理服务器名称';
comment on column SYS_BATCH_SERVER_INFO.SERVER_ADDRESS is '批处理服务器地址';
comment on column SYS_BATCH_SERVER_INFO.PORT is '批处理管理服务监听端口号';
comment on column SYS_BATCH_SERVER_INFO.STATUS is '批处理服务器状态';
comment on column SYS_BATCH_SERVER_INFO.SSH_PORT is 'SSH端口号';
comment on column SYS_BATCH_SERVER_INFO.SSH_USER is 'SSH用户名';
comment on column SYS_BATCH_SERVER_INFO.SSH_PASSWORD is 'SSH用户密码（支持ENC加密）';
comment on column SYS_BATCH_SERVER_INFO.COMMAND is '批处理控制脚本（可以是文件或者命令）';
comment on column SYS_BATCH_SERVER_INFO.START_TIME is '批处理服务器第一次启动时间';
comment on column SYS_BATCH_SERVER_INFO.STOP_TIME is '批处理服务器停止时间';
comment on column SYS_BATCH_SERVER_INFO.RESTART_TIME is '批处理服务器重启时间';
comment on column SYS_BATCH_SERVER_INFO.VERSION is '版本号';

--模型估值结果明细表
drop table MOD_CURVE_RESULT_DETAIL;
create table MOD_CURVE_RESULT_DETAIL
(
  seqno        VARCHAR2(50) not null,
  result_seqno VARCHAR2(50) not null,
  finprod_id   VARCHAR2(50) not null,
  cdate        DATE not null,
  cash_date    DATE not null,
  period       NUMBER(30,12),
  cash_amt     NUMBER(30,2),
  dis_rate     NUMBER(30,8),
  dis_factor   NUMBER(30,2),
  dis_cash_amt NUMBER(30,2),
  create_user  VARCHAR2(20) not null,
  create_dept  VARCHAR2(32),
  create_time  TIMESTAMP(6) not null,
  update_user  VARCHAR2(20) not null,
  update_time  TIMESTAMP(6) not null
);
-- Add comments to the table
comment on table MOD_CURVE_RESULT_DETAIL
  is '模型估值结果明细';
-- Add comments to the columns
comment on column MOD_CURVE_RESULT_DETAIL.seqno
  is '序号';
comment on column MOD_CURVE_RESULT_DETAIL.result_seqno
  is '结果表序号';
comment on column MOD_CURVE_RESULT_DETAIL.finprod_id
  is '金融产品代码';
comment on column MOD_CURVE_RESULT_DETAIL.cdate
  is '日期';
comment on column MOD_CURVE_RESULT_DETAIL.cash_date
  is '现金流日期';
comment on column MOD_CURVE_RESULT_DETAIL.period
  is '期限（年化）';
comment on column MOD_CURVE_RESULT_DETAIL.cash_amt
  is '现金流金额';
comment on column MOD_CURVE_RESULT_DETAIL.dis_rate
  is '折现利率';
comment on column MOD_CURVE_RESULT_DETAIL.dis_factor
  is '折现因子';
comment on column MOD_CURVE_RESULT_DETAIL.dis_cash_amt
  is '折算后金额';
comment on column MOD_CURVE_RESULT_DETAIL.create_user
  is '创建人';
comment on column MOD_CURVE_RESULT_DETAIL.create_dept
  is '创建部门';
comment on column MOD_CURVE_RESULT_DETAIL.create_time
  is '创建时间';
comment on column MOD_CURVE_RESULT_DETAIL.update_user
  is '更新人';
comment on column MOD_CURVE_RESULT_DETAIL.update_time
  is '更新时间';

--估值模型股息增长率配置 库表
drop table MOD_STOCKDIVITRATE_CONFIG cascade constraints;
create table MOD_STOCKDIVITRATE_CONFIG
(
  seqno                 VARCHAR2(32) not null,
  finprod_id            VARCHAR2(50),
  finprod_name          VARCHAR2(50),
  sec_type_model        VARCHAR2(50) not null,
  sec_detail_type_model VARCHAR2(50) not null,
  stock_intrate         NUMBER(30,14),
  create_user           VARCHAR2(20) not null,
  create_dept           VARCHAR2(32),
  create_time           TIMESTAMP(6) not null,
  update_user           VARCHAR2(20) not null,
  update_time           TIMESTAMP(6) not null
);
-- Add comments to the table
comment on table MOD_STOCKDIVITRATE_CONFIG
  is '估值模型股息增长率配置';
-- Add comments to the columns
comment on column MOD_STOCKDIVITRATE_CONFIG.seqno
  is '流水号';
comment on column MOD_STOCKDIVITRATE_CONFIG.finprod_id
  is '金融产品代码，存储上游系统内部代码，比如金融产品代码';
comment on column MOD_STOCKDIVITRATE_CONFIG.finprod_name
  is '金融产品名称';
comment on column MOD_STOCKDIVITRATE_CONFIG.sec_type_model
  is '资产类型';
comment on column MOD_STOCKDIVITRATE_CONFIG.sec_detail_type_model
  is '资产细分类';
comment on column MOD_STOCKDIVITRATE_CONFIG.stock_intrate
  is '预期股息增长率';
comment on column MOD_STOCKDIVITRATE_CONFIG.create_user
  is '创建人';
comment on column MOD_STOCKDIVITRATE_CONFIG.create_dept
  is '创建部门';
comment on column MOD_STOCKDIVITRATE_CONFIG.create_time
  is '创建时间';
comment on column MOD_STOCKDIVITRATE_CONFIG.update_user
  is '更新人';
comment on column MOD_STOCKDIVITRATE_CONFIG.update_time
  is '更新时间';



--减值阶段参数表
drop table MOD_STAGE_PARA cascade constraints;
create table MOD_STAGE_PARA
(
   SEQNO                VARCHAR2(50)         not null,
   STAGE_OF_ASSET       VARCHAR2(50),
   ABSOLUTE_OF_ELEMENTS  VARCHAR2(50),
   ELEMENTS              VARCHAR2(50),
   ELEMENTS_VALUE        VARCHAR2(200),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_STAGE_PARA primary key (SEQNO)
);
comment on table MOD_STAGE_PARA is
'减值阶段参数';
comment on column MOD_STAGE_PARA.SEQNO is
'序列号';
comment on column MOD_STAGE_PARA.STAGE_OF_ASSET is
'资产所属阶段(数据字典 减值阶段)';
comment on column MOD_STAGE_PARA.ABSOLUTE_OF_ELEMENTS is
'要素绝对性(数据字典 要素绝对性)';
comment on column MOD_STAGE_PARA.ELEMENTS is
'要素(数据字典 减值要素)';
comment on column MOD_STAGE_PARA.ELEMENTS_VALUE is
'要素值';
comment on column MOD_STAGE_PARA.CREATE_USER is
'创建人';
comment on column MOD_STAGE_PARA.CREATE_DEPT is
'创建部门';
comment on column MOD_STAGE_PARA.CREATE_TIME is
'创建时间';
comment on column MOD_STAGE_PARA.UPDATE_USER is
'更新人';
comment on column MOD_STAGE_PARA.UPDATE_TIME is
'更新时间';

--减值结果过程表
drop table MOD_ECL_RESULT_PROCESS ;
create table MOD_ECL_RESULT_PROCESS
(
   SEQNO                VARCHAR2(50)         not null,
   PROD_ID              VARCHAR2(50)         not null,
   PROD_NAME            VARCHAR2(200),
   PORTFOLIO_ID         VARCHAR2(50),
   ASSET_ID             VARCHAR2(50)         not null,
   ASSET_NAME           VARCHAR2(200),
   MARKET_ID            VARCHAR2(50)         not null,
   TYPE_8               VARCHAR2(50),
   TYPE_9               VARCHAR2(50),
   TYPE_10              VARCHAR2(50),
   TYPE_11              VARCHAR2(50),
   TYPE_12              VARCHAR2(50),
   MODEL_ASSET_TYPE     VARCHAR2(50),
   MODEL_ASSET_DETAIL_TYPE VARCHAR2(50),
   MDATE                DATE,
   UPDATE_TIME          TIMESTAMP            not null,
   FINANCIER            VARCHAR2(50),
   GRADE_ORG_ID         VARCHAR2(50),
   ISSUER               VARCHAR2(50),
   ACTUAL_CREDIT        VARCHAR2(50),
   SPONSOR              VARCHAR2(50),
   RISK_LEVEL_FIVE      VARCHAR2(50),
   TRADE_ID             VARCHAR2(50),
   TRADE_DATE           DATE,
   PS                   VARCHAR2(50),
   SHARE_AMT            NUMBER(30,4),
   YIELD                NUMBER(30,14),
   PAPER_RATE           NUMBER(30,14),
   PLAN_END_DATE        DATE,
   IS_CLEAN             VARCHAR2(50),
   DEC_DATE             DATE                 not null,
   O_TYPE               VARCHAR2(50),
   RESULT_01            VARCHAR2(50),
   RESULT_02            VARCHAR2(50),
   RESULT_ISSUER        VARCHAR2(50),
   RESULT_FINANCIER     VARCHAR2(50),
   RESULT_SPONSOR       VARCHAR2(50),
   RESULT_ACTUAL_CREDIT VARCHAR2(50),
   O_TYPE_FINAL         VARCHAR2(50),
   ORG_ID_FINAL         VARCHAR2(50),
   IS_FIN_LICENCE       VARCHAR2(50),
   RESULT_DEAL          VARCHAR2(50),
   RESULT_CURRENT       VARCHAR2(50),
   PRIN_OVERDUE_DAY     NUMBER(20,2),
   INT_OVERDUE_DAY      NUMBER(20,2),
   FIN_STAGE            VARCHAR2(50),
   EMA_SHARE_AMT        NUMBER(30,4),
   EAD                  NUMBER(30,4),
   PRE_PROSPECTIVE_PD   NUMBER(30,6),
   PROSPECTIVE_PD       NUMBER(30,6),
   LGD                  NUMBER(30,6),
   PRE_PROSPECTIVE_ECL  NUMBER(30,4),
   PROSPECTIVE_ECL      NUMBER(30,4),
   PROCESS              VARCHAR2(50)         not null,
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   IS_CONTAIN_PLEDGE    VARCHAR2(50)
);

comment on table MOD_ECL_RESULT_PROCESS is
'减值结果过程表';
comment on column MOD_ECL_RESULT_PROCESS.SEQNO is
'序列号';
comment on column MOD_ECL_RESULT_PROCESS.PROD_ID is
'产品代码';
comment on column MOD_ECL_RESULT_PROCESS.PROD_NAME is
'产品名称';
comment on column MOD_ECL_RESULT_PROCESS.PORTFOLIO_ID is
'投资组合代码（有子组合是子组合代码，没有子组合是母组合代码）';
comment on column MOD_ECL_RESULT_PROCESS.ASSET_ID is
'资产代码';
comment on column MOD_ECL_RESULT_PROCESS.ASSET_NAME is
'资产名称';
comment on column MOD_ECL_RESULT_PROCESS.MARKET_ID is
'市场代码';
comment on column MOD_ECL_RESULT_PROCESS.TYPE_8 is
'TYPE_8';
comment on column MOD_ECL_RESULT_PROCESS.TYPE_9 is
'TYPE_9';
comment on column MOD_ECL_RESULT_PROCESS.TYPE_10 is
'TYPE_10';
comment on column MOD_ECL_RESULT_PROCESS.TYPE_11 is
'TYPE_11';
comment on column MOD_ECL_RESULT_PROCESS.TYPE_12 is
'TYPE_12';
comment on column MOD_ECL_RESULT_PROCESS.MODEL_ASSET_TYPE is
'模型资产类型';
comment on column MOD_ECL_RESULT_PROCESS.MODEL_ASSET_DETAIL_TYPE is
'模型资产细分类';
comment on column MOD_ECL_RESULT_PROCESS.MDATE is
'到期日';
comment on column MOD_ECL_RESULT_PROCESS.UPDATE_TIME is
'更新时间';
comment on column MOD_ECL_RESULT_PROCESS.FINANCIER is
'融资人';
comment on column MOD_ECL_RESULT_PROCESS.GRADE_ORG_ID is
'评级机构';
comment on column MOD_ECL_RESULT_PROCESS.ISSUER is
'发行人';
comment on column MOD_ECL_RESULT_PROCESS.ACTUAL_CREDIT is
'实际授信主体';
comment on column MOD_ECL_RESULT_PROCESS.SPONSOR is
'担保人';
comment on column MOD_ECL_RESULT_PROCESS.RISK_LEVEL_FIVE is
'风险五级分类';
comment on column MOD_ECL_RESULT_PROCESS.TRADE_ID is
'交易编号';
comment on column MOD_ECL_RESULT_PROCESS.TRADE_DATE is
'交易日期';
comment on column MOD_ECL_RESULT_PROCESS.PS is
'交易方向';
comment on column MOD_ECL_RESULT_PROCESS.SHARE_AMT is
'交易份额';
comment on column MOD_ECL_RESULT_PROCESS.YIELD is
'到期收益率';
comment on column MOD_ECL_RESULT_PROCESS.PAPER_RATE is
'票面利率';
comment on column MOD_ECL_RESULT_PROCESS.PLAN_END_DATE is
'计划截止日';
comment on column MOD_ECL_RESULT_PROCESS.IS_CLEAN is
'是否结清';
comment on column MOD_ECL_RESULT_PROCESS.DEC_DATE is
'减值日期';
comment on column MOD_ECL_RESULT_PROCESS.O_TYPE is
'评级类型';
comment on column MOD_ECL_RESULT_PROCESS.RESULT_01 is
'债项评级结果（最新）
债券主体评级结果（最新）
发行人评级结果（最新）
融资人评级结果（最新）
担保人评级结果（最新）
实际授信主体评级结果（最新）
买入时点评级结果
最新评级结果
债项评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.RESULT_02 is
'债券主体评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.RESULT_ISSUER is
'发行人评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.RESULT_FINANCIER is
'融资人评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.RESULT_SPONSOR is
'担保人评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.RESULT_ACTUAL_CREDIT is
'实际授信主体评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.O_TYPE_FINAL is
'最终使用评级类型';
comment on column MOD_ECL_RESULT_PROCESS.ORG_ID_FINAL is
'最终使用主体机构';
comment on column MOD_ECL_RESULT_PROCESS.IS_FIN_LICENCE is
'主体是否持有金融牌照';
comment on column MOD_ECL_RESULT_PROCESS.RESULT_DEAL is
'买入时点评级结果';
comment on column MOD_ECL_RESULT_PROCESS.RESULT_CURRENT is
'最新评级结果';
comment on column MOD_ECL_RESULT_PROCESS.PRIN_OVERDUE_DAY is
'本金逾期天数';
comment on column MOD_ECL_RESULT_PROCESS.INT_OVERDUE_DAY is
'利息逾期天数';
comment on column MOD_ECL_RESULT_PROCESS.FIN_STAGE is
'减值阶段';
comment on column MOD_ECL_RESULT_PROCESS.EMA_SHARE_AMT is
'移动平均后份额';
comment on column MOD_ECL_RESULT_PROCESS.EAD is
'EAD违约风险暴露';
comment on column MOD_ECL_RESULT_PROCESS.PRE_PROSPECTIVE_PD is
'前瞻前PD违约概率%';
comment on column MOD_ECL_RESULT_PROCESS.PROSPECTIVE_PD is
'前瞻后PD违约概率%';
comment on column MOD_ECL_RESULT_PROCESS.LGD is
'LGD违约损失率%';
comment on column MOD_ECL_RESULT_PROCESS.PRE_PROSPECTIVE_ECL is
'前瞻前ECL预期信用损失';
comment on column MOD_ECL_RESULT_PROCESS.PROSPECTIVE_ECL is
'前瞻后ECL预期信用损失';
comment on column MOD_ECL_RESULT_PROCESS.PROCESS is
'过程';
comment on column MOD_ECL_RESULT_PROCESS.CREATE_USER is
'创建人';
comment on column MOD_ECL_RESULT_PROCESS.CREATE_DEPT is
'创建部门';
comment on column MOD_ECL_RESULT_PROCESS.CREATE_TIME is
'创建时间';
comment on column MOD_ECL_RESULT_PROCESS.UPDATE_USER is
'更新人';
comment on column MOD_ECL_RESULT_PROCESS.IS_CONTAIN_PLEDGE is
'是否含有抵质押物';

--减值结果过程表
drop index "Index_9";
drop table MOD_ECL_RESULT_PROCESS ;
create table MOD_ECL_RESULT_PROCESS
(
  seqno                   VARCHAR2(50) not null,
  prod_id                 VARCHAR2(50) not null,
  prod_name               VARCHAR2(200),
  portfolio_id            VARCHAR2(50),
  asset_id                VARCHAR2(50) not null,
  asset_name              VARCHAR2(200),
  market_id               VARCHAR2(50) not null,
  type_8                  VARCHAR2(50),
  type_9                  VARCHAR2(50),
  type_10                 VARCHAR2(50),
  type_11                 VARCHAR2(50),
  type_12                 VARCHAR2(50),
  model_asset_type        VARCHAR2(50),
  model_asset_detail_type VARCHAR2(50),
  mdate                   DATE,
  update_time             TIMESTAMP(6) not null,
  financier               VARCHAR2(50),
  grade_org_id            VARCHAR2(50),
  issuer                  VARCHAR2(50),
  actual_credit           VARCHAR2(50),
  sponsor                 VARCHAR2(50),
  risk_level_five         VARCHAR2(50),
  trade_id                VARCHAR2(50),
  trade_date              DATE,
  ps                      VARCHAR2(50),
  share_amt               NUMBER(30,4),
  trade_amt               NUMBER(30,4),
  yield                   NUMBER(30,14),
  paper_rate              NUMBER(30,14),
  plan_end_date           DATE,
  is_clean                VARCHAR2(50),
  dec_date                DATE not null,
  o_type                  VARCHAR2(50),
  result_01               VARCHAR2(50),
  result_02               VARCHAR2(50),
  result_issuer           VARCHAR2(50),
  result_financier        VARCHAR2(50),
  result_sponsor          VARCHAR2(50),
  result_actual_credit    VARCHAR2(50),
  o_type_final            VARCHAR2(50),
  org_id_final            VARCHAR2(50),
  is_fin_licence          VARCHAR2(50),
  result_deal             VARCHAR2(50),
  result_current          VARCHAR2(50),
  prin_overdue_day        NUMBER(20,2),
  int_overdue_day         NUMBER(20,2),
  fin_stage               VARCHAR2(50),
  ema_share_amt           NUMBER(30,4),
  ead                     NUMBER(30,4),
  pre_prospective_pd      NUMBER(30,6),
  prospective_pd          NUMBER(30,6),
  lgd                     NUMBER(30,6),
  pre_prospective_ecl     NUMBER(30,4),
  prospective_ecl         NUMBER(30,4),
  issue_amt               NUMBER(30,2),
  vdate                   DATE,
  coupon_species          VARCHAR2(50),
  issue_price             NUMBER(30,14),
  process                 VARCHAR2(50) not null,
  create_user             VARCHAR2(20) not null,
  create_dept             VARCHAR2(32),
  create_time             TIMESTAMP(6) not null,
  update_user             VARCHAR2(20) not null,
  is_contain_pledge       VARCHAR2(50)
);
comment on table MOD_ECL_RESULT_PROCESS
  is '减值结果过程表';
comment on column MOD_ECL_RESULT_PROCESS.seqno
  is '序列号';
comment on column MOD_ECL_RESULT_PROCESS.prod_id
  is '产品代码';
comment on column MOD_ECL_RESULT_PROCESS.prod_name
  is '产品名称';
comment on column MOD_ECL_RESULT_PROCESS.portfolio_id
  is '投资组合代码（有子组合是子组合代码，没有子组合是母组合代码）';
comment on column MOD_ECL_RESULT_PROCESS.asset_id
  is '资产代码';
comment on column MOD_ECL_RESULT_PROCESS.asset_name
  is '资产名称';
comment on column MOD_ECL_RESULT_PROCESS.market_id
  is '市场代码';
comment on column MOD_ECL_RESULT_PROCESS.type_8
  is 'TYPE_8';
comment on column MOD_ECL_RESULT_PROCESS.type_9
  is 'TYPE_9';
comment on column MOD_ECL_RESULT_PROCESS.type_10
  is 'TYPE_10';
comment on column MOD_ECL_RESULT_PROCESS.type_11
  is 'TYPE_11';
comment on column MOD_ECL_RESULT_PROCESS.type_12
  is 'TYPE_12';
comment on column MOD_ECL_RESULT_PROCESS.model_asset_type
  is '模型资产类型';
comment on column MOD_ECL_RESULT_PROCESS.model_asset_detail_type
  is '模型资产细分类';
comment on column MOD_ECL_RESULT_PROCESS.mdate
  is '到期日';
comment on column MOD_ECL_RESULT_PROCESS.update_time
  is '更新时间';
comment on column MOD_ECL_RESULT_PROCESS.financier
  is '融资人';
comment on column MOD_ECL_RESULT_PROCESS.grade_org_id
  is '评级机构';
comment on column MOD_ECL_RESULT_PROCESS.issuer
  is '发行人';
comment on column MOD_ECL_RESULT_PROCESS.actual_credit
  is '实际授信主体';
comment on column MOD_ECL_RESULT_PROCESS.sponsor
  is '担保人';
comment on column MOD_ECL_RESULT_PROCESS.risk_level_five
  is '风险五级分类';
comment on column MOD_ECL_RESULT_PROCESS.trade_id
  is '交易编号';
comment on column MOD_ECL_RESULT_PROCESS.trade_date
  is '交易日期';
comment on column MOD_ECL_RESULT_PROCESS.ps
  is '交易方向';
comment on column MOD_ECL_RESULT_PROCESS.share_amt
  is '交易份额';
comment on column MOD_ECL_RESULT_PROCESS.trade_amt
  is '交易金额';
comment on column MOD_ECL_RESULT_PROCESS.yield
  is '到期收益率';
comment on column MOD_ECL_RESULT_PROCESS.paper_rate
  is '票面利率';
comment on column MOD_ECL_RESULT_PROCESS.plan_end_date
  is '计划截止日';
comment on column MOD_ECL_RESULT_PROCESS.is_clean
  is '是否结清';
comment on column MOD_ECL_RESULT_PROCESS.dec_date
  is '减值日期';
comment on column MOD_ECL_RESULT_PROCESS.o_type
  is '评级类型';
comment on column MOD_ECL_RESULT_PROCESS.result_01
  is '债项评级结果（最新）
债券主体评级结果（最新）
发行人评级结果（最新）
融资人评级结果（最新）
担保人评级结果（最新）
实际授信主体评级结果（最新）
买入时点评级结果
最新评级结果
债项评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.result_02
  is '债券主体评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.result_issuer
  is '发行人评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.result_financier
  is '融资人评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.result_sponsor
  is '担保人评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.result_actual_credit
  is '实际授信主体评级结果（最新）';
comment on column MOD_ECL_RESULT_PROCESS.o_type_final
  is '最终使用评级类型';
comment on column MOD_ECL_RESULT_PROCESS.org_id_final
  is '最终使用主体机构';
comment on column MOD_ECL_RESULT_PROCESS.is_fin_licence
  is '主体是否持有金融牌照';
comment on column MOD_ECL_RESULT_PROCESS.result_deal
  is '买入时点评级结果';
comment on column MOD_ECL_RESULT_PROCESS.result_current
  is '最新评级结果';
comment on column MOD_ECL_RESULT_PROCESS.prin_overdue_day
  is '本金逾期天数';
comment on column MOD_ECL_RESULT_PROCESS.int_overdue_day
  is '利息逾期天数';
comment on column MOD_ECL_RESULT_PROCESS.fin_stage
  is '减值阶段';
comment on column MOD_ECL_RESULT_PROCESS.ema_share_amt
  is '移动平均后份额';
comment on column MOD_ECL_RESULT_PROCESS.ead
  is 'EAD违约风险暴露';
comment on column MOD_ECL_RESULT_PROCESS.pre_prospective_pd
  is '前瞻前PD违约概率%';
comment on column MOD_ECL_RESULT_PROCESS.prospective_pd
  is '前瞻后PD违约概率%';
comment on column MOD_ECL_RESULT_PROCESS.lgd
  is 'LGD违约损失率%';
comment on column MOD_ECL_RESULT_PROCESS.pre_prospective_ecl
  is '前瞻前ECL预期信用损失';
comment on column MOD_ECL_RESULT_PROCESS.prospective_ecl
  is '前瞻后ECL预期信用损失';
comment on column MOD_ECL_RESULT_PROCESS.issue_amt
  is '发行规模';
comment on column MOD_ECL_RESULT_PROCESS.vdate
  is '起息日';
comment on column MOD_ECL_RESULT_PROCESS.coupon_species
  is '息票品种';
comment on column MOD_ECL_RESULT_PROCESS.issue_price
  is '发行价';
comment on column MOD_ECL_RESULT_PROCESS.process
  is '过程';
comment on column MOD_ECL_RESULT_PROCESS.create_user
  is '创建人';
comment on column MOD_ECL_RESULT_PROCESS.create_dept
  is '创建部门';
comment on column MOD_ECL_RESULT_PROCESS.create_time
  is '创建时间';
comment on column MOD_ECL_RESULT_PROCESS.update_user
  is '更新人';
comment on column MOD_ECL_RESULT_PROCESS.is_contain_pledge
  is '是否含有抵质押物';

create index "Index_9" on MOD_ECL_RESULT_PROCESS (
   DEC_DATE ASC,
   PROCESS ASC
);



--资产减值结果表
drop table MOD_ECL_RESULT;
create table MOD_ECL_RESULT
(
   SEQNO                varchar(50)                    not null,
   PROD_ID              varchar(50)                    null,
   PROD_NAME            varchar(200)                   null,
   ASSET_ID             varchar(50)                    null,
   ASSET_NAME           varchar(200)                   null,
   ASSET_TYPE           varchar(50)                    null,
   PORTFOLIO_ID         varchar(50)                    null,
   BUSS_NO              varchar(50)                    null,
   PS                   varchar(50)                    null,
   TRADE_DATE           date                           null,
   DEC_DATE             date                           null,
   PRIN_OVERDUE_DAY     NUMBER(20,2)                   null,
   INT_OVERDUE_DAY      NUMBER(20,2)                   null,
   PURCHASE_TIME_GRADE  varchar(50)                    null,
   CURRENT_TIME_GRADE   varchar(50)                    null,
   PRE_PROSPECTIVE_PD   NUMBER(30,6)                 null,
   PROSPECTIVE_PD       NUMBER(30,6)                 null,
   STAGE                varchar(50)                    null,
   LGD                  NUMBER(30,6)                 null,
   EAD                  NUMBER(30,4)                 null,
   PRE_PROSPECTIVE_ECL  NUMBER(30,4)                 null,
   PROSPECTIVE_ECL      NUMBER(30,4)                 null,
   ALLOW_MODEL_COVER    varchar(50)                    null,
   DATA_SOURCE          varchar(50)                    null,
   CREATE_USER          varchar(20)                    not null,
   CREATE_DEPT          varchar(32)                    null,
   CREATE_TIME          timestamp                      not null,
   UPDATE_USER          varchar(20)                    not null,
   UPDATE_TIME          timestamp                      not null
);

comment on table MOD_ECL_RESULT is
'资产减值结果表';
comment on column MOD_ECL_RESULT.SEQNO is
'序列号';
comment on column MOD_ECL_RESULT.PROD_ID is
'产品代码';
comment on column MOD_ECL_RESULT.PROD_NAME is
'产品名称';
comment on column MOD_ECL_RESULT.ASSET_ID is
'资产代码';
comment on column MOD_ECL_RESULT.ASSET_NAME is
'资产名称';
comment on column MOD_ECL_RESULT.ASSET_TYPE is
'资产类型';
comment on column MOD_ECL_RESULT.PORTFOLIO_ID is
'组合代码';
comment on column MOD_ECL_RESULT.BUSS_NO is
'业务编号';
comment on column MOD_ECL_RESULT.PS is
'交易方向';
comment on column MOD_ECL_RESULT.TRADE_DATE is
'交易日期';
comment on column MOD_ECL_RESULT.DEC_DATE is
'减值日期';
comment on column MOD_ECL_RESULT.PRIN_OVERDUE_DAY is
'本金逾期天数';
comment on column MOD_ECL_RESULT.INT_OVERDUE_DAY is
'利息逾期天数';
comment on column MOD_ECL_RESULT.PURCHASE_TIME_GRADE is
'买入时点评级';
comment on column MOD_ECL_RESULT.CURRENT_TIME_GRADE is
'当前时点评级';
comment on column MOD_ECL_RESULT.PRE_PROSPECTIVE_PD is
'前瞻前PD违约概率%';
comment on column MOD_ECL_RESULT.PROSPECTIVE_PD is
'前瞻后PD违约概率%';
comment on column MOD_ECL_RESULT.STAGE is
'减值阶段';
comment on column MOD_ECL_RESULT.LGD is
'LGD违约损失率%';
comment on column MOD_ECL_RESULT.EAD is
'EAD违约风险暴露';
comment on column MOD_ECL_RESULT.PRE_PROSPECTIVE_ECL is
'前瞻前ECL预期信用损失';
comment on column MOD_ECL_RESULT.PROSPECTIVE_ECL is
'前瞻后ECL预期信用损失';
comment on column MOD_ECL_RESULT.ALLOW_MODEL_COVER is
'是否允许模型覆盖';
comment on column MOD_ECL_RESULT.DATA_SOURCE is
'数据来源';
comment on column MOD_ECL_RESULT.CREATE_USER is
'创建人';
comment on column MOD_ECL_RESULT.CREATE_DEPT is
'创建部门';
comment on column MOD_ECL_RESULT.CREATE_TIME is
'创建时间';
comment on column MOD_ECL_RESULT.UPDATE_USER is
'更新人';
comment on column MOD_ECL_RESULT.UPDATE_TIME is
'更新时间';

-- 模型子系统资产分类映射表
drop table MOD_ASEET_MAPPING;
create table MOD_ASEET_MAPPING
(
   SEQ_NO               varchar(32)                    not null,
   MAPPING_TYPE         varchar(50)                    not null,
   INPUT1               varchar(50)                    null,
   INPUT2               varchar(50)                    null,
   INPUT3               varchar(50)                    null,
   INPUT4               varchar(50)                    null,
   INPUT5               varchar(50)                    null,
   OUTPUT1              varchar(50)                    null,
   OUTPUT2              varchar(50)                    null,
   OUTPUT3              varchar(50)                    null,
   OUTPUT4              varchar(50)                    null,
   OUTPUT5              varchar(50)                    null,
   CREATE_USER          varchar(20)                    not null,
   CREATE_DEPT          varchar(32)                    null,
   CREATE_TIME          timestamp                      not null,
   UPDATE_USER          varchar(20)                    not null,
   UPDATE_TIME          timestamp                      not null
);

comment on table MOD_ASEET_MAPPING is
'模型子系统资产分类映射
资产类型：债券、项目类
资产细分类：如国债、企业债？';
comment on column MOD_ASEET_MAPPING.SEQ_NO is
'流水号';
comment on column MOD_ASEET_MAPPING.MAPPING_TYPE is
'映射类型';
comment on column MOD_ASEET_MAPPING.INPUT1 is
'输入1';
comment on column MOD_ASEET_MAPPING.INPUT2 is
'输入2';
comment on column MOD_ASEET_MAPPING.INPUT3 is
'输入3';
comment on column MOD_ASEET_MAPPING.INPUT4 is
'输入4';
comment on column MOD_ASEET_MAPPING.INPUT5 is
'输入5';
comment on column MOD_ASEET_MAPPING.OUTPUT1 is
'输出1';
comment on column MOD_ASEET_MAPPING.OUTPUT2 is
'输出2';
comment on column MOD_ASEET_MAPPING.OUTPUT3 is
'输出3';
comment on column MOD_ASEET_MAPPING.OUTPUT4 is
'输出4';
comment on column MOD_ASEET_MAPPING.OUTPUT5 is
'输出5';
comment on column MOD_ASEET_MAPPING.CREATE_USER is
'创建人';
comment on column MOD_ASEET_MAPPING.CREATE_DEPT is
'创建部门';
comment on column MOD_ASEET_MAPPING.CREATE_TIME is
'创建时间';
comment on column MOD_ASEET_MAPPING.UPDATE_USER is
'更新人';
comment on column MOD_ASEET_MAPPING.UPDATE_TIME is
'更新时间';

--模型估值结果表
drop index "Index_MOD_CURVE_RESULT10";
drop index "Index_MOD_CURVE_RESULT9";
drop table MOD_CURVE_RESULT ;
create table MOD_CURVE_RESULT
(
   SEQNO                VARCHAR2(50)         not null,
   FINPROD_ID           VARCHAR2(50)         not null,
   CDATE                DATE                 not null,
   FULL_PRICE           NUMBER(30,14)        not null,
   NET_PRICE            NUMBER(30,14)        not null,
   CURVE_SPREAD         NUMBER(30,14),
   CURVE_NAME           VARCHAR2(50),
   CURVE_CODE           VARCHAR2(50),
   BASIS                VARCHAR2(50),
   VAL_MODEL            VARCHAR2(50)         not null,
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_CURVE_RESULT primary key (SEQNO)
);
comment on table MOD_CURVE_RESULT is
'模型估值结果';
comment on column MOD_CURVE_RESULT.SEQNO is
'序号';
comment on column MOD_CURVE_RESULT.FINPROD_ID is
'金融产品代码';
comment on column MOD_CURVE_RESULT.CDATE is
'日期';
comment on column MOD_CURVE_RESULT.FULL_PRICE is
'估值全价';
comment on column MOD_CURVE_RESULT.NET_PRICE is
'估值净价';
comment on column MOD_CURVE_RESULT.CURVE_SPREAD is
'交易点差';
comment on column MOD_CURVE_RESULT.CURVE_NAME is
'曲线名称';
comment on column MOD_CURVE_RESULT.CURVE_CODE is
'评级结果';
comment on column MOD_CURVE_RESULT.BASIS is
'计息基础';
comment on column MOD_CURVE_RESULT.VAL_MODEL is
'估值模型';
comment on column MOD_CURVE_RESULT.CREATE_USER is
'创建人';
comment on column MOD_CURVE_RESULT.CREATE_DEPT is
'创建部门';
comment on column MOD_CURVE_RESULT.CREATE_TIME is
'创建时间';
comment on column MOD_CURVE_RESULT.UPDATE_USER is
'更新人';
comment on column MOD_CURVE_RESULT.UPDATE_TIME is
'更新时间';
create index "Index_MOD_CURVE_RESULT9" on MOD_CURVE_RESULT (
   CDATE ASC,
   VAL_MODEL ASC
);
create index "Index_MOD_CURVE_RESULT10" on MOD_CURVE_RESULT (
   FINPROD_ID ASC,
   CDATE ASC,
   VAL_MODEL ASC
);

--标准评级映射表
drop table MOD_STANDARD_GRADE_MAPPING;
create table MOD_STANDARD_GRADE_MAPPING
(
   SEQ_NO               VARCHAR2(50)         not null,
   GRADE_INST           VARCHAR2(50),
   O_TYPE               VARCHAR2(50),
   GRADE_RESULT         VARCHAR2(50),
   GRADE_STANDARD_RESULT VARCHAR2(50),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null
);

comment on table MOD_STANDARD_GRADE_MAPPING is
'标准评级映射表';
comment on column MOD_STANDARD_GRADE_MAPPING.SEQ_NO is
'流水号';
comment on column MOD_STANDARD_GRADE_MAPPING.GRADE_INST is
'评级机构';
comment on column MOD_STANDARD_GRADE_MAPPING.O_TYPE is
'评级类型';
comment on column MOD_STANDARD_GRADE_MAPPING.GRADE_RESULT is
'外部评级结果';
comment on column MOD_STANDARD_GRADE_MAPPING.GRADE_STANDARD_RESULT is
'标准评级结果';
comment on column MOD_STANDARD_GRADE_MAPPING.CREATE_USER is
'创建人';
comment on column MOD_STANDARD_GRADE_MAPPING.CREATE_DEPT is
'创建部门';
comment on column MOD_STANDARD_GRADE_MAPPING.CREATE_TIME is
'创建时间';
comment on column MOD_STANDARD_GRADE_MAPPING.UPDATE_USER is
'更新人';
comment on column MOD_STANDARD_GRADE_MAPPING.UPDATE_TIME is
'更新时间';

--评级PD配置表
drop table MOD_GRADE_PD_CONFIG cascade constraints;
create table MOD_GRADE_PD_CONFIG
(
   SEQNO                VARCHAR2(50)         not null,
   VOUCH_YEAR           NUMBER(10),
   GRADE_ORG_ID         VARCHAR2(50),
   O_TYPE               VARCHAR2(50),
   GRADE_RESULT         VARCHAR2(50),
   FORWARD_YEAR_PD      NUMBER(30,6),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_GRADE_PD_CONFIG primary key (SEQNO)
);

comment on table MOD_GRADE_PD_CONFIG is
'评级PD配置';
comment on column MOD_GRADE_PD_CONFIG.SEQNO is
'流水号';
comment on column MOD_GRADE_PD_CONFIG.VOUCH_YEAR is
'年份';
comment on column MOD_GRADE_PD_CONFIG.GRADE_ORG_ID is
'评级机构（数据字典 评级机构）';
comment on column MOD_GRADE_PD_CONFIG.O_TYPE is
'评级类型（数据字典 评级类型）';
comment on column MOD_GRADE_PD_CONFIG.GRADE_RESULT is
'评级结果（数据字典 评级结果）';
comment on column MOD_GRADE_PD_CONFIG.FORWARD_YEAR_PD is
'前瞻前年度边际';
comment on column MOD_GRADE_PD_CONFIG.CREATE_USER is
'创建人';
comment on column MOD_GRADE_PD_CONFIG.CREATE_DEPT is
'创建部门';
comment on column MOD_GRADE_PD_CONFIG.CREATE_TIME is
'创建时间';
comment on column MOD_GRADE_PD_CONFIG.UPDATE_USER is
'更新人';
comment on column MOD_GRADE_PD_CONFIG.UPDATE_TIME is
'更新时间';

--曲线族-曲线映射表
drop table MOD_CURVE_MAPPING;
create table MOD_CURVE_MAPPING
(
  seq_no            VARCHAR2(50) not null,
  curve_group_code  VARCHAR2(50) not null,
  curve_group_name  VARCHAR2(50),
  curve_code        VARCHAR2(50) not null,
  curve_type        VARCHAR2(50) not null,
  curve_name        VARCHAR2(50),
  grade_result      VARCHAR2(50),
  grade_result_name VARCHAR2(50),
  create_user       VARCHAR2(20) not null,
  create_dept       VARCHAR2(32),
  create_time       TIMESTAMP(6) not null,
  update_user       VARCHAR2(20) not null,
  update_time       TIMESTAMP(6) not null
);

comment on table MOD_CURVE_MAPPING
  is '曲线族-曲线映射表';
comment on column MOD_CURVE_MAPPING.seq_no
  is '流水号';
comment on column MOD_CURVE_MAPPING.curve_group_code
  is '曲线族代码';
comment on column MOD_CURVE_MAPPING.curve_group_name
  is '曲线组名称，企业债、国债收益率曲线';
comment on column MOD_CURVE_MAPPING.curve_code
  is '曲线代码';
comment on column MOD_CURVE_MAPPING.curve_type
  is '曲线类型';
comment on column MOD_CURVE_MAPPING.curve_name
  is '曲线名称';
comment on column MOD_CURVE_MAPPING.grade_result
  is '评级结果';
comment on column MOD_CURVE_MAPPING.grade_result_name
  is '评级结果名称';
comment on column MOD_CURVE_MAPPING.create_user
  is '创建人';
comment on column MOD_CURVE_MAPPING.create_dept
  is '创建部门';
comment on column MOD_CURVE_MAPPING.create_time
  is '创建时间';
comment on column MOD_CURVE_MAPPING.update_user
  is '更新人';
comment on column MOD_CURVE_MAPPING.update_time
  is '更新时间';

--特殊资产LGD违约损失率
drop table MOD_LGD_SPECIAL_CONFIG ;
create table MOD_LGD_SPECIAL_CONFIG
(
   SEQNO                VARCHAR2(50)         not null,
   FINPROD_ID           VARCHAR2(50),
   FINPROD_NAME         VARCHAR2(200),
   LGD                  NUMBER(30,6),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null
);

comment on table MOD_LGD_SPECIAL_CONFIG is
'特殊资产LGD违约损失率';
comment on column MOD_LGD_SPECIAL_CONFIG.SEQNO is
'流水号';
comment on column MOD_LGD_SPECIAL_CONFIG.FINPROD_ID is
'金融产品代码';
comment on column MOD_LGD_SPECIAL_CONFIG.FINPROD_NAME is
'金融产品名称';
comment on column MOD_LGD_SPECIAL_CONFIG.LGD is
'LGD(%)';
comment on column MOD_LGD_SPECIAL_CONFIG.CREATE_USER is
'创建人';
comment on column MOD_LGD_SPECIAL_CONFIG.CREATE_DEPT is
'创建部门';
comment on column MOD_LGD_SPECIAL_CONFIG.CREATE_TIME is
'创建时间';
comment on column MOD_LGD_SPECIAL_CONFIG.UPDATE_USER is
'更新人';
comment on column MOD_LGD_SPECIAL_CONFIG.UPDATE_TIME is
'更新时间';

--
drop table MOD_ASSET_GRADE_SPECIAL_CONFIG cascade constraints;

/*==============================================================*/
/* Table: MOD_ASSET_GRADE_SPECIAL_CONFIG                        */
/*==============================================================*/
create table mod_asset_grade_special_config
(
   SEQNO                VARCHAR2(50)         not null,
   FINPROD_ID           VARCHAR2(50),
   FINPROD_NAME         VARCHAR2(200),
   GRADE_ORG_ID         VARCHAR2(50),
   O_TYPE               VARCHAR2(50),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_ASSET_GRADE_SPECIAL_CON primary key (SEQNO)
);

comment on table MOD_ASSET_GRADE_SPECIAL_CONFIG is
'特殊资产评级配置';

comment on column MOD_ASSET_GRADE_SPECIAL_CONFIG.SEQNO is
'流水号';

comment on column MOD_ASSET_GRADE_SPECIAL_CONFIG.FINPROD_ID is
'金融产品代码';

comment on column MOD_ASSET_GRADE_SPECIAL_CONFIG.FINPROD_NAME is
'金融产品名称';

comment on column MOD_ASSET_GRADE_SPECIAL_CONFIG.GRADE_ORG_ID is
'评级机构（数据字典 评级机构）';

comment on column MOD_ASSET_GRADE_SPECIAL_CONFIG.O_TYPE is
'评级类型（数据字典 评级类型）';

comment on column MOD_ASSET_GRADE_SPECIAL_CONFIG.CREATE_USER is
'创建人';

comment on column MOD_ASSET_GRADE_SPECIAL_CONFIG.CREATE_DEPT is
'创建部门';

comment on column MOD_ASSET_GRADE_SPECIAL_CONFIG.CREATE_TIME is
'创建时间';

comment on column MOD_ASSET_GRADE_SPECIAL_CONFIG.UPDATE_USER is
'更新人';

comment on column MOD_ASSET_GRADE_SPECIAL_CONFIG.UPDATE_TIME is
'更新时间';

--LGD违约损失率表
drop table MOD_LGD_CONFIG ;
create table MOD_LGD_CONFIG
(
   SEQNO                VARCHAR2(50)         not null,
   ASSET_TYPE           VARCHAR2(50),
   ASSET_DETAIL_TYPE    VARCHAR2(50),
   IS_CONTAIN_PLEDGE    VARCHAR2(50),
   LGD                  NUMBER(30,6),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null
);

comment on table MOD_LGD_CONFIG is
'LGD违约损失率';
comment on column MOD_LGD_CONFIG.SEQNO is
'序列号';
comment on column MOD_LGD_CONFIG.ASSET_TYPE is
'资产类型(数据字典 证券品种_模型)';
comment on column MOD_LGD_CONFIG.ASSET_DETAIL_TYPE is
'资产细分类(数据字典 证券细分类_模型)';
comment on column MOD_LGD_CONFIG.IS_CONTAIN_PLEDGE is
'是否含有抵质押物';
comment on column MOD_LGD_CONFIG.LGD is
'LGD(%)';
comment on column MOD_LGD_CONFIG.CREATE_USER is
'创建人';
comment on column MOD_LGD_CONFIG.CREATE_DEPT is
'创建部门';
comment on column MOD_LGD_CONFIG.CREATE_TIME is
'创建时间';
comment on column MOD_LGD_CONFIG.UPDATE_USER is
'更新人';
comment on column MOD_LGD_CONFIG.UPDATE_TIME is
'更新时间';


--MOD_ASEET_MAPPING字段必填项修改
alter table MOD_ASEET_MAPPING modify input1 not null;
alter table MOD_ASEET_MAPPING modify input2 not null;
alter table MOD_ASEET_MAPPING modify output1 not null;
alter table MOD_ASEET_MAPPING modify output2 not null;
alter table MOD_ASEET_MAPPING modify output3 not null;
alter table MOD_ASEET_MAPPING modify output4 not null;
alter table MOD_ASEET_MAPPING modify output5 not null;

--MOD_ASEET_MAPPING创建索引
drop index MOD_ASEET_MAPPING_INDEX;
drop index MOD_ASEET_MAPPING_INDEX1;
create index MOD_ASEET_MAPPING_INDEX on MOD_ASEET_MAPPING (MAPPING_TYPE, INPUT1, INPUT2);
create index MOD_ASEET_MAPPING_INDEX1 on MOD_ASEET_MAPPING (MAPPING_TYPE, OUTPUT5);

--MOD_CURVE_MAPPING字段必填项修改
alter table MOD_CURVE_MAPPING modify curve_group_code not null;
alter table MOD_CURVE_MAPPING modify curve_code not null;
alter table MOD_CURVE_MAPPING modify curve_type not null;
alter table MOD_CURVE_MAPPING modify grade_result not null;

--MOD_CURVE_MAPPING创建索引
drop index MOD_CURVE_MAPPING_INDEX;
create index MOD_CURVE_MAPPING_INDEX on MOD_CURVE_MAPPING (CURVE_GROUP_CODE, CURVE_TYPE, GRADE_RESULT);

--MOD_CURVE_CONFIG字段必填项修改
alter table MOD_CURVE_CONFIG modify curve_group_code not null;
alter table MOD_CURVE_CONFIG modify curve_type not null;
alter table MOD_CURVE_CONFIG modify issmooth not null;

--MOD_CURVE_CONFIG创建索引
drop index MOD_CURVE_CONFIG_INDEX;
drop index MOD_CURVE_CONFIG_INDEX1;
create index MOD_CURVE_CONFIG_INDEX on MOD_CURVE_CONFIG (SEC_TYPE_MODEL, SEC_DETAIL_TYPE_MODEL);
create index MOD_CURVE_CONFIG_INDEX1 on MOD_CURVE_CONFIG (SEC_TYPE_MODEL, SEC_DETAIL_TYPE_MODEL, FINPROD_ID);

--MOD_BINTREE_CONFIG字段必填项修改
alter table MOD_BINTREE_CONFIG modify finprod_id not null;
alter table MOD_BINTREE_CONFIG modify stock_bordsttus not null;
alter table MOD_BINTREE_CONFIG modify bintree_step not null;
alter table MOD_BINTREE_CONFIG modify curve_code not null;

--MOD_BINTREE_CONFIG创建索引
drop index MOD_BINTREE_CONFIG_INDEX;
create index MOD_BINTREE_CONFIG_INDEX on MOD_BINTREE_CONFIG (FINPROD_ID);

--MOD_RISK_PREMIUM字段必填项修改
alter table MOD_RISK_PREMIUM modify sec_detail_type_model not null;
alter table MOD_RISK_PREMIUM modify finprod_id not null;
alter table MOD_RISK_PREMIUM modify grade_source not null;
alter table MOD_RISK_PREMIUM modify grade_result not null;
alter table MOD_RISK_PREMIUM modify basedate not null;
alter table MOD_RISK_PREMIUM modify spread not null;
alter table MOD_RISK_PREMIUM modify b_fullprice not null;
alter table MOD_RISK_PREMIUM modify mod_method not null;

--MOD_RISK_PREMIUM字段必填项修改
drop index MOD_RISK_PREMIUM_INDEX;
create index MOD_RISK_PREMIUM_INDEX on MOD_RISK_PREMIUM (FINPROD_ID, BASEDATE, MOD_METHOD);

--MOD_RISK_PREMIUM创建索引
alter table MOD_STOCKDIVITRATE_CONFIG modify finprod_id not null;
alter table MOD_STOCKDIVITRATE_CONFIG modify stock_intrate not null;

--MOD_STOCKDIVITRATE_CONFIG字段必填项修改
drop index STOCKDIVITRATE_INDEX;
--MOD_STOCKDIVITRATE_CONFIG创建索引
create index STOCKDIVITRATE_INDEX on MOD_STOCKDIVITRATE_CONFIG (FINPROD_ID);

--MOD_CURVE_RESULT_DETAIL创建索引
drop index MOD_CURVE_RESULT_DETAIL_INDEX;
create index MOD_CURVE_RESULT_DETAIL_INDEX on MOD_CURVE_RESULT_DETAIL (FINPROD_ID, CDATE);




drop index MOD_ASEET_MAPPING_INDEX1;
drop index MOD_ASEET_MAPPING_INDEX2;
create index MOD_ASEET_MAPPING_INDEX1 on MOD_ASEET_MAPPING (MAPPING_TYPE, INPUT1, INPUT2);
create index MOD_ASEET_MAPPING_INDEX2 on MOD_ASEET_MAPPING (MAPPING_TYPE, OUTPUT5);

alter table MOD_ASSET_GRADE_CONFIG modify asset_type not null;
alter table MOD_ASSET_GRADE_CONFIG modify grade_org_id not null;
alter table MOD_ASSET_GRADE_CONFIG modify o_type not null;
alter table MOD_ASSET_GRADE_CONFIG modify create_time not null;
alter table MOD_ASSET_GRADE_CONFIG modify update_user not null;
alter table MOD_ASSET_GRADE_CONFIG modify update_time not null;

drop index MOD_ASSET_GRADE_CONFIG_INDEX;
create index MOD_ASSET_GRADE_CONFIG_INDEX on MOD_ASSET_GRADE_CONFIG (ASSET_TYPE, ASSET_DETAIL_TYPE);


alter table MOD_ASSET_GRADE_SPECIAL_CONFIG modify finprod_id not null;
alter table MOD_ASSET_GRADE_SPECIAL_CONFIG modify finprod_name not null;
alter table MOD_ASSET_GRADE_SPECIAL_CONFIG modify grade_org_id not null;
alter table MOD_ASSET_GRADE_SPECIAL_CONFIG modify o_type not null;

drop index MOD_SPECIAL_CONFIG_INDEX;
create index MOD_SPECIAL_CONFIG_INDEX on MOD_ASSET_GRADE_SPECIAL_CONFIG (FINPROD_ID);

alter table MOD_GRADE_PD_CONFIG modify vouch_year not null;
alter table MOD_GRADE_PD_CONFIG modify grade_result not null;
alter table MOD_GRADE_PD_CONFIG modify forward_year_pd not null;

alter table MOD_PD_FACTOR_CONFIG modify vouch_year not null;
alter table MOD_PD_FACTOR_CONFIG modify vouch_quarter not null;
alter table MOD_PD_FACTOR_CONFIG modify optimistic_factor not null;
alter table MOD_PD_FACTOR_CONFIG modify basic_factor not null;
alter table MOD_PD_FACTOR_CONFIG modify pessimism_factor not null;

alter table MOD_LGD_CONFIG modify asset_type not null;


alter table MOD_LGD_SPECIAL_CONFIG modify finprod_id not null;
alter table MOD_LGD_SPECIAL_CONFIG modify lgd not null;

drop index MOD_LGD_SPECIAL_CONFIG_INDEX;
create index MOD_LGD_SPECIAL_CONFIG_INDEX on MOD_LGD_SPECIAL_CONFIG (FINPROD_ID);


alter table MOD_STAGE_PARA modify stage_of_asset not null;
alter table MOD_STAGE_PARA modify absolute_of_elements not null;
alter table MOD_STAGE_PARA modify elements not null;

alter table MOD_STANDARD_GRADE_MAPPING modify grade_inst not null;
alter table MOD_STANDARD_GRADE_MAPPING modify o_type not null;
alter table MOD_STANDARD_GRADE_MAPPING modify grade_result not null;
alter table MOD_STANDARD_GRADE_MAPPING modify grade_standard_result not null;

drop index MOD_STANDARD_GRADE_MAPPING_INDEX;
create index MOD_STANDARD_GRADE_MAPPING_INDEX on MOD_STANDARD_GRADE_MAPPING (GRADE_INST, O_TYPE, GRADE_RESULT);

alter table MOD_PD_WEIGHT_CONFIG modify optimistic_weight not null;
alter table MOD_PD_WEIGHT_CONFIG modify basic_weight not null;
alter table MOD_PD_WEIGHT_CONFIG modify pessimism_weight not null;

alter table MOD_CONFIG_USEAGE modify config_seqno not null;
alter table MOD_CONFIG_USEAGE modify config_type not null;
alter table MOD_CONFIG_USEAGE modify use_process not null;

drop index MOD_CONFIG_USEAGE_INDEX;
create index MOD_CONFIG_USEAGE_INDEX on MOD_CONFIG_USEAGE (CONFIG_SEQNO, CONFIG_TYPE, USE_PROCESS);

alter table MOD_ECL_RESULT modify prod_id not null;
alter table MOD_ECL_RESULT modify prod_name not null;
alter table MOD_ECL_RESULT modify asset_type not null;
alter table MOD_ECL_RESULT modify portfolio_id not null;
alter table MOD_ECL_RESULT modify buss_no not null;
alter table MOD_ECL_RESULT modify trade_date not null;
alter table MOD_ECL_RESULT modify dec_date not null;
alter table MOD_ECL_RESULT modify allow_model_cover not null;

drop index MOD_ECL_RESULT_INDEX;
create index MOD_ECL_RESULT_INDEX on MOD_ECL_RESULT (PORTFOLIO_ID, DEC_DATE);

drop index MOD_ECL_RESULT_PROCESS_INDEX;
create index MOD_ECL_RESULT_PROCESS_INDEX on MOD_ECL_RESULT_PROCESS (DEC_DATE, PROCESS);

--修改估值模型二叉树模型参数维护表字段长度
alter table MOD_BINTREE_CONFIG MODIFY FINPROD_NAME VARCHAR2(200);
alter table MOD_BINTREE_CONFIG MODIFY COMP_ORGNAME VARCHAR2(200);

--修改估值模型风险溢价数据证券细分类可为空
alter table MOD_RISK_PREMIUM modify SEC_DETAIL_TYPE_MODEL null;

--资产减值结果表字段可空修改
alter table MOD_ECL_RESULT modify allow_model_cover null;

--股票行情表添加市盈率字段
alter table MST_SHARE_PRICE add PE NUMBER(26,4);
comment on column MST_SHARE_PRICE.PE is '市盈率';
--股票行情表添加市净率字段
alter table MST_SHARE_PRICE add PB NUMBER(26,4);
comment on column MST_SHARE_PRICE.PB is '市净率';