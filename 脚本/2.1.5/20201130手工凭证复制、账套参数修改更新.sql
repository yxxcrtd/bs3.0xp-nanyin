--add by yangwenjun 20201130 手工凭证页面增加复制按钮 start
delete from SYS_PAGE_ELEMENT where PAGE_ID='ValManualVoucher001' and ELEMENT_ID='ValManualVoucher001_TBLROW_manual';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('ValManualVoucher001', 'ValManualVoucher001_TBLROW_manual', '01', '01', null, '复制', 'system', null, sysdate, 'system', sysdate, "COM");
--add by yangwenjun 20201130 手工凭证页面增加复制按钮 end


--add by yangwenjun 20201130 增加账套参数修改按钮 start
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID='ValAssetBook001' AND ELEMENT_ID='ValAssetBook001_btn_modification';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('ValAssetBook001', 'ValAssetBook001_btn_modification', '01', '01', null, '账套参数修改', 'system', null,sysdate, 'system',sysdate, "COM");
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID='ValAssetBook006' AND ELEMENT_ID='ValAssetBook006_btn_submit';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG) VALUES ('ValAssetBook006', 'ValAssetBook006_btn_submit', '01', '02', null, '提交', 'system', null,sysdate, 'system',sysdate, "COM");
--add by yangwenjun 20201130 增加账套参数修改按钮 end


