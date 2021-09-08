delete from SYS_PARA where PARA_CODE in  ('infBuyPsAmt','infBuyPsNum'，'infSalPsAmt','infSalPsNum','infEpsPsAmt','infEpsPsNum');
INSERT INTO SYS_PARA VALUES ('INF','infBuyPsAmt','T01,T04,T18,T20,T46,T47,T48,T52',NULL,NULL,'影响资产规模-金额变化的买入类交易方向','system',NULL,sysdate,'system',sysdate,'','01');
INSERT INTO SYS_PARA VALUES ('INF','infSalPsAmt','T02,T03,T06,T07,T08,T09,T11,T19,T21,T38,T39,T54,T56,T59,T61',NULL,NULL,'影响资产规模-金额变化的卖出类交易方向','system',NULL,sysdate,'system',sysdate,'','01');
INSERT INTO SYS_PARA VALUES ('INF','infBuyPsNum','T01,T04,T12,T18,T20,T46,T47,T48,T52',NULL,NULL,'影响资产规模-数量变化的买入类交易方向','system',NULL,sysdate,'system',sysdate,'','01');
INSERT INTO SYS_PARA VALUES ('INF','infSalPsNum','T02,T03,T06,T07,T08,T19,T21,T38,T39,T54,T56,T59,T61',NULL,NULL,'影响资产规模-数量变化的卖出类交易方向','system',NULL,sysdate,'system',sysdate,'','01');
INSERT INTO SYS_PARA VALUES ('INF','infEpsPsAmt','[]',NULL,NULL,'影响资产规模-金额变化的资产中排出案例，[平安资产类型,交易方向]','system',NULL,sysdate,'system',sysdate,'','01');
INSERT INTO SYS_PARA VALUES ('INF','infEpsPsNum','[F01,T07][F01,T08][F01,T39]',NULL,NULL,'影响资产规模-数量变化的资产中排出案例，[平安资产类型,交易方向]','system',NULL,sysdate,'dzw',sysdate,'','01');
