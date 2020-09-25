/*
 * 文件名：ExchangeAutoDurDealWflStartService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.CommonResult;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioCashPositionsFlowInputDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDuration002ProcessDto;

/**
 * 外汇远期掉期到期交易自动生成Writer相关业务处理
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @see ExchangeAutoDurDealWflStartService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ExchangeAutoDurDealWflStartService
        extends BaseService<TrdExchangeDuration002ProcessDto> {

//	@Autowired 
//	private ValuationInterfService valuationInterfService;
	
    /**
     * 外汇远期差额清算时，发工作流待办
     * 
     * @param dto
     *            参数
     * @throws BatchException
     *             web请求异常
     * @see
     * @since 1.0
     */
    public void startwfl(TrdExchangeDuration002ProcessDto dto) throws BatchException {
        deal流程中(dto);
        String processId = restTemplate.postForObject(
                ServiceApplication.PINGAN_SERVICE_NAME + "/invest/trade/forwex/TrdForwexInvestTrade002/autosave",
                dto.getTrdExchangeDurDbInertDto(), String.class);
        if (StringUtil.isEmpty(processId)) {
            throw new BatchException(
                    "外汇远期工作流启动失败！交易编号：" + dto.getTrdExchangeDurDbInertDto().getBusiCode());
        }
    }

    /**
     * 外汇远期差额清算以外或外汇掉期远端时，直接生成有效交易
     * 
     * @param dto
     *            参数
     * @see
     * @since 1.0
     */
    public void insertBusinessData(TrdExchangeDurDbInertDto trdExchangeDurDbInertDto) {
        deal有效(trdExchangeDurDbInertDto);
        
        // 外汇远期 需要调用jixu接口 start
//        TrdCommDto trdCommDto = new TrdCommDto();
//        trdCommDto.setFinTradeProduct(trdExchangeDurDbInertDto.getFinTradeProduct());
//        trdCommDto.setStlPayOrdersList(trdExchangeDurDbInertDto.getStlPayOrdersList());
//        trdCommDto.setTrdProductDeal(trdExchangeDurDbInertDto.getTrdProductDeal());
//        trdCommDto.setTrdProductDealAdd(trdExchangeDurDbInertDto.getTrdProductDealAdd());
//        trdCommDto.setOperateType(BussinessConstant.OPERATE_TYPE_ADD);
//        trdCommDto.setHeader(BussinessJixuConstant.消息头_远期换汇合约到期);
//        valuationInterfService.runValInvest2Jixu(trdCommDto);
        // jixu接口 end
        
    	// 外汇远期 需要调用jixu接口 end
        trdExchangeDurDbInertDto.setOperateType("NEW");
        restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdFxswapInvestTrade001/doComplete",
                trdExchangeDurDbInertDto, Integer.class);
    }

    /**
     * 处理流程中状态
     *
     * @return CommonResult
     * @param baseTradeProductDealDtoList
     */
    private CommonResult deal流程中(TrdExchangeDuration002ProcessDto dto) {
        // 1.调用【组合可用持仓与实际持仓查询更新组件】与【组合可用现金与实际现金查询更新组件】执行持仓与现金的插入操作
        if (!insertPosition(dto.getTrdExchangeDurDbInertDto(), DictCodeList.持仓类型_可用, false)) {
            return new CommonResult(CommonResult.FAILD, "处理失败");
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    /**
     * @description: 2.写入类型为[实际]的组合持仓流水与组合现金流水
     * @param: [BaseTradeProductDealDtoList,
     *             持仓类型]
     * @return: void
     * @date: 2018/9/29
     */
    private Boolean insertPosition(TrdExchangeDurDbInertDto trdExchangeDurDbInertDto,
            DictCodeList 持仓类型, boolean needNegative) {
        List<PtlSecPositionFlowDto> ptlSecPositionFlows = new ArrayList<>();
        List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<>();
        if (trdExchangeDurDbInertDto.getStlPayOrdersList() != null
                && !trdExchangeDurDbInertDto.getStlPayOrdersList().isEmpty()) {
            PortfolioCashPositionsFlowInputDto cashInputDto = new PortfolioCashPositionsFlowInputDto();
            cashInputDto.setPositionType(持仓类型.getValue());
            cashInputDto.setTrdProductDeal(trdExchangeDurDbInertDto.getTrdProductDeal());
            cashInputDto.setPortfolioId(
                    trdExchangeDurDbInertDto.getTrdProductDealAdd().getPortfolioId());
            cashInputDto.setBonusMode(trdExchangeDurDbInertDto.getTrdProductDealAdd().getPayType());
            ptlCashPositionFlows.addAll(
                    this.cashDeal(cashInputDto, trdExchangeDurDbInertDto.getStlPayOrdersList()));
        }
        PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlows);
        ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
        return restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
                ptlPositionHoldDto, Boolean.class);
    }

    private CommonResult deal有效(TrdExchangeDurDbInertDto trdExchangeDurDbInertDto) {
        BaseTradeProductDealDto BaseTradeProductDealDto = new BaseTradeProductDealDto();
        BeanUtil.copyPropertiesIgnoreNull(BaseTradeProductDealDto, trdExchangeDurDbInertDto);
        List<BaseTradeProductDealDto> list = new ArrayList<>();
        list.add(BaseTradeProductDealDto);

        // 2.调用基础组件.【金融产品交易组件.新建金融产品交易信息】,写入金融产品交易信息
        Boolean result = restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/common/trdcommonbase/insertTradeInfo",
                list, Boolean.class);
        if (result) {
            // 3.写入类型为[实际]的组合持仓流水与组合现金流水
            insertPosition(trdExchangeDurDbInertDto, DictCodeList.持仓类型_实际, false);
            trdExchangeDurDbInertDto.getTrdProductDealAdd().setPortfolioId(
                trdExchangeDurDbInertDto.getPortfolioId());
            insertPosition(trdExchangeDurDbInertDto, DictCodeList.持仓类型_实际, false);
        }
        return new CommonResult(CommonResult.SUCCED, "处理完成");
    }

    /**
     * {@inheritDoc}
     */
    private List<PtlCashPositionFlowDto> cashDeal(PortfolioCashPositionsFlowInputDto inputDto,
            List<StlPayOrders> stlPayOrdersList) {
        List<PtlCashPositionFlowDto> result = new ArrayList<>();
        String portfolioId = inputDto.getPortfolioId();
        TrdProductDeal deal = inputDto.getTrdProductDeal();
        for (StlPayOrders stlPayOrder : stlPayOrdersList) {
            PtlCashPositionFlowDto resultDto = new PtlCashPositionFlowDto();
            // 输入参数.组合代码
            resultDto.setPortfolioId(portfolioId);
            // 划款指令Entity.本方账户
            resultDto.setAcctId(stlPayOrder.getMyAcctNo());
            // 输入参数.持仓类型
            resultDto.setHodingType(inputDto.getPositionType());
            // 划款指令Entity.币种
            resultDto.setCcy(stlPayOrder.getCcy());
            // 划款指令Entity.指令编号
            resultDto.setOrderId(stlPayOrder.getOrderId());
            resultDto.setCreateUser(deal.getCreateUser());
            resultDto.setCreateDept(deal.getCreateDept());
            resultDto.setUpdateUser(deal.getCreateUser());
            if (DictCodeListExt1.收支方向_支出.getValue().equals(stlPayOrder.getPs())) {
                resultDto.setCdate(stlPayOrder.getSettleDate());
                resultDto.setAmount(
                        ArithmeticUtil.multiply(stlPayOrder.getSettleAmt(), new BigDecimal(-1)));
                result.add(resultDto);
            }

            // 2 只插入“实际”
            if (DictCodeList.持仓类型_实际.getValue().equals(inputDto.getPositionType())) {
                if (DictCodeListExt1.收支方向_收入.getValue().equals(stlPayOrder.getPs())) {
                    resultDto.setCdate(stlPayOrder.getSettleDate());
                    resultDto.setAmount(stlPayOrder.getSettleAmt());
                    result.add(resultDto);
                }
            }
        }
        return result;
    }
}
