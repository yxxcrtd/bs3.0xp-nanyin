--【风险管理-产品封闭期】取值不正确
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='CPFBQ' and PARA_GRANULARITY IN('04','05');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CPFBQ', '04', 'SQL', 'SELECT  (r1.OPEN_CYCLE - r1.OPEN_DAYS)/365
    from FIN_OPEN_RULE r1
    inner join (
    select fpa.FINPROD_ID,max(CASH_ID) CASH_ID from FIN_OPEN_PLAN fop 
    inner join FIN_PRODUCT_ADD fpa 
    on fop.FINPROD_ID = fpa.FINPROD_ID 
    where fpa.PORTFOLIO_ID in ${portfolioCode}
    group by fpa.FINPROD_ID,fop.CASH_ID) r2
    ON r1.CASH_ID = r2.CASH_ID    
union all 
SELECT
   case when TERM_DAYS >= 0 then round(TERM_DAYS/365, 2) end as RVALUE
FROM
    FIN_PRODUCT
WHERE
        FINPROD_ID = (select max(FINPROD_ID) from FIN_PRODUCT_ADD where PORTFOLIO_ID in ${portfolioCode})
    and OPERATION_TYPE = ''01''', 'portfolioCode', 'N', 'admin', '30122018', TIMESTAMP '2020-05-09 18:22:46', 'admin', TIMESTAMP '2020-05-09 18:22:46');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CPFBQ', '05', 'SQL', 'SELECT  (r1.OPEN_CYCLE - r1.OPEN_DAYS)/365
    from FIN_OPEN_RULE r1
    inner join (
    select fpa.FINPROD_ID,max(CASH_ID) CASH_ID from FIN_OPEN_PLAN fop 
    inner join FIN_PRODUCT_ADD fpa 
    on fop.FINPROD_ID = fpa.FINPROD_ID 
    where fpa.PORTFOLIO_ID in ${portfolioCode}
    group by fpa.FINPROD_ID,fop.CASH_ID) r2
    ON r1.CASH_ID = r2.CASH_ID    
union all 
SELECT
   case when TERM_DAYS >= 0 then round(TERM_DAYS/365, 2) end as RVALUE
FROM
    FIN_PRODUCT
WHERE
        FINPROD_ID = (select max(FINPROD_ID) from FIN_PRODUCT_ADD where PORTFOLIO_ID in ${portfolioCode})
    and OPERATION_TYPE = ''01''', 'portfolioCode', 'N', 'admin', '30122018', TIMESTAMP '2020-05-09 18:22:46', 'admin', TIMESTAMP '2020-05-09 18:22:46');
