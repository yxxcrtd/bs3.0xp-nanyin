delete from data_auth_mapper_config a where a.sql_id in ('com.joyintech.service.basicdata.msttradeopponent.dao.BasicMstTradeOpponent002Mapper.checkcounterName','com.joyintech.service.risk.creditmanagement.ceassetgroup.dao.CeAssetGroupMapper.getAssetGroupAliasIds','com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportMapper.searchPage','com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper.searchPage');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.basicdata.msttradeopponent.dao.BasicMstTradeOpponent002Mapper.checkcounterName', '2', '02', 'COUNTER_ID', null, 'MST', 'MstTradeOpponent002', 'admin', null, sysdate, 'admin', sysdate , null, null, 'MST15');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.risk.creditmanagement.ceassetgroup.dao.CeAssetGroupMapper.getAssetGroupAliasIds', '1', '01', null, 'select base99.*,base99.institutionCode as belongInstitutionCode from ( %s ) base99 where base99.institutionCode in %s', 'MST', 'CeAssetGroup001', 'ZCW', null, sysdate, 'JQQ', sysdate , null, null, null);

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportMapper.searchPage', '1', '01', null, 'select b.* from ( %s ) b  where b.orgCode in %s', 'SVR', 'SvrMonthReport001', 'admin', null, sysdate, 'admin', sysdate, null, null, null);

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper.searchPage', '1', '01', null, 'select * from ( %s ) b where b.DEPT_CODE in %s', 'SVR', 'PbcMonthReport001', 'admin', null, sysdate, 'admin', sysdate, null, null, null);