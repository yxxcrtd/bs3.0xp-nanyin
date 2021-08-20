/**风险指标参数:债券对应万得的收益率曲线**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='ZQDYWDDSYLQX';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('ZQDYWDDSYLQX','债券对应万得的收益率曲线','N','','whmcp','2222222',sysdate,'whmcp',sysdate,'','','Y','String', '','REDIS', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='ZQDYWDDSYLQX';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('ZQDYWDDSYLQX','01', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','whmcp','2222222',sysdate,'whmcp',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('ZQDYWDDSYLQX','02', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','whmcp','2222222',sysdate,'whmcp',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('ZQDYWDDSYLQX','03', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','whmcp','2222222',sysdate,'whmcp',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('ZQDYWDDSYLQX','04', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','whmcp','2222222',sysdate,'whmcp',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('ZQDYWDDSYLQX','05', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','whmcp','2222222',sysdate,'whmcp',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'SELECT FP.FINPROD_ID RKEY,
case when to_date(IC.TRADE_DT,''yyyy-MM-dd'') < IC2.value_date then NVL(IC2.BPYIELD,IC.B_ANAL_YIELD)
else NVL(IC.B_ANAL_YIELD,IC2.BPYIELD) end RVALUE
FROM FIN_PRODUCT FP
LEFT JOIN INTERF_CBONDCURVECNBD IC ON IC.OBJECT_ID=FP.FINPROD_MARKET_ID
LEFT JOIN (
    select t1.BPYIELD,t1.SEC_ID,t1.value_date from MST_BOND_VALUATION_INFO t1
    where t1.value_date = (
        select max(value_date) from MST_BOND_VALUATION_INFO where SEC_ID = t1.SEC_ID and value_date < Date''${valDate}''
    )
) IC2 ON IC2.SEC_ID=FP.FINPROD_MARKET_ID
LEFT JOIN (SELECT MAX(to_date(TRADE_DT,''yyyy-MM-dd'')) AS TRADE_DT,
            OBJECT_ID
            FROM INTERF_CBONDCURVECNBD
            WHERE to_date(TRADE_DT,''yyyy-MM-dd'')<Date''${valDate}'' - 7
            GROUP BY OBJECT_ID
            ) ICN
ON IC.OBJECT_ID                 =ICN.OBJECT_ID
AND to_date(IC.TRADE_DT,''yyyy-MM-dd'')=ICN.TRADE_DT
WHERE FP.FINPROD_TYPE2=''F01''';

----事后(日间)
V_CONTENT_04 CLOB := 'SELECT FP.FINPROD_ID RKEY,
case when to_date(IC.TRADE_DT,''yyyy-MM-dd'') < IC2.value_date then NVL(IC2.BPYIELD,IC.B_ANAL_YIELD)
else NVL(IC.B_ANAL_YIELD,IC2.BPYIELD) end RVALUE
FROM FIN_PRODUCT FP
LEFT JOIN INTERF_CBONDCURVECNBD IC ON IC.OBJECT_ID=FP.FINPROD_MARKET_ID
LEFT JOIN (
    select t1.BPYIELD,t1.SEC_ID,t1.value_date from MST_BOND_VALUATION_INFO t1
    where t1.value_date = (
        select max(value_date) from MST_BOND_VALUATION_INFO where SEC_ID = t1.SEC_ID and value_date <= Date''${valDate}''
    )
) IC2 ON IC2.SEC_ID=FP.FINPROD_MARKET_ID
LEFT JOIN (SELECT MAX(to_date(TRADE_DT,''yyyy-MM-dd'')) AS TRADE_DT,
            OBJECT_ID
            FROM INTERF_CBONDCURVECNBD
            WHERE to_date(TRADE_DT,''yyyy-MM-dd'')<Date''${valDate}'' - 7
            GROUP BY OBJECT_ID
            ) ICN
ON IC.OBJECT_ID                 =ICN.OBJECT_ID
AND to_date(IC.TRADE_DT,''yyyy-MM-dd'')=ICN.TRADE_DT
WHERE FP.FINPROD_TYPE2=''F01''';

----事后(日终)
V_CONTENT_05 CLOB := 'SELECT FP.FINPROD_ID RKEY,
case when to_date(IC.TRADE_DT,''yyyy-MM-dd'') < IC2.value_date then NVL(IC2.BPYIELD,IC.B_ANAL_YIELD)
else NVL(IC.B_ANAL_YIELD,IC2.BPYIELD) end RVALUE
FROM FIN_PRODUCT FP
LEFT JOIN INTERF_CBONDCURVECNBD IC ON IC.OBJECT_ID=FP.FINPROD_MARKET_ID
LEFT JOIN (
    select t1.BPYIELD,t1.SEC_ID,t1.value_date from MST_BOND_VALUATION_INFO t1
    where t1.value_date = (
        select max(value_date) from MST_BOND_VALUATION_INFO where SEC_ID = t1.SEC_ID and value_date <= Date''${valDate}''
    )
) IC2 ON IC2.SEC_ID=FP.FINPROD_MARKET_ID
LEFT JOIN (SELECT MAX(to_date(TRADE_DT,''yyyy-MM-dd'')) AS TRADE_DT,
            OBJECT_ID
            FROM INTERF_CBONDCURVECNBD
            WHERE to_date(TRADE_DT,''yyyy-MM-dd'')<Date''${valDate}'' - 7
            GROUP BY OBJECT_ID
            ) ICN
ON IC.OBJECT_ID                 =ICN.OBJECT_ID
AND to_date(IC.TRADE_DT,''yyyy-MM-dd'')=ICN.TRADE_DT
WHERE FP.FINPROD_TYPE2=''F01''';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'ZQDYWDDSYLQX' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'ZQDYWDDSYLQX' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'ZQDYWDDSYLQX' and PARA_GRANULARITY = '05';
END;
/





/**风险指标参数:债券对应万得的收益率曲线**/