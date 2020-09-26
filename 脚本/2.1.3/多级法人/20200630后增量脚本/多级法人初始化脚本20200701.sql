
--按钮脚本  监管报送-中债报送信息登记 add by caojin 20200701 start
--产品发行登记
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrProdPersonIssue001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdPersonIssue001','SvrProdPersonIssue001_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdPersonIssue001','SvrProdPersonIssue001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdPersonIssue001','SvrProdPersonIssue001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdPersonIssue001','SvrProdPersonIssue001_btn_chbexport','01','01',null,'导出中债Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdPersonIssue001','SvrProdPersonIssue001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--产品存续期登记
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrProdDurationReg001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdDurationReg001','ProdDurationReg001_btn_submit','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdDurationReg001','ProdDurationReg001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdDurationReg001','ProdDurationReg001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdDurationReg001','ProdDurationReg001_btn_chbexport','01','01',null,'导出中债Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdDurationReg001','ProdDurationReg001_btn_exportDel','01','01',null,'导出删除Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdDurationReg001','SvrProdDurationReg001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--产品募集总量登记
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrRaiseTotalRegist001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrRaiseTotalRegist001','SvrRaiseTotalRegist001_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrRaiseTotalRegist001','SvrRaiseTotalRegist001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrRaiseTotalRegist001','SvrRaiseTotalRegist001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrRaiseTotalRegist001','SvrRaiseTotalRegist001_btn_chbexport','01','01',null,'导出中债Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrRaiseTotalRegist001','SvrRaiseTotalRegist001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--产品申报登记
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrPerProDeclareReg001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrPerProDeclareReg001','SvrPerProDeclareReg001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrPerProDeclareReg001','SvrPerProDeclareReg001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrPerProDeclareReg001','SvrPerProDeclareReg001_btn_chbexport','01','01',null,'导出中债Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrPerProDeclareReg001','SvrPerProDeclareReg001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrPerProDeclareReg001','SvrPerProDeclareReg001_btn_upload','01','01',null,'上传','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrPerProDeclareReg001','SvrPerProDeclareReg001_btn_delt','01','01',null,'删除','system',null,sysdate,'system',sysdate);
--产品终止登记
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrProdTerminationRegist001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdTerminationRegist001','SvrProdTerminationRegist001_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdTerminationRegist001','SvrProdTerminationRegist001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdTerminationRegist001','SvrProdTerminationRegist001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdTerminationRegist001','SvrProdTerminationRegist001_btn_chbexport','01','01',null,'导出中债Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrProdTerminationRegist001','SvrProdTerminationRegist001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--资产要素登记
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrAssetInfoRegist001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrAssetInfoRegist001','AssetInfoRegist001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrAssetInfoRegist001','AssetInfoRegist001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrAssetInfoRegist001','AssetInfoRegist001_btn_chbexport','01','01',null,'导出中债Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrAssetInfoRegist001','AssetInfoRegist001_btn_exportDel','01','01',null,'导出删除Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrAssetInfoRegist001','SvrAssetInfoRegist001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--资产估值信息登记
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrChbAstValueRegist001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbAstValueRegist001','ChbAstValueRegist001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbAstValueRegist001','ChbAstValueRegist001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbAstValueRegist001','ChbAstValueRegist001_btn_chbexport','01','01',null,'导出中债Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbAstValueRegist001','SvrChbAstValueRegist001_btn_exportDel','01','01',null,'导出删除Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbAstValueRegist001','SvrChbAstValueRegist001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--底层资产持仓登记
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrChbBotAstPositionRegist001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbBotAstPositionRegist001','ChbBotAstPositionRegist001_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbBotAstPositionRegist001','ChbBotAstPositionRegist001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbBotAstPositionRegist001','ChbBotAstPositionRegist001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbBotAstPositionRegist001','ChbBotAstPositionRegist001_btn_chbexport','01','01',null,'导出中债Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbBotAstPositionRegist001','SvrChbBotAstPositionRegist001_btn_exportDel','01','01',null,'导出删除Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrChbBotAstPositionRegist001','SvrChbBotAstPositionRegist001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--交易信息登记
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrInvestTradeRegist001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrInvestTradeRegist001','InvestTradeRegist001_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrInvestTradeRegist001','InvestTradeRegist001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrInvestTradeRegist001','InvestTradeRegist001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrInvestTradeRegist001','InvestTradeRegist001_btn_chbexport','01','01',null,'导出中债Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrInvestTradeRegist001','InvestTradeRegist001_btn_exportDel','01','01',null,'导出删除Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrInvestTradeRegist001','SvrInvestTradeRegist001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--G06                                        
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrG06MonthReport001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrG06MonthReport001','ChbG06MonthReport001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrG06MonthReport001','ChbG06MonthReport001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrG06MonthReport001','ChbG06MonthReport001_btn_chbexport','01','01',null,'导出G06 excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrG06MonthReport001','SvrG06MonthReport001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--按钮脚本   监管报送-中债报送信息登记 add by caojin 20200701 end

--按钮脚本  监管报送-人行报送信息登记 add by caojin 20200701 start
--产品基本信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdBaseinfoSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoSub','PbcPrdBaseinfoSub_btn_submit','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoSub','PbcPrdBaseinfoSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoSub','PbcPrdBaseinfoSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoSub','PbcPrdBaseinfoSub_btn_chbexport','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoSub','PbcPrdBaseinfoSub_btn_chbexportxls','01','01',null,'导出人行Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoSub','PbcPrdBaseinfoSub_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--产品起始募集信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdInitRaiseSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdInitRaiseSub','PbcPrdInitRaiseSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdInitRaiseSub','PbcPrdInitRaiseSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdInitRaiseSub','PbcPrdInitRaiseSub_btn_chbexport','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdInitRaiseSub','PbcPrdInitRaiseSub_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--产品终止信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdBaseinfoEndSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoEndSub','PbcPrdBaseinfoEndSub_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoEndSub','PbcPrdBaseinfoEndSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoEndSub','PbcPrdBaseinfoEndSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoEndSub','PbcPrdBaseinfoEndSub_btn_chbexport','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoEndSub','PbcPrdBaseinfoEndSub_btn_chbpedestrianexport','01','01',null,'导出人行Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoEndSub','PbcPrdBaseinfoEndSub_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--产品资金募集信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdCashRaiseSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdCashRaiseSub','PbcPrdCashRaiseSub_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdCashRaiseSub','PbcPrdCashRaiseSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdCashRaiseSub','PbcPrdCashRaiseSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdCashRaiseSub','PbcPrdCashRaiseSub_btn_chbexport','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdCashRaiseSub','PbcPrdCashRaiseSub_btn_chbexportxls','01','01',null,'导出人行Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdCashRaiseSub','PbcPrdCashRaiseSub_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--资产池基本信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdAccountSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountSub','PbcPrdAccountSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountSub','PbcPrdAccountSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountSub','PbcPrdAccountSub_btn_chbexport','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountSub','PbcPrdAccountSub_btn_chbpedestrianexport','01','01',null,'导出人行Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountSub','PbcPrdAccountSub_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--资产池终止信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdAccountEndSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountEndSub','PbcPrdAccountEndSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountEndSub','PbcPrdAccountEndSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountEndSub','PbcPrdAccountEndSub_btn_chbexport','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountEndSub','PbcPrdAccountEndSub_btn_chbpedestrianexport','01','01',null,'导出人行Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountEndSub','PbcPrdAccountEndSub_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--资产负债信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdAssetDebtSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAssetDebtSub','PbcPrdAssetDebtSub_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAssetDebtSub','PbcPrdAssetDebtSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAssetDebtSub','PbcPrdAssetDebtSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAssetDebtSub','PbcPrdAssetDebtSub_btn_chbexport','01','01',null,'导出人行Excel','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAssetDebtSub','PbcPrdAssetDebtSub_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--人行资管产品月度统计表
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcMonthReport001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','ChbMonthReport001_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','ChbMonthReport001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','ChbMonthReport001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','ChbMonthReport001_btn_recal','01','01',null,'重新计算','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','ChbMonthReport001_btn_chbexport','01','01',null,'导出报送文件','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','ChbMonthReport001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--按钮脚本 监管报送-人行报送信息登记  add by caojin 20200701 end

--按钮脚本  监管报送-业务信息维护  by caojin 20200702 start
--产品编码登记管理
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'ProRegistCodeManage001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ProRegistCodeManage001','ProRegistCodeManage001_btn_importnew','01','01',null,'导入新模板','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ProRegistCodeManage001','ProRegistCodeManage001_btn_delete','01','01',null,'删除','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ProRegistCodeManage001','ProRegistCodeManage001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('ProRegistCodeManage001','ProRegistCodeManage001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--统一数据维护
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'SvrOperationalInfo001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrOperationalInfo001','SvrOperationalInfo001_btn_resource','01','01',null,'数据源表配置','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrOperationalInfo001','SvrOperationalInfo001_btn_save','01','01',null,'保存方案','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrOperationalInfo001','SvrOperationalInfo001_btn_delete','01','01',null,'删除','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrOperationalInfo001','SvrOperationalInfo001_btn_import','01','01',null,'导入','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrOperationalInfo001','SvrOperationalInfo001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvrOperationalInfo001','SvrOperationalInfo001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--贷款明细信息维护
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'OperationalLoanInfo001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('OperationalLoanInfo001','OperationalLoanInfo001_btn_add','01','01',null,'新建','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('OperationalLoanInfo001','OperationalLoanInfo001_btn_import','01','01',null,'导入','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('OperationalLoanInfo001','OperationalLoanInfo001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('OperationalLoanInfo001','OperationalLoanInfo001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--人行资金信托代码管理
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcCapAssetInfo001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcCapAssetInfo001','PbcCapAssetInfo001_btn_delete','01','01',null,'删除','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcCapAssetInfo001','PbcCapAssetInfo001_btn_import','01','01',null,'导入','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcCapAssetInfo001','PbcCapAssetInfo001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcCapAssetInfo001','PbcCapAssetInfo001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--按钮脚本  监管报送-业务信息维护  by caojin 20200702 end

--add  by wangyang1 风险限额角色权限配置 start
delete from SYS_PAGE_ELEMENT where PAGE_ID ='RskPortAutoRelatePoint001';
delete from SYS_PAGE_ELEMENT where PAGE_ID ='RskSupervisionIndicatorParam001';
delete from SYS_PAGE_ELEMENT where PAGE_ID ='RskSupervisionPortfPointMain';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionIndicatorParam001', 'RskSupervisionIndicatorParam001_btn_yycs_create', '01', '01', null, '语言参数', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionIndicatorParam001', 'RskSupervisionIndicatorParam001_btn_zdy_create', '01', '01', null, '自定义参数', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionIndicatorParam001', 'RskSupervisionIndicatorParam001_btn_gdz_create', '01', '01', null, '固定值参数', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_btn_creat', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_btn_add', '01', '01', null, '添加', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_btn_delete', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_btn_add', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_btn_delete', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_btn_portBagAddAicon', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_TBLROW_editPortfBag', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_TBLROW_delPortfBag', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_btn_delt', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_TBLROW_node_modify', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_TBLROW_node_delete', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionIndividuation001_btn_tjsz_add', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionIndividuation001_btn_tsjd_add', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionIndividuation001_btn_tszqsz_add', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
--add  by wangyang1 风险限额角色权限配置 end

--add  by wangyang1 风险限额角色权限配置 start
  update SYS_PAGE_ELEMENT set ELEMENT_ID = 'RskSupervisionIndicator001_TBLROW_delete' where ELEMENT_ID = 'RskSupervisionIndicator001_btn_delete';
  update SYS_PAGE_ELEMENT set ELEMENT_ID = 'RskSupervisionIndicator001_TBLROW_edit' where ELEMENT_ID = 'RskSupervisionIndicator001_btn_edit';
--add  by wangyang1 风险限额角色权限配置 end  



------  add by  wujian 风险管理-压力测试 增加机构代码字段 20200703
ALTER TABLE stt_scene ADD INSTITUTION_CODE  VARCHAR2(32) ;
  comment on column stt_scene.INSTITUTION_CODE
  is '机构代码';
 ALTER TABLE STT_STRESS_TEST ADD INSTITUTION_CODE  VARCHAR2(32) ;
  comment on column STT_STRESS_TEST.INSTITUTION_CODE
  is '机构代码';  

--按钮脚本  监管报送-业务信息维护-贷款明细信息维护  by caojin 20200706 start
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'OperationalLoanInfo001';
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'loanplan';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('loanplan','OperationalLoanInfo001_btn_add','01','01',null,'新建','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('loanplan','OperationalLoanInfo001_btn_import','01','01',null,'导入','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('loanplan','loanplan_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('loanplan','loanplan_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
--按钮脚本  监管报送-业务信息维护-贷款明细信息维护  by caojin 20200706 end


-- 债券交易 多组合 投资目的 选择框 add by songyawei 20200707 start
delete from SYS_PAGE_DICTLIST where PAGE_ID = 'TrdBondInvestTrade003' and DICT_ID = 'INV_AIM';
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdBondInvestTrade003', '999999', 'INV_AIM', 'AC', '', '', 'admin', '', '06-3月 -19 12.00.00.000000 上午', 'admin', '06-3月 -19 12.00.00.000000 上午', '');
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdBondInvestTrade003', '999999', 'INV_AIM', 'FVOCI', '', '', 'admin', '', '06-3月 -19 12.00.00.000000 上午', 'admin', '06-3月 -19 12.00.00.000000 上午', '');
insert into SYS_PAGE_DICTLIST (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('TrdBondInvestTrade003', '999999', 'INV_AIM', 'FVTPL', '', '', 'admin', '', '06-3月 -19 12.00.00.000000 上午', 'admin', '06-3月 -19 12.00.00.000000 上午', '');
-- 债券交易 多组合 投资目的 选择框 add by songyawei 20200707 end

 -- 回补信息调整 多组合 投资目的 选择框 add by songyawei 20200713 start
delete sys_page_element s where  page_id = 'FinProductAnapleroticInfo001';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductAnapleroticInfo001', 'FinProductAnapleroticInfo001_btn_add', '01', '01', '', '新建', 'system', '', '28-10月-19 05.25.48.000000 下午', 'system', '28-10月-19 05.25.48.000000 下午');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductAnapleroticInfo001', 'FinProductAnapleroticInfo001_btn_delete', '01', '01', '', '删除', 'system', '', '28-10月-19 05.25.48.000000 下午', 'system', '28-10月-19 05.25.48.000000 下午');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductAnapleroticInfo001', 'FinProductAnapleroticInfo001_btn_edit', '01', '01', '', '编辑', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductAnapleroticInfo001', 'FinProductAnapleroticInfo001_btn_search', '01', '01', '', '查询', 'system', '', '28-10月-19 05.25.48.000000 下午', 'system', '28-10月-19 05.25.48.000000 下午');
 -- 回补信息调整 多组合 投资目的 选择框 add by songyawei 20200713 end