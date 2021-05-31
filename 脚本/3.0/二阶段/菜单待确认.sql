/**======= 画面：估值减值优化升级 ADD BY lby 2020.11.16 start======**/
delete from sys_page sp where sp.page_id = 'ValUpVersion001';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValUpVersion001', 'VAL', '/valuation/system/valupversion/ValUpVersion001', 'valuation/system/valupversion/ValUpVersion001', 'SYSTEM_001_006_010', null, null, '02', '01', '估值减值优化升级', 'system', null, SYSDATE, 'system', SYSDATE);
/**======= 画面：估值减值优化升级 ADD BY lby 2020.11.16 end======**/


/**======= 画面：估值减值优化升级 ADD BY lby 2020.11.17 start======**/
delete from sys_page sp where sp.page_id = 'ValUpVersion002';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValUpVersion002', 'VAL', '/valuation/system/valupversion/ValUpVersion002', 'valuation/system/valupversion/ValUpVersion002', null, null, null, '02', '01', '估值减值优化升级', 'system', null, SYSDATE, 'system', SYSDATE);
/**======= 画面：估值减值优化升级 ADD BY lby 2020.11.17 end======**/




-----------------------------估值表（数据溯源信息查看）add by zwq start----------------------------------------------------
delete from sys_page where page_id = 'ValBalanceStatementQuery002';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, 
PAGE_FILE, MENU_ID, HEIGHT, 
WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, 
REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('ValBalanceStatementQuery002', 'VAL', '/valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002', 
'valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery002', 'VALSTATEACCOUNT_001_001_001', null, 
null, '02', '02', 
'估值表（数据溯源信息查看）', 'system', null, 
sysdate, 'system', sysdate);
-----------------------------估值表（数据溯源信息查看）add by zwq end----------------------------------------------------





/** modify by yanya on 20210319 增加利率调整**/
delete from SYS_PAGE where page_id = 'FinAssetRateAdj001';
insert into SYS_PAGE values ('FinAssetRateAdj001','VAL','/valuation/component/asset/FinAssetRateAdj001','valuation/component/asset/FinAssetRateAdj001','','','','02','01','利率调整','system','',to_date('2021/03/19', 'yyyy/mm/dd'), 'system',to_date('2021/03/19', 'yyyy/mm/dd'));
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'FinAssetRateAdj001';
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinAssetRateAdj001','FinAssetRateAdj001_btn_submit','oneApproval','system','',to_date('2021/03/19', 'yyyy/mm/dd'), 'system',to_date('2021/03/19', 'yyyy/mm/dd'));
