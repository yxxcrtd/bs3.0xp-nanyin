
--估值模型股息增长率配置系统参数配置
delete from sys_menu t where t.MENU_ID='VALUATION_ACCOUNTING_001_009' ;

delete from sys_menu t where t.PAGE_ID='ModStockDivitrateConfig001' ;
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_010_004', 'VAL', null, 'VALUATION_001_010', 'ModStockDivitrateConfig001', 4, '2', '股息增长率配置', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

delete from sys_page t where t.PAGE_ID='ModStockDivitrateConfig001' ;
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig001', 'VAL', '/valmodel/modstockdivitrateconfig/ModStockDivitrateConfig001', 'valmodel/modstockdivitrateconfig/ModStockDivitrateConfig001', 'VALUATION_001_010_004', null, null, '02', '01', '预期股息增长率配置查询', 'system', null, '05-AUG-21 02.56.41.000000 PM', 'system', '05-AUG-21 02.56.41.000000 PM');

delete from sys_page t where t.PAGE_ID='ModStockDivitrateConfig002' ;
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig002', 'VAL', '/valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002', '/valmodel/modstockdivitrateconfig/ModStockDivitrateConfig002', null, null, null, '02', '01', '预期股息增长率配置查询', 'system', null, '05-AUG-21 06.42.39.000000 PM', 'system', '05-AUG-21 06.42.39.000000 PM');

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStockDivitrateConfig002' and t.ELEMENT_ID='ModStockDivitrateConfig002_btn_submit';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig002', 'ModStockDivitrateConfig002_btn_submit', '01', '02', null, '提交', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStockDivitrateConfig001' and t.ELEMENT_ID='ModStockDivitrateConfig001_btn_clear';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig001_btn_clear', '01', '02', null, '重置', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStockDivitrateConfig001' and t.ELEMENT_ID='ModStockDivitrateConfig001_btn_add';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig001_btn_add', '01', '01', null, '新建', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStockDivitrateConfig001' and t.ELEMENT_ID='ModStockDivitrateConfig001_btn_search';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig001_btn_search', '01', '01', null, '查询', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='ModStockDivitrateConfig001' and t.ELEMENT_ID='ModStockDivitrateConfig001_btn_delete';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig001_btn_delete', '01', '01', null, '删除', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));



delete from sys_wfl_business_module t where t.PAGE_ID='ModStockDivitrateConfig002';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModStockDivitrateConfig002', 'ModStockDivitrateConfig001_btn_delete', 'oneApproval', 'system', null, '05-AUG-21 03.25.41.000000 PM', 'system', '05-AUG-21 03.25.41.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModStockDivitrateConfig002', 'ModStockDivitrateConfig002_btn_submit', 'oneApproval', 'system', null, '05-AUG-21 03.25.41.000000 PM', 'system', '05-AUG-21 03.25.41.000000 PM', 'ALL', '02');

delete from sys_datadict_list t where t.DICT_CODE='COMMON_BUSINESS_TYPE' and t.ITEM_CODE='STCOKDIVITRATE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'STCOKDIVITRATE', '预期股息增长率配置', 1225, '0', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));
