update BOK_BOOKSET_EXT set remark = '估值舍入规则: 01、进位 02、舍位 03、四舍五入默认执行为该参数，但是若前台界面要素配置，系统会根据前台要素配置的信息联动调整该账套参数' where param_code = 'VAL_DEC_TYPE' and param_name = '估值小数位处理方式';

update BOK_BOOKSET_EXT set remark = '保留的小数位数默认执行为该参数，但是若前台界面要素配置，系统会根据前台要素配置的信息联动调整该账套参数' where param_code = 'VAL_DEC_DIGITS' and param_name = '估值小数位位数';

update BOK_BOOKSET_EXT set remark = '数据字典：回购费用处理方式：01 不计入成本/02 计入成本；计入成本指的是首期费用金额体现在成本类科目进行摊销考虑，但不支持资产转仓；不计入成本指的首期费用金额体现在损益科目不进行摊销考虑' where param_code = 'IB_C_REP_FEE_EXE_TYPE' and param_name = '银行间质押式回购费用处理方式';

update BOK_BOOKSET_EXT set remark = '数据字典：回购费用处理方式：01 不计入成本/03 计入成本；计入成本指的是首期费用金额体现在成本类科目进行摊销考虑，但不支持资产转仓；不计入成本指的首期费用金额体现在损益科目不进行摊销考虑' where param_code = 'TRA_C_REP_FEE_EXE_TYPE' and param_name = '交易所质押式回购费用处理方式';

update BOK_BOOKSET_EXT set remark = '数据字典：回购费用处理方式：01 不计入成本/04 计入成本；计入成本指的是首期费用金额体现在成本类科目进行摊销考虑，但不支持资产转仓；不计入成本指的首期费用金额体现在损益科目不进行摊销考虑' where param_code = 'IB_O_REP_FEE_EXE_TYPE' and param_name = '银行间买断式回购费用处理方式';

update BOK_BOOKSET_EXT set remark = '数据字典：回购费用处理方式：01 不计入成本/05 计入成本；计入成本指的是首期费用金额体现在成本类科目进行摊销考虑，但不支持资产转仓；不计入成本指的首期费用金额体现在损益科目不进行摊销考虑' where param_code = 'TRA_O_REP_FEE_EXE_TYPE' and param_name = '交易所买断式回购费用处理方式';

update BOK_BOOKSET_EXT set remark = '数据字段：损益结转频率，枚举值D:日 M：月  Y:年' where param_code = 'CARRYOVER_CYCLE' and param_name = '损益科目结转频率';

update BOK_BOOKSET_EXT set remark = '数据字段：损益结转频率，枚举值D:日 M：月  Y:年' where param_code = 'PAL_CARRYOVER_CYCLE' and param_name = '损益平准金结转频率';

update BOK_BOOKSET_EXT set remark = '数据字段：损益结转频率，枚举值D:日 M：月  Y:年' where param_code = 'UP_CARRYOVER_CYCLE' and param_name = '本期利润损益结转频率';

update BOK_BOOKSET_EXT set remark = '账户剩余现金是否进行活期利息计提，是否：N:否 Y:是' where param_code = 'DEPOSIT_ACCOUT' and param_name = '活期存款是否计提';

update BOK_BOOKSET_EXT set remark = '货币型产品分红尾差处理方式：01:无需处理 02:确认为当期损益；理财产品的分红：01无需处理，则分红记账按照交易金额入账，02：冲减前一日应付利润余额' where param_code = 'BONUS_TAIL_MODE' and param_name = '分红尾差处理方式';

update BOK_BOOKSET_EXT set remark = '估值舍入规则: 01、进位 02、舍位 03、四舍五入；默认执行为该参数，但是若前台界面要素配置，系统会根据前台要素配置的信息联动调整该账套参数' where param_code = 'YEILD_DEC_TYPE' and param_name = '收益率小数位处理方式';

update BOK_BOOKSET_EXT set remark = '保留的小数位数；默认执行为该参数，但是若前台界面要素配置，系统会根据前台要素配置的信息联动调整该账套参数' where param_code = 'YEILD_DEC_DIGITS' and param_name = '收益率小数位位数';

update BOK_BOOKSET_EXT set remark = '公允价值变动计提日类型：01：交易日，02：交割日；若交易日与交割日非同一日，公允价值变动损益记账时间' where param_code = 'FV_CALCDATE_TYPE' and param_name = '公允价值变动计提日类型，交易日或交割日';

update BOK_BOOKSET_EXT set remark = '01：区间倒减，02：每日新增；计费基数不管是资产净值还是存续本金均支持该参数。' where param_code = 'PRD_FEE_ACCR_TYPE_C03' and param_name = '产品费用计提方式_固定管理费';

update BOK_BOOKSET_EXT set remark = '01:区间结束日一次计提， 02:区间内每日倒减计提；仅支持净值化普通、分档产品' where param_code = 'PRD_FEE_ACCR_TYPE_C04' and param_name = '净值型产品区间内浮动管理费计提方式';

update BOK_BOOKSET_EXT set remark = '01：区间倒减，02：每日新增；计费基数不管是资产净值还是存续本金均支持该参数。' where param_code = 'PRD_FEE_ACCR_TYPE_C05' and param_name = '产品费用计提方式_托管费';

update BOK_BOOKSET_EXT set remark = '01：区间倒减，02：每日新增；计费基数不管是资产净值还是存续本金均支持该参数。' where param_code = 'PRD_FEE_ACCR_TYPE_C06' and param_name = '产品费用计提方式_销售费';

update BOK_BOOKSET_EXT set remark = '01：区间倒减，02：每日新增；计费基数不管是资产净值还是存续本金均支持该参数。' where param_code = 'PRD_FEE_ACCR_TYPE_C09' and param_name = '产品费用计提方式_外包服务费';

update BOK_BOOKSET_EXT set remark = '01：区间倒减，02：每日新增；计费基数不管是资产净值还是存续本金均支持该参数。' where param_code = 'PRD_FEE_ACCR_TYPE_C10' and param_name = '产品费用计提方式_代销费';

update BOK_BOOKSET_EXT set remark = '01：区间倒减，02：每日新增；计费基数不管是资产净值还是存续本金均支持该参数。' where param_code = 'PRD_FEE_ACCR_TYPE_C18' and param_name = '产品费用计提方式_投资顾问费';

update BOK_BOOKSET_EXT set remark = '保留的小数位数；默认执行为该参数，但是若前台界面要素配置，系统会根据前台要素配置的信息联动调整该账套参数' where param_code = 'PYEILD_DEC_DIGITS' and param_name = '万份收益小数位位数';

update BOK_BOOKSET_EXT set remark = '保留的小数位数，估值表显示小数位设置' where param_code = 'SHARE_DEC_DIGITS' and param_name = '份额小数位位数';

update BOK_BOOKSET_EXT set remark = '计提方式: 01：区间倒减，02：每日新增；每日新增，最后一日区间倒减' where param_code = 'REPO_ACCR_TYPE_F02_F03' and param_name = '回购计提方式_质押式';

update BOK_BOOKSET_EXT set remark = '计提方式: 01：区间倒减，02：每日新增；每日新增，最后一日未处理区间倒减' where param_code = 'REPO_ACCR_TYPE_F04_F05' and param_name = '回购计提方式_买断式';

update BOK_BOOKSET_EXT set remark = '计提方式: 01：区间倒减，02：每日新增；每日新增，最后一日区间倒减' where param_code = 'DL_ACCR_TYPE' and param_name = '拆借计提方式';

update BOK_BOOKSET_EXT set remark = '计提方式: 01：区间倒减，02：每日新增；每日新增，最后一日区间倒减' where param_code = 'DEPO_ACCR_TYPE' and param_name = '存款计提方式';

update BOK_BOOKSET_EXT set remark = '计提方式: 01：区间倒减，02：每日新增；每日新增，最后一日区间倒减' where param_code = 'PLAN_ACCR_TYPE' and param_name = '计划类利率型资产计提方式';

update BOK_BOOKSET_EXT set remark = '对于计入当期损益债券类资产卖出交易，随交易支付的费用损益科目使用哪个科目01:计入投资收益,02:计入费用支出；其他投资目的的其他债券类资产卖出的场景均计入了投资收益' where param_code = 'SEC_DEAL_FEE_SUBJECT' and param_name = '债券交易费用损益计入方式';

update BOK_BOOKSET_EXT set remark = '01：区间倒减，02：每日新增；收益型产品客户收益计提方式，若选择每日新增，最后一笔不进行区间倒减' where param_code = 'PRD_INT_ACCR_TYPE' and param_name = '产品利息计提方式';

update BOK_BOOKSET_EXT set remark = '是否：N:否 Y:是；收益型产品利息计提是否本系统处理，如果不是本系统计提，可通过上游接入数据，但无账务数据' where param_code = 'IS_PRD_INT_ACCR' and param_name = '产品是否本系统计提';

update BOK_BOOKSET_EXT set remark = '01：上游提供 02：估值方案生成；选择02时候，可以不使用交易界面的投资目的' where param_code = 'INVESTMENT_PURPOSE_ACCESS' and param_name = '投资目的获取方式';

update BOK_BOOKSET_EXT set remark = '计提方式: 01：区间倒减，02：每日新增；每日新增，最后一日区间倒减' where param_code = 'SECLEND_ACCR_TYPE' and param_name = '债券借贷计提方式';

update BOK_BOOKSET_EXT set remark = '保留的小数位数，估值表中周期年化收益率显示' where param_code = 'FLOAT_DEC_DIGITS' and param_name = '浮动管理费_估值小数位';

update BOK_BOOKSET_EXT set remark = '估值舍入规则: 01、进位 02、舍位 03、四舍五入；估值表中周期年化收益率显示' where param_code = 'FLOAT_DEC_TYPE' and param_name = '浮动管理费_估值小数尾数处理方式';

update BOK_BOOKSET_EXT set remark = '保留的小数位数，-1默认为全精度' where param_code = 'FLOAT_CYCLYEAR_YIELD_DIGITS' and param_name = '浮动管理费_周期年化收益率小数位数';

update BOK_BOOKSET_EXT set remark = '估值舍入规则: 01、进位 02、舍位 03、四舍五入' where param_code = 'FLOAT_CYCLYEAR_YIELD_TYPE' and param_name = '浮动管理费_周期年化收益率位数处理方式';

update BOK_BOOKSET_EXT set remark = '保留的小数位数' where param_code = 'FLOAT_ENDTOTAL_NET_DIGITS' and param_name = '浮动管理费_期末累计净值小数位数';

update BOK_BOOKSET_EXT set remark = '估值舍入规则: 01、进位 02、舍位 03、四舍五入' where param_code = 'FLOAT_ENDTOTAL_NET_TYPE' and param_name = '浮动管理费_期末累计净值尾数处理方式';

update BOK_BOOKSET_EXT set remark = '保留的小数位数' where param_code = 'FLOAT_STARTTOTAL_NET_DIGITS' and param_name = '浮动管理费_期初累计净值小数位数';

update BOK_BOOKSET_EXT set remark = '估值舍入规则: 01、进位 02、舍位 03、四舍五入' where param_code = 'FLOAT_STARTTOTAL_NET_TYPE' and param_name = '浮动管理费_期初累计净值尾数处理方式';

update BOK_BOOKSET_EXT set remark = '保留的小数位数' where param_code = 'FLOAT_STARTTOTAL_UNITNET_DIGITS' and param_name = '浮动管理费_期初单位净值小数位数';

update BOK_BOOKSET_EXT set remark = '估值舍入规则: 01、进位 02、舍位 03、四舍五入' where param_code = 'FLOAT_STARTTOTAL_UNITNET_TYPE' and param_name = '浮动管理费_期初单位净值尾数处理方式';

update BOK_BOOKSET_EXT set remark = '01：发生额 02：余额；计算含税销售额的取值逻辑 ' where param_code = 'TAX_ACCR_BASE_TYPE_DKYWSR_FS' and param_name = '当日计税计提基准_贷款业务收入_发生';

update BOK_BOOKSET_EXT set remark = '01：发生额 02：余额；计算含税销售额的取值逻辑  ' where param_code = 'TAX_ACCR_BASE_TYPE_DKYWSR_YG' and param_name = '当日计税计提基准_贷款业务收入_预估';

update BOK_BOOKSET_EXT set remark = '01：单资产 03：全资产；算当日单产品的增值税，是按照单资产分别算含税销售额及增值税，进行增值税额汇总，还是按照全资产进行含税销售额汇总后进行增值税额  ' where param_code = 'TAX_ASSET_TYPE_DKYWSR_FS' and param_name = '证券类型计税方式_贷款业务收入_发生';

update BOK_BOOKSET_EXT set remark = '01：单资产 03：全资产；算当日单产品的增值税，是按照单资产分别算含税销售额及增值税，进行增值税额汇总，还是按照全资产进行含税销售额汇总后进行增值税额 ' where param_code = 'TAX_ASSET_TYPE_DKYWSR_YG' and param_name = '证券类型计税方式_贷款业务收入_预估';

update BOK_BOOKSET_EXT set remark = '01：单资产 03：全资产；算当日单产品的增值税，是按照单资产分别算含税销售额及增值税，进行增值税额汇总，还是按照全资产进行含税销售额汇总后进行增值税额 ' where param_code = 'TAX_ASSET_TYPE_JRSPZR_FS' and param_name = '证券类型计税方式_金融商品转让_发生';

update BOK_BOOKSET_EXT set remark = '01：单资产 03：全资产；算当日单产品的增值税，是按照单资产分别算含税销售额及增值税，进行增值税额汇总，还是按照全资产进行含税销售额汇总后进行增值税额 ' where param_code = 'TAX_ASSET_TYPE_JRSPZR_YG' and param_name = '证券类型计税方式_金融商品转让_预估';

update BOK_BOOKSET_EXT set remark = '01:计入投资收益,02:计入费用支出 ' where param_code = 'BOND_LENDING_DEAL_FEE_SUBJECT' and param_name = '债券借贷费用损益计入方式';

update BOK_BOOKSET_EXT set remark = '估值舍入规则: 01、进位 02、舍位 03、四舍五入 ' where param_code = 'DAY7_YEILD_DEC_TYPE' and param_name = '7日年化收益率小数尾数_估值表展示';

update BOK_BOOKSET_EXT set remark = 'D:日 M：月  Y:年；汇兑换算批处理频率' where param_code = 'EXCHANGE_CYCLE' and param_name = '汇兑损益结转频率';

update BOK_BOOKSET_EXT set remark = '市价取最近的时候有时间差，中间如果发生券面变化需要折算。01：按占比计算(当前市价=当前市价*估值日债券面值/行情日债券面值) 02：按金额计算 (当前市价=当前市价-(行情日债券面值-估值日债券面值)' where param_code = 'SEC_MPRICE_OBVERSION' and param_name = '债券市价折算方式';

update BOK_BOOKSET_EXT set remark = '估值表里面昨日单位净值展示的数据来源；01:上一自然日  02：上一工作日' where param_code = 'VALUATION_DATETYPE' and param_name = '昨日单位净值估值日期类型';

update BOK_SCHEME_CONFIG set remark = '01日/02周/03月/04季/05年/06半年…按频率重置计税余额，类似于缴费频率；除了价差收入税，其他收入的税额结转频率' where config_id = 'TAX_CARRYOVER_CYCLE' and config_name = '税额结转频率';

update BOK_SCHEME_CONFIG set remark = '1:amt/(1+rate)*rate;2:round(amt/(1+rate),2)*rate' where config_id = 'TAX_CALC_FORMULA' and config_name = '增值税计算公式';

update BOK_SCHEME_CONFIG set remark = '价差收入抵扣公允价值变动损益计税。实亏是否抵扣浮盈，01：抵扣，02：不抵扣' where config_id = 'FLOATCALCBYDIFF' and config_name = '实亏是否抵扣浮盈';

update BOK_SCHEME_CONFIG set remark = '是否预估公允价值变动增值税附加' where config_id = 'ISCALCFLOATALT' and config_name = '是否预估公允价值变动增值税附加';

update BOK_SCHEME_CONFIG set remark = '01:使用收入备抵科目/02:冲减原收入科目' where config_id = 'INTTAXSUBJECTNOSET' and config_name = '利息收入增值税科目处理方式';

update BOK_BOOKSET_EXT set remark = 'Y:是/N:否；若选择N则在资产到期时或者资产全量卖出时候进行冲销，存在行提出该需求' where param_code = 'REDAMOFLAG' and param_name = '还本是否冲减摊销';

update BOK_SCHEME_CONFIG set remark = '交易所债券卖出优先计算：01 优先计算卖出 ，02 优先计算计提；若选择“02”，债券t+1交易，t日进行税务计算时候，关于卖出冲减利息，也要包含t日的计提' where config_id = 'SH_DEAL_ORDER' and config_name = '交易所债券卖出优先计算';

update BOK_SCHEME_CONFIG set remark = '01 存在,02 不存在；考虑到交易所债券买入当日不计提，但是若第二天全部卖出的话，会存在预估冲销不干净的场景，故如果配置成01，代码处理可以冲销完成。但是考虑部分项目可能不存在该场景。' where config_id = 'EXCHANGE_ALLSELL_DEAL' and config_name = '是否存在交易所债券买入后第二天全部卖出';

update BOK_BOOKSET_EXT set remark = '01：倒减科目余额；02：倒减前一日计算结果；指项目类资产计提时，若是区间倒减，可选择倒减形式。计提时候是否考虑交易上的应收利息的影响，部分客户提出该需求' where param_code = 'PLAN_SUBTRACTION_TYPE' and param_name = '项目类计提区间倒减方式';

update BOK_BOOKSET_EXT set remark = '01：手动回补 02：自动回补' where param_code = 'ANAPLEROTIC_TYPE' and param_name = '回补方式';

update BOK_BOOKSET_EXT set remark = '01：使用固定管理费回补 02：使用浮动管理费回补 03：优先使用浮动管理费回补 04：强制回补' where param_code = 'ANAPLEROTIC_RULE' and param_name = '回补规则';

update BOK_BOOKSET_EXT set remark = '01:按占比收息；02:不按占比收息' where param_code = 'INT_SHARE_CALC_TYPE' and param_name = '计提份额占比收息方式';

update BOK_BOOKSET_EXT set remark = '是否：N:否 Y:是' where param_code = 'IS_ENTIRE' and param_name = '批处理是否全量';

update BOK_BOOKSET_EXT set remark = '是否：N:否 Y:是' where param_code = 'IS_DIFF_CCY' and param_name = '是否分币核算';

update BOK_SCHEME_CONFIG set remark = 'Y/N' where config_id = 'IS_CALC_TURNED' and config_name = '是否处理待抵扣增值额';

update BOK_SCHEME_CONFIG set remark = '01:按照数量扣减,02:按照增值额扣减' where config_id = 'TURNED_CALC_TYPE' and config_name = '待抵扣增值额处理方式';

update BOK_BOOKSET_EXT set remark = '数据字典：收益类型；估值表生成按照这个参数生成不同收益类型的估值表信息' where param_code = 'PROFIT_TYPE' and param_name = '收益类型';

update BOK_BOOKSET_EXT set remark = '是否：N:否 Y:是，该参数无需客户确认，按照默认值配置' where param_code = 'IS_COMB_TRA_VOUC' and param_name = '交易凭证是否合并';

update BOK_BOOKSET_EXT set remark = '是否：N:否 Y:是，该参数无需客户确认，按照默认值配置' where param_code = 'IS_COMB_SUP_VOUC' and param_name = '补账凭证是否合并';

update BOK_BOOKSET_EXT set remark = '是否：N:否 Y:是，该参数无需客户确认，按照默认值配置' where param_code = 'IS_COMB_SUP_REC' and param_name = '补账分录是否合并';

update BOK_BOOKSET_EXT set remark = '01：当日自动结转，不生成交易，02：次日生成交易，不自动结转，99：不处理；虽然有参数，但是代码里写死了02' where param_code = 'VAL_CPRD_AUTO_BONUS_TYPE' and param_name = '货币型产品自动红利再投';





















