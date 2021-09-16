update data_auth_mapper_config set filter_sql='select * from ( %s ) b LEFT JOIN DATA_AUTH_NOTIFY_INST di  on di.business_id = b.insideProdId and di.model_type = ''02'' and DI.DATA_TYPE = ''PTL01'' where di.institution_code in %s'
where sql_id='com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dao.ProPeoplebankcodeManagement001DtoMapper.queryForPage';

delete from data_auth_mapper_config where sql_id='com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dao.ProPeoplebankcodeManagement001DtoMapper.queryReportList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dao.ProPeoplebankcodeManagement001DtoMapper.queryReportList', '1', '01', null, 'select * from ( %s ) b LEFT JOIN DATA_AUTH_NOTIFY_INST di on di.business_id= b.insideProdId  and di.model_type = ''02'' and DI.DATA_TYPE = ''PTL01'' where di.institution_code in %s', 'SVR', 'ProPeoplebankcodeManagement001', 'admin', null, sysdate, 'admin', sysdate, null, null, null);
