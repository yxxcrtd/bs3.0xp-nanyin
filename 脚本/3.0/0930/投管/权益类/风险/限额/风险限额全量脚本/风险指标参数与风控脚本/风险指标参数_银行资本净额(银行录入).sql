/**风险指标参数:银行资本净额(银行录入)**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='FV202001010004';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('FV202001010004','银行资本净额(银行录入)','N','','w1','C001',sysdate,'w1',sysdate,'','','','', 'FIX','REDIS', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_FIX_RECODE WHERE PARA_CODE = 'FV202001010004';
INSERT INTO RSK_ISUP_FIX_RECODE VALUES('FV202001010004','1000000000',to_date('2020-01-01','yyyy-mm-dd'),to_date('2021-07-10','yyyy-mm-dd'),'w1','C001',SYSDATE,'w1',SYSDATE,'FIX');





/**风险指标参数:银行资本净额(银行录入)**/