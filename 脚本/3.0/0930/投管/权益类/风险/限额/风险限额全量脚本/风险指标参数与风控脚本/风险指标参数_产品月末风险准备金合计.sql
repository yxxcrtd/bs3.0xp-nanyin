/**风险指标参数:产品月末风险准备金合计**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='FV202008130061';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('FV202008130061','产品月末风险准备金合计','N','','zhangrui1','001',sysdate,'zhangrui1',sysdate,'','','N','', 'FIX','', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_FIX_RECODE WHERE PARA_CODE = 'FV202008130061';
INSERT INTO RSK_ISUP_FIX_RECODE VALUES('FV202008130061','100000000',to_date('2020-08-13','yyyy-mm-dd'),to_date('2020-09-13','yyyy-mm-dd'),'zhangrui1','001',SYSDATE,'zhangrui1',SYSDATE,'FIX');





/**风险指标参数:产品月末风险准备金合计**/