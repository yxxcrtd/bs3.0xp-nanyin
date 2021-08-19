/**风险指标参数:一年的日期（365、366）**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='FV202008200001';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('FV202008200001','一年的日期（365、366）','N','','system','001',sysdate,'system',sysdate,'','','N','', 'FIX','REDIS', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_FIX_RECODE WHERE PARA_CODE = 'FV202008200001';
INSERT INTO RSK_ISUP_FIX_RECODE VALUES('FV202008200001','365',to_date('2020-08-01','yyyy-mm-dd'),to_date('2020-08-20','yyyy-mm-dd'),'system','001',SYSDATE,'system',SYSDATE,'FIX');





/**风险指标参数:一年的日期（365、366）**/