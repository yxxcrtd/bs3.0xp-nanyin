delete from data_auth_mapper_config where SQL_ID='com.joyintech.service.portfolio.ptlfundallocation.dao.PtlFundAllocationMapper.getPtlSecTrdAcctNo';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.portfolio.ptlfundallocation.dao.PtlFundAllocationMapper.getPtlSecTrdAcctNo', '2', '02', 'seqNo', '', 'STL', 'PtlFundAllocation003', 'admin', '', sysdate, 'admin', sysdate, '', '', 'MST09');
