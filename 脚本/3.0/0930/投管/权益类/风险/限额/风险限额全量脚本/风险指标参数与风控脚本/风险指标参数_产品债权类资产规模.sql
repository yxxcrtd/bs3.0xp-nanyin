/**风险指标参数:产品债权类资产规模**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='CPZCLZCGM';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('CPZCLZCGM','产品债权类资产规模','N','','cdj','C001',sysdate,'cdj',sysdate,'','','Y','String', '','REDIS', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='CPZCLZCGM';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPZCLZCGM','01', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPZCLZCGM','02', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPZCLZCGM','03', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPZCLZCGM','04', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPZCLZCGM','05', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'select PORTFOLIO_ID RKEY,sum(TRADE_AMT) as RVALUE from
   (
  select m.PORTFOLIO_ID,NVL(SUM(NVL(m.MARKET_VALUE,0)+nvl(m.TDY_INTINCEXP, 0)),0) as TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
    INNER JOIN FIN_PRODUCT_ADD PFP on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
  where   m.CDATE =date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID  group by m.PORTFOLIO_ID
  union all
  select m.PORTFOLIO_ID,NVL(SUM(NVL(m.MARKET_VALUE * GOAL.CREDITOR_RIGHTS_PER,0)+nvl(m.TDY_INTINCEXP * GOAL.CREDITOR_RIGHTS_PER,0)),0) as TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
    INNER JOIN FIN_PRODUCT_ADD PFP on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = m.FINPROD_ID
  where   m.CDATE =date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID  group by m.PORTFOLIO_ID
  union all
  select  m.PORTFOLIO_ID,
    NVL(SUM(NVL(m.MARKET_VALUE,0)+nvl(m.TDY_INTINCEXP, 0)),0) as  TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
  INNER JOIN FIN_TRADE_PRODUCT n on m.FINPROD_ID = n.FINPROD_ID and n.FINPROD_TYPE2 in (''F03'',''F05'',''F09'') and n.BRANCH=0
  where  m.CDATE =date''${valDate}''-1  group by m.PORTFOLIO_ID
  ) group by PORTFOLIO_ID';

----事后(日间)
V_CONTENT_04 CLOB := 'select sum(TRADE_AMT) as tradeAmt from
   (
   --估值表（投资性质=债权类）
  select NVL(SUM(NVL(M.MARKET_VALUE,0)+nvl(M.TDY_INTINCEXP, 0)),0) as TRADE_AMT 
  from PTL_SEC_PIERCE_VALUTION m
    INNER JOIN FIN_PRODUCT_ADD PFP
    on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
  where m.PORTFOLIO_ID in ${portfolioCode}  and  
  m.CDATE =date''${valDate}''-1   AND m.PORTFOLIO_ID!=m.FINPROD_ID
  union all
   --估值表（投资性质=混合类）
  select NVL(SUM(NVL(m.MARKET_VALUE * GOAL.CREDITOR_RIGHTS_PER,0)+nvl(m.TDY_INTINCEXP * GOAL.CREDITOR_RIGHTS_PER, 0)),0) as TRADE_AMT 
  from PTL_SEC_PIERCE_VALUTION m
    INNER JOIN FIN_PRODUCT_ADD PFP on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = m.FINPROD_ID
  where m.PORTFOLIO_ID in ${portfolioCode}  and  
  m.CDATE =date''${valDate}''-1   AND m.PORTFOLIO_ID!=m.FINPROD_ID
  union all
   --估值表（逆回购、拆出）
  select
    NVL(SUM(NVL(M.MARKET_VALUE,0)+nvl(M.TDY_INTINCEXP, 0)),0)  as  TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
  INNER JOIN FIN_TRADE_PRODUCT n on m.FINPROD_ID = n.FINPROD_ID and n.FINPROD_TYPE2 in (''F03'',''F05'',''F09'') AND n.BRANCH=0
  where m.PORTFOLIO_ID in ${portfolioCode}  and  
  m.CDATE =date''${valDate}''-1
  union ALL
  --1.1交易表（标的类资产，投资性质=债权类）
    SELECT
     SUM(CASE  WHEN n.PS IN(''T01'',''T04'',''T52'',''T25'',''T64'') THEN NVL(n.TRADE_AMT,0)
               WHEN n.PS =''T49'' THEN NVL(n.SHARE_AMT,0)
     ELSE  NVL(-1*n.TRADE_AMT,0)
     END) TRADE_AMT
    from TRD_PRODUCT_DEAL n
    INNER JOIN FIN_PRODUCT_ADD PFP
     on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
     LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
    where n1.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'', ''T07'', ''T11'', ''T52'', ''T22'', ''T23'', ''T38'', ''T39'',''T25'', ''T49'',''T64'',''T63'',''T93'',''T95'',''T96'',''T54'',''T55'',''T56'')
    AND n.TRADE_STATUS = ''01''
    AND n.IS_CANCEL = ''N''
  union ALL
  --1.2交易表（标的类资产，投资性质=混合类）
    SELECT
     SUM(CASE  WHEN n.PS IN(''T01'',''T04'',''T52'',''T25'',''T64'') THEN NVL(n.TRADE_AMT,0)*NVL(GOAL.CREDITOR_RIGHTS_PER,0)
               WHEN n.PS =''T49'' THEN NVL(n.SHARE_AMT,0)*NVL(GOAL.CREDITOR_RIGHTS_PER,0)
     ELSE  NVL(-1*n.TRADE_AMT,0) * NVL(GOAL.CREDITOR_RIGHTS_PER,0)
     END) TRADE_AMT
    from TRD_PRODUCT_DEAL n
    INNER JOIN FIN_PRODUCT_ADD PFP
     on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
     LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
     INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = n.FINPROD_ID
    where n1.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'', ''T07'', ''T11'', ''T52'', ''T22'', ''T23'', ''T38'', ''T39'',''T25'', ''T49'',''T64'',''T63'',''T93'',''T95'',''T96'',''T54'',''T55'',''T56'')
    AND n.TRADE_STATUS = ''01''
    AND n.IS_CANCEL = ''N''  
union ALL
--1.3交易表（逆回购、拆出）
    select
     SUM(CASE  WHEN n.PS IN(''T01'',''T04'',''T52'',''T25'',''T64'') THEN NVL(n.TRADE_AMT,0)
               WHEN n.PS =''T49'' THEN NVL(n.SHARE_AMT,0)
     ELSE  NVL(-1*n.TRADE_AMT,0)
     END) TRADE_AMT
    from  TRD_PRODUCT_DEAL n
    INNER JOIN FIN_TRADE_PRODUCT t2 on t2.FINPROD_ID=n.FINPROD_ID and t2.FINPROD_TYPE2 in (''F03'',''F05'',''F09'') AND t2.BRANCH=0
    LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
    where n1.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'', ''T07'', ''T11'', ''T52'', ''T22'', ''T23'', ''T38'', ''T39'',''T25'', ''T49'',''T64'',''T63'',''T93'',''T95'',''T96'',''T54'',''T55'',''T56'')
    AND n.TRADE_STATUS = ''01''
    AND n.IS_CANCEL = ''N''
  union ALL
  --2.1日间交易表（标的类资产，投资性质=债权类）
    SELECT
     SUM(CASE  WHEN n.PS IN(''T01'',''T04'',''T52'',''T25'',''T64'') THEN NVL(n.TRADE_AMT,0)
               WHEN n.PS =''T49'' THEN NVL(n.SHARE_AMT,0)
     ELSE  NVL(-1*n.TRADE_AMT,0)
     END) TRADE_AMT
    from TRD_PRODUCT_TEMP_DEAL n
    INNER JOIN FIN_PRODUCT_ADD PFP
     on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
     LEFT JOIN TRD_PRODUCT_TEMP_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
    where n1.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'', ''T07'', ''T11'', ''T52'', ''T22'', ''T23'', ''T38'', ''T39'',''T25'', ''T49'',''T93'',''T95'',''T96'',''T54'',''T55'',''T56'')
    AND n.TRADE_STATUS = ''01''
   AND n.IS_CANCEL = ''N''
    union ALL
  --2.2日间交易表（标的类资产，投资性质=混合类）
    SELECT
     SUM(CASE  WHEN n.PS IN(''T01'',''T04'',''T52'',''T25'',''T64'') THEN NVL(n.TRADE_AMT,0) * NVL(GOAL.CREDITOR_RIGHTS_PER,0)
               WHEN n.PS =''T49'' THEN NVL(n.SHARE_AMT,0) * NVL(GOAL.CREDITOR_RIGHTS_PER,0)
     ELSE  NVL(-1*n.TRADE_AMT,0) * NVL(GOAL.CREDITOR_RIGHTS_PER,0)
     END) TRADE_AMT
    from TRD_PRODUCT_TEMP_DEAL n
    INNER JOIN FIN_PRODUCT_ADD PFP
     on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
     LEFT JOIN TRD_PRODUCT_TEMP_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
     INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = n.FINPROD_ID
    where n1.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'', ''T07'', ''T11'', ''T52'', ''T22'', ''T23'', ''T38'', ''T39'',''T25'', ''T49'',''T93'',''T95'',''T96'',''T54'',''T55'',''T56'')
    AND n.TRADE_STATUS = ''01''
   AND n.IS_CANCEL = ''N''
    union ALL
  --2.3日间交易表（逆回购、拆出）
    select
     SUM(CASE  WHEN n.PS IN(''T01'',''04'',''T04'',''T52'',''T25'',''T64'') THEN NVL(n.TRADE_AMT,0)
               WHEN n.PS =''T49'' THEN NVL(n.SHARE_AMT,0)
     ELSE  NVL(-1*n.TRADE_AMT,0)
     END) TRADE_AMT
    from  TRD_PRODUCT_TEMP_DEAL n
    INNER JOIN FIN_TRADE_PRODUCT t2 on t2.FINPROD_ID=n.FINPROD_ID and t2.FINPROD_TYPE2 in (''F03'',''F05'',''F09'') AND t2.BRANCH=0
    LEFT JOIN TRD_PRODUCT_TEMP_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
    where n1.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN (''T01'',''04'', ''T02'', ''T03'', ''T04'', ''T19'', ''T07'', ''T11'', ''T52'', ''T22'', ''T23'', ''T38'', ''T39'',''T25'', ''T49'',''T64'',''T63'',''T93'',''T95'',''T96'',''T54'',''T55'',''T56'')
    AND n.TRADE_STATUS = ''01''
    AND n.IS_CANCEL = ''N''
  union ALL
  --3.1流程交易表（标的类资产，投资性质=债权类）
     select
     SUM(CASE  WHEN n.PS IN(''T01'',''T04'',''T52'') THEN NVL(n.TRADE_AMT,0)
               WHEN n.PS =''T49'' THEN NVL(n.SHARE_AMT,0)
     ELSE  NVL(-1*n.TRADE_AMT,0)
     END) TRADE_AMT
    from WFL_TRD_PRODUCT_DEAL_ADD m
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS = ''01''
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    INNER JOIN FIN_PRODUCT_ADD PFP on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
    where m.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'',  ''T52'', ''T49'')
   AND n.IS_CANCEL = ''N''
  union ALL
  --3.2流程交易表（标的类资产，投资性质=混合类）
     select
     SUM(CASE  WHEN n.PS IN(''T01'',''T04'',''T52'') THEN NVL(n.TRADE_AMT,0) * NVL(GOAL.CREDITOR_RIGHTS_PER,0)
               WHEN n.PS =''T49'' THEN NVL(n.SHARE_AMT,0) * NVL(GOAL.CREDITOR_RIGHTS_PER,0)
     ELSE  NVL(-1*n.TRADE_AMT,0) * NVL(GOAL.CREDITOR_RIGHTS_PER,0)
     END) TRADE_AMT
    from WFL_TRD_PRODUCT_DEAL_ADD m
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS = ''01''
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    INNER JOIN FIN_PRODUCT_ADD PFP on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = n.FINPROD_ID
    where m.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'',  ''T52'', ''T49'')
   AND n.IS_CANCEL = ''N''
  union ALL
  --3.3流程交易表（逆回购、拆出）
     select
     SUM(NVL(n.TRADE_AMT,0)) TRADE_AMT
    from WFL_TRD_PRODUCT_DEAL_ADD m
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS = ''01''
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    INNER JOIN FIN_TRADE_PRODUCT t2 on t2.FINPROD_ID=n.FINPROD_ID and t2.FINPROD_TYPE2 in (''F03'',''F05'',''F09'') AND t2.BRANCH=0
    where m.PORTFOLIO_ID in ${portfolioCode} and 
    n.TRADE_DATE=date''${valDate}'' 
    AND n.PS IN(''T01'',''04''）
   AND n.IS_CANCEL = ''N''
  UNION ALL
  --4.1内部卖出流程中交易表（标的类资产，投资性质=债权类）
    SELECT
     SUM(NVL(n.TRADE_AMT,0)) TRADE_AMT
    FROM WFL_TRD_PRODUCT_DEAL_ADD m
    INNER JOIN WFL_TRD_PRODUCT_DEAL n ON m.TRADE_ID = n.TRADE_ID AND n.TRADE_STATUS = ''01''
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    INNER JOIN FIN_PRODUCT_ADD PFP on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
    WHERE n.counter_id IN ${portfolioCode} AND 
    n.TRADE_DATE = DATE''${valDate}'' AND n.PS = ''T02''
    AND EXISTS (SELECT 1 FROM SYS_PARA WHERE PARA_CODE = ''is_auto_generate_negate_deal'' AND PARAM_VALUE = ''Y'')
    AND n.IS_CANCEL = ''N''
  UNION ALL
  --4.2内部卖出流程中交易表（标的类资产，投资性质=混合类）
    SELECT
     SUM(NVL(n.TRADE_AMT,0)* NVL(GOAL.CREDITOR_RIGHTS_PER,0)) TRADE_AMT
    FROM WFL_TRD_PRODUCT_DEAL_ADD m
    INNER JOIN WFL_TRD_PRODUCT_DEAL n ON m.TRADE_ID = n.TRADE_ID AND n.TRADE_STATUS = ''01''
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    INNER JOIN FIN_PRODUCT_ADD PFP on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = n.FINPROD_ID
    WHERE n.counter_id IN ${portfolioCode} AND 
    n.TRADE_DATE = DATE''${valDate}'' AND n.PS = ''T02''
    AND EXISTS (SELECT 1 FROM SYS_PARA WHERE PARA_CODE = ''is_auto_generate_negate_deal'' AND PARAM_VALUE = ''Y'')
    AND n.IS_CANCEL = ''N''
      union all
    --5.可转债网下申购：还未做申购确认（投资性质=债权类）

  SELECT SUM(nvl(T.prin_amt,0)) TRADE_AMT
  FROM TRD_PRODUCT_DEAL T
 INNER JOIN TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 INNER JOIN TRD_FIN_PRODUCT_DEAL_TOTAL T3 on T.TOTLE_TRADE_ID = T3.TOTLE_TRADE_ID
 INNER JOIN FIN_PRODUCT_ADD PFP on T.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
 WHERE T1.PORTFOLIO_ID in ${portfolioCode} AND
     T.PS = ''T50''
   AND T.FINPROD_TYPE2 = ''F01''
   AND T.TRADE_STATUS = ''01''
   AND T.IS_CANCEL = ''N''
   AND T.TRADE_DATE = DATE''${valDate}''
   AND NOT EXISTS (
      -- 没有后续申购确认交易
      SELECT 1 FROM TRD_PRODUCT_DEAL TRD WHERE T3.TOTLE_TRADE_ID = TRD.B_TRADE_ID AND TRD.TRADE_STATUS = ''01'' AND TRD.IS_CANCEL = ''N''
)
  AND NOT EXISTS (SELECT 1 FROM WFL_TRD_PRODUCT_DEAL TRD
    INNER JOIN WFL_TRD_PRODUCT_DEAL_ADD TRDA ON TRD.trade_id = TRDA.trade_id
     INNER JOIN SYS_WFL_BUSINESS_LIST swbl on TRDA.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
     WHERE T3.TOTLE_TRADE_ID = TRD.B_TRADE_ID AND TRD.TRADE_STATUS = ''01'' AND TRD.IS_CANCEL=''N''
  )
 union all
 --6.可转债网下申购：申购交易流程中（投资性质=债权类）
 SELECT SUM(NVL(T.prin_amt, 0)) TRADE_AMT
  FROM WFL_TRD_PRODUCT_DEAL T
 INNER JOIN WFL_TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 INNER JOIN FIN_PRODUCT_ADD PFP on T.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
 INNER JOIN SYS_WFL_BUSINESS_LIST swbl on T1.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
 WHERE T1.PORTFOLIO_ID in${portfolioCode} AND
    T.PS = ''T50''
   AND T.FINPROD_TYPE2 = ''F01''
   AND T.TRADE_STATUS = ''01''
   AND T.IS_CANCEL = ''N''
   AND T.TRADE_DATE = DATE''${valDate}''
union all
 --7.可转债网下申购：有申购确认交易（投资性质=债权类）
SELECT SUM(NVL(T.prin_amt, 0)) TRADE_AMT
  FROM TRD_PRODUCT_DEAL T
 INNER JOIN TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 INNER JOIN FIN_PRODUCT_ADD PFP on T.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
 WHERE T.TRADE_STATUS = ''01''
   AND T.IS_CANCEL = ''N''
   AND T1.PORTFOLIO_ID in ${portfolioCode}
   AND T.PS = ''T05''
   AND T.FINPROD_TYPE2 = ''F01''
  union all
 --8.可转债网下申购：申购确认交易(流程中)（投资性质=债权类）
SELECT SUM(NVL(T.prin_amt, 0)) TRADE_AMT
  FROM WFL_TRD_PRODUCT_DEAL T
 INNER JOIN WFL_TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 INNER JOIN FIN_PRODUCT_ADD PFP on T.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
 INNER JOIN SYS_WFL_BUSINESS_LIST swbl on T1.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
 WHERE T1.PORTFOLIO_ID in  ${portfolioCode} AND
    T.PS = ''T05''
   AND T.TRADE_STATUS = ''01''
   AND T.IS_CANCEL = ''N''
   AND T.TRADE_DATE = DATE''${valDate}''
   AND T.FINPROD_TYPE2 = ''F01''
  union all 
--9.债转股（股票）（投资性质=债权类）
  SELECT SUM(Nvl(T.prin_amt,0)) TRADE_AMT
  FROM TRD_FIN_PRODUCT_BARTER T 
  INNER JOIN TRD_PRODUCT_DEAL T3 ON T.TRADE_ID = T3.TRADE_ID
 INNER JOIN TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 INNER JOIN FIN_PRODUCT_ADD PFP on T.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
 WHERE T1.PORTFOLIO_ID in  ${portfolioCode} AND
    T3.PS = ''T49''
   AND T.FINPROD_TYPE = ''F10''
   AND T3.TRADE_STATUS = ''01''
   AND T3.IS_CANCEL = ''N''
   AND T3.TRADE_DATE = DATE''${valDate}''
      union all
    --5.可转债网下申购：还未做申购确认（投资性质=混合类）

  SELECT SUM(nvl(T.prin_amt,0)* NVL(GOAL.CREDITOR_RIGHTS_PER,0)) TRADE_AMT
  FROM TRD_PRODUCT_DEAL T
 INNER JOIN TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
 INNER JOIN TRD_FIN_PRODUCT_DEAL_TOTAL T3 on T.TOTLE_TRADE_ID = T3.TOTLE_TRADE_ID
    INNER JOIN FIN_PRODUCT_ADD PFP on T.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = T.FINPROD_ID
 WHERE T1.PORTFOLIO_ID in ${portfolioCode} AND
     T.PS = ''T50''
   AND T.FINPROD_TYPE2 = ''F01''
   AND T.TRADE_STATUS = ''01''
   AND T.IS_CANCEL = ''N''
   AND T.TRADE_DATE = DATE''${valDate}''
   AND NOT EXISTS (
      -- 没有后续申购确认交易
      SELECT 1 FROM TRD_PRODUCT_DEAL TRD WHERE T3.TOTLE_TRADE_ID = TRD.B_TRADE_ID AND TRD.TRADE_STATUS = ''01'' AND TRD.IS_CANCEL = ''N''
)
  AND NOT EXISTS (SELECT 1 FROM WFL_TRD_PRODUCT_DEAL TRD
    INNER JOIN WFL_TRD_PRODUCT_DEAL_ADD TRDA ON TRD.trade_id = TRDA.trade_id
     INNER JOIN SYS_WFL_BUSINESS_LIST swbl on TRDA.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
     WHERE T3.TOTLE_TRADE_ID = TRD.B_TRADE_ID AND TRD.TRADE_STATUS = ''01'' AND TRD.IS_CANCEL=''N''
  )
 union all
 --6.可转债网下申购：申购交易流程中（投资性质=混合类）
 SELECT SUM(NVL(T.prin_amt, 0)* NVL(GOAL.CREDITOR_RIGHTS_PER,0)) TRADE_AMT
  FROM WFL_TRD_PRODUCT_DEAL T
 INNER JOIN WFL_TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
    INNER JOIN FIN_PRODUCT_ADD PFP on T.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = T.FINPROD_ID
 INNER JOIN SYS_WFL_BUSINESS_LIST swbl on T1.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
 WHERE T1.PORTFOLIO_ID in${portfolioCode} AND
    T.PS = ''T50''
   AND T.FINPROD_TYPE2 = ''F01''
   AND T.TRADE_STATUS = ''01''
   AND T.IS_CANCEL = ''N''
   AND T.TRADE_DATE = DATE''${valDate}''
union all
 --7.可转债网下申购：有申购确认交易（投资性质=混合类）
SELECT SUM(NVL(T.prin_amt, 0)* NVL(GOAL.CREDITOR_RIGHTS_PER,0)) TRADE_AMT
  FROM TRD_PRODUCT_DEAL T
 INNER JOIN TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
    INNER JOIN FIN_PRODUCT_ADD PFP on T.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = T.FINPROD_ID
 WHERE T.TRADE_STATUS = ''01''
   AND T.IS_CANCEL = ''N''
   AND T1.PORTFOLIO_ID in ${portfolioCode}
   AND T.PS = ''T05''
   AND T.FINPROD_TYPE2 = ''F01''
  union all
 --8.可转债网下申购：申购确认交易(流程中)（投资性质=混合类）
SELECT SUM(NVL(T.prin_amt, 0)* NVL(GOAL.CREDITOR_RIGHTS_PER,0)) TRADE_AMT
  FROM WFL_TRD_PRODUCT_DEAL T
 INNER JOIN WFL_TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
    INNER JOIN FIN_PRODUCT_ADD PFP on T.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = T.FINPROD_ID
 INNER JOIN SYS_WFL_BUSINESS_LIST swbl on T1.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
 WHERE T1.PORTFOLIO_ID in  ${portfolioCode} AND
    T.PS = ''T05''
   AND T.TRADE_STATUS = ''01''
   AND T.IS_CANCEL = ''N''
   AND T.TRADE_DATE = DATE''${valDate}''
   AND T.FINPROD_TYPE2 = ''F01''
  union all 
--9.债转股（股票）（投资性质=混合类）
  SELECT SUM(nvl(T.prin_amt,0)* NVL(GOAL.CREDITOR_RIGHTS_PER,0)) TRADE_AMT
  FROM TRD_FIN_PRODUCT_BARTER T 
  INNER JOIN TRD_PRODUCT_DEAL T3 ON T.TRADE_ID = T3.TRADE_ID
 INNER JOIN TRD_PRODUCT_DEAL_ADD T1 ON T.trade_id = T1.trade_id
 INNER JOIN FIN_PRODUCT T2 on T.FINPROD_ID = T2.FINPROD_ID
    INNER JOIN FIN_PRODUCT_ADD PFP on T.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = T.FINPROD_ID
 WHERE T1.PORTFOLIO_ID in  ${portfolioCode} AND
    T3.PS = ''T49''
   AND T.FINPROD_TYPE = ''F10''
   AND T3.TRADE_STATUS = ''01''
   AND T3.IS_CANCEL = ''N''
   AND T3.TRADE_DATE = DATE''${valDate}''
  )';

----事后(日终)
V_CONTENT_05 CLOB := 'select sum(TRADE_AMT) as tradeAmt from
   (
  select nvl(sum(NVL(M.MARKET_VALUE,0)+nvl(TDY_INTINCEXP, 0)),0) as TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
    INNER JOIN FIN_PRODUCT_ADD PFP
    on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''01''
     LEFT JOIN FIN_PRODUCT_TYPE FPT ON m.FINPROD_ID=FPT.FINPROD_ID AND FPT.BRANCH=0
     left join FIN_PRODUCT fin on fin.FINPROD_ID = m.FINPROD_ID
  where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}'' AND m.PORTFOLIO_ID!=m.FINPROD_ID
  union all
  select nvl(sum(NVL(M.MARKET_VALUE * GOAL.CREDITOR_RIGHTS_PER,0)+nvl(TDY_INTINCEXP * GOAL.CREDITOR_RIGHTS_PER, 0)),0) as TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
    INNER JOIN FIN_PRODUCT_ADD PFP on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    left join FIN_PRODUCT fin on fin.FINPROD_ID = m.FINPROD_ID
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.CREDITOR_RIGHTS_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = m.FINPROD_ID
  where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}'' AND m.PORTFOLIO_ID!=m.FINPROD_ID
  union all
  select
    NVL(SUM(NVL(M.MARKET_VALUE,0)+nvl(TDY_INTINCEXP, 0)),0)  as  TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
  INNER JOIN FIN_TRADE_PRODUCT n on m.FINPROD_ID = n.FINPROD_ID and n.FINPROD_TYPE2 in (''F03'',''F05'',''F09'') AND n.BRANCH=0
  where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}''
  )';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'CPZCLZCGM' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'CPZCLZCGM' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'CPZCLZCGM' and PARA_GRANULARITY = '05';
END;
/


   /**风险指标参数:混合基金债权类占比**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='HHJJZQLZB';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('HHJJZQLZB','混合基金债权类占比','N','','dzw','001',sysdate,'dzw',sysdate,'','','Y','String','','REDIS', '');
---监督指标参数明细

DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='HHJJZQLZB';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('HHJJZQLZB','01', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','dzw','001',sysdate,'dzw',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('HHJJZQLZB','02', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','dzw','001',sysdate,'dzw',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('HHJJZQLZB','03', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','dzw','001',sysdate,'dzw',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('HHJJZQLZB','04', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','dzw','001',sysdate,'dzw',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('HHJJZQLZB','05', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','dzw','001',sysdate,'dzw',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'SELECT NVL(GOAL.CREDITOR_RIGHTS_PER, 0) RVALUE,FP.FINPROD_ID RKEY
  FROM FIN_PRODUCT FP
 INNER JOIN FIN_PRODUCT_ADD FPA
    ON FP.FINPROD_ID = FPA.FINPROD_ID
  LEFT JOIN (select *
               from (select FPG.FINPROD_ID,
                            FPG.CREDITOR_RIGHTS_PER,
                            row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
                       from FIN_PRODUCT_GOAL FPG
                      WHERE FPG.GOAL_DATE <= DATE ''${valDate}'')
              where rn = 1) GOAL
    ON FP.FINPROD_ID = GOAL.FINPROD_ID
 WHERE FPA.INVEST_NATURE = ''04''
   AND FP.FINPROD_TYPE2 = ''F06''';

----事后(日间)
V_CONTENT_04 CLOB := 'SELECT NVL(GOAL.CREDITOR_RIGHTS_PER, 0) RVALUE,FP.FINPROD_ID RKEY
  FROM FIN_PRODUCT FP
 INNER JOIN FIN_PRODUCT_ADD FPA
    ON FP.FINPROD_ID = FPA.FINPROD_ID
  LEFT JOIN (select *
               from (select FPG.FINPROD_ID,
                            FPG.CREDITOR_RIGHTS_PER,
                            row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
                       from FIN_PRODUCT_GOAL FPG
                      WHERE FPG.GOAL_DATE <= DATE ''${valDate}'')
              where rn = 1) GOAL
    ON FP.FINPROD_ID = GOAL.FINPROD_ID
 WHERE FPA.INVEST_NATURE = ''04''
   AND FP.FINPROD_TYPE2 = ''F06''';

----事后(日终)
V_CONTENT_05 CLOB := 'SELECT NVL(GOAL.CREDITOR_RIGHTS_PER, 0) RVALUE,FP.FINPROD_ID RKEY
  FROM FIN_PRODUCT FP
 INNER JOIN FIN_PRODUCT_ADD FPA
    ON FP.FINPROD_ID = FPA.FINPROD_ID
  LEFT JOIN (select *
               from (select FPG.FINPROD_ID,
                            FPG.CREDITOR_RIGHTS_PER,
                            row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
                       from FIN_PRODUCT_GOAL FPG
                      WHERE FPG.GOAL_DATE <= DATE ''${valDate}'')
              where rn = 1) GOAL
    ON FP.FINPROD_ID = GOAL.FINPROD_ID
 WHERE FPA.INVEST_NATURE = ''04''
   AND FP.FINPROD_TYPE2 = ''F06''';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'HHJJZQLZB' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'HHJJZQLZB' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'HHJJZQLZB' and PARA_GRANULARITY = '05';
END;
/



----产品债权类资产规模-买入预占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZCLZCGM_01';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZCLZCGM_01','产品债权类资产规模-买入预占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_01';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_01';

----产品债权类资产规模-卖出预占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZCLZCGM_02';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZCLZCGM_02','产品债权类资产规模-卖出预占（不算指标）','N','','system','',sysdate,'dzw',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_02';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_02';

----产品债权类资产规模-买入实占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZCLZCGM_03';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZCLZCGM_03','产品债权类资产规模-买入实占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_03';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_03';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_03','01','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_03','02','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_03','03','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');

----产品债权类资产规模-卖出实占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZCLZCGM_04';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZCLZCGM_04','产品债权类资产规模-卖出实占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_04';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_04','eventType','522cbe1212744d1ba5b91434e5d80f37','','1,2','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_04','ps','522cbe1212744d1ba5b91434e5d80f37','','T07,T08,T09,T11,T38,T39,T54','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_04','investType','522cbe1212744d1ba5b91434e5d80f37','','01,04','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_04','assetType','84eb8cd46bf6488d821258fdb80b946b','','F07,F08,F10','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_04','assetType2','84eb8cd46bf6488d821258fdb80b946b','','02,04','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_04','eventType','84eb8cd46bf6488d821258fdb80b946b','','1,2','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_04','ps','84eb8cd46bf6488d821258fdb80b946b','','T07,T54,T55,T56','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_04';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_04','01','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_04','02','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_04','03','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');

----产品债权类资产规模-买入预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZCLZCGM_05';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZCLZCGM_05','产品债权类资产规模-买入预占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_05';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_05','assetType','0a97eb68fae6411ca92d32a55e5800ae','','F07,F08,F10','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_05','ps','0a97eb68fae6411ca92d32a55e5800ae','','T01','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_05','assetType2','0a97eb68fae6411ca92d32a55e5800ae','','02,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_05','eventType','0a97eb68fae6411ca92d32a55e5800ae','','0','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_05','eventType','5c0d5e7adda2454ea0b714da42c15440','','0','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_05','investType','5c0d5e7adda2454ea0b714da42c15440','','01,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_05','ps','5c0d5e7adda2454ea0b714da42c15440','','T01,T04,T20,T52','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_05';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_05','02','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');

----产品债权类资产规模-卖出预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZCLZCGM_06';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZCLZCGM_06','产品债权类资产规模-卖出预占','Y','','system','',sysdate,'dzw',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_06';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_06','eventType','4f2c48c2d20d47e1b718ff4c27c7ae35','','0','=','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_06','investType','4f2c48c2d20d47e1b718ff4c27c7ae35','','01,04','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_06','ps','4f2c48c2d20d47e1b718ff4c27c7ae35','','T02,T03,T06,T19,T21,T49','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_06';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_06','03','-','amount','','dzw','001',SYSDATE,'dzw',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');

----产品债权类资产规模-买入实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZCLZCGM_07';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZCLZCGM_07','产品债权类资产规模-买入实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_07';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_07','eventType','553393266b3c4968bb4587bd1c627a43','','1,2','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_07','ps','553393266b3c4968bb4587bd1c627a43','','T01,T04,T20,T52','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_07','investType','553393266b3c4968bb4587bd1c627a43','','01,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_07','assetType','de3f13fbb7dd47aba4b139f20ed48eaa','','F07,F08,F10','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_07','assetType2','de3f13fbb7dd47aba4b139f20ed48eaa','','02,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_07','eventType','de3f13fbb7dd47aba4b139f20ed48eaa','','1,2','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_07','ps','de3f13fbb7dd47aba4b139f20ed48eaa','','T01','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_07';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_07','01','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_07','02','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_07','03','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * amount');

----产品债权类资产规模-卖出实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPZCLZCGM_08';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPZCLZCGM_08','产品债权类资产规模-卖出实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_08';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_08','eventType','d62887c99bd14161800ea52a9a185a77','','1,2','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_08','investType','d62887c99bd14161800ea52a9a185a77','','01,04','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPZCLZCGM_08','ps','d62887c99bd14161800ea52a9a185a77','','T02,T03,T06,T19,T21,T49','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPZCLZCGM_08';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_08','01','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * mixAmount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_08','02','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * mixAmount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPZCLZCGM','EVENT_ID_CPZCLZCGM_08','03','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJZQLZB:01[assetCode] * mixAmount');



/**风险指标参数:产品债权类资产规模**/