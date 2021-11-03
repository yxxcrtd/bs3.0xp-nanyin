DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='LDXSXZCGZ';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('LDXSXZCGZ','01', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('LDXSXZCGZ','02', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('LDXSXZCGZ','03', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('LDXSXZCGZ','04', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('LDXSXZCGZ','05', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'WITH PROD AS (select distinct FINPROD_ID from (
    -- 资产到期向前第10个交易日
    SELECT FINPROD_ID FROM (
        SELECT FINPROD_ID, CDATE, ROW_NUMBER() OVER(PARTITION BY FINPROD_ID ORDER BY CDATE asc) RN FROM (
            SELECT m.FINPROD_ID, MH.CDATE, ROW_NUMBER() OVER(PARTITION BY FINPROD_ID ORDER BY CDATE desc) ROWNO FROM (
                -- 存款类资产
                SELECT FPT.FINPROD_ID,FPT.MDATE,NVL(FPT.CALENDAR_ID,''01'') CALENDAR_ID FROM FIN_PRODUCT FPT
                LEFT JOIN FIN_PRODUCT_TYPE T1 ON FPT.FINPROD_ID = T1.FINPROD_ID 
				INNER JOIN FIN_PRODUCT_ADD FPA ON FPA.FINPROD_ID = FPT.FINPROD_ID
                WHERE FPT.MDATE IS NOT NULL 
				-- 是否可提前支取条件
				AND FPA.IS_INADVANCE in (''01'',''02'')
				AND T1.TYPE_8 = ''F05'' AND FPT.MDATE >= DATE ''${valDate}'' - 1
                UNION ALL
                -- 逆回购
                SELECT FTP.FINPROD_ID,FTP.MDATE,NVL(FTP.CALENDAR_ID,''01'') CALENDAR_ID FROM FIN_TRADE_PRODUCT FTP
                LEFT JOIN FIN_PRODUCT_TYPE T1 ON FTP.FINPROD_ID = T1.FINPROD_ID 
                WHERE FTP.MDATE IS NOT NULL AND T1.TYPE_8 IN (''F07'',''F10'') AND TYPE_7 = ''04'' AND FTP.MDATE >= DATE ''${valDate}'' - 1
            ) m
            INNER JOIN MST_HOLIDAY MH ON MH.CALENDAR_ID = m.CALENDAR_ID AND m.MDATE >= MH.CDATE  AND MH.IS_HOLIDAY = ''0''
        ) WHERE ROWNO <= 10
    ) WHERE RN = 1 AND CDATE > DATE ''${valDate}''
  UNION ALL
    -- 资产支持证券
    SELECT FPT.FINPROD_ID FROM FIN_PRODUCT FPT
    LEFT JOIN FIN_PRODUCT_TYPE T1 ON FPT.FINPROD_ID = T1.FINPROD_ID 
    WHERE FPT.MDATE IS NOT NULL AND T1.TYPE_8 = ''F01'' AND TYPE_6 = ''B13'' AND FPT.MDATE >= DATE ''${valDate}'' - 1
  UNION ALL
    -- 流动性受限债券
    SELECT FPT.FINPROD_ID FROM FIN_PRODUCT_TYPE FPT
    WHERE EXISTS (
        SELECT 1 FROM RSK_SPECIFIC_ASSET_TYPE RSAT WHERE RSAT.IS_FLOW_LIMITED = ''Y'' AND (
                  (RSAT.TYPE_2 is not null and RSAT.TYPE_3 is null and RSAT.TYPE_2 = FPT.TYPE_9)
              OR  (RSAT.TYPE_3 is not null and RSAT.TYPE_4 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10 )
              OR  (RSAT.TYPE_4 is not null and RSAT.TYPE_5 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 = FPT.TYPE_11 )
              OR  (RSAT.TYPE_5 is not null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 =  FPT.TYPE_11  and RSAT.TYPE_5= FPT.TYPE_12)
              OR
               FPT.FINPROD_ID = RSAT.FINPROD_ID
              OR (REGEXP_INSTR( (select wm_concat(t.item_code) from sys_datadict_list_link t
              where t.linked_dict_code=''ASSET_CLASS'' and t.dict_code=''CUSTOM_ASSET_TYPE_TWO'' AND TRIM(t.linked_item_code)= RSAT.TYPE_1),FPT.TYPE_9)>=1
              and RSAT.TYPE_1 is not null and RSAT.TYPE_2 is null )
              )
              and FPT.TYPE_9 is not null
    )AND NOT EXISTS(
         SELECT 1 FROM RSK_SPECIFIC_ASSET_TYPE RSAT WHERE RSAT.IS_FLOW_LIMITED = ''N'' AND (
                  (RSAT.TYPE_2 is not null and RSAT.TYPE_3 is null and RSAT.TYPE_2 = FPT.TYPE_9)
              OR  (RSAT.TYPE_3 is not null and RSAT.TYPE_4 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10 )
              OR  (RSAT.TYPE_4 is not null and RSAT.TYPE_5 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 = FPT.TYPE_11 )
              OR  (RSAT.TYPE_5 is not null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 =  FPT.TYPE_11  and RSAT.TYPE_5= FPT.TYPE_12)
              OR
               FPT.FINPROD_ID = RSAT.FINPROD_ID
              OR (REGEXP_INSTR( (select wm_concat(t.item_code) from sys_datadict_list_link t
              where t.linked_dict_code=''ASSET_CLASS'' and t.dict_code=''CUSTOM_ASSET_TYPE_TWO'' AND TRIM(t.linked_item_code)= RSAT.TYPE_1),FPT.TYPE_9)>=1
              and RSAT.TYPE_1 is not null and RSAT.TYPE_2 is null )
              )
              and FPT.TYPE_9 is not null
              )
))
SELECT
    NVL(SUM(NVL(m.MARKET_VALUE,0)+nvl(m.TDY_INTINCEXP, 0)),0) RVALUE,
    M.PORTFOLIO_ID RKEY
FROM PTL_SEC_PIERCE_VALUTION M
INNER JOIN PROD N ON M.FINPROD_ID = N.FINPROD_ID
WHERE M.CDATE = date''${valDate}'' - 1
GROUP BY M.PORTFOLIO_ID';

----事后(日间)
V_CONTENT_04 CLOB := 'WITH PROD AS (
SELECT DISTINCT FINPROD_ID FROM(
    -- 资产到期向前第10个交易日
    SELECT FINPROD_ID FROM (
        SELECT FINPROD_ID, CDATE, ROW_NUMBER() OVER(PARTITION BY FINPROD_ID ORDER BY CDATE asc) RN FROM (
            SELECT m.FINPROD_ID, MH.CDATE, ROW_NUMBER() OVER(PARTITION BY FINPROD_ID ORDER BY CDATE desc) ROWNO FROM (
                -- 存款类资产
                SELECT FPT.FINPROD_ID,FPT.MDATE,NVL(FPT.CALENDAR_ID,''01'') CALENDAR_ID FROM FIN_PRODUCT FPT
                LEFT JOIN FIN_PRODUCT_TYPE T1 ON FPT.FINPROD_ID = T1.FINPROD_ID
				INNER JOIN FIN_PRODUCT_ADD FPA ON FPA.FINPROD_ID = FPT.FINPROD_ID
                WHERE FPT.MDATE IS NOT NULL AND T1.TYPE_8 = ''F05'' 
				-- 是否可提前支取条件
				AND FPA.IS_INADVANCE in (''01'',''02'')
				AND FPT.MDATE >= DATE ''${valDate}'' - 1
                UNION ALL
                -- 逆回购
                SELECT FTP.FINPROD_ID,FTP.MDATE,NVL(FTP.CALENDAR_ID,''01'') CALENDAR_ID FROM FIN_TRADE_PRODUCT FTP
                LEFT JOIN FIN_PRODUCT_TYPE T1 ON FTP.FINPROD_ID = T1.FINPROD_ID
                WHERE FTP.MDATE IS NOT NULL AND T1.TYPE_8 IN (''F07'',''F10'') AND TYPE_7 = ''04'' AND FTP.MDATE >= DATE ''${valDate}'' - 1
            ) m
            INNER JOIN MST_HOLIDAY MH ON MH.CALENDAR_ID = m.CALENDAR_ID AND m.MDATE >= MH.CDATE  AND MH.IS_HOLIDAY = ''0''
        ) WHERE ROWNO <= 10
    ) WHERE RN = 1 AND CDATE > DATE ''${valDate}''
  UNION ALL
    -- 资产支持证券
    SELECT FPT.FINPROD_ID FROM FIN_PRODUCT FPT
    LEFT JOIN FIN_PRODUCT_TYPE T1 ON FPT.FINPROD_ID = T1.FINPROD_ID
    WHERE FPT.MDATE IS NOT NULL AND T1.TYPE_8 = ''F01'' AND TYPE_6 = ''B13'' AND FPT.MDATE >= DATE ''${valDate}'' - 1
  UNION ALL
      -- 流动性受限债券
      SELECT FPT.FINPROD_ID FROM FIN_PRODUCT_TYPE FPT
      WHERE EXISTS (
        SELECT 1 FROM RSK_SPECIFIC_ASSET_TYPE RSAT WHERE RSAT.IS_FLOW_LIMITED = ''Y'' AND (
                  (RSAT.TYPE_2 is not null and RSAT.TYPE_3 is null and RSAT.TYPE_2 = FPT.TYPE_9)
              OR  (RSAT.TYPE_3 is not null and RSAT.TYPE_4 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10 )
              OR  (RSAT.TYPE_4 is not null and RSAT.TYPE_5 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 = FPT.TYPE_11 )
              OR  (RSAT.TYPE_5 is not null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 =  FPT.TYPE_11  and RSAT.TYPE_5= FPT.TYPE_12)
              OR
               FPT.FINPROD_ID = RSAT.FINPROD_ID
              OR (REGEXP_INSTR( (select wm_concat(t.item_code) from sys_datadict_list_link t
              where t.linked_dict_code=''ASSET_CLASS'' and t.dict_code=''CUSTOM_ASSET_TYPE_TWO'' AND TRIM(t.linked_item_code)= RSAT.TYPE_1),FPT.TYPE_9)>=1
              and RSAT.TYPE_1 is not null and RSAT.TYPE_2 is null )
              )
              and FPT.TYPE_9 is not null
      )AND NOT EXISTS(
         SELECT 1 FROM RSK_SPECIFIC_ASSET_TYPE RSAT WHERE RSAT.IS_FLOW_LIMITED = ''N'' AND (
                  (RSAT.TYPE_2 is not null and RSAT.TYPE_3 is null and RSAT.TYPE_2 = FPT.TYPE_9)
              OR  (RSAT.TYPE_3 is not null and RSAT.TYPE_4 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10 )
              OR  (RSAT.TYPE_4 is not null and RSAT.TYPE_5 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 = FPT.TYPE_11 )
              OR  (RSAT.TYPE_5 is not null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 =  FPT.TYPE_11  and RSAT.TYPE_5= FPT.TYPE_12)
              OR
               FPT.FINPROD_ID = RSAT.FINPROD_ID
              OR (REGEXP_INSTR( (select wm_concat(t.item_code) from sys_datadict_list_link t
              where t.linked_dict_code=''ASSET_CLASS'' and t.dict_code=''CUSTOM_ASSET_TYPE_TWO'' AND TRIM(t.linked_item_code)= RSAT.TYPE_1),FPT.TYPE_9)>=1
              and RSAT.TYPE_1 is not null and RSAT.TYPE_2 is null )
              )
              and FPT.TYPE_9 is not null
              )
))
SELECT SUM(TRADE_AMT) RVALUE FROM (
    SELECT
        NVL(SUM(NVL(M.MARKET_VALUE,0)+nvl(M.TDY_INTINCEXP, 0)),0) TRADE_AMT
    FROM PTL_SEC_PIERCE_VALUTION M
    INNER JOIN PROD N ON M.FINPROD_ID = N.FINPROD_ID
    WHERE M.CDATE = date''${valDate}'' - 1 AND  M.PORTFOLIO_ID IN ${portfolioCode}
  UNION ALL
    SELECT
        nvl(sum(decode(n.PS, ''T01'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''T04'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''T52'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T02'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T03'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T19'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T07'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T08'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T09'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T11'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T22'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T23'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T38'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T39'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0) AS TRADE_AMT
    FROM TRD_PRODUCT_DEAL_ADD m
    INNER JOIN TRD_PRODUCT_DEAL n ON m.TRADE_ID = n.TRADE_ID
    INNER JOIN PROD t2 ON t2.FINPROD_ID = n.FINPROD_ID
    WHERE m.PORTFOLIO_ID IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}''
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'', ''T07'', ''T08'', ''T09'', ''T11'', ''T52'', ''T22'', ''T23'', ''T39'', ''T38'')
  UNION ALL
    SELECT
        nvl(sum(decode(n.PS, ''T01'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''T04'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''T52'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''04'',  decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T93'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))),0)
      - nvl(sum(decode(n.PS, ''T95'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))),0)
      - nvl(sum(decode(n.PS, ''T96'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))),0)
      - nvl(sum(decode(n.PS, ''T02'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T03'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T19'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T07'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T08'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T09'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T11'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T22'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T23'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T38'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T39'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0) AS TRADE_AMT
    FROM TRD_PRODUCT_TEMP_DEAL_ADD m
    INNER JOIN TRD_PRODUCT_TEMP_DEAL n ON m.TRADE_ID = n.TRADE_ID
    INNER JOIN PROD t2 ON t2.FINPROD_ID = n.FINPROD_ID
    WHERE m.PORTFOLIO_ID IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}''
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'', ''T07'', ''T08'', ''T09'', ''T11'', ''T52'', ''T22'', ''T23'', ''T39'', ''T38'',''04'',''T93'',''T95'',''T96'')
  UNION ALL
    SELECT
        nvl(sum(decode(n.PS, ''T01'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''T04'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''T52'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T02'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T03'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T19'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0) AS TRADE_AMT
    FROM WFL_TRD_PRODUCT_DEAL_ADD m
    INNER JOIN WFL_TRD_PRODUCT_DEAL n ON m.TRADE_ID = n.TRADE_ID AND n.TRADE_STATUS != ''03''
    INNER JOIN PROD t2 ON t2.FINPROD_ID = n.FINPROD_ID
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    WHERE m.PORTFOLIO_ID IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}''
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'', ''T52'')
  UNION ALL
    SELECT
        nvl(sum(decode(n.PS, ''T02'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0) AS TRADE_AMT
    FROM WFL_TRD_PRODUCT_DEAL_ADD m
    INNER JOIN WFL_TRD_PRODUCT_DEAL n ON m.TRADE_ID = n.TRADE_ID AND n.TRADE_STATUS != ''03''
    INNER JOIN PROD t2 ON t2.FINPROD_ID = n.FINPROD_ID
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    WHERE n.counter_id IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}''
    AND n.PS = ''T02''
    AND EXISTS (SELECT 1 FROM SYS_PARA WHERE PARA_CODE = ''is_auto_generate_negate_deal'' AND PARAM_VALUE = ''Y'')
)';

----事后(日终)
V_CONTENT_05 CLOB := 'WITH PROD AS (select distinct FINPROD_ID from (
    -- 资产到期向前第10个交易日
    SELECT FINPROD_ID FROM (
        SELECT FINPROD_ID, CDATE, ROW_NUMBER() OVER(PARTITION BY FINPROD_ID ORDER BY CDATE asc) RN FROM (
            SELECT m.FINPROD_ID, MH.CDATE, ROW_NUMBER() OVER(PARTITION BY FINPROD_ID ORDER BY CDATE desc) ROWNO FROM (
                -- 存款类资产
                SELECT FPT.FINPROD_ID,FPT.MDATE,NVL(FPT.CALENDAR_ID,''01'') CALENDAR_ID FROM FIN_PRODUCT FPT
                LEFT JOIN FIN_PRODUCT_TYPE T1 ON FPT.FINPROD_ID = T1.FINPROD_ID 
				INNER JOIN FIN_PRODUCT_ADD FPA ON FPA.FINPROD_ID = FPT.FINPROD_ID
                WHERE FPT.MDATE IS NOT NULL AND T1.TYPE_8 = ''F05'' 
				-- 是否可提前支取条件
				AND FPA.IS_INADVANCE in (''01'',''02'')
				AND FPT.MDATE >= DATE ''${valDate}'' - 1
                UNION ALL
                -- 逆回购
                SELECT FTP.FINPROD_ID,FTP.MDATE,NVL(FTP.CALENDAR_ID,''01'') CALENDAR_ID FROM FIN_TRADE_PRODUCT FTP
                LEFT JOIN FIN_PRODUCT_TYPE T1 ON FTP.FINPROD_ID = T1.FINPROD_ID 
                WHERE FTP.MDATE IS NOT NULL AND T1.TYPE_8 IN (''F07'',''F10'') AND TYPE_7 = ''04'' AND FTP.MDATE >= DATE ''${valDate}'' - 1
            ) m
            INNER JOIN MST_HOLIDAY MH ON MH.CALENDAR_ID = m.CALENDAR_ID AND m.MDATE >= MH.CDATE  AND MH.IS_HOLIDAY = ''0''
        ) WHERE ROWNO <= 10
    ) WHERE RN = 1 AND CDATE > DATE ''${valDate}''
  UNION ALL
    -- 资产支持证券
    SELECT FPT.FINPROD_ID FROM FIN_PRODUCT FPT
    LEFT JOIN FIN_PRODUCT_TYPE T1 ON FPT.FINPROD_ID = T1.FINPROD_ID 
    WHERE FPT.MDATE IS NOT NULL AND T1.TYPE_8 = ''F01'' AND TYPE_6 = ''B13'' AND FPT.MDATE >= DATE ''${valDate}'' - 1
  UNION ALL
    -- 流动性受限债券
    SELECT FPT.FINPROD_ID FROM FIN_PRODUCT_TYPE FPT
    WHERE EXISTS (
        SELECT 1 FROM RSK_SPECIFIC_ASSET_TYPE RSAT WHERE RSAT.IS_FLOW_LIMITED = ''Y'' AND (
                  (RSAT.TYPE_2 is not null and RSAT.TYPE_3 is null and RSAT.TYPE_2 = FPT.TYPE_9)
              OR  (RSAT.TYPE_3 is not null and RSAT.TYPE_4 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10 )
              OR  (RSAT.TYPE_4 is not null and RSAT.TYPE_5 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 = FPT.TYPE_11 )
              OR  (RSAT.TYPE_5 is not null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 =  FPT.TYPE_11  and RSAT.TYPE_5= FPT.TYPE_12)
              OR
               FPT.FINPROD_ID = RSAT.FINPROD_ID
              OR (REGEXP_INSTR( (select wm_concat(t.item_code) from sys_datadict_list_link t
              where t.linked_dict_code=''ASSET_CLASS'' and t.dict_code=''CUSTOM_ASSET_TYPE_TWO'' AND TRIM(t.linked_item_code)= RSAT.TYPE_1),FPT.TYPE_9)>=1
              and RSAT.TYPE_1 is not null and RSAT.TYPE_2 is null )
              )
              and FPT.TYPE_9 is not null
    )AND NOT EXISTS(
         SELECT 1 FROM RSK_SPECIFIC_ASSET_TYPE RSAT WHERE RSAT.IS_FLOW_LIMITED = ''N'' AND (
                  (RSAT.TYPE_2 is not null and RSAT.TYPE_3 is null and RSAT.TYPE_2 = FPT.TYPE_9)
              OR  (RSAT.TYPE_3 is not null and RSAT.TYPE_4 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10 )
              OR  (RSAT.TYPE_4 is not null and RSAT.TYPE_5 is null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 = FPT.TYPE_11 )
              OR  (RSAT.TYPE_5 is not null and RSAT.TYPE_2 = FPT.TYPE_9 and RSAT.TYPE_3 = FPT.TYPE_10  and RSAT.TYPE_4 =  FPT.TYPE_11  and RSAT.TYPE_5= FPT.TYPE_12)
              OR
               FPT.FINPROD_ID = RSAT.FINPROD_ID
              OR (REGEXP_INSTR( (select wm_concat(t.item_code) from sys_datadict_list_link t
              where t.linked_dict_code=''ASSET_CLASS'' and t.dict_code=''CUSTOM_ASSET_TYPE_TWO'' AND TRIM(t.linked_item_code)= RSAT.TYPE_1),FPT.TYPE_9)>=1
              and RSAT.TYPE_1 is not null and RSAT.TYPE_2 is null )
              )
              and FPT.TYPE_9 is not null
              )
))
SELECT
    NVL(SUM(NVL(M.MARKET_VALUE,0)+nvl(M.TDY_INTINCEXP, 0)),0) RVALUE
FROM PTL_SEC_PIERCE_VALUTION M
INNER JOIN PROD N ON M.FINPROD_ID = N.FINPROD_ID
WHERE M.CDATE = date''${valDate}'' AND M.PORTFOLIO_ID IN ${portfolioCode}';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'LDXSXZCGZ' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'LDXSXZCGZ' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'LDXSXZCGZ' and PARA_GRANULARITY = '05';
END;
/