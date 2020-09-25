/*
 * 文件名：FinProductFloatFeeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.dao.FinFloatCalcRuleMapper;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleKey;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.product.common.dao.FinProductFloatFeeMapper;
import com.joyintech.service.product.common.dto.FinProductFloatFeeDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月19日
 * @see FinProductFloatFeeService
 * @since 1.0
 */
@Service("backFinProductFloatFeeService")
public class FinProductFloatFeeService {
    
    @Autowired(required=false)
    private FinProductFloatFeeMapper finProductFloatFeeMapper;
    /**
     * 现金流信息
     */
    @Autowired(required=false)
    private FinCashInfoMapper finCashInfoMapper;
    /**
     * 业绩报酬计算规则
     */
    @Autowired(required=false)
    private FinFloatCalcRuleMapper finFloatCalcRuleMapper;
    /**
     * 浮动管理删除
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public int delete(String cashId) {
        if(cashId != null){
            finCashInfoMapper.deleteByPrimaryKey(cashId);
            finProductFloatFeeMapper.delete(cashId);
        }
        return 0;
    }
    /**
     * 报价管理时浮动管理删除
     * <功能详细描述>
     * @param key
     * @return
     * @see
     * @since 1.0
     */
    public int deleteByPrimaryKey(FinFloatCalcRuleKey key) {
        if(key != null){
            finCashInfoMapper.deleteByPrimaryKey(key.getCashId());
            finFloatCalcRuleMapper.deleteByPrimaryKey(key);
        }
        return 1;
    }
    
    /**
     * 查询浮动管理费信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 浮动管理费信息
     */
    public List<FinFloatCalcRuleDetail> queryFloatFeeDetailByFinprodId(String finprodId, String effDate){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("effDate", effDate);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        //是否来自浮动管理费控件
        map.put("dealMode10", DictCodeList.处理模式_周期型.getValue());
        map.put("termType02", DictCodeListExt2.期限类型_多固定期限.getValue());

        return finProductFloatFeeMapper.queryFloatFeeDetailByFinprodId(map);
    }
    
    /**
     * 查询浮动管理费信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 浮动管理费信息
     */
    public FinProductFloatFeeDto queryFloatFeeInfoByFinprodId(String finprodId, String effDate){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("effDate", effDate);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        map.put("dealMode10", DictCodeList.处理模式_周期型.getValue());
        map.put("termType02", DictCodeListExt2.期限类型_多固定期限.getValue());
        return finProductFloatFeeMapper.queryFloatFeeInfoByFinprodId(map);
    }
    
    /**
     * 查询浮动管理费信息
     * @param finprodId 金融产品代码
     * @param effDate 生效日期
     * @return 浮动管理费信息
     */
    public List<FinProductFloatFeeDto> queryLayerFloatFeeInfoByFinprodId(String finprodId, String effDate){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("effDate", effDate);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        map.put("baseRuleType", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
        List<FinProductFloatFeeDto> finProductFloatFeeDtos = finProductFloatFeeMapper.queryLayerFloatFeeInfoByFinprodId(map);
        for(FinProductFloatFeeDto finProductFloatFeeDto : finProductFloatFeeDtos){
            map.put("finprodId", finProductFloatFeeDto.getFinprodid());
            map.put("effDate", DateUtil.format(finProductFloatFeeDto.getVdate()));
            map.put("termType02", DictCodeListExt2.期限类型_多固定期限.getValue());

            finProductFloatFeeDto.setFinFloatCalcRuleDetailDtoList(finProductFloatFeeMapper.queryFloatFeeDetailByFinprodId(map));
        }
        return finProductFloatFeeDtos;
    }
    
    /**
     * 根据金融产品代码查询浮动管理费现金代码
     * @param map 金融产品代码
     * @return 浮动管理费现金代码
     */
    public String queryFloatCashIdByFinprodId(String finprodId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        return finProductFloatFeeMapper.queryFloatCashIdByFinprodId(map);
    }
    
}
