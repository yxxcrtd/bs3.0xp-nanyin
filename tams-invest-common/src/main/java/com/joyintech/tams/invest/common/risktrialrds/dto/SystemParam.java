package com.joyintech.tams.invest.common.risktrialrds.dto;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;

public class SystemParam {

    private String valDate;

    private String portfolioCode;

    private String assetCode;

    private String tradeCode;

    /**
     * 资产类型
     */
    private String assetType;
    
    public String getValDate() {
        return valDate;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    public String getPortfolioCode() {
        return portfolioCode;
    }

    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
    
    public String replaceForString(String sql) {
        String ptlCode = StringUtil.contains(portfolioCode,
            GlobalConstant.OPEN_PARENTHESIS) ? portfolioCode : "'" + portfolioCode + "'";
        String astCode = StringUtil.contains(assetCode,
            GlobalConstant.OPEN_PARENTHESIS) ? assetCode : "'" + assetCode + "'";
        return sql.replaceAll("\\$\\{valDate\\}", valDate).replaceAll("\\$\\{portfolioCode\\}",
                ptlCode).replaceAll("\\$\\{assetCode\\}", astCode).replaceAll("\\$\\{tradeCode\\}",
                    tradeCode).replaceAll(
                    "\\$\\{assetType\\}", "'" +assetType+ "'");
        }
  }