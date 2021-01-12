var assetColList1 = [ //资产要素标题栏
	{rowspan: 2, type:'checkbox'},
	{align: 'center', title: '公共信息（必填）', colspan: 5},
	{align: 'center', title: '本行/他行存款||大额存单', colspan: 9},
	{align: 'center', title: '债券类资产/理财直接融资工具/同业存单', colspan: 11},
	{align: 'center', title: '拆放同业及买入返售/同业拆入及卖出回购', colspan: 7},
	{align: 'center', title: '非标准化债权类资产/新增可投资资产', colspan: 36},
	{align: 'center', title: '票据类/信用证类资产', colspan: 12},
	{align: 'center', title: '权益类资产', colspan: 9},
	{align: 'center', title: '金融衍生品', colspan: 3},
	{align: 'center', title: 'QDII债券资产', colspan: 14},
	{align: 'center', title: 'QDII拆出/逆回购资产', colspan: 6},
	{align: 'center', title: 'QDII股票/基金类资产', colspan: 5},
	{align: 'center', title: 'QDII结构性票据类资产', colspan: 17},
	{align: 'center', title: '资产管理产品', colspan: 23},
	{align: 'center', title: '贵金属/商品', colspan: 5},
	{align: 'center', title: '非标准化债权类/权益类/代客境外理财投资QDII/新增可投资资产', colspan: 23},
	{align: 'center', title: '另类资产', colspan: 6},
	{align: 'center', title: '公募基金/私募基金', colspan: 14},
	{align: 'center', title: '委外投资——协议方式', colspan: 20},
	{align: 'center', title: '其他（负债）类资产', colspan: 5},
	// 附加
	{rowspan: 2, field: 'REMARK', align: 'center', title: '备注', width: 150},
];

var assetColList2 = [
	// 公共信息（必填）
	{field: 'ASSET_CODE', align: 'center', title: '*行内资产/负债编码', width: 150},
	{field: 'ISSUE_PARTY_ID', align: 'center', title: '*发行机构代码', width: 150},
	{field: 'ASSET_TYPE2', align: 'center', title: '*资产/负债类别', width: 150},
	{field: 'TRADE_MARKET', align: 'center', title: '*交易流通场所', width: 150},
	{field: 'CCY', align: 'center', title: '*币种', width: 150},
	// 本行/他行存款||大额存单
	{field: 'BANK_NAME01', align: 'center', title: '资金存入银行', width: 150},
	{field: 'AMT01', align: 'center', title: '*存款金额', width: 150},
	{field: 'VDATE01', align: 'center', title: '*起息日', width: 150},
	{field: 'MDATE01', align: 'center', title: '*到期日', width: 150},
	{field: 'ASSET_YIELD01', align: 'center', title: '*年利率%', width: 150},
	{field: 'BASIS01', align: 'center', title: '*计息基础', width: 150},
	{field: 'IS_STRUCT01', align: 'center', title: '*是否为结构性存款', width: 150},
	{field: 'TARGET_TYPE01', align: 'center', title: '结构性存款挂钩标的类别', width: 150},
	{field: 'TARGET01', align: 'center', title: '结构性存款挂钩标的', width: 150},
	// 债券类资产/理财直接融资工具/同业存单
	{field: 'ASSET_ID03', align: 'center', title: '*代码', width: 150},
	{field: 'ASSET_NAME03', align: 'center', title: '*名称', width: 150},
	{field: 'CHB_SECTYPE03', align: 'center', title: '具体类别', width: 150},
	{field: 'ISSUE_MODE03', align: 'center', title: '*发行方式', width: 150},
	{field: 'SEC_MAIN_GRADE03', align: 'center', title: '*主体评级', width: 150},
	{field: 'PARTY_TYPE103', align: 'center', title: '*发行机构类型（按规模划分）', width: 150},
	{field: 'PARTY_TYPE303', align: 'center', title: '*发行机构类型（按技术领域划分）', width: 150},
	{field: 'PARTY_TYPE203', align: 'center', title: '*发行机构类型（按经济类型划分）', width: 150},
	{field: 'INDUSTRY_LEVEL203', align: 'center', title: '*发行机构所属行业', width: 150},
	{field: 'REGIST_ORG03', align: 'center', title: '*登记托管机构', width: 150},
	{field: 'REGISTORG_COMMENT03', align: 'center', title: '登记托管机构说明', width: 150},
	// 拆放同业及买入返售/同业拆入及卖出回购
	{field: 'VDATE02', align: 'center', title: '*起息日', width: 150},
	{field: 'MDATE02', align: 'center', title: '*到期日', width: 150},
	{field: 'RIVAL_PARTY02', align: 'center', title: '*对手方', width: 150},
	{field: 'ASSET_YIELD02', align: 'center', title: '*年利率%', width: 150},
	{field: 'BASIS02', align: 'center', title: '*计息基础', width: 150},
	{field: 'TARGET_TYPE102', align: 'center', title: '回购标的类别', width: 150},
	{field: 'TARGET_AMT02', align: 'center', title: '回购标的金额', width: 150},
	// 非标准化债权类资产/新增可投资资产
	{field: 'PROFIT_TYPE04', align: 'center', title: '收/受益权类型', width: 150},
	{field: 'IS_RESALE04', align: 'center', title: '*是否属于买入返售', width: 150},
	{field: 'ASSET_NAME04', align: 'center', title: '*名称', width: 150},
	{field: 'AMT04', align: 'center', title: '*金额', width: 150},
	{field: 'VDATE04', align: 'center', title: '*起息日', width: 150},
	{field: 'MDATE04', align: 'center', title: '*到息日', width: 150},
	{field: 'RATE_FLAG04', align: 'center', title: '*是否有预期收益率', width: 150},
	{field: 'ASSET_YIELD04', align: 'center', title: '项目收益率(利率)%', width: 150},
	{field: 'INTPAY_CYCLE04', align: 'center', title: '*付息频率（个月/次）', width: 150},
	{field: 'REPAY_CMT04', align: 'center', title: '*还本付息情况说明', width: 150},
	{field: 'BASIS04', align: 'center', title: '*计息基础', width: 150},
	{field: 'BASIS_TYPE04', align: 'center', title: '基准利率种类', width: 150},
	{field: 'FLOAT_FACTOR04', align: 'center', title: '浮动因子（%）', width: 150},
	{field: 'SPREAD_RATE04', align: 'center', title: '利差(BP)', width: 150},
	{field: 'FINANCIE04', align: 'center', title: '*融资人', width: 150},
	{field: 'FCR_GRADE_IN04', align: 'center', title: '*融资人内部信用评级', width: 150},
	{field: 'FCR_GRADE_OUT04', align: 'center', title: '*外部评级机构名称及对融资人评级结果', width: 150},
	{field: 'FINANCIER_TYPE104', align: 'center', title: '*融资人类型（按规模划分）', width: 150},
	{field: 'FINANCIER_TYPE204', align: 'center', title: '*融资人类型（按技术领域划分）', width: 150},
	{field: 'FINANCIER_TYPE304', align: 'center', title: '*融资人类型（按经济类型划分）', width: 150},
	{field: 'FINANCE_PROJ04', align: 'center', title: '*融资项目', width: 150},
	{field: 'INDUSTRY_LEVEL2_TYPE04', align: 'center', title: '*融资人所属行业', width: 150},
	{field: 'INDUSTRY_FLAG04', align: 'center', title: '*项目是否属于重点监控行业和领域', width: 150},
	{field: 'MONIT_TYPE04', align: 'center', title: '重点监控行业和领域类别', width: 150},
	{field: 'MON_TYPE_COMM04', align: 'center', title: '重点监控行业和领域类别说明', width: 150},
	{field: 'GUA_TYPE04', align: 'center', title: '担保方式', width: 150},
	{field: 'GUA_TYPE_REMARK04', align: 'center', title: '担保情况说明', width: 150},
	{field: 'FINANCIER_GRADE04', align: 'center', title: '*融资人主体评级', width: 150},
	{field: 'IN_GRADE04', align: 'center', title: '*资产内部评级', width: 150},
	{field: 'OUT_GRADE04', align: 'center', title: '*资产外部评级', width: 150},
	{field: 'CHOSE04', align: 'center', title: '*选择权', width: 150},
	{field: 'CONDITION_CMM04', align: 'center', title: '*行权条件说明', width: 150},
	{field: 'FIN_AREA04', align: 'center', title: '*融资人所属地区', width: 150},
	{field: 'FIN_ORG_CODE04', align: 'center', title: '*融资人组织机构（社会信用）代码', width: 150},
	{field: 'TOTAL_FEE_RATE04', align: 'center', title: '*融资总费率%', width: 150},
	{field: 'PRO_INDUSTRY_LEVEL204', align: 'center', title: '*融资项目所属行业', width: 150},
	// 票据类/信用证类资产
	{field: 'IS_RIGHT05', align: 'center', title: '*是否为收/受益权', width: 150},
	{field: 'IS_RESALE05', align: 'center', title: '*是否属于买入返售', width: 150},
	{field: 'NOTSTAND_SEC_TYPE05', align: 'center', title: '*非标准化债券类资产类型', width: 150},
	{field: 'NOTSTAND_SEC_NUM05', align: 'center', title: '*非标准化债券类资产数量', width: 150},
	{field: 'AMT05', align: 'center', title: '*合计金额', width: 150},
	{field: 'REMAIN_TERM05', align: 'center', title: '*加权剩余期限（天）', width: 150},
	{field: 'MAX_REMAIN_TERM05', align: 'center', title: '*最长剩余期限（天）', width: 150},
	{field: 'MIN_REMAIN_TERM05', align: 'center', title: '*最短剩余期限（天）', width: 150},
	{field: 'VDATE05', align: 'center', title: '*起息日', width: 150},
	{field: 'MDATE05', align: 'center', title: '*到期日', width: 150},
	{field: 'INDUSTRY_SHARES205', align: 'center', title: '*行业', width: 150},
	{field: 'ASSET_YIELD05', align: 'center', title: '*贴现利率（%）', width: 150},
	// 权益类资产
	{field: 'ASSET_ID06', align: 'center', title: '股票代码', width: 150},
	{field: 'ASSET_NAME06', align: 'center', title: '*股票/企业名称', width: 150},
	{field: 'SHARES_TYPE06', align: 'center', title: '股票类型', width: 150},
	{field: 'INDUSTRY_SHARES206', align: 'center', title: '*行业', width: 150},
	{field: 'INVEST_STAGE06', align: 'center', title: '投资阶段', width: 150},
	{field: 'SHARES_TYPE106', align: 'center', title: '*企业类型（按规模划分）', width: 150},
	{field: 'SHARES_TYPE306', align: 'center', title: '*企业类型（按技术领域划分）', width: 150},
	{field: 'SHARES_TYPE206', align: 'center', title: '*企业类型（按经济类型划分）', width: 150},
	{field: 'IS_PLEDGE06', align: 'center', title: '*是否为质押融资', width: 150},
	// 金融衍生品
	{field: 'ASSET_NAME07', align: 'center', title: '*名称', width: 150},
	{field: 'NOM_CAPI07', align: 'center', title: '*名义本金', width: 150},
	{field: 'TARGET_TYPE307', align: 'center', title: '*标的类别', width: 150},
	// QDII债券资产
	{field: 'COUNTRY_AREA08', align: 'center', title: '*所属国家或地区', width: 150},
	{field: 'ASSET_NAME08', align: 'center', title: '*债券名称', width: 150},
	{field: 'ASSET_ID08', align: 'center', title: '*债券代码', width: 150},
	{field: 'ISSUE_PARTY_NAME08', align: 'center', title: '*发行机构', width: 150},
	{field: 'INDUSTRY_LEVEL208', align: 'center', title: '*发行机构所属行业', width: 150},
	{field: 'VDATE08', align: 'center', title: '*起息日', width: 150},
	{field: 'MDATE08', align: 'center', title: '*到息日', width: 150},
	{field: 'TERM_MONTH08', align: 'center', title: '*期限（月）', width: 150},
	{field: 'PARTY_GRADE08', align: 'center', title: '*发行机构主体信用评级', width: 150},
	{field: 'SEC_CREDIT_GRADE_TYPE08', align: 'center', title: '*债券信用评级', width: 150},
	{field: 'ASSET_YIELD08', align: 'center', title: '*票面利率%', width: 150},
	{field: 'INTPAY_CYCLE08', align: 'center', title: '*付息频率（个月/次）', width: 150},
	{field: 'GUA_TYPE_REMARK08', align: 'center', title: '*担保情况说明', width: 150},
	{field: 'SPECIAL_CMT08', align: 'center', title: '*是否含权等特殊条款情况说明', width: 150},
	// QDII拆出/逆回购资产
	{field: 'COUNTRY_AREA09', align: 'center', title: '*所属国家或地区', width: 150},
	{field: 'VDATE09', align: 'center', title: '*起息日', width: 150},
	{field: 'MDATE09', align: 'center', title: '*到息日', width: 150},
	{field: 'RIVAL_PARTY09', align: 'center', title: '*对手方', width: 150},
	{field: 'ASSET_YIELD09', align: 'center', title: '*年利率%', width: 150},
	{field: 'BASIS09', align: 'center', title: '*计息基础', width: 150},
	// QDII股票/基金类资产
	{field: 'COUNTRY_AREA10', align: 'center', title: '*所属国家或地区', width: 150},
	{field: 'ASSET_ID10', align: 'center', title: '*股票/基金代码', width: 150},
	{field: 'ASSET_NAME10', align: 'center', title: '*股票/基金名称', width: 150},
	{field: 'ISSUE_PARTY_NAME10', align: 'center', title: '*发行机构', width: 150},
	{field: 'INDUSTRY_SHARES210', align: 'center', title: '*行业', width: 150},
	// QDII结构性票据类资产
	{field: 'COUNTRY_AREA11', align: 'center', title: '*所属国家或地区', width: 150},
	{field: 'CONTRACT_NAME11', align: 'center', title: '*合约名称', width: 150},
	{field: 'VDATE11', align: 'center', title: '*起息日', width: 150},
	{field: 'MDATE11', align: 'center', title: '*到息日', width: 150},
	{field: 'ASSET_YIELD11', align: 'center', title: '票面利率%', width: 150},
	{field: 'INTPAY_CYCLE11', align: 'center', title: '付息频率（个月/次）', width: 150},
	{field: 'INCOME_RATIO11', align: 'center', title: '*固定收益部分所占比例(%)', width: 150},
	{field: 'DER_INSTR_RATIO11', align: 'center', title: '*衍生金融工具所占比例(%)', width: 150},
	{field: 'DER_INSTR_INV_TYPE11', align: 'center', title: '*衍生金融工具具体投资方式', width: 150},
	{field: 'DER_INSTR_TARGET11', align: 'center', title: '*衍生金融工具挂钩的标的资产', width: 150},
	{field: 'ACCT_MODE11', align: 'center', title: '*利息结算方式', width: 150},
	{field: 'SPECIAL_CMT111', align: 'center', title: '*含权情况说明', width: 150},
	{field: 'MAX_YIELD11', align: 'center', title: '*结构性票据最高收益率%', width: 150},
	{field: 'MIN_YIELD11', align: 'center', title: '结构性票据最低收益率%', width: 150},
	{field: 'BASIC_PRICE11', align: 'center', title: '挂钩标的资产基准价格', width: 150},
	{field: 'REG_DATE_PRICE11', align: 'center', title: '*挂钩标的资产登记日价格', width: 150},
	{field: 'TRADE_FEE11', align: 'center', title: '*交易费', width: 150},
	// 资产管理产品
	{field: 'ASSET_NAME12', align: 'center', title: '*资管计划名称', width: 150},
	{field: 'PARTY_CODE12', align: 'center', title: '*资管计划发起人机构编码', width: 150},
	{field: 'CHECK_CODE12', align: 'center', title: '资管计划登记编码', width: 150},
	{field: 'MANAGER_ID12', align: 'center', title: '*管理人', width: 150},
	{field: 'TRUSTEE_ID12', align: 'center', title: '*托管人', width: 150},
	{field: 'AMT12', align: 'center', title: '*金额', width: 150},
	{field: 'TRU_ACT_TYPE12', align: 'center', title: '*资金实际投向', width: 150},
	{field: 'INVEST_TYPE12', align: 'center', title: '*资金运用方式', width: 150},
	{field: 'CAP_USE_INDU_LEVEL212', align: 'center', title: '*资金运用行业', width: 150},
	{field: 'PLAN_SDATE12', align: 'center', title: '*资管计划成立日期', width: 150},
	{field: 'PLAN_EDATE12', align: 'center', title: '*资管计划终止日期', width: 150},
	{field: 'PRO_PROPERTY12', align: 'center', title: '*资管计划属性', width: 150},
	{field: 'RATE_FLAG12', align: 'center', title: '*是否有预期收益率', width: 150},
	{field: 'MAX_RATE12', align: 'center', title: '预期最高收益率%', width: 150},
	{field: 'MIN_RATE12', align: 'center', title: '预期最低收益率%', width: 150},
	{field: 'BUY_STRUCT12', align: 'center', title: '*购买结构', width: 150},
	{field: 'MANAGE_TYPE12', align: 'center', title: '*管理方式', width: 150},
	{field: 'IS_CTRL_BOTTOM12', align: 'center', title: '*是否掌控计划底层资产详情', width: 150},
	{field: 'MGR_FEE_RATE12', align: 'center', title: '*管理费率%', width: 150},
	{field: 'TRU_FEE_RATE12', align: 'center', title: '*托管费率%', width: 150},
	{field: 'TRADE_FEE_RATE12', align: 'center', title: '*交易相关合计费率%', width: 150},
	{field: 'AGENCY_FEE_RATE12', align: 'center', title: '*中介服务机构合计费率%', width: 150},
	{field: 'OTHER_FEE_RATE12', align: 'center', title: '*其他合计费率%', width: 150},
	// 贵金属/商品
	{field: 'COUNTRY_AREA13', align: 'center', title: '*所属国家或地区', width: 150},
	{field: 'ASSET_NAME13', align: 'center', title: '*名称', width: 150},
	{field: 'TERM_DATE13', align: 'center', title: '期限（天）', width: 150},
	{field: 'ASSET_VALUE13', align: 'center', title: '*资产价值', width: 150},
	{field: 'ASSET_YIELD13', align: 'center', title: '资产收益率%', width: 150},
	// 非标准化债权类/权益类/代客境外理财投资QDII/新增可投资资产
	{field: 'ASSET_NAME14', align: 'center', title: '*名称', width: 150},
	{field: 'IN_ASSET_TYPE14', align: 'center', title: '*行内资产类别', width: 150},
	{field: 'IN_ASSET_TYPE_CMM14', align: 'center', title: '行内资产类别说明', width: 150},
	{field: 'ASSET_VALUE14', align: 'center', title: '*资产价值', width: 150},
	{field: 'VDATE14', align: 'center', title: '*起息日', width: 150},
	{field: 'MDATE14', align: 'center', title: '*到息日', width: 150},
	{field: 'COUNTRY_AREA14', align: 'center', title: '*所属国家或地区', width: 150},
	{field: 'RATE_FLAG14', align: 'center', title: '*是否有预期收益率', width: 150},
	{field: 'ASSET_YIELD14', align: 'center', title: '项目收益率（利率）%', width: 150},
	{field: 'INTPAY_CYCLE14', align: 'center', title: '*付息频率（个月/次）', width: 150},
	{field: 'FINANCIE14', align: 'center', title: '*融资人', width: 150},
	{field: 'FIN_ORG_CODE14', align: 'center', title: '*融资人组织机构（社会信用）代码', width: 150},
	{field: 'FCR_GRADE_OUT14', align: 'center', title: '外部评级机构名称及对融资人评级结果', width: 150},
	{field: 'FINANCIER_TYPE114', align: 'center', title: '*融资人类型（按规模划分）', width: 150},
	{field: 'FINANCIER_TYPE214', align: 'center', title: '*融资人类型（按技术领域划分）', width: 150},
	{field: 'FINANCIER_TYPE314', align: 'center', title: '*融资人类型（按经济类型划分）', width: 150},
	{field: 'FINANCE_PROJ14', align: 'center', title: '*融资项目', width: 150},
	{field: 'INDUSTRY_LEVEL2_TYPE14', align: 'center', title: '*融资人所属行业', width: 150},
	{field: 'PRO_INDUSTRY_LEVEL214', align: 'center', title: '*融资项目所属行业', width: 150},
	{field: 'INDUSTRY_FLAG14', align: 'center', title: '*项目是否属于重点监控行业和领域', width: 150},
	{field: 'MONIT_TYPE14', align: 'center', title: '重点监控行业和领域类别', width: 150},
	{field: 'MON_TYPE_COMM14', align: 'center', title: '重点监控行业和领域类别说明', width: 150},
	{field: 'OUT_GRADE14', align: 'center', title: '对应资产外部评级', width: 150},
	// 另类资产
	{field: 'COUNTRY_AREA18', align: 'center', title: '*所属国家或地区', width: 150},
	{field: 'ASSET_NAME18', align: 'center', title: '*名称', width: 150},
	{field: 'VDATE18', align: 'center', title: '*起息日', width: 150},
	{field: 'MDATE18', align: 'center', title: '*到息日', width: 150},
	{field: 'ASSET_VALUE18', align: 'center', title: '*资产价值', width: 150},
	{field: 'ASSET_YIELD18', align: 'center', title: '资产收益率%', width: 150},
	// 公募基金/私募基金
	{field: 'ASSET_ID15', align: 'center', title: '*基金代码', width: 150},
	{field: 'ASSET_NAME15', align: 'center', title: '*基金名称', width: 150},
	{field: 'INDUSTRY_SHARES215', align: 'center', title: '*行业', width: 150},
	{field: 'REGIST_ORG115', align: 'center', title: '*登记备案机构', width: 150},
	{field: 'IS_GOVERN_FUND15', align: 'center', title: '是否属于政府投资基金', width: 150},
	{field: 'GOVERN_INVEST_FUND15', align: 'center', title: '政府投资基金投向', width: 150},
	{field: 'FUND_ENPE_NAME15', align: 'center', title: '基金公司名称', width: 150},
	{field: 'MNG_ORG_NAME15', align: 'center', title: '*基金管理机构名称', width: 150},
	{field: 'TRU_ORG_NAME15', align: 'center', title: '*基金托管机构名称', width: 150},
	{field: 'INVEST_STAGE15', align: 'center', title: '投资阶段', width: 150},
	{field: 'SHARES_TYPE115', align: 'center', title: '投资企业类型（按规模划分）', width: 150},
	{field: 'SHARES_TYPE315', align: 'center', title: '投资企业类型（按技术领域划分）', width: 150},
	{field: 'SHARES_TYPE215', align: 'center', title: '投资企业类型（按经济类型划分）', width: 150},
	{field: 'FUND_INVEST_ASSET15', align: 'center', title: '*基金投资资产', width: 150},
	// 委外投资——协议方式
	{field: 'ASSET_NAME16', align: 'center', title: '委外投资协议名称', width: 150},
	{field: 'ASSET_ID16', align: 'center', title: '委外投资协议编号', width: 150},
	{field: 'TRUSTEE16', align: 'center', title: '受托人', width: 150},
	{field: 'MANAGER_ID16', align: 'center', title: '实际管理人', width: 150},
	{field: 'TRUSTEE_ID16', align: 'center', title: '托管人', width: 150},
	{field: 'AMT16', align: 'center', title: '委托投资金额', width: 150},
	{field: 'TRU_ACT_TYPE16', align: 'center', title: '资金实际投向', width: 150},
	{field: 'INVEST_TYPE16', align: 'center', title: '资金运用方式', width: 150},
	{field: 'CAP_USE_INDU_LEVEL216', align: 'center', title: '资金运用行业', width: 150},
	{field: 'PLAN_SDATE16', align: 'center', title: '投资运作起始日期', width: 150},
	{field: 'PLAN_EDATE16', align: 'center', title: '投资运作终止日期', width: 150},
	{field: 'PRO_PROPERTY16', align: 'center', title: '委外投资属性', width: 150},
	{field: 'RATE_FLAG16', align: 'center', title: '是否有预期收益率', width: 150},
	{field: 'MAX_RATE16', align: 'center', title: '预期最高收益率', width: 150},
	{field: 'MIN_RATE16', align: 'center', title: '预期最低收益率', width: 150},
	{field: 'MGR_FEE_RATE16', align: 'center', title: '管理费率%', width: 150},
	{field: 'TRU_FEE_RATE16', align: 'center', title: '托管费率%', width: 150},
	{field: 'TRADE_FEE_RATE16', align: 'center', title: '交易相关合计费率%', width: 150},
	{field: 'AGENCY_FEE_RATE16', align: 'center', title: '中介服务机构合计费率%', width: 150},
	{field: 'OTHER_FEE_RATE16', align: 'center', title: '其他合计费率%', width: 150},
	// 其他（负债）类资产
	{field: 'COUNTRY_AREA17', align: 'center', title: '*所属国家或地区', width: 150},
	{field: 'ASSET_NAME17', align: 'center', title: '*名称', width: 150},
	{field: 'TERM_DATE17', align: 'center', title: '期限（天）', width: 150},
	{field: 'DEBT_SCALE17', align: 'center', title: '*负债规模', width: 150},
	{field: 'ASSET_YIELD17', align: 'center', title: '利率%', width: 150},

];

var colList = [];
// 已选择字段列表
var selectedColumns = [];
//数据源表触发事件
function getColumns() {
	var tableId = $('#baseTableName').val();
	$("#columnType").empty();
	$("#resultColumn").empty();
	$("#selectedColumns").empty();
	var table = layui.table;
	var cols = [];
	var i = 1;
	$.ajax({
		url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColmnInfo",
		async : false,
		type : 'POST',
		traditional : true,
		data : {
			tableId : $('#baseTableName').val(),
		},
		success : function(result) {
			var jsonObj = eval("(" + result + ")");
			cols[0] = {
				type : 'checkbox',
				fixed : 'left'
			};
			var resultData = [];
			for ( var key in jsonObj) {
				cols[i] = {
					field : key,
					title : jsonObj[key],
					width : 150
				};
				i++;
				var curData = {"id": key, "text": jsonObj[key]};
				resultData.push(curData);
			}
			//type : 'action',field:'operation'
			// var operation = {fixed : 'right', title: '操作', width : 130, align:'center', toolbar: '#buttonDemo'};
			// cols.push(operation);

			$("#resultColumn").select2({
				placeholder: '请选择字段',
				multiple: true,
				data: resultData,
				allowClear:true,
			});
				if (tableId == "CHB_ASSET_INFO" || tableId == "CHB_ASSET_INFO_TMP") {
//							assetColList2.push(cols);
				layui.use([ 'table' ], function() {
					var table = layui.table;
					table.render({
						elem : '#dataTable',
						cols : [assetColList1,
							assetColList2]
					});
				});

			} else {
				colList = cols;
				layui.use(['table'], function () {
					var table = layui.table;
					table.render({
						elem: '#dataTable',
						cols: [cols]
					});
				});
			}
		}
	});
	Joyin.ajax( basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnByTableId",
		{tableId : tableId}, function(result) {
			if (result.lenght == 0) {
				return;
			}
			var jsonObj = eval("(" + result + ")");
			var initData = [];
			for (var key in jsonObj) {
				var curData = {"id": key, "text": jsonObj[key]};
				initData.push(curData);
			}
			$("#columnType").select2({
				placeholder: '请选择字段',
				multiple: true,
				data: initData,
				allowClear:true,
			});
		});
}


function setData(filterId) {
	var valueList = [];
	var initData = [];
	$("#columnType").val(valueList).trigger("change");
	$.ajax({
		url : basePath
			+ "common/infomanagement/SvrOperationalInfo001/queryPlanByfilterId",
		async : false,
		data : {
			filterId : filterId
		},
		success : function(result) {
			colunmList = eval("(" + result + ")");
			var tableId = colunmList['tableName'];
			// 数据源表已保存配置
			$('#baseTableName').val(tableId).trigger("change");
			for ( var key in colunmList) {
				if ("tableName" != key) {
					valueList.push(key);
				}
			}
			$.ajax({
				url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnByTableId",
				async : false,
				data : {
					tableId : tableId
				},
				success : function(result) {
					$("#columnType").empty();
					var jsonObj = eval("(" + result + ")");
					for ( var key in jsonObj) {
						var curData = {"id": key, "text": jsonObj[key]};
						initData.push(curData);
					}
				}

			});
			$("#columnType").select2({
				placeholder: '请选择字段',
				multiple: true,
				data: initData,
				allowClear:true
			});
			$("#columnType").val(valueList).trigger("change");
		}
	});
}

//选择字段下拉框事件
function showSelectColumns() {
	var tableId = $('#baseTableName').val();
	var columnIds = $('#columnType').val();
	if (typeof (columnIds) == "undefined" || columnIds == null
		|| columnIds == "") {
		$("#selectedColumns").hide();
		$('#selectedColumns').html("");
		selectedColumns = [];
		return;
	};
	// 当前需要操作字段
	var curColumns = [];
	// 更新时间默认值
	var defaultDate = '';
	// 当前操作字段名称
	var curColumnName;
	// 新增选项
	if (columnIds.length > selectedColumns.length) {
		for (var i = 0; i < columnIds.length; i ++) {
			if (selectedColumns.indexOf(columnIds[i]) == -1) {
				curColumns.push(columnIds[i]);
			}
		}
		for (var i = 0; i < curColumns.length; i ++) {
			var	curColumn = curColumns[i];
			$.ajax({
				url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnNameByCondition",
				async : false,
				data : {
					tableId : tableId,
					curColumn : curColumn
				},
				success : function(result) {
					curColumnName = result;
				}
			});

			$.ajax({
				url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnTypeByCondition",
				async : false,
				data : {
					tableId : tableId,
					curColumn : curColumn
				},
				success : function(result) {
					$("#selectedColumns").show();
					if (result == 'T') {
						$("#selectedColumns")
							.append(
								"<div class=\"layui-inline layui-col-md4\" id=\""
								+ tableId
								+ "-"
								+ curColumn
								+	"\">"
								+ " <label class=\"layui-form-label form-lable\">"
								+ curColumnName
								+ "</label><div class=\"layui-input-inline input-inline\">"
								+ "<input id=\""
								+ tableId
								+ "-"
								+ curColumn
								+ "-T"
								+ "\" class=\"layui-input form-input input-lenovoselect\" >"
								+ "</div>");
					} else if (result == 'S') {
						var colunmInofList = queryColumnInfoList(
							tableId, curColumn);
						var content = "<div class=\"layui-inline layui-col-md4\" id=\""
							+ tableId
							+ "-"
							+ curColumn
							+	"\">"
							+ "<label class=\"layui-form-label form-lable\">"
							+ curColumnName
							+ "</label>"
							+ "<div class=\"layui-input-inline input-inline\">"
							+ "<select type=\"text\" id=\""
							+ tableId
							+ "-"
							+ curColumn
							+ "-S"
							+ "\" name=\"columnType\""
							+ " class=\"layui-input form-input input-select\" "
							+ "allowClear=\"true\" multiple=\"multiple\">"
						for ( var key in colunmInofList) {
							content += "<option value='" + key
								+ "'>" + colunmInofList[key]
								+ "</option>"
						}
						content += "</select></div></div>"
						$("#selectedColumns").append(content);
						$("#selectedColumns").find('select')
							.textSelect();
					} else if (result == 'D') {
						$("#selectedColumns")
							.append(
								"<div class=\"layui-inline layui-col-md4\" id=\""
								+ tableId
								+ "-"
								+ curColumn
								+	"\">"
								+ "<label class=\"layui-form-label form-lable\">"
								+ curColumnName
								+ "</label>"
								+ "<div class=\"layui-input-inline input-inline\" id=\"test1\">"
								+ "<input type=\"text\"  name=\"timeControl\"  "
								+ "class=\"layui-input form-input input-dateRangeSelect\" id=\""
								+ tableId
								+ "-"
								+ curColumn
								+ "-D"
								+ "\" placeholder=\"请选择日期区间\" />"
								+ "</div>"
								+ "<div class=\"time-icon\"><i class=\"iconfont icon-date\"></i></div></div>");
						var laydate = layui.laydate;
						laydate.render({
							elem : $("#" + tableId + "-"
								+ curColumn + "-D")[0],
							range : true,
							value:defaultDate // 设置默认值
						});
					}
				}
			});
		}
		selectedColumns = columnIds;
		// 设置接口类型查询框默认值
		$('#CHB_INTERF_ERRODATA-INTERF_TYPE-T').val(interfType==null?'':interfType);
		// 触发查询按钮
		$('#loanplan_btn_search').trigger('click')
	};
	// 删除选项
	if (selectedColumns.length > columnIds.length) {
		for (var i = 0; i < selectedColumns.length; i ++) {
			if (columnIds.indexOf(selectedColumns[i]) == -1) {
				$("#" + tableId + "-" + selectedColumns[i]).remove();
			}
		}
		selectedColumns = columnIds;
	}
}

var getSearchParams = function() {
	var columnId = getColumnParams();
	return {
		tableId : $('#baseTableName').val(),
		columnIds : columnId
	}
};

var getColumnParams = function() {
	var tableId = $('#baseTableName').val();
	var colunmList = '';
	$.ajax({
		url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnTypeByTableId",
		async : false,
		data : {
			tableId : tableId
		},
		success : function(result) {
			var jsonObj = eval("(" + result + ")");
			for ( var key in jsonObj) {
				var columnInfo = key.split(",");
				var columnId = tableId + "-" + columnInfo[0] + "-"
					+ jsonObj[key];
				if ($("#" + columnId).length > 0) {
					var obj = '';
					if (jsonObj[key] == 'T') {
						obj = columnInfo[0] + "+" + "T" + "+"
							+ $("#" + columnId).val();
					} else if (jsonObj[key] == 'S') {
						var sdata = $("#" + columnId).val();
						if (sdata != null) {
							obj = columnInfo[0] + "+" + "S" + "+"
								+ sdata.join(",");
						}

					} else if (jsonObj[key] == 'D') {
						var dates = $("#" + columnId).val();
						if (!(typeof (dates) == "undefined"
							|| dates == null || dates == "")) {
							var dateList = dates.split(" - ");
							obj = columnInfo[0] + "+" + "D" + "+"
								+ dateList[0] + ","
								+ dateList[1];
						}
					}
					colunmList = colunmList + obj + ";";
				}
			}
		}
	});
	return colunmList;
};

//监听工具条
layui.table.on('tool(dataTable)', function(obj){
	var data = obj.data; //获得当前行数据
	var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

	if(layEvent === 'edit'){ //行内编辑按钮事件
		if(data.STATUS != "E:有效"){
			return Joyin.error("只能编辑有效数据，请重新选择");
		}
		data.pageModel = PAGE_MODEL_EDIT;
		data.operateType = "edit";
//			Joyin.ajaxJson(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/CHB_LOAN_DETAIL_INFO/check', data, function(result) {
		Joyin.dialog(JoyinUtil.getLabel("OperationalLoanInfo001.edit"),
			basePath + "svreport/loaninfomanagement/OperationalLoanInfo002/init",
			"98%", "95%", data, function() {
				table.reload();
			});
//			});
	} else if(layEvent === 'del'){ // 行内删除事件
		if(data.STATUS != "E:有效"){
			return Joyin.error("只能删除有效数据，请重新选择");
		}
//	        Joyin.ajaxJson('check', data, function(result){
//	            if(result){
		var param = {};
		param.ASSET_UUID = data.ASSET_UUID
		Joyin.confirm("请确认是否要删除选中记录？", function() {
			Joyin.ajax(basePath + "common/loaninfomanagement/OperationalLoanInfo001/delete", param, function() {
				table.reload();
			});
		});
//	            }
//	        });
	}
});

var table = $("#dataTable").joyintable({
	ajax : {
		url : 'findPage',
		type : 'POST',
		traditional : true,
		data : function() {
			return getSearchParams();
		},
	},
	cols : [ colList ],
	page : true
});

function showTables() {
	var h = $("#selectedColumns").outerHeight(true);
	var divheight = 110 + h;
	var tableId = $('#baseTableName').val();
	var columnId = getColumnParams();

		// 资产要素表特殊处理
		if (tableId == "CHB_ASSET_INFO" || tableId == "CHB_ASSET_INFO_TMP") {
			layui.use(
				[ 'table' ],
				function() {
					var table = layui.table;
					table
						.render({
							elem : '#dataTable',
							type : 'POST',
							where : {
								tableId : $('#baseTableName').val(),
								columnIds : columnId
							},
							url : basePath
								+ "common/infomanagement/SvrOperationalInfo001/findPage",
							page : true,
							limits: [20,100,200,300,400,500,600,700,800,900],
							limit: 20,
							height : 'full-' + divheight,
							cols : [assetColList1,
								assetColList2]
						});
				});
			return;

	}
	layui.use([ 'table' ], function() {
		var table = layui.table;
		table.render({
			elem : '#dataTable',
			type : 'POST',
			where : {
				tableId : $('#baseTableName').val(),
				columnIds : columnId
			},
			url : basePath + "common/commoninfomanagement/CommonOperationalInfo001/findPage",
			page : true,
			height : 'full-' + divheight,
			cols : [ colList ]
		});
	});
}

var queryColumnInfoList = function(tableId, columnId) {
	var colunmList;
	$.ajax({
		url : basePath + "common/infomanagement/SvrOperationalInfo001/queryColumnInfoList",
		async : false,
		data : {
			tableId : tableId,
			columnId : columnId
		},
		success : function(result) {
			colunmList = eval("(" + result + ")");
		}
	});
	return colunmList;
}

/**
 * 页面加载完后执行
 */
$(function() {
	var interfType = $("#interfType").val();
	if (typeof (interfType) == "undefined" || interfType == null || interfType == "") {
		return;
	}
	$('#baseTableName').val(interfType).trigger("change");

	setData(filterId);
	// showTables();
	// 查询按钮事件
	$('#prdBaseInfo_btn_search').click(function() {
		showTables();
	});

	//新建按钮绑定事件
	$('#OperationalLoanInfo001_btn_add').click(function() {
		Joyin.dialog(JoyinUtil.getLabel("新建"),
			JoyinUtil.getRealPath("/svreport/loaninfomanagement/OperationalLoanInfo002/init"),
			"98%", "95%", {pageModel:PAGE_MODEL_NEW},
			function(data){
				table.reload();
			});
	});

	// 导出按钮点击事件
	$('#prdBaseInfo_btn_export').click(function() {
		var table = layui.table;
		var checkStatus = table.checkStatus('dataTable');
		var selectedData = checkStatus.data;
		var jasonData = JSON.stringify(selectedData);
		var tableId = $('#baseTableName').val();
		var columnIds = getColumnParams();
		var param = {};
		param.tableId = tableId;
		param.columnIds = columnIds;
		param.jasonData = jasonData;
		if (typeof (tableId) == "undefined" || tableId == null
			|| tableId == "") {
			layer.alert('请选择数据源表', {
				icon : 2,
				skin : 'layer-ext-moon'
			});
			return;
		}
		if (tableId == "CHB_ASSET_INFO") {
		// 资产要素表,必须指定导出列
		if (jasonData == "[]") {
			layer.alert('资产要素表请先查询再勾选导出！', {
				icon : 2,
				skin : 'layer-ext-moon'
			});
			return;
		}
	}
	if (tableId == "CHB_INTERF_ERRODATA") {
		// 如果是错误信息表,必须指定导出列
		if (jasonData == "[]") {
			layer.alert('错误信息表必须指定导出列', {
				icon : 2,
				skin : 'layer-ext-moon'
			});
			return;
		}
		// 错误信息导出时,一次性只能导出一张业务信息表的数据
		Joyin.ajax('/common/commoninfomanagement/CommonOperationalInfo001/checkIsSingleTable', {
			jasonData : jasonData
		}, function(result) {
			if (result) {
				layer.alert('一次性只能导出一张业务信息表的数据', {
					icon : 2,
					skin : 'layer-ext-moon'
				});
			} else {
				Joyin.exportExcel(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/export', param);
			}
		});
	}else {
		Joyin.exportExcel(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/export', param);
	}
	});

	// 导入按钮点击事件
	$('#OperationalLoanInfo001_btn_import').click(function() {
		Joyin.confirm(JoyinUtil.getMessage('CHB_ISIMPORT'), function() {
			Joyin.uploadFile({
				hideTpl : true
			}, function(fileName) {
				Joyin.ajax(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/checkConfirmMsgForImportData', {fileName : fileName}, function(result) {
					// var message = result.message;
					if (result.length == 0) {
						Joyin.ajax(basePath + 'common/loaninfomanagement/OperationalLoanInfo001/importFile', {fileName : fileName}, function(result){
							Joyin.ajax(basePath + 'common/infomanagement/SvrOperationalInfo001/linkSvrReport', {fileName : fileName});
						});
					}
				});
			}, function() {
				table.reload();
			});
		});
	});

});