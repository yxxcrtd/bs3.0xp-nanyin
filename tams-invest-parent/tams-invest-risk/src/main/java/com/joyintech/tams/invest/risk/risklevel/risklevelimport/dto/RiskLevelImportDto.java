package com.joyintech.tams.invest.risk.risklevel.risklevelimport.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

public class RiskLevelImportDto extends BaseEntity implements ProcessEntity {

    //资产类型
    private String assetsType;

    private String assetsTypeStr;

    //资产代码
    private  String finprodMarketId;


    //资产名称
    private  String finprodName;

    //风险五级分类
    private  String bondRiskType;

    private  String bondRiskTypeStr;

     //更新时间
    private Date updateTime;

    private  String updateTimeStr;
    /**
     * 选中的数据
     */
    private transient List<RiskLevelImportDto> selectedItem;

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

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getAssetsTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用,this.getAssetsType());
    }

    public void setAssetsTypeStr(String assetsTypeStr) {
        this.assetsTypeStr = assetsTypeStr;
    }

    public String getBondRiskTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.风险五级分类,this.getBondRiskType());
    }

    public void setBondRiskTypeStr(String bondRiskTypeStr) {
        this.bondRiskTypeStr = bondRiskTypeStr;
    }

    public List<RiskLevelImportDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RiskLevelImportDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}
