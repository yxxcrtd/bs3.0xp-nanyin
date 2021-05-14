----成交明细表
ALTER TABLE INF_CONTRACT_DETAILFILE ADD SCALE NUMBER ;
COMMENT ON COLUMN INF_CONTRACT_DETAILFILE.SCALE IS '折算比例';

----新增质押券置换临时表
drop table FIN_PLEDGE_DISP_TEMP cascade constraints;

/*==============================================================*/
/* Table: FIN_PLEDGE_DISP_TEMP                                  */
/*==============================================================*/
create table FIN_PLEDGE_DISP_TEMP 
(
   TRADE_ID             VARCHAR(32)          not null,
   F_TRADE_ID           VARCHAR(32),
   TRADE_DATE           DATE,
   EXCHANGE_FEE         NUMERIC(30, 14),
   REMARK               VARCHAR(1000),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_PLEDGE_DISP_TEMP primary key (TRADE_ID)
);

comment on table FIN_PLEDGE_DISP_TEMP is
'质押券置换临时表';

comment on column FIN_PLEDGE_DISP_TEMP.TRADE_ID is
'业务编号';

comment on column FIN_PLEDGE_DISP_TEMP.F_TRADE_ID is
'原始业务编号';

comment on column FIN_PLEDGE_DISP_TEMP.TRADE_DATE is
'置换日期';

comment on column FIN_PLEDGE_DISP_TEMP.EXCHANGE_FEE is
'置换费';

comment on column FIN_PLEDGE_DISP_TEMP.REMARK is
'备注';

comment on column FIN_PLEDGE_DISP_TEMP.CREATE_USER is
'创建人';

comment on column FIN_PLEDGE_DISP_TEMP.CREATE_DEPT is
'创建部门';

comment on column FIN_PLEDGE_DISP_TEMP.CREATE_TIME is
'创建时间';

comment on column FIN_PLEDGE_DISP_TEMP.UPDATE_USER is
'更新人';

comment on column FIN_PLEDGE_DISP_TEMP.UPDATE_TIME is
'更新时间';


----新增质押券置换临时表
drop table FIN_PLEDGE_DISP_DETAIL_TEMP cascade constraints;

/*==============================================================*/
/* Table: FIN_PLEDGE_DISP_DETAIL_TEMP                           */
/*==============================================================*/
create table FIN_PLEDGE_DISP_DETAIL_TEMP 
(
   SEQ_NO               VARCHAR(32)          not null,
   TRADE_ID             VARCHAR(32),
   FINPROD_ID           VARCHAR(50),
   BRANCH               NUMERIC(20),
   TRADE_TYPE           VARCHAR(50),
   PLEDGE_DISPLACE_AMT  NUMERIC(30, 14),
   SCALE                NUMERIC(30, 14),
   FIRST_NETPRICE       NUMERIC(30, 14),
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_FIN_PLEDGE_DISP_DETAIL_TEMP primary key (SEQ_NO)
);

comment on table FIN_PLEDGE_DISP_DETAIL_TEMP is
'质押券置换明细临时表';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.SEQ_NO is
'流水号';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.TRADE_ID is
'业务编号';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.FINPROD_ID is
'质押金融产品代码';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.BRANCH is
'分支序号';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.TRADE_TYPE is
'置换方向';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.PLEDGE_DISPLACE_AMT is
'换出换入面额';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.SCALE is
'折算比例';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.FIRST_NETPRICE is
'首次净价';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.CREATE_USER is
'创建人';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.CREATE_DEPT is
'创建部门';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.CREATE_TIME is
'创建时间';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.UPDATE_USER is
'更新人';

comment on column FIN_PLEDGE_DISP_DETAIL_TEMP.UPDATE_TIME is
'更新时间';
