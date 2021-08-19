/**风险指标:每只现金管理类产品投资于同一机构发行的债券及其作为原始权益人的资产支持证券的比例合计不得超过该产品资产净值的10%**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='每只现金管理类产品投资于同一机构发行的债券及其作为原始权益人的资产支持证券的比例合计不得超过该产品资产净值的10%';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('16ef2f7302b34dd390688858b31bcf7c','每只现金管理类产品投资于同一机构发行的债券及其作为原始权益人的资产支持证券的比例合计不得超过该产品资产净值的10%','LIMIT_CONCENT','${TYJGFXDZQJQZWYSQYRDZCZCZQGZHJ}/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","unit":"PERCENT","is_model":"02"}}','N','','01','每只现金管理类产品投资于同一机构发行的债券及其作为原始权益人的资产支持证券的比例合计不得超过该产品资产净值的10%，投资于国债、中央银行票据、政策性金融债券的除外','system',null,sysdate,'system',sysdate,'02','','','','','','');
 
/**风险指标:每只现金管理类产品投资于同一机构发行的债券及其作为原始权益人的资产支持证券的比例合计不得超过该产品资产净值的10%**/