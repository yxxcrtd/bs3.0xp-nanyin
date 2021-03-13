

-----ZGBSS-848

DELETE data_auth_mapper_config WHERE sql_id = 'com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dao.ValAccountHandle001Mapper.checkExecute';

INSERT INTO DATA_AUTH_MAPPER_CONFIG
  (SQL_ID,
   FILTER_TYPE,
   IS_NEST,JOIN_COLUMN_NAME,
   FILTER_SQL,
   SYSTEM_CODE,PAGE_ID,
   CREATE_USER,
   CREATE_DEPT,
   CREATE_TIME,
   UPDATE_USER,
   UPDATE_TIME,
   ADD_COLUMN,
   ADD_COLUMN_NAME,
   DATA_TYPE)
VALUES
  ('com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dao.ValAccountHandle001Mapper.checkExecute',
   '2',
   '01',
   NULL,
   ' %s  AND EXISTS ( SELECT 1 FROM Data_Auth_Notify_Inst inst WHERE inst.business_id = GRPLINK.product_id AND inst.model_type = ''02'' AND inst.data_type = ''V08'' AND INST.INSTITUTION_CODE IN %s ) ',
   'SYS',
   'ValBatchStart001',
   'admin',
   NULL,
   SYSDATE,
   'admin',
   SYSDATE,
   NULL,
   NULL,
   NULL);