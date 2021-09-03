/** add by yanay on 20210902 调整计划类资产画面**/
delete from SYS_PAGE where page_id in ('FinPlanAssetC001','FinPlanAssetC002');
insert into SYS_PAGE values ('FinPlanAssetC001','VAL','/valuation/basicdata/asset/plan/plancur/FinPlanAssetC001','valuation/basicdata/asset/plan/plancur/FinPlanAssetC001','ASSET_MANAGEMENT_001_002_004','','','02','01','计划类资产管理','system','', sysDate, 'system', sysDate);
insert into SYS_PAGE values ('FinPlanAssetC002','VAL','/valuation/basicdata/asset/plan/plancur/FinPlanAssetC002','valuation/basicdata/asset/plan/plancur/FinPlanAssetC002','ASSET_MANAGEMENT_001_002_004','','','02','01','计划类资产新建','system','', sysDate, 'system', sysDate);
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'FinPlanAssetC001' and DICT_ID in ('PROFIT_TYPE');
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'FinPlanAssetC002' and DICT_ID in ('PROFIT_TYPE','TDAY_RULE');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinPlanAssetC002','PROFIT_TYPE','01','999999','净值型','','system','', sysDate, 'system', sysDate, 'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinPlanAssetC002','PROFIT_TYPE','03','999999','货币型','','system','', sysDate, 'system', sysDate, 'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinPlanAssetC001','PROFIT_TYPE','01','999999','净值型','','system','', sysDate, 'system', sysDate, 'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinPlanAssetC001','PROFIT_TYPE','03','999999','货币型','','system','', sysDate, 'system', sysDate, 'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinPlanAssetC002','TDAY_RULE','02','999999','向后调整','','system','', sysDate, 'system', sysDate, 'ALL');
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('FinPlanAssetC002','TDAY_RULE','04','999999','不调整','','system','', sysDate, 'system', sysDate, 'ALL');
delete from SYS_WFL_BUSINESS_MODULE where page_id = 'FinPlanAssetC002';
insert into SYS_WFL_BUSINESS_MODULE values ('FinPlanAssetC002','FinPlanAssetC001_btn_delete','oneApproval','system','', sysDate, 'system', sysDate, 'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE values ('FinPlanAssetC002','FinPlanAssetC002_btn_save','oneApproval','system','', sysDate, 'system', sysDate, 'ALL','02');
insert into SYS_WFL_BUSINESS_MODULE values ('FinPlanAssetC002','FinPlanAssetC002_btn_submit','oneApproval','system','', sysDate, 'system', sysDate, 'ALL','02');

update sys_menu set page_id = 'FinPlanAssetC001' where menu_id = 'ASSET_MANAGEMENT_001_002_004';