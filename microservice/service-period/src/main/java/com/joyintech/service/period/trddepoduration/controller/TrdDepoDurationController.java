/*
 * 文件名：TrdDepoDurationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：丁志鹏
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trddepoduration.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trddepoduration.dto.TrdDepoDurationDto;
import com.joyintech.service.period.trddepoduration.service.TrdDepoDurationService;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 存款存续期微服务Controller 〈功能详细描述〉
 *
 * @author 丁志鹏
 * @version 1.0 2018年10月11日
 * @see TrdDepoDurationController
 * @since 1.0
 */
@RestController("TrdDepoDurationCtr_dtx")
@RequestMapping("/TrdDepoDuration")
public class TrdDepoDurationController {

    /**
     * 存款存续期service
     */
    @Autowired
    private TrdDepoDurationService trdDepoDurationService;

    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @param param 界面参数
     * @param page 分页用参数
     * @param length 分页用参数
     * @return 查询结果集
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdDepoDurationDto> findPage(@RequestBody Map<String, Object> param,
                                             @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdDepoDurationService.findPage(page, length, param);
    }

    /**
     * 查询所有数据
     *
     * @param param 界面参数
     * @return 查询结果集
     */
    @PostMapping("selectAllData")
    public List<TrdDepoDurationDto> selectAllData(@RequestBody Map<String, Object> param) {
        return trdDepoDurationService.selectAllData(param);
    }

    /**
     * 查询存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdDepoDurationDto findEffectiveData(@RequestBody Map<String, Object> params) {
        return trdDepoDurationService.findEffectiveData(params);
    }

    /**
     * 判断其在现金流对账模块是否完成匹配
     *
     * @param busiCode 交易编号
     * @return 统计结果
     */
    @PostMapping("countCashMatch")
    public int countCashMatch(String busiCode) {
        return trdDepoDurationService.countCashMatch(busiCode);
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
    public String doComplete(@RequestBody TrdDepoDurationDto trdDepoDurationDto) {
        return trdDepoDurationService.doComplete(trdDepoDurationDto);
    }
    /**
     * 撤销交易，插入流出表
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doRevoke")
    public String doRevoke(@RequestBody TrdDepoDurationDto durationDto) {
        return trdDepoDurationService.doRevoke(durationDto);
    }
    /**
     *
     * 查询资产代码
     * <功能详细描述>
     * @param param 参数
     * @param page 分页所需
     * @param length 分页所需
     * @return 查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return trdDepoDurationService.getFinprodMarketId(page, length, param);
    }
    /**
     *
     * 获取所有的数据以便校验
     * <功能详细描述>
     * @param param 参数
     * @return 匹配结果集
     * @see
     * @since 1.0
     */
    @PostMapping("getAllDataForCheck")
    public List<TrdDepoDurationDto> getAllDataForCheck(@RequestBody Map<String, Object> param) {
        return trdDepoDurationService.getAllDataForCheck(param);
    }

    /**
     *
     * 画面查询方法-组合代码
     * <功能详细描述>
     * @param param 参数
     * @param page 分页参数
     * @param length  分页参数
     * @return
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page,
                                                @PathVariable("length") int length)
        throws Exception {
        return trdDepoDurationService.getPortfolioId(page, length, param);
    }
    //add by yujiaping 2020-12-5 BS-1562【存款类存续期】同一资产同一计息结束日只能做一次活期存放结息start
    @PostMapping("getTradeId")
    public Map<String, Object> getTradeId(@RequestBody Map<String, Object> map) {
        return trdDepoDurationService.getTradeId(map);
    }
    //add by yujiaping 2020-12-5 BS-1562【存款类存续期】同一资产同一计息结束日只能做一次活期存放结息end
    /**
     * 市场代码联动 <功能详细描述>
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    @PostMapping("finprodMarketIdLink")
    public Map<String, Object> finprodMarketIdLink(@RequestBody Map<String, Object> map) {
        return trdDepoDurationService.finprodMarketIdLink(map);
    }

    /**
     * 组合代码联动 <功能详细描述>
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    @PostMapping("portfolioIdLink")
    public Map<String, Object> portfolioIdLink(@RequestBody Map<String, Object> map) {
        return trdDepoDurationService.portfolioIdLink(map);
    }

    /**
     * 剩余本金计算联动 <功能详细描述>
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    @PostMapping("calResiPrincipal")
    public Map<String, Object> calResiPrincipal(@RequestBody Map<String, Object> map) {
        return trdDepoDurationService.calResiPrincipal(map);
    }

    /**
     * 理论应计利息计算联动 <功能详细描述>
     *
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
    @PostMapping("calTheoryIntDemand")
    public Map<String, Object> calTheoryIntDemand(@RequestBody Map<String, Object> map) {
        return trdDepoDurationService.calTheoryIntDemand(map);
    }

    /**
     * 根据汇总交易编号查询交易 <功能详细描述>
     *
     * @param busiCode 汇总交易编号
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findDealByTotalId")
    public List<TrdProductDeal> findDealByTotalId(@RequestBody String busiCode) {
        return trdDepoDurationService.findDealByTotalId(busiCode);
    }
    /**
     * 
     * 获取业务编号下拉框
     * <功能详细描述>
     * @param param 参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @PostMapping("getBusiCode/{page}/{length}")
    public Page<SelectOptionDto> getBusiCode(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                      @PathVariable("length") int length){
        return trdDepoDurationService.getBusiCode(page, length, param);
    }
    /**
     * 查询利息明细信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAssetIntInfo")
    public List<FinAssetIntInfo> findAssetIntInfo(@RequestBody Map<String, Object> params) {
        return trdDepoDurationService.findAssetIntInfo(params);
    }

    @PostMapping("countTradeForDemand")
    public int countTradeForDemand(@RequestBody  Map<String,Object> map) {
        return trdDepoDurationService.countTradeForDemand(map);
    }

}
