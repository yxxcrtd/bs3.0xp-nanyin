--评级类型随着证券品种其他债券资产联动配置
DELETE FROM sys_datadict_list_link WHERE DICT_CODE = 'MODEL_MAIN_GRADE_TYPE';
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '09', 'SEC_TYPE', '13', 'system', null, sysdate, 'system', sysdate);


update sys_wfl_business_module set  module_id='noneApproval' where page_id='PdFactorConfig002';



