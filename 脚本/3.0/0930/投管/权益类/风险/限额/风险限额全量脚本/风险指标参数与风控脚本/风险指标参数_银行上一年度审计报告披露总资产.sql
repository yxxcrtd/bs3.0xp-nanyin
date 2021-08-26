/**风险指标参数:银行上一年度审计报告披露总资产**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='FV202003020061';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('FV202003020061','银行上一年度审计报告披露总资产','N','','cdj','C001',sysdate,'cdj',sysdate,'','','','', 'FIX','', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_FIX_RECODE WHERE PARA_CODE = 'FV202003020061';
INSERT INTO RSK_ISUP_FIX_RECODE VALUES('FV202003020061','1000000000',to_date('2020-02-26','yyyy-mm-dd'),to_date('','yyyy-mm-dd'),'cdj','C001',SYSDATE,'cdj',SYSDATE,'FIX');





/**风险指标参数:银行上一年度审计报告披露总资产**/