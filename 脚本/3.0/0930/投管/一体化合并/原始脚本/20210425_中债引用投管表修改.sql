update data_auth_mapper_config set filter_sql='select * from ( %s ) b LEFT JOIN (select di.INSTITUTION_CODE,d.trade_code from(select DISTINCT trade_code,prod_id from CHB_INVEST_TRADE union all select DISTINCT trade_id trade_code,prod_id from chb_prd_fee_deal where 1 = 1 and FEE_TYPE = ''02'' ) d  left join DATA_AUTH_NOTIFY_INST di on di.business_id=d.prod_id and di.model_type=''02'' and DI.DATA_TYPE=''PTL01'') di on di.trade_code = b.id where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.chb.investtradereg.dao.InvestTradeRegist001Mapper.selectTradeNoCodeList';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id  finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PRODID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.chb.prodpersonissue.dao.ProdPersonIssue001Mapper.queryForProdPersonIssuePage';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id  finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PRODID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.chb.proddurationreg.dao.ProdDurationReg001Mapper.selectAllData';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id  finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PRODID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.chb.prodraisetotalregist.dao.RaiseTotalRegist001Mapper.selectAllData';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id  finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PRODID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.chb.perprodeclarereg.dao.PerProDeclareReg001Mapper.selectAllData';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id  finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PRODID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.chb.prodterminationregist.dao.ProdTerminationRegist001Mapper.selectAllData';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id  finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.assetCode where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.chb.astvalueregist.dao.ChbAstValueRegist001Mapper.searchPage';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id  finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PRODID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.chb.investtradereg.dao.InvestTradeRegist001Mapper.selectAllData';

update data_auth_mapper_config set filter_sql='select * from ( %s ) b LEFT JOIN  DATA_AUTH_NOTIFY_INST di  on di.business_id = b.assetCode and di.model_type = ''02'' and DI.DATA_TYPE = ''PTL01'' where di.institution_code in %s'
where sql_id='com.joyintech.tams.report.system.systempbccapassetinfo.dao.PbcCapAssetInfo001DtoMapper.queryForPage';
update data_auth_mapper_config set filter_sql='select * from ( %s ) b LEFT JOIN  DATA_AUTH_NOTIFY_INST di  on di.business_id = b.id  and di.model_type = ''02'' and DI.DATA_TYPE = ''PTL01'' where di.institution_code in %s'
where sql_id='com.joyintech.tams.report.system.systempbccapassetinfo.dao.PbcCapAssetInfo001DtoMapper.getAssetCodeList';
update data_auth_mapper_config set filter_sql='select * from ( %s ) b LEFT JOIN (select p.pobcassetid finprod_market_id,di.institution_code from PBC_CAP_ASSET_INFO p  left join DATA_AUTH_NOTIFY_INST di on p.assetid=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE = ''PTL01'') di on di.finprod_market_id = b.id where di.institution_code in %s'
where sql_id='com.joyintech.tams.report.system.systempbccapassetinfo.dao.PbcCapAssetInfo001DtoMapper.setPobCasSeTidList';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PRODID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper.searchPage';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PRODID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dao.PbcInitProdRaisingSubMapper.searchPage';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PRODID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dao.PbcPrdBaseinfoEndSubMapper.searchPage';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PRODID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.dao.PbcProdRaiseSubMapper.searchPageForOnePrd';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.accountId  where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.searchPage';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.accountId where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.pbc.classic.prdaccountendsub.dao.PbcPrdAccountEndSubMapper.searchPage';
update data_auth_mapper_config set filter_sql='select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.ACCOUNT_ID where di.institution_code in %s'
where sql_id='com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper.searchPageForOnePrd';
update data_auth_mapper_config set filter_sql = 'select b.*,di.belongInstitutionCode,di.model_type from ( %s )  b LEFT JOIN ( select S.PBC_ACCOUNT_ID,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type  from PBC_PRD_ACCOUNT_SUB s  left join DATA_AUTH_NOTIFY_INST di on S.ACCOUNT_ID=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di  on di.PBC_ACCOUNT_ID = b.PBC_ACCOUNT_ID where di.institution_code in %s'
where sql_id = 'com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcAssetBaseInfo';
update data_auth_mapper_config set filter_sql = 'select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from  DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PROD_ID  where di.institution_code in %s'
where sql_id = 'com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper.queryAllPbcPrdBaseInfo';
update data_auth_mapper_config set filter_sql = 'select b.*,di.belongInstitutionCode,di.model_type from ( %s ) b LEFT JOIN (select di.business_id finprod_market_id,di.institution_code,di.belong_institution_code belongInstitutionCode,di.model_type from DATA_AUTH_NOTIFY_INST di where di.model_type = ''02'' and DI.DATA_TYPE=''PTL01'') di on di.finprod_market_id = b.PROD_ID  where di.institution_code in %s'
where sql_id = 'com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper.queryAllPbcPrdBaseInfo';


















