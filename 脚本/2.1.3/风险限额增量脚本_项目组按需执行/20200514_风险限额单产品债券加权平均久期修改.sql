--单产品债券加权平均久期
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE = 'DCPZQJQPJJQ' and PARA_GRANULARITY ='05';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('DCPZQJQPJJQ', '05', 'SQL', 'select SUM(dataOne.chiCangMianE/dataTwo.zongHe) AS RVALE  
from(  
	select (  
       case    
		   when B.FINPROD_TYPE2=''F01'' AND D.LAST_PERIOD IS NOT NULL then D.LAST_PERIOD  
		   when B.FINPROD_TYPE2=''F01'' AND C.TYPE_6 IN (''B03'', ''B04'') then (B.MDATE - DATE ''${valDate}'') / 365  
		   when B.FINPROD_TYPE2=''F01'' AND B.IS_RIGHT = ''Y'' then (NVL((SELECT MIN(FEP.EXERCISE_DATE)  
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
		   LEFT JOIN ( 	 
				select  B1.SEC_ID,B1.LAST_PERIOD from  MST_BOND_VALUATION_INFO B1 	 
				inner join 			 
				(select to_char(max(VALUE_DATE),''yyyy-MM-dd'') as VALUE_DATE,SEC_ID from MST_BOND_VALUATION_INFO where VALUE_DATE<=date''${valDate}'' group by sec_id) B2 			 
				on B1.SEC_ID=B2.SEC_ID and to_char(B1.VALUE_DATE,''yyyy-MM-dd'')=B2.VALUE_DATE 
		   ) D  
		   ON  B.FINPROD_MARKET_ID = D.SEC_ID    
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
		   LEFT JOIN ( 	 
				select  B1.SEC_ID,B1.LAST_PERIOD from  MST_BOND_VALUATION_INFO B1 	 
				inner join 			 
				(select to_char(max(VALUE_DATE),''yyyy-MM-dd'') as VALUE_DATE,SEC_ID from MST_BOND_VALUATION_INFO where VALUE_DATE<=date''${valDate}'' group by sec_id) B2 			 
				on B1.SEC_ID=B2.SEC_ID and to_char(B1.VALUE_DATE,''yyyy-MM-dd'')=B2.VALUE_DATE 
		   )  D  
		   ON  B.FINPROD_MARKET_ID = D.SEC_ID    
		   WHERE t1.PORTFOLIO_ID IN ${portfolioCode}  
		   AND t1.CDATE           = DATE ''${valDate}''  
	) dataTwo', 'portfolioCode', 'N', 'GY', '001', TIMESTAMP '2020-05-14 14:45:13', 'GY', TIMESTAMP '2020-05-14 14:45:13');
