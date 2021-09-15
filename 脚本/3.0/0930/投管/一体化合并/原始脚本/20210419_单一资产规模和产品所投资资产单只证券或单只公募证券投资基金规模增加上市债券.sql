DELETE FROM  RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE IN('DYZCGM','GMZDDDZZQHJJ');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('DYZCGM', '01', 'SQL', 'select
 sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, t1.PORTFOLIO_ID||'',''||t1.FINPROD_ID as RKEY
from PTL_SEC_VALUTION t1
 left join FIN_PRODUCT t2
  on t1.FINPROD_ID = t2.FINPROD_ID
left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3
  on t1.FINPROD_ID = t3.FINPROD_ID
where  t1.CDATE = date''${valDate}''-1  AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'', ''F04'', ''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
group by t1.PORTFOLIO_ID,t1.FINPROD_ID,t2.FINPROD_NAME
union all	   
select sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)) as RVALUE,
       t1.PORTFOLIO_ID||'',''|| ''F01_''||FRD.APPEAR_MARKET_ID as RKEY
  from PTL_SEC_VALUTION t1
  inner join FIN_PRODUCT t2
    on t1.FINPROD_ID = t2.FINPROD_ID 
  inner join FIN_PRODUCT_ADD FRD
    ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
 where t1.CDATE = date ''${valDate}'' - 1
   AND t1.PORTFOLIO_ID != t1.FINPROD_ID
   and t2.FINPROD_TYPE2 =''F01'' 
   group by t1.PORTFOLIO_ID,FRD.APPEAR_MARKET_ID,t2.FINPROD_NAME ', 'portfolioCode,assetCode', 'N', 'wc', '001', TIMESTAMP '2021-04-19 14:23:32', 'jl1', TIMESTAMP '2021-04-19 14:24:03'); 
---------过长截断
 update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' union all
  select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY
 from (select t1.*
from PTL_SEC_VALUTION t1
 left join FIN_PRODUCT t2
  on t1.FINPROD_ID = t2.FINPROD_ID
left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3
  on t1.FINPROD_ID = t3.FINPROD_ID
where  t1.CDATE = date''${valDate}''-1   AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'',  ''F04'', ''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'')
s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID
union all  
select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY
from (select t1.*
from PTL_SEC_VALUTION t1
inner join FIN_PRODUCT t2
  on t1.FINPROD_ID = t2.FINPROD_ID
inner join FIN_PRODUCT_ADD FRD
    ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
where t1.CDATE = date''${valDate}''-1   AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
and t2.FINPROD_TYPE2 =''F01''
) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'')
s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID'  where PARA_CODE = 'DYZCGM' and PARA_GRANULARITY='01';

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('DYZCGM', '02', 'SQL', 'select
 sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, t1.PORTFOLIO_ID||'',''||t1.FINPROD_ID as RKEY
from PTL_SEC_VALUTION t1
 left join FIN_PRODUCT t2
  on t1.FINPROD_ID = t2.FINPROD_ID
left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3
  on t1.FINPROD_ID = t3.FINPROD_ID
where  t1.CDATE = date''${valDate}''-1  AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'', ''F04'', ''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
group by t1.PORTFOLIO_ID,t1.FINPROD_ID,t2.FINPROD_NAME
union all	   
select sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)) as RVALUE,
       t1.PORTFOLIO_ID||'',''|| ''F01_''||FRD.APPEAR_MARKET_ID as RKEY
  from PTL_SEC_VALUTION t1
  inner join FIN_PRODUCT t2
    on t1.FINPROD_ID = t2.FINPROD_ID 
  inner join FIN_PRODUCT_ADD FRD
    ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
 where t1.CDATE = date ''${valDate}'' - 1
   AND t1.PORTFOLIO_ID != t1.FINPROD_ID
   and t2.FINPROD_TYPE2 =''F01'' 
   group by t1.PORTFOLIO_ID,FRD.APPEAR_MARKET_ID,t2.FINPROD_NAME ', 'portfolioCode,assetCode', 'N', 'wc', '001', TIMESTAMP '2021-04-19 14:23:32', 'jl1', TIMESTAMP '2021-04-19 14:24:03');
 ---------过长截断  
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' union all
  select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY
 from (select t1.*
from PTL_SEC_VALUTION t1
 left join FIN_PRODUCT t2
  on t1.FINPROD_ID = t2.FINPROD_ID
left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3
  on t1.FINPROD_ID = t3.FINPROD_ID
where  t1.CDATE = date''${valDate}''-1   AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'',  ''F04'', ''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'')
s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID
union all  
select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY
from (select t1.*
from PTL_SEC_VALUTION t1
inner join FIN_PRODUCT t2
  on t1.FINPROD_ID = t2.FINPROD_ID
inner join FIN_PRODUCT_ADD FRD
    ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
where t1.CDATE = date''${valDate}''-1   AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
and t2.FINPROD_TYPE2 =''F01''
) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'')
s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID'  where PARA_CODE = 'DYZCGM' and PARA_GRANULARITY='02';

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('DYZCGM', '03', 'SQL', 'select
 sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, t1.PORTFOLIO_ID||'',''||t1.FINPROD_ID as RKEY
from PTL_SEC_VALUTION t1
 left join FIN_PRODUCT t2
  on t1.FINPROD_ID = t2.FINPROD_ID
left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3
  on t1.FINPROD_ID = t3.FINPROD_ID
where  t1.CDATE = date''${valDate}''-1  AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'', ''F04'', ''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
group by t1.PORTFOLIO_ID,t1.FINPROD_ID,t2.FINPROD_NAME
union all	   
select sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)) as RVALUE,
       t1.PORTFOLIO_ID||'',''|| ''F01_''||FRD.APPEAR_MARKET_ID as RKEY
  from PTL_SEC_VALUTION t1
  inner join FIN_PRODUCT t2
    on t1.FINPROD_ID = t2.FINPROD_ID 
  inner join FIN_PRODUCT_ADD FRD
    ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
 where t1.CDATE = date ''${valDate}'' - 1
   AND t1.PORTFOLIO_ID != t1.FINPROD_ID
   and t2.FINPROD_TYPE2 =''F01'' 
   group by t1.PORTFOLIO_ID,FRD.APPEAR_MARKET_ID,t2.FINPROD_NAME ', 'portfolioCode,assetCode', 'N', 'wc', '001', TIMESTAMP '2021-04-19 14:23:32', 'jl1', TIMESTAMP '2021-04-19 14:24:03');
---------过长截断  
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' union all
  select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY
 from (select t1.*
from PTL_SEC_VALUTION t1
 left join FIN_PRODUCT t2
  on t1.FINPROD_ID = t2.FINPROD_ID
left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3
  on t1.FINPROD_ID = t3.FINPROD_ID
where  t1.CDATE = date''${valDate}''-1   AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'',  ''F04'', ''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'')
s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID
union all  
select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY
from (select t1.*
from PTL_SEC_VALUTION t1
inner join FIN_PRODUCT t2
  on t1.FINPROD_ID = t2.FINPROD_ID
inner join FIN_PRODUCT_ADD FRD
    ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
where t1.CDATE = date''${valDate}''-1   AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
and t2.FINPROD_TYPE2 =''F01''
) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'')
s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID'  where PARA_CODE = 'DYZCGM' and PARA_GRANULARITY='03';

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('DYZCGM', '04', 'SQL', 'select
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
   and t2.FINPROD_TYPE2 =''F01'' ', 'portfolioCode,assetCode', 'N', 'wc', '001', TIMESTAMP '2021-04-19 15:40:40', 'jl1', TIMESTAMP '2021-04-19 15:41:06');
---------过长截断  
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' union all
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
  group by n.FINPROD_ID, t2.FINPROD_NAME '  where PARA_CODE = 'DYZCGM' and PARA_GRANULARITY='04';
---------过长截断  
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' union all 
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
group by targetData.ASSETCODE, targetData.ASSETNAME '  where PARA_CODE = 'DYZCGM' and PARA_GRANULARITY='04';

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('DYZCGM', '05', 'SQL', 'select
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
) targetData
group by targetData.ASSETCODE, targetData.ASSETNAME', 'portfolioCode,assetCode', 'N', 'wc', '001', TIMESTAMP '2021-04-19 14:23:32', 'jl1', TIMESTAMP '2021-04-19 14:24:03');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('GMZDDDZZQHJJ', '01', 'SQL', 'SELECT nvl(sum(nvl(RVALUE, 0)),0) as RVALUE,s1.RKEY  FROM (	 
	select 
	sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, t1.PORTFOLIO_ID||'',''||t1.FINPROD_ID as RKEY 
	from PTL_SEC_VALUTION t1 
	inner join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  IN (''F01'',''F06'',''F10'') 
	where  t1.CDATE = date''${valDate}''-1 
	group by t1.PORTFOLIO_ID,t1.FINPROD_ID,t2.FINPROD_NAME 
	union all	   
	select sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)) as RVALUE, 
		 t1.PORTFOLIO_ID||'',''||''F01_''||FRD.APPEAR_MARKET_ID as RKEY 
	from PTL_SEC_VALUTION t1
	inner join FIN_PRODUCT t2
	  on t1.FINPROD_ID = t2.FINPROD_ID 
	inner join FIN_PRODUCT_ADD FRD
	  ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
	where t1.CDATE = date ''${valDate}'' - 1
	  AND t1.PORTFOLIO_ID != t1.FINPROD_ID
	  and t2.FINPROD_TYPE2 =''F01'' 
	  group by t1.PORTFOLIO_ID,FRD.APPEAR_MARKET_ID,t2.FINPROD_NAME ', 'portfolioCode,assetCode', 'N', 'wc', '001', TIMESTAMP '2021-04-19 14:35:01', 'jl1', TIMESTAMP '2021-04-19 14:36:04'); 
---------过长截断  
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' UNION ALL 
	select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY 
	from (select t1.* 
	from PTL_SEC_VALUTION t1 
	inner join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  IN (''F01'',''F06'',''F10'') 
	where  t1.CDATE = date''${valDate}''-1 
	) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' ) 
	s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID 
	UNION ALL 
	select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY 
	from (select t1.* 
	from PTL_SEC_VALUTION t1 
	INNER JOIN FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  = ''F01''
	INNER JOIN FIN_PRODUCT_ADD FRD ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
	where  t1.CDATE = date''${valDate}''-1 
	) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' ) 
	s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID 
	) s1 group by s1.RKEY '  where PARA_CODE = 'GMZDDDZZQHJJ' and PARA_GRANULARITY='01';
	
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('GMZDDDZZQHJJ', '02', 'SQL', 'SELECT nvl(sum(nvl(RVALUE, 0)),0) as RVALUE,s1.RKEY  FROM (	 
	select 
	sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, t1.PORTFOLIO_ID||'',''||t1.FINPROD_ID as RKEY 
	from PTL_SEC_VALUTION t1 
	inner join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  IN (''F01'',''F06'',''F10'') 
	where  t1.CDATE = date''${valDate}''-1 
	group by t1.PORTFOLIO_ID,t1.FINPROD_ID,t2.FINPROD_NAME 
	union all	   
	select sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)) as RVALUE, 
		 t1.PORTFOLIO_ID||'',''||''F01_''||FRD.APPEAR_MARKET_ID as RKEY 
	from PTL_SEC_VALUTION t1
	inner join FIN_PRODUCT t2
	  on t1.FINPROD_ID = t2.FINPROD_ID 
	inner join FIN_PRODUCT_ADD FRD
	  ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
	where t1.CDATE = date ''${valDate}'' - 1
	  AND t1.PORTFOLIO_ID != t1.FINPROD_ID
	  and t2.FINPROD_TYPE2 =''F01'' 
	  group by t1.PORTFOLIO_ID,FRD.APPEAR_MARKET_ID,t2.FINPROD_NAME ', 'portfolioCode,assetCode', 'N', 'wc', '001', TIMESTAMP '2021-04-19 14:35:01', 'jl1', TIMESTAMP '2021-04-19 14:36:04');
	---------过长截断  
    update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' UNION ALL 
	select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY 
	from (select t1.* 
	from PTL_SEC_VALUTION t1 
	inner join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  IN (''F01'',''F06'',''F10'') 
	where  t1.CDATE = date''${valDate}''-1 
	) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' ) 
	s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID 
	UNION ALL 
	select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY 
	from (select t1.* 
	from PTL_SEC_VALUTION t1 
	INNER JOIN FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  = ''F01''
	INNER JOIN FIN_PRODUCT_ADD FRD ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
	where  t1.CDATE = date''${valDate}''-1 
	) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' ) 
	s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID 
	) s1 group by s1.RKEY '  where PARA_CODE = 'GMZDDDZZQHJJ' and PARA_GRANULARITY='02';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('GMZDDDZZQHJJ', '03', 'SQL', 'SELECT nvl(sum(nvl(RVALUE, 0)),0) as RVALUE,s1.RKEY  FROM (	 
	select 
	sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, t1.PORTFOLIO_ID||'',''||t1.FINPROD_ID as RKEY 
	from PTL_SEC_VALUTION t1 
	inner join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  IN (''F01'',''F06'',''F10'') 
	where  t1.CDATE = date''${valDate}''-1 
	group by t1.PORTFOLIO_ID,t1.FINPROD_ID,t2.FINPROD_NAME 
	union all	   
	select sum(nvl(DSC_COST_AMT, 0) + nvl(TDY_FLOAT_INGPL, 0) + nvl(TDY_INTINCEXP, 0) + nvl(DELAY_PAY_AMT, 0)) as RVALUE, 
		 t1.PORTFOLIO_ID||'',''||''F01_''||FRD.APPEAR_MARKET_ID as RKEY 
	from PTL_SEC_VALUTION t1
	inner join FIN_PRODUCT t2
	  on t1.FINPROD_ID = t2.FINPROD_ID 
	inner join FIN_PRODUCT_ADD FRD
	  ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
	where t1.CDATE = date ''${valDate}'' - 1
	  AND t1.PORTFOLIO_ID != t1.FINPROD_ID
	  and t2.FINPROD_TYPE2 =''F01'' 
	  group by t1.PORTFOLIO_ID,FRD.APPEAR_MARKET_ID,t2.FINPROD_NAME ', 'portfolioCode,assetCode', 'N', 'wc', '001', TIMESTAMP '2021-04-19 14:35:01', 'jl1', TIMESTAMP '2021-04-19 14:36:04');
	---------过长截断  
    update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' UNION ALL 
	select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY 
	from (select t1.* 
	from PTL_SEC_VALUTION t1 
	inner join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  IN (''F01'',''F06'',''F10'') 
	where  t1.CDATE = date''${valDate}''-1 
	) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' ) 
	s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID 
	UNION ALL 
	select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY 
	from (select t1.* 
	from PTL_SEC_VALUTION t1 
	INNER JOIN FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  = ''F01''
	INNER JOIN FIN_PRODUCT_ADD FRD ON T2.FINPROD_ID = FRD.FINPROD_ID  AND FRD.APPEAR_MARKET_ID IS NOT NULL AND FRD.IS_ISSUE_ASSET = ''Y'' 
	where  t1.CDATE = date''${valDate}''-1 
	) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' ) 
	s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID 
	) s1 group by s1.RKEY '  where PARA_CODE = 'GMZDDDZZQHJJ' and PARA_GRANULARITY='03';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('GMZDDDZZQHJJ', '04', 'SQL', 'select nvl(sum(nvl(RVALUE, 0)),0) as RVALUE,s1.FINPROD_ID as RKEY,s1.FINPROD_NAME RKEYCN from   
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
    select n.FINPROD_ID ,m.FINPROD_NAME, 
     nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 
    +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)   
    -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) ', 'portfolioCode,assetCode', 'N', 'wc', '001', TIMESTAMP '2021-04-19 14:35:01', 'jl1', TIMESTAMP '2021-04-19 14:36:04');  
 ---------过长截断  
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||'  
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
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) '  where PARA_CODE = 'GMZDDDZZQHJJ' and PARA_GRANULARITY='04';
  ---------过长截断  
    update RSK_ISUP_INDICATOR_PARA_DTL set content = content||'	
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
 ) s1  group by s1.FINPROD_ID,s1.FINPROD_NAME '  where PARA_CODE = 'GMZDDDZZQHJJ' and PARA_GRANULARITY='04';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('GMZDDDZZQHJJ', '05', 'SQL', 'SELECT nvl(sum(nvl(RVALUE, 0)),0) as RVALUE,s1.FINPROD_ID as RKEY,s1.FINPROD_NAME RKEYCN FROM (	
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
 ) s1 group by s1.FINPROD_ID,s1.FINPROD_NAME', 'portfolioCode,assetCode', 'N', 'wc', '001', TIMESTAMP '2021-04-19 14:35:01', 'jl1', TIMESTAMP '2021-04-19 14:36:04');
