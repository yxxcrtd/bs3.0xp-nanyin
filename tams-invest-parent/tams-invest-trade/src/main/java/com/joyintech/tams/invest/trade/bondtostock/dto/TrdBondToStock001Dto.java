package com.joyintech.tams.invest.trade.bondtostock.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risk.dto.RiskCreditListCommonDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.bondtostock.validator.TrdBondToStock001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("TrdBondToStock002")
@ValidLogic(targetClass = TrdBondToStock001Validator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdBondToStock001Dto extends TrdFinProductDealTotal implements ProcessEntity {
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 画面类型
     */
    private String pageModel;
    /**
     * 债券一级分类
     */
    private String windtypeone;
    /**
     * 有效状态
     */
    private String validStatus;
    /**
     * 转股代码
     */
    private String conShareId;
    /**
     * 业务编号
     */
    private String busiCode;
    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 组合代码
     */
    @NotEmpty(fieldName = "TrdBondToStock001.portfolioId" ,groups = {ValidateGroupEdit.class})
    private String portfolioId;
    /**
     *  实际交易组合
     */
    private String actPortfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 转债名称
     */
    private String finprodName;
    /**
     * 正股代码
     */
    @NotEmpty(fieldName = "TrdBondToStock001.finprodMarketId" ,groups = {ValidateGroupEdit.class})
    private String finprodMarketId;
    /**
     * 正股金融产品代码
     */
    private String shareFinprodId;
    /**
     * 正股名称
     */
    private String finprodMarketName;
    /**
     * 持有券面总额
     */
    private BigDecimal holdetrPriceTotal;
    /**
     * 转股价
     */
    private BigDecimal conPrice;
    /**
     * 转股数
     */
    private BigDecimal conNum;
    /**
     * 零股数量
     */
    private BigDecimal spoShareNum;
    /**
     * 转股溢价率
     */
    private BigDecimal preb;
    /**
     * 正股价
     */
    private BigDecimal firstUnitCprice3;
    /**
     * 可转换券面总额
     */
    private BigDecimal conetrPriceTotal;

    /**
     * 转股券面总额
     */
    private BigDecimal stocketrPriceTotal;

    /**
     * 转股起始日
     */
    private Date convdate;

    /**
     * 预计转股价值
     */
    private BigDecimal expConValue;

    /**
     * 预计转换股数
     */
    private BigDecimal expconnum;
    /**
     * 纯债溢价率
     */
    private BigDecimal prec;
    /**
     * 基础汇总信息
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 人员信息
     */
    @Valid
    private TrdInvestPersonInfo002Dto trdInvestPersonInfo;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;
    
    private RiskCreditListCommonDto riskCreditListCommonDto;
    
    /**
     * 撤销原因
     */
    private String revokeReason;
    @NotEmpty(fieldName = "TrdBondToStock001.counterId" ,groups = {ValidateGroupEdit.class})
    public String getCounterIdInfo(){
        return StringUtil.isNotEmpty(getCounterId())?getCounterId():getTrdInvestPersonInfo()==null?null:getTrdInvestPersonInfo().getCounterId();
    }
    @Override
    @NotEmpty(fieldName = "TrdBondToStock001.tradeAmt" ,groups = {ValidateGroupEdit.class})
    public BigDecimal getTradeAmt() {
        return super.getTradeAmt();
    }

    @Override
    @NotEmpty(fieldName = "TrdBondToStock001.invAim" ,groups = {ValidateGroupEdit.class})
    public String getInvAim() {
        return super.getInvAim();
    }

    @Override
    @NotEmpty(fieldName = "TrdBondToStock001.shareAmt" ,groups = {ValidateGroupEdit.class})
    public BigDecimal getShareAmt(){
        return super.getShareAmt();
    }
    @NotEmpty(fieldName = "TrdBondToStock001.finprodId" ,groups = {ValidateGroupEdit.class})
    @Override
    public String getFinprodId() {
        return super.getFinprodId();
    }

    /**
     * 获取 有效状态
     *
     * @return validStatus 有效状态
     */
    public String getValidStatus() {
        return this.validStatus;
    }

    /**
     * 设置 有效状态
     *
     * @param validStatus 有效状态
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * 获取 业务编号
     *
     * @return busiCode 业务编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getBusiCode() {
        return StringUtil.isEmpty(this.busiCode)? IDGenerator.generate():busiCode;
    }

    /**
     * 设置 业务编号
     *
     * @param busiCode 业务编号
     */
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    /**
     * 获取 委托代码
     *
     * @return entrustId 委托代码
     */
    public String getEntrustId() {
        return this.entrustId;
    }

    /**
     * 设置 委托代码
     *
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 获取 组合代码
     *
     * @return portfolioId 组合代码
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 组合代码
     *
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 组合名称
     *
     * @return portfolioName 组合名称
     */
    public String getPortfolioName() {
        return this.portfolioName;
    }

    /**
     * 设置 组合名称
     *
     * @param portfolioName 组合名称
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 转债名称
     *
     * @return finprodName 转债名称
     */
    public String getFinprodName() {
        return this.finprodName;
    }

    /**
     * 设置 转债名称
     *
     * @param finprodName 转债名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 正股代码
     *
     * @return finprodMarketId 正股代码
     */
    public String getFinprodMarketId() {
        return this.finprodMarketId;
    }

    /**
     * 设置 正股代码
     *
     * @param finprodMarketId 正股代码
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 正股名称
     *
     * @return finprodMarketName 正股名称
     */
    public String getFinprodMarketName() {
        return this.finprodMarketName;
    }

    /**
     * 设置 正股名称
     *
     * @param finprodMarketName 正股名称
     */
    public void setFinprodMarketName(String finprodMarketName) {
        this.finprodMarketName = finprodMarketName;
    }

    /**
     * 获取 转股价
     *
     * @return conPrice 转股价
     */
    public BigDecimal getConPrice() {
        return this.conPrice;
    }

    /**
     * 设置 转股价
     *
     * @param conPrice 转股价
     */
    public void setConPrice(BigDecimal conPrice) {
        this.conPrice = conPrice;
    }

    /**
     * 获取 转股数
     *
     * @return conNum 转股数
     */
    public BigDecimal getConNum() {
        return this.conNum;
    }

    /**
     * 设置 转股数
     *
     * @param conNum 转股数
     */
    public void setConNum(BigDecimal conNum) {
        this.conNum = conNum;
    }


    /**
     * 获取 转股溢价率
     *
     * @return preb 转股溢价率
     */
    public BigDecimal getPreb() {
        return this.preb;
    }

    /**
     * 设置 转股溢价率
     *
     * @param preb 转股溢价率
     */
    public void setPreb(BigDecimal preb) {
        this.preb = preb;
    }

    /**
     * 获取 零股数量
     *
     * @return spoShareNum 零股数量
     */
    public BigDecimal getSpoShareNum() {
        return this.spoShareNum;
    }

    /**
     * 设置 零股数量
     *
     * @param spoShareNum 零股数量
     */
    public void setSpoShareNum(BigDecimal spoShareNum) {
        this.spoShareNum = spoShareNum;
    }

    /**
     * 获取 操作类型
     *
     * @return operateType 操作类型
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * 设置 操作类型
     *
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 画面类型
     *
     * @return pageModel 画面类型
     */
    public String getPageModel() {
        return this.pageModel;
    }

    /**
     * 设置 画面类型
     *
     * @param pageModel 画面类型
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 正股价
     *
     * @return firstUnitCprice3 正股价
     */
    @NotEmpty(fieldName = "TrdBondToStock001.firstUnitCprice3" ,groups = {ValidateGroupEdit.class})
    public BigDecimal getFirstUnitCprice3() {
        return this.firstUnitCprice3;
    }

    /**
     * 设置 正股价
     *
     * @param firstUnitCprice3 正股价
     */
    public void setFirstUnitCprice3(BigDecimal firstUnitCprice3) {
        this.firstUnitCprice3 = firstUnitCprice3;
    }

    /**
     * 获取 纯债溢价率
     *
     * @return prec 纯债溢价率
     */
    public BigDecimal getPrec() {
        return this.prec;
    }

    /**
     * 设置 纯债溢价率
     *
     * @param prec 纯债溢价率
     */
    public void setPrec(BigDecimal prec) {
        this.prec = prec;
    }

    /**
     * 获取 预计转股价值
     *
     * @return expConValue 预计转股价值
     */
    public BigDecimal getExpConValue() {
        return this.expConValue;
    }

    /**
     * 设置 预计转股价值
     *
     * @param expConValue 预计转股价值
     */
    public void setExpConValue(BigDecimal expConValue) {
        this.expConValue = expConValue;
    }

    /**
     * 获取 基础汇总信息
     *
     * @return baseTotalTradeInfoDto 基础汇总信息
     */
    public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
        return this.baseTotalTradeInfoDto;
    }

    /**
     * 设置 基础汇总信息
     *
     * @param baseTotalTradeInfoDto 基础汇总信息
     */
    public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
        this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
    }

    /**
     * 获取 划款指令
     *
     * @return bussTrdTransferOrder 划款指令
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return this.bussTrdTransferOrder;
    }

    /**
     * 设置 划款指令
     *
     * @param bussTrdTransferOrder 划款指令
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 风险试算实体类
     *
     * @return riskTrialRetDto 风险试算实体类
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return this.riskTrialRetDto;
    }

    /**
     * 设置 风险试算实体类
     *
     * @param riskTrialRetDto 风险试算实体类
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 人员信息
     *
     * @return trdInvestPersonInfo 人员信息
     */
    public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
        return this.trdInvestPersonInfo;
    }

    /**
     * 设置 人员信息
     *
     * @param trdInvestPersonInfo 人员信息
     */
    public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
        this.trdInvestPersonInfo = trdInvestPersonInfo;
    }

    /**
     * 获取 附件上传
     *
     * @return uploadFileList 附件上传
     */
    public List<UploadFile> getUploadFileList() {
        return this.uploadFileList;
    }

    /**
     * 设置 附件上传
     *
     * @param uploadFileList 附件上传
     */
    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

    /**
     * 获取 实际交易组合
     *
     * @return actPortfolioId 实际交易组合
     */
    public String getActPortfolioId() {
        return this.actPortfolioId;
    }

    /**
     * 设置 实际交易组合
     *
     * @param actPortfolioId 实际交易组合
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * 获取 撤销原因
     *
     * @return revokeReason 撤销原因
     */
    public String getRevokeReason() {
        return this.revokeReason;
    }

    /**
     * 设置 撤销原因
     *
     * @param revokeReason 撤销原因
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 转股代码
     *
     * @return conShareId 转股代码
     */
    public String getConShareId() {
        return this.conShareId;
    }

    /**
     * 设置 转股代码
     *
     * @param conShareId 转股代码
     */
    public void setConShareId(String conShareId) {
        this.conShareId = conShareId;
    }

    /**
     * 获取 持有券面总额
     *
     * @return holdetrPriceTotal 持有券面总额
     */
    public BigDecimal getHoldetrPriceTotal() {
        return this.holdetrPriceTotal;
    }

    /**
     * 设置 持有券面总额
     *
     * @param holdetrPriceTotal 持有券面总额
     */
    public void setHoldetrPriceTotal(BigDecimal holdetrPriceTotal) {
        this.holdetrPriceTotal = holdetrPriceTotal;
    }

    /**
     * 获取 可转换券面总额
     *
     * @return conetrPriceTotal 可转换券面总额
     */
    public BigDecimal getConetrPriceTotal() {
        return this.conetrPriceTotal;
    }

    /**
     * 设置 可转换券面总额
     *
     * @param conetrPriceTotal 可转换券面总额
     */
    public void setConetrPriceTotal(BigDecimal conetrPriceTotal) {
        this.conetrPriceTotal = conetrPriceTotal;
    }

    /**
     * 获取 转股券面总额
     *
     * @return stocketrPriceTotal 转股券面总额
     */
    public BigDecimal getStocketrPriceTotal() {
        return this.stocketrPriceTotal;
    }

    /**
     * 设置 转股券面总额
     *
     * @param stocketrPriceTotal 转股券面总额
     */
    public void setStocketrPriceTotal(BigDecimal stocketrPriceTotal) {
        this.stocketrPriceTotal = stocketrPriceTotal;
    }

    /**
     * 获取 转股起始日
     *
     * @return convdate 转股起始日
     */
    public Date getConvdate() {
        return this.convdate;
    }

    /**
     * 设置 转股起始日
     *
     * @param convdate 转股起始日
     */
    public void setConvdate(Date convdate) {
        this.convdate = convdate;
    }

    /**
     * 获取 预计转换股数
     *
     * @return expconnum 预计转换股数
     */
    public BigDecimal getExpconnum() {
        return this.expconnum;
    }

    /**
     * 设置 预计转换股数
     *
     * @param expconnum 预计转换股数
     */
    public void setExpconnum(BigDecimal expconnum) {
        this.expconnum = expconnum;
    }

    /**
     * 获取 正股金融产品代码
     *
     * @return shareFinprodId 正股金融产品代码
     */
    public String getShareFinprodId() {
        return this.shareFinprodId;
    }

    /**
     * 设置 正股金融产品代码
     *
     * @param shareFinprodId 正股金融产品代码
     */
    public void setShareFinprodId(String shareFinprodId) {
        this.shareFinprodId = shareFinprodId;
    }

    public String getValidStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态,getValidStatus());
    }
    public String getInvAimStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.投资目的,getInvAim());
    }
    public String getTradeMarketStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所,getTradeMarket());
    }
    public String getCcyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种,getCcy());
    }

    /**
     * 获取 债券一级分类
     *
     * @return windtypeone 债券一级分类
     */
    public String getWindtypeone() {
        return this.windtypeone;
    }

    /**
     * 设置 债券一级分类
     *
     * @param windtypeone 债券一级分类
     */
    public void setWindtypeone(String windtypeone) {
        this.windtypeone = windtypeone;
    }
    public RiskCreditListCommonDto getRiskCreditListCommonDto() {
        return riskCreditListCommonDto;
    }
    public void setRiskCreditListCommonDto(RiskCreditListCommonDto riskCreditListCommonDto) {
        this.riskCreditListCommonDto = riskCreditListCommonDto;
    }
}
