/**风险指标参数:产品总资产**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='CPZZC';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('CPZZC','产品总资产','N','','ysfu','C001',sysdate,'ysfu',sysdate,'','','Y','String', '','', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='CPZZC';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPZZC','01', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPZZC','02', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPZZC','03', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPZZC','04', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPZZC','05', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'WITH  temp  as(
    SELECT
       DISTINCT t2.PORTFOLIO_ID ,
       mujiData.mujiAmt  as AMOUNT
       from  PTL_PORTFOLIO t2
       left join
       (
       select t1.PORTFOLIO_ID, sum(t1.TRADE_AMT) as mujiAmt from TRD_FIN_PRODUCT_DEAL_TOTAL t1
       WHERE
           t1.PS in (''C1'', ''C2'')
           and t1.SETTLE_DATE <= date''${valDate}'' and t1.TRADE_STATUS=''01'' and nvl(t1.IS_CANCEL,''N'') =''N''
       group by t1.PORTFOLIO_ID
       ) mujiData
       on t2.PORTFOLIO_ID = mujiData.PORTFOLIO_ID
       where  t2.VDATE=date''${valDate}''
   UNION ALL
      SELECT
       DISTINCT t1.PORTFOLIO_ID RKEY,
      nvl(TDY_ASSET_VALUE,0) as RVALUE
       from PTL_PORTFOLIO_VALUATION t1
       where  t1.CDATE=date''${valDate}''-1
   )
     SELECT PORTFOLIO_ID RKEY,AMOUNT RVALUE FROM temp
  union all
    select m.PTL_BAG_ID as RKEY,
     (
      SELECT sum(AMOUNT) FROM (
       SELECT PORTFOLIO_ID,AMOUNT FROM temp
       ) t1 where t1.PORTFOLIO_ID in (select t1.PORTFOLIO_ID from RSK_PORTFOLIO_PACKAGE_DTL t1
       inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' where t1.PTL_BAG_ID=m.PTL_BAG_ID)
     ) AS RVALUE
     from RSK_PORTFOLIO_PACKAGE m';

----事后(日间)
V_CONTENT_04 CLOB := 'select sum(amount) AS amount from
  (
     select sum(amount) as amount
     from
     (
     SELECT
       DISTINCT t2.PORTFOLIO_ID ,
       mujiData.mujiAmt  as AMOUNT
       from  PTL_PORTFOLIO t2
       inner join
       (
       select t1.PORTFOLIO_ID, sum(t1.TRADE_AMT) as mujiAmt from TRD_FIN_PRODUCT_DEAL_TOTAL t1
       WHERE  t1.PS in (''C1'', ''C2'')   and t1.SETTLE_DATE <= date''${valDate}'' AND t1.TRADE_STATUS=''01'' and nvl(t1.IS_CANCEL,''N'') =''N''  AND t1.TRADE_DATE  < date''${valDate}''
       group by t1.PORTFOLIO_ID
       ) mujiData
       on t2.PORTFOLIO_ID = mujiData.PORTFOLIO_ID
       where  t2.VDATE=date''${valDate}'' and t2.PORTFOLIO_ID in ${portfolioCode}
     UNION ALL
      SELECT
       DISTINCT t1.PORTFOLIO_ID RKEY,
      nvl(TDY_ASSET_VALUE,0) as RVALUE
       from PTL_PORTFOLIO_VALUATION t1
       where  t1.CDATE=date''${valDate}''-1 and t1.PORTFOLIO_ID in ${portfolioCode}
     )
    union all
    --1.产品端交易表
    select
     CASE  WHEN n.PS IN(''C1'',''C2'',''C3'',''C10'',''C15'',''T98'') THEN NVL(n.TRADE_AMT,0)
     ELSE  NVL(-1*n.TRADE_AMT,0)
     END amount
    from TRD_PRODUCT_DEAL_ADD m
    inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
    where m.PORTFOLIO_ID in ${portfolioCode}  and 
    n.TRADE_DATE=date''${valDate}'' 
    AND  n.PS IN (''C1'', ''C2'', ''C3'', ''C4'', ''C5'', ''C6'', ''C7'', ''C9'', ''C10'', ''C14'', ''C15'', ''C16'', ''C17'',''C18'', ''C19'',''C20'',''T98'',''T99'')
    AND n.TRADE_STATUS = ''01''
    AND n.IS_CANCEL = ''N''

     union ALL
    --2.回购交易
    select 
     CASE  WHEN n.PS =''T01'' THEN NVL(n.TRADE_AMT,0)
     ELSE  NVL(-1*n.TRADE_AMT,0)
     END amount
     from TRD_PRODUCT_DEAL_ADD m
    inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 in (''F02'',''F08'',''F25'',''F04'')
    where  m.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T07'',''T54'',''T55'',''T56'')
    AND n.TRADE_STATUS = ''01''
    AND n.IS_CANCEL = ''N''

    union all
  --3.日间交易表（场内交易：回购）
    select 
     CASE  WHEN n.PS =''T01'' THEN NVL(n.TRADE_AMT,0)
     ELSE  NVL(-1*n.TRADE_AMT,0)
     END amount
    from TRD_PRODUCT_TEMP_DEAL_ADD m
    inner join TRD_PRODUCT_TEMP_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 in (''F02'',''F08'',''F25'',''F04'')
    where  m.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T07'',''T22'',''T23'',''T54'',''T55'',''T56'',''T93'',''T95'',''T96'')
    AND n.TRADE_STATUS = ''01''
    AND n.IS_CANCEL = ''N''

     union ALL
  --4.回购交易流程表
    select 
     NVL(n.TRADE_AMT,0) amount
     from WFL_TRD_PRODUCT_DEAL_ADD m
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.FINPROD_TYPE2 in (''F02'',''F08'',''F25'',''F04'')
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    where  m.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' and n.PS =''T01''
    AND n.TRADE_STATUS = ''01''
    AND n.IS_CANCEL = ''N''
  )';

----事后(日终)
V_CONTENT_05 CLOB := 'SELECT NVL(SUM(TDY_ASSET_VALUE),0) AS amount
  FROM PTL_PORTFOLIO_VALUATION
  WHERE PORTFOLIO_ID IN ${portfolioCode} AND CDATE = date''${valDate}''';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'CPZZC' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'CPZZC' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'CPZZC' and PARA_GRANULARITY = '05';
END;
/



----产品总资产-买入预占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZZC_01';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZZC_01','产品总资产-买入预占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZZC_01';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZZC_01';

----产品总资产-卖出预占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZZC_02';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZZC_02','产品总资产-卖出预占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZZC_02';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZZC_02';

----产品总资产-买入实占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZZC_03';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZZC_03','产品总资产-买入实占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZZC_03';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_03','eventType','9e6294bc5ea44f36ac1fee9165f18774','','1,2','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_03','ps','9e6294bc5ea44f36ac1fee9165f18774','','C1,C2,C3,T98','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZZC_03';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_03','01','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_03','02','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_03','03','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----产品总资产-卖出实占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZZC_04';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZZC_04','产品总资产-卖出实占（不算指标）','N','','system','',sysdate,'cdj',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZZC_04';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_04','assetType','25790b3bc3d640d29cf76168caaefe3b','','F07,F08,F10','NO','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_04','ps','25790b3bc3d640d29cf76168caaefe3b','','C4,C5,C6,C7,C9,C14,C16,C17,C18,C19,C20,T99,C15','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_04','eventType','25790b3bc3d640d29cf76168caaefe3b','','1,2','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_04','assetType','a455b23ea872437c991ced39767774fd','','F07,F08,F10','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_04','assetType2','a455b23ea872437c991ced39767774fd','','01,03','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_04','eventType','a455b23ea872437c991ced39767774fd','','1,2','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_04','ps','a455b23ea872437c991ced39767774fd','','T07,T54,T56,T55,T93,T95,T96','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZZC_04';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_04','01','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_04','02','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_04','03','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----产品总资产-买入预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZZC_05';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZZC_05','产品总资产-买入预占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZZC_05';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_05','assetType','292675d070974a0fb8bac7abcbe30158','','F07,F08,F10','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_05','assetType2','292675d070974a0fb8bac7abcbe30158','','01,03','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_05','eventType','292675d070974a0fb8bac7abcbe30158','','0','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_05','ps','292675d070974a0fb8bac7abcbe30158','','T01','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZZC_05';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_05','02','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----产品总资产-卖出预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZZC_06';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZZC_06','产品总资产-卖出预占','Y','','system','',sysdate,'admin',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZZC_06';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZZC_06';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_06','03','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----产品总资产-买入实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZZC_07';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZZC_07','产品总资产-买入实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZZC_07';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_07','assetType','292675d070974a0fb8bac7abcbe30158','','F07,F08,F10','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_07','assetType2','292675d070974a0fb8bac7abcbe30158','','01,03','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_07','eventType','292675d070974a0fb8bac7abcbe30158','','1,2','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZZC_07','ps','292675d070974a0fb8bac7abcbe30158','','T01','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZZC_07';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_07','01','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_07','02','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_07','03','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----产品总资产-卖出实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZZC_08';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZZC_08','产品总资产-卖出实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZZC_08';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZZC_08';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_08','01','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_08','02','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZZC','EVENT_ID_CPZZC_08','03','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');



/**风险指标参数:产品总资产**/