delete from sys_page where page_id in ('BondPtlFeePay001','BondPtlFeePay002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BondPtlFeePay001', 'PTL', '/invest/portfolio/cebbank/bondptlfeepay/BondPtlFeePay001', 'invest/portfolio/cebbank/bondptlfeepay/BondPtlFeePay001', 'PTL_MANAGEMENT_001_002_006', null, null, '02', '01', '证券组合费支付', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('BondPtlFeePay002', 'PTL', '/invest/portfolio/cebbank/bondptlfeepay/BondPtlFeePay002', 'invest/portfolio/cebbank/bondptlfeepay/BondPtlFeePay002', null, null, null, '02', '01', '证券组合费支付新建', 'system', null, sysdate, 'system', sysdate);