delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetInfoDetailRisk001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('AssetInfoDetailRisk001', 'AssetInfoDetailRisk001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, 'C');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('AssetInfoDetailRisk001', 'AssetInfoDetailRisk001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('AssetInfoDetailRisk001', 'AssetInfoDetailRisk001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, 'COM');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('AssetInfoDetailRisk001', 'AssetInfoDetailRisk001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, 'COM');

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'RskValBotDetailReport001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('RskValBotDetailReport001', 'RskValBotDetailReport001_btn_filter', '01', '02', null, '筛选',  'system', null, sysdate,  'system', sysdate, 'C');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('RskValBotDetailReport001', 'RskValBotDetailReport001_btn_export', '01', '01', null, '导出',  'system', null, sysdate,  'system', sysdate, 'C');

delete from SYS_PAGE_ELEMENT where PAGE_ID = 'RskOutValtblImportHis001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('RskOutValtblImportHis001', 'RskOutValtblImportHis001_btn_filter', '01', '02', null, '筛选',  'system', null, sysdate,  'system', sysdate, 'C');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('RskOutValtblImportHis001', 'RskOutValtblImportHis001_btn_export', '01', '01', null, '导出',  'system', null, sysdate,  'system', sysdate, 'C');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('RskOutValtblImportHis001', 'RskOutValtblImportHis001_btn_search', '01', '01', null, '查询',  'system', null, sysdate,  'system', sysdate, 'COM');
