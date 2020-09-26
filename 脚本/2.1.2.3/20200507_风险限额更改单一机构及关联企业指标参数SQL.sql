--------------------------单一机构及关联企业非标准化债权类资产持仓----------------------------
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='DYGLF';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('DYGLF', '01', 'SQL', 'with temp as (select  m.FINPROD_ID,m.FINPROD_TYPE2, 		
case when m.FINPROD_TYPE2 = ''F01'' then m.ISSUER else m.FINANCIER end org_code 		
FROM		
    FIN_PRODUCT m		
INNER JOIN		
    FIN_PRODUCT_TYPE fpt ON  m.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0		
AND (  (fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR  ( fpt.TYPE_8 =''F01''  AND fpt.TYPE_6 =''B18'')))		
		
SELECT RKEY,SUM(RVALUE) RVALUE FROM (		
SELECT nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  RVALUE 		
, m.PORTFOLIO_ID||'',''||g.org_code as RKEY from PTL_SEC_VALUTION m		
 inner join ( 		
 select FINPROD_ID,org_code,org_code org_code_union from temp 		
 union all 		
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a 		
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO		
 inner join temp c on c.org_code = b.COR_ORGID		
 ) g on  m.FINPROD_ID=g.FINPROD_ID 		
where    m.CDATE =date''${valDate}''-1  group by m.PORTFOLIO_ID,g.org_code		
 union all		
   select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.org_code as RKEY		
   FROM ( select m.*,g.org_code from PTL_SEC_VALUTION m		
   inner join ( 		
 select FINPROD_ID,org_code,org_code org_code_union from temp 		
 union all 		
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a 		
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO		
 inner join temp c on c.org_code = b.COR_ORGID		
 ) g on  m.FINPROD_ID=g.FINPROD_ID 		
	where  m.CDATE =date''${valDate}''-1 ) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' )	
		s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.org_code 
) GROUP BY RKEY', 'portfolioCode,issuerCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('DYGLF', '02', 'SQL', 'with temp as (select  m.FINPROD_ID,m.FINPROD_TYPE2, 		
case when m.FINPROD_TYPE2 = ''F01'' then m.ISSUER else m.FINANCIER end org_code 		
FROM		
    FIN_PRODUCT m		
INNER JOIN		
    FIN_PRODUCT_TYPE fpt ON  m.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0		
AND (  (fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR  ( fpt.TYPE_8 =''F01''  AND fpt.TYPE_6 =''B18'')))		
		
SELECT RKEY,SUM(RVALUE) RVALUE FROM (		
SELECT nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  RVALUE 		
, m.PORTFOLIO_ID||'',''||g.org_code as RKEY from PTL_SEC_VALUTION m		
 inner join ( 		
 select FINPROD_ID,org_code,org_code org_code_union from temp 		
 union all 		
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a 		
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO		
 inner join temp c on c.org_code = b.COR_ORGID		
 ) g on  m.FINPROD_ID=g.FINPROD_ID 		
where    m.CDATE =date''${valDate}''-1  group by m.PORTFOLIO_ID,g.org_code		
 union all		
   select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.org_code as RKEY		
   FROM ( select m.*,g.org_code from PTL_SEC_VALUTION m		
   inner join ( 		
 select FINPROD_ID,org_code,org_code org_code_union from temp 		
 union all 		
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a 		
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO		
 inner join temp c on c.org_code = b.COR_ORGID		
 ) g on  m.FINPROD_ID=g.FINPROD_ID 		
	where  m.CDATE =date''${valDate}''-1 ) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' )	
		s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.org_code 
) GROUP BY RKEY', 'portfolioCode,issuerCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('DYGLF', '03', 'SQL', 'with temp as (select  m.FINPROD_ID,m.FINPROD_TYPE2, 		
case when m.FINPROD_TYPE2 = ''F01'' then m.ISSUER else m.FINANCIER end org_code 		
FROM		
    FIN_PRODUCT m		
INNER JOIN		
    FIN_PRODUCT_TYPE fpt ON  m.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0		
AND (  (fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR  ( fpt.TYPE_8 =''F01''  AND fpt.TYPE_6 =''B18'')))		
		
SELECT RKEY,SUM(RVALUE) RVALUE FROM (		
SELECT nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  RVALUE 		
, m.PORTFOLIO_ID||'',''||g.org_code as RKEY from PTL_SEC_VALUTION m		
 inner join ( 		
 select FINPROD_ID,org_code,org_code org_code_union from temp 		
 union all 		
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a 		
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO		
 inner join temp c on c.org_code = b.COR_ORGID		
 ) g on  m.FINPROD_ID=g.FINPROD_ID 		
where    m.CDATE =date''${valDate}''-1  group by m.PORTFOLIO_ID,g.org_code		
 union all		
   select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.org_code as RKEY		
   FROM ( select m.*,g.org_code from PTL_SEC_VALUTION m		
   inner join ( 		
 select FINPROD_ID,org_code,org_code org_code_union from temp 		
 union all 		
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a 		
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO		
 inner join temp c on c.org_code = b.COR_ORGID		
 ) g on  m.FINPROD_ID=g.FINPROD_ID 		
	where  m.CDATE =date''${valDate}''-1 ) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' )	
		s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.org_code 
) GROUP BY RKEY', 'portfolioCode,issuerCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
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
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
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
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T07'',''T08'') group by m.PORTFOLIO_ID ,g.org_code,T3.CUSTOMER_NAME		
union ALL		
  select		
  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)		
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
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T07'',''T08'') 	group by m.PORTFOLIO_ID ,g.org_code,T3.CUSTOMER_NAME	
) targetData group by targetData.ASSETCODE, targetData.ASSETNAME', 'portfolioCode,issuerCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);

INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('DYGLF', '05', 'SQL', 'with temp as (select m.FINPROD_ID,m.FINPROD_TYPE2,   		
case when m.FINPROD_TYPE2 = ''F01'' then m.ISSUER else m.FINANCIER end org_code   		
FROM  		
    FIN_PRODUCT m    		
INNER JOIN  		
    FIN_PRODUCT_TYPE fpt ON  m.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0  		
AND (  (fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR  ( fpt.TYPE_8 =''F01''  AND fpt.TYPE_6 =''B18''))) 		
select   		
sum(nvl(targetData.VALUE, 0)) as RVALUE, targetData.ASSETCODE AS RKEY, targetData.ASSETNAME RKEYCN 		
from  		
(  		
select  nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  VALUE,T1.PORTFOLIO_ID,g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME from PTL_SEC_VALUTION t1  		
 inner join ( 		
 select FINPROD_ID,org_code,org_code org_code_union from temp 		
 union all 		
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a 		
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO		
 inner join temp c on c.org_code = b.COR_ORGID		
 ) g on  t1.FINPROD_ID=g.FINPROD_ID  		
left join MST_CUSTOMER_INFO t3 on  g.org_code=t3.CUSTOMER_ID  		
where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE =date''${valDate}'' group by t1.PORTFOLIO_ID ,g.org_code,T3.CUSTOMER_NAME  		
) targetData  		
group by targetData.ASSETCODE, targetData.ASSETNAME', 'portfolioCode,issuerCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);

--------------------------信用债与利率债指标参数04，05修改---------------------------
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE in ('LLZDBJYJJ','XYZDBJYJJ') and PARA_GRANULARITY in ('04','05');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL
(PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('LLZDBJYJJ', '04', 'SQL', 'SELECT DISTINCT n.UNIT_CPRICE as RVALUE ,n.FINPROD_ID RKEY
  from TRD_PRODUCT_DEAL_ADD m
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 =''F01'' AND n.PS IN (''T01'',''T02'',''T03'')
  INNER JOIN FIN_PRODUCT_TYPE s ON s.FINPROD_ID=n.FINPROD_ID AND s.BRANCH=0 AND s.TYPE_6 IN (''B12'',''B01'',''B02'',''B03'',''B05'')
  where  m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}''
  UNION all
  select DISTINCT n.UNIT_CPRICE as RVALUE ,n.FINPROD_ID RKEY
  from WFL_TRD_PRODUCT_DEAL_ADD m
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 =''F01'' AND n.PS IN (''T01'',''T02'',''T03'') and n.TRADE_STATUS != ''03''
  INNER JOIN FIN_PRODUCT_TYPE s ON s.FINPROD_ID=n.FINPROD_ID AND s.BRANCH=0 AND s.TYPE_6 IN (''B12'',''B01'',''B02'',''B03'',''B05'')
  where  m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}''', 'assetCode', 'N', 'jjpei', '2222222', TIMESTAMP '2020-05-07 11:07:34.057000', 'jjpei', TIMESTAMP '2020-05-07 11:07:34.232000');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL
(PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('XYZDBJYJJ', '04', 'SQL', 'SELECT DISTINCT n.UNIT_CPRICE as RVALUE ,n.FINPROD_ID RKEY
  from TRD_PRODUCT_DEAL_ADD m
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 =''F01'' AND n.PS IN (''T01'',''T02'',''T03'')
  INNER JOIN FIN_PRODUCT_TYPE s ON s.FINPROD_ID=n.FINPROD_ID AND s.BRANCH=0 AND s.TYPE_6 IN (''B10'',''B11'',''B13'',''B14'',''B15'',''B04'',''B16'',''B05'',''B06'',''B07'',''B08'',''B09'')
  where  m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}''
  UNION all
  select DISTINCT n.UNIT_CPRICE as RVALUE ,n.FINPROD_ID RKEY
  from WFL_TRD_PRODUCT_DEAL_ADD m
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 =''F01'' AND n.PS IN (''T01'',''T02'',''T03'') and n.TRADE_STATUS != ''03''
  INNER JOIN FIN_PRODUCT_TYPE s ON s.FINPROD_ID=n.FINPROD_ID AND s.BRANCH=0 AND s.TYPE_6 IN (''B10'',''B11'',''B13'',''B14'',''B15'',''B04'',''B16'',''B05'',''B06'',''B07'',''B08'',''B09'')
  where  m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}''', 'assetCode', 'N', 'jjpei', '2222222', TIMESTAMP '2020-05-07 11:06:58.552000', 'jjpei', TIMESTAMP '2020-05-07 11:06:58.697000');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL
(PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('LLZDBJYJJ', '05', 'SQL', 'SELECT DISTINCT n.UNIT_CPRICE as RVALUE ,n.FINPROD_ID RKEY
  from TRD_PRODUCT_DEAL_ADD m
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 =''F01'' AND n.PS IN (''T01'',''T02'',''T03'')
  INNER JOIN FIN_PRODUCT_TYPE s ON s.FINPROD_ID=n.FINPROD_ID AND s.BRANCH=0 AND s.TYPE_6 IN (''B12'',''B01'',''B02'',''B03'',''B05'')
  where  m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}''', 'assetCode', 'N', 'jjpei', '2222222', TIMESTAMP '2020-05-07 11:16:58.201000', 'jjpei', TIMESTAMP '2020-05-07 11:16:58.334000');
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL
(PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
VALUES('XYZDBJYJJ', '05', 'SQL', 'SELECT DISTINCT n.UNIT_CPRICE as RVALUE ,n.FINPROD_ID RKEY
  from TRD_PRODUCT_DEAL_ADD m
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 =''F01'' AND n.PS IN (''T01'',''T02'',''T03'')
  INNER JOIN FIN_PRODUCT_TYPE s ON s.FINPROD_ID=n.FINPROD_ID AND s.BRANCH=0 AND s.TYPE_6 IN (''B10'',''B11'',''B13'',''B14'',''B15'',''B04'',''B16'',''B05'',''B06'',''B07'',''B08'',''B09'')
  where  m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}''', 'assetCode', 'N', 'jjpei', '2222222', TIMESTAMP '2020-05-07 11:16:37.865000', 'jjpei', TIMESTAMP '2020-05-07 11:16:38.008000');



