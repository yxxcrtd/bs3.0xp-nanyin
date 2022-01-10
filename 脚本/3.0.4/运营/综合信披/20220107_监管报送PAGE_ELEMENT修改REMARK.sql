--监管报送
-- 中债excel数据导入 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '删除' WHERE "PAGE_ID" = 'SvrSubOperationalInfo001' AND "ELEMENT_ID" = 'SvrSubOperationalInfo001_btn_delete';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '导入' WHERE "PAGE_ID" = 'SvrSubOperationalInfo001' AND "ELEMENT_ID" = 'SvrSubOperationalInfo001_btn_import';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '查询' WHERE "PAGE_ID" = 'SvrSubOperationalInfo001' AND "ELEMENT_ID" = 'SvrSubOperationalInfo001_btn_search';

-- 资产基本信息维护 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '导入' WHERE "PAGE_ID" = 'AssetInfoDetail001' AND "ELEMENT_ID" = 'AssetInfoDetail001_btn_import';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '新建' WHERE "PAGE_ID" = 'AssetInfoDetail001' AND "ELEMENT_ID" = 'AssetInfoDetail001_btn_add';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '导出' WHERE "PAGE_ID" = 'AssetInfoDetail001' AND "ELEMENT_ID" = 'AssetInfoDetail001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '查询' WHERE "PAGE_ID" = 'AssetInfoDetail001' AND "ELEMENT_ID" = 'AssetInfoDetail001_btn_search';

-- 资产/负债类别配置 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '新建' WHERE "PAGE_ID" = 'AssetTypeSetting001' AND "ELEMENT_ID" = 'AssetTypeSetting001_btn_create';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '查询' WHERE "PAGE_ID" = 'AssetTypeSetting001' AND "ELEMENT_ID" = 'AssetTypeSetting001_btn_search';

-- 识别标识合并 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '导出' WHERE "PAGE_ID" = 'SvrInvestorMerger001' AND "ELEMENT_ID" = 'SvrInvestorMerger001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '查询' WHERE "PAGE_ID" = 'SvrInvestorMerger001' AND "ELEMENT_ID" = 'SvrInvestorMerger001_btn_search';

-- 变形转换规则 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '新建' WHERE "PAGE_ID" = 'SvrTransformRule001' AND "ELEMENT_ID" = 'TransformRule001_btn_add';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '删除' WHERE "PAGE_ID" = 'SvrTransformRule001' AND "ELEMENT_ID" = 'TransformRule001_btn_delete';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '导出' WHERE "PAGE_ID" = 'SvrTransformRule001' AND "ELEMENT_ID" = 'SvrTransformRule001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '查询' WHERE "PAGE_ID" = 'SvrTransformRule001' AND "ELEMENT_ID" = 'SvrTransformRule001_btn_search';

-- 产品份额检查 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '导出' WHERE "PAGE_ID" = 'SvrProShareCheck001' AND "ELEMENT_ID" = 'SvrProShareCheck001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '查询' WHERE "PAGE_ID" = 'SvrProShareCheck001' AND "ELEMENT_ID" = 'SvrProShareCheck001_btn_search';

-- 数据接入检查 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '导出' WHERE "PAGE_ID" = 'ErrorMessage001' AND "ELEMENT_ID" = 'ErrorMessage001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '查询' WHERE "PAGE_ID" = 'ErrorMessage001' AND "ELEMENT_ID" = 'ErrorMessage001_btn_search';

-- 产品存续期登记 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '报送' WHERE "PAGE_ID" = 'SvrProdDurationReg001' AND "ELEMENT_ID" = 'ProdDurationReg001_btn_submitChb';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '重新报送' WHERE "PAGE_ID" = 'SvrProdDurationReg001' AND "ELEMENT_ID" = 'ProdDurationReg001_btn_reSubmitChb';

-- 产品募集总量登记 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '报送' WHERE "PAGE_ID" = 'SvrRaiseTotalRegist001' AND "ELEMENT_ID" = 'SvrRaiseTotalRegist001_btn_submit';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '重新报送' WHERE "PAGE_ID" = 'SvrRaiseTotalRegist001' AND "ELEMENT_ID" = 'SvrRaiseTotalRegist001_btn_reSubmit';

-- 产品申报登记 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '报送' WHERE "PAGE_ID" = 'SvrPerProDeclareReg001' AND "ELEMENT_ID" = 'SvrPerProDeclareReg001_btn_submit';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '重新报送' WHERE "PAGE_ID" = 'SvrPerProDeclareReg001' AND "ELEMENT_ID" = 'SvrPerProDeclareReg001_btn_reSubmit';

-- 产品终止登记 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '报送' WHERE "PAGE_ID" = 'SvrProdTerminationRegist001' AND "ELEMENT_ID" = 'SvrProdTerminationRegist001_btn_submit';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '重新报送' WHERE "PAGE_ID" = 'SvrProdTerminationRegist001' AND "ELEMENT_ID" = 'SvrProdTerminationRegist001_btn_reSubmit';

-- 资产要素登记 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '报送' WHERE "PAGE_ID" = 'SvrAssetInfoRegist001' AND "ELEMENT_ID" = 'AssetInfoRegist001_btn_submit';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '重新报送' WHERE "PAGE_ID" = 'SvrAssetInfoRegist001' AND "ELEMENT_ID" = 'AssetInfoRegist001_btn_reSubmit';

-- 资产估值信息登记 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '报送' WHERE "PAGE_ID" = 'SvrChbAstValueRegist001' AND "ELEMENT_ID" = 'ChbAstValueRegist001_btn_submit';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '重新报送' WHERE "PAGE_ID" = 'SvrChbAstValueRegist001' AND "ELEMENT_ID" = 'ChbAstValueRegist001_btn_reSubmit';

-- 底层资产持仓登记 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '报送' WHERE "PAGE_ID" = 'SvrChbBotAstPositionRegist001' AND "ELEMENT_ID" = 'ChbBotAstPositionRegist001_btn_submit';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '重新报送' WHERE "PAGE_ID" = 'SvrChbBotAstPositionRegist001' AND "ELEMENT_ID" = 'ChbBotAstPositionRegist001_btn_reSubmit';

-- 交易信息登记 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '报送' WHERE "PAGE_ID" = 'SvrInvestTradeRegist001' AND "ELEMENT_ID" = 'InvestTradeRegist001_btn_submit';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '重新报送' WHERE "PAGE_ID" = 'SvrInvestTradeRegist001' AND "ELEMENT_ID" = 'InvestTradeRegist001_btn_reSubmit';

-- 表内外投资业务情况统计表 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '导出' WHERE "PAGE_ID" = 'InvestmentsInfo001' AND "ELEMENT_ID" = 'InvestmentsInfo001_btn_export';

-- 发送状态信息查询 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '查询' WHERE "PAGE_ID" = 'ReportStatusInfo001' AND "ELEMENT_ID" = 'ReportStatusInfo001_btn_search';

-- 中债反馈文件管理 
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '查询' WHERE "PAGE_ID" = 'ChbFeedbackFileManagement001' AND "ELEMENT_ID" = 'ChbFeedbackFileManagement001_btn_search';

-- 产品发行登记
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '报送' WHERE "PAGE_ID" = 'SvrProdPersonIssue001' AND "ELEMENT_ID" = 'SvrProdPersonIssue001_btn_submitChb';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '重新报送' WHERE "PAGE_ID" = 'SvrProdPersonIssue001' AND "ELEMENT_ID" = 'SvrProdPersonIssue001_btn_reSubmitChb';

--查询统计
-- 增值税台账
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '生成' WHERE "PAGE_ID" = 'ValueAddedTaxQuery001' AND "ELEMENT_ID" = 'ValueAddedTaxQuery001_btn_create';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '导出' WHERE "PAGE_ID" = 'ValueAddedTaxQuery001' AND "ELEMENT_ID" = 'ValueAddedTaxQuery001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET "REMARK" = '查询' WHERE "PAGE_ID" = 'ValueAddedTaxQuery001' AND "ELEMENT_ID" = 'ValueAddedTaxQuery001_btn_search';