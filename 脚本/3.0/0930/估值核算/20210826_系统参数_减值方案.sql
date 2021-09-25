DELETE FROM sys_para WHERE PARA_CODE = 'LOSS_TYPE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('VAL', 'LOSS_TYPE', '02', null, null, '系统减值方案参数，01 毕马威；02 普华永道', 'yuzj1', null, sysdate, 'yuzj1', sysdate, null, '01');



DELETE FROM SYS_PAGE WHERE PAGE_ID = 'AssetImpairmentManagement001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetImpairmentManagement001', 'RSK', '/model/impairment/assetimpairmentmanagement/AssetImpairmentManagement001', 'lossmodel/assetimpairmentmanagement/AssetImpairmentManagement001', null, null, null, '02', '02', '资产减值管理', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'AssetImpairmentManagement002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetImpairmentManagement002', 'RSK', '/model/impairment/assetimpairmentmanagement/AssetImpairmentManagement002', 'lossmodel/assetimpairmentmanagement/AssetImpairmentManagement002', null, null, null, '02', '01', '资产减值新建', 'system', null, sysdate, 'system', sysdate);


--数据字典评级类型全量更新MODEL_MAIN_GRADE_TYPE
delete from SYS_DATADICT_LIST where dict_code = 'MODEL_MAIN_GRADE_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_MAIN_GRADE_TYPE', '01', '主体', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_MAIN_GRADE_TYPE', '02', '债项', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_MAIN_GRADE_TYPE', '03', '融资人', 3, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_MAIN_GRADE_TYPE', '04', '担保人', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_MAIN_GRADE_TYPE', '05', '较高值', 5, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_MAIN_GRADE_TYPE', '06', '较低值', 6, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_MAIN_GRADE_TYPE', '07', '存借款机构', 7, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_MAIN_GRADE_TYPE', '08', '交易对手', 8, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MODEL_MAIN_GRADE_TYPE', '09', '发行人', 9, '0', 'system', null, sysdate, 'system', sysdate, null);

--证券品种联动评级类型配置（因删除了发行人修改了存借款机构、交易对手）
delete from SYS_DATADICT_LIST_LINK  where dict_code='MODEL_MAIN_GRADE_TYPE' and linked_dict_code='SEC_TYPE';
insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '01', 'SEC_TYPE', '01', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '03', 'SEC_TYPE', '13', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '04', 'SEC_TYPE', '01', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '04', 'SEC_TYPE', '13', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '05', 'SEC_TYPE', '01', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '05', 'SEC_TYPE', '13', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '06', 'SEC_TYPE', '01', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '06', 'SEC_TYPE', '13', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '07', 'SEC_TYPE', '07', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MODEL_MAIN_GRADE_TYPE', '08', 'SEC_TYPE', '09', 'system', null, sysdate, 'system', sysdate);

--资产减值管理sys_page修改
DELETE FROM SYS_PAGE WHERE PAGE_ID = 'AssetImpairmentManagement001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetImpairmentManagement001', 'RSK', '/model/impairment/assetimpairmentmanagement/AssetImpairmentManagement001', 'lossmodel/assetimpairmentmanagement/AssetImpairmentManagement001', null, null, null, '02', '02', '资产减值管理', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'AssetImpairmentManagement002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetImpairmentManagement002', 'RSK', '/model/impairment/assetimpairmentmanagement/AssetImpairmentManagement002', 'lossmodel/assetimpairmentmanagement/AssetImpairmentManagement002', null, null, null, '02', '01', '资产减值新建', 'system', null, sysdate, 'system', sysdate);

--评级机构数据字典数据调整
delete from sys_datadict_list where dict_code='GRADE_PARTY' and item_code in ('32','98','99');
delete from  sys_page_dictlist  where page_id='InsideOutsideGrade001' and dict_id='GRADE_PARTY'  and dict_item_val in ('32','98','99');

--减值阶段参数管理表添加启用/停用字段
ALTER TABLE MOD_STAGE_PARA ADD STATUS VARCHAR2(50) DEFAULT('Y') NOT NULL;
COMMENT ON COLUMN MOD_STAGE_PARA.STATUS IS '启用状态(01启用/02停用)' ;