declare
	dbsqlStr clob:='WITH BONDSTYPE AS(
SELECT
RESULT1.PORTFOLIO_ID,
RESULT1.FINPROD_ID,
RESULT1.CDATE,
RESULT1.INV_AIM,
NVL(RESULT1.SHARE_AMT,0)+NVL(RESULT2.no_amt,0) as finally_amt,
NVL(RESULT1.DSC_COST_AMT,0)+NVL(RESULT2.no_cost,0) as finally_cost ,
NVL(RESULT1.TDY_FLOAT_INGPL,0)*(NVL(RESULT1.SHARE_AMT,0)+NVL(RESULT2.no_amt,0))/DECODE(RESULT1.SHARE_AMT,0,null,RESULT1.SHARE_AMT) as finally_GY,
NVL(RESULT1.TDY_INTINCEXP,0)+NVL(RESULT2.no_lixi,0) as finally_LX,
''system'' as UPDATE_USER,
sysdate as UPDATE_TIME
FROM(
SELECT F.PORTFOLIO_ID,
       F.FINPROD_ID,
       F.CDATE,
       F.INV_AIM,
       F.SHARE_AMT,
       F.TDY_FLOAT_INGPL,
       F.DSC_COST_AMT,
       F.TDY_INTINCEXP
  FROM PTL_SEC_VALUTION F
  left join fin_product fp on fp.finprod_id=F.FINPROD_ID
 WHERE F.CDATE >= TRUNC(SYSDATE-1)
   AND F.SHARE_AMT IS NOT NULL
   AND fp.finprod_type2=''F01''
   ) RESULT1
   LEFT JOIN (
   select
    tpda.portfolio_id,
    tpd.finprod_id,
    sum(decode(tpd.ps,''T01'',-1*tpd.share_amt,tpd.share_amt)) as no_amt,
    sum(decode(tpd.ps,''T01'',-1*tpd.cprice_amt,tpd.cprice_amt)) as no_cost,
    sum(decode(tpd.ps,''T01'',-1*tpd.int_amt,tpd.int_amt)) as no_lixi,
    tpd.inv_aim
    from TRD_PRODUCT_DEAL tpd,TRD_PRODUCT_DEAL_ADD tpda
    where tpd.trade_id=tpda.trade_id
    and tpd.finprod_type2=''F01''
    and tpd.trade_date<=TRUNC(sysdate-1) and TRUNC(sysdate-1)<tpd.settle_date
    group by tpda.portfolio_id,tpd.finprod_id,tpd.inv_aim
   ) RESULT2 ON  RESULT1.PORTFOLIO_ID=RESULT2.PORTFOLIO_ID
             AND RESULT1.FINPROD_ID=RESULT2.FINPROD_ID
             AND RESULT1.INV_AIM=RESULT2.INV_AIM
),
YDRTYPE AS(
select
psv.cdate,
psv.portfolio_id,
psv.finprod_id,
psv.inv_aim,
psv.share_amt,
psv.tdy_float_ingpl,
''system'' as UPDATE_USER,
sysdate as UPDATE_TIME
from ptl_sec_valution psv
left join fin_trade_product ftp on psv.finprod_id=ftp.finprod_id
where nvl(ftp.finprod_type2,''notFound'') in(''F12'',''F13'',''F14'')
and psv.cdate >= trunc(sysdate-1)
),
OTHERTYPE AS(
select
psv.cdate,
psv.portfolio_id,
psv.finprod_id,
psv.inv_aim,
psv.share_amt,
psv.tdy_float_ingpl,
psv.dsc_cost_amt,
psv.tdy_intincexp,
''system'' as UPDATE_USER,
sysdate as UPDATE_TIME
from ptl_sec_valution psv
left join fin_trade_product ftp on psv.finprod_id=ftp.finprod_id
left join fin_product fp on psv.finprod_id=fp.finprod_id
where nvl(nvl(ftp.finprod_type2,fp.finprod_type2),''notFound'')  not in(''F01'',''F12'',''F13'',''F14'',''notFound'')
and psv.cdate >= trunc(sysdate-1)
)
 
SELECT * FROM (
SELECT F.PORTFOLIO_ID AS OBJECTID,
       F.FINPROD_ID AS ELEMENTID,
       F.CDATE,
       F.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''SHARE_AMT'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(NVL(F.finally_amt,0)) AS PARAMVALUE,
       ''SHARE_AMT'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       F.UPDATE_USER,
       F.UPDATE_TIME
FROM BONDSTYPE F
UNION ALL
SELECT F.PORTFOLIO_ID AS OBJECTID,
       F.FINPROD_ID AS ELEMENTID,
       F.CDATE,
       F.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''SHARE_AMT'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(F.share_amt) AS PARAMVALUE,
       ''SHARE_AMT'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       F.UPDATE_USER,
       F.UPDATE_TIME
FROM YDRTYPE F
UNION ALL
SELECT F.PORTFOLIO_ID AS OBJECTID,
       F.FINPROD_ID AS ELEMENTID,
       F.CDATE,
       F.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''SHARE_AMT'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(nvl(F.share_amt,0)) AS PARAMVALUE,
       ''SHARE_AMT'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       F.UPDATE_USER,
       F.UPDATE_TIME
FROM OTHERTYPE F
UNION ALL 
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''MARKET_VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(NVL(G.finally_cost,0)+NVL(G.finally_GY,0)+NVL(G.finally_LX,0)) AS PARAMVALUE,
       ''MARKET_VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM BONDSTYPE G
UNION ALL
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''MARKET_VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(NVL(G.tdy_float_ingpl,0)) AS PARAMVALUE,
       ''MARKET_VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM YDRTYPE G
UNION ALL
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''MARKET_VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(NVL(G.tdy_float_ingpl,0)+NVL(G.dsc_cost_amt,0)+NVL(G.tdy_intincexp,0)) AS PARAMVALUE,
       ''MARKET_VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM OTHERTYPE G
UNION ALL 
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''CURRENT_PRICE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       to_char((NVL(G.finally_cost,0)+NVL(G.finally_GY,0)+NVL(G.finally_LX,0))/decode(G.finally_amt,0,null,G.finally_amt)) AS PARAMVALUE,
       ''CURRENT_PRICE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM BONDSTYPE G
UNION ALL
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''CURRENT_PRICE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       to_char(NVL(G.tdy_float_ingpl,null)) AS PARAMVALUE,
       ''CURRENT_PRICE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM YDRTYPE G
UNION ALL
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''CURRENT_PRICE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       to_char((NVL(G.tdy_float_ingpl,0)+NVL(G.dsc_cost_amt,0)+NVL(G.tdy_intincexp,0))/decode(G.share_amt,0,null,G.share_amt)) AS PARAMVALUE,
       ''CURRENT_PRICE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM OTHERTYPE G
UNION ALL 
SELECT B.PORTFOLIO_ID AS OBJECT_ID,
       NULL AS ELEMENT_ID,
       B.CDATE,
       NULL AS INV_AIM,
       NULL AS SEC_ACCT_ID,
       ''TDY_ASSET_NETVALUE'' AS PARAM_CODE,
       ''01'' AS PARAM_TYPE,
       TO_CHAR(PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS(B.TDY_ASSET_NETVALUE, B.CCY, B.CDATE, ''JIXU'')) AS PARAM_VALUE,
       ''TDY_ASSET_NETVALUE'' AS DICT_CODE,
       ''P_IMPORT_PROD_DATA'' AS CREATE_USER,
       ''system'' AS CREATE_DEPT,
       B.UPDATE_USER,
       B.UPDATE_TIME
  FROM PTL_PORTFOLIO_VALUATION B
 WHERE B.TDY_ASSET_NETVALUE IS NOT NULL
   AND B.CDATE IS NOT NULL
   AND B.CDATE >= TRUNC(SYSDATE)-1
UNION ALL
SELECT C.PORTFOLIO_ID AS OBJECTID,
       NULL AS ELEMENTID,
       C.CDATE,
       NULL AS INVAIM,
       NULL AS SECACCIID,
       ''TDY_ASSET_VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS(C.TDY_ASSET_VALUE, C.CCY, C.CDATE, ''JIXU'')) AS PARAMVALUE,
       ''TDY_ASSET_VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       C.UPDATE_USER,
       C.UPDATE_TIME
  FROM PTL_PORTFOLIO_VALUATION C
 WHERE C.TDY_ASSET_VALUE IS NOT NULL
   AND C.CDATE IS NOT NULL
   AND C.CDATE >= TRUNC(SYSDATE)-1
UNION ALL
SELECT nvl(fp.FINPROD_MARKET_ID,D.FINPROD_ID) AS OBJECTID,
       NULL AS ELEMENTID,
       D.CDATE,
       NULL AS INVAIM,
       NULL AS SECACCIID,
       ''TDY_SHARE_AMT'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(D.TDY_SHARE_AMT) AS PARAMVALUE,
       ''TDY_SHARE_AMT'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       D.UPDATE_USER,
       D.UPDATE_TIME
  FROM BOK_POSITION_PRD D
  left join fin_product fp on fp.finprod_id=D.FINPROD_ID
 WHERE D.TDY_SHARE_AMT IS NOT NULL
   AND D.CDATE IS NOT NULL
   AND D.CDATE >= TRUNC(SYSDATE)-1
UNION ALL 
SELECT nvl(fp.FINPROD_MARKET_ID,E.FINPROD_ID) AS OBJECTID,
       NULL AS ELEMENTID,
       E.CDATE,
       NULL AS INVAIM,
       NULL AS SECACCIID,
       ''TDY_UNIT_VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(E.TDY_UNIT_VALUE) AS PARAMVALUE,
       ''TDY_UNIT_VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       E.UPDATE_USER,
       E.UPDATE_TIME
  FROM BOK_POSITION_PRD E
  left join fin_product fp on fp.finprod_id=E.FINPROD_ID
 WHERE E.TDY_UNIT_VALUE IS NOT NULL
   AND E.CDATE IS NOT NULL
   AND E.CDATE >= TRUNC(SYSDATE)-1
UNION ALL
SELECT H.PORTFOLIO_ID AS OBJECTID,
       H.CCY AS ELEMENTID,
       H.CDATE,
       NULL AS INVAIM,
       NULL AS SECACCIID,
       ''AMOUNT'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(H.AMOUNT) AS PARAMVALUE,
       ''AMOUNT'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       H.UPDATE_USER,
       H.UPDATE_TIME
  FROM PTL_CASH_POSITION H
 WHERE H.AMOUNT IS NOT NULL
   AND H.CDATE IS NOT NULL
   AND H.CDATE >= TRUNC(SYSDATE)-1
UNION ALL 
SELECT I.PORTFOLIO_ID AS OBJECTID,
       I.CCY AS ELEMENTID,
       I.CDATE,
       NULL AS INVAIM,
       NULL AS SECACCIID,
       ''VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS(I.AMOUNT, I.CCY, I.CDATE, ''JIXU'')) AS PARAMVALUE,
       ''VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       I.UPDATE_USER,
       I.UPDATE_TIME
  FROM PTL_CASH_POSITION I
 WHERE I.AMOUNT IS NOT NULL
   AND I.CDATE IS NOT NULL
   AND I.CDATE >= TRUNC(SYSDATE)-1
UNION ALL
SELECT J.PORTFOLIO_ID AS OBJECTID,
       J.FINPROD_ID AS ELEMENTID,
       J.CDATE,
       J.INV_AIM AS INVAIM,
       J.SEC_ACCT_ID AS SECACCIID,
       J.HODING_TYPE AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(J.AMOUNT) AS PARAMVALUE,
       ''POSITION_TYPE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       J.UPDATE_USER,
       J.UPDATE_TIME
  FROM PTL_SEC_POSITION J
 WHERE J.BRANCH = 0
   AND J.AMOUNT IS NOT NULL
   AND J.CDATE IS NOT NULL
   AND J.CDATE >= TRUNC(SYSDATE)-1
union all
select fp.finprod_id   AS OBJECTID,
       fp.finprod_id AS ELEMENTID,
       trunc(sysdate - 1) as CDATE,
       null AS INVAIM,
       NULL AS SECACCIID,
       ''GRADE_ORDER'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       sdl2.item_value AS PARAMVALUE,
       ''GRADE_ORDER'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       pj.UPDATE_USER,
       pj.UPDATE_TIME
from FIN_PRODUCT fp
left join (select t1.*
           from (select t.*,
                            row_number() over(partition by t.o_code order by t.grade_date desc, t.grade_result desc) rownumber
                 from MST_BOND_CREDIT_RATING t
                 where t.grade_date <= (sysdate - 1)) t1
           where t1.rownumber = 1) pj
on pj.o_code = substr(fp.finprod_id, instr(fp.finprod_id, ''_'') + 1)
left join sys_datadict_list sdl
on sdl.dict_code = ''MAIN_GRADE_RESULT''
and sdl.item_code = pj.grade_result
left join sys_datadict_list sdl2
on sdl2.dict_code = ''ST_BOND_GRADE''
and sdl.item_value = sdl2.item_code
where fp.finprod_type2 = ''F01''
and pj.o_type = ''02''
) RESULTS WHERE RESULTS.PARAMVALUE IS NOT NULL';
	dbsqlStrInit clob:='WITH BONDSTYPE AS(
SELECT
RESULT1.PORTFOLIO_ID,
RESULT1.FINPROD_ID,
RESULT1.CDATE,
RESULT1.INV_AIM,
NVL(RESULT1.SHARE_AMT,0)+NVL(RESULT2.no_amt,0) as finally_amt,
NVL(RESULT1.DSC_COST_AMT,0)+NVL(RESULT2.no_cost,0) as finally_cost ,
NVL(RESULT1.TDY_FLOAT_INGPL,0)*(NVL(RESULT1.SHARE_AMT,0)+NVL(RESULT2.no_amt,0))/DECODE(RESULT1.SHARE_AMT,0,null,RESULT1.SHARE_AMT) as finally_GY,
NVL(RESULT1.TDY_INTINCEXP,0)+NVL(RESULT2.no_lixi,0) as finally_LX,
''system'' as UPDATE_USER,
sysdate as UPDATE_TIME
FROM(
SELECT F.PORTFOLIO_ID,
       F.FINPROD_ID,
       F.CDATE,
       F.INV_AIM,
       F.SHARE_AMT,
       F.TDY_FLOAT_INGPL,
       F.DSC_COST_AMT,
       F.TDY_INTINCEXP
  FROM PTL_SEC_VALUTION F
  left join fin_product fp on fp.finprod_id=F.FINPROD_ID
 WHERE F.CDATE <= TRUNC(SYSDATE-1)
   AND F.SHARE_AMT IS NOT NULL
   AND fp.finprod_type2=''F01''
   ) RESULT1
   LEFT JOIN (
   select
    tpda.portfolio_id,
    tpd.finprod_id,
    sum(decode(tpd.ps,''T01'',-1*tpd.share_amt,tpd.share_amt)) as no_amt,
    sum(decode(tpd.ps,''T01'',-1*tpd.cprice_amt,tpd.cprice_amt)) as no_cost,
    sum(decode(tpd.ps,''T01'',-1*tpd.int_amt,tpd.int_amt)) as no_lixi,
    tpd.inv_aim
    from TRD_PRODUCT_DEAL tpd,TRD_PRODUCT_DEAL_ADD tpda
    where tpd.trade_id=tpda.trade_id
    and tpd.finprod_type2=''F01''
    and tpd.trade_date<=TRUNC(sysdate-1) and TRUNC(sysdate-1)<tpd.settle_date
    group by tpda.portfolio_id,tpd.finprod_id,tpd.inv_aim
   ) RESULT2 ON  RESULT1.PORTFOLIO_ID=RESULT2.PORTFOLIO_ID
             AND RESULT1.FINPROD_ID=RESULT2.FINPROD_ID
             AND RESULT1.INV_AIM=RESULT2.INV_AIM
),
YDRTYPE AS(
select
psv.cdate,
psv.portfolio_id,
psv.finprod_id,
psv.inv_aim,
psv.share_amt,
psv.tdy_float_ingpl,
''system'' as UPDATE_USER,
sysdate as UPDATE_TIME
from ptl_sec_valution psv
left join fin_trade_product ftp on psv.finprod_id=ftp.finprod_id
where nvl(ftp.finprod_type2,''notFound'') in(''F12'',''F13'',''F14'')
and psv.cdate <= trunc(sysdate-1)
),
OTHERTYPE AS(
select
psv.cdate,
psv.portfolio_id,
psv.finprod_id,
psv.inv_aim,
psv.share_amt,
psv.tdy_float_ingpl,
psv.dsc_cost_amt,
psv.tdy_intincexp,
''system'' as UPDATE_USER,
sysdate as UPDATE_TIME
from ptl_sec_valution psv
left join fin_trade_product ftp on psv.finprod_id=ftp.finprod_id
left join fin_product fp on psv.finprod_id=fp.finprod_id
where nvl(nvl(ftp.finprod_type2,fp.finprod_type2),''notFound'')  not in(''F01'',''F12'',''F13'',''F14'',''notFound'')
and psv.cdate <= trunc(sysdate-1)
) 

SELECT * FROM (
SELECT F.PORTFOLIO_ID AS OBJECTID,
       F.FINPROD_ID AS ELEMENTID,
       F.CDATE,
       F.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''SHARE_AMT'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(NVL(F.finally_amt,0)) AS PARAMVALUE,
       ''SHARE_AMT'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       F.UPDATE_USER,
       F.UPDATE_TIME
FROM BONDSTYPE F
UNION ALL
SELECT F.PORTFOLIO_ID AS OBJECTID,
       F.FINPROD_ID AS ELEMENTID,
       F.CDATE,
       F.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''SHARE_AMT'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(F.share_amt) AS PARAMVALUE,
       ''SHARE_AMT'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       F.UPDATE_USER,
       F.UPDATE_TIME
FROM YDRTYPE F
UNION ALL
SELECT F.PORTFOLIO_ID AS OBJECTID,
       F.FINPROD_ID AS ELEMENTID,
       F.CDATE,
       F.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''SHARE_AMT'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(nvl(F.share_amt,0)) AS PARAMVALUE,
       ''SHARE_AMT'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       F.UPDATE_USER,
       F.UPDATE_TIME
FROM OTHERTYPE F
UNION ALL 
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''MARKET_VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(NVL(G.finally_cost,0)+NVL(G.finally_GY,0)+NVL(G.finally_LX,0)) AS PARAMVALUE,
       ''MARKET_VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM BONDSTYPE G
UNION ALL
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''MARKET_VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(NVL(G.tdy_float_ingpl,0)) AS PARAMVALUE,
       ''MARKET_VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM YDRTYPE G
UNION ALL
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''MARKET_VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(NVL(G.tdy_float_ingpl,0)+NVL(G.dsc_cost_amt,0)+NVL(G.tdy_intincexp,0)) AS PARAMVALUE,
       ''MARKET_VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM OTHERTYPE G
UNION ALL 
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''CURRENT_PRICE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       to_char((NVL(G.finally_cost,0)+NVL(G.finally_GY,0)+NVL(G.finally_LX,0))/decode(G.finally_amt,0,null,G.finally_amt)) AS PARAMVALUE,
       ''CURRENT_PRICE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM BONDSTYPE G
UNION ALL
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''CURRENT_PRICE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       to_char(NVL(G.tdy_float_ingpl,null)) AS PARAMVALUE,
       ''CURRENT_PRICE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM YDRTYPE G
UNION ALL
SELECT G.PORTFOLIO_ID AS OBJECTID,
       G.FINPROD_ID AS ELEMENTID,
       G.CDATE,
       G.INV_AIM AS INVAIM,
       NULL AS SECACCIID,
       ''CURRENT_PRICE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       to_char((NVL(G.tdy_float_ingpl,0)+NVL(G.dsc_cost_amt,0)+NVL(G.tdy_intincexp,0))/decode(G.share_amt,0,null,G.share_amt)) AS PARAMVALUE,
       ''CURRENT_PRICE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       G.UPDATE_USER,
       G.UPDATE_TIME
FROM OTHERTYPE G
UNION ALL 
SELECT B.PORTFOLIO_ID AS OBJECT_ID,
       NULL AS ELEMENT_ID,
       B.CDATE,
       NULL AS INV_AIM,
       NULL AS SEC_ACCT_ID,
       ''TDY_ASSET_NETVALUE'' AS PARAM_CODE,
       ''01'' AS PARAM_TYPE,
       TO_CHAR(PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS(B.TDY_ASSET_NETVALUE, B.CCY, B.CDATE, ''JIXU'')) AS PARAM_VALUE,
       ''TDY_ASSET_NETVALUE'' AS DICT_CODE,
       ''P_IMPORT_PROD_DATA'' AS CREATE_USER,
       ''system'' AS CREATE_DEPT,
       B.UPDATE_USER,
       B.UPDATE_TIME
  FROM PTL_PORTFOLIO_VALUATION B
 WHERE B.TDY_ASSET_NETVALUE IS NOT NULL
   AND B.CDATE IS NOT NULL
   AND B.CDATE <= TRUNC(SYSDATE)-1
UNION ALL
SELECT C.PORTFOLIO_ID AS OBJECTID,
       NULL AS ELEMENTID,
       C.CDATE,
       NULL AS INVAIM,
       NULL AS SECACCIID,
       ''TDY_ASSET_VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS(C.TDY_ASSET_VALUE, C.CCY, C.CDATE, ''JIXU'')) AS PARAMVALUE,
       ''TDY_ASSET_VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       C.UPDATE_USER,
       C.UPDATE_TIME
  FROM PTL_PORTFOLIO_VALUATION C
 WHERE C.TDY_ASSET_VALUE IS NOT NULL
   AND C.CDATE IS NOT NULL
   AND C.CDATE <= TRUNC(SYSDATE)-1
UNION ALL
SELECT nvl(fp.FINPROD_MARKET_ID,D.FINPROD_ID) AS OBJECTID,
       NULL AS ELEMENTID,
       D.CDATE,
       NULL AS INVAIM,
       NULL AS SECACCIID,
       ''TDY_SHARE_AMT'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(D.TDY_SHARE_AMT) AS PARAMVALUE,
       ''TDY_SHARE_AMT'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       D.UPDATE_USER,
       D.UPDATE_TIME
  FROM BOK_POSITION_PRD D
  left join fin_product fp on fp.finprod_id=D.FINPROD_ID
 WHERE D.TDY_SHARE_AMT IS NOT NULL
   AND D.CDATE IS NOT NULL
   AND D.CDATE <= TRUNC(SYSDATE)-1
UNION ALL
SELECT nvl(fp.FINPROD_MARKET_ID,E.FINPROD_ID) AS OBJECTID,
       NULL AS ELEMENTID,
       E.CDATE,
       NULL AS INVAIM,
       NULL AS SECACCIID,
       ''TDY_UNIT_VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(E.TDY_UNIT_VALUE) AS PARAMVALUE,
       ''TDY_UNIT_VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       E.UPDATE_USER,
       E.UPDATE_TIME
  FROM BOK_POSITION_PRD E
  left join fin_product fp on fp.finprod_id=E.FINPROD_ID
 WHERE E.TDY_UNIT_VALUE IS NOT NULL
   AND E.CDATE IS NOT NULL
   AND E.CDATE <= TRUNC(SYSDATE)-1
UNION ALL 
SELECT H.PORTFOLIO_ID AS OBJECTID,
       H.CCY AS ELEMENTID,
       H.CDATE,
       NULL AS INVAIM,
       NULL AS SECACCIID,
       ''AMOUNT'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(H.AMOUNT) AS PARAMVALUE,
       ''AMOUNT'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       H.UPDATE_USER,
       H.UPDATE_TIME
  FROM PTL_CASH_POSITION H
 WHERE H.AMOUNT IS NOT NULL
   AND H.CDATE IS NOT NULL
   AND H.CDATE <= TRUNC(SYSDATE)-1
UNION ALL
SELECT I.PORTFOLIO_ID AS OBJECTID,
       I.CCY AS ELEMENTID,
       I.CDATE,
       NULL AS INVAIM,
       NULL AS SECACCIID,
       ''VALUE'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS(I.AMOUNT, I.CCY, I.CDATE, ''JIXU'')) AS PARAMVALUE,
       ''VALUE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       I.UPDATE_USER,
       I.UPDATE_TIME
  FROM PTL_CASH_POSITION I
 WHERE I.AMOUNT IS NOT NULL
   AND I.CDATE IS NOT NULL
   AND I.CDATE <= TRUNC(SYSDATE)-1
UNION ALL
SELECT J.PORTFOLIO_ID AS OBJECTID,
       J.FINPROD_ID AS ELEMENTID,
       J.CDATE,
       J.INV_AIM AS INVAIM,
       J.SEC_ACCT_ID AS SECACCIID,
       J.HODING_TYPE AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       TO_CHAR(J.AMOUNT) AS PARAMVALUE,
       ''POSITION_TYPE'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       J.UPDATE_USER,
       J.UPDATE_TIME
  FROM PTL_SEC_POSITION J
 WHERE J.BRANCH = 0
   AND J.AMOUNT IS NOT NULL
   AND J.CDATE IS NOT NULL
   AND J.CDATE <= TRUNC(SYSDATE)-1
union all
select fp.finprod_id   AS OBJECTID,
       fp.finprod_id AS ELEMENTID,
       pj.CDATE as CDATE,
       null AS INVAIM,
       NULL AS SECACCIID,
       ''GRADE_ORDER'' AS PARAMCODE,
       ''01'' AS PARAMTYPE,
       sdl2.item_value AS PARAMVALUE,
       ''GRADE_ORDER'' AS DICTCODE,
       ''P_IMPORT_PROD_DATA'' AS CREATEUSER,
       ''system'' AS CREATEDEPT,
       pj.UPDATE_USER,
       pj.UPDATE_TIME
from FIN_PRODUCT fp
left join (select t1.*
           from (select t.*,t2.CDATE,
                            row_number() over(partition by t.o_code,t2.CDATE order by t.grade_date desc, t.grade_result desc) rownumber
                 from MST_BOND_CREDIT_RATING t right join (
                       select startDate+level-1 as CDATE
                         from (select min(t.grade_date) as startDate from MST_BOND_CREDIT_RATING t)
                      connect by level <= trunc(sysdate) - startDate
                 ) t2 on t.grade_date <= CDATE) t1
           where t1.rownumber = 1) pj
on pj.o_code = substr(fp.finprod_id, instr(fp.finprod_id, ''_'') + 1)
left join sys_datadict_list sdl
on sdl.dict_code = ''MAIN_GRADE_RESULT''
and sdl.item_code = pj.grade_result
left join sys_datadict_list sdl2
on sdl2.dict_code = ''ST_BOND_GRADE''
and sdl.item_value = sdl2.item_code
where fp.finprod_type2 = ''F01''
and pj.o_type = ''02''
) RESULTS WHERE RESULTS.PARAMVALUE IS NOT NULL';
begin 
	update ADPT_INTERFACESOURCE set DBSQL = dbsqlStr where SOURCEID = '1910290006';
	update ADPT_INTERFACESOURCE set DBSQL = dbsqlStrInit where SOURCEID = '1910290001';
end;
/