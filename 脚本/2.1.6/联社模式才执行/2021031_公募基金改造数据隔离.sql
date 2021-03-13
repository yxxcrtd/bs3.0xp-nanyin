delete from data_auth_mapper_config where SQL_ID in('com.joyintech.service.period.trdfunddurationnew.dao.TrdFundDurationNewMapper.findAssetIntInfo','com.joyintech.service.period.trdfunddurationnew.dao.TrdFundDurationNewMapper.findPage');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.period.trdfunddurationnew.dao.TrdFundDurationNewMapper.findAssetIntInfo', '2', '02', 'portfolioId', '', 'MST', 'TrdFundDurationNew002', 'admin', '', sysdate, 'admin', sysdate, '', '', 'PTL01');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.period.trdfunddurationnew.dao.TrdFundDurationNewMapper.findPage', '1', '02', 'institutionCode', '', 'MST', 'TrdFundDurationNew001', 'admin', '', sysdate, 'admin', sysdate, '', '', '');

