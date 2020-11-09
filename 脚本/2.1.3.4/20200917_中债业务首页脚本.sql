delete FROM SYS_PAGE WHERE PAGE_ID = 'SvreportHome002';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SvreportHome002', 'SVR', '/system/businessHome', 'report/system/businessHome', null, null, null, '01', '01', 'ÒµÎñÊ×Ò³', 'system', null, SYSDATE, 'system', SYSDATE);


