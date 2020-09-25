package com.joyintech.tams.common.base.entity;

public class PrdResearchSchemeWithBLOBs extends PrdResearchScheme {
    /**
     * 产品要素设计结果
     */
    private String prdDesignResult;

    /**
     * 产品压力测试结果
     */
    private String prdLoadResult;

    /**
     * 产品模拟测算结果
     */
    private String prdMockResult;

    /**
     * 取得 产品要素设计结果
     * @return 产品要素设计结果
     */
    public String getPrdDesignResult() {
        return prdDesignResult;
    }

    /**
     * 设置 产品要素设计结果
     * @param prdDesignResult 产品要素设计结果
     */
    public void setPrdDesignResult(String prdDesignResult) {
        this.prdDesignResult = prdDesignResult == null ? null : prdDesignResult.trim();
    }

    /**
     * 取得 产品压力测试结果
     * @return 产品压力测试结果
     */
    public String getPrdLoadResult() {
        return prdLoadResult;
    }

    /**
     * 设置 产品压力测试结果
     * @param prdLoadResult 产品压力测试结果
     */
    public void setPrdLoadResult(String prdLoadResult) {
        this.prdLoadResult = prdLoadResult == null ? null : prdLoadResult.trim();
    }

    /**
     * 取得 产品模拟测算结果
     * @return 产品模拟测算结果
     */
    public String getPrdMockResult() {
        return prdMockResult;
    }

    /**
     * 设置 产品模拟测算结果
     * @param prdMockResult 产品模拟测算结果
     */
    public void setPrdMockResult(String prdMockResult) {
        this.prdMockResult = prdMockResult == null ? null : prdMockResult.trim();
    }
}