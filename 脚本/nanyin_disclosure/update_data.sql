
-- add by zhengxiaolian 2022-07-19 报表数据格式类型数据字典值更新 start
delete from sys_datadict_list where DICT_CODE='DIS_DATA_TYPE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_TYPE', '01', '产品名称下拉框', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_TYPE', '02', '子产品代码下拉框', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_TYPE', '03', '费率名称下拉框', 3, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_TYPE', '04', '要素名称下拉框', 4, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_TYPE', '05', '季度选择框', 5, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_TYPE', '06', '半年度选择框', 6, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_TYPE', '07', '年度选择框', 7, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_TYPE', '08', '开始日期', 8, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('DIS_DATA_TYPE', '09', '结束日期', 9, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-19 报表数据格式类型数据字典值更新 end 

-- add by zhengxiaolian 2022-07-19 报表页面管理产品类型数据字典 start 
delete from sys_datadict_list where DICT_CODE='PROD_TYPE' and ITEM_CODE='04';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('PROD_TYPE', '04', '现金管理类', 10, '0', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_dictlist where page_id='ReportPageManage002' and DICT_ID='PROD_TYPE';

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'PROD_TYPE', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'PROD_TYPE', '04', null, null, 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-19 报表页面管理产品类型数据字典 end 

-- add by zhengxiaolian 2022-07-19 开放频率数据字典 start 
delete from sys_datadict where DICT_CODE='OPEN_FREQ' ;

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('OPEN_FREQ', 'DIS', '开放频率', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='OPEN_FREQ' ;

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('OPEN_FREQ', '01', '日开', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('OPEN_FREQ', '02', '频率在半年（不含）以内', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('OPEN_FREQ', '03', '频率在半年以上', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-19 开放频率数据字典 end 

-- add by zhengxiaolian 2022-07-19 作业规则1增加开放频率，是否是客户周期型产品，是否是指数型产品 start
delete from sys_datadict_list where DICT_CODE='RULE_TYPE1' and ITEM_CODE in('OPEN_FREQ','IS_INDEX','IS_PERIOD');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RULE_TYPE1', 'OPEN_FREQ', '开放频率', 15, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RULE_TYPE1', 'IS_INDEX', '是否是指数型产品', 16, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RULE_TYPE1', 'IS_PERIOD', '是否是客户周期型产品', 17, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-19 作业规则1增加开放频率，是否是客户周期型产品，是否是指数型产品 end

-- add by zhengxiaolian 2022-07-19 报表页面管理规则联动 start
delete from sys_datadict_list_link where DICT_CODE='RULE_TYPE3';

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'CASH_MANA_CATE', 'admin', NULL, sysdate, 'admin', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'FINA_TYPE', 'admin', NULL, sysdate, 'admin', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'ISSUE_MODE', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'ISSUE_STATUS', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'IS_INDEPE_ACC', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'NATURE_INVEST', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'OPEN_MODE', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'PROD_ID', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'PROD_MANAGER', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'PROD_SERIES', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'PROD_TERM_TYPE', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'PROD_TYPE', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'PROFIT_TYPE', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'RAISE_TYPE', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'RISK_LEVEL', 'admin', NULL, sysdate, 'admin', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '03', 'RULE_TYPE1', 'MDATE', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '03', 'RULE_TYPE1', 'VDATE', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '03', 'RULE_TYPE1', 'WINDUP_DATE', 'system', NULL, sysdate, 'system', sysdate);

delete from sys_datadict_list_link where DICT_CODE='RULE_TYPE2';

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '<', 'RULE_TYPE1', 'MDATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '<', 'RULE_TYPE1', 'VDATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '<', 'RULE_TYPE1', 'WINDUP_DATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '<=', 'RULE_TYPE1', 'MDATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '<=', 'RULE_TYPE1', 'VDATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '<=', 'RULE_TYPE1', 'WINDUP_DATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'CASH_MANA_CATE', 'admin', NULL, sysdate, 'admin', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'FINA_TYPE', 'admin', NULL, sysdate, 'admin', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'ISSUE_MODE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'ISSUE_STATUS', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'IS_INDEPE_ACC', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'MDATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'NATURE_INVEST', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'OPEN_MODE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'PROD_ID', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'PROD_MANAGER', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'PROD_SERIES', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'PROD_TERM_TYPE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'PROD_TYPE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'PROFIT_TYPE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'RAISE_TYPE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'RISK_LEVEL', 'admin', NULL, sysdate, 'admin', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'VDATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'WINDUP_DATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '>', 'RULE_TYPE1', 'MDATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '>', 'RULE_TYPE1', 'VDATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '>', 'RULE_TYPE1', 'WINDUP_DATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '>=', 'RULE_TYPE1', 'MDATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '>=', 'RULE_TYPE1', 'VDATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '>=', 'RULE_TYPE1', 'WINDUP_DATE', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '≠', 'RULE_TYPE1', 'PROD_TERM_TYPE', 'system', NULL, sysdate, 'system', sysdate); 
-- add by zhengxiaolian 2022-07-19 报表页面管理规则联动 end

-- add by zhengxiaolian 2022-07-20 报表页面管理 规则设置联动 start 
delete from sys_datadict_list_link where DICT_CODE='RULE_TYPE2' and LINKED_ITEM_CODE in('OPEN_FREQ','IS_INDEX','IS_PERIOD');

delete from sys_datadict_list_link where DICT_CODE='RULE_TYPE3' and LINKED_ITEM_CODE in('OPEN_FREQ','IS_INDEX','IS_PERIOD');

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'OPEN_FREQ', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'OPEN_FREQ', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'IS_INDEX', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'IS_INDEX', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'IS_PERIOD', 'system', NULL, sysdate, 'system', sysdate); 

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'IS_PERIOD', 'system', NULL, sysdate, 'system', sysdate);
-- add by zhengxiaolian 2022-07-20 报表页面管理 规则设置联动 end 

-- add by zhengxiaolian 2022-07-20 报表页面管理 发行状态枚举值调整 start 
delete from sys_page_dictlist where page_id='ReportPageManage002' and DICT_ID='ISSUE_STATUS';

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'ISSUE_STATUS', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'ISSUE_STATUS', '02', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'ISSUE_STATUS', '03', null, null, 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-20 报表页面管理 发行状态枚举值调整 end 

-- add by zhengxiaolian 2022-07-20 	作业规则1 数据字典调整 start 
delete from sys_datadict_list where DICT_CODE='RULE_TYPE1' and ITEM_CODE  in('ISSUE_MODE','ISSUING_WAY');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RULE_TYPE1', 'ISSUING_WAY', '发行方式', 10, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-20 	作业规则1 数据字典调整 end 

-- add by zhengxiaolian 2022-07-20 	增加数据字典 发行方式 start 
delete from sys_datadict where DICT_CODE='ISSUING_WAY' ;

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ISSUING_WAY', 'DIS', '发行方式', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='ISSUING_WAY';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ISSUING_WAY', '01', '开放式', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ISSUING_WAY', '02', '封闭式', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-20 	增加数据字典 发行方式 end 

-- add by zhengxiaolian 2022-07-20 报表页面管理 规则设置联动 start 
delete from sys_datadict_list_link where DICT_CODE='RULE_TYPE2' and LINKED_ITEM_CODE in('ISSUE_MODE','ISSUING_WAY');

delete from sys_datadict_list_link where DICT_CODE='RULE_TYPE3' and LINKED_ITEM_CODE in('ISSUE_MODE','ISSUING_WAY');

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'ISSUING_WAY', 'system', NULL, sysdate, 'system', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)  
VALUES ('RULE_TYPE2', '=', 'RULE_TYPE1', 'ISSUING_WAY', 'system', NULL, sysdate, 'system', sysdate); 
-- add by zhengxiaolian 2022-07-20 报表页面管理 规则设置联动 end

-- add by zhengxiaolian 2022-07-20 	数据字典 募集方式修改 start 
delete from sys_datadict_list where DICT_CODE='RAISE_TYPE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RAISE_TYPE', '01', '公募', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RAISE_TYPE', '02', '私募', 2, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-20 	数据字典 募集方式修改 end 

-- add by zhengxiaolian 2022-07-22 	数据字典 基准日新增枚举值 start 
delete from sys_datadict_list where DICT_CODE='BASIS_DATE_TYPE' and ITEM_CODE in ('14','15');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('BASIS_DATE_TYPE', '14', '当期收益分配基准日', 14, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('BASIS_DATE_TYPE', '15', '自动赎回日', 15, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-22 	数据字典 基准日新增枚举值 end

-- add by zhengxiaolian 2022-07-22 	增加数据字典 信批产品类别 start
delete from sys_datadict where DICT_CODE='REP_PROD_TYPE' ;

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP_PROD_TYPE', 'DIS', '信批产品类别', null, '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE='REP_PROD_TYPE';

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REP_PROD_TYPE', '01', '母产品', 1, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REP_PROD_TYPE', '02', '普通产品', 2, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('REP_PROD_TYPE', '03', '子产品', 3, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-22 	增加数据字典 信批产品类别 end

-- add by zhengxiaolian 2022-07-22  报表规则设置 日历类型枚举值调整 start 
delete from sys_page_dictlist where page_id='ReportRule002' and DICT_ID='BATCH_QUARTZ_SKIP_HOLIDAY_MARKET_CAL_TYPE';

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportRule002', '999999', 'BATCH_QUARTZ_SKIP_HOLIDAY_MARKET_CAL_TYPE', '01', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportRule002', '999999', 'BATCH_QUARTZ_SKIP_HOLIDAY_MARKET_CAL_TYPE', '04', null, null, 'system', null, sysdate, 'system', sysdate, null);

-- add by zhengxiaolian 2022-07-22  报表规则设置 日历类型枚举值调整 end

-- add by sunjie 20220725 产品要素调整 start
-- 产品要素调整 菜单页面 配置
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_002';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_004_002', 'DIS', null, 'REPORT_006_004', 'ProElementAdj001', 2, '02', '产品要素调整', 'system', null, sysdate, 'system', sysdate);
delete from SYS_PAGE where PAGE_ID like '%ProElementAdj00%';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ProElementAdj002', 'DIS', '/disclosure/information/config/proelementadj/ProElementAdj002', 'disclosure/information/config/proelementadj/ProElementAdj002', null, null, null, '02', '01', '产品要素调整新建', 'system', null,sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ProElementAdj001', 'DIS', '/disclosure/information/config/proelementadj/ProElementAdj001', 'disclosure/information/config/proelementadj/ProElementAdj001', 'REPORT_006_004_002', null, null, '02', '01', '产品要素调整', 'system', null, sysdate, 'system',sysdate);

-- 页面控件流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID like '%ProElementAdj00%';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('ProElementAdj002', 'ProElementAdj001_btn_delete', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('ProElementAdj002', 'ProElementAdj002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('ProElementAdj002', 'ProElementAdj002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

-- 业务类型数据字典配置
delete from SYS_DATADICT_LIST where DICT_CODE ='COMMON_BUSINESS_TYPE' and ITEM_CODE = 'DISE';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('COMMON_BUSINESS_TYPE', 'DISE', '产品要素调整', 353, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by sunjie 20220725 产品要素调整 end

-- add by sunjie 20220725 巨额赎回信息 start
-- 巨额赎回信息 菜单页面 配置
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_003';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_004_003', 'DIS', null, 'REPORT_006_004', 'HugeInfoPro001', 2, '03', '巨额赎回信息维护', 'system', null,sysdate, 'system', sysdate);
delete from SYS_PAGE where PAGE_ID like '%HugeInfoPro00%';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('HugeInfoPro001', 'DIS', '/disclosure/information/config/hugeinfopro/HugeInfoPro001', 'disclosure/information/config/hugeinfopro/HugeInfoPro001', 'REPORT_006_004_003', null, null, '02', '01', '巨额赎回信息维护', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('HugeInfoPro002', 'DIS', '/disclosure/information/config/hugeinfopro/HugeInfoPro002', 'disclosure/information/config/hugeinfopro/HugeInfoPro002', null, null, null, '02', '01', '巨额赎回信息新建', 'system', null, sysdate, 'system', sysdate);

-- 页面控件流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID like '%HugeInfoPro00%';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('HugeInfoPro002', 'HugeInfoPro001_btn_delete', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('HugeInfoPro002', 'HugeInfoPro002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('HugeInfoPro002', 'HugeInfoPro002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

-- 业务类型数据字典配置
delete from SYS_DATADICT_LIST where DICT_CODE ='COMMON_BUSINESS_TYPE' and ITEM_CODE = 'DISP';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('COMMON_BUSINESS_TYPE', 'DISP', '巨额赎回信息维护', 298, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by sunjie 20220725 巨额赎回信息 end

-- add by sunjie 20220725 产品不成立信息 start
-- 产品不成立信息 菜单页面 配置
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_014';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('REPORT_006_004_014', 'DIS', null, 'REPORT_006_004', 'ProFailureInfo001', 12, '02', '产品不成立信息维护', 'system', null, sysdate, 'system', sysdate);
delete from SYS_PAGE where PAGE_ID like '%ProFailureInfo00%';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ProFailureInfo001', 'DIS', '/disclosure/information/config/profailureinfo/ProFailureInfo001', 'disclosure/information/config/profailureinfo/ProFailureInfo001', 'REPORT_006_004_014', null, null, '02', '01', '产品不成立信息维护', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ProFailureInfo002', 'DIS', '/disclosure/information/config/profailureinfo/ProFailureInfo002', 'disclosure/information/config/profailureinfo/ProFailureInfo002', null, null, null, '02', '01', '产品不成立信息新建', 'system', null, sysdate, 'system', sysdate);

-- 页面控件流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID like '%ProFailureInfo00%';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('ProFailureInfo002', 'ProFailureInfo001_btn_delete', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('ProFailureInfo002', 'ProFailureInfo002_btn_save', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG) VALUES ('ProFailureInfo002', 'ProFailureInfo002_btn_submit', 'oneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

-- 业务类型数据字典配置
delete from SYS_DATADICT_LIST where DICT_CODE ='COMMON_BUSINESS_TYPE' and ITEM_CODE = 'DISF';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('COMMON_BUSINESS_TYPE', 'DISF', '产品不成立信息维护', 355, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by sunjie 20220725 产品不成立信息 end


-- add by shengning 20220725 产品费用交易信息维护 start
-- 产品费用交易信息维护 菜单页面 配置
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_011';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004_011', 'SVR', null, 'REPORT_006_004', 'SvrProdFeeDealInterf001', 11, '02', '产品费用交易信息维护', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'SvrProdFeeDealInterf001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('SvrProdFeeDealInterf001', 'SVR', '/disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001', 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf001', 'REPORT_006_004_011', null, null, '02', '01', '产品费用交易信息维护', 'system', null,SYSDATE, 'system', SYSDATE);

delete from SYS_PAGE where PAGE_ID = 'SvrProdFeeDealInterf002';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('SvrProdFeeDealInterf002', 'SVR', '/disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002', 'disclosure/information/config/prodfeedealInterf/SvrProdFeeDealInterf002',null, null, null, '02', '01', '产品费用交易信息维护编辑', 'system', null,SYSDATE, 'system', SYSDATE);

-- 页面控件流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'SvrProdFeeDealInterf002';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SvrProdFeeDealInterf002', 'SvrProdFeeDealInterf002_btn_submit', 'oneApproval', 'system', '0001',SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SvrProdFeeDealInterf002', 'SvrProdFeeDealInterf002_btn_save', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('SvrProdFeeDealInterf002', 'SvrProdFeeDealInterf001_btn_delete', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);

-- 业务类型数据字典配置
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'SVR11';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'SVR11', '产品费用交易信息维护', 1400, '0', 'system', null, sysdate, 'system',sysdate, null);

--产品费用交易费用类型数据字典配置
delete from SYS_DATADICT where DICT_CODE = 'FEE_TYPE_REP';
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FEE_TYPE_REP', 'REP', '产品费用交易费用类型', null, '02', 'system', null, SYSDATE, 'system', SYSDATE);

delete from SYS_DATADICT_LIST where DICT_CODE = 'FEE_TYPE_REP';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FEE_TYPE_REP', '01', '托管费', 1, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FEE_TYPE_REP', '02', '管理费（固定）', 2, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FEE_TYPE_REP', '03', '管理费（超额）', 3, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FEE_TYPE_REP', '04', '销售费', 4, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FEE_TYPE_REP', '05', '申购费', 5, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FEE_TYPE_REP', '06', '赎回费', 6, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('FEE_TYPE_REP', '07', '客户收益', 7, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);

-- add by shengning 20220725 产品费用交易信息维护 end

-- add by shengning 20220725 投资账户信息维护 start
-- 投资账户信息维护 菜单页面 配置
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_012';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004_012', 'SVR', null, 'REPORT_006_004', 'RepInvestAccount001', 11, '02', '投资账户信息维护', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'RepInvestAccount001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RepInvestAccount001', 'SVR', '/disclosure/information/config/repInvestAccount/RepInvestAccount001', 'disclosure/information/config/repInvestAccount/RepInvestAccount001', 'REPORT_006_004_012', null, null, '02', '01', '投资账户信息维护', 'system', null,SYSDATE, 'system', SYSDATE);

delete from SYS_PAGE where PAGE_ID = 'RepInvestAccount002';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RepInvestAccount002', 'SVR', '/disclosure/information/config/repInvestAccount/RepInvestAccount002', 'disclosure/information/config/repInvestAccount/RepInvestAccount002',null, null, null, '02', '01', '投资账户信息维护编辑', 'system', null,SYSDATE, 'system', SYSDATE);

-- 页面控件流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'RepInvestAccount002';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepInvestAccount002', 'RepInvestAccount002_btn_submit', 'oneApproval', 'system', '0001',SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepInvestAccount002', 'RepInvestAccount002_btn_save', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepInvestAccount002', 'RepInvestAccount001_btn_delete', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);

-- 业务类型数据字典配置
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'SVR12';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'SVR12', '投资账户信息维护', 1400, '0', 'system', null, sysdate, 'system',sysdate, null);

-- add by shengning 20220725 投资账户信息维护 end

-- add by shengning 20220725 产品基础指标信息维护 start
-- 产品基础指标信息维护 菜单页面 配置
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_017';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004_017', 'SVR', null, 'REPORT_006_004', 'RepProdIndex001', 11, '02', '产品基础指标信息维护', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'RepProdIndex001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RepProdIndex001', 'SVR', '/disclosure/information/config/repProdIndex/RepProdIndex001', 'disclosure/information/config/repProdIndex/RepProdIndex001', 'REPORT_006_004_017', null, null, '02', '01', '产品基础指标信息维护', 'system', null,SYSDATE, 'system', SYSDATE);

delete from SYS_PAGE where PAGE_ID = 'RepProdIndex002';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RepProdIndex002', 'SVR', '/disclosure/information/config/repProdIndex/RepProdIndex002', 'disclosure/information/config/repProdIndex/RepProdIndex002',null, null, null, '02', '01', '产品基础指标信息维护编辑', 'system', null,SYSDATE, 'system', SYSDATE);

-- 页面控件流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'RepProdIndex002';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepProdIndex002', 'RepProdIndex002_btn_submit', 'oneApproval', 'system', '0001',SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepProdIndex002', 'RepProdIndex002_btn_save', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepProdIndex002', 'RepProdIndex001_btn_delete', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);

-- 业务类型数据字典配置
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'SVR13';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'SVR13', '产品基础指标信息维护', 1400, '0', 'system', null, sysdate, 'system',sysdate, null);

--账户类型数据字典配置
delete from SYS_DATADICT where DICT_CODE = 'ACCOUNT_TYPE';
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ACCOUNT_TYPE', 'REP', '托管账户', null, '02', 'system', null, SYSDATE, 'system', SYSDATE);
delete from SYS_DATADICT_LIST where DICT_CODE = 'ACCOUNT_TYPE';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('ACCOUNT_TYPE', 'A01', '托管账户', 1, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);

-- add by shengning 20220725 产品基础指标信息维护 end

-- add by zhengxiaolian 2022-07-26 报表页面管理规则联动 start
delete from sys_datadict_list_link where DICT_CODE='RULE_TYPE2' and LINKED_ITEM_CODE  ='REP_PROD_TYPE';

delete from sys_datadict_list_link where DICT_CODE='RULE_TYPE3' and LINKED_ITEM_CODE ='REP_PROD_TYPE';

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RULE_TYPE2', '02', 'RULE_TYPE1', 'REP_PROD_TYPE', 'admin', NULL, sysdate, 'admin', sysdate);

INSERT INTO sys_datadict_list_link(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RULE_TYPE3', '02', 'RULE_TYPE1', 'REP_PROD_TYPE', 'admin', NULL, sysdate, 'admin', sysdate);
-- add by zhengxiaolian 2022-07-26 报表页面管理规则联动 end

-- add by zhengxiaolian 2022-07-27 	报表模板路径修改 start
update sys_para a set a.param_value='/joyintech' where a.para_code='TEMPLATE_FILE_PATH';
-- add by zhengxiaolian 2022-07-27 	报表模板路径修改 end


-- add by shengning 20220727 产品超额业绩报酬信息维护 start
-- 产品超额业绩报酬信息维护 菜单页面 配置
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_022';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004_022', 'SVR', null, 'REPORT_006_004', 'RepFloatFee001', 11, '02', '产品超额业绩报酬信息维护', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'RepFloatFee001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RepFloatFee001', 'SVR', '/disclosure/information/config/repFloatFee/RepFloatFee001', 'disclosure/information/config/repFloatFee/RepFloatFee001', 'REPORT_006_004_022', null, null, '02', '01', '产品超额业绩报酬信息维护', 'system', null,SYSDATE, 'system', SYSDATE);

delete from SYS_PAGE where PAGE_ID = 'RepFloatFee002';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RepFloatFee002', 'SVR', '/disclosure/information/config/repFloatFee/RepFloatFee002', 'disclosure/information/config/repFloatFee/RepFloatFee002',null, null, null, '02', '01', '产品超额业绩报酬信息维护编辑', 'system', null,SYSDATE, 'system', SYSDATE);

-- 页面控件流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'RepFloatFee002';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepFloatFee002', 'RepFloatFee002_btn_submit', 'oneApproval', 'system', '0001',SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepFloatFee002', 'RepFloatFee002_btn_save', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepFloatFee002', 'RepFloatFee001_btn_delete', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);

-- 业务类型数据字典配置

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'SVR14';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'SVR14', '产品超额业绩报酬信息维护', 1400, '0', 'system', null, sysdate, 'system',sysdate, null);

--业务代码数据字典配置
delete from SYS_DATADICT where DICT_CODE = 'BUSINESS_CODE';
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('BUSINESS_CODE', 'REP', '业务代码', null, '02', 'system', null, SYSDATE, 'system', SYSDATE);
delete from SYS_DATADICT_LIST where DICT_CODE = 'BUSINESS_CODE';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('BUSINESS_CODE', '03', '赎回', 1, '0', 'system', null, SYSDATE, 'system', SYSDATE, null);

-- add by shengning 20220727 产品超额业绩报酬信息维护 end


-- add by zhengxiaolian 2022-07-28 作业规则1增加信批产品类别 start
delete from sys_datadict_list where DICT_CODE='RULE_TYPE1' and ITEM_CODE in('REP_PROD_TYPE');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RULE_TYPE1', 'REP_PROD_TYPE', '信批产品类别', 16, '0', 'system', null, sysdate, 'system', sysdate, null);
-- add by zhengxiaolian 2022-07-28 作业规则1增加信批产品类别 end

/**信披合规留痕管理 add cuiwei 2022-08-01 start**/
delete from sys_menu where menu_id in('REPORT_006_005','REPORT_006_005_001');
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_005', 'DIS', null, 'REPORT_006', null, 5, '02', '信批合规管理', 'system', null, sysdate, 'system', sysdate);
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REPORT_006_005_001', 'DIS', null, 'REPORT_006_005', 'InfoCompliance001', 1, '02', '信批合规管理', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where page_id in ('InfoCompliance001','InfoCompliance002','InfoCompliance003');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InfoCompliance001', 'DIS', '/disclosure/infoCompliance/infoCompliance001', 'disclosure/infoCompliance/infoCompliance001', 'REPORT_006_005_001', null, null, '02', '01', '信批合规管理', 'system', null, sysdate, 'system', sysdate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InfoCompliance002', 'DIS', '/disclosure/infoCompliance/infoCompliance002', 'disclosure/infoCompliance/infoCompliance002', '', null, null, '02', '01', '信批合规新建', 'system', null, sysdate, 'system', sysdate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('InfoCompliance003', 'DIS', '/disclosure/infoCompliance/infoCompliance003', 'disclosure/infoCompliance/infoCompliance003', '', null, null, '02', '01', '开发状态修改', 'system', null, sysdate, 'system', sysdate);

delete from sys_page where page_id in ('UploadFile001','UploadFile002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('UploadFile001', 'DIS', '/disclosure/infoCompliance/uploadFile001', 'disclosure/infoCompliance/uploadFile001', '', null, null, '02', '01', '报表/模板附件', 'system', null, sysdate, 'system', sysdate);
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('UploadFile002', 'DIS', '/disclosure/infoCompliance/uploadFile002', 'disclosure/infoCompliance/uploadFile002', '', null, null, '02', '01', '其他附件', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict where DICT_CODE IN('INFO_TYPE','INFO_STATUS','INFO_DEV_STATUS');
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INFO_TYPE', 'DIS', '模板类型', '', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INFO_STATUS', 'DIS', '当前状态', '', '02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INFO_DEV_STATUS', 'DIS', '开发状态', '', '02', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE IN ('INFO_TYPE','INFO_STATUS','INFO_DEV_STATUS');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INFO_TYPE', '01', '模板', 1, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INFO_TYPE', '02', '临时公告', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INFO_STATUS', '01', '待复核', 1, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INFO_STATUS', '02', '审核通过', 1, '0', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INFO_DEV_STATUS', '01', '待开发', 1, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INFO_DEV_STATUS', '02', '需修改', 1, '0', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('INFO_DEV_STATUS', '03', '终止', 1, '0', 'system', null, sysdate, 'system', sysdate);

delete from sys_datadict_list where DICT_CODE IN('COMMON_BUSINESS_TYPE') AND ITEM_CODE='COMPLIANCE';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('COMMON_BUSINESS_TYPE', 'COMPLIANCE', '合规留痕管理', 1002, '0', 'system', null, sysdate, 'system', sysdate);

delete from sys_wfl_business_module where page_id ='InfoCompliance002';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('InfoCompliance002', 'InfoCompliance002_btn_save', 'fiveprocess', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('InfoCompliance002', 'InfoCompliance002_btn_submit', 'fiveprocess', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');
/**信披合规留痕管理 add cuiwei 2022-08-01 end**/


-- add by shengning 20220801 关联交易情况维护 start
-- 关联交易情况维护 菜单页面 配置
delete from SYS_MENU where MENU_ID = 'REPORT_006_004_023';
INSERT INTO SYS_MENU (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('REPORT_006_004_023', 'SVR', null, 'REPORT_006_004', 'RepRelDeal001', 11, '02', '关联交易情况维护', 'system', null, sysdate, 'system', sysdate);

delete from SYS_PAGE where PAGE_ID = 'RepRelDeal001';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RepRelDeal001', 'SVR', '/disclosure/information/config/repRelDeal/RepRelDeal001', 'disclosure/information/config/repRelDeal/RepRelDeal001', 'REPORT_006_004_023', null, null, '02', '01', '关联交易情况维护', 'system', null,SYSDATE, 'system', SYSDATE);

delete from SYS_PAGE where PAGE_ID = 'RepRelDeal002';
INSERT INTO SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES ('RepRelDeal002', 'SVR', '/disclosure/information/config/repRelDeal/RepRelDeal002', 'disclosure/information/config/repRelDeal/RepRelDeal002',null, null, null, '02', '01', '关联交易情况维护编辑', 'system', null,SYSDATE, 'system', SYSDATE);

-- 页面控件流程配置
delete from SYS_WFL_BUSINESS_MODULE where PAGE_ID = 'RepRelDeal002';
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepRelDeal002', 'RepRelDeal002_btn_submit', 'oneApproval', 'system', '0001',SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepRelDeal002', 'RepRelDeal002_btn_save', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_WFL_BUSINESS_MODULE (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RepRelDeal002', 'RepRelDeal001_btn_delete', 'oneApproval', 'system', '0001', SYSDATE, 'system', SYSDATE);

-- 业务类型数据字典配置
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'COMMON_BUSINESS_TYPE' and ITEM_CODE = 'SVR15';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
VALUES ('COMMON_BUSINESS_TYPE', 'SVR15', '关联交易情况维护', 1400, '0', 'system', null, sysdate, 'system',sysdate, null);

-- add by shengning 20220801 关联交易情况维护 end

-- add by zhengxiaolian 20220801 报表编辑页面脚本 start
delete from sys_page where page_id='RepEdit001';

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RepEdit001', 'DIS', '/disclosure/information/reveal/detailshow/RepEdit001', 'disclosure/information/reveal/detailshow/RepEdit001', null, null, null, '02', '02', '报表编辑', 'system', null, '07-7月 -22 06.39.05.966000 下午', 'system', '07-7月 -22 06.39.14.306000 下午');

-- add by zhengxiaolian 20220801 报表编辑页面脚本 start

--add by zhengbs 20220802 poi脚本 start
/*phantomjs-2.1.1 的安装路径 poi导出报表之生成echarts图片工具存放路径*/
update sys_para set param_value = '/home/phantomjs-2.1.1-linux-x86_64/bin/' where para_code = 'POI_ECHARTS_PIC_EXPORT_PATH';
/*poi导出报表Echarts临时文件存放路径*/
update sys_para set param_value = '/home/bs3.0/yy/app/poi/echartstmp/' where para_code = 'POI_ECHARTS_TMPFILE_PATH';
/*apache服务器的安装*/
DELETE FROM sys_para WHERE PARA_CODE = 'POI_APACHE_PATH';
INSERT INTO sys_para ("SYSTEM_CODE", "PARA_CODE", "PARAM_VALUE", "DATA_TYPE", "DATA_SCOPE", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME", "INSTITUTION_CODE", "UPDATE_FLG")
VALUES ('DIS', 'POI_APACHE_PATH', '/home/httpd24/htdocs/images', NULL, NULL, 'POI_APACHE服务器存放路径', 'system', NULL, sysdate, 'system', sysdate, NULL, '01');

DELETE FROM sys_para WHERE PARA_CODE = 'POI_APACHE_ADDRESS';
INSERT INTO sys_para ("SYSTEM_CODE", "PARA_CODE", "PARAM_VALUE", "DATA_TYPE", "DATA_SCOPE", "REMARK", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME", "INSTITUTION_CODE", "UPDATE_FLG")
VALUES ('DIS', 'POI_APACHE_ADDRESS', '192.168.60.236', NULL, NULL, 'POI_APACHE服务器ip地址', 'system', NULL, sysdate, 'system', sysdate, NULL, '01');
--add by zhengbs 20220802 poi脚本 end

--add by zhengxiaolian 20220803 页面规则设置枚举值修改
delete from sys_page_dictlist where PAGE_ID='ReportWorkManage002' and DICT_ID='RULE_TYPE1';

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportWorkManage002', '999999', 'RULE_TYPE1', 'RAISE_TYPE', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportWorkManage002', '999999', 'RULE_TYPE1', 'PROD_TYPE', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportWorkManage002', '999999', 'RULE_TYPE1', 'OPEN_FREQ', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportWorkManage002', '999999', 'RULE_TYPE1', 'IS_PERIOD', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportWorkManage002', '999999', 'RULE_TYPE1', 'IS_INDEX', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportWorkManage002', '999999', 'RULE_TYPE1', 'ISSUE_STATUS', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportWorkManage002', '999999', 'RULE_TYPE1', 'REP_PROD_TYPE', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportWorkManage002', '999999', 'RULE_TYPE1', 'ISSUING_WAY', null, null, 'system', null, sysdate, 'system', sysdate, null);

delete from sys_page_dictlist where PAGE_ID='ReportPageManage002' and DICT_ID='RULE_TYPE1';

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'RULE_TYPE1', 'RAISE_TYPE', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'RULE_TYPE1', 'PROD_TYPE', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'RULE_TYPE1', 'OPEN_FREQ', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'RULE_TYPE1', 'IS_PERIOD', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'RULE_TYPE1', 'IS_INDEX', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'RULE_TYPE1', 'ISSUE_STATUS', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'RULE_TYPE1', 'REP_PROD_TYPE', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ReportPageManage002', '999999', 'RULE_TYPE1', 'ISSUING_WAY', null, null, 'system', null, sysdate, 'system', sysdate, null);
--add by zhengxiaolian 20220803 页面规则设置枚举值修改

-- add by zhengxiaolian 2022-08-03 数据字典中文调整 start
delete from sys_datadict_list where DICT_CODE='RULE_TYPE1' and ITEM_CODE in('IS_INDEX','IS_PERIOD');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RULE_TYPE1', 'IS_INDEX', '是否指数型产品', 16, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('RULE_TYPE1', 'IS_PERIOD', '是否客户周期型产品', 17, '0', 'system', null, sysdate, 'system', sysdate, null);

delete from sys_datadict where DICT_CODE='REP_PROD_TYPE' ;

insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REP_PROD_TYPE', 'DIS', '信披产品类别', null, '02', 'system', null, sysdate, 'system', sysdate);
-- add by zhengxiaolian 2022-08-03 数据字典中文调整，是否是指数型产品 end
