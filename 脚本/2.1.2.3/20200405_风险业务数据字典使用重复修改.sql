
delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'RSK26';
delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'RSK46';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('COMMON_BUSINESS_TYPE','RSK46','主体额度占用调整',726,'0','system',null,to_timestamp('2019-08-06 00:00:00','yyyy-MM-dd HH24:MI:SS'),'system',to_timestamp('2019-08-06 00:00:00','yyyy-MM-dd HH24:MI:SS'));
delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'RSK25';
delete from SYS_DATADICT_LIST where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'RSK45';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('COMMON_BUSINESS_TYPE','RSK45','单笔资产额度校验规则',725,'0','system',null,to_timestamp('2019-08-06 00:00:00','yyyy-MM-dd HH24:MI:SS'),'system',to_timestamp('2019-08-06 00:00:00','yyyy-MM-dd HH24:MI:SS'));

insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','RSK25','组合自动关联指标','32','0','system','',to_date('2019/8/14', 'yyyy/mm/dd'), 'system',to_date('2019/8/14', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('COMMON_BUSINESS_TYPE','RSK26','个性化参数','33','0','system','',to_date('2019/8/14', 'yyyy/mm/dd'), 'system',to_date('2019/8/14', 'yyyy/mm/dd'));
