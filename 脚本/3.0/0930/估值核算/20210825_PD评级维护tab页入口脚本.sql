insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdDefaultManager001', 'RSK', '/model/impairment/pddefaultmanager/pdfactorconfig/PdDefaultManager001', 'lossmodel/pddefaultmanager/pdfactorconfig/PdDefaultManager001', null, null, null, '02', '01', 'PD违约概率管理', 'system', null, '05-AUG-21 03.09.00.000000 PM', 'system', '05-AUG-21 03.09.00.000000 PM');

delete from sys_page where PAGE_ID='ModHgGradePdConfig001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModHgGradePdConfig001', 'RSK', '/model/impairment/pddefaultmanager/hggradepdconfig/ModHgGradePdConfig001', 'lossmodel/pddefaultmanager/hggradepdconfig/ModHgGradePdConfigQuery001', null, null, null, '02', '01', '回购评级PD表查询', 'system', null, '04-AUG-21 06.44.23.000000 PM', 'system', '04-AUG-21 06.44.23.000000 PM');

delete from sys_page where PAGE_ID='ModHgGradePdConfig002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModHgGradePdConfig002', 'RSK', '/model/impairment/pddefaultmanager/hggradepdconfig/ModHgGradePdConfig002', 'lossmodel/pddefaultmanager/hggradepdconfig/ModHgGradePdConfigInfo002', null, null, null, '02', '01', '回购评级PD维护新建', 'system', null, '03-AUG-21 07.37.38.000000 PM', 'system', '03-AUG-21 07.37.38.000000 PM');

delete from sys_page where PAGE_ID='ModCkGradePdConfig001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModCkGradePdConfig001', 'RSK', '/model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig001', 'lossmodel/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfigQuery001', null, null, null, '02', '01', '存款评级PD表查询', 'system', null, '04-AUG-21 06.44.23.000000 PM', 'system', '04-AUG-21 06.44.23.000000 PM');

delete from sys_page where PAGE_ID='ModCkGradePdConfig002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModCkGradePdConfig002', 'RSK', '/model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002', 'lossmodel/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfigInfo002', null, null, null, '02', '01', '存款评级PD维护新建', 'system', null, '03-AUG-21 07.37.38.000000 PM', 'system', '03-AUG-21 07.37.38.000000 PM');


delete from sys_wfl_business_module where PAGE_ID='ModHgGradePdConfig002';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModHgGradePdConfig002', 'ModHgGradePdConfig001_btn_delete', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModHgGradePdConfig002', 'ModHgGradePdConfig001_btn_import', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModHgGradePdConfig002', 'ModHgGradePdConfig002_btn_save', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModHgGradePdConfig002', 'ModHgGradePdConfig002_btn_submit', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

delete from sys_wfl_business_module where PAGE_ID='ModCkGradePdConfig002';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModCkGradePdConfig002', 'ModCkGradePdConfig001_btn_delete', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModCkGradePdConfig002', 'ModCkGradePdConfig001_btn_import', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModCkGradePdConfig002', 'ModCkGradePdConfig002_btn_save', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModCkGradePdConfig002', 'ModCkGradePdConfig002_btn_submit', 'oneApproval', 'system', null, '25-JUN-21 02.31.30.000000 PM', 'system', '25-JUN-21 02.31.30.000000 PM', 'ALL', '02');




