/**风险指标参数:单一机构及关联企业非标准化债权类资产余额**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='DYGLF';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('DYGLF','单一机构及关联企业非标准化债权类资产余额','N','','ysfu','C001',sysdate,'ysfu',sysdate,'','','Y','String', '','REDIS', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='DYGLF';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DYGLF','01', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode,issuerCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DYGLF','02', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode,issuerCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DYGLF','03', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode,issuerCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DYGLF','04', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode,issuerCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DYGLF','05', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode,issuerCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'with temp as (select  m.FINPROD_ID,m.FINPROD_TYPE2,m.FINANCIER org_code
FROM
    FIN_PRODUCT m
INNER JOIN
    FIN_PRODUCT_TYPE fpt ON  m.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0
AND  fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')
)
SELECT RKEY,SUM(RVALUE) RVALUE FROM (
SELECT NVL(SUM(NVL(m.MARKET_VALUE,0)+nvl(m.TDY_INTINCEXP, 0)),0) as  RVALUE
, m.PORTFOLIO_ID||'',''||g.org_code as RKEY from PTL_SEC_PIERCE_VALUTION m
 inner join (
 select FINPROD_ID,org_code,org_code org_code_union from temp
 union all
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO
 inner join temp c on c.org_code = b.COR_ORGID
 ) g on  m.FINPROD_ID=g.FINPROD_ID
where    m.CDATE =date''${valDate}''-1  group by m.PORTFOLIO_ID,g.org_code
 union all
   select NVL(SUM(NVL(s1.MARKET_VALUE,0)+nvl(s1.TDY_INTINCEXP, 0)),0) as RVALUE, s2.PTL_BAG_ID||'',''||s1.org_code as RKEY
   FROM ( select m.*,g.org_code from PTL_SEC_PIERCE_VALUTION m
   inner join (
 select FINPROD_ID,org_code,org_code org_code_union from temp
 union all
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO
 inner join temp c on c.org_code = b.COR_ORGID
 ) g on  m.FINPROD_ID=g.FINPROD_ID
  where  m.CDATE =date''${valDate}''-1 ) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' )
    s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.org_code
) GROUP BY RKEY';

----事后(日间)
V_CONTENT_04 CLOB := 'with temp as (select  m.FINPROD_ID,m.FINPROD_TYPE2,m.FINANCIER org_code
FROM
    FIN_PRODUCT m
INNER JOIN
    FIN_PRODUCT_TYPE fpt ON  m.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0
AND fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')
)
select sum(nvl(targetData.VALUE, 0)) as RVALUE, targetData.ASSETCODE AS RKEY, targetData.ASSETNAME  RKEYCN   from
(
  select  NVL(NVL(t1.MARKET_VALUE,0)+nvl(t1.TDY_INTINCEXP, 0),0) as  VALUE,t1.PORTFOLIO_ID,g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME 
  from PTL_SEC_PIERCE_VALUTION t1
   inner join (
   select FINPROD_ID,org_code,org_code org_code_union from temp
   union all
   select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a
   inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO
   inner join temp c on c.org_code = b.COR_ORGID
   ) g on  t1.FINPROD_ID=g.FINPROD_ID
  left join MST_CUSTOMER_INFO t3 on  g.org_code=t3.CUSTOMER_ID
  where t1.PORTFOLIO_ID in ${portfolioCode} and  
  t1.CDATE =date''${valDate}''-1 
union ALL
  --1.交易表
  select
     CASE  WHEN n.PS IN(''T01'',''T25'') THEN NVL(n.TRADE_AMT,0)
     ELSE  NVL(-1*n.TRADE_AMT,0)
     END VALUE,m.PORTFOLIO_ID,
  g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME  
  from TRD_PRODUCT_DEAL_ADD m
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
 inner join (
  select FINPROD_ID,org_code,org_code org_code_union from temp
  union all
  select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a
  inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO
  inner join temp c on c.org_code = b.COR_ORGID
 ) g on  n.FINPROD_ID=g.FINPROD_ID
  left join MST_CUSTOMER_INFO t3 on  g.org_code=t3.CUSTOMER_ID
  where m.PORTFOLIO_ID in ${portfolioCode} and 
  n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN (''T01'', ''T02'',''T07'',''T22'', ''T23'',''T25'')
    AND n.TRADE_STATUS = ''01''
    AND n.IS_CANCEL = ''N''
union ALL
  --2.交易流程表
  select
     CASE  WHEN n.PS IN(''T01'',''T25'') THEN NVL(n.TRADE_AMT,0)
     ELSE  NVL(-1*n.TRADE_AMT,0)
     END VALUE,m.PORTFOLIO_ID,
  g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME  
  from WFL_TRD_PRODUCT_DEAL_ADD m
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS = ''01''
  inner join (
 select FINPROD_ID,org_code,org_code org_code_union from temp
 union all
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO
 inner join temp c on c.org_code = b.COR_ORGID
 ) g on  n.FINPROD_ID=g.FINPROD_ID
  left join MST_CUSTOMER_INFO t3 on  g.org_code=t3.CUSTOMER_ID
  where m.PORTFOLIO_ID in ${portfolioCode} and 
  n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN (''T01'', ''T02'',''T07'', ''T22'', ''T23'',''T25'')
    AND n.IS_CANCEL = ''N''
  UNION ALL
  --3.内部卖出流程中交易表
  select
     nvl( n.TRADE_AMT, 0)   AS VALUE,m.PORTFOLIO_ID,
  g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME  
  from WFL_TRD_PRODUCT_DEAL_ADD m
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS = ''01''
  inner join (
 select FINPROD_ID,org_code,org_code org_code_union from temp
 union all
 select DISTINCT c.FINPROD_ID,a.org_code,b.COR_ORGID org_code_union from  temp a
 inner join INF_ORG_CORRELATION b on a.org_code = b.ORG_NO
 inner join temp c on c.org_code = b.COR_ORGID
 ) g on  n.FINPROD_ID=g.FINPROD_ID
  left join MST_CUSTOMER_INFO t3 on  g.org_code=t3.CUSTOMER_ID
  where n.counter_id IN ${portfolioCode}  and 
  n.TRADE_DATE=date''${valDate}'' 
    AND n.PS = ''T02''
    AND EXISTS (SELECT 1 FROM SYS_PARA WHERE PARA_CODE = ''is_auto_generate_negate_deal'' AND PARAM_VALUE = ''Y'')
    AND n.IS_CANCEL = ''N''
) targetData group by targetData.ASSETCODE, targetData.ASSETNAME';

----事后(日终)
V_CONTENT_05 CLOB := 'with temp as (select m.FINPROD_ID,m.FINPROD_TYPE2,m.FINANCIER org_code
FROM
    FIN_PRODUCT m
INNER JOIN
    FIN_PRODUCT_TYPE fpt ON  m.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0
AND  fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')
)
select
sum(nvl(targetData.VALUE, 0)) as RVALUE, targetData.ASSETCODE AS RKEY, targetData.ASSETNAME RKEYCN
from
(
select  NVL(SUM(NVL(t1.MARKET_VALUE,0)+nvl(t1.TDY_INTINCEXP, 0)),0) as  VALUE,T1.PORTFOLIO_ID,g.org_code as ASSETCODE,t3.CUSTOMER_NAME as ASSETNAME from PTL_SEC_PIERCE_VALUTION t1
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
group by targetData.ASSETCODE, targetData.ASSETNAME';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'DYGLF' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'DYGLF' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'DYGLF' and PARA_GRANULARITY = '05';
END;
/



----单一机构及关联企业非标准化债权类资产余额-买入预占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYGLF_01';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYGLF_01','单一机构及关联企业非标准化债权类资产余额-买入预占(不算指标)','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYGLF_01';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYGLF_01';

----单一机构及关联企业非标准化债权类资产余额-卖出预占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYGLF_02';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYGLF_02','单一机构及关联企业非标准化债权类资产余额-卖出预占(不算指标)','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYGLF_02';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYGLF_02';

----单一机构及关联企业非标准化债权类资产余额-买入实占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYGLF_03';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYGLF_03','单一机构及关联企业非标准化债权类资产余额-买入实占(不算指标)','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYGLF_03';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYGLF_03';

----单一机构及关联企业非标准化债权类资产余额-卖出实占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYGLF_04';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYGLF_04','单一机构及关联企业非标准化债权类资产余额-卖出实占(不算指标)','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYGLF_04';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_04','assetType','82ad585c4dee4dfba622b4b79192f8f6','','F06','=','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_04','assetType2','82ad585c4dee4dfba622b4b79192f8f6','','P01,P11','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_04','eventType','82ad585c4dee4dfba622b4b79192f8f6','','1,2','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_04','ps','82ad585c4dee4dfba622b4b79192f8f6','','T07,T08,T09,T54','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYGLF_04';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_04','01','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_04','02','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_04','03','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----单一机构及关联企业非标准化债权类资产余额-买入预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYGLF_05';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYGLF_05','单一机构及关联企业非标准化债权类资产余额-买入预占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYGLF_05';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_05','assetType','aca5d90c0d0d4901870ea69c69e7b7ba','','F06','=','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_05','assetType2','aca5d90c0d0d4901870ea69c69e7b7ba','','P01,P11','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_05','eventType','aca5d90c0d0d4901870ea69c69e7b7ba','','0','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_05','ps','aca5d90c0d0d4901870ea69c69e7b7ba','','T01','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYGLF_05';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_05','02','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----单一机构及关联企业非标准化债权类资产余额-卖出预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYGLF_06';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYGLF_06','单一机构及关联企业非标准化债权类资产余额-卖出预占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYGLF_06';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_06','assetType','bfbe37f37d3a492ba80a6a87cbae0546','','F06','=','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_06','assetType2','bfbe37f37d3a492ba80a6a87cbae0546','','P01,P11','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_06','eventType','bfbe37f37d3a492ba80a6a87cbae0546','','0','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_06','ps','bfbe37f37d3a492ba80a6a87cbae0546','','T02','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYGLF_06';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_06','03','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----单一机构及关联企业非标准化债权类资产余额-买入实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYGLF_07';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYGLF_07','单一机构及关联企业非标准化债权类资产余额-买入实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYGLF_07';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_07','assetType','5e34f913d80945b8863dae0be00d4474','','F06','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_07','assetType2','5e34f913d80945b8863dae0be00d4474','','P01,P11','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_07','eventType','5e34f913d80945b8863dae0be00d4474','','1,2','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_07','ps','5e34f913d80945b8863dae0be00d4474','','T01','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYGLF_07';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_07','01','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_07','02','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_07','03','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----单一机构及关联企业非标准化债权类资产余额-卖出实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYGLF_08';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYGLF_08','单一机构及关联企业非标准化债权类资产余额-卖出实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYGLF_08';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_08','assetType','2df22b02513742918b1ff826dd80d1f8','','F06','=','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_08','assetType2','2df22b02513742918b1ff826dd80d1f8','','P01,P11','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_08','eventType','2df22b02513742918b1ff826dd80d1f8','','1,2','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYGLF_08','ps','2df22b02513742918b1ff826dd80d1f8','','T02','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYGLF_08';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_08','01','-','mixAmount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_08','02','-','mixAmount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYGLF','EVENT_ID_DYGLF_08','03','-','mixAmount','','system','',SYSDATE,'system',SYSDATE,'','');



/**风险指标参数:单一机构及关联企业非标准化债权类资产余额**/