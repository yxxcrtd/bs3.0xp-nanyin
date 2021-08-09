
delete from sys_page t where t.PAGE_ID='LgdSpecialConfig001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LgdSpecialConfig001', 'RSK', '/model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig001', 'lossmodel/lgddefultmanager/lgdspecialconfig/LgdSpecialConfigQuery001', null, null, null, '02', '01', '特殊资产LGD维护查询', 'system', null, '05-AUG-21 02.37.06.000000 PM', 'system', '05-AUG-21 02.37.06.000000 PM');

delete from sys_page t where t.PAGE_ID='LgdSpecialConfig002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LgdSpecialConfig002', 'RSK', '/model/impairment/lgddefultmanager/lgdspecialconfig/LgdSpecialConfig002', 'lossmodel/lgddefultmanager/lgdspecialconfig/LgdSpecialConfigInfo002', null, null, null, '02', '01', '特殊资产LGD维护新建', 'system', null, '05-AUG-21 02.37.06.000000 PM', 'system', '05-AUG-21 02.37.06.000000 PM');

-- 流程按钮添加begin
delete from sys_wfl_business_module t where t.ELEMENT_ID='LgdSpecialConfig001_btn_delete';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LgdSpecialConfig002', 'LgdSpecialConfig001_btn_delete', 'oneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.ELEMENT_ID='LgdSpecialConfig002_btn_save';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LgdSpecialConfig002', 'LgdSpecialConfig002_btn_save', 'oneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

delete from sys_wfl_business_module t where t.ELEMENT_ID='LgdSpecialConfig002_btn_submit';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LgdSpecialConfig002', 'LgdSpecialConfig002_btn_submit', 'oneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

-- 流程按钮添加end

--配置代办事项业务类型begin--
delete from sys_datadict_list t where t.DICT_CODE='COMMON_BUSINESS_TYPE' and t.ITEM_CODE='MOD08';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'MOD08', '特殊资产LGD维护', 1300, '0', 'system', null, '05-JUL-21 03.36.53.000000 PM', 'system', '05-JUL-21 03.36.53.000000 PM');
--配置代办事项业务类型end--


