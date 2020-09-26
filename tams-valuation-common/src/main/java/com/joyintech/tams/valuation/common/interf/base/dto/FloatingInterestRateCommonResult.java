/*
 * 文件名：NPrdDealForInterfRecProcessorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.MstBenchmarkCompo;
import com.joyintech.tams.common.base.entity.MstBenchmarkInfo;

/**
 * 浮动利率初始化 处理返回共通结果
 * 
 * @author wangwei1
 * @version 1.0 2018年7月9日
 * @see FloatingInterestRateCommonResult
 * @since 1.0
 */
public class FloatingInterestRateCommonResult extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 基准信息
     */
    private MstBenchmarkInfo  benchmarkInfoModel = new MstBenchmarkInfo();
    /**
     * 基准成分表
     */
    private MstBenchmarkCompo  benchmarkCompoModel  = new MstBenchmarkCompo();
    
    
    
    /**
     * 获取 benchmarkInfoModel
     * @return benchmarkInfoModel.
     */
    public MstBenchmarkInfo getBenchmarkInfoModel() {
        return benchmarkInfoModel;
    }


    
    /**
     * 设置 benchmarkInfoModel
     * @param benchmarkInfoModel benchmarkInfoModel
     */
    public void setBenchmarkInfoModel(MstBenchmarkInfo benchmarkInfoModel) {
        this.benchmarkInfoModel = benchmarkInfoModel;
    }


    
    /**
     * 获取 benchmarkCompoModel
     * @return benchmarkCompoModel.
     */
    public MstBenchmarkCompo getBenchmarkCompoModel() {
        return benchmarkCompoModel;
    }


    
    /**
     * 设置 benchmarkCompoModel
     * @param benchmarkCompoModel benchmarkCompoModel
     */
    public void setBenchmarkCompoModel(MstBenchmarkCompo benchmarkCompoModel) {
        this.benchmarkCompoModel = benchmarkCompoModel;
    }


    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
}
