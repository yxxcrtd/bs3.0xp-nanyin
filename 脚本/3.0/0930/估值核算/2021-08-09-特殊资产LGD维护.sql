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
