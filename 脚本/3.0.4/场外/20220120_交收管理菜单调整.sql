--新增菜单
delete from sys_page where PAGE_ID in ('ManualSettleQuery','CfetsManualSettleUpdate','CfetsManualSettle');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ManualSettleQuery', 'STL', 'home.html#/interbank/settlementmanage?a=a', 'cfets/manualsettle/ManualSettleQuery', 'QS_MANAGEMENT_001_010_001', '', '', '02', '01', '交收管理', 'system', '', sysdate, 'system', sysdate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsManualSettleUpdate', 'STL', '/cfets/manualsettle/ManualSettleUpdate', 'cfets/manualsettle/ManualSettleUpdate', '', '', '', '02', '01', '交收管理修改', 'system', '', sysdate, 'system', sysdate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsManualSettle', 'STL', '/cfets/manualsettle/CfetsManualSettle', 'cfets/manualsettle/CfetsManualSettle', '', '', '', '02', '01', '交收管理', 'system', '', sysdate, 'system', sysdate);

delete from sys_menu where MENU_ID in ('QS_MANAGEMENT_001_010','QS_MANAGEMENT_001_010_001');
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QS_MANAGEMENT_001_010', 'STL', '', 'QS_MANAGEMENT_001', '', 8, '01', '交收管理', 'system', '', sysdate, 'system', sysdate);
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QS_MANAGEMENT_001_010_001', 'STL', '', 'QS_MANAGEMENT_001_010', 'ManualSettleQuery', 1, '01', '交收管理', 'system', '', sysdate, 'system', sysdate);
