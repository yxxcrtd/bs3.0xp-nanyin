/*
 * 文件名：BondQueryConditionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.uplink.interbank.business.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.background.component.common.pack.common.util.StringUtil;

/**
 * 现券报价查询条件DTO 〈功能详细描述〉
 * 
 * @author caoguo
 * @version 1.0 2020年5月7日
 * @see BondQuoteSearchDto
 * @since 1.0
 */
public class BondQuoteSearchDto {

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产代码模糊匹配字符串
     */
    private String finprodFuzzy;
    
    /**
     * 资产代码列表
     */
    private List<String> finprodIdList;

    /**
     * 对手银行间机构，6位编号
     */
    private String cpPartyId;
    
    /**
     * 对手方交易员
     */
    private String cpPartyTrade;

    /**
     * 外部报价编号编号列表
     */
    private List<String> pQuoteIdList;

    /**
     * 报价状态列表
     */
    private List<String> tradeStatusList;

    /**
     * 是否全数据查询角色
     */
    private boolean isAutoDataRole;

    /**
     * 待回复数据查询方式
     */
    private String replyQueryTypeIbu;

    /**
     * 报价日期
     */
    private Date quoteDate;

    /**
     * 本方交易员ID列表
     */
    private List<String> ouTraderIdList;

    /**
     * 登录用户对应的交易员ID
     */
    private String loginTraderId;

    /**
     * 登录用户ID
     */
    private String loginUserId;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 本方角色列表
     */
    private List<String> quoteRoleTypeList;

    /**
     * CFETS报价编号列表
     */
    private List<String> cfetsQuoteIdList;

    /**
     * CFETS报价编号模糊查询条件
     */
    private String cfetsQuoteIdFuzzy;

    /**
     * 产品组合ID列表
     */
    private List<String> portfolioIdList;

    /**
     * 报价编号
     */
    private String quoteId;

    /**
     * 是否过滤做事报价:true / false
     */
    private String isZsbjFilter;

    /**
     * 排序字段
     */
    private String orderField;

    /**
     * 排序方式
     */
    private String orderDirection;

    /**
     * 关键字
     */
    private String keywords;
    
    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    /**
     * 获取 quoteRoleTypeList
     * 
     * @return quoteRoleTypeList.
     */
    public List<String> getQuoteRoleTypeList() {
        return quoteRoleTypeList;
    }

    /**
     * 设置 quoteRoleTypeList
     * 
     * @param quoteRoleTypeList quoteRoleTypeList
     */
    public void setQuoteRoleTypeList(List<String> quoteRoleTypeList) {
        this.quoteRoleTypeList = quoteRoleTypeList;
    }

    /**
     * 获取 cfetsQuoteIdList
     * 
     * @return cfetsQuoteIdList.
     */
    public List<String> getCfetsQuoteIdList() {
        return cfetsQuoteIdList;
    }

    /**
     * 设置 cfetsQuoteIdList
     * 
     * @param cfetsQuoteIdList cfetsQuoteIdList
     */
    public void setCfetsQuoteIdList(List<String> cfetsQuoteIdList) {
        this.cfetsQuoteIdList = cfetsQuoteIdList;
    }

    /**
     * 获取 ps
     * 
     * @return ps.
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ps
     * 
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodFuzzy
     * 
     * @return finprodFuzzy.
     */
    public String getFinprodFuzzy() {
        return finprodFuzzy;
    }

    /**
     * 设置 finprodFuzzy
     * 
     * @param finprodFuzzy finprodFuzzy
     */
    public void setFinprodFuzzy(String finprodFuzzy) {
        this.finprodFuzzy = finprodFuzzy;
    }

    /**
     * 获取 cpPartyId
     * 
     * @return cpPartyId.
     */
    public String getCpPartyId() {
        return cpPartyId;
    }

    /**
     * 设置 cpPartyId
     * 
     * @param cpPartyId cpPartyId
     */
    public void setCpPartyId(String cpPartyId) {
        this.cpPartyId = cpPartyId;
    }

    /**
     * 获取 pQuoteIdList
     * 
     * @return pQuoteIdList.
     */
    public List<String> getpQuoteIdList() {
        return pQuoteIdList;
    }

    /**
     * 设置 pQuoteIdList
     * 
     * @param pQuoteIdList pQuoteIdList
     */
    public void setpQuoteIdList(List<String> pQuoteIdList) {
        this.pQuoteIdList = pQuoteIdList;
    }

    /**
     * 获取 tradeStatusList
     * 
     * @return tradeStatusList.
     */
    public List<String> getTradeStatusList() {
        return tradeStatusList;
    }

    /**
     * 设置 tradeStatusList
     * 
     * @param tradeStatusList tradeStatusList
     */
    public void setTradeStatusList(List<String> tradeStatusList) {
        this.tradeStatusList = tradeStatusList;
    }

    /**
     * 获取 isAutoDataRole
     * 
     * @return isAutoDataRole.
     */
    public boolean isAutoDataRole() {
        return isAutoDataRole;
    }

    /**
     * 设置 isAutoDataRole
     * 
     * @param isAutoDataRole isAutoDataRole
     */
    public void setAutoDataRole(boolean isAutoDataRole) {
        this.isAutoDataRole = isAutoDataRole;
    }

    /**
     * 获取 replyQueryTypeIbu
     * 
     * @return replyQueryTypeIbu.
     */
    public String getReplyQueryTypeIbu() {
        return replyQueryTypeIbu;
    }

    /**
     * 设置 replyQueryTypeIbu
     * 
     * @param replyQueryTypeIbu replyQueryTypeIbu
     */
    public void setReplyQueryTypeIbu(String replyQueryTypeIbu) {
        this.replyQueryTypeIbu = replyQueryTypeIbu;
    }

    /**
     * 获取 quoteDate
     * 
     * @return quoteDate.
     */
    public Date getQuoteDate() {
        return quoteDate;
    }

    /**
     * 设置 quoteDate
     * 
     * @param quoteDate quoteDate
     */
    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    /**
     * 获取 ouTraderIdList
     * 
     * @return ouTraderIdList.
     */
    public List<String> getOuTraderIdList() {
        return ouTraderIdList;
    }

    /**
     * 设置 ouTraderIdList
     * 
     * @param ouTraderIdList ouTraderIdList
     */
    public void setOuTraderIdList(List<String> ouTraderIdList) {
        this.ouTraderIdList = ouTraderIdList;
    }

    /**
     * 获取 loginTraderId
     * 
     * @return loginTraderId.
     */
    public String getLoginTraderId() {
        return loginTraderId;
    }

    /**
     * 设置 loginTraderId
     * 
     * @param loginTraderId loginTraderId
     */
    public void setLoginTraderId(String loginTraderId) {
        this.loginTraderId = loginTraderId;
    }

    /**
     * 获取 loginUserId
     * 
     * @return loginUserId.
     */
    public String getLoginUserId() {
        return loginUserId;
    }

    /**
     * 设置 loginUserId
     * 
     * @param loginUserId loginUserId
     */
    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    /**
     * 获取 entrustId
     * 
     * @return entrustId.
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 entrustId
     * 
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 返回本方交易员ID，用逗号隔开的字符串且以逗号结尾
     */
    public String getOuTraderIds() {
        String ouTraderIds = StringUtil.join(this.ouTraderIdList, ",");
        if (ouTraderIds == null) {
            return ouTraderIds;
        }
        return ouTraderIds + ",";
    }

    /**
     * 获取 cfetsQuoteIdFuzzy
     * 
     * @return cfetsQuoteIdFuzzy.
     */
    public String getCfetsQuoteIdFuzzy() {
        return cfetsQuoteIdFuzzy;
    }

    /**
     * 设置 cfetsQuoteIdFuzzy
     * 
     * @param cfetsQuoteIdFuzzy cfetsQuoteIdFuzzy
     */
    public void setCfetsQuoteIdFuzzy(String cfetsQuoteIdFuzzy) {
        this.cfetsQuoteIdFuzzy = cfetsQuoteIdFuzzy;
    }

    /**
     * 获取 portfolioIdList
     * 
     * @return portfolioIdList.
     */
    public List<String> getPortfolioIdList() {
        return portfolioIdList;
    }

    /**
     * 设置 portfolioIdList
     * 
     * @param portfolioIdList portfolioIdList
     */
    public void setPortfolioIdList(List<String> portfolioIdList) {
        this.portfolioIdList = portfolioIdList;
    }

    /**
     * 获取 isZsbjFilter
     * 
     * @return isZsbjFilter.
     */
    public String getIsZsbjFilter() {
        return isZsbjFilter;
    }

    /**
     * 设置 isZsbjFilter
     * 
     * @param isZsbjFilter isZsbjFilter
     */
    public void setIsZsbjFilter(String isZsbjFilter) {
        this.isZsbjFilter = isZsbjFilter;
    }

    /**
     * 获取 orderField
     * 
     * @return orderField.
     */
    public String getOrderField() {
        return orderField;
    }

    /**
     * 设置 orderField
     * 
     * @param orderField orderField
     */
    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    /**
     * 获取 orderDirection
     * 
     * @return orderDirection.
     */
    public String getOrderDirection() {
        return orderDirection;
    }

    /**
     * 设置 orderDirection
     * 
     * @param orderDirection orderDirection
     */
    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }
    
    /**
     * 是否查询历史表
     */
    private Boolean isSearchHis=false;
    
    public Boolean getIsSearchHis() {
		return isSearchHis;
	}
    
	public void setIsSearchHis(Boolean isSearchHis) {
		this.isSearchHis = isSearchHis;
	}
	
	public String getTableStuff(){
    	if (isSearchHis){
    		return "_HIS";
    	}else{
    		return "";
    	}
    }
	
	/**
	 * 版本号
	 */
	private String hisVersion;
	
	public String getHisVersion() {
		return hisVersion;
	}
	
	public void setHisVersion(String hisVersion) {
		this.hisVersion = hisVersion;
	}
	
	/**
	 * 开始日
	 */
	private String startQuoteDate;
	
	/**
	 * 结束日
	 */
	private String endQuoteDate;

	public String getStartQuoteDate() {
		return startQuoteDate;
	}

	public void setStartQuoteDate(String startQuoteDate) {
		this.startQuoteDate = startQuoteDate;
	}

	public String getEndQuoteDate() {
		return endQuoteDate;
	}

	public void setEndQuoteDate(String endQuoteDate) {
		this.endQuoteDate = endQuoteDate;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCpPartyTrade() {
		return cpPartyTrade;
	}

	public void setCpPartyTrade(String cpPartyTrade) {
		this.cpPartyTrade = cpPartyTrade;
	}

	public List<String> getFinprodIdList() {
		return finprodIdList;
	}

	public void setFinprodIdList(List<String> finprodIdList) {
		this.finprodIdList = finprodIdList;
	}

}
