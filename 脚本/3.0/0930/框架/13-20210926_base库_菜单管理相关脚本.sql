-- 菜单管理功能
DELETE FROM SYS_MENU WHERE MENU_ID = 'SYSTEM_001_001_006';
INSERT INTO SYS_MENU VALUES ('SYSTEM_001_001_006', 'SYS', null, 'SYSTEM_001_001', 'SysMenu001', 6, '02', '菜单管理', 'system', null, sysdate, 'system', sysdate);

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'SysMenu001';
INSERT INTO SYS_PAGE VALUES ('SysMenu001', 'SYS', '/system/menu/SysMenu001', 'system/menu/SysMenu001', 'SYSTEM_001_001_006', null, null, '02', '02', '菜单列表', 'system','',sysdate, 'system',sysdate);

DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'SysMenu001';
INSERT INTO SYS_PAGE_ELEMENT VALUES ('SysMenu001','SysMenu001_btn_search','01','01','','查询','system','',sysdate, 'system',sysdate,'COM');
INSERT INTO SYS_PAGE_ELEMENT VALUES ('SysMenu001','SysMenu001_btn_add','01','01','','新建','system','',sysdate, 'system',sysdate,'COM');
INSERT INTO SYS_PAGE_ELEMENT VALUES ('SysMenu001','SysMenu001_btn_delete','01','01','','删除','system','',sysdate, 'system',sysdate,'COM');
INSERT INTO SYS_PAGE_ELEMENT VALUES ('SysMenu001','SysMenu001_btn_sort','01','01','','更新排序','system','',sysdate, 'system', sysdate,'COM');

DELETE FROM SYS_PAGE WHERE PAGE_ID = 'SysMenu002';
INSERT INTO SYS_PAGE VALUES ('SysMenu002', 'SYS', '/system/menu/SysMenu002', 'system/menu/SysMenu002', null, null, null, '02', '02', '菜单详情', 'system','',sysdate, 'system',sysdate);

DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'SysMenu002';
INSERT INTO SYS_PAGE_ELEMENT VALUES ('SysMenu002','SysMenu002_btn_save','01','01','','保存','system','',sysdate, 'system',sysdate,'C');
INSERT INTO SYS_PAGE_ELEMENT VALUES ('SysMenu002','SysMenu002_btn_submit','01','01','','提交','system','',sysdate, 'system',sysdate,'C');