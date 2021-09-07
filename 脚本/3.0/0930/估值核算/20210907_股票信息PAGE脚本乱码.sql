delete from sys_page where page_id in ('FinStockInfo001','FinStockInfo002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinStockInfo001', 'VAL', '/valuation/basicdata/asset/stock/FinStockInfo001', 'valuation/basicdata/asset/stock/FinStockInfo001', 'ASSET_MANAGEMENT_001_002_009', null, null, '02', '01', '股票类资产', 'system', null, sysDate, 'system', sysDate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinStockInfo002', 'VAL', '/valuation/basicdata/asset/stock/FinStockInfo002', 'valuation/basicdata/asset/stock/FinStockInfo002', '', null, null, '02', '01', '股票类资产-新建', 'system', null, sysDate, 'system', sysDate);
