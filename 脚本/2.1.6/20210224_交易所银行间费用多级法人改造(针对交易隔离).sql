
delete from  data_auth_mapper_config  where sql_id='com.joyintech.tams.invest.common.component.business.basicdata.dao.BaseTradeFeeCommMapper.selectFeeConfig';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.common.component.business.basicdata.dao.BaseTradeFeeCommMapper.selectFeeConfig', '2', '02', 'batch_Seq', null, 'MST', 'MstCommissionSet001', 'admin', null, sysdate, 'admin', sysdate, null, null, 'MST20,MST21');
