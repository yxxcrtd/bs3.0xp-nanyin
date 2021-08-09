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