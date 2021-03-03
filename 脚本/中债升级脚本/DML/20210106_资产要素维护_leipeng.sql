DELETE FROM SYS_MENU WHERE PAGE_ID IN ('AssetInfoDetail001','AssetInfoDetail002');
insert into SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_012', 'REP', null, 'REPORT_001_003', 'AssetInfoDetail001', 12, null, '资产基本信息维护', 'system', null, SysDate, 'system', SysDate);


DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('AssetInfoDetail001','AssetInfoDetail002');
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetInfoDetail001', 'SVR', '/chb/assetinfodetail/AssetInfoDetail001', 'report/chb/assetinfodetail/AssetInfoDetail001', 'REPORT_001_003_008', null, null, '02', '02', '资产基本信息维护', 'system', null, sysdate, 'system', sysdate);


insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetInfoDetail002', 'SVR', '/chb/assetinfodetail/AssetInfoDetail002', 'report/chb/assetinfodetail/AssetInfoDetail002', null, null, null, '02', '02', '资产基本信息维护详情', 'system', null, sysdate, 'system', sysdate);


/***增加数据字典  待维护-已维护**/
delete from SYS_DATADICT where DICT_CODE = 'DATA_STATUS';

insert into SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DATA_STATUS', 'SVR', '处理状态', 'DATA_STATUS', '02', 'system', null,sysdate, 'system', sysdate);

delete from SYS_DATADICT_LIST where DICT_CODE = 'DATA_STATUS';

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DATA_STATUS', '01', '待处理', 1, '0', 'system', null,sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DATA_STATUS', '02', '已处理', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
