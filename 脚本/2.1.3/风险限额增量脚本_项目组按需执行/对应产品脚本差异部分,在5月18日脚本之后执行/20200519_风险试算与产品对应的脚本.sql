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

delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='DYGLF' and PARA_GRANULARITY='04';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('DYGLF', '04', 'SQL', 'with temp as (select  m.FINPROD_ID,m.FINPROD_TYPE2, 		
case when m.FINPROD_TYPE2 = ''F01'' then m.ISSUER else m.FINANCIER end org_code 		
FROM		
    FIN_PRODUCT m		
INNER JOIN		
    FIN_PRODUCT_TYPE fpt ON  m.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0		
AND (  (fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR  ( fpt.TYPE_8 =''F01''  AND fpt.TYPE_6 =''B18'')))		
select sum(nvl(targetData.VALUE, 0)) as RVALUE, targetData.ASSETCODE AS RKEY, targetData.ASSETNAME	RKEYCN	 from
(		
	select  nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  VALUE,g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME from PTL_SEC_VALUTION t1	
	 inner join ( 	
	 select FINPROD_ID,org_code,org_code org_code_union from temp 	
	 union all 	
	 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a 	
	 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO	
	 inner join temp c on c.org_code = b.COR_ORGID	
	 ) g on  t1.FINPROD_ID=g.FINPROD_ID 	
	left join MST_CUSTOMER_INFO t3 on  g.org_code=t3.CUSTOMER_ID	
	where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE =date''${valDate}''-1 group by t1.PORTFOLIO_ID ,g.org_code,T3.CUSTOMER_NAME	
union ALL		
  select		
   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME  from TRD_PRODUCT_DEAL_ADD m		
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID		
 inner join ( 		
 select FINPROD_ID,org_code,org_code org_code_union from temp 		
 union all 		
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a 		
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO		
 inner join temp c on c.org_code = b.COR_ORGID		
 ) g on  n.FINPROD_ID=g.FINPROD_ID 		
  left join MST_CUSTOMER_INFO t3 on  g.org_code=t3.CUSTOMER_ID		
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T22'',''T23'',''T08'') group by m.PORTFOLIO_ID ,g.org_code,T3.CUSTOMER_NAME		
union ALL		
  select		
  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME  from WFL_TRD_PRODUCT_DEAL_ADD m		
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''		
  inner join ( 		
 select FINPROD_ID,org_code,org_code org_code_union from temp 		
 union all 		
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a 		
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO		
 inner join temp c on c.org_code = b.COR_ORGID		
 ) g on  n.FINPROD_ID=g.FINPROD_ID 		
  left join MST_CUSTOMER_INFO t3 on  g.org_code=t3.CUSTOMER_ID		
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T22'',''T23'',''T08'') 	group by m.PORTFOLIO_ID ,g.org_code,T3.CUSTOMER_NAME	
) targetData group by targetData.ASSETCODE, targetData.ASSETNAME', 'portfolioCode,issuerCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);

--利率债单笔交易净价
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='LLZDBJYJJ' and PARA_GRANULARITY IN ('04','05');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('LLZDBJYJJ', '04', 'SQL', 'SELECT DISTINCT n.UNIT_CPRICE as RVALUE ,n.FINPROD_ID RKEY 
  from TRD_PRODUCT_DEAL_ADD m 
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 =''F01'' AND n.PS IN (''T01'',''T02'',''T03'') 
  INNER JOIN FIN_PRODUCT_TYPE s ON s.FINPROD_ID=n.FINPROD_ID AND s.BRANCH=0 AND (s.TYPE_6 IN (''B12'',''B01'',''B02'',''B03'') OR (s.TYPE_6=''B05'' AND s.TYPE_7=''01'')) 
  where  m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' 
  UNION all 
  select DISTINCT n.UNIT_CPRICE as RVALUE ,n.FINPROD_ID RKEY 
  from WFL_TRD_PRODUCT_DEAL_ADD m 
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 =''F01'' AND n.PS IN (''T01'',''T02'',''T03'') and n.TRADE_STATUS != ''03'' 
  INNER JOIN FIN_PRODUCT_TYPE s ON s.FINPROD_ID=n.FINPROD_ID AND s.BRANCH=0 AND (s.TYPE_6 IN (''B12'',''B01'',''B02'',''B03'') OR (s.TYPE_6=''B05'' AND s.TYPE_7=''01'')) 
  where  m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}''', 'assetCode', 'N', 'GY', '001', TIMESTAMP '2020-05-18 09:10:11', 'GY', TIMESTAMP '2020-05-18 09:10:12');
  
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('LLZDBJYJJ', '05', 'SQL', 'SELECT DISTINCT n.UNIT_CPRICE as RVALUE ,n.FINPROD_ID RKEY 
  from TRD_PRODUCT_DEAL_ADD m 
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 =''F01'' AND n.PS IN (''T01'',''T02'',''T03'') 
  INNER JOIN FIN_PRODUCT_TYPE s ON s.FINPROD_ID=n.FINPROD_ID AND s.BRANCH=0 AND s.TYPE_6 IN（''B12'',''B01'',''B02'',''B03''） or (s.TYPE_6=''B05'' AND s.TYPE_7=''01'')
  where  m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' 
  UNION all 
  select DISTINCT n.UNIT_CPRICE as RVALUE ,n.FINPROD_ID RKEY 
  from WFL_TRD_PRODUCT_DEAL_ADD m 
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 =''F01'' AND n.PS IN (''T01'',''T02'',''T03'') and n.TRADE_STATUS != ''03'' 
  INNER JOIN FIN_PRODUCT_TYPE s ON s.FINPROD_ID=n.FINPROD_ID AND s.BRANCH=0 AND s.TYPE_6 IN（''B12'',''B01'',''B02'',''B03''） or (s.TYPE_6=''B05'' AND s.TYPE_7=''01'')
  where  m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}''', 'assetCode', 'N', 'GY', '001', TIMESTAMP '2020-05-18 09:10:11', 'GY', TIMESTAMP '2020-05-18 09:10:12');

  
----------------------非标准化债权类资产的终止日------------------------------
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='FBZHZQLZCZZR';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('FBZHZQLZCZZR', '01', 'SQL', 'SELECT to_char(mdate,''yyyy-MM-dd'') AS RVALUE,
m.FINPROD_ID RKEY FROM  FIN_PRODUCT m
inner join FIN_PRODUCT_TYPE fpt on m.FINPROD_ID=fpt.FINPROD_ID and ((fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B18''))', 'portfolioCode', 'N', 'jjpei', '99999', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('FBZHZQLZCZZR', '02', 'SQL', 'SELECT to_char(mdate,''yyyy-MM-dd'') AS RVALUE,
m.FINPROD_ID RKEY FROM  FIN_PRODUCT m
inner join FIN_PRODUCT_TYPE fpt on m.FINPROD_ID=fpt.FINPROD_ID and ((fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B18''))', 'portfolioCode', 'N', 'jjpei', '99999', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('FBZHZQLZCZZR', '03', 'SQL', 'SELECT to_char(mdate,''yyyy-MM-dd'') AS RVALUE,
m.FINPROD_ID RKEY FROM  FIN_PRODUCT m
inner join FIN_PRODUCT_TYPE fpt on m.FINPROD_ID=fpt.FINPROD_ID and ((fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B18''))', 'portfolioCode', 'N', 'jjpei', '99999', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('FBZHZQLZCZZR', '04', 'SQL', 'select ceil(n.mdate-date''${valDate}'') as RVALUE,m.FINPROD_ID as RKEY,n.FINPROD_NAME AS RKEYCN   from PTL_SEC_VALUTION m
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 IN (''F18'',''F01'')
  inner join FIN_PRODUCT_TYPE fpt on m.FINPROD_ID=fpt.FINPROD_ID and ((fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B18''))
  where  m.PORTFOLIO_ID in ${portfolioCode} and m.CDATE = date''${valDate}''', 'portfolioCode', 'N', 'jjpei', '99999', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('FBZHZQLZCZZR', '05', 'SQL', 'select ceil(n.mdate-date''${valDate}'') as RVALUE,m.FINPROD_ID as RKEY,n.FINPROD_NAME AS RKEYCN   from PTL_SEC_VALUTION m
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 IN (''F18'',''F01'')
  inner join FIN_PRODUCT_TYPE fpt on m.FINPROD_ID=fpt.FINPROD_ID and ((fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B18''))
  where  m.PORTFOLIO_ID in ${portfolioCode} and m.CDATE = date''${valDate}''', 'portfolioCode', 'N', 'jjpei', '99999', sysdate, 'jjpei', sysdate);
   
---------------------封闭式理财产品的到期日或者开放式理财产品的最近一次开放日--------------------
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='FBSLCCPDQRHKFSLCCP';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL"("PARA_CODE", "PARA_GRANULARITY", "PARA_DISPLAY_TYPE", "CONTENT", "REDIS_FIELD", "IS_FRESH_CAN_TRD", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('FBSLCCPDQRHKFSLCCP', '01', 'SQL', 'select n.PORTFOLIO_ID RKEY,MDATE as RVALUE from PTL_PORTFOLIO m INNER JOIN PA_PTL_PORTFOLIO n 
  ON m.PORTFOLIO_ID=n.PORTFOLIO_ID AND n.OPEN_TYPE=''01'' 
  UNION ALL 
  SELECT PORTFOLIO_ID ,OPEN_VDATE FROM (SELECT m.PORTFOLIO_ID,y.OPEN_VDATE ,rank() over(PARTITION BY m.PORTFOLIO_ID ORDER BY y.OPEN_VDATE asc) AS rowno  from PTL_PORTFOLIO m 
  INNER JOIN  FIN_CASH_INFO n ON n.FINPROD_ID=''F16_''||m.PORTFOLIO_ID  AND n.CASH_TYPE_1=''01'' AND n.CASH_TYPE_2=''C01''
  INNER JOIN  FIN_OPEN_PLAN y ON y.CASH_ID=n.CASH_ID AND y.OPEN_TYPE IN (''02'',''03'')
  INNER JOIN  PA_PTL_PORTFOLIO s   on m.PORTFOLIO_ID=s.PORTFOLIO_ID AND s.OPEN_TYPE=''02''
  WHERE    y.OPEN_VDATE>date''${valDate}''  ORDER BY y.OPEN_VDATE ASC) WHERE  rowno=1', 'portfolioCode', 'N', 'jjpei', '99999', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL"("PARA_CODE", "PARA_GRANULARITY", "PARA_DISPLAY_TYPE", "CONTENT", "REDIS_FIELD", "IS_FRESH_CAN_TRD", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('FBSLCCPDQRHKFSLCCP', '02', 'SQL', 'select n.PORTFOLIO_ID RKEY,MDATE as RVALUE from PTL_PORTFOLIO m INNER JOIN PA_PTL_PORTFOLIO n 
  ON m.PORTFOLIO_ID=n.PORTFOLIO_ID AND n.OPEN_TYPE=''01'' 
  UNION ALL 
  SELECT PORTFOLIO_ID ,OPEN_VDATE FROM (SELECT m.PORTFOLIO_ID,y.OPEN_VDATE ,rank() over(PARTITION BY m.PORTFOLIO_ID ORDER BY y.OPEN_VDATE asc) AS rowno  from PTL_PORTFOLIO m 
  INNER JOIN  FIN_CASH_INFO n ON n.FINPROD_ID=''F16_''||m.PORTFOLIO_ID  AND n.CASH_TYPE_1=''01'' AND n.CASH_TYPE_2=''C01''
  INNER JOIN  FIN_OPEN_PLAN y ON y.CASH_ID=n.CASH_ID AND y.OPEN_TYPE IN (''02'',''03'')
  INNER JOIN  PA_PTL_PORTFOLIO s   on m.PORTFOLIO_ID=s.PORTFOLIO_ID AND s.OPEN_TYPE=''02''
  WHERE    y.OPEN_VDATE>date''${valDate}''  ORDER BY y.OPEN_VDATE ASC) WHERE  rowno=1', 'portfolioCode', 'N', 'jjpei', '99999', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL"("PARA_CODE", "PARA_GRANULARITY", "PARA_DISPLAY_TYPE", "CONTENT", "REDIS_FIELD", "IS_FRESH_CAN_TRD", "CREATE_USER", "CREATE_DEPT", "CREATE_TIME", "UPDATE_USER", "UPDATE_TIME") VALUES ('FBSLCCPDQRHKFSLCCP', '03', 'SQL', 'select n.PORTFOLIO_ID RKEY,MDATE as RVALUE from PTL_PORTFOLIO m INNER JOIN PA_PTL_PORTFOLIO n 
  ON m.PORTFOLIO_ID=n.PORTFOLIO_ID AND n.OPEN_TYPE=''01'' 
  UNION ALL 
  SELECT PORTFOLIO_ID ,OPEN_VDATE FROM (SELECT m.PORTFOLIO_ID,y.OPEN_VDATE ,rank() over(PARTITION BY m.PORTFOLIO_ID ORDER BY y.OPEN_VDATE asc) AS rowno  from PTL_PORTFOLIO m 
  INNER JOIN  FIN_CASH_INFO n ON n.FINPROD_ID=''F16_''||m.PORTFOLIO_ID  AND n.CASH_TYPE_1=''01'' AND n.CASH_TYPE_2=''C01''
  INNER JOIN  FIN_OPEN_PLAN y ON y.CASH_ID=n.CASH_ID AND y.OPEN_TYPE IN (''02'',''03'')
  INNER JOIN  PA_PTL_PORTFOLIO s   on m.PORTFOLIO_ID=s.PORTFOLIO_ID AND s.OPEN_TYPE=''02''
  WHERE    y.OPEN_VDATE>date''${valDate}''  ORDER BY y.OPEN_VDATE ASC) WHERE  rowno=1', 'portfolioCode', 'N', 'jjpei', '99999', sysdate, 'jjpei', sysdate);
  INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('FBSLCCPDQRHKFSLCCP', '04', 'SQL', 'select ceil(MDATE-date''${valDate}'') as mdate from PTL_PORTFOLIO m INNER JOIN PA_PTL_PORTFOLIO n 
  ON m.PORTFOLIO_ID=n.PORTFOLIO_ID AND n.OPEN_TYPE=''01'' where n.PORTFOLIO_ID in ${portfolioCode}
  UNION ALL 
  SELECT mdate FROM (SELECT ceil(y.OPEN_VDATE-date''${valDate}'') as mdate  from PTL_PORTFOLIO m 
  INNER JOIN  FIN_CASH_INFO n ON n.FINPROD_ID=''F16_''||m.PORTFOLIO_ID  AND n.CASH_TYPE_1=''01'' AND n.CASH_TYPE_2=''C01''
  INNER JOIN  FIN_OPEN_PLAN y ON y.CASH_ID=n.CASH_ID AND y.OPEN_TYPE IN (''02'',''03'')
  INNER JOIN  PA_PTL_PORTFOLIO s   on m.PORTFOLIO_ID=s.PORTFOLIO_ID AND s.OPEN_TYPE=''02''
  WHERE  m.PORTFOLIO_ID in ${portfolioCode} AND  y.OPEN_VDATE>date''${valDate}'' ORDER BY y.OPEN_VDATE ASC) WHERE  rownum=1', 'portfolioCode', 'N', 'jjpei', '99999', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('FBSLCCPDQRHKFSLCCP', '05', 'SQL', 'select ceil(MDATE-date''${valDate}'') as mdate from PTL_PORTFOLIO m INNER JOIN PA_PTL_PORTFOLIO n 
  ON m.PORTFOLIO_ID=n.PORTFOLIO_ID AND n.OPEN_TYPE=''01'' where n.PORTFOLIO_ID in ${portfolioCode}
  UNION ALL 
  SELECT mdate FROM (SELECT ceil(y.OPEN_VDATE-date''${valDate}'') as mdate  from PTL_PORTFOLIO m 
  INNER JOIN  FIN_CASH_INFO n ON n.FINPROD_ID=''F16_''||m.PORTFOLIO_ID  AND n.CASH_TYPE_1=''01'' AND n.CASH_TYPE_2=''C01''
  INNER JOIN  FIN_OPEN_PLAN y ON y.CASH_ID=n.CASH_ID AND y.OPEN_TYPE IN (''02'',''03'')
  INNER JOIN  PA_PTL_PORTFOLIO s   on m.PORTFOLIO_ID=s.PORTFOLIO_ID AND s.OPEN_TYPE=''02''
  WHERE  m.PORTFOLIO_ID in ${portfolioCode} AND  y.OPEN_VDATE>date''${valDate}'' ORDER BY y.OPEN_VDATE ASC) WHERE  rownum=1', 'portfolioCode', 'N', 'jjpei', '99999', sysdate, 'jjpei', sysdate);
  
--产品商品及金融衍生品类资产规模-卖出实占（不算指标）
delete from RSK_EVENT_ITEM_RULE where EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_04';
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_SPJJRYSPLZCGM_04', 'assetType', 'd36a7d25b48349459af06c78b9c665ef', null, 'F03,F04,F09,F02,F06', 'IN', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 10:45:28', 'test2', TIMESTAMP '2020-05-19 10:46:00');
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_SPJJRYSPLZCGM_04', 'assetType', 'dc7fc1ef847f4820a51ce9bd22ee7e2f', null, 'F18', 'IN', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 10:45:28', 'test2', TIMESTAMP '2020-05-19 10:46:00');
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_SPJJRYSPLZCGM_04', 'assetType', 'ef9b43955ef54020bdd632355e9ba107', null, 'F16,F12', 'IN', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 10:45:28', 'test2', TIMESTAMP '2020-05-19 10:46:00');
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_SPJJRYSPLZCGM_04', 'investType', 'd36a7d25b48349459af06c78b9c665ef', null, '03', 'IN', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 10:45:28', 'test2', TIMESTAMP '2020-05-19 10:46:00');
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_SPJJRYSPLZCGM_04', 'investType', 'dc7fc1ef847f4820a51ce9bd22ee7e2f', null, '03', 'IN', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 10:45:28', 'test2', TIMESTAMP '2020-05-19 10:46:00');
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_SPJJRYSPLZCGM_04', 'investType', 'ef9b43955ef54020bdd632355e9ba107', null, '03', 'IN', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 10:45:28', 'test2', TIMESTAMP '2020-05-19 10:46:00');
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_SPJJRYSPLZCGM_04', 'ps', 'd36a7d25b48349459af06c78b9c665ef', null, 'T07,T08,T09,T11', 'IN', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 10:45:28', 'test2', TIMESTAMP '2020-05-19 10:46:00');
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_SPJJRYSPLZCGM_04', 'ps', 'dc7fc1ef847f4820a51ce9bd22ee7e2f', null, 'T59,T60,T61', 'IN', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 10:45:28', 'test2', TIMESTAMP '2020-05-19 10:46:00');
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_SPJJRYSPLZCGM_04', 'ps', 'ef9b43955ef54020bdd632355e9ba107', null, 'T07', 'IN', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 10:45:28', 'test2', TIMESTAMP '2020-05-19 10:46:00');
 
--按照资产净值加权的组合久期
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE = 'AZZCJZJQDZHJQ' and PARA_GRANULARITY IN ('04');
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('AZZCJZJQDZHJQ', '04', 'SQL', 'SELECT sum((TRADE_AMT)*(M_DURATION)) AS RVALUE FROM (				
  select				
	SUM(A1.TRADE_AMT) TRADE_AMT,			
	CASE WHEN A1.FINPROD_TYPE =''F01'' THEN			
	(			
	 	select  CASE WHEN nvl(sum(B1.M_DURATION),0)=0 THEN (((A1.mdate)-(date''${valDate}''))/365) ELSE (nvl(sum(B1.M_DURATION),0)) END M_DURATION from  MST_BOND_VALUATION_INFO B1		
	    inner join			
	    (select to_char(max(VALUE_DATE),''yyyy-MM-dd'') as VALUE_DATE,SEC_ID from MST_BOND_VALUATION_INFO where VALUE_DATE<=date''${valDate}'' group by sec_id) B2			
	    on B1.SEC_ID=B2.SEC_ID and to_char(B1.VALUE_DATE,''yyyy-MM-dd'') =B2.VALUE_DATE WHERE B1.SEC_ID=A1.FINPROD_ID			
	)			
	WHEN (A1.FINPROD_TYPE =''F06'' OR A1.FINPROD_TYPE =''XJ'') THEN (1)/365	
    WHEN (A1.FINPROD_TYPE =''F10'' AND A1.MDATE IS NULL) THEN (1) 	
	WHEN a1.FINPROD_ID =a1.PORTFOLIO_ID			
	THEN (1)/365			
    ELSE ((nvl((A1.mdate)-(date''${valDate}''),0))/365) END M_DURATION				
	from			
	(			
 	    select NULL AS FINPROD_ID,NULL AS PORTFOLIO_ID,nvl(sum(AMOUNT),0) as  TRADE_AMT,''XJ'' AS FINPROD_TYPE,NULL AS MDATE   from PTL_CASH_POSITION			
 	    where  PORTFOLIO_ID in ${portfolioCode} and HODING_TYPE=''03'' and CDATE =date''${valDate}''			
        UNION ALL				
		select		
		t2.FINPROD_MARKET_ID FINPROD_ID,t1.PORTFOLIO_ID,nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0) as TRADE_AMT,t2.FINPROD_TYPE2,t2.MDATE		
		from PTL_SEC_VALUTION t1		
		inner join FIN_PRODUCT t2	on t1.FINPROD_ID = t2.FINPROD_ID	
		where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''-1	
          and t2.FINPROD_TYPE2 not in (''F10'',''F15'',''F11'',''F12'',''F13'',''F35'',''F14'') 		
	   UNION ALL			
	   select			
		NVL(t2.FINPROD_MARKET_ID,t1.FINPROD_ID) FINPROD_ID,t1.PORTFOLIO_ID, nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0) as TRADE_AMT,t3.FINPROD_TYPE2,t3.MDATE		
		from PTL_SEC_VALUTION t1		
		inner join FIN_TRADE_PRODUCT t3	 on t1.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0	
		left join FIN_PRODUCT t2 on t3.FINPROD_ID = t2.FINPROD_ID	
		where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''-1		
		and t3.FINPROD_TYPE2 not in (''F02'',''F04'',''F08'', ''F25'',''F10'',''F15'',''F11'',''F12'',''F13'',''F35'',''F14'')		
	  ', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
----------过长截断	
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||'  UNION ALL			
		select		
		  NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID) FINPROD_ID ,t4.PORTFOLIO_ID,		
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
          -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 		  as  TRADE_AMT,n.FINPROD_TYPE2,nvl(t2.MDATE,t3.MDATE) as MDATE		
		  from TRD_PRODUCT_DEAL n		
		  left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID		
		  left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0 
		  LEFT JOIN TRD_PRODUCT_DEAL_ADD t4 ON n.TRADE_ID=t4.TRADE_ID		
		  where t4.PORTFOLIO_ID in ${portfolioCode} and  n.TRADE_DATE = date''${valDate}''		
		  and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in  (''F02'',''F04'',''F08'', ''F25'',''F10'',''F15'',''F11'',''F12'',''F13'',''F35'',''F14'')))		
		  and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'') group by t4.PORTFOLIO_ID,NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID),nvl(t2.MDATE,t3.MDATE),n.FINPROD_TYPE2	
'  where PARA_CODE = 'AZZCJZJQDZHJQ' and PARA_GRANULARITY='04';
update RSK_ISUP_INDICATOR_PARA_DTL set content = content||'  UNION ALL		
	  select NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID) FINPROD_ID ,m.PORTFOLIO_ID,		
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
      -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 	  as  TRADE_AMT,n.FINPROD_TYPE2,nvl(t2.MDATE,t3.MDATE) as MDATE			
	  from WFL_TRD_PRODUCT_DEAL_ADD m			
	  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''			
	  INNER JOIN FIN_PRODUCT T2 on T2.FINPROD_ID=n.FINPROD_ID 			 
	  left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0 
      WHERE  m.PORTFOLIO_ID in ${portfolioCode}  AND n.TRADE_DATE=date''${valDate}''  				
      AND ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in  (''F02'',''F04'',''F08'', ''F25'',''F10'',''F15'',''F11'',''F12'',''F13'',''F35'',''F14''))) 				
      and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'') group by m.PORTFOLIO_ID,NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID),nvl(t2.MDATE,t3.MDATE),n.FINPROD_TYPE2				
	) A1 GROUP BY A1.PORTFOLIO_ID,A1.FINPROD_ID,A1.FINPROD_TYPE,A1.MDATE			
)'  where PARA_CODE = 'AZZCJZJQDZHJQ' and PARA_GRANULARITY='04'; 

 --组合可投资资产限制-买入实占
 delete from RSK_EVENT_ITEM_RULE where EVENT_CODE like 'EVENT_ID_ZHKTZZCXZ%';
 INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_ZHKTZZCXZ_07', 'ps', 'e2dee483916744fcb82b46124c4b2bbc', null, 'C1,C10,C11,C12,C13,C14,C15,C16,C17,C18,C2,C20,C21,C3,C4,C5,C6,C7,C8,C9', 'NO', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 15:32:57', 'test2', TIMESTAMP '2020-05-19 15:33:27');
INSERT INTO RSK_EVENT_ITEM_RULE (EVENT_CODE, ITEM_CODE, GROUP_ID, ITEM_ANO_NAME, ITEM_VALUE, MATCH_TYPE, OFFSET_VAL, OFFSET_TYPE, OFFSET_UNIT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('EVENT_ID_ZHKTZZCXZ_07', 'eventType', 'e2dee483916744fcb82b46124c4b2bbc', null, '0,1,2', 'IN', null, null, null, 'jl', '001', TIMESTAMP '2020-05-19 15:32:57', 'test2', TIMESTAMP '2020-05-19 15:33:27');


-- 非标准化债权类资产的终止日 Redis存储识别域 修改成资产代码
update RSK_ISUP_INDICATOR_PARA_DTL set redis_field = 'assetCode' where para_code = 'FBZHZQLZCZZR';

delete from RSK_ISUP_INDICATOR_PARA  where PARA_CODE = 'ZHKTZZCXZ';
INSERT INTO RSK_ISUP_INDICATOR_PARA (PARA_CODE, PARA_NAME, IS_FORBID, FORBID_ENUM, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, MODEL_CODE, MODEL_VALUE, IS_UP_REDIS, PARAM_DATA_TYPE) VALUES ('ZHKTZZCXZ', '组合可投资资产限制', 'Y', 'SELECT ID,TEXT FROM (									
select ITEM_CODE id,ITEM_VALUE text from SYS_DATADICT_LIST where DICT_CODE=''PINAN_ASSET_TYPE'' and ITEM_CODE not in (''F01'',''F02'',''F05'',''F06'')									
union all									
select ''F01_''||ITEM_CODE id,ITEM_VALUE text from SYS_DATADICT_LIST where DICT_CODE=''WINDTYPEONE''									
union all									
select ''F02_''||ITEM_CODE id,ITEM_VALUE text from SYS_DATADICT_LIST where DICT_CODE=''FUND_ASSET_TYPE''									
union all									
select ''F05_''||ITEM_CODE id,ITEM_VALUE text from SYS_DATADICT_LIST where DICT_CODE=''DEPO_TYPE''	
union all									
select ''F05_''||ITEM_CODE id,ITEM_VALUE text from SYS_DATADICT_LIST where DICT_CODE=''DEPO_ASSET_TYPE''									
union all									
select ''F06_''||ITEM_CODE id,ITEM_VALUE text from SYS_DATADICT_LIST where DICT_CODE=''ASSET_TYPE_PROJECT''									
) ORDER BY ID', 'admin', '001', TIMESTAMP '2020-05-19 15:38:45', 'admin', TIMESTAMP '2020-05-19 15:38:46', null, null, 'N', 'String');
