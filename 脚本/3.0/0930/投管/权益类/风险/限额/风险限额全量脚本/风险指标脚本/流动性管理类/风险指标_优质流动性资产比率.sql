/**风险指标:优质流动性资产比率**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='优质流动性资产比率';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('a12fdfacc94a448aaa48fd3ceddb9ba7','优质流动性资产比率','LIMIT_FLUID','(${XJGZZYYHPJZCXJRZQGM}+${XJGM})/${CPJZCJZ}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":"<=","value":"6.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<=","value":"5.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":"<=","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:优质流动性资产比率**/