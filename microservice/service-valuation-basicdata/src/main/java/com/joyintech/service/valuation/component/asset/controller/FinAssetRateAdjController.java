package com.joyintech.service.valuation.component.asset.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.valuation.component.asset.dto.AssetCommonInfoDto;
import com.joyintech.service.valuation.component.asset.dto.FinAssetRateAdjDto;
import com.joyintech.service.valuation.component.asset.dto.FinCashPayRuleDto;
import com.joyintech.service.valuation.component.asset.dto.FinCashPlanDto;
import com.joyintech.service.valuation.component.asset.service.FinAssetRateAdjService;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName FinAssetRateAdjController
 * @Description: 利率调整列表对应的业务类
 * @Author yanya
 * @Date 2021/3/4 0004
 * @Version V1.0
 **/
@RestController(value="serviceFinAssetRateAdjController")
@RequestMapping("/FinAssetRateAdj")
public class FinAssetRateAdjController {

    @Autowired
    private FinAssetRateAdjService finAssetRateAdjService;

    /**
     *  获取利率调整信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @param finprodType
     * @param cashId
     * @param cashType
     * @param isInitial
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getFinAssetRateAdjByCashId")
    public List<FinCashCalcRule> getFinAssetRateAdjByCashId(String finprodId, String finprodType, String cashId, String cashType, String isInitial) {
        return finAssetRateAdjService.getFinAssetRateAdjByCashId(finprodId, finprodType, cashId, cashType, isInitial);
    }

    /**
     * 获取市场利率行情信息
     * <功能详细描述>
     * @param indexId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getMstRateInfo")
    public List<FinCashCalcRule> getMstRateInfo(String indexId){
        return finAssetRateAdjService.getMstRateInfo(indexId);
    }

    @PostMapping("getfinAssetRateAdjByProductId")
    public List<FinAssetRateAdjDto> getfinAssetRateAdjByProductId(@RequestBody Map<String, Object> param) throws Exception {
        return finAssetRateAdjService.getfinAssetRateAdjByProductId(param);
    }

    /**
     * 重新查询利率信息
     * @param param
     * @return
     */
    @PostMapping("selectInitAssetRateAdj")
    public List<FinAssetRateAdjDto> selectInitAssetRateAdj(@RequestBody Map<String, Object> param){
        return finAssetRateAdjService.selectInitAssetRateAdj(param);
    }

    /**
     *  利率调整(取资产的利率类型、息票品种)
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetInfoToRateAdj")
    public AssetCommonInfoDto getAssetInfoToRateAdj(@RequestBody Map<String, Object> param) {
        return finAssetRateAdjService.getAssetInfoToRateAdj(param);
    }

    /**
     * 获取资产到期日
     * yjm 20191014
     * @param finprodId
     * @return
     */
    @GetMapping("getMdateByFinproId")
    public AssetCommonInfoDto getMdateByFinproId(String finprodId){
        return  finAssetRateAdjService.getMdateByFinproId(finprodId);
    }

    /**
     * 新增
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody FinAssetRateAdjDto dto) throws Exception {
        finAssetRateAdjService.insert(dto);
    }

    /**
     * GET资产公共信息
     * @param param
     * @return
     */
    @PostMapping("getAssetBasicInfoByFinprodId")
    public AssetCommonInfoDto getAssetBasicInfoByFinprodId(@RequestBody Map<String, Object> param) {
        return finAssetRateAdjService.getAssetBasicInfoByFinprodId(param);
    }

    /**
     * GET现金流计划
     * @param param
     * @return
     */
    @PostMapping("getFinCashPlanByProductId")
    public List<FinCashPlanDto> getFinCashPlanByProductId(@RequestBody Map<String, Object> param) {
        return finAssetRateAdjService.getFinCashPlanByProductId(param);
    }

    /**
     * GET现金流支付规则
     * @param param
     * @return
     */
    @PostMapping("getFinPayInterestStdInfoByProductId")
    public FinCashPayRuleDto getFinPayInterestStdInfoByProductId(@RequestBody Map<String, Object> param) {
        return finAssetRateAdjService.getFinPayInterestStdInfoByProductId(param);
    }

    /**
     * GET现金流计划
     * @param param
     * @return
     */
    @PostMapping("getDealCalendarHoliday")
    public List<String> getDealCalendarHoliday(@RequestBody Map<String, Object> param) {
        return finAssetRateAdjService.getDealCalendarHoliday(param);
    }

}
