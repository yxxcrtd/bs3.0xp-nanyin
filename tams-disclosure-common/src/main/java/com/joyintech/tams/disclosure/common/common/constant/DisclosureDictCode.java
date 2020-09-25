/*
 * 文件名：ReportDictCode.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved. 描述：中信银行资管系统
 * 修改人：何阳阳 修改时间：2016年10月19日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.common.common.constant;

import com.joyintech.framework.base.BaseDictCode;
import com.joyintech.framework.util.StringUtil;

/**
 * 数据字典枚举
 * 中债子系统专用
 *
 * @author 何阳阳
 * @version 1.0 2016年10月19日
 * @see DisclosureDictCode
 * @since 1.0
 */
public enum DisclosureDictCode implements BaseDictCode{

//    /**
//     * 机构类型
//     */
//    机构类型("ORG_TYPE"),
//
//    /**
//     * 任务组
//     */
//    任务组("TASKCLASS"),
//
//    /**
//     * 提醒类型(大分类)
//     */
//    提醒类型_大分类("REMINDER_TYPE_BIG"),
//
//    /**
//     * 提醒类型(小分类)
//     */
//    提醒类型_小分类("REMINDER_TYPE_SMALL"),
//
    /**
     * 子系统
     */
    子系统("SUBSYSTEM"),
//
    /**
     * 模块名称
     */
    模块名称("MODULENAME"),
//
//    /**
//     * 合作模式
//     */
//    合作模式("CONTMODE"),
//
//    /**
//     * 资产所属中心
//     */
//    资产所属中心("ASSET_OWN_CENTRE"),
//
//    /**
//     * 开放模式
//     */
//    开放模式("OPEN_MODE"),
//
//    /**
//     * 产品系列
//     */
//    产品系列("PRODSERIES"),
//
//    /**
//     * 调整规则
//     */
//    调整规则("ADJUSTMENT_RULE"),
//
////    /**
////     * 投资者类型
////     */
////    投资者类型("SALETARGET_DECLARE"),
//
//    /**
//     * 科目级别
//     */
//    科目级别("SUBJECT_LEVEL"),
//
//    /**
//     * 产品运作模式
//     */
//    产品运作模式("OPERATEMODE_DECLARE"),
//
//    /**
//     * 投资者风险偏好
//     */
//    投资者风险偏好("RISKTYPE"),
//
//    /**
//     * 币种
//     */
//    币种("CURRENCY"),
//    /**
//     * 中债币种
//     */
//    中债币种("CURRENCY_CHB"),
//    /**
//     * 币种(底层资产)
//     */
//    底层资产币种("CURRENCY_BOT"),
//
//    /**
//     * 管理方式（产品）
//     */
//    产品管理方式("MANATYPE_PRODUCT"),
//
//    /**
//     * 管理方式（计划类资产）
//     */
//    计划类资产管理方式("MANATYPE_PLAN_ASSET"),
//
//    /**
//     * 投资本金(收益)到账日
//     */
//    投资本金_收益_到账日("AMTDATETYPE"),
//
//    /**
//     * 是否
//     */
//    是否("YES_NO"),
//
//    /**
//     * 投资类型
//     */
//    投资类型("INVESTTYPE_DECLARE"),
//
//    /**
//     * 投资类型(人行报表)
//     */
//    投资类型_人行报表("INVESTTYPE_DECLARE_PBC_REPORT"),
//
//    /**
//     * 投资者类型(人行报表)
//     */
//    投资者类型_人行报表("SALETARGET_DECLARE_PBC_REPORT"),
//
//    /**
//     * 结构性产品挂钩标的
//     */
//    结构性产品挂钩标的("STRUCT_PROD_LINK_SUBJECT"),
//
//    /**
//     * 从业人员类型
//     */
//    从业人员类型("TYPE_OF_PRACTITIONER"),
//
//    /**
//     * 资金投向地区
//     */
//    资金投向地区("SALEAREA_DECLARE"),
//
//    /**
//     * 产品投资国家或地区
//     */
//    产品投资国家或地区("OVERSEAS_COUNTRY"),
//
//    /**
//     * 理财业务服务模式
//     */
//    理财业务服务模式("SERVICEMODE"),
//
//    /**
//     * 会计核算方式
//     */
//    会计核算方式("BOOKMODEL_DECLARE"),
//
//    /**
//     * 产品定价方式
//     */
//    产品定价方式("PRODVALUETYPE"),
//
//    /**
//     * 资产类型（项目类资产）
//     */
//    项目类资产类型("ASSET_TYPE_PROJECT"),
//
//    /**
//     * 销售渠道
//     */
//    销售渠道("SALECHANNELCHB"),
//
//    /**
//     * 渠道
//     */
//    渠道("CHANNEL"),

    /**
     * 数据状态
     */
    数据状态("STATUS"),
//
//    /**
//     * 要素类型
//     */
//    要素类型("ELEMENT_TYPE"),
//
    /**
     * 产品类型属性
     */
    产品类型("PROD_TYPE"),
//
//    /**
//     * 发行状态
//     */
//    发行状态("ISSUE_STATUS"),
//
//    /**
//     * 批处理任务状态
//     */
//    批处理任务状态("TASK_STATUS"),
//
//    /**
//     * 批处理执行结果
//     */
//    批处理执行结果("TASK_RESULT"),
//
//    /**
//     * 共通业务类型
//     */
//    共通业务类型("COMMON_BUSINESS_TYPE"),
//
//    /**
//     * 资产存续期 -> 资产存续期管理 -> 资产存续期状态
//     */
//    资产存续期状态("ASSETS_DEPOSIT_STATUS"),
//
//    /**
//     * 角色岗位
//     */
//    角色岗位("ROLE_POST"),
//    /**
//     * 头寸类型
//     */
//    头寸类型("POSITION_TYPE"),
//
//    /**
//     * 操作类型
//     */
//    操作类型("ACTION_TYPE"),
//
//    /**
//     * 流程状态
//     */
//    流程状态("PROCESS_STATE"),
//
//
//    /**
//     * 报文类型
//     */
//    报文类型("REPORT_TYPE"),
//
//    /**
//     * 付息类型
//     */
//    付息类型("INTEREST_TYPE"),
//
//    /**
//     * 付息分红规则
//     */
//    付息分红规则("INTEREST_ROLE"),
//
//    /**
//     * 推算方法
//     */
//    推算方法("CALC_METHOD"),
//
//    /**
//     * 结构类型
//     */
//    结构类别("STRUCTURE_CATEGORY"),
//
//    /**
//     * 营业日准则
//     */
//    营业日准则("BUSINESS_DAY_STANDARD"),
//
//    /**
//     * 付息方式
//     */
//    付息方式("PAYMENT_KIND"),
//
//    /**
//     * 报备状态
//     */
//    报备状态("STATE_PREPARATION"),
//
//    /**
//     * 期限品种（产品端）
//     */
//    产品端期限品种("TERM_TYPE_PRODUCT"),
//
//    /**
//     * 期限品种（质押式回购）
//     */
//    质押式回购期限品种("TERM_TYPE_COLLATERALISED_REPO"),
//
//    /**
//     * 期限品种(内部拆借)
//     */
//    内部拆借期限品种("TERM_TYPE_RESOLUTE"),
//    /**
//     * 产品期限
//     */
//    产品期限("PRODUCT_TERM"),
//
//    /**
//     * 投资资产类型
//     */
//    投资资产类型("INV_ASSET_TYPE"),
//
//    /**
//     * 产品销售区域
//     */
//    产品销售区域("SALE_AREA"),
//    /**
//     * 产品增信标识
//     */
//    产品增信标识("PRODUCT_IDENTIFICATION_FLAG"),
//    /**
//     * 兑付本金币种
//     */
//    兑付本金币种("PAYMENT_CURRENCY"),
//    /**
//     * 兑付收益币种
//     */
//    兑付收益币种("INCOMING_CURRENCY"),
//    /**
//     * 境外托管机构国别
//     */
//    境外托管机构国别("COUNTRY"),
//    /**
//     * 发行机构提前终止权标识
//     */
//    发行机构提前终止权标识("TERM_RIGHT_FLAG"),
//
//    /**
//     * 客户赎回权标识
//     */
//    客户赎回权标识("CUS_REDEEM_FLAG"),
//
//    /**
//     * 产品增信机构类型
//     */
//    产品增信机构类型("CREDIT_TYPE"),
//
//    /**
//     * 发行方式
//     */
//    发行方式("ISSUE_MODE"),
//
//    /**
//     * 发行方式（债券类资产）
//     */
//    债券类资产发行方式("ISSUE_MODE_FUND"),
//
//    /**
//     * 开放日类型
//     */
//    开放日类型("OPEN_DAY_TYPE"),
//
//    /**
//     * 利率类型
//     */
//    利率类型("INTEREST_RATE"),
//
//    /**
//     * 产品分类
//     */
//    产品分类("PROD_BUSS_TYPE"),
//
//    /**
//     * 产品二级类型
//     */
//    产品二级类型("PROD_TYPE2"),
//
//    /**
//     * 增信标识
//     */
//    增信标识("CREDIT_FLAG"),
//
//    /**
//     * 增信形式
//     */
//    增信形式("CREDIT_MODE"),
//
//    /**
//     * 资产配置方式
//     */
//    资产配置方式("CMMODE"),
//
//    /**
//     * 管理模式
//     */
//    管理模式("PROD_MANAGE"),
//
//    /**
//     * 关联范围
//     */
//    关联范围("CORRELATION_RANGE"),
//
//    /**
//     * 记账类型
//     */
//    记账类型("BOOKS_TYPE"),
//
//    /**
//     * 收费类型
//     */
//    收费类型("CHARGE_TYPE"),
//    /**
//     * 计算基础
//     */
//    计算基础("ACCOUNT_BASIS"),
//    /**
//     * 费用类型
//     */
//    费用类型("FEE_TYPE"),
//
//    /**
//     * 费用二级类型
//     */
//    费用二级类型("FEE_SEC_TYPE"),
//    /**
//     * 星期号
//     */
//    星期号("WEEK"),
//
//    /**
//     * 支付状态
//     */
//    支付状态("PAYMENT_TYPE"),
//
//    /**
//     * 账户类型
//     */
//    账户类型("ACCT_TYPE"),
//
//    /**
//     * 开户行类型
//     */
//    开户行类型("FINANCE_DETAIL"),
//
//    /**
//     * 认购扣款方式
//     */
//    认购扣款方式("SUBSCRIPTION_PAY_MODE"),
//
//    /**
//     * 期限范围
//     */
//    期限范围("TERM_RANGE"),
//    /**
//     * 分层类型
//     */
//    分层类型("CLASSIFICATION_TYPE"),
//    /**
//     * 尾数处理方式
//     */
//    尾数处理方式("MANTISSA_PROCESS_MODE"),
//    /**
//     * 担险标志
//     */
//    担险标志("RISK_FLAG"),
//    /**
//     * 币种钞汇标志
//     */
//    币种钞汇标志("NOTE_EXCHANGE_FLAG"),
//    /**
//     * 赎回份额明细处理
//     */
//    赎回份额明细处理("DETAIL_PROCESSING"),
    /**
     * 理财类型
     */
    理财类型("FINA_TYPE"),

    /**
     * 产品期限类型
     */
    产品期限类型("PROD_TERM_TYPE"),
//    /**
//     * 适用范围
//     */
//    适用范围("APPLY_SCOPE"),
//
//    /**
//     * R定义
//     */
//    R定义("R_DEFINE"),
//    /**
//     * 付费类型
//     */
//    付费类型("PAY_TYPE"),
//    /**
//     * 文档类型
//     */
//    文档类型("FILE_TYPE"),
//
//    /**
//     * 产品信息管理 -> 信息披露 -> 披露状态
//     */
//    披露状态("DISCLOSURE_STATUS"),
//
//    /**
//     * 开放期业务
//     */
//    开放期业务("OPEN_BUSS"),
//
//    /**
//     * 销售部门
//     */
//    销售部门("SALE_DEPT"),
//
//    /**
//     * 持有证明开立权限
//     */
//    持有证明开立权限("AUTHORITY"),
//
//    /**
//     * 自动续投申购确认延迟天数
//     */
//    自动续投申购确认延迟天数("AUTO_DELAY_DAY"),
//
//    /**
//     * 利率处理方式
//     */
//    利率处理方式("RATEVALUE_MODE"),
//
//    /**
//     * 组合类别
//     */
//    组合类别("GROUP_TYPE"),
//
//    /**
//     * 付费规则
//     */
//    付费规则("PAY_RULE"),
//
//    /**
//     * 观察日的交易日历
//     */
//    观察日的交易日历("OBS_CAL_ID"),
//
//    /**
//     * 期限品种(区间计算用)
//     */
//    区间计算用期限品种("TERM_TYPE_FOR_CALC"),
//
//    /**
//     * 计费基数（产品端）
//     */
//    产品端计费基数("BILL_BASE_PRODUCT"),
//
//    /**
//     * 计费基数（固定费用）
//     */
//    固定费用计费基数("BILL_BASE_FIX_FEE"),
//
//    /**
//     * 计费基数（浮动费用）
//     */
//    浮动费用计费基数("BILL_BASE_FLOAT_FEE"),
//
//    /**
//     * 计费基数（费用品种）
//     */
//    费用品种计费基数("BILL_BASE_FEE_KIND"),
//
//    /**
//     * 销售渠道
//     */
//    产品销售渠道("SALE_CHANEEL"),
//
//    /**
//     * 生效规则
//     */
//    生效规则("EFFECTIVE_RULE"),
//
//    /**
//     * 业绩基准规则
//     */
//    业绩基准规则("ACHIEVEMENT_BENCHMARK_RULE"),
//    /**
//     * 部门职能
//     */
//    部门职能("DEPT_FUNCTION"),
//    /**
//     * 产品研发默认部门
//     */
//    产品研发默认部门("DEPARTMENT"),
//    /**
//     * 销售对象
//     */
//    销售对象("SELL_TARGET"),
//
//    /**
//     * 生成方式
//     */
//    生成方式("GENER_TYPE"),
//
//    /**
//     * 专业技术职称
//     */
//    专业技术职称("PROFESSION_TECH"),
//
//    /**
//     * 理财专业证书
//     */
//    理财专业证书("FINANCIAL_CERT"),
//
//    /**
//     * 性别
//     */
//    性别("SEX"),
//
//    /**
//     * 学历
//     */
//    学历("EMPLOYEE_EDUCATION"),
//
//    /**
//     * 学位
//     */
//    学位("EMPLOYEE_DEGREE"),
//
//    /**
//     * 存款类型
//     */
//    存款类型("DEPO_TYPE"),
//
//    /**
//     * 要素列（底层资产）
//     */
//    底层资产要素列("BOTTOM_ASSET_TYPE"),
//
//    /**
//     * 交易类型（产品端）
//     */
//    产品交易类型("PROD_DEAL_TYPE"),
//
//    /**
//     * 境内外
//     */
//    境内外("BLN_AREA"),
//
//    /**
//     * 付息频率
//     */
//    付息频率("INTPAY_CYCLE"),
//
//    /**
//     * 贷后频率
//     */
//    贷后频率("AC_CYCLE"),
//
//    /**
//     * 二级资产类别
//     */
//    二级资产类别("ASSET_TYPE_SECOND_LEVEL"),
//
//    /**
//     * 资产负债类别
//     */
//    资产负债类别("ASSETDEBT_TYPE"),
//
//    /**
//     * 资产负债类型
//     */
//    资产负债类型("ASSET_LIABILITY_TYPE"),
//
//    /**
//     * 汇率类型
//     */
//    汇率类型("EXCHANGE_TYPE"),
//
//    /**
//     * 货币对
//     */
//    货币对("CYY_PAIR"),
//
//    /**
//     * 股票交易状态
//     */
//    股票交易状态("SHARES_TRADE_STATUS"),
//
//    /**
//     * 交易流通场所
//     */
//    交易流通场所("TRADE_MARKET"),
//
//    /**
//     * 周期单位(持有上下限)
//     */
//    持有上下限周期单位("PERIOD_UNIT"),
//
//    /**
//     * 资产类型（债券类资产）
//     */
//    债券类资产资产类型("CHB_SECTYPE"),
//    /**
//     * 资产评级
//     */
//    资产评级("MAIN_GRADE"),
//    /**
//     * 机构类型（按规模划分）
//     */
//    发行机构类型_按规模划分("PARTY_TYPE1"),
//    /**
//     * 机构类型（按技术领域划分）
//     */
//    发行机构类型_按技术领域划分("PARTY_TYPE3"),
//    /**
//     * 机构类型（按经济类型划分）
//     */
//    发行机构类型_按经济类型划分("PARTY_TYPE2"),
//    /**
//     * 机构类型（按规模划分）画面使用
//     */
//    发行机构类型_按规模划分_画面用("PARTY_TYPE_FOR_PAGE1"),
//    /**
//     * 机构类型（按技术领域划分）画面使用
//     */
//    发行机构类型_按技术领域划分_画面用("PARTY_TYPE_FOR_PAGE3"),
//    /**
//     * 机构类型（按经济类型划分）画面使用
//     */
//    发行机构类型_按经济类型划分_画面用("PARTY_TYPE_FOR_PAGE2"),
//    /**
//     * 登记托管机构
//     */
//    登记托管机构("REGIST_ORG"),
//
//    /**
//     * 登记托管机构(本来上面的登记托管机构是中债创建的，被其他模块使用，并且修改了具体内容，重新创建)
//     */
//    中债登记托管机构("BOT_REGIST_ORG"),
//    /**
//     * 重点监控行业和领域类别 当“项目是否属于重点监控行业和领域”选择“是”，必填，选否不可填；若选其他，则需要在说明中注明行业具体类型。
//     */
//    重点监控行业和领域类别("MOINT_TYPE"),
//
//    /**
//     * 融资人类型（按经济类型划分）
//     */
//    融资人类型按经济类型划分("FINANCIER_TYPE2"),
//
//    /**
//     * 融资人类型（按经济类型划分）
//     */
//    融资人类型按技术领域划分("FINANCIER_TYPE3"),
//
//    /**
//     * 融资人类型（按经济类型划分）
//     */
//    融资人类型按规模划分("FINANCIER_TYPE1"),
//
//    /**
//     * 回购标的类别 对于分类为“货币市场工具—逆回购”的资产、“货币市场—正回购“的负债必填，其他类别资产不可填。 选择“其他”时，备注必填。
//     */
//    回购标的类别("TARGET_TYPE2"),
//
//    /**
//     * 投资阶段 “私募基金”中“成长基金”、“并购基金”、“房地产基金”、“基础设施基金”和“创业投资基金”必填，其他类别的基金可不填。
//     */
//    投资阶段("INVEST_STAGE"),
//
//    /**
//     * 登记备案机构
//     */
//    登记备案机构("REGIST_ORG2"),
//
//    /**
//     * 购买结构
//     */
//    购买结构("BUY_STRUCT"),
//
//    /**
//     * 资管计划属性
//     */
//    资管计划属性("PRO_PROPERTY"),
//
//    /**
//     * 资金实际投向
//     */
//    资金实际投向("TRU_ACT_TYPE"),
//
//    /**
//     * 标的类别(可多选)
//     */
//    标的类别("TARGET_TYPE"),
//
//    /**
//     * 担保方式
//     */
//    担保方式("GUA_TYPE"),
//
//    /**
//     * 基准利率种类 该要素对于浮动收益类资产为必填，对于固定收益类资产无须填写。
//     */
//    基准利率("BASIS_TYPE"),
//
//    /**
//     * 购买地
//     */
//    购买地("BUY_PLACE"),
//
//    /**
//     * 所属国家或地区
//     */
//    所属国家或地区("COUNTRY_AREA"),
//
//    /**
//     * 分配方案
//     */
//    分配方案("DIS_SCHE"),
//
//    /**
//     * 分配方式
//     */
//    分配方式("DIS_WAY"),
//
//    /**
//     * 校验结果
//     */
//    校验结果("CHECKRESULT"),
//
//    /**
//     * 小数处理位数
//     */
//    小数处理位数("DECIMAL_DIGIT"),
//
//    /**
//     * 数据状态
//     */
//    数据新增修改("DATASTATUS"),
//
//    /**
//     * 公司性质
//     */
//    公司性质("COMPANY_NATURE"),
//
//    /**
//     * 核算分类
//     */
//    核算分类("PARTY_STL_ID"),
//
//    /**
//     * 市场机构分类
//     */
//    市场机构分类("MARKET_TYPE"),
    /**
     * 交易市场
     */
    交易市场("SHARES_TRADE_MARKET"),
//    /**
//     * 上市板
//     */
//    上市板("LIST_PLATE"),
//    /**
//     * 股票类型
//     */
//    股票类型("SHARES_TYPE"),
//    /**
//     * 曲线类型
//     */
//    曲线类型("CURVE_TYPE"),
//    /**
//     * 行政区划_省
//     */
//    行政区划_省("ADMINISTRATIVE_TYPE1"),
//    /**
//     * 行政区划_市
//     */
//    行政区划_市("ADMINISTRATIVE_TYPE2"),
//    /**
//     * 行政区划_县
//     */
//    行政区划_县("ADMINISTRATIVE_TYPE3"),
//    /**
//     * 类型_票据或信用证资产
//     */
//    类型_票据或信用证资产("TYPE_BILE"),
//    /**
//     * 外汇类型
//     */
//    外汇类型("EXCHANGE_TYPE"),
//    /**
//     * 客户性质
//     */
//    客户性质("CUSTOMER_NATURE"),
//    /**
//     * 客户类型
//     */
//    客户类型("CUSTOMER_TYPE"),
//    /**
//     * 录入方式
//     */
//    录入方式("INPUT_TYPE"),
//
//    /**
//     * 计划资产类型
//     */
//    计划资产类型("PLAN_ASSET_TYPE"),
//    /**
//     * 计划收益类型
//     */
//    计划收益类型("PLAN_PROFIT_TYPE"),
//    /**
//     * 基金类型
//     */
//    基金类型("FUND_TYPE"),
//    /**
//     * 生效时点
//     */
//    生效时点("ACTIVE_TIMING"),
//    /**
//     * 生效时期
//     */
//    生效时期("ACTIVE_PERIOD"),
//    /**
//     * 生效条件
//     */
//    生效条件("ACTIVE_CDN"),
//    /**
//     * 层级信息
//     */
//    层级信息("HIERARCHY_INFO"),
//    /**
//     * 分红频率
//     */
//    分红频率("BONUS_CYCLE"),
//    /**
//     * 分红频率（计划类资产货币型）
//     */
//    计划类资产货币型分红频率("BONUS_CYCLE_PLAN_CURR"),
//
//    /**
//     * 资产净值日类型
//     */
//    资产净值日类型("AST_VLU_DATE_TYPE"),
//    /**
//     * 原因类型
//     */
//    原因类型("CAUSE_TYPE"),
//    /**
//     * 估值来源
//     */
//    估值来源("VALUE_SOURCE"),
//    /**
//     * 金额单位
//     */
//    金额单位("MONEY_UNIT"),
//    /**
//     * 外聘类型
//     */
//    外聘类型("EXTER_TYPE"),
//    /**
//     * 节假日市场
//     */
//    节假日市场("HOLIDAY_MARKET"),
//    /**
//     * 固定间隔时间单位
//     */
//    固定间隔时间单位("INTERVAL_TIME_UNIT"),
//    /**
//     * 结构型文档类型
//     */
//    结构型文档类型("FILE_TYPE_Y_SUBJECT"),
//    /**
//     * 开放式预期型文档类型
//     */
//    开放式预期型文档类型("FILE_TYPE_Y_OPEN"),
//    /**
//     * 封闭式预期型文档类型
//     */
//    封闭式预期型文档类型("FILE_TYPE_Y_CLOSED"),
//    /**
//     * 净值型文档类型
//     */
//    净值型文档类型("FILE_TYPE_N"),
//    /**
//     * 利息分配方式
//     */
//    利息分配方式("INTPAY_RULE"),
//    /**
//     * 日期号
//     */
//    日期号("DAY_OF_MONTH"),
//    /**
//     * 工作日号
//     */
//    工作日号("WORKDAY_OF_MONTH"),
//    /**
//     * 分红方式
//     */
//    分红方式("BONUS_MODE"),
//    /**
//     * 流通方式
//     */
//    流通方式("CIRCULATION_MODE"),
//    /**
//     * 委托状态（投资经理）
//     */
//    委托状态_投资经理("ENTRUST_STATUS_IMGR"),
//    /**
//     * 委托状态（交易员）
//     */
//    委托状态_交易员("ENTRUST_STATUS_TRADER"),
//    /**
//     * 除权状态
//     */
//    除权状态("DEL_STATUS"),
//    /**
//     * 日期（日）
//     */
//    日期_日("DATE_DAY"),
//    /**
//     * 推算方法(开放计划)
//     */
//    开放计划推算方法("CALC_METHOD_OPEN_PLAN"),
//    /**
//     * 计提频率类型
//     */
//    计提频率类型("ACCR_DAY_TYPE"),
//    /**
//     * 账套类型
//     */
//    账套类型("ACCOUNTSET_TYPE"),
//    /**
//     * 数据来源（交易）
//     */
//    数据来源_交易("DATA_SOURCE_TRAN"),
//    /**
//     * 通道类型
//     */
//    通道类型("CHANNEL_TYPE"),
//    /**
//     * 收取方式
//     */
//    收取方式("COLLECT_MODE"),
//    /**
//     * 息票品种
//     */
//    息票品种("COUPON_SPECIES"),
//
//    /**
//     * 资产存续期类型
//     */
//    资产存续期类型("ASSET_D_DEAL_TYPE"),
//
//    /**
//     * 收支方向（产品端）
//     */
//    产品端收支方向("PS_PRODUCT"),
//
//    /**
//     * 收支方向（资产端）
//     */
//    资产端收支方向("PS_ASSET"),
//
//    /**
//     * 账户类型（其他账户收息）
//     */
//    其他账户收息账户类型("ACCT_TYPE_OTHER"),
//
//
//    /**
//     * 交易方向(风险准备金)
//     */
//    风险准备金交易方向("TRADE_DIRECTION"),
//
//    /**
//     * 交易场所
//     */
//    交易场所("TRADE_PLACE"),
//
//    /**
//     * 交易类型（投资端）
//     */
//    投资端交易类型("TRADE_TYPE_INVEST"),
//
//    /**
//     * 分红方案
//     */
//    分红方案("BONU_SCALC_FLAG"),
//    /**
//     * 筛选条件
//     */
//    筛选条件("FILTER_CONDITION"),
//
//    /**
//     * 统计报表_报送状态
//     */
//    报送状态("SUBMIT_STATUS"),
//
//    /**
//     * 统计报表_反馈状态
//     */
//    反馈状态("FEEDBACK_STATUS"),
//    /**
//     * 行内行外
//     */
//    行内行外("IN_OUT_BANK"),
//    /**
//     * 内部职能
//     */
//    内部职能("DEPT_FUN"),
//    /**
//     * 分红类型
//     */
//    分红类型("BONUS_TYPE"),
//    /**
//     * 债券分类
//     */
//    债券分类("SEC_TYPE"),
//
//    /**
//     * 层级
//     */
//    层级("HIERARCHY"),
//
//    /**
//     * 行权状态
//     */
//    行权状态("EXEC_TYPE"),
//
//    /**
//     * 行权方式
//     */
//    行权方式("EXEC_MODE"),
//
//    /**
//     * 交易方式
//     */
//    交易方式("DEAL_MODE"),
//
//    /**
//     * 冲销标志
//     */
//    冲销标志("OFFSET_FLAG"),
//
//    /**
//     * 质押状态
//     */
//    质押状态("PLEDGED_STATE"),
//
//    /**
//     * 计息基础
//     */
//    计息基础("BASIS"),
//
//    /**
//     * 计息基础（债券类资产）
//     */
//    计息基础_债券类资产("BASIS_BOND"),
//
//    /**
//     * 计息基础（底层资产）
//     */
//    计息基础_底层资产("BASIS_BOTTOM"),
//
//    /**
//     * 数据类型
//     */
//    数据类型("DATA_TYPE"),
//
//    /**
//     * 交易状态（市场交易）
//     */
//    交易状态_市场交易("MARKET_TRADE_STATUS"),
//    /**
//     * 借贷方向
//     */
//    借贷方向("CD_FLAG"),
//    /**
//     * 表外借贷方向
//     */
//    表外借贷方向("CD_FLAG_OFF_BALANCE"),
//    /**
//     * 质押品操作类型
//     */
//    质押品操作类型("PLEDGE_OPE_TYPE"),
//    /**
//     * 质押品类型
//     */
//    质押品类型("PLEDGE_TYPE"),
//    /**
//     * 质押品类型
//     */
//    分红计划推算方法("CALC_METHOD_BONUS_PLAN"),
//    /**
//     * 基金类型_基金估值
//     */
//    基金类型_基金估值("FUND_NM_TYPE"),
//    /**
//     * 科目类型
//     */
//    科目类型("SUBJECT_TYPE"),
//
//    /**
//     * 拆借方向
//     */
//    拆借方向("LIBOR_DIRECTION"),
//
//    /**
//     * 交易状态（内部拆借）
//     */
//    交易状态_内部拆借("LIBOR_TRADE_STATUS"),
//
//    /**
//     * 收支方向
//     */
//    收支方向("IE_FLAG"),
//
//    /**
//     * 境内托管机构名称
//     */
//    境内托管机构名称("IN_DEP_NAME"),
//
//    /**
//     * 会计场景代码
//     */
//    会计场景代码("TABLE_ID"),
//    /**
//     * 批处理一览
//     */
//    批处理一览("BATCH_LIST"),
//    /**
//     * 自动处理标志
//     */
//    自动处理标志("AUTO_DEAL_FLAG"),
//    /**
//     * 计提方式
//     */
//    计提方式("ACCURE_MODE"),
//
//    /**
//     * 账务记账类型
//     */
//    账务记账类型("ACCOUNT_TYPE"),
//
//    /**
//     * 确认状态
//     */
//    确认状态("DIS_CONFIRM_STATUS"),
//     /**
//      * 
//      */
//    信息披露状态("REVEAL_STATUS"),
//    
//    
//
//    /**
//     * 异常状态
//     */
//    异常状态("EXCEPTION_STATUS"),
//
//    /**
//     * 发送状态(清算指令)
//     */
//    清算指令_发送状态("SQUARE_SEND_STATUS"),
//
//    /**
//     * 区分类型
//     */
//    区分类型("DISTINGUISH_TYPE"),
//
//    /**
//     * 指令业务类型
//     */
//    指令业务类型("ORDER_BUSINESS_TYPE"),
//
//    /**
//     * 限额要素类型
//     */
//    限额要素类型("QUOTA_FACTOR_TYPE"),
//
//    /**
//     * 拆借精度
//     */
//    拆借精度("LIBOR_PREC"),
//
//    /**
//     * 拆借精度计算值
//     */
//    拆借精度计算值("LIBOR_PREC_CALC_VALUE"),
//
//    /**
//     * 内外部交易
//     */
//    内外部交易("INTERNAL_EXTERNAL_TRADE"),
//
//    /**
//     * 交易分类
//     */
//    交易分类("TRANSACTION_CLASSIFICATION"),
//
//    /**
//     * 质押方向
//     */
//    质押方向("PLEDGE_PS"),
//
//    /**
//     * 划款类型
//     */
//    划款类型("PAYER_TYPE"),
//
//    /**
//     * 结转状态
//     */
//    结转状态("CARRYOVER_STATUS"),
//
//    /**
//     * 计提类型
//     */
//    计提类型("ACCR_TYPE"),
//
//    /**
//     * 科目性质
//     */
//    科目性质 ("SUBJECT_PROPERTY"),
//
//    /**
//     * 账务类型（产品）
//     */
//    产品账务类型("ACCOUNT_TYPE_PRODUCT"),
//
//    /**
//     * 限额类型
//     */
//    限额类型("LIMIT_TYPE"),
//
//    /**
//     * 票据类/信用证类资产类型
//     */
//    票据类信用证类资产类型("ASSET_TYPE_BILL_LETTERCREDIT"),
//
//    /**
//     * 还本方式
//     */
//    还本方式("REPAY_TYPE"),
//
//    /**
//     * 投资目的
//     */
//    投资目的("INVESTMENT_PURPOSE"),
//
//    /**
//     * 频率单位
//     */
//    频率单位("FREQUENCY_UNIT"),
//
//    /**
//     * 期限分层类型
//     */
//    期限分层类型("TERM_LAYER_TYPE"),
//
//    /**
//     * 分配方式（项目类资产）
//     */
//    项目类资产分配方式("DIS_WAY_PROJECT"),
//
//    /**
//     * 计划类型
//     */
//    计划类型("PLAN_TYPE"),
//
//    /**
//     * 是否自动覆盖
//     */
//    是否自动覆盖("IS_AUTO_COVER"),
//
//    /**
//     * 业务分类
//     */
//    业务分类("BUSINESS_CLASSIFICATION"),
//
//
//    /**
//     * 业务种类
//     */
//    业务种类("BUSINESS_TYPE"),
//
//
//    /**
//     * 机构所属行业一级分类
//     */
//    机构所属行业一级分类("INSTITUTION_INDUSTRY_FIRST_LEVEL"),
//
//    /**
//     * 机构所属行业二级分类
//     */
//    机构所属行业二级分类("INSTITUTION_INDUSTRY_SECOND_LEVEL"),
//
//    /**
//     * 债券记账类型
//     */
//    债券记账类型("BOOKS_TYPE_BOND"),
//
//    /**
//     * 是否符合该产品说明成立条件
//     */
//    是否符合该产品说明成立条件("IS_FIT_PROD_DESC_SETUP_CON"),
//
//    /**
//     * 清算速度
//     */
//    清算速度("ACCOUNT_SPEED"),
//
//    /**
//     * 结算方式
//     */
//    结算方式("SETTLEMENT_WAY"),
//
//    /**
//     * 展示维度
//     */
//    展示维度("DISPLAY_DIMENSION"),
//
//    /**
//     * 展示方式（清算指令）
//     */
//    清算指令展示方式("DISPLAY_MODE_SQUARE_ORDER"),
//
//    /**
//     * 债券类型
//     */
//    债券类型("BOND_TYPE"),
//
//    /**
//     * 增信方式
//     */
//    增信方式("INCREASE_TRUST_MODE"),
//
//    /**
//     * 资金用途
//     */
//    资金用途("FUND_USE"),
//
//    /**
//     * 业务模式
//     */
//    业务模式("BUSINESS_MODEL"),
//
//    /**
//     * 交易场所类别
//     */
//    交易场所类别("TRADE_PLACE_TYPE"),
//
//    /**
//     * 资金投向（项目类资产）
//     */
//    项目类资产资金投向("CAPITAL_INVESTMENT_PROJECT"),
//
//    /**
//     * 产品状态
//     */
//    产品状态("PRODUCT_STATUS"),
//
//    /**
//     * 显示方式
//     */
//    显示方式("DISPLAY_MODE"),
//
//    /**
//     * 基础资产推荐部门
//     */
//    基础资产推荐部门("BASIC_ASSET_RECOMMENDED_DEPARTMENT"),
//
//    /**
//     * 提醒状态
//     */
//    提醒状态("ALERT_STATUS"),
//
//    /**
//     * 对账结果
//     */
//    对账结果("RECONCILIATION_RESULT"),
//
//    /**
//     * 自定义现金流类型
//     */
//    自定义现金流类型("CUSTOM_CASHFLOW_TYPE"),
//
//    /**
//     * 转仓类型
//     */
//    转仓类型("TRANSFER_TYPE"),
//
//    /**
//     * 记账凭证业务类别
//     */
//    记账凭证业务类别("ACCOUNTING_CER_BUSINESS_CATEGORY"),
//
//    /**
//     * 债券核算分类
//     */
//    债券核算分类("BOND_ACCOUNTING_CLASSIFICATION"),
//
//    /**
//     * 交易状态（拆分）
//     */
//    拆分交易状态("TRADE_STATUS_RESOLUTE"),
//
//    /**
//     * 账户类型（投资账户用）
//     */
//    投资账户用账户类型("ACCT_TYPE_INVEST_ACCOUNT"),
//
//    /**
//     * 具体投向
//     */
//    具体投向("SPECIFIC_INVESTMENT"),
//
//    /**
//     * 中债用数据状态
//     */
//    中债用数据状态("DATA_STATUS_CHINA_BOND"),
//
//    /**
//     * 交易目的
//     */
//    交易目的("TRADE_PURPOSE"),
//
//    /**
//     * 委托方
//     */
//    委托方("ENTRUSTING_PARTY"),
//
//    /**
//     * FOF投向
//     */
//    FOF投向("FOF_INVEST"),
//
//    /**
//     * 规律开放周期
//     */
//    规律开放周期("REGULAR_OPEN_CYCLE"),
//
//    /**
//     * 有效状态（银证转账管理）
//     */
//    银证转账有效状态("BAS_TRA_STATUS"),
//
//    /**
//     * 财务报表类型
//     */
//    财务报表类型("FINANCIAL_STATEMENTS_TYPE"),
//
//    /**
//     * 财务报表日期类型
//     */
//    财务报表日期类型("FINANCIAL_STATEMENTS_DATE_TYPE"),
//
//    /**
//     * 互换方式
//     */
//    互换方式("SWAP_MODE"),
//
//    /**
//     * 利率互换有效状态
//     */
//    利率互换有效状态("INT_RATE_SWAP_MATURITY_STATUS"),
//
//    /**
//     * 计算方式
//     */
//    计算方式("CALCULATION_METHOD"),
//
//    /**
//     * 观察频率单位
//     */
//    观察频率单位("OBS_CYCLE_UNIT"),
//
//    /**
//     * 接口系统ID
//     */
//    接口系统ID("INTERF_ID"),
    /**
     * 接口类型
     */
    接口类型("INTERF_TYPE"),
//    /**
//     * 接口执行状态
//     */
//    接口执行状态("INTERF_TASK_STATUS"),
//    /**
//     * 预警标示
//     */
//    预警标示("ISNEEDWARNING"),
//    /**
//     * 交易方向（利率互换到期）
//     */
//    利率互换到期交易方向("TRADE_DIRECTION_INT_RATE_SWAP"),
//    /**
//     * 合同编号设置方式
//     */
//    合同编号设置方式("CONTRACT_NUMBER_SETTING_METHOD"),
//    /**
//     * 交易员
//     */
//    交易员("TRADER_ID"),
//    /**
//     * 交易类型（产品存续期登记）
//     */
//    产品存续期登记交易类型("DEAL_TYPE_PRD_LIFE_PERIOD_REG"),
//    /**
//     * 汇总维度
//     */
//    汇总维度("SUMMARY_DIMENSION"),
//    /**
//     * 估值类型
//     */
//    估值类型("VALUE_TYPE"),
//    /**
//     * 明细区分
//     */
//    明细区分("DETAIL_DIST"),
//    /**
//     * 结构性产品账务类型
//     */
//    结构性产品账务类型("STRUCT_PROD_ACCOUNT_TYPE"),
//    /**
//     * 数据来源的开关
//     */
//    数据来源的开关("DATA_SOURCE_SWITCH"),
//    /**
//     * 数据来源（产品）
//     */
//    产品数据来源("PRD_ITEM_SCR"),
//    /**
//     * 文档类型HF
//     */
//    文档类型HF("FILE_TYPE_HF"),
//    /**
//     * 累计值计算的开关
//     */
//    累计值计算的开关("CUMULATIVE_VALUE_CALC_SWITCH"),
//    /**
//     * 交易对手类型（结构化利率互换交易）
//     */
//    结构化利率互换交易对手类型("COUNTERPARTY_TYPE_STRUCT_INT_SWAP"),
//    /**
//     * 资产/负债类型
//     */
//    资产要素登记资产负债类型("REGIST_ASSET_LIABILITY_TYPE"),
//    /**
//     * 记账网点
//     */
//    记账网点("ACCOUNTING_NETWORK"),
//    /**
//     * 金额显示精度
//     */
//    金额显示精度("AMOUNT_DISPLAY_ACCURACY"),
//    /**
//     * 发送状态(内部拆借)
//     */
//    发送状态_内部拆借("LIBOR_SEND_STATUS"),
//    /**
//     * 电子对账类型
//     */
//    电子对账类型("EC_CHECK_TYPE"),
//    /**
//     * 电子对账结果
//     */
//    电子对账结果("EC_CHECK_RESULT"),
//    /**
//     * 处理状态（清算指令）
//     */
//    处理状态_清算指令("PROCESSING_STATE_SQUARE_ORDER"),
//    /**
//     * 撤销状态（清算指令）
//     */
//    撤销状态_清算指令("REVOKE_STATE_SQUARE_ORDER"),
//    /**
//     * 估值场景分类
//     */
//    估值场景分类("DEALTYPE_EXC"),
//    /**
//     * 确认收支
//     */
//    确认收支("BALANCE"),
//    /**
//     * 利息费用业务类型（一级）
//     */
//    利息费用一级业务类型("INTCOST_BUSINESS_TYPE_FIRSTLEVEL"),
//    /**
//     * 利息费用业务类型（二级）
//     */
//    利息费用二级业务类型("INTCOST_BUSINESS_TYPE_SECONDLEVEL"),
//    /**
//     * 报表展现方式
//     */
//    报表展现方式("REPORT_DISPLAY_MODE"),
//    /**
//     * 估值对象
//     */
//    估值对象("VALUATION_OBJECT"),
//    /**
//     * 业务类型（资产余额）
//     */
//    资产余额业务类型("BUSINESS_TYPE_ASSET_BALANCE"),
//    /**
//     * 汇总维度（债券头寸查询）
//     */
//    债券头寸查询汇总维度("SUMMARY_DIMENSION_BOND_POSITION"),
//    /**
//     * 结算状态
//     */
//    结算状态("SETTLEMENT_STATUS"),
//    /**
//     * 有无
//     */
//    有无("HAVE_OR_NOT"),
//    /**
//     * 资金流动类型
//     */
//    资金流动类型("FUND_FLOW_TYPE"),
//    /**
//     * 客户分类
//     */
//    客户分类("CUSTOMER_CLASSIFICATION"),
//
//
//
//
//    // tangzj add 2017/05/25 start
//    /**
//     * 产品一级分类
//     */
//    产品一级分类("PRODUT_LEVEL1"),
//    /**
//     * 产品二级分类
//     */
//    产品二级分类("PRODUT_LEVEL2"),
//      /**
//     * 产品三级分类
//     */
//    产品三级分类("PRODUT_LEVEL3"),
//    /**
//     * 管理方式
//     */
//    管理方式("MANAGE_STYLE"),
//    /**
//     * 质押股票性质
//     */
//    质押股票性质("STOCK_NATURE"),
//    /**
//     * 结构化优先级分类
//     */
//    结构化优先级分类("STRUCT_PRIORITY"),
//    /**
//     * 结构类型
//     */
//    结构类型("STRUCT_TYPE"),
//    /**
//     * 通道出回款划款类型
//     */
//    通道出回款划款类型("CHANNEL_COLLECTION_TYPE"),
//    /**
//     * 通道出回款有效状态
//     */
//    通道出回款有效状态("CHANNEL_COLLECTION_STATUS"),
//    /**
//     * 组合收益类型
//     */
//    组合收益类型("GROUP_PROFIT_TYPE"),
//    /**
//     * 处理方式
//     */
//    处理方式("HANDLING_MODE"),
//    /**
//     * 债券移动平均处理类型
//     */
//    债券移动平均处理类型("BOND_MOVE_AVG_TYPE"),
//    // tangzj add 2017/05/25 end
//
//    /**
//     * 下发状态
//     */
//    下发状态("DECENTRALIZED_STATE"),
//
//    /**
//     * 资产来源
//     */
//    资产来源("ASSET_SOURCE"),
//
//    /**
//     * 资产类型_推荐费
//     */
//    资产类型_推荐费("ASSET_TYPE_REFE_FEE"),
//
//    /**
//     * 期限_推荐费
//     */
//    期限_推荐费("TERM_REFE_FEE"),
//
//    /**
//     * 信用资质类型
//     */
//    信用资质类型("CREDIT_QUAL_TYPE"),
//    /**
//     * 评级来源
//     */
//    评级来源("SECRATING_SCR"),
//    /**
//     * 评级期限
//     */
//    评级期限("SECRATING_TERM"),
//    /**
//     * 评级类型
//     */
//    评级类型("SECRATING_TYPE"),
//     /**
//     * 评级类型
//     */
//    交易方向("TRANSACTION_DIRECTION"),
//
//    /**
//     * WIND一级分类
//     */
//    WIND一级分类("WINDTYPEONE"),
//    /**
//     * WIND一级分类
//     */
//    WIND二级分类("WINDTYPETWO"),
//    /**
//     * 交易处理方式
//     */
//    交易处理方式("DEALMODE"),
//    /**
//     * 模拟类型
//     */
//    模拟类型("MOCK_TYPE"),
//
//    /**
//     * 通道活期利息
//     */
//    通道活期利息("COMMON_BUSINESS_TYPE"),
//
//    /**
//     *产品台账关注类型
//     */
//    产品台账关注类型("PRD_LEDGER_CONCERNS_TYPE"),
//    /**
//     *交易性质
//     */
//    交易性质("DEAL_NATURE"),
//    /**
//     *产品台账关注类型
//     */
//    配置性质("CONFIG_NATURE"),
//    /**
//     *产品台账关注类型
//     */
//    流动性管理性质("FLOW_MANAGE_NATURE"),
//    /**
//     *维度
//     */
//    维度("DIMENSION"),
//
//    /**
//     *是否到期
//     */
//    是否到期("IS_EXPIRE"),
//    /**
//     *冲销状态
//     */
//    冲销状态("REVERSE_STATUS"),
//    /**
//     *资产一级分类_行内
//     */
//    资产一级分类_行内("ASSET_ONETYPE_IN"),
//    /**
//     *资产二级分类_行内
//     */
//    资产二级分类_行内("ASSET_TWOTYPE_IN"),
//    /**
//     * 集中交易处报表 期限结构
//     */
//    集中_期限结构规则("TERM_STRUCT_RULE"),
//    /**
//     * 所属总行或分支行
//     */
//    所属总行或分支行("HEAD_OFFICE_OR_BRANCH_BANK"),
//    /**
//     * 规划类型
//     */
//    规划类型("PLANNING_TYPE"),
//
//    /**
//     * 凭证处理状态
//     */
//    凭证处理状态("PROCESSING_STATE_CERTIFICATE"),
//
//    /**
//     * 名单类型  add by zhuruqing 2017-07-26
//     */
//    名单类型("LIST_TYPE"),
//
//    /**
//     * 操作类型 add by zhuruqing 2017-08-03
//     */
//    操作日志_操作类型("OPERATE_STATUS"),
//
//    /**
//     * 主题评级展望
//     */
//    主题评级展望("SUBJECT_RATING_STATUS"),
//
//    /**
//     * 专属产品
//     */
//    专属产品("EXCLUSIVE_PRODUCT"),
//    /**
//     * 五级分类
//     */
//    五级分类("FIVE_TYPE"),
//    /**
//     * 净值型文档类型HF
//     */
//    净值型文档类型HF("FILE_TYPE_NET_HF"),
//    /**
//     * 净值型文档类型HF
//     */
//    非净值型文档类型HF("FILE_TYPE_NONET_HF"),
//
//    /**
//     * 交易类型(付息、还本、到期)
//     */
//    交易类型("TRADE_TYPE"),
//    /**
//     * 份额登记确认状态
//     */
//    份额登记确认状态("SHARE_CONFIRM_STATUS"),
//    /**
//     * 比较符号
//     */
//    比较符号("COMPARE_SYMBOL"),
//    /**
//     * 比较符号
//     */
//    指定成本("COST_DESIGNATION"),
//
//    /**
//     * 头寸资产类型
//     */
//    头寸资产类型("POSITION_ASSET_TYPE"),
//
//    /**
//     * 资产类型
//     */
//    资产类型("ASSET_TYPE"),
//    /**
//     * 该权益人是否属于本行
//     */
//    该权益人是否属于本行("IS_BELONG_BANK"),
//
//    /**
//     * 权益人类别
//     */
//    权益人类别("HOLDER_TYPE"),
//
//    /**
//     * 个人证件类别
//     */
//    个人证件类别("PERSONAL_IDENTIFY_TYPE"),
//
//    /**
//     * 机构证件类别
//     */
//    机构证件类别("INSTITUTION_IDENTIFY_TYPE"),
//
//    /**
//     * SPV资金托管账户开户行
//     */
//    SPV资金托管账户开户行("SPV_CAP_ACC_BANK"),
//
//    /**
//     * 权益人境内外标识
//     */
//    权益人境内外标识("HOLDER_MARK"),
//    /**
//     * 风险偏好
//     */
//    风险偏好("HOLDER_RISK_PREFER"),
//
//    /**
//     * 月报权益人类别
//     */
//    月报权益人类别("MONTH_HOLDER_TYPE"),
//    /**
//     * 报表名称
//     */
//    报表名称("REPORT_CODE"),
//    /**
//     * 月报权益人类别
//     */
//    细分类型("TYPE_DETAIL"),
//
//    /**
//     * 报送反馈状态
//     */
//    报送反馈状态("SUBMIT_FEEDBACK_STATUS"),
//
//    /**
//     * 投资者类型
//     */
//    投资者类型("INVESTOR"),
//    /**
//     * 省或地区
//     */
//    省("PROVINCE"),
//    /**
//     * 权益人数据类型
//     */
//    权益人数据类型("TYPE"),
//    /**
//     * 市场日历类型
//     */
//    市场日历类型("MARKET_CALENDAR_TYPE"),
//    /**
//     * 权益人所属国家或地区
//     */
//    权益人所属国家或地区("HOLDER_REGION"),
    /**
     * 维护数据状态
     */
    维护数据状态("MAINTAIN_STATUS"),
//    /**
//     * 报送数据状态
//     */
//    报送数据状态("SUBMITTED_STATUS"),
//    /**
//     * 数据来源
//     */
//    数据来源("DATA_SOURCE"),
//
//	/**
//     *报送时点类型
//     */
//    报送时点类型("SUBMIT_TIMETYPE"),
//    /**
//     * 推送方式
//     */
//    推送方式("SUBMIT_REQUIRE2"),
//
//    /**
//     * 变动类型
//     */
//    变动类型("CHANG_TYPE"),
//
//    /**
//     * 产品风险等级
//     */
//    产品风险等级("PRODUCT_RISK_GRADE"),
//    /**
//     * 持有信息数据来源
//     */
//    持有信息数据来源("HOLD_INFO_SOURCE"),
//    /**
//     * 检查结果
//     */
//    检查结果("CHECK_RESULT"),
//    /**
//     * 上游接口文件读取处理状态
//     */
//    上游接口文件读取处理状态("UPINTERF_FILE_READ_STATUS"),
//    /**
//     * 上游接口文件类型
//     */
//    上游接口文件类型("UPINTERF_FILE_TYPE"),
//    /**
//     * 循环类型
//     */
//    报表类型("REP_TYPE"),
//    /**
//     * 循环类型
//     */
//    循环类型("LOOP_TYPE"),
//    /**
//     * 运行状态
//     */
//    运行状态("JOB_STATUS"),
//    /**
//     * 批处理信息披露状态
//     */
//    批处理信息披露状态("EXEC_STATUS"),
//    /**
//     * 执行结果
//     */
//    执行结果("EXEC_RESULT"),
//    /**
//     * 执行结果详细表
//     */
//   执行结果详细表("EXEC_RESULT_DETAIL"),
//    /**
//     * 信息披露报表类型
//     */
//    信息披露报表类型("REP_INFO_TYPE"),
//    
//    
//    /**
//     * 披露频率
//     */
//    披露频率("REVEAL_FREQUENCY"),
//    /**
//     * 基准日
//     */
//    基准日("BASIS_DATE_TYPE"),
    /**
     * 日期类型
     */
    日期类型("DIS_DATE_TYPE"),
//    /**
//     * 报表数据类型
//     */
//    报表数据类型("REPORT_DATA_TYPE"),
//    /**
//     * 取数范围
//     */
//    取数范围("DATA_RANGE"),
//    /**
//     * 文件类型
//     */
//    文件类型("DIS_FILE_TYPE"),
//    
//    /**
//     * 披露渠道
//     */
//   披露渠道("REVEAL_CHANNEL"),
//   /**
//    * 产品投资性质
//    */
//   产品投资性质("PROD_INVEST_TYPE"),
//   	/**
//   	 * 报表状态
//   	 */
//   报表启用状态("REPORT_TYPE"),
   /**
    * 文件类型
    */
   日志状态("LOG_STATUS"),
   /**
    * 生成时点类型
    */
   生成时点类型("MAKE_TYPE"),
//   /**
//    * 报表类别
//    */
//   报表类别("REPORT_STYLE"),
////	add by longhao 2018/12/04 start
//	/**
//	 * 取数开始日
//	 */
//	取数开始日("VALUE_STARTDAY"),
//	/**
//	 * 确认方式
//	 */
//	确认方式("CONFIRM_TYPE"),
//	/**
//	 * 披露方式
//	 */
//	披露方式("REVEAL_TYPE"),
//	/**
//	 * 时点类型
//	 */
//	时点类型("TIME_TYPE"),
//	/**
//     * 时点类型生成
//     */
//    时点类型生成("MAKE_TIME_TYPE"),
//	/**
//	 * 取数日
//	 */
//	取数日("VALUE_STARTDAY"),
////	add by longhao 2018/12/04 end
//	/**
//	 * 作业规则1
//	 */
//	作业规则1("RULE_TYPE1"),
//	/**
//	 * 作业规则1
//	 */
//	作业规则2("RULE_TYPE2"),
//	/**
//	 * 作业规则1
//	 */
//	作业规则3("RULE_TYPE3"),
//	/**
//	 * 收益类型
//	 */
//	收益类型("PROFIT_TYPE"),
//	/**
//	 * 募集方式
//	 */
//	募集方式("RAISE_TYPE"),
	/**
	 * 规则类型
	 */
	规则类型("JOB_RANGE_FLAG"),
//	/**
//	 * 指标类型
//	 */
//	指标类型("INDEX_TYPE"),
//	/**
//	 * 执行标志
//	 */
//	执行标志("EXEC_FLAG"),
//	
//	
//	/**
//     * 数据状态
//     */
//    报表模板种类("REPORT_MODEL_TYPE"),
//    
//    /**
//     * 估值表类型
//     */
//    估值表类型(
//        "VAL_TABLE_TYPE"
//    ),
//    /**
//     * 金融产品类型
//     */
//    金融产品类型(
//        "FINPROD_TYPE"
//    ),
//    /**
//     * 查询范围
//     */
//    查询范围(
//        "QUERY_SCOPE"
//    ),
//    /**
//     * 查询类型
//     */
//    查询类型(
//        "QUERY_TYPE"
//    ),
//    /**
//     * 基础数据类型
//     */
//    基础数据类型(
//        "BASE_DATA_TYPE"
//    ),
//    /**
//     * 查询数据类型
//     */
//    查询数据类型(
//        "QUERY_DATA_TYPE"
//    ),
//    /**
//     * 参数类型
//     */
//    参数类型(
//        "PARAM_TYPE"
//    ),
//    /**
//     * 校验位置
//     */
//    校验位置(
//        "CHECK_FOR"
//    ),
//    上游数据来源(
//            "DIS_DATA_SOURCE"
//        ),
//    现金流一级类型(
//            "CASH_TYPE_1"
//        ),
//    /**
//     * 披露渠道
//     */
//   报表数据格式类型("DIS_DATA_TYPE"),
	 /**
	    * 统计维度
	    */
	   统计维度("STATISTICAL_DIMENSION"),
	    ;
    ;

    /**
     * 对应于数据字典code
     */
    private String value;

    DisclosureDictCode(String value) {
        this.value = value;
    }

    /**
     * @param value 数据字典值
     * @return DictCode
     * @see
     * @since 1.0
     */
    public static DisclosureDictCode get(String value) {
        DisclosureDictCode[] values = DisclosureDictCode.values();
        for (DisclosureDictCode code : values) {
            if (StringUtil.equals(value, code.getValue())) {
                return code;
            }
        }
        return null;
    }

    /**
     * 获取 value
     *
     * @return value.
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     *
     * @param value value
     */
    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 通过value生成枚举
     *
     * @param value value值
     * @return DictCode
     */
    public static DisclosureDictCode fromValue(String value) {
        for (DisclosureDictCode dictCode : DisclosureDictCode.values()) {
            if (dictCode.getValue().equals(value)) {
                return dictCode;
            }
        }
        return null;
    }
}
