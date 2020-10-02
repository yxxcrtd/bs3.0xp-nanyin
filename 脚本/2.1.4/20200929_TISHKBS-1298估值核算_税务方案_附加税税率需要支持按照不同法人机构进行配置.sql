

---- wj  20200929 
delete DATA_AUTH_MAPPER_CONFIG where sql_id = 'com.joyintech.service.valuation.taxparametermanage.taxscheme.dao.TaxScheme001Mapper.selectAdtTax';

INSERT INTO DATA_AUTH_MAPPER_CONFIG
   (SQL_ID,
    FILTER_TYPE,
    IS_NEST,
    JOIN_COLUMN_NAME,
    FILTER_SQL,
    SYSTEM_CODE,
    PAGE_ID,
    DATA_TYPE,
    CREATE_USER,
    CREATE_DEPT,
    CREATE_TIME,
    UPDATE_USER,
    UPDATE_TIME)
 VALUES
   ('com.joyintech.service.valuation.taxparametermanage.taxscheme.dao.TaxScheme001Mapper.selectAdtTax',
    '2',
    '02',
    'taxId',
    '',
    'VAL',
    'TaxScheme001',
    'TAXADT',
    'admin',
    NULL,
    SYSDATE,
    'admin',
    SYSDATE);