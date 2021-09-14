--曲线来源数据字典
delete from sys_datadict where dict_code='CURVE_SOURCE';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CURVE_SOURCE', 'VAL', '曲线来源', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where dict_code='CURVE_SOURCE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CURVE_SOURCE', '01', '自定义曲线', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CURVE_SOURCE', '02', '第三方曲线', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

--收益率曲线新增字段
alter table MST_CURVE_DEF add CURVE_GROUP_TYPE VARCHAR2(20);
comment on column MST_CURVE_DEF.CURVE_GROUP_TYPE is '是否曲线族  Y是 N 否';

alter table MST_CURVE_DEF add GRADE_RESULT VARCHAR2(50);
comment on column MST_CURVE_DEF.GRADE_RESULT is '评级结果';

alter table MST_CURVE_DEF add CURVE_GROUP_CODE VARCHAR2(50);
comment on column MST_CURVE_DEF.CURVE_GROUP_CODE is '所属曲线族';

alter table MST_CURVE_DEF add CCY VARCHAR2(50);
comment on column MST_CURVE_DEF.CCY is '币种';

alter table MST_CURVE_DEF add CURVE_NAME VARCHAR2(50);
comment on column MST_CURVE_DEF.CURVE_NAME is '曲线名称';

--工作流查询
delete from sys_datadict_list T where dict_code= 'COMMON_BUSINESS_TYPE' AND item_code ='MOD13' and item_value='收益法曲线管理';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('COMMON_BUSINESS_TYPE', 'MOD13', '收益法曲线管理', 1304, '0', 'system', null, sysdate, 'system', sysdate, null);

--修改CURVE_TYPE可为null
alter table MST_CURVE_DEF modify CURVE_TYPE null;

--收益率曲线管理工作流配置
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('MstCurveDefManage002', 'MstCurveDefManage001_btn_delete', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('MstCurveDefManage002', 'MstCurveDefManage002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('MstCurveDefManage002', 'MstCurveDefManage002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

--收益法管理配置，设置支持工作流
update sys_page set is_need_workflow='01' where page_id='MstCurveDefManage002' ;
update sys_page set is_need_workflow='01' where page_id='MstCurveDefManage001' ;



