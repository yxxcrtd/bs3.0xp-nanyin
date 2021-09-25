--资产系列管理-移动至新批复按钮工作流配置
delete from sys_wfl_business_module where PAGE_ID='CeAssetGroupManagement005' and ELEMENT_ID='CeAssetGroupManagement005_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CeAssetGroupManagement005', 'CeAssetGroupManagement005_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');