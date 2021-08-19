/**风险指标:现金管理类产品市值法资产净值与摊余成本法计算的资产净值负偏离度绝对值不超过0.25%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='现金管理类产品市值法资产净值与摊余成本法计算的资产净值负偏离度绝对值不超过0.25%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('d2f8f79486fc491cbc36965be16080a0','现金管理类产品市值法资产净值与摊余成本法计算的资产净值负偏离度绝对值不超过0.25%','LIMIT_DEVIATION','(${YZDJQDDCPJZC}-${TYCBFJSDZCJZ})/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","value":"0.2500","unit":"PERCENT","is_model":"01"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"01"}}','N','','01','现金管理类产品市值法资产净值与摊余成本法计算的资产净值负偏离度绝对值不超过0.25%','system',null,sysdate,'system',sysdate,'02','','','','','','');
 
/**风险指标:现金管理类产品市值法资产净值与摊余成本法计算的资产净值负偏离度绝对值不超过0.25%**/