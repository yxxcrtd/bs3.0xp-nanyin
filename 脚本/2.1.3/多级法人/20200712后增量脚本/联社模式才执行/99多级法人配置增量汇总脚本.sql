
-----  add by wj 20200716 二级法人 新增配置  START
delete from data_auth_mapper_config WHERE SQL_ID IN  ('com.joyintech.service.entrust.etrproject.dao.EtrProjectMapper.selectForPage','com.joyintech.service.entrust.etrboutrepo.dao.EtrBoutRepoMapper.selectForPage',
'com.joyintech.service.entrust.etrseclend.dao.EtrSecLendMapper.selectForPage',
'com.joyintech.service.entrust.etrstock.dao.EtrStockMapper.selectForPage','com.joyintech.service.entrust.etrirs.dao.EtrIRSMapper.selectForPage','com.joyintech.service.entrust.etrwtspotex.dao.EtrWtSpotexMapper.selectForPage','com.joyintech.service.entrust.etrforwex.dao.EtrForwexMapper.select','com.joyintech.service.entrust.etrwtfxswap.dao.EtrWtFxswapMapper.selectForPage','com.joyintech.service.entrust.etrconbond.dao.EtrConBondMapper.selectForPage','com.joyintech.service.entrust.etrbondtostock.dao.EtrBondToStockMapper.selectForPage','com.joyintech.service.entrust.etroption.dao.EtrOptionMapper.selectForPage');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrproject.dao.EtrProjectMapper.selectForPage','2','02','portfolioId','','ETR','EtrProject001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrboutrepo.dao.EtrBoutRepoMapper.selectForPage','2','02','portfolioId','','ETR','EtrBoutRepo001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrseclend.dao.EtrSecLendMapper.selectForPage','2','02','portfolioId','','ETR','EtrSecLend001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrstock.dao.EtrStockMapper.selectForPage','2','02','portfolioId','','ETR','EtrStock001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrirs.dao.EtrIRSMapper.selectForPage','2','02','portfolioId','','ETR','EtrIRS001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrwtspotex.dao.EtrWtSpotexMapper.selectForPage','2','02','portfolioId','','ETR','EtrWtSpotex001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrforwex.dao.EtrForwexMapper.select','2','02','portfolioId','','ETR','EtrForwex001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrwtfxswap.dao.EtrWtFxswapMapper.selectForPage','2','02','portfolioId','','ETR','EtrWtFxswap001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrconbond.dao.EtrConBondMapper.selectForPage','2','02','portfolioId','','ETR','EtrConBond001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrbondtostock.dao.EtrBondToStockMapper.selectForPage','2','02','portfolioId','','ETR','EtrBondToStock001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etroption.dao.EtrOptionMapper.selectForPage','2','02','portfolioId','','ETR','EtrOption001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
-----  add by wj 20200716 二级法人 新增配置  END

--modify by hxy 20200716 多级法人 修改配置 START
--【产品端轧差管理】划款信息查询
update data_auth_mapper_config set join_column_name='portid' where sql_id='com.joyintech.service.settle.netting.dao.NettingManagementMapper.transferMessage';
--modify by hxy 20200716 多级法人 修改配置 END

--sjj add 20200716 场外期权交易过滤 start
delete from data_auth_mapper_config where sql_id ='com.joyintech.service.trade.option.dao.TrdOptionTradeMapper.selectDeal';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.trade.option.dao.TrdOptionTradeMapper.selectDeal', '2', '02', 'targetId', '', 'TRD', 'TrdOptionTrade001', 'SJJ', '', sysdate, 'SJJ', sysdate, '', '', 'V29');
--sjj add 20200716 场外期权交易过滤 end

--wangyang1 add 20200716 指令汇总过滤删除 start 
delete from DATA_AUTH_MAPPER_CONFIG where SQL_ID ='com.joyintech.service.trade.investtrade.commandscollection.dao.TrdCommandsCollectionMapper.selectForPage';
--wangyang1 add 20200716 指令汇总过滤删除 end


-- add by caiqun 20200716 多级法人改造 新增配置  START
delete from data_auth_mapper_config where sql_id = 'com.joyintech.service.period.trdstockbonus.dao.TrdStockBonusMapper.getBusiCode';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.period.trdstockbonus.dao.TrdStockBonusMapper.getBusiCode','1','02','institutionCode','','MST','TrdStockBonus001','', 'admin', null, sysdate, 'admin', sysdate);
-- add by caiqun 20200716 多级法人改造 新增配置  END=======


-----  add by leipeng 20200716 二级法人 修改配置  START
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.chb.botastpositionregist.dao.ChbBotAstPositionRegist001Mapper.searchPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.chb.botastpositionregist.dao.ChbBotAstPositionRegist001Mapper.searchPage','1','01','','select b.*,di.BELONG_INSTITUTION_CODE belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN DATA_AUTH_NOTIFY_INST di on di.BUSINESS_ID = b.planAssetId AND DI.MODEL_TYPE=''02'' where di.institution_code in %s','SVR','SvrChbBotAstPositionRegist001','', 'admin', null, sysdate, 'admin', sysdate);
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcAssetBaseInfo';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcPrdBaseInfo';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper.queryAllPbcPrdBaseInfo';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcAssetBaseInfo','1','01','','select b.*,di.belongInstitutionCode,di.model_type from ( %s )  b LEFT JOIN ( select S.PBC_ACCOUNT_ID,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type  from PBC_PRD_ACCOUNT_SUB s LEFT JOIN fin_product f ON S.ACCOUNT_ID=F.FINPROD_MARKET_ID left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di  on di.PBC_ACCOUNT_ID = b.PBC_ACCOUNT_ID where di.institution_code in %s','SVR','PbcPrdAccountSub','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcPrdBaseInfo','1','01','','select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di on di.finprod_market_id = b.PROD_ID  where di.institution_code in %s','SVR','PbcPrdAccountSub','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper.queryAllPbcPrdBaseInfo','1','01','','select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di on di.finprod_market_id = b.PROD_ID  where di.institution_code in %s','SVR','PbcPrdAccountSub','', 'admin', null, sysdate, 'admin', sysdate);
-----  add by leipeng 20200716 二级法人 修改配置  END

-----  add by sjj 20200716 二级法人 修改配置  START
delete from data_auth_mapper_config WHERE SQL_ID ='com.joyintech.service.trade.pretrdrepotrade.dao.PreTrdRepoTrade001Mapper.selectForPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.trade.pretrdrepotrade.dao.PreTrdRepoTrade001Mapper.selectForPage', '2', '02', 'portfolioId', '', 'TRD', 'PreTrdRepoTrade001', 'admin', '', sysdate, 'admin', sysdate, '', '', 'PTL01');
-----  add by sjj 20200716 二级法人 修改配置  end


-----  add by wj 20200716  修复 bug BS-1215  START
 UPDATE Data_Auth_Mapper_Config SET data_type = 'MST15'
      WHERE sql_id 
     = 'com.joyintech.service.basicdata.msttradeopponent.dao.BasicMstTradeOpponent001Mapper.queryForPage';
     -----  add by wj 20200716  修复 bug BS-1215  END
	 
-----  add by wj 20200716  修复 bug BS-1254  START
DELETE  Data_Auth_Mapper_Config WHERE SQL_ID IN ('com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dao.ValFxValuztionInfo002Mapper.getFinprodId2'
,'com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao.findFinRateInfoByBenchmarkId',
'com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dao.ValRateSwapValuationInfo001Mapper.selectList');


-- 外汇
insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dao.ValFxValuztionInfo002Mapper.getFinprodId2', '2', '02', 'portfolioId', null, 'VAL', 'ValFxValuztionInfo002', null, null, null, null, null, null, null, 'PTL01');

---  期权行情信息  合约标的
insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao.findFinRateInfoByBenchmarkId', '2', '02', 'code', null, 'VAL', 'ExternalOptions002', null, null, null, null, null, null, null, 'V29');


--- 利率估值
insert into DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dao.ValRateSwapValuationInfo001Mapper.selectList', '2', '02', 'portfolioId', null, 'VAL', 'ValRateSwapValuationInfo001', null, null, null, null, null, null, null, 'PTL01');


-------  add by wj 20200716  修复 bug BS-1254  END


-----  add by wj 20200716  修复 bug BS-1298/1323  START


DELETE  Data_Auth_Mapper_Config WHERE SQL_ID IN ('com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao.selectList'
,'com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dao.ValRateSwapValuationInfo001Mapper.getFinprodId2'
,'com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dao.ValFxValuztionInfo001Mapper.selectList');


insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dao.ValRateSwapValuationInfo001Mapper.getFinprodId2', '2', '02', 'portfolioId', null, 'VAL', 'ValRateSwapValuationInfo002', null, null, null, null, null, null, null, 'PTL01');


---  期权行情信息  
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao.selectList', '2', '02', 'targetId', null, 'VAL', 'ExternalOptions001', null, null, null, null, null, null, null, 'V29');


-- 外汇
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dao.ValFxValuztionInfo001Mapper.selectList', '2', '02', 'portfolioId', null, 'VAL', 'ValFxValuztionInfo001', null, null, null, null, null, null, null, 'PTL01');

-------  add by wj 20200716  修复 bug   END

-----  add by sjj 20200717 二级法人可转债转股交易新增配置  START
delete from data_auth_mapper_config WHERE SQL_ID ='com.joyintech.tams.invest.trade.bondtostock.dao.TrdBondToStockMapper.findPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.invest.trade.bondtostock.dao.TrdBondToStockMapper.findPage','2','02','portfolioId','','SVR','PbcPrdAccountSub','PTL01', 'admin', null, sysdate, 'admin', sysdate);
-----  add by sjj 20200717 二级法人可转债转股交易新增配置  END 

-----  add by wangyang1 20200718 二级法人授权新增配置  START
delete from data_auth_mapper_config WHERE SQL_ID ='com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dao.CeAssetGroupManagementMapper.getAssetId';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dao.CeAssetGroupManagementMapper.getAssetId','2','02','id','','RSK','CeAssetGroupManagement003','', 'admin', null, sysdate, 'wy', sysdate);
-----  add by wangyang1 20200718 二级法人授权新增配置  end


----- add by  wj  20200720 ---- 期权行情信息 查询条件下拉框过滤 start
delete from data_auth_mapper_config WHERE SQL_ID IN  ('com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao.findFinprodId','com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao.findTargetId');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao.findFinprodId', '2', '02', 'targetId', null, 'VAL', 'ExternalOptions001', null, null, null, null, null, null, null, 'V29');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.valuation.basedata.marketdata.externaloptions.dao.ExternalOptionsDao.findTargetId', '2', '02', 'code', null, 'VAL', 'ExternalOptions001', null, null, null, null, null, null, null, 'V29');

----- add by  wj  20200720 ---- 期权行情信息 查询条件下拉框过滤  end

----- add by  sjj  20200720 ---- 期权行情信息 查询条件下拉框过滤 start
delete from data_auth_mapper_config WHERE SQL_ID ='com.joyintech.service.trade.pretrdbondtrade.dao.PreTrdBondInvestTradeMapper.selectDeal';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.trade.pretrdbondtrade.dao.PreTrdBondInvestTradeMapper.selectDeal','2','02','portfolioId','','TRD','PreTrdBondInvestTrade001','PTL01', 'admin', null, sysdate, 'admin', sysdate);
----- add by  sjj  20200720 ---- 期权行情信息 查询条件下拉框过滤 end

----- add by  wangyang  20200720 二级法人指令 start
delete from data_auth_mapper_config where SQL_ID IN('com.joyintech.service.entrust.etrinvestallinfo.dao.EtrInvestAllInfoMapper.getfinprodId',
'com.joyintech.service.entrust.etrfund.dao.EtrFundMapper.getSelectPortfolioId',
'com.joyintech.service.entrust.etrinvestallinfo.dao.EtrInvestAllInfoMapper.select',
'com.joyintech.service.entrust.etrbondfirstlbidding.dao.EtrBondFirstLBiddingMapper.getentrustIdAdd',
'com.joyintech.service.entrust.etrbondfirstlbidding.dao.EtrBondFirstLBiddingMapper.selectForPage',
'com.joyintech.service.entrust.etrrepo.dao.EtrRepoMapper.getentrustIdAdd',
'com.joyintech.service.trade.bond.dao.TrdBondInvestTradeMapper.getSecManageAcctId',
'com.joyintech.service.entrust.etrstandardbond.dao.EtrStandardBondMapper.selectSearchDataList',
'com.joyintech.service.entrust.etrstandardbond.dao.EtrStandardBondMapper.selectAvaPledgeBondList',
'com.joyintech.service.entrust.etrstandardbond.dao.EtrStandardBondMapper.selectPledgeBondList',
'com.joyintech.service.entrust.etrbrowdepoinfo.dao.EtrBrowDepoInfoMapper.getSelectFinprodMarketId',
'com.joyintech.service.entrust.etrbrowdepoinfo.dao.EtrBrowDepoInfoMapper.getFinprodMarketId',
'com.joyintech.service.entrust.etrbrowdepoinfo.dao.EtrBrowDepoInfoMapper.getSelectPortfolioId',
'com.joyintech.service.entrust.etrfund.dao.EtrFundMapper.getSelectPortfolioId',
'com.joyintech.service.entrust.etrfund.dao.EtrFundMapper.select',
'com.joyintech.service.entrust.etrplanassetn.dao.EtrPlanAssetNMapper.getentrustIdAdd',
'com.joyintech.service.entrust.etrplanassetn.dao.EtrPlanAssetNMapper.getfinprodId',
'com.joyintech.service.entrust.etrplanassetn.dao.EtrPlanAssetNMapper.selectForPage',
'com.joyintech.service.entrust.etrplanassetn.dao.EtrPlanAssetNMapper.getfinprodIdForAdd',
'com.joyintech.service.entrust.etrplanassetr.dao.EtrPlanAssetRMapper.getentrustIdAdd',
'com.joyintech.service.entrust.etrplanassetr.dao.EtrPlanAssetRMapper.getfinprodId',
'com.joyintech.service.entrust.etrplanassetr.dao.EtrPlanAssetRMapper.selectForPage',
'com.joyintech.service.entrust.etrplanassetr.dao.EtrPlanAssetRMapper.getfinprodIdForAdd',
'com.joyintech.service.entrust.etrplanassetc.dao.EtrPlanAssetCMapper.getentrustIdAdd',
'com.joyintech.service.entrust.etrplanassetc.dao.EtrPlanAssetCMapper.getfinprodId',
'com.joyintech.service.entrust.etrplanassetc.dao.EtrPlanAssetCMapper.selectForPage',
'com.joyintech.service.entrust.etrplanassetc.dao.EtrPlanAssetCMapper.getfinprodIdForAdd',
'com.joyintech.service.entrust.etrinstruction.dao.EtrInstructionMapper.getFinprodMarketId',
'com.joyintech.service.entrust.etrinstruction.dao.EtrInstructionMapper.select');
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrinvestallinfo.dao.EtrInvestAllInfoMapper.getfinprodId','2','02','FINPROD_ID','','ETR','EtrInvestAllInfo001','', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrfund.dao.EtrFundMapper.getSelectPortfolioId','2','02','id','','ETR','EtrInvestAllInfo001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrinvestallinfo.dao.EtrInvestAllInfoMapper.select','2','02','portfolioId','','ETR','EtrInvestAllInfo001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrbondfirstlbidding.dao.EtrBondFirstLBiddingMapper.getentrustIdAdd','2','02','id','','ETR','EtrBondFirstLBidding001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrbondfirstlbidding.dao.EtrBondFirstLBiddingMapper.selectForPage','2','02','PORTFOLIOID','','ETR','EtrBondFirstLBidding001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrrepo.dao.EtrRepoMapper.getentrustIdAdd','2','02','id','','ETR','EtrRepo001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.trade.bond.dao.TrdBondInvestTradeMapper.getSecManageAcctId','2','02','hidCode','','ETR','EtrStandardBond001','MST10', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrstandardbond.dao.EtrStandardBondMapper.selectSearchDataList','2','02','portfolioId','','ETR','EtrStandardBond001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrstandardbond.dao.EtrStandardBondMapper.selectAvaPledgeBondList','2','02','portfolioId','','ETR','EtrStandardBond002','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrstandardbond.dao.EtrStandardBondMapper.selectPledgeBondList','2','02','portfolioId','','ETR','EtrStandardBond003','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrbrowdepoinfo.dao.EtrBrowDepoInfoMapper.getSelectFinprodMarketId','2','02','FINPROD_ID','','ETR','EtrBrowDepoInfo002','FINASSET02', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrbrowdepoinfo.dao.EtrBrowDepoInfoMapper.getFinprodMarketId','2','02','finprodId','','ETR','EtrBrowDepoInfo001','FINASSET02', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrbrowdepoinfo.dao.EtrBrowDepoInfoMapper.getSelectPortfolioId','2','02','id','','ETR','EtrBrowDepoInfo001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrfund.dao.EtrFundMapper.select','2','02','portfolioId','','ETR','EtrFund001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetn.dao.EtrPlanAssetNMapper.getentrustIdAdd','2','02','id','','ETR','EtrPlanAssetN001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetn.dao.EtrPlanAssetNMapper.getfinprodId','2','02','hidCode','','ETR','EtrPlanAssetN001','FINASSET04', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetn.dao.EtrPlanAssetNMapper.selectForPage','2','02','portfolioId','','ETR','EtrPlanAssetN001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetn.dao.EtrPlanAssetNMapper.getfinprodIdForAdd','2','02','FINPROD_ID','','ETR','EtrPlanAssetN002','FINASSET04', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetr.dao.EtrPlanAssetRMapper.getentrustIdAdd','2','02','id','','ETR','EtrPlanAssetR002','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetr.dao.EtrPlanAssetRMapper.getfinprodId','2','02','finprodId','','ETR','EtrPlanAssetR001','FINASSET05', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetr.dao.EtrPlanAssetRMapper.selectForPage','2','02','portfolioId','','ETR','EtrPlanAssetR001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetr.dao.EtrPlanAssetRMapper.getfinprodIdForAdd','2','02','FINPROD_ID','','ETR','EtrPlanAssetR002','FINASSET05', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetc.dao.EtrPlanAssetCMapper.getentrustIdAdd','2','02','id','','ETR','EtrPlanAssetC002','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetc.dao.EtrPlanAssetCMapper.getfinprodId','2','02','hidCode','','ETR','EtrPlanAssetC001','FINASSET011', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetc.dao.EtrPlanAssetCMapper.selectForPage','2','02','portfolioId','','ETR','EtrPlanAssetC001','PTL01', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrplanassetc.dao.EtrPlanAssetCMapper.getfinprodIdForAdd','2','02','FINPROD_ID','','ETR','EtrPlanAssetC002','FINASSET011', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrinstruction.dao.EtrInstructionMapper.getFinprodMarketId','2','02','hidCode','','ETR','EtrInstruction001','TRD75', 'admin', null, sysdate, 'wy', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.entrust.etrinstruction.dao.EtrInstructionMapper.select','2','02','finprodMarketId','','ETR','EtrInstruction001','TRD75', 'admin', null, sysdate, 'wy', sysdate);
----- add by  wangyang  20200720 二级法人指令 end

----- add by  wj  20200720 ---- BS-1423【联社模式】【投资交易-项目类资产指令】项目类资产指令新建-资产名称栏可选择其他机构名下资产，数据未隔离 start
delete from data_auth_mapper_config WHERE SQL_ID IN  ('com.joyintech.service.entrust.etrproject.dao.EtrProjectMapper.getfinprodIdForAdd'
,'com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dao.ValFxValuztionInfo001Mapper.getFinprodId2');

insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.entrust.etrproject.dao.EtrProjectMapper.getfinprodIdForAdd', '2', '02', 'FINPROD_ID', null, 'ETR', 'EtrProject002', null, null, null, null, null, null, null, 'FINASSET06');


--- 外汇行情信息 001  交易编号
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dao.ValFxValuztionInfo001Mapper.getFinprodId2', '2', '02', 'portfolioId', null, 'VAL', 'ValFxValuztionInfo001', null, null, null, null, null, null, null, 'PTL01');
----- add by  wj  20200720 ---- BS-1423 end



-----  add by wj 20200722 交易对手 代码回退  START
 UPDATE Data_Auth_Mapper_Config SET data_type = 'PTL01,MST15'
      WHERE sql_id 
     = 'com.joyintech.service.basicdata.msttradeopponent.dao.BasicMstTradeOpponent001Mapper.queryForPage';
     -----  add by wj 20200722 交易对手 代码回退  END
	 
-----  add by wj 20200722  START

delete Data_Auth_Mapper_Config where sql_id = 'com.joyintech.service.asset.common.dao.AssetCommonMapper.getSysDictCode';

 insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.asset.common.dao.AssetCommonMapper.getSysDictCode', '1', '01', null, 'SELECT * FROM ( %s ) WHERE institutionCode IS NULL OR institutionCode IN  %s', 'FIN', 'FinProductBuild001', null, null, null, null, null, null, null, null);

     -----  add by wj 20200722  END	 

-----  add by sjj 20200723  产品登记编码拦截器配置回退脚本 START
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryForPage';
DELETE FROM data_auth_mapper_config WHERE SQL_ID =  'com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryForPageOld'; 
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryForPage','1','01','','select * from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE = ''FINPROD04'') di on di.finprod_market_id = b.prodId  where di.institution_code in %s','SVR','ProRegistCodeManage001','', 'admin', null, sysdate, 'admin', sysdate);
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryForPageOld','1','01','','select * from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02''  and di.data_type = ''FINPROD04'') di on di.finprod_market_id = b.prodId  where di.institution_code in %s','SVR','ProRegistCodeManage001','', 'admin', null, sysdate, 'admin', sysdate);
-----  add by sjj 20200723  产品登记编码拦截器配置回退脚本 end


-----  add by wj 20200723  BS-1287 新增资产时，资产代码下拉框应该只能选到本机构资产，现在可以选到其他机构的资产，不正确； START
DELETE FROM data_auth_mapper_config WHERE SQL_ID in (
'com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dao.CeAssetGroupManagementMapper.selectAssetInfo', 'com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dao.CeAssetGroupManagementMapper.queryAssetInfo'); 


insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dao.CeAssetGroupManagementMapper.queryAssetInfo', '1', '01', null, 'SELECT * FROM ( %s
) base99 WHERE FINPROD_TYPE2 IN (''F01'',''F06'',''F10'',''F32'')
OR ( FINPROD_TYPE2 NOT IN (''F01'',''F06'',''F10'',''F32'')
AND EXISTS ( SELECT 1 FROM data_auth_notify_inst inst
WHERE base99.id = inst.business_id AND inst.model_type = ''02''
AND inst.institution_code IN %s) 
)', 'RSK', 'CeAssetGroupManagement003', null, null, null, null, null, null, null, null);

insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.risk.creditmanagement.ceassetgroupmanagement.dao.CeAssetGroupManagementMapper.selectAssetInfo', '1', '01', null, 'SELECT * FROM ( %s
) base99 WHERE FINPROD_TYPE2 IN (''F01'',''F06'',''F10'',''F32'')
OR ( FINPROD_TYPE2 NOT IN (''F01'',''F06'',''F10'',''F32'')
AND EXISTS ( SELECT 1 FROM data_auth_notify_inst inst
WHERE base99.finprodId = inst.business_id AND inst.model_type = ''02''
AND inst.institution_code IN %s) 
)', 'RSK', 'CeAssetGroupManagement003', null, null, null, null, null, null, null, null);



-----  add by wj 20200723  BS-1287 新增资产时，资产代码下拉框应该只能选到本机构资产，现在可以选到其他机构的资产，不正确； end

-----  add by sjj 20200723  应收款拦截器脚本 start
delete from data_auth_mapper_config WHERE SQL_ID='com.joyintech.service.period.trdsecoverdueduration.dao.TrdSecOverdueDurationMapper.theoryFindPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.period.trdsecoverdueduration.dao.TrdSecOverdueDurationMapper.theoryFindPage', '2', '02', 'assetId', '', 'TRD','TrdSecOverdueDuration001','admin', '', sysdate, 'admin', sysdate, '', '', 'MST10,FINASSET12,FINASSET05,FINASSET02,FINASSET06');
-----  add by sjj 20200723  应收款拦截器脚本 end


-----  add by wj 20200727  BS-727 查询没有根据选择的机构名称筛选； start

DELETE  Data_Auth_Mapper_Config WHERE sql_id = 'com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryForPage';


insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryForPage', '1', '01', null, 'select * from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from fin_product f inner join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE = ''FINPROD04'') di on di.finprod_market_id = b.prodId  where di.institution_code in %s', 'SVR', 'ProRegistCodeManage001', 'admin', null, SYSDATE, 'admin', SYSDATE, null, null, null);

-----  add by wj 20200727  BS-727 查询没有根据选择的机构名称筛选；  end

--BS-1708【联社模式】【风险管理-指标参数中间值管理】产品总资产未计入质押式正回购交易发生额  wangyang1 start
delete from data_auth_mapper_config where SQL_ID = 'com.joyintech.service.product.messageadjust.finproductratequotation.dao.FinProductRateQuotationMapper.selectForPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.product.messageadjust.finproductratequotation.dao.FinProductRateQuotationMapper.selectForPage','2','02','finprodId','','FIN','FinProductRateQuotation001','FINPROD04', 'admin', null, sysdate, 'wy', sysdate);
--BS-1708【联社模式】【风险管理-指标参数中间值管理】产品总资产未计入质押式正回购交易发生额  wangyang1 end



-----  add by wj 20200728  BS-1681【债券名单】-成员行A将债券或者其发行人加入黑名单后，成员行B买入资产时仍提示资产/机构在黑名单内

DELETE  Data_Auth_Mapper_Config WHERE sql_id = 'com.joyintech.tams.invest.common.risk.dao.RiskNamListCommonMapper.selectAssetCount';


insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.tams.invest.common.risk.dao.RiskNamListCommonMapper.selectAssetCount', '1', '02', 'insitituteCode', null, 'COM', 'RiskNamListCommonValidatorService', 'admin', null, SYSDATE, 'admin', SYSDATE, null, null, null);

-----  add by wj 20200728  BS-1681【债券名单】-成员行A将债券或者其发行人加入黑名单后，成员行B买入资产时仍提示资产/机构在黑名单内；  end


--BS-1699【产品业绩基准调整】分档产品，按款型调整提交成功，返回列表未展示改生效数据 wangyang1 start
delete from data_auth_mapper_config where SQL_ID = 'com.joyintech.service.product.messageadjust.bmquotation.dao.FinProductBmQuotationMapper.selectForPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID,DATA_TYPE,CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values ('com.joyintech.service.product.messageadjust.bmquotation.dao.FinProductBmQuotationMapper.selectForPage','2','02','pFinprodId','','FIN','FinProductBmQuotation001','FINPROD04', 'admin', null, sysdate, 'wy', sysdate);
--BS-1699【产品业绩基准调整】分档产品，按款型调整提交成功，返回列表未展示改生效数据 wangyang1 end

-----  add by sjj 20200727  BS-998应收款项】成员行 查询资产代码下拉不应展示其他机构私有资产 start
delete from Data_Auth_Mapper_Config where SQL_ID='com.joyintech.service.period.trdsecoverdueduration.dao.TrdSecOverdueDurationMapper.getFinprodMarketId';
insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.period.trdsecoverdueduration.dao.TrdSecOverdueDurationMapper.getFinprodMarketId', '1', '01', null, 'SELECT * FROM ( %s
) base99 WHERE type_8 =''F01''
OR ( type_8 IN (''F03'',''F05'',''F06'')
AND EXISTS ( SELECT 1 FROM data_auth_notify_inst inst
WHERE base99.FINPROD_ID = inst.business_id AND inst.model_type = ''02''
AND inst.institution_code IN %s) 
)', 'TRD', 'TrdSecOverdueDuration001', null, null, null, null, null, null, null, null);
-----  add by sjj 20200727  BS-998应收款项】成员行 查询资产代码下拉不应展示其他机构私有资产 end

--授权二级法人 20200731 start
DELETE  Data_Auth_Mapper_Config WHERE sql_id = 'com.joyintech.service.risk.rskauthoritymanage.dao.RskAuthorityManageMapper.selectAssetTrade';
INSERT INTO DATA_AUTH_MAPPER_CONFIG (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE) VALUES ('com.joyintech.service.risk.rskauthoritymanage.dao.RskAuthorityManageMapper.selectAssetTrade', '2', '02', 'portfolioId', null, 'ETR', 'AAA', 'admin', null, TIMESTAMP '2020-07-28 11:08:21', 'admin', TIMESTAMP '2020-07-28 11:08:21', null, null, 'PTL01');
--授权二级法人  20200731  end