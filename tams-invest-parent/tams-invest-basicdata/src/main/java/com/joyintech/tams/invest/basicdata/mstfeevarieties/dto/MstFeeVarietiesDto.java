package com.joyintech.tams.invest.basicdata.mstfeevarieties.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.invest.basicdata.mstfeevarieties.validator.MstFeeVarietiesValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;
/**
 * 
 * @author wangzehui
 *
 */
@PageId("MstFeeVarieties002")
@ValidLogic(targetClass = MstFeeVarietiesValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class MstFeeVarietiesDto extends MstFeeinfo implements ProcessEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选择数据
     */
    private List<MstFeeVarietiesDto> selectedItem;

    /**
     * 要素id
     */
    private String elementId;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;
    /**
     * 用来判断是否为维护数据
     */
    private boolean flag;
    
    public boolean isFlag() {
        return flag;
    }
    
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    /**
     * 设置表格控件不可用
     * <一句话功能简述>
     * <功能详细描述>
     * @return String
     * @see
     * @since 1.0
     */
    public String getUpdateFlg(){
        if(this.flag){
            return "02";
        }
        return "01";
        
    }
    /**
     * 费用代码
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName = "MstFeeVarieties001.feeId", max = 32, groups = {ValidateGroupEdit.class})
    public String getFeeId() {
        return super.getFeeId();
    }
    /**
     * 费用名称
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstFeeVarieties001.feeName", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstFeeVarieties001.feeName", max = 200, groups = {
        ValidateGroupEdit.class})
    @PrimaryKey(value = Field.PK_1)
    public String getFeeName() {
        return super.getFeeName();
    }
    /**
     * 关联范围
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstFeeVarieties002.applyType", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstFeeVarieties002.applyType", max = 50, groups = {
        ValidateGroupEdit.class})
    public String getApplyType() {
        return super.getApplyType();
    }
    /**
     * 关联范围显示类型
     * {@inheritDoc}
     */
    public String getApplyTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.关联范围, super.getApplyType());
    }
    /**
     * 费用类型
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstFeeVarieties001.feeType", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstFeeVarieties001.feeType", max = 50, groups = {ValidateGroupEdit.class})
    public String getFeeType() {
        return super.getFeeType();
    }

    public String getFeeTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.费用品种类型, super.getFeeType());
    }
    /**
     * 计费基数
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName = "MstFeeVarieties002.basicBill", max = 50, groups = {
        ValidateGroupEdit.class})
    public String getBasicBill() {
        return super.getBasicBill();
    }

    public String getBasicBillStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.计费基数_费用品种, super.getBasicBill());
    }
    /**
     * 计息基础
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName = "MstFeeVarieties002.basis", max = 50, groups = {ValidateGroupEdit.class})
    public String getBasis() {
        return super.getBasis();
    }

    public String getBasisStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.计息基础, super.getBasis());
    }
    /**
     * 收费类型
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstFeeVarieties002.chargeType", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstFeeVarieties002.chargeType", max = 50, groups = {
        ValidateGroupEdit.class})
    public String getChargeType() {
        return super.getChargeType();
    }

    public String getChargeTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.收费类型, super.getChargeType());
    }
    /**
     * 费用类型
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstFeeVarieties002.orgType", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstFeeVarieties002.orgType", max = 50, groups = {ValidateGroupEdit.class})
    public String getOrgType() {
        return super.getOrgType();
    }

    public String getOrgTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.费用类型, super.getOrgType());
    }
    /**
     * 二级机构费用类型
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstFeeVarieties002.orgType2", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstFeeVarieties002.orgType2", max = 50, groups = {
        ValidateGroupEdit.class})
    public String getOrgType2() {
        return super.getOrgType2();
    }
    
    public String getOrgType2Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.费用二级类型, super.getOrgType2());
    }
    /**
     * 备注
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName = "MstFeeVarieties002.remark", max = 300, groups = {
        ValidateGroupEdit.class})
    public String getRemark() {
        return super.getRemark();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<MstFeeVarietiesDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<MstFeeVarietiesDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    // 产品（模板）新建特殊处理
    /**
     * 市场代码
     */
    private String finprodMarketId;
    
    /**
     * 收益类型
     */
    private String profitType;
    
    /**
     * 产品挂的费用
     */
    private transient List<BussFinProductFeeInfoDto> feeList;

    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    /**
     * 获取 feeList
     * @return feeList.
     */
    public List<BussFinProductFeeInfoDto> getFeeList() {
        return feeList;
    }

    
    /**
     * 设置 feeList
     * @param feeList feeList
     */
    public void setFeeList(List<BussFinProductFeeInfoDto> feeList) {
        this.feeList = feeList;
    }

    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }
    
}
