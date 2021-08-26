/**风险指标参数:非公开发行债券资产**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='FGKFXZQZC';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('FGKFXZQZC','非公开发行债券资产','N','','cdj','C001',sysdate,'cdj',sysdate,'','','Y','String', '','REDIS', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='FGKFXZQZC';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('FGKFXZQZC','01', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','Y','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('FGKFXZQZC','02', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','Y','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('FGKFXZQZC','03', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','Y','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('FGKFXZQZC','04', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','Y','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('FGKFXZQZC','05', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','Y','cdj','C001',sysdate,'cdj',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'select
 t1.PORTFOLIO_ID as RKEY,NVL(SUM(NVL(t1.MARKET_VALUE,0)+nvl(t1.TDY_INTINCEXP, 0)),0)  as RVALUE
from PTL_SEC_PIERCE_VALUTION t1
inner join FIN_PRODUCT t2  on t1.FINPROD_ID=t2.FINPROD_ID and t2.FINPROD_TYPE2 =''F01'' and t2.ISSUE_TYPE = ''02''
 inner join FIN_PRODUCT_TYPE fpt on t1.FINPROD_ID = fpt.FINPROD_ID and fpt.TYPE_6 != ''B17''
where  t1.CDATE = date''${valDate}''-1
group by t1.PORTFOLIO_ID
UNION all
   select t.PTL_BAG_ID as RKEY,
   ( select NVL(SUM(NVL(m.MARKET_VALUE,0)+nvl(m.TDY_INTINCEXP, 0)),0) as  RVALUE from PTL_SEC_PIERCE_VALUTION m
  inner join FIN_PRODUCT t2  on m.FINPROD_ID=t2.FINPROD_ID and t2.FINPROD_TYPE2 =''F01'' and t2.ISSUE_TYPE = ''02''
    inner join FIN_PRODUCT_TYPE fpt on m.FINPROD_ID = fpt.FINPROD_ID and fpt.TYPE_6 != ''B17''
  where    m.CDATE =date''${valDate}''-1  AND
  m.PORTFOLIO_ID in (   select t1.PORTFOLIO_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' where t1.PTL_BAG_ID=t.PTL_BAG_ID)
   )
   from RSK_PORTFOLIO_PACKAGE t';

----事后(日间)
V_CONTENT_04 CLOB := 'select sum(nvl(TRADE_AMT, 0)) as tradeAmt from
 (
 select sum(amount) as TRADE_AMT from
 (
  SELECT
  NVL(t1.MARKET_VALUE,0)+nvl(t1.TDY_INTINCEXP, 0)  as  amount
  from PTL_SEC_PIERCE_VALUTION t1
  inner join FIN_PRODUCT fp on t1.FINPROD_ID = fp.FINPROD_ID and fp.FINPROD_TYPE2 = ''F01'' and fp.ISSUE_TYPE = ''02''
  inner join FIN_PRODUCT_TYPE fpt on t1.FINPROD_ID = fpt.FINPROD_ID and fpt.TYPE_6 != ''B17''
  where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE=date''${valDate}''-1
 )
 union ALL
  select
  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT
  from TRD_PRODUCT_DEAL_ADD m inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
  inner join FIN_PRODUCT fp on n.FINPROD_ID = fp.FINPROD_ID and fp.FINPROD_TYPE2 = ''F01'' and fp.ISSUE_TYPE = ''02''
  inner join FIN_PRODUCT_TYPE fpt on n.FINPROD_ID = fpt.FINPROD_ID and fpt.TYPE_6 != ''B17''
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T07'',''T08'',''T09'',''T11'',''T52'',''T22'',''T23'')
 union ALL
  select
  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT
  from TRD_PRODUCT_TEMP_DEAL_ADD m inner join TRD_PRODUCT_TEMP_DEAL n on m.TRADE_ID=n.TRADE_ID
  inner join FIN_PRODUCT fp on n.FINPROD_ID = fp.FINPROD_ID and fp.FINPROD_TYPE2 = ''F01'' and fp.ISSUE_TYPE = ''02''
  inner join FIN_PRODUCT_TYPE fpt on n.FINPROD_ID = fpt.FINPROD_ID and fpt.TYPE_6 != ''B17''
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T07'',''T08'',''T09'',''T11'',''T52'',''T22'',''T23'')
union all
  select
   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT
  from WFL_TRD_PRODUCT_DEAL_ADD m inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
  INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
  inner join FIN_PRODUCT fp on n.FINPROD_ID = fp.FINPROD_ID and fp.FINPROD_TYPE2 = ''F01'' and fp.ISSUE_TYPE = ''02''
  inner join FIN_PRODUCT_TYPE fpt on n.FINPROD_ID = fpt.FINPROD_ID and fpt.TYPE_6 != ''B17''
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T52'')
union all
  select
  nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT
  from WFL_TRD_PRODUCT_DEAL_ADD m inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
  INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
  inner join FIN_PRODUCT fp on n.FINPROD_ID = fp.FINPROD_ID and fp.FINPROD_TYPE2 = ''F01'' and fp.ISSUE_TYPE = ''02''
  inner join FIN_PRODUCT_TYPE fpt on n.FINPROD_ID = fpt.FINPROD_ID and fpt.TYPE_6 != ''B17''
  where n.counter_id IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}''  AND n.PS = ''T02''
   AND EXISTS (SELECT 1 FROM SYS_PARA WHERE PARA_CODE = ''is_auto_generate_negate_deal'' AND PARAM_VALUE = ''Y'')
)';

----事后(日终)
V_CONTENT_05 CLOB := 'select
nvl(sum(nvl(targetData.VALUE, 0)), 0) as RVALUE
from
(
select
  NVL(SUM(NVL(t1.MARKET_VALUE,0)+nvl(t1.TDY_INTINCEXP, 0)),0)  as  VALUE from PTL_SEC_VALUTION t1
inner join FIN_PRODUCT t2  on t1.FINPROD_ID=t2.FINPROD_ID and t2.FINPROD_TYPE2 =''F01'' and t2.ISSUE_TYPE = ''02''
inner join FIN_PRODUCT_TYPE fpt on t1.FINPROD_ID = fpt.FINPROD_ID and fpt.TYPE_6 != ''B17''
where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE =date''${valDate}''
group by t1.PORTFOLIO_ID
) targetData';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'FGKFXZQZC' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'FGKFXZQZC' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'FGKFXZQZC' and PARA_GRANULARITY = '05';
END;
/



----非公开发行债券-买入预占（不计算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FGKFXZQZC_01';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FGKFXZQZC_01','非公开发行债券-买入预占（不计算指标）','N','','whmcp','2222222',sysdate,'dongzk',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_01';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_01';

----非公开发行债券-卖出预占（不计算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FGKFXZQZC_02';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FGKFXZQZC_02','非公开发行债券-卖出预占（不计算指标）','N','','whmcp','2222222',sysdate,'dongzk',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_02';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_02';

----非公开发行债券-买入实占（不计算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FGKFXZQZC_03';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FGKFXZQZC_03','非公开发行债券-买入实占（不计算指标）','N','','whmcp','2222222',sysdate,'dongzk',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_03';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_03';

----非公开发行债券-卖出实占（不计算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FGKFXZQZC_04';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FGKFXZQZC_04','非公开发行债券-卖出实占（不计算指标）','N','','whmcp','2222222',sysdate,'jjpei',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_04';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_04','assetType','189d5d6e4d7c422e9a3d851b7d41ee12','','F01,F17','IN','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_04','eventType','189d5d6e4d7c422e9a3d851b7d41ee12','','1,2','IN','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_04','issueType','189d5d6e4d7c422e9a3d851b7d41ee12','','02','=','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_04','ps','189d5d6e4d7c422e9a3d851b7d41ee12','','T07,T08,T09,T39','IN','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_04';
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_04','01','-','amount','','whmcp','2222222',SYSDATE,'whmcp',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_04','02','-','amount','','whmcp','2222222',SYSDATE,'whmcp',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_04','03','-','amount','','whmcp','2222222',SYSDATE,'whmcp',SYSDATE,'','');

----非公开发行债券-买入预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FGKFXZQZC_05';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FGKFXZQZC_05','非公开发行债券-买入预占','Y','','whmcp','2222222',sysdate,'whmcp',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_05';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_05','assetType','d6f812cee0c04824a7496402f860f000','','F01,F17','IN','','','','system','2222222',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_05','eventType','d6f812cee0c04824a7496402f860f000','','0','IN','','','','system','2222222',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_05','issueType','d6f812cee0c04824a7496402f860f000','','02','=','','','','system','2222222',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_05','ps','d6f812cee0c04824a7496402f860f000','','C1,C10,C11,C12,C13,C14,C15,C16,C17,C18,C2,C20,C21,C3,C4,C5,C6,C7,C8,C9','NO','','','','system','2222222',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_05';
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_05','02','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----非公开发行债券-卖出预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FGKFXZQZC_06';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FGKFXZQZC_06','非公开发行债券-卖出预占','Y','','whmcp','2222222',sysdate,'dongzk',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_06';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_06','assetType','d8001116711849918f0f87fe4cf90ef9','','F01,F17','IN','','','','system','2222222',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_06','eventType','d8001116711849918f0f87fe4cf90ef9','','0','IN','','','','system','2222222',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_06','issueType','d8001116711849918f0f87fe4cf90ef9','','02','=','','','','system','2222222',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_06','ps','d8001116711849918f0f87fe4cf90ef9','','T02,T03','IN','','','','system','2222222',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_06';
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_06','03','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----非公开发行债券-买入实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FGKFXZQZC_07';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FGKFXZQZC_07','非公开发行债券-买入实占','Y','','whmcp','2222222',sysdate,'jjpei',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_07';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_07','assetType','d6f812cee0c04824a7496402f860f000','','F01,F17','IN','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_07','eventType','d6f812cee0c04824a7496402f860f000','','1,2','IN','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_07','issueType','d6f812cee0c04824a7496402f860f000','','02','=','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_07','ps','d6f812cee0c04824a7496402f860f000','','T01','=','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_07';
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_07','01','+','amount','','whmcp','2222222',SYSDATE,'whmcp',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_07','02','+','amount','','whmcp','2222222',SYSDATE,'whmcp',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_07','03','+','amount','','whmcp','2222222',SYSDATE,'whmcp',SYSDATE,'','');

----非公开发行债券-卖出实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FGKFXZQZC_08';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FGKFXZQZC_08','非公开发行债券-卖出实占','Y','','whmcp','2222222',sysdate,'jjpei',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_08';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_08','assetType','d8001116711849918f0f87fe4cf90ef9','','F01,F17','IN','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_08','eventType','d8001116711849918f0f87fe4cf90ef9','','1,2','IN','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_08','issueType','d8001116711849918f0f87fe4cf90ef9','','02','=','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FGKFXZQZC_08','ps','d8001116711849918f0f87fe4cf90ef9','','T02,T03','IN','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FGKFXZQZC_08';
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_08','01','-','mixAmount','','whmcp','2222222',SYSDATE,'whmcp',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_08','02','-','mixAmount','','whmcp','2222222',SYSDATE,'whmcp',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('FGKFXZQZC','EVENT_ID_FGKFXZQZC_08','03','-','mixAmount','','whmcp','2222222',SYSDATE,'whmcp',SYSDATE,'','');



/**风险指标参数:非公开发行债券资产**/