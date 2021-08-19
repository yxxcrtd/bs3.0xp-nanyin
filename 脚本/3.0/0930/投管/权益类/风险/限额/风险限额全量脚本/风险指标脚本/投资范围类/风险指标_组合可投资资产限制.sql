/**风险指标:组合可投资资产限制**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='组合可投资资产限制';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('69c491053f5041b0812f9f0d3e1aa691','组合可投资资产限制','FORBID_FUND','${ZHKTZZCXZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','','','{"threshold_1":{"value":"Y,Y,Y,Y,Y,","value_cn":"央行票据,金融债,货币市场基金,存放他行,非标债权,","fb_model":"N"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','N');
 
/**风险指标:组合可投资资产限制**/