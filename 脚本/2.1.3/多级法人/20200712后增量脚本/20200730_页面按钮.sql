--批处理启动
update sys_page_element set is_control = '02' where element_id = 'SysBatchStart001_btn_start' and page_id = 'SysBatchStart001';
--用户管理置
delete from sys_page_element where page_id = 'SysUser001' and element_id = 'SysUser001_btn_pwdreset';
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
VALUES
    ('SysUser001','SysUser001_btn_pwdreset','01','01','密码重置','system',null,sysdate,'system',sysdate);
--报表模板管理
update sys_page_element set is_control = '01' where element_id ='ReportModelManage001_btn_delete' and page_id = 'ReportModelManage001';
--人行产品资产池代码管理
update sys_page_element set is_control = '01' where element_id ='ProPeoplebankcodeManagement001_btn_export' and page_id = 'ProPeoplebankcodeManagement001';
update sys_page_element set is_control = '01' where element_id ='ProPeoplebankcodeManagement001_btn_import' and page_id = 'ProPeoplebankcodeManagement001';
update sys_page_element set is_control = '01' where element_id ='ProPeoplebankcodeManagement001_btn_search' and page_id = 'ProPeoplebankcodeManagement001';
--机构负面信息
update sys_page_element set is_control = '02' where element_id ='AgencyDetailsInf001_i_addTree' and page_id = 'AgencyDetailsInf001';

DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID ='FinNetProductBonusScheme001' and ELEMENT_ID ='FinNetProductBonusScheme001_TBLROW_edit';
 
UPDATE SYS_PAGE_ELEMENT SET REMARK='撤销' where PAGE_ID='FinNetProductBonusScheme001'  and ELEMENT_ID ='FinNetProductBonusScheme001_btn_delete';

--批处理启动
update sys_page_element set is_control = '02' where element_id = 'SysBatchStart001_btn_start' and page_id = 'SysBatchStart001';
--用户管理置
delete from sys_page_element where page_id = 'SysUser001' and element_id = 'SysUser001_btn_pwdreset';
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
VALUES
    ('SysUser001','SysUser001_btn_pwdreset','01','01','密码重置','system',null,sysdate,'system',sysdate);
--报表模板管理
update sys_page_element set is_control = '01' where element_id ='ReportModelManage001_btn_delete' and page_id = 'ReportModelManage001';
--人行产品资产池代码管理
update sys_page_element set is_control = '01' where element_id ='ProPeoplebankcodeManagement001_btn_export' and page_id = 'ProPeoplebankcodeManagement001';
update sys_page_element set is_control = '01' where element_id ='ProPeoplebankcodeManagement001_btn_import' and page_id = 'ProPeoplebankcodeManagement001';
update sys_page_element set is_control = '01' where element_id ='ProPeoplebankcodeManagement001_btn_search' and page_id = 'ProPeoplebankcodeManagement001';
--机构负面信息
update sys_page_element set is_control = '02' where element_id ='AgencyDetailsInf001_i_addTree' and page_id = 'AgencyDetailsInf001';

INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RskValtblPrtBotasset001','RskValtblPrtBotasset001_TBLROW_batch','01','01','','应用','system','',sysdate,'system',sysdate);