DELETE FROM INTERF_POSITION_CONFIG WHERE TRADE_PLAT = 'TRANS_MATCH' AND FINPROD_TYPE IN ('F01','F01-B14') AND PS IN ('T39','T22','T23','T41');

/*债券存续期接口*/
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F01', 'T39', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'Bond002', 'system', null, sysdate, 'system', sysdate, '债券存续期接口-非可转债-成交匹配-行权赎回', 'Y');
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F01', 'T22', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'Bond002', 'system', null, sysdate, 'system', sysdate, '债券存续期接口-非可转债-成交匹配-收息到账', 'Y');
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F01', 'T23', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'Bond002', 'system', null, sysdate, 'system', sysdate, '债券存续期接口-非可转债-成交匹配-还本到账', 'Y');
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F01', 'T41', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'Bond002', 'system', null, sysdate, 'system', sysdate, '债券存续期接口-非可转债-成交匹配-上市流通', 'Y');
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F01-B14', 'T39', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'Bond003', 'system', null, sysdate, 'system', sysdate, '债券存续期接口-可转债-成交匹配-行权赎回', 'Y');
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F01-B14', 'T22', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'Bond003', 'system', null, sysdate, 'system', sysdate, '债券存续期接口-可转债-成交匹配-收息到账', 'Y');
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F01-B14', 'T23', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'Bond003', 'system', null, sysdate, 'system', sysdate, '债券存续期接口-可转债-成交匹配-还本到账', 'Y');
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F01-B14', 'T41', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'Bond003', 'system', null, sysdate, 'system', sysdate, '债券存续期接口-可转债-成交匹配-上市流通', 'Y');



DELETE FROM INTERF_POSITION_CONFIG WHERE TRADE_PLAT = 'TRANS_MATCH' AND FINPROD_TYPE IN ('F02','F03') AND PS IN ('T22','T23');
/*回购存续期接口*/
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F02', 'T22', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'REPO003', 'system', null, sysdate, 'system', sysdate, '正回购存续期接口-成交匹配-收息到账', 'Y');
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F02', 'T23', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'REPO003', 'system', null, sysdate, 'system', sysdate, '正回购存续期接口-成交匹配-还本到账', 'Y');
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F03', 'T22', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'REPO003', 'system', null, sysdate, 'system', sysdate, '逆回购存续期接口-成交匹配-收息到账', 'Y');
INSERT INTO INTERF_POSITION_CONFIG (TRADE_PLAT, FINPROD_TYPE, PS, BUSI_ACT, POSITION_TYPE, ENTITY, INTERF_TYPE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, REMARK, ENABLE) 
VALUES ('TRANS_MATCH', 'F03', 'T23', '19', 'TRD', 'com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto', 'REPO003', 'system', null, sysdate, 'system', sysdate, '逆回购存续期接口-成交匹配-收息到账', 'Y');