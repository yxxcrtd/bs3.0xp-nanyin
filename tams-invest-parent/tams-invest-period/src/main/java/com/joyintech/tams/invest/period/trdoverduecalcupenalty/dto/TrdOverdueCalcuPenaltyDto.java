package com.joyintech.tams.invest.period.trdoverduecalcupenalty.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.period.trdoverduecalcupenalty.validator.TrdOverdueCalcuPenaltyValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName TrdOverdueCalcuPenaltyDto
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2019/12/10 0010
 * @Version V1.0
 **/
@PageId("TrdOverdueCalcuPenalty002")
@ValidLogic(targetClass = TrdOverdueCalcuPenaltyValidator.class, groups = {
        ValidateGroupEdit.class, ValidateGroupDelete.class})
public class TrdOverdueCalcuPenaltyDto extends BaseEntity implements ProcessEntity {
     private String finprodId;
    //本金的分支序号
     private String bjBranch;
     private String oldBjBranch;
    //利息的分支序号
     private String liBranch;
    private String oldLiBranch;
    private String finprodType;
     private String finprodTypeName;
     private String vdate;
     private String mdate;
     private String oldMdate;
    private String finprodName;

    private String withoutBranch;//还本不带息分支序号
    private String oldwithoutBranch;
    //交易类金融产品List
    private List<FinTradeProduct> finTradeProducts;
    //交易类金融产品附属list
    private List<FinTradeProductAdd> finTradeProductAdds;
    //现金流信息list
    private List<FinCashInfo> finCashInfos;
    //现金流计划规则list
    private List<FinCashCalcRule> finCashCalcRules;
    //现金流计划list
    private List<FinCashPlan> finCashPlans;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 控件Id
     */
    private String elementId;

    /**
     * 模式（辅助标记功能）
     */
    private String mode;
    /**
     * 有效状态
     */
    private String statusValid;


    private String totleTradeId;
    @NotEmpty(fieldName = "是否计提罚息",groups= {ValidateGroupEdit.class})
    private String isAccrPenaltyInt;
    //计提罚息本金
    private String penaltyBaseType;

    private String oldPenaltyBaseType;
    //罚息利率
    private BigDecimal yield;
    @NotEmpty(fieldName = "截止后是否冲回",groups= {ValidateGroupEdit.class})
    private String isRushBack;

    private String oldIsRushBack;
    //罚息开始日
    private String fxvdate;
    @NotEmpty(fieldName = "罚息截止日",groups= {ValidateGroupEdit.class})
    private String fxmdate;

    private String oldFxmdate;
    private String cashIds;

    private String oldCashIds;
    private String doType;
    private String finprodTypeGz;
    private String finprodTypeTg;

    private String basis;

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getOldCashIds() {
        return oldCashIds;
    }

    public void setOldCashIds(String oldCashIds) {
        this.oldCashIds = oldCashIds;
    }

    public String getOldwithoutBranch() {
        return oldwithoutBranch;
    }

    public void setOldwithoutBranch(String oldwithoutBranch) {
        this.oldwithoutBranch = oldwithoutBranch;
    }

    public String getFinprodTypeGz() {
        return finprodTypeGz;
    }

    public void setFinprodTypeGz(String finprodTypeGz) {
        this.finprodTypeGz = finprodTypeGz;
    }

    public String getFinprodTypeTg() {
        return finprodTypeTg;
    }

    public void setFinprodTypeTg(String finprodTypeTg) {
        this.finprodTypeTg = finprodTypeTg;
    }

    public String getDoType() {
        return doType;
    }

    public void setDoType(String doType) {
        this.doType = doType;
    }

    public String getCashIds() {
        return cashIds;
    }

    public void setCashIds(String cashIds) {
        this.cashIds = cashIds;
    }

    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    public String getStatusValid() {
        return statusValid;
    }

    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    public String getBjBranch() {
        return bjBranch;
    }

    public void setBjBranch(String bjBranch) {
        this.bjBranch = bjBranch;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public List<FinTradeProductAdd> getFinTradeProductAdds() {
        return finTradeProductAdds;
    }

    public void setFinTradeProductAdds(List<FinTradeProductAdd> finTradeProductAdds) {
        this.finTradeProductAdds = finTradeProductAdds;
    }

    public List<FinCashInfo> getFinCashInfos() {
        return finCashInfos;
    }

    public void setFinCashInfos(List<FinCashInfo> finCashInfos) {
        this.finCashInfos = finCashInfos;
    }

    public List<FinCashCalcRule> getFinCashCalcRules() {
        return finCashCalcRules;
    }

    public void setFinCashCalcRules(List<FinCashCalcRule> finCashCalcRules) {
        this.finCashCalcRules = finCashCalcRules;
    }

    public List<FinCashPlan> getFinCashPlans() {
        return finCashPlans;
    }

    public void setFinCashPlans(List<FinCashPlan> finCashPlans) {
        this.finCashPlans = finCashPlans;
    }
    /**
     * PK1
     * @return
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String getFinProdId_PK1(){
        return StringUtil.isNotEmpty(this.getFinprodId())? this.getFinprodId(): IDGenerator.generate();
    }

    @PrimaryKey(PrimaryKey.Field.PK_2)
    private String getBranch_PK2(){
        String pk2 = "";
        if(StringUtil.isNotBlank(this.getBjBranch())){
            pk2 = pk2+this.getBjBranch();
        }
        if(StringUtil.isNotBlank(this.getLiBranch())){
            pk2 = pk2+this.getLiBranch();
        }
        return pk2;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getFinprodTypeName() {
        return finprodTypeName;
    }

    public void setFinprodTypeName(String finprodTypeName) {
        this.finprodTypeName = finprodTypeName;
    }

    public String getVdate() {
        return vdate;
    }

    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getLiBranch() {
        return liBranch;
    }

    public void setLiBranch(String liBranch) {
        this.liBranch = liBranch;
    }

    public List<FinTradeProduct> getFinTradeProducts() {
        return finTradeProducts;
    }

    public void setFinTradeProducts(List<FinTradeProduct> finTradeProducts) {
        this.finTradeProducts = finTradeProducts;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getIsAccrPenaltyInt() {
        return isAccrPenaltyInt;
    }

    public void setIsAccrPenaltyInt(String isAccrPenaltyInt) {
        this.isAccrPenaltyInt = isAccrPenaltyInt;
    }

    public String getPenaltyBaseType() {
        return penaltyBaseType;
    }

    public void setPenaltyBaseType(String penaltyBaseType) {
        this.penaltyBaseType = penaltyBaseType;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public String getIsRushBack() {
        return isRushBack;
    }

    public void setIsRushBack(String isRushBack) {
        this.isRushBack = isRushBack;
    }

    public String getFxvdate() {
        return fxvdate;
    }

    public void setFxvdate(String fxvdate) {
        this.fxvdate = fxvdate;
    }

    public String getFxmdate() {
        return fxmdate;
    }

    public void setFxmdate(String fxmdate) {
        this.fxmdate = fxmdate;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getOldMdate() {
        return oldMdate;
    }

    public void setOldMdate(String oldMdate) {
        this.oldMdate = oldMdate;
    }

    public String getOldFxmdate() {
        return oldFxmdate;
    }

    public void setOldFxmdate(String oldFxmdate) {
        this.oldFxmdate = oldFxmdate;
    }

    public String getOldPenaltyBaseType() {
        return oldPenaltyBaseType;
    }

    public void setOldPenaltyBaseType(String oldPenaltyBaseType) {
        this.oldPenaltyBaseType = oldPenaltyBaseType;
    }

    public String getOldBjBranch() {
        return oldBjBranch;
    }

    public void setOldBjBranch(String oldBjBranch) {
        this.oldBjBranch = oldBjBranch;
    }

    public String getOldLiBranch() {
        return oldLiBranch;
    }

    public void setOldLiBranch(String oldLiBranch) {
        this.oldLiBranch = oldLiBranch;
    }

    public String getOldIsRushBack() {
        return oldIsRushBack;
    }

    public void setOldIsRushBack(String oldIsRushBack) {
        this.oldIsRushBack = oldIsRushBack;
    }

    public String getWithoutBranch() {
        return withoutBranch;
    }

    public void setWithoutBranch(String withoutBranch) {
        this.withoutBranch = withoutBranch;
    }
}
