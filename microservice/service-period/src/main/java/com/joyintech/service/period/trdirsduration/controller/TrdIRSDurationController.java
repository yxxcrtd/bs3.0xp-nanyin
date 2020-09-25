package com.joyintech.service.period.trdirsduration.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdirsduration.service.TrdIRSDurationService;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import com.joyintech.tams.invest.common.dto.period.trdirsduration.TrdIRSDurationDto;

/**
 * 利率互换存续期管理
 *
 * @author : renping
 * @version V1.0
 * @Project: microservice
 * @Package com.joyintech.service.product.productduration.addreduce.controller
 * @date Date : 2019年08月10日
 */
@RestController("TrdIRSDurationCtr_dtx")
@RequestMapping("/TrdIRSDuration")
public class TrdIRSDurationController {

    @Autowired
    private TrdIRSDurationService trdIRSDurationService;
    /**
     * 组合可用持仓和实际持仓查询更新组件接口
     */
    @Autowired(required=false)
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;

    /**
     * 画面查询方法-交易编号
     *
     * @return 查询结果集
     * @throws Exception 异常
     * 
     */
    @PostMapping("getBusiCode/{page}/{length}")
    public Page<SelectOptionDto> getBusiCode(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdIRSDurationService.getBusiCode(page, length, param);
    }

    /**
     * 画面查询方法-金融产品代码
     *
     * @return 查询结果集
     * @throws Exception 异常
     * 
     */
    @PostMapping("getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdIRSDurationService.getFinprodId(page, length, param);
    }

    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdIRSDurationDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdIRSDurationService.findPage(page, length, param);
    }

    /**
     * 查询有效、撤销或待处理的存续期交易数据（所有数据）
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAllData")
    public List<TrdIRSDurationDto> findAllData(@RequestBody Map<String, Object> param) {
        return trdIRSDurationService.findAllData(param);
    }

    /**
     * 查询存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdIRSDurationDto findEffectiveData(@RequestBody Map<String, Object> params) {
        return trdIRSDurationService.findEffectiveData(params);
    }

    /**
     * 查询有效的存续期交易数据(撤销用)
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveDeal")
    public List<BaseTradeProductDealDto> findEffectiveDeal(@RequestBody Map<String, Object> params) {
        return trdIRSDurationService.findEffectiveDeal(params);
    }

    /**
     * 组合可用持仓查询
     *
     * @param dto PtlSecPositionFlowDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody PtlSecPositionFlowDto dto) {
        BigDecimal result = basePtlSecPositionInfoService.queryUsableSecPosition(dto);
        return result;
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
    public String doComplete(@RequestBody TrdIRSDurationDto trdIRSDurDbInertDto) {
        return trdIRSDurationService.doComplete(trdIRSDurDbInertDto);
    }

    /**
     * 作废
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doDelete")
    public String doDelete(@RequestBody TrdCommonDeleteDto trdCommonDeleteDto) {
        return trdIRSDurationService.doDelete(trdCommonDeleteDto);
    }

    /**
     * 撤销
     *
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doRevoke")
    public String doRevoke(@RequestBody TrdIRSDurationDto dto) {
        return trdIRSDurationService.doRevoke(dto);
    }
}
