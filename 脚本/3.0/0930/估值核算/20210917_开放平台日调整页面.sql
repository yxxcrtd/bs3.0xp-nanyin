--开放平台日调整页面
delete from sys_menu WHERE  PAGE_ID='SysDate001' ;
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYSTEM_001_003_006', 'SYS', null, 'SYSTEM_001_003', 'SysDate001', 7, '2', '平台日调整', 'system', null, sysdate, 'system',sysdate);


--现金流折现模型曲线配置中，曲线类型配置
delete from sys_page_dictlist where PAGE_ID='CashFlowDiscount002' and DICT_ID='CURVE_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CashFlowDiscount002', '999999', 'CURVE_TYPE', 'MA', null, null, 'system', null, '25-AUG-21 05.29.43.000000 PM', 'system', '25-AUG-21 05.29.43.000000 PM', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CashFlowDiscount002', '999999', 'CURVE_TYPE', 'SP', null, null, 'system', null, '25-AUG-21 05.29.43.000000 PM', 'system', '25-AUG-21 05.29.43.000000 PM', null);





