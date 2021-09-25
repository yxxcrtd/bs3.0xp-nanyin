/*
 * 文件名：SecPosAutoGenerationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.risk.service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.autogeneration.risk.dao.RiskComRedFeeExpCondGenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 强制赎回费征收条件落地批处理Service
 *
 * @author yflv
 * @version 1.0 2021年9月17日
 * @see RiskComRedFeeExpCondGenService
 * @since 1.0
 */
@Service
public class RiskComRedFeeExpCondGenService {
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * RiskComRedFeeExpCondGenMapper
     */
    @Autowired
    private RiskComRedFeeExpCondGenMapper riskComRedFeeExpCondGenMapper;
    
    /**
     * 系统参数【强制赎回费征收条件关联指标ID】
     */
    private final static String COM_RED_IND_ID = "COM_RED_FEE_EXP_COND_IND_ID";

    /**
     * 强制赎回费征收条件落地数据自动生成
     *
     * @param batchDate 执行日
     * @see
     * @since 1.0
     */
    public void autoGeneration(Date batchDate) {
    	String indID = CacheUtil.getSysParam(COM_RED_IND_ID);
    	if(StringUtil.isEmpty(indID)) {
    		logger.error("系统参数{}:值为空",COM_RED_IND_ID);
    		return ;
    	}
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("batchDate", batchDate);
        paramMap.put("indID",indID);
        // 删除当日数据
        riskComRedFeeExpCondGenMapper.deleteTodayRiskComRedFeeExpCondGen(paramMap);
        // 插入当日数据
        riskComRedFeeExpCondGenMapper.insertTodayRiskComRedFeeExpCondGen(paramMap);
    }


}
