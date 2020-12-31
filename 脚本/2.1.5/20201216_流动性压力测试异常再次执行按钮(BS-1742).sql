--配置重新运行按钮
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'FlowRiskStressTestView001' and ELEMENT_ID = 'FlowRiskStressTestView001_TBLROW_manual';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('FlowRiskStressTestView001', 'FlowRiskStressTestView001_TBLROW_manual', '01', '01', null, '重新运行', 'system', null, sysdate, 'system', sysdate, 'C');
