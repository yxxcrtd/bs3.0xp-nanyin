package com.joyintech.service.period.trdlendduration.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdlendduration.dto.TrdLendDurationDto;
import com.joyintech.service.period.trdlendduration.service.TrdLendDurationService;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.invest.common.dto.common.TrdCommonWflParam;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.service.TradeProductDealCommService;

@RestController("TrdLendDurationCtr_dtx")
@RequestMapping("/TrdLendDuration")
public class TrdLendDurationController {
    /**
     * service
     */
    @Autowired
    private TrdLendDurationService trdLendDurationService;

    @Autowired
    private MapperDelegateService mapperDelegateService;
    
    @Autowired
    private TradeProductDealCommService baseTradeProductDealCommService;

    /**
     * 查询组合代码
     * <功能详细描述>
     *
     * @param map 参数
     * @return 结果值
     * @see
     * @since 1.0
     */
    @RequestMapping("/getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> map) {
        return trdLendDurationService.getPortfolioId(map);
    }

    /**
     * 查询组合代码
     * <功能详细描述>
     *
     * @param map 参数
     * @return 结果值
     * @see
     * @since 1.0
     */
    @RequestMapping("/getCounterId")
    public List<SelectOptionDto> getCounterId(@RequestBody Map<String, Object> map) {
        return trdLendDurationService.getCounterId(map);
    }

    /**
     * 请求画面
     * <功能详细描述>
     *
     * @param map 参数
     * @return 结果值
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<TrdLendDurationDto> selectForPage(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return trdLendDurationService.selectForPage(page, length, map);
    }

    /**
     * 查询所有数据
     *
     * @param map 参数
     * @return 结果值
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectAllDataList")
    public List<TrdLendDurationDto> selectAllDataList(@RequestBody Map<String, Object> map) throws Exception {
        return trdLendDurationService.selectAllDataList(map);
    }

    /**
     * 获取entity
     * <功能详细描述>
     *
     * @param map 参数
     * @return 结果值
     * @see
     * @since 1.0
     */
    @RequestMapping("/getEntityByMap")
    public TrdLendDurationDto getEntityByMap(@RequestBody Map<String, Object> map) {
        return trdLendDurationService.getEntityByMap(map);
    }

    /**
     * 插入数据，并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public String doComplete(@RequestBody TrdLendDurationDto trdLendDurationDto) {
        return trdLendDurationService.doComplete(trdLendDurationDto);
    }

    @PostMapping("findDealByWorkflowId")
    public List<WflTrdProductDeal> findDealByWorkflowId(@RequestBody String workflowId) {
        return trdLendDurationService.findDealByWorkflowId(workflowId);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insertAutoClassifyBatch")
    public String insertAutoClassifyBatch(@RequestBody TrdCommonWflParam trdCommonWflParam) {
    	baseTradeProductDealCommService.deleteWflData(trdCommonWflParam.getWflTrdFinProductDealTotal().getWorkflowId());
        mapperDelegateService.insertAutoClassifyBatch(trdCommonWflParam.getWflTrdProductDeals(),
                trdCommonWflParam.getWflPaTrdFinDealTotal(),
                trdCommonWflParam.getWflTrdFinProductDealTotal(),
                trdCommonWflParam.getWflStlPayOrdersDtos(),
                trdCommonWflParam.getWflPaDeals(),
                trdCommonWflParam.getWflDealAdds());
        return "Success";
    }
    //add by liupengfei for 多级法人改造 S
    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getBusiCode/{page}/{length}")
    public Page<SelectOptionDto> getBusiCode(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdLendDurationService.getBusiCode(page, length, param);
    }
    //add by liupengfei for 多级法人改造 E
}
