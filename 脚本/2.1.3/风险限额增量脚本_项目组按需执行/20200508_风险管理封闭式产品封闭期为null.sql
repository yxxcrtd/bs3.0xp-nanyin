--封闭式产品封闭期为null
delete from RSK_ISUP_INDICATOR_PARA_DTL   where PARA_CODE = 'CPFBQ' and PARA_GRANULARITY in ('04','05');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CPFBQ', '04', 'SQL', 'SELECT
    case when REP_OPEN_CIRCLE = ''06'' then 0.5
     when REP_OPEN_CIRCLE = ''07'' then 1
     when REP_OPEN_CIRCLE = ''99'' 
     and round(REP_OPEN_CIRCLE_DAYS/365, 2) >= 0.5 then round(REP_OPEN_CIRCLE_DAYS/365, 2)
     else 0
    end as RVALUE
FROM
    REP_PROD_ISSUE
WHERE
        REP_FINPROD_ID = (select max(FINPROD_ID) from FIN_PRODUCT_ADD where PORTFOLIO_ID in ${portfolioCode})
    and REP_OPEN_CIRCLE in (''06'', ''07'', ''99'')
union all 
SELECT
   case when TERM_DAYS >= 0 then round(TERM_DAYS/365, 2) end as RVALUE
FROM
    FIN_PRODUCT
WHERE
        FINPROD_ID = (select max(FINPROD_ID) from FIN_PRODUCT_ADD where PORTFOLIO_ID in ${portfolioCode})
    and OPERATION_TYPE = ''01''', 'portfolioCode', 'N', 'jjpei', '2222222', TIMESTAMP '2020-05-06 11:26:27', 'jjpei', TIMESTAMP '2020-05-06 11:26:27');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CPFBQ', '05', 'SQL', 'SELECT
    case when REP_OPEN_CIRCLE = ''06'' then 0.5
     when REP_OPEN_CIRCLE = ''07'' then 1
     when REP_OPEN_CIRCLE = ''99'' 
     and round(REP_OPEN_CIRCLE_DAYS/365, 2) >= 0.5 then round(REP_OPEN_CIRCLE_DAYS/365, 2)
     else 0
    end as RVALUE
FROM
    REP_PROD_ISSUE
WHERE
        REP_FINPROD_ID = (select max(FINPROD_ID) from FIN_PRODUCT_ADD where PORTFOLIO_ID in ${portfolioCode})
    and REP_OPEN_CIRCLE in (''06'', ''07'', ''99'')
union all 
SELECT
   case when TERM_DAYS >= 0 then round(TERM_DAYS/365, 2) end as RVALUE
FROM
    FIN_PRODUCT
WHERE
        FINPROD_ID = (select max(FINPROD_ID) from FIN_PRODUCT_ADD where PORTFOLIO_ID in ${portfolioCode})
    and OPERATION_TYPE = ''01''', 'portfolioCode', 'N', 'jjpei', '2222222', TIMESTAMP '2020-05-06 11:26:27', 'jjpei', TIMESTAMP '2020-05-06 11:26:27');
