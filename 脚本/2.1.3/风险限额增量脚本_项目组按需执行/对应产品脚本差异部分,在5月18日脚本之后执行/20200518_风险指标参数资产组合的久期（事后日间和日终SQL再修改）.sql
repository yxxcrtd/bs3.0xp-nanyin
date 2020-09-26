
update RSK_ISUP_INDICATOR_PARA_DTL set content = 
'select
 sum(nvl(targetData.remainPeriod, 0) * nvl(targetData.jiaQuanGuiZe, 0)/dataTwo.amount) as RVALUE
 from
 (
 select
   case when B.FINPROD_TYPE2 = ''F01'' and D.ENCASH_YEARS is not null then D.ENCASH_YEARS
    when B.FINPROD_TYPE2 = ''F07'' then (B.MDATE - date ''${valDate}'') / 365
    when E.FINPROD_TYPE2 in (''F03'', ''F05'', ''F09'') then (E.MDATE - date ''${valDate}'') / 365
    when B.FINPROD_TYPE2 = ''F01'' and C.TYPE_6 in (''B03'', ''B04'') then (B.MDATE - date ''${valDate}'') / 365
    when B.FINPROD_TYPE2 = ''F01'' and B.IS_RIGHT = ''Y'' 
        then ((SELECT MIN(FEP.EXERCISE_DATE) 
                FROM FIN_EXERCISE_PLAN FEP 
                WHERE FEP.FINPROD_ID = A.FINPROD_ID AND FEP.FINPROD_TYPE2 = ''F01'' AND FEP.EXERCISE_DATE >= date ''${valDate}'')
                - date ''${valDate}'') / 365
     when B.FINPROD_TYPE2 = ''F01'' then (B.MDATE - date ''${valDate}'') / 365
     when B.FINPROD_TYPE2 = ''F18'' then (B.MDATE - date ''${valDate}'') / 365
     else 0
   end as remainPeriod,
   case when B.FINPROD_TYPE2 = ''F07'' then A.AMOUNT + nvl(PSV.TDY_INTINCEXP, 0)
     when E.FINPROD_TYPE2 in (''F03'', ''F05'') then E.PRIN + nvl(PSV.TDY_INTINCEXP, 0)
     else nvl(PSV.DSC_COST_AMT, 0) + nvl(PSV.TDY_INTINCEXP, 0)
   end as jiaQuanGuiZe
 from PTL_SEC_POSITION A
  left join FIN_PRODUCT B on A.FINPROD_ID = B.FINPROD_ID and B.FINPROD_TYPE2 != ''F16''
  left join FIN_PRODUCT_TYPE C on A.FINPROD_ID = C.FINPROD_ID
  left join INTERF_PAYHSRCCBVLT D on decode(B.TRADE_MARKET, ''01'', ''IB'', ''03'', ''SH'', ''04'', ''SZ'', '''') || B.FINPROD_MARKET_ID = D.SECID
     || decode(B.TRADE_MARKET, ''01'', ''.IB'', ''03'', ''.SH'', ''04'', ''.SZ'', '''')
     and D.CDATE = to_char(date ''${valDate}'', ''yyyy-MM-dd'')
  left join FIN_TRADE_PRODUCT E on A.FINPROD_ID = E.FINPROD_ID and E.BRANCH = 0
  left join PTL_SEC_VALUTION PSV ON A.FINPROD_ID = PSV.FINPROD_ID AND A.PORTFOLIO_ID = PSV.PORTFOLIO_ID AND PSV.CDATE = date ''${valDate}''
 where A.PORTFOLIO_ID in ${portfolioCode} and A.CDATE = date ''${valDate}'' and A.AMOUNT > 0 and A.HODING_TYPE = ''03''
 and (E.FINPROD_TYPE2 is null or E.FINPROD_TYPE2  not in (''F02'', ''F04'', ''F08'', ''F25''))
 
 union all
 
 select
  1/365 as remainPeriod,
  A.AMOUNT as jiaQuanGuiZe
 from PTL_CASH_POSITION A
where A.PORTFOLIO_ID in ${portfolioCode} and A.CDATE = date ''${valDate}'' and A.AMOUNT > 0 and A.HODING_TYPE = ''03''
'
where para_code = 'ZCZHJQ' and para_granularity = '04';

update RSK_ISUP_INDICATOR_PARA_DTL set content = content ||
'union all

select 
   case when t2.FINPROD_TYPE2 = ''F01'' and t2.ENCASH_YEARS is not null then t2.ENCASH_YEARS
    when t2.FINPROD_TYPE2 = ''F07'' then (t2.MDATE - date ''${valDate}'') / 365
    when t2.FINPROD_TYPE2 = ''F01'' and t2.IS_RIGHT = ''Y'' 
        then ((SELECT MIN(FEP.EXERCISE_DATE) 
                FROM FIN_EXERCISE_PLAN FEP 
                WHERE FEP.FINPROD_ID = t2.FINPROD_ID AND FEP.FINPROD_TYPE2 = ''F01'' AND FEP.EXERCISE_DATE >= date ''${valDate}'')
                - date ''${valDate}'') / 365
     when t2.FINPROD_TYPE2 = ''F01'' then (t2.MDATE - date ''${valDate}'') / 365
     when t2.FINPROD_TYPE2 = ''F18'' then (t2.MDATE - date ''${valDate}'') / 365
     else 0
   end as remainPeriod,
   TRADE_AMT as jiaQuanGuiZe
from (
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
		  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_TYPE2,t2.MDATE,t2.IS_RIGHT,D.ENCASH_YEARS		
		  from TRD_PRODUCT_DEAL n		
		  left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID		
		  left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID		
		  LEFT JOIN TRD_PRODUCT_DEAL_ADD t4 ON n.TRADE_ID=t4.TRADE_ID	
  left join INTERF_PAYHSRCCBVLT D on decode(t2.TRADE_MARKET, ''01'', ''IB'', ''03'', ''SH'', ''04'', ''SZ'', '''') || t2.FINPROD_MARKET_ID = D.SECID
     || decode(t2.TRADE_MARKET, ''01'', ''.IB'', ''03'', ''.SH'', ''04'', ''.SZ'', '''')
     and D.CDATE = to_char(date ''${valDate}'', ''yyyy-MM-dd'')	
		  where t4.PORTFOLIO_ID in ${portfolioCode} and  n.TRADE_DATE = date''${valDate}''		
		  and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'', ''F04'',''F08'', ''F25'') and t3.BRANCH=0))		
		  and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T52'') group by t4.PORTFOLIO_ID,n.FINPROD_ID,t2.MDATE,n.FINPROD_TYPE2,t2.IS_RIGHT,D.ENCASH_YEARS		
'
where para_code = 'ZCZHJQ' and para_granularity = '04';
update RSK_ISUP_INDICATOR_PARA_DTL set content = content ||
'     UNION ALL				
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
	  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_TYPE2,t2.MDATE,t2.IS_RIGHT,D.ENCASH_YEARS 			
	  from WFL_TRD_PRODUCT_DEAL_ADD m			
	  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''			
	  INNER JOIN FIN_PRODUCT T2 on T2.FINPROD_ID=n.FINPROD_ID 			 
	  left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID 			
  left join INTERF_PAYHSRCCBVLT D on decode(t2.TRADE_MARKET, ''01'', ''IB'', ''03'', ''SH'', ''04'', ''SZ'', '''') || t2.FINPROD_MARKET_ID = D.SECID
     || decode(t2.TRADE_MARKET, ''01'', ''.IB'', ''03'', ''.SH'', ''04'', ''.SZ'', '''')
     and D.CDATE = to_char(date ''${valDate}'', ''yyyy-MM-dd'')
      WHERE  m.PORTFOLIO_ID in ${portfolioCode}  AND n.TRADE_DATE=date''${valDate}''  				
      AND ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'', ''F04'',''F08'', ''F25'') AND t3.BRANCH=0 )) 				
      and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T52'') group by m.PORTFOLIO_ID,n.FINPROD_ID,t2.MDATE,n.FINPROD_TYPE2,t2.IS_RIGHT,D.ENCASH_YEARS				
) t2
'
where para_code = 'ZCZHJQ' and para_granularity = '04';

update RSK_ISUP_INDICATOR_PARA_DTL set content = content ||
') targetData, ( 
select sum(amount) AS amount from (     
    select sum(amount) as amount from (
        SELECT       
            DISTINCT t2.PORTFOLIO_ID ,     
            mujiData.mujiAmt  as AMOUNT      
        from  PTL_PORTFOLIO t2     
        left join (      
           select t1.PORTFOLIO_ID, sum(t1.TRADE_AMT) as mujiAmt from TRD_FIN_PRODUCT_DEAL_TOTAL t1      
           WHERE  t1.PS in (''C1'', ''C2'')   and t1.SETTLE_DATE <= date''${valDate}''  and t1.TRADE_STATUS=''01''  
           group by t1.PORTFOLIO_ID     
        ) mujiData on t2.PORTFOLIO_ID = mujiData.PORTFOLIO_ID     
        where  t2.VDATE=date''${valDate}'' and t2.PORTFOLIO_ID in ${portfolioCode}     
        UNION ALL      
        SELECT      
            DISTINCT t1.PORTFOLIO_ID RKEY,     
            nvl(TDY_ASSET_NETVALUE,0) as RVALUE     
        from PTL_PORTFOLIO_VALUATION t1      
        where  t1.CDATE=date''${valDate}''-1 and t1.PORTFOLIO_ID in ${portfolioCode}     
    )
'
where para_code = 'ZCZHJQ' and para_granularity = '04';

update RSK_ISUP_INDICATOR_PARA_DTL set content = content ||
'    union all   
    select    
        nvl(sum(decode(n.PS,''C1'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
       +nvl(sum(decode(n.PS,''C2'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
       +nvl(sum(decode(n.PS,''C3'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
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
    left join PTL_PORTFOLIO t2  on t2.portfolio_id =  m.portfolio_id     
    where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' and  t2.VDATE>date''${valDate}'' group by m.PORTFOLIO_ID       
'
where para_code = 'ZCZHJQ' and para_granularity = '04';

update RSK_ISUP_INDICATOR_PARA_DTL set content = content ||
'    union all    
    select    
        nvl(sum(decode(n.PS,''C1'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
       +nvl(sum(decode(n.PS,''C2'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
       +nvl(sum(decode(n.PS,''C3'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
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
)) dataTwo'
where para_code = 'ZCZHJQ' and para_granularity = '04';



update RSK_ISUP_INDICATOR_PARA_DTL set content = 
'select
 sum(nvl(targetData.remainPeriod, 0) * nvl(targetData.jiaQuanGuiZe, 0)/dataTwo.amount) as RVALUE
 from
 (
 select
   case when B.FINPROD_TYPE2 = ''F01'' and D.ENCASH_YEARS is not null then D.ENCASH_YEARS
    when B.FINPROD_TYPE2 = ''F07'' then (B.MDATE - date ''${valDate}'') / 365
    when E.FINPROD_TYPE2 in (''F03'', ''F05'', ''F09'') then (E.MDATE - date ''${valDate}'') / 365
    when B.FINPROD_TYPE2 = ''F01'' and C.TYPE_6 in (''B03'', ''B04'') then (B.MDATE - date ''${valDate}'') / 365
    when B.FINPROD_TYPE2 = ''F01'' and B.IS_RIGHT = ''Y'' 
        then ((SELECT MIN(FEP.EXERCISE_DATE) 
                FROM FIN_EXERCISE_PLAN FEP 
                WHERE FEP.FINPROD_ID = A.FINPROD_ID AND FEP.FINPROD_TYPE2 = ''F01'' AND FEP.EXERCISE_DATE >= date ''${valDate}'')
                - date ''${valDate}'') / 365
     when B.FINPROD_TYPE2 = ''F01'' then (B.MDATE - date ''${valDate}'') / 365
     when B.FINPROD_TYPE2 = ''F18'' then (B.MDATE - date ''${valDate}'') / 365
     else 0
   end as remainPeriod,
   case when B.FINPROD_TYPE2 = ''F07'' then A.AMOUNT + nvl(PSV.TDY_INTINCEXP, 0)
     when E.FINPROD_TYPE2 in (''F03'', ''F05'') then E.PRIN + nvl(PSV.TDY_INTINCEXP, 0)
     else nvl(PSV.DSC_COST_AMT, 0) + nvl(PSV.TDY_INTINCEXP, 0)
   end as jiaQuanGuiZe
 from PTL_SEC_POSITION A
  left join FIN_PRODUCT B on A.FINPROD_ID = B.FINPROD_ID and B.FINPROD_TYPE2 != ''F16''
  left join FIN_PRODUCT_TYPE C on A.FINPROD_ID = C.FINPROD_ID
  left join INTERF_PAYHSRCCBVLT D on decode(B.TRADE_MARKET, ''01'', ''IB'', ''03'', ''SH'', ''04'', ''SZ'', '''') || B.FINPROD_MARKET_ID = D.SECID
     || decode(B.TRADE_MARKET, ''01'', ''.IB'', ''03'', ''.SH'', ''04'', ''.SZ'', '''')
     and D.CDATE = to_char(date ''${valDate}'', ''yyyy-MM-dd'')
  left join FIN_TRADE_PRODUCT E on A.FINPROD_ID = E.FINPROD_ID and E.BRANCH = 0
  left join PTL_SEC_VALUTION PSV ON A.FINPROD_ID = PSV.FINPROD_ID AND A.PORTFOLIO_ID = PSV.PORTFOLIO_ID AND PSV.CDATE = date ''${valDate}''
 where A.PORTFOLIO_ID in ${portfolioCode} and A.CDATE = date ''${valDate}'' and A.AMOUNT > 0 and A.HODING_TYPE = ''03''
 and (E.FINPROD_TYPE2 is null or E.FINPROD_TYPE2  not in (''F02'', ''F04'', ''F08'', ''F25''))
 
 union all
 
 select
  1/365 as remainPeriod,
  A.AMOUNT as jiaQuanGuiZe
 from PTL_CASH_POSITION A
where A.PORTFOLIO_ID in ${portfolioCode} and A.CDATE = date ''${valDate}'' and A.AMOUNT > 0 and A.HODING_TYPE = ''03''
'
where para_code = 'ZCZHJQ' and para_granularity = '05';

update RSK_ISUP_INDICATOR_PARA_DTL set content = content ||
') targetData,
( 
    SELECT NVL(SUM(TDY_ASSET_NETVALUE),0) AS amount		
    FROM PTL_PORTFOLIO_VALUATION		
    WHERE PORTFOLIO_ID IN ${portfolioCode} AND CDATE = date''${valDate}''
) dataTwo'
where para_code = 'ZCZHJQ' and para_granularity = '05';
