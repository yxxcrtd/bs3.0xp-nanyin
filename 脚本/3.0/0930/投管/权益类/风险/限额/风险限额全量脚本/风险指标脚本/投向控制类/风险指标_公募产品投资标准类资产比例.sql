/**风险指标:公募产品投资标准类资产比例**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='公募产品投资标准类资产比例';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('b493ae793f154a7486eb5ccb4763f08b','公募产品投资标准类资产比例','LIMIT_INVEST','(${GMCPSTZBZLZC}+${GMCPTZGPZZ})/${CPJZCJZ}','SUPERVISION','01,02','','','{"threshold_1":{"op":">","value":"45","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"48","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"50","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:公募产品投资标准类资产比例**/