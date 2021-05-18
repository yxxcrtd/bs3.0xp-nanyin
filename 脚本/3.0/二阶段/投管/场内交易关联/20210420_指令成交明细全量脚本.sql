/*新增：指令成交明细查询菜单*/
delete from sys_menu t where t.menu_id = 'INVEST_MANAGEMENT_001_002_026';

insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INVEST_MANAGEMENT_001_002_026', 'ETR', null, 'INVEST_MANAGEMENT_001_002', 'EtrTransactionDetail001', 26, '02', '指令成交明细查询', 'system', 'system', sysdate, 'system', sysdate);

/*新增：指令成交明细查询页面配置*/
delete from sys_page t where t.page_id = 'EtrTransactionDetail001';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrTransactionDetail001', 'ETR', '/invest/entrust/etrtransactiondetail/EtrTransactionDetail001', 'invest/entrust/etrtransactiondetail/EtrTransactionDetail001', 'INVEST_MANAGEMENT_001_002_026', null, null, '02', '01', '指令成交明细查询', 'system', null, sysdate, 'system', sysdate);


/*导出显示列除外画面参数*/
delete from SYS_PARA where para_code = 'not_show_export_by_column_page';
insert into SYS_PARA values ('SYS','not_show_export_by_column_page','PtlPositionAnalysis001,EtrTransactionDetail001,PreTrdRepoTrade001','01','','','system','',sysdate, 'system',sysdate,null,'01');

/*【导出按钮变成按钮组：导出全部列+导出显示列】相关脚本*/
delete from SYS_PARA WHERE PARA_CODE = 'export_by_column';
insert into SYS_PARA values ('SYS','export_by_column','02','01','','','system','',sysdate, 'system',sysdate,null,'01');

-- 角色权限新建时角色权限名称改成联想框
delete from SYS_PARA WHERE PARA_CODE = 'authority_create_lenovo';
insert into SYS_PARA values ('SYS','authority_create_lenovo','01','01','','','system','',sysdate, 'system', sysdate, null, '01');

-- 导出显示列报错修改
ALTER TABLE SYS_ACTIONLOG MODIFY ELEMENT_ID VARCHAR(100);