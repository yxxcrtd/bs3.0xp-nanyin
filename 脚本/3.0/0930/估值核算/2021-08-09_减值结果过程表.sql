drop table MOD_ECL_RESULT_PROCESS cascade constraints;
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




