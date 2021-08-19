/**风险指标参数:单产品债券加权平均久期**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='DCPZQJQPJJQ';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('DCPZQJQPJJQ','单产品债券加权平均久期','N','','admin','001',sysdate,'admin',sysdate,'','','N','', 'String','REDIS', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='DCPZQJQPJJQ';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DCPZQJQPJJQ','01', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','admin','001',sysdate,'admin',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DCPZQJQPJJQ','02', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','admin','001',sysdate,'admin',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DCPZQJQPJJQ','03', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','admin','001',sysdate,'admin',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DCPZQJQPJJQ','04', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','admin','001',sysdate,'admin',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('DCPZQJQPJJQ','05', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','admin','001',sysdate,'admin',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'select 1 from dual';

----事后(日间)
V_CONTENT_04 CLOB := 'select 1 from dual';

----事后(日终)
V_CONTENT_05 CLOB := 'select SUM(dataOne.chiCangMianE/dataTwo.zongHe) AS RVALE
from(
  select (
       case
       when B.FINPROD_TYPE2=''F01'' AND D.ENCASH_YEARS IS NOT NULL then D.ENCASH_YEARS
       when B.FINPROD_TYPE2=''F01'' AND C.TYPE_6 IN (''B03'', ''B04'') then (B.MDATE - DATE ''${valDate}'') / 365
       when B.FINPROD_TYPE2=''F01'' AND B.IS_RIGHT = ''Y'' then ((SELECT MIN(FEP.EXERCISE_DATE)
                            FROM FIN_EXERCISE_PLAN FEP
                            WHERE FEP.FINPROD_ID   = t1.FINPROD_ID
                            AND FEP.FINPROD_TYPE2  = ''F01''
                            AND FEP.EXERCISE_DATE >= DATE ''${valDate}''
                            ) - DATE ''${valDate}''
                             ) / 365
       when B.FINPROD_TYPE2=''F01'' then (B.MDATE - DATE ''${valDate}'') / 365
       else 0
       end)*(NVL(t1.MARKET_VALUE,0)+nvl(t1.TDY_INTINCEXP, 0)) AS chiCangMianE
       FROM PTL_SEC_PIERCE_VALUTION t1
       INNER JOIN FIN_PRODUCT B
       ON t1.FINPROD_ID    = B.FINPROD_ID
       AND B.FINPROD_TYPE2 = ''F01''
       INNER JOIN FIN_PRODUCT_TYPE C
       ON t1.FINPROD_ID = C.FINPROD_ID
       INNER JOIN PTL_PORTFOLIO pp
       ON t1.PORTFOLIO_ID = pp.PORTFOLIO_ID
       LEFT JOIN INTERF_PAYHSRCCBVLT D
       ON DECODE(B.TRADE_MARKET, ''01'', ''IB'', ''03'', ''SH'', ''04'', ''SZ'')
          || B.FINPROD_MARKET_ID = D.SECID
          || DECODE(B.TRADE_MARKET, ''01'', ''.IB'', ''03'', ''.SH'', ''04'', ''.SZ'')
       AND D.CDATE            = TO_CHAR(DATE ''${valDate}'', ''yyyy-MM-dd'')
       WHERE t1.PORTFOLIO_ID IN ${portfolioCode}
       AND t1.CDATE           = DATE ''${valDate}''
  ) dataOne,
  (
    SELECT NVL(SUM(TDY_ASSET_NETVALUE),0) AS zongHe
    FROM PTL_PORTFOLIO_VALUATION
    WHERE PORTFOLIO_ID IN ${portfolioCode} AND CDATE = date''${valDate}''
  ) dataTwo';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'DCPZQJQPJJQ' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'DCPZQJQPJJQ' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'DCPZQJQPJJQ' and PARA_GRANULARITY = '05';
END;
/





/**风险指标参数:单产品债券加权平均久期**/