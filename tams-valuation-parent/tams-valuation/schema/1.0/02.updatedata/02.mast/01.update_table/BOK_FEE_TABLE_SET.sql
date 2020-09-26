drop table FEE_TABLE_SET cascade constraints;
drop table BOK_FEE_TABLE_SET cascade constraints;

/*==============================================================*/
/* Table: BOK_FEE_TABLE_SET                                     */
/*==============================================================*/
create table BOK_FEE_TABLE_SET 
(
   FINPROD_TYPE         VARCHAR(50)          not null,
   PS                   VARCHAR(50)          not null,
   FEE_TYPE_ID          VARCHAR(50)          not null,
   TABLE_ID             VARCHAR(32),
   BASE_SUBJECT_NATURE  VARCHAR(100)         not null,
   CREATE_USER          VARCHAR(20)          not null,
   CREATE_DEPT          VARCHAR(32),
   CREATE_TIME          TIMESTAMP            not null,
   UPDATE_USER          VARCHAR(20)          not null,
   UPDATE_TIME          TIMESTAMP            not null,
   constraint PK_BOK_FEE_TABLE_SET primary key (FINPROD_TYPE, PS, FEE_TYPE_ID, BASE_SUBJECT_NATURE)
);

comment on table BOK_FEE_TABLE_SET is
'费用场景配置表';

comment on column BOK_FEE_TABLE_SET.FINPROD_TYPE is
'金融产品类型';

comment on column BOK_FEE_TABLE_SET.PS is
'交易方向';

comment on column BOK_FEE_TABLE_SET.FEE_TYPE_ID is
'费用类型代码';

comment on column BOK_FEE_TABLE_SET.TABLE_ID is
'场景代码';

comment on column BOK_FEE_TABLE_SET.BASE_SUBJECT_NATURE is
'基础科目性质';

comment on column BOK_FEE_TABLE_SET.CREATE_USER is
'创建人';

comment on column BOK_FEE_TABLE_SET.CREATE_DEPT is
'创建部门';

comment on column BOK_FEE_TABLE_SET.CREATE_TIME is
'创建时间';

comment on column BOK_FEE_TABLE_SET.UPDATE_USER is
'更新人';

comment on column BOK_FEE_TABLE_SET.UPDATE_TIME is
'更新时间';



insert into BOK_FEE_TABLE_SET values ('C11','T10','B01','3001','YFJSF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T10','B02','3001','YFYHS','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T10','B03','3001','YFZGF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T10','B05','3001','YFYJ','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T10','B06','3001','YFJYSXF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T10','B07','3001','YFJSF1','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T10','B08','3001','YFZZDJSSXF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T10','B08','3001','YFSQSJSSXF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T10','B08','3001','YFZDJSSXF','system','',to_date('2018/4/25', 'yyyy/mm/dd'), 'system',to_date('2018/4/25', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T20','B01','3002','YFJSF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T20','B02','3002','YFYHS','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T20','B03','3002','YFZGF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T20','B05','3002','YFYJ','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T20','B06','3002','YFJYSXF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T20','B07','3002','YFJSF1','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T20','B08','3002','YFZZDJSSXF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T20','B08','3002','YFSQSJSSXF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T20','B08','3002','YFZDJSSXF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T21','B01','3004','YFJSF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T21','B02','3004','YFYHS','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T21','B03','3004','YFZGF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T21','B05','3004','YFYJ','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T21','B06','3004','YFJYSXF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T21','B07','3004','YFJSF1','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T21','B08','3004','YFZZDJSSXF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T21','B08','3004','YFSQSJSSXF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C11','T21','B08','3004','YFZDJSSXF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C06','T10','C06','8102','YFXSFWF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C03','T10','C03','8104','YFGLRBC','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C04','T10','C04','8106','YFCEGLF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C05','T10','C05','8108','YFTGF1','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C10','T10','C10','8111','YFDXF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C09','T10','C09','8113','YFWBFWF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));


insert into BOK_FEE_TABLE_SET values ('C06','T00','C06','8101','YFXSFWF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C03','T00','C03','8103','YFGLRBC','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C04','T00','C04','8105','YFCEGLF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C05','T00','C05','8107','YFTGF1','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C10','T00','C10','8110','YFDXF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));
insert into BOK_FEE_TABLE_SET values ('C09','T00','C09','8112','YFWBFWF','system','',to_date('2018/7/5', 'yyyy/mm/dd'), 'system',to_date('2018/7/5', 'yyyy/mm/dd'));




