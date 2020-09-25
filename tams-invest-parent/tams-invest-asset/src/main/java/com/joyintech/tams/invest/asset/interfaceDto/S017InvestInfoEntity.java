package com.joyintech.tams.invest.asset.interfaceDto;

public class S017InvestInfoEntity {
    private String WARN_LINE;//预警线
    private String FLAT_LINE;//平仓线
    private String BASE_RULE_INFO;//业绩基准
    private String PROFIT_DIS_TYPE;//超额分成方式

    public String getWARN_LINE() {
        return WARN_LINE;
    }

    public void setWARN_LINE(String WARN_LINE) {
        this.WARN_LINE = WARN_LINE;
    }

    public String getFLAT_LINE() {
        return FLAT_LINE;
    }

    public void setFLAT_LINE(String FLAT_LINE) {
        this.FLAT_LINE = FLAT_LINE;
    }

    public String getBASE_RULE_INFO() {
        return BASE_RULE_INFO;
    }

    public void setBASE_RULE_INFO(String BASE_RULE_INFO) {
        this.BASE_RULE_INFO = BASE_RULE_INFO;
    }

    public String getPROFIT_DIS_TYPE() {
        return PROFIT_DIS_TYPE;
    }

    public void setPROFIT_DIS_TYPE(String PROFIT_DIS_TYPE) {
        this.PROFIT_DIS_TYPE = PROFIT_DIS_TYPE;
    }
}
