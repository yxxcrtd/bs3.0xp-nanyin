/**风险指标:同一发行人发行的ABN、ABS在所有组合的持仓**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='同一发行人发行的ABN、ABS在所有组合的持仓';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('1fe32f8a2d1d45cbbc27815a6ecaee29','同一发行人发行的ABN、ABS在所有组合的持仓','LIMIT_AMOUNT','${DYFXRB12QMZE}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","value":"20000","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:同一发行人发行的ABN、ABS在所有组合的持仓**/