/**风险指标:同一机构发行的债券及其作为原始权益人的资产支持证券的比例合计不得超过该产品资产净值的10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='同一机构发行的债券及其作为原始权益人的资产支持证券的比例合计不得超过该产品资产净值的10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('3f219bc189f941cb9ba8e81a25a60d99','同一机构发行的债券及其作为原始权益人的资产支持证券的比例合计不得超过该产品资产净值的10%','LIMIT_CONCENT','${JGZQGZHJ}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:同一机构发行的债券及其作为原始权益人的资产支持证券的比例合计不得超过该产品资产净值的10%**/