/*
 * 文件名：BussRiskMathCommService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.common.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RskIsupTrialClsMapper;
import com.joyintech.tams.common.base.dao.RskIsupTrialMapper;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.RskIsupTrial;
import com.joyintech.tams.common.base.entity.RskIsupTrialCls;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

/**
 * 风险试算
 * @author fanshuanghu
 * @version 1.0 2018年12月5日
 * @see BasRiskMathCommService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class BasRiskMathCommService extends BaseService{
    @Autowired
    private RskIsupTrialMapper rskIsupTrialMapper;
    @Autowired
    private RskIsupTrialClsMapper rskIsupTrialClsMapper;

    /**
     * 插入数据库
     * @param param 实体类
     * @return 是否0：1
     * @see
     * @since 1.0
     */

    public int insert(List<RiskTrialRetDto> param,String tradeId) {
        int i = 0;
        if(param!=null && param.size()>0 && StringUtil.isNotEmpty(tradeId)){
            //---zxjjpei 添加uuid----------
            String uuid=String.valueOf(System.currentTimeMillis());
            //风险试算列表数据插入到数据库
            for(int o=0; o<param.size();o++){
                RiskTrialRetDto riskTrialRetDto=param.get(o);
                RskIsupTrial rskIsupTrial = new RskIsupTrial();
                BeanUtil.copyPropertiesIgnoreNull(rskIsupTrial, riskTrialRetDto);
                //---zxjjpei 添加SeqNo及UUID----------
                rskIsupTrial.setTradeId(tradeId);
                rskIsupTrial.setSeqNo(uuid);
                rskIsupTrial.setTradeType(BussinessDictCodeListRds.事件类型_投资委托.getValue());
                rskIsupTrial.setUuid(IDGenerator.generate());
                rskIsupTrial.setPortfolioId(riskTrialRetDto.getPortfolioCode());
                rskIsupTrial.setIndId(riskTrialRetDto.getKriCode());
                rskIsupTrial.setSupStat(riskTrialRetDto.getKriStatus());
                rskIsupTrial.setUniteSupStat(riskTrialRetDto.getUniteStatus());
                rskIsupTrial.setSupDate(DateUtil.parse(DateUtil.getSysDate()));
                rskIsupTrial.setSupVal(riskTrialRetDto.getKriValue());
                rskIsupTrial.setOldSupVal(riskTrialRetDto.getOldValue());
                rskIsupTrial.setSupTrend(riskTrialRetDto.getKriTrend());
                rskIsupTrial.setWarnCond(riskTrialRetDto.getWarningLine());
                rskIsupTrial.setApproveCond(riskTrialRetDto.getApproveLine());
                rskIsupTrial.setFoulCond(riskTrialRetDto.getForbiddenLn());
                i = rskIsupTrialMapper.insertSelective(rskIsupTrial);
            }
            //插入风险试算结论到数据库
            RskIsupTrialCls rskIsupTrialCls = new RskIsupTrialCls();
            //js中将风险试算结论存入隐藏的第一行数据中
            rskIsupTrialCls.setConclusion(param.get(0).getTotalConclu());
            rskIsupTrialCls.setTradeId(tradeId);
            //---zxjjpei---添加SeqNo----
            rskIsupTrialCls.setSeqNo(uuid);
            rskIsupTrialCls.setTradeType(BussinessDictCodeListRds.事件类型_投资委托.getValue());
            i = rskIsupTrialClsMapper.insertSelective(rskIsupTrialCls);
        }
        return i;
    }
    /**
     * 获取单位净值
     * @param map 请求参数
     * @return 金融产品估值信息
     * @see
     * @since 1.0
     */
    public FinProductValuation getValue(Map<String,Object> map){
        return restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME + "/RiskSelect/getValue",
            map,FinProductValuation.class);
    }
    
    /**
     * 初始化查询
     * @param tradeId 请求参数组合名称
     * @return 结果实体类
     * @see
     * @since 1.0
     */
    public List<RiskTrialRetDto> select(String tradeId) {
        List<RiskTrialRetDto> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("tradeId", tradeId);
        list =  restTemplate.postForList(ServiceApplication.COMMON_SERVICE_NAME + "/RiskSelect/select",
            map,RiskTrialRetDto.class);
        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<RiskTrialRetDto> getBusinessEntityByWorkFlowId(String workflowId, String beanName) {
        Map<String,Object> map= new HashMap<>();
        map.put("workflowId", workflowId);
        map.put("beanName", beanName);
        // 获取业务entity
        String temp = restTemplate.postForObject(
            ServiceApplication.COMMON_SERVICE_NAME + "/core/getBusinessEntityByWorkFlowId", map,
            String.class);
        if(temp != null) {
            Map<String,Object> all = JsonUtils.json2Object(temp,Map.class);
            try {
                if (all.get(beanName) != null){
                    return (List<RiskTrialRetDto>)all.get(beanName);
                }else if(StringUtil.isNotEmpty(all.get("tradeId"))){
                    return select(all.get("tradeId").toString());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            } 
        }
        return null;
    }

    public PtlPortfolio getPtlPortfolioInfo(String ptlId){
        Map<String,Object> map = new HashMap<>();
        map.put("ptlId",ptlId);
        return restTemplate.postForObject(ServiceApplication.COMMON_SERVICE_NAME + "/RiskSelect/getPtlPortfolioInfo",
                map,PtlPortfolio.class);
    }
}
