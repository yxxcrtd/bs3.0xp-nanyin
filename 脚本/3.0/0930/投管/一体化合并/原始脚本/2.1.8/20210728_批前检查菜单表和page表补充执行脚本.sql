--------------批前检查菜单权限
 
delete from sys_menu where page_id = 'SysPrebatchResult001';

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_006_011', 'SYS', null, 'SYSTEM_001_006', 'SysPrebatchResult001', 12, '02', '批前检查', 'system', null, sysdate, 'system', sysdate);

   
delete from sys_page where page_id in ( 'SysPrebatchResult001','SysPrebatchResult002');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysPrebatchResult001', 'SYS', '/invest/prebatch/SysPrebatchResult001', 'invest/prebatch/SysPrebatchResult001', 'SYSTEM_001_006_011', null, null, '01', '02', '批前检查', 'system', null, sysdate, 'system', sysdate);

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysPrebatchResult002', 'SYS', '/invest/prebatch/SysPrebatchResult002', 'invest/prebatch/SysPrebatchResult002', null, null, null, '01', '02', '批前检查明细', 'system', null, sysdate, 'system', sysdate);
