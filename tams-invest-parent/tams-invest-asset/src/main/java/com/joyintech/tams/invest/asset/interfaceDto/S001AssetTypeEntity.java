package com.joyintech.tams.invest.asset.interfaceDto;

public class S001AssetTypeEntity {
    private  String SPECIFIC_TYPE; //   资产分类类型
    private  String  SPECIFIC_TYPE_VALUE; //资产分类具体分类值

    public String getSPECIFIC_TYPE() {
        return SPECIFIC_TYPE;
    }

    public void setSPECIFIC_TYPE(String SPECIFIC_TYPE) {
        this.SPECIFIC_TYPE = SPECIFIC_TYPE;
    }

    public String getSPECIFIC_TYPE_VALUE() {
        return SPECIFIC_TYPE_VALUE;
    }

    public void setSPECIFIC_TYPE_VALUE(String SPECIFIC_TYPE_VALUE) {
        this.SPECIFIC_TYPE_VALUE = SPECIFIC_TYPE_VALUE;
    }
}
