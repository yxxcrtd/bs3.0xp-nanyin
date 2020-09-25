package com.joyintech.service.product.investmentevaluate.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.investmentevaluate.dao.FinInvestmentEvaluateMapper;
import com.joyintech.service.product.investmentevaluate.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author weipeng
 */
@Service(value = "finInvestmentEvaluateService")
@Transactional(rollbackFor = Exception.class)
public class FinInvestmentEvaluateService extends CommonExportService {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinInvestmentEvaluateMapper finInvestmentEvaluateMapper;

    /**
     *
     * 
     * @param page page
     * @param length length
     * @param
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<FinInvestmentEvaluateDto> findPage2(int page, int length,
                                                        Map<String, Object> pageParam)
            throws Exception {


        return this.queryForPage(page, length,
                () -> finInvestmentEvaluateMapper.selectPage2Data(pageParam));
    }
    /**
     * 净值表现查询
     */
    public Page<BokPositionPrdNetvalueDto>  findPage3(int page, int length,
                                                      Map<String, Object> pageParam){
        return this.queryForPage(page,length,
                ()->finInvestmentEvaluateMapper.findPage3(pageParam));
    }
    /**
     * 净值表现查询echart图
     */
    public List<BokPositionPrdNetvalueDto>  findPage3e(Map<String, Object> pageParam){
        return finInvestmentEvaluateMapper.findPage3(pageParam);
    }
    /**
     *金融产品信息+组合代码名称+区间开始日结束日
     */
    public FinInvestmentEvaluate002Dto searchFinancialProductInformationDto(Map<String, Object> pageParam){
        return finInvestmentEvaluateMapper.searchFinancialProductInformationDto(pageParam);
    }
    /**
     * 组合信息查询
     */
    public Page<FinInvestmentEvaluate003Dto>  findPage(int page, int length,
                                                       Map<String, Object> pageParam){
        return this.queryForPage(page,length,
                ()->finInvestmentEvaluateMapper.portfolioInfo(pageParam));
    }
    /**
     * 策略执行有效性
     */
    public List<FinInvestmentEvaluate005Dto> findPage7(Map<String, Object> pageParam){
        return finInvestmentEvaluateMapper.findPage7(pageParam);
    }
    /**
     * 策略执收益来源
     */
    public List<FinInvestmentEvaluate006Dto> findPage8(Map<String, Object> pageParam){
        return finInvestmentEvaluateMapper.findPage8(pageParam);
    }
    /**
     * 组合查询
     */
    public List<SelectOptionDto> findPort(Map<String, Object> pageParam){
        return finInvestmentEvaluateMapper.findPort(pageParam);
    }
    /**
     * 策略代码、名称查询
     */
    public List<SelectOptionDto> findStrategyId(Map<String, Object> pageParam){
        return finInvestmentEvaluateMapper.findStrategyId(pageParam);
    }

    /**
     * 收益指标目标
     */
    public List<FinInvestmentEvaluate007Dto> info7(Map<String, Object> pageParam){
        List list=finInvestmentEvaluateMapper.info7(pageParam);
        return list;
    }
    /**
     * 基准明细
     */
    public List<FinInvestmentEvaluate008Dto> info8(Map<String, Object> pageParam){
        return finInvestmentEvaluateMapper.info8(pageParam);
    }
}
