
delete from sys_page t where t.PAGE_ID='ModGradePdConfig001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModGradePdConfig001', 'RSK', '/model/impairment/pddefaultmanager/gradepdconfig/ModGradePdConfig001', 'lossmodel/pddefaultmanager/gradepdconfig/ModGradePdConfigQuery001', 'VALUATION_001_011_004', null, null, '02', '01', '评级PD表查询', 'system', null, '04-AUG-21 06.44.23.000000 PM', 'system', '04-AUG-21 06.44.23.000000 PM');

delete from sys_page t where t.PAGE_ID='ModGradePdConfig002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModGradePdConfig002', 'RSK', '/model/impairment/pddefaultmanager/gradepdconfig/ModGradePdConfig002', 'lossmodel/pddefaultmanager/gradepdconfig/ModGradePdConfigInfo002', null, null, null, '02', '01', '评级PD维护新建', 'system', null, '03-AUG-21 07.37.38.000000 PM', 'system', '03-AUG-21 07.37.38.000000 PM');

-- 流程按钮添加begin
delete from sys_wfl_business_module t where t.ELEMENT_ID='ModGradePdConfig001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModGradePdConfig002', 'ModGradePdConfig001_btn_delete', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

delete from sys_wfl_business_module t where t.ELEMENT_ID='ModGradePdConfig001_btn_import';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModGradePdConfig002', 'ModGradePdConfig001_btn_import', 'oneApproval', 'system', null, '25-JUN-21 04.54.32.000000 PM', 'system', '25-JUN-21 04.54.32.000000 PM', 'ALL', '02');

delete from sys_wfl_business_module t where t.ELEMENT_ID='ModGradePdConfig002_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModGradePdConfig002', 'ModGradePdConfig002_btn_save', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

delete from sys_wfl_business_module t where t.ELEMENT_ID='ModGradePdConfig002_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModGradePdConfig002', 'ModGradePdConfig002_btn_submit', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

-- 流程按钮添加end

--配置代办事项业务类型begin--
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'MOD07', '评级PD维护', 1224, '0', 'system', null, '01-JUL-21 03.24.24.000000 PM', 'system', '01-JUL-21 03.24.24.000000 PM');
--配置代办事项业务类型end--


