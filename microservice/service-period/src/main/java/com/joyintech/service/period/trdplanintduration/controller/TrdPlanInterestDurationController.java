package com.joyintech.service.period.trdplanintduration.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.period.trdplanintduration.service.TrdPlanInterestDurationService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.autoperiod.basePtlRecePosition.BasePtlRecePositionService;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.common.TrdCommonDeleteDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.trdplanintduration.TrdPlanInterestDurationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 计划类资产利率型 存续期
 *
 * @author : 强成西
 * @version V1.0
 * @Project: microservice
 * @Package com.joyintech.service.product.productduration.addreduce.controller
 * @Description: TODO
 * @date Date : 2018年09月19日 10:02
 */
@RestController("TrdPlanInterestDurationCtr_dtx")
@RequestMapping("/TrdPlanInterestDuration")
public class TrdPlanInterestDurationController {

    @Autowired
    private TrdPlanInterestDurationService trdPlanInterestDurationService;
    @Autowired
    private BasePtlRecePositionService basePtlRecePositionService;

    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdPlanInterestDurationDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                     @PathVariable("length") int length) {
        return trdPlanInterestDurationService.findPage(page, length, param);
    }

    /**
     * 查询有效、撤销或待处理的存续期交易数据(不分页)
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectAllData")
    public List<TrdPlanInterestDurationDto> selectAllData(@RequestBody Map<String, Object> param) {
        return trdPlanInterestDurationService.selectAllData(param);
    }

    /**
     * 查询有效的存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdPlanInterestDurationDto findEffectiveData(@RequestBody Map<String, Object> params) {
        return trdPlanInterestDurationService.findEffectiveData(params);
    }

    /**
     * 查询利息明细信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAssetIntInfo")
    public List<FinAssetIntInfo> findAssetIntInfo(@RequestBody Map<String, Object> params) {
        List<FinAssetIntInfo> assetIntInfoList=trdPlanInterestDurationService.findAssetIntInfo(params);
        for(FinAssetIntInfo dto:assetIntInfoList) {
            //查询区间未付利息
            Long branch = new Long(DateUtil.formatDate(dto.getmDate()) + "02");
            params.put("portfolioId", dto.getPortfolioId());//列表组合
            params.put("branch", branch);//列表分支序号
            String statusValid = (String)params.get("statusValid");//获取交易状态
            BigDecimal repaymentUnpayInterest=BigDecimal.ZERO;//理论区间未付初始值为0
            BigDecimal sjRepaymentUnpayInterest=BigDecimal.ZERO;//实际区间未付初始值为0
            if(statusValid.equals(DictCodeListExt1.画面数据状态_待处理.getValue())){//待处理，从应收款流水表取值
                repaymentUnpayInterest = basePtlRecePositionService.getRepayAmt(params);//区间未付利息
                sjRepaymentUnpayInterest=repaymentUnpayInterest;
            }else{//从交易表取值
                //取交易表的branch
                String actbranch=trdPlanInterestDurationService.getBranch(params);
                if(actbranch!=null) {
                    params.put("branch", actbranch);
                    //理论区间未付还本不带息的值
                    repaymentUnpayInterest = basePtlRecePositionService.getRepaymentUnpayInterest(params);//区间未付利息
                    //实际区间未付还本不带息的值
                    sjRepaymentUnpayInterest = trdPlanInterestDurationService.getRepaymentUnpayInterest(params);
                }
            }
            dto.setRepaymentUnpayInterest(repaymentUnpayInterest);//理论未付还本不带息金额
            dto.setActrRepaymentUnpayInterest(sjRepaymentUnpayInterest);
        }
        return assetIntInfoList;
    }

    /**
     * 插入流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public String doInsert(@RequestBody TrdPlanInterestDurationDto durationDto) {
        return trdPlanInterestDurationService.doInsert(durationDto);
    }

    /**
     * 插入流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doInsertBaseTradeInfo")
    public String doInsertBaseTradeInfo(@RequestBody BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
        return trdPlanInterestDurationService.doInsertBaseTradeInfo(baseTotalTradeInfoDto);
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
    public String doComplete(@RequestBody TrdPlanInterestDurationDto durationDto) {
        return trdPlanInterestDurationService.doComplete(durationDto);
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
    public String doRevoke(@RequestBody TrdPlanInterestDurationDto durationDto) {
        return trdPlanInterestDurationService.doRevoke(durationDto);
    }

    /**
     * 作废流程， 并删除流程表数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doDelete")
    public String doDelete(@RequestBody TrdCommonDeleteDto trdCommonDeleteDto) {
        return trdPlanInterestDurationService.doDelete(trdCommonDeleteDto);
    }
}
