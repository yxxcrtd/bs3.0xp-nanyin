
--债券标的
delete from sys_page where page_id in ('FinBondTargetAsset001','FinBondTargetAsset002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinBondTargetAsset001', 'FIN', '/invest/asset/bond/FinBondTargetAsset001', 'invest/asset/bond/FinBondTargetAsset001', 'ASSET_MANAGEMENT_001_002_006', null, null, '02', '01', '债券标的', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinBondTargetAsset002', 'FIN', '/invest/asset/bond/FinBondTargetAsset002', 'invest/asset/bond/FinBondTargetAsset002', null, null, null, '02', '01', '债券新建', 'system', null, sysdate, 'system', sysdate);

--公募基金标的
delete from sys_page where page_id in ('FinFundTarget001','FinFundTarget002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinFundTarget001', 'FIN', '/invest/asset/fund/FinFundTarget001', 'invest/asset/fund/FinFundTarget001', 'ASSET_MANAGEMENT_001_002_007', null, null, '02', '01', '公募基金标的', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinFundTarget002', 'FIN', '/invest/asset/fund/FinFundTarget002', 'invest/asset/fund/FinFundTarget002', null, null, null, '02', '01', '基金类标的信息新建', 'system', null, sysdate, 'system', sysdate);


--市场机构
delete from sys_page where PAGE_ID in ('MstMarketInstitutions001','MstMarketInstitutions002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstMarketInstitutions001', 'MST', '/invest/basicdata/mstmarketinstitutions/MstMarketInstitutions001', 'invest/basicdata/mstmarketinstitutions/MstMarketInstitutions001', 'BUSINESS_PARA_MANAGEMENT_001_003_003', null, null, '02', '01', '市场机构管理', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstMarketInstitutions002', 'MST', '/invest/basicdata/mstmarketinstitutions/MstMarketInstitutions002', 'invest/basicdata/mstmarketinstitutions/MstMarketInstitutions002', null, null, null, '02', '01', '市场机构信息新建', 'system', null, sysdate, 'system', sysdate);


--日历类型管理
delete from sys_page where page_id in ('MstCalendarType001','MstCalendarType002','MstCalendarType003','MstCalendarType004');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstCalendarType001', 'MST', '/invest/basicdata/mstcalendartype/MstCalendarType001', 'invest/basicdata/mstcalendartype/MstCalendarType001', 'BUSINESS_PARA_MANAGEMENT_001_003_005', null, null, '02', '01', '日历类型管理', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstCalendarType002', 'MST', '/invest/basicdata/mstcalendartype/MstCalendarType002', 'invest/basicdata/mstcalendartype/MstCalendarType002', null, null, null, '02', '01', '日历类型信息管理新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstCalendarType003', 'MST', '/invest/basicdata/mstcalendartype/MstCalendarType003', 'invest/basicdata/mstcalendartype/MstCalendarType003', null, null, null, '02', '02', '节假日查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('MstCalendarType004', 'MST', '/invest/basicdata/mstcalendartype/MstCalendarType004', 'invest/basicdata/mstcalendartype/MstCalendarType004', null, null, null, '02', '01', '日历类型信息管理', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

--下游数据发布
delete from sys_page where PAGE_ID = 'TrdInterfDataImport001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdInterfDataImport001', 'TRD', '/invest/interf/TrdInterfDataImport001', 'invest/interf/TrdInterfDataImport001', 'SYSTEM_001_003_006', null, null, '02', '02', '接口数据下行', 'system', null, sysdate, 'system', sysdate);





