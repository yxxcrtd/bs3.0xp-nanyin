
package com.joyintech.tams.invest.portfolio.ptlmock.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.component.business.trade.BussTrdAvailableCashQueryService;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.entrust.impl.BaseEtrInvestInfoServiceImpl;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTest002Dto;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTestTradeDto;

/**
 * 模拟试算
 *
 * @author yjm
 * @version 1.0 2020/04/16
 * @see PtlMockTestValidator
 * @since 1.0
 */
@Component
public class PtlMockTestValidator implements BeanValidator {


    @Autowired(required = false)
    private BussTrdAvailableCashQueryService bussTrdAvailableCashQueryService;


    @Autowired
    private IPlatformDate iPlatformDate;


    @Autowired
    private BaseEtrInvestInfoServiceImpl baseEtrInvestInfoServiceImpl;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            PtlMockTest002Dto inputDto = (PtlMockTest002Dto)value;

            // 虚拟组合
            if (StringUtil.equals(DictCodeListExt3.组合类型_虚拟组合.getValue(),
                inputDto.getPortfolioType())) {
                // 业绩基准% 必填
                if (inputDto.getBaseRule() == null) {
                    errorList.add(new ValidMessage(
                        getMessage("BUSS_COM_E_0038", getMessage("PtlMockTest002.baseRule"))));
                }
                // 管理费率% 必填
                if (inputDto.getMgrFeeRate() == null) {
                    errorList.add(new ValidMessage(
                        getMessage("BUSS_COM_E_0038", getMessage("PtlMockTest002.mgrFeeRate"))));
                }
                // 募集金额（万元） 必填
                if (inputDto.getRaiseAmtPlan() == null) {
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
                        getMessage("PtlMockTest002.repRaiseAmtPlan"))));
                }
            }

            // 模拟交易列表不能为空
            if (!StringUtil.isNotEmpty(inputDto.getPageMockDealList())
                || inputDto.getPageMockDealList().size() == 0) {
                if (StringUtil.equals(DictCodeListExt3.组合类型_虚拟组合.getValue(),
                        inputDto.getPortfolioType())) {
                    errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0018")));
                }
            }
            else {
                // 校验交易列表数据
                List<PtlMockTestTradeDto> list = inputDto.getPageMockDealList();
                Map<String, List<PtlMockTestTradeDto>> 买入map = list.stream().filter(x->
                        StringUtil.equals(DictCodeList.交易方向_买入.getValue(),x.getPs())&&StringUtil.isNotEmpty(x.getFinprodId()))
                        .collect(Collectors.groupingBy(PtlMockTestTradeDto::getFinprodId));
                Map<String,BigDecimal> 卖出map = new HashMap<>();
                String tableName = "模拟交易";
                for (int i = 0; i < list.size(); i++ ) {
                    PtlMockTestTradeDto tradeDto = list.get(i);
                    if (!StringUtil.isNotEmpty(tradeDto.getTradeDate())) {
                        errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0022", tableName,
                            String.valueOf(i + 1), "交易日期")));
                    }
                    if (!StringUtil.isNotEmpty(tradeDto.getPs())) {
                        errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0022", tableName,
                            String.valueOf(i + 1), "交易方向")));
                    }
                    if (StringUtil.isNotEmpty(tradeDto.getType8())&&StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                        tradeDto.getType8())) {
                        if (StringUtil.isBlank(tradeDto.getInvAim())) {
                            errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0022",
                                tableName, String.valueOf(i + 1), "投资目的")));
                        }
                    }
                    //除债券外，其他的投资目的为空
                    if (StringUtil.isNotEmpty(tradeDto.getInvAim())&&!StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                            tradeDto.getType8())) {
                                errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0038",
                                    tableName, String.valueOf(i + 1), "投资目的")));
                        }
                    if (!StringUtil.isNotEmpty(tradeDto.getAmount())) {
                        errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0022", tableName,
                            String.valueOf(i + 1), "交易数量")));
                    }
                    if (!StringUtil.isNotEmpty(tradeDto.getTradePrice())) {
                        errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0022", tableName,
                            String.valueOf(i + 1), "交易净价")));
                    }else{
                    	//计划类与存款类净价必然为100
                        if ((StringUtil.equals(DictCodeList.平安资产类型_计划类资产_利率型.getValue(),
                                tradeDto.getType8())||StringUtil.equals(DictCodeList.平安资产类型_存款.getValue(),
                                        tradeDto.getType8()))&&tradeDto.getTradePrice().compareTo(new BigDecimal(100))!= 0) {
                        	errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0039", tableName,
                                    String.valueOf(i + 1), "交易净价")));
						}
                    }
                    if (!StringUtil.isNotEmpty(tradeDto.getMdate())) {
                        errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0022", tableName,
                            String.valueOf(i + 1), "到期日")));
                    }else if(DateUtil.compareDate(DateUtil.parse(iPlatformDate.getSysDateStr()),tradeDto.getMdate())>=0){
                        errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0035", tableName,
                                String.valueOf(i + 1), "到期日")));
                    }
                    if (!StringUtil.isNotEmpty(tradeDto.getYield())) {
                        errorList.add(new ValidMessage(this.getMessage("PtlMockTest_E_0022", tableName,
                            String.valueOf(i + 1), "到期收益率")));
                    }
                    if (!StringUtil.isNotEmpty(tradeDto.getCcy())) {
                        errorList.add(new ValidMessage(this.getMessage("PtlMockTest_E_0022", tableName,
                                String.valueOf(i + 1), "币种")));
                    }
                    // 虚拟资产不能卖出
                    if (StringUtil.isEmpty(tradeDto.getFinprodId())) {
                        if(StringUtil.equals(DictCodeList.交易方向_卖出.getValue(),tradeDto.getPs()))
                        errorList.add(new ValidMessage(this.getMessage("PtlMockTest_E_0032", tableName,
                                String.valueOf(i + 1), DictCodeList.交易方向_卖出.getCodeName())));
                    }else{
                        if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                                tradeDto.getType8())&&StringUtil.isNotEmpty(inputDto.getPortfolioId())) {
                            //温州无估值方案数据，注释此判断
                            /*String portfolioId = inputDto.getPortfolioId();
                            String finprodId = tradeDto.getFinprodId();
                            String invAim = baseEtrInvestInfoServiceImpl.findInvAim(portfolioId, finprodId);
                            if (StringUtil.isEmpty(invAim)) {
                                errorList.add(new ValidMessage(this.getMessage("PtlMockTest_E_0036",
                                        String.valueOf(i + 1))));
                            }
                            else if(!StringUtil.equals(tradeDto.getInvAim(),invAim)) {
                                errorList.add(new ValidMessage(this.getMessage("PtlMockTest_E_0037", tableName,
                                        String.valueOf(i + 1))));
                            }*/
                        }

                        if(StringUtil.isNotEmpty(tradeDto.getAmount())&&StringUtil.equals(DictCodeList.交易方向_卖出.getValue(),tradeDto.getPs())){
                            Map<String,Object> map = new HashMap<>();
                            map.put("tradeDate",iPlatformDate.getSysDateStr());
                            map.put("finprodId",tradeDto.getFinprodId());
                            map.put("portfolioId",inputDto.getPortfolioId());
                            if (StringUtil.isNotEmpty(tradeDto.getType8())&&StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                                    tradeDto.getType8())) {
                                map.put("invAim",tradeDto.getInvAim());
                            }
                            BigDecimal position = bussTrdAvailableCashQueryService.getSecPosition(map);
                            position = position==null?BigDecimal.ZERO:position;
                            BigDecimal 买入数量 = BigDecimal.ZERO;
                            BigDecimal amount = tradeDto.getAmount();
                            if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                                    tradeDto.getType8())) {
                                amount = ArithmeticUtil.divide(amount,new BigDecimal(100),2);
                            }
                            String key = tradeDto.getFinprodId();
                            if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                                    tradeDto.getType8())) {
                                key = key +"_"+tradeDto.getInvAim();
                            }
                            if(买入map.get(tradeDto.getFinprodId())!=null)
                            买入数量 = 买入map.get(tradeDto.getFinprodId()).stream().filter(x->!StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                                    tradeDto.getType8())||StringUtil.equals(tradeDto.getInvAim(),x.getInvAim()))
                                    .map(PtlMockTestTradeDto::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
                            BigDecimal 已经卖出数量 = 卖出map.get(key) ==null ?BigDecimal.ZERO:卖出map.get(key);
                            position = position.add(买入数量).subtract(已经卖出数量);
                            卖出map.put(key,已经卖出数量.add(amount));
                            if(StringUtil.isNotEmpty(amount)&& position.compareTo(amount)<0){
                                errorList.add(new ValidMessage(this.getMessage("PtlMockTest_E_0033", tableName,
                                        String.valueOf(i + 1))));
                            }
                        }
                    }

                }
            }
            return errorList;
        }
    }
}
