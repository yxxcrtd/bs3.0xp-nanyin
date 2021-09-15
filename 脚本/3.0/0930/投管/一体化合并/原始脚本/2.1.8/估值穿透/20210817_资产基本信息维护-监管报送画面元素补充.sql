delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetInfoDetail001' and ELEMENT_ID = 'AssetInfoDetail001_btn_import';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('AssetInfoDetail001', 'AssetInfoDetail001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate, 'C');
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'AssetInfoDetail002' ;
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('AssetInfoDetail002', 'AssetInfoDetail002_btn_save', '01', '02', null, '保存', 'system', null, sysdate, 'system', sysdate, 'C');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
VALUES ('AssetInfoDetail002', 'AssetInfoDetail002_btn_submit', '01', '02', null, '提交', 'system', null, sysdate, 'system', sysdate, 'C');