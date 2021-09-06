--机构名单管理查询页删除按钮工作流配置
delete from sys_wfl_business_module where PAGE_ID='OrgNameManagement002' and ELEMENT_ID='OrgNameManagement001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('OrgNameManagement002', 'OrgNameManagement001_btn_delete', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');


--资产名单管理查询页删除按钮工作流配置
delete from sys_wfl_business_module where PAGE_ID='AssetNameManagement002' and ELEMENT_ID='AssetNameManagement001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('AssetNameManagement002', 'AssetNameManagement001_btn_delete', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');