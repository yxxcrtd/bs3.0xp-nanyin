package com.joyintech.tams.model.impairment.batch.pdcalculate.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * @Description:减值配置使用情况Dto
 * @auther: yuzj
 * @date: 2021/7/15
 * @param: 
 * @return: 
 */
public class ModConfigUseageResultDto extends BaseEntity {


    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 配置流水号
     */
    private String configSeqNo;

    /**
     * 配置类型（数据字典-配置类型_模型）
     */
    private String configType;

    /**
     * 使用过程（数据字典-减值批处理过程_模型）
     */
    private String useProcess;


    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getConfigSeqNo() {
        return configSeqNo;
    }

    public void setConfigSeqNo(String configSeqNo) {
        this.configSeqNo = configSeqNo;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getUseProcess() {
        return useProcess;
    }

    public void setUseProcess(String useProcess) {
        this.useProcess = useProcess;
    }
}
