
package com.joyintech.tams.poc.portfolio.ptlmock.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.PtlMockTest002Dto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.PtlMockTestTradeDto;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        {
            ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
            PtlMockTest002Dto inputDto = (PtlMockTest002Dto)value;

            // 虚拟组合
//            if (StringUtil.equals(DictCodeListExt3.组合类型_虚拟组合.getValue(),
//                inputDto.getPortfolioType())) {
//                // 业绩基准% 必填
//                if (inputDto.getBaseRule() == null) {
//                    errorList.add(new ValidMessage(
//                        getMessage("BUSS_COM_E_0038", getMessage("PtlMockTest002.baseRule"))));
//                }
//                // 管理费率% 必填
//                if (inputDto.getMgrFeeRate() == null) {
//                    errorList.add(new ValidMessage(
//                        getMessage("BUSS_COM_E_0038", getMessage("PtlMockTest002.mgrFeeRate"))));
//                }
//                // 募集金额（万元） 必填
//                if (inputDto.getRaiseAmtPlan() == null) {
//                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
//                        getMessage("PtlMockTest002.repRaiseAmtPlan"))));
//                }
//            }

            // 模拟交易列表不能为空
            if (!StringUtil.isNotEmpty(inputDto.getPageMockDealList())
                || inputDto.getPageMockDealList().size() == 0) {
                errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0018")));
            }
            else {
                // 校验交易列表数据
                List<PtlMockTestTradeDto> list = inputDto.getPageMockDealList();
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
                    if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                        tradeDto.getType8())) {
                        if (StringUtil.isBlank(tradeDto.getInvAim())) {
                            errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0022",
                                tableName, String.valueOf(i + 1), "投资目的")));
                        }
                    }
                    if (!StringUtil.isNotEmpty(tradeDto.getAmount())) {
                        errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0022", tableName,
                            String.valueOf(i + 1), "交易数量")));
                    }
                    if (!StringUtil.isNotEmpty(tradeDto.getTradePrice())) {
                        errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0022", tableName,
                            String.valueOf(i + 1), "交易净价")));
                    }
                    if (!StringUtil.isNotEmpty(tradeDto.getMdate())) {
                        errorList.add(new ValidMessage(getMessage("PtlMockTest_E_0022", tableName,
                            String.valueOf(i + 1), "到期日")));
                    }
                    if (!StringUtil.isNotEmpty(tradeDto.getYield())) {
                        errorList.add(new ValidMessage(this.getMessage("PtlMockTest_E_0022", tableName,
                            String.valueOf(i + 1), "到期收益率")));
                    }
                }
            }
            return errorList;
        }
    }
}
