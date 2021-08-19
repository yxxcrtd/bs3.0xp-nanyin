/**风险指标:同一管理人发行的委托投资类型的计划类资产在所有组合持仓**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='同一管理人发行的委托投资类型的计划类资产在所有组合持仓';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('d396bb1948c24263839166852bd85347','同一管理人发行的委托投资类型的计划类资产在所有组合持仓','LIMIT_AMOUNT','${DYGLRJHLZCCCJE}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"400000.0000","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","value":"450000.0000","unit":"WRMB","is_model":"02"}}','{"threshold_1":{"op":">","value":"500000.0000","unit":"WRMB","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:同一管理人发行的委托投资类型的计划类资产在所有组合持仓**/