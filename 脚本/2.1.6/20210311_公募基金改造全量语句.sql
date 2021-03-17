--菜单表
delete from sys_menu where MENU_ID = 'ASSET_DURATION_MANAGEMENT_001_001_018';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ASSET_DURATION_MANAGEMENT_001_001_018', 'TRD', '', 'ASSET_DURATION_MANAGEMENT_001_001', 'TrdFundDurationNew001', 18, '02', '公募基金存续期（多种分红类型）', 'system', '', sysdate, 'system', sysdate);

--页面表
delete from sys_page where PAGE_ID in ('TrdFundDurationNew001','TrdFundDurationNew002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdFundDurationNew002', 'TRD', '/invest/period/trdfunddurationnew/TrdFundDurationNew002', 'invest/period/trdfunddurationnew/TrdFundDurationNew002', '', '', '', '02', '01', '存续期基金分红新建', 'system', '', sysdate, 'system', sysdate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdFundDurationNew001', 'TRD', '/invest/period/trdfunddurationnew/TrdFundDurationNew001', 'invest/period/trdfunddurationnew/TrdFundDurationNew001', 'ASSET_DURATION_MANAGEMENT_001_001_018', '', '', '02', '01', '多种分红类型公募基金存续期', 'system', '', sysdate, 'system', sysdate);

--画面要素表
delete from sys_page_element where PAGE_ID in ('TrdFundDurationNew001','TrdFundDurationNew002');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdFundDurationNew001', 'TrdFundDurationNew001_btn_add', '01', '01', '', '新建', 'system', '', sysdate, 'system', sysdate, 'C');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdFundDurationNew001', 'TrdFundDurationNew001_btn_revoke', '01', '01', '', '撤销', 'system', '', sysdate, 'system', sysdate, 'C');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdFundDurationNew001', 'TrdFundDurationNew001_btn_export', '01', '01', '', '导出', 'system', '', sysdate, 'system', sysdate, 'COM');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdFundDurationNew001', 'TrdFundDurationNew001_btn_search', '01', '01', '', '查询', 'system', '', sysdate, 'system', sysdate, 'COM');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdFundDurationNew001', 'TrdFundDurationNew001_TBLROW_edit', '01', '01', '', '确认', 'system', '', sysdate, 'system', sysdate, 'C');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdFundDurationNew002', 'TrdFundDurationNew002_btn_save', '01', '01', '', '保存', 'system', '', sysdate, 'system', sysdate, 'C');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdFundDurationNew002', 'TrdFundDurationNew002_btn_submit', '01', '02', '', '提交', 'system', '', sysdate, 'system', sysdate, 'C');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdFundDurationNew001', 'TrdFundObversion001_btn_add', '01', '01', '', '折算-新建', 'system', '', sysdate, 'system', sysdate, 'C');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('TrdFundDurationNew001', 'TrdFundObversion001_btn_revoke', '01', '01', '', '折算-撤销', 'system', '', sysdate, 'system', sysdate, 'C');

--画面数据字典明细关联
delete from sys_page_dictlist  where page_id in('TrdFundDurationNew001','TrdFundDurationNew002');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdFundDurationNew001', '999999', 'PAGE_DATA_STATUS', '03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdFundDurationNew001', '999999', 'PAGE_DATA_STATUS', '05', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdFundDurationNew001', '999999', 'PAGE_DATA_STATUS', '06', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdFundDurationNew001', '999999', 'BONUS_MODE', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdFundDurationNew002', '999999', 'BONUS_MODE', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdFundDurationNew001', '999999', 'BONUS_MODE', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdFundDurationNew002', '999999', 'BONUS_MODE', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');

--业务流程模板
delete from sys_wfl_business_module  where page_id ='TrdFundDurationNew002';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('TrdFundDurationNew002', 'TrdFundDurationNew002_btn_total', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('TrdFundDurationNew002', 'TrdFundDurationNew002_btn_save', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('TrdFundDurationNew002', 'TrdFundDurationNew002_btn_submit', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('TrdFundDurationNew002', 'TrdFundDurationNew001_btn_revoke', 'oneApproval', 'system', '', sysdate, 'system', sysdate, 'ALL', '02');

--草稿箱模式非对象画面表
delete from SYS_TRASH_NOT_TARGET_PAGE where SEQ_NO = 'A6986B98361453A7E0835C5AA8C081D0'; 
insert into SYS_TRASH_NOT_TARGET_PAGE (SEQ_NO, PAGE_ID)
values ('A6986B98361453A7E0835C5AA8C081D0', 'TrdFundDurationNew002');

--公墓基金存续期处理逻辑 01：旧逻辑 02：新逻辑
delete from sys_para WHERE SYSTEM_CODE ='TRD' AND PARA_CODE ='FUND_DEAL_TYPE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, UPDATE_FLG, INSTITUTION_CODE)
values ('TRD', 'FUND_DEAL_TYPE', '02', '  ', ' ', '公墓基金存续期处理逻辑 01：旧逻辑 02：新逻辑', 'system', '', sysdate, 'system', sysdate, '01', '');
