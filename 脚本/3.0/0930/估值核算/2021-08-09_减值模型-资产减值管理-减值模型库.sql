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
   UPDATE_TIME          timestamp                      not null,
   constraint PK_MOD_ECL_RESULT primary key (SEQNO)
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
   UPDATE_TIME          timestamp                      not null,
   constraint PK_TRD_ASSET_LOSS primary key (SEQ_NO)
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
