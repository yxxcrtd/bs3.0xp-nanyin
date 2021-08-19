/**风险指标:同一银行理财子公司采用摊余成本法进行核算的现金管理类产品的月末资产净值，合计不得超过其风险准备金月末余额的200倍**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='同一银行理财子公司采用摊余成本法进行核算的现金管理类产品的月末资产净值，合计不得超过其风险准备金月末余额的200倍';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('7149fdea70a94bcaa3983da9b10fe0b6','同一银行理财子公司采用摊余成本法进行核算的现金管理类产品的月末资产净值，合计不得超过其风险准备金月末余额的200倍','LIMIT_SCALE','${YMCPJZC}/${FV202008130061}','SUPERVISION','03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<=","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":"<=","unit":"NONE","is_model":"02"}}','{"threshold_1":{"op":"<=","value":"200.0000","unit":"NONE","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:同一银行理财子公司采用摊余成本法进行核算的现金管理类产品的月末资产净值，合计不得超过其风险准备金月末余额的200倍**/