/**风险指标:同一管理人发行的委托投资类型的计划类资产在所有组合持仓/该管理人所有托投资类型的计划类资产的发行规模**/

 DELETE FROM RSK_ISUP_INDICATOR WHERE IND_NAME='同一管理人发行的委托投资类型的计划类资产在所有组合持仓/该管理人所有托投资类型的计划类资产的发行规模';
 INSERT INTO RSK_ISUP_INDICATOR VALUES('91c0a86c598a43829c06cf417d014688','同一管理人发行的委托投资类型的计划类资产在所有组合持仓/该管理人所有托投资类型的计划类资产的发行规模','LIMIT_AMOUNT','${DYGLRJHLZCCCJE}/${ZCFXGM}','SUPERVISION','01,02,03','DAYTIME,DAYEND','','{"threshold_1":{"op":">","value":"8.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"9.0000","unit":"PERCENT","is_model":"02"}}','{"threshold_1":{"op":">","value":"10","unit":"PERCENT","is_model":"02"}}','N','','01','','system',null,sysdate,'system',sysdate,'03','','','','','','Y');
 
/**风险指标:同一管理人发行的委托投资类型的计划类资产在所有组合持仓/该管理人所有托投资类型的计划类资产的发行规模**/