
delete from SYS_MENU where MENU_ID like 'REPORT_003%';
delete from SYS_MENU where MENU_ID like 'SVR_%';
insert into SYS_MENU values ('SVR_003_003','REP',null,'SVR_003',null,3,null,'投资者相关登记','system',null,to_date('2019/2/27', 'yyyy/mm/dd'),'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_003_001','REP',null,'SVR_003_003','EquityInfoRegist001',1,null,'投资者身份信息登记','system',null,to_date('2019/2/27', 'yyyy/mm/dd'),'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_003_002','REP',null,'SVR_003_003','InvestorsHoldInfoRegist001',2,null,'投资者持有信息登记','system',null,to_date('2019/2/27', 'yyyy/mm/dd'),'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003','SVR','icon-reportregister','','','3','','报送信息登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_004','SVR','','SVR_003','','4','','产品端相关登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_004_002','SVR','','SVR_003_004','SvrProdPersonIssue001','1','','个人产品发行登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_004_003','SVR','','SVR_003_004','SvrProdOrgIssue001','2','','机构产品发行登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_004_004','SVR','','SVR_003_004','SvrProdDurationReg001','3','','产品存续期登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_004_005','SVR','','SVR_003_004','SvrRaiseTotalRegist001','4','','产品募集总量登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_004_006','SVR','','SVR_003_004','SvrPerProDeclareReg001','5','','产品申报登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_004_007','SVR','','SVR_003_004','SvrProdTerminationRegist001','6','','产品终止登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_005','SVR','','SVR_003','','5','','资产端相关登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_005_001','SVR','','SVR_003_005','SvrAssetInfoRegist001','1','','资产要素登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_005_002','SVR','','SVR_003_005','SvrChbAstValueRegist001','2','','资产估值信息登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_005_003','SVR','','SVR_003_005','SvrChbBotAstPositionRegist001','3','','底层资产持仓信息登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_005_004','SVR','','SVR_003_005','SvrInvestTradeRegist001','4','','交易信息登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_006','SVR','','SVR_003','','6','','月报相关登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_MENU values ('SVR_003_006_001','SVR','','SVR_003_006','SvrMonthReport001','1','','银行月度报表登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));

delete from SYS_PAGE where menu_id like 'REPORT_003%';
delete from SYS_PAGE where SYSTEM_CODE = 'SVR';
insert into SYS_PAGE values ('InvestorsHoldInfoRegist001','SVR','/svreport/submitmanagement/investorsholdinforegist/InvestorsHoldInfoRegist001','report/submitmanagement/investorsholdinforegist/InvestorsHoldInfoRegist001','SVR_003_003_002',null,null,'02','02','投资者持有信息登记','system',null,to_date('2019/2/27', 'yyyy/mm/dd'),'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('EquityInfoRegist001','SVR','/svreport/submitmanagement/equityinforegist/EquityInfoRegist001','report/submitmanagement/equityinforegist/EquityInfoRegist001','SVR_003_003_001',null,null,'02','02','投资者身份信息登记','system',null,to_date('2019/2/27', 'yyyy/mm/dd'),'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrProdPersonIssue001','SVR','/chb/submitmanagement/prodpersonissue/ProdPersonIssue001','report/chb/prodpersonissue/ProdPersonIssue001','SVR_003_004_002','','','02','02','个人产品发行登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrPerProDeclareReg001','SVR','/chb/perprodeclarereg/PerProDeclareReg001','report/chb/perprodeclarereg/PerProDeclareReg001','SVR_003_004_006','','','02','02','个人产品申报登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrInvestTradeRegist001','SVR','/chb/investtraderegist/InvestTradeRegist001','report/chb/investtraderegist/InvestTradeRegist001','SVR_003_005_004','','','02','02','交易信息登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrRaiseTotalRegist001','SVR','/chb/prodraisetotalregist/RaiseTotalRegist001','report/chb/prodraisetotalregist/RaiseTotalRegist001','SVR_003_004_005','','','02','02','产品募集总量登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrProdDurationReg001','SVR','/chb/proddurationreg/ProdDurationReg001','report/chb/proddurationreg/ProdDurationReg001','SVR_003_004_004','','','02','02','产品存续期登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrProdTerminationRegist001','SVR','/chb/prodterminationregist/ProdTerminationRegist001','report/chb/prodterminationregist/ProdTerminationRegist001','SVR_003_004_007','','','02','02','产品终止登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrChbBotAstPositionRegist001','SVR','/chb/botastpositionregist/ChbBotAstPositionRegist001','report/chb/botastpositionregist/ChbBotAstPositionRegist001','SVR_003_005_003','','','01','01','底层资产持仓信息登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrProdOrgIssue001','SVR','/chb/prodorgissue/ProdOrgIssue001','report/chb/prodorgissue/ProdOrgIssue001','SVR_003_004_003','','','02','02','机构产品发行登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrChbAstValueRegist001','SVR','/chb/astvalueregist/ChbAstValueRegist001','report/chb/astvalueregist/ChbAstValueRegist001','SVR_003_005_002','','','01','01','资产估值信息登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrAssetInfoRegist001','SVR','/chb/assetinforegist/AssetInfoRegist001','report/chb/assetinforegist/AssetInfoRegist001','SVR_003_005_001','','','02','02','资产要素登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrMonthReport001','SVR','/chb/monthreport/ChbMonthReport001','report/chb/monthreport/ChbMonthReport001','SVR_003_006_001','','','01','01','银行月度报表登记','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));

DELETE FROM SYS_PARA WHERE SYSTEM_CODE = 'SVR';
insert into SYS_PARA values ('SVR','SVR_IS_CHL_BTM','02','01','数据字典(是否)','通道投资资产管理产品是否穿透报送(01：是，02：否)','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PARA values ('SVR','SVR_CALENDAR_ID','01','01','','上流系统日历编号（银行间）','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PARA values ('SVR','SVR_EXCHANGE_SOURCE','02','02','01,02,03,04,05','汇率取值来源(01:开盘价，02:收盘价，03:最高价，04:最低价，05:中间价)','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PARA values ('SVR','SVR_NET_UNIT_VALUE_SOURCE','01','02','01,02,03','单位净值取值来源(01:估值核算系统，02:信息批露系统，03:第三方)','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PARA values ('SVR','SVR_ISSUE_PARTY_NAME','发行机构代码','01','','发行机构代码','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PARA values ('SVR','SVR_ISSUE_PARTY_ID','发行机构名称','01','','发行机构名称','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PARA values ('SVR','SVR_IS_AUTOGENE_CHLDEAL','01','01','数据字典(是否）','上游资产交易数据接入是否自动生成产品买通道交易(01：自动生成 02：不自动生成)','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PARA values ('SVR','SVR_INSERT_COUNT','50','01','单次批量插入最大数量','单次批量插入最大数量(正整数)：默认50。','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));


DELETE FROM SYS_BATCH_TASK WHERE SYSTEM_CODE = 'SVR';
insert into SYS_BATCH_TASK values ('3022','SVR','中债基础数据导入','','chbBaseInfoService','3010','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));    
insert into SYS_BATCH_TASK values ('3008','SVR','产品发行申报登记报送','','prodIssueAndDeclareService','3010','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));  
insert into SYS_BATCH_TASK values ('3009','SVR','资产要素基础表数据导入','','assetInfoImpService','3010','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));    
insert into SYS_BATCH_TASK values ('3010','SVR','投资交易表数据导入','','investTradeImpService','3015','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));    
insert into SYS_BATCH_TASK values ('3011','SVR','产品存续期数据导入','','prodDurationImpService','3015','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));   
insert into SYS_BATCH_TASK values ('3012','SVR','产品头寸批处理','','prodPositionService','3020','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));    
insert into SYS_BATCH_TASK values ('3013','SVR','资产头寸批处理','','chbAssetpositionCBService','3030','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));  
insert into SYS_BATCH_TASK values ('3014','SVR','产品存续期交易登记报送','','prodDurationRegService','3030','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd')); 
insert into SYS_BATCH_TASK values ('3015','SVR','资产要素登记报送','','assetInfoCBService','3030','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));    
insert into SYS_BATCH_TASK values ('3016','SVR','投资交易登记报送','','investTradeCBService','3030','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));  
insert into SYS_BATCH_TASK values ('3017','SVR','产品募集总量登记报送','','prodRaiseTotalService','3030','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));   
insert into SYS_BATCH_TASK values ('3018','SVR','产品终止登记报送','','prodTerminationService','3030','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));    
insert into SYS_BATCH_TASK values ('3019','SVR','底层持仓信息登记报送','','chbBotAstPosCBService','3030','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));   
insert into SYS_BATCH_TASK values ('3030','SVR','资产估值信息登记报送','','chbAstValCBService','3030','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));  
insert into SYS_BATCH_TASK values ('3021','SVR','银行月度统计表报送','','monthreportCBService','3040','system',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd')); 


/*发行登记改造脚本 add by weiyuqiang 2019-03-23 start*/
DELETE FROM SYS_PAGE WHERE menu_id = 'SVR_003_004_003';
DELETE FROM SYS_MENU WHERE MENU_ID = 'SVR_003_004_003';
UPDATE SYS_PAGE 
set remark = '产品发行登记'
WHERE MENU_ID = 'SVR_003_004_002';
UPDATE SYS_Menu
set remark = '产品发行登记'
WHERE MENU_ID = 'SVR_003_004_002';

/*发行登记改造脚本 add by weiyuqiang 2019-03-23 end*/

/*中债启用版改造菜单名称调整 add by weiyuqiang 2019-03-28 start*/
UPDATE SYS_PAGE 
SET REMARK = '底层资产持仓登记'
WHERE MENU_ID = 'SVR_003_005_003';
UPDATE SYS_Menu
SET REMARK = '底层资产持仓登记'
WHERE MENU_ID = 'SVR_003_005_003';
/*中债启用版改造菜单名称调整 add by weiyuqiang 2019-03-28 end*/



/**放到最后，不要删除***/
commit;