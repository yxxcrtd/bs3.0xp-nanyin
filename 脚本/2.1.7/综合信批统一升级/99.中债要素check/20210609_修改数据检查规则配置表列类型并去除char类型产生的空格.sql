--修改列类型将 char 改为 varchar
alter table CHB_DATACHECK_CONDITION_INV modify (CHECKCONDITIONID varchar(36));
alter table CHB_DATACHECK_CONDITION_INV modify (CHECKRULEID varchar(64));                   
alter table CHB_DATACHECK_RULE_INV modify (CHECKRULEID varchar(64));

--去除char 类型自动补全的空格
update CHB_DATACHECK_CONDITION_INV set CHECKCONDITIONID = trim(CHECKCONDITIONID) where 1=1;
update CHB_DATACHECK_CONDITION_INV set CHECKRULEID = trim(CHECKRULEID) where 1=1;
update CHB_DATACHECK_RULE_INV set CHECKRULEID = trim(CHECKRULEID) where 1=1;