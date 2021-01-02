
------- BS-1418  仅联社模式执行
DELETE  data_auth_mapper_config WHERE sql_id = 'com.joyintech.service.entrust.etrinvestbondinfo.dao.EtrInvestBondInfoMapper.selectForPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.entrust.etrinvestbondinfo.dao.EtrInvestBondInfoMapper.selectForPage', '2', '02', 'portfolioId', null, 'ETR', 'EtrInvestBondInfo001', 'admin', null, sysdate, 'wy', SYSDATE, null, null, 'PTL01');
