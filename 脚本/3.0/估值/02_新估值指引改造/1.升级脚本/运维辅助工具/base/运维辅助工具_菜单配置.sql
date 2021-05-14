-----------------------拓展四级科目   sys_menu		start---------------------------------
delete from sys_menu where menu_id='VALUATION_ACCOUNTING_001_008_002';

insert into sys_menu 
(MENU_ID, SYSTEM_CODE, ICON, 
PARENT_MENU_ID, PAGE_ID, ORDER_NO, 
IS_SPREAD, REMARK, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, 
UPDATE_TIME)
values 
('VALUATION_ACCOUNTING_001_008_002', 'VAL', null, 
'VALUATION_ACCOUNTING_001_008', 'ValSubDevelopment001', 4, 
'02', '扩展四级科目', 'system', 
null, SYSDATE, 'system', SYSDATE);
-----------------------拓展四级科目   sys_menu		end---------------------------------






-----------------------拓展四级科目   sys_page		start---------------------------------
delete from sys_page where page_id='ValSubDevelopment001';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, 
PAGE_FILE, MENU_ID, HEIGHT, 
WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, 
REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('ValSubDevelopment001', 'VAL', '/valuation/dailyoperation/dailyaccountmake/valsubdevelopment/ValSubDevelopment001', 
'valuation/dailyoperation/dailyaccountmake/valsubdevelopment/ValSubDevelopment001', 'VALUATION_ACCOUNTING_001_008_002', null, 
null, '02', '02', 
'扩展四级科目', 'system', null, 
SYSDATE, 'system', SYSDATE);

-----------------------拓展四级科目   sys_page		end---------------------------------


-------------------------基础科目性质调整(慎用) 	sys_menu		start----------------------------------
delete from sys_menu where menu_id='VALUATION_ACCOUNTING_001_008_003';

insert into sys_menu 
(MENU_ID, SYSTEM_CODE, ICON, 
PARENT_MENU_ID, PAGE_ID, ORDER_NO, 
IS_SPREAD, REMARK, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, 
UPDATE_TIME)
values 
('VALUATION_ACCOUNTING_001_008_003', 'VAL', null, 
'VALUATION_ACCOUNTING_001_008', 'ValBasicSubAdjust001', 6, 
'02', '基础科目性质调整(慎用)', 'system', 
null, SYSDATE, 'system', SYSDATE);
-------------------------基础科目性质调整(慎用) 	sys_menu		end----------------------------------




-------------------------基础科目性质调整(慎用) 	sys_page		start----------------------------------
delete from sys_page where page_id='ValBasicSubAdjust001';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, 
PAGE_FILE, MENU_ID, HEIGHT, 
WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, 
REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('ValBasicSubAdjust001', 'VAL', '/valuation/dailyoperation/dailyaccountmake/valbasicsubadjust/ValBasicSubAdjust001', 
'valuation/dailyoperation/dailyaccountmake/valbasicsubadjust/ValBasicSubAdjust001', 'VALUATION_ACCOUNTING_001_008_003', null, 
null, '02', '02', 
'基础科目性质调整(慎用)', 'system', null, 
SYSDATE, 'system', SYSDATE);
-------------------------基础科目性质调整(慎用) 	sys_page		end----------------------------------

/** add by yanya on 20201230 增加科目号变更画面及数据字典项**/
delete from SYS_MENU where menu_id = 'VALUATION_ACCOUNTING_001_008_001';
insert into SYS_MENU values ('VALUATION_ACCOUNTING_001_008_001','VAL','','VALUATION_ACCOUNTING_001_008','ValChangeSubjectNo001','1','02','科目信息变更','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
delete from SYS_PAGE where page_id = 'ValChangeSubjectNo001';
insert into SYS_PAGE values ('ValChangeSubjectNo001','VAL','/valuation/dailyoperation/dailyaccountmake/changesubjectno/ValChangeSubjectNo001','valuation/dailyoperation/dailyaccountmake/changesubjectno/ValChangeSubjectNo001','VALUATION_ACCOUNTING_001_008_001','','','02','02','科目信息变更','system','',to_date('2020/01/07', 'yyyy/mm/dd'), 'system',to_date('2020/01/07', 'yyyy/mm/dd'));
delete from SYS_PAGE_ELEMENT where page_id = 'ValChangeSubjectNo001';
insert into SYS_PAGE_ELEMENT values ('ValChangeSubjectNo001','ValChangeSubjectNo001_btn_submit','01','01','','变更','system','',to_date('2020/12/30', 'yyyy/mm/dd'), 'system',to_date('2020/12/30', 'yyyy/mm/dd'),'01');
insert into SYS_PAGE_ELEMENT values ('ValChangeSubjectNo001','ValChangeSubjectNo001_btn_reset','01','01','','重置','system','',to_date('2020/12/30', 'yyyy/mm/dd'), 'system',to_date('2020/12/30', 'yyyy/mm/dd'),'01');
insert into SYS_PAGE_ELEMENT values ('ValChangeSubjectNo001','ValChangeSubjectNo001_btn_TPLDOWLOAD','01','01','','模板下载','system','',to_date('2020/12/30', 'yyyy/mm/dd'), 'system',to_date('2020/12/30', 'yyyy/mm/dd'),'01');


--账务明细代码调整
---菜单
delete from SYS_MENU where MENU_ID in ('VALUATION_ACCOUNTING_001_008','VALUATION_ACCOUNTING_001_008_004','VALUATION_ACCOUNTING_001_008_006');
insert into SYS_MENU values ('VALUATION_ACCOUNTING_001_008','VAL','','VALUATION_ACCOUNTING_001','','8','02','运维辅助工具','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_MENU values ('VALUATION_ACCOUNTING_001_008_004','VAL','','VALUATION_ACCOUNTING_001_008','BokDetailIdUpdate002','4','02','账务明细代码调整','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_MENU values ('VALUATION_ACCOUNTING_001_008_006','VAL','','VALUATION_ACCOUNTING_001_008','BokDetailIdUpdate001','4','02','账务明细代码调整(原)','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));

---页面
delete from SYS_PAGE where page_id in('BokDetailIdUpdate002','BokDetailIdUpdate001');
insert into SYS_PAGE values ('BokDetailIdUpdate001','VAL','/valuation/bokdetailidupdate/BokDetailIdUpdate001','valuation/bokdetailidupdate/BokDetailIdUpdate001','VALUATION_ACCOUNTING_001_008_006','','','02','02','账务明细代码更新','system','',to_date('2020/01/07', 'yyyy/mm/dd'), 'system',to_date('2020/01/07', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('BokDetailIdUpdate002','VAL','/valuation/bokdetailidupdate/BokDetailIdUpdate002','valuation/bokdetailidupdate/BokDetailIdUpdate002','VALUATION_ACCOUNTING_001_008_004','','','02','02','账务明细代码调整','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));

---画面元素表
delete from SYS_PAGE_ELEMENT where page_id ='BokDetailIdUpdate002';
insert into SYS_PAGE_ELEMENT values ('BokDetailIdUpdate002','BokDetailIdUpdate002_btn_preview','01','01','','预览','system','',to_date('2020/12/30', 'yyyy/mm/dd'), 'system',to_date('2020/12/30', 'yyyy/mm/dd'),'01');
insert into SYS_PAGE_ELEMENT values ('BokDetailIdUpdate002','BokDetailIdUpdate002_btn_submit','01','01','','确认调整','system','',to_date('2020/12/30', 'yyyy/mm/dd'), 'system',to_date('2020/12/30', 'yyyy/mm/dd'),'01');
insert into SYS_PAGE_ELEMENT values ('BokDetailIdUpdate002','BokDetailIdUpdate002_btn_reset','01','01','','重置','system','',to_date('2020/12/30', 'yyyy/mm/dd'), 'system',to_date('2020/12/30', 'yyyy/mm/dd'),'01');


---画面数据字典明细关联表
delete from SYS_PAGE_DICTLIST where PAGE_ID='BokDetailIdUpdate002';
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','INV_AIM','FVTPL','999999','','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','INV_AIM','AC','999999','','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','INV_AIM','FVOCI','999999','','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','INV_AIM','SELL','999999','','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F01','999999','债券','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F02','999999','债券质押式正回购','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F03','999999','债券质押式逆回购','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F04','999999','债券买断式正回购','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F05','999999','债券买断式逆回购','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F06','999999','基金','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F07','999999','存款','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F08','999999','拆入','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F09','999999','拆出','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F10','999999','股票','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F11','999999','外汇即期','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F12','999999','外汇远期','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F13','999999','外汇掉期','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F14','999999','利率互换','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F16','999999','理财产品','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F17','999999','计划类资产','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F21','999999','同业借款','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F22','999999','理财子产品','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F23','999999','债券借贷融入','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F24','999999','债券借贷融出','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F25','999999','融入债券','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F33','999999','期货','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F34','999999','货币掉期','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','FINPROD_TYPE','F35','999999','期权','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','ACCT_TYPE','A01','999999','产品托管户','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','ACCT_TYPE','A04','999999','结算备付金账户','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','ACCT_TYPE','A06','999999','交易保证金账户','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BokDetailIdUpdate002','ACCT_TYPE','A07','999999','券商资金账户','','system','',to_date('2020/12/29', 'yyyy/mm/dd'), 'system',to_date('2020/12/29', 'yyyy/mm/dd'));

-------------------------账务明细代码加密	sys_menu		start----------------------------------
delete from sys_menu where menu_id='VALUATION_ACCOUNTING_001_008_005';

insert into sys_menu 
(MENU_ID, SYSTEM_CODE, ICON, 
PARENT_MENU_ID, PAGE_ID, ORDER_NO, 
IS_SPREAD, REMARK, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, 
UPDATE_TIME)
values 
('VALUATION_ACCOUNTING_001_008_005', 'VAL', null, 
'VALUATION_ACCOUNTING_001_008', 'ValAccountEncryption001', 5, 
'02', '账务明细代码加密', 'system', 
null, SYSDATE, 'system', 
SYSDATE);
-------------------------账务明细代码加密	sys_menu		end----------------------------------



-------------------------账务明细代码加密	sys_page		start----------------------------------
delete from sys_page where page_id='ValBasicSubAdjust001';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, 
PAGE_FILE, MENU_ID, HEIGHT, 
WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, 
REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('ValAccountEncryption001', 'VAL', '/valuation/dailyoperation/dailyaccountmake/valaccountencryption/ValAccountEncryption001', 
'valuation/dailyoperation/dailyaccountmake/valaccountencryption/ValAccountEncryption001', 'VALUATION_ACCOUNTING_001_008_005', null, 
null, '02', '02', 
'账务明细代码加密', 'system', null, 
SYSDATE, 'system', SYSDATE);
-------------------------账务明细代码加密	sys_page		end----------------------------------

--账务明细代码加密
---画面数据字典明细关联表
delete from SYS_PAGE_DICTLIST where PAGE_ID='ValAccountEncryption001';
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F01','999999','债券','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F02','999999','债券质押式正回购','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F03','999999','债券质押式逆回购','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F04','999999','债券买断式正回购','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F05','999999','债券买断式逆回购','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F06','999999','基金','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F07','999999','存款','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F08','999999','拆入','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F09','999999','拆出','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F10','999999','股票','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F11','999999','外汇即期','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F12','999999','外汇远期','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F13','999999','外汇掉期','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F14','999999','利率互换','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F16','999999','理财产品','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F17','999999','计划类资产','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F21','999999','同业借款','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F22','999999','理财子产品','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F23','999999','债券借贷融入','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F24','999999','债券借贷融出','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F25','999999','融入债券','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F33','999999','期货','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F34','999999','货币掉期','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','FINPROD_TYPE','F35','999999','期权','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','ACCT_TYPE','A01','999999','产品托管户','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','ACCT_TYPE','A04','999999','结算备付金账户','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','ACCT_TYPE','A06','999999','交易保证金账户','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValAccountEncryption001','ACCT_TYPE','A07','999999','券商资金账户','','system','',to_date('2021/01/04', 'yyyy/mm/dd'), 'system',to_date('2021/01/04', 'yyyy/mm/dd'));

