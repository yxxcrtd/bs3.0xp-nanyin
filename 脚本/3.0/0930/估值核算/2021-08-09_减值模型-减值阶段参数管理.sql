--减值阶段参数管理

delete from sys_page t where t.PAGE_ID='ModStagePara001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara001', 'RSK', '/model/impairment/phaseparameter/ModStagePara001', 'lossmodel/phaseparameter/ModStagePara001', 'VALUATION_001_011_006', null, null, '02', '01', '减值阶段参数管理', 'system', null, '05-AUG-21 03.10.42.000000 PM', 'system', '05-AUG-21 03.10.42.000000 PM');

delete from sys_page t where t.PAGE_ID='ModStagePara002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModStagePara002', 'RSK', '/model/impairment/phaseparameter/ModStagePara002', 'lossmodel/phaseparameter/ModStagePara002', null, null, null, '02', '01', '减值阶段参数修改', 'system', null, '05-AUG-21 03.10.42.000000 PM', 'system', '05-AUG-21 03.10.42.000000 PM');

delete from sys_menu t where t.PAGE_ID='ModStagePara001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_011_006', 'VAL', null, 'VALUATION_001_011', 'ModStagePara001', 5, '2', '减值阶段参数管理', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');