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

drop table MOD_LGD_CONFIG cascade constraints;

/*==============================================================*/
/* Table: MOD_LGD_CONFIG                                        */
/*==============================================================*/
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
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_LGD_CONFIG primary key (SEQNO)
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
