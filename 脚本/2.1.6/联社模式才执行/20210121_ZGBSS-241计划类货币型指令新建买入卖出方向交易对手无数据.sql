delete from data_auth_mapper_config where SQL_ID = 'com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper.getCounterId';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper.getCounterId', '2', '02', 'id', '', 'MST', 'EtrInvestBondInfo002', 'admin', '', sysdate, 'admin', sysdate, '', '', 'PTL01,MST15');

