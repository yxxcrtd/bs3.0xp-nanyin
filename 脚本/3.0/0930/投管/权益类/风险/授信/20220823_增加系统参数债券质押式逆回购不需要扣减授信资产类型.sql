DELETE FROM SYS_PARA WHERE PARA_CODE = 'CREDIT_ASSET_TYPE_F03';
INSERT INTO SYS_PARA VALUES ('RSK','CREDIT_ASSET_TYPE_F03','03,06','01','','债券质押式逆回购不需要扣减授信资产类型(资产三级分类),如果需要过滤次级债，则在资产类型后面拼接‘_Y’','system','',sysdate,'system',sysdate,'','01');

