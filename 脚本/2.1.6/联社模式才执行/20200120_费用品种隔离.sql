delete from data_auth_mapper_config where SQL_ID in('com.joyintech.service.basicdata.mstfeevarieties.dao.MstFeeVarietiesMapper.selectForPage') ;
insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.basicdata.mstfeevarieties.dao.MstFeeVarietiesMapper.selectForPage', '2', '02', 'feeId', null, 'MST', 'MstFeeVarieties002', 'admin', null, SYSDATE, 'admin', SYSDATE, null, null, 'MST16');


delete from data_auth_mapper_config where SQL_ID in('com.joyintech.service.product.feepay.dao.FinProductFeePayMapper.queryFeeInfoList','com.joyintech.service.product.feepay.dao.FinProductFeePayMapper.queryFeeInfoListEx'
,'com.joyintech.service.basicdata.msttradeopponent.dto.MstTradeOpponentDto.getFeeId','com.joyintech.service.product.common.dao.FinProductFeeInfoMapper.queryFeeInfoList');


insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.basicdata.mstfeevarieties.dao.MstFeeVarietiesMapper.selectForPage', '2', '02', 'feeId', null, 'MST', 'MstFeeVarieties002', 'admin', null, SYSDATE, 'admin', SYSDATE, null, null, 'MST16');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.basicdata.msttradeopponent.dto.MstTradeOpponentDto.getFeeId', '2', '02', 'id', null, null, null, 'admin', null, SYSDATE, 'admin', SYSDATE, null, null, 'MST16');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.product.common.dao.FinProductFeeInfoMapper.queryFeeInfoList', '2', '02', 'id', null, null, null, 'admin', null, SYSDATE, 'admin', SYSDATE, null, null, 'MST16');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.product.feepay.dao.FinProductFeePayMapper.queryFeeInfoList', '2', '02', 'id', null, null, null, 'admin', null, SYSDATE, 'admin', SYSDATE, null, null, 'MST16');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.product.feepay.dao.FinProductFeePayMapper.queryFeeInfoListEx', '2', '02', 'id', null, null, null, 'admin', null, SYSDATE, 'admin', SYSDATE, null, null, 'MST16');
