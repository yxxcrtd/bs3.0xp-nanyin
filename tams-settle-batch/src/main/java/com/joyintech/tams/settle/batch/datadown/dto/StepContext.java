package com.joyintech.tams.settle.batch.datadown.dto;

import com.joyintech.tams.common.dbf.DBFRow;

import java.util.*;

/**
 * @author 何阳阳
 * @date 2020/7/9
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
     * 任务ID
     */
    private String taskId;

    /**
     * 导入日期
     */
    private Date importDate;

    /**
     * 日志信息
     */
    private Map<String, LogDto> logDtoMap;

    private StepContext() {
        this.logDtoMap = new HashMap<>();
    }

    public static StepContext get(String stepName) {
        if(!cache.containsKey(stepName)) {
            cache.put(stepName, new StepContext());
        }
        return cache.get(stepName);
    }

    public static StepContext remove(String stepName){
        if(cache.containsKey(stepName)) {
            return cache.remove(stepName);
        }
        return null;
    }

    public void errorIncrement(String dataFile) {
        if(logDtoMap.containsKey(dataFile)) {
            logDtoMap.get(dataFile).incrementErrorCount();
        }
    }

    public void warningIncrement(String dataFile) {
        if(logDtoMap.containsKey(dataFile)) {
            logDtoMap.get(dataFile).incrementWarningCount();
        }
    }

    public Collection<String> getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Collection<String> portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Map<String, LogDto> getLogDtoMap() {
        return logDtoMap;
    }

    public void initLogDto(String dataFile) {
        this.logDtoMap.put(dataFile, new LogDto(dataFile));
    }

    public void setData(String dataFile, List<DBFRow> data) {
        if(logDtoMap.containsKey(dataFile)) {
            logDtoMap.get(dataFile).setData(data);
        }
    }

    static class Cache extends HashMap<String, StepContext> {

    }
}
