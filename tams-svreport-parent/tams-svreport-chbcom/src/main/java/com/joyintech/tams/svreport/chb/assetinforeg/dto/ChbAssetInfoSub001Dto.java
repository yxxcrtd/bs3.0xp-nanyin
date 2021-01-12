package com.joyintech.tams.svreport.chb.assetinforeg.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_ASSET_INFO_SUB表的实体类，用于后台批处理调度
 * @version
 * @author:  Administrator
 * @创建时间: 2019-06-17
 */
public class ChbAssetInfoSub001Dto {
    /**
     * 登记序号
     */
    private String registSeqno;

    /**
     * 行内资产负债编码
     */
    private String assetCode;

    /**
     * 资产/负债类别 01本行/他行存款||大额存单 02拆放同业/买入返售（资产）||同业拆入/卖出回购（负债） 03债券类资产/理财直接融资工具/同业存单 04非标准化债权类资产/信贷资产流转项目 05	票据类资产/信用证类资产 06权益类资产 07金融衍生品 08QDII债券资产 09QDII拆出/逆回购资产 10QDII股票/基金类资产 11QDII结构性票据类资产 12资产管理产品 13商品类资产 14各类资产的其他项 15公募基金 16委外投资——协议方式 17其他（负债）类资产 18另类资产 19私募基金
     */
    private String assetType;

    /**
     * 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     */
    private String assetType2;

    /**
     * 币种 参考数据字典：CURRENCY_CHB
     */
    private String ccy;

    /**
     * 交易流通场所01 银行间债券市场/02 商业银行柜台市场/03 上海证券交易所/04 深圳证券交易所/05 全国中小企业股份转让系统/06 上海期货交易所/07 郑州商品交易所/08 大连商品交易所/09 中国金融期货交易所/10 上海黄金交易所/11 区域性产权/资产交易市场/12 区域性商品交易所/13 区域性贵金属交易所/14 艺术品市场/15 基金市场/16 信托市场/17全国银行间同业拆借市场 /18 上海票据交易所/99 其他交易流通场所
     */
    private String tradeMarket;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 收益率/年利率
     */
    private BigDecimal assetYield;

    /**
     * 计息基础 01 ACT/366；02 ACT/360；03 ACT/365；04 ACT/ACT；05 30/360
     */
    private String basis;

    /**
     * 金额
     */
    private BigDecimal amt;

    /**
     * 资金存入银行
     */
    private String bankName;

    /**
     * 是否为结构性存款 01是/02否
     */
    private String isStruct;

    /**
     * 结构性存款挂钩标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     */
    private String targetType;

    /**
     * 结构性存款挂钩标的
     */
    private String target;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 具体类别 01 商业银行普通债/ 02 商业银行次级债/ 03 商业银行混合资本债/ 04 商业银行二级资本工具/ 05 证券公司金融债/ 06 证券公司短期融资券/ 07 保险公司金融债/ 08 财务公司金融债/ 09 融资租赁公司金融债/ 10 资产管理公司金融债/ 11 集合企业债/ 12 项目收益债券/ 13 可转换公司债券/ 14 可交换公司债券/ 15 分离交易的可转换公司债券/ 16 短期融资券（CP）/ 17 超短期融资券（SCP）/ 18 中期票据（MTN）/ 19 项目收益票据（PRN）/ 20 中小企业集合票据（SMECN）/ 21非公开定向债务融资工具/ 22 信贷资产支持证券/ 23 资产支持专项计划/ 24 非金融企业资产支持票据（ABN）/ 25 国际金融机构债券/ 26 外国政府债券/ 27 外国金融机构债券/ 28 外国公司债券/ 99 其他债券
     */
    private String chbSectype;

    /**
     * 发行方式 01 公开发行/02 非公开发行
     */
    private String issueMode;

    /**
     * 发行机构主体信用评级
     */
    private String partyGrade;

    /**
     * 发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    private String partyType1;

    /**
     * 发行机构类型（按技术领域划分）01 高新技术企业/99 其他01 高新技术企业/99 其他
     */
    private String partyType3;

    /**
     * 发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    private String partyType2;

    /**
     * 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    private String industry;

    /**
     * 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    private String industryLevel2;

    /**
     * 债券主体评级
     */
    private String secMainGrade;

    /**
     * 债券债项评级
     */
    private String secCreditGrade;

    /**
     * 债券信用评级
     */
    private String secCreditGradeType;

    /**
     * 登记托管机构 01 中央国债登记结算有限责任公司/ 02 上海清算所/03 中国证券登记结算有限责任公司/99 其他
     */
    private String registOrg;

    /**
     * 登记托管机构说明
     */
    private String registorgComment;

    /**
     * 对手方
     */
    private String rivalParty;

    /**
     * 回购标的类别 01 债券/ 02 股票/03 基金类产品/99 其他
     */
    private String targetType1;

    /**
     * 回购标的金额
     */
    private BigDecimal targetAmt;

    /**
     * 所属国家或地区 参考数据字典：COUNTRY_AREA
     */
    private String countryArea;

    /**
     * 收/受益权类型 01 信托贷款/ 02 信贷资产转让/ 03 委托债权/ 04 应收账款/ 05 带回购条款的股权性融资/06 私募债权/ 07 融资融券/ 08 证券公司收益凭证/ 09 委托贷款/ 99 其他
     */
    private String profitType;

    /**
     * 是否属于买入返售 01是/02否
     */
    private String isResale;

    /**
     * 期限（天）
     */
    private BigDecimal termDate;

    /**
     * 是否有预期收益率 01是/02否
     */
    private String rateFlag;

    /**
     * 付息频率（个月/次）
     */
    private Long intpayCycle;

    /**
     * 还本付息说明
     */
    private String repayCmt;

    /**
     * 基准利率种类 参考数据字典：BASIS_TYPE
     */
    private String basisType;

    /**
     * 浮动因子
     */
    private BigDecimal floatFactor;

    /**
     * 利差
     */
    private BigDecimal spreadRate;

    /**
     * 融资人
     */
    private String financie;

    /**
     * 融资人组织机构代码
     */
    private String finOrgCode;

    /**
     * 融资人内部信用评级
     */
    private String fcrGradeIn;

    /**
     * 外部评级机构名称及融资人评级结果
     */
    private String fcrGradeOut;

    /**
     * 融资人类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    private String financierType1;

    /**
     * 融资人类型（按技术领域划分）01 高新技术企业/99 其他
     */
    private String financierType2;

    /**
     * 融资人类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    private String financierType3;

    /**
     * 融资项目
     */
    private String financeProj;

    /**
     * 融资人所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    private String industry1;

    /**
     * 融资人所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    private String industryLevel2Type;

    /**
     * 融资项目所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    private String proIndustry;

    /**
     * 融资项目所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    private String proIndustryLevel2;

    /**
     * 项目是否属于重点监控行业和领域 01 是/ 02 否
     */
    private String industryFlag;

    /**
     * 重点监控行业和领域类别 01 房地产(保障房除外)/ 02 地方政府融资平台/ 03 “铁公基”行业/ 04 “两高一剩”行业/ 99 其他
     */
    private String monitType;

    /**
     * 重点监控行业和领域类别说明
     */
    private String monTypeComm;

    /**
     * 担保方式 01 信用担保/ 02 第三方保证/ 03 抵押/ 04 质押/ 05 信用增级/ 99 其他
     */
    private String guaType;

    /**
     * 担保方式说明
     */
    private String guaTypeRemark;

    /**
     * 融资人主体评级
     */
    private String financierGrade;

    /**
     * 资产内部评级
     */
    private String inGrade;

    /**
     * 资产外部评级
     */
    private String outGrade;

    /**
     * 选择权
     */
    private String chose;

    /**
     * 行权条件说明
     */
    private String conditionCmm;

    /**
     * 融资人所属地区  参考数据字典：COUNTRY_AREA
     */
    private String finArea;

    /**
     * 融资总费率
     */
    private BigDecimal totalFeeRate;

    /**
     * 是否为收/受益权 01是/02否
     */
    private String isRight;

    /**
     * 非标准化债券类资产类型 01 商业承兑汇票/ 02 银行承兑汇票/ 03 其他票据/ 04 混合类票据/ 05 信用证
     */
    private String notstandSecType;

    /**
     * 非标准化债券类资产数量
     */
    private Long notstandSecNum;

    /**
     * 加权剩余期限(天)
     */
    private BigDecimal remainTerm;

    /**
     * 最长剩余期限(天)
     */
    private BigDecimal maxRemainTerm;

    /**
     * 最短剩余期限(天)
     */
    private BigDecimal minRemainTerm;

    /**
     * 股票类型 01 普通股/02 优先股/99 其他
     */
    private String sharesType;

    /**
     * 行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    private String industryShares;

    /**
     * 行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    private String industryShares2;

    /**
     * 投资阶段 01 种子期/ 02 起步期/ 03 扩张期/ 04 过渡期/ 05 重建期/ 06 已上市/ 99 其它
     */
    private String investStage;

    /**
     * 投资企业类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    private String sharesType1;

    /**
     * 投资企业类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    private String sharesType2;

    /**
     * 投资企业类型（按技术领域划分）01 高新技术企业/99 其他
     */
    private String sharesType3;

    /**
     * 是否为质押融资 01是/02否
     */
    private String isPledge;

    /**
     * 名义本金
     */
    private BigDecimal nomCapi;

    /**
     * 标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     */
    private String targetType3;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 期限（月）
     */
    private BigDecimal termMonth;

    /**
     * 担保情况说明
     */
    private String guaranteeCmt;

    /**
     * 是否含权等特条款情况说明
     */
    private String specialCmt;

    /**
     * 合约名称
     */
    private String contractName;

    /**
     * 固定收益部分所占比例
     */
    private BigDecimal incomeRatio;

    /**
     * 衍生金融工具所占比例
     */
    private BigDecimal derInstrRatio;

    /**
     * 衍生金融工具具体投资方式
     */
    private String derInstrInvType;

    /**
     * 衍生金融工具挂钩标的资产
     */
    private String derInstrTarget;

    /**
     * 利息结算方式
     */
    private String acctMode;

    /**
     * 含权情况说明
     */
    private String specialCmt1;

    /**
     * 结构性票据最高收益率
     */
    private BigDecimal maxYield;

    /**
     * 结构性票据最低收益率
     */
    private BigDecimal minYield;

    /**
     * 挂钩标的资产基准价格
     */
    private BigDecimal basicPrice;

    /**
     * 挂钩标的资产登记日价格
     */
    private BigDecimal regDatePrice;

    /**
     * 交易费
     */
    private String tradeFee;

    /**
     * 资管计划发起人机构编码
     */
    private String partyCode;

    /**
     * 资管计划登记编码
     */
    private String checkCode;

    /**
     * 管理人
     */
    private String managerId;

    /**
     * 托管人
     */
    private String trusteeId;

    /**
     * 资金实际投向 01 结构性投资类/02 货币市场工具类/03 债券类/04 股票类/05 股权类/06 另类投资类/07 混合类/08 非标准化债权类/09 银行存款类/10 理财直接融资工具类/11 新增可投资资产类/12 代客境外投资类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类/18 同业存单类/19拆放同业及买入返售类
     */
    private String truActType;

    /**
     * 资金运用方式
     */
    private String investType;

    /**
     * 资金运用行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    private String capUseIndu;

    /**
     * 资管计划成立日期
     */
    private Date planSdate;

    /**
     * 资管计划终止日期
     */
    private Date planEdate;

    /**
     * 资管计划属性/委外投资属性 01 集合/02 单一/03 专项/04 定向/05 专户/99 其他
     */
    private String proProperty;

    /**
     * 最高预期收益率
     */
    private BigDecimal maxRate;

    /**
     * 最低预期收益率
     */
    private BigDecimal minRate;

    /**
     * 负债规模
     */
    private BigDecimal debtScale;

    /**
     * 购买结构 01 优先/02 劣后/03 整体
     */
    private String buyStruct;

    /**
     * 管理方式 01 主动（自主管理）/02被动（委托管理）/99其他
     */
    private String manageType;

    /**
     * 是否掌控计划底层资产详情 01 是/ 02 否
     */
    private String isCtrlBottom;

    /**
     * 管理费率
     */
    private BigDecimal mgrFeeRate;

    /**
     * 托管费率
     */
    private BigDecimal truFeeRate;

    /**
     * 交易相关合计费率
     */
    private BigDecimal tradeFeeRate;

    /**
     * 中介服务机构合计费率
     */
    private BigDecimal agencyFeeRate;

    /**
     * 其他合计费率
     */
    private BigDecimal otherFeeRate;

    /**
     * 资产价值
     */
    private BigDecimal assetValue;

    /**
     * 登记备案机构 01 中央国债登记结算有限责任公司/ 02 上海清算所 / 03 中国证券登记结算有限责任公司 / 04 中国证券投资基金业协会 /99 其他
     */
    private String registOrg1;

    /**
     * 基金管理机构名称
     */
    private String mngOrgName;

    /**
     * 基金托管机构名称
     */
    private String truOrgName;

    /**
     * 基金投资资产
     */
    private String fundInvestAsset;

    /**
     * 行内资产类别 01 场内股票质押回购/02 场外股票质押融资/03 融资租赁/99 其他
     */
    private String inAssetType;

    /**
     * 行内资产类别说明
     */
    private String inAssetTypeCmm;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否底层资产  01 是/ 02 否
     */
    private String isUnderAsset;

    /**
     * 是否属于政府投资基金  01 是/ 02 否
     */
    private String isGovernFund;

    /**
     * 政府投资基金投向 01 投向创业早期企业/ 02 投向中小微企业/ 03 投向产业转型升级和发展/ 04 投向基础设施和公共服务领域/99 其他
     */
    private String governInvestFund;

    /**
     * 基金公司名称
     */
    private String fundEnpeName;

    /**
     * 受托人
     */
    private String trustee;

    /**
     * 行内报送截止日
     */
    private Date submitDeadline;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 数据状态 01 新建/02 修改/03 删除
     */
    private String status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 报送状态 01 未报送/02 手工处理/03 接口报送
     */
    private String sendStatus;

    /**
     * 报送流水号
     */
    private String sumitSeq;

    /**
     * 报送反馈状态
     */
    private String submitFeedbackStatus;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 删除原因
     */
    private String deleteRes;

    /**
     * 资金运用行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    private String capUseInduLevel2;

    /**
     * 发行机构
     */
    private String issuePartyName;

	/**
	 * 存款类型
	*/
	private String depoType;
	
	/**
	 * 对手方类型
	*/
	private String rivalPartyType;
	
	/**
	 * 份额面值
	*/
	private BigDecimal prinBaseAmt;
	
	/**
	 * 法定到期日
	*/
	private Date fmDate;
	
	/**
	 * 计息类型
	*/
	private String couponSpecies;
	
	/**
	 * 规则付息标识
	*/
	private String interestRuleFlag;
	
	/**
	 * 利息分布方式
	*/
	private String intpayRule;
	
	/**
	 * 是否有浮动因子
	*/
	private String floatFactorFlag;
	
	/**
	 * 结构档次
	*/
	private String structGrade;
	
	/**
	 * 还本方式
	*/
	private String repaymentType;
	
	/**
	 * 分期还本条款标识
	*/
	private String periodRepaymentFlag;
	
	/**
	 * 基础资产类型
	*/
	private String basicAssetType;
	
	/**
	 * 超额收益分配比例%
	*/
	private BigDecimal excessProfitRate;
	
	/**
	 * 抵质押物类型
	*/
	private String pledgeType;
	
	/**
	 * 抵质押物价值（元）
	*/
	private BigDecimal pledgeValue;
	
	/**
	 * 担保性质
	*/
	private String natureGuarantee;
	
	/**
	 * 担保人与融资人关系
	*/
	private String relationFinan;
	
	/**
	 * 含权类型
	*/
	private String containType;
	
	/**
	 * 行权方式
	*/
	private String exerciseType;
	
	/**
	 * 固定行权日
	*/
	private Date exerciseDate;
	
	/**
	 * 首次行权日期
	*/
	private Date firstExerciseDate;
	
	/**
	 * 行权周期
	*/
	private Long exercisePeriod;
	
	/**
	 * 行权价格
	*/
	private BigDecimal exercisePrice;
	
	/**
	 * 永续条款类型
	*/
	private String perpetualClauseType;
	
	/**
	 * 利息递延条款类型
	*/
	private String deferredInterestType;
	
	/**
	 * 递延利息是否计息
	*/
	private String deferredInterestInt;
	
	/**
	 * 首次重定价日期
	*/
	private Date firstReprceDate;
	
	/**
	 * 重定价周期
	*/
	private Long reprceCycle;
	
	/**
	 * 部分赎回标识
	*/
	private String partRedemFlag;
	
	/**
	 * 部分赎回比例%
	*/
	private BigDecimal partRedemRate;
	
	/**
	 * 增信机构代码
	*/
	private String creditOrgCode;
	
	/**
	 * 增信机构名称
	*/
	private String creditOrgName;
	
	/**
	 * 股权退出安排
	*/
	private Date equityQuit;
	
	/**
	 * 是否为债转股
	*/
	private String deptEquity;
	
	/**
	 * 持有目的
	*/
	private String holdPurpose;
	
	/**
	 * 是否由金融资产投资公司发行
	*/
	private String financialComissue;

    
    /**
     * 登记序号
     * @return REGIST_SEQNO 登记序号
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 登记序号
     * @param registSeqno 登记序号
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 行内资产负债编码
     * @return ASSET_CODE 行内资产负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 行内资产负债编码
     * @param assetCode 行内资产负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 资产/负债类别 01本行/他行存款||大额存单 02拆放同业/买入返售（资产）||同业拆入/卖出回购（负债） 03债券类资产/理财直接融资工具/同业存单 04非标准化债权类资产/信贷资产流转项目 05	票据类资产/信用证类资产 06权益类资产 07金融衍生品 08QDII债券资产 09QDII拆出/逆回购资产 10QDII股票/基金类资产 11QDII结构性票据类资产 12资产管理产品 13商品类资产 14各类资产的其他项 15公募基金 16委外投资——协议方式 17其他（负债）类资产 18另类资产 19私募基金
     * @return ASSET_TYPE 资产/负债类别 01本行/他行存款||大额存单 02拆放同业/买入返售（资产）||同业拆入/卖出回购（负债） 03债券类资产/理财直接融资工具/同业存单 04非标准化债权类资产/信贷资产流转项目 05	票据类资产/信用证类资产 06权益类资产 07金融衍生品 08QDII债券资产 09QDII拆出/逆回购资产 10QDII股票/基金类资产 11QDII结构性票据类资产 12资产管理产品 13商品类资产 14各类资产的其他项 15公募基金 16委外投资——协议方式 17其他（负债）类资产 18另类资产 19私募基金
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 资产/负债类别 01本行/他行存款||大额存单 02拆放同业/买入返售（资产）||同业拆入/卖出回购（负债） 03债券类资产/理财直接融资工具/同业存单 04非标准化债权类资产/信贷资产流转项目 05	票据类资产/信用证类资产 06权益类资产 07金融衍生品 08QDII债券资产 09QDII拆出/逆回购资产 10QDII股票/基金类资产 11QDII结构性票据类资产 12资产管理产品 13商品类资产 14各类资产的其他项 15公募基金 16委外投资——协议方式 17其他（负债）类资产 18另类资产 19私募基金
     * @param assetType 资产/负债类别 01本行/他行存款||大额存单 02拆放同业/买入返售（资产）||同业拆入/卖出回购（负债） 03债券类资产/理财直接融资工具/同业存单 04非标准化债权类资产/信贷资产流转项目 05	票据类资产/信用证类资产 06权益类资产 07金融衍生品 08QDII债券资产 09QDII拆出/逆回购资产 10QDII股票/基金类资产 11QDII结构性票据类资产 12资产管理产品 13商品类资产 14各类资产的其他项 15公募基金 16委外投资——协议方式 17其他（负债）类资产 18另类资产 19私募基金
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     * @return ASSET_TYPE2 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     * @param assetType2 资产/负债二级类别 参考数据字典:ASSET_TYPE_SECOND_LEVEL
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 币种 参考数据字典：CURRENCY_CHB
     * @return CCY 币种 参考数据字典：CURRENCY_CHB
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 币种 参考数据字典：CURRENCY_CHB
     * @param ccy 币种 参考数据字典：CURRENCY_CHB
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 交易流通场所01 银行间债券市场/02 商业银行柜台市场/03 上海证券交易所/04 深圳证券交易所/05 全国中小企业股份转让系统/06 上海期货交易所/07 郑州商品交易所/08 大连商品交易所/09 中国金融期货交易所/10 上海黄金交易所/11 区域性产权/资产交易市场/12 区域性商品交易所/13 区域性贵金属交易所/14 艺术品市场/15 基金市场/16 信托市场/17全国银行间同业拆借市场 /18 上海票据交易所/99 其他交易流通场所
     * @return TRADE_MARKET 交易流通场所01 银行间债券市场/02 商业银行柜台市场/03 上海证券交易所/04 深圳证券交易所/05 全国中小企业股份转让系统/06 上海期货交易所/07 郑州商品交易所/08 大连商品交易所/09 中国金融期货交易所/10 上海黄金交易所/11 区域性产权/资产交易市场/12 区域性商品交易所/13 区域性贵金属交易所/14 艺术品市场/15 基金市场/16 信托市场/17全国银行间同业拆借市场 /18 上海票据交易所/99 其他交易流通场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 交易流通场所01 银行间债券市场/02 商业银行柜台市场/03 上海证券交易所/04 深圳证券交易所/05 全国中小企业股份转让系统/06 上海期货交易所/07 郑州商品交易所/08 大连商品交易所/09 中国金融期货交易所/10 上海黄金交易所/11 区域性产权/资产交易市场/12 区域性商品交易所/13 区域性贵金属交易所/14 艺术品市场/15 基金市场/16 信托市场/17全国银行间同业拆借市场 /18 上海票据交易所/99 其他交易流通场所
     * @param tradeMarket 交易流通场所01 银行间债券市场/02 商业银行柜台市场/03 上海证券交易所/04 深圳证券交易所/05 全国中小企业股份转让系统/06 上海期货交易所/07 郑州商品交易所/08 大连商品交易所/09 中国金融期货交易所/10 上海黄金交易所/11 区域性产权/资产交易市场/12 区域性商品交易所/13 区域性贵金属交易所/14 艺术品市场/15 基金市场/16 信托市场/17全国银行间同业拆借市场 /18 上海票据交易所/99 其他交易流通场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 起息日
     * @return VDATE 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 起息日
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 到期日
     * @return MDATE 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 到期日
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 收益率/年利率
     * @return ASSET_YIELD 收益率/年利率
     */
    public BigDecimal getAssetYield() {
        return assetYield;
    }

    /**
     * 收益率/年利率
     * @param assetYield 收益率/年利率
     */
    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }

    /**
     * 计息基础 01 ACT/366；02 ACT/360；03 ACT/365；04 ACT/ACT；05 30/360
     * @return BASIS 计息基础 01 ACT/366；02 ACT/360；03 ACT/365；04 ACT/ACT；05 30/360
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 计息基础 01 ACT/366；02 ACT/360；03 ACT/365；04 ACT/ACT；05 30/360
     * @param basis 计息基础 01 ACT/366；02 ACT/360；03 ACT/365；04 ACT/ACT；05 30/360
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 金额
     * @return AMT 金额
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 金额
     * @param amt 金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * 资金存入银行
     * @return BANK_NAME 资金存入银行
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 资金存入银行
     * @param bankName 资金存入银行
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 是否为结构性存款 01是/02否
     * @return IS_STRUCT 是否为结构性存款 01是/02否
     */
    public String getIsStruct() {
        return isStruct;
    }

    /**
     * 是否为结构性存款 01是/02否
     * @param isStruct 是否为结构性存款 01是/02否
     */
    public void setIsStruct(String isStruct) {
        this.isStruct = isStruct == null ? null : isStruct.trim();
    }

    /**
     * 结构性存款挂钩标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     * @return TARGET_TYPE 结构性存款挂钩标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * 结构性存款挂钩标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     * @param targetType 结构性存款挂钩标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType == null ? null : targetType.trim();
    }

    /**
     * 结构性存款挂钩标的
     * @return TARGET 结构性存款挂钩标的
     */
    public String getTarget() {
        return target;
    }

    /**
     * 结构性存款挂钩标的
     * @param target 结构性存款挂钩标的
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * 资产代码
     * @return ASSET_ID 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 资产名称
     * @return ASSET_NAME 资产名称
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 资产名称
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    /**
     * 具体类别 01 商业银行普通债/ 02 商业银行次级债/ 03 商业银行混合资本债/ 04 商业银行二级资本工具/ 05 证券公司金融债/ 06 证券公司短期融资券/ 07 保险公司金融债/ 08 财务公司金融债/ 09 融资租赁公司金融债/ 10 资产管理公司金融债/ 11 集合企业债/ 12 项目收益债券/ 13 可转换公司债券/ 14 可交换公司债券/ 15 分离交易的可转换公司债券/ 16 短期融资券（CP）/ 17 超短期融资券（SCP）/ 18 中期票据（MTN）/ 19 项目收益票据（PRN）/ 20 中小企业集合票据（SMECN）/ 21非公开定向债务融资工具/ 22 信贷资产支持证券/ 23 资产支持专项计划/ 24 非金融企业资产支持票据（ABN）/ 25 国际金融机构债券/ 26 外国政府债券/ 27 外国金融机构债券/ 28 外国公司债券/ 99 其他债券
     * @return CHB_SECTYPE 具体类别 01 商业银行普通债/ 02 商业银行次级债/ 03 商业银行混合资本债/ 04 商业银行二级资本工具/ 05 证券公司金融债/ 06 证券公司短期融资券/ 07 保险公司金融债/ 08 财务公司金融债/ 09 融资租赁公司金融债/ 10 资产管理公司金融债/ 11 集合企业债/ 12 项目收益债券/ 13 可转换公司债券/ 14 可交换公司债券/ 15 分离交易的可转换公司债券/ 16 短期融资券（CP）/ 17 超短期融资券（SCP）/ 18 中期票据（MTN）/ 19 项目收益票据（PRN）/ 20 中小企业集合票据（SMECN）/ 21非公开定向债务融资工具/ 22 信贷资产支持证券/ 23 资产支持专项计划/ 24 非金融企业资产支持票据（ABN）/ 25 国际金融机构债券/ 26 外国政府债券/ 27 外国金融机构债券/ 28 外国公司债券/ 99 其他债券
     */
    public String getChbSectype() {
        return chbSectype;
    }

    /**
     * 具体类别 01 商业银行普通债/ 02 商业银行次级债/ 03 商业银行混合资本债/ 04 商业银行二级资本工具/ 05 证券公司金融债/ 06 证券公司短期融资券/ 07 保险公司金融债/ 08 财务公司金融债/ 09 融资租赁公司金融债/ 10 资产管理公司金融债/ 11 集合企业债/ 12 项目收益债券/ 13 可转换公司债券/ 14 可交换公司债券/ 15 分离交易的可转换公司债券/ 16 短期融资券（CP）/ 17 超短期融资券（SCP）/ 18 中期票据（MTN）/ 19 项目收益票据（PRN）/ 20 中小企业集合票据（SMECN）/ 21非公开定向债务融资工具/ 22 信贷资产支持证券/ 23 资产支持专项计划/ 24 非金融企业资产支持票据（ABN）/ 25 国际金融机构债券/ 26 外国政府债券/ 27 外国金融机构债券/ 28 外国公司债券/ 99 其他债券
     * @param chbSectype 具体类别 01 商业银行普通债/ 02 商业银行次级债/ 03 商业银行混合资本债/ 04 商业银行二级资本工具/ 05 证券公司金融债/ 06 证券公司短期融资券/ 07 保险公司金融债/ 08 财务公司金融债/ 09 融资租赁公司金融债/ 10 资产管理公司金融债/ 11 集合企业债/ 12 项目收益债券/ 13 可转换公司债券/ 14 可交换公司债券/ 15 分离交易的可转换公司债券/ 16 短期融资券（CP）/ 17 超短期融资券（SCP）/ 18 中期票据（MTN）/ 19 项目收益票据（PRN）/ 20 中小企业集合票据（SMECN）/ 21非公开定向债务融资工具/ 22 信贷资产支持证券/ 23 资产支持专项计划/ 24 非金融企业资产支持票据（ABN）/ 25 国际金融机构债券/ 26 外国政府债券/ 27 外国金融机构债券/ 28 外国公司债券/ 99 其他债券
     */
    public void setChbSectype(String chbSectype) {
        this.chbSectype = chbSectype == null ? null : chbSectype.trim();
    }

    /**
     * 发行方式 01 公开发行/02 非公开发行
     * @return ISSUE_MODE 发行方式 01 公开发行/02 非公开发行
     */
    public String getIssueMode() {
        return issueMode;
    }

    /**
     * 发行方式 01 公开发行/02 非公开发行
     * @param issueMode 发行方式 01 公开发行/02 非公开发行
     */
    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode == null ? null : issueMode.trim();
    }

    /**
     * 发行机构主体信用评级
     * @return PARTY_GRADE 发行机构主体信用评级
     */
    public String getPartyGrade() {
        return partyGrade;
    }

    /**
     * 发行机构主体信用评级
     * @param partyGrade 发行机构主体信用评级
     */
    public void setPartyGrade(String partyGrade) {
        this.partyGrade = partyGrade == null ? null : partyGrade.trim();
    }

    /**
     * 发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     * @return PARTY_TYPE1 发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    public String getPartyType1() {
        return partyType1;
    }

    /**
     * 发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     * @param partyType1 发行机构类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    public void setPartyType1(String partyType1) {
        this.partyType1 = partyType1 == null ? null : partyType1.trim();
    }

    /**
     * 发行机构类型（按技术领域划分）01 高新技术企业/99 其他01 高新技术企业/99 其他
     * @return PARTY_TYPE3 发行机构类型（按技术领域划分）01 高新技术企业/99 其他01 高新技术企业/99 其他
     */
    public String getPartyType3() {
        return partyType3;
    }

    /**
     * 发行机构类型（按技术领域划分）01 高新技术企业/99 其他01 高新技术企业/99 其他
     * @param partyType3 发行机构类型（按技术领域划分）01 高新技术企业/99 其他01 高新技术企业/99 其他
     */
    public void setPartyType3(String partyType3) {
        this.partyType3 = partyType3 == null ? null : partyType3.trim();
    }

    /**
     * 发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     * @return PARTY_TYPE2 发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    public String getPartyType2() {
        return partyType2;
    }

    /**
     * 发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     * @param partyType2 发行机构类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    public void setPartyType2(String partyType2) {
        this.partyType2 = partyType2 == null ? null : partyType2.trim();
    }

    /**
     * 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @return INDUSTRY 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @param industry 发行机构所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @return INDUSTRY_LEVEL2 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public String getIndustryLevel2() {
        return industryLevel2;
    }

    /**
     * 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @param industryLevel2 发行机构所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public void setIndustryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2 == null ? null : industryLevel2.trim();
    }

    /**
     * 债券主体评级
     * @return SEC_MAIN_GRADE 债券主体评级
     */
    public String getSecMainGrade() {
        return secMainGrade;
    }

    /**
     * 债券主体评级
     * @param secMainGrade 债券主体评级
     */
    public void setSecMainGrade(String secMainGrade) {
        this.secMainGrade = secMainGrade == null ? null : secMainGrade.trim();
    }

    /**
     * 债券债项评级
     * @return SEC_CREDIT_GRADE 债券债项评级
     */
    public String getSecCreditGrade() {
        return secCreditGrade;
    }

    /**
     * 债券债项评级
     * @param secCreditGrade 债券债项评级
     */
    public void setSecCreditGrade(String secCreditGrade) {
        this.secCreditGrade = secCreditGrade == null ? null : secCreditGrade.trim();
    }

    /**
     * 债券信用评级
     * @return SEC_CREDIT_GRADE_TYPE 债券信用评级
     */
    public String getSecCreditGradeType() {
        return secCreditGradeType;
    }

    /**
     * 债券信用评级
     * @param secCreditGradeType 债券信用评级
     */
    public void setSecCreditGradeType(String secCreditGradeType) {
        this.secCreditGradeType = secCreditGradeType == null ? null : secCreditGradeType.trim();
    }

    /**
     * 登记托管机构 01 中央国债登记结算有限责任公司/ 02 上海清算所/03 中国证券登记结算有限责任公司/99 其他
     * @return REGIST_ORG 登记托管机构 01 中央国债登记结算有限责任公司/ 02 上海清算所/03 中国证券登记结算有限责任公司/99 其他
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 登记托管机构 01 中央国债登记结算有限责任公司/ 02 上海清算所/03 中国证券登记结算有限责任公司/99 其他
     * @param registOrg 登记托管机构 01 中央国债登记结算有限责任公司/ 02 上海清算所/03 中国证券登记结算有限责任公司/99 其他
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 登记托管机构说明
     * @return REGISTORG_COMMENT 登记托管机构说明
     */
    public String getRegistorgComment() {
        return registorgComment;
    }

    /**
     * 登记托管机构说明
     * @param registorgComment 登记托管机构说明
     */
    public void setRegistorgComment(String registorgComment) {
        this.registorgComment = registorgComment == null ? null : registorgComment.trim();
    }

    /**
     * 对手方
     * @return RIVAL_PARTY 对手方
     */
    public String getRivalParty() {
        return rivalParty;
    }

    /**
     * 对手方
     * @param rivalParty 对手方
     */
    public void setRivalParty(String rivalParty) {
        this.rivalParty = rivalParty == null ? null : rivalParty.trim();
    }

    /**
     * 回购标的类别 01 债券/ 02 股票/03 基金类产品/99 其他
     * @return TARGET_TYPE1 回购标的类别 01 债券/ 02 股票/03 基金类产品/99 其他
     */
    public String getTargetType1() {
        return targetType1;
    }

    /**
     * 回购标的类别 01 债券/ 02 股票/03 基金类产品/99 其他
     * @param targetType1 回购标的类别 01 债券/ 02 股票/03 基金类产品/99 其他
     */
    public void setTargetType1(String targetType1) {
        this.targetType1 = targetType1 == null ? null : targetType1.trim();
    }

    /**
     * 回购标的金额
     * @return TARGET_AMT 回购标的金额
     */
    public BigDecimal getTargetAmt() {
        return targetAmt;
    }

    /**
     * 回购标的金额
     * @param targetAmt 回购标的金额
     */
    public void setTargetAmt(BigDecimal targetAmt) {
        this.targetAmt = targetAmt;
    }

    /**
     * 所属国家或地区 参考数据字典：COUNTRY_AREA
     * @return COUNTRY_AREA 所属国家或地区 参考数据字典：COUNTRY_AREA
     */
    public String getCountryArea() {
        return countryArea;
    }

    /**
     * 所属国家或地区 参考数据字典：COUNTRY_AREA
     * @param countryArea 所属国家或地区 参考数据字典：COUNTRY_AREA
     */
    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea == null ? null : countryArea.trim();
    }

    /**
     * 收/受益权类型 01 信托贷款/ 02 信贷资产转让/ 03 委托债权/ 04 应收账款/ 05 带回购条款的股权性融资/06 私募债权/ 07 融资融券/ 08 证券公司收益凭证/ 09 委托贷款/ 99 其他
     * @return PROFIT_TYPE 收/受益权类型 01 信托贷款/ 02 信贷资产转让/ 03 委托债权/ 04 应收账款/ 05 带回购条款的股权性融资/06 私募债权/ 07 融资融券/ 08 证券公司收益凭证/ 09 委托贷款/ 99 其他
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 收/受益权类型 01 信托贷款/ 02 信贷资产转让/ 03 委托债权/ 04 应收账款/ 05 带回购条款的股权性融资/06 私募债权/ 07 融资融券/ 08 证券公司收益凭证/ 09 委托贷款/ 99 其他
     * @param profitType 收/受益权类型 01 信托贷款/ 02 信贷资产转让/ 03 委托债权/ 04 应收账款/ 05 带回购条款的股权性融资/06 私募债权/ 07 融资融券/ 08 证券公司收益凭证/ 09 委托贷款/ 99 其他
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 是否属于买入返售 01是/02否
     * @return IS_RESALE 是否属于买入返售 01是/02否
     */
    public String getIsResale() {
        return isResale;
    }

    /**
     * 是否属于买入返售 01是/02否
     * @param isResale 是否属于买入返售 01是/02否
     */
    public void setIsResale(String isResale) {
        this.isResale = isResale == null ? null : isResale.trim();
    }

    /**
     * 期限（天）
     * @return TERM_DATE 期限（天）
     */
    public BigDecimal getTermDate() {
        return termDate;
    }

    /**
     * 期限（天）
     * @param termDate 期限（天）
     */
    public void setTermDate(BigDecimal termDate) {
        this.termDate = termDate;
    }

    /**
     * 是否有预期收益率 01是/02否
     * @return RATE_FLAG 是否有预期收益率 01是/02否
     */
    public String getRateFlag() {
        return rateFlag;
    }

    /**
     * 是否有预期收益率 01是/02否
     * @param rateFlag 是否有预期收益率 01是/02否
     */
    public void setRateFlag(String rateFlag) {
        this.rateFlag = rateFlag == null ? null : rateFlag.trim();
    }

    /**
     * 付息频率（个月/次）
     * @return INTPAY_CYCLE 付息频率（个月/次）
     */
    public Long getIntpayCycle() {
        return intpayCycle;
    }

    /**
     * 付息频率（个月/次）
     * @param intpayCycle 付息频率（个月/次）
     */
    public void setIntpayCycle(Long intpayCycle) {
        this.intpayCycle = intpayCycle;
    }

    /**
     * 还本付息说明
     * @return REPAY_CMT 还本付息说明
     */
    public String getRepayCmt() {
        return repayCmt;
    }

    /**
     * 还本付息说明
     * @param repayCmt 还本付息说明
     */
    public void setRepayCmt(String repayCmt) {
        this.repayCmt = repayCmt == null ? null : repayCmt.trim();
    }

    /**
     * 基准利率种类 参考数据字典：BASIS_TYPE
     * @return BASIS_TYPE 基准利率种类 参考数据字典：BASIS_TYPE
     */
    public String getBasisType() {
        return basisType;
    }

    /**
     * 基准利率种类 参考数据字典：BASIS_TYPE
     * @param basisType 基准利率种类 参考数据字典：BASIS_TYPE
     */
    public void setBasisType(String basisType) {
        this.basisType = basisType == null ? null : basisType.trim();
    }

    /**
     * 浮动因子
     * @return FLOAT_FACTOR 浮动因子
     */
    public BigDecimal getFloatFactor() {
        return floatFactor;
    }

    /**
     * 浮动因子
     * @param floatFactor 浮动因子
     */
    public void setFloatFactor(BigDecimal floatFactor) {
        this.floatFactor = floatFactor;
    }

    /**
     * 利差
     * @return SPREAD_RATE 利差
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 融资人
     * @return FINANCIE 融资人
     */
    public String getFinancie() {
        return financie;
    }

    /**
     * 融资人
     * @param financie 融资人
     */
    public void setFinancie(String financie) {
        this.financie = financie == null ? null : financie.trim();
    }

    /**
     * 融资人组织机构代码
     * @return FIN_ORG_CODE 融资人组织机构代码
     */
    public String getFinOrgCode() {
        return finOrgCode;
    }

    /**
     * 融资人组织机构代码
     * @param finOrgCode 融资人组织机构代码
     */
    public void setFinOrgCode(String finOrgCode) {
        this.finOrgCode = finOrgCode == null ? null : finOrgCode.trim();
    }

    /**
     * 融资人内部信用评级
     * @return FCR_GRADE_IN 融资人内部信用评级
     */
    public String getFcrGradeIn() {
        return fcrGradeIn;
    }

    /**
     * 融资人内部信用评级
     * @param fcrGradeIn 融资人内部信用评级
     */
    public void setFcrGradeIn(String fcrGradeIn) {
        this.fcrGradeIn = fcrGradeIn == null ? null : fcrGradeIn.trim();
    }

    /**
     * 外部评级机构名称及融资人评级结果
     * @return FCR_GRADE_OUT 外部评级机构名称及融资人评级结果
     */
    public String getFcrGradeOut() {
        return fcrGradeOut;
    }

    /**
     * 外部评级机构名称及融资人评级结果
     * @param fcrGradeOut 外部评级机构名称及融资人评级结果
     */
    public void setFcrGradeOut(String fcrGradeOut) {
        this.fcrGradeOut = fcrGradeOut == null ? null : fcrGradeOut.trim();
    }

    /**
     * 融资人类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     * @return FINANCIER_TYPE1 融资人类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    public String getFinancierType1() {
        return financierType1;
    }

    /**
     * 融资人类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     * @param financierType1 融资人类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    public void setFinancierType1(String financierType1) {
        this.financierType1 = financierType1 == null ? null : financierType1.trim();
    }

    /**
     * 融资人类型（按技术领域划分）01 高新技术企业/99 其他
     * @return FINANCIER_TYPE2 融资人类型（按技术领域划分）01 高新技术企业/99 其他
     */
    public String getFinancierType2() {
        return financierType2;
    }

    /**
     * 融资人类型（按技术领域划分）01 高新技术企业/99 其他
     * @param financierType2 融资人类型（按技术领域划分）01 高新技术企业/99 其他
     */
    public void setFinancierType2(String financierType2) {
        this.financierType2 = financierType2 == null ? null : financierType2.trim();
    }

    /**
     * 融资人类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     * @return FINANCIER_TYPE3 融资人类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    public String getFinancierType3() {
        return financierType3;
    }

    /**
     * 融资人类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     * @param financierType3 融资人类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    public void setFinancierType3(String financierType3) {
        this.financierType3 = financierType3 == null ? null : financierType3.trim();
    }

    /**
     * 融资项目
     * @return FINANCE_PROJ 融资项目
     */
    public String getFinanceProj() {
        return financeProj;
    }

    /**
     * 融资项目
     * @param financeProj 融资项目
     */
    public void setFinanceProj(String financeProj) {
        this.financeProj = financeProj == null ? null : financeProj.trim();
    }

    /**
     * 融资人所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @return INDUSTRY1 融资人所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public String getIndustry1() {
        return industry1;
    }

    /**
     * 融资人所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @param industry1 融资人所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public void setIndustry1(String industry1) {
        this.industry1 = industry1 == null ? null : industry1.trim();
    }

    /**
     * 融资人所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @return INDUSTRY_LEVEL2_TYPE 融资人所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public String getIndustryLevel2Type() {
        return industryLevel2Type;
    }

    /**
     * 融资人所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @param industryLevel2Type 融资人所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public void setIndustryLevel2Type(String industryLevel2Type) {
        this.industryLevel2Type = industryLevel2Type == null ? null : industryLevel2Type.trim();
    }

    /**
     * 融资项目所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @return PRO_INDUSTRY 融资项目所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public String getProIndustry() {
        return proIndustry;
    }

    /**
     * 融资项目所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @param proIndustry 融资项目所属行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public void setProIndustry(String proIndustry) {
        this.proIndustry = proIndustry == null ? null : proIndustry.trim();
    }

    /**
     * 融资项目所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @return PRO_INDUSTRY_LEVEL2 融资项目所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public String getProIndustryLevel2() {
        return proIndustryLevel2;
    }

    /**
     * 融资项目所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @param proIndustryLevel2 融资项目所属行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public void setProIndustryLevel2(String proIndustryLevel2) {
        this.proIndustryLevel2 = proIndustryLevel2 == null ? null : proIndustryLevel2.trim();
    }

    /**
     * 项目是否属于重点监控行业和领域 01 是/ 02 否
     * @return INDUSTRY_FLAG 项目是否属于重点监控行业和领域 01 是/ 02 否
     */
    public String getIndustryFlag() {
        return industryFlag;
    }

    /**
     * 项目是否属于重点监控行业和领域 01 是/ 02 否
     * @param industryFlag 项目是否属于重点监控行业和领域 01 是/ 02 否
     */
    public void setIndustryFlag(String industryFlag) {
        this.industryFlag = industryFlag == null ? null : industryFlag.trim();
    }

    /**
     * 重点监控行业和领域类别 01 房地产(保障房除外)/ 02 地方政府融资平台/ 03 “铁公基”行业/ 04 “两高一剩”行业/ 99 其他
     * @return MONIT_TYPE 重点监控行业和领域类别 01 房地产(保障房除外)/ 02 地方政府融资平台/ 03 “铁公基”行业/ 04 “两高一剩”行业/ 99 其他
     */
    public String getMonitType() {
        return monitType;
    }

    /**
     * 重点监控行业和领域类别 01 房地产(保障房除外)/ 02 地方政府融资平台/ 03 “铁公基”行业/ 04 “两高一剩”行业/ 99 其他
     * @param monitType 重点监控行业和领域类别 01 房地产(保障房除外)/ 02 地方政府融资平台/ 03 “铁公基”行业/ 04 “两高一剩”行业/ 99 其他
     */
    public void setMonitType(String monitType) {
        this.monitType = monitType == null ? null : monitType.trim();
    }

    /**
     * 重点监控行业和领域类别说明
     * @return MON_TYPE_COMM 重点监控行业和领域类别说明
     */
    public String getMonTypeComm() {
        return monTypeComm;
    }

    /**
     * 重点监控行业和领域类别说明
     * @param monTypeComm 重点监控行业和领域类别说明
     */
    public void setMonTypeComm(String monTypeComm) {
        this.monTypeComm = monTypeComm == null ? null : monTypeComm.trim();
    }

    /**
     * 担保方式 01 信用担保/ 02 第三方保证/ 03 抵押/ 04 质押/ 05 信用增级/ 99 其他
     * @return GUA_TYPE 担保方式 01 信用担保/ 02 第三方保证/ 03 抵押/ 04 质押/ 05 信用增级/ 99 其他
     */
    public String getGuaType() {
        return guaType;
    }

    /**
     * 担保方式 01 信用担保/ 02 第三方保证/ 03 抵押/ 04 质押/ 05 信用增级/ 99 其他
     * @param guaType 担保方式 01 信用担保/ 02 第三方保证/ 03 抵押/ 04 质押/ 05 信用增级/ 99 其他
     */
    public void setGuaType(String guaType) {
        this.guaType = guaType == null ? null : guaType.trim();
    }

    /**
     * 担保方式说明
     * @return GUA_TYPE_REMARK 担保方式说明
     */
    public String getGuaTypeRemark() {
        return guaTypeRemark;
    }

    /**
     * 担保方式说明
     * @param guaTypeRemark 担保方式说明
     */
    public void setGuaTypeRemark(String guaTypeRemark) {
        this.guaTypeRemark = guaTypeRemark == null ? null : guaTypeRemark.trim();
    }

    /**
     * 融资人主体评级
     * @return FINANCIER_GRADE 融资人主体评级
     */
    public String getFinancierGrade() {
        return financierGrade;
    }

    /**
     * 融资人主体评级
     * @param financierGrade 融资人主体评级
     */
    public void setFinancierGrade(String financierGrade) {
        this.financierGrade = financierGrade == null ? null : financierGrade.trim();
    }

    /**
     * 资产内部评级
     * @return IN_GRADE 资产内部评级
     */
    public String getInGrade() {
        return inGrade;
    }

    /**
     * 资产内部评级
     * @param inGrade 资产内部评级
     */
    public void setInGrade(String inGrade) {
        this.inGrade = inGrade == null ? null : inGrade.trim();
    }

    /**
     * 资产外部评级
     * @return OUT_GRADE 资产外部评级
     */
    public String getOutGrade() {
        return outGrade;
    }

    /**
     * 资产外部评级
     * @param outGrade 资产外部评级
     */
    public void setOutGrade(String outGrade) {
        this.outGrade = outGrade == null ? null : outGrade.trim();
    }

    /**
     * 选择权
     * @return CHOSE 选择权
     */
    public String getChose() {
        return chose;
    }

    /**
     * 选择权
     * @param chose 选择权
     */
    public void setChose(String chose) {
        this.chose = chose == null ? null : chose.trim();
    }

    /**
     * 行权条件说明
     * @return CONDITION_CMM 行权条件说明
     */
    public String getConditionCmm() {
        return conditionCmm;
    }

    /**
     * 行权条件说明
     * @param conditionCmm 行权条件说明
     */
    public void setConditionCmm(String conditionCmm) {
        this.conditionCmm = conditionCmm == null ? null : conditionCmm.trim();
    }

    /**
     * 融资人所属地区  参考数据字典：COUNTRY_AREA
     * @return FIN_AREA 融资人所属地区  参考数据字典：COUNTRY_AREA
     */
    public String getFinArea() {
        return finArea;
    }

    /**
     * 融资人所属地区  参考数据字典：COUNTRY_AREA
     * @param finArea 融资人所属地区  参考数据字典：COUNTRY_AREA
     */
    public void setFinArea(String finArea) {
        this.finArea = finArea == null ? null : finArea.trim();
    }

    /**
     * 融资总费率
     * @return TOTAL_FEE_RATE 融资总费率
     */
    public BigDecimal getTotalFeeRate() {
        return totalFeeRate;
    }

    /**
     * 融资总费率
     * @param totalFeeRate 融资总费率
     */
    public void setTotalFeeRate(BigDecimal totalFeeRate) {
        this.totalFeeRate = totalFeeRate;
    }

    /**
     * 是否为收/受益权 01是/02否
     * @return IS_RIGHT 是否为收/受益权 01是/02否
     */
    public String getIsRight() {
        return isRight;
    }

    /**
     * 是否为收/受益权 01是/02否
     * @param isRight 是否为收/受益权 01是/02否
     */
    public void setIsRight(String isRight) {
        this.isRight = isRight == null ? null : isRight.trim();
    }

    /**
     * 非标准化债券类资产类型 01 商业承兑汇票/ 02 银行承兑汇票/ 03 其他票据/ 04 混合类票据/ 05 信用证
     * @return NOTSTAND_SEC_TYPE 非标准化债券类资产类型 01 商业承兑汇票/ 02 银行承兑汇票/ 03 其他票据/ 04 混合类票据/ 05 信用证
     */
    public String getNotstandSecType() {
        return notstandSecType;
    }

    /**
     * 非标准化债券类资产类型 01 商业承兑汇票/ 02 银行承兑汇票/ 03 其他票据/ 04 混合类票据/ 05 信用证
     * @param notstandSecType 非标准化债券类资产类型 01 商业承兑汇票/ 02 银行承兑汇票/ 03 其他票据/ 04 混合类票据/ 05 信用证
     */
    public void setNotstandSecType(String notstandSecType) {
        this.notstandSecType = notstandSecType == null ? null : notstandSecType.trim();
    }

    /**
     * 非标准化债券类资产数量
     * @return NOTSTAND_SEC_NUM 非标准化债券类资产数量
     */
    public Long getNotstandSecNum() {
        return notstandSecNum;
    }

    /**
     * 非标准化债券类资产数量
     * @param notstandSecNum 非标准化债券类资产数量
     */
    public void setNotstandSecNum(Long notstandSecNum) {
        this.notstandSecNum = notstandSecNum;
    }

    /**
     * 加权剩余期限(天)
     * @return REMAIN_TERM 加权剩余期限(天)
     */
    public BigDecimal getRemainTerm() {
        return remainTerm;
    }

    /**
     * 加权剩余期限(天)
     * @param remainTerm 加权剩余期限(天)
     */
    public void setRemainTerm(BigDecimal remainTerm) {
        this.remainTerm = remainTerm;
    }

    /**
     * 最长剩余期限(天)
     * @return MAX_REMAIN_TERM 最长剩余期限(天)
     */
    public BigDecimal getMaxRemainTerm() {
        return maxRemainTerm;
    }

    /**
     * 最长剩余期限(天)
     * @param maxRemainTerm 最长剩余期限(天)
     */
    public void setMaxRemainTerm(BigDecimal maxRemainTerm) {
        this.maxRemainTerm = maxRemainTerm;
    }

    /**
     * 最短剩余期限(天)
     * @return MIN_REMAIN_TERM 最短剩余期限(天)
     */
    public BigDecimal getMinRemainTerm() {
        return minRemainTerm;
    }

    /**
     * 最短剩余期限(天)
     * @param minRemainTerm 最短剩余期限(天)
     */
    public void setMinRemainTerm(BigDecimal minRemainTerm) {
        this.minRemainTerm = minRemainTerm;
    }

    /**
     * 股票类型 01 普通股/02 优先股/99 其他
     * @return SHARES_TYPE 股票类型 01 普通股/02 优先股/99 其他
     */
    public String getSharesType() {
        return sharesType;
    }

    /**
     * 股票类型 01 普通股/02 优先股/99 其他
     * @param sharesType 股票类型 01 普通股/02 优先股/99 其他
     */
    public void setSharesType(String sharesType) {
        this.sharesType = sharesType == null ? null : sharesType.trim();
    }

    /**
     * 行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @return INDUSTRY_SHARES 行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public String getIndustryShares() {
        return industryShares;
    }

    /**
     * 行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @param industryShares 行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public void setIndustryShares(String industryShares) {
        this.industryShares = industryShares == null ? null : industryShares.trim();
    }

    /**
     * 行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @return INDUSTRY_SHARES2 行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public String getIndustryShares2() {
        return industryShares2;
    }

    /**
     * 行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @param industryShares2 行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public void setIndustryShares2(String industryShares2) {
        this.industryShares2 = industryShares2 == null ? null : industryShares2.trim();
    }

    /**
     * 投资阶段 01 种子期/ 02 起步期/ 03 扩张期/ 04 过渡期/ 05 重建期/ 06 已上市/ 99 其它
     * @return INVEST_STAGE 投资阶段 01 种子期/ 02 起步期/ 03 扩张期/ 04 过渡期/ 05 重建期/ 06 已上市/ 99 其它
     */
    public String getInvestStage() {
        return investStage;
    }

    /**
     * 投资阶段 01 种子期/ 02 起步期/ 03 扩张期/ 04 过渡期/ 05 重建期/ 06 已上市/ 99 其它
     * @param investStage 投资阶段 01 种子期/ 02 起步期/ 03 扩张期/ 04 过渡期/ 05 重建期/ 06 已上市/ 99 其它
     */
    public void setInvestStage(String investStage) {
        this.investStage = investStage == null ? null : investStage.trim();
    }

    /**
     * 投资企业类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     * @return SHARES_TYPE1 投资企业类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    public String getSharesType1() {
        return sharesType1;
    }

    /**
     * 投资企业类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     * @param sharesType1 投资企业类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    public void setSharesType1(String sharesType1) {
        this.sharesType1 = sharesType1 == null ? null : sharesType1.trim();
    }

    /**
     * 投资企业类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     * @return SHARES_TYPE2 投资企业类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    public String getSharesType2() {
        return sharesType2;
    }

    /**
     * 投资企业类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     * @param sharesType2 投资企业类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    public void setSharesType2(String sharesType2) {
        this.sharesType2 = sharesType2 == null ? null : sharesType2.trim();
    }

    /**
     * 投资企业类型（按技术领域划分）01 高新技术企业/99 其他
     * @return SHARES_TYPE3 投资企业类型（按技术领域划分）01 高新技术企业/99 其他
     */
    public String getSharesType3() {
        return sharesType3;
    }

    /**
     * 投资企业类型（按技术领域划分）01 高新技术企业/99 其他
     * @param sharesType3 投资企业类型（按技术领域划分）01 高新技术企业/99 其他
     */
    public void setSharesType3(String sharesType3) {
        this.sharesType3 = sharesType3 == null ? null : sharesType3.trim();
    }

    /**
     * 是否为质押融资 01是/02否
     * @return IS_PLEDGE 是否为质押融资 01是/02否
     */
    public String getIsPledge() {
        return isPledge;
    }

    /**
     * 是否为质押融资 01是/02否
     * @param isPledge 是否为质押融资 01是/02否
     */
    public void setIsPledge(String isPledge) {
        this.isPledge = isPledge == null ? null : isPledge.trim();
    }

    /**
     * 名义本金
     * @return NOM_CAPI 名义本金
     */
    public BigDecimal getNomCapi() {
        return nomCapi;
    }

    /**
     * 名义本金
     * @param nomCapi 名义本金
     */
    public void setNomCapi(BigDecimal nomCapi) {
        this.nomCapi = nomCapi;
    }

    /**
     * 标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     * @return TARGET_TYPE3 标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     */
    public String getTargetType3() {
        return targetType3;
    }

    /**
     * 标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     * @param targetType3 标的类别 01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     */
    public void setTargetType3(String targetType3) {
        this.targetType3 = targetType3 == null ? null : targetType3.trim();
    }

    /**
     * 发行机构代码
     * @return ISSUE_PARTY_ID 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 期限（月）
     * @return TERM_MONTH 期限（月）
     */
    public BigDecimal getTermMonth() {
        return termMonth;
    }

    /**
     * 期限（月）
     * @param termMonth 期限（月）
     */
    public void setTermMonth(BigDecimal termMonth) {
        this.termMonth = termMonth;
    }

    /**
     * 担保情况说明
     * @return GUARANTEE_CMT 担保情况说明
     */
    public String getGuaranteeCmt() {
        return guaranteeCmt;
    }

    /**
     * 担保情况说明
     * @param guaranteeCmt 担保情况说明
     */
    public void setGuaranteeCmt(String guaranteeCmt) {
        this.guaranteeCmt = guaranteeCmt == null ? null : guaranteeCmt.trim();
    }

    /**
     * 是否含权等特条款情况说明
     * @return SPECIAL_CMT 是否含权等特条款情况说明
     */
    public String getSpecialCmt() {
        return specialCmt;
    }

    /**
     * 是否含权等特条款情况说明
     * @param specialCmt 是否含权等特条款情况说明
     */
    public void setSpecialCmt(String specialCmt) {
        this.specialCmt = specialCmt == null ? null : specialCmt.trim();
    }

    /**
     * 合约名称
     * @return CONTRACT_NAME 合约名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 合约名称
     * @param contractName 合约名称
     */
    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    /**
     * 固定收益部分所占比例
     * @return INCOME_RATIO 固定收益部分所占比例
     */
    public BigDecimal getIncomeRatio() {
        return incomeRatio;
    }

    /**
     * 固定收益部分所占比例
     * @param incomeRatio 固定收益部分所占比例
     */
    public void setIncomeRatio(BigDecimal incomeRatio) {
        this.incomeRatio = incomeRatio;
    }

    /**
     * 衍生金融工具所占比例
     * @return DER_INSTR_RATIO 衍生金融工具所占比例
     */
    public BigDecimal getDerInstrRatio() {
        return derInstrRatio;
    }

    /**
     * 衍生金融工具所占比例
     * @param derInstrRatio 衍生金融工具所占比例
     */
    public void setDerInstrRatio(BigDecimal derInstrRatio) {
        this.derInstrRatio = derInstrRatio;
    }

    /**
     * 衍生金融工具具体投资方式
     * @return DER_INSTR_INV_TYPE 衍生金融工具具体投资方式
     */
    public String getDerInstrInvType() {
        return derInstrInvType;
    }

    /**
     * 衍生金融工具具体投资方式
     * @param derInstrInvType 衍生金融工具具体投资方式
     */
    public void setDerInstrInvType(String derInstrInvType) {
        this.derInstrInvType = derInstrInvType == null ? null : derInstrInvType.trim();
    }

    /**
     * 衍生金融工具挂钩标的资产
     * @return DER_INSTR_TARGET 衍生金融工具挂钩标的资产
     */
    public String getDerInstrTarget() {
        return derInstrTarget;
    }

    /**
     * 衍生金融工具挂钩标的资产
     * @param derInstrTarget 衍生金融工具挂钩标的资产
     */
    public void setDerInstrTarget(String derInstrTarget) {
        this.derInstrTarget = derInstrTarget == null ? null : derInstrTarget.trim();
    }

    /**
     * 利息结算方式
     * @return ACCT_MODE 利息结算方式
     */
    public String getAcctMode() {
        return acctMode;
    }

    /**
     * 利息结算方式
     * @param acctMode 利息结算方式
     */
    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode == null ? null : acctMode.trim();
    }

    /**
     * 含权情况说明
     * @return SPECIAL_CMT1 含权情况说明
     */
    public String getSpecialCmt1() {
        return specialCmt1;
    }

    /**
     * 含权情况说明
     * @param specialCmt1 含权情况说明
     */
    public void setSpecialCmt1(String specialCmt1) {
        this.specialCmt1 = specialCmt1 == null ? null : specialCmt1.trim();
    }

    /**
     * 结构性票据最高收益率
     * @return MAX_YIELD 结构性票据最高收益率
     */
    public BigDecimal getMaxYield() {
        return maxYield;
    }

    /**
     * 结构性票据最高收益率
     * @param maxYield 结构性票据最高收益率
     */
    public void setMaxYield(BigDecimal maxYield) {
        this.maxYield = maxYield;
    }

    /**
     * 结构性票据最低收益率
     * @return MIN_YIELD 结构性票据最低收益率
     */
    public BigDecimal getMinYield() {
        return minYield;
    }

    /**
     * 结构性票据最低收益率
     * @param minYield 结构性票据最低收益率
     */
    public void setMinYield(BigDecimal minYield) {
        this.minYield = minYield;
    }

    /**
     * 挂钩标的资产基准价格
     * @return BASIC_PRICE 挂钩标的资产基准价格
     */
    public BigDecimal getBasicPrice() {
        return basicPrice;
    }

    /**
     * 挂钩标的资产基准价格
     * @param basicPrice 挂钩标的资产基准价格
     */
    public void setBasicPrice(BigDecimal basicPrice) {
        this.basicPrice = basicPrice;
    }

    /**
     * 挂钩标的资产登记日价格
     * @return REG_DATE_PRICE 挂钩标的资产登记日价格
     */
    public BigDecimal getRegDatePrice() {
        return regDatePrice;
    }

    /**
     * 挂钩标的资产登记日价格
     * @param regDatePrice 挂钩标的资产登记日价格
     */
    public void setRegDatePrice(BigDecimal regDatePrice) {
        this.regDatePrice = regDatePrice;
    }

    /**
     * 交易费
     * @return TRADE_FEE 交易费
     */
    public String getTradeFee() {
        return tradeFee;
    }

    /**
     * 交易费
     * @param tradeFee 交易费
     */
    public void setTradeFee(String tradeFee) {
        this.tradeFee = tradeFee == null ? null : tradeFee.trim();
    }

    /**
     * 资管计划发起人机构编码
     * @return PARTY_CODE 资管计划发起人机构编码
     */
    public String getPartyCode() {
        return partyCode;
    }

    /**
     * 资管计划发起人机构编码
     * @param partyCode 资管计划发起人机构编码
     */
    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode == null ? null : partyCode.trim();
    }

    /**
     * 资管计划登记编码
     * @return CHECK_CODE 资管计划登记编码
     */
    public String getCheckCode() {
        return checkCode;
    }

    /**
     * 资管计划登记编码
     * @param checkCode 资管计划登记编码
     */
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode == null ? null : checkCode.trim();
    }

    /**
     * 管理人
     * @return MANAGER_ID 管理人
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 管理人
     * @param managerId 管理人
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    /**
     * 托管人
     * @return TRUSTEE_ID 托管人
     */
    public String getTrusteeId() {
        return trusteeId;
    }

    /**
     * 托管人
     * @param trusteeId 托管人
     */
    public void setTrusteeId(String trusteeId) {
        this.trusteeId = trusteeId == null ? null : trusteeId.trim();
    }

    /**
     * 资金实际投向 01 结构性投资类/02 货币市场工具类/03 债券类/04 股票类/05 股权类/06 另类投资类/07 混合类/08 非标准化债权类/09 银行存款类/10 理财直接融资工具类/11 新增可投资资产类/12 代客境外投资类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类/18 同业存单类/19拆放同业及买入返售类
     * @return TRU_ACT_TYPE 资金实际投向 01 结构性投资类/02 货币市场工具类/03 债券类/04 股票类/05 股权类/06 另类投资类/07 混合类/08 非标准化债权类/09 银行存款类/10 理财直接融资工具类/11 新增可投资资产类/12 代客境外投资类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类/18 同业存单类/19拆放同业及买入返售类
     */
    public String getTruActType() {
        return truActType;
    }

    /**
     * 资金实际投向 01 结构性投资类/02 货币市场工具类/03 债券类/04 股票类/05 股权类/06 另类投资类/07 混合类/08 非标准化债权类/09 银行存款类/10 理财直接融资工具类/11 新增可投资资产类/12 代客境外投资类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类/18 同业存单类/19拆放同业及买入返售类
     * @param truActType 资金实际投向 01 结构性投资类/02 货币市场工具类/03 债券类/04 股票类/05 股权类/06 另类投资类/07 混合类/08 非标准化债权类/09 银行存款类/10 理财直接融资工具类/11 新增可投资资产类/12 代客境外投资类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类/18 同业存单类/19拆放同业及买入返售类
     */
    public void setTruActType(String truActType) {
        this.truActType = truActType == null ? null : truActType.trim();
    }

    /**
     * 资金运用方式
     * @return INVEST_TYPE 资金运用方式
     */
    public String getInvestType() {
        return investType;
    }

    /**
     * 资金运用方式
     * @param investType 资金运用方式
     */
    public void setInvestType(String investType) {
        this.investType = investType == null ? null : investType.trim();
    }

    /**
     * 资金运用行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @return CAP_USE_INDU 资金运用行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public String getCapUseIndu() {
        return capUseIndu;
    }

    /**
     * 资金运用行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     * @param capUseIndu 资金运用行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    public void setCapUseIndu(String capUseIndu) {
        this.capUseIndu = capUseIndu == null ? null : capUseIndu.trim();
    }

    /**
     * 资管计划成立日期
     * @return PLAN_SDATE 资管计划成立日期
     */
    public Date getPlanSdate() {
        return planSdate;
    }

    /**
     * 资管计划成立日期
     * @param planSdate 资管计划成立日期
     */
    public void setPlanSdate(Date planSdate) {
        this.planSdate = planSdate;
    }

    /**
     * 资管计划终止日期
     * @return PLAN_EDATE 资管计划终止日期
     */
    public Date getPlanEdate() {
        return planEdate;
    }

    /**
     * 资管计划终止日期
     * @param planEdate 资管计划终止日期
     */
    public void setPlanEdate(Date planEdate) {
        this.planEdate = planEdate;
    }

    /**
     * 资管计划属性/委外投资属性 01 集合/02 单一/03 专项/04 定向/05 专户/99 其他
     * @return PRO_PROPERTY 资管计划属性/委外投资属性 01 集合/02 单一/03 专项/04 定向/05 专户/99 其他
     */
    public String getProProperty() {
        return proProperty;
    }

    /**
     * 资管计划属性/委外投资属性 01 集合/02 单一/03 专项/04 定向/05 专户/99 其他
     * @param proProperty 资管计划属性/委外投资属性 01 集合/02 单一/03 专项/04 定向/05 专户/99 其他
     */
    public void setProProperty(String proProperty) {
        this.proProperty = proProperty == null ? null : proProperty.trim();
    }

    /**
     * 最高预期收益率
     * @return MAX_RATE 最高预期收益率
     */
    public BigDecimal getMaxRate() {
        return maxRate;
    }

    /**
     * 最高预期收益率
     * @param maxRate 最高预期收益率
     */
    public void setMaxRate(BigDecimal maxRate) {
        this.maxRate = maxRate;
    }

    /**
     * 最低预期收益率
     * @return MIN_RATE 最低预期收益率
     */
    public BigDecimal getMinRate() {
        return minRate;
    }

    /**
     * 最低预期收益率
     * @param minRate 最低预期收益率
     */
    public void setMinRate(BigDecimal minRate) {
        this.minRate = minRate;
    }

    /**
     * 负债规模
     * @return DEBT_SCALE 负债规模
     */
    public BigDecimal getDebtScale() {
        return debtScale;
    }

    /**
     * 负债规模
     * @param debtScale 负债规模
     */
    public void setDebtScale(BigDecimal debtScale) {
        this.debtScale = debtScale;
    }

    /**
     * 购买结构 01 优先/02 劣后/03 整体
     * @return BUY_STRUCT 购买结构 01 优先/02 劣后/03 整体
     */
    public String getBuyStruct() {
        return buyStruct;
    }

    /**
     * 购买结构 01 优先/02 劣后/03 整体
     * @param buyStruct 购买结构 01 优先/02 劣后/03 整体
     */
    public void setBuyStruct(String buyStruct) {
        this.buyStruct = buyStruct == null ? null : buyStruct.trim();
    }

    /**
     * 管理方式 01 主动（自主管理）/02被动（委托管理）/99其他
     * @return MANAGE_TYPE 管理方式 01 主动（自主管理）/02被动（委托管理）/99其他
     */
    public String getManageType() {
        return manageType;
    }

    /**
     * 管理方式 01 主动（自主管理）/02被动（委托管理）/99其他
     * @param manageType 管理方式 01 主动（自主管理）/02被动（委托管理）/99其他
     */
    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    /**
     * 是否掌控计划底层资产详情 01 是/ 02 否
     * @return IS_CTRL_BOTTOM 是否掌控计划底层资产详情 01 是/ 02 否
     */
    public String getIsCtrlBottom() {
        return isCtrlBottom;
    }

    /**
     * 是否掌控计划底层资产详情 01 是/ 02 否
     * @param isCtrlBottom 是否掌控计划底层资产详情 01 是/ 02 否
     */
    public void setIsCtrlBottom(String isCtrlBottom) {
        this.isCtrlBottom = isCtrlBottom == null ? null : isCtrlBottom.trim();
    }

    /**
     * 管理费率
     * @return MGR_FEE_RATE 管理费率
     */
    public BigDecimal getMgrFeeRate() {
        return mgrFeeRate;
    }

    /**
     * 管理费率
     * @param mgrFeeRate 管理费率
     */
    public void setMgrFeeRate(BigDecimal mgrFeeRate) {
        this.mgrFeeRate = mgrFeeRate;
    }

    /**
     * 托管费率
     * @return TRU_FEE_RATE 托管费率
     */
    public BigDecimal getTruFeeRate() {
        return truFeeRate;
    }

    /**
     * 托管费率
     * @param truFeeRate 托管费率
     */
    public void setTruFeeRate(BigDecimal truFeeRate) {
        this.truFeeRate = truFeeRate;
    }

    /**
     * 交易相关合计费率
     * @return TRADE_FEE_RATE 交易相关合计费率
     */
    public BigDecimal getTradeFeeRate() {
        return tradeFeeRate;
    }

    /**
     * 交易相关合计费率
     * @param tradeFeeRate 交易相关合计费率
     */
    public void setTradeFeeRate(BigDecimal tradeFeeRate) {
        this.tradeFeeRate = tradeFeeRate;
    }

    /**
     * 中介服务机构合计费率
     * @return AGENCY_FEE_RATE 中介服务机构合计费率
     */
    public BigDecimal getAgencyFeeRate() {
        return agencyFeeRate;
    }

    /**
     * 中介服务机构合计费率
     * @param agencyFeeRate 中介服务机构合计费率
     */
    public void setAgencyFeeRate(BigDecimal agencyFeeRate) {
        this.agencyFeeRate = agencyFeeRate;
    }

    /**
     * 其他合计费率
     * @return OTHER_FEE_RATE 其他合计费率
     */
    public BigDecimal getOtherFeeRate() {
        return otherFeeRate;
    }

    /**
     * 其他合计费率
     * @param otherFeeRate 其他合计费率
     */
    public void setOtherFeeRate(BigDecimal otherFeeRate) {
        this.otherFeeRate = otherFeeRate;
    }

    /**
     * 资产价值
     * @return ASSET_VALUE 资产价值
     */
    public BigDecimal getAssetValue() {
        return assetValue;
    }

    /**
     * 资产价值
     * @param assetValue 资产价值
     */
    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }

    /**
     * 登记备案机构 01 中央国债登记结算有限责任公司/ 02 上海清算所 / 03 中国证券登记结算有限责任公司 / 04 中国证券投资基金业协会 /99 其他
     * @return REGIST_ORG1 登记备案机构 01 中央国债登记结算有限责任公司/ 02 上海清算所 / 03 中国证券登记结算有限责任公司 / 04 中国证券投资基金业协会 /99 其他
     */
    public String getRegistOrg1() {
        return registOrg1;
    }

    /**
     * 登记备案机构 01 中央国债登记结算有限责任公司/ 02 上海清算所 / 03 中国证券登记结算有限责任公司 / 04 中国证券投资基金业协会 /99 其他
     * @param registOrg1 登记备案机构 01 中央国债登记结算有限责任公司/ 02 上海清算所 / 03 中国证券登记结算有限责任公司 / 04 中国证券投资基金业协会 /99 其他
     */
    public void setRegistOrg1(String registOrg1) {
        this.registOrg1 = registOrg1 == null ? null : registOrg1.trim();
    }

    /**
     * 基金管理机构名称
     * @return MNG_ORG_NAME 基金管理机构名称
     */
    public String getMngOrgName() {
        return mngOrgName;
    }

    /**
     * 基金管理机构名称
     * @param mngOrgName 基金管理机构名称
     */
    public void setMngOrgName(String mngOrgName) {
        this.mngOrgName = mngOrgName == null ? null : mngOrgName.trim();
    }

    /**
     * 基金托管机构名称
     * @return TRU_ORG_NAME 基金托管机构名称
     */
    public String getTruOrgName() {
        return truOrgName;
    }

    /**
     * 基金托管机构名称
     * @param truOrgName 基金托管机构名称
     */
    public void setTruOrgName(String truOrgName) {
        this.truOrgName = truOrgName == null ? null : truOrgName.trim();
    }

    /**
     * 基金投资资产
     * @return FUND_INVEST_ASSET 基金投资资产
     */
    public String getFundInvestAsset() {
        return fundInvestAsset;
    }

    /**
     * 基金投资资产
     * @param fundInvestAsset 基金投资资产
     */
    public void setFundInvestAsset(String fundInvestAsset) {
        this.fundInvestAsset = fundInvestAsset == null ? null : fundInvestAsset.trim();
    }

    /**
     * 行内资产类别 01 场内股票质押回购/02 场外股票质押融资/03 融资租赁/99 其他
     * @return IN_ASSET_TYPE 行内资产类别 01 场内股票质押回购/02 场外股票质押融资/03 融资租赁/99 其他
     */
    public String getInAssetType() {
        return inAssetType;
    }

    /**
     * 行内资产类别 01 场内股票质押回购/02 场外股票质押融资/03 融资租赁/99 其他
     * @param inAssetType 行内资产类别 01 场内股票质押回购/02 场外股票质押融资/03 融资租赁/99 其他
     */
    public void setInAssetType(String inAssetType) {
        this.inAssetType = inAssetType == null ? null : inAssetType.trim();
    }

    /**
     * 行内资产类别说明
     * @return IN_ASSET_TYPE_CMM 行内资产类别说明
     */
    public String getInAssetTypeCmm() {
        return inAssetTypeCmm;
    }

    /**
     * 行内资产类别说明
     * @param inAssetTypeCmm 行内资产类别说明
     */
    public void setInAssetTypeCmm(String inAssetTypeCmm) {
        this.inAssetTypeCmm = inAssetTypeCmm == null ? null : inAssetTypeCmm.trim();
    }

    /**
     * 备注
     * @return REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 是否底层资产  01 是/ 02 否
     * @return IS_UNDER_ASSET 是否底层资产  01 是/ 02 否
     */
    public String getIsUnderAsset() {
        return isUnderAsset;
    }

    /**
     * 是否底层资产  01 是/ 02 否
     * @param isUnderAsset 是否底层资产  01 是/ 02 否
     */
    public void setIsUnderAsset(String isUnderAsset) {
        this.isUnderAsset = isUnderAsset == null ? null : isUnderAsset.trim();
    }

    /**
     * 是否属于政府投资基金  01 是/ 02 否
     * @return IS_GOVERN_FUND 是否属于政府投资基金  01 是/ 02 否
     */
    public String getIsGovernFund() {
        return isGovernFund;
    }

    /**
     * 是否属于政府投资基金  01 是/ 02 否
     * @param isGovernFund 是否属于政府投资基金  01 是/ 02 否
     */
    public void setIsGovernFund(String isGovernFund) {
        this.isGovernFund = isGovernFund == null ? null : isGovernFund.trim();
    }

    /**
     * 政府投资基金投向 01 投向创业早期企业/ 02 投向中小微企业/ 03 投向产业转型升级和发展/ 04 投向基础设施和公共服务领域/99 其他
     * @return GOVERN_INVEST_FUND 政府投资基金投向 01 投向创业早期企业/ 02 投向中小微企业/ 03 投向产业转型升级和发展/ 04 投向基础设施和公共服务领域/99 其他
     */
    public String getGovernInvestFund() {
        return governInvestFund;
    }

    /**
     * 政府投资基金投向 01 投向创业早期企业/ 02 投向中小微企业/ 03 投向产业转型升级和发展/ 04 投向基础设施和公共服务领域/99 其他
     * @param governInvestFund 政府投资基金投向 01 投向创业早期企业/ 02 投向中小微企业/ 03 投向产业转型升级和发展/ 04 投向基础设施和公共服务领域/99 其他
     */
    public void setGovernInvestFund(String governInvestFund) {
        this.governInvestFund = governInvestFund == null ? null : governInvestFund.trim();
    }

    /**
     * 基金公司名称
     * @return FUND_ENPE_NAME 基金公司名称
     */
    public String getFundEnpeName() {
        return fundEnpeName;
    }

    /**
     * 基金公司名称
     * @param fundEnpeName 基金公司名称
     */
    public void setFundEnpeName(String fundEnpeName) {
        this.fundEnpeName = fundEnpeName == null ? null : fundEnpeName.trim();
    }

    /**
     * 受托人
     * @return TRUSTEE 受托人
     */
    public String getTrustee() {
        return trustee;
    }

    /**
     * 受托人
     * @param trustee 受托人
     */
    public void setTrustee(String trustee) {
        this.trustee = trustee == null ? null : trustee.trim();
    }

    /**
     * 行内报送截止日
     * @return SUBMIT_DEADLINE 行内报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 行内报送截止日
     * @param submitDeadline 行内报送截止日
     */
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 中债报送截止日
     * @return CHB_SUBMIT_DEADLINE 中债报送截止日
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 中债报送截止日
     * @param chbSubmitDeadline 中债报送截止日
     */
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    /**
     * 报送日期
     * @return SUBMIT_TIME 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 报送日期
     * @param submitTime 报送日期
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 数据日期
     * @return STATUS_DATE 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 数据状态 01 新建/02 修改/03 删除
     * @return STATUS 数据状态 01 新建/02 修改/03 删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态 01 新建/02 修改/03 删除
     * @param status 数据状态 01 新建/02 修改/03 删除
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 部门代码
     * @return DEPT_CODE 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 报送状态 01 未报送/02 手工处理/03 接口报送
     * @return SEND_STATUS 报送状态 01 未报送/02 手工处理/03 接口报送
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 报送状态 01 未报送/02 手工处理/03 接口报送
     * @param sendStatus 报送状态 01 未报送/02 手工处理/03 接口报送
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public String getSumitSeq() {
		return sumitSeq;
	}

	public void setSumitSeq(String sumitSeq) {
		this.sumitSeq = sumitSeq;
	}

	/**
     * 报送反馈状态
     * @return SUBMIT_FEEDBACK_STATUS 报送反馈状态
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 报送反馈状态
     * @param submitFeedbackStatus 报送反馈状态
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 数据来源
     * @return DATA_SOURCE 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * 删除原因
     * @return DELETE_RES 删除原因
     */
    public String getDeleteRes() {
        return deleteRes;
    }

    /**
     * 删除原因
     * @param deleteRes 删除原因
     */
    public void setDeleteRes(String deleteRes) {
        this.deleteRes = deleteRes == null ? null : deleteRes.trim();
    }

    /**
     * 资金运用行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @return CAP_USE_INDU_LEVEL2 资金运用行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public String getCapUseInduLevel2() {
        return capUseInduLevel2;
    }

    /**
     * 资金运用行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     * @param capUseInduLevel2 资金运用行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    public void setCapUseInduLevel2(String capUseInduLevel2) {
        this.capUseInduLevel2 = capUseInduLevel2 == null ? null : capUseInduLevel2.trim();
    }

    /**
     * 发行机构
     * @return ISSUE_PARTY_NAME 发行机构
     */
    public String getIssuePartyName() {
        return issuePartyName;
    }

    /**
     * 发行机构
     * @param issuePartyName 发行机构
     */
    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName == null ? null : issuePartyName.trim();
    }
    public String getDepoType() {
		return depoType;
	}

	public void setDepoType(String depoType) {
		this.depoType = depoType;
	}

	public String getRivalPartyType() {
		return rivalPartyType;
	}

	public void setRivalPartyType(String rivalPartyType) {
		this.rivalPartyType = rivalPartyType;
	}

	public BigDecimal getPrinBaseAmt() {
		return prinBaseAmt;
	}

	public void setPrinBaseAmt(BigDecimal prinBaseAmt) {
		this.prinBaseAmt = prinBaseAmt;
	}

	public Date getFmDate() {
		return fmDate;
	}

	public void setFmDate(Date fmDate) {
		this.fmDate = fmDate;
	}

	public String getCouponSpecies() {
		return couponSpecies;
	}

	public void setCouponSpecies(String couponSpecies) {
		this.couponSpecies = couponSpecies;
	}

	public String getInterestRuleFlag() {
		return interestRuleFlag;
	}

	public void setInterestRuleFlag(String interestRuleFlag) {
		this.interestRuleFlag = interestRuleFlag;
	}

	public String getIntpayRule() {
		return intpayRule;
	}

	public void setIntpayRule(String intpayRule) {
		this.intpayRule = intpayRule;
	}

	public String getFloatFactorFlag() {
		return floatFactorFlag;
	}

	public void setFloatFactorFlag(String floatFactorFlag) {
		this.floatFactorFlag = floatFactorFlag;
	}

	public String getStructGrade() {
		return structGrade;
	}

	public void setStructGrade(String structGrade) {
		this.structGrade = structGrade;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getPeriodRepaymentFlag() {
		return periodRepaymentFlag;
	}

	public void setPeriodRepaymentFlag(String periodRepaymentFlag) {
		this.periodRepaymentFlag = periodRepaymentFlag;
	}

	public String getBasicAssetType() {
		return basicAssetType;
	}

	public void setBasicAssetType(String basicAssetType) {
		this.basicAssetType = basicAssetType;
	}

	public BigDecimal getExcessProfitRate() {
		return excessProfitRate;
	}

	public void setExcessProfitRate(BigDecimal excessProfitRate) {
		this.excessProfitRate = excessProfitRate;
	}

	public String getPledgeType() {
		return pledgeType;
	}

	public void setPledgeType(String pledgeType) {
		this.pledgeType = pledgeType;
	}

	public BigDecimal getPledgeValue() {
		return pledgeValue;
	}

	public void setPledgeValue(BigDecimal pledgeValue) {
		this.pledgeValue = pledgeValue;
	}

	public String getNatureGuarantee() {
		return natureGuarantee;
	}

	public void setNatureGuarantee(String natureGuarantee) {
		this.natureGuarantee = natureGuarantee;
	}

	public String getRelationFinan() {
		return relationFinan;
	}

	public void setRelationFinan(String relationFinan) {
		this.relationFinan = relationFinan;
	}

	public String getContainType() {
		return containType;
	}

	public void setContainType(String containType) {
		this.containType = containType;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

	public Date getExerciseDate() {
		return exerciseDate;
	}

	public void setExerciseDate(Date exerciseDate) {
		this.exerciseDate = exerciseDate;
	}

	public Date getFirstExerciseDate() {
		return firstExerciseDate;
	}

	public void setFirstExerciseDate(Date firstExerciseDate) {
		this.firstExerciseDate = firstExerciseDate;
	}

	public Long getExercisePeriod() {
		return exercisePeriod;
	}

	public void setExercisePeriod(Long exercisePeriod) {
		this.exercisePeriod = exercisePeriod;
	}

	public BigDecimal getExercisePrice() {
		return exercisePrice;
	}

	public void setExercisePrice(BigDecimal exercisePrice) {
		this.exercisePrice = exercisePrice;
	}

	public String getPerpetualClauseType() {
		return perpetualClauseType;
	}

	public void setPerpetualClauseType(String perpetualClauseType) {
		this.perpetualClauseType = perpetualClauseType;
	}

	public String getDeferredInterestType() {
		return deferredInterestType;
	}

	public void setDeferredInterestType(String deferredInterestType) {
		this.deferredInterestType = deferredInterestType;
	}

	public String getDeferredInterestInt() {
		return deferredInterestInt;
	}

	public void setDeferredInterestInt(String deferredInterestInt) {
		this.deferredInterestInt = deferredInterestInt;
	}

	public Date getFirstReprceDate() {
		return firstReprceDate;
	}

	public void setFirstReprceDate(Date firstReprceDate) {
		this.firstReprceDate = firstReprceDate;
	}

	public Long getReprceCycle() {
		return reprceCycle;
	}

	public void setReprceCycle(Long reprceCycle) {
		this.reprceCycle = reprceCycle;
	}

	public String getPartRedemFlag() {
		return partRedemFlag;
	}

	public void setPartRedemFlag(String partRedemFlag) {
		this.partRedemFlag = partRedemFlag;
	}

	public BigDecimal getPartRedemRate() {
		return partRedemRate;
	}

	public void setPartRedemRate(BigDecimal partRedemRate) {
		this.partRedemRate = partRedemRate;
	}

	public String getCreditOrgCode() {
		return creditOrgCode;
	}

	public void setCreditOrgCode(String creditOrgCode) {
		this.creditOrgCode = creditOrgCode;
	}

	public String getCreditOrgName() {
		return creditOrgName;
	}

	public void setCreditOrgName(String creditOrgName) {
		this.creditOrgName = creditOrgName;
	}

	public Date getEquityQuit() {
		return equityQuit;
	}

	public void setEquityQuit(Date equityQuit) {
		this.equityQuit = equityQuit;
	}

	public String getDeptEquity() {
		return deptEquity;
	}

	public void setDeptEquity(String deptEquity) {
		this.deptEquity = deptEquity;
	}

	public String getHoldPurpose() {
		return holdPurpose;
	}

	public void setHoldPurpose(String holdPurpose) {
		this.holdPurpose = holdPurpose;
	}

	public String getFinancialComissue() {
		return financialComissue;
	}

	public void setFinancialComissue(String financialComissue) {
		this.financialComissue = financialComissue;
	}
}