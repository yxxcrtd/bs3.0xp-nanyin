----清除错误的流程配置
delete from sys_wfl_business_module where PAGE_ID = 'EtrProject003' and ELEMENT_ID in ('EtrProject002_btn_save','EtrProject002_btn_submit');
----插入正确的流程配置
delete from sys_wfl_business_module where PAGE_ID = 'EtrProject003' and ELEMENT_ID in ('EtrProject003_btn_save','EtrProject003_btn_submit');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('EtrProject003', 'EtrProject003_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('EtrProject003', 'EtrProject003_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

