DELETE FROM SYS_MENU WHERE PAGE_ID IN ('AssetInfoDetail001','AssetInfoDetail002');
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_001_010', 'REP', null, 'REPORT_001_001', 'AssetInfoDetail001', 7, 02, '资产基本信息维护', 'system', null, SysDate, 'system', SysDate);

DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('AssetInfoDetail001','AssetInfoDetail002');
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetInfoDetail001', 'SVR', '/chb/assetinfodetail/AssetInfoDetail001', 'report/chb/assetinfodetail/AssetInfoDetail001', 'REPORT_001_001_010', null, null, '02', '02', '资产基本信息维护', 'system', null, sysdate, 'system', sysdate);
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetInfoDetail002', 'SVR', '/chb/assetinfodetail/AssetInfoDetail002', 'report/chb/assetinfodetail/AssetInfoDetail002', null, null, null, '02', '02', '资产基本信息维护详情', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_MENU WHERE PAGE_ID IN ('AssetTypeSetting001','AssetTypeSetting002');
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_001_001_011', 'REP', null, 'REPORT_001_001', 'AssetTypeSetting001', 8, 02, '资产/负债类别配置', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('AssetTypeSetting001','AssetTypeSetting002');
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('AssetTypeSetting002', 'SVR', '/chb/assettypesetting/AssetTypeSetting002', 'report/chb/assettypesetting/AssetTypeSetting002', 'REPORT_001_001_011', null, null, '01', '01', '资产/负债类别配置新建', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('AssetTypeSetting001', 'SVR', '/chb/assettypesetting/AssetTypeSetting001', 'report/chb/assettypesetting/AssetTypeSetting001', 'REPORT_001_001_011', null, null, '01', '01', '资产/负债类别配置', 'system', null, sysdate, 'system', sysdate);
