delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE = 'DCPZQJQPJJQ' and PARA_GRANULARITY = '05';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('DCPZQJQPJJQ', '05', 'SQL', 'select SUM(dataOne.chiCangMianE/dataTwo.zongHe) AS RVALE  
from(  
	select (  
       case    
		   when B.FINPROD_TYPE2=''F01'' AND D.ENCASH_YEARS IS NOT NULL then D.ENCASH_YEARS  
		   when B.FINPROD_TYPE2=''F01'' AND C.TYPE_6 IN (''B03'', ''B04'') then (B.MDATE - DATE ''${valDate}'') / 365  
		   when B.FINPROD_TYPE2=''F01'' AND B.IS_RIGHT = ''Y'' then (NVL((SELECT  MIN(FEP.EXERCISE_DATE)  
														FROM FIN_EXERCISE_PLAN FEP   
														WHERE FEP.FINPROD_ID   = t1.FINPROD_ID  
														AND FEP.FINPROD_TYPE2  = ''F01''  
														AND FEP.EXERCISE_DATE >= DATE ''${valDate}''  
														),B.MDATE) - DATE ''${valDate}''  
													   ) / 365  
		   when B.FINPROD_TYPE2=''F01'' then (B.MDATE - DATE ''${valDate}'') / 365  
		   else 0  
		   end)*(nvl(t1.DSC_COST_AMT,0)+nvl(t1.TDY_FLOAT_INGPL,0)+nvl(t1.TDY_INTINCEXP,0)+ nvl(t1.DELAY_PAY_AMT,0)) AS chiCangMianE  
		   FROM PTL_SEC_VALUTION t1  
		   INNER JOIN FIN_PRODUCT B  
		   ON t1.FINPROD_ID    = B.FINPROD_ID  
		   AND B.FINPROD_TYPE2 = ''F01''  
		   INNER JOIN FIN_PRODUCT_TYPE C  
		   ON t1.FINPROD_ID = C.FINPROD_ID  
		   INNER JOIN PTL_PORTFOLIO pp  
		   ON t1.PORTFOLIO_ID = pp.PORTFOLIO_ID  
		   LEFT JOIN INTERF_PAYHSRCCBVLT D  
		   ON DECODE(B.TRADE_MARKET, ''01'', ''IB'', ''03'', ''SH'', ''04'', ''SZ'')  
		    	|| B.FINPROD_MARKET_ID = D.SECID  
		    	|| DECODE(B.TRADE_MARKET, ''01'', ''.IB'', ''03'', ''.SH'', ''04'', ''.SZ'')  
		   AND D.CDATE            = TO_CHAR(DATE ''${valDate}'', ''yyyy-MM-dd'')  
		   WHERE t1.PORTFOLIO_ID IN ${portfolioCode}  
		   AND t1.CDATE           = DATE ''${valDate}''  
	) dataOne,  
  (  
	SELECT 
       SUM(nvl(t1.DSC_COST_AMT,0)+nvl(t1.TDY_FLOAT_INGPL,0)+nvl(t1.TDY_INTINCEXP,0)+ nvl(t1.DELAY_PAY_AMT,0)) as zongHe  
		   FROM PTL_SEC_VALUTION t1  
		   INNER JOIN FIN_PRODUCT B  
		   ON t1.FINPROD_ID    = B.FINPROD_ID  
		   AND B.FINPROD_TYPE2 = ''F01''  
		   INNER JOIN FIN_PRODUCT_TYPE C  
		   ON t1.FINPROD_ID = C.FINPROD_ID  
		   INNER JOIN PTL_PORTFOLIO pp  
		   ON t1.PORTFOLIO_ID = pp.PORTFOLIO_ID  
		   LEFT JOIN INTERF_PAYHSRCCBVLT D  
		   ON DECODE(B.TRADE_MARKET, ''01'', ''IB'', ''03'', ''SH'', ''04'', ''SZ'')  
		    	|| B.FINPROD_MARKET_ID = D.SECID  
		    	|| DECODE(B.TRADE_MARKET, ''01'', ''.IB'', ''03'', ''.SH'', ''04'', ''.SZ'')  
		   AND D.CDATE            = TO_CHAR(DATE ''${valDate}'', ''yyyy-MM-dd'')  
		   WHERE t1.PORTFOLIO_ID IN ${portfolioCode}  
		   AND t1.CDATE           = DATE ''${valDate}''  
	) dataTwo', 'portfolioCode', 'N', 'GY', '001', TIMESTAMP '2020-05-12 18:15:40', 'GY', TIMESTAMP '2020-05-12 18:15:40');
