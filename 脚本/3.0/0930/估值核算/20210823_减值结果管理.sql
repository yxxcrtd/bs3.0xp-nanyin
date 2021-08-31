--普华_减值结果管理
-- insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
-- values ('VALUATION_001_011_009', 'VAL', null, 'VALUATION_001_011', 'AssetImpairmentManagement001', 9, '2', '减值结果管理_普华', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');


--毕马威_减值结果管理_菜单
-- insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
-- values ('VALUATION_001_011_010', 'VAL', null, 'VALUATION_001_011', 'AssetImpairmentKpmgManagement001', 10, '2', '减值结果管理_毕马威', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

--毕马威_减值结果管理_页面
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetImpairmentKpmgManagement001', 'RSK', '/model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement001', 'lossmodel/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement001', null, null, null, '02', '01', '减值结果管理_毕马威', 'system', null, '05-AUG-21 03.50.54.000000 PM', 'system', '05-AUG-21 03.50.54.000000 PM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetImpairmentKpmgManagement002', 'RSK', '/model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement002', 'lossmodel/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement002', null, null, null, '02', '01', '减值结果新建', 'system', null, '05-AUG-21 03.50.55.000000 PM', 'system', '05-AUG-21 03.50.55.000000 PM');

--毕马威_减值结果管理_工作流
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('AssetImpairmentKpmgManagement002', 'AssetImpairmentKpmgManagement001_btn_delete', 'oneApproval', 'system', null, '05-AUG-21 03.50.55.000000 PM', 'system', '05-AUG-21 03.50.55.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('AssetImpairmentKpmgManagement002', 'AssetImpairmentKpmgManagement002_btn_save', 'oneApproval', 'system', null, '05-AUG-21 03.50.55.000000 PM', 'system', '05-AUG-21 03.50.55.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('AssetImpairmentKpmgManagement002', 'AssetImpairmentKpmgManagement002_btn_submit', 'oneApproval', 'system', null, '05-AUG-21 03.50.55.000000 PM', 'system', '05-AUG-21 03.50.55.000000 PM', 'ALL', '02');


--数据字典-减值数据来源
delete from sys_datadict_list t where dict_code='LOSS_INPUT_TYPE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('LOSS_INPUT_TYPE', '01', '手工', 1, '0', 'system', null, '15-JUN-21 12.00.00.000000 AM', 'system', '15-JUN-21 12.00.00.000000 AM', 'ALL');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('LOSS_INPUT_TYPE', '02', 'wind', 2, '0', 'system', null, '15-JUN-21 12.00.00.000000 AM', 'system', '15-JUN-21 12.00.00.000000 AM', 'ALL');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('LOSS_INPUT_TYPE', '03', '中债', 3, '0', 'system', null, '15-JUN-21 12.00.00.000000 AM', 'system', '15-JUN-21 12.00.00.000000 AM', null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('LOSS_INPUT_TYPE', '04', '模型', 4, '0', 'system', null, '15-JUN-21 12.00.00.000000 AM', 'system', '15-JUN-21 12.00.00.000000 AM', null);
