/*场外期权标的*/
------------------------------------新增：资产信息菜单	start---------------------------------
delete from sys_menu where menu_id = 'VALINFOSERVICE_001_020';

insert into sys_menu 
(MENU_ID, SYSTEM_CODE, ICON, 
PARENT_MENU_ID, PAGE_ID, ORDER_NO, 
IS_SPREAD, REMARK, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('VALINFOSERVICE_001_020', 'VAL', null, 
'VALINFOSERVICE_001', null, 19, 
'03', '资产信息', 'system', 
null, sysdate, 'system', sysdate);
------------------------------------新增：资产信息菜单	end---------------------------------

------------------------------------新增：场外期权标的菜单	start---------------------------------
delete from sys_menu where menu_id = 'VALINFOSERVICE_001_020_001';

insert into sys_menu 
(MENU_ID, SYSTEM_CODE, ICON, 
PARENT_MENU_ID, PAGE_ID, ORDER_NO, 
IS_SPREAD, REMARK, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('VALINFOSERVICE_001_020_001', 'VAL', null, 
'VALINFOSERVICE_001_020', 'FinOptionTarget001', 1, 
'03', '场外期权标的', 'system', 
null, sysdate, 'system', sysdate);
------------------------------------新增：场外期权标的菜单	end---------------------------------


------------------------------------新增：场外期权标的页面	start---------------------------------
delete from sys_page where page_id = 'FinOptionTarget001';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, 
PAGE_FILE, MENU_ID, HEIGHT, 
WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, 
REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('FinOptionTarget001', 'VAL', '/valuation/basicdata/asset/option/FinOptionTarget001', 
'valuation/basicdata/asset/option/FinOptionTarget001', 'VALINFOSERVICE_001_020_001', null,
 null, '02', '01', 
 '场外期权标的', 'system', null, 
 sysdate, 'system', sysdate);
 
 
 
 delete from sys_page where page_id = 'FinOptionTarget002';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, 
PAGE_FILE, MENU_ID, HEIGHT, 
WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, 
REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('FinOptionTarget002', 'VAL', '/valuation/basicdata/asset/option/FinOptionTarget002', 
'valuation/basicdata/asset/option/FinOptionTarget002', 'VALINFOSERVICE_001_020_001', null,
 null, '02', '01', 
 '场外期权标的-新建', 'system', null, 
 sysdate, 'system', sysdate);
------------------------------------新增：场外期权标的页面	end---------------------------------

---------------------------------------场外期权标的：流程模板	start-------------------------------
delete from sys_wfl_business_module where page_id = 'FinOptionTarget002' and element_id = 'FinOptionTarget002_btn_submit';

insert into sys_wfl_business_module 
(PAGE_ID, ELEMENT_ID, MODULE_ID, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, 
UPDATE_USER, UPDATE_TIME)
values 
('FinOptionTarget002', 'FinOptionTarget002_btn_submit', 'oneApproval', 
'system', null, sysdate, 
'system', sysdate);




delete from sys_wfl_business_module where page_id = 'FinOptionTarget002' and element_id = 'FinOptionTarget001_btn_delete';

insert into sys_wfl_business_module 
(PAGE_ID, ELEMENT_ID, MODULE_ID, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, 
UPDATE_USER, UPDATE_TIME)
values 
('FinOptionTarget002', 'FinOptionTarget001_btn_delete', 'oneApproval', 
'system', null, sysdate, 
'system', sysdate);



delete from sys_wfl_business_module where page_id = 'FinOptionTarget002' and element_id = 'FinOptionTarget002_btn_save';

insert into sys_wfl_business_module 
(PAGE_ID, ELEMENT_ID, MODULE_ID, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, 
UPDATE_USER, UPDATE_TIME)
values 
('FinOptionTarget002', 'FinOptionTarget002_btn_save', 'oneApproval', 
'system', null, sysdate, 
'system', sysdate);
---------------------------------------场外期权标的：流程模板	end-------------------------------


--存款类资产
DELETE FROM SYS_MENU WHERE MENU_ID='VALINFOSERVICE_001_020_004';
DELETE FROM SYS_PAGE WHERE PAGE_ID IN ('FinDepositTarget001','FinDepositTarget002');
insert into SYS_MENU values ('VALINFOSERVICE_001_020_004','VAL','','VALINFOSERVICE_001_020','FinDepositTarget001','4','02','存款类资产','system','',to_date('2021/03/08', 'yyyy/mm/dd'), 'system',to_date('2021/03/08', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('FinDepositTarget001','VAL','/valuation/basicdata/asset/depo/FinDepositTarget001','valuation/basicdata/asset/depo/FinDepositTarget001','VALINFOSERVICE_001_020_004','','','02','01','存款类资产管理','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('FinDepositTarget002','VAL','/valuation/basicdata/asset/depo/FinDepositTarget002','valuation/basicdata/asset/depo/FinDepositTarget002','VALINFOSERVICE_001_020_004','','','02','01','存款类资产新建','system','',to_date('2021/03/08', 'yyyy/mm/dd'), 'system',to_date('2021/03/08', 'yyyy/mm/dd'));


/*存款类资产_流程模板配置*/
DELETE FROM SYS_WFL_BUSINESS_MODULE WHERE PAGE_ID ='FinDepositTarget002';
insert into SYS_WFL_BUSINESS_MODULE values ('FinDepositTarget002','FinDepositTarget001_btn_delete','oneApproval','system','',to_date('2021/03/22', 'yyyy/mm/dd'), 'system',to_date('2021/03/22', 'yyyy/mm/dd'),'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE values ('FinDepositTarget002','FinDepositTarget002_btn_submit','oneApproval','system','',to_date('2021/03/22', 'yyyy/mm/dd'), 'system',to_date('2021/03/22', 'yyyy/mm/dd'),'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE values ('FinDepositTarget002','FinDepositTarget002_btn_save','oneApproval','system','',to_date('2021/03/22', 'yyyy/mm/dd'), 'system',to_date('2021/03/22', 'yyyy/mm/dd'),'ALL','02');


------------------------------------新增：基金类页面	start---------------------------------
delete from sys_page where page_id = 'FinFundTarget001';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, 
PAGE_FILE, MENU_ID, HEIGHT, 
WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, 
REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('FinFundTarget001', 'VAL', '/valuation/basicdata/asset/fund/FinFundTarget001', 
'valuation/basicdata/asset/fund/FinFundTarget001', 'VALINFOSERVICE_001_020_003', null,
 null, '02', '01', 
 '基金类资产', 'system', null, 
 sysdate, 'system', sysdate);
 
 
 
delete from sys_page where page_id = 'FinFundTarget002';

insert into sys_page 
(PAGE_ID, SYSTEM_CODE, PAGE_URL, 
PAGE_FILE, MENU_ID, HEIGHT, 
WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, 
REMARK, CREATE_USER, CREATE_DEPT, 
CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('FinFundTarget002', 'VAL', '/valuation/basicdata/asset/fund/FinFundTarget002', 
'valuation/basicdata/asset/fund/FinFundTarget002', 'VALINFOSERVICE_001_020_003', null,
 null, '02', '01', 
 '新建-基金类资产', 'system', null, 
 sysdate, 'system', sysdate);
------------------------------------新增：基金类页面	end---------------------------------




------------------------------------新增：基金类菜单	start---------------------------------
delete from sys_menu where menu_id = 'VALINFOSERVICE_001_020_003';

insert into sys_menu 
(MENU_ID, SYSTEM_CODE, ICON, 
PARENT_MENU_ID, PAGE_ID, ORDER_NO, 
IS_SPREAD, REMARK, CREATE_USER, 
CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('VALINFOSERVICE_001_020_003', 'VAL', null, 
'VALINFOSERVICE_001_020', 'FinFundTarget001', 3, 
'03', '基金类资产', 'system', 
null, sysdate, 'system', sysdate);
------------------------------------新增：基金类菜单	end---------------------------------

/** ----------------业务流程模板  add by 李炳言 计划类标的流程添加 on 20210312 start--------------- **/
delete from SYS_WFL_BUSINESS_MODULE SWBM WHERE SWBM.PAGE_ID = 'FinPlanAssetC002';

insert into SYS_WFL_BUSINESS_MODULE values ('FinPlanAssetC002','FinPlanAssetC001_btn_delete','oneApproval','system','',to_date('2021/03/12', 'yyyy/mm/dd'), 'system',to_date('2021/03/12', 'yyyy/mm/dd'),'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE values ('FinPlanAssetC002','FinPlanAssetC002_btn_save','oneApproval','system','',to_date('2021/03/12', 'yyyy/mm/dd'), 'system',to_date('2021/03/12', 'yyyy/mm/dd'),'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE values ('FinPlanAssetC002','FinPlanAssetC002_btn_submit','oneApproval','system','',to_date('2021/03/12', 'yyyy/mm/dd'), 'system',to_date('2021/03/12', 'yyyy/mm/dd'),'ALL','02');
/** ----------------业务流程模板  add by 李炳言 计划类标的流程添加 on 20210312 end--------------- **/

--画面元素表
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID='FinDepositTarget001';
insert into SYS_PAGE_ELEMENT values ('FinDepositTarget001','FinDepositTarget001_btn_rateAdj','01','01','','利率调整','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT values ('FinDepositTarget001','FinDepositTarget001_btn_add','01','01','','新建','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT values ('FinDepositTarget001','FinDepositTarget001_btn_clear','01','02','','重置','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT values ('FinDepositTarget001','FinDepositTarget001_btn_delete','01','01','','删除','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT values ('FinDepositTarget001','FinDepositTarget001_btn_export','01','01','','导出','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT values ('FinDepositTarget001','FinDepositTarget001_btn_search','01','01','','查询','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT values ('FinDepositTarget001','FinDepositTarget002_btn_save','01','02','','保存','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'COM');
insert into SYS_PAGE_ELEMENT values ('FinDepositTarget001','FinDepositTarget002_btn_submit','01','02','','提交','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'COM');

--画面数据字典明细关联表
DELETE FROM SYS_PAGE_DICTLIST WHERE PAGE_ID='FinDepositTarget002';
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','INT_TYPE','01','999999','固定利率','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','BASIS','A/360','999999','A/360','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','BASIS','A/365','999999','A/365','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','TDAY_RULE','04','FinPayInterestStdInfo','不调整','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','TDAY_RULE','02','FinPayInterestStdInfo','向后调整','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','FREQUENCY_UNIT','05','FinPayInterestStdInfo','年付','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','FREQUENCY_UNIT','07','FinPayInterestStdInfo','到期支付','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','FREQUENCY_UNIT','06','FinPayInterestStdInfo','半年付','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','FREQUENCY_UNIT','03','FinPayInterestStdInfo','月付','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','FREQUENCY_UNIT','04','FinPayInterestStdInfo','季付','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinDepositTarget002','FREQUENCY_UNIT','08','FinPayInterestStdInfo','不规则','','system','',to_date('2021/03/20', 'yyyy/mm/dd'), 'system',to_date('2021/03/20', 'yyyy/mm/dd'),'ALL');


--------------------------------------------基金类资产_新建画面基金类型数据字典		start----------------------------------
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'FinFundTarget002' and DICT_ID = 'FUND_TYPE';

insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinFundTarget002','FUND_TYPE','01','999999','货币基金','','system','',to_date('2021/03/22', 'yyyy/mm/dd'), 'system',to_date('2021/03/22', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinFundTarget002','FUND_TYPE','02','999999','债券基金','','system','',to_date('2021/03/22', 'yyyy/mm/dd'), 'system',to_date('2021/03/22', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinFundTarget002','FUND_TYPE','03','999999','股票基金','','system','',to_date('2021/03/22', 'yyyy/mm/dd'), 'system',to_date('2021/03/22', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinFundTarget002','FUND_TYPE','04','999999','混合基金','','system','',to_date('2021/03/22', 'yyyy/mm/dd'), 'system',to_date('2021/03/22', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinFundTarget002','FUND_TYPE','05','999999','其他公募基金','','system','',to_date('2021/03/22', 'yyyy/mm/dd'), 'system',to_date('2021/03/22', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinFundTarget002','FUND_TYPE','06','999999','私募基金','','system','',to_date('2021/03/22', 'yyyy/mm/dd'), 'system',to_date('2021/03/22', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinFundTarget002','FUND_TYPE','09','999999','FOF','','system','',to_date('2021/03/22', 'yyyy/mm/dd'), 'system',to_date('2021/03/22', 'yyyy/mm/dd'));

--------------------------------------------基金类资产_新建画面基金类型数据字典		end----------------------------------


/** 标的维护债券类资产画面添加**/
delete from SYS_MENU where menu_id = 'VALINFOSERVICE_001_020_002';
insert into SYS_MENU values ('VALINFOSERVICE_001_020_002','VAL','','VALINFOSERVICE_001_020','FinBondTargetAsset001','1','02','债券类资产','system','',to_date('2021/03/05', 'yyyy/mm/dd'), 'system',to_date('2021/03/05', 'yyyy/mm/dd'));
delete from SYS_PAGE where PAGE_ID in ('FinBondTargetAsset001','FinBondTargetAsset002');
insert into SYS_PAGE values ('FinBondTargetAsset001','VAL','/valuation/basicdata/asset/bond/FinBondTargetAsset001','valuation/basicdata/asset/bond/FinBondTargetAsset001','VALINFOSERVICE_001_020_002','','','02','01','债券类资产','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('FinBondTargetAsset002','VAL','/valuation/basicdata/asset/bond/FinBondTargetAsset002','valuation/basicdata/asset/bond/FinBondTargetAsset002','VALINFOSERVICE_001_020_002','','','02','01','债券类资产-新建','system','',to_date('2021/03/02', 'yyyy/mm/dd'), 'system',to_date('2021/03/02', 'yyyy/mm/dd'));

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID in ('FinBondTargetAsset002');
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','FinBondTargetAsset001_btn_delete','oneApproval','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)  values ('FinBondTargetAsset002','FinBondTargetAsset002_btn_save','oneApproval','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)  values ('FinBondTargetAsset002','FinBondTargetAsset002_btn_submit','oneApproval','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));

delete from SYS_PAGE_DICTLIST where page_id ='FinBondTargetAsset002';
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','REGIST_ORG','01','999999','中央国债登记结算有限责任公司','','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','REGIST_ORG','02','999999','上海清算所','','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','REGIST_ORG','03','999999','中国证券登记结算有限责任公司','','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','REGIST_ORG','06','999999','中证机构间报价系统股份有限公司','','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','REGIST_ORG','99','999999','其他','','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TRADE_PLACE','01','999999','银行间','','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TRADE_PLACE','03','999999','上海证券交易所','','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TRADE_PLACE','04','999999','深圳证券交易所','','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TRADE_PLACE','05','999999','机构间私募系统','','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TRADE_PLACE','10','999999','境外交易场所','','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));



delete from SYS_DATADICT_LIST_LINK t where t.dict_code = 'BOND_TYPE' and t.linked_dict_code = 'TRADE_PLACE';
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','001','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','002','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','003','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','004','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','005','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','006','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','007','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','008','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','009','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','010','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','011','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','012','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','013','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','014','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','015','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','016','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','017','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','018','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','019','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','020','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','021','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','022','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','023','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','024','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','025','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','026','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','027','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','028','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','029','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','030','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','031','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','032','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','033','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','034','TRADE_PLACE','01','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','001','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','002','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','003','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','004','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','005','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','006','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','007','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','008','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','009','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','010','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','011','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','012','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','013','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','014','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','015','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','016','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','017','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','018','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','019','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','020','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','021','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','022','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','023','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','024','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','025','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','026','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','027','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','028','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','029','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','030','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','031','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','032','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','033','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','034','TRADE_PLACE','03','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','001','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','002','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','003','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','004','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','005','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','006','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','007','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','008','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','009','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','010','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','011','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','012','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','013','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','014','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','015','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','016','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','017','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','018','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','019','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','020','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','021','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','022','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','023','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','024','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','025','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','026','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','027','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','028','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','029','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','030','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','031','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','032','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','033','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','034','TRADE_PLACE','04','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','001','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','002','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','003','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','004','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','005','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','006','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','007','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','008','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','009','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','010','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','011','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','012','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','013','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','014','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','015','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','016','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','017','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','018','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','019','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','020','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','021','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','022','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','023','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','024','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','025','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','026','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','027','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','028','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','029','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','030','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','031','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','032','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','033','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','034','TRADE_PLACE','10','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','015','TRADE_PLACE','05','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','024','TRADE_PLACE','05','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','025','TRADE_PLACE','05','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','026','TRADE_PLACE','05','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST_LINK values ('BOND_TYPE','027','TRADE_PLACE','05','system','',to_date('2021/03/06', 'yyyy/mm/dd'), 'system',to_date('2021/03/06', 'yyyy/mm/dd'));

delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'FinFundTarget002' and ELEMENT_ID = 'FinFundTarget001_btn_delete';

insert into SYS_WFL_BUSINESS_MODULE 
(PAGE_ID, ELEMENT_ID, MODULE_ID, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, 
UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values 
('FinFundTarget002', 'FinFundTarget001_btn_delete', 'oneApproval', 
'system', null, sysdate, 
'system', sysdate, 'ALL', '02');




delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'FinFundTarget002' and ELEMENT_ID = 'FinFundTarget002_btn_submit';

insert into SYS_WFL_BUSINESS_MODULE 
(PAGE_ID, ELEMENT_ID, MODULE_ID, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, 
UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values 
('FinFundTarget002', 'FinFundTarget002_btn_submit', 'oneApproval', 
'system', null, sysdate, 
'system', sysdate, 'ALL', '02');



delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'FinFundTarget002' and ELEMENT_ID = 'FinFundTarget002_btn_save';

insert into SYS_WFL_BUSINESS_MODULE 
(PAGE_ID, ELEMENT_ID, MODULE_ID, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, 
UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values 
('FinFundTarget002', 'FinFundTarget002_btn_save', 'oneApproval', 
'system', null, sysdate, 
'system', sysdate, 'ALL', '02');


/** ===== 画面数据字典明细关联 计划类资产--收益类型 add by 李炳言 on 20210312  start===== **/
delete from sys_page_dictlist spd where spd.page_id in ('FinPlanAssetC001','FinPlanAssetC002');

insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinPlanAssetC002','PROFIT_TYPE','01','999999','净值型','','system','',to_date('2021/03/12', 'yyyy/mm/dd'), 'system',to_date('2021/03/12', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinPlanAssetC002','PROFIT_TYPE','03','999999','货币型','','system','',to_date('2021/03/12', 'yyyy/mm/dd'), 'system',to_date('2021/03/12', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinPlanAssetC001','PROFIT_TYPE','01','999999','净值型','','system','',to_date('2021/03/12', 'yyyy/mm/dd'), 'system',to_date('2021/03/12', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinPlanAssetC001','PROFIT_TYPE','03','999999','货币型','','system','',to_date('2021/03/12', 'yyyy/mm/dd'), 'system',to_date('2021/03/12', 'yyyy/mm/dd'));
/** ===== 画面数据字典明细关联 计划类资产--收益类型 add by 李炳言 on 20210312  end===== **/

/** modify by yanya on 20210325 调整金融交易查询删除走流程**/
update sys_page t set t.is_need_workflow = '01' where page_id = 'ValFinprodTradeQuery001';

/** modify by yanya on 20210319 增加利率调整**/
delete from SYS_PAGE where page_id = 'FinAssetRateAdj001';
insert into SYS_PAGE values ('FinAssetRateAdj001','VAL','/valuation/component/asset/FinAssetRateAdj001','valuation/component/asset/FinAssetRateAdj001','','','','02','01','利率调整','system','',to_date('2021/03/19', 'yyyy/mm/dd'), 'system',to_date('2021/03/19', 'yyyy/mm/dd'));
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'FinAssetRateAdj001';
insert into SYS_WFL_BUSINESS_MODULE(PAGE_ID,ELEMENT_ID,MODULE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinAssetRateAdj001','FinAssetRateAdj001_btn_submit','oneApproval','system','',to_date('2021/03/19', 'yyyy/mm/dd'), 'system',to_date('2021/03/19', 'yyyy/mm/dd'));

delete from SYS_PAGE_ELEMENT t where t.page_id = 'FinBondTargetAsset001' and ELEMENT_ID = 'FinBondTargetAsset001_btn_rateAdj';
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset001','FinBondTargetAsset001_btn_rateAdj','01','02','','利率调整','system','',to_date('2021/03/19', 'yyyy/mm/dd'), 'system',to_date('2021/03/19', 'yyyy/mm/dd'));
delete from SYS_PAGE_ELEMENT t where t.page_id = 'FinAssetRateAdj001';
insert into SYS_PAGE_ELEMENT(PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)  values ('FinAssetRateAdj001','FinAssetRateAdj001_btn_submit','01','02','','提交','system','',to_date('2021/03/19', 'yyyy/mm/dd'), 'system',to_date('2021/03/19', 'yyyy/mm/dd'));

/**add by yanya on 20210311 债券类资产：页面数据字典**/


delete from SYS_PAGE_DICTLIST t where t.page_id = 'FinBondTargetAsset002' ;
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','BASIS','A/365','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','BASIS','A/365F','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','BASIS','A/A','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','BASIS','30U/360','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','BASIS','30/360','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','BASIS','A/360','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','CALC_METHOD','01','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','CALC_METHOD','04','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','CALC_RULE','01','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','CALC_RULE','03','999999','首次付息日','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinBondTargetAsset002','EXEC_MODE','01','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'), 'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinBondTargetAsset002','EXEC_MODE','03','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'), 'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinBondTargetAsset002','EXEC_MODE','04','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'), 'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinBondTargetAsset002','EXEC_MODE','02','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'), 'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinBondTargetAsset002','EXEC_MODE','05','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'), 'ALL');

insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','FREQUENCY_UNIT','05','FinPayInterestStdInfo','年付','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','FREQUENCY_UNIT','07','FinPayInterestStdInfo','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','FREQUENCY_UNIT','06','FinPayInterestStdInfo','半年付','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','FREQUENCY_UNIT','03','FinPayInterestStdInfo','月付','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','FREQUENCY_UNIT','04','FinPayInterestStdInfo','季付','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','INTPAY_CYCLE','04','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','INTPAY_CYCLE','03','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','INTPAY_CYCLE','02','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','INTPAY_CYCLE','05','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','INT_TYPE','02','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','INT_TYPE','01','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TDAY_RULE','04','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TDAY_RULE','02','999999','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','REGIST_ORG','01','999999','中央国债登记结算有限责任公司','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','REGIST_ORG','02','999999','上海清算所','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','REGIST_ORG','03','999999','中国证券登记结算有限责任公司','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','REGIST_ORG','06','999999','中证机构间报价系统股份有限公司','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','REGIST_ORG','99','999999','其他','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TRADE_PLACE','01','999999','银行间','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TRADE_PLACE','03','999999','上海证券交易所','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TRADE_PLACE','04','999999','深圳证券交易所','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TRADE_PLACE','05','999999','机构间私募系统','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TRADE_PLACE','10','999999','境外交易场所','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TDAY_RULE','04','FinPayInterestStdInfo','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('FinBondTargetAsset002','TDAY_RULE','02','FinPayInterestStdInfo','','','system','',to_date('2021/03/11', 'yyyy/mm/dd'), 'system',to_date('2021/03/11', 'yyyy/mm/dd'));






































