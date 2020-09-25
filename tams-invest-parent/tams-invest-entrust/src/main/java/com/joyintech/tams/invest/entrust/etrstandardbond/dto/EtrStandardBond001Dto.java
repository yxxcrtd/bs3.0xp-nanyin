/*
 * 文件名：TrdRepoTrade001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.entrust.etrstandardbond.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("EtrStandardBond001")
public class EtrStandardBond001Dto extends BaseEntity implements ProcessEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 4673833638465263091L;

    /**
     * pageModel
     */
    private String pageModel;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 金融产品代码
     */
    private String finProdId;

    /**
     * 交易场所
     */
    private String tradePlace;

    /**
     * 证券账户
     */
    private String secAcctId;


    /**
     * 分支序号
     */
    private Long branch;
    

    /**
     * 获取 pageModel
     *
     * @rturn pageModel
     */
    public String getPageModel() {
        return this.pageModel;
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
     * 获取 portfolioId
     *
     * @rturn portfolioId
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 finProdId
     *
     * @rturn finProdId
     */
    public String getFinProdId() {
        return this.finProdId;
    }

    /**
     * 设置 finProdId
     *
     * @param finProdId finProdId
     */
    public void setFinProdId(String finProdId) {
        this.finProdId = finProdId;
    }

    /**
     * 获取 tradePlace
     *
     * @rturn tradePlace
     */
    public String getTradePlace() {
        return this.tradePlace;
    }

    /**
     * 设置 tradePlace
     *
     * @param tradePlace tradePlace
     */
    public void setTradePlace(String tradePlace) {
        this.tradePlace = tradePlace;
    }

    /**
     * 获取 secAcctId
     *
     * @rturn secAcctId
     */
    public String getSecAcctId() {
        return this.secAcctId;
    }

    /**
     * 设置 secAcctId
     *
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 获取 branch
     *
     * @rturn branch
     */
    public Long getBranch() {
        return this.branch;
    }

    /**
     * 设置 branch
     *
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }
    /**
     * 可质押券面总额
     */
    private BigDecimal pledStrdPrinAmt;
    /**
     * 持仓券面总额
     */
    private BigDecimal posStrdPrinAmt;
    /**
     * 登记托管机构
     */
    private String registOrg ;
    /**
     * 到期日
     */
    private Date mdate ;
    
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 折算比例
     */
    private BigDecimal scale;
    // 债券名称
    private String finProdName ;


    
    public BigDecimal getPledStrdPrinAmt() {
        return pledStrdPrinAmt;
    }

    
    public void setPledStrdPrinAmt(BigDecimal pledStrdPrinAmt) {
        this.pledStrdPrinAmt = pledStrdPrinAmt;
    }

    
    public BigDecimal getPosStrdPrinAmt() {
        return posStrdPrinAmt;
    }

    
    public void setPosStrdPrinAmt(BigDecimal posStrdPrinAmt) {
        this.posStrdPrinAmt = posStrdPrinAmt;
    }

    
    public String getRegistOrg() {
        return registOrg;
    }

    
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    
    public Date getMdate() {
        return mdate;
    }

    
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    public BigDecimal getScale() {
        return scale;
    }

    
    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    
    public String getFinProdName() {
        return finProdName;
    }

    
    public void setFinProdName(String finProdName) {
        this.finProdName = finProdName;
    }
    //交易场所TEXT
    private String tradePlaceText ;
    /**
     * 获取 tradePlaceText
    *
    * @rturn tradePlaceText
    */
   public String getTradePlaceText() {
       if (StringUtil.isNotEmpty(this.getTradePlace())) {
           tradePlaceText = SysDataUtil.getNameFromDataDict(DictCode.交易场所, this.getTradePlace());
       }
       return tradePlaceText;
   }

   /**
    * 设置 tradePlaceText
    *
    * @param tradePlaceText tradePlaceText
    */
   public void setTradePlaceText(String tradePlaceText) {
       this.tradePlaceText = tradePlaceText;
   }
   
   // 质押券管理页面选中的数据
   private  List<EtrStandardBond001Dto> selectedItem = new ArrayList<>();



   public List<EtrStandardBond001Dto> getSelectedItem() {
       return selectedItem;
   }
    
    
   public void setSelectedItem(List<EtrStandardBond001Dto> selectedItem) {
       this.selectedItem = selectedItem;
   }

}
