package com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisProdBonusplan;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.validator.ProdBonusplanValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

//public class ProdBonusplanDto extends BaseEntity {
@PageId("ProdBonusplanManager001")
@ValidLogic(targetClass = ProdBonusplanValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class ProdBonusplanDto extends DisProdBonusplan implements ITableRow, ProcessEntity {

    private Map<String, Object> param;

    private String updateDateStr;

    private String prodName;

    private String dataStatusName;

    private String operateType ;

    /**
     * 收益分配基准日
     */
    private String settDateStr;

    /**
     * 红利发放日
     */
    private String paymentDateStr;

    private String bonusModeName;

    /**
     * 产品编号拼接
     */
    private String prodIdArr;

    private String operationType ;

    /**
     * 选中的数据
     */
    private transient List<ProdBonusplanDto> selectedItem;
    /**
     * 元素ID
     */
    private String elementId;

    public List<ProdBonusplanDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ProdBonusplanDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getProdIdArr() {
        return prodIdArr;
    }

    public void setProdIdArr(String prodIdArr) {
        this.prodIdArr = prodIdArr;
    }

    public String getSettDateStr() {
        if(StringUtils.isNotBlank(this.settDateStr)){
            return this.settDateStr ;
        }else{
            return this.settDateStr = this.getSettDate()==null?null: DateUtil.format(this.getSettDate(), "yyyy-MM-dd");
        }
    }

    public void setSettDateStr(String settDateStr) {
        this.settDateStr = settDateStr;
    }

    public String getPaymentDateStr() {
        if(StringUtils.isNotBlank(this.paymentDateStr)){
            return this.paymentDateStr ;
        }else{
            return this.paymentDateStr = this.getPaymentDate()==null?null: DateUtil.format(this.getPaymentDate(), "yyyy-MM-dd");
        }
    }

    public void setPaymentDateStr(String paymentDateStr) {
        this.paymentDateStr = paymentDateStr;
    }

    public String getBonusModeName() {
        return bonusModeName;
    }

    public void setBonusModeName(String bonusModeName) {
        this.bonusModeName = bonusModeName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public String getUpdateDateStr() {
        return updateDateStr;
    }

    public void setUpdateDateStr(String updateDateStr) {
        this.updateDateStr = updateDateStr;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getDataStatusName() {
        return dataStatusName;
    }

    public void setDataStatusName(String dataStatusName) {
        this.dataStatusName = dataStatusName;
    }


    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getBonusPlanId() {
        return super.getBonusPlanId();
    }
}