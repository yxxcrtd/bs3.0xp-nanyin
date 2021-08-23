--资产减值流水增删字段，删除[市场代码] 新增[金融产品代码、代偿期类型、期限描述]
alter table TRD_ASSET_LOSS drop (SEC_ID);
alter table TRD_ASSET_LOSS add FINPROD_ID VARCHAR2(50);
comment on column TRD_ASSET_LOSS.FINPROD_ID is  '金融产品代码';
alter table TRD_ASSET_LOSS add PEROID_TYPE VARCHAR2(50);
comment on column TRD_ASSET_LOSS.PEROID_TYPE is  '代偿期类型';
alter table TRD_ASSET_LOSS add TERM_DESCRIBE VARCHAR2(50);
comment on column TRD_ASSET_LOSS.TERM_DESCRIBE is  '期限描述';

--数据字典—代偿期类型
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('PERIOD_TYPE','01','行权','1','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'), 'ALL');
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('PERIOD_TYPE','02','到期','2','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'), 'ALL');

insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PERIOD_TYPE', 'MOD', '代偿期类型', null, '02', 'system', null, '24-JUN-19 12.00.00.000000 AM', 'system', '24-JUN-19 12.00.00.000000 AM');

--数据字典—减值数据来源
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('LOSS_INPUT_TYPE','01','手工','1','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'), 'ALL');
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('LOSS_INPUT_TYPE','02','中债/wind接入','2','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'), 'ALL');

insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LOSS_INPUT_TYPE', 'MOD', '减值数据来源', null, '02', 'system', null, '24-JUN-19 12.00.00.000000 AM', 'system', '24-JUN-19 12.00.00.000000 AM');


--删除按钮工作流配置
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('TrdAssetLoss002', 'TrdAssetLoss001_btn_delete', 'oneApproval', 'system', null, '12-MAY-21 03.56.10.000000 PM', 'system', '12-MAY-21 03.56.10.000000 PM', 'ALL', '02');