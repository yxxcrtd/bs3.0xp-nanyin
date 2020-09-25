package com.joyintech.tams.invest.common.dto.period.trdoptionduration;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
public class OptionDurationDetailDto extends TrdFinProductDealTotal implements ProcessEntity {
    private static final long serialVersionUID = -5790927186395487118L;
    /**
     * 模式（辅助标记功能）
     */
    private String mode;

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    private String dataStatus;
    /**
     * 所有相关表，贯穿整个流程
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;

    public String getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * revokeReason
     */
    private String revokeReason;

    @NotEmpty(fieldName = "划款指令", groups = { ValidateGroupEdit.class })
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getActPortfolioId() {
        return actPortfolioId;
    }

    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * 实体组合代码
     */
    private String actPortfolioId;
    public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
        return baseTotalTradeInfoDto;
    }

    public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
        this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
    }
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 行权类型
     */
    private String xqType;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;

    /**
     * 已选择的数据
     */
    public List<OptionDurationDetailDto> SelectedItem;

    /**
     * 要素id
     */
    private String elementId;


    /**
     * 业务编号
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    private String busiCode;
    /**
     * 交易编号
     */
    private String totleTradeId;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 合同名称
     */
    private String contractName;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     */
    private String targetId;
    /**
     * 合约类型
     */
    private String assetType11;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 交易日期
     */
    private Date cDate;
    /**
     * 到期日
     */
    private Date mDate;
    /**
     * 数量
     */
    private BigDecimal amount;
    /**
     * 行权基准日
     */
    private Date execBaseDate;
    /**
     * 执行价格
     */
    private BigDecimal exePrice;
    /**
     * 敲出价格
     */
    private BigDecimal knockPrice;
    /**
     * 低障碍价
     */
    private BigDecimal lowHindPrice;
    /**
     * 低行权价
     */
    private BigDecimal lowExercPrice;
    /**
     * 高障碍价
     */
    private BigDecimal highHindPrice;
    /**
     * 高行权价
     */
    private BigDecimal highExercPrice;
    /**
     * 行权方式
     */
    private String exerciseType;
    /**
     * 期权费率
     */
    private BigDecimal optFeeRate;
    /**
     * 期权费支付类型
     */
    private String optFeePayType;
    /**
     * 期权费
     */
    private BigDecimal fpriceAmt;
    /**
     * 保证金比例
     */
    private BigDecimal bzjbl;
    /**
     * 保证金
     */
    private BigDecimal bzj;
    /**
     * 交易对手
     */
    private String counterId;
    /**
     * 本方账户
     */
    private String accountId;
    /**
     * 是否放弃行权
     */
    private String isfqxq;
    /**
     * 收付方向
     */
    private String sffx;
    /**
     * 行权日
     */
    private Date xqDate;

    /**
     * 放弃行权日
     */
    private Date fqxqDate;
    /**
     * 支付日
     */
    private Date zfDate;

    /**
     * 到期金额
     */
    private BigDecimal tradeAmt;

    /**
     * 还款信息
     */
    private String hkxx;

    /**
     * 备注
     */
    private String remark;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;/**
     * 获取 uploadFileList
     * @return uploadFileList.
     */
    public List<UploadFile> getUploadFileList() {
        return uploadFileList;
    }
    /**
     * 设置 uploadFileList
     * @param uploadFileList uploadFileList
     */
    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }


    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    public String getXqType() {
        return xqType;
    }

    public void setXqType(String xqType) {
        this.xqType = xqType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public List<OptionDurationDetailDto> getSelectedItem() {
        return SelectedItem;
    }

    public void setSelectedItem(List<OptionDurationDetailDto> selectedItem) {
        SelectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getAssetType11() {
        return assetType11;
    }

    public void setAssetType11(String assetType11) {
        this.assetType11 = assetType11;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getExecBaseDate() {
        return execBaseDate;
    }

    public void setExecBaseDate(Date execBaseDate) {
        this.execBaseDate = execBaseDate;
    }

    public BigDecimal getExePrice() {
        return exePrice;
    }

    public void setExePrice(BigDecimal exePrice) {
        this.exePrice = exePrice;
    }

    public BigDecimal getKnockPrice() {
        return knockPrice;
    }

    public void setKnockPrice(BigDecimal knockPrice) {
        this.knockPrice = knockPrice;
    }

    public BigDecimal getLowHindPrice() {
        return lowHindPrice;
    }

    public void setLowHindPrice(BigDecimal lowHindPrice) {
        this.lowHindPrice = lowHindPrice;
    }

    public BigDecimal getLowExercPrice() {
        return lowExercPrice;
    }

    public void setLowExercPrice(BigDecimal lowExercPrice) {
        this.lowExercPrice = lowExercPrice;
    }

    public BigDecimal getHighHindPrice() {
        return highHindPrice;
    }

    public void setHighHindPrice(BigDecimal highHindPrice) {
        this.highHindPrice = highHindPrice;
    }

    public BigDecimal getHighExercPrice() {
        return highExercPrice;
    }

    public void setHighExercPrice(BigDecimal highExercPrice) {
        this.highExercPrice = highExercPrice;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }


    public BigDecimal getOptFeeRate() {
        return optFeeRate;
    }

    public void setOptFeeRate(BigDecimal optFeeRate) {
        this.optFeeRate = optFeeRate;
    }

    public String getOptFeePayType() {
        return optFeePayType;
    }

    public void setOptFeePayType(String optFeePayType) {
        this.optFeePayType = optFeePayType;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public BigDecimal getBzjbl() {
        return bzjbl;
    }

    public void setBzjbl(BigDecimal bzjbl) {
        this.bzjbl = bzjbl;
    }

    public BigDecimal getBzj() {
        return bzj;
    }

    public void setBzj(BigDecimal bzj) {
        this.bzj = bzj;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getIsfqxq() {
        return isfqxq;
    }

    public void setIsfqxq(String isfqxq) {
        this.isfqxq = isfqxq;
    }

    @NotEmpty(fieldName = "收付方向", groups = {ValidateGroupEdit.class})
    public String getSffx() {
        return sffx;
    }

    public void setSffx(String sffx) {
        this.sffx = sffx;
    }

    public Date getXqDate() {
        return xqDate;
    }

    public void setXqDate(Date xqDate) {
        this.xqDate = xqDate;
    }

    @NotEmpty(fieldName = "支付日", groups = {ValidateGroupEdit.class})
    public Date getZfDate() {
        return zfDate;
    }

    public void setZfDate(Date zfDate) {
        this.zfDate = zfDate;
    }

    @Override
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    @Override
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getHkxx() {
        return hkxx;
    }

    public void setHkxx(String hkxx) {
        this.hkxx = hkxx;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getFqxqDate() {
        return fqxqDate;
    }

    public void setFqxqDate(Date fqxqDate) {
        this.fqxqDate = fqxqDate;
    }
}
