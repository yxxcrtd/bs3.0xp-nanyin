DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('MstCurveDefManage001','MstCurveDefManage002');
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstCurveDefManage001', 'MST', '/valuation/basicdata/mstcurvedefmanage/MstCurveDefManage001', 'valuation/basicdata/mstcurvedefmanage/MstCurveDefManage001', 'ASSET_MANAGEMENT_001_003_007', null, null, '02', '02', '收益率曲线管理', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstCurveDefManage002', 'MST', '/valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002', 'valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002', null, null, null, '02', '02', '收益率曲线管理详情', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE_ELEMENT WHERE PAGE_ID IN ('MstCurveDefManage001','MstCurveDefManage002');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstCurveDefManage001', 'MstCurveDefManage001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstCurveDefManage001', 'MstCurveDefManage001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'CURVE_TYPE';
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CURVE_TYPE', 'SP', 'SP即期', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CURVE_TYPE', 'MA', 'MA远期', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CURVE_TYPE', 'FS', 'FS远期的即期', 3, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CURVE_TYPE', 'FM', 'FM远期的到期', 4, '0', 'system', null, sysdate, 'system', sysdate, null);
