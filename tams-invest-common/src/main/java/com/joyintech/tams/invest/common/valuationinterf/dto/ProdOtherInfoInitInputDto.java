/*
 * 文件名：ProdOtherInfoInitInputDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.valuationinterf.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.framework.base.BaseEntity;

/**
 * 估值接口 产品其他信息初始化 输入参数
 *
 * @author etliu
 * @version 1.0 2019年1月12日
 * @see ProdOtherInfoInitInputDto
 * @since 1.0
 */
public class ProdOtherInfoInitInputDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2275923693861664434L;

    /**
     * 画面id
     */
    private String pageId;

    /**
     * 标的类金融产品entity
     */
    private FinProduct finProduct;

    /**
     * 标的类金融产品附属表
     */
    private FinProductAdd finProductAdd;

    /**
     * 理财产品估值参数
     */
    private PrdValParam prdValParam;

    /**
     * 组合信息表
     */
    private PtlPortfolio ptlPortfolio;

    /**
     * 金融产品账户信息
     */
    private List<PtlCapacctInfo> prodAcctDataList;

    /**
     * 金融产品账户信息
     */
    private List<BussFinProductFundAccountInfoDto> finProdAcctDataList;

    /**
     * 组合估值参数
     */
    private PtlValParam ptlValParam;

    /**
     * 交易类金融产品
     */
    private FinTradeProduct finTradeProduct;

    /**
     * 是否是新建数据
     */
    private boolean isNewData;
    /**
     * 是否子产品
     */
    private String isLayer;
    /**
     * add by jiangpeng on 20210401
     * 是否是产品编辑 TISHKBS-1952 用于处理产品编辑时覆盖 BOK_BOOKSET_CAPACCT_INFO(账套清算账户表)的问题
     */
    private boolean isProductEdit = false;
    /**
     * 获取 pageId
     * 
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * 
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取 finProduct
     * 
     * @return finProduct.
     */
    public FinProduct getFinProduct() {
        return finProduct;
    }

    /**
     * 设置 finProduct
     * 
     * @param finProduct finProduct
     */
    public void setFinProduct(FinProduct finProduct) {
        this.finProduct = finProduct;
    }

    /**
     * 获取 finProductAdd
     * 
     * @return finProductAdd.
     */
    public FinProductAdd getFinProductAdd() {
        return finProductAdd;
    }

    /**
     * 设置 finProductAdd
     * 
     * @param finProductAdd finProductAdd
     */
    public void setFinProductAdd(FinProductAdd finProductAdd) {
        this.finProductAdd = finProductAdd;
    }

    /**
     * 获取 prdValParam
     * 
     * @return prdValParam.
     */
    public PrdValParam getPrdValParam() {
        return prdValParam;
    }

    /**
     * 设置 prdValParam
     * 
     * @param prdValParam prdValParam
     */
    public void setPrdValParam(PrdValParam prdValParam) {
        this.prdValParam = prdValParam;
    }

    /**
     * 获取 ptlPortfolio
     * 
     * @return ptlPortfolio.
     */
    public PtlPortfolio getPtlPortfolio() {
        return ptlPortfolio;
    }

    /**
     * 设置 ptlPortfolio
     * 
     * @param ptlPortfolio ptlPortfolio
     */
    public void setPtlPortfolio(PtlPortfolio ptlPortfolio) {
        this.ptlPortfolio = ptlPortfolio;
    }

    /**
     * 获取 prodAcctDataList
     * 
     * @return prodAcctDataList.
     */
    public List<PtlCapacctInfo> getProdAcctDataList() {
        return prodAcctDataList;
    }

    /**
     * 设置 prodAcctDataList
     * 
     * @param prodAcctDataList prodAcctDataList
     */
    public void setProdAcctDataList(List<PtlCapacctInfo> prodAcctDataList) {
        this.prodAcctDataList = prodAcctDataList;
    }

    /**
     * 获取 ptlValParam
     * 
     * @return ptlValParam.
     */
    public PtlValParam getPtlValParam() {
        return ptlValParam;
    }

    /**
     * 设置 ptlValParam
     * 
     * @param ptlValParam ptlValParam
     */
    public void setPtlValParam(PtlValParam ptlValParam) {
        this.ptlValParam = ptlValParam;
    }

    /**
     * 获取 finTradeProduct
     * 
     * @return finTradeProduct.
     */
    public FinTradeProduct getFinTradeProduct() {
        return finTradeProduct;
    }

    /**
     * 设置 finTradeProduct
     * 
     * @param finTradeProduct finTradeProduct
     */
    public void setFinTradeProduct(FinTradeProduct finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
    }

    /**
     * 获取 isNewData
     * 
     * @return isNewData.
     */
    public boolean isNewData() {
        return isNewData;
    }

    /**
     * 设置 isNewData
     * 
     * @param isNewData isNewData
     */
    public void setNewData(boolean isNewData) {
        this.isNewData = isNewData;
    }

    /**
     * 获取 finProdAcctDataList
     *
     * @return finProdAcctDataList
     */
    public List<BussFinProductFundAccountInfoDto> getFinProdAcctDataList() {
        return finProdAcctDataList;
    }

    /**
     * 设置 finProdAcctDataList
     *
     * @param finProdAcctDataList finProdAcctDataList
     */
    public void setFinProdAcctDataList(List<BussFinProductFundAccountInfoDto> finProdAcctDataList) {
        this.finProdAcctDataList = finProdAcctDataList;
    }

	public String getIsLayer() {
		return isLayer;
	}

	public void setIsLayer(String isLayer) {
		this.isLayer = isLayer;
	}

    public boolean isProductEdit() {
        return isProductEdit;
    }

    public void setProductEdit(boolean productEdit) {
        isProductEdit = productEdit;
    }
}
