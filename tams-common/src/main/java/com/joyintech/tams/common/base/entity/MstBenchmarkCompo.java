package com.joyintech.tams.common.base.entity;

public class MstBenchmarkCompo extends MstBenchmarkCompoKey {
    /**
     * 权重
     */
    private String weights;

    /**
     * 取得 权重
     * @return 权重
     */
    public String getWeights() {
        return weights;
    }

    /**
     * 设置 权重
     * @param weights 权重
     */
    public void setWeights(String weights) {
        this.weights = weights == null ? null : weights.trim();
    }
}