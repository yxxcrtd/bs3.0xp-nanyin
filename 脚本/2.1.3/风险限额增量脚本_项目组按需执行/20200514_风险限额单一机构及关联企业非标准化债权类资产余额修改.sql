--单一机构及关联企业非标准化债权类资产余额
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE = 'DYGLF'  and PARA_GRANULARITY = '04';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('DYGLF', '04', 'SQL', 'with temp as (select  m.FINPROD_ID,m.FINPROD_TYPE2,
case when m.FINPROD_TYPE2 = ''F01'' then m.ISSUER else m.FINANCIER end org_code
FROM
    FIN_PRODUCT m
INNER JOIN
    FIN_PRODUCT_TYPE fpt ON  m.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0
AND (  (fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR  ( fpt.TYPE_8 =''F01''  AND fpt.TYPE_6 =''B18'')))
select sum(nvl(targetData.VALUE, 0)) as RVALUE, targetData.ASSETCODE AS RKEY, targetData.ASSETNAME  RKEYCN   from
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
  -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME  from TRD_PRODUCT_DEAL_ADD m
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
 inner join (
 select FINPROD_ID,org_code,org_code org_code_union from temp
 union all
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO
 inner join temp c on c.org_code = b.COR_ORGID
 ) g on  n.FINPROD_ID=g.FINPROD_ID
  left join MST_CUSTOMER_INFO t3 on  g.org_code=t3.CUSTOMER_ID
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T22'',''T23'') group by m.PORTFOLIO_ID ,g.org_code,T3.CUSTOMER_NAME
union ALL
  select
  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME  from WFL_TRD_PRODUCT_DEAL_ADD m
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
  inner join (
 select FINPROD_ID,org_code,org_code org_code_union from temp
 union all
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO
 inner join temp c on c.org_code = b.COR_ORGID
 ) g on  n.FINPROD_ID=g.FINPROD_ID
  left join MST_CUSTOMER_INFO t3 on  g.org_code=t3.CUSTOMER_ID
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T22'',''T23'')   group by m.PORTFOLIO_ID ,g.org_code,T3.CUSTOMER_NAME
) targetData group by targetData.ASSETCODE, targetData.ASSETNAME', 'portfolioCode,issuerCode', 'N', 'GY', '001', TIMESTAMP '2020-05-14 17:29:05', 'GY', TIMESTAMP '2020-05-14 17:29:05');
