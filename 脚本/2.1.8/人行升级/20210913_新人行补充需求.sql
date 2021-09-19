DELETE FROM CHB_REG_TIME_POINTS WHERE SEQNO IN ('90','91');
insert into CHB_REG_TIME_POINTS (SEQNO, ORG_CODE, REPORT_CODE, TYPE_DETAIL, SUBMIT_TIMETYPE, SUBMIT_REQUIRE3, SUBMIT_REQUIRE2, SUBMIT_REQUIRE, SUBMIT_POINT_BOND, SUBMIT_POINT_BANK, UPDATE_SUBMIT_POINT, DELETE_SUBMING_POINT, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, CREATE_DEPT)
values ('90', null, '32', null, '03', '', '02', '业务发生后N个工作日', 1.00, 1.00, 12.00, 4.00, 'E', null, null, 'admin', sysdate, 'system', sysdate, null);
insert into CHB_REG_TIME_POINTS (SEQNO, ORG_CODE, REPORT_CODE, TYPE_DETAIL, SUBMIT_TIMETYPE, SUBMIT_REQUIRE3, SUBMIT_REQUIRE2, SUBMIT_REQUIRE, SUBMIT_POINT_BOND, SUBMIT_POINT_BANK, UPDATE_SUBMIT_POINT, DELETE_SUBMING_POINT, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, CREATE_DEPT)
values ('91', null, '33', null, '03', '01', '02', '产品终止后N工作日', 1.00, 1.00, 12.00, 4.00, 'E', null, null, 'admin', sysdate, 'system', sysdate, null);

DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'SVR_REPORT_CODE' AND ITEM_CODE IN ('17','18','19','20','21','22','23','24','32','33');
insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_REPORT_CODE', '17', 'MI统计模板数据', 14, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_REPORT_CODE', '18', 'PPB1资产池基本信息', 18, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_REPORT_CODE', '19', 'PIB1产品基本信息', 19, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_REPORT_CODE', '20', 'PIB2产品起始募集信息', 20, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_REPORT_CODE', '21', 'PCD1产品资金募集信息', 21, '0', 'system', null,sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_REPORT_CODE', '22', 'PPE1资产池终止信息', 22, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_REPORT_CODE', '23', 'PIE1产品终止信息', 23, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_REPORT_CODE', '24', 'PVD1资产负债信息', 24, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_REPORT_CODE', '32', 'PIB产品基本信息', 24, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_REPORT_CODE', '33', 'PIE产品终止信息', 24, '0', 'system', null, sysdate, 'system', sysdate, null);


                   
UPDATE SYS_PARA SET PARAM_VALUE = '69,72,73,18,75,76,77,78,79,80,40,60,60' WHERE PARA_CODE = 'COLOR_CONFIG_mid_list'; 
UPDATE SYS_PARA SET PARAM_VALUE = '81,82,83,84,85,86,87,88,89,90,65,95,95' WHERE PARA_CODE = 'COLOR_CONFIG_top_list'; 
UPDATE SYS_PARA SET PARAM_VALUE = '90,90,90,90,90,90,90,90,90,90' WHERE PARA_CODE = 'COLOR_CONFIG_MID_LIST_PBC'; 
UPDATE SYS_PARA SET PARAM_VALUE = '95,95,95,95,95,95,95,95,95,95' WHERE PARA_CODE = 'COLOR_CONFIG_TOP_LIST_PBC'; 

DELETE FROM data_auth_mapper_config WHERE SQL_ID = 'com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportMapper.searchPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportMapper.searchPage','1','01','','select b.* from ( %s ) b where b.orgCode in %s','SVR','SvrMonthReport001','', 'admin', null, sysdate, 'admin', sysdate);


DELETE FROM data_auth_mapper_config WHERE SQL_ID = 'com.joyintech.tams.svreport.loaninfomanagement.dao.OperationalLoanInfo001Mapper.queryPbcAssetCode';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.svreport.loaninfomanagement.dao.OperationalLoanInfo001Mapper.queryPbcAssetCode', '1', '01', null, 'select b.*,di.belongInstitutionCode,nvl(di.model_type,''02'') model_type from ( %s ) b LEFT JOIN (select di.business_id finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.id  where (di.institution_code in %s or di.institution_code is null)', 'SVR', 'SvrRaiseTotalRegist001', 'admin', null,SYSDATE, 'admin', SYSDATE, null, null, null);
