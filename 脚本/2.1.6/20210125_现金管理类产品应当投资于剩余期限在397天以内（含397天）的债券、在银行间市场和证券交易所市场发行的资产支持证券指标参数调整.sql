--ZGBSS-397【现金管理类产品应当投资于剩余期限在397天以内（含397天）的债券、在银行间市场和证券交易所市场发行的资产支持证券】指标参数不应更新redis
update rsk_isup_indicator_para set IS_UP_REDIS = 'N' where para_code = 'XJGLLCPYDTZYSYQJXZSJQTYNZXQ';
--ZGBSS-398   风险限额-【现金管理类产品应当投资于剩余期限在397天以内（含397天）的债券、在银行间市场和证券交易所市场发行的资产支持证券】日间指标试算结果错误，少一天
declare
	sql01 clob:='WITH ASSET AS (
    -- 剔除央票、同业存单 债券（非含权）
    SELECT FP.FINPROD_ID,FP.MDATE,FP.FINPROD_NAME FROM FIN_PRODUCT FP
    INNER JOIN FIN_PRODUCT_TYPE FPT ON FP.FINPROD_ID = FPT.FINPROD_ID
    WHERE FP.FINPROD_TYPE2 = ''F01'' AND (FPT.type_6 NOT IN (''B03'',''B04'',''B13'')
    OR (FPT.type_6 = ''B13'' AND FP.TRADE_MARKET IN (''01'',''03'',''04'')))
    AND FP.IS_RIGHT = ''N''
  UNION ALL
    -- 剔除央票、同业存单 债券（含权）
    SELECT FP.FINPROD_ID,t1.MDATE,FP.FINPROD_NAME FROM FIN_PRODUCT FP
    INNER JOIN FIN_PRODUCT_TYPE FPT ON FP.FINPROD_ID = FPT.FINPROD_ID
    AND FP.FINPROD_TYPE2 = ''F01'' AND (FPT.type_6 NOT IN (''B03'',''B04'',''B13'')
    OR (FPT.type_6 = ''B13'' AND FP.TRADE_MARKET IN (''01'',''03'',''04'')))
    LEFT JOIN (
        SELECT FINPROD_ID,min(EXERCISE_DATE) MDATE FROM FIN_EXERCISE_PLAN
        WHERE EXERCISE_DATE >= DATE ''${valDate}''
        GROUP BY FINPROD_ID
    ) t1 ON t1.FINPROD_ID = FP.FINPROD_ID
    WHERE FP.IS_RIGHT = ''Y''
)
SELECT distinct TO_NUMBER(ASSET.MDATE - DATE ''${valDate}'') RVALUE, ASSET.FINPROD_ID RKEY, ASSET.FINPROD_NAME RKEYCN
FROM PTL_SEC_POSITION PSP
INNER JOIN ASSET ON ASSET.FINPROD_ID = PSP.FINPROD_ID
WHERE ASSET.MDATE > DATE ''${valDate}''
AND PSP.PORTFOLIO_ID IN ${portfolioCode} AND PSP.CDATE = DATE ''${valDate}''';
begin
	update rsk_isup_indicator_para_dtl set content = sql01 where para_code = 'XJGLLCPYDTZYSYQJXZSJQTYNZXQ' and para_granularity = '04';
	update rsk_isup_indicator_para_dtl set content = sql01 where para_code = 'XJGLLCPYDTZYSYQJXZSJQTYNZXQ' and para_granularity = '05';
end;
/