
/*存款类资产交易菜单*/
delete from SYS_MENU where PAGE_ID='TrdDepositValuationTrade001'
and MENU_ID='DEAL_001_003_002';

INSERT INTO "SYS_MENU"("MENU_ID", "SYSTEM_CODE", "ICON", "PARENT_MENU_ID", "PAGE_ID", "ORDER_NO", "IS_SPREAD", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('DEAL_001_003_002', 'VAL', NULL, 'DEAL_001_003', 'TrdDepositValuationTrade001', '2', '2', '存款类交易', 'system', NULL, TO_TIMESTAMP('2021-12-24 23:29:50.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'), 'system', TO_TIMESTAMP('2021-12-24 23:29:50.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'));


/*查询,新建页面*/
delete from SYS_PAGE where PAGE_ID='TrdDepositValuationTrade001';
delete from SYS_PAGE where PAGE_ID='TrdDepositValuationTrade002';

INSERT INTO "SYS_PAGE"("PAGE_ID", "SYSTEM_CODE", "PAGE_URL", "PAGE_FILE", "MENU_ID", "HEIGHT", "WIDTH", "IS_HP_SELECT", "IS_NEED_WORKFLOW", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('TrdDepositValuationTrade001', 'VAL', '/valuation/trademanage/trddepositvaluationtrade/TrdDepositValuationTrade001', 'valuation/trademanage/trddepositvaluationtrade/TrdDepositValuationTrade001', 'DEAL_001_003_002', NULL, NULL, '02', '02', '存款类交易查询', 'system', NULL, TO_TIMESTAMP('2019-05-24 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'), 'system', TO_TIMESTAMP('2019-05-24 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'));

INSERT INTO "SYS_PAGE"("PAGE_ID", "SYSTEM_CODE", "PAGE_URL", "PAGE_FILE", "MENU_ID", "HEIGHT", "WIDTH", "IS_HP_SELECT", "IS_NEED_WORKFLOW", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('TrdDepositValuationTrade002', 'VAL', '/valuation/trademanage/trddepositvaluationtrade/TrdDepositValuationTrade002', 'valuation/trademanage/trddepositvaluationtrade/TrdDepositValuationTrade002', NULL, NULL, NULL, '02', '02', '存款类交易新建', 'system', NULL, TO_TIMESTAMP('2019-05-24 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'), 'system', TO_TIMESTAMP('2019-05-24 00:00:00.000000', 'SYYYY-MM-DD HH24:MI:SS:FF6'));


/*存款类资产提交,保存按钮*/

delete from sys_wfl_business_module where page_id='TrdDepositValuationTrade002';

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('TrdDepositValuationTrade002', 'TrdDepositValuationTrade002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('TrdDepositValuationTrade002', 'TrdDepositValuationTrade002_btn_sbumit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');