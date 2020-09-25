/*
 * 文件名：PreTrdRepoTrade002DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王伟
 * 修改时间：2019年12月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.pretrdrepotrade.validator;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.trade.common.service.TradeCommonService;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.pretrdrepotrade.dto.PreTrdRepoTrade002Dto;
import com.joyintech.tams.invest.trade.pretrdrepotrade.service.PreTrdRepoTrade002Service;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTrade002Dto;
import com.joyintech.tams.invest.trade.trdrepotrade.service.TrdRepoTrade002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 质押式回购交易预录入新建画面：PreTrdRepoTrade002DtoValidator
 * @author 王伟
 * @version 1.0 2019年12月30日
 * @see com.joyintech.tams.invest.trade.pretrdrepotrade.validator.PreTrdRepoTrade002DtoValidator
 * @since 1.0
 */
@Component
public class PreTrdRepoTrade002DtoValidator implements TradeCommonValidator {
    /**
     * TrdRepoTrade002Service
     */
    @Autowired(required = false)
    private TrdRepoTrade002Service trdRepoTrade002Service;
    /**
     * TrdRepoTrade002Service
     */
    @Autowired(required = false)
    private PreTrdRepoTrade002Service preTrdRepoTrade002Service;
    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private TradeCommonService tradeCommonService;
    @Autowired
    private FinProductMapper finProductMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        PreTrdRepoTrade002Dto inputDto = (PreTrdRepoTrade002Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        List<PledgeBondInfoDto> pledgeBondList = inputDto.getBussRepoSecPtl().getPledgeBondList();
        List<PledgeBondInfoDto> pledgeBondListDetailed = inputDto.getBussRepoSecPtl().getPledgeBondListDetailed();
        boolean fromEtrust = false;
        BigDecimal prinAmt = inputDto.getPrinAmt();

        //检查是否添加质押品
        if (StringUtil.isEmptyList(pledgeBondListDetailed)){
            errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_003")));
        }else {
            //正回购交易金额不能大于质押券信息中汇总券面总额
            if (StringUtil.equals(inputDto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())){
                BigDecimal pledgeAmt = BigDecimal.ZERO;
                for (PledgeBondInfoDto pledgeBondInfoDto : pledgeBondListDetailed){
                    FinProduct finProduct = finProductMapper.selectByPrimaryKey(pledgeBondInfoDto.getFinprodId());
                    if (inputDto.getMdate() != null && finProduct != null && finProduct.getMdate() != null
                        && inputDto.getMdate().after(finProduct.getMdate())){
                        errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_010")));
                    }

                    pledgeAmt = ArithmeticUtil.doAdd(2,RoundingMode.HALF_UP,pledgeAmt,pledgeBondInfoDto.getPledgePrin());
                }
                if (pledgeAmt.compareTo(prinAmt) == -1){
                    errorList.add(new ValidMessage(getMessage("TRD_E_00121")));
                }
            }

        }

        //检查组合分配的和金额信息是否一致
        if (!StringUtil.isEmptyList(pledgeBondList)){
            BigDecimal number = BigDecimal.ZERO;
            for (PledgeBondInfoDto pledgeBondInfoDto : pledgeBondList){
                if (StringUtil.isNotEmpty(pledgeBondInfoDto.getEntrustId())){
                    fromEtrust = true;
                }
                if(pledgeBondInfoDto.getPrinAmt() != null){
                    number = ArithmeticUtil.doAdd(2, RoundingMode.HALF_UP,number,pledgeBondInfoDto.getPrinAmt());
                    if (StringUtil.equals(inputDto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue()) && pledgeBondInfoDto.getAvalibleCash() != null
                            && pledgeBondInfoDto.getPrinAmt().compareTo(pledgeBondInfoDto.getAvalibleCash()) == 1){
                        errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_005",pledgeBondInfoDto.getPortfolioId())));
                    }
                }



            }
            if (!ArithmeticUtil.equals(prinAmt,number)){
                errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_004")));
            }
        }
        //检查从指令接收页面进行交易预录入信息
        if (fromEtrust){
            if (StringUtil.equals(inputDto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())){
                for (PledgeBondInfoDto pledgeBondInfoDto : pledgeBondList){
                    if (pledgeBondInfoDto.getPrinAmt() != null && new BigDecimal(pledgeBondInfoDto.getEntrustSurAmt()).compareTo(pledgeBondInfoDto.getPrinAmt()) == -1){
                        errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_006",pledgeBondInfoDto.getPortfolioId())));
                    }
                }
                if (inputDto.getEntrustPrice() != null && inputDto.getEntrustPriceBack() != null
                        && inputDto.getEntrustPrice().compareTo(inputDto.getEntrustPriceBack()) == -1){
                    errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_008")));
                }
            }
            if (StringUtil.equals(inputDto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())){
                if (inputDto.getEntrustPrice() != null && inputDto.getEntrustPriceBack() != null
                        && inputDto.getEntrustPriceBack().compareTo(inputDto.getEntrustPrice()) == -1){
                    errorList.add(new ValidMessage(getMessage("TRD_REPO_PRE_009")));
                }
            }
        }



        return errorList;
    }
}
