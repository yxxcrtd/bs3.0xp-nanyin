package com.joyintech.service.period.trdprojnetduration.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdprojnetduration.dto.TrdProjectNetDurationDto;
import com.joyintech.service.period.trdprojnetduration.service.TrdProjectNetDurationService;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("TrdProjectNetDurationCtr_dtx")
@RequestMapping("/TrdProjectNetDuration")
public class TrdProjectNetDurationController {
    /**
     *
     */
    @Autowired
    private TrdProjectNetDurationService trdProjectNetDurationService;
    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdProjectNetDurationDto> findPage(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page,
                                                   @PathVariable("length") int length) {
        return trdProjectNetDurationService.findPage(page, length, param);
    }
    /**
     *
     * 市场代码联动
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    @PostMapping("finprodMarketIdLink")
    public Map<String,Object>finprodMarketIdLink(@RequestBody Map<String,Object>map){
        return trdProjectNetDurationService.finprodMarketIdLink(map);
    }
    /**
     *
     * 组合代码联动
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    @PostMapping("portfolioIdLink")
    public Map<String,Object>portfolioIdLink(@RequestBody Map<String,Object>map){
        return trdProjectNetDurationService.portfolioIdLink(map);
    }
    /**
     * 剩余本金计算联动 <功能详细描述>
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    @PostMapping("calRegistShare")
    public Map<String, Object> calRegistShare(@RequestBody Map<String, Object> map) {
        return trdProjectNetDurationService.calRegistShare(map);
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
    public String doComplete(@RequestBody TrdProjectNetDurationDto entity) {
        return trdProjectNetDurationService.doComplete(entity);
    }
    /**
     * 获取有效数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdProjectNetDurationDto findEffectiveData(@RequestBody Map<String, Object> map) {
        return trdProjectNetDurationService.findEffectiveData(map);
    }
    /**
     * 现金分红流程中
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findCashDividend")
    public TrdProjectNetDurationDto findCashDividend(@RequestBody Map<String, Object> map) {
        return trdProjectNetDurationService.findCashDividend(map);
    }
    /**
     * 统计是否存在后续交易
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("countFollowDeal")
    public int countFollowDeal(@RequestBody Map<String, Object> map) {
        return trdProjectNetDurationService.countFollowDeal(map);
    }
    /**
     * 查询利息明细信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAssetIntInfo")
    public List<FinAssetIntInfo> findAssetIntInfo(@RequestBody Map<String, Object> params) {
        return trdProjectNetDurationService.findAssetIntInfo(params);
    }

    /**
     * 画面查询方法-交易编号
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getBusiCode/{page}/{length}")
    public Page<SelectOptionDto> getBusiCode(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdProjectNetDurationService.getBusiCode(page, length, param);
    }
}
