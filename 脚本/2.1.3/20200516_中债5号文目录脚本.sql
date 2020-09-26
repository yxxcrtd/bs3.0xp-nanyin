delete from sys_menu where MENU_ID='REPORT_001_003_011'; 
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_001_003_011', 'SVR', null, 'REPORT_001_003', 'SvrTransMonthReport001', 11, '02', '理财产品转型情况月度统计表', 'system', null, '12-9月 -19 12.00.00.000000 上午', 'system', '12-9月 -19 12.00.00.000000 上午');
delete from sys_page where page_id in ('SvrTransMonthReport001');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvrTransMonthReport001', 'SVR', '/chb/transmonthreport/ChbTransMonthReport001', 'report/chb/transmonthreport/ChbTransMonthReport001', 'REPORT_001_003_011', null, null, '01', '01', '理财产品转型情况月度统计表', 'system', null, '12-9月 -19 08.24.15.000000 下午', 'system', '12-9月 -19 08.24.15.000000 下午');
commit;