package com.joyintech.tams.invest.trade.primarybiddingmanage.validator;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.BidOrderBidPriceDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.TrdBiddingManage003Dto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.service.PrimaryBiddingManage003Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : 裴家俊
 * @version V1.0
 */
@Component
public class PrimaryBiddingManage003Validator implements BeanValidator {


    @Autowired
    private PrimaryBiddingManage003Service primaryBiddingManage003;

    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> valid(Object value) {
        TrdBiddingManage003Dto dto = (TrdBiddingManage003Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        //判断流程中是否存在相同的数据
        WflValidInputParameter param = new WflValidInputParameter();
        param.setBusinessDataPk1(dto.getBidId());
        param.setBusinessDataPk2(dto.getOperateType());
        param.setPageId("PrimaryBiddingManage003");
        String processStatus = "";
        if (null != dto.getWorkflowId()){
            processStatus = primaryBiddingManage003.findProcessStatusByWorkflowId(dto.getWorkflowId());
        }
        if (null != mstWorkflowCommonService &&
            mstWorkflowCommonService.isInWflProcess(param) &&
                !"BACK".equals(processStatus)) {
            errorList.add(new ValidMessage(this.getMessage("BUSS_COM_MDATE_0002")));
            return errorList;
        }
        //如果不是撤销按钮则验证
        if (null != dto.getOperateType() && !"revoke".equals(dto.getOperateType())){
            if (dto.getIsWin().equals("Y")) {
                if (!StringUtil.isNotEmpty(dto.getPayType())) {
                    errorList.add(new ValidMessage("payType",
                            getMessage("BUSS_COM_E_0038", "缴款方式")));
                }
                if (!StringUtil.isNotEmpty(dto.getPayDate()) && !dto.getOperateType().equals("autoAllocation")) {
                    errorList.add(new ValidMessage("payDate",
                            getMessage("BUSS_COM_E_0038", "缴款日")));
                }
                if (!StringUtil.isNotEmpty(dto.getWinPrice())) {
                    errorList.add(new ValidMessage("winPrice",
                            getMessage("BUSS_COM_E_0038", "中标标位")));
                }
                if (!StringUtil.isNotEmpty(dto.getWinPrinAmt())) {
                    errorList.add(new ValidMessage("winPrinAmt",
                            getMessage("BUSS_COM_E_0038", "中标券面总额")));
                }
                if (!StringUtil.isNotEmpty(dto.getWinType())) {
                    errorList.add(new ValidMessage("winType",
                            getMessage("BUSS_COM_E_0038", "中标方式")));
                }
            }
            List<BidOrderBidPriceDto> bidOrderBidPriceList = dto.getBidOrderBidPriceList();
            if (null != bidOrderBidPriceList && StringUtil.equals(dto.getIsWin(), DictCodeList.是否_是.getValue())) {
                BigDecimal winPrinAmtSum = BigDecimal.ZERO;
                BigDecimal theWinPrinAmtSum = BigDecimal.ZERO;
                boolean flag = false;
                for (BidOrderBidPriceDto bidOrderBidPriceDto : bidOrderBidPriceList) {
                    winPrinAmtSum = ArithmeticUtil.doAdd(bidOrderBidPriceDto.getWinPrinAmt(), winPrinAmtSum);
                    theWinPrinAmtSum = ArithmeticUtil.doAdd(bidOrderBidPriceDto.getTheWinPrinAmt(), theWinPrinAmtSum);
                    if (null != bidOrderBidPriceDto.getWinPrinAmt() && bidOrderBidPriceDto.getBidPrinAmt().compareTo(bidOrderBidPriceDto.getWinPrinAmt())<0){
                        errorList.add(new ValidMessage("",
                                getMessage("BUSS_COM_E_0003", "实际中标券面总额", "投标券面总额")));
                    }
                    if (null != bidOrderBidPriceDto.getWinPrice()){
                        flag = true;
                    }
                }
                //如果分配明细列表数据中标标位都为null，说明没有自动分配，则给出提示
                if (!flag){
                    errorList.add(new ValidMessage("",
                            getMessage("TRD_PRIMARY_BIDDING_001")));
                }
                if (null != dto.getWinPrinAmt() && winPrinAmtSum.compareTo(theWinPrinAmtSum) != 0) {
                    errorList.add(new ValidMessage("",
                            getMessage("BUSS_COM_E_0048", "实际中标券面总额之和", "理论中标券面总额之和")));
                }
            }
        }else {
            Boolean tradeIdByBidId = primaryBiddingManage003.findTradeIdByBidId(dto.getBidId());
            if (!tradeIdByBidId){
                errorList.add(new ValidMessage("",
                        getMessage("TRD_PRIMARY_BIDDING_002")));
            }
        }
        return errorList;
    }
}
