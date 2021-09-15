--投资费用支付:InvestFeePay001
--更新sys_page表中InvestFeePay001信息
update sys_page set menu_id = 'PTL_MANAGEMENT_001_003_003' where page_id='InvestFeePay001'; 
--重置按钮，更，InvestFeePay001_btn_reset
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01' WHERE PAGE_ID = 'InvestFeePay001' AND ELEMENT_ID='InvestFeePay001_btn_reset';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='InvestFeePay001' and ELEMENT_ID='InvestFeePay001_btn_confirm' and REMARK='确认';
--确认按钮，增，InvestFeePay001_btn_confirm
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('InvestFeePay001','InvestFeePay001_btn_confirm','01','01',null,'确认','system',null,sysdate,'system',sysdate,'C');


--资产转仓：AssetsTransfer001
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetsTransfer001' and ELEMENT_ID='AssetsTransfer001_btn_portofioSearch' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetsTransfer001' and ELEMENT_ID='AssetsTransfer001_btn_search' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetsTransfer001' and ELEMENT_ID='AssetsTransfer001_btn_transferSearch' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetsTransfer001' and ELEMENT_ID='AssetsTransfer001_btn_revoke' and REMARK='撤销转仓';
--查询按钮，增，AssetsTransfer001_btn_portofioSearch
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetsTransfer001','AssetsTransfer001_btn_portofioSearch','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--查询按钮，增，AssetsTransfer001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetsTransfer001','AssetsTransfer001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--查询按钮，增，AssetsTransfer001_btn_transferSearch
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetsTransfer001','AssetsTransfer001_btn_transferSearch','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--撤销转仓按钮，增，AssetsTransfer001_btn_revoke
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetsTransfer001','AssetsTransfer001_btn_revoke','01','01',null,'撤销转仓','system',null,sysdate,'system',sysdate,'C');


--存续期交易查询：TrdQueryDuration001
--DELECT
delete from SYS_PAGE_ELEMENT where PAGE_ID='TrdQueryDuration001' and ELEMENT_ID='TrdQueryDuration001_btn_export' and REMARK='导出';
delete from SYS_PAGE_ELEMENT where PAGE_ID='TrdQueryDuration001' and ELEMENT_ID='TrdQueryDuration001_btn_search' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='TrdQueryDuration001' and ELEMENT_ID='TrdQueryDuration001_btn_reset' and REMARK='重置';
--导出按钮，增，TrdQueryDuration001_btn_export
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('TrdQueryDuration001','TrdQueryDuration001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate,'COM');
--查询按钮，增，TrdQueryDuration001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('TrdQueryDuration001','TrdQueryDuration001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--重置按钮，增，TrdQueryDuration001_btn_reset
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('TrdQueryDuration001','TrdQueryDuration001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');


-- 增值税台账：ValueAddedTaxQuery001
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='ValueAddedTaxQuery001' and ELEMENT_ID='ValueAddedTaxQuery001_btn_create' and REMARK='生成';
delete from SYS_PAGE_ELEMENT where PAGE_ID='ValueAddedTaxQuery001' and ELEMENT_ID='ValueAddedTaxQuery001_btn_export' and REMARK='导出';
delete from SYS_PAGE_ELEMENT where PAGE_ID='ValueAddedTaxQuery001' and ELEMENT_ID='ValueAddedTaxQuery001_btn_search' and REMARK='查询';
--生成按钮，增，ValueAddedTaxQuery001_btn_create
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('ValueAddedTaxQuery001','ValueAddedTaxQuery001_btn_create','01','01',null,'生成','system',null,sysdate,'system',sysdate,'C');
--导出按钮，增，ValueAddedTaxQuery001_btn_export
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('ValueAddedTaxQuery001','ValueAddedTaxQuery001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate,'COM');
--查询按钮，增，ValueAddedTaxQuery001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('ValueAddedTaxQuery001','ValueAddedTaxQuery001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');


--资产基本信息维护：AssetInfoDetail001
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetInfoDetail001' and ELEMENT_ID='AssetInfoDetail001_btn_add' and REMARK='新建';
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetInfoDetail001' and ELEMENT_ID='AssetInfoDetail001_btn_export' and REMARK='导出';
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetInfoDetail001' and ELEMENT_ID='AssetInfoDetail001_btn_search' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetInfoDetail001' and ELEMENT_ID='AssetInfoDetail001_btn_reset' and REMARK='重置';
--新建按钮，增，AssetInfoDetail001_btn_add
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetInfoDetail001','AssetInfoDetail001_btn_add','01','01',null,'新建','system',null,sysdate,'system',sysdate,'C');
--导出按钮，增，AssetInfoDetail001_btn_export
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetInfoDetail001','AssetInfoDetail001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate,'COM');
--查询按钮，增，AssetInfoDetail001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetInfoDetail001','AssetInfoDetail001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--重置按钮，增，AssetInfoDetail001_btn_reset
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetInfoDetail001','AssetInfoDetail001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');


--资产/负债类别配置：AssetTypeSetting001
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetTypeSetting001' and ELEMENT_ID='AssetTypeSetting001_btn_create' and REMARK='新建';
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetTypeSetting001' and ELEMENT_ID='AssetTypeSetting001_btn_search' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='AssetTypeSetting001' and ELEMENT_ID='AssetTypeSetting001_btn_reset' and REMARK='重置';
--新建按钮，增，AssetTypeSetting001_btn_create
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetTypeSetting001','AssetTypeSetting001_btn_create','01','01',null,'新建','system',null,sysdate,'system',sysdate,'C');
--查询按钮，增，AssetTypeSetting001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetTypeSetting001','AssetTypeSetting001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--重置按钮，增，AssetTypeSetting001_btn_reset
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('AssetTypeSetting001','AssetTypeSetting001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');


--识别标识合并：SvrInvestorMerger001
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrInvestorMerger001' and ELEMENT_ID='SvrInvestorMerger001_btn_export' and REMARK='导出';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrInvestorMerger001' and ELEMENT_ID='SvrInvestorMerger001_btn_search' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrInvestorMerger001' and ELEMENT_ID='SvrInvestorMerger001_btn_reset' and REMARK='重置';
--导出按钮，增，SvrInvestorMerger001_btn_export
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrInvestorMerger001','SvrInvestorMerger001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate,'COM');
--查询按钮，增，SvrInvestorMerger001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrInvestorMerger001','SvrInvestorMerger001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--重置按钮，增，SvrInvestorMerger001_btn_reset
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrInvestorMerger001','SvrInvestorMerger001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');


--中债excel数据导入：SvrSubOperationalInfo001
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrSubOperationalInfo001' and ELEMENT_ID='SvrSubOperationalInfo001_btn_delete' and REMARK='删除';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrSubOperationalInfo001' and ELEMENT_ID='SvrSubOperationalInfo001_btn_import' and REMARK='导入';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrSubOperationalInfo001' and ELEMENT_ID='SvrSubOperationalInfo001_btn_search' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrSubOperationalInfo001' and ELEMENT_ID='SvrSubOperationalInfo001_btn_reset' and REMARK='重置';
--删除按钮，增，SvrSubOperationalInfo001_btn_delete
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrSubOperationalInfo001','SvrSubOperationalInfo001_btn_delete','01','01',null,'删除','system',null,sysdate,'system',sysdate,'C');
--导入按钮，增，SvrSubOperationalInfo001_btn_import
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrSubOperationalInfo001','SvrSubOperationalInfo001_btn_import','01','01',null,'导入','system',null,sysdate,'system',sysdate,'C');
--查询按钮，增，SvrSubOperationalInfo001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrSubOperationalInfo001','SvrSubOperationalInfo001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--重置按钮，增，SvrSubOperationalInfo001_btn_reset
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrSubOperationalInfo001','SvrSubOperationalInfo001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');


--报送前检查：CheckReport001
--查询按钮，更，CheckReport001_btn_search
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'CheckReport001' AND ELEMENT_ID='CheckReport001_btn_search';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='CheckReport001' and ELEMENT_ID='CheckReport001_btn_export' and REMARK='导出';
delete from SYS_PAGE_ELEMENT where PAGE_ID='CheckReport001' and ELEMENT_ID='CheckReport001_btn_reset' and REMARK='重置';
--导出按钮，增，CheckReport001_btn_export
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('CheckReport001','CheckReport001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate,'COM');
--重置按钮，增，CheckReport001_btn_reset
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('CheckReport001','CheckReport001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');


--延时报送检查：DelayVerification001
--查询按钮，更，DelayVerification001_btn_search
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'DelayVerification001' AND ELEMENT_ID='DelayVerification001_btn_search';


--数据接入检查：ErrorMessage001
--更新sys_page表中ErrorMessage001信息
update sys_page set menu_id = 'REPORT_001_002_003' where page_id='ErrorMessage001'; 
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='ErrorMessage001' and ELEMENT_ID='ErrorMessage001_btn_export' and REMARK='导出';
delete from SYS_PAGE_ELEMENT where PAGE_ID='ErrorMessage001' and ELEMENT_ID='ErrorMessage001_btn_search' and REMARK='查询';
--导出按钮，增，ErrorMessage001_btn_export
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('ErrorMessage001','ErrorMessage001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate,'COM');
--查询按钮，增，ErrorMessage001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('ErrorMessage001','ErrorMessage001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');


--投资者份额检查：SvrEquityShareCheck001
--导出按钮，更，SvrEquityShareCheck001_btn_export
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEquityShareCheck001' AND ELEMENT_ID='SvrEquityShareCheck001_btn_export';
--查询按钮，更，SvrEquityShareCheck001_btn_search
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'SvrEquityShareCheck001' AND ELEMENT_ID='SvrEquityShareCheck001_btn_search';
--重置按钮，更，SvrEquityShareCheck001_btn_reset
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C',IS_CONTROL='01',ELEMENT_ID='SvrEquityShareCheck001_btn_reset'  WHERE PAGE_ID = 'SvrEquityShareCheck001' AND REMARK='重置';


--产品份额检查：SvrProShareCheck001
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrProShareCheck001' and ELEMENT_ID='SvrProShareCheck001_btn_export' and REMARK='导出';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrProShareCheck001' and ELEMENT_ID='SvrProShareCheck001_btn_search' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrProShareCheck001' and ELEMENT_ID='SvrProShareCheck001_btn_reset' and REMARK='重置';
--导出按钮，增，SvrProShareCheck001_btn_export
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrProShareCheck001','SvrProShareCheck001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate,'COM');
--查询按钮，增，SvrProShareCheck001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrProShareCheck001','SvrProShareCheck001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--重置按钮，增，SvrProShareCheck001_btn_reset
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrProShareCheck001','SvrProShareCheck001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');


--投资者质量查核：SvrInvestorsQualityCheck001
--更新sys_page表中SvrInvestorsQualityCheck001信息
update sys_page set menu_id = 'REPORT_001_002_007' where page_id='SvrInvestorsQualityCheck001'; 
--查询按钮，更，InvestorsQualityCheck001_btn_search
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01',ELEMENT_ID='InvestorsQualityCheck001_btn_search' WHERE PAGE_ID = 'SvrInvestorsQualityCheck001' AND REMARK='查询';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='SvrInvestorsQualityCheck001' and ELEMENT_ID='SvrInvestorsQualityCheck001_btn_summary' and REMARK='持有信息汇总';
--持有信息汇总按钮，增，SvrInvestorsQualityCheck001_btn_summary
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('SvrInvestorsQualityCheck001','SvrInvestorsQualityCheck001_btn_summary','01','01',null,'持有信息汇总','system',null,sysdate,'system',sysdate,'C');


--理财产品转型情况月度统计表：SvrTransMonthReport001
--更新sys_page表中SvrTransMonthReport001信息
update sys_page set menu_id = 'REPORT_001_003_099' where page_id='SvrTransMonthReport001'; 
--关闭SvrTransMonthReport001中查询按钮的配置
UPDATE SYS_PAGE_ELEMENT SET IS_CONTROL='02' WHERE PAGE_ID = 'SvrTransMonthReport001' AND ELEMENT_ID='ChbTransMonthReport001_btn_search';


-- 表内外投资业务情况统计表：InvestmentsInfo001
--更新sys_page表中InvestmentsInfo001信息
update sys_page set menu_id = 'REPORT_001_005_002' where page_id='InvestmentsInfo001'; 
--关闭InvestmentsInfo001中查询按钮的配置
UPDATE SYS_PAGE_ELEMENT SET IS_CONTROL='02' WHERE PAGE_ID = 'InvestmentsInfo001' AND ELEMENT_ID='InvestmentsInfo001_btn_search';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='InvestmentsInfo001' and ELEMENT_ID='InvestmentsInfo001_btn_export' and REMARK='导出';
--导出按钮，增，InvestmentsInfo001_btn_export
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('InvestmentsInfo001','InvestmentsInfo001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate,'COM');


--报送情况统计及反馈:DebtSubmitInfo001
--查询按钮，更，DebtSubmitInfo001_btn_search
UPDATE SYS_PAGE_ELEMENT SET IS_CONTROL='01' WHERE PAGE_ID = 'DebtSubmitInfo001' AND ELEMENT_ID='DebtSubmitInfo001_btn_search';

-- 发送状态信息查询：ReportStatusInfo001
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='ReportStatusInfo001' and ELEMENT_ID='ReportStatusInfo001_btn_search' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='ReportStatusInfo001' and ELEMENT_ID='ReportStatusInfo001_btn_reset' and REMARK='重置';
--查询按钮，增，ReportStatusInfo001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('ReportStatusInfo001','ReportStatusInfo001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--重置按钮，增，ReportStatusInfo001_btn_reset
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('ReportStatusInfo001','ReportStatusInfo001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');


--中债反馈文件管理：ChbFeedbackFileManagement001
--送信息匹配，更，ChbFeedbackFileManagement001_btn_match
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C' WHERE PAGE_ID = 'ChbFeedbackFileManagement001' AND ELEMENT_ID='ChbFeedbackFileManagement001_btn_match';
--文件移除备份，更，ChbFeedbackFileManagement001_btn_backup
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'C' WHERE PAGE_ID = 'ChbFeedbackFileManagement001' AND ELEMENT_ID='ChbFeedbackFileManagement001_btn_backup';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='ChbFeedbackFileManagement001' and ELEMENT_ID='ChbFeedbackFileManagement001_btn_search' and REMARK='查询';
delete from SYS_PAGE_ELEMENT where PAGE_ID='ChbFeedbackFileManagement001' and ELEMENT_ID='ChbFeedbackFileManagement001_btn_reset' and REMARK='重置';
--查询按钮，增，ChbFeedbackFileManagement001_btn_search
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('ChbFeedbackFileManagement001','ChbFeedbackFileManagement001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate,'COM');
--重置按钮，增，ChbFeedbackFileManagement001_btn_reset
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('ChbFeedbackFileManagement001','ChbFeedbackFileManagement001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');

-- 中债三期大数据量报送文件下载:ChbSubinfoBatchLog001
--导出按钮，更，ChbSubinfoBatchLog001_btn_export
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'ChbSubinfoBatchLog001' AND ELEMENT_ID='ChbSubinfoBatchLog001_btn_export';
--查询按钮，更，ChbSubinfoBatchLog001_btn_search
UPDATE SYS_PAGE_ELEMENT SET ADMIN_FLAG = 'COM',IS_CONTROL='01' WHERE PAGE_ID = 'ChbSubinfoBatchLog001' AND ELEMENT_ID='ChbSubinfoBatchLog001_btn_search';
--DELETE
delete from SYS_PAGE_ELEMENT where PAGE_ID='ChbSubinfoBatchLog001' and ELEMENT_ID='ChbSubinfoBatchLog001_btn_reset' and REMARK='重置';
--重置按钮，增，ChbSubinfoBatchLog001_btn_reset
insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADMIN_FLAG)
values ('ChbSubinfoBatchLog001','ChbSubinfoBatchLog001_btn_reset','01','01',null,'重置','system',null,sysdate,'system',sysdate,'C');

-- 自动报送批处理结果查询:AutBatchProResultQuery001
--查询按钮，更，AutBatchProResultQuery001_btn_search
UPDATE SYS_PAGE_ELEMENT SET IS_CONTROL='01' WHERE PAGE_ID = 'AutBatchProResultQuery001' AND ELEMENT_ID='AutBatchProResultQuery001_btn_search';






