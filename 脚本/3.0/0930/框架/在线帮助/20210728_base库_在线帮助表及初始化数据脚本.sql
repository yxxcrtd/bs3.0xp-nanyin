-- 表脚本
DROP TABLE SYS_PAGE_ELEMENT_HELPDOC;
CREATE TABLE SYS_PAGE_ELEMENT_HELPDOC(
	PAGE_ID VARCHAR2(50) NOT NULL,
	ELEMENT_ID VARCHAR2(50) NOT NULL,
	HREF VARCHAR2(512),
	ALT VARCHAR2(256),
	IMAGESRC VARCHAR2(128),
	TARGET_TYPE VARCHAR2(8) DEFAULT '1' NOT NULL,
	CREATE_USER VARCHAR2(32) NOT NULL,
	CREATE_DEPT VARCHAR2(32),
	CREATE_TIME TIMESTAMP(6) NOT NULL,
	UPDATE_USER VARCHAR2(32) NOT NULL,
	UPDATE_TIME TIMESTAMP(6) NOT NULL,
	CONSTRAINT PK_SYS_PAGE_ELEMENT_HELPDOC PRIMARY KEY (PAGE_ID, ELEMENT_ID)
);
COMMENT ON TABLE SYS_PAGE_ELEMENT_HELPDOC IS '画面帮助手册';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.PAGE_ID IS '画面代码';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.ELEMENT_ID IS '要素代码';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.HREF IS '链接路径：http://必填';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.ALT IS '悬浮提示';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.IMAGESRC IS '图片路径';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.TARGET_TYPE IS '默认1:id配置,暂为备用字段';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.CREATE_USER IS '创建人';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.CREATE_DEPT IS '创建部门';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.UPDATE_USER IS '更新人';
COMMENT ON COLUMN SYS_PAGE_ELEMENT_HELPDOC.UPDATE_TIME IS '更新时间';

-- 初始化数据脚本
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('FinNetValueProductBuild002', 'bussFinProductOpenPlanDto_calcRule', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1a2cjau00i5', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:37', 'system', TIMESTAMP '2021-06-30 21:08:37');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('MstFeeVarieties002', 'feeName', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1a2d81h288r', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:37', 'system', TIMESTAMP '2021-06-30 21:08:37');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('PayTheVatQuery002', 'payTaxVDate', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d17jekqv3a78', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:37', 'system', TIMESTAMP '2021-06-30 21:08:37');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('FinNetProductBonusScheme002', 'bonuScalcFlag', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d2a0vb15ip0u', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:37', 'system', TIMESTAMP '2021-06-30 21:08:37');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('MstSecuritiesAccount002', 'recAcctId', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1r2sv60i156', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:38', 'system', TIMESTAMP '2021-06-30 21:08:38');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('MstSecuritiesAccount002', 'trdAcctId', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1r2sv60i156', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:38', 'system', TIMESTAMP '2021-06-30 21:08:38');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('FinBondTargetAsset002', 'finInterestFactorDto_basis', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1a3lms6sr4q', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:38', 'system', TIMESTAMP '2021-06-30 21:08:38');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('FinBondTargetAsset002', 'finInterestFactorDto_rate', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1a3lms6sr4q', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:38', 'system', TIMESTAMP '2021-06-30 21:08:38');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('TrdBondInvestTrade002', 'yield', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1a3lms6sr4q', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:38', 'system', TIMESTAMP '2021-06-30 21:08:38');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('MstFeeVarieties002', 'feeType', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d17v3ubjc01a', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:39', 'system', TIMESTAMP '2021-06-30 21:08:39');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('MstBankTranFeeManage002', 'feeType', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d17v3ubjc01a', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:39', 'system', TIMESTAMP '2021-06-30 21:08:39');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('MstCommissionSet002', 'feeType', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d17v3ubjc01a', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:39', 'system', TIMESTAMP '2021-06-30 21:08:39');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('TrdBondInvestTrade002', 'ps', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1a3sn3kn51v', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:39', 'system', TIMESTAMP '2021-06-30 21:08:39');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('TrdRepoTrade002', 'tradeMarket', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1a4afl5947i', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:40', 'system', TIMESTAMP '2021-06-30 21:08:40');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('TrdFundInvestTrade002', 'settleSpreed', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1rj533c37kq', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:40', 'system', TIMESTAMP '2021-06-30 21:08:40');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('TrdFundInvestTrade002', 'tradeAmt', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1rj533c37kq', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:40', 'system', TIMESTAMP '2021-06-30 21:08:40');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('ChannelAsset002', 'payType', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1ac12sahdaj', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:40', 'system', TIMESTAMP '2021-06-30 21:08:40');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('AssetsTransfer001', 'AssetsTransfer001_assetType', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1acdkvckv3l', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:41', 'system', TIMESTAMP '2021-06-30 21:08:41');
INSERT INTO SYS_PAGE_ELEMENT_HELPDOC VALUES ('TrdInvestDealQuery001', 'TrdInvestDealQuery001_finprodType2', 'http://192.168.70.190:8181/docs/mindoc/mindoc-1d1ae3bm6n50e', '帮助手册', 'icon-help', '1', 'system', null, TIMESTAMP '2021-06-30 21:08:41', 'system', TIMESTAMP '2021-06-30 21:08:41');

