-- insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
-- values ('ACCOUTING_001_002_007', 'VAL', null, 'ACCOUTING_001_002', 'ValManualVoucherTemplete001', 3, '01', '手工凭证模板', 'system', null, sysdate, 'system', sysdate);


insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValManualVoucherTemplete001', 'VAL', '/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete001', 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete001', 'ACCOUTING_001_002_007', null, null, '02', '02', '手工凭证模板', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValManualVoucherTemplete002', 'VAL', '/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete002', 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete002', null, null, null, '02', '02', '查看手工凭证模板', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValManualVoucherTemplete003', 'VAL', '/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003', 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003', null, null, null, '02', '02', '新建手工凭证模板', 'system', null, sysdate, 'system', sysdate);
