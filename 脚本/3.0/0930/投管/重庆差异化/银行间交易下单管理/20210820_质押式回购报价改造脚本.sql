delete
from SYS_MENU
where MENU_ID = 'IB_DEAL_MANAGEMENT_001_002_002';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK,
                      CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('IB_DEAL_MANAGEMENT_001_002_002', 'STL', null, 'IB_DEAL_MANAGEMENT_001_002', 'CfetsRepoDealQuotedPrMain', 5,
        '02', 'CFETS质押式回购报价', 'system', null, systimestamp,
        'system', systimestamp);
delete
from SYS_PAGE
where PAGE_ID = 'CfetsRepoDealQuotedPrMain';
delete
from SYS_PAGE
where PAGE_ID = 'CfetsRepoDealQuotedPr001';
delete
from SYS_PAGE
where PAGE_ID = 'CfetsRepoDealQuotedPr002';
delete
from SYS_PAGE
where PAGE_ID = 'CfetsRepoDealQuotedPr003';
delete
from SYS_PAGE
where PAGE_ID = 'CfetsRepoDealQuotedPr006';
delete
from SYS_PAGE
where PAGE_ID = 'CfetsRepoDealQuotedPrFile';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT,
                      IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                      UPDATE_TIME)
VALUES ('CfetsRepoDealQuotedPrMain', 'CFETS', '/invest/bankuplink/reponew/CfetsRepoDealQuotedPrMain',
        'invest/bankuplink/reponew/CfetsRepoDealQuotedPrMain', null, null, null, '02', '01',
        'CFETS质押式回购报价', 'system', null, systimestamp,
        'system', systimestamp);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT,
                      IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                      UPDATE_TIME)
VALUES ('CfetsRepoDealQuotedPr001', 'CFETS', '/invest/bankuplink/reponew/CfetsRepoDealQuotedPr001',
        'invest/bankuplink/reponew/CfetsRepoDealQuotedPr001', null, null, null, '02', '01',
        'CFETS质押式回购报价', 'system', null, systimestamp,
        'system', systimestamp);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT,
                      IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                      UPDATE_TIME)
VALUES ('CfetsRepoDealQuotedPr002', 'CFETS', '/invest/bankuplink/reponew/CfetsRepoDealQuotedPr002',
        'invest/bankuplink/reponew/CfetsRepoDealQuotedPr002', null, null, null, '02', '01',
        'CFETS质押式回购报价', 'system', null, systimestamp,
        'system', systimestamp);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT,
                      IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                      UPDATE_TIME)
VALUES ('CfetsRepoDealQuotedPr003', 'CFETS', '/invest/bankuplink/reponew/CfetsRepoDealQuotedPr003',
        'invest/bankuplink/reponew/CfetsRepoDealQuotedPr003', null, null, null, '02', '01',
        'CFETS质押式回购报价', 'system', null, systimestamp,
        'system', systimestamp);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT,
                      IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                      UPDATE_TIME)
VALUES ('CfetsRepoDealQuotedPr006', 'CFETS', '/invest/bankuplink/reponew/CfetsRepoDealQuotedPr006',
        'invest/bankuplink/reponew/CfetsRepoDealQuotedPr006', null, null, null, '02', '01',
        '质押券置换', 'system', null, systimestamp,
        'system', systimestamp);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT,
                      IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                      UPDATE_TIME)
VALUES ('CfetsRepoDealQuotedPrFile', 'CFETS', '/invest/bankuplink/reponew/CfetsRepoDealQuotedPrFile',
        'invest/bankuplink/reponew/CfetsRepoDealQuotedPrFile', null, null, null, '02', '01',
        '附件上传', 'system', null, systimestamp,
        'system', systimestamp);


delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'CfetsRepoDealQuotedPr002';
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'CfetsRepoDealQuotedPr003';
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'CfetsRepoDealQuotedPr006';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                     UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('CfetsRepoDealQuotedPr002', 'CfetsRepoDealQuotedPrice002_btn_done', 'oneApproval', 'system', null,
        systimestamp, 'system',
        systimestamp, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                     UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('CfetsRepoDealQuotedPr002', 'CfetsRepoDealQuotedPrice002_btn_repoRevoke', 'oneApproval', 'system', null,
        systimestamp, 'system',
        systimestamp, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                     UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('CfetsRepoDealQuotedPr002', 'CfetsRepoDealQuotedPrice002_btn_save', 'oneApproval', 'system', null,
        systimestamp, 'system',
        systimestamp, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                     UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('CfetsRepoDealQuotedPr002', 'CfetsRepoDealQuotedPrice002_btn_submit', 'oneApproval', 'system', null,
        systimestamp, 'system',
        systimestamp, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                     UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('CfetsRepoDealQuotedPr003', 'CfetsRepoDealQuotedPrice003_btn_done', 'oneApproval', 'system', '0001',
        systimestamp, 'system',
        systimestamp, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                     UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('CfetsRepoDealQuotedPr003', 'CfetsRepoDealQuotedPrice003_btn_repoRevoke', 'oneApproval', 'system', '0001',
        systimestamp, 'system',
        systimestamp, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                     UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('CfetsRepoDealQuotedPr003', 'CfetsRepoDealQuotedPrice003_btn_save', 'oneApproval', 'system', '0001',
        systimestamp, 'system',
        systimestamp, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER,
                                     UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('CfetsRepoDealQuotedPr003', 'CfetsRepoDealQuotedPrice003_btn_submit', 'oneApproval', 'system', '0001',
        systimestamp, 'system',
        systimestamp, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME,
                                                  UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('CfetsRepoDealQuotedPr006', 'CfetsRepoDealQuotedPr006_btn_save', 'oneApproval', 'system', '0001',
        systimestamp, 'system',
        systimestamp, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME,
                                                  UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
VALUES ('CfetsRepoDealQuotedPr006', 'CfetsRepoDealQuotedPr006_btn_submit', 'oneApproval', 'system', '0001',
        systimestamp, 'system',
        systimestamp, 'ALL', '02');