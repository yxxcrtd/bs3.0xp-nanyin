package com.joyintech.tams.disclosure.information.config.repRelDeal.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepRelDeal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.repRelDeal.validator.RepRelDealValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@PageId("RepRelDeal002")
@ValidLogic(targetClass = RepRelDealValidator.class, groups = { ValidateGroupEdit.class, ValidateGroupDelete.class })
public class RepRelDealDto extends RepRelDeal implements ITableRow, ProcessEntity {
    /**
     * excle行
     */
    private String excelNum;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 页面类型
     */
    private String pageModel;
    /**
     * 要素id
     */
    private String elementId;
    /**
     * 选中的数据列表
     */
    private transient List<RepRelDealDto> selectedItem;

    /**
     * 产品名称
     */
    private String prodName;

    private String effdateStr;

    private String effdelidateStr;

    @Override
    public String getUuid() {
        return super.getUuid();
    }
    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<RepRelDealDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RepRelDealDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
    }

    public String getPubDateStr() {
        return this.getPubDate()==null?null: DateUtil.format(this.getPubDate(), "yyyy-MM-dd");
    }
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getEffdateStr() {
        if (StringUtil.isNotEmpty(this.effdateStr)) {
            return this.effdateStr;
        } else {
            return this.getEffdate() == null ? null : DateUtil.format(this.getEffdate(), "yyyy-MM-dd");
        }
    }
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getEffdelidateStr() {
        if (StringUtil.isNotEmpty(this.effdelidateStr)) {
            return this.effdelidateStr;
        } else {
            return this.getEffdelidate() == null ? null : DateUtil.format(this.getEffdelidate(), "yyyy-MM-dd");
        }
    }

    public void setEffdateStr(String effdateStr) {
        this.effdateStr = effdateStr;
    }

    public void setEffdelidateStr(String effdelidateStr) {
        this.effdelidateStr = effdelidateStr;
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }

    //导出时保留两位小数
    public String getCreditorAmtStr() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        return this.getCreditorAmt()==null?"0.00":decimalFormat.format(this.getCreditorAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public String getBondAmtStr() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        return this.getBondAmt()==null?"0.00":decimalFormat.format(this.getBondAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public String getInvestAmtStr() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        return this.getInvestAmt()==null?"0.00":decimalFormat.format(this.getInvestAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public String getAssetAmtStr() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        return this.getAssetAmt()==null?"0.00":decimalFormat.format(this.getAssetAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public String getTrustFeeStr() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        return this.getTrustFee()==null?"0.00":decimalFormat.format(this.getTrustFee().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public String getSellFeeStr() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        return this.getSellFee()==null?"0.00":decimalFormat.format(this.getSellFee().setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public String getOtherFeeStr() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00#");
        return this.getOtherFee()==null?"0.00":decimalFormat.format(this.getOtherFee().setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}
