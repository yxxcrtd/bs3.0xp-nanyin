
delete from sys_page where page_id in ('CfetsTrdRepoTrade002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsTrdRepoTrade002', 'TRD', '/cfets/trdquery/CfetsTrdRepoTrade002', 'cfets/trdquery/CfetsTrdRepoTrade002', 'DEAL_MANAGEMENT_001_002_012', null, null, '02', '01', '银行间交易', 'system', null, sysdate, 'system', sysdate);


delete from sys_wfl_business_module where page_id='CfetsTrdRepoTrade002' and element_id='CfetsTrdRepoTrade002_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsTrdRepoTrade002', 'CfetsTrdRepoTrade002_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

delete from sys_wfl_business_module where page_id='CfetsTrdRepoTrade002' and element_id='CfetsTrdRepoTrade002_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsTrdRepoTrade002', 'CfetsTrdRepoTrade002_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

delete from sys_wfl_business_module where page_id='CfetsTrdRepoTrade002' and element_id='CfetsTrdRepoTrade002_btn_revoke';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsTrdRepoTrade002', 'CfetsTrdRepoTrade002_btn_revoke', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

