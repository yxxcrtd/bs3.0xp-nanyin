package com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.valuationparametermanage.withholdingamortize.validator.ValWithholdingAmortizeSet002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author tanxuan
 * @version 1.0 2019年11月29日
 * @see ValWithholdingAmortizeSet002Dto
 * @since 1.0
 */
@PageId("ValWithholdingAmortizeSet002")
@ValidLogic(targetClass = ValWithholdingAmortizeSet002DtoValidator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class ValWithholdingAmortizeSet002Dto extends BaseEntity implements ProcessEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    @PrimaryKey(value = Field.PK_1)
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 业务编号
     */
    @PrimaryKey(value = Field.PK_2)
    private String finprodId;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal prin;

    /**
     * 开始日期
     */
    private String vdate;

    /**
     * 结束日期
     */
    private String mdate;

    /**
     * pageModel
     */
    private String pageModel;

    /**
     * 处理操作
     */
    private String operateType;

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 取得 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName == null ? null : booksetName.trim();
    }

    /**
     * 取得 业务编号
     * 
     * @return 业务编号
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 业务编号
     * 
     * @param finprodId 业务编号
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * 取得 金额
     * 
     * @return 金额
     */
    public BigDecimal getPrin() {
        return prin;
    }

    /**
     * 设置 金额
     * 
     * @param prin 金额
     */
    public void setPrin(BigDecimal prin) {
        this.prin = prin;
    }

    /**
     * 取得 开始日期
     * 
     * @return 开始日期
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 开始日期
     * 
     * @param vdate 开始日期
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 结束日期
     * 
     * @return 结束日期
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 结束日期
     * 
     * @param mdate 结束日期
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    /**
     * 选中的数据
     */
    private transient List<ValWithholdingAmortizeSet002Dto> listValWithholdingAmortizeSetDto;

    public List<ValWithholdingAmortizeSet002Dto> getListValWithholdingAmortizeSetDto() {
        return listValWithholdingAmortizeSetDto;
    }

    public void setListValWithholdingAmortizeSetDto(List<ValWithholdingAmortizeSet002Dto> listValWithholdingAmortizeSetDto) {
        this.listValWithholdingAmortizeSetDto = listValWithholdingAmortizeSetDto;
    }

    /**
     * 获取 费用类型数据字典
     * 
     * @return 费用类型数据字典.
     */
    public String getFeeTypeFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.一次性费用类型,
            this.getFeeType())) ? SysDataUtil.getNameFromDataDict(DictCode.一次性费用类型,
                this.getFeeType()) : this.getFeeType();
    }

}
