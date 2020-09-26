---增加系统参数
delete from SYS_PARA where PARA_CODE = 'IS_NAME_SOFTCONTROL';
insert into SYS_PARA values ('RSK','IS_NAME_SOFTCONTROL','true','01','','交易(委托)名单是否软控','system','',to_date('2019/11/19', 'yyyy/mm/dd'), 'admin',to_date('2019/11/19', 'yyyy/mm/dd'));

---增加字段
alter table RSK_ACCESS_LIST add LIST_LEVEL VARCHAR(50);
COMMENT ON COLUMN RSK_ACCESS_LIST.LIST_LEVEL IS '名单级别，一级库、二级库';

alter table WFL_RSK_ACCESS_LIST add LIST_LEVEL VARCHAR(50);
COMMENT ON COLUMN WFL_RSK_ACCESS_LIST.LIST_LEVEL IS '名单级别，一级库、二级库';


---增加数据字典明细
delete from SYS_DATADICT where DICT_CODE = 'LIST_TYPE';
delete from SYS_DATADICT_LIST where DICT_CODE = 'LIST_TYPE';
insert into SYS_DATADICT values ('LIST_TYPE','COM','名单属性','','02','system','',to_date('2019/8/6', 'yyyy/mm/dd'), 'system',to_date('2019/8/6', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('LIST_TYPE','01','白名单','1','0','system','',to_date('2019/6/17', 'yyyy/mm/dd'), 'system',to_date('2019/6/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('LIST_TYPE','02','黑名单','2','0','system','',to_date('2019/6/17', 'yyyy/mm/dd'), 'system',to_date('2019/6/17', 'yyyy/mm/dd'));


delete from SYS_DATADICT where DICT_CODE = 'LIST_LEVEL';
delete from SYS_DATADICT_LIST where DICT_CODE = 'LIST_LEVEL';
insert into SYS_DATADICT values ('LIST_LEVEL','COM','名单级别','','02','system','',to_date('2019/8/6', 'yyyy/mm/dd'), 'system',to_date('2019/8/6', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('LIST_LEVEL','01','一级库','1','0','system','',to_date('2019/6/17', 'yyyy/mm/dd'), 'system',to_date('2019/6/17', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('LIST_LEVEL','02','二级库','2','0','system','',to_date('2019/6/17', 'yyyy/mm/dd'), 'system',to_date('2019/6/17', 'yyyy/mm/dd'));


---更新流程模板
update SYS_WFL_BUSINESS_MODULE SET PAGE_ID = 'OrgNameManagement003' WHERE PAGE_ID  = 'OrgNameManagement002';
update SYS_WFL_BUSINESS_MODULE SET PAGE_ID = 'AssetNameManagement003' WHERE PAGE_ID  = 'AssetNameManagement002';