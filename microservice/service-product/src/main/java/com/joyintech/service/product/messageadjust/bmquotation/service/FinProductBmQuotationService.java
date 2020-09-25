/*
 * 文件名：FinProductBmQuotationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.bmquotation.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.messageadjust.bmquotation.dao.FinProductBmQuotationMapper;
import com.joyintech.service.product.messageadjust.bmquotation.dto.BenchmarkRate;
import com.joyintech.service.product.messageadjust.bmquotation.dto.FinProductBmQuotationDto;
import com.joyintech.tams.common.base.dao.FinBaseRuleInfoMapper;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfoKey;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品业绩基准报价管理
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月11日
 * @see FinProductBmQuotationService
 * @since 1.0
 */
@Service("backFinProductBmQuotationService")
public class FinProductBmQuotationService extends BaseService<FinProductBmQuotationDto> {
    @Autowired
    private FinProductBmQuotationMapper finProductBmQuotationMapper;
    @Autowired
    private FinProductBmQuotationService finProductBmQuotationService;
    @Autowired
    private FinProductMapper finProductMapper;
    @Autowired
    private FinProductAddMapper finProductAddMapper;
    /**
     * 业绩基准mapper
     */
    @Autowired
    private FinBaseRuleInfoMapper finBaseRuleInfoMapper;
    
    public List<SelectOptionDto> getFinprodMarketIdList(Map<String, Object> param){
        if("null".equals(param.get("finprodMarketId"))){
            param.replace("finprodMarketId", null);
        }
        param.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        param.put("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());
        return finProductBmQuotationMapper.getFinprodMarketIdList(param);
    }
    
    /**
     * 产品业绩基准报价管理-查询
     * @param param
     * @return
     */
    public Page<FinProductBmQuotationDto> selectForPage(int page, int length,Map<String, Object> pageParam)
        throws Exception {
            return super.queryForPage(page, length, () -> finProductBmQuotationService.select(pageParam));
    }
    
    /**
     * 产品业绩基准报价管理-查询
     * @param param
     * @return
     */
    public List<FinProductBmQuotationDto> selectForList(Map<String, Object> pageParam)
        throws Exception {
        String keys = (String)pageParam.get("keys");
        pageParam.put("keys", StringUtil.splitToList(GlobalConstant.COMMA, keys));
        return finProductBmQuotationService.select(pageParam);
    }

    /**
     * 
     * 处理利率
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    private List<FinProductBmQuotationDto> select(Map<String, Object> pageParam) {
        pageParam.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        pageParam.put("FINPROD_TYPE_TG_F24", DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        pageParam.put("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        pageParam.put("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());
        pageParam.put("sDate", pageParam.get("sDate"));
        pageParam.put("eDate", pageParam.get("eDate"));
        String finprodMarketId = (String)pageParam.get("finprodMarketId");
        
        pageParam.put("yesOrNo_Yes", DictCodeList.是否_是.getValue());
        pageParam.put("DISPOSE_PATTERN_10", DictCodeList.处理模式_周期型.getValue());
        pageParam.put("FINPROD_TYPE_TG_F26", DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue());
        pageParam.put("BASE_RULE_TYPE_01", DictCodeListExt1.业绩基准类型_产品披露.getValue());
   
        //产品款型
        String prodStyle = (String)pageParam.get("prodStyle");
        if(StringUtil.isNotEmpty(finprodMarketId)){
            pageParam.put("finprodMarketId", StringUtil.splitToList(GlobalConstant.COMMA,finprodMarketId));
        }else{
            pageParam.put("finprodMarketId",null);
        }
        if(StringUtil.isNotEmpty(prodStyle)){
            pageParam.put("prodStyle", StringUtil.splitToList(GlobalConstant.COMMA,prodStyle));
        }else{
            pageParam.put("prodStyle",null);
        }
        List<FinProductBmQuotationDto> dtoList = finProductBmQuotationMapper.selectForPage(pageParam);
        if(dtoList != null && dtoList.size()>0){
            for(FinProductBmQuotationDto dto:dtoList){
                BigDecimal realIntRate = BigDecimal.valueOf(0);
                BigDecimal intRate = BigDecimal.valueOf(0);
                if(dto.getRate() == null){
                    
                  //基准来源 = 利率行情时
                    //从利率行情表中取得基准利率
                    if(DictCodeListExt1.基准类型_2_利率.getValue().equals(dto.getIndexSource())){
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("indexId", dto.getIndexId());
                        map.put("effDate", DateUtil.format(dto.getEffDate()));
                        if(finProductBmQuotationMapper.getIntRate(map) != null){
                            intRate = finProductBmQuotationMapper.getIntRate(map);
                            realIntRate = intRate;
                        }
                        
                    }
                    //基准类型 = 复合基准时
                    //查询出该基准代码所对应的所有行情信息
                    else if(DictCodeListExt1.基准类型_2_复合.getValue().equals(dto.getIndexSource())
                            || DictCodeListExt1.基准类型_2_SAA.getValue().equals(dto.getIndexSource())){
                        Map<String, Object> param = new HashMap<String, Object>();
                        param.put("indexId", dto.getIndexId());
                        //设置平台日
                        param.put("effDate", DateUtil.format(dto.getEffDate()));
                        //查出出该基准代码所对应的所有行情信息
                        List<BenchmarkRate> list = finProductBmQuotationMapper.getBenchmarkRate(param);
                        //循环利率*权重
                        if(list != null && list.size()>0){
                            for(BenchmarkRate benchmarkRate :list){
                                realIntRate = realIntRate.add(intRate.add(benchmarkRate.getIntRate().multiply(benchmarkRate.getWeight())));
                            }
                        }
                    }
                    if(dto.getCoefficient() == null){
                        if(dto.getSpreadRate() != null){
                            dto.setFloatRate(realIntRate.add(dto.getSpreadRate()));
                        }else if(BigDecimal.ZERO.compareTo(realIntRate)!=0){
                            dto.setFloatRate(realIntRate);
                        }
                        
                    }
                    else if(dto.getCoefficient() != null && dto.getSpreadRate() != null){
                        if(realIntRate != null){
                            dto.setFloatRate(realIntRate.multiply(dto.getCoefficient()).add(dto.getSpreadRate()));
                        }
                    }
                }
            } 
        }
        
        return dtoList;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int delete(FinProductBmQuotationDto dto) {
        return 0;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int insert(FinProductBmQuotationDto dto) throws IllegalAccessException, InvocationTargetException {
        dto.getBussBenchMarkInfoDto().setFinprodId((dto.getFinprodId()));
        dto.getBussBenchMarkInfoDto().setEffDate(dto.getEffDate());
        dto.getBussBenchMarkInfoDto().setRemark(dto.getRemark());
        dto.getBussBenchMarkInfoDto().setBranch(dto.getBranch());
        if(dto.getBussBenchMarkInfoDto().getRate() != null){
            dto.getBussBenchMarkInfoDto().setRate(dto.getBussBenchMarkInfoDto().getRate().divide(BigDecimal.valueOf(100)));
        }
        if(dto.getBussBenchMarkInfoDto().getSpreadRate() != null){
            dto.getBussBenchMarkInfoDto().setSpreadRate(dto.getBussBenchMarkInfoDto().getSpreadRate().divide(BigDecimal.valueOf(100)));
        }
        if(dto.getBussBenchMarkInfoDto().getBaseRuleId() == null){
            dto.getBussBenchMarkInfoDto().setBaseRuleId(dto.getBaseRuleId());
        }
        if(StringUtil.isEmpty(dto.getBussBenchMarkInfoDto().getBaseRuleType())){
        	dto.getBussBenchMarkInfoDto().setBaseRuleType(DictCodeListExt1.业绩基准类型_产品披露.getValue());
        }
        if(StringUtil.equals(dto.getBussBenchMarkInfoDto().getRateType(), DictCodeList.利率类型_浮动利率.getValue())){
            String indexId = dto.getBussBenchMarkInfoDto().getIndexId();
            //设置基准来源
            String indexSource = finProductBmQuotationMapper.selectIndexSource(indexId);
            if(indexSource != null){
              //设置基准来源为复合基准
                dto.getBussBenchMarkInfoDto().setIndexSource(indexSource);
            }else{
                //设置基准来源为利率
                dto.getBussBenchMarkInfoDto().setIndexSource(DictCodeListExt1.基准类型_2_利率.getValue());
            }
        }
        //add by zhangJin start 新建业绩基准生效后，利率最小值、最大值大了100倍 
        dto.getBussBenchMarkInfoDto().setRateLimit(ArithmeticUtil.divide(dto.getBussBenchMarkInfoDto().getRateLimit(), 100, 8));
        dto.getBussBenchMarkInfoDto().setRateLower(ArithmeticUtil.divide(dto.getBussBenchMarkInfoDto().getRateLower(), 100, 8));
        //add by zhangJin end
        return finBaseRuleInfoMapper.insert(dto.getBussBenchMarkInfoDto());
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public int update(FinProductBmQuotationDto dto) {
        dto.getBussBenchMarkInfoDto().setFinprodId((dto.getFinprodId()));
        dto.getBussBenchMarkInfoDto().setBaseRuleId(dto.getBaseRuleId());
        dto.getBussBenchMarkInfoDto().setEffDate(dto.getEffDate());
        if(dto.getBussBenchMarkInfoDto().getRate() != null){
            dto.getBussBenchMarkInfoDto().setRate(dto.getBussBenchMarkInfoDto().getRate().divide(BigDecimal.valueOf(100)));
        }
        if(dto.getBussBenchMarkInfoDto().getSpreadRate() != null){
            dto.getBussBenchMarkInfoDto().setSpreadRate(dto.getBussBenchMarkInfoDto().getSpreadRate().divide(BigDecimal.valueOf(100)));
        }
        if(StringUtil.isEmpty(dto.getBussBenchMarkInfoDto().getBaseRuleType())){
        	dto.getBussBenchMarkInfoDto().setBaseRuleType(DictCodeListExt1.业绩基准类型_产品披露.getValue());
        }
        if(StringUtil.equals(dto.getBussBenchMarkInfoDto().getRateType(), DictCodeList.利率类型_浮动利率.getValue())){
            String indexId = dto.getBussBenchMarkInfoDto().getIndexId();
            //设置基准来源
            String indexSource = finProductBmQuotationMapper.selectIndexSource(indexId);
            if(indexSource != null){
              //设置基准来源为复合基准
                dto.getBussBenchMarkInfoDto().setIndexSource(indexSource);
            }else{
                //设置基准来源为利率
                dto.getBussBenchMarkInfoDto().setIndexSource(DictCodeListExt1.基准类型_2_利率.getValue());
            }
        }
        dto.getBussBenchMarkInfoDto().setRateLimit(ArithmeticUtil.divide(dto.getBussBenchMarkInfoDto().getRateLimit(), 100, 8));
        dto.getBussBenchMarkInfoDto().setRateLower(ArithmeticUtil.divide(dto.getBussBenchMarkInfoDto().getRateLower(), 100, 8));
        return finProductBmQuotationMapper.updateBaseRuleInfo(dto.getBussBenchMarkInfoDto());
    }

    /**
     * 新建-产品代码联想下拉框
     * <功能详细描述>
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodMarketIdToNew(@RequestBody Map<String, Object> param) {
        if("null".equals(param.get("finprodMarketId"))){
            param.replace("finprodMarketId", null);
        }
        param.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
        param.put("PROFIT_TYPE_03", DictCodeList.收益类型_货币型.getValue());
        return finProductBmQuotationMapper.getFinprodMarketIdToNew(param);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public FinProductBmQuotationDto selectByFinprodId(Map<String, Object> param) {
        FinProduct entity = finProductMapper.selectByPrimaryKey((String)param.get("finprodId"));
        FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey((String)param.get("finprodId"));
        FinBaseRuleInfoKey key = new FinBaseRuleInfoKey();
        key.setBaseRuleId((String)param.get("baseRuleId"));
        key.setEffDate(DateUtil.parse((String)param.get("effDate")));
        FinBaseRuleInfo e = finBaseRuleInfoMapper.selectByPrimaryKey(key);
        FinProductBmQuotationDto dto = new FinProductBmQuotationDto();
        dto.setFinprodId(entity.getFinprodId());
        dto.setProfitType(entity.getProfitType());
        dto.setFinprodMarketId(entity.getFinprodMarketId());
        dto.setEffDate(e.getEffDate());
        dto.setBaseRuleId(e.getBaseRuleId());
        dto.setRemark(e.getRemark());
        dto.setpFinprodId(entity.getpFinprodId());
        if(finProductAdd!=null){
        	if(StringUtil.equals(finProductAdd.getDealMode(), DictCodeList.处理模式_周期型.getValue())){
            	dto.setInvestmentCycle(finProductAdd.getInvestmentCycle());	
        	}
        	dto.setDealMode(finProductAdd.getDealMode());
        }
        FinProduct p_entity = finProductMapper.selectByPrimaryKey(entity.getpFinprodId());
        if(p_entity != null) {
        	dto.setFinprodAbbr(p_entity.getFinprodAbbr());
        }else {
        	dto.setFinprodAbbr(entity.getFinprodAbbr());
        }
        return dto;
    }

    /**
     * 根据基准代码和生效日查询数据
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<FinProductBmQuotationDto> selectByFinprodIdAndEffDate(FinProductBmQuotationDto dto) {
        return finProductBmQuotationMapper.selectByFinprodIdAndEffDate(dto);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public FinProductBmQuotationDto getMdate(String finprodId) {
        return finProductBmQuotationMapper.getMdate(finprodId);
    }

    /**
     * 判断生效日后是否发生了浮动管理费计提、支付交易
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Integer getCount(FinProductBmQuotationDto dto) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("finprodId", dto.getFinprodId());
        
        param.put("effDate",DateUtil.format(dto.getEffDate()));
        param.put("CASH_TYPE_2_C04", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        param.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        param.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        List<Integer> list = finProductBmQuotationMapper.getCount(param);
        int count = 0;
        for(Integer i:list){
            count+=i;
        }
        return count;
    }
}
