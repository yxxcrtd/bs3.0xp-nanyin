DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'AssetInfoDetail002';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('AssetInfoDetail002', 'AssetInfoDetail002_btn_submit', 'oneApproval', 'system', null,sysdate, 'system', sysdate,'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('AssetInfoDetail002', 'AssetInfoDetail002_btn_save', 'oneApproval', 'system', null,sysdate, 'system', sysdate,'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('AssetInfoDetail002', 'AssetInfoDetail001_btn_delete', 'oneApproval', 'system', null,sysdate, 'system', sysdate,'ALL', '02');

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'COMMON_BUSINESS_TYPE' AND ITEM_CODE = 'SVR01' AND ORDER_NO = '912';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'SVR01', '资产基本信息维护', '912', '0', 'system',null, sysdate, 'system',sysdate, null);

delete from SYS_PAGE where PAGE_ID in ('AssetInfoDetail001','AssetInfoDetail002');
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('AssetInfoDetail001','SVR','/chb/assetinfodetail/AssetInfoDetail001','report/chb/assetinfodetail/AssetInfoDetail001','REPORT_001_001_010',null,null,'02','01','资产基本信息维护','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('AssetInfoDetail002','SVR','/chb/assetinfodetail/AssetInfoDetail002','report/chb/assetinfodetail/AssetInfoDetail002',null,null,null,'02','01','资产基本信息维护详情','system',null,sysdate,'system',sysdate);

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetInfoDetail001' and ELEMENT_ID = 'AssetInfoDetail001_btn_import';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('AssetInfoDetail001', 'AssetInfoDetail001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C');
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetInfoDetail002' ;
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('AssetInfoDetail002', 'AssetInfoDetail002_btn_save', '01', '02', null, '保存', 'system', null, sysdate, 'system', sysdate, 'C');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('AssetInfoDetail002', 'AssetInfoDetail002_btn_submit', '01', '02', null, '提交', 'system', null, sysdate, 'system', sysdate, 'C');