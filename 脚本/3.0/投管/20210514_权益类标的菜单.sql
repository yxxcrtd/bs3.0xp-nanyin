delete from sys_menu where page_id = 'FinProjectNetTargetAsset001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ASSET_MANAGEMENT_001_002_011', 'MST', null, 'ASSET_MANAGEMENT_001_002', 'FinProjectNetTargetAsset001', 11, '02', '权益类标的', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where PAGE_ID = 'FinProjectNetTargetAsset001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProjectNetTargetAsset001', 'FIN', '/invest/asset/projectnet/FinProjectNetTargetAsset001', 'invest/asset/projectnet/FinProjectNetTargetAsset001', 'ASSET_MANAGEMENT_001_002_011', null, null, '02', '01', '权益类标的', 'system', null, sysdate, 'system', sysdate);

