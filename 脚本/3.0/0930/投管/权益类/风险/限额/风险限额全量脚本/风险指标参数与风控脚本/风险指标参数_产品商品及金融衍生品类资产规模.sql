/**风险指标参数:产品商品及金融衍生品类资产规模**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='SPJJRYSPLZCGM';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('SPJJRYSPLZCGM','产品商品及金融衍生品类资产规模','N','','cdj','C001',sysdate,'cdj',sysdate,'','','Y','String', '','REDIS', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='SPJJRYSPLZCGM';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('SPJJRYSPLZCGM','01', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','Y','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('SPJJRYSPLZCGM','02', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','Y','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('SPJJRYSPLZCGM','03', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','Y','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('SPJJRYSPLZCGM','04', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','Y','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('SPJJRYSPLZCGM','05', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','Y','cdj','C001',sysdate,'cdj',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'select PORTFOLIO_ID RKEY,sum(TRADE_AMT) as RVALUE from (
select m.PORTFOLIO_ID,NVL(SUM(NVL(m.MARKET_VALUE,0)+nvl(m.TDY_INTINCEXP, 0)),0) as TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
  INNER JOIN FIN_PRODUCT_ADD PFP
  on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''03''
  where  m.CDATE =date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID group by m.PORTFOLIO_ID
union all
select m.PORTFOLIO_ID,NVL(SUM(NVL(m.MARKET_VALUE  * GOAL.COMMODITY_DERV_PER,0)+nvl(m.TDY_INTINCEXP  * GOAL.COMMODITY_DERV_PER, 0)),0) as TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
INNER JOIN FIN_PRODUCT_ADD PFP
on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
INNER JOIN (
   select * from (
      select FPG.FINPROD_ID,
             FPG.COMMODITY_DERV_PER,
             row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
      from FIN_PRODUCT_GOAL FPG
      WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
   ) where rn = 1
) GOAL ON GOAL.FINPROD_ID = m.FINPROD_ID
where  m.CDATE =date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID group by m.PORTFOLIO_ID
) group by PORTFOLIO_ID';

----事后(日间)
V_CONTENT_04 CLOB := 'select sum(TRADE_AMT) as tradeAmt from
   (
  select NVL(SUM(NVL(m.MARKET_VALUE,0)+nvl(m.TDY_INTINCEXP, 0)),0) as TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
    INNER JOIN FIN_PRODUCT_ADD PFP
    on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''03''
  where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID
  union all
  select NVL(SUM(NVL(m.MARKET_VALUE  * GOAL.COMMODITY_DERV_PER,0)+nvl(m.TDY_INTINCEXP  * GOAL.COMMODITY_DERV_PER, 0)),0) as TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
  INNER JOIN FIN_PRODUCT_ADD PFP
  on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
  INNER JOIN (
     select * from (
        select FPG.FINPROD_ID,
               FPG.COMMODITY_DERV_PER,
               row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
        from FIN_PRODUCT_GOAL FPG
        WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
     ) where rn = 1
  ) GOAL ON GOAL.FINPROD_ID = m.FINPROD_ID
  where m.PORTFOLIO_ID in ${portfolioCode} and m.CDATE =date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID
  union ALL
  select
   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T59'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T60'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T61'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT
    from  TRD_PRODUCT_DEAL n
    inner join FIN_PRODUCT_ADD FPA on n.FINPROD_ID = FPA.FINPROD_ID  and FPA.INVEST_NATURE =''03''
    INNER JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
    where n1.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T07'',''T08'',''T09'',''T20'',''T21''
    ,''T52'',''T59'',''T60'',''T61'',''T22'',''T23'')
 union ALL
  select
   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T59'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T60'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T61'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0) as  TRADE_AMT
    from  TRD_PRODUCT_DEAL n
    inner join FIN_PRODUCT_ADD FPA on n.FINPROD_ID = FPA.FINPROD_ID  and FPA.INVEST_NATURE =''04''
    INNER JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.COMMODITY_DERV_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = n.FINPROD_ID
  where n1.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T07'',''T08'',''T09'',''T20'',''T21''
    ,''T52'',''T59'',''T60'',''T61'',''T22'',''T23'')
 UNION all
  select
   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T59'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T60'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T61'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT
    from  TRD_PRODUCT_TEMP_DEAL n
    inner join FIN_PRODUCT_ADD FPA on n.FINPROD_ID = FPA.FINPROD_ID  and FPA.INVEST_NATURE =''03''
    INNER JOIN TRD_PRODUCT_TEMP_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
    where n1.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T07'',''T08'',''T09'',''T20'',''T21''
    ,''T52'',''T59'',''T60'',''T61'',''T22'',''T23'')
 UNION all
  select
   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T59'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T60'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T61'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0) as  TRADE_AMT
    from  TRD_PRODUCT_TEMP_DEAL n
    inner join FIN_PRODUCT_ADD FPA on n.FINPROD_ID = FPA.FINPROD_ID  and FPA.INVEST_NATURE =''04''
    INNER JOIN TRD_PRODUCT_TEMP_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.COMMODITY_DERV_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = n.FINPROD_ID
    where n1.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T07'',''T08'',''T09'',''T20'',''T21''
    ,''T52'',''T59'',''T60'',''T61'',''T22'',''T23'')
     union ALL
     select
    nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT
    from WFL_TRD_PRODUCT_DEAL_ADD m
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    INNER JOIN FIN_PRODUCT_ADD PFP on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''03''
    where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T20'',''T21'',''T52'')
  union ALL
     select
    nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0)
    -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0) as  TRADE_AMT
    from WFL_TRD_PRODUCT_DEAL_ADD m
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    INNER JOIN FIN_PRODUCT_ADD PFP on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.COMMODITY_DERV_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = n.FINPROD_ID
    where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T19'',''T20'',''T21'',''T52'')
  union ALL
     select
    nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT
    from WFL_TRD_PRODUCT_DEAL_ADD m
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    INNER JOIN FIN_PRODUCT_ADD PFP on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''03''
    where n.counter_id IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}'' AND n.PS = ''T02''
    AND EXISTS (SELECT 1 FROM SYS_PARA WHERE PARA_CODE = ''is_auto_generate_negate_deal'' AND PARAM_VALUE = ''Y'')
  union ALL
     select
    nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT) * NVL(GOAL.COMMODITY_DERV_PER,0))),0) as  TRADE_AMT
    from WFL_TRD_PRODUCT_DEAL_ADD m
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    INNER JOIN FIN_PRODUCT_ADD PFP on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
    INNER JOIN (
       select * from (
          select FPG.FINPROD_ID,
                 FPG.COMMODITY_DERV_PER,
                 row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
          from FIN_PRODUCT_GOAL FPG
          WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
       ) where rn = 1
    ) GOAL ON GOAL.FINPROD_ID = n.FINPROD_ID
    where n.counter_id IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}'' AND n.PS = ''T02''
    AND EXISTS (SELECT 1 FROM SYS_PARA WHERE PARA_CODE = ''is_auto_generate_negate_deal'' AND PARAM_VALUE = ''Y'')
  )';

----事后(日终)
V_CONTENT_05 CLOB := 'select sum(TRADE_AMT) as TRADE_AMT from (
select NVL(SUM(NVL(m.MARKET_VALUE,0)+nvl(m.TDY_INTINCEXP, 0)),0) as  TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
  INNER JOIN FIN_PRODUCT_ADD PFP
  on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''03''
  where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}'' AND m.PORTFOLIO_ID!=m.FINPROD_ID
union all
select NVL(SUM(NVL(m.MARKET_VALUE  * GOAL.COMMODITY_DERV_PER,0)+nvl(m.TDY_INTINCEXP  * GOAL.COMMODITY_DERV_PER, 0)),0) as TRADE_AMT from PTL_SEC_PIERCE_VALUTION m
INNER JOIN FIN_PRODUCT_ADD PFP
on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''04''
INNER JOIN (
   select * from (
      select FPG.FINPROD_ID,
             FPG.COMMODITY_DERV_PER,
             row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
      from FIN_PRODUCT_GOAL FPG
      WHERE FPG.GOAL_DATE <= to_date(''${valDate}'', ''yyyy-MM-dd'')
   ) where rn = 1
) GOAL ON GOAL.FINPROD_ID = m.FINPROD_ID
where m.PORTFOLIO_ID in ${portfolioCode} and m.CDATE =date''${valDate}'' AND m.PORTFOLIO_ID!=m.FINPROD_ID group by m.PORTFOLIO_ID
)';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'SPJJRYSPLZCGM' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'SPJJRYSPLZCGM' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'SPJJRYSPLZCGM' and PARA_GRANULARITY = '05';
END;
/


   /**风险指标参数:混合基金商品衍生品占比**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='HHJJSPYSPZB';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('HHJJSPYSPZB','混合基金商品衍生品占比','N','','dzw','001',sysdate,'dzw',sysdate,'','','Y','String','','REDIS', '');
---监督指标参数明细

DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='HHJJSPYSPZB';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('HHJJSPYSPZB','01', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','dzw','001',sysdate,'dzw',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('HHJJSPYSPZB','02', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','dzw','001',sysdate,'dzw',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('HHJJSPYSPZB','03', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','dzw','001',sysdate,'dzw',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('HHJJSPYSPZB','04', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','dzw','001',sysdate,'dzw',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('HHJJSPYSPZB','05', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','dzw','001',sysdate,'dzw',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'SELECT NVL(GOAL.COMMODITY_DERV_PER, 0) RVALUE,FP.FINPROD_ID RKEY
  FROM FIN_PRODUCT FP
 INNER JOIN FIN_PRODUCT_ADD FPA
    ON FP.FINPROD_ID = FPA.FINPROD_ID
  LEFT JOIN (select *
               from (select FPG.FINPROD_ID,
                            FPG.COMMODITY_DERV_PER,
                            row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
                       from FIN_PRODUCT_GOAL FPG
                      WHERE FPG.GOAL_DATE <= DATE ''${valDate}'')
              where rn = 1) GOAL
    ON FP.FINPROD_ID = GOAL.FINPROD_ID
 WHERE FPA.INVEST_NATURE = ''04''
   AND FP.FINPROD_TYPE2 = ''F06''';

----事后(日间)
V_CONTENT_04 CLOB := 'SELECT NVL(GOAL.COMMODITY_DERV_PER, 0) RVALUE,FP.FINPROD_ID RKEY
  FROM FIN_PRODUCT FP
 INNER JOIN FIN_PRODUCT_ADD FPA
    ON FP.FINPROD_ID = FPA.FINPROD_ID
  LEFT JOIN (select *
               from (select FPG.FINPROD_ID,
                            FPG.COMMODITY_DERV_PER,
                            row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
                       from FIN_PRODUCT_GOAL FPG
                      WHERE FPG.GOAL_DATE <= DATE ''${valDate}'')
              where rn = 1) GOAL
    ON FP.FINPROD_ID = GOAL.FINPROD_ID
 WHERE FPA.INVEST_NATURE = ''04''
   AND FP.FINPROD_TYPE2 = ''F06''';

----事后(日终)
V_CONTENT_05 CLOB := 'SELECT NVL(GOAL.COMMODITY_DERV_PER, 0) RVALUE,FP.FINPROD_ID RKEY
  FROM FIN_PRODUCT FP
 INNER JOIN FIN_PRODUCT_ADD FPA
    ON FP.FINPROD_ID = FPA.FINPROD_ID
  LEFT JOIN (select *
               from (select FPG.FINPROD_ID,
                            FPG.COMMODITY_DERV_PER,
                            row_number() over(partition by FPG.FINPROD_ID order by FPG.GOAL_DATE desc) rn
                       from FIN_PRODUCT_GOAL FPG
                      WHERE FPG.GOAL_DATE <= DATE ''${valDate}'')
              where rn = 1) GOAL
    ON FP.FINPROD_ID = GOAL.FINPROD_ID
 WHERE FPA.INVEST_NATURE = ''04''
   AND FP.FINPROD_TYPE2 = ''F06''';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'HHJJSPYSPZB' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'HHJJSPYSPZB' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'HHJJSPYSPZB' and PARA_GRANULARITY = '05';
END;
/



----产品商品及金融衍生品类资产规模-买入预占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_SPJJRYSPLZCGM_01';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_SPJJRYSPLZCGM_01','产品商品及金融衍生品类资产规模-买入预占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_01';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_01';

----产品商品及金融衍生品类资产规模-卖出预占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_SPJJRYSPLZCGM_02';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_SPJJRYSPLZCGM_02','产品商品及金融衍生品类资产规模-卖出预占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_02';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_02';

----产品商品及金融衍生品类资产规模-买入实占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_SPJJRYSPLZCGM_03';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_SPJJRYSPLZCGM_03','产品商品及金融衍生品类资产规模-买入实占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_03';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_03';
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_03','01','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_03','02','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_03','03','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');

----产品商品及金融衍生品类资产规模-卖出实占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_SPJJRYSPLZCGM_04';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_SPJJRYSPLZCGM_04','产品商品及金融衍生品类资产规模-卖出实占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_04';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_04','assetType','42d994387ae9490f8a2a1da08e3c453d','','F16,F12','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_04','ps','42d994387ae9490f8a2a1da08e3c453d','','T07','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_04','investType','42d994387ae9490f8a2a1da08e3c453d','','03,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_04','assetType','73a5bc3ba70a4a8cac6eb988f3b23d87','','F03,F04,F09,F02,F06','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_04','ps','73a5bc3ba70a4a8cac6eb988f3b23d87','','T07,T08,T09,T11','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_04','investType','73a5bc3ba70a4a8cac6eb988f3b23d87','','03,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_04','assetType','df5e4b5f34584163839d148498bcc74c','','F18','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_04','investType','df5e4b5f34584163839d148498bcc74c','','03,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_04','ps','df5e4b5f34584163839d148498bcc74c','','T59,T60,T61','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_04';
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_04','01','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_04','02','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_04','03','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');

----产品商品及金融衍生品类资产规模-买入预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_SPJJRYSPLZCGM_05';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_SPJJRYSPLZCGM_05','产品商品及金融衍生品类资产规模-买入预占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_05';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_05','assetType','eaa73a2d468f4f638568421db59eaf71','','F03,F04,F09,F02,F06','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_05','eventType','eaa73a2d468f4f638568421db59eaf71','','0','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_05','investType','eaa73a2d468f4f638568421db59eaf71','','03,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_05','ps','eaa73a2d468f4f638568421db59eaf71','','T01,T04,T20,T52','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_05';
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_05','02','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');

----产品商品及金融衍生品类资产规模-卖出预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_SPJJRYSPLZCGM_06';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_SPJJRYSPLZCGM_06','产品商品及金融衍生品类资产规模-卖出预占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_06';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_06','assetType','b38f64a547bc42ec84a8200d3a51bdda','','F03,F04,F09,F02,F06','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_06','eventType','b38f64a547bc42ec84a8200d3a51bdda','','0','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_06','investType','b38f64a547bc42ec84a8200d3a51bdda','','03,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_06','ps','b38f64a547bc42ec84a8200d3a51bdda','','T02,T06,T21,T19','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_06';
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_06','03','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');

----产品商品及金融衍生品类资产规模-买入实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_SPJJRYSPLZCGM_07';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_SPJJRYSPLZCGM_07','产品商品及金融衍生品类资产规模-买入实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_07';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_07','assetType','eaa73a2d468f4f638568421db59eaf71','','F03,F04,F09,F02,F06','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_07','eventType','eaa73a2d468f4f638568421db59eaf71','','1,2','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_07','investType','eaa73a2d468f4f638568421db59eaf71','','03,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_07','ps','eaa73a2d468f4f638568421db59eaf71','','T01,T04,T20,T52','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_07';
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_07','01','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_07','02','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_07','03','+','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * amount');

----产品商品及金融衍生品类资产规模-卖出实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_SPJJRYSPLZCGM_08';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_SPJJRYSPLZCGM_08','产品商品及金融衍生品类资产规模-卖出实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_08';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_08','assetType','b38f64a547bc42ec84a8200d3a51bdda','','F03,F04,F09,F02,F06','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_08','eventType','b38f64a547bc42ec84a8200d3a51bdda','','1,2','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_08','investType','b38f64a547bc42ec84a8200d3a51bdda','','03,04','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_SPJJRYSPLZCGM_08','ps','b38f64a547bc42ec84a8200d3a51bdda','','T02,T06,T21,T19','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_SPJJRYSPLZCGM_08';
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_08','01','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * mixAmount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_08','02','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * mixAmount');
INSERT INTO RSK_EVENT_PARA_REL VALUES('SPJJRYSPLZCGM','EVENT_ID_SPJJRYSPLZCGM_08','03','-','formula','','system','',SYSDATE,'system',SYSDATE,'','HHJJSPYSPZB:01[assetCode] * mixAmount');



/**风险指标参数:产品商品及金融衍生品类资产规模**/