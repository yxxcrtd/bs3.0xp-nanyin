/*
 * 文件名：MstYieldCurveInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhao
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.MstYieldCurveInfo;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.basicdata.marketdata.yieldcurveinfo.validator.ComYieldCurveInfoValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 收益率曲线dto
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ComYieldCurveInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = ComYieldCurveInfoValidator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class ComYieldCurveInfoDto extends MstYieldCurveInfo implements ITableRow,ProcessEntity  {
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
     * 获取 curveTypeName
     * @return curveTypeName.
     */
	//修改数据字典值 modified by lishuping 20191008 start
    public String getCurveTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.曲线利率类型, this.getCurveType());
    }
    //修改数据字典值 modified by lishuping 20191008 end




    
    /**
     * 设置 curveTypeName
     * @param curveTypeName curveTypeName
     */
    public void setCurveTypeName(String curveTypeName) {
        this.curveTypeName = curveTypeName;
    }





    /**
     * 获取 curveName
     * @return curveName.
     */
    public String getCurveName() {
        
        return SysDataUtil.getNameFromDataDict(DictCode.收益率曲线, this.getCurveCode());
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
    @Length(fieldName = "ComYieldCurveInfo001.curveName", max = 32, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ComYieldCurveInfo001.curveName", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value=Field.PK_1)
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
    @PrimaryKey(value = Field.PK_2)
    public String getCurveDateStr() {
        return DateUtil.format(super.getCurveDate());
    }
    
    @Override
    @NotEmpty(fieldName = "ComYieldCurveInfo001.curveDate", groups = {ValidateGroupEdit.class})
    public Date getCurveDate() {
        return super.getCurveDate();
    }
   
    @Override
    @Length(fieldName = "ComYieldCurveInfo001.curveType", max = 50, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ComYieldCurveInfo001.curveType", groups = {ValidateGroupEdit.class})
    @PrimaryKey(Field.PK_3)
    public String getCurveType() {
        return super.getCurveType();
    }

	//添加n、k modified by lishuping 20190930 start
    private BigDecimal K;

    private BigDecimal N;

    /**
     * 设置k
     * @param k k
     */
    public void setK(BigDecimal k) {
        K = k;
    }

    /**
     * 设置n
     * @param n n
     */
    public void setN(BigDecimal n) {
        N = n;
    }
    //add by lishuping 20191009 start
    @Length(fieldName = "ComYieldCurveInfo001.K", max = 30, groups = {ValidateGroupEdit.class})
    /*@NotEmpty(fieldName = "MstYieldCurveInfo001.K", groups = {ValidateGroupEdit.class})*/
    public BigDecimal getK() {
        return K;
    }

    @Length(fieldName = "ComYieldCurveInfo001.N", max = 30, groups = {ValidateGroupEdit.class})
    /*@NotEmpty(fieldName = "MstYieldCurveInfo001.N", groups = {ValidateGroupEdit.class})*/
    public BigDecimal getN() {
        return N;
    }
    //add by lishuping 20191009 end
    @Override
    @Length(fieldName = "ComYieldCurveInfo001.yield", max = 30, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ComYieldCurveInfo001.yield", groups = {ValidateGroupEdit.class})
    public BigDecimal getYield() {
        return super.getYield();
    }
   
    @Override
    @Length(fieldName = "ComYieldCurveInfo001.keyPoint", max = 10, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ComYieldCurveInfo001.keyPoint", groups = {ValidateGroupEdit.class})
    public BigDecimal getKeyPoint() {
        return super.getKeyPoint();
    }
}
