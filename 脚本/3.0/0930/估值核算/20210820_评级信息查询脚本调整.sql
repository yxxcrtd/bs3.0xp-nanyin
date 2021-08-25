--评级信息查询功能

DELETE FROM SYS_MENU where PAGE_ID = 'ComMarketManagement';
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ASSET_MANAGEMENT_001_003_019', 'COM', null, 'ASSET_MANAGEMENT_001_003', 'ComMarketManagement', 19, '2', '评级信息查询', 'system', null, sysdate, 'system',sysdate);

DELETE FROM SYS_PAGE where PAGE_ID = 'ComMarketManagement';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComMarketManagement', 'COM', '/valuation/basicdata/commarketmanagement/ComMarketManagement', 'valuation/basicdata/commarketmanagement/ComMarketManagement', 'ASSET_MANAGEMENT_001_003_019', null, null, '02', '01', '评级信息查询', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE_ELEMENT where PAGE_ID = 'ComMarketManagement';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComMarketManagement', 'ComMarketManagement_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM');
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComMarketManagement', 'ComMarketManagement_btn_reset', '01', '02', null, '重置', 'system', null, sysdate, 'system', sysdate, 'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComMarketManagement', 'ComMarketManagement_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM');