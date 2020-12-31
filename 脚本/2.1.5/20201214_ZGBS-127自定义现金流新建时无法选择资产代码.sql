
------- ZGBS-127
DELETE Data_Auth_Mapper_Config WHERE sql_id = 'com.joyintech.service.portfolio.ptlcashflow.dao.PtlCashFlowMapper.getFinprodIdToNew';

insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.portfolio.ptlcashflow.dao.PtlCashFlowMapper.getFinprodIdToNew', '1', '01', null, 'SELECT * FROM ( %s
) base99 WHERE FINPROD_TYPE2 IN (''F01'',''F06'',''F10'',''F32'')
OR ( FINPROD_TYPE2 NOT IN (''F01'',''F06'',''F10'',''F32'')
AND EXISTS ( SELECT 1 FROM data_auth_notify_inst inst
WHERE base99.object = inst.business_id AND inst.model_type = ''02''
AND inst.institution_code IN %s)
)', 'PTL', 'PtlCashFlow002', null, null, null, null, null, null, null, null);


