package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdBaseinfoDetail extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 父产品代码
     */
    private String fprodId;

    /**
     * 机构代码
     */
    private String issuePartyId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品登记编码
     */
    private String regCode;

    /**
     * 行内标识码
     */
    private String prodInsId;

    /**
     * 产品品牌
     */
    private String prodBrand;

    /**
     * 产品期次
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodStage;

    /**
     * 募集方式
     */
    private String raiseType;

    /**
     * 产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    private String profitType;

    /**
     * 产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上
     */
    private String termtype;

    /**
     * 是否金融同业专属
     */
    private String excFinSector;

    /**
     * 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    private String operateMode;

    /**
     * 是否设置最短持有期限
     */
    private String isShortTerm;

    /**
     * 最短持有期限(天)
     */
    private Long shortTerm;

    /**
     * 是否现金管理类
     */
    private String cashManaCate;

    /**
     * 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    private String ivstorientation;

    /**
     * 合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他
     */
    private String contMode;

    /**
     * 募集币种
     */
    private String raiseCcy;

    /**
     * 兑付本金币种
     */
    private String cashPrinCcy;

    /**
     * 兑付收益币种
     */
    private String cashProfCcy;

    /**
     * 资金投向地区  01 境内/02 境外/03 境内和境外
     */
    private String investArae;

    /**
     * 境内托管机构名称
     */
    private String inDepName;

    /**
     * 境内托管机构代码
     */
    private String inDepCode;

    /**
     * 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     */
    private String riskLevel;

    /**
     * 发行机构提前终止权标识
     */
    private String termRightFlag;

    /**
     * 客户赎回权标识
     */
    private String cusRedeemFlag;

    /**
     * 产品增信标识
     */
    private String creditFlag;

    /**
     * 产品增信机构类型
     */
    private String creditType;

    /**
     * 产品定价方式
     */
    private String pricedMode;

    /**
     * 计息基础 A/360, A/365
     */
    private String basic;

    /**
     * 标识哪种产品，净值N，货币M，利率R，客户周期C
     */
    private String prodtype;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 提前终止日
     */
    private Date terminatedate;

    /**
     * 募集起始日期
     */
    private Date raiseVdate;

    /**
     * 募集结束日期
     */
    private Date raiseEnddate;

    /**
     * 是否结构化分层产品
     */
    private String isConstracte;

    /**
     * 预计最低收益率-发行
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowYieldIssue;

    /**
     * 预计最高收益率-发行
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highYieldIssue;

    /**
     * 业绩比较基准下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldLow;

    /**
     * 业绩比较基准上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldHigh;

    /**
     * 开放模式01 有规律开放/02 无规律开放
     */
    private String openMode;

    /**
     * 规律开放周期
     */
    private String openCircle;

    /**
     * 其他规律开放周期（天）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal openCircleDays;

    /**
     * 首次开放日
     */
    private Date firstOpenDay;

    /**
     * 节假日是否开放
     */
    private String holidayOnFlag;

    /**
     * 平均开放次数（年化）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal avgOpenCount;

    /**
     * 开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他
     */
    private String openBuss;

    /**
     * 资金托管账号
     */
    private String busiAcctNo;

    /**
     * 资金托管账户
     */
    private String acctName;

    /**
     * 受托职责
     */
    private String fiduciaryDuty;

    /**
     * 转让类型
     */
    private String transferType;

    /**
     * 货币市场基金标识
     */
    private String isMarketFund;

    /**
     * 管理方式
     */
    private String manageType;

    /**
     * 客户类型:单选，1-单位，2-个人，3-个人及单位
     */
    private String customerType;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 是否母行划转产品
     */
    private String isTransfer;

    /**
     * 现金管理类产品-是否完全符合
     */
    private String isFullyCompliant;

    /**
     * 是否养老理财产品
     */
    private String isYllccp;

    /**
     * 投资者类型
     */
    private String investorType;

    /**
     * 发行要素维护完整
     */
    private String isIntact;

    /**
     * 是否代销，01/02
     */
    private String isagency;

    /**
     * 目标客户类型,01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     */
    private String saletarget;

    /**
     * 存续状态  存续01，终止02，未起息03
     */
    private String durationflag;

    /**
     * 募集起始日期（从）
     */
    private Date raisePstrdate;

    /**
     * 募集起始日期（到）
     */
    private Date raisePenddate;

    /**
     * 报备状态
     */
    private String declareStatus;

    /**
     * 发行状态  确认发行/发行成功/发行失败/不发行
     */
    private String issuingStatus;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 产品设计人姓名
     */
    private String designerName;

    /**
     * 产品设计人身份证号
     */
    private String designerId;

    /**
     * 产品审批人姓名
     */
    private String approverName;

    /**
     * 产品审批人身份证号
     */
    private String approverId;

    /**
     * 投资经理姓名
     */
    private String managerName;

    /**
     * 投资经理身份证号
     */
    private String managerId;

    /**
     * 会计核算方式  01 表内/02 表外
     */
    private String bookMode;

    /**
     * 业务联络人姓名
     */
    private String liaisonName;

    /**
     * 业务联络人座机
     */
    private String liaisonTel;

    /**
     * 业务联络人手机
     */
    private String liaisonMobile;

    /**
     * 业务联络人邮箱
     */
    private String liaisonEmail;

    /**
     * 产品投资国家或地区参考《世界各国和地区名称代码》
     */
    private String investCountry;

    /**
     * 理财业务服务模式01 综合理财服务/02 理财顾问服务/99 其他
     */
    private String finanSerModel;

    /**
     * 产品资产配置方式01 单一资产配置（一对一）/02 资产组合配置（一对多）/03 其他配置（多对多）
     */
    private String cmmode;

    /**
     * 产品管理模式01银行为实际管理人/02 其他机构为实际管理人/03 资产管理公司/04 特殊目的载体（SPV）
     */
    private String pmmode;

    /**
     * 实际管理人名称
     */
    private String mname;

    /**
     * 投资资产类型  01 结构性投资类/02 货币市场工具类/03 债券类/04 非标准化债权类/05 股票类/06 股权类/07 另类投资类/08 混合类/09代客境外投资类/10 银行存款类/11 理财直接融资工具类/12 新增可投资资产类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类
     */
    private String invAssetType;

    /**
     * 合作机构
     */
    private String cooperationName;

    /**
     * 结构性产品挂钩标的01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     */
    private String conPrdTraget;

    /**
     * 是否有预期收益率01 是/02 否
     */
    private String isYield;

    /**
     * 是否有收益率测算依据  01 是/02 否
     */
    private String isYieldMeasBasis;

    /**
     * 投资者风险偏好01 保守型/02 稳健型/03 平衡型/04 成长型/05 进取型
     */
    private String riskType;

    /**
     * 起点销售金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleAmtStr;

    /**
     * 计划募集金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseAmtPlan;

    /**
     * 销售手续费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleFeeRate;

    /**
     * 投资本金到账日
     */
    private String investPrinDate;

    /**
     * 投资收益到账日
     */
    private String investProfDate;

    /**
     * 托管费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal truFeeRate;

    /**
     * 产品起始日期
     */
    private Date prdVdate;

    /**
     * 产品终止日期
     */
    private Date prdMdate;

    /**
     * 平均预计客户年收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expAvgYield;

    /**
     * 层级类别  11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五
     */
    private String gradeType;

    /**
     * 产品增信形式
     */
    private String creditMode;

    /**
     * 开放模式申 01	定期开放02	高频开放03	周期开放
     */
    private String opentypep;

    /**
     * 开放天数申
     */
    private String opendayp;

    /**
     * 开放周期申
     */
    private String opencyclep;

    /**
     * 开放周期单位
     */
    private String opencycleunitp;

    /**
     * 周申
     */
    private String weekp;

    /**
     * 几号申
     */
    private String dayp;

    /**
     * 第几工作日申
     */
    private String workdayp;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 经办人
     */
    private String agent;

    /**
     * 最短持有期后是否自由赎回
     */
    private String isFreeRedem;

    /**
     * 业绩比较基准%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 投资管理费率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investFeeRate;

    /**
     * 预计客户最高年收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highYield;

    /**
     * 预计客户最低年收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowYield;

    /**
     * 是否合规新产品
     */
    private String isFhxgcp;

    /**
     * 是否收益权转让产品
     */
    private String isSyqzccp;

    /**
     * 数据来源
     */
    private String svrDataSources;

    /**
     * 本位币
     */
    private String functionalCurrency;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 父产品代码
     * @return 父产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 设置 父产品代码
     * @param fprodId 父产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 机构代码
     * @param issuePartyId 机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getRegCode() {
        return regCode;
    }

    /**
     * 设置 产品登记编码
     * @param regCode 产品登记编码
     */
    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    /**
     * 取得 行内标识码
     * @return 行内标识码
     */
    public String getProdInsId() {
        return prodInsId;
    }

    /**
     * 设置 行内标识码
     * @param prodInsId 行内标识码
     */
    public void setProdInsId(String prodInsId) {
        this.prodInsId = prodInsId == null ? null : prodInsId.trim();
    }

    /**
     * 取得 产品品牌
     * @return 产品品牌
     */
    public String getProdBrand() {
        return prodBrand;
    }

    /**
     * 设置 产品品牌
     * @param prodBrand 产品品牌
     */
    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand == null ? null : prodBrand.trim();
    }

    /**
     * 取得 产品期次
     * @return 产品期次
     */
    public BigDecimal getProdStage() {
        return prodStage;
    }

    /**
     * 设置 产品期次
     * @param prodStage 产品期次
     */
    public void setProdStage(BigDecimal prodStage) {
        this.prodStage = prodStage;
    }

    /**
     * 取得 募集方式
     * @return 募集方式
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 设置 募集方式
     * @param raiseType 募集方式
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType == null ? null : raiseType.trim();
    }

    /**
     * 取得 产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益
     * @return 产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益
     * @param profitType 产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上
     * @return 产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上
     */
    public String getTermtype() {
        return termtype;
    }

    /**
     * 设置 产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上
     * @param termtype 产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上
     */
    public void setTermtype(String termtype) {
        this.termtype = termtype == null ? null : termtype.trim();
    }

    /**
     * 取得 是否金融同业专属
     * @return 是否金融同业专属
     */
    public String getExcFinSector() {
        return excFinSector;
    }

    /**
     * 设置 是否金融同业专属
     * @param excFinSector 是否金融同业专属
     */
    public void setExcFinSector(String excFinSector) {
        this.excFinSector = excFinSector == null ? null : excFinSector.trim();
    }

    /**
     * 取得 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     * @return 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 设置 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     * @param operateMode 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 取得 是否设置最短持有期限
     * @return 是否设置最短持有期限
     */
    public String getIsShortTerm() {
        return isShortTerm;
    }

    /**
     * 设置 是否设置最短持有期限
     * @param isShortTerm 是否设置最短持有期限
     */
    public void setIsShortTerm(String isShortTerm) {
        this.isShortTerm = isShortTerm == null ? null : isShortTerm.trim();
    }

    /**
     * 取得 最短持有期限(天)
     * @return 最短持有期限(天)
     */
    public Long getShortTerm() {
        return shortTerm;
    }

    /**
     * 设置 最短持有期限(天)
     * @param shortTerm 最短持有期限(天)
     */
    public void setShortTerm(Long shortTerm) {
        this.shortTerm = shortTerm;
    }

    /**
     * 取得 是否现金管理类
     * @return 是否现金管理类
     */
    public String getCashManaCate() {
        return cashManaCate;
    }

    /**
     * 设置 是否现金管理类
     * @param cashManaCate 是否现金管理类
     */
    public void setCashManaCate(String cashManaCate) {
        this.cashManaCate = cashManaCate == null ? null : cashManaCate.trim();
    }

    /**
     * 取得 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @return 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public String getIvstorientation() {
        return ivstorientation;
    }

    /**
     * 设置 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @param ivstorientation 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation == null ? null : ivstorientation.trim();
    }

    /**
     * 取得 合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他
     * @return 合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他
     */
    public String getContMode() {
        return contMode;
    }

    /**
     * 设置 合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他
     * @param contMode 合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他
     */
    public void setContMode(String contMode) {
        this.contMode = contMode == null ? null : contMode.trim();
    }

    /**
     * 取得 募集币种
     * @return 募集币种
     */
    public String getRaiseCcy() {
        return raiseCcy;
    }

    /**
     * 设置 募集币种
     * @param raiseCcy 募集币种
     */
    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy == null ? null : raiseCcy.trim();
    }

    /**
     * 取得 兑付本金币种
     * @return 兑付本金币种
     */
    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    /**
     * 设置 兑付本金币种
     * @param cashPrinCcy 兑付本金币种
     */
    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy == null ? null : cashPrinCcy.trim();
    }

    /**
     * 取得 兑付收益币种
     * @return 兑付收益币种
     */
    public String getCashProfCcy() {
        return cashProfCcy;
    }

    /**
     * 设置 兑付收益币种
     * @param cashProfCcy 兑付收益币种
     */
    public void setCashProfCcy(String cashProfCcy) {
        this.cashProfCcy = cashProfCcy == null ? null : cashProfCcy.trim();
    }

    /**
     * 取得 资金投向地区  01 境内/02 境外/03 境内和境外
     * @return 资金投向地区  01 境内/02 境外/03 境内和境外
     */
    public String getInvestArae() {
        return investArae;
    }

    /**
     * 设置 资金投向地区  01 境内/02 境外/03 境内和境外
     * @param investArae 资金投向地区  01 境内/02 境外/03 境内和境外
     */
    public void setInvestArae(String investArae) {
        this.investArae = investArae == null ? null : investArae.trim();
    }

    /**
     * 取得 境内托管机构名称
     * @return 境内托管机构名称
     */
    public String getInDepName() {
        return inDepName;
    }

    /**
     * 设置 境内托管机构名称
     * @param inDepName 境内托管机构名称
     */
    public void setInDepName(String inDepName) {
        this.inDepName = inDepName == null ? null : inDepName.trim();
    }

    /**
     * 取得 境内托管机构代码
     * @return 境内托管机构代码
     */
    public String getInDepCode() {
        return inDepCode;
    }

    /**
     * 设置 境内托管机构代码
     * @param inDepCode 境内托管机构代码
     */
    public void setInDepCode(String inDepCode) {
        this.inDepCode = inDepCode == null ? null : inDepCode.trim();
    }

    /**
     * 取得 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     * @return 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     * @param riskLevel 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 取得 发行机构提前终止权标识
     * @return 发行机构提前终止权标识
     */
    public String getTermRightFlag() {
        return termRightFlag;
    }

    /**
     * 设置 发行机构提前终止权标识
     * @param termRightFlag 发行机构提前终止权标识
     */
    public void setTermRightFlag(String termRightFlag) {
        this.termRightFlag = termRightFlag == null ? null : termRightFlag.trim();
    }

    /**
     * 取得 客户赎回权标识
     * @return 客户赎回权标识
     */
    public String getCusRedeemFlag() {
        return cusRedeemFlag;
    }

    /**
     * 设置 客户赎回权标识
     * @param cusRedeemFlag 客户赎回权标识
     */
    public void setCusRedeemFlag(String cusRedeemFlag) {
        this.cusRedeemFlag = cusRedeemFlag == null ? null : cusRedeemFlag.trim();
    }

    /**
     * 取得 产品增信标识
     * @return 产品增信标识
     */
    public String getCreditFlag() {
        return creditFlag;
    }

    /**
     * 设置 产品增信标识
     * @param creditFlag 产品增信标识
     */
    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag == null ? null : creditFlag.trim();
    }

    /**
     * 取得 产品增信机构类型
     * @return 产品增信机构类型
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 产品增信机构类型
     * @param creditType 产品增信机构类型
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    /**
     * 取得 产品定价方式
     * @return 产品定价方式
     */
    public String getPricedMode() {
        return pricedMode;
    }

    /**
     * 设置 产品定价方式
     * @param pricedMode 产品定价方式
     */
    public void setPricedMode(String pricedMode) {
        this.pricedMode = pricedMode == null ? null : pricedMode.trim();
    }

    /**
     * 取得 计息基础 A/360, A/365
     * @return 计息基础 A/360, A/365
     */
    public String getBasic() {
        return basic;
    }

    /**
     * 设置 计息基础 A/360, A/365
     * @param basic 计息基础 A/360, A/365
     */
    public void setBasic(String basic) {
        this.basic = basic == null ? null : basic.trim();
    }

    /**
     * 取得 标识哪种产品，净值N，货币M，利率R，客户周期C
     * @return 标识哪种产品，净值N，货币M，利率R，客户周期C
     */
    public String getProdtype() {
        return prodtype;
    }

    /**
     * 设置 标识哪种产品，净值N，货币M，利率R，客户周期C
     * @param prodtype 标识哪种产品，净值N，货币M，利率R，客户周期C
     */
    public void setProdtype(String prodtype) {
        this.prodtype = prodtype == null ? null : prodtype.trim();
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 提前终止日
     * @return 提前终止日
     */
    public Date getTerminatedate() {
        return terminatedate;
    }

    /**
     * 设置 提前终止日
     * @param terminatedate 提前终止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTerminatedate(Date terminatedate) {
        this.terminatedate = terminatedate;
    }

    /**
     * 取得 募集起始日期
     * @return 募集起始日期
     */
    public Date getRaiseVdate() {
        return raiseVdate;
    }

    /**
     * 设置 募集起始日期
     * @param raiseVdate 募集起始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseVdate(Date raiseVdate) {
        this.raiseVdate = raiseVdate;
    }

    /**
     * 取得 募集结束日期
     * @return 募集结束日期
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 设置 募集结束日期
     * @param raiseEnddate 募集结束日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 取得 是否结构化分层产品
     * @return 是否结构化分层产品
     */
    public String getIsConstracte() {
        return isConstracte;
    }

    /**
     * 设置 是否结构化分层产品
     * @param isConstracte 是否结构化分层产品
     */
    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte == null ? null : isConstracte.trim();
    }

    /**
     * 取得 预计最低收益率-发行
     * @return 预计最低收益率-发行
     */
    public BigDecimal getLowYieldIssue() {
        return lowYieldIssue;
    }

    /**
     * 设置 预计最低收益率-发行
     * @param lowYieldIssue 预计最低收益率-发行
     */
    public void setLowYieldIssue(BigDecimal lowYieldIssue) {
        this.lowYieldIssue = lowYieldIssue;
    }

    /**
     * 取得 预计最高收益率-发行
     * @return 预计最高收益率-发行
     */
    public BigDecimal getHighYieldIssue() {
        return highYieldIssue;
    }

    /**
     * 设置 预计最高收益率-发行
     * @param highYieldIssue 预计最高收益率-发行
     */
    public void setHighYieldIssue(BigDecimal highYieldIssue) {
        this.highYieldIssue = highYieldIssue;
    }

    /**
     * 取得 业绩比较基准下限
     * @return 业绩比较基准下限
     */
    public BigDecimal getYieldLow() {
        return yieldLow;
    }

    /**
     * 设置 业绩比较基准下限
     * @param yieldLow 业绩比较基准下限
     */
    public void setYieldLow(BigDecimal yieldLow) {
        this.yieldLow = yieldLow;
    }

    /**
     * 取得 业绩比较基准上限
     * @return 业绩比较基准上限
     */
    public BigDecimal getYieldHigh() {
        return yieldHigh;
    }

    /**
     * 设置 业绩比较基准上限
     * @param yieldHigh 业绩比较基准上限
     */
    public void setYieldHigh(BigDecimal yieldHigh) {
        this.yieldHigh = yieldHigh;
    }

    /**
     * 取得 开放模式01 有规律开放/02 无规律开放
     * @return 开放模式01 有规律开放/02 无规律开放
     */
    public String getOpenMode() {
        return openMode;
    }

    /**
     * 设置 开放模式01 有规律开放/02 无规律开放
     * @param openMode 开放模式01 有规律开放/02 无规律开放
     */
    public void setOpenMode(String openMode) {
        this.openMode = openMode == null ? null : openMode.trim();
    }

    /**
     * 取得 规律开放周期
     * @return 规律开放周期
     */
    public String getOpenCircle() {
        return openCircle;
    }

    /**
     * 设置 规律开放周期
     * @param openCircle 规律开放周期
     */
    public void setOpenCircle(String openCircle) {
        this.openCircle = openCircle == null ? null : openCircle.trim();
    }

    /**
     * 取得 其他规律开放周期（天）
     * @return 其他规律开放周期（天）
     */
    public BigDecimal getOpenCircleDays() {
        return openCircleDays;
    }

    /**
     * 设置 其他规律开放周期（天）
     * @param openCircleDays 其他规律开放周期（天）
     */
    public void setOpenCircleDays(BigDecimal openCircleDays) {
        this.openCircleDays = openCircleDays;
    }

    /**
     * 取得 首次开放日
     * @return 首次开放日
     */
    public Date getFirstOpenDay() {
        return firstOpenDay;
    }

    /**
     * 设置 首次开放日
     * @param firstOpenDay 首次开放日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstOpenDay(Date firstOpenDay) {
        this.firstOpenDay = firstOpenDay;
    }

    /**
     * 取得 节假日是否开放
     * @return 节假日是否开放
     */
    public String getHolidayOnFlag() {
        return holidayOnFlag;
    }

    /**
     * 设置 节假日是否开放
     * @param holidayOnFlag 节假日是否开放
     */
    public void setHolidayOnFlag(String holidayOnFlag) {
        this.holidayOnFlag = holidayOnFlag == null ? null : holidayOnFlag.trim();
    }

    /**
     * 取得 平均开放次数（年化）
     * @return 平均开放次数（年化）
     */
    public BigDecimal getAvgOpenCount() {
        return avgOpenCount;
    }

    /**
     * 设置 平均开放次数（年化）
     * @param avgOpenCount 平均开放次数（年化）
     */
    public void setAvgOpenCount(BigDecimal avgOpenCount) {
        this.avgOpenCount = avgOpenCount;
    }

    /**
     * 取得 开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他
     * @return 开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他
     */
    public String getOpenBuss() {
        return openBuss;
    }

    /**
     * 设置 开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他
     * @param openBuss 开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他
     */
    public void setOpenBuss(String openBuss) {
        this.openBuss = openBuss == null ? null : openBuss.trim();
    }

    /**
     * 取得 资金托管账号
     * @return 资金托管账号
     */
    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    /**
     * 设置 资金托管账号
     * @param busiAcctNo 资金托管账号
     */
    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo == null ? null : busiAcctNo.trim();
    }

    /**
     * 取得 资金托管账户
     * @return 资金托管账户
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置 资金托管账户
     * @param acctName 资金托管账户
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName == null ? null : acctName.trim();
    }

    /**
     * 取得 受托职责
     * @return 受托职责
     */
    public String getFiduciaryDuty() {
        return fiduciaryDuty;
    }

    /**
     * 设置 受托职责
     * @param fiduciaryDuty 受托职责
     */
    public void setFiduciaryDuty(String fiduciaryDuty) {
        this.fiduciaryDuty = fiduciaryDuty == null ? null : fiduciaryDuty.trim();
    }

    /**
     * 取得 转让类型
     * @return 转让类型
     */
    public String getTransferType() {
        return transferType;
    }

    /**
     * 设置 转让类型
     * @param transferType 转让类型
     */
    public void setTransferType(String transferType) {
        this.transferType = transferType == null ? null : transferType.trim();
    }

    /**
     * 取得 货币市场基金标识
     * @return 货币市场基金标识
     */
    public String getIsMarketFund() {
        return isMarketFund;
    }

    /**
     * 设置 货币市场基金标识
     * @param isMarketFund 货币市场基金标识
     */
    public void setIsMarketFund(String isMarketFund) {
        this.isMarketFund = isMarketFund == null ? null : isMarketFund.trim();
    }

    /**
     * 取得 管理方式
     * @return 管理方式
     */
    public String getManageType() {
        return manageType;
    }

    /**
     * 设置 管理方式
     * @param manageType 管理方式
     */
    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    /**
     * 取得 客户类型:单选，1-单位，2-个人，3-个人及单位
     * @return 客户类型:单选，1-单位，2-个人，3-个人及单位
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * 设置 客户类型:单选，1-单位，2-个人，3-个人及单位
     * @param customerType 客户类型:单选，1-单位，2-个人，3-个人及单位
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    /**
     * 取得 交易日历
     * @return 交易日历
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 交易日历
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId == null ? null : calendarId.trim();
    }

    /**
     * 取得 是否母行划转产品
     * @return 是否母行划转产品
     */
    public String getIsTransfer() {
        return isTransfer;
    }

    /**
     * 设置 是否母行划转产品
     * @param isTransfer 是否母行划转产品
     */
    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer == null ? null : isTransfer.trim();
    }

    /**
     * 取得 现金管理类产品-是否完全符合
     * @return 现金管理类产品-是否完全符合
     */
    public String getIsFullyCompliant() {
        return isFullyCompliant;
    }

    /**
     * 设置 现金管理类产品-是否完全符合
     * @param isFullyCompliant 现金管理类产品-是否完全符合
     */
    public void setIsFullyCompliant(String isFullyCompliant) {
        this.isFullyCompliant = isFullyCompliant == null ? null : isFullyCompliant.trim();
    }

    /**
     * 取得 是否养老理财产品
     * @return 是否养老理财产品
     */
    public String getIsYllccp() {
        return isYllccp;
    }

    /**
     * 设置 是否养老理财产品
     * @param isYllccp 是否养老理财产品
     */
    public void setIsYllccp(String isYllccp) {
        this.isYllccp = isYllccp == null ? null : isYllccp.trim();
    }

    /**
     * 取得 投资者类型
     * @return 投资者类型
     */
    public String getInvestorType() {
        return investorType;
    }

    /**
     * 设置 投资者类型
     * @param investorType 投资者类型
     */
    public void setInvestorType(String investorType) {
        this.investorType = investorType == null ? null : investorType.trim();
    }

    /**
     * 取得 发行要素维护完整
     * @return 发行要素维护完整
     */
    public String getIsIntact() {
        return isIntact;
    }

    /**
     * 设置 发行要素维护完整
     * @param isIntact 发行要素维护完整
     */
    public void setIsIntact(String isIntact) {
        this.isIntact = isIntact == null ? null : isIntact.trim();
    }

    /**
     * 取得 是否代销，01/02
     * @return 是否代销，01/02
     */
    public String getIsagency() {
        return isagency;
    }

    /**
     * 设置 是否代销，01/02
     * @param isagency 是否代销，01/02
     */
    public void setIsagency(String isagency) {
        this.isagency = isagency == null ? null : isagency.trim();
    }

    /**
     * 取得 目标客户类型,01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     * @return 目标客户类型,01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     */
    public String getSaletarget() {
        return saletarget;
    }

    /**
     * 设置 目标客户类型,01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     * @param saletarget 目标客户类型,01 私人银行客户专属/02 机构客户专属/03 一般个人客户/04 金融同业专属/05 高资产净值客户
     */
    public void setSaletarget(String saletarget) {
        this.saletarget = saletarget == null ? null : saletarget.trim();
    }

    /**
     * 取得 存续状态  存续01，终止02，未起息03
     * @return 存续状态  存续01，终止02，未起息03
     */
    public String getDurationflag() {
        return durationflag;
    }

    /**
     * 设置 存续状态  存续01，终止02，未起息03
     * @param durationflag 存续状态  存续01，终止02，未起息03
     */
    public void setDurationflag(String durationflag) {
        this.durationflag = durationflag == null ? null : durationflag.trim();
    }

    /**
     * 取得 募集起始日期（从）
     * @return 募集起始日期（从）
     */
    public Date getRaisePstrdate() {
        return raisePstrdate;
    }

    /**
     * 设置 募集起始日期（从）
     * @param raisePstrdate 募集起始日期（从）
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaisePstrdate(Date raisePstrdate) {
        this.raisePstrdate = raisePstrdate;
    }

    /**
     * 取得 募集起始日期（到）
     * @return 募集起始日期（到）
     */
    public Date getRaisePenddate() {
        return raisePenddate;
    }

    /**
     * 设置 募集起始日期（到）
     * @param raisePenddate 募集起始日期（到）
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaisePenddate(Date raisePenddate) {
        this.raisePenddate = raisePenddate;
    }

    /**
     * 取得 报备状态
     * @return 报备状态
     */
    public String getDeclareStatus() {
        return declareStatus;
    }

    /**
     * 设置 报备状态
     * @param declareStatus 报备状态
     */
    public void setDeclareStatus(String declareStatus) {
        this.declareStatus = declareStatus == null ? null : declareStatus.trim();
    }

    /**
     * 取得 发行状态  确认发行/发行成功/发行失败/不发行
     * @return 发行状态  确认发行/发行成功/发行失败/不发行
     */
    public String getIssuingStatus() {
        return issuingStatus;
    }

    /**
     * 设置 发行状态  确认发行/发行成功/发行失败/不发行
     * @param issuingStatus 发行状态  确认发行/发行成功/发行失败/不发行
     */
    public void setIssuingStatus(String issuingStatus) {
        this.issuingStatus = issuingStatus == null ? null : issuingStatus.trim();
    }

    /**
     * 取得 模板名称
     * @return 模板名称
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * 设置 模板名称
     * @param templateName 模板名称
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    /**
     * 取得 产品设计人姓名
     * @return 产品设计人姓名
     */
    public String getDesignerName() {
        return designerName;
    }

    /**
     * 设置 产品设计人姓名
     * @param designerName 产品设计人姓名
     */
    public void setDesignerName(String designerName) {
        this.designerName = designerName == null ? null : designerName.trim();
    }

    /**
     * 取得 产品设计人身份证号
     * @return 产品设计人身份证号
     */
    public String getDesignerId() {
        return designerId;
    }

    /**
     * 设置 产品设计人身份证号
     * @param designerId 产品设计人身份证号
     */
    public void setDesignerId(String designerId) {
        this.designerId = designerId == null ? null : designerId.trim();
    }

    /**
     * 取得 产品审批人姓名
     * @return 产品审批人姓名
     */
    public String getApproverName() {
        return approverName;
    }

    /**
     * 设置 产品审批人姓名
     * @param approverName 产品审批人姓名
     */
    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    /**
     * 取得 产品审批人身份证号
     * @return 产品审批人身份证号
     */
    public String getApproverId() {
        return approverId;
    }

    /**
     * 设置 产品审批人身份证号
     * @param approverId 产品审批人身份证号
     */
    public void setApproverId(String approverId) {
        this.approverId = approverId == null ? null : approverId.trim();
    }

    /**
     * 取得 投资经理姓名
     * @return 投资经理姓名
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * 设置 投资经理姓名
     * @param managerName 投资经理姓名
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    /**
     * 取得 投资经理身份证号
     * @return 投资经理身份证号
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 设置 投资经理身份证号
     * @param managerId 投资经理身份证号
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    /**
     * 取得 会计核算方式  01 表内/02 表外
     * @return 会计核算方式  01 表内/02 表外
     */
    public String getBookMode() {
        return bookMode;
    }

    /**
     * 设置 会计核算方式  01 表内/02 表外
     * @param bookMode 会计核算方式  01 表内/02 表外
     */
    public void setBookMode(String bookMode) {
        this.bookMode = bookMode == null ? null : bookMode.trim();
    }

    /**
     * 取得 业务联络人姓名
     * @return 业务联络人姓名
     */
    public String getLiaisonName() {
        return liaisonName;
    }

    /**
     * 设置 业务联络人姓名
     * @param liaisonName 业务联络人姓名
     */
    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName == null ? null : liaisonName.trim();
    }

    /**
     * 取得 业务联络人座机
     * @return 业务联络人座机
     */
    public String getLiaisonTel() {
        return liaisonTel;
    }

    /**
     * 设置 业务联络人座机
     * @param liaisonTel 业务联络人座机
     */
    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel == null ? null : liaisonTel.trim();
    }

    /**
     * 取得 业务联络人手机
     * @return 业务联络人手机
     */
    public String getLiaisonMobile() {
        return liaisonMobile;
    }

    /**
     * 设置 业务联络人手机
     * @param liaisonMobile 业务联络人手机
     */
    public void setLiaisonMobile(String liaisonMobile) {
        this.liaisonMobile = liaisonMobile == null ? null : liaisonMobile.trim();
    }

    /**
     * 取得 业务联络人邮箱
     * @return 业务联络人邮箱
     */
    public String getLiaisonEmail() {
        return liaisonEmail;
    }

    /**
     * 设置 业务联络人邮箱
     * @param liaisonEmail 业务联络人邮箱
     */
    public void setLiaisonEmail(String liaisonEmail) {
        this.liaisonEmail = liaisonEmail == null ? null : liaisonEmail.trim();
    }

    /**
     * 取得 产品投资国家或地区参考《世界各国和地区名称代码》
     * @return 产品投资国家或地区参考《世界各国和地区名称代码》
     */
    public String getInvestCountry() {
        return investCountry;
    }

    /**
     * 设置 产品投资国家或地区参考《世界各国和地区名称代码》
     * @param investCountry 产品投资国家或地区参考《世界各国和地区名称代码》
     */
    public void setInvestCountry(String investCountry) {
        this.investCountry = investCountry == null ? null : investCountry.trim();
    }

    /**
     * 取得 理财业务服务模式01 综合理财服务/02 理财顾问服务/99 其他
     * @return 理财业务服务模式01 综合理财服务/02 理财顾问服务/99 其他
     */
    public String getFinanSerModel() {
        return finanSerModel;
    }

    /**
     * 设置 理财业务服务模式01 综合理财服务/02 理财顾问服务/99 其他
     * @param finanSerModel 理财业务服务模式01 综合理财服务/02 理财顾问服务/99 其他
     */
    public void setFinanSerModel(String finanSerModel) {
        this.finanSerModel = finanSerModel == null ? null : finanSerModel.trim();
    }

    /**
     * 取得 产品资产配置方式01 单一资产配置（一对一）/02 资产组合配置（一对多）/03 其他配置（多对多）
     * @return 产品资产配置方式01 单一资产配置（一对一）/02 资产组合配置（一对多）/03 其他配置（多对多）
     */
    public String getCmmode() {
        return cmmode;
    }

    /**
     * 设置 产品资产配置方式01 单一资产配置（一对一）/02 资产组合配置（一对多）/03 其他配置（多对多）
     * @param cmmode 产品资产配置方式01 单一资产配置（一对一）/02 资产组合配置（一对多）/03 其他配置（多对多）
     */
    public void setCmmode(String cmmode) {
        this.cmmode = cmmode == null ? null : cmmode.trim();
    }

    /**
     * 取得 产品管理模式01银行为实际管理人/02 其他机构为实际管理人/03 资产管理公司/04 特殊目的载体（SPV）
     * @return 产品管理模式01银行为实际管理人/02 其他机构为实际管理人/03 资产管理公司/04 特殊目的载体（SPV）
     */
    public String getPmmode() {
        return pmmode;
    }

    /**
     * 设置 产品管理模式01银行为实际管理人/02 其他机构为实际管理人/03 资产管理公司/04 特殊目的载体（SPV）
     * @param pmmode 产品管理模式01银行为实际管理人/02 其他机构为实际管理人/03 资产管理公司/04 特殊目的载体（SPV）
     */
    public void setPmmode(String pmmode) {
        this.pmmode = pmmode == null ? null : pmmode.trim();
    }

    /**
     * 取得 实际管理人名称
     * @return 实际管理人名称
     */
    public String getMname() {
        return mname;
    }

    /**
     * 设置 实际管理人名称
     * @param mname 实际管理人名称
     */
    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }

    /**
     * 取得 投资资产类型  01 结构性投资类/02 货币市场工具类/03 债券类/04 非标准化债权类/05 股票类/06 股权类/07 另类投资类/08 混合类/09代客境外投资类/10 银行存款类/11 理财直接融资工具类/12 新增可投资资产类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类
     * @return 投资资产类型  01 结构性投资类/02 货币市场工具类/03 债券类/04 非标准化债权类/05 股票类/06 股权类/07 另类投资类/08 混合类/09代客境外投资类/10 银行存款类/11 理财直接融资工具类/12 新增可投资资产类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类
     */
    public String getInvAssetType() {
        return invAssetType;
    }

    /**
     * 设置 投资资产类型  01 结构性投资类/02 货币市场工具类/03 债券类/04 非标准化债权类/05 股票类/06 股权类/07 另类投资类/08 混合类/09代客境外投资类/10 银行存款类/11 理财直接融资工具类/12 新增可投资资产类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类
     * @param invAssetType 投资资产类型  01 结构性投资类/02 货币市场工具类/03 债券类/04 非标准化债权类/05 股票类/06 股权类/07 另类投资类/08 混合类/09代客境外投资类/10 银行存款类/11 理财直接融资工具类/12 新增可投资资产类/13 公募基金类/ 14 私募基金类/ 15 产业基金类 /16 衍生产品类 /17 商品类
     */
    public void setInvAssetType(String invAssetType) {
        this.invAssetType = invAssetType == null ? null : invAssetType.trim();
    }

    /**
     * 取得 合作机构
     * @return 合作机构
     */
    public String getCooperationName() {
        return cooperationName;
    }

    /**
     * 设置 合作机构
     * @param cooperationName 合作机构
     */
    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName == null ? null : cooperationName.trim();
    }

    /**
     * 取得 结构性产品挂钩标的01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     * @return 结构性产品挂钩标的01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     */
    public String getConPrdTraget() {
        return conPrdTraget;
    }

    /**
     * 设置 结构性产品挂钩标的01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     * @param conPrdTraget 结构性产品挂钩标的01 利率/02 汇率/03 股票/04 农产品/05 大宗商品/06 贵金属/07 债券/08 指数 /09 基金/10 另类资产/11 期货/12 期权/13 远期/14 互换/15 复合金融衍生品/99 其他
     */
    public void setConPrdTraget(String conPrdTraget) {
        this.conPrdTraget = conPrdTraget == null ? null : conPrdTraget.trim();
    }

    /**
     * 取得 是否有预期收益率01 是/02 否
     * @return 是否有预期收益率01 是/02 否
     */
    public String getIsYield() {
        return isYield;
    }

    /**
     * 设置 是否有预期收益率01 是/02 否
     * @param isYield 是否有预期收益率01 是/02 否
     */
    public void setIsYield(String isYield) {
        this.isYield = isYield == null ? null : isYield.trim();
    }

    /**
     * 取得 是否有收益率测算依据  01 是/02 否
     * @return 是否有收益率测算依据  01 是/02 否
     */
    public String getIsYieldMeasBasis() {
        return isYieldMeasBasis;
    }

    /**
     * 设置 是否有收益率测算依据  01 是/02 否
     * @param isYieldMeasBasis 是否有收益率测算依据  01 是/02 否
     */
    public void setIsYieldMeasBasis(String isYieldMeasBasis) {
        this.isYieldMeasBasis = isYieldMeasBasis == null ? null : isYieldMeasBasis.trim();
    }

    /**
     * 取得 投资者风险偏好01 保守型/02 稳健型/03 平衡型/04 成长型/05 进取型
     * @return 投资者风险偏好01 保守型/02 稳健型/03 平衡型/04 成长型/05 进取型
     */
    public String getRiskType() {
        return riskType;
    }

    /**
     * 设置 投资者风险偏好01 保守型/02 稳健型/03 平衡型/04 成长型/05 进取型
     * @param riskType 投资者风险偏好01 保守型/02 稳健型/03 平衡型/04 成长型/05 进取型
     */
    public void setRiskType(String riskType) {
        this.riskType = riskType == null ? null : riskType.trim();
    }

    /**
     * 取得 起点销售金额
     * @return 起点销售金额
     */
    public BigDecimal getSaleAmtStr() {
        return saleAmtStr;
    }

    /**
     * 设置 起点销售金额
     * @param saleAmtStr 起点销售金额
     */
    public void setSaleAmtStr(BigDecimal saleAmtStr) {
        this.saleAmtStr = saleAmtStr;
    }

    /**
     * 取得 计划募集金额（元）
     * @return 计划募集金额（元）
     */
    public BigDecimal getRaiseAmtPlan() {
        return raiseAmtPlan;
    }

    /**
     * 设置 计划募集金额（元）
     * @param raiseAmtPlan 计划募集金额（元）
     */
    public void setRaiseAmtPlan(BigDecimal raiseAmtPlan) {
        this.raiseAmtPlan = raiseAmtPlan;
    }

    /**
     * 取得 销售手续费率%
     * @return 销售手续费率%
     */
    public BigDecimal getSaleFeeRate() {
        return saleFeeRate;
    }

    /**
     * 设置 销售手续费率%
     * @param saleFeeRate 销售手续费率%
     */
    public void setSaleFeeRate(BigDecimal saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    /**
     * 取得 投资本金到账日
     * @return 投资本金到账日
     */
    public String getInvestPrinDate() {
        return investPrinDate;
    }

    /**
     * 设置 投资本金到账日
     * @param investPrinDate 投资本金到账日
     */
    public void setInvestPrinDate(String investPrinDate) {
        this.investPrinDate = investPrinDate == null ? null : investPrinDate.trim();
    }

    /**
     * 取得 投资收益到账日
     * @return 投资收益到账日
     */
    public String getInvestProfDate() {
        return investProfDate;
    }

    /**
     * 设置 投资收益到账日
     * @param investProfDate 投资收益到账日
     */
    public void setInvestProfDate(String investProfDate) {
        this.investProfDate = investProfDate == null ? null : investProfDate.trim();
    }

    /**
     * 取得 托管费率
     * @return 托管费率
     */
    public BigDecimal getTruFeeRate() {
        return truFeeRate;
    }

    /**
     * 设置 托管费率
     * @param truFeeRate 托管费率
     */
    public void setTruFeeRate(BigDecimal truFeeRate) {
        this.truFeeRate = truFeeRate;
    }

    /**
     * 取得 产品起始日期
     * @return 产品起始日期
     */
    public Date getPrdVdate() {
        return prdVdate;
    }

    /**
     * 设置 产品起始日期
     * @param prdVdate 产品起始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPrdVdate(Date prdVdate) {
        this.prdVdate = prdVdate;
    }

    /**
     * 取得 产品终止日期
     * @return 产品终止日期
     */
    public Date getPrdMdate() {
        return prdMdate;
    }

    /**
     * 设置 产品终止日期
     * @param prdMdate 产品终止日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPrdMdate(Date prdMdate) {
        this.prdMdate = prdMdate;
    }

    /**
     * 取得 平均预计客户年收益率
     * @return 平均预计客户年收益率
     */
    public BigDecimal getExpAvgYield() {
        return expAvgYield;
    }

    /**
     * 设置 平均预计客户年收益率
     * @param expAvgYield 平均预计客户年收益率
     */
    public void setExpAvgYield(BigDecimal expAvgYield) {
        this.expAvgYield = expAvgYield;
    }

    /**
     * 取得 层级类别  11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五
     * @return 层级类别  11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五
     */
    public String getGradeType() {
        return gradeType;
    }

    /**
     * 设置 层级类别  11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五
     * @param gradeType 层级类别  11 优先层级一/12 优先层级二/13 优先层级三/14 优先层级四/15 优先层级五/21 进取层级一/22 进取层级二/23 进取层级三/24 进取层级四/25 进取层级五
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType == null ? null : gradeType.trim();
    }

    /**
     * 取得 产品增信形式
     * @return 产品增信形式
     */
    public String getCreditMode() {
        return creditMode;
    }

    /**
     * 设置 产品增信形式
     * @param creditMode 产品增信形式
     */
    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode == null ? null : creditMode.trim();
    }

    /**
     * 取得 开放模式申 01	定期开放02	高频开放03	周期开放
     * @return 开放模式申 01	定期开放02	高频开放03	周期开放
     */
    public String getOpentypep() {
        return opentypep;
    }

    /**
     * 设置 开放模式申 01	定期开放02	高频开放03	周期开放
     * @param opentypep 开放模式申 01	定期开放02	高频开放03	周期开放
     */
    public void setOpentypep(String opentypep) {
        this.opentypep = opentypep == null ? null : opentypep.trim();
    }

    /**
     * 取得 开放天数申
     * @return 开放天数申
     */
    public String getOpendayp() {
        return opendayp;
    }

    /**
     * 设置 开放天数申
     * @param opendayp 开放天数申
     */
    public void setOpendayp(String opendayp) {
        this.opendayp = opendayp == null ? null : opendayp.trim();
    }

    /**
     * 取得 开放周期申
     * @return 开放周期申
     */
    public String getOpencyclep() {
        return opencyclep;
    }

    /**
     * 设置 开放周期申
     * @param opencyclep 开放周期申
     */
    public void setOpencyclep(String opencyclep) {
        this.opencyclep = opencyclep == null ? null : opencyclep.trim();
    }

    /**
     * 取得 开放周期单位
     * @return 开放周期单位
     */
    public String getOpencycleunitp() {
        return opencycleunitp;
    }

    /**
     * 设置 开放周期单位
     * @param opencycleunitp 开放周期单位
     */
    public void setOpencycleunitp(String opencycleunitp) {
        this.opencycleunitp = opencycleunitp == null ? null : opencycleunitp.trim();
    }

    /**
     * 取得 周申
     * @return 周申
     */
    public String getWeekp() {
        return weekp;
    }

    /**
     * 设置 周申
     * @param weekp 周申
     */
    public void setWeekp(String weekp) {
        this.weekp = weekp == null ? null : weekp.trim();
    }

    /**
     * 取得 几号申
     * @return 几号申
     */
    public String getDayp() {
        return dayp;
    }

    /**
     * 设置 几号申
     * @param dayp 几号申
     */
    public void setDayp(String dayp) {
        this.dayp = dayp == null ? null : dayp.trim();
    }

    /**
     * 取得 第几工作日申
     * @return 第几工作日申
     */
    public String getWorkdayp() {
        return workdayp;
    }

    /**
     * 设置 第几工作日申
     * @param workdayp 第几工作日申
     */
    public void setWorkdayp(String workdayp) {
        this.workdayp = workdayp == null ? null : workdayp.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * 取得 经办人
     * @return 经办人
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置 经办人
     * @param agent 经办人
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    /**
     * 取得 最短持有期后是否自由赎回
     * @return 最短持有期后是否自由赎回
     */
    public String getIsFreeRedem() {
        return isFreeRedem;
    }

    /**
     * 设置 最短持有期后是否自由赎回
     * @param isFreeRedem 最短持有期后是否自由赎回
     */
    public void setIsFreeRedem(String isFreeRedem) {
        this.isFreeRedem = isFreeRedem == null ? null : isFreeRedem.trim();
    }

    /**
     * 取得 业绩比较基准%
     * @return 业绩比较基准%
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 业绩比较基准%
     * @param yield 业绩比较基准%
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 投资管理费率%
     * @return 投资管理费率%
     */
    public BigDecimal getInvestFeeRate() {
        return investFeeRate;
    }

    /**
     * 设置 投资管理费率%
     * @param investFeeRate 投资管理费率%
     */
    public void setInvestFeeRate(BigDecimal investFeeRate) {
        this.investFeeRate = investFeeRate;
    }

    /**
     * 取得 预计客户最高年收益率%
     * @return 预计客户最高年收益率%
     */
    public BigDecimal getHighYield() {
        return highYield;
    }

    /**
     * 设置 预计客户最高年收益率%
     * @param highYield 预计客户最高年收益率%
     */
    public void setHighYield(BigDecimal highYield) {
        this.highYield = highYield;
    }

    /**
     * 取得 预计客户最低年收益率%
     * @return 预计客户最低年收益率%
     */
    public BigDecimal getLowYield() {
        return lowYield;
    }

    /**
     * 设置 预计客户最低年收益率%
     * @param lowYield 预计客户最低年收益率%
     */
    public void setLowYield(BigDecimal lowYield) {
        this.lowYield = lowYield;
    }

    /**
     * 取得 是否合规新产品
     * @return 是否合规新产品
     */
    public String getIsFhxgcp() {
        return isFhxgcp;
    }

    /**
     * 设置 是否合规新产品
     * @param isFhxgcp 是否合规新产品
     */
    public void setIsFhxgcp(String isFhxgcp) {
        this.isFhxgcp = isFhxgcp == null ? null : isFhxgcp.trim();
    }

    /**
     * 取得 是否收益权转让产品
     * @return 是否收益权转让产品
     */
    public String getIsSyqzccp() {
        return isSyqzccp;
    }

    /**
     * 设置 是否收益权转让产品
     * @param isSyqzccp 是否收益权转让产品
     */
    public void setIsSyqzccp(String isSyqzccp) {
        this.isSyqzccp = isSyqzccp == null ? null : isSyqzccp.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getSvrDataSources() {
        return svrDataSources;
    }

    /**
     * 设置 数据来源
     * @param svrDataSources 数据来源
     */
    public void setSvrDataSources(String svrDataSources) {
        this.svrDataSources = svrDataSources == null ? null : svrDataSources.trim();
    }

    /**
     * 取得 本位币
     * @return 本位币
     */
    public String getFunctionalCurrency() {
        return functionalCurrency;
    }

    /**
     * 设置 本位币
     * @param functionalCurrency 本位币
     */
    public void setFunctionalCurrency(String functionalCurrency) {
        this.functionalCurrency = functionalCurrency == null ? null : functionalCurrency.trim();
    }
}