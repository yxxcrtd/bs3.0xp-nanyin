/**风险指标参数:单一资产规模**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='DYZCGM';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('DYZCGM','单一资产规模','N','','cdj','C001',sysdate,'cdj',sysdate,'','','Y','String', '','REDIS', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='DYZCGM';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DYZCGM','01', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode,assetCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DYZCGM','02', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode,assetCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DYZCGM','03', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode,assetCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DYZCGM','04', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode,assetCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DYZCGM','05', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode,assetCode','N','cdj','C001',sysdate,'cdj',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'SELECT nvl(sum(nvl(RVALUE, 0)),0) as RVALUE,s1.RKEY  FROM (
select
     NVL(SUM(NVL(t1.MARKET_VALUE,0)+nvl(t1.TDY_INTINCEXP, 0)),0) as RVALUE, t1.PORTFOLIO_ID||'',''||t1.FINPROD_ID as RKEY
    from PTL_SEC_PIERCE_VALUTION t1
     left join FIN_PRODUCT t2
      on t1.FINPROD_ID = t2.FINPROD_ID
    left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3
      on t1.FINPROD_ID = t3.FINPROD_ID
    where  t1.CDATE = date''${valDate}''-1  AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
    and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'',''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'',''F29'',''F30'') AND t3.BRANCH=0))
    group by t1.PORTFOLIO_ID,t1.FINPROD_ID,t2.FINPROD_NAME

    union all
      select NVL(SUM(NVL(s1.MARKET_VALUE,0)+nvl(s1.TDY_INTINCEXP, 0)),0) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY
     from (select t1.*
    from PTL_SEC_PIERCE_VALUTION t1
     left join FIN_PRODUCT t2
      on t1.FINPROD_ID = t2.FINPROD_ID
    left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3
      on t1.FINPROD_ID = t3.FINPROD_ID
    where  t1.CDATE = date''${valDate}''-1  AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
    and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'',''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'',''F29'',''F30'') AND t3.BRANCH=0))
    ) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'')
    s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID
    UNION ALL

   SELECT nvl(sum(decode(T.PS,''T50'',decode(T.TRADE_STATUS,''03'',T.prin_amt*-1,T.prin_amt))),0) RVALUE,
          T1.PORTFOLIO_ID||'',''||T.FINPROD_ID as RKEY
  FROM TRD_PRODUCT_DEAL T
 INNER JOIN TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 INNER JOIN TRD_FIN_PRODUCT_DEAL_TOTAL T3 on T.TOTLE_TRADE_ID = T3.TOTLE_TRADE_ID
 WHERE T.PS = ''T50''
   AND T.FINPROD_TYPE2 = ''F01''
   AND T.TRADE_DATE < date''${valDate}''
   AND NOT EXISTS (
      -- 没有后续申购确认交易
      SELECT 1 FROM TRD_PRODUCT_DEAL TRD WHERE T3.TOTLE_TRADE_ID = TRD.B_TRADE_ID AND TRD.TRADE_STATUS = ''01''
      AND NOT EXISTS (
          SELECT 1 FROM TRD_PRODUCT_DEAL TRD1
         WHERE TRD.TRADE_ID = TRD1.P_TRADE_ID
           AND TRD1.TRADE_STATUS = ''03''
  ))
  AND NOT EXISTS (SELECT 1 FROM WFL_TRD_PRODUCT_DEAL TRD
    INNER JOIN WFL_TRD_PRODUCT_DEAL_ADD TRDA ON TRD.trade_id = TRDA.trade_id
     INNER JOIN SYS_WFL_BUSINESS_LIST swbl on TRDA.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
     WHERE T3.TOTLE_TRADE_ID = TRD.B_TRADE_ID AND TRD.TRADE_STATUS = ''01''
  )
  group by T1.PORTFOLIO_ID,T.FINPROD_ID
) s1 group by s1.RKEY';

----事后(日间)
V_CONTENT_04 CLOB := 'select
    sum(nvl(targetData.VALUE, 0)) as RVALUE, targetData.ASSETCODE AS RKEY, targetData.ASSETNAME AS RKEYCN
    from
    (
    select
     NVL(t1.MARKET_VALUE,0)+nvl(t1.TDY_INTINCEXP, 0) as VALUE, t1.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
    from PTL_SEC_PIERCE_VALUTION t1
     left join FIN_PRODUCT t2  on t1.FINPROD_ID = t2.FINPROD_ID
     left join FIN_TRADE_PRODUCT t3 on t1.FINPROD_ID = t3.FINPROD_ID
    where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''-1  AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
    and ((t3.FINPROD_TYPE is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'',''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'',''F29'',''F30'') AND t3.BRANCH=0))
union all
SELECT nvl(sum(decode(T.PS,''T50'',decode(T.TRADE_STATUS,''03'',T.prin_amt*-1,T.prin_amt))),0) TRADE_AMT,
 t.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
  FROM TRD_PRODUCT_DEAL T
 INNER JOIN TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 INNER JOIN TRD_FIN_PRODUCT_DEAL_TOTAL T3 on T.TOTLE_TRADE_ID = T3.TOTLE_TRADE_ID
 WHERE T1.PORTFOLIO_ID in ${portfolioCode}
   AND T.PS = ''T50''
   AND T.FINPROD_TYPE2 = ''F01''
   AND T.TRADE_DATE <= date''${valDate}''
   AND NOT EXISTS (
      -- 没有后续申购确认交易
      SELECT 1 FROM TRD_PRODUCT_DEAL TRD WHERE T3.TOTLE_TRADE_ID = TRD.B_TRADE_ID AND TRD.TRADE_STATUS = ''01''
      AND NOT EXISTS (
          SELECT 1 FROM TRD_PRODUCT_DEAL TRD1
         WHERE TRD.TRADE_ID = TRD1.P_TRADE_ID
           AND TRD1.TRADE_STATUS = ''03''
  ))
  AND NOT EXISTS (SELECT 1 FROM WFL_TRD_PRODUCT_DEAL TRD
    INNER JOIN WFL_TRD_PRODUCT_DEAL_ADD TRDA ON TRD.trade_id = TRDA.trade_id
     INNER JOIN SYS_WFL_BUSINESS_LIST swbl on TRDA.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
     WHERE T3.TOTLE_TRADE_ID = TRD.B_TRADE_ID AND TRD.TRADE_STATUS = ''01''
  )
  group by T.FINPROD_ID,T2.FINPROD_NAME
   union all
SELECT NVL(SUM(NVL(T.prin_amt, 0)), 0) TRADE_AMT,t.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
  FROM WFL_TRD_PRODUCT_DEAL T
 INNER JOIN WFL_TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 INNER JOIN SYS_WFL_BUSINESS_LIST swbl on T1.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
 WHERE T1.PORTFOLIO_ID in ${portfolioCode}
   AND T.PS = ''T50''
   AND T.FINPROD_TYPE2 = ''F01''
   AND T.TRADE_DATE = date''${valDate}''
  group by T.FINPROD_ID,T2.FINPROD_NAME
union all
 --没有撤销记录的申购确认交易
SELECT NVL(SUM(NVL(T.prin_amt, 0)), 0) TRADE_AMT,t.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
  FROM TRD_PRODUCT_DEAL T
 INNER JOIN TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 WHERE T.TRADE_STATUS = ''01''
   AND T1.PORTFOLIO_ID in ${portfolioCode}
   AND T.PS = ''T05''
   AND T.FINPROD_TYPE2 = ''F01''
   AND NOT EXISTS ( SELECT 1 FROM TRD_PRODUCT_DEAL TRD WHERE T.TRADE_ID = TRD.P_TRADE_ID AND TRD.TRADE_STATUS = ''03'')
 group by T.FINPROD_ID, T2.FINPROD_NAME
  --没有撤销记录的申购确认交易
   union all
 --申购确认交易(流程中)
SELECT NVL(SUM(NVL(T.prin_amt, 0)), 0) TRADE_AMT,t.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
  FROM WFL_TRD_PRODUCT_DEAL T
 INNER JOIN WFL_TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 INNER JOIN SYS_WFL_BUSINESS_LIST swbl on T1.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
 WHERE T1.PORTFOLIO_ID in ${portfolioCode}
   AND T.PS = ''T05''
   AND T.TRADE_DATE = date''${valDate}''
   AND T.FINPROD_TYPE2 = ''F01''
   group by T.FINPROD_ID,T2.FINPROD_NAME
   --申购确认交易(流程中)
    union all
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
      -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
      -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
      -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
      from TRD_PRODUCT_DEAL n
      left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID
     left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID
       LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
      where n1.PORTFOLIO_ID in ${portfolioCode} and  n.TRADE_DATE = date''${valDate}''
      and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'',''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'',''F29'',''F30'') AND t3.BRANCH=0))
      and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T07'',''T08'',''T09'',''T11'',''T52'',''T38'',''T22'',''T23'',''T39'') group by n.FINPROD_ID, t2.FINPROD_NAME
    union all
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
      -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
      -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
      -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
      from TRD_PRODUCT_TEMP_DEAL n
      left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID
     left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID
       LEFT JOIN TRD_PRODUCT_TEMP_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
      where n1.PORTFOLIO_ID in ${portfolioCode} and  n.TRADE_DATE = date''${valDate}''
      and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'',''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'',''F29'',''F30'') AND t3.BRANCH=0))
      and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T07'',''T08'',''T09'',''T11'',''T52'',''T38'',''T22'',''T23'',''T39'') group by n.FINPROD_ID, t2.FINPROD_NAME
    union ALL
       select
     nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
      +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
      +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
      -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
      -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
      -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
      from WFL_TRD_PRODUCT_DEAL_ADD m
      inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
      INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
      left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID
     left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID
      where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}''
       and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'',''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'',''F29'',''F30'') AND t3.BRANCH=0))
      and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T52'')
      group by n.FINPROD_ID, t2.FINPROD_NAME
    union ALL
       select
        nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
      from WFL_TRD_PRODUCT_DEAL_ADD m
      inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
      INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
      left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID
     left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID
      where n.counter_id IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}''
       and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'',''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'',''F29'',''F30'') AND t3.BRANCH=0))
       AND n.PS = ''T02''
       AND EXISTS (SELECT 1 FROM SYS_PARA WHERE PARA_CODE = ''is_auto_generate_negate_deal'' AND PARAM_VALUE = ''Y'')
       group by n.FINPROD_ID, t2.FINPROD_NAME
    ) targetData
    group by targetData.ASSETCODE, targetData.ASSETNAME';

----事后(日终)
V_CONTENT_05 CLOB := 'select
    sum(nvl(targetData.VALUE, 0)) as RVALUE, targetData.ASSETCODE RKEY, targetData.ASSETNAME RKEYCN
    from
    (
    select
     NVL(t1.MARKET_VALUE,0)+nvl(t1.TDY_INTINCEXP, 0) as VALUE, t1.FINPROD_ID as ASSETCODE, t2.FINPROD_NAME as ASSETNAME
    from PTL_SEC_PIERCE_VALUTION t1
     left join FIN_PRODUCT t2
      on t1.FINPROD_ID = t2.FINPROD_ID
      left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3
      on t1.FINPROD_ID = t3.FINPROD_ID
    where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''
    and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'',''F04'',''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'',''F29'',''F30'') AND t3.BRANCH=0))
) targetData
group by targetData.ASSETCODE, targetData.ASSETNAME';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'DYZCGM' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'DYZCGM' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'DYZCGM' and PARA_GRANULARITY = '05';
END;
/



----单一资产规模-买入预占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYZCGM_01';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYZCGM_01','单一资产规模-买入预占（不算指标）','N','','jq','',sysdate,'jq',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_01';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_01';

----单一资产规模-卖出预占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYZCGM_02';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYZCGM_02','单一资产规模-卖出预占（不算指标）','N','','jq','',sysdate,'jq',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_02';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_02';

----单一资产规模-买入实占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYZCGM_03';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYZCGM_03','单一资产规模-买入实占（不算指标）','N','','jq','',sysdate,'jq',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_03';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_03','assetType','facfbea77b1040bd8062ea8c1c320a23','','F11','=','','','','cdjjyy','2222222',SYSDATE,'qq1',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_03','eventType','facfbea77b1040bd8062ea8c1c320a23','','1,2','IN','','','','cdjjyy','2222222',SYSDATE,'qq1',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_03','ps','facfbea77b1040bd8062ea8c1c320a23','','T11','IN','','','','cdjjyy','2222222',SYSDATE,'qq1',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_03';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_03','01','+','amount','','yh1','jsadjflsajfsdkl',SYSDATE,'yh1',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_03','02','+','amount','','yh1','jsadjflsajfsdkl',SYSDATE,'yh1',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_03','03','+','amount','','yh1','jsadjflsajfsdkl',SYSDATE,'yh1',SYSDATE,'','');

----单一资产规模-卖出实占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYZCGM_04';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYZCGM_04','单一资产规模-卖出实占（不算指标）','N','','jq','',sysdate,'jjpei',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_04';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_04','assetType','6be3a3b1b4f741a197d156158959168b','','F07,F08,F10,F11','NO','','','','cdjjyy','2222222',SYSDATE,'qq1',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_04','ps','6be3a3b1b4f741a197d156158959168b','','T07,T08,T09,T11,T38,T39,T54,T55','IN','','','','cdjjyy','2222222',SYSDATE,'qq1',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_04','eventType','6be3a3b1b4f741a197d156158959168b','','1,2','IN','','','','cdjjyy','2222222',SYSDATE,'qq1',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_04','assetType','fe33388998434a27a76969f8ef050647','','F11','=','','','','cdjjyy','2222222',SYSDATE,'qq1',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_04','eventType','fe33388998434a27a76969f8ef050647','','1,2','IN','','','','cdjjyy','2222222',SYSDATE,'qq1',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_04','ps','fe33388998434a27a76969f8ef050647','','T07,T08,T09,T38,T39,T54','IN','','','','cdjjyy','2222222',SYSDATE,'qq1',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_04';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_04','01','-','amount','','yh1','jsadjflsajfsdkl',SYSDATE,'yh1',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_04','02','-','amount','','yh1','jsadjflsajfsdkl',SYSDATE,'yh1',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_04','03','-','amount','','yh1','jsadjflsajfsdkl',SYSDATE,'yh1',SYSDATE,'','');

----单一资产规模-买入预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYZCGM_05';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYZCGM_05','单一资产规模-买入预占','Y','','jq','',sysdate,'cdj',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_05';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_05','assetType','1fbf599d18784a7c8da94f24cf2308e7','','F07,F08,F10','IN','','','','cdj','C001',SYSDATE,'cdjsp',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_05','ps','1fbf599d18784a7c8da94f24cf2308e7','','T01','=','','','','cdj','C001',SYSDATE,'cdjsp',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_05','eventType','1fbf599d18784a7c8da94f24cf2308e7','','0','=','','','','cdj','C001',SYSDATE,'cdjsp',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_05','assetType2','1fbf599d18784a7c8da94f24cf2308e7','','02,04','IN','','','','cdj','C001',SYSDATE,'cdjsp',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_05','assetType','fd1bed84d61d43a6893a3b028ab053af','','F07,F08,F10','NO','','','','cdj','C001',SYSDATE,'cdjsp',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_05','eventType','fd1bed84d61d43a6893a3b028ab053af','','0','IN','','','','cdj','C001',SYSDATE,'cdjsp',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_05','ps','fd1bed84d61d43a6893a3b028ab053af','','T01,T04,T20,T46,T47,T48,T52','IN','','','','cdj','C001',SYSDATE,'cdjsp',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_05';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_05','02','+','amount','','system','jsadjflsajfsdkl',SYSDATE,'system',SYSDATE,'','');

----单一资产规模-卖出预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYZCGM_06';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYZCGM_06','单一资产规模-卖出预占','Y','','jq','',sysdate,'jyy004',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_06';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_06','assetType','2a1ae7454f3d4f8799c6077e1b56d4f0','','F07,F08,F10','NO','','','','jyy004','001',SYSDATE,'cdsp',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_06','eventType','2a1ae7454f3d4f8799c6077e1b56d4f0','','0','IN','','','','jyy004','001',SYSDATE,'cdsp',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_06','ps','2a1ae7454f3d4f8799c6077e1b56d4f0','','T02,T03,T06,T19,T21','IN','','','','jyy004','001',SYSDATE,'cdsp',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_06';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_06','03','-','amount','','system','jsadjflsajfsdkl',SYSDATE,'system',SYSDATE,'','');

----单一资产规模-买入实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYZCGM_07';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYZCGM_07','单一资产规模-买入实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_07';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_07','assetType','bf256fa87a094948bb4fefef0c17c1d0','','F07,F08,F10','NO','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_07','eventType','bf256fa87a094948bb4fefef0c17c1d0','','1,2','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_07','ps','bf256fa87a094948bb4fefef0c17c1d0','','T01,T04,T12,T20,T46,T47,T48,T52','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_07';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_07','01','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_07','02','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_07','03','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----单一资产规模-卖出实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_DYZCGM_08';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_DYZCGM_08','单一资产规模-卖出实占','Y','','jq','',sysdate,'jjpei',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_08';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_08','assetType','1a941296bdf3493ab918793f4f91a408','','F07,F08,F10','NO','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_08','eventType','1a941296bdf3493ab918793f4f91a408','','1,2','IN','','','','jjpei','2222222',SYSDATE,'jjpei',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_DYZCGM_08','ps','1a941296bdf3493ab918793f4f91a408','','T02,T03,T06,T19,T21','IN','','','','jjpei','99999',SYSDATE,'jjpei',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_DYZCGM_08';
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_08','01','-','mixAmount','','yh1','jsadjflsajfsdkl',SYSDATE,'yh1',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_08','02','-','mixAmount','','yh1','jsadjflsajfsdkl',SYSDATE,'yh1',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('DYZCGM','EVENT_ID_DYZCGM_08','03','-','mixAmount','','yh1','jsadjflsajfsdkl',SYSDATE,'yh1',SYSDATE,'','');



/**风险指标参数:单一资产规模**/