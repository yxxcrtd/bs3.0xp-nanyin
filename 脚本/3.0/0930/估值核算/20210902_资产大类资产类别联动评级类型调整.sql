--资产大类资产类别联动评级类型调整
delete from sys_datadict_list_link where  dict_code ='MODEL_MAIN_GRADE_TYPE' and linked_dict_code='SEC_TYPE' and linked_item_code='01' and item_code='04';
delete from sys_datadict_list_link where  dict_code ='MODEL_MAIN_GRADE_TYPE' and linked_dict_code='SEC_TYPE' and linked_item_code='01' and item_code='02';
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '02', 'SEC_TYPE', '01', 'system', null, sysdate, 'system', sysdate);

--更新前瞻因子权重配置工作流模式
update sys_wfl_business_module set module_id='noneApproval' where page_id='PdFactorWeightConfig003'
and element_id='PdFactorWeightConfig003_btn_submit';