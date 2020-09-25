package com.joyintech.service.basicdata.mstfeevarieties.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.MstFeeinfo;
/**
 * 
 * @author wangzehui
 *
 */
public class MstFeeVarietiesDto extends MstFeeinfo{

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
     * @return
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
    public String getFeeId() {
        return super.getFeeId();
    }
    /**
     * 费用名称
     * {@inheritDoc}
     */
    @Override
    public String getFeeName() {
        return super.getFeeName();
    }
    /**
     * 关联范围
     * {@inheritDoc}
     */
    @Override
    public String getApplyType() {
        return super.getApplyType();
    }
    /**
     * 费用类型
     * {@inheritDoc}
     */
    @Override
    public String getFeeType() {
        return super.getFeeType();
    }
    /**
     * 计费基数
     * {@inheritDoc}
     */
    @Override
    public String getBasicBill() {
        return super.getBasicBill();
    }
    /**
     * 计息基础
     * {@inheritDoc}
     */
    @Override
    public String getBasis() {
        return super.getBasis();
    }
    /**
     * 收费类型
     * {@inheritDoc}
     */
    @Override
    public String getChargeType() {
        return super.getChargeType();
    }
    /**
     * 机构费用类型
     * {@inheritDoc}
     */
    @Override
    public String getOrgType() {
        return super.getOrgType();
    }
    /**
     * 二级机构费用类型
     * {@inheritDoc}
     */
    @Override
    public String getOrgType2() {
        return super.getOrgType2();
    }
    /**
     * 备注
     * {@inheritDoc}
     */
    @Override
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

}
