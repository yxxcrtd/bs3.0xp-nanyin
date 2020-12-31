/*
 * 文件名：RiskSelectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.common.risk.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.service.common.risk.dao.RiskSelectMapper;
import com.joyintech.service.common.risk.dto.RiskSelectDto;

/**
 * 获取风险试算的结果
 * @author fanshuanghu
 * @version 1.0 2019年1月17日
 * @see RiskSelectService
 * @since 1.0
 */
@Service
public class RiskSelectService extends BaseService{
    
    @Autowired
    private RiskSelectMapper riskSelectMapper;

    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;
    /**
     * 风险结果查询
     * @param param 查询的交易编号
     * @return 风险试算结果
     */
    public List<RiskSelectDto> select(Map<String, Object> param) {
        return riskSelectMapper.select(param);
    }
    /**
     * 获取单位净值
     * @param map 请求参数
     * @return 金融产品估值信息
     * @see
     * @since 1.0
     */
    public FinProductValuation getValue(Map<String,Object> map){
        return riskSelectMapper.getValue(map);
    }

    public PtlPortfolio getPtlPortfolioInfo(Map<String,Object> map){
        return ptlPortfolioMapper.selectByPrimaryKey(map.get("ptlId").toString());
    }

    public List<Map<String,Object>> getRiskInfoByPortfolioId(Map<String,Object> map){
        return ptlPortfolioMapper.getRiskInfoByPortfolioId(map);
    }
}
