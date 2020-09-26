--按照资产净值加权的事后(日间)修改
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE = 'AZZCJZJQDZHJQ' and PARA_GRANULARITY = '04';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('AZZCJZJQDZHJQ', '04', 'SQL', 'SELECT sum((TRADE_AMT)*(M_DURATION)) AS RVALUE FROM (				
  select				
	SUM(A1.TRADE_AMT) TRADE_AMT,			
	CASE WHEN A1.FINPROD_TYPE =''F01'' THEN			
	(			
	 	select  CASE WHEN nvl(sum(B1.M_DURATION),0)=0 THEN (((A1.mdate)-(date''${valDate}''))/365) ELSE (nvl(sum(B1.M_DURATION),0)) END M_DURATION from  MST_BOND_VALUATION_INFO B1		
	    inner join			
	    (select to_char(max(VALUE_DATE),''yyyy-MM-dd'') as VALUE_DATE,SEC_ID from MST_BOND_VALUATION_INFO where VALUE_DATE<=date''${valDate}'' group by sec_id) B2			
	    on B1.SEC_ID=B2.SEC_ID and B1.VALUE_DATE=B2.VALUE_DATE WHERE B1.SEC_ID=A1.FINPROD_ID			
	)			
	WHEN (A1.FINPROD_TYPE =''F06'' OR A1.FINPROD_TYPE =''XJ'') THEN (1)/365			
	WHEN a1.FINPROD_ID =a1.PORTFOLIO_ID			
	THEN (1)/365			
    ELSE ((nvl((A1.mdate)-(date''${valDate}''),0))/365) END M_DURATION				
	from			
	(			
 	select NULL AS FINPROD_ID,NULL AS PORTFOLIO_ID,nvl(sum(AMOUNT),0) as  TRADE_AMT,''XJ'' AS FINPROD_TYPE,NULL AS MDATE   from PTL_CASH_POSITION			
 	 where  PORTFOLIO_ID in ${portfolioCode} and HODING_TYPE=''03'' and CDATE =date''${valDate}''			
    UNION ALL				
		select		
		t1.FINPROD_ID,t1.PORTFOLIO_ID,nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0) as TRADE_AMT,t2.FINPROD_TYPE2,t2.MDATE		
		from PTL_SEC_VALUTION t1		
		inner join FIN_PRODUCT t2	on t1.FINPROD_ID = t2.FINPROD_ID	
		where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''-1		
	   UNION ALL			
	   select			
		t1.FINPROD_ID,t1.PORTFOLIO_ID, nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0) as TRADE_AMT,t3.FINPROD_TYPE2,t3.MDATE		
		from PTL_SEC_VALUTION t1		
		inner join FIN_TRADE_PRODUCT t3	 on t1.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0	
		where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''-1		
		and  t3.FINPROD_TYPE2 not in (''F02'', ''F04'', ''F08'', ''F25'')		
	  ', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
----------过长截断	
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||'  UNION ALL			
		select		
		n.FINPROD_ID ,t4.PORTFOLIO_ID,		
		 nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
		  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
		  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
		  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
		  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
		  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
		  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
		  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
		  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
		  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
          -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		  as  TRADE_AMT,n.FINPROD_TYPE2,nvl(t2.MDATE,t3.MDATE) as MDATE		
		  from TRD_PRODUCT_DEAL n		
		  left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID		
		  left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID		
		  LEFT JOIN TRD_PRODUCT_DEAL_ADD t4 ON n.TRADE_ID=t4.TRADE_ID		
		  where t4.PORTFOLIO_ID in ${portfolioCode} and  n.TRADE_DATE = date''${valDate}''		
		  and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'', ''F04'',''F08'', ''F25'') and t3.BRANCH=0))		
		  and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T52'',''T19'') group by t4.PORTFOLIO_ID,n.FINPROD_ID,nvl(t2.MDATE,t3.MDATE),n.FINPROD_TYPE2		
     UNION ALL				
	  select	n.FINPROD_ID ,m.PORTFOLIO_ID,		
	   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 
	  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_TYPE2,nvl(t2.MDATE,t3.MDATE) as MDATE			
	  from WFL_TRD_PRODUCT_DEAL_ADD m			
	  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''			
	  INNER JOIN FIN_PRODUCT T2 on T2.FINPROD_ID=n.FINPROD_ID 			 
	  left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID 			
      WHERE  m.PORTFOLIO_ID in ${portfolioCode}  AND n.TRADE_DATE=date''${valDate}''  				
      AND ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'', ''F04'',''F08'', ''F25'') AND t3.BRANCH=0 )) 				
      and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T52'',''T19'') group by m.PORTFOLIO_ID,n.FINPROD_ID,nvl(t2.MDATE,t3.MDATE),n.FINPROD_TYPE2				
	) A1 GROUP BY A1.PORTFOLIO_ID,A1.FINPROD_ID,A1.FINPROD_TYPE,A1.MDATE			
)'  where PARA_CODE = 'AZZCJZJQDZHJQ' and PARA_GRANULARITY='04';

--产品净资产日间修改
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE = 'CPJZCJZ' and PARA_GRANULARITY = '04';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('CPJZCJZ', '04', 'SQL', 'select sum(amount) AS amount from			
(			
      select sum(amount) as amount			
   from			
   (			
     SELECT 			
       DISTINCT t2.PORTFOLIO_ID ,			
       mujiData.mujiAmt  as AMOUNT			
       from  PTL_PORTFOLIO t2			
       left join 			
       (			
       select t1.PORTFOLIO_ID, sum(t1.TRADE_AMT) as mujiAmt from TRD_FIN_PRODUCT_DEAL_TOTAL t1			
       WHERE  t1.PS in (''C1'', ''C2'')	  and t1.SETTLE_DATE <= date''${valDate}''	and t1.TRADE_STATUS=''01''	
       group by t1.PORTFOLIO_ID			
       ) mujiData			
       on t2.PORTFOLIO_ID = mujiData.PORTFOLIO_ID			
       where  t2.VDATE=date''${valDate}'' and t2.PORTFOLIO_ID in ${portfolioCode}			
   UNION ALL			
      SELECT 			
       DISTINCT t1.PORTFOLIO_ID RKEY,			
        nvl(TDY_ASSET_NETVALUE,0) as RVALUE			
       from PTL_PORTFOLIO_VALUATION t1			
       where  t1.CDATE=date''${valDate}''-1 and t1.PORTFOLIO_ID in ${portfolioCode}			
   )			
    union all		
    select		 	
  nvl(sum(decode(n.PS,''C3'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''C4'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''C5'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''C6'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
	-nvl(sum(decode(n.PS,''C7'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C9'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C12'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C13'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C14'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C15'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C16'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C17'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C18'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as amount	
    from TRD_PRODUCT_DEAL_ADD m		
    inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID		
    where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' group by m.PORTFOLIO_ID				
 ', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
---------过长截断
 update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' union all		
    select		 	
   nvl(sum(decode(n.PS,''C3'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''C4'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''C5'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''C6'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
	-nvl(sum(decode(n.PS,''C7'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C9'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C12'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C13'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C14'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C15'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C16'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C17'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C18'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
	-nvl(sum(decode(n.PS,''C19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as amount	
    from WFL_TRD_PRODUCT_DEAL_ADD m		
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''		
    where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' group by m.PORTFOLIO_ID		
)'  where PARA_CODE = 'CPJZCJZ' and PARA_GRANULARITY='04';	 
 