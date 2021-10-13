
--新增字段:业务模式
alter table ETR_ENTRUST_BACKUP add BUSINESS_MODEL varchar2(50);

--新增字段:指令创建来源
alter table ETR_ENTRUST_ADD_BACKUP add (ENTRUST_CREATE_SOURCE VARCHAR2(10));