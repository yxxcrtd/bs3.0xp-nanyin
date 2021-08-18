package com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto;


/**
 * 贴现法曲线配置
 * @author zznn
 * @date 2021/7/6
 */
public class ModCurveDto extends ModCurveConfig {

    /**
     * 资产一级分类
     */
    private String assetOne;

    /**
     * 资产二级分类
     */
    private String assetTwo;
    /**
     * 资产三级分类
     */
    private String assetThree;
    /**
     * 资产四级分类
     */
    private String assetFour;
    /**
     * 资产五级分类
     */
    private String assetFive;

    public String getAssetOne() {
        return assetOne;
    }

    public void setAssetOne(String assetOne) {
        this.assetOne = assetOne;
    }

    public String getAssetTwo() {
        return assetTwo;
    }

    public void setAssetTwo(String assetTwo) {
        this.assetTwo = assetTwo;
    }

    public String getAssetThree() {
        return assetThree;
    }

    public void setAssetThree(String assetThree) {
        this.assetThree = assetThree;
    }

    public String getAssetFour() {
        return assetFour;
    }

    public void setAssetFour(String assetFour) {
        this.assetFour = assetFour;
    }

    public String getAssetFive() {
        return assetFive;
    }

    public void setAssetFive(String assetFive) {
        this.assetFive = assetFive;
    }
}

