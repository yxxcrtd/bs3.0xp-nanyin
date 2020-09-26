----投资单一上市公司发行的股票（事中日间）---
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='TZDYSSGSFXGP' AND PARA_GRANULARITY ='04';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('TZDYSSGSFXGP', '04', 'SQL', 'select nvl(sum(nvl(TRADE_AMT, 0)),0) as RVALUE ,s1.FINPROD_ID as RKEY,s1.FINPROD_NAME RKEYCN from	
(	
  select t1.FINPROD_ID ,n.FINPROD_NAME,nvl(sum(nvl(SHARE_AMT,0)),0) as  TRADE_AMT from PTL_SEC_PIERCE_VALUTION t1	
  INNER  JOIN FIN_PRODUCT n on t1.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F10''	
  where t1.PORTFOLIO_ID in ${portfolioCode}  and  t1.CDATE=date''${valDate}''-1	
  group by t1.FINPROD_ID,n.FINPROD_NAME	
union all	
   select t2.FINPROD_ID ,m.FINPROD_NAME,	
  nvl(sum(decode(t2.PS,''T01'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0)
  + nvl(sum(decode(t2.PS,''T46'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0)
  + nvl(sum(decode(t2.PS,''T47'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0)
  + nvl(sum(decode(t2.PS,''T48'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0)
  - nvl(sum(decode(t2.PS,''T11'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0)	
  - nvl(sum(decode(t2.PS,''T02'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0) 	
  from TRD_PRODUCT_DEAL_ADD t1	
  inner join TRD_PRODUCT_DEAL t2 on t1.TRADE_ID=t2.TRADE_ID 	
   INNER  JOIN FIN_PRODUCT m on m.FINPROD_ID=t2.FINPROD_ID and m.FINPROD_TYPE2 =''F10''	
  where t1.PORTFOLIO_ID in ${portfolioCode} and  t2.TRADE_DATE=date''${valDate}'' and t2.PS in (''T01'',''T02'',''T46'',''T47'',''T48'',''T11'')	
   group by t2.FINPROD_ID,m.FINPROD_NAME	
union all	
   select 	t2.FINPROD_ID ,s.FINPROD_NAME,
  nvl(sum(decode(t2.PS,''T01'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0)
  + nvl(sum(decode(t2.PS,''T46'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0)
  + nvl(sum(decode(t2.PS,''T47'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0)
  + nvl(sum(decode(t2.PS,''T48'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0)
  - nvl(sum(decode(t2.PS,''T11'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0)	
  - nvl(sum(decode(t2.PS,''T02'',decode(TRADE_STATUS,''03'',t2.TRADE_AMT*-1,t2.TRADE_AMT))),0) 	
  from WFL_TRD_PRODUCT_DEAL_ADD t1	
  inner join WFL_TRD_PRODUCT_DEAL t2 on t1.TRADE_ID=t2.TRADE_ID   and t2.TRADE_STATUS != ''03''	
  INNER  JOIN FIN_PRODUCT s on t2.FINPROD_ID=s.FINPROD_ID and s.FINPROD_TYPE2 =''F10''	
  where t1.PORTFOLIO_ID in ${portfolioCode}  and t2.TRADE_DATE=date''${valDate}'' and t2.PS in (''T01'',''T02'',''T46'',''T47'',''T48'',''T11'')	
   group by t2.FINPROD_ID,s.FINPROD_NAME	
) s1  group by s1.FINPROD_ID,s1.FINPROD_NAME', 'portfolioCode,assetCode', 'N', 'wangzhao', '99999', TIMESTAMP '2020-05-08 10:24:09', 'wangzhao', TIMESTAMP '2020-05-08 10:24:09');
