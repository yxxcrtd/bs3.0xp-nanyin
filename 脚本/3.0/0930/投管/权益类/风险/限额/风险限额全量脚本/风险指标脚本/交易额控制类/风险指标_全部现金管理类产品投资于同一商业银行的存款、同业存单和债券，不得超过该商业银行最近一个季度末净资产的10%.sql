/**风险指标:全部现金管理类产品投资于同一商业银行的存款、同业存单和债券，不得超过该商业银行最近一个季度末净资产的10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='全部现金管理类产品投资于同一商业银行的存款、同业存单和债券，不得超过该商业银行最近一个季度末净资产的10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('8ba6b37d49574c6c86a6e399ad8003fa','全部现金管理类产品投资于同一商业银行的存款、同业存单和债券，不得超过该商业银行最近一个季度末净资产的10%','LIMIT_TRAN','(${XJGM}+${ZJCCSZ})/${FV201907200001}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10.0000","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'02','','','','','','');
 
/**风险指标:全部现金管理类产品投资于同一商业银行的存款、同业存单和债券，不得超过该商业银行最近一个季度末净资产的10%**/