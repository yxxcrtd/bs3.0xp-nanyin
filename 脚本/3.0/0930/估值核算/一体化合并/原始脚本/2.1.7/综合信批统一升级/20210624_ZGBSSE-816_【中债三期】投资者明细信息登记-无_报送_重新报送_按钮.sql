
--投资者身份信息登记:EquityInfoRegist001
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfoRegist001' AND ELEMENT_ID='EquityInfoRegist001_btn_cancelManual';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfoRegist001' AND ELEMENT_ID='EquityInfoRegist001_btn_chbexport';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfoRegist001' AND ELEMENT_ID='EquityInfoRegist001_btn_checkData';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfoRegist001' AND ELEMENT_ID='EquityInfoRegist001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfoRegist001' AND ELEMENT_ID='EquityInfoRegist001_btn_exportDel';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfoRegist001' AND ELEMENT_ID='EquityInfoRegist001_btn_manual';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfoRegist001' AND ELEMENT_ID='EquityInfoRegist001_btn_search';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'EquityInfoRegist001' AND ELEMENT_ID='EquityInfoRegist001_btn_submit';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='EquityInfoRegist001' and ELEMENT_ID='EquityInfoRegist001_btn_reset' and REMARK='重置';
delete from SYS_PAGE_ELEMENT where PAGE_ID='EquityInfoRegist001' and ELEMENT_ID='EquityInfoRegist001_btn_submitAgain' and REMARK='重新报送';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('EquityInfoRegist001','EquityInfoRegist001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('EquityInfoRegist001','EquityInfoRegist001_btn_submitAgain','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');

--投资者持有信息登记:InvestorsHoldInfoRegist001
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfoRegist001' AND ELEMENT_ID='InvestorsHoldInfoRegist001_btn_cancelManual';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfoRegist001' AND ELEMENT_ID='InvestorsHoldInfoRegist001_btn_chbexport';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfoRegist001' AND ELEMENT_ID='InvestorsHoldInfoRegist001_btn_checkData';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfoRegist001' AND ELEMENT_ID='InvestorsHoldInfoRegist001_btn_export';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfoRegist001' AND ELEMENT_ID='InvestorsHoldInfoRegist001_btn_manual';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfoRegist001' AND ELEMENT_ID='InvestorsHoldInfoRegist001_btn_reset';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfoRegist001' AND ELEMENT_ID='InvestorsHoldInfoRegist001_btn_search';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestorsHoldInfoRegist001' AND ELEMENT_ID='InvestorsHoldInfoRegist001_btn_submit';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='InvestorsHoldInfoRegist001' and ELEMENT_ID='InvestorsHoldInfoRegist001_btn_submitAgain' and REMARK='重新报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='InvestorsHoldInfoRegist001' and ELEMENT_ID='InvestorsHoldInfoRegist001_btn_exportDel' and REMARK='导出删除模板';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('InvestorsHoldInfoRegist001','InvestorsHoldInfoRegist001_btn_submitAgain','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('InvestorsHoldInfoRegist001','InvestorsHoldInfoRegist001_btn_exportDel','01','01',null,'导出删除模板','system',null,sysdate,'system',sysdate,'C');

--投资者明细信息登记:SvrEquityBusiInfoRegist001
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusiInfoRegist001' and ELEMENT_ID='EquityBusiInfoRegist001_btn_checkData' and REMARK='数据检查';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusiInfoRegist001' and ELEMENT_ID='EquityBusiInfoRegist001_btn_submitAgain' and REMARK='重新报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusiInfoRegist001' and ELEMENT_ID='EquityBusiInfoRegist001_btn_submit' and REMARK='报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusiInfoRegist001' and ELEMENT_ID='EquityBusiInfoRegist001_btn_manual' and REMARK='手工处理';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusiInfoRegist001' and ELEMENT_ID='EquityBusiInfoRegist001_btn_cancelManual' and REMARK='取消手工处理';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusiInfoRegist001' and ELEMENT_ID='EquityBusiInfoRegist001_btn_export' and REMARK='导出查询结果';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusiInfoRegist001' and ELEMENT_ID='EquityBusiInfoRegist001_btn_chbexport' and REMARK='导出中债Excel';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusiInfoRegist001' and ELEMENT_ID='EquityBusiInfoRegist001_btn_exportDel' and REMARK='导出删除模板';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusiInfoRegist001' and ELEMENT_ID='SvrEquityBusiInfoRegist001_btn_search' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrEquityBusiInfoRegist001' and ELEMENT_ID='SvrEquityBusiInfoRegist001_btn_reset' and REMARK='重置';

insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusiInfoRegist001','EquityBusiInfoRegist001_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusiInfoRegist001','EquityBusiInfoRegist001_btn_submit','01','01',null,'报送','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusiInfoRegist001','EquityBusiInfoRegist001_btn_submitAgain','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusiInfoRegist001','EquityBusiInfoRegist001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusiInfoRegist001','EquityBusiInfoRegist001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusiInfoRegist001','EquityBusiInfoRegist001_btn_export','01','01',null,'导出查询结果','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusiInfoRegist001','EquityBusiInfoRegist001_btn_chbexport','01','01',null,'导出中债Excel','system',null,sysdate,'system',sysdate,'COM');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusiInfoRegist001','EquityBusiInfoRegist001_btn_exportDel','01','01',null,'导出删除模板','system',null,sysdate,'system',sysdate,'C');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusiInfoRegist001','SvrEquityBusiInfoRegist001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrEquityBusiInfoRegist001','SvrEquityBusiInfoRegist001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');

--产品发行登记SvrProdPersonIssue001:添加报送与重新报送
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrProdPersonIssue001' and ELEMENT_ID='SvrProdPersonIssue001_btn_submitChb' and REMARK='报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrProdPersonIssue001' and ELEMENT_ID='SvrProdPersonIssue001_btn_reSubmitChb' and REMARK='重新报送';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrProdPersonIssue001','SvrProdPersonIssue001_btn_submitChb','01','01',null,'报送','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrProdPersonIssue001','SvrProdPersonIssue001_btn_reSubmitChb','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');

--产品存续期登记SvrProdDurationReg001:添加报送与重新报送
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrProdDurationReg001' and ELEMENT_ID='ProdDurationReg001_btn_submitChb' and REMARK='报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrProdDurationReg001' and ELEMENT_ID='ProdDurationReg001_btn_reSubmitChb' and REMARK='重新报送';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrProdDurationReg001','ProdDurationReg001_btn_submitChb','01','01',null,'报送','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrProdDurationReg001','ProdDurationReg001_btn_reSubmitChb','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');

--产品募集总量登记SvrRaiseTotalRegist001:添加报送与重新报送
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrRaiseTotalRegist001' and ELEMENT_ID='SvrRaiseTotalRegist001_btn_submit' and REMARK='报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrRaiseTotalRegist001' and ELEMENT_ID='SvrRaiseTotalRegist001_btn_reSubmit' and REMARK='重新报送';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrRaiseTotalRegist001','SvrRaiseTotalRegist001_btn_submit','01','01',null,'报送','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrRaiseTotalRegist001','SvrRaiseTotalRegist001_btn_reSubmit','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');

--产品申报登记SvrPerProDeclareReg001:添加报送与重新报送
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrPerProDeclareReg001' and ELEMENT_ID='SvrPerProDeclareReg001_btn_submit' and REMARK='报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrPerProDeclareReg001' and ELEMENT_ID='SvrPerProDeclareReg001_btn_reSubmit' and REMARK='重新报送';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrPerProDeclareReg001','SvrPerProDeclareReg001_btn_submit','01','01',null,'报送','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrPerProDeclareReg001','SvrPerProDeclareReg001_btn_reSubmit','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');

--产品终止登记SvrProdTerminationRegist001:添加报送与重新报送
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrProdTerminationRegist001' and ELEMENT_ID='SvrProdTerminationRegist001_btn_submit' and REMARK='报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrProdTerminationRegist001' and ELEMENT_ID='SvrProdTerminationRegist001_btn_reSubmit' and REMARK='重新报送';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrProdTerminationRegist001','SvrProdTerminationRegist001_btn_submit','01','01',null,'报送','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrProdTerminationRegist001','SvrProdTerminationRegist001_btn_reSubmit','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');

--资产要素登记SvrAssetInfoRegist001:添加报送与重新报送
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrAssetInfoRegist001' and ELEMENT_ID='AssetInfoRegist001_btn_submit' and REMARK='报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrAssetInfoRegist001' and ELEMENT_ID='AssetInfoRegist001_btn_reSubmit' and REMARK='重新报送';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrAssetInfoRegist001','AssetInfoRegist001_btn_submit','01','01',null,'报送','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrAssetInfoRegist001','AssetInfoRegist001_btn_reSubmit','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');

--资产估值信息登记SvrChbAstValueRegist001:添加报送与重新报送
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrChbAstValueRegist001' and ELEMENT_ID='ChbAstValueRegist001_btn_submit' and REMARK='报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrChbAstValueRegist001' and ELEMENT_ID='ChbAstValueRegist001_btn_reSubmit' and REMARK='重新报送';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrChbAstValueRegist001','ChbAstValueRegist001_btn_submit','01','01',null,'报送','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrChbAstValueRegist001','ChbAstValueRegist001_btn_reSubmit','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');

--底层资产持仓登记SvrChbBotAstPositionRegist001:添加报送与重新报送
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrChbBotAstPositionRegist001' and ELEMENT_ID='ChbBotAstPositionRegist001_btn_submit' and REMARK='报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrChbBotAstPositionRegist001' and ELEMENT_ID='ChbBotAstPositionRegist001_btn_reSubmit' and REMARK='重新报送';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrChbBotAstPositionRegist001','ChbBotAstPositionRegist001_btn_submit','01','01',null,'报送','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrChbBotAstPositionRegist001','ChbBotAstPositionRegist001_btn_reSubmit','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');

--交易信息登记SvrInvestTradeRegist001:添加报送与重新报送
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrInvestTradeRegist001' and ELEMENT_ID='InvestTradeRegist001_btn_submit' and REMARK='报送';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrInvestTradeRegist001' and ELEMENT_ID='InvestTradeRegist001_btn_reSubmit' and REMARK='重新报送';
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrInvestTradeRegist001','InvestTradeRegist001_btn_submit','01','01',null,'报送','system',null,sysdate,'system',sysdate,'C');
--INSERT 
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)values ('SvrInvestTradeRegist001','InvestTradeRegist001_btn_reSubmit','01','01',null,'重新报送','system',null,sysdate,'system',sysdate,'C');

--从业人员登记:SvrEmployeeInfoReg001
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEmployeeInfoReg001' AND ELEMENT_ID='SvrEmployeeInfoReg001_btn_manual';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEmployeeInfoReg001' AND ELEMENT_ID='SvrEmployeeInfoReg001_btn_cancelManual';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEmployeeInfoReg001' AND ELEMENT_ID='SvrEmployeeInfoReg001_btn_chbexport';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEmployeeInfoReg001' AND ELEMENT_ID='SvrEmployeeInfoReg001_btn_submitChb';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEmployeeInfoReg001' AND ELEMENT_ID='SvrEmployeeInfoReg001_btn_reSubmitChb';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEmployeeInfoReg001' AND ELEMENT_ID='SvrEmployeeInfoReg001_btn_search';
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEmployeeInfoReg001' AND ELEMENT_ID='SvrEmployeeInfoReg001_btn_reset';

