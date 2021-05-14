-- sppI 测试
delete from SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'BOND_SPPI_USE_TEMPLATE';
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VAL', 'BOND_SPPI_USE_TEMPLATE', '01', '01', '01,02,03', '债券类资产SPPI测试模板', 'system', null, systimestamp, 'system', systimestamp);

delete from SYS_PARA where SYSTEM_CODE = 'VAL' and PARA_CODE = 'NOT_BOND_SPPI_USE_TEMPLATE';
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VAL', 'NOT_BOND_SPPI_USE_TEMPLATE', '02', '01', '01,02,03', '非标准化债权资产SPPI测试模板', 'system', null, systimestamp, 'system', systimestamp);


delete from sys_datadict where DICT_CODE = 'SPPI_TEST_STATUS';
delete from sys_datadict_list where DICT_CODE = 'SPPI_TEST_STATUS';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SPPI_TEST_STATUS', 'VAL', 'SPPI测试状态', null, '02', 'system', null, systimestamp, 'system', systimestamp);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SPPI_TEST_STATUS', '0', '未测试', 1, null, 'system', null, systimestamp, 'system', systimestamp);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SPPI_TEST_STATUS', '1', '测试中', 2, null, 'system', null, systimestamp, 'system', systimestamp);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SPPI_TEST_STATUS', '2', '测试通过', 3, null, 'system', null, systimestamp, 'system', systimestamp);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SPPI_TEST_STATUS', '3', '测试不通过', 4, null, 'system', null, systimestamp, 'system', systimestamp);

delete from sys_datadict_list where DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'FINASSET21';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'FINASSET21', 'SPPI测试', 1021, null, 'system', null, systimestamp, 'system', systimestamp);
delete from sys_datadict where DICT_CODE = 'YES_NO_NA';
delete from sys_datadict_list where DICT_CODE = 'YES_NO_NA';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('YES_NO_NA', 'VAL', '是否不适用', null, '02', 'system', null, systimestamp, 'system', systimestamp);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('YES_NO_NA', 'Y', '是', 1, null, 'system', null, systimestamp, 'system', systimestamp);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('YES_NO_NA', 'N', '否', 2, null, 'system', null, systimestamp, 'system', systimestamp);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('YES_NO_NA', 'NA', '不适用', 3, null, 'system', null, systimestamp, 'system', systimestamp);


--计划类资产：增加投资目的


update sys_datadict_list set ITEM_VALUE = '以收取该金融资产合同现金流量为目的' where DICT_CODE = 'INV_AIM' and ITEM_CODE = 'AC';
update sys_datadict_list set ITEM_VALUE = '以收取该合同现金流量及出售金融资产为目的' where DICT_CODE = 'INV_AIM' and ITEM_CODE = 'FVOCI';
update sys_datadict_list set ITEM_VALUE = '以出售该金融资产为目的' where DICT_CODE = 'INV_AIM' and ITEM_CODE = 'FVTPL';

--项目类资产委托
delete from sys_page_dictlist where PAGE_ID = 'EtrProject002' and DICT_ID = 'INV_AIM';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrProject002', '999999', 'INV_AIM', 'AC', null, null, 'admin', null, SYSDATE, 'admin', SYSDATE);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrProject002', '999999', 'INV_AIM', 'FVOCI', null, null, 'admin', null, SYSDATE, 'admin', SYSDATE);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrProject002', '999999', 'INV_AIM', 'FVTPL', null, null, 'admin', null, SYSDATE, 'admin', SYSDATE);


--计划类利率型委托
delete from sys_page_dictlist where PAGE_ID = 'EtrPlanAssetR002' and DICT_ID = 'INV_AIM';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrPlanAssetR002', '999999', 'INV_AIM', 'AC', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrPlanAssetR002', '999999', 'INV_AIM', 'FVOCI', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EtrPlanAssetR002', '999999', 'INV_AIM', 'FVTPL', null, null, 'admin', null, sysdate, 'admin', sysdate);

--项目类资产交易
delete from sys_page_dictlist where PAGE_ID = 'TrdProjectInvestTrade002' and DICT_ID = 'INV_AIM';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdProjectInvestTrade002', '999999', 'INV_AIM', 'AC', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdProjectInvestTrade002', '999999', 'INV_AIM', 'FVOCI', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdProjectInvestTrade002', '999999', 'INV_AIM', 'FVTPL', null, null, 'admin', null, sysdate, 'admin', sysdate);

delete from sys_page_dictlist where PAGE_ID = 'TrdProjectInvestTrade003' and DICT_ID = 'INV_AIM';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdProjectInvestTrade003', '999999', 'INV_AIM', 'AC', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdProjectInvestTrade003', '999999', 'INV_AIM', 'FVOCI', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdProjectInvestTrade003', '999999', 'INV_AIM', 'FVTPL', null, null, 'admin', null, sysdate, 'admin', sysdate);

--计划类利率型交易
delete from sys_page_dictlist where PAGE_ID = 'TrdPlanRateInvestTrade002' and DICT_ID = 'INV_AIM';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdPlanRateInvestTrade002', '999999', 'INV_AIM', 'AC', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdPlanRateInvestTrade002', '999999', 'INV_AIM', 'FVOCI', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdPlanRateInvestTrade002', '999999', 'INV_AIM', 'FVTPL', null, null, 'admin', null, sysdate, 'admin', sysdate);

delete from sys_page_dictlist where PAGE_ID = 'TrdPlanRateInvestTrade003' and DICT_ID = 'INV_AIM';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdPlanRateInvestTrade003', '999999', 'INV_AIM', 'AC', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdPlanRateInvestTrade003', '999999', 'INV_AIM', 'FVOCI', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdPlanRateInvestTrade003', '999999', 'INV_AIM', 'FVTPL', null, null, 'admin', null, sysdate, 'admin', sysdate);


--项目类资产存续期
delete from sys_page_dictlist where PAGE_ID = 'TrdProjectDuration002' and DICT_ID = 'INV_AIM';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdProjectDuration002', '999999', 'INV_AIM', 'AC', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdProjectDuration002', '999999', 'INV_AIM', 'FVOCI', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdProjectDuration002', '999999', 'INV_AIM', 'FVTPL', null, null, 'admin', null, sysdate, 'admin', sysdate);

--债券二级交易
delete from sys_page_dictlist where PAGE_ID = 'TrdBondInvestTrade003' and DICT_ID = 'INV_AIM';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdBondInvestTrade003', '999999', 'INV_AIM', 'AC', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdBondInvestTrade003', '999999', 'INV_AIM', 'FVOCI', null, null, 'admin', null, sysdate, 'admin', sysdate);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdBondInvestTrade003', '999999', 'INV_AIM', 'FVTPL', null, null, 'admin', null, sysdate, 'admin', sysdate);

/**============业务类型：共同业务类型--项目类_计划类利率型行情信息-- add by lby 20201023 start============**/
delete from sys_datadict_list sdl where sdl.dict_code = 'COMMON_BUSINESS_TYPE' and sdl.item_code = 'FINASSET66';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'FINASSET66', '项目类_计划类利率型行情信息', 1222, '0', 'system', null, sysdate, 'system', sysdate);
/**============业务类型：共同业务类型--项目类_计划类利率型行情信息 add by lby 20201023 end============**/

/** ============= 数据字典值-证券品种：“证券品种”中计划类（收益型）修改为计划类（利率型） add by lby 20201027 start ============= **/
update sys_datadict_list sdl
set sdl.item_value = '计划类（利率型）'
where sdl.dict_code = 'SEC_TYPE'
and sdl.item_code = '12';
/** ============= 数据字典值-证券品种：“证券品种”中计划类（收益型）修改为计划类（利率型） add by lby 20201027 end ============= **/


/**
摊余成本（AC）--> 以收取该金融资产合同现金流量为目的
以公允价值计量且其变动计入其他综合收益（FVOCI）--> 以收取该合同现金流量及出售金融资产为目的
以公允价值计量且其变动计入当前损益（FVTPL）--> 以出售该金融资产为目的
**/

/** ===============画面数据字典明细关联:投资目的枚举值修改  画面：一般资产估值配置 add by lby 20201027 start=============== **/

update SYS_PAGE_DICTLIST set DICT_ITEM_VAL_CNNAME = '以收取该金融资产合同现金流量为目的' 
where PAGE_ID='ValuationMethodScheme001' and DICT_ITEM_VAL='AC';

update SYS_PAGE_DICTLIST set DICT_ITEM_VAL_CNNAME = '以收取该合同现金流量及出售金融资产为目的' 
where PAGE_ID='ValuationMethodScheme001' and DICT_ITEM_VAL='FVOCI';

update SYS_PAGE_DICTLIST set DICT_ITEM_VAL_CNNAME = '以出售该金融资产为目的' 
where PAGE_ID='ValuationMethodScheme001' and DICT_ITEM_VAL='FVTPL';

/** ===============画面数据字典明细关联:投资目的枚举值修改  画面：一般资产估值配置 add by lby 20201027 end=============== **/

/** ===============画面数据字典明细关联:投资目的枚举值修改  画面：特殊资产估值配置--新建 add by lby 20201027 start=============== **/

update SYS_PAGE_DICTLIST set DICT_ITEM_VAL_CNNAME = '以收取该金融资产合同现金流量为目的' 
where PAGE_ID='ValuationMethodScheme004' and DICT_ITEM_VAL='AC';

update SYS_PAGE_DICTLIST set DICT_ITEM_VAL_CNNAME = '以收取该合同现金流量及出售金融资产为目的' 
where PAGE_ID='ValuationMethodScheme004' and DICT_ITEM_VAL='FVOCI';

update SYS_PAGE_DICTLIST set DICT_ITEM_VAL_CNNAME = '以出售该金融资产为目的' 
where PAGE_ID='ValuationMethodScheme004' and DICT_ITEM_VAL='FVTPL';

/** ===============画面数据字典明细关联:投资目的枚举值修改  画面：特殊资产估值配置--新建 add by lby 20201027 end=============== **/


/** ===============画面数据字典明细关联:投资目的  数据字典明细关联 add by lby 20201027 start=============== **/
delete FROM SYS_DATADICT_LIST_LINK sdll WHERE sdll.DICT_CODE = 'INV_AIM' and TRIM(sdll.LINKED_ITEM_CODE) = TRIM('12');

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV_AIM', 'AC', 'SEC_TYPE', '12                                                ', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV_AIM', 'FVOCI', 'SEC_TYPE', '12                                                ', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INV_AIM', 'FVTPL', 'SEC_TYPE', '12                                                ', 'system', null, sysdate, 'system', sysdate);
/** ===============画面数据字典明细关联:投资目的枚举值修改  数据字典明细关联 add by lby 20201027 END=============== **/

/** ===============画面数据字典明细关联: 估值方法可选择市值法、成本法  add by lby 20201027 start=============== **/
delete from SYS_DATADICT_LIST_LINK sdll where sdll.LINKED_DICT_CODE = 'SEC_TYPE' and sdll.DICT_CODE = 'VALUATION_METHOD'
and sdll.linked_item_code = '12';

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_METHOD', '01', 'SEC_TYPE', '12                                                ', 'system', null, sysdate, 'system', sysdate);

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_METHOD', '02', 'SEC_TYPE', '12                                                ', 'system', null, sysdate, 'system', sysdate);
/** ===============画面数据字典明细关联: 估值方法可选择市值法、成本法  add by lby 20201027 end=============== **/



/** ===============画面数据字典明细关联:投资目的枚举值修改  画面：一般资产估值配置--新建 add by lby 20201027 start=============== **/

update SYS_PAGE_DICTLIST set DICT_ITEM_VAL_CNNAME = '以收取该金融资产合同现金流量为目的' 
where PAGE_ID='ValuationMethodScheme003' and DICT_ITEM_VAL='AC';

update SYS_PAGE_DICTLIST set DICT_ITEM_VAL_CNNAME = '以收取该合同现金流量及出售金融资产为目的' 
where PAGE_ID='ValuationMethodScheme003' and DICT_ITEM_VAL='FVOCI';

update SYS_PAGE_DICTLIST set DICT_ITEM_VAL_CNNAME = '以出售该金融资产为目的' 
where PAGE_ID='ValuationMethodScheme003' and DICT_ITEM_VAL='FVTPL';

/** ===============画面数据字典明细关联:投资目的枚举值修改  画面：一般资产估值配置--新建 add by lby 20201027 end=============== **/

/** -------================数据字典明细关联：证券品种为利率型时，市价展示枚举值【管理人报价】 画面：一般资产估值配置 add by lby start 20201028================------ **/
delete from SYS_DATADICT_LIST_LINK sdll where sdll.LINKED_DICT_CODE = 'SEC_TYPE' and sdll.DICT_CODE = 'VALUATION_PRICE'
and TRIM(sdll.LINKED_ITEM_CODE) = TRIM('12');

insert into SYS_DATADICT_LIST_LINK (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_PRICE', '07', 'SEC_TYPE', '12                                                ', 'system', null, sysdate, 'system', sysdate);
/** -------================数据字典明细关联：证券品种为利率型时，市价展示枚举值【管理人报价】 画面：一般资产估值配置 add by lby end 20201028================------ **/


-- 估值优化
delete from sys_para where SYSTEM_CODE = 'VAL' and PARA_CODE = 'SEQUENCES_INCREMENTAL';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VAL', 'SEQUENCES_INCREMENTAL', '500', '01', null, '估值批处理SEQUENCES初始增长量(防止接数过程系统异常中断导致SEQUENCES未更新导致异常)', 'system', null, sysdate, 'system', sysdate);

delete from sys_para where SYSTEM_CODE = 'VAL' and PARA_CODE = 'BATCH_CACHE_STORAGE';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VAL', 'BATCH_CACHE_STORAGE', 'ON', '01', null, '估值核算批处理使用缓存存储开关 ON:开 OFF:关', 'system', null, sysdate, 'system', sysdate);


-- 资产减值

delete from sys_datadict where DICT_CODE = 'FIN_STAGE';

insert into sys_datadict 
(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('FIN_STAGE', 'VAL', '阶段', null, '02', 'system', null, 
date'2020-11-18', 'system', date'2020-11-18');

delete from sys_datadict_list where DICT_CODE = 'FIN_STAGE';

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('FIN_STAGE', 'P1', '一阶段', 1, '0', 'system', null, date'2020-11-18', 'system', date'2020-11-18');
insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('FIN_STAGE', 'P2', '二阶段', 2, '0', 'system', null, date'2020-11-18', 'system', date'2020-11-18');
insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('FIN_STAGE', 'P3', '三阶段', 3, '0', 'system', null, date'2020-11-18', 'system', date'2020-11-18');
/** ============= 数据字典-减值测算方式 add by lby 20201013 start ============= **/
delete from sys_datadict sd where sd.dict_code = 'LOSS_CAL_PATTERN';

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LOSS_CAL_PATTERN', 'VAL', '减值测算方式', null, '02', 'system', null, sysdate, 'system', sysdate);
/** ============= 数据字典-减值测算方式 add by lby 20201013 end ============= **/

/** ============= 数据字典值-减值测算方式：外部评估、预期信用损失模型 add by lby 20201013 start ============= **/
delete from sys_datadict_list sdl where sdl.dict_code = 'LOSS_CAL_PATTERN';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LOSS_CAL_PATTERN', '01', '外部评估', 1, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LOSS_CAL_PATTERN', '02', '预期信用损失模型', 2, '0', 'system', null, sysdate, 'system', sysdate);
/** ============= 数据字典值-减值测算方式：外部评估、预期信用损失模型 add by lby 20201013 end ============= **/

/** ============= 数据字典-减值录入方式 add by lby 20201013 start ============= **/
delete from sys_datadict sd where sd.dict_code = 'LOSS_INPUT_PATTERN';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LOSS_INPUT_PATTERN', 'VAL', '减值录入方式', null, '02', 'system', null, sysdate, 'system', sysdate);
/** ============= 数据字典-减值录入方式 add by lby 20201013 end ============= **/

/** ============= 数据字典值-减值录入方式：按比例减值 add by lby 20201013 start ============= **/
delete from sys_datadict_list sdl where sdl.dict_code = 'LOSS_INPUT_PATTERN';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LOSS_INPUT_PATTERN', '01', '按比例减值', 1, '0', 'system', null, sysdate, 'system', sysdate);
/** ============= 数据字典值-减值录入方式：按比例减值 add by lby 20201013 end ============= **/

/** ============= 数据字典值-共通业务类型：资产减值管理 add by lby 20201013 start ============= **/
delete from sys_datadict_list sdl where sdl.dict_code = 'COMMON_BUSINESS_TYPE' and sdl.item_code = 'TEDLOSS';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'TEDLOSS', '资产减值管理', 1220, '0', 'system', null, sysdate, 'system', sysdate);
/** ============= 数据字典值-共通业务类型：资产减值管理 add by lby 20201013 end ============= **/


--新增数据字典【本位币差额处理方式】
delete from SYS_DATADICT where dict_code='BCCYADJ_TYPE';
insert into SYS_DATADICT values ('BCCYADJ_TYPE','VAL','本位币差额处理方式','','02','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/15', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where dict_code='BCCYADJ_TYPE';
insert into SYS_DATADICT_LIST values ('BCCYADJ_TYPE','01','利息收入','1','0','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('BCCYADJ_TYPE','02','减值准备','2','0','system','',to_date('2020/10/21', 'yyyy/mm/dd'), 'system',to_date('2020/10/21', 'yyyy/mm/dd'),NULL);


delete from SYS_DATADICT_LIST t where t.dict_code = 'BUSI_TYPE' and t.item_code = '35';
insert into SYS_DATADICT_LIST values ('BUSI_TYPE','35','自定义现金流交易','35','0','system','',to_date('2019/03/05', 'yyyy/mm/dd'), 'system',to_date('2019/03/05', 'yyyy/mm/dd'),NULL);
delete from SYS_DATADICT_LIST t where t.dict_code = 'CUSTOM_CASHFLOW_TYPE' and t.item_code = '08';
insert into SYS_DATADICT_LIST values ('CUSTOM_CASHFLOW_TYPE','08','减值资产清理后收回','8','0','system','',to_date('2019/03/05', 'yyyy/mm/dd'), 'system',to_date('2019/03/05', 'yyyy/mm/dd'),NULL);

delete from sys_page_dictlist where PAGE_ID = 'PtlCashFlow002' and DICT_ID = 'CUSTOM_CASHFLOW_TYPE' and DICT_ITEM_VAL = '08';
delete from sys_page_dictlist where PAGE_ID = 'PtlCashFlow001' and DICT_ID = 'CUSTOM_CASHFLOW_TYPE' and DICT_ITEM_VAL = '08';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PtlCashFlow001', '999999', 'CUSTOM_CASHFLOW_TYPE', '08', null, null, 'admin', null, sysdate, 'admin', sysdate);
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PtlCashFlow002', '999999', 'CUSTOM_CASHFLOW_TYPE', '08', null, null, 'admin', null, sysdate, 'admin', sysdate);



--数据字典：指令类型
delete FROM SYS_DATADICT_LIST WHERE DICT_CODE='ORDER_TYPE' AND ITEM_CODE IN ('N2798','N2799');
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N2798','资金收款-其他收入到账','176','0','system','',to_date('2019/03/05', 'yyyy/mm/dd'), 'system',to_date('2019/03/05', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','N2799','资金划款-其他支出划款','177','0','system','',to_date('2019/03/05', 'yyyy/mm/dd'), 'system',to_date('2019/03/05', 'yyyy/mm/dd'),NULL);



--新增数据字典
delete from SYS_DATADICT where DICT_CODE in ('OFFSETIMPT_DATETYPE','OFFSETIMPT_SHELL_TYPE','OFFSETIMPT_SX_TYPE');
insert into SYS_DATADICT values ('OFFSETIMPT_DATETYPE','VAL','还本收息冲抵减值准备处理日','','02','system','',to_date('2020/10/30', 'yyyy/mm/dd'), 'system',to_date('2020/10/30', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('OFFSETIMPT_SHELL_TYPE','VAL','卖出冲抵减值准备处理方式','','02','system','',to_date('2020/10/30', 'yyyy/mm/dd'), 'system',to_date('2020/10/30', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('OFFSETIMPT_SX_TYPE','VAL','收息冲抵减值准备处理方式','','02','system','',to_date('2020/10/30', 'yyyy/mm/dd'), 'system',to_date('2020/10/30', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE in ('OFFSETIMPT_DATETYPE','OFFSETIMPT_SHELL_TYPE','OFFSETIMPT_SX_TYPE');
insert into SYS_DATADICT_LIST values ('OFFSETIMPT_DATETYPE','01','理论日','1','0','system','',to_date('2020/10/30', 'yyyy/mm/dd'), 'system',to_date('2020/10/30', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('OFFSETIMPT_DATETYPE','02','资金到账日','2','0','system','',to_date('2020/10/30', 'yyyy/mm/dd'), 'system',to_date('2020/10/30', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('OFFSETIMPT_SHELL_TYPE','01','数量占比','1','0','system','',to_date('2020/10/30', 'yyyy/mm/dd'), 'system',to_date('2020/10/30', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('OFFSETIMPT_SHELL_TYPE','02','金额占比','2','0','system','',to_date('2020/10/30', 'yyyy/mm/dd'), 'system',to_date('2020/10/30', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('OFFSETIMPT_SHELL_TYPE','03','原金额占比','3','0','system','',to_date('2020/10/30', 'yyyy/mm/dd'), 'system',to_date('2020/10/30', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('OFFSETIMPT_SX_TYPE','01','金额占比','1','0','system','',to_date('2020/10/30', 'yyyy/mm/dd'), 'system',to_date('2020/10/30', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('OFFSETIMPT_SX_TYPE','02','原金额占比','2','0','system','',to_date('2020/10/30', 'yyyy/mm/dd'), 'system',to_date('2020/10/30', 'yyyy/mm/dd'),NULL);

delete from SYS_DATADICT_LIST where DICT_CODE = 'BUSI_TYPE' and ITEM_CODE = '39';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('BUSI_TYPE','39','清理核销',39,'0','system',null,sysdate,'system',sysdate);

delete from SYS_DATADICT where DICT_CODE ='LOSS_GEN_TYPE';
insert into SYS_DATADICT values ('LOSS_GEN_TYPE','VAL','减值生成方式','','02','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where DICT_CODE ='LOSS_GEN_TYPE';
insert into SYS_DATADICT_LIST values ('LOSS_GEN_TYPE','01','计提批处理','1','0','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/15', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('LOSS_GEN_TYPE','02','交易批处理','2','0','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/15', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('LOSS_GEN_TYPE','99','其他','99','0','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/15', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('LOSS_GEN_TYPE','03','减值计提批处理','03','0','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/15', 'yyyy/mm/dd'),NULL);

delete from sys_datadict where DICT_CODE = 'LOSS_HADDLE_PATTERN';

insert into sys_datadict 
(DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('LOSS_HADDLE_PATTERN', 'VAL', '减值处理方式', null, '02', 'system', null, 
date'2020-11-18', 'system', date'2020-11-18');

delete from sys_datadict_list where DICT_CODE = 'LOSS_HADDLE_PATTERN';

insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('LOSS_HADDLE_PATTERN', '01', '减值', 1, '0', 'system', null, date'2020-11-18', 'system', date'2020-11-18');
insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('LOSS_HADDLE_PATTERN', '02', '转回', 2, '0', 'system', null, date'2020-11-18', 'system', date'2020-11-18');
insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('LOSS_HADDLE_PATTERN', '03', '减值使用', 3, '0', 'system', null, date'2020-11-18', 'system', date'2020-11-18');
insert into sys_datadict_list 
(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, 
CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values 
('LOSS_HADDLE_PATTERN', '04', '清理核销', 4, '0', 'system', null, date'2020-11-18', 'system', date'2020-11-18');
delete from sys_para where PARA_CODE = 'IS_QUERY_ASSETS_LOSS';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VAL', 'IS_QUERY_ASSETS_LOSS', '01', '  ', null, '是否查询资产减值清理交易（01：是，02：否）', 'system', null, sysdate, 'system', sysdate);

--会计场景代码
---数据字典新增项缺少
delete from SYS_DATADICT_LIST where DICT_CODE='TABLE_ID' and ITEM_CODE in ('1113','1114','1115','1116','1359','1362','1364','1367','1536','1537','1540','1543');
--数据字典：会计事件代码
delete FROM SYS_DATADICT_LIST WHERE DICT_CODE='TABLE_ID' AND ITEM_CODE IN ('2798','2799');
--计划类资产：增加投资目的 add by wujie 20201015
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE='TABLE_ID' AND ITEM_CODE IN ('1510','1511','1512','1513','1514','1515',
'1530','1531','1532','1533','1534','1535','2712','2713','2738','2739');
insert into SYS_DATADICT_LIST values ('TABLE_ID','1510','公允价值变动类收益型资产-买入','513','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1511','公允价值变动类收益型资产-利息计提','514','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1512','公允价值变动类收益型资产-收息','515','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1513','公允价值变动类收益型资产-还本到期','516','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1514','公允价值变动类收益型资产-卖出','517','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1515','公允价值变动类收益型资产-公允价值变动','518','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1530','综合收益类收益型资产-买入','519','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1531','综合收益类收益型资产-计提摊销','520','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1532','综合收益类收益型资产-公允价值变动','521','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1533','综合收益类收益型资产-收息','522','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1534','综合收益类收益型资产-还本到期','523','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1535','综合收益类收益型资产-卖出','524','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2712','资金收款-公允价值变动类计划类资产收息','525','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2713','资金收款-综合收益类计划类资产收息','526','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2738','资金收款-公允价值变动类计划类资产还本','527','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2739','资金收款-综合收益类计划类资产还本','528','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1113','存放同业-减值计提','529','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1114','存放同业-减值清理','530','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1115','拆放同业-减值计提','531','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1116','拆放同业-减值清理','532','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1359','摊余成本类债券-减值计提','533','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1362','摊余成本类债券-减值清理','534','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1364','综合收益类债券-减值计提','535','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1367','综合收益类债券-减值清理','536','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1536','综合收益类收益型资产-减值计提','537','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1537','综合收益类收益型资产-减值清理','538','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1540','摊余成本类收益型计划类资产-减值计提','539','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','1543','摊余成本类收益型计划类资产-减值清理','540','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2798','资金收款-其他收入到账','541','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('TABLE_ID','2799','资金收款-其他收入到账','542','0','system','',to_date('2020/10/13', 'yyyy/mm/dd'), 'system',to_date('2020/10/13', 'yyyy/mm/dd'),NULL);

delete from sys_datadict where DICT_CODE = 'LOSS_P3_SECACCR_SUBJECT_NO';
delete from sys_datadict where DICT_CODE = 'LOSS_P3_SECACCR_YEILD_RECALC';
insert into SYS_DATADICT values ('LOSS_P3_SECACCR_SUBJECT_NO','VAL','减值资产第三阶段利息计提摊销差额计入科目','','02','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));
insert into SYS_DATADICT values ('LOSS_P3_SECACCR_YEILD_RECALC','VAL','减值资产第三阶段是否重新计算实际利率','','02','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/28', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where DICT_CODE='LOSS_P3_SECACCR_SUBJECT_NO';
delete from SYS_DATADICT_LIST where DICT_CODE='LOSS_P3_SECACCR_YEILD_RECALC';
insert into SYS_DATADICT_LIST values ('LOSS_P3_SECACCR_SUBJECT_NO','01','减值准备','1','0','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/15', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('LOSS_P3_SECACCR_SUBJECT_NO','02','原科目','2','0','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/15', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('LOSS_P3_SECACCR_YEILD_RECALC','01','不重算','1','0','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/15', 'yyyy/mm/dd'),NULL);
insert into SYS_DATADICT_LIST values ('LOSS_P3_SECACCR_YEILD_RECALC','02','重算','2','0','system','',to_date('2020/10/15', 'yyyy/mm/dd'), 'system',to_date('2020/10/15', 'yyyy/mm/dd'),NULL);
