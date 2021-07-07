package com.joyintech.service.entrust.etribrepo.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBondInDto;


public class EtrIbRepoLib001Dto {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2006758325640536249L;

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

    private String secAcctStr;
    
    private String pledgeStockId; 
    private String pledgeStockName;
    
    /**
     * 质押券列表信息
     */
    List<EtrIbRepoLib001Dto> pledgeBondList = new ArrayList<>();

    /**
     * 可质押券列表信息
     */
    List<EtrIbRepoLib001Dto> avaPledgeBondList;

    public String getSecAcctStr() {
        return secAcctStr;
    }

    public void setSecAcctStr(String secAcctStr) {
        this.secAcctStr = secAcctStr;
    }

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
     * 债项评级
     */
    private String gradeResult ;
    /**
     * 剩余期限
     */
    private String lastPeriod;
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
    /**
     * 债券简称
     */
    private String finProdAbbr;


    
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

    public String getGradeResult() {
        return gradeResult;
    }
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }
    public String getLastPeriod() {
        return lastPeriod;
    }
    public void setLastPeriod(String lastPeriod) {
        this.lastPeriod = lastPeriod;
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

    // 质押券管理页面选中的数据
    private  List<EtrIbRepo001Dto> selectedItem = new ArrayList<>();


    
    public List<EtrIbRepo001Dto> getSelectedItem() {
        return selectedItem;
    }

    
    public void setSelectedItem(List<EtrIbRepo001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

	public String getFinProdAbbr() {
		return finProdAbbr;
	}

	public void setFinProdAbbr(String finProdAbbr) {
		this.finProdAbbr = finProdAbbr;
	}

	public List<EtrIbRepoLib001Dto> getPledgeBondList() {
		return pledgeBondList;
	}

	public void setPledgeBondList(List<EtrIbRepoLib001Dto> pledgeBondList) {
		this.pledgeBondList = pledgeBondList;
	}

	public List<EtrIbRepoLib001Dto> getAvaPledgeBondList() {
		return avaPledgeBondList;
	}

	public void setAvaPledgeBondList(List<EtrIbRepoLib001Dto> avaPledgeBondList) {
		this.avaPledgeBondList = avaPledgeBondList;
	}

	public String getPledgeStockId() {
		return pledgeStockId;
	}

	public void setPledgeStockId(String pledgeStockId) {
		this.pledgeStockId = pledgeStockId;
	}

	public String getPledgeStockName() {
		return pledgeStockName;
	}

	public void setPledgeStockName(String pledgeStockName) {
		this.pledgeStockName = pledgeStockName;
	}

    private String parentPortfolioId;


    public String getParentPortfolioId() {
        return parentPortfolioId;
    }

    public void setParentPortfolioId(String parentPortfolioId) {
        this.parentPortfolioId = parentPortfolioId;
    }


}
