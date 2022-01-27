--新增菜单
delete from sys_page where PAGE_ID in ('ManualSettleQuery','CfetsManualSettleUpdate','CfetsManualSettle');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ManualSettleQuery', 'STL', 'home.html#/interbank/settlementmanage?a=a', 'cfets/manualsettle/ManualSettleQuery', 'QS_MANAGEMENT_001_010_001', '', '', '02', '01', '交收管理', 'system', '', sysdate, 'system', sysdate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsManualSettleUpdate', 'STL', '/cfets/manualsettle/ManualSettleUpdate', 'cfets/manualsettle/ManualSettleUpdate', '', '', '', '02', '01', '交收管理修改', 'system', '', sysdate, 'system', sysdate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsManualSettle', 'STL', '/cfets/manualsettle/CfetsManualSettle', 'cfets/manualsettle/CfetsManualSettle', '', '', '', '02', '01', '交收管理', 'system', '', sysdate, 'system', sysdate);

delete from sys_menu where MENU_ID in ('QS_MANAGEMENT_001_010','QS_MANAGEMENT_001_010_001');
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QS_MANAGEMENT_001_010', 'STL', '', 'QS_MANAGEMENT_001', '', 8, '01', '交收管理', 'system', '', sysdate, 'system', sysdate);
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('QS_MANAGEMENT_001_010_001', 'STL', '', 'QS_MANAGEMENT_001_010', 'ManualSettleQuery', 1, '01', '交收管理', 'system', '', sysdate, 'system', sysdate);

--自动刷新
delete from sys_para t where t.para_code='cfetsManualSettle_tradeList_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('STL', 'cfetsManualSettle_tradeList_refresh_period', '30000', '01', '', '交收列表自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');

--增加日终自动交收系统参数
delete from sys_para where SYSTEM_CODE ='INV' and PARA_CODE='auto_settle_flag';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('INV', 'auto_settle_flag', 'N', '', '', '日终自动交收Y:自动交收;N:不自动交收', 'system', '', sysdate, 'system', sysdate, '', '01');

--共通业务类型增加枚举值
delete from sys_datadict_list where DICT_CODE ='COMMON_BUSINESS_TYPE' and ITEM_CODE='STL06';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('COMMON_BUSINESS_TYPE', 'STL06', '交收管理', 1301, '0', 'system', '', sysdate, 'system', sysdate, '');

--手工交收新增共通业务类型
delete from sys_page_dictlist where PAGE_ID='MainPage' and DICT_ID = 'COMMON_BUSINESS_TYPE' and DICT_ITEM_VAL='STL06';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('MainPage', '999999', 'COMMON_BUSINESS_TYPE', 'STL06', '', '', 'system', '', sysdate, 'system', sysdate, '');

--交收操作类型增加枚举值
delete from sys_datadict_list where DICT_CODE ='DELIVERY_OP_TYPE' and ITEM_CODE = '05';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DELIVERY_OP_TYPE', '05', '交收数据修改', 1, '0', 'system', '', sysdate, 'system', sysdate, '');

--清算状态
delete from sys_datadict where dict_code='SETTLE_STATUS';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_STATUS', 'TG', '清算状态', '', '02', 'system', '', sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE ='SETTLE_STATUS';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SETTLE_STATUS', 'S', '清算成功', 1, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SETTLE_STATUS', 'F', '清算失败', 1, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SETTLE_STATUS', 'A', '清算中', 1, '0', 'system', '', sysdate, 'system', sysdate, '');

--结算指令状态
delete from sys_datadict where dict_code='CLEAN_STL_STATUS';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CLEAN_STL_STATUS', 'TG', '结算指令状态', '', '02', 'system', '', sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE ='CLEAN_STL_STATUS';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '01', '待复核', 1, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '02', '已复核', 2, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '03', '待确认', 3, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '04', '已确认', 4, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '05', '合法', 5, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '06', '非法', 6, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '07', '作废', 7, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '08', '撤销', 8, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '09', '未生效', 09, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '10', '风控检查中', 10, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '11', '风控检查不通过', 11, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '12', '风控检查通过', 12, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '13', '待轧差', 13, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_STL_STATUS', '14', '已轧差', 14, '0', 'system', '', sysdate, 'system', sysdate, '');

--结算合同状态
delete from sys_datadict where dict_code='CLEAN_ARG_STATUS';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CLEAN_ARG_STATUS', 'TG', '结算合同状态', '', '02', 'system', '', sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE ='CLEAN_ARG_STATUS';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '01', '成功', 1, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '02', '等券', 2, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '03', '等款', 3, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '04', '待生效', 4, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '05', '待履行', 5, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '06', '应履行', 6, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '07', '履行中', 7, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '08', '部分过户', 8, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '09', '现金了结', 09, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '10', '逾期完成', 10, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '11', '已清偿', 11, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '12', '失败', 12, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '13', '取消', 13, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '14', '撤消', 14, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '15', '作废', 15, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '16', '交割中', 16, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '17', '修改中', 17, '0', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CLEAN_ARG_STATUS', '18', '终止', 18, '0', 'system', '', sysdate, 'system', sysdate, '');


--画面配置数据字典
--资产类型
delete from sys_page_dictlist where PAGE_ID = 'cfetsManualSettleQuery' and DICT_ID='PINAN_ASSET_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PINAN_ASSET_TYPE', 'F01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PINAN_ASSET_TYPE', 'F07', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PINAN_ASSET_TYPE', 'F10', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PINAN_ASSET_TYPE', 'F12', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PINAN_ASSET_TYPE', 'F16', '', '', 'system', '', sysdate, 'system', sysdate, '');

--业务类型
delete from sys_page_dictlist where PAGE_ID = 'cfetsManualSettleQuery' and DICT_ID='BUSI_TYPE_POSITION';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '04', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '05', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '06', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '07', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '08', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '10', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '11', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '12', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '13', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'BUSI_TYPE_POSITION', '15', '', '', 'system', '', sysdate, 'system', sysdate, '');

--交易方向
delete from sys_page_dictlist where PAGE_ID = 'cfetsManualSettleQuery' and DICT_ID='PS';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', 'T01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', 'T02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', 'T03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', 'T07', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', 'T08', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', 'T09', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', 'T39', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', '03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', '04', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', '05', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('cfetsManualSettleQuery', '999999', 'PS', '06', '', '', 'system', '', sysdate, 'system', sysdate, '');


--配置画面流程
delete from sys_wfl_business_module where PAGE_ID in ('CfetsManualSettleUpdate','CfetsManualSettle');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsManualSettleUpdate', 'CfetsManualSettleUpdate_btn_submit', 'noneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsManualSettle', 'CfetsManualSettle_btn_submit', 'noneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('CfetsManualSettle', 'CfetsManualSettle_btn_modify', 'noneApproval', 'system', '0001', sysdate, 'system', sysdate, 'ALL', '02');

--债券存续期自动生成(前后端分离)批处理
delete from sys_batch_task where TASK_ID ='10017';
delete from sys_batch_job where BATCH_SEQ_NO ='inv3'and TASK_ID = '10017';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('10017', 'INV', '债券存续期自动生成(前后端分离)', '', 'cfetsbondAutoDurDealGenerateTask', 22, 'system', '', sysdate, 'system', sysdate, '');

insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('inv3', '10017', 'system', '', sysdate, 'system', sysdate);

--自动交收批处理
delete from sys_batch_task where TASK_ID ='10020';
delete from sys_batch_job where BATCH_SEQ_NO ='inv2'and TASK_ID = '10020';
insert into sys_batch_task (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('10020', 'STL', '自动交收批处理', '', 'cfetsSettleTask', 22, 'system', '', sysdate, 'system', sysdate, '');

insert into sys_batch_job (BATCH_SEQ_NO, TASK_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('inv2', '10020', 'system', '', sysdate, 'system', sysdate);
-- Create sequence
drop sequence SETTLE_SEQ;
create sequence SETTLE_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

delete from MST_BUSINESS_ID_RULE where SEQ_NO='9992';
insert into MST_BUSINESS_ID_RULE (SEQ_NO, COMMON_BUSINESS_TYPE, TYPE_1, TYPE_2, TYPE_3, TYPE_4, TYPE_5, TYPE_6, BUSINESS_ID_RULE_TYPE, PREFIX_NAME, PREFIX_SEQUENCE_NAME, PREFIX_SEQUENCE_LEN, POSTFIX_NAME, POSTFIX_SEQUENCE_NAME, POSTFIX_SEQUENCE_LEN, DATE_RULE_TYPE, BUSINESS_ID_SERVICE_NAME, BUSINESS_ID_METHOD_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('9992', 'STL06', '', '', '', '', '', '', '04', 'SETL', '', null, '', 'SETTLE_SEQ', 8, '01', '', '', 'system', '', sysdate, 'system', sysdate);

--银行间手工交收流水表
DROP TABLE TRD_BANK_MANUAL_SETTLE_FLOW;
CREATE TABLE TRD_BANK_MANUAL_SETTLE_FLOW  (
  SEQ_NO              VARCHAR(32) NOT NULL ,
  SETTLE_SEQ_NO       VARCHAR(32),
  SETTLE_TIME         TIMESTAMP,
  FINPROD_ID          VARCHAR(50),
  FINPROD_ABBR        VARCHAR(50),
  SETTLE_NUM          NUMERIC(30, 14),
  SETTLE_AMT          NUMERIC(30, 2),
  OPER_TYPE           VARCHAR(50),
  CREATE_USER         VARCHAR(20) NOT NULL ,
  CREATE_DEPT         VARCHAR(32),
  CREATE_TIME         TIMESTAMP NOT NULL ,
  UPDATE_USER         VARCHAR(20) NOT NULL ,
  UPDATE_TIME         TIMESTAMP NOT NULL ,
  CONSTRAINT PK_TRD_BANK_MANUAL_SETTLE_FLOW PRIMARY KEY (SEQ_NO)
);
COMMENT ON TABLE TRD_BANK_MANUAL_SETTLE_FLOW IS 'tabledesc';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.SEQ_NO IS '交收流水序号';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.SETTLE_SEQ_NO IS '交收编号';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.SETTLE_TIME IS '交收时间';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.FINPROD_ID IS '金融产品代码';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.FINPROD_ABBR IS '金融产品简称';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.SETTLE_NUM IS '交割数量';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.SETTLE_AMT IS '交割金额';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.OPER_TYPE IS '操作类型';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.CREATE_USER IS '创建人';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.CREATE_DEPT IS '创建部门';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.UPDATE_USER IS '更新人';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_FLOW.UPDATE_TIME IS '更新时间';

--银行间手工交收信息表
DROP TABLE TRD_BANK_MANUAL_SETTLE_INFO;
CREATE TABLE TRD_BANK_MANUAL_SETTLE_INFO  (
  SETTLE_SEQ_NO       VARCHAR(32) NOT NULL ,
  SETTLE_STATUS       VARCHAR(50),
  TRADE_ID            VARCHAR(32),
  SETTLE_DATE         DATE,
  FINPROD_ID          VARCHAR(50),
  SETTLE_NUM          NUMERIC(30, 14),
  SETTLE_AMT          NUMERIC(30, 2),
  BUSI_TYPE           VARCHAR(50),
  CREATE_USER         VARCHAR(20) NOT NULL ,
  CREATE_DEPT         VARCHAR(32),
  CREATE_TIME         TIMESTAMP NOT NULL ,
  UPDATE_USER         VARCHAR(20) NOT NULL ,
  UPDATE_TIME         TIMESTAMP NOT NULL ,
  CONSTRAINT PK_TRD_BANK_MANUAL_SETTLE_INFO PRIMARY KEY (SETTLE_SEQ_NO)
);
COMMENT ON TABLE TRD_BANK_MANUAL_SETTLE_INFO IS 'tabledesc';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.SETTLE_SEQ_NO IS '交收编号';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.SETTLE_STATUS IS '交收状态';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.TRADE_ID IS '原业务编号';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.SETTLE_DATE IS '交收日期';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.FINPROD_ID IS '金融产品代码';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.SETTLE_NUM IS '交割数量';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.SETTLE_AMT IS '交割金额';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.BUSI_TYPE IS '业务类别';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.CREATE_USER IS '创建人';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.CREATE_DEPT IS '创建部门';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.UPDATE_USER IS '更新人';
COMMENT ON COLUMN TRD_BANK_MANUAL_SETTLE_INFO.UPDATE_TIME IS '更新时间';

--银行间手工交收明细
DROP TABLE TRD_BANK_MANUAL_SET_DETAIL;
CREATE TABLE TRD_BANK_MANUAL_SET_DETAIL  (
  SEQ_NO              VARCHAR(32) NOT NULL ,
  SETTLE_SEQ_NO       VARCHAR(32),
  SETTLE_STATUS       VARCHAR(50),
  SETTLE_DATE         DATE,
  TRADE_ID            VARCHAR(32),
  FINPROD_ID          VARCHAR(50),
  FINPROD_ABBR        VARCHAR(50),
  PORTFOLIO_ID        VARCHAR(32),
  SETTLE_NUM          NUMERIC(30, 14),
  SETTLE_AMT          NUMERIC(30, 2),
  BUSI_TYPE           VARCHAR(50),
  CREATE_USER         VARCHAR(20) NOT NULL ,
  CREATE_DEPT         VARCHAR(32),
  CREATE_TIME         TIMESTAMP NOT NULL ,
  UPDATE_USER         VARCHAR(20) NOT NULL ,
  UPDATE_TIME         TIMESTAMP NOT NULL ,
  CONSTRAINT PK_TRD_BANK_MANUAL_SET_DETAIL PRIMARY KEY (SEQ_NO)
);
COMMENT ON TABLE TRD_BANK_MANUAL_SET_DETAIL IS 'tabledesc';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.SEQ_NO IS '交收流水序号';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.SETTLE_SEQ_NO IS '交收编号';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.SETTLE_STATUS IS '交收状态';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.SETTLE_DATE IS '交收日期';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.TRADE_ID IS '原业务编号';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.FINPROD_ID IS '金融产品代码';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.FINPROD_ABBR IS '金融产品简称';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.PORTFOLIO_ID IS '组合代码';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.SETTLE_NUM IS '交割数量';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.SETTLE_AMT IS '交割金额';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.BUSI_TYPE IS '业务类别';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.CREATE_USER IS '创建人';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.CREATE_DEPT IS '创建部门';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.UPDATE_USER IS '更新人';
COMMENT ON COLUMN TRD_BANK_MANUAL_SET_DETAIL.UPDATE_TIME IS '更新时间';

--手工交收新增交收失败原因
-- Add/modify columns
alter table TRD_BANK_MANUAL_SETTLE_INFO add REMARK VARCHAR2(200);
-- Add comments to the columns
comment on column TRD_BANK_MANUAL_SETTLE_INFO.REMARK
  is '交收失败原因';