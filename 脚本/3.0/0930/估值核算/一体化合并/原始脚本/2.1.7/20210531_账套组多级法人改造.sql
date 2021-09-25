update sys_page_element set admin_flag='C'  where element_id='SysBatchProcess001_btn_productGroup';
delete from DATA_AUTH_MAPPER_CONFIG where sql_id='com.joyintech.system.batch.batchprocess.dao.SysBatchProcess003Mapper.findAllPrdGroup';
INSERT INTO DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)VALUES ('com.joyintech.system.batch.batchprocess.dao.SysBatchProcess003Mapper.findAllPrdGroup', '2', '02', 'SEQ_NO', null, 'SVR', 'SysBatchProcess001', 'admin', null,sysdate, 'admin',sysdate, null, null, 'V08');
delete from DATA_AUTH_MAPPER_CONFIG where sql_id='com.joyintech.tams.valuation.system.batchstart.dao.ValBatchStart001Mapper.findAllPrdGroup';
INSERT INTO DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)VALUES ('com.joyintech.tams.valuation.system.batchstart.dao.ValBatchStart001Mapper.findAllPrdGroup', '2', '02', 'SEQ_NO', null, 'SVR', 'SysBatchProcess001', 'admin', null,sysdate, 'admin',sysdate, null, null, 'V08');

delete from sys_datadict_list where dict_code='BATCH_BOOKSET_TYPE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('BATCH_BOOKSET_TYPE', '0', '账套组', 1, '0', 'system', null, sysdate, 'system', sysdate, null);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('BATCH_BOOKSET_TYPE', '1', '账套', 2, '0', 'system', null, sysdate, 'system', sysdate, null);