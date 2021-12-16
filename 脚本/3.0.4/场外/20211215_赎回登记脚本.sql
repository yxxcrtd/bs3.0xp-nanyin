delete from sys_menu where page_id='CfetsTrdRedeem';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DEAL_MANAGEMENT_001_002_013', 'TRD', null, 'DEAL_MANAGEMENT_001_002', 'CfetsTrdRedeem', 13, '01', '赎回登记', 'system', null, sysdate, 'system', sysdate);
delete from sys_page where page_id='CfetsTrdRedeem';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsTrdRedeem', 'TRD', 'home.html#/interbank/redeem?a=a', '/cfets/trdredeem/CfetsTrdRedeem', 'DEAL_MANAGEMENT_001_002_013', null, null, '02', '01', '赎回登记', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where page_id in ('CfetsTrdRedeem001');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsTrdRedeem001', 'TRD', '/cfets/trdredeem/CfetsTrdRedeem001', '/cfets/trdredeem/CfetsTrdRedeem001', 'DEAL_MANAGEMENT_001_002_013', null, null, '02', '01', '赎回登记', 'system', null, sysdate, 'system', sysdate);


delete from sys_wfl_business_module where page_id='CfetsTrdRedeem001' and element_id='CfetsTrdRedeem001_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsTrdRedeem001', 'CfetsTrdRedeem001_btn_submit', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

delete from sys_wfl_business_module where page_id='CfetsTrdRedeem001' and element_id='CfetsTrdRedeem001_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsTrdRedeem001', 'CfetsTrdRedeem001_btn_save', 'oneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');





