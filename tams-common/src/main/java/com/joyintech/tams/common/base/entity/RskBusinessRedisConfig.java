package com.joyintech.tams.common.base.entity;

public class RskBusinessRedisConfig extends RskBusinessRedisConfigKey {
    /**
     * null
     */
    private String redisModel;

    /**
     * 取得 null
     * @return null
     */
    public String getRedisModel() {
        return redisModel;
    }

    /**
     * 设置 null
     * @param redisModel null
     */
    public void setRedisModel(String redisModel) {
        this.redisModel = redisModel == null ? null : redisModel.trim();
    }
}