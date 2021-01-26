--2021.1.25

----增加COMMON_BUSINESS_TYPE字典
DELETE Sys_Datadict_List WHERE dict_code = 'COMMON_BUSINESS_TYPE'
AND ITEM_CODE ='S10';
insert into Sys_Datadict_List (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('COMMON_BUSINESS_TYPE', 'S10', '流程配置', 10, '0', 'system', null, sysdate, 'system', sysdate, null);



---- 增加sys_page配置
DELETE sys_page WHERE PAGE_ID in ( 'SysWflBusinessModule001','SysWflBusinessModule002');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysWflBusinessModule001', 'SYS', '/system/wflmoduleset/SysWflBusinessModule001', 'system/wflmoduleset/SysWflBusinessModule001', 'SYSTEM_001_006_010', null, null, '01', '02', '流程配置', 'system', null, sysdate, 'system', sysdate);


insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysWflBusinessModule002', 'SYS', '/system/wflmoduleset/SysWflBusinessModule002', 'system/wflmoduleset/SysWflBusinessModule002', null, null, null, '01', '01', '流程配置', 'system', null, sysdate, 'system', sysdate);

--- 增加菜单
DELETE sys_menu WHERE MENU_ID in ( 'SYSTEM_001_006_010');

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_006_010', 'SYS', null, 'SYSTEM_001_006', 'SysWflBusinessModule001', 11, '02', '流程配置', 'system', null, sysdate, 'system', sysdate);

---增加菜单按钮
DELETE sys_page_element WHERE page_id = 'SysWflBusinessModule001' and ELEMENT_ID = 'SysWflBusinessModule001_btn_search';

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('SysWflBusinessModule001', 'SysWflBusinessModule001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM');


--- 流程配置的按钮  配置流程
DELETE sys_wfl_business_module WHERE page_id = 'SysWflBusinessModule002' and ELEMENT_ID in ( 'SysWflBusinessModule002_btn_submit','SysWflBusinessModule002_btn_save');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('SysWflBusinessModule002', 'SysWflBusinessModule002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('SysWflBusinessModule002', 'SysWflBusinessModule002_btn_save', 'noneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');









