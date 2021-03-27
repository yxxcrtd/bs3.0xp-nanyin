delete from SYS_MENU where menu_id = 'REPORT_001_003_000';
Insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('REPORT_001_003_000','SVR',null,'REPORT_001_003','SvrEmployeeInfoReg001',0,'02','从业人员登记','system',null,sysdate,'system',sysdate);

delete from SYS_PAGE where PAGE_ID = 'SvrEmployeeInfoReg001';
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('SvrEmployeeInfoReg001','SVR','chb/employeeinforegist/EmployeeInfoReg001','report/chb/employeeinforegist/EmployeeInfoReg001','REPORT_001_003_000',null,null,'02','02','从业人员登记','system',null,sysdate,'system',sysdate);



DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID ='SvrEmployeeInfoReg001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEmployeeInfoReg001','SvrEmployeeInfoReg001_btn_cancelManual','01','02',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEmployeeInfoReg001','SvrEmployeeInfoReg001_btn_chbexport','01','02',null,'导出中债Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEmployeeInfoReg001','SvrEmployeeInfoReg001_btn_manual','01','02',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEmployeeInfoReg001','SvrEmployeeInfoReg001_btn_submitChb','01','02',null,'报送','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEmployeeInfoReg001','SvrEmployeeInfoReg001_btn_search','01','02',null,'查询','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEmployeeInfoReg001','SvrEmployeeInfoReg001_btn_reSubmitChb','01','02',null,'重新报送','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrEmployeeInfoReg001','SvrEmployeeInfoReg001_btn_reset','01','02',null,'重置','system',null,sysdate,'system',sysdate);
