/*
 * 文件名：MstNaturalPerson001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstnaturalperson.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.MstCustomerInfoAddWithBLOBs;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.GridRowDto;

/**
 * 
 * @author xiexinting
 * @version 1.0 2018年8月14日
 * @see MstNaturalPerson001Dto
 * @since 1.0
 */
public class MstNaturalPerson001Dto extends MstCustomerInfoAddWithBLOBs implements ITableRow,GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6541942592369283207L;
    /**
     * 客户类型
     */
    private String customerType;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面模型
     */
    private String pageModel;

    /**
     * 客户名
     */
    private String customerName;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 获取 customerType
     * @return customerType.
     */
    public String getCustomerType() {
        return customerType;
    }

    
    /**
     * 设置 customerType
     * @param customerType customerType
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * 选中的数据
     */
    private transient List<MstNaturalPerson001Dto> selectedItem;

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 pageModel
     * 
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     * 
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<MstNaturalPerson001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstNaturalPerson001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 姓名check并获取
     * 
     * @return customerName.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSex() {
        return super.getSex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPersonId() {
        return super.getPersonId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPhone() {
        return super.getPhone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getmPhone() {
        return super.getmPhone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMail() {
        return super.getMail();
    }

    /**
     * 
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public String getPosition() {
        return super.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCompanyName() {
        return super.getCompanyName();
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public String getAddress() {
        return super.getAddress();
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public String getProperty() {
        return super.getProperty();
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return 返回备注
     * @see
     * @since 1.0
     */
    public String getRemark() {
        return super.getRemark();
    }

    /**
     * 设置 customerName
     * 
     * @param customerName customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRowId() {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowId(String rowId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOrder(int order) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParentId() {
  
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParentId(String parentId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf() {

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLeaf(boolean leaf) {

    }

}
