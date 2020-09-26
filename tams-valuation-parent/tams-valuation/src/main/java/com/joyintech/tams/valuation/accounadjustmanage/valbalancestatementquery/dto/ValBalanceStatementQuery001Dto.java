/*
 * 文件名：ValBalanceStatementQuery001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Zane
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.valbalancestatementquery.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.common.base.entity.BokValTableData;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;


public class ValBalanceStatementQuery001Dto extends BokValTableData implements ITableRow, GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -904100591006148547L;
    
    /**
     * 行ID
     */
    private String rowId;
    
    
    /**
     * 是否有子节点
     */
    private String hasChildren;

    /**
     * 名称
     */
    private String name;
    
    
    /**
     * 行情日期（暂时设为String,为空，后续批处理设计修改后才取值）
     */
    private String marketDate;

    /**
     * 原币币种 - 中文
     */
    private String oCcyFmt;

    public String getoCcyFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.币种, oCcyFmt))?
                SysDataUtil.getNameFromDataDict(DictCode.币种,oCcyFmt):oCcyFmt;
    }

    public void setoCcyFmt(String oCcyFmt) {
        this.oCcyFmt = oCcyFmt;
    }

    
    public String getHasChildren() {
        return hasChildren;
    }


    
    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }
    

    @Override
    @NotEmpty(fieldName = "ValBalanceStatementQuery001.booksetId")
    public String getBooksetId() {
        return super.getBooksetId();
    }


    @Override
    @NotEmpty(fieldName = "ValBalanceStatementQuery001.valDate")
    public Date getValDate() {
        return super.getValDate();
    }
    
    public String getBalFlagFmt(){
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.余额方向, this.getBalFlag()))?
            SysDataUtil.getNameFromDataDict(DictCode.余额方向, this.getBalFlag()):this.getBalFlag();
            
//        return SysDataUtil.getNameFromDataDict(DictCode.余额方向, this.getBalFlag());
    }


    
    /**
     * 获取 行情日期
     * @return 行情日期
     */
    public String getMarketDate() {
        if(StringUtils.isNotEmpty(marketDate)){
            return DateUtil.format(DateUtil.parse(marketDate), "yyyy/MM/dd");
        }
        return "";
    }


    
    /**
     * 设置 行情日期
     * @param marketDate 行情日期
     */
    public void setMarketDate(String marketDate) {
        this.marketDate = marketDate;
    }
    
    
    @Override
    public boolean isLeaf() {
        return StringUtil.equals(com.joyintech.framework.constant.DictCodeList.是否_是.getValue(), hasChildren);
    }

    @Override
    public void setLeaf(boolean leaf) {
//        this.leaf = leaf;
    }

    @Override
    public String getRowId() {
        return this.rowId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void setOrder(int order) {
    }
    
    public String getId() {
        return this.getSubjectNo();
    }

    
    public void setId(String id) {
        setSubjectNo(id);;
    }

    
    public String getParentId() {
        return this.getFsubjectId();
    }

    
    public void setParentId(String parentId) {
        setFsubjectId(parentId);;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 取得 科目名称
     * 
     * @return 科目名称
     */
    @Override
    public String getSubjectName() {
        String subjectName = super.getSubjectName();
        String subjectNo = super.getSubjectNo();
        if (StringUtil.isNotEmpty(subjectName)) {
            // 由于框架会查询2次，故屏蔽第二次缓存查询进入，已包含%，则不处理
            if ((DictCodeList.估值表类型_成立以来年化收益率.getValue().equals(super.getDetailDist())
                 || DictCodeList.估值表类型_周期年化收益率.getValue().equals(super.getDetailDist()))
                && !subjectNo.contains("%")) {
                // 获取小数位数
                int length = subjectName.length() - subjectName.indexOf(".") - 1;
                // 取值* 100，且小数位数与原来保持一致
                return ArithmeticUtil.multiply(length, null, new BigDecimal(subjectName),
                    new BigDecimal(100)).toString();
            }
        }
        return subjectName;
    }

    /**
     * 取得 科目号
     * 
     * @return 科目号
     */
    @Override
    public String getSubjectNo() {
        String subjectNo = super.getSubjectNo();
        // 由于框架会查询2次，故屏蔽第二次缓存查询进入，已包含%，则不处理
        if (DictCodeList.估值表类型_成立以来年化收益率.getValue().equals(super.getDetailDist())
            && !subjectNo.contains("%")) {
            return subjectNo.replace("成立以来年化收益率", "成立以来年化收益率%");
        }
        else if (DictCodeList.估值表类型_周期年化收益率.getValue().equals(super.getDetailDist())
                 && !subjectNo.contains("%")) {
            return subjectNo.replace("周期年化收益率", "周期年化收益率%");
        }
        return subjectNo;
    }
    
}
