----------------------------产品总资产--------------------------
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='CPZZC' and PARA_GRANULARITY='04' AND PARA_DISPLAY_TYPE='SQL';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('CPZZC', '04', 'SQL', 'select sum(amount) AS amount from			
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
       WHERE  t1.PS in (''C1'', ''C2'')	  and t1.SETTLE_DATE <= date''${valDate}'' AND t1.TRADE_STATUS=''01''		
       group by t1.PORTFOLIO_ID			
       ) mujiData			
       on t2.PORTFOLIO_ID = mujiData.PORTFOLIO_ID			
       where  t2.VDATE=date''${valDate}''	and t2.PORTFOLIO_ID in ${portfolioCode}		
   UNION ALL			
      SELECT 			
       DISTINCT t1.PORTFOLIO_ID RKEY,			
        nvl(TDY_ASSET_VALUE,0) as RVALUE			
       from PTL_PORTFOLIO_VALUATION t1			
       where  t1.CDATE=date''${valDate}''-1 	and t1.PORTFOLIO_ID in ${portfolioCode}		
   )			
  union all			
    select			
     nvl(sum(decode(n.PS,''C1'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
  +nvl(sum(decode(n.PS,''C2'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
  +nvl(sum(decode(n.PS,''C3'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
  +nvl(sum(decode(n.PS,''C9'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
  -nvl(sum(decode(n.PS,''C4'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
  -nvl(sum(decode(n.PS,''C6'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	-nvl(sum(decode(n.PS,''C11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
	-nvl(sum(decode(n.PS,''C12'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
	-nvl(sum(decode(n.PS,''C17'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
	-nvl(sum(decode(n.PS,''C20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as amount		
    from TRD_PRODUCT_DEAL_ADD m			
    inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID			
    where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' group by m.PORTFOLIO_ID			
   ', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
---------过长截断
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' union ALL			
    select nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0)			
    + nvl(sum(decode(n.PS,''T98'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0)			
    - nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0)			
    - nvl(sum(decode(n.PS,''T56'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0)			
    - nvl(sum(decode(n.PS,''T99'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0)			
    - nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0) as TRADE_AMT			
    from TRD_PRODUCT_DEAL_ADD m			
    inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 in (''F02'',''F08'',''F25'',''F04'')			
    where  m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T07'',''T54'',''T56'',''T98'',''T99'') 			
 			
 union all			
    select			
     nvl(sum(decode(n.PS,''C1'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
  +nvl(sum(decode(n.PS,''C2'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
  +nvl(sum(decode(n.PS,''C3'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
  +nvl(sum(decode(n.PS,''C9'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
  -nvl(sum(decode(n.PS,''C4'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
  -nvl(sum(decode(n.PS,''C6'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)			
	-nvl(sum(decode(n.PS,''C11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
	-nvl(sum(decode(n.PS,''C12'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
	-nvl(sum(decode(n.PS,''C17'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
	-nvl(sum(decode(n.PS,''C20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as amount		
    from WFL_TRD_PRODUCT_DEAL_ADD m			
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''			
    where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' group by m.PORTFOLIO_ID			
   union ALL			
    select nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0)			
    + nvl(sum(decode(n.PS,''T98'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0)			
    - nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0)			
    - nvl(sum(decode(n.PS,''T56'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0)			
    - nvl(sum(decode(n.PS,''T99'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0)			
    - nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))), 0) as TRADE_AMT			
    from WFL_TRD_PRODUCT_DEAL_ADD m			
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 in (''F02'',''F08'',''F25'',''F04'') and n.TRADE_STATUS != ''03''			
    where  m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T07'',''T54'',''T56'',''T98'',''T99'') 			
)					
'  where PARA_CODE = 'CPZZC' and PARA_GRANULARITY='04';

---------------------------债权类资产持仓----------------------------------
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='CPZCLZCGM' and PARA_GRANULARITY='04' AND PARA_DISPLAY_TYPE='SQL';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('CPZCLZCGM', '04', 'SQL', 'select sum(TRADE_AMT) as tradeAmt from		
 (		
select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)) as TRADE_AMT from PTL_SEC_VALUTION m		
  INNER JOIN FIN_PRODUCT_ADD PFP		
  on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''		
where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}''-1 	 AND m.PORTFOLIO_ID!=m.FINPROD_ID	
union all		
select		
  sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)) as  TRADE_AMT from PTL_SEC_VALUTION m		
INNER JOIN FIN_TRADE_PRODUCT n on m.FINPROD_ID = n.FINPROD_ID and n.FINPROD_TYPE2 in (''F03'',''F05'',''F09'') AND n.BRANCH=0		
where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}''-1		
union ALL		
  select		
  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT		
  from  TRD_PRODUCT_DEAL n		
  INNER JOIN FIN_TRADE_PRODUCT t2 on t2.FINPROD_ID=n.FINPROD_ID and t2.FINPROD_TYPE2 in (''F03'',''F05'',''F09'')	AND t2.BRANCH=0	
  LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID		
  where n1.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',
  ''T19'',''T20'',''T21'',''T38'',''T39'',''T52'',''T54'')		
', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
---------过长截断
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' union ALL		
  SELECT		
     nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT	
  from TRD_PRODUCT_DEAL n		
    INNER JOIN FIN_PRODUCT_ADD PFP		
   on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''		
   LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID		
  where n1.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',
  ''T19'',''T20'',''T21'',''T38'',''T39'',''T52'',''T54'')		
   union ALL 		
   select 		
 nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT		
  '  where PARA_CODE = 'CPZCLZCGM' and PARA_GRANULARITY='04';
---------过长截断
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||' from WFL_TRD_PRODUCT_DEAL_ADD m		
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''		
  INNER JOIN FIN_TRADE_PRODUCT t2 on t2.FINPROD_ID=n.FINPROD_ID and t2.FINPROD_TYPE2 in (''F03'',''F05'',''F09'')	AND t2.BRANCH=0	
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',
  ''T19'',''T20'',''T21'',''T38'',''T39'',''T52'',''T54'')  		
  union ALL 		
   select 		
  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT		
  from WFL_TRD_PRODUCT_DEAL_ADD m		
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''		
  INNER JOIN FIN_PRODUCT_ADD PFP on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''		
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',
  ''T19'',''T20'',''T21'',''T38'',''T39'',''T52'',''T54'') 		
)'  where PARA_CODE = 'CPZCLZCGM' and PARA_GRANULARITY='04';

-----------------------------权益类资产持仓--------------------------------
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='QYLZCGM' and PARA_GRANULARITY='04' AND PARA_DISPLAY_TYPE='SQL';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('QYLZCGM', '04', 'SQL', 'select sum(TRADE_AMT) as tradeAmt from	
 (	
select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as TRADE_AMT from PTL_SEC_VALUTION m	
  INNER JOIN FIN_PRODUCT_ADD PFP	
  on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''02''	
where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}''-1 	AND m.PORTFOLIO_ID!=m.FINPROD_ID
union ALL	
  select	
    nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T46'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T47'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T48'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 	
  -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 	
  -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT	
  from TRD_PRODUCT_DEAL n	
    INNER JOIN FIN_PRODUCT_ADD PFP	
   on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''02''	
     LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID	
  where n1.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T19''	
  ,''T20'',''T21'',''T38'',''T39'',''T46'',''T47'',''T48'',''T52'',''T54'')	
  ', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
---------过长截断
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||'  union ALL 	
   select 	
  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T46'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T47'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T48'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 	
  -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 	
  -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT	
  from WFL_TRD_PRODUCT_DEAL_ADD m	
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''	
   INNER JOIN FIN_PRODUCT_ADD PFP  on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''02''	
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T19''	
  ,''T20'',''T21'',''T38'',''T39'',''T46'',''T47'',''T48'',''T52'',''T54'') 	
)'  where PARA_CODE = 'CPZZC' and PARA_GRANULARITY='04';

-----------------------------商品及衍生品资产持仓---------------------------
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='SPJJRYSPLZCGM' and PARA_GRANULARITY='04' AND PARA_DISPLAY_TYPE='SQL';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('SPJJRYSPLZCGM', '04', 'SQL', 'select sum(TRADE_AMT) as tradeAmt from	
 (	
select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as TRADE_AMT from PTL_SEC_VALUTION m	
  INNER JOIN FIN_PRODUCT_ADD PFP	
  on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''03''	
where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}''-1 	AND m.PORTFOLIO_ID!=m.FINPROD_ID
union ALL	
select	
 nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T59'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T60'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T61'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT	
  from  TRD_PRODUCT_DEAL n	
  inner join FIN_PRODUCT_ADD FPA on n.FINPROD_ID = FPA.FINPROD_ID  and FPA.INVEST_NATURE =''03''	
  INNER JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID	
  where n1.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T20'',''T21''	
  ,''T52'',''T59'',''T60'',''T61'')	
   union ALL 	
   select 	
  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T59'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T60'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T61'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT	
  from WFL_TRD_PRODUCT_DEAL_ADD m	
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''	
  INNER JOIN FIN_PRODUCT_ADD PFP on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''03''	 
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T20'',''T21''	
  ,''T52'',''T59'',''T60'',''T61'')  	
)', 'portfolioCode', 'Y', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);