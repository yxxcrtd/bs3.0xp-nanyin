package com.joyintech.tams.model.impairment.batch.eclcalculation.dto;

import com.joyintech.framework.base.BaseEntity;

public class ModConfigUseage extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 配置流水号
     */
    private String configSeqno;

    /**
     * 配置类型（数据字典-配置类型_模型）
     */
    private String configType;

    /**
     * 使用过程（数据字典-减值批处理过程_模型）
     */
    private String useProcess;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 配置流水号
     * @return 配置流水号
     */
    public String getConfigSeqno() {
        return configSeqno;
    }

    /**
     * 设置 配置流水号
     * @param configSeqno 配置流水号
     */
    public void setConfigSeqno(String configSeqno) {
        this.configSeqno = configSeqno == null ? null : configSeqno.trim();
    }

    /**
     * 取得 配置类型（数据字典-配置类型_模型）
     * @return 配置类型（数据字典-配置类型_模型）
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * 设置 配置类型（数据字典-配置类型_模型）
     * @param configType 配置类型（数据字典-配置类型_模型）
     */
    public void setConfigType(String configType) {
        this.configType = configType == null ? null : configType.trim();
    }

    /**
     * 取得 使用过程（数据字典-减值批处理过程_模型）
     * @return 使用过程（数据字典-减值批处理过程_模型）
     */
    public String getUseProcess() {
        return useProcess;
    }

    /**
     * 设置 使用过程（数据字典-减值批处理过程_模型）
     * @param useProcess 使用过程（数据字典-减值批处理过程_模型）
     */
    public void setUseProcess(String useProcess) {
        this.useProcess = useProcess == null ? null : useProcess.trim();
    }
}