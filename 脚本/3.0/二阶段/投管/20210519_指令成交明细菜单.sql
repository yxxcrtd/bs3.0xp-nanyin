delete from sys_menu where page_id = 'EtrTransactionDetail001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SH_DEAL_MANAGEMENT_001_001_003', 'STL', null, 'SH_DEAL_MANAGEMENT_001_001', 'EtrTransactionDetail001', 3, '02', '指令成交明细查询', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'EtrTransactionDetail001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrTransactionDetail001', 'ETR', '/invest/entrust/etrtransactiondetail/EtrTransactionDetail001', 'invest/entrust/etrtransactiondetail/EtrTransactionDetail001', 'SH_DEAL_MANAGEMENT_001_001_003', null, null, '02', '01', '指令成交明细查询', 'system', null, sysdate, 'system', sysdate);