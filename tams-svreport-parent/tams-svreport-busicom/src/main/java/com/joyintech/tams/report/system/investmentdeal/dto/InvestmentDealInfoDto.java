package com.joyintech.tams.report.system.investmentdeal.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbInvestTrade;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.system.investmentdeal.validator.InvestmentDealValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@TableName("chb_invest_trade")
@PageId("InvestmentDealInfo002")
@ValidLogic(targetClass = InvestmentDealValidator.class, groups = { ValidateGroupEdit.class,
        ValidateGroupDelete.class })
public class InvestmentDealInfoDto extends ChbInvestTrade implements ITableRow, ProcessEntity {

    private List<InvestmentDealInfoDto> selectedItem;
    
    private String statusName;
    
    private String ccyStr;
    
    private String assetMethodStr;
    
    private String curAssetTypeStr;

    private String rivalPartyTypeStr;

    private String dataSourceName;

    /**
     * 导出类型
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<InvestmentDealInfoDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<InvestmentDealInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCcyStr() {
        return ccyStr;
    }

    public void setCcyStr(String ccyStr) {
        this.ccyStr = ccyStr;
    }

    public String getAssetMethodStr() {
        return assetMethodStr;
    }

    public void setAssetMethodStr(String assetMethodStr) {
        this.assetMethodStr = assetMethodStr;
    }

    public String getCurAssetTypeStr() {
        return curAssetTypeStr;
    }

    public void setCurAssetTypeStr(String curAssetTypeStr) {
        this.curAssetTypeStr = curAssetTypeStr;
    }

    public String getRivalPartyTypeStr() {
        return rivalPartyTypeStr;
    }

    public void setRivalPartyTypeStr(String rivalPartyTypeStr) {
        this.rivalPartyTypeStr = rivalPartyTypeStr;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
}
