--增加当前头寸映射表，用以切日回退撤账时，提供当前头寸表数据
CREATE TABLE "JXT_TRAN_SUM_RECORD" 
(	
	"ID" NUMBER(10,0) NOT NULL ENABLE, 
	"LEDGERDATE" DATE,
	"PORTFOLIO" VARCHAR2(50), 
	"CUSIP" VARCHAR2(50),
	"LOTNUM" NUMBER(10,0), 
	"LST_TRANNUM" NUMBER(10,0), 	
	"ALTDATE" DATE, 
	"ALTRATE" NUMBER(30,14), 
	"AMOUNT" NUMBER(30,4), 
	"AMOUNT2" NUMBER(30,4), 
	"FXTRANFLG" NUMBER(1,0), 
	"G_COST" NUMBER(30,2), 
	"GCOST2" NUMBER(30,2), 
	"G_COSTFUNC" NUMBER(30,2), 
	"GCOSTFUNC2" NUMBER(30,2), 
	"LASTACTIVETYDATE" DATE, 
	"LSINDICATOR" NUMBER(1,0), 
	"LST_LEDGERDATE" DATE, 
	"LST_SETTLEDATE" DATE, 
	"LSTTRADE_DATE" DATE, 
	"MANAGERID" NUMBER(10,0), 
	"POSCLOSED" VARCHAR2(1), 
	"SECACCT" NUMBER(10,0), 
	"VALUATIONTYPE" NUMBER(10,0), 
	"CREATE_TIME" TIMESTAMP (6), 
	"CREATE_DEPT" VARCHAR2(50), 
	"UPDATE_TIME" TIMESTAMP (6), 
	"UPDATE_USER" VARCHAR2(20), 
	"CREATE_USER" VARCHAR2(20), 
	PRIMARY KEY ("ID")
);

CREATE INDEX "JXT_TRAN_SUM_RECORD_INDEX1" ON "JXT_TRAN_SUM_RECORD" ("PORTFOLIO", "LEDGERDATE");


comment on column "JXT_TRAN_SUM_RECORD"."ID" is '唯一主键';
comment on column "JXT_TRAN_SUM_RECORD"."LEDGERDATE" is '备份记录日';
comment on column "JXT_TRAN_SUM_RECORD"."PORTFOLIO" is '投资组合系统编号';
comment on column "JXT_TRAN_SUM_RECORD"."CUSIP" is '证券编号';
comment on column "JXT_TRAN_SUM_RECORD"."LOTNUM" is '同一个产品投资同一个证券,因为证券账户、基金经理等不同，区分头寸的标记。具体算法根据标交易的不同而不同 | 关联Tran_Basic表的lot字段 ';
comment on column "JXT_TRAN_SUM_RECORD"."LST_TRANNUM" is '';
comment on column "JXT_TRAN_SUM_RECORD"."ALTDATE" is '对于债券(摊余成本)、回购等交易，每次买入都会生成一笔新的头寸，altdate记录到期日期 ';
comment on column "JXT_TRAN_SUM_RECORD"."ALTRATE" is '对于债券(摊余成本)、回购等交易，每次买入都会生成一笔新的头寸，altrate记录实际利率 ';
comment on column "JXT_TRAN_SUM_RECORD"."AMOUNT" is '头寸数量';
comment on column "JXT_TRAN_SUM_RECORD"."AMOUNT2" is '空方头寸数量';
comment on column "JXT_TRAN_SUM_RECORD"."FXTRANFLG" is '是否为换汇/互换交易 | 1:换汇/互换 0:其它交易 ';
comment on column "JXT_TRAN_SUM_RECORD"."G_COST" is '头寸的原币成本,每钟证券类型有自己的成本计算方法。具体实现在Post类 ';
comment on column "JXT_TRAN_SUM_RECORD"."GCOST2" is '空方成本';
comment on column "JXT_TRAN_SUM_RECORD"."G_COSTFUNC" is '头寸的本币成本,每钟证券类型有自己的成本计算方法。具体实现在Post类 ';
comment on column "JXT_TRAN_SUM_RECORD"."GCOSTFUNC2" is '空方本币成本';
comment on column "JXT_TRAN_SUM_RECORD"."LASTACTIVETYDATE" is '最后激活的日期在债券里,卖空之后可能还会计息,等计息结束,再通过判断这个日期参数,决定是否关闭交易 ';
comment on column "JXT_TRAN_SUM_RECORD"."LSINDICATOR" is '该交易是买多还是卖空 | 1:买多 2:卖空 ';
comment on column "JXT_TRAN_SUM_RECORD"."LST_LEDGERDATE" is '头寸最后一次交易的入账日期 ';
comment on column "JXT_TRAN_SUM_RECORD"."LST_SETTLEDATE" is '头寸最后一次交易的结算日期 ';
comment on column "JXT_TRAN_SUM_RECORD"."LSTTRADE_DATE" is '头寸最后一次交易的交易日期';
comment on column "JXT_TRAN_SUM_RECORD"."MANAGERID" is '基金经理';
comment on column "JXT_TRAN_SUM_RECORD"."POSCLOSED" is '该头寸是否已被关闭';
comment on column "JXT_TRAN_SUM_RECORD"."SECACCT" is '证券账户';
comment on column "JXT_TRAN_SUM_RECORD"."VALUATIONTYPE" is '估值方法';
comment on column "JXT_TRAN_SUM_RECORD"."CREATE_TIME" is '创建时间';
comment on column "JXT_TRAN_SUM_RECORD"."CREATE_DEPT" is '创建部门';
comment on column "JXT_TRAN_SUM_RECORD"."UPDATE_TIME" is '更新时间';
comment on column "JXT_TRAN_SUM_RECORD"."UPDATE_USER" is '更新用户';
comment on column "JXT_TRAN_SUM_RECORD"."CREATE_USER" is '创建用户';


--应收应付表的索引
CREATE INDEX "JXB_BKCASHRCPY_INDEX3" ON "JXB_BK_CASH_RCPY" ("PORTFOLIO", "LEDGER");
CREATE INDEX "JXT_TRANSUM_INDEX3" ON "JXT_TRAN_SUM" ("PORTFOLIO");
CREATE INDEX "JXT_TRAN_INDEX7" ON "JXT_TRAN" ("PORTFOLIO","LEDGER_DATE");
CREATE INDEX "JXT_TRANBASIC_INDEX4" ON "JXT_TRAN_BASIC" ("PORTFOLIO","LEDGERDATE","POST_STATUS");

