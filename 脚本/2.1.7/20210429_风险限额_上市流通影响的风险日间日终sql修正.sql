--单一资产持仓规模：DYZCGM
--产品所投资资产单只证券或单只公募证券投资基金规模:GMZDDDZZQHJJ
declare
	sql01 clob:= 'select
sum(nvl(targetData.VALUE, 0)) as RVALUE, targetData.ASSETCODE AS RKEY, targetData.ASSETNAME AS RKEYCN
from
(
  select nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0) as VALUE,
       t1.FINPROD_ID as ASSETCODE,
       t2.FINPROD_NAME as ASSETNAME
  from PTL_SEC_VALUTION t1
  left join FIN_PRODUCT t2
    on t1.FINPROD_ID = t2.FINPROD_ID
  left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH = 0) t3
    on t1.FINPROD_ID = t3.FINPROD_ID
 where t1.PORTFOLIO_ID in ${portfolioCode}
   and t1.CDATE = date ''${valDate}'' - 1
   AND t1.PORTFOLIO_ID != t1.FINPROD_ID
   and ((t3.FINPROD_TYPE is null and t2.FINPROD_TYPE2 is not null) or
       (t3.FINPROD_TYPE2 not in
       (''F02'', ''F04'', ''F08'', ''F11'', ''F12'', ''F13'', ''F14'', ''F15'', ''F25'') AND
       t3.BRANCH = 0))
union all
  select nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0) as VALUE,
       ''F01_''||FRD.APPEAR_MARKET_ID as ASSETCODE,
       t2.FINPROD_NAME as ASSETNAME
  from PTL_SEC_VALUTION t1
  inner join FIN_PRODUCT t2
    on t1.FINPROD_ID = t2.FINPROD_ID
  inner join FIN_PRODUCT_ADD FRD
    ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL  AND FRD.IS_ISSUE_ASSET = ''Y''
 where t1.PORTFOLIO_ID in ${portfolioCode}
   and t1.CDATE = date ''${valDate}'' - 1
   AND t1.PORTFOLIO_ID != t1.FINPROD_ID
   and t2.FINPROD_TYPE2 =''F01''  
  union all
    select -(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)) as VALUE,
       t2.FINPROD_ID as ASSETCODE,
       t2.FINPROD_NAME as ASSETNAME
  from PTL_SEC_VALUTION t1
  inner join FIN_PRODUCT t2
    on t1.FINPROD_ID = t2.FINPROD_ID
  inner join FIN_PRODUCT_ADD FRD
    ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL  AND FRD.IS_ISSUE_ASSET = ''Y''
 where t1.PORTFOLIO_ID in ${portfolioCode}
   and t1.CDATE = date ''${valDate}'' - 1
   AND t1.PORTFOLIO_ID != t1.FINPROD_ID
   and t2.FINPROD_TYPE2 =''F01''  
  union all
  select
  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
  from TRD_PRODUCT_DEAL n
    left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID
 left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3 on n.FINPROD_ID = t3.FINPROD_ID
   LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
  where n1.PORTFOLIO_ID in ${portfolioCode} and  n.TRADE_DATE = date''${valDate}''
  and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'', ''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
  and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T52'') group by n.FINPROD_ID, t2.FINPROD_NAME
        union ALL
   select
 nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
  from WFL_TRD_PRODUCT_DEAL_ADD m
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
  left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID
 left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3 on n.FINPROD_ID = t3.FINPROD_ID
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}''
   and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'', ''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
  and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T52'')
  group by n.FINPROD_ID, t2.FINPROD_NAME  union all
   select
   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as TRADE_AMT,n.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
  from TRD_PRODUCT_DEAL n
  inner join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID
  inner join FIN_PRODUCT_ADD FRD  ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL  AND FRD.IS_ISSUE_ASSET = ''Y''
  LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
  where n1.PORTFOLIO_ID in ${portfolioCode} and  n.TRADE_DATE = date''${valDate}''
  and  t2.FINPROD_TYPE2=''F01'' group by n.FINPROD_ID, t2.FINPROD_NAME
  union ALL
   select
   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
  from WFL_TRD_PRODUCT_DEAL_ADD m
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
  inner join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID
  inner join FIN_PRODUCT_ADD FRD  ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL  AND FRD.IS_ISSUE_ASSET = ''Y''
  LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
  where n1.PORTFOLIO_ID in ${portfolioCode} and  n.TRADE_DATE = date''${valDate}''
  and  t2.FINPROD_TYPE2=''F01'' group by n.FINPROD_ID, t2.FINPROD_NAME
) targetData
group by targetData.ASSETCODE, targetData.ASSETNAME';
	sql02 clob:='select
sum(nvl(targetData.VALUE, 0)) as RVALUE, targetData.ASSETCODE RKEY, targetData.ASSETNAME RKEYCN
from
(
select
 nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0) as VALUE, t1.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
from PTL_SEC_VALUTION t1
 left join FIN_PRODUCT t2
  on t1.FINPROD_ID = t2.FINPROD_ID
  left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3
  on t1.FINPROD_ID = t3.FINPROD_ID
where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''
and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'',''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
union all 	   
  select nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0) as VALUE,
       ''F01_''||FRD.APPEAR_MARKET_ID as ASSETCODE,
       t2.FINPROD_NAME as ASSETNAME
  from PTL_SEC_VALUTION t1
  inner join FIN_PRODUCT t2
    on t1.FINPROD_ID = t2.FINPROD_ID 
  inner join FIN_PRODUCT_ADD FRD
    ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL  AND FRD.IS_ISSUE_ASSET = ''Y'' 
 where t1.PORTFOLIO_ID in ${portfolioCode}
   and t1.CDATE = date ''${valDate}''  
   AND t1.PORTFOLIO_ID != t1.FINPROD_ID
   and t2.FINPROD_TYPE2 =''F01''
union all 	   
  select -(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)) as VALUE,
       T2.FINPROD_ID as ASSETCODE,
       t2.FINPROD_NAME as ASSETNAME
  from PTL_SEC_VALUTION t1
  inner join FIN_PRODUCT t2
    on t1.FINPROD_ID = t2.FINPROD_ID 
  inner join FIN_PRODUCT_ADD FRD
    ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL  AND FRD.IS_ISSUE_ASSET = ''Y'' 
 where t1.PORTFOLIO_ID in ${portfolioCode}
   and t1.CDATE = date ''${valDate}''  
   AND t1.PORTFOLIO_ID != t1.FINPROD_ID
   and t2.FINPROD_TYPE2 =''F01''
) targetData
group by targetData.ASSETCODE, targetData.ASSETNAME';
	sql03 clob:='select nvl(sum(nvl(RVALUE, 0)),0) as RVALUE,s1.FINPROD_ID as RKEY,s1.FINPROD_NAME RKEYCN from   
 (   
 select m.FINPROD_ID,
        n.FINPROD_NAME,
        nvl(sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) +
                nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)),
            0) as RVALUE
   from PTL_SEC_VALUTION m
  INNER JOIN FIN_PRODUCT n
     on m.FINPROD_ID = n.FINPROD_ID
    and n.FINPROD_TYPE2 IN (''F01'', ''F06'', ''F10'')
  where m.PORTFOLIO_ID in ${portfolioCode}
    and m.CDATE = date ''${valDate}'' - 1
  group by m.FINPROD_ID, n.FINPROD_NAME
union all	   
  select  ''F01_''||FRD.APPEAR_MARKET_ID as FINPROD_ID,
        t2.FINPROD_NAME as FINPROD_NAME, 
        nvl(sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)),0) as RVALUE   
  from PTL_SEC_VALUTION t1
  inner join FIN_PRODUCT t2
    on t1.FINPROD_ID = t2.FINPROD_ID 
  inner join FIN_PRODUCT_ADD FRD ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
 where t1.PORTFOLIO_ID in ${portfolioCode}
   and t1.CDATE = date ''${valDate}'' - 1 
   and t2.FINPROD_TYPE2 =''F01''
 group by FRD.APPEAR_MARKET_ID, t2.FINPROD_NAME  
 union ALL
  select t2.FINPROD_ID as FINPROD_ID,
        t2.FINPROD_NAME as FINPROD_NAME, 
        -nvl(sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)),0) as RVALUE   
  from PTL_SEC_VALUTION t1
  inner join FIN_PRODUCT t2
    on t1.FINPROD_ID = t2.FINPROD_ID 
  inner join FIN_PRODUCT_ADD FRD ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
 where t1.PORTFOLIO_ID in ${portfolioCode}
   and t1.CDATE = date ''${valDate}'' - 1 
   and t2.FINPROD_TYPE2 =''F01''
 group by FRD.APPEAR_MARKET_ID, t2.FINPROD_NAME  
 union ALL   
    select n.FINPROD_ID ,m.FINPROD_NAME, 
     nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 
    +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  as  TRADE_AMT   
    from  TRD_PRODUCT_DEAL n 
    INNER JOIN FIN_PRODUCT m on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 IN (''F01'',''F06'',''F10'') 
    LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID 
    where  n1.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'') 
    group by n.FINPROD_ID,m.FINPROD_NAME 
    union ALL    
    select  n.FINPROD_ID ,s.FINPROD_NAME, 
     nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 
    +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  as  TRADE_AMT   
  from WFL_TRD_PRODUCT_DEAL_ADD m   
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''   
  INNER  JOIN FIN_PRODUCT s on n.FINPROD_ID=s.FINPROD_ID and s.FINPROD_TYPE2 IN (''F01'',''F06'',''F10'')   
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'')    
  group by n.FINPROD_ID,s.FINPROD_NAME 
  union ALL   
  select n.FINPROD_ID ,m.FINPROD_NAME, 
   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 	
	-nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
	-nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
	-nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
	-nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
	-nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
	-nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
	-nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
	-nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
	-nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
	-nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  as  TRADE_AMT   
    from  TRD_PRODUCT_DEAL n 
    INNER JOIN FIN_PRODUCT m on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 = ''F01''
	INNER JOIN FIN_PRODUCT_ADD FRD ON m.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL  AND FRD.IS_ISSUE_ASSET = ''Y'' 
    LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID  
    where  n1.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'') 
    group by n.FINPROD_ID,m.FINPROD_NAME 
    union ALL    
    select n.FINPROD_ID ,s.FINPROD_NAME, 
     nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 
    +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)  as  TRADE_AMT   
  from WFL_TRD_PRODUCT_DEAL_ADD m   
  INNER JOIN WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''   
  INNER JOIN FIN_PRODUCT s on n.FINPROD_ID=s.FINPROD_ID and s.FINPROD_TYPE2 = ''F01'' 
  INNER JOIN FIN_PRODUCT_ADD FRD ON s.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL  AND FRD.IS_ISSUE_ASSET = ''Y'' 
  INNER JOIN TRD_PRODUCT_DEAL_ADD n1 ON m.TRADE_ID =n1.TRADE_ID  
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'')    
  group by n.FINPROD_ID,s.FINPROD_NAME    
 ) s1  group by s1.FINPROD_ID,s1.FINPROD_NAME ';
	sql04 clob:='SELECT nvl(sum(nvl(RVALUE, 0)),0) as RVALUE,s1.FINPROD_ID as RKEY,s1.FINPROD_NAME RKEYCN FROM (	
   select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)),0) as RVALUE,m.FINPROD_ID ,n.FINPROD_NAME  from PTL_SEC_VALUTION m	
 INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 IN (''F01'',''F06'',''F10'')	
 where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}'' group by m.FINPROD_ID,n.FINPROD_NAME	
 union all 
 select nvl(sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)),0) as RVALUE, 
 	''F01_''||FRD.APPEAR_MARKET_ID as FINPROD_ID,
    t2.FINPROD_NAME as FINPROD_NAME
 from PTL_SEC_VALUTION t1
 inner join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID  and t2.FINPROD_TYPE2 =''F01'' 
 inner join FIN_PRODUCT_ADD FRD ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
 where t1.CDATE = date ''${valDate}''  
   AND t1.PORTFOLIO_ID in ${portfolioCode} 
   group by FRD.APPEAR_MARKET_ID,t2.FINPROD_NAME
  union all 
 select -nvl(sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)),0) as RVALUE, 
 	 t2.FINPROD_ID as FINPROD_ID,
    t2.FINPROD_NAME as FINPROD_NAME
 from PTL_SEC_VALUTION t1
 inner join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID  and t2.FINPROD_TYPE2 =''F01'' 
 inner join FIN_PRODUCT_ADD FRD ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
 where t1.CDATE = date ''${valDate}''  
   AND t1.PORTFOLIO_ID in ${portfolioCode} 
   group by FRD.APPEAR_MARKET_ID,t2.FINPROD_NAME
 ) s1 group by s1.FINPROD_ID,s1.FINPROD_NAME';
begin
	update rsk_isup_indicator_para_dtl set content=sql01 where para_code ='DYZCGM' and para_granularity = '04';
	update rsk_isup_indicator_para_dtl set content=sql02 where para_code ='DYZCGM' and para_granularity = '05';
	update rsk_isup_indicator_para_dtl set content=sql03 where para_code ='GMZDDDZZQHJJ' and para_granularity = '04';
	update rsk_isup_indicator_para_dtl set content=sql04 where para_code ='GMZDDDZZQHJJ' and para_granularity = '05';
end;
/
