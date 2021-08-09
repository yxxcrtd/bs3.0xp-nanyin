drop table MOD_BINTREE_CONFIG cascade constraints;

/*==============================================================*/
/* Table: MOD_BINTREE_CONFIG                                    */
/*==============================================================*/
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
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_BINTREE_CONFIG primary key (SEQNO)
  
)
;

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
