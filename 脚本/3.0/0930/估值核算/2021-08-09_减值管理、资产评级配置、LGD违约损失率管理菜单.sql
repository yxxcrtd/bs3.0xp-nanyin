delete from SYS_MENU where PAGE_ID='AssetRatingAllocation001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_008_015', 'VAL', null, 'VALUATION_001_008', 'AssetRatingAllocation001', 15, '2', '资产评级来源配置', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');
delete from SYS_MENU where PAGE_ID='LossGivenDefaultManage001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_011_005', 'VAL', null, 'VALUATION_001_011', 'LossGivenDefaultManage001', 4, '2', 'LGD违约损失率管理', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');
commit;