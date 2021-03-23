delete from data_auth_mapper_config where sql_id in ('com.joyintech.service.trade.transfer.assetstransfer.dao.AssetsTransferMapper.getPortfolioIdTree','com.joyintech.service.trade.transfer.assetstransfer.dao.AssetsTransferMapper.getPortfolioId','com.joyintech.service.trade.transfer.assetstransfer.dao.AssetsTransferMapper.findUnTransPage','com.joyintech.service.trade.transfer.assetstransfer.dao.AssetsTransferMapper.findTransPage');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.trade.transfer.assetstransfer.dao.AssetsTransferMapper.getPortfolioIdTree', '2', '02', 'id', null, 'TRD', 'AssetsTransfer001', 'admin', null, '21-JUL-20 09.08.51.000000 PM', 'wy', '21-JUL-20 09.08.51.000000 PM', null, null, 'PTL01');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.trade.transfer.assetstransfer.dao.AssetsTransferMapper.getPortfolioId', '2', '02', 'id', null, 'TRD', 'AssetsTransfer001', 'admin', null, '21-JUL-20 09.08.51.000000 PM', 'wy', '21-JUL-20 09.08.51.000000 PM', null, null, 'PTL01');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.trade.transfer.assetstransfer.dao.AssetsTransferMapper.findUnTransPage', '2', '02', 'portfolioId', null, 'TRD', 'AssetsTransfer001', 'admin', null, '21-JUL-20 09.08.51.000000 PM', 'wy', '21-JUL-20 09.08.51.000000 PM', null, null, 'PTL01');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.trade.transfer.assetstransfer.dao.AssetsTransferMapper.findTransPage', '2', '02', 'portfolioId', null, 'TRD', 'AssetsTransfer001', 'admin', null, '21-JUL-20 09.08.51.000000 PM', 'wy', '21-JUL-20 09.08.51.000000 PM', null, null, 'PTL01');
