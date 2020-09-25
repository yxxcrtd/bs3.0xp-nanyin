--适配器导出功能增加字段，判断上游空数据时，是否生成空的文件

alter table ADPT_INTERFACESOURCE ADD (OUTNODATAGENFILEFLAG NUMBER(1,0) default 0);
comment on column "ADPT_INTERFACESOURCE"."OUTNODATAGENFILEFLAG" is '上游空数据，是否生成空文件';
