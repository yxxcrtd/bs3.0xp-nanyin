/**风险指标:收益率曲线**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='收益率曲线';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('abb47127979c4c6085a9c27effa6db3b','收益率曲线','LIMIT_DEVIATION','${FV202001010003}-${ZQDYWDDSYLQX}','INTERNAL','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"200","unit":"BP","is_model":"01"}}','{"threshold_1":{"op":">","unit":"BP","is_model":"01"}}','{"threshold_1":{"op":">","unit":"BP","is_model":"01"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:收益率曲线**/