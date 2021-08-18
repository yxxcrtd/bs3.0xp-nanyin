package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto;

/**
 * 减值阶段查询的参数dto
 */
public class ModStageParaDto {
    private String item;
    private String itemValue;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
