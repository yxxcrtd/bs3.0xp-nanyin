--权益类资产指令
delete from sys_menu where page_id = 'EtrProjectNet001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_MANAGEMENT_001_004_010', 'STL', null, 'SETTLE_MANAGEMENT_001_004', 'EtrProjectNet001', 10, '02', '权益类资产指令', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where page_id = 'EtrProjectNet001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrProjectNet001', 'ETR', '/invest/entrust/etrprojectnet/EtrProjectNet001', 'invest/entrust/etrprojectnet/EtrProjectNet001', 'SETTLE_MANAGEMENT_001_004_010', null, null, '02', '01', '权益类资产指令管理', 'system', null, sysdate, 'system', sysdate);

--权益类资产交易
delete from sys_menu where page_id = 'TrdProjectNetInvestTrade001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DEAL_MANAGEMENT_001_004_009', 'TRD', null, 'DEAL_MANAGEMENT_001_004', 'TrdProjectNetInvestTrade001', 9, '02', '权益类资产交易', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'TrdProjectNetInvestTrade001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdProjectNetInvestTrade001', 'TRD', '/invest/trade/projectnet/TrdProjectNetInvestTrade001', 'invest/trade/projectnet/TrdProjectNetInvestTrade001', 'DEAL_MANAGEMENT_001_004_009', null, null, '02', '01', '权益类资产投资交易查询', 'system', null, sysdate, 'system', sysdate);
