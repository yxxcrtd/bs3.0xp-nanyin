/**风险指标:现金管理类产品市值法资产净值与摊余成本法计算的资产净值正偏离度绝对值不超过0.5%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='现金管理类产品市值法资产净值与摊余成本法计算的资产净值正偏离度绝对值不超过0.5%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('831b0c5159b94a3caaa2cd0d555cc6f1','现金管理类产品市值法资产净值与摊余成本法计算的资产净值正偏离度绝对值不超过0.5%','LIMIT_DEVIATION','${CPGZPLD}','SUPERVISION','03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","value":"0.5000","unit":"PERCENT","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:现金管理类产品市值法资产净值与摊余成本法计算的资产净值正偏离度绝对值不超过0.5%**/