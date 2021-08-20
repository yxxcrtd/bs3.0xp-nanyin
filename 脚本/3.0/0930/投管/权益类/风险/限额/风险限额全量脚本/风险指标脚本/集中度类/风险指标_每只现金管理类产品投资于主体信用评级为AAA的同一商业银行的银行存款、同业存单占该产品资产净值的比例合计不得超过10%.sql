/**风险指标:每只现金管理类产品投资于主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='每只现金管理类产品投资于主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('736f323093754a55af6a28fb7be60464','每只现金管理类产品投资于主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%','LIMIT_CONCENT','${TZYZTXYPJDYAAATYSYYH}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','每只现金管理类产品投资于主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%','system',null,sysdate,'system',sysdate,'02','','','','','','');
 
/**风险指标:每只现金管理类产品投资于主体信用评级为AAA的同一商业银行的银行存款、同业存单占该产品资产净值的比例合计不得超过10%**/