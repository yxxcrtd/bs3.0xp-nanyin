--标准券管理相关画面的remark修正
update SYS_PAGE set REMARK = '标准券查询' where PAGE_ID = 'TrdStandardBond001';
update SYS_PAGE set REMARK = '标准券入库' where PAGE_ID = 'TrdStandardBond002';
update SYS_PAGE set REMARK = '标准券出库' where PAGE_ID = 'TrdStandardBond003';
--标准券管理相关画面的保存、提交按钮不应受权限控制
update SYS_PAGE_ELEMENT set IS_CONTROL = '02' where PAGE_ID = 'TrdStandardBond002' and ELEMENT_ID in ('TrdStandardBond002_btn_save', 'TrdStandardBond002_btn_submit');
update SYS_PAGE_ELEMENT set IS_CONTROL = '02' where PAGE_ID = 'TrdStandardBond003' and ELEMENT_ID in ('TrdStandardBond003_btn_save', 'TrdStandardBond003_btn_submit');
