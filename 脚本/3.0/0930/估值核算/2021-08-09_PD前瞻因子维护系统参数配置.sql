
delete from sys_page t where t.PAGE_ID='PdDefaultManager001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_011_004', 'VAL', null, 'VALUATION_001_011', 'PdDefaultManager001', 3, '2', 'PD违约概率管理', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

delete from sys_page t where t.PAGE_ID='PdFactorConfig001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'RSK', '/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig001', 'lossmodel/pddefaultmanager/pdfactorconfig/PdFactorConfig001', null, null, null, '02', '01', '前瞻因子维护', 'system', null, '05-AUG-21 03.09.00.000000 PM', 'system', '05-AUG-21 03.09.00.000000 PM');

delete from sys_page t where t.PAGE_ID='PdFactorConfig002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'RSK', '/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig002', 'lossmodel/pddefaultmanager/pdfactorconfig/PdFactorConfig002', null, null, null, '02', '01', '前瞻因子维护新建', 'system', null, '05-AUG-21 03.09.00.000000 PM', 'system', '05-AUG-21 03.09.00.000000 PM');

delete from sys_page t where t.PAGE_ID='PdFactorWeightConfig003';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorWeightConfig003', 'RSK', '/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorWeightConfig003', 'lossmodel/pddefaultmanager/pdfactorconfig/PdFactorWeightConfig003', null, null, null, '02', '01', '前瞻因子权重配置', 'system', null, '05-AUG-21 03.09.00.000000 PM', 'system', '05-AUG-21 03.09.00.000000 PM');

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig002' and t.ELEMENT_ID='PdFactorConfig002_btn_save';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'PdFactorConfig002_btn_save', '01', '02', null, '保存', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig002' and t.ELEMENT_ID='PdFactorConfig002_btn_submit';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'PdFactorConfig002_btn_submit', '01', '02', null, '提交', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig001' and t.ELEMENT_ID='PdFactorConfig001_btn_clear';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'PdFactorConfig001_btn_clear', '01', '02', null, '重置', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig001' and t.ELEMENT_ID='PdFactorConfig001_btn_add';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'PdFactorConfig001_btn_add', '01', '01', null, '新建', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig001' and t.ELEMENT_ID='PdFactorConfig001_btn_search';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'PdFactorConfig001_btn_search', '01', '01', null, '查询', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig001' and t.ELEMENT_ID='PdFactorConfig001_btn_delete';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'PdFactorConfig001_btn_delete', '01', '01', null, '删除', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorConfig001' and t.ELEMENT_ID='PdFactorConfig001_btn_export';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig001', 'PdFactorConfig001_btn_export', '01', '01', null, '导出', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from SYS_PAGE_ELEMENT t where t.PAGE_ID='PdFactorWeightConfig003' and t.ELEMENT_ID='PdFactorWeightConfig003_btn_submit';
insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorWeightConfig003', 'PdFactorWeightConfig003_btn_submit', '01', '02', null, '提交', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));


delete from sys_wfl_business_module t where t.PAGE_ID='PdFactorConfig002' and t.ELEMENT_ID='PdFactorConfig002_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'PdFactorConfig002_btn_save', 'oneApproRSK', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.PAGE_ID='PdFactorConfig002' and t.ELEMENT_ID='PdFactorConfig002_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'PdFactorConfig002_btn_submit', 'oneApproRSK', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.PAGE_ID='PdFactorConfig002' and t.ELEMENT_ID='PdFactorConfig001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorConfig002', 'PdFactorConfig001_btn_delete', 'oneApproRSK', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.PAGE_ID='PdFactorWeightConfig003' and t.ELEMENT_ID='PdFactorWeightConfig003_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorWeightConfig003', 'PdFactorWeightConfig003_btn_submit', 'oneApproRSK', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));


--配置流程BusinessType
delete from sys_datadict_list t where t.DICT_CODE='COMMON_BUSINESS_TYPE' and t.ITEM_CODE='PDFACTOR';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_RSKUE, ORDER_NO, DEFAULT_RSKUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'PDFACTOR', '前瞻因子维护', 1221, '0', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_datadict_list t where t.DICT_CODE='COMMON_BUSINESS_TYPE' and t.ITEM_CODE='PDWEIGHT';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_RSKUE, ORDER_NO, DEFAULT_RSKUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'PDWEIGHT', '前瞻因子权重配置', 1222, '0', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));


commit;