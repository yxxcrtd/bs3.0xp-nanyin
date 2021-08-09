
DELETE FROM sys_menu WHERE PAGE_ID = 'InsideOutsideGrade001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ASSET_MANAGEMENT_001_003_020', 'VAL', null, 'ASSET_MANAGEMENT_001_003', 'InsideOutsideGrade001', 20, '2', '评级关系映射', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');

DELETE FROM sys_page WHERE PAGE_ID = 'InsideOutsideGrade001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InsideOutsideGrade001', 'VAL', '/model/impairment/insideoutsidegrademanagement/InsideOutsideGrade001', 'lossmodel/insideoutsidegrademanagement/InsideOutsideGrade001', 'ASSET_MANAGEMENT_001_003_020', null, null, '02', '01', '内外部评级映射管理', 'system', null, '05-AUG-21 03.29.20.000000 PM', 'system', '05-AUG-21 03.29.20.000000 PM');
DELETE FROM sys_page WHERE PAGE_ID = 'InsideOutsideGrade002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InsideOutsideGrade002', 'VAL', '/model/impairment/insideoutsidegrademanagement/InsideOutsideGrade002', 'lossmodel/insideoutsidegrademanagement/InsideOutsideGrade002', null, null, null, '02', '01', '内外部评级映射管理新建', 'system', null, '05-AUG-21 03.29.20.000000 PM', 'system', '05-AUG-21 03.29.20.000000 PM');

-- 内部评级枚举值初始化 start
insert into SYS_DATADICT values ('MODEL_STANDARD_GRADE_RESULT','MOD','模型_标准评级结果','STANDARD_GRADE_RESULT','02','system','',to_date('2019/06/24', 'yyyy/mm/dd'), 'system',to_date('2019/06/24', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','01','AAA+','01','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','02','AAA','02','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','03','AAA-','03','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','04','AA+','04','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','05','AA','05','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','06','AA-','06','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','07','A+','07','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','08','A','08','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','09','A-','09','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','10','BBB+','10','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','11','BBB','11','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','12','BBB-','12','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','13','BB','13','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','14','B','14','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','15','CCC','15','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','16','CC','16','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','17','C','17','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MODEL_STANDARD_GRADE_RESULT','18','D','18','0','system','',to_date('2021/6/15', 'yyyy/mm/dd'), 'system',to_date('2021/6/15', 'yyyy/mm/dd'));
-- end


-- 流程按钮添加begin
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InsideOutsideGrade002', 'ValInsideOutsideGrade002_btn_submit', 'noneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InsideOutsideGrade002', 'ValInsideOutsideGrade001_btn_delete', 'noneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

--insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
--values ('AssetCategories002', 'ValAssetCategories002_btn_save', 'oneApproval', 'system', null, to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'), 'system', to_timestamp(to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS'));

-- 流程按钮添加end

-- 内外部评级映射管理配置的业务类型begin
insert into sys_datadict_list values('COMMON_BUSINESS_TYPE','MOD09','内外部评级映射管理',1225,0,'system','',sysdate,'system',sysdate);
-- end
