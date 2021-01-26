
---- 债券预录入
delete from data_auth_mapper_config where SQL_ID = 'com.joyintech.service.trade.pretrdbondtrade.dao.PreTrdBondInvestTradeMapper.selectDeal';

insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.trade.pretrdbondtrade.dao.PreTrdBondInvestTradeMapper.selectDeal', '4', '02', 'portfolioId', null, 'TRD', 'PreTrdBondInvestTrade001', 'admin', null, sysdate, 'admin', sysdate, null, null, 'PTL01');




