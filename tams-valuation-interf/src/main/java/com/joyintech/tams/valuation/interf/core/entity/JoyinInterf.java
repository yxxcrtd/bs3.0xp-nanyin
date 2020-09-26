/*
 * 文件名：JoyinInterf.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.core.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.dto.InterfNeedOperateEntity;
import com.joyintech.tams.valuation.interf.core.InterfBaseProcessor;
import com.joyintech.tams.valuation.interf.core.InterfBaseReader;
import com.joyintech.tams.valuation.interf.core.InterfBaseWriter;

public class JoyinInterf {
    /**
     * step名称
     */
    private String stepName;

    /**
     * 每一个step的Reader
     */
    private InterfBaseReader<? extends InterfNeedOperateEntity> reader;

    /**
     * 每一个step的processor
     */
    private InterfBaseProcessor<InterfNeedOperateEntity, ? extends BaseEntity> processor;

    /**
     * 每一个step的writer
     */
    private InterfBaseWriter<BaseEntity> writer;

    
    public String getStepName() {
        return stepName;
    }

    
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    
    public InterfBaseReader<? extends InterfNeedOperateEntity> getReader() {
        return reader;
    }

    
    public void setReader(InterfBaseReader<? extends InterfNeedOperateEntity> reader) {
        this.reader = reader;
    }

    
    public InterfBaseProcessor<InterfNeedOperateEntity, ? extends BaseEntity> getProcessor() {
        return processor;
    }

    
    public void setProcessor(InterfBaseProcessor<InterfNeedOperateEntity, ? extends BaseEntity> processor) {
        this.processor = processor;
    }

    
    public InterfBaseWriter<BaseEntity> getWriter() {
        return writer;
    }

    
    public void setWriter(InterfBaseWriter<BaseEntity> writer) {
        this.writer = writer;
    }

    public JoyinInterf(String stepName, InterfBaseReader<? extends InterfNeedOperateEntity> reader,
                       InterfBaseProcessor<InterfNeedOperateEntity, ? extends BaseEntity> processor,
                       InterfBaseWriter<BaseEntity> writer) {
        this.stepName = stepName;
        this.reader = reader;
        this.processor = processor;
        this.writer = writer;
    }
}
