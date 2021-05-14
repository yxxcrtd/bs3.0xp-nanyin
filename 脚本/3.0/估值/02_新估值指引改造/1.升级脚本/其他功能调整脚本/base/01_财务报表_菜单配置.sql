/** ============ 画面：利润表-明细列表 add by lby 20201201 start ===========**/
delete from sys_page sp where sp.page_id = 'ValProfitNewQuery002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValProfitNewQuery002', 'VAL', '/valuation/accounadjustmanage/valprofitquerynew/ValProfitNewQuery002', 'valuation/accounadjustmanage/valprofitquerynew/ValProfitNewQuery002', null, null, null, '02', '02', '利润表-明细列表', 'system', null, sysdate, 'system', sysdate);
/** ============ 画面：利润表-明细列表 add by lby 20201201 end ===========**/

/** ============ 画面：资产负债表-明细列表 add by lby 20201201 start ===========**/
delete from sys_page sp where sp.page_id = 'AssetLiabilityStatementNewQuery002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetLiabilityStatementNewQuery002', 'VAL', '/valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery002', 'valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery002', null, null, null, '02', '02', '资产负债表-明细列表', 'system', null, sysdate, 'system', sysdate);
/** ============ 画面：资产负债表-明细列表 add by lby 20201201 end ===========**/

--2020年5月31日 huanghao: 系统菜单表增加三表查询、批量导出菜单
delete from sys_menu where MENU_ID ='VALUATION_ACCOUNTING_001_007_005';
delete from sys_menu where MENU_ID ='VALUATION_ACCOUNTING_001_007_006';
delete from sys_menu where MENU_ID ='VALUATION_ACCOUNTING_001_007_007';
delete from sys_menu where MENU_ID ='VALUATION_ACCOUNTING_001_007_008';

Insert into sys_menu (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values 
('VALUATION_ACCOUNTING_001_007_005','VAL',null,'VALUATION_ACCOUNTING_001_007','AssetLiabilityStatementNewQuery001',5,'02','资产负债表(新)','system',null,sysdate,'system',sysdate);
Insert into sys_menu (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values 
('VALUATION_ACCOUNTING_001_007_006','VAL',null,'VALUATION_ACCOUNTING_001_007','OwnerEquityChangeNew001',6,'03','所有者权益变动表(新)','system',null,sysdate,'system',sysdate);
Insert into sys_menu (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values 
('VALUATION_ACCOUNTING_001_007_007','VAL',null,'VALUATION_ACCOUNTING_001_007','ValProfitNewQuery001',7,'02','利润表(新)','system',null,sysdate,'system',sysdate);
Insert into sys_menu (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values 
('VALUATION_ACCOUNTING_001_007_008','VAL',null,'VALUATION_ACCOUNTING_001_007','ValBatchExport001',8,'02','三表批量导出','system',null,sysdate,'system',sysdate);

--2020年5月31日 huanghao: 系统页面表增加三表查询、批量导出页面
delete from sys_page where PAGE_ID = 'AssetLiabilityStatementNewQuery001';
delete from sys_page where PAGE_ID = 'OwnerEquityChangeNew001';
delete from sys_page where PAGE_ID = 'ValProfitNewQuery001';
delete from sys_page where PAGE_ID = 'ValBatchExport001';

Insert into sys_page (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values 
('AssetLiabilityStatementNewQuery001','VAL','/valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery001','valuation/financialstatement/assetliailitystatementnew/AssetLiabilityStatementNewQuery001','VALUATION_ACCOUNTING_001_007_005',null,null,'02','02','资产负债表(新)','system',null,sysdate,'system',sysdate);
Insert into sys_page (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values 
('OwnerEquityChangeNew001','VAL','/valuation/accounadjustmanage/ownerequitychangenew/OwnerEquityChangeNew001','valuation/accounadjustmanage/ownerequitychangenew/OwnerEquityChangeNew001','VALUATION_ACCOUNTING_001_007_006',null,null,'02','02','所有者权益变动表（新）','system',null,sysdate,'system',sysdate);
Insert into sys_page (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values 
('ValProfitNewQuery001','VAL','/valuation/accounadjustmanage/valprofitquerynew/ValProfitNewQuery001','valuation/accounadjustmanage/valprofitquerynew/ValProfitNewQuery001','VALUATION_ACCOUNTING_001_007_007',null,null,'02','02','利润表(新)','system',null,sysdate,'system',sysdate);
Insert into sys_page (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values 
('ValBatchExport001','VAL','/valuation/accounadjustmanage/valbatchexport/ValBatchExport001','valuation/accounadjustmanage/valbatchexport/ValBatchExport001','VALUATION_ACCOUNTING_001_007_008',null,null,'02','02','三表批量导出','system',null,sysdate,'system',sysdate);

--2020年5月31日 huanghao: 更新旧的三表查询功能名称
update sys_menu set REMARK = '资产负债表(旧)' where MENU_ID ='VALUATION_ACCOUNTING_001_007_001';

update sys_menu set REMARK = '利润表(旧)' where MENU_ID ='VALUATION_ACCOUNTING_001_007_003';

update sys_menu set REMARK = '所有者权益变动表(旧)' where MENU_ID ='VALUATION_ACCOUNTING_001_007_004';

/** modify by yanya on 20201221 现金流量表增加明细查询page**/
delete sys_page where page_id = 'CashFlowsStatement002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CashFlowsStatement002', 'VAL', '/valuation/accounadjustmanage/cashflowsstatement/CashFlowsStatement002', 'valuation/accounadjustmanage/cashflowsstatement/CashFlowsStatement002', 'VALUATION_ACCOUNTING_001_007_002', null, null, '02', '02', '现金流量表', 'system', null, sysDate, 'system', sysDate);

/** modify by yanya on 20210113 删除老的财务三表菜单**/
delete from sys_menu where menu_id in ('VALUATION_ACCOUNTING_001_007_001','VALUATION_ACCOUNTING_001_007_003','VALUATION_ACCOUNTING_001_007_004');


