declare
	sql01 clob:='SELECT nvl(SUM(rvalue),0) AS rvalue ,rkey  FROM (
SELECT PP.PORTFOLIO_ID  AS rkey ,
       nvl(SUM(DECODE(wfl.ps,''T01'',WFL.PRIN_AMT,''T02'',WFL.PRIN_AMT*-1,WFL.PRIN_AMT)),0)  AS rvalue
  FROM TRD_PRODUCT_DEAL WFL
  LEFT JOIN PTL_TRADE PT
    ON NVL(WFL.P_TRADE_ID, WFL.TRADE_ID) = PT.BUSI_NO
  LEFT JOIN PTL_PORTFOLIO PP
    ON PT.PORTFOLIO_ID = PP.PORTFOLIO_ID
 WHERE PP.INVEST_YESORNO = ''Y''
   AND WFL.FINPROD_TYPE2 = ''F07''
   AND wfl.trade_date = date''${valDate}''
   AND WFL.PS IN (''T01'', ''T02'')
   AND WFL.TRADE_STATUS = ''01''
   AND WFL.IS_CANCEL != ''Y''
   AND pp.mdate>=date''${valDate}''
   GROUP BY pp.portfolio_id
UNION ALL
 select portfolio_id  rkey,
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue
  from ptl_sec_valution
 where CDATE = date''${valDate}''-1
 and FINPROD_ID like ''F07_%''
 group by portfolio_id
UNION ALL
SELECT t1.portfolio_id AS rkey,NVL(SUM(T1.FPRICE_AMT)*-1,0) AS rvalue

                   FROM TRD_FIN_PRODUCT_DEAL_TOTAL T1
                   LEFT JOIN PA_TRD_FIN_PRODUCT_DEAL_TOTAL T3  ON T1.TOTLE_TRADE_ID = T3.TOTLE_TRADE_ID
                   LEFT JOIN FIN_PRODUCT T4 ON T1.FINPROD_ID = T4.FINPROD_ID
                   LEFT JOIN FIN_PRODUCT TC4 ON T1.CHL_ID = TC4.FINPROD_ID
                   LEFT JOIN FIN_PRODUCT_TYPE FPROD_TYPE ON FPROD_TYPE.FINPROD_ID = T1.FINPROD_ID
                  WHERE T1.TRADE_STATUS = ''01''
                    AND NVL(T1.IS_OVERDUE_DEAL, ''N'') = ''N''
                    AND T1.IS_CANCEL = ''N''
                    AND T1.FINPROD_TYPE2 = ''F07''
                    AND T1.P_TOTLE_TRADE_ID IS NULL
                    AND T3.PA_BUSS_TYPE = ''41''
                    AND t1.trade_date=date''${valDate}''
                  GROUP BY  t1.portfolio_id
                  UNION ALL
SELECT
    VAL.PORTFOLIO_ID RKEY,NVL(SUM(NVL(VAL.MARKET_VALUE,0)+nvl(VAL.TDY_INTINCEXP, 0)),0) RVALUE
FROM PTL_SEC_PIERCE_VALUTION VAL
INNER JOIN FIN_PRODUCT PRD ON VAL.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN FIN_PRODUCT_ADD FPA ON FPA.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN FIN_PRODUCT_TYPE FPT ON FPT.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN PA_FIN_PRODUCT PFP ON PFP.FINPROD_ID = PRD.FINPROD_ID
WHERE VAL.CDATE = date''${valDate}'' - 1 AND PRD.FINPROD_TYPE2 = ''F07''
-- 这里是是否可提前支取的条件
AND PRD.MDATE IS NOT NULL AND FPT.TYPE_6 != ''D03''
GROUP BY VAL.PORTFOLIO_ID
 )GROUP BY rkey';
	sql02 clob:='SELECT nvl(SUM(rvalue),0) AS rvalue ,rkey  FROM (
SELECT PP.PORTFOLIO_ID  AS rkey ,
       nvl(SUM(DECODE(wfl.ps,''T01'',WFL.PRIN_AMT,''T02'',WFL.PRIN_AMT*-1,WFL.PRIN_AMT)),0)  AS rvalue
  FROM TRD_PRODUCT_DEAL WFL
  LEFT JOIN PTL_TRADE PT
    ON NVL(WFL.P_TRADE_ID, WFL.TRADE_ID) = PT.BUSI_NO
  LEFT JOIN PTL_PORTFOLIO PP
    ON PT.PORTFOLIO_ID = PP.PORTFOLIO_ID
 WHERE PP.INVEST_YESORNO = ''Y''
   AND WFL.FINPROD_TYPE2 = ''F07''
   AND wfl.trade_date = date''${valDate}''
   AND WFL.PS IN (''T01'', ''T02'')
   AND WFL.TRADE_STATUS = ''01''
   AND WFL.IS_CANCEL != ''Y''
   AND pp.mdate>=date''${valDate}''
   GROUP BY pp.portfolio_id
UNION ALL
 select portfolio_id  rkey,
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue
  from ptl_sec_valution
 where CDATE = date''${valDate}''-1
 and FINPROD_ID like ''F07_%''
 group by portfolio_id
UNION ALL
SELECT t1.portfolio_id AS rkey,NVL(SUM(T1.FPRICE_AMT)*-1,0) AS rvalue

                   FROM TRD_FIN_PRODUCT_DEAL_TOTAL T1
                   LEFT JOIN PA_TRD_FIN_PRODUCT_DEAL_TOTAL T3  ON T1.TOTLE_TRADE_ID = T3.TOTLE_TRADE_ID
                   LEFT JOIN FIN_PRODUCT T4 ON T1.FINPROD_ID = T4.FINPROD_ID
                   LEFT JOIN FIN_PRODUCT TC4 ON T1.CHL_ID = TC4.FINPROD_ID
                   LEFT JOIN FIN_PRODUCT_TYPE FPROD_TYPE ON FPROD_TYPE.FINPROD_ID = T1.FINPROD_ID
                  WHERE T1.TRADE_STATUS = ''01''
                    AND NVL(T1.IS_OVERDUE_DEAL, ''N'') = ''N''
                    AND T1.IS_CANCEL = ''N''
                    AND T1.FINPROD_TYPE2 = ''F07''
                    AND T1.P_TOTLE_TRADE_ID IS NULL
                    AND T3.PA_BUSS_TYPE = ''41''
                    AND t1.trade_date=date''${valDate}''
                  GROUP BY  t1.portfolio_id
                  UNION ALL
SELECT
    VAL.PORTFOLIO_ID RKEY,NVL(SUM(NVL(VAL.MARKET_VALUE,0)+nvl(VAL.TDY_INTINCEXP, 0)),0) RVALUE
FROM PTL_SEC_PIERCE_VALUTION VAL
INNER JOIN FIN_PRODUCT PRD ON VAL.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN FIN_PRODUCT_ADD FPA ON FPA.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN FIN_PRODUCT_TYPE FPT ON FPT.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN PA_FIN_PRODUCT PFP ON PFP.FINPROD_ID = PRD.FINPROD_ID
WHERE VAL.CDATE = date''${valDate}'' - 1 AND PRD.FINPROD_TYPE2 = ''F07''
-- 这里是是否可提前支取的条件
AND PRD.MDATE IS NOT NULL AND FPT.TYPE_6 != ''D03''
GROUP BY VAL.PORTFOLIO_ID
 )GROUP BY rkey';
	sql03 clob:='SELECT nvl(SUM(rvalue),0) AS rvalue ,rkey  FROM (
SELECT PP.PORTFOLIO_ID  AS rkey ,
       nvl(SUM(DECODE(wfl.ps,''T01'',WFL.PRIN_AMT,''T02'',WFL.PRIN_AMT*-1,WFL.PRIN_AMT)),0)  AS rvalue
  FROM TRD_PRODUCT_DEAL WFL
  LEFT JOIN PTL_TRADE PT
    ON NVL(WFL.P_TRADE_ID, WFL.TRADE_ID) = PT.BUSI_NO
  LEFT JOIN PTL_PORTFOLIO PP
    ON PT.PORTFOLIO_ID = PP.PORTFOLIO_ID
 WHERE PP.INVEST_YESORNO = ''Y''
   AND WFL.FINPROD_TYPE2 = ''F07''
   AND wfl.trade_date = date''${valDate}''
   AND WFL.PS IN (''T01'', ''T02'')
   AND WFL.TRADE_STATUS = ''01''
   AND WFL.IS_CANCEL != ''Y''
   AND pp.mdate>=date''${valDate}''
   GROUP BY pp.portfolio_id
UNION ALL
 select portfolio_id  rkey,
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue
  from ptl_sec_valution
 where CDATE = date''${valDate}''-1
 and FINPROD_ID like ''F07_%''
 group by portfolio_id
UNION ALL
SELECT t1.portfolio_id AS rkey,NVL(SUM(T1.FPRICE_AMT)*-1,0) AS rvalue

                   FROM TRD_FIN_PRODUCT_DEAL_TOTAL T1
                   LEFT JOIN PA_TRD_FIN_PRODUCT_DEAL_TOTAL T3  ON T1.TOTLE_TRADE_ID = T3.TOTLE_TRADE_ID
                   LEFT JOIN FIN_PRODUCT T4 ON T1.FINPROD_ID = T4.FINPROD_ID
                   LEFT JOIN FIN_PRODUCT TC4 ON T1.CHL_ID = TC4.FINPROD_ID
                   LEFT JOIN FIN_PRODUCT_TYPE FPROD_TYPE ON FPROD_TYPE.FINPROD_ID = T1.FINPROD_ID
                  WHERE T1.TRADE_STATUS = ''01''
                    AND NVL(T1.IS_OVERDUE_DEAL, ''N'') = ''N''
                    AND T1.IS_CANCEL = ''N''
                    AND T1.FINPROD_TYPE2 = ''F07''
                    AND T1.P_TOTLE_TRADE_ID IS NULL
                    AND T3.PA_BUSS_TYPE = ''41''
                    AND t1.trade_date=date''${valDate}''
                  GROUP BY  t1.portfolio_id
                  UNION ALL
SELECT
    VAL.PORTFOLIO_ID RKEY,NVL(SUM(NVL(VAL.MARKET_VALUE,0)+nvl(VAL.TDY_INTINCEXP, 0)),0) RVALUE
FROM PTL_SEC_PIERCE_VALUTION VAL
INNER JOIN FIN_PRODUCT PRD ON VAL.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN FIN_PRODUCT_ADD FPA ON FPA.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN FIN_PRODUCT_TYPE FPT ON FPT.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN PA_FIN_PRODUCT PFP ON PFP.FINPROD_ID = PRD.FINPROD_ID
WHERE VAL.CDATE = date''${valDate}'' - 1 AND PRD.FINPROD_TYPE2 = ''F07''
-- 这里是是否可提前支取的条件
AND PRD.MDATE IS NOT NULL AND FPT.TYPE_6 != ''D03''
GROUP BY VAL.PORTFOLIO_ID
 )GROUP BY rkey';
	sql04 clob:='select sum(O_MARKET_VALUE) O_MARKET_VALUE from (
select sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) O_MARKET_VALUE
from ptl_sec_valution where  CDATE= date''${valDate}''-1
and FINPROD_ID like ''F07_%'' and ${portfolioCode} = concat(''F16_'',portfolio_id)
union all 
SELECT  case ps when ''T01'' then trade_amt else -1*trade_amt end O_MARKET_VALUE  
FROM TRD_PRODUCT_DEAL  tpd inner join TRD_PRODUCT_DEAL_ADD  tpda on tpd.trade_id= tpda.trade_id
where  tpd.finprod_id like ''F07_%'' and concat(''F16_'',tpda.PORTFOLIO_ID)=${portfolioCode} and tpd.trade_date = date''${valDate}''
and not exists (select 1 from TRD_PRODUCT_DEAL where TRD_PRODUCT_DEAL.trade_id = concat(tpd.trade_id,''R''))
and not exists (select 1 from WFL_TRD_PRODUCT_DEAL where TRD_PRODUCT_DEAL.trade_id = concat(tpd.trade_id,''R''))
and not exists (select 1 from TRD_PRODUCT_DEAL where concat(TRD_PRODUCT_DEAL.trade_id,''R'') = tpd.trade_id)
union all
SELECT  case ps when ''T01'' then trade_amt else -1*trade_amt end O_MARKET_VALUE  
FROM WFL_TRD_PRODUCT_DEAL  tpd inner join WFL_TRD_PRODUCT_DEAL_ADD  tpda on tpd.trade_id= tpda.trade_id
where  tpd.finprod_id like ''F07_%'' and concat(''F16_'',tpda.PORTFOLIO_ID)=${portfolioCode} and tpd.trade_date = date''${valDate}''
and not exists (select 1 from TRD_PRODUCT_DEAL where concat(TRD_PRODUCT_DEAL.trade_id,''R'') = tpd.trade_id))';
	sql05 clob:='select sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) O_MARKET_VALUE
  from ptl_sec_valution
 where CDATE= date''${valDate}''
 and FINPROD_ID like ''F07_%''
 and ${portfolioCode} = concat(''F16_'',portfolio_id)';
begin
	update RSK_ISUP_INDICATOR_PARA_DTL set content = sql01 where PARA_CODE = 'YGDQXYHCKGZ' and para_granularity = '01'; 
	update RSK_ISUP_INDICATOR_PARA_DTL set content = sql02 where PARA_CODE = 'YGDQXYHCKGZ' and para_granularity = '02'; 
	update RSK_ISUP_INDICATOR_PARA_DTL set content = sql03 where PARA_CODE = 'YGDQXYHCKGZ' and para_granularity = '03'; 
	update RSK_ISUP_INDICATOR_PARA_DTL set content = sql04 where PARA_CODE = 'YGDQXYHCKGZ' and para_granularity = '04'; 
	update RSK_ISUP_INDICATOR_PARA_DTL set content = sql05 where PARA_CODE = 'YGDQXYHCKGZ' and para_granularity = '05'; 
end;
/