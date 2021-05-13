package com.joyintech.tams.common.basicdata.marketdata.externaloptions.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;
//import com.joyintech.tams.common.basicdata.marketdata.externaloptions.validator.ExternalOptions002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * @author 姚轶文
 * @create 2019- 12-31 11:40
 */
//@ValidLogic(targetClass = ExternalOptions002DtoValidator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class ComExternalOptions002Dto extends BaseEntity implements ProcessEntity {


    private String finprodId; //金融产品代码
    /**
     * 画面元素
     */
    private String elementId;
    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * null
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    @NotBlank(message = "交易编号不能为空!", groups = ValidateGroupEdit.class)
    private String finprodId2;

    /**
     * null
     */
    @PrimaryKey(value= PrimaryKey.Field.PK_2)
    @NotBlank(message = "估值日期不能为空!", groups = ValidateGroupEdit.class)
    private String valDate;

    /**
     * 处理操作
     */
    private String operateType;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    //@NotBlank(message = "公布日期不能为空!", groups = ValidateGroupEdit.class)
    private String publishDate;
    /**
     * 取得 null
     * @return null
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     * 设置 null
     * @param publishDate null
     */
    //@JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    /**
     * 取得 null
     * @return null
     */
    public String getFinprodId2() {
        return finprodId2;
    }

    /**
     * 设置 null
     * @param finprodId2 null
     */
    public void setFinprodId2(String finprodId2) {
        this.finprodId2 = finprodId2 == null ? null : finprodId2.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getValDate() {
        return valDate;
    }

    /**
     * 设置 null
     * @param valDate null
     */
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    /**
     * null
     */
    @NotNull(message = "收盘价不能为空!", groups = ValidateGroupEdit.class)
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal valuation;

    /**
     * null
     */
    private String valmodelId;

    /**
     * null
     */
    private String ccy;

    /**
     * null
     */
    private String inputType;

    private List<ComExternalOptions002Dto> selectedItem;

    public List<ComExternalOptions002Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ComExternalOptions002Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getValuation() {
        return valuation;
    }

    /**
     * 设置 null
     * @param valuation null
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getValmodelId() {
        return valmodelId;
    }

    /**
     * 设置 null
     * @param valmodelId null
     */
    public void setValmodelId(String valmodelId) {
        this.valmodelId = valmodelId == null ? null : valmodelId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 null
     * @param ccy null
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 null
     * @param inputType null
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    //@NotBlank(message = "合约标的不能为空!", groups = ValidateGroupEdit.class)
    public String targetId;
    public String targetName;
    public String targetType;
    public String targetTypeStr;

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
    
    //add by tanxuan on 20200210
    /**
     * 起息日
     */
    private String vdate;
    
    /**
     * 到期日
     */
    private String mdate;
    
    /**
     * 取得 起息日
     * @return 起息日
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }
    
    /**
     * 取得 到期日
     * @return 到期日
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetTypeStr() {
        return SysDatadictUtil.getNameFromDataDict(DictCode.期权资产类型,this.getTargetType());
    }

    public void setTargetTypeStr(String targetTypeStr) {
        this.targetTypeStr = targetTypeStr;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
