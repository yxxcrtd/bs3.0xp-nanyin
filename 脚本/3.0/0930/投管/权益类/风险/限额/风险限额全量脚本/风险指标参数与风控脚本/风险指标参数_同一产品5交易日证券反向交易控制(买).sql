/**风险指标参数:同一产品5交易日证券反向交易控制(买)**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='TYCPFIVEJYRZQFXBUY';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('TYCPFIVEJYRZQFXBUY','同一产品5交易日证券反向交易控制(买)','N','','ysfu','C001',sysdate,'ysfu',sysdate,'','','N','String', '','DATABASE', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='TYCPFIVEJYRZQFXBUY';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('TYCPFIVEJYRZQFXBUY','01', 'SQL','SELECT 1 FROM DUAL', '','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('TYCPFIVEJYRZQFXBUY','02', 'SQL','SELECT 1 FROM DUAL', '','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('TYCPFIVEJYRZQFXBUY','03', 'SQL','SELECT 1 FROM DUAL', '','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('TYCPFIVEJYRZQFXBUY','04', 'SQL','SELECT 1 FROM DUAL', '','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('TYCPFIVEJYRZQFXBUY','05', 'SQL','SELECT 1 FROM DUAL', '','N','ysfu','C001',sysdate,'ysfu',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'WITH temp AS ( SELECT s.FINPROD_ID, s.CDATE
                  FROM ( SELECT m.FINPROD_ID, n.CDATE, ROW_NUMBER() OVER(PARTITION BY m.FINPROD_ID ORDER BY n.CDATE DESC) num
                       FROM( SELECT FINPROD_ID,
                       CASE  WHEN CALENDAR_ID IS NULL THEN ''01'' 
                       ELSE CALENDAR_ID END CALENDAR_ID
                         FROM FIN_PRODUCT WHERE FINPROD_ID=${assetCode}) m
                         INNER JOIN  MST_HOLIDAY n
                       ON m.CALENDAR_ID=n.CALENDAR_ID
                       AND n.CDATE<=DATE''${valDate}''
                       AND n.CDATE>=DATE''${valDate}''-10
                       AND n.IS_HOLIDAY=0) s
                      WHERE s.num<=5)
SELECT DISTINCT nvl(max(RVALUE),0) RVALUE  FROM (
 --1.交易表
           SELECT t2.UNIT_CPRICE  RVALUE
           FROM TRD_PRODUCT_DEAL_ADD t1
           INNER JOIN TRD_PRODUCT_DEAL t2
           ON t1.TRADE_ID=t2.TRADE_ID AND t2.TRADE_STATUS =''01'' AND NVL(t2.IS_CANCEL,''N'') =''N''
           INNER JOIN FIN_PRODUCT m
           ON  m.FINPROD_ID=t2.FINPROD_ID
           INNER JOIN temp p
           ON t2.FINPROD_ID = p.FINPROD_ID
           AND t2.TRADE_DATE=p.CDATE
           WHERE  (
          (t2.PS IN (''T01'',''T46'',''T47'',''T48'',''T04'',''T50'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F10'')
          )
        AND t1.PORTFOLIO_ID IN ${portfolioCode} and t2.FINPROD_ID=${assetCode}
UNION all
 --2.日间交易表
           SELECT t2.UNIT_CPRICE  RVALUE
           FROM TRD_PRODUCT_TEMP_DEAL_ADD t1
           INNER JOIN TRD_PRODUCT_TEMP_DEAL t2
           ON t1.TRADE_ID=t2.TRADE_ID AND t2.TRADE_STATUS =''01'' AND NVL(t2.IS_CANCEL,''N'') =''N''
           INNER JOIN FIN_PRODUCT m
           ON  m.FINPROD_ID=t2.FINPROD_ID
           INNER JOIN temp p
           ON t2.FINPROD_ID = p.FINPROD_ID
           AND t2.TRADE_DATE=p.CDATE
           WHERE  (
          (t2.PS IN (''T01'',''T46'',''T47'',''T48'',''T04'',''T50'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F10'')
          )
        AND t1.PORTFOLIO_ID IN ${portfolioCode} and t2.FINPROD_ID=${assetCode}
UNION all
--3.1流程交易表
SELECT t2.UNIT_CPRICE RVALUE
           FROM WFL_TRD_PRODUCT_DEAL_ADD t1
           INNER JOIN WFL_TRD_PRODUCT_DEAL t2
           ON t1.TRADE_ID=t2.TRADE_ID AND t2.TRADE_STATUS =''01'' AND NVL(t2.IS_CANCEL,''N'') =''N''
           INNER JOIN SYS_WFL_BUSINESS_LIST swbl on t2.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
           INNER JOIN FIN_PRODUCT m
           ON  m.FINPROD_ID=t2.FINPROD_ID
           INNER JOIN temp p
           ON t2.FINPROD_ID = p.FINPROD_ID
           AND t2.TRADE_DATE=p.CDATE
           WHERE (
          (t2.PS IN (''T01'',''T46'',''T47'',''T48'',''T04'',''T50'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F10'')
          )
           AND t1.PORTFOLIO_ID IN ${portfolioCode} and t2.FINPROD_ID=${assetCode}
           )';

----事后(日间)
V_CONTENT_04 CLOB := 'WITH TEMP AS (SELECT s.FINPROD_ID, s.CDATE
                           FROM ( SELECT m.FINPROD_ID, n.CDATE, ROW_NUMBER() OVER(PARTITION BY m.FINPROD_ID ORDER BY n.CDATE DESC) num
                                   FROM( SELECT FINPROD_ID,CASE  WHEN CALENDAR_ID IS NULL THEN ''01'' ELSE CALENDAR_ID END CALENDAR_ID
                                          FROM FIN_PRODUCT WHERE FINPROD_TYPE2 IN (''F01'',''F32'',''F10'')) m
                                          INNER JOIN  MST_HOLIDAY n
                                          ON m.CALENDAR_ID=n.CALENDAR_ID
                                          AND n.CDATE<=DATE''${valDate}''
                      AND n.CDATE>=DATE''${valDate}''-10
                                          AND n.IS_HOLIDAY=0) s
                         WHERE s.num<=5)
SELECT COUNT(1) AS RVALUE,FINPROD_ID AS RKEY,FINPROD_NAME AS RKEYCN
FROM(SELECT A.PORTFOLIO_ID,B.FINPROD_ID,A.FINPROD_NAME, A.MAX_BUY, B.MIN_SELL
       FROM(SELECT PORTFOLIO_ID, FINPROD_ID , FINPROD_NAME, MAX(UNIT_CPRICE) AS MAX_BUY
              FROM ( SELECT t1.PORTFOLIO_ID, t2.FINPROD_ID , m.FINPROD_NAME,t2.UNIT_CPRICE
                         FROM TRD_PRODUCT_DEAL_ADD t1
                         INNER JOIN TRD_PRODUCT_DEAL t2
                         ON t1.TRADE_ID=t2.TRADE_ID
                         INNER JOIN FIN_PRODUCT m
                         ON  m.FINPROD_ID=t2.FINPROD_ID
                         INNER JOIN TEMP p
                         ON t2.FINPROD_ID = p.FINPROD_ID
                         AND t2.TRADE_DATE=p.CDATE
                         WHERE  (
          (t2.PS IN (''T01'',''T46'',''T47'',''T48'',''T04'',''T50'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F10'')
          )
          AND t2.TRADE_STATUS=''01''
                         AND NVL(t2.IS_CANCEL,''N'') =''N''
                         AND t1.PORTFOLIO_ID IN ${portfolioCode}
                         UNION ALL
             SELECT t1.PORTFOLIO_ID, t2.FINPROD_ID , m.FINPROD_NAME,t2.UNIT_CPRICE
                         FROM TRD_PRODUCT_TEMP_DEAL_ADD t1
                         INNER JOIN TRD_PRODUCT_TEMP_DEAL t2
                         ON t1.TRADE_ID=t2.TRADE_ID
                         INNER JOIN FIN_PRODUCT m
                         ON  m.FINPROD_ID=t2.FINPROD_ID
                         INNER JOIN TEMP p
                         ON t2.FINPROD_ID = p.FINPROD_ID
                         AND t2.TRADE_DATE=p.CDATE
                         WHERE (
          (t2.PS IN (''T01'',''T46'',''T47'',''T48'',''T04'',''T50'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F10'')
          )
          AND t2.TRADE_STATUS=''01''
                         AND NVL(t2.IS_CANCEL,''N'') =''N''
                         AND t1.PORTFOLIO_ID IN ${portfolioCode}
                         UNION ALL
                         SELECT t1.PORTFOLIO_ID, t2.FINPROD_ID , m.FINPROD_NAME, t2.UNIT_CPRICE
                         FROM WFL_TRD_PRODUCT_DEAL_ADD t1
                         INNER JOIN  WFL_TRD_PRODUCT_DEAL t2
                         ON t1.TRADE_ID=t2.TRADE_ID
                         AND t2.TRADE_STATUS =''01''
               INNER JOIN SYS_WFL_BUSINESS_LIST swbl on t2.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
                         INNER JOIN  FIN_PRODUCT m
                         ON m.FINPROD_ID=t2.FINPROD_ID
                         INNER JOIN TEMP p
                         ON t2.FINPROD_ID = p.FINPROD_ID
                         AND t2.TRADE_DATE=p.CDATE
                         WHERE (
          (t2.PS IN (''T01'',''T46'',''T47'',''T48'',''T04'',''T50'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F10'')
          )
           AND t1.PORTFOLIO_ID IN ${portfolioCode})
          GROUP BY PORTFOLIO_ID,FINPROD_ID,FINPROD_NAME) A
             INNER JOIN
            (SELECT PORTFOLIO_ID, FINPROD_ID , FINPROD_NAME, MIN(UNIT_CPRICE) AS MIN_SELL
               FROM ( SELECT t1.PORTFOLIO_ID, t2.FINPROD_ID , m.FINPROD_NAME,t2.UNIT_CPRICE
                         FROM TRD_PRODUCT_DEAL_ADD t1
                         INNER JOIN TRD_PRODUCT_DEAL t2
                         ON t1.TRADE_ID=t2.TRADE_ID
                         INNER JOIN FIN_PRODUCT m
                         ON  m.FINPROD_ID=t2.FINPROD_ID
                         INNER JOIN TEMP p
                         ON t2.FINPROD_ID = p.FINPROD_ID
                         AND t2.TRADE_DATE=p.CDATE
                         WHERE  (
          (t2.PS IN (''T02'',''T03'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F01'')
          )
          AND t2.TRADE_STATUS=''01''
                         AND NVL(t2.IS_CANCEL,''N'') =''N''
                         AND t1.PORTFOLIO_ID IN ${portfolioCode}
                         UNION ALL
             SELECT t1.PORTFOLIO_ID, t2.FINPROD_ID , m.FINPROD_NAME,t2.UNIT_CPRICE
                         FROM TRD_PRODUCT_TEMP_DEAL_ADD t1
                         INNER JOIN TRD_PRODUCT_TEMP_DEAL t2
                         ON t1.TRADE_ID=t2.TRADE_ID
                         INNER JOIN FIN_PRODUCT m
                         ON  m.FINPROD_ID=t2.FINPROD_ID
                         INNER JOIN TEMP p
                         ON t2.FINPROD_ID = p.FINPROD_ID
                         AND t2.TRADE_DATE=p.CDATE
                         WHERE  (
          (t2.PS IN (''T02'',''T03'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F01'')
          )
           AND t2.TRADE_STATUS=''01''
                         AND NVL(t2.IS_CANCEL,''N'') =''N''
                         AND t1.PORTFOLIO_ID IN ${portfolioCode}
                         UNION ALL
                         SELECT t1.PORTFOLIO_ID, t2.FINPROD_ID , m.FINPROD_NAME, t2.UNIT_CPRICE
                         FROM WFL_TRD_PRODUCT_DEAL_ADD t1
                         INNER JOIN  WFL_TRD_PRODUCT_DEAL t2
                         ON t1.TRADE_ID=t2.TRADE_ID
                         AND t2.TRADE_STATUS =''01''
               INNER JOIN SYS_WFL_BUSINESS_LIST swbl on t2.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
                         INNER JOIN  FIN_PRODUCT m
                         ON m.FINPROD_ID=t2.FINPROD_ID
                         INNER JOIN  TEMP p
                         ON t2.FINPROD_ID = p.FINPROD_ID
                         AND t2.TRADE_DATE=p.CDATE
                         WHERE  (
          (t2.PS IN (''T02'',''T03'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F01'')
          )
          AND t1.PORTFOLIO_ID IN ${portfolioCode})
         GROUP BY PORTFOLIO_ID,FINPROD_ID,FINPROD_NAME) B
        ON A.PORTFOLIO_ID = B.PORTFOLIO_ID
        AND A.FINPROD_ID =B.FINPROD_ID
        AND A.MAX_BUY>B.MIN_SELL )
GROUP BY  FINPROD_ID, FINPROD_NAME';

----事后(日终)
V_CONTENT_05 CLOB := 'WITH TEMP AS (SELECT s.FINPROD_ID, s.CDATE
                           FROM ( SELECT m.FINPROD_ID, n.CDATE, ROW_NUMBER() OVER(PARTITION BY m.FINPROD_ID ORDER BY n.CDATE DESC) num
                                   FROM( SELECT FINPROD_ID,CASE  WHEN CALENDAR_ID IS NULL THEN ''01'' ELSE CALENDAR_ID END CALENDAR_ID
                                          FROM FIN_PRODUCT WHERE FINPROD_TYPE2 IN (''F01'',''F32'',''F10'')) m
                                          INNER JOIN  MST_HOLIDAY n
                                          ON m.CALENDAR_ID=n.CALENDAR_ID
                                          AND n.CDATE<=DATE''${valDate}''
                      AND n.CDATE>=DATE''${valDate}''-10
                                          AND n.IS_HOLIDAY=0) s
                         WHERE s.num<=5)
SELECT COUNT(1) AS RVALUE,FINPROD_ID AS RKEY,FINPROD_NAME AS RKEYCN
FROM(SELECT A.PORTFOLIO_ID,B.FINPROD_ID,A.FINPROD_NAME, A.MAX_BUY, B.MIN_SELL
       FROM(SELECT PORTFOLIO_ID, FINPROD_ID , FINPROD_NAME, MAX(UNIT_CPRICE) AS MAX_BUY
              FROM ( SELECT t1.PORTFOLIO_ID, t2.FINPROD_ID , m.FINPROD_NAME,t2.UNIT_CPRICE
           FROM TRD_PRODUCT_DEAL_ADD t1
           INNER JOIN TRD_PRODUCT_DEAL t2
           ON t1.TRADE_ID=t2.TRADE_ID
           INNER JOIN FIN_PRODUCT m
           ON  m.FINPROD_ID=t2.FINPROD_ID
           INNER JOIN TEMP p
           ON t2.FINPROD_ID = p.FINPROD_ID
           AND t2.TRADE_DATE=p.CDATE
           WHERE (
          (t2.PS IN (''T01'',''T46'',''T47'',''T48'',''T04'',''T50'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F10'')
          )
           AND t2.TRADE_STATUS =''01''
                     AND NVL(t2.IS_CANCEL,''N'') =''N''
           AND t1.PORTFOLIO_ID IN ${portfolioCode})
       GROUP BY PORTFOLIO_ID,FINPROD_ID,FINPROD_NAME) A
        INNER JOIN
            (SELECT PORTFOLIO_ID, FINPROD_ID , FINPROD_NAME, MIN(UNIT_CPRICE) AS MIN_SELL
              FROM ( SELECT t1.PORTFOLIO_ID, t2.FINPROD_ID , m.FINPROD_NAME,t2.UNIT_CPRICE
           FROM TRD_PRODUCT_DEAL_ADD t1
           INNER JOIN TRD_PRODUCT_DEAL t2
           ON t1.TRADE_ID=t2.TRADE_ID
           INNER JOIN FIN_PRODUCT m
           ON  m.FINPROD_ID=t2.FINPROD_ID
           INNER JOIN( SELECT s.FINPROD_ID, s.CDATE
                  FROM ( SELECT m.FINPROD_ID, n.CDATE, ROW_NUMBER() OVER(PARTITION BY m.FINPROD_ID ORDER BY n.CDATE DESC) num
                       FROM( SELECT FINPROD_ID,CASE  WHEN CALENDAR_ID IS NULL THEN ''01'' ELSE CALENDAR_ID END CALENDAR_ID
                         FROM FIN_PRODUCT) m
                         INNER JOIN  MST_HOLIDAY n
                       ON m.CALENDAR_ID=n.CALENDAR_ID
                       AND n.CDATE<=DATE''${valDate}''
                       AND n.IS_HOLIDAY=0) s
                      WHERE s.num<=5) p
           ON t2.FINPROD_ID = p.FINPROD_ID
           AND t2.TRADE_DATE=p.CDATE
           WHERE   (
          (t2.PS IN (''T02'',''T03'') AND m.FINPROD_TYPE2 IN (''F01'',''F32'',''F10''))OR
          (t2.PS =''T49''AND m.FINPROD_TYPE2 =''F01'')
          )
          AND t2.TRADE_STATUS =''01''
                     AND NVL(t2.IS_CANCEL,''N'') =''N''
           AND t1.PORTFOLIO_ID IN ${portfolioCode})
       GROUP BY PORTFOLIO_ID,FINPROD_ID,FINPROD_NAME) B
        ON A.PORTFOLIO_ID = B.PORTFOLIO_ID
        AND A.FINPROD_ID =B.FINPROD_ID
        AND A.MAX_BUY>B.MIN_SELL )
GROUP BY  FINPROD_ID, FINPROD_NAME';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'TYCPFIVEJYRZQFXBUY' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'TYCPFIVEJYRZQFXBUY' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'TYCPFIVEJYRZQFXBUY' and PARA_GRANULARITY = '05';
END;
/



----同一产品5交易日证券反向交易控制(买)-买入预占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_TYCPFIVEJYRZQFXBUY_01';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_TYCPFIVEJYRZQFXBUY_01','同一产品5交易日证券反向交易控制(买)-买入预占(不算指标)','N','','admin','001',sysdate,'whmcp',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_01';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_01';

----同一产品5交易日证券反向交易控制(买)-卖出预占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_TYCPFIVEJYRZQFXBUY_02';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_TYCPFIVEJYRZQFXBUY_02','同一产品5交易日证券反向交易控制(买)-卖出预占(不算指标)','N','','admin','001',sysdate,'whmcp',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_02';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_02';

----同一产品5交易日证券反向交易控制(买)-买入实占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_TYCPFIVEJYRZQFXBUY_03';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_TYCPFIVEJYRZQFXBUY_03','同一产品5交易日证券反向交易控制(买)-买入实占(不算指标)','N','','admin','001',sysdate,'whmcp',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_03';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_03';

----同一产品5交易日证券反向交易控制(买)-卖出实占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_TYCPFIVEJYRZQFXBUY_04';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_TYCPFIVEJYRZQFXBUY_04','同一产品5交易日证券反向交易控制(买)-卖出实占(不算指标)','N','','admin','001',sysdate,'whmcp',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_04';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_04';

----同一产品5交易日证券反向交易控制(买)-买入预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_TYCPFIVEJYRZQFXBUY_05';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_TYCPFIVEJYRZQFXBUY_05','同一产品5交易日证券反向交易控制(买)-买入预占','Y','','admin','001',sysdate,'whmcp',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_05';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_05';

----同一产品5交易日证券反向交易控制(买)-卖出预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_TYCPFIVEJYRZQFXBUY_06';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_TYCPFIVEJYRZQFXBUY_06','同一产品5交易日证券反向交易控制(买)-卖出预占','Y','','admin','001',sysdate,'whmcp',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_06';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_06';

----同一产品5交易日证券反向交易控制(买)-买入实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_TYCPFIVEJYRZQFXBUY_07';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_TYCPFIVEJYRZQFXBUY_07','同一产品5交易日证券反向交易控制(买)-买入实占','Y','','admin','001',sysdate,'whmjyy',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_07';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_TYCPFIVEJYRZQFXBUY_07','assetType','6f6a8c7e624c4276acd1a4b30c40b752','','F01,F11,F17','IN','','','','whmjyy','1234',SYSDATE,'yy2',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_TYCPFIVEJYRZQFXBUY_07','eventType','6f6a8c7e624c4276acd1a4b30c40b752','','1,2','IN','','','','whmjyy','1234',SYSDATE,'yy2',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_TYCPFIVEJYRZQFXBUY_07','ps','6f6a8c7e624c4276acd1a4b30c40b752','','T01,T46,T47,T48,T04','IN','','','','whmjyy','1234',SYSDATE,'yy2',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_07';
INSERT INTO RSK_EVENT_PARA_REL VALUES('TYCPFIVEJYRZQFXBUY','EVENT_ID_TYCPFIVEJYRZQFXBUY_07','01','MAX','netPrice','','jjpei','99999',SYSDATE,'jjpei',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('TYCPFIVEJYRZQFXBUY','EVENT_ID_TYCPFIVEJYRZQFXBUY_07','02','MAX','netPrice','','jjpei','99999',SYSDATE,'jjpei',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('TYCPFIVEJYRZQFXBUY','EVENT_ID_TYCPFIVEJYRZQFXBUY_07','03','MAX','netPrice','','jjpei','99999',SYSDATE,'jjpei',SYSDATE,'','');

----同一产品5交易日证券反向交易控制(买)-卖出实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_TYCPFIVEJYRZQFXBUY_08';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_TYCPFIVEJYRZQFXBUY_08','同一产品5交易日证券反向交易控制(买)-卖出实占','Y','','admin','001',sysdate,'whmcp',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_08';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_TYCPFIVEJYRZQFXBUY_08';



/**风险指标参数:同一产品5交易日证券反向交易控制(买)**/