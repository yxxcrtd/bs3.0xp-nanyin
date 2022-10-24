package com.joyintech.tams.disclosure.information.config.bonuscalist.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisBonusList;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.bonuscalist.validator.BonusCalist001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("BonusCalist002")
@ValidLogic(targetClass = BonusCalist001Validator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class BonusCalist001Dto extends DisBonusList implements ITableRow, ProcessEntity {

    /**
     * 序列
     */
    private static final long serialVersionUID = -2744914232496735461L;
    /**
     * excle行
     */
    private String excelNum;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private List<BonusCalist001Dto> selectedItem;
    /**
     * 元素ID
     */
    private String elementId;

    private String prodName;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<BonusCalist001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<BonusCalist001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getDataStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,super.getDataStatus());
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public String profitBaseDateStr;

    public void setProfitBaseDateStr(String profitBaseDateStr) {
        this.profitBaseDateStr = profitBaseDateStr;
    }

    public String getProfitBaseDateStr() {
        if (StringUtil.isNotEmpty(this.profitBaseDateStr)) {
            return this.profitBaseDateStr;
        } else {
            return super.getProfitBaseDate() == null ? null : DateUtil.format(super.getProfitBaseDate(), "yyyy/MM/dd");
        }
    }

    private String lastProfitBaseDateStr;

    public void setLastProfitBaseDateStr(String lastProfitBaseDateStr) {
        this.lastProfitBaseDateStr = lastProfitBaseDateStr;
    }

    public String getLastProfitBaseDateStr(){
        if (StringUtil.isNotEmpty(this.lastProfitBaseDateStr)) {
            return this.lastProfitBaseDateStr;
        } else {
            return super.getLastProfitBaseDate() == null ? null : DateUtil.format(super.getLastProfitBaseDate(), "yyyy/MM/dd");
        }
    }
    @Override
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    public String getBonusListId() {
        return super.getBonusListId();
    }

    @Override
    @NotEmpty(fieldName = "子产品代码", groups = {ValidateGroupEdit.class})
    public String getProdId() {
        return super.getProdId();
    }

    @Override
    @NotEmpty(fieldName = "单位净值", groups = {ValidateGroupEdit.class})
    public BigDecimal getUnitNum() {
        return super.getUnitNum();
    }

    @Override
    @NotEmpty(fieldName = "本期收益分配基准日", groups = {ValidateGroupEdit.class})
    public Date getProfitBaseDate() {
        return super.getProfitBaseDate();
    }

    @Override
    @NotEmpty(fieldName = "可供分配利润", groups = {ValidateGroupEdit.class})
    public BigDecimal getAvaiProfit() {
        return super.getAvaiProfit();
    }

    @Override
    @NotEmpty(fieldName = "应分配分红金额", groups = {ValidateGroupEdit.class})
    public BigDecimal getDisBonusMoney() {
        return super.getDisBonusMoney();
    }

    @Override
    @NotEmpty(fieldName = "单位分红", groups = {ValidateGroupEdit.class})
    public BigDecimal getUnitBonus() {
        return super.getUnitBonus();
    }

    public String getRunDaysStr(){
        return String.valueOf(super.getRunDays());
    }
}
