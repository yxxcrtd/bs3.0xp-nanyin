--add by yangwenjun 20201124 手工凭证页面增加复制按钮 start
delete from SYS_PAGE_ELEMENT where PAGE_ID='ValManualVoucher001' and ELEMENT_ID='ValManualVoucher001_TBLROW_manual';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('ValManualVoucher001', 'ValManualVoucher001_TBLROW_manual', '01', '01', null, '复制', 'system', null, sysdate, 'system', sysdate, null);
--add by yangwenjun 20201124 手工凭证页面增加复制按钮 end