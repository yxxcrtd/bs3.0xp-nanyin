
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
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_CURVE_CONFIG primary key (SEQNO) 
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
