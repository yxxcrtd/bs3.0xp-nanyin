/*
 * 文件名：ValBalanceStatementQuery001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Zane
 * 修改时间：2018年7月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valbalancestatementquery.dto;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.common.base.entity.BokValTableData;
import com.joyintech.tams.constant.DictCode;


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

    /**
     * 获取 行情日期
     * @return 行情日期
     */
    public String getMarketDate() {
        return this.marketDate;
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

}
