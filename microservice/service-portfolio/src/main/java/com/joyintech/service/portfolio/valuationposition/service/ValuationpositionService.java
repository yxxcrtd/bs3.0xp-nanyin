package com.joyintech.service.portfolio.valuationposition.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.portfolio.valuationposition.controller.ValuationpositionController;
import com.joyintech.service.portfolio.valuationposition.dao.ValuationpositionMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlCashPositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlFeePositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlValuationPositionDto;

/**
 * 估值头寸服务
 * 
 * @author wl
 * @version 1.0 2020年4月13日
 * @see ValuationpositionController
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
public class ValuationpositionService extends BaseService {

    /**
     * 估值头寸服务Mapper
     */
    @Autowired
    private ValuationpositionMapper valuationpositionMapper;

    /**
     * 查询估值头寸所有数据
     * 
     * @param param 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    public MockPtlValuationPositionDto selectPtlValuationPositionAllData(@RequestBody Map<String, Object> param)
        throws Exception {
        MockPtlValuationPositionDto resultDto = new MockPtlValuationPositionDto();
        // 组合资产分析查询
        resultDto.setPtlSecValuationList(this.selectPtlSecValuationData(param));

        // 组合估值查询 所有数据
        Map<String, Object> tempParamMap = new HashMap<String, Object>();
        tempParamMap.put("portfolioId", param.get("portfolioId"));
        tempParamMap.put("sysdate", param.get("sysdate"));
        resultDto.setPtlPortfolioValuationList(this.selectPtlPortfolioValuationData(tempParamMap));

        // 组合费用头寸查询
        resultDto.setPtlFeePositionList(this.selectPtlFeePositionData(param));

        // 组合现金查询
        resultDto.setPtlCashPositionList(this.selectPtlCashPositionData(param));

        return resultDto;
    }

    /**
     * 组合资产分析查询
     * 
     * @param map 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    public List<MockPtlSecValuationDto> selectPtlSecValuationData(Map<String, Object> map)
        throws Exception {
        return valuationpositionMapper.selectPtlSecValuationData(map);
    }

    /**
     * 组合估值查询
     * 
     * @param map 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    public List<MockPtlPortfolioValuationDto> selectPtlPortfolioValuationData(Map<String, Object> map)
        throws Exception {
        return valuationpositionMapper.selectPtlPortfolioValuationData(map);
    }

    /**
     * 组合费用头寸查询
     * 
     * @param map 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    public List<MockPtlFeePositionDto> selectPtlFeePositionData(Map<String, Object> map)
        throws Exception {
        return valuationpositionMapper.selectPtlFeePositionData(map);
    }

    /**
     * 组合现金查询
     * 
     * @param map 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    public List<MockPtlCashPositionDto> selectPtlCashPositionData(Map<String, Object> map)
        throws Exception {
        map.put("HODING_TYPE", DictCodeList.持仓类型_实际.getValue());
        return valuationpositionMapper.selectPtlCashPositionData(map);
    }
}
