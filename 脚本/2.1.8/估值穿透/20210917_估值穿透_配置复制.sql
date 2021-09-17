delete from sys_page where page_id='RskValStructure005';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RskValStructure005', 'RSK', '/invest/risk/penetrationmanagement/rskvalstructure/RskValStructure005', 'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure005', null, null, null, '02', '01', '估值表指标结构映射生成', 'system', null,sysdate, 'system',sysdate);
delete from sys_page where page_id='RskValAssetCode006';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RskValAssetCode006', 'RSK', '/invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode006', 'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode006', null, null, null, '02', '01', '估值表资产科目映射生成', 'system', null, sysdate, 'system', sysdate);
delete from sys_page where page_id='RskValSumname005';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RskValSumname005', 'RSK', '/invest/risk/penetrationmanagement/rskvalsumname/RskValSumname005', 'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname005', null, null, null, '02', '01', '估值表组合汇总科目映射生成', 'system', null, sysdate, 'system',sysdate);
