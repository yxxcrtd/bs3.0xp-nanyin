delete from SYS_PAGE_ELEMENT a where a.ELEMENT_ID='RskValTableImport001_btn_batchUpload' and a.PAGE_ID='RskValTableImport001';

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('RskValTableImport001', 'RskValTableImport001_btn_batchUpload', '01', '01', null, '批量导入', 'system', null, ' 24-MAY-19 12.00.00.000000 AM', 'system', ' 24-MAY-19 12.00.00.000000 AM', 'C');

