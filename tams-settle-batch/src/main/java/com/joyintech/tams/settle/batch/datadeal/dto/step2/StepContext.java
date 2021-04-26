package com.joyintech.tams.settle.batch.datadeal.dto.step2;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * @author 何阳阳
 * @date 2020/8/10
 * @see StepContext
 * @since 1.0
 */
public final class StepContext {

    /**
     * 缓存
     */
    private static Cache cache = new Cache();

    /**
     * 组合代码
     */
    private Collection<String> portfolioId;

    /**
     * 执行状态
     */
    private String status;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    public static StepContext get(String secType) {
        return cache.get(secType);
    }

    public static void start(String secType) {
        if(!cache.containsKey(secType)) {
            cache.put(secType, new StepContext());
        }
    }

    public static void destroy(){
        cache.clear();
    }

    public Collection<String> getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Collection<String> portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    static class Cache extends HashMap<String, StepContext> {

    }
}
