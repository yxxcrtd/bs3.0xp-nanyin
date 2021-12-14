package com.joyintech.tams.cfets.etrib.repo.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.util.ArrayList;
import java.util.List;

public class CfetsEtrIbRepoLibDto extends CfetsEtrIbRepoLib001Dto {

    /**
     * 
     */
    private static final long serialVersionUID = 934652225044201805L;
    
    /**
     * pageModel
     */
    private String pageModel; 
    
    private String tradePlace;

    public String getSecAcctId() {
        return super.getSecAcctId();
    }
    
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    
    /**
     * 质押券列表信息
     */
    List<CfetsEtrIbRepoLibDto> pledgeBondList = new ArrayList<>();

    /**
     * 可质押券列表信息
     */
    List<CfetsEtrIbRepoLibDto> avaPledgeBondList;
    
    private String tradeId;
    
    private String finProdAbbr;
    
    private String pledgeStockId;
    
    private String pledgeStockName;
    
    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public String getTradePlace() {
		return tradePlace;
	}

	public void setTradePlace(String tradePlace) {
		this.tradePlace = tradePlace;
	}

	public String getFinProdAbbr() {
		return finProdAbbr;
	}

	public void setFinProdAbbr(String finProdAbbr) {
		this.finProdAbbr = finProdAbbr;
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
    
    public String getRegistOrgStr() {
        if (StringUtil.isEmpty(getRegistOrg())) {
            return null;
        }
        return SysDataUtil.getNameFromDataDict(DictCode.登记托管机构_中债, this.getRegistOrg());
    }

    public String getGradeResultStr() {
        if (StringUtil.isEmpty(getGradeResult())) {
            return null;
        }
        return SysDataUtil.getNameFromDataDict(DictCode.评级结果, this.getGradeResult());
    }
    private String parentPortfolioId;


    public String getParentPortfolioId() {
        return parentPortfolioId;
    }

    public void setParentPortfolioId(String parentPortfolioId) {
        this.parentPortfolioId = parentPortfolioId;
    }

	@Override
	public void setWorkflowId(String workflowId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		
	}

}
