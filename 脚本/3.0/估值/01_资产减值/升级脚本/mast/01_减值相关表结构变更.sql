drop table TRD_ASSET_LOSS cascade constraints;

/*==============================================================*/
/* Table: TRD_ASSET_LOSS                                        */
/*==============================================================*/
create table TRD_ASSET_LOSS 
(
   TRADE_ID             VARCHAR(32)          not null,
   SEC_ID               VARCHAR(50),
   STAGE                VARCHAR(20),
   PEROID               NUMERIC(30, 14),
   VAL_DATE             DATE,
   STR_DATE             DATE,
   END_DATE             DATE,
   LOSS_TEST_TYPE       VARCHAR(50),
   LOSS_INPUT_TYPE      VARCHAR(50),
   LOSS_RATIO           NUMERIC(30, 14),
   PD_VAULE             NUMERIC(30, 14),
   LGD_VAULE            NUMERIC(30, 14),
   ECL_VAULE            NUMERIC(30, 14),
   STATUS               VARCHAR(50),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_TRD_ASSET_LOSS primary key (TRADE_ID)
);

comment on table TRD_ASSET_LOSS is
'资产减值流水';

comment on column TRD_ASSET_LOSS.TRADE_ID is
'交易代码';

comment on column TRD_ASSET_LOSS.SEC_ID is
'市场代码';

comment on column TRD_ASSET_LOSS.STAGE is
'减值阶段';

comment on column TRD_ASSET_LOSS.PEROID is
'代偿期';

comment on column TRD_ASSET_LOSS.VAL_DATE is
'行情日';

comment on column TRD_ASSET_LOSS.STR_DATE is
'区间开始日';

comment on column TRD_ASSET_LOSS.END_DATE is
'区间结束日';

comment on column TRD_ASSET_LOSS.LOSS_TEST_TYPE is
'减值测算方式';

comment on column TRD_ASSET_LOSS.LOSS_INPUT_TYPE is
'减值录入方式';

comment on column TRD_ASSET_LOSS.LOSS_RATIO is
'减值比例';

comment on column TRD_ASSET_LOSS.PD_VAULE is
'PD违约概率';

comment on column TRD_ASSET_LOSS.LGD_VAULE is
'LGD违约损失率';

comment on column TRD_ASSET_LOSS.ECL_VAULE is
'ECL预期信用损失比例';

comment on column TRD_ASSET_LOSS.STATUS is
'状态，初始、撤销';

comment on column TRD_ASSET_LOSS.CREATE_USER is
'创建人';

comment on column TRD_ASSET_LOSS.CREATE_DEPT is
'创建部门';

comment on column TRD_ASSET_LOSS.CREATE_TIME is
'创建时间';

comment on column TRD_ASSET_LOSS.UPDATE_USER is
'更新人';

comment on column TRD_ASSET_LOSS.UPDATE_TIME is
'更新时间';
drop table BOK_STAT_LOSS_POSIITON cascade constraints;

/*==============================================================*/
/* Table: BOK_STAT_LOSS_POSIITON                                */
/*==============================================================*/
create table BOK_STAT_LOSS_POSIITON 
(
   SEQNO                VARCHAR(32)          not null,
   BOK_SEQNO            VARCHAR(32),
   BOOKSET_ID           VARCHAR(50),
   HAPPEN_DATE          DATE,
   BOOK_DATE            DATE,
   BOOKSET_DATE         DATE,
   BOK_DETAIL_ID        VARCHAR(32),
   BUSI_ID              VARCHAR(32),
   SCHEME_DETAIL_ID     VARCHAR(32),
   TRADE_ID             VARCHAR(32),
   LOSS_EXE_TYPE        VARCHAR(50),
   CCY                  VARCHAR(50),
   B_CCY                VARCHAR(50),
   TDY_COST_AMT         NUMERIC(30, 2),
   TDY_INT_AMT          NUMERIC(30, 2),
   TDY_LOSSAMT_ADD      NUMERIC(30, 2),
   TDY_LOSSAMT          NUMERIC(30, 2),
   TDY_LOSSAMT_ADD_B    NUMERIC(30, 2),
   TDY_LOSSAMT_B        NUMERIC(30, 2),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_STAT_LOSS_POSIITON primary key (SEQNO)
);

comment on table BOK_STAT_LOSS_POSIITON is
'统计分析明细表（资产减值）';

comment on column BOK_STAT_LOSS_POSIITON.SEQNO is
'流水';

comment on column BOK_STAT_LOSS_POSIITON.BOK_SEQNO is
'账务明细流水';

comment on column BOK_STAT_LOSS_POSIITON.BOOKSET_ID is
'账套代码';

comment on column BOK_STAT_LOSS_POSIITON.HAPPEN_DATE is
'发生日期';

comment on column BOK_STAT_LOSS_POSIITON.BOOK_DATE is
'入账日期';

comment on column BOK_STAT_LOSS_POSIITON.BOOKSET_DATE is
'账套日期';

comment on column BOK_STAT_LOSS_POSIITON.BOK_DETAIL_ID is
'账务明细代码';

comment on column BOK_STAT_LOSS_POSIITON.BUSI_ID is
'业务明细代码';

comment on column BOK_STAT_LOSS_POSIITON.SCHEME_DETAIL_ID is
'方案明细代码';

comment on column BOK_STAT_LOSS_POSIITON.TRADE_ID is
'交易编号';

comment on column BOK_STAT_LOSS_POSIITON.LOSS_EXE_TYPE is
'减值处理方式：减值处理方式：减值，减值使用、清理核销';

comment on column BOK_STAT_LOSS_POSIITON.CCY is
'原币';

comment on column BOK_STAT_LOSS_POSIITON.B_CCY is
'本币';

comment on column BOK_STAT_LOSS_POSIITON.TDY_COST_AMT is
'当日账面本金价值';

comment on column BOK_STAT_LOSS_POSIITON.TDY_INT_AMT is
'当日账面应收利息';

comment on column BOK_STAT_LOSS_POSIITON.TDY_LOSSAMT_ADD is
'当日减值金额';

comment on column BOK_STAT_LOSS_POSIITON.TDY_LOSSAMT is
'当日累计减值金额';

comment on column BOK_STAT_LOSS_POSIITON.TDY_LOSSAMT_ADD_B is
'当日减值金额_本位币';

comment on column BOK_STAT_LOSS_POSIITON.TDY_LOSSAMT_B is
'当日累计减值金额当日累计减值金额_本位币';

comment on column BOK_STAT_LOSS_POSIITON.CREATE_USER is
'创建人';

comment on column BOK_STAT_LOSS_POSIITON.CREATE_DEPT is
'创建部门';

comment on column BOK_STAT_LOSS_POSIITON.CREATE_TIME is
'创建时间';

comment on column BOK_STAT_LOSS_POSIITON.UPDATE_USER is
'更新人';

comment on column BOK_STAT_LOSS_POSIITON.UPDATE_TIME is
'更新时间';

/*==============================================================*/
/* Table: BOK_STAT_DET_POSIITON 增加利息调整字段及减值字段      */
/*==============================================================*/
alter table BOK_STAT_DET_POSIITON add TDY_INTADJEXP_ADD NUMERIC(30, 2);
alter table BOK_STAT_DET_POSIITON add TDY_INTADJEXP_ADD_B NUMERIC(30, 2);
alter table BOK_STAT_DET_POSIITON add TDY_LOSS_AMT NUMERIC(30, 2);
alter table BOK_STAT_DET_POSIITON add TDY_LOSS_AMT_B NUMERIC(30, 2);

comment on column BOK_STAT_DET_POSIITON.TDY_INTADJEXP_ADD is
'当日发生利息调整';
comment on column BOK_STAT_DET_POSIITON.TDY_LOSS_AMT is
'减值余额';
comment on column BOK_STAT_DET_POSIITON.TDY_INTADJEXP_ADD_B is
'当日发生利息调整_本位币';
comment on column BOK_STAT_DET_POSIITON.TDY_LOSS_AMT_B is
'减值余额_本位币';

drop table FIN_PRODUCT_LOSS_STAGE ;

/*==============================================================*/
/* Table: FIN_PRODUCT_LOSS_STAGE                            */
/*==============================================================*/
create table FIN_PRODUCT_LOSS_STAGE 
(
   SEQ_NO     VARCHAR(32)          not null,
   FINPROD_ID           VARCHAR(50),
   STAGE                VARCHAR(20),
   EFF_DATE             DATE,
   INVALID_DATE         DATE,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_PRODUCT_LOSS_STAGE primary key (SEQ_NO)
);

comment on table FIN_PRODUCT_LOSS_STAGE is
'减值阶段管理';

comment on column FIN_PRODUCT_LOSS_STAGE.SEQ_NO is
'流水号';

comment on column FIN_PRODUCT_LOSS_STAGE.FINPROD_ID is
'金融产品代码';

comment on column FIN_PRODUCT_LOSS_STAGE.STAGE is
'阶段';

comment on column FIN_PRODUCT_LOSS_STAGE.EFF_DATE is
'生效日';

comment on column FIN_PRODUCT_LOSS_STAGE.INVALID_DATE is
'失效日';


comment on column FIN_PRODUCT_LOSS_STAGE.CREATE_USER is
'创建人';

comment on column FIN_PRODUCT_LOSS_STAGE.CREATE_DEPT is
'创建部门';

comment on column FIN_PRODUCT_LOSS_STAGE.CREATE_TIME is
'创建时间';

comment on column FIN_PRODUCT_LOSS_STAGE.UPDATE_USER is
'更新人';

comment on column FIN_PRODUCT_LOSS_STAGE.UPDATE_TIME is
'更新时间';

alter table PTL_SEC_VALUTION add tdy_loss_amt_b NUMBER(30,2);
alter table PTL_SEC_VALUTION add tdy_loss_amt NUMBER(30,2);
comment on column PTL_SEC_VALUTION.tdy_loss_amt_b
  is '减值余额_本币';
comment on column PTL_SEC_VALUTION.tdy_loss_amt
  is '减值余额';
  
alter table TRD_DEAL_TOTAL_ADD add CLEAR_REC_AMT NUMBER(30,2);
alter table TRD_DEAL_TOTAL_ADD add CLEAR_LOSS_AMT NUMBER(30,2);
-- Add comments to the columns 
comment on column TRD_DEAL_TOTAL_ADD.CLEAR_REC_AMT
  is '清理应收款';
comment on column TRD_DEAL_TOTAL_ADD.CLEAR_LOSS_AMT
  is '清理减值';
  
DROP SEQUENCE LOSS_SEQUENCE;
CREATE SEQUENCE  LOSS_SEQUENCE  MINVALUE 1 MAXVALUE 999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;

alter table BOK_STAT_LOSS_POSIITON add LOSS_GEN_TYPE VARCHAR(50);
comment on column BOK_STAT_LOSS_POSIITON.LOSS_GEN_TYPE is '减值生成方式';