/*
 * 文件名：MstYieldCurveInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhao
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.yieldcurveinfo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.MstYieldCurveInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;

/**
 * 收益率曲线dto
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ComYieldCurveInfoDto
 * @since 1.0
 */
public class ComYieldCurveInfoDto extends MstYieldCurveInfo implements ITableRow{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5143537191208289654L;
    
    /**
     * 选中的数据
     */
    private transient List<ComYieldCurveInfoDto> selectedItem;

    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;


    
   /**
    * 
    */
    @SuppressWarnings("unused")
    private String curveTypeName;
    
    
    /**
     * 设置 curveTypeName
     * @param curveTypeName curveTypeName
     */
    public void setCurveTypeName(String curveTypeName) {
        this.curveTypeName = curveTypeName;
    }

    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<ComYieldCurveInfoDto> getSelectedItem() {
        return selectedItem;
    }

    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<ComYieldCurveInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }


    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }


    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }


    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    
   
    @Override
    public String getCurveCode() {
        
        return super.getCurveCode();
    }
    
    /**
     * 设置曲线日期为主键
     * <一句话功能简述>
     * <功能详细描述>
     * @return String
     * @see
     * @since 1.0
     */
    public String getCurveDateStr() {
        return DateUtil.format(super.getCurveDate());
    }
    
    @Override
    public Date getCurveDate() {
        return super.getCurveDate();
    }
   
    @Override
    public String getCurveType() {
        return super.getCurveType();
    }
    
	//添加n、k modified by lishuping 20190930 start
    private BigDecimal K;

    private BigDecimal N;

    /**
     * 设置 k
     * @param k k
     */
    public void setK(BigDecimal k) {
        K = k;
    }

    /**
     * 设置 n
     * @param n n
     */
    public void setN(BigDecimal n) {
        N = n;
    }

    /**
     * 获取 k
     * @return k
     */
    public BigDecimal getK() {
        return K;
    }

    /**
     * 获取 n
     * @return n
     */
    public BigDecimal getN() {
        return N;
    }
    //添加n、k modified by lishuping 20190930 end
    @Override
    public BigDecimal getYield() {
        return super.getYield();
    }
    //修改返回值 modified by lishuping 20190930 start
    @Override
    public BigDecimal getKeyPoint() {
        return super.getKeyPoint();
    }
    //修改返回值 modified by lishuping 20190930 end
}
