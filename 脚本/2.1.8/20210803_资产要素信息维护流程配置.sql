DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID = 'AssetInfoDetailRisk002';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('AssetInfoDetailRisk002', 'AssetInfoDetailRisk002_btn_submit', 'oneApproval', 'system', null,sysdate, 'system', sysdate,'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('AssetInfoDetailRisk002', 'AssetInfoDetailRisk002_btn_save', 'oneApproval', 'system', null,sysdate, 'system', sysdate,'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('AssetInfoDetailRisk002', 'AssetInfoDetailRisk001_btn_delete', 'oneApproval', 'system', null,sysdate, 'system', sysdate,'ALL', '02');

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'COMMON_BUSINESS_TYPE' AND ITEM_CODE = 'MST26' AND ORDER_NO = '911';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'MST26', '资产要素信息维护', '911', '0', 'system','null', sysdate, 'system',sysdate, 'null');

delete from SYS_PAGE where PAGE_ID in ('AssetInfoDetailRisk001','AssetInfoDetailRisk002');
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('AssetInfoDetailRisk001','MST','/invest/risk/assetinfodetail/AssetInfoDetailRisk001','invest/risk/assetinfodetail/AssetInfoDetailRisk001','RISK_MANAGEMENT_001_005_008',null,null,'02','01','资产要素信息维护','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('AssetInfoDetailRisk002','MST','/invest/risk/assetinfodetail/AssetInfoDetailRisk002','invest/risk/assetinfodetail/AssetInfoDetailRisk002',null,null,null,'02','01','资产要素信息维护新建','system',null,sysdate,'system',sysdate);
