DELETE FROM SYS_PAGE_DICTLIST  WHERE PAGE_ID ='FinBondTargetAsset002' AND DICT_ID ='EXEC_MODE';
INSERT INTO SYS_PAGE_DICTLIST  VALUES('FinBondTargetAsset002', 'BusiFinExercisePlan', 'EXEC_MODE', '01', '赎回', NULL, 'admin', NULL, sysdate, 'admin', sysdate,null);
INSERT INTO SYS_PAGE_DICTLIST  VALUES('FinBondTargetAsset002', 'BusiFinExercisePlan', 'EXEC_MODE', '02', '回售', NULL, 'admin', NULL, sysdate, 'admin', sysdate,null);
INSERT INTO SYS_PAGE_DICTLIST  VALUES('FinBondTargetAsset002', 'BusiFinExercisePlan', 'EXEC_MODE', '03', '发行人上调票面利率选择权', NULL, 'admin', NULL, sysdate, 'admin', sysdate,null);
INSERT INTO SYS_PAGE_DICTLIST  VALUES('FinBondTargetAsset002', 'BusiFinExercisePlan', 'EXEC_MODE', '04', '发行人/投资人递延兑付选择权', NULL, 'admin', NULL, sysdate, 'admin', sysdate,null);
INSERT INTO SYS_PAGE_DICTLIST  VALUES('FinBondTargetAsset002', 'BusiFinExercisePlan', 'EXEC_MODE', '05', '其他选择权', NULL, 'admin', NULL, sysdate, 'admin', sysdate,null);
