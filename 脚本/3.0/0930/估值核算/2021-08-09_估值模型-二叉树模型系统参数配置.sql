----菜单脚本----
delete from SYS_MENU where PAGE_ID='ModBintreeConfig001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_010_003', 'VAL', null, 'VALUATION_001_010', 'ModBintreeConfig001', 3, '2', '二叉树模型配置', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

----页面脚本----
delete from SYS_PAGE where PAGE_ID in('ModBintreeConfig001','ModBintreeConfig002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModBintreeConfig001', 'VAL', '/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig001', 'valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig001', 'VALUATION_001_010_003', null, null, '02', '01', '二叉树模型参数配置', 'system', null, '05-AUG-21 10.50.26.000000 AM', 'system', '05-AUG-21 10.50.26.000000 AM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModBintreeConfig002', 'VAL', '/modelvaluation/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002', 'valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002', null, null, null, '02', '01', '二叉树模型参数配置新建', 'system', null, '01-JUL-21 04.29.38.000000 PM', 'system', '01-JUL-21 04.29.38.000000 PM');
----流程配置----
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID in('ModBintreeConfig002');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModBintreeConfig002', 'ModBintreeConfig002_btn_save', 'oneApproval', 'system', null, '05-AUG-21 03.26.19.000000 PM', 'system', '05-AUG-21 03.26.19.000000 PM', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ModBintreeConfig002', 'ModBintreeConfig002_btn_submit', 'oneApproval', 'system', null, '05-AUG-21 03.26.19.000000 PM', 'system', '05-AUG-21 03.26.19.000000 PM', 'ALL', '02');
----数据字典配置----
delete from SYS_DATADICT where DICT_CODE='MODEL_MARKET_MULTI';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MARKET_MULTI','MOD','市场乘数方法','BOARD_STATUS','02','system',null,sysdate,'system',sysdate);
delete from SYS_DATADICT_LIST where DICT_CODE='MODEL_MARKET_MULTI';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MARKET_MULTI','01','市净率法',1,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_MARKET_MULTI','02','市盈率法',2,'0','system',null,sysdate,'system',sysdate);
delete from SYS_DATADICT where DICT_CODE='MODEL_BOARD_STATUS';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_BOARD_STATUS','MOD','上市状态','BOARD_STATUS','02','system',null,sysdate,'system',sysdate);
delete from SYS_DATADICT_LIST where DICT_CODE='MODEL_BOARD_STATUS';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_BOARD_STATUS','01','上市',1,'0','system',null,sysdate,'system',sysdate);
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_BOARD_STATUS','02','未上市',2,'0','system',null,sysdate,'system',sysdate);

commit;