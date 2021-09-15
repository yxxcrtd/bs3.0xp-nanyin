delete from SYS_PAGE where PAGE_ID='EquityExportDeleteExcelConfirm001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('EquityExportDeleteExcelConfirm001', 'SVR', '/svreport/submitmanagement/exportdeleteexcel/EquityExportDeleteExcelConfirm001',
'report/submitmanagement/exportdeleteexcel/EquityExportDeleteExcelConfirm001', null,null, null, '02','02', '导出删除Excel确认', 'system', null, sysdate, 'system', sysdate);