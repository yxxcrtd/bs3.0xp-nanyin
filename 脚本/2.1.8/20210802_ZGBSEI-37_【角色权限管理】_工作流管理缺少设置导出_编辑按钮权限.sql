--delete
delete from SYS_PAGE_ELEMENT where PAGE_ID='SysWflBusinessModule001' and ELEMENT_ID='SysWflBusinessModule001_btn_export' and REMARK='导出';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SysWflBusinessModule001' and ELEMENT_ID='SysWflBusinessModule001_TBLROW_edit' and REMARK='编辑';
--工作流管理页面:SysWflBusinessModule001，添加，导出按钮:SysWflBusinessModule001_btn_export
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SysWflBusinessModule001','SysWflBusinessModule001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate,'C');
--工作流管理页面:SysWflBusinessModule001，添加，编辑按钮：SysWflBusinessModule001_TBLROW_edit
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SysWflBusinessModule001','SysWflBusinessModule001_TBLROW_edit','01','01',null,'编辑','system',null,sysdate,'system',sysdate,'C');
