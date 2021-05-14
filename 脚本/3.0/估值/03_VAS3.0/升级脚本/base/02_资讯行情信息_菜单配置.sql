/*** --------- 项目类/计划类利率型估值行情 add by 李炳言 on 20210222 begin---------***/
--菜单
delete from SYS_MENU sm where sm.menu_id = 'VALINFOSERVICE_001_013_014';
insert into SYS_MENU values ('VALINFOSERVICE_001_013_014','VAL','','VALINFOSERVICE_001_013','MstRateAssetQuotationInfo001','14','02','项目类/计划类利率型估值行情','system','',to_date('2021/02/24', 'yyyy/mm/dd'), 'system',to_date('2021/02/24', 'yyyy/mm/dd'));
--画面
delete from sys_page sp where sp.page_id in ('MstRateAssetQuotationInfo001','MstRateAssetQuotationInfo002');
insert into SYS_PAGE values ('MstRateAssetQuotationInfo001','VAL','/valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001','valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo001','VALINFOSERVICE_001_013_014','','','02','01','项目类/计划类利率型估值行情','system','',to_date('2021/02/21', 'yyyy/mm/dd'), 'system',to_date('2021/02/21', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('MstRateAssetQuotationInfo002','VAL','/valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo002','valuation/basedata/marketdata/mstrateassetquotationinfo/MstRateAssetQuotationInfo002','','','','02','01','项目类/计划类利率型估值行情新建','system','',to_date('2021/02/21', 'yyyy/mm/dd'), 'system',to_date('2021/02/21', 'yyyy/mm/dd'));
--流程
delete from SYS_WFL_BUSINESS_MODULE swbm where swbm.page_id =  'MstRateAssetQuotationInfo002';
insert into SYS_WFL_BUSINESS_MODULE values ('MstRateAssetQuotationInfo002','MstRateAssetQuotationInfo002_btn_submit','oneApproval','system','',to_date('2021/02/22', 'yyyy/mm/dd'), 'system',to_date('2021/02/22', 'yyyy/mm/dd'),'All','02');
insert into SYS_WFL_BUSINESS_MODULE values ('MstRateAssetQuotationInfo002','MstRateAssetQuotationInfo002_btn_delete','oneApproval','system','',to_date('2021/02/22', 'yyyy/mm/dd'), 'system',to_date('2021/02/22', 'yyyy/mm/dd'),'All','02');
/*** --------- 项目类/计划类利率型估值行情 add by 李炳言 on 20210222 end---------***/


/**-- 期权行情：删除--流程 add by 李炳言 on 20210303 start --**/
delete from SYS_PAGE sp where sp.page_id = 'ExternalOptions001';
insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ExternalOptions001', 'VAL', '/valuation/basedata/marketdata/externaloptions/ExternalOptions001', 'valuation/basedata/marketdata/externaloptions/ExternalOptions001', 'VALINFOSERVICE_001_013_013', null, null, '02', '01', '场外期权', 'system', null, sysdate, 'system', sysdate);
/**-- 期权行情：删除--流程 add by 李炳言 on 20210303 end --**/


/**------------  基金类估值行情信息 add by 李炳言 on 20210301 start------------**/
delete from sys_menu sm where sm.menu_id = 'VALINFOSERVICE_001_013_003';

insert into SYS_MENU
values
  ('VALINFOSERVICE_001_013_003',
   'VAL',
   '',
   'VALINFOSERVICE_001_013',
   'ValCoinFundQuotationInfo001',
   '3',
   '02',
   '基金类估值行情信息',
   'system',
   '',
   to_date('2019/12/02', 'yyyy/mm/dd'),
   'system',
   to_date('2019/12/02', 'yyyy/mm/dd'));
/**------------  基金类估值行情信息 add by 李炳言 on 20210301 end------------**/

/** modify by yanya on 20210302 调整菜单【汇率行情信息】为【基准汇率行情】**/
update sys_menu set remark = '基准汇率行情' where menu_id ='VALINFOSERVICE_001_013_010';

update sys_menu set remark = '基准汇率行情' where page_id ='ValExchangeQuotationInfo001';

update sys_menu set remark = '基准汇率行情新建' where page_id ='ValExchangeQuotationInfo002';


/** 画面数据字典明细关联 ：利率互换估值信息-估值来源 add by 李炳言 on 20210302 start**/
delete from sys_page_dictlist spd where spd.page_id = 'ValRateSwapValuationInfo002' and spd.dict_id = 'VALUATION_SOURCE';
/** 画面数据字典明细关联 ：利率互换估值信息-估值来源 add by 李炳言 on 20210302 end**/

--add by tanxuan on 20210226 修正股票行情信息页面元素
delete from sys_page_element where page_id = 'ValSharePrice001';
delete from sys_page_element where page_id = 'ValSharePrice002';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValSharePrice001', 'ValSharePrice001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValSharePrice001', 'ValSharePrice001_btn_import', '01', '01', null, '导入', 'system', null, sysdate, 'system', sysdate);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValSharePrice001', 'ValSharePrice001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValSharePrice001', 'ValSharePrice001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValSharePrice001', 'ValSharePrice001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValSharePrice002', 'ValSharePrice002_btn_save', '01', '02', null, '保存', 'system', null, sysdate, 'system', sysdate);
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ValSharePrice002', 'ValSharePrice002_btn_submit', '01', '02', null, '提交', 'system', null, sysdate, 'system', sysdate);


/**-- 画面数据字典明细关联 ： 计划类净值/货币资产估值行情 新建画面：收益类型：add by 李炳言 on 20210303 start --**/
delete from sys_page_dictlist spd where spd.page_id = 'ValNetAssetQuotationInfo002';

insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValNetAssetQuotationInfo002','PROFIT_TYPE','01','999999','净值型','','system','',to_date('2021/03/03', 'yyyy/mm/dd'), 'system',to_date('2021/03/03', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST (PAGE_ID,DICT_ID,DICT_ITEM_VAL,SUB_PAGE_ID,DICT_ITEM_VAL_CNNAME,DICT_ITEM_VAL_DEFAULT,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ValNetAssetQuotationInfo002','PROFIT_TYPE','03','999999','货币型','','system','',to_date('2021/03/03', 'yyyy/mm/dd'), 'system',to_date('2021/03/03', 'yyyy/mm/dd'));
/**-- 画面数据字典明细关联 ： 计划类净值/货币资产估值行情 新建画面：收益类型：add by 李炳言 on 20210303 end --**/


/** modify by yanya on 20210329 项目类/计划类利率型估值行情保存按钮增加流程配置**/
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'MstRateAssetQuotationInfo002' and ELEMENT_ID ='MstRateAssetQuotationInfo002_btn_save';
insert into SYS_WFL_BUSINESS_MODULE values ('MstRateAssetQuotationInfo002','MstRateAssetQuotationInfo002_btn_save','oneApproval','system','',to_date('2021/02/22', 'yyyy/mm/dd'), 'system',to_date('2021/02/22', 'yyyy/mm/dd'), 'ALL','02');



/*数据字典业务类型_添加每日债券行情信息和每日基金行情信息*/
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE='COMMON_BUSINESS_TYPE' AND ITEM_CODE IN('MST27','MST28');
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('COMMON_BUSINESS_TYPE','MST27','每日债券行情信息','927','0','system','',to_date('2021/03/23', 'yyyy/mm/dd'), 'system',to_date('2021/03/23', 'yyyy/mm/dd'), 'ALL');
insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,INSTITUTION_CODE) values ('COMMON_BUSINESS_TYPE','MST28','每日基金行情信息','928','0','system','',to_date('2021/03/23', 'yyyy/mm/dd'), 'system',to_date('2021/03/23', 'yyyy/mm/dd'), 'ALL');


/** modify by yanya on 20210327 外汇估值信息过滤估值来源数据字典**/
delete from SYS_PAGE_DICTLIST where page_id ='ValFxValuztionInfo002' and DICT_ID = 'VALUATION_SOURCE';













