--配置菜单和页面
delete from sys_menu where PAGE_ID = 'CashFlowDiscount001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_009_004', 'VAL', null, 'VALUATION_001_009', 'CashFlowDiscount001', 4, '2', '现金流折现模型曲线配置', 'system', null, '05-AUG-21 10.05.34.000000 AM', 'system', '05-AUG-21 10.05.34.000000 AM');

delete from sys_menu where PAGE_ID = 'CashFlowDiscount001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CashFlowDiscount001', 'VAL', '/valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount001', 'valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount001', 'VALUATION_001_009_004', null, null, '02', '01', '现金流折现模型曲线配置', 'system', null, '05-AUG-21 04.20.44.000000 PM', 'system', '05-AUG-21 04.20.44.000000 PM');

delete from sys_menu where PAGE_ID = 'CashFlowDiscount002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CashFlowDiscount002', 'VAL', '/valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002', 'valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002', null, null, null, '02', '01', '现金流折现模型曲线配置新建', 'system', null, '05-AUG-21 06.37.18.000000 PM', 'system', '05-AUG-21 06.37.18.000000 PM');


-- 配置工作流按钮
delete from sys_wfl_business_module where PAGE_ID = 'CashFlowDiscount002';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CashFlowDiscount002', 'ValCashFlowDiscount001_btn_delete', 'oneApproval', 'system', null, '05-AUG-21 03.43.43.000000 PM', 'system', '05-AUG-21 03.43.43.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CashFlowDiscount002', 'ValCashFlowDiscount002_btn_save', 'oneApproval', 'system', null, '05-AUG-21 03.43.49.000000 PM', 'system', '05-AUG-21 03.43.49.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CashFlowDiscount002', 'ValCashFlowDiscount002_btn_submit', 'oneApproval', 'system', null, '05-AUG-21 03.43.37.000000 PM', 'system', '05-AUG-21 03.43.37.000000 PM', 'ALL', '02');


-- 现金流折现模型曲线配置的业务类型begin
delete from sys_datadict_list where dict_code='COMMON_BUSINESS_TYPE' and item_code='MOD06';
insert into sys_datadict_list values('COMMON_BUSINESS_TYPE','MOD06','现金流折现模型曲线配置',1224,0,'system','',sysdate,'system',sysdate);
-- end

-- 曲线族枚举begin
insert into SYS_DATADICT values ('CURVE_GROUP','MOD','曲线族','CURVEGROUP','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('CURVE_GROUP','01','中债国债曲线','1','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('CURVE_GROUP','02','中债企业债曲线','2','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('CURVE_GROUP','03','中债资产支持证券曲线','3','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
-- end

--平滑区间枚举begin
insert into SYS_DATADICT values ('SMOOTHSECTION','MOD','平滑区间','SMOOTHSECTION','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SMOOTHSECTION','01','1个月','1','2','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SMOOTHSECTION','02','3个月','2','2','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
--end

-- 映射类型_模型枚举begin
insert into SYS_DATADICT values ('MODEL_MAPPING_TYPE','MOD','映射类型_模型','MODEL_MAPPING_TYPE','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MAPPING_TYPE','01','资产分类映射','1','1','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
--end