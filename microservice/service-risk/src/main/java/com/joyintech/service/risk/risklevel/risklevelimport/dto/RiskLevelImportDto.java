package com.joyintech.service.risk.risklevel.risklevelimport.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;

public class RiskLevelImportDto extends BaseEntity implements ProcessEntity {

    //资产类型
    private String assetsType;

    //资产代码
    private  String finprodMarketId;


    //资产名称
    private  String finprodName;

    //风险五级分类
    private  String bondRiskType;

     //更新时间
    private Date updateTime;

    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public String getBondRiskType() {
        return bondRiskType;
    }

    public void setBondRiskType(String bondRiskType) {
        this.bondRiskType = bondRiskType;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
