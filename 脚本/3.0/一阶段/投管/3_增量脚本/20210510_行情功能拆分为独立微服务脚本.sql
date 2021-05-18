delete from SYS_PAGE WHERE PAGE_ID IN ('ComBondMarketInfo001',
'ComBondMarketInfo002',
'ComBondQuotationInfo001',
'ComBondQuotationInfo002',
'ComCoinFundQuotationInfo001',
'ComCoinFundQuotationInfo002',
'ComExchangeQuotationInfo001',
'ComExchangeQuotationInfo002',
'ComExternalOptions001',
'ComExternalOptions002',
'ComFundMarketInfo001',
'ComFundMarketInfo002',
'ComFxValuztionInfo001',
'ComFxValuztionInfo002',
'ComInterestRateQuotationInfo001',
'ComInterestRateQuotationInfo002',
'ComMarketCalendarInfo001',
'ComNetAssetQuotationInfo001',
'ComNetAssetQuotationInfo002',
'ComRateSwapInformation001',
'ComRateSwapInformation002',
'ComRateSwapValuationInfo001',
'ComRateSwapValuationInfo002',
'ComSharePrice001',
'ComSharePrice002',
'ComYieldCurveInfo001',
'ComYieldCurveInfo002');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComBondMarketInfo001', 'VAL', '/common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001', 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001', 'ASSET_MANAGEMENT_001_003_014', null, null, '02', '01', '每日债券行情', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComBondMarketInfo002', 'VAL', '/common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo002', 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo002', null, null, null, '02', '01', '每日债券行情', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComBondQuotationInfo001', 'MST', '/common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo001', 'common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo001', 'ASSET_MANAGEMENT_001_003_008', null, null, '02', '01', '债券估值行情信息', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComBondQuotationInfo002', 'MST', '/common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo002', 'common/basicdata/marketdata/mstbondquotationinfo/ComBondQuotationInfo002', null, null, null, '02', '01', '债券估值行情信息新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComCoinFundQuotationInfo001', 'MST', '/common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo001', 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo001', 'ASSET_MANAGEMENT_001_003_007', null, null, '02', '01', '基金类行情信息', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComCoinFundQuotationInfo002', 'MST', '/common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002', 'common/basicdata/marketdata/mstcoinfundquotationinfo/ComCoinFundQuotationInfo002', null, null, null, '02', '01', '基金类行情信息新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComExchangeQuotationInfo001', 'MST', '/common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo001', 'common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo001', 'ASSET_MANAGEMENT_001_003_016', null, null, '02', '01', '汇率行情信息', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComExchangeQuotationInfo002', 'MST', '/common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo002', 'common/basicdata/marketdata/exchangerateinfo/ComExchangeQuotationInfo002', null, null, null, '02', '01', '汇率行情信息新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComExternalOptions001', 'VAL', '/common/basicdata/marketdata/externaloptions/ComExternalOptions001', 'common/basicdata/marketdata/externaloptions/ComExternalOptions001', 'ASSET_MANAGEMENT_001_003_011', null, null, '02', '01', '期权行情信息', 'system', null, '18-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComExternalOptions002', 'VAL', '/common/basicdata/marketdata/externaloptions/ComExternalOptions002', 'common/basicdata/marketdata/externaloptions/ComExternalOptions002', null, null, null, '02', '01', '场外期权', 'system', null, '18-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComFundMarketInfo001', 'VAL', '/common/basicdata/marketdata/fundmarketinfo/ComFundMarketInfo001', 'common/basicdata/marketdata/fundmarketinfo/ComFundMarketInfo001', 'ASSET_MANAGEMENT_001_003_013', null, null, '02', '02', '每日基金行情', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComFundMarketInfo002', 'VAL', '/common/basicdata/marketdata/fundmarketinfo/ComFundMarketInfo002', 'common/basicdata/marketdata/fundmarketinfo/ComFundMarketInfo002', null, null, null, '02', '01', '每日基金行情新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComFxValuztionInfo001', 'VAL', '/common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001', 'common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001', 'ASSET_MANAGEMENT_001_003_009', null, null, '02', '01', '外汇行情信息', 'system', null, '18-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComFxValuztionInfo002', 'VAL', '/common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo002', 'common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo002', null, null, null, '02', '01', '外汇估值信息', 'system', null, '19-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComInterestRateQuotationInfo001', 'MST', '/common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo001', 'common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo001', 'ASSET_MANAGEMENT_001_003_004', null, null, '02', '01', '基准利率行情', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComInterestRateQuotationInfo002', 'MST', '/common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo002', 'common/basicdata/marketdata/mstinterestratequotationinfo/ComInterestRateQuotationInfo002', null, null, null, '02', '01', '利率行情新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComMarketCalendarInfo001', 'MST', '/common/basicdata/marketdata/mstmarketcalendarinfo/ComMarketCalendarInfo001', 'common/basicdata/marketdata/mstmarketcalendarinfo/ComMarketCalendarInfo001', 'ASSET_MANAGEMENT_001_003_006', null, null, '02', '01', '市场日历信息', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComNetAssetQuotationInfo001', 'MST', '/common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo001', 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo001', 'ASSET_MANAGEMENT_001_003_002', null, null, '02', '01', '计划类资产行情信息', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComNetAssetQuotationInfo002', 'MST', '/common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo002', 'common/basicdata/marketdata/mstnetassetquotationinfo/ComNetAssetQuotationInfo002', null, null, null, '02', '01', '计划类资产估值行情信息新建 ', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComRateSwapInformation001', 'MST', '/common/basicdata/marketdata/rateswapinformation/ComRateSwapInformation001', 'common/basicdata/marketdata/rateswapinformation/ComRateSwapInformation001', 'ASSET_MANAGEMENT_001_003_015', null, null, '02', '01', '利率互换曲线', 'system', null, '19-7月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComRateSwapInformation002', 'MST', '/common/basicdata/marketdata/rateswapinformation/ComRateSwapInformation002', 'common/basicdata/marketdata/rateswapinformation/ComRateSwapInformation002', null, null, null, '02', '01', '利率互换行情信息新建', 'system', null, '31-8月 -19 06.44.47.000000 下午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComRateSwapValuationInfo001', 'VAL', '/common/basicdata/marketdata/rateswapvaluationinfo/ComRateSwapValuationInfo001', 'common/basicdata/marketdata/rateswapvaluationinfo/ComRateSwapValuationInfo001', 'ASSET_MANAGEMENT_001_003_010', null, null, '02', '01', '利率互换估值', 'system', null, '16-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComRateSwapValuationInfo002', 'VAL', '/common/basicdata/marketdata/rateswapvaluationinfo/ComRateSwapValuationInfo002', 'common/basicdata/marketdata/rateswapvaluationinfo/ComRateSwapValuationInfo002', null, null, null, '02', '01', '利率互换估值信息', 'system', null, '16-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComSharePrice001', 'MST', '/common/basicdata/marketdata/mstshareprice/ComSharePrice001', 'common/basicdata/marketdata/mstshareprice/ComSharePrice001', 'ASSET_MANAGEMENT_001_003_001', null, null, '02', '01', '股票行情信息', 'system', null, '19-6月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComSharePrice002', 'MST', '/common/basicdata/marketdata/mstshareprice/ComSharePrice002', 'common/basicdata/marketdata/mstshareprice/ComSharePrice002', null, null, null, '02', '01', '股票行情新建', 'system', null, '19-6月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComYieldCurveInfo001', 'MST', '/common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo001', 'common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo001', 'ASSET_MANAGEMENT_001_003_003', null, null, '02', '01', '收益率曲线信息', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');

insert into SYS_PAGE (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ComYieldCurveInfo002', 'MST', '/common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo002', 'common/basicdata/marketdata/yieldcurveinfo/ComYieldCurveInfo002', null, null, null, '02', '01', '收益率曲线信息新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午');








DELETE from sys_page_dictlist WHERE PAGE_ID IN ('ComBondMarketInfo001',
'ComBondMarketInfo002',
'ComBondQuotationInfo001',
'ComBondQuotationInfo002',
'ComCoinFundQuotationInfo001',
'ComCoinFundQuotationInfo002',
'ComExchangeQuotationInfo001',
'ComExchangeQuotationInfo002',
'ComExternalOptions001',
'ComExternalOptions002',
'ComFundMarketInfo001',
'ComFundMarketInfo002',
'ComFxValuztionInfo001',
'ComFxValuztionInfo002',
'ComInterestRateQuotationInfo001',
'ComInterestRateQuotationInfo002',
'ComMarketCalendarInfo001',
'ComNetAssetQuotationInfo001',
'ComNetAssetQuotationInfo002',
'ComRateSwapInformation001',
'ComRateSwapInformation002',
'ComRateSwapValuationInfo001',
'ComRateSwapValuationInfo002',
'ComSharePrice001',
'ComSharePrice002',
'ComYieldCurveInfo001',
'ComYieldCurveInfo002');


insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComBondQuotationInfo001', '999999', 'VALUATION_SOURCE', 'CBC', null, null, 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComBondQuotationInfo001', '999999', 'VALUATION_SOURCE', 'CSD', null, null, 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComBondQuotationInfo002', '999999', 'VALUATION_SOURCE', 'CBC', null, null, 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComBondQuotationInfo002', '999999', 'VALUATION_SOURCE', 'CSD', null, null, 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComNetAssetQuotationInfo002', '999999', 'PROFIT_TYPE', '01', null, null, 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComNetAssetQuotationInfo002', '999999', 'PROFIT_TYPE', '03', null, null, 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '6M', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '9M', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '1Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '2Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '3Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '4Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '5Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '7Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '10Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '1M', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '3M', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '6M', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '9M', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '1Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '2Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '3Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '4Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '5Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '7Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'TERM_TYPE_IRS', '10Y', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_INT_TYPE', 'SP', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_INT_TYPE', 'MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_INT_TYPE', 'FS', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_INT_TYPE', 'FM', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_INT_TYPE', 'SP', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_INT_TYPE', 'MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_INT_TYPE', 'FS', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_INT_TYPE', 'FM', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_CODE_IRS', 'SHIBOR3M_F_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_CODE_IRS', 'SHIBORON_F_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_CODE_IRS', 'FR007_F_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_CODE_IRS', 'FDR007_F_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_CODE_IRS', 'SHIBOR3M_C_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_CODE_IRS', 'SHIBORON_C_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_CODE_IRS', 'FR007_C_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'CURVE_CODE_IRS', 'FDR007_C_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_CODE_IRS', 'SHIBOR3M_F_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_CODE_IRS', 'SHIBORON_F_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_CODE_IRS', 'FR007_F_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_CODE_IRS', 'FDR007_F_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_CODE_IRS', 'SHIBOR3M_C_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_CODE_IRS', 'SHIBORON_C_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_CODE_IRS', 'FR007_C_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'CURVE_CODE_IRS', 'FDR007_C_MA', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'IRS_CURVE_TYPE', '01', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'IRS_CURVE_TYPE', '01', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '1M', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'TERM_TYPE_IRS', '3M', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapValuationInfo002', '999999', 'VALUATION_SOURCE', 'BAK', null, null, 'system', null, '19-9月 -19 06.10.03.000000 下午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapValuationInfo002', '999999', 'VALUATION_SOURCE', 'CALYPSO', null, null, 'system', null, '19-9月 -19 06.10.03.000000 下午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapValuationInfo002', '999999', 'INPUT_TYPE', '01', null, null, 'system', null, '19-9月 -19 06.10.03.000000 下午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapValuationInfo002', '999999', 'INPUT_TYPE', '05', null, null, 'system', null, '19-9月 -19 06.10.03.000000 下午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapValuationInfo002', '999999', 'INPUT_TYPE', '02', null, null, 'system', null, '09-9月 -18 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation002', '999999', 'IRS_CURVE_TYPE', '02', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComRateSwapInformation001', '999999', 'IRS_CURVE_TYPE', '02', null, null, 'admin', null, '20-7月 -19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComBondQuotationInfo001', '999999', 'VALUATION_SOURCE', 'SCH', null, null, 'admin', null, '14-11月-19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ComBondQuotationInfo002', '999999', 'VALUATION_SOURCE', 'SCH', null, null, 'admin', null, '14-11月-19 12.00.00.000000 上午', 'admin', '21-4月 -21 12.00.00.000000 上午', null);



DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID IN ('ComBondMarketInfo001',
'ComBondMarketInfo002',
'ComBondQuotationInfo001',
'ComBondQuotationInfo002',
'ComCoinFundQuotationInfo001',
'ComCoinFundQuotationInfo002',
'ComExchangeQuotationInfo001',
'ComExchangeQuotationInfo002',
'ComExternalOptions001',
'ComExternalOptions002',
'ComFundMarketInfo001',
'ComFundMarketInfo002',
'ComFxValuztionInfo001',
'ComFxValuztionInfo002',
'ComInterestRateQuotationInfo001',
'ComInterestRateQuotationInfo002',
'ComMarketCalendarInfo001',
'ComNetAssetQuotationInfo001',
'ComNetAssetQuotationInfo002',
'ComRateSwapInformation001',
'ComRateSwapInformation002',
'ComRateSwapValuationInfo001',
'ComRateSwapValuationInfo002',
'ComSharePrice001',
'ComSharePrice002',
'ComYieldCurveInfo001',
'ComYieldCurveInfo002');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExternalOptions001', 'ComExternalOptions001_btn_import', '01', '01', null, '导入', 'system', null, '10-7月 -20 10.23.43.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComCoinFundQuotationInfo001', 'ComCoinFundQuotationInfo001_btn_add', '01', '01', null, '新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComCoinFundQuotationInfo001', 'ComCoinFundQuotationInfo001_btn_search', '01', '01', null, '查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComCoinFundQuotationInfo001', 'ComCoinFundQuotationInfo001_btn_reset', '01', '02', null, '重置', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComCoinFundQuotationInfo001', 'ComCoinFundQuotationInfo001_btn_export', '01', '01', null, '导出', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComCoinFundQuotationInfo001', 'ComCoinFundQuotationInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComCoinFundQuotationInfo001', 'ComCoinFundQuotationInfo001_btn_delete', '01', '01', null, '删除', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComCoinFundQuotationInfo002', 'ComCoinFundQuotationInfo002_btn_save', '01', '02', null, '保存', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComCoinFundQuotationInfo002', 'ComCoinFundQuotationInfo002_btn_submit', '01', '02', null, '提交', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComInterestRateQuotationInfo001', 'ComInterestRateQuotationInfo001_btn_add', '01', '01', null, '新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComInterestRateQuotationInfo001', 'ComInterestRateQuotationInfo001_btn_search', '01', '01', null, '查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComInterestRateQuotationInfo001', 'ComInterestRateQuotationInfo001_btn_reset', '01', '02', null, '重置', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComInterestRateQuotationInfo001', 'ComInterestRateQuotationInfo001_btn_export', '01', '01', null, '导出', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComInterestRateQuotationInfo001', 'ComInterestRateQuotationInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComInterestRateQuotationInfo001', 'ComInterestRateQuotationInfo001_btn_delete', '01', '01', null, '删除', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComInterestRateQuotationInfo002', 'ComInterestRateQuotationInfo002_btn_save', '01', '02', null, '保存', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComInterestRateQuotationInfo002', 'ComInterestRateQuotationInfo002_btn_submit', '01', '02', null, '提交', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComMarketCalendarInfo001', 'ComMarketCalendarInfo001_btn_search', '01', '01', null, '查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComMarketCalendarInfo001', 'ComMarketCalendarInfo001_btn_reset', '01', '02', null, '重置', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComMarketCalendarInfo001', 'ComMarketCalendarInfo001_btn_export', '01', '01', null, '导出', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComMarketCalendarInfo001', 'ComMarketCalendarInfo001_btn_import', '01', '01', null, '导入', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComMarketCalendarInfo001', 'ComMarketCalendarInfo001_btn_sethday', '01', '01', null, '设为节假日', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComMarketCalendarInfo001', 'ComMarketCalendarInfo001_btn_setwday', '01', '01', null, '设为工作日', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExchangeQuotationInfo001', 'ComExchangeQuotationInfo001_btn_add', '01', '01', null, '新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExchangeQuotationInfo001', 'ComExchangeQuotationInfo001_btn_search', '01', '01', null, '查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExchangeQuotationInfo001', 'ComExchangeQuotationInfo001_btn_reset', '01', '02', null, '重置', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExchangeQuotationInfo001', 'ComExchangeQuotationInfo001_btn_export', '01', '01', null, '导出', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExchangeQuotationInfo001', 'ComExchangeQuotationInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExchangeQuotationInfo001', 'ComExchangeQuotationInfo001_btn_delete', '01', '01', null, '删除', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExchangeQuotationInfo002', 'ComExchangeQuotationInfo002_btn_save', '01', '02', null, '保存', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComYieldCurveInfo001', 'ComYieldCurveInfo001_btn_add', '01', '01', null, '新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComYieldCurveInfo001', 'ComYieldCurveInfo001_btn_search', '01', '01', null, '查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComYieldCurveInfo001', 'ComYieldCurveInfo001_btn_reset', '01', '02', null, '重置', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComYieldCurveInfo001', 'ComYieldCurveInfo001_btn_export', '01', '01', null, '导出', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComYieldCurveInfo001', 'ComYieldCurveInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComYieldCurveInfo001', 'ComYieldCurveInfo001_btn_delete', '01', '01', null, '删除', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComYieldCurveInfo002', 'ComYieldCurveInfo002_btn_save', '01', '02', null, '保存', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComYieldCurveInfo002', 'ComYieldCurveInfo002_btn_submit', '01', '02', null, '提交', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExchangeQuotationInfo002', 'ComExchangeQuotationInfo002_btn_submit', '01', '02', null, '提交', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondQuotationInfo001', 'ComBondQuotationInfo001_btn_add', '01', '01', null, '新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondQuotationInfo001', 'ComBondQuotationInfo001_btn_search', '01', '01', null, '查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondQuotationInfo001', 'ComBondQuotationInfo001_btn_reset', '01', '02', null, '重置', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondQuotationInfo001', 'ComBondQuotationInfo001_btn_export', '01', '01', null, '导出', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondQuotationInfo001', 'ComBondQuotationInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondQuotationInfo001', 'ComBondQuotationInfo001_btn_delete', '01', '01', null, '删除', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondQuotationInfo002', 'ComBondQuotationInfo002_btn_save', '01', '02', null, '保存', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondQuotationInfo002', 'ComBondQuotationInfo002_btn_submit', '01', '02', null, '提交', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExternalOptions001', 'ComExternalOptions001_btn_export', '01', '01', null, '导出', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExternalOptions001', 'ComExternalOptions001_btn_search', '01', '01', null, '查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComCoinFundQuotationInfo001', 'ComCoinFundQuotationInfo001_btn_import', '01', '01', null, '导入', 'system', null, '10-7月 -20 10.23.42.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComNetAssetQuotationInfo001', 'ComNetAssetQuotationInfo001_btn_import', '01', '01', null, '导入', 'system', null, '10-7月 -20 10.23.42.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComSharePrice001', 'ComSharePrice001_btn_import', '01', '01', null, '导入', 'system', null, '10-7月 -20 10.23.42.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComFundMarketInfo001', 'ComFundMarketInfo001_btn_import', '01', '01', null, '导入', 'system', null, '10-7月 -20 10.23.42.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondMarketInfo001', 'ComBondMarketInfo001_btn_import', '01', '01', null, '导入', 'system', null, '10-7月 -20 10.23.42.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondQuotationInfo001', 'ComBondQuotationInfo001_btn_import', '01', '01', null, '导入', 'system', null, '10-7月 -20 10.04.24.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComInterestRateQuotationInfo001', 'ComInterestRateQuotationInfo001_btn_import', '01', '01', null, '导入', 'system', null, '10-7月 -20 10.23.42.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExchangeQuotationInfo001', 'ComExchangeQuotationInfo001_btn_import', '01', '01', null, '导入', 'system', null, '10-7月 -20 10.23.43.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComFxValuztionInfo001', 'ComFxValuztionInfo001_btn_import', '01', '01', null, '导入', 'system', null, '10-7月 -20 10.23.43.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExternalOptions001', 'ComExternalOptions001_btn_delete', '01', '01', null, '删除', 'system', null, '10-7月 -20 10.23.43.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapValuationInfo001', 'ComRateSwapValuationInfo001_btn_add', '01', '01', null, '新建', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapValuationInfo001', 'ComRateSwapValuationInfo001_btn_delete', '01', '01', null, '删除', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapValuationInfo001', 'ComRateSwapValuationInfo001_btn_export', '01', '01', null, '导出', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapValuationInfo001', 'ComRateSwapValuationInfo001_btn_search', '01', '01', null, '查询', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondMarketInfo001', 'ComBondMarketInfo001_btn_search', '01', '01', null, '查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondMarketInfo001', 'ComBondMarketInfo001_btn_export', '01', '01', null, '导出', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondMarketInfo001', 'ComBondMarketInfo001_btn_reset', '01', '02', null, '重置', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondMarketInfo001', 'ComBondMarketInfo001_btn_delete', '01', '01', null, '删除', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComBondMarketInfo001', 'ComBondMarketInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComSharePrice001', 'ComSharePrice001_btn_add', '01', '01', null, '新建', 'system', null, '20-6月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComSharePrice001', 'ComSharePrice001_btn_export', '01', '01', null, '导出', 'system', null, '20-6月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComSharePrice001', 'ComSharePrice001_btn_search', '01', '01', null, '查询', 'system', null, '20-6月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComSharePrice001', 'ComSharePrice001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '20-6月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComSharePrice001', 'ComSharePrice001_btn_delete', '01', '01', null, '删除', 'system', null, '20-6月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapInformation001', 'ComRateSwapInformation001_btn_del', '01', '01', null, '删除', 'system', null, '10-7月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapInformation001', 'ComRateSwapInformation001_btn_edit', '01', '01', null, '编辑', 'system', null, '10-7月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapInformation001', 'ComRateSwapInformation001_btn_export', '01', '01', null, '导出', 'system', null, '11-7月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapInformation001', 'ComRateSwapInformation001_btn_reset', '01', '02', null, '重置', 'system', null, '11-7月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapInformation001', 'ComRateSwapInformation001_btn_add', '01', '01', null, '新建', 'system', null, '11-7月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComFxValuztionInfo001', 'ComFxValuztionInfo001_btn_add', '01', '01', null, '新建', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComFxValuztionInfo001', 'ComFxValuztionInfo001_btn_delete', '01', '01', null, '删除', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComFxValuztionInfo001', 'ComFxValuztionInfo001_btn_export', '01', '01', null, '导出', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComFxValuztionInfo001', 'ComFxValuztionInfo001_btn_search', '01', '01', null, '查询', 'system', null, '03-2月 -18 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComFundMarketInfo001', 'ComFundMarketInfo001_btn_search', '01', '01', null, '查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComFundMarketInfo001', 'ComFundMarketInfo001_btn_export', '01', '01', null, '导出', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComFundMarketInfo001', 'ComFundMarketInfo001_btn_reset', '01', '02', null, '重置', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComNetAssetQuotationInfo001', 'ComNetAssetQuotationInfo001_btn_add', '01', '01', null, '新建', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComNetAssetQuotationInfo001', 'ComNetAssetQuotationInfo001_btn_search', '01', '01', null, '查询', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComNetAssetQuotationInfo001', 'ComNetAssetQuotationInfo001_btn_reset', '01', '02', null, '重置', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComNetAssetQuotationInfo001', 'ComNetAssetQuotationInfo001_btn_export', '01', '01', null, '导出', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComNetAssetQuotationInfo001', 'ComNetAssetQuotationInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComNetAssetQuotationInfo001', 'ComNetAssetQuotationInfo001_btn_delete', '01', '01', null, '删除', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComNetAssetQuotationInfo002', 'ComNetAssetQuotationInfo002_btn_save', '01', '02', null, '保存', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComNetAssetQuotationInfo002', 'ComNetAssetQuotationInfo002_btn_submit', '01', '02', null, '提交', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapInformation001', 'ComRateSwapInformation001_btn_search', '01', '01', null, '查询', 'system', null, '24-7月 -20 12.22.34.000000 下午', 'system', '21-4月 -21 12.00.00.000000 上午', 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComRateSwapValuationInfo001', 'ComRateSwapValuationInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '24-7月 -20 01.37.17.000000 下午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComFxValuztionInfo001', 'ComFxValuztionInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '24-7月 -20 01.43.12.000000 下午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExternalOptions001', 'ComExternalOptions001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, '24-7月 -20 01.56.04.000000 下午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ComExternalOptions001', 'ComExternalOptions001_btn_add', '01', '01', null, '新建', 'system', null, '24-7月 -20 01.57.47.000000 下午', 'system', '21-4月 -21 12.00.00.000000 上午', 'C');


DELETE from sys_wfl_business_module WHERE PAGE_ID IN ('ComBondMarketInfo001',
'ComBondMarketInfo002',
'ComBondQuotationInfo001',
'ComBondQuotationInfo002',
'ComCoinFundQuotationInfo001',
'ComCoinFundQuotationInfo002',
'ComExchangeQuotationInfo001',
'ComExchangeQuotationInfo002',
'ComExternalOptions001',
'ComExternalOptions002',
'ComFundMarketInfo001',
'ComFundMarketInfo002',
'ComFxValuztionInfo001',
'ComFxValuztionInfo002',
'ComInterestRateQuotationInfo001',
'ComInterestRateQuotationInfo002',
'ComMarketCalendarInfo001',
'ComNetAssetQuotationInfo001',
'ComNetAssetQuotationInfo002',
'ComRateSwapInformation001',
'ComRateSwapInformation002',
'ComRateSwapValuationInfo001',
'ComRateSwapValuationInfo002',
'ComSharePrice001',
'ComSharePrice002',
'ComYieldCurveInfo001',
'ComYieldCurveInfo002');


insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComBondMarketInfo002', 'ComBondMarketInfo001_btn_delete', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComBondMarketInfo002', 'ComBondMarketInfo002_btn_save', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComBondMarketInfo002', 'ComBondMarketInfo002_btn_submit', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComFundMarketInfo002', 'ComFundMarketInfo002_btn_save', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComFundMarketInfo002', 'ComFundMarketInfo002_btn_submit', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComFundMarketInfo002', 'ComFundMarketInfo001_btn_delete', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComYieldCurveInfo002', 'ComYieldCurveInfo001_btn_delete', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComYieldCurveInfo002', 'ComYieldCurveInfo002_btn_save', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComYieldCurveInfo002', 'ComYieldCurveInfo002_btn_submit', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComNetAssetQuotationInfo002', 'ComNetAssetQuotationInfo001_btn_delete', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComNetAssetQuotationInfo002', 'ComNetAssetQuotationInfo002_btn_save', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComNetAssetQuotationInfo002', 'ComNetAssetQuotationInfo002_btn_submit', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComExchangeQuotationInfo002', 'ComExchangeQuotationInfo001_btn_delete', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComExchangeQuotationInfo002', 'ComExchangeQuotationInfo002_btn_save', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComExchangeQuotationInfo002', 'ComExchangeQuotationInfo002_btn_submit', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComCoinFundQuotationInfo002', 'ComCoinFundQuotationInfo001_btn_delete', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComCoinFundQuotationInfo002', 'ComCoinFundQuotationInfo002_btn_save', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComCoinFundQuotationInfo002', 'ComCoinFundQuotationInfo002_btn_submit', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComBondQuotationInfo002', 'ComBondQuotationInfo002_btn_save', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComBondQuotationInfo002', 'ComBondQuotationInfo002_btn_submit', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComBondQuotationInfo002', 'ComBondQuotationInfo001_btn_delete', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComInterestRateQuotationInfo002', 'ComInterestRateQuotationInfo002_btn_save', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComInterestRateQuotationInfo002', 'ComInterestRateQuotationInfo002_btn_submit', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComInterestRateQuotationInfo002', 'ComInterestRateQuotationInfo001_btn_delete', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComRateSwapValuationInfo002', 'ComRateSwapValuationInfo001_btn_delete', 'oneApproval', 'system', null, '16-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComRateSwapValuationInfo002', 'ComRateSwapValuationInfo002_btn_save', 'oneApproval', 'system', null, '17-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComRateSwapValuationInfo002', 'ComRateSwapValuationInfo002_btn_submit', 'oneApproval', 'system', null, '18-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComFxValuztionInfo002', 'ComFxValuztionInfo001_btn_delete', 'oneApproval', 'system', null, '16-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComFxValuztionInfo002', 'ComFxValuztionInfo002_btn_save', 'oneApproval', 'system', null, '17-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComFxValuztionInfo002', 'ComFxValuztionInfo002_btn_submit', 'oneApproval', 'system', null, '18-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComSharePrice002', 'ComSharePrice002_btn_save', 'oneApproval', 'system', '0001', '27-6月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComSharePrice002', 'ComSharePrice002_btn_submit', 'oneApproval', 'system', '0001', '27-6月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComSharePrice002', 'ComSharePrice001_btn_delete', 'oneApproval', 'system', '0001', '27-6月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComRateSwapInformation002', 'ComRateSwapInformation002_btn_save', 'oneApproval', 'system', '0001', '22-7月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComRateSwapInformation002', 'ComRateSwapInformation002_btn_submit', 'oneApproval', 'system', '0001', '22-7月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComRateSwapInformation002', 'ComRateSwapInformation001_btn_del', 'oneApproval', 'system', '0001', '22-7月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComExternalOptions002', 'ComExternalOptions001_btn_delete', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComExternalOptions002', 'ComExternalOptions002_btn_submit', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('ComExternalOptions002', 'ComExternalOptions002_btn_save', 'oneApproval', 'system', null, '24-5月 -19 12.00.00.000000 上午', 'system', '21-4月 -21 12.00.00.000000 上午', 'ALL', '02');

