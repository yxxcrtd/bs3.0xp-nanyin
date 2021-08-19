/**风险指标:每只现金管理类产品投资于所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='每只现金管理类产品投资于所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('81d7bca4f9344a3e8c5dff805b1eb774','每只现金管理类产品投资于所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%','LIMIT_CONCENT','${TZYSYZTXYPJDYAAAJGHJ}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','每只现金管理类产品投资于所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%','system',null,sysdate,'system',sysdate,'02','','','','','','');
 
/**风险指标:每只现金管理类产品投资于所有主体信用评级低于AAA的机构发行的金融工具的比例合计不得超过该产品资产净值的10%**/