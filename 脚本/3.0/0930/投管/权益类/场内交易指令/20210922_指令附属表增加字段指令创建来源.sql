--ETR_ENTRUST_ADD新增字段:指令创建来源
alter table etr_entrust_add add (ENTRUST_CREATE_SOURCE VARCHAR2(10));
comment on column etr_entrust_add.ENTRUST_CREATE_SOURCE is '指令创建来源';