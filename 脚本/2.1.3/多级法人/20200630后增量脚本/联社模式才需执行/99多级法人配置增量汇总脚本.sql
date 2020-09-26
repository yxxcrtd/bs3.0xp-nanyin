--日期 ： 2020/07/03 start
--曹锦
update DATA_AUTH_MAPPER_CONFIG set JOIN_COLUMN_NAME = 'seqNo' , DATA_TYPE = 'MST09' where SQL_ID = 'com.joyintech.service.settle.balance.dao.InflowFundsCheckManagementMapper.findPortfolioUnmatched';
--王杨
delete from data_auth_mapper_config where SQL_ID = 'com.joyintech.service.portfolio.ptlmanagementinfo.dao.PtlManagementInfo001Mapper.getMatchPortData';
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dao.FlowRiskStressTestMapper.getAllPortfolioByProfit',
'com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dao.FlowRiskStressTestMapper.getAllPortfolioByOther',
'com.joyintech.service.portfolio.ptlmanagementinfo.dao.PtlManagementInfo001Mapper.selectPtlRuleAll',
'com.joyintech.tams.invest.common.prodinfo.dao.RskRuleCommonMapper.selectRuleRelInd',
'com.joyintech.tams.invest.common.prodinfo.dao.RskRuleCommonMapper.getRulePoolConfig');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dao.FlowRiskStressTestMapper.getAllPortfolioByProfit','2','02','id','','RSK','FlowRiskStressTest001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dao.FlowRiskStressTestMapper.getAllPortfolioByOther','2','02','id','','RSK','FlowRiskStressTest001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.portfolio.ptlmanagementinfo.dao.PtlManagementInfo001Mapper.selectPtlRuleAll','2','02','ruleId','','RSK','RskSupervisionPortfPoint001','RSK25', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.invest.common.prodinfo.dao.RskRuleCommonMapper.selectRuleRelInd','2','02','IND_ID','','RSK','RskSupervisionPortfPoint001','RSK08', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.invest.common.prodinfo.dao.RskRuleCommonMapper.getRulePoolConfig','2','02','RULE_ID','','RSK','RskSupervisionPortfPoint001','RSK25', 'admin', null, sysdate, 'wy', sysdate);
 
--郝祥云
update DATA_AUTH_MAPPER_CONFIG set filter_sql = 'select * from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02''and  di.data_type=''FINPROD04'') di on di.finprod_market_id = b.prod_id where di.institution_code in %s'
where SQL_ID = 'com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.getProdinfo';
update DATA_AUTH_MAPPER_CONFIG set filter_sql = 'select * from ( %s ) b LEFT JOIN (select c.REG_CODE finprod_market_id,di.institution_code from CHB_PRDRESIST_CODE c left join fin_product f on c.prodid=f.finprod_market_id left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and  di.data_type=''FINPROD04'') di on di.finprod_market_id = b.id where di.institution_code in %s'
where SQL_ID = 'com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryPrdRegList';
update DATA_AUTH_MAPPER_CONFIG set filter_sql = 'select * from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02''  and  di.data_type=''FINPROD04'') di on di.finprod_market_id = b.code where di.institution_code in %s'
where SQL_ID = 'com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper.queryPrdNameList';
update DATA_AUTH_MAPPER_CONFIG set filter_sql = 'select * from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type =  ''02'' and  di.data_type=''FINPROD04'') di on di.finprod_market_id = b.code where di.institution_code in %s'
where SQL_ID = 'com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper.queryPrdNameListOld';
delete from  data_auth_mapper_config where sql_id='com.joyintech.service.portfolio.ptlpositionanalysis.dao.PtlPositionAnalysisMapper.secReportPage';

--井琼琼
DELETE FROM DATA_AUTH_MAPPER_CONFIG WHERE DATA_AUTH_MAPPER_CONFIG.SQL_ID='com.joyintech.service.portfolio.ptlpositionanalysis.dao.PtlPositionAnalysisMapper.select';
DELETE FROM DATA_AUTH_MAPPER_CONFIG WHERE DATA_AUTH_MAPPER_CONFIG.SQL_ID='com.joyintech.service.portfolio.ptlpositionanalysis.dao.PtlPositionAnalysisMapper.selectForExport';

--宋亚伟 新增二级法人脚本
delete from data_auth_mapper_config  where SQL_ID = 'com.joyintech.tams.invest.common.risk.dto.RiskNamListCommonDto.RiskNamListCommonMapper.selectAssetCoun';
delete from data_auth_mapper_config  where SQL_ID = 'com.joyintech.tams.systemtools.dao.ServiceInstitutionMapper.getInstitutionName';
delete from data_auth_mapper_config  where SQL_ID = 'com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper.searchTable';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('com.joyintech.tams.invest.common.risk.dto.RiskNamListCommonDto.RiskNamListCommonMapper.selectAssetCoun','1','01','','SELECT * FROM ( %s ) su WHERE su.insitituteCode IN %s','TRD','TrdBondInvestTrade001','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('com.joyintech.tams.systemtools.dao.ServiceInstitutionMapper.getInstitutionName','1','01','','SELECT * FROM ( %s ) su WHERE su.code IN %s','REP','CheckReport001','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper.searchTable','1','01','','SELECT * FROM ( %s ) su WHERE su.orgCode IN %s','REP','CheckReport001','', 'admin', null, sysdate, 'admin', sysdate);

--日期 ： 2020/07/03 end



---- add by wujian 风险管理-压力测试  START  20200703
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dao.MarketRiskSceneManage001Mapper.queryForPage',
'com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dao.FlowRiskStressTestMapper.querySceneOption',
'com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dao.MarketRiskStressTestView001Mapper.queryForPage',
'com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dao.MarketRiskStressTestView001Mapper.queryTestList');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dao.MarketRiskSceneManage001Mapper.queryForPage','1','02','belongInstitutionCode','','TG','MarketRiskSceneManage001','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dao.FlowRiskStressTestMapper.querySceneOption','1','02','institutionCode','','TG','FlowRiskStressTest001','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dao.MarketRiskStressTestView001Mapper.queryForPage','1','02','belongInstitutionCode','','TG','MarketRiskStressTestView001','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dao.MarketRiskStressTestView001Mapper.queryTestList','1','02','institutionCode','','TG','MarketRiskStressTestView001','', 'admin', null, sysdate, 'admin', sysdate);

---- add by wujian 风险管理-压力测试  END  20200703

-- add by caojin 产品端轧差管理--划款信息查询：查询  start  20200706
update DATA_AUTH_MAPPER_CONFIG set JOIN_COLUMN_NAME = 'portid' where SQL_ID = 'com.joyintech.service.settle.netting.dao.NettingManagementMapper.transferMessage';
-- add by caojin 产品端轧差管理--划款信息查询：查询  END  20200706

--wangyang1 二级法人压力测试 start 20200706
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dao.FlowRiskSceneManageMapper.queryForPage',
'com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dao.FlowRiskStressTestView001Mapper.queryForPage',
'com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dao.FlowRiskStressTestView001Mapper.queryTestList');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dao.FlowRiskSceneManageMapper.queryForPage','1','02','belongInstitutionCode','','RSK','FlowRiskSceneManage001','', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dao.FlowRiskStressTestView001Mapper.queryForPage','1','02','belongInstitutionCode','','RSK','FlowRiskStressTestView001','', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dao.FlowRiskStressTestView001Mapper.queryTestList','','','','','RSK','FlowRiskStressTestView001','', 'admin', null, sysdate, 'wy', sysdate);
--wangyang1 二级法人压力测试 end

--wangyang1 二级法人 start 20200707
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.asset.plan.planProfit.dao.FinPlanProfitAssetMapper.getBasicInfoByFinprodId');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.asset.plan.planProfit.dao.FinPlanProfitAssetMapper.getBasicInfoByFinprodId','2','02','finprodId','','MST','FinPlanProfitAsset002','FINASSET05', 'admin', null, sysdate, 'wy', sysdate);
--wangyang1 二级法人 start 20200707


-----  wujian 项目类 计划类货币型  放大镜进行过滤 20200708  START
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.asset.plan.plancur.dao.FinPlanAssetCMapper.getBasicInfoByFinprodId','com.joyintech.service.asset.project.dao.FinProjectTargetAssetMapper.getBasicInfo');
insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.asset.plan.plancur.dao.FinPlanAssetCMapper.getBasicInfoByFinprodId', '2', '02', 'finprodId', null, 'FIN', 'FinPlanAssetC002', 'admin', null, sysdate, 'admin', sysdate, null, null, 'FINASSET11');

insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.asset.project.dao.FinProjectTargetAssetMapper.getBasicInfo', '2', '02', 'FINPROD_ID', null, 'FIN', 'FinProjectTargetAsset002', 'admin', null, sysdate, 'admin', sysdate, null, null, 'FINASSET06');
-----  wujian 项目类 计划类货币型  放大镜进行过滤 20200708  END



-- songyawei  债券单组合单资产新建  20200709 start
delete from data_auth_mapper_config where SQL_ID = 'com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper.getCounterId';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper.getCounterId','2','02','id','','MST','EtrInvestBondInfo002','MST15', 'admin', null, sysdate, 'admin', sysdate);	
--songyawei  债券单组合单资产新建  20200709 end

-----  wujian 流程监控 增加机构名称 20200709  START
UPDATE data_auth_mapper_config SET add_column = '01' , add_column_name ='base99.instCode belongInstitutionCode'
WHERE sql_id = 'com.joyintech.system.workflow.dao.WorkflowMonitor001Mapper.queryList';
----------  wujian 流程监控 增加机构名称 20200709  end

-----  wangyang1 产品端增加配置 20200709  START
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.product.productelement.periodicmanagement.dao.PeriodicManagementMapper.selectForPage',
'com.joyintech.service.asset.plan.planNet.dao.FinPlanNetAssetMapper.getBasicInfoByFinprodId',
'com.joyintech.service.product.common.dao.ProductCommonMapper.queryRepFinprodInsId');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.product.productelement.periodicmanagement.dao.PeriodicManagementMapper.selectForPage','2','02','periodProdId','','FIN','PeriodicManagement001','FINPROD04,FINPROD13', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.asset.plan.planNet.dao.FinPlanNetAssetMapper.getBasicInfoByFinprodId','2','02','finprodId','','MST','FinPlanNetAsset002','FINASSET04', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.product.common.dao.ProductCommonMapper.queryRepFinprodInsId','2','02','finprodId','','FIN','FinProductBuild001','FINASSET04', 'admin', null, sysdate, 'wy', sysdate);
-----  wangyang1 产品端增加配置 20200709  END


--leipeng  中债问题处理 20200710 start
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcAssetBaseInfo';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcPrdBaseInfo';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper.queryAllPbcPrdBaseInfo';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.chb.botastpositionregist.dao.ChbBotAstPositionRegist001Mapper.searchPage';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryForPage';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryForPageOld';

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcAssetBaseInfo','1','01','','select * from ( %s )  b LEFT JOIN ( select S.PBC_ACCOUNT_ID,di.institution_code  from PBC_PRD_ACCOUNT_SUB s LEFT JOIN fin_product f ON S.ACCOUNT_ID=F.FINPROD_MARKET_ID left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di  on di.PBC_ACCOUNT_ID = b.PBC_ACCOUNT_ID where di.institution_code in %s','SVR','PbcPrdAccountSub','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcPrdBaseInfo','1','01','','select * from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di on di.finprod_market_id = b.PROD_ID  where di.institution_code in %s','SVR','PbcPrdAccountSub','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper.queryAllPbcPrdBaseInfo','1','01','','select * from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di on di.finprod_market_id = b.PROD_ID  where di.institution_code in %s','SVR','PbcPrdAccountSub','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.chb.botastpositionregist.dao.ChbBotAstPositionRegist001Mapper.searchPage','1','01','','select * from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code  from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di on di.finprod_market_id = b.planAssetId where di.institution_code in %s','SVR','SvrChbBotAstPositionRegist001','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryForPage','1','01','','select *  from (%s) b  LEFT JOIN (select f.rep_finprod_id FINPROD_MARKET_ID,  di.institution_code,  di.belong_institution_code belongInstitutionCode, di.model_type from REP_PROD_DECLARE f left join DATA_AUTH_NOTIFY_INST di on f.rep_finprod_id = di.business_id and di.model_type = ''02'' and DI.DATA_TYPE = ''FINPROD28'') di on di.finprod_market_id = ''F16_'' ||b.prodId  where di.institution_code in %s','SVR','ProRegistCodeManage001','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryForPageOld','1','01','','select *  from (%s) b LEFT JOIN (select f.rep_finprod_id FINPROD_MARKET_ID, di.institution_code, di.belong_institution_code belongInstitutionCode,  di.model_type from REP_PROD_DECLARE f left join DATA_AUTH_NOTIFY_INST di on f.rep_finprod_id = di.business_id and di.model_type = ''02'' and di.data_type = ''FINPROD28'') di on di.finprod_market_id = ''F16_'' ||b.prodId where di.institution_code in %s','SVR','ProRegistCodeManage001','', 'admin', null, sysdate, 'admin', sysdate);
--leipeng  中债问题处理 20200710 END

/*20200710_计划类净值利率货币资产快录增加机构权限隔离_朱如庆 start*/
delete from DATA_AUTH_MAPPER_CONFIG where SQL_ID in(
'com.joyintech.service.asset.plan.planNet.dao.FinPlanNetAssetMapper.getFinprodId');
insert into DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.asset.plan.planNet.dao.FinPlanNetAssetMapper.getFinprodId', '2', '02', 'FINPROD_ID', null, 'MST', 'FinPlanNetAsset002', 'zrq', null, sysdate, 'zrq', sysdate, null, null, 'FINASSET04');

delete from DATA_AUTH_MAPPER_CONFIG where SQL_ID in(
'com.joyintech.service.asset.plan.planProfit.dao.FinPlanProfitAssetMapper.getFinprodId');
insert into DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.asset.plan.planProfit.dao.FinPlanProfitAssetMapper.getFinprodId', '2', '02', 'FINPROD_ID', null, 'MST', 'FinPlanProfitAsset002', 'zrq', null, sysdate, 'zrq', sysdate, null, null, 'FINASSET05');

delete from DATA_AUTH_MAPPER_CONFIG where SQL_ID in(
'com.joyintech.service.asset.plan.plancur.dao.FinPlanAssetCMapper.getFinprodId');
insert into DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.asset.plan.plancur.dao.FinPlanAssetCMapper.getFinprodId', '2', '02', 'FINPROD_ID', null, 'MST', 'FinPlanAssetC002', 'zrq', null, sysdate, 'zrq', sysdate, null, null, 'FINASSET11');
/*20200710_计划类净值利率货币资产快录增加机构权限隔离_朱如庆 end*/


/*20200710_修正预期收益型产品行内识别码值_周秀萍 start*/
delete from DATA_AUTH_MAPPER_CONFIG where SQL_ID in(
'com.joyintech.service.product.common.dao.ProductCommonMapper.queryRepFinprodInsId');
Insert into DATA_AUTH_MAPPER_CONFIG (SQL_ID,FILTER_TYPE,IS_NEST,JOIN_COLUMN_NAME,FILTER_SQL,SYSTEM_CODE,PAGE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADD_COLUMN,ADD_COLUMN_NAME,DATA_TYPE) values ('com.joyintech.service.product.common.dao.ProductCommonMapper.queryRepFinprodInsId','2','02','finprodId',null,'FIN','FinProductBuild001','admin',null,to_timestamp('10-7月 -20 12.38.51.000000000 下午','DD-MON-RR HH.MI.SSXFF AM'),'wy',to_timestamp('10-7月 -20 12.38.51.000000000 下午','DD-MON-RR HH.MI.SSXFF AM'),null,null,'FINPROD28');

/*20200710_修正预期收益型产品行内识别码值_周秀萍 end*/


-----  wujian 批处理监控 增加机构 20200710  START
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.system.batch.batchmonitor.dao.SysBatchMonitor001Mapper.findBatchExeDetail');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.system.batch.batchmonitor.dao.SysBatchMonitor001Mapper.findBatchExeDetail','1','01','','SELECT base99.*,si.Institution_Code belongInstitutionCode FROM ( %s ) base99 
INNER join sys_user su
ON base99.CREATEUSER = su.user_name OR base99.CREATEUSER IS NULL OR base99.CREATEUSER = ''System''
 INNER JOIN Sys_Department sd ON sd.dept_code = su.dept_code
  INNER JOIN Sys_Institution SI ON Si.Institution_Code = sd.institution_code
 WHERE SI.Institution_Code IN %s ','SYS','SysBatchMonitor001','', 'admin', null, sysdate, 'admin', sysdate);
-----  wujian 批处理监控 过滤 20200710  end
 

-----  xr 自定义现金流资产代码二级法人权限控制 start
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.portfolio.ptlcashflow.dao.PtlCashFlowMapper.getFinprodIdToNew','com.joyintech.service.portfolio.ptlcashflow.dao.PtlCashFlowMapper.getFinprodMarketId');
INSERT INTO DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE) VALUES ('com.joyintech.service.portfolio.ptlcashflow.dao.PtlCashFlowMapper.getFinprodIdToNew', '2', '02', 'object', null, 'PTL', 'PtlCashFlow002', 'admin', null, TIMESTAMP '2020-07-03 17:48:52', 'admin', TIMESTAMP '2020-07-03 17:48:52', null, null, null);
INSERT INTO DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE) VALUES ('com.joyintech.service.portfolio.ptlcashflow.dao.PtlCashFlowMapper.getFinprodMarketId', '2', '02', 'hidCode', null, 'PTL', 'PtlCashFlow001', 'admin', null, TIMESTAMP '2020-07-03 17:48:52', 'admin', TIMESTAMP '2020-07-03 17:48:52', null, null, null);
-----  xr 自定义现金流资产代码二级法人权限控制 start

-----  sjj 证券账户二级法人权限控制 start
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.common.select.dao.CommonSelectMapper.getSecManageAcctId');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.common.select.dao.CommonSelectMapper.getSecManageAcctId','2','02','id','','TRD','TrdSpotexInvestTrade001','MST10', 'admin', null, sysdate, 'admin', sysdate);
-----  sjj 证券账户二级法人权限控制 end 

-----  zcw 资产系列管理二级法人权限控制  20200711 start
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dao.CeAssetGroupManagementMapper.getAssetName');
INSERT INTO DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE) VALUES ('com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dao.CeAssetGroupManagementMapper.getAssetName', '1', '01', null, 'select base99.* from ( %s ) base99 where base99.institutionCode in %s', 'MST', 'CeAssetGroupManagement001', 'ZCW', null, TIMESTAMP '2020-07-06 11:32:31', 'ZCW', TIMESTAMP '2020-07-06 11:32:31', null, null, null);
-----  zcw 资产系列管理二级法人权限控制  20200711 end



/*计划类利率型交易资产代码增加数据隔离 panbin 2020-7-11 start*/
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.trade.fund.dao.TrdFundInvestTradeMapper.selectFManeger');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.trade.fund.dao.TrdFundInvestTradeMapper.selectFManeger','2','02','id','','MST','TrdPlanRateInvestTrade003','MST15', 'admin', null, sysdate, 'admin', sysdate);
/*计划类利率型交易资产代码增加数据隔离 panbin 2020-7-11 end*/


/*20200711_修正净值型周期型产品期限品种值不出问题 周秀萍 start*/
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.product.productelement.periodicmanagement.dao.PeriodicManagementMapper.selectForPage');
Insert into DATA_AUTH_MAPPER_CONFIG (SQL_ID,FILTER_TYPE,IS_NEST,JOIN_COLUMN_NAME,FILTER_SQL,SYSTEM_CODE,PAGE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADD_COLUMN,ADD_COLUMN_NAME,DATA_TYPE) values ('com.joyintech.service.product.productelement.periodicmanagement.dao.PeriodicManagementMapper.selectForPage','2','02','finprodId',null,'FIN','PeriodicManagement001','admin',null,to_timestamp('30-6月 -20 08.23.04.000000000 下午','DD-MON-RR HH.MI.SSXFF AM'),'wy',to_timestamp('30-6月 -20 08.23.04.000000000 下午','DD-MON-RR HH.MI.SSXFF AM'),null,null,'FINPROD04');
 
/*20200711_修正净值型周期型产品期限品种值不出问题 周秀萍 end*/

-----  jqq 公募基金交易资产推荐方未过滤 start  
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.trade.fund.dao.TrdFundInvestTradeMapper.getAssetRecommandOrg');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.trade.fund.dao.TrdFundInvestTradeMapper.getAssetRecommandOrg','2','02','id','','TRD','TrdFundInvestTrade001','MST15', 'admin', null, sysdate, 'admin', sysdate);
--	   jqq 公募基金交易资产推荐方未过滤 end


/*20200712_修正净值型周期型产品存续期交易期限品种值不出问题 周秀萍 start*/
    delete from DATA_AUTH_MAPPER_CONFIG
    where sql_id='com.joyintech.service.product.productduration.netproductduration.dao.FinNetProductDurationMapper.getPeriodsList';
    Insert into DATA_AUTH_MAPPER_CONFIG (SQL_ID,FILTER_TYPE,IS_NEST,JOIN_COLUMN_NAME,FILTER_SQL,SYSTEM_CODE,PAGE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADD_COLUMN,ADD_COLUMN_NAME,DATA_TYPE) 
    values ('com.joyintech.service.product.productduration.netproductduration.dao.FinNetProductDurationMapper.getPeriodsList','2','02','parentId',null,'FIN','FinNetProductDuration','admin',null,to_timestamp('30-6月 -20 08.23.04.000000000 下午','DD-MON-RR HH.MI.SSXFF AM'),'wy',to_timestamp('30-6月 -20 08.23.04.000000000 下午','DD-MON-RR HH.MI.SSXFF AM'),null,null,'FINASSET13');

/*20200712_修正净值型周期型产品存续期交易期限品种值不出问题 周秀萍 end*/
    
-----  zcw 资产系列授信下拉框权限控制 20200712 start
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.risk.creditmanagement.ceassetgroup.dao.CeAssetGroupMapper.getAssetGroupAliasIds');
INSERT INTO DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE) VALUES ('com.joyintech.service.risk.creditmanagement.ceassetgroup.dao.CeAssetGroupMapper.getAssetGroupAliasIds', '1', '01', null, 'select base99.*,base99.institutionCode as belongInstitutionCode from ( %s ) base99 where base99.institutionCode in %s', 'MST', 'CeAssetGroup001', 'ZCW', null, TIMESTAMP '2020-07-10 00:27:13', 'JQQ', TIMESTAMP '2020-07-10 00:27:13', null, null, null);
-----  zcw 资产系列授信下拉框权限控制  20200712 end

-----  xr 债券存续期二级法人权限控制 start
delete DATA_AUTH_MAPPER_CONFIG where SQL_ID='com.joyintech.service.period.trdbondduration.dao.TrdBondDurationMapper.getSecManageAcctId';
INSERT INTO DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE) VALUES ('com.joyintech.service.period.trdbondduration.dao.TrdBondDurationMapper.getSecManageAcctId', '2', '02', 'id', null, 'MST', 'TrdBondDuration001', 'admin', null, TIMESTAMP '2020-07-10 00:27:19', 'admin', TIMESTAMP '2020-07-10 00:27:19', null, null, null);
-----  xr 债券存续期二级法人权限控制 end

-----  20200712 add by lizhi 产品费用支付：指令信息二级法人权限控制 start
delete DATA_AUTH_MAPPER_CONFIG where SQL_ID='com.joyintech.service.settle.order.dao.BussSettleOrderCommMapper.queryAcctList';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.settle.order.dao.BussSettleOrderCommMapper.queryAcctList','2','02','seq_no','','FIN','FinProductFeePay001','MST09', 'admin', null, sysdate, 'wy', sysdate);
-----  20200712 add by lizhi 产品费用支付：指令信息二级法人权限控制 end

-----  20200712 add by lizhi BS-1030:【产品端管理/浮动管理费调整】分档产品查询不到浮动管理费信息 start
delete DATA_AUTH_MAPPER_CONFIG where SQL_ID='com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dao.FinFloatMgtExpQuotationMapper.selectForPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dao.FinFloatMgtExpQuotationMapper.selectForPage','2','02','pfinprodid','','FIN','FinFloatMgtExpQuotation001','FINPROD04', 'admin', null, sysdate, 'wy', sysdate);
-----  20200712 add by lizhi BS-1030:【产品端管理/浮动管理费调整】分档产品查询不到浮动管理费信息 end

-----  20200712 add by leipeng 多级法人脚本修改 start
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcAssetBaseInfo';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcPrdBaseInfo';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper.queryAllPbcPrdBaseInfo';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.chb.botastpositionregist.dao.ChbBotAstPositionRegist001Mapper.searchPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcAssetBaseInfo','1','01','','select b.*,di.belongInstitutionCode,di.model_type from ( %s )  b LEFT JOIN ( select S.PBC_ACCOUNT_ID,di.institution_code  from PBC_PRD_ACCOUNT_SUB s LEFT JOIN fin_product f ON S.ACCOUNT_ID=F.FINPROD_MARKET_ID left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di  on di.PBC_ACCOUNT_ID = b.PBC_ACCOUNT_ID where di.institution_code in %s','SVR','PbcPrdAccountSub','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcPrdBaseInfo','1','01','','select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di on di.finprod_market_id = b.PROD_ID  where di.institution_code in %s','SVR','PbcPrdAccountSub','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper.queryAllPbcPrdBaseInfo','1','01','','select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di on di.finprod_market_id = b.PROD_ID  where di.institution_code in %s','SVR','PbcPrdAccountSub','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.chb.botastpositionregist.dao.ChbBotAstPositionRegist001Mapper.searchPage','1','01','','select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN DATA_AUTH_NOTIFY_INST di on di.finprod_market_id = b.planAssetId AND DI.MODEL_TYPE=''02'' where di.institution_code in %s','SVR','SvrChbBotAstPositionRegist001','', 'admin', null, sysdate, 'admin', sysdate);
-----  20200712 add by leipeng 多级法人脚本修改 start

/*公募基金交易二级法人权限控制 panbin 2020-7-12 start*/
DELETE FROM DATA_AUTH_MAPPER_CONFIG where SQL_ID='com.joyintech.service.entrust.etrfund.dao.EtrFundMapper.selectManeger';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrfund.dao.EtrFundMapper.selectManeger','2','02','id','','MST','EtrFund002','MST15', 'admin', null, sysdate, 'admin', sysdate);
/*公募基金交易二级法人权限控制 panbin 2020-7-12 end*/


-----  wujian 批处理监控 过滤修改bug 20200713  START
delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.system.batch.batchmonitor.dao.SysBatchMonitor001Mapper.findBatchExeDetail');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.system.batch.batchmonitor.dao.SysBatchMonitor001Mapper.findBatchExeDetail','1','01','','SELECT base99.*,si.Institution_Code belongInstitutionCode FROM ( %s ) base99 left join sys_user su ON base99.CREATEUSER = su.user_name left JOIN Sys_Department sd ON sd.dept_code = su.dept_code left JOIN Sys_Institution SI ON Si.Institution_Code = sd.institution_code WHERE SI.Institution_Code IN %s  or BASE99.CREATEUSER = ''System''
','SYS','SysBatchMonitor001','', 'admin', null, sysdate, 'admin', sysdate);
-----  wujian 批处理监控 过滤修改bug 20200713  end


-----  songyawei 交易对手名称重复修改 20200714  start

delete from data_auth_mapper_config where SQL_ID IN ('com.joyintech.service.basicdata.msttradeopponent.dao.BasicMstTradeOpponent002Mapper.checkcounterName');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
values ('com.joyintech.service.basicdata.msttradeopponent.dao.BasicMstTradeOpponent002Mapper.checkcounterName','2','02','COUNTER_ID','','MST','MstTradeOpponent002','MST15', 'admin', null, sysdate, 'admin', sysdate);
-----  songyawei 交易对手名称重复修改 20200714  end