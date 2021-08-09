drop table MOD_STAGE_PARA cascade constraints;

/*==============================================================*/
/* Table: MOD_STAGE_PARA                                        */
/*==============================================================*/
create table MOD_STAGE_PARA 
(
   SEQNO                VARCHAR2(50)         not null,
   STAGE_OF_ASSET       VARCHAR2(50),
   ABSOLUTE_OF_ELEMENTS  VARCHAR2(50),
   ELEMENTS              VARCHAR2(50),
   ELEMENTS_VALUE        VARCHAR2(200),
   CREATE_USER          VARCHAR2(20)         not null,
   CREATE_DEPT          VARCHAR2(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR2(20)         not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_MOD_STAGE_PARA primary key (SEQNO)
);

comment on table MOD_STAGE_PARA is
'减值阶段参数';

comment on column MOD_STAGE_PARA.SEQNO is
'序列号';

comment on column MOD_STAGE_PARA.STAGE_OF_ASSET is
'资产所属阶段(数据字典 减值阶段)';

comment on column MOD_STAGE_PARA.ABSOLUTE_OF_ELEMENTS is
'要素绝对性(数据字典 要素绝对性)';

comment on column MOD_STAGE_PARA.ELEMENTS is
'要素(数据字典 减值要素)';

comment on column MOD_STAGE_PARA.ELEMENTS_VALUE is
'要素值';

comment on column MOD_STAGE_PARA.CREATE_USER is
'创建人';

comment on column MOD_STAGE_PARA.CREATE_DEPT is
'创建部门';

comment on column MOD_STAGE_PARA.CREATE_TIME is
'创建时间';

comment on column MOD_STAGE_PARA.UPDATE_USER is
'更新人';

comment on column MOD_STAGE_PARA.UPDATE_TIME is
'更新时间';

delete from MOD_STAGE_PARA;
insert into MOD_STAGE_PARA values ('MODELINIT00000000000','P3','01','01','90','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000001','P3','01','02','90','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000002','P3','01','03','20','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000003','P3','01','04','05','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000004','P2','01','01','30','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000005','P2','01','02','30','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000006','P2','01','03','19','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000007','P2','01','04','02','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));
insert into MOD_STAGE_PARA values ('MODELINIT00000000008','P2','02','05','3','system','',to_date('2021/6/22', 'yyyy/mm/dd'), 'system',to_date('2021/6/22', 'yyyy/mm/dd'));


commit;
