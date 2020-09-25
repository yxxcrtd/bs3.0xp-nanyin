package com.joyintech.tams.invest.trade.primarybiddingmanage.validator;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.BidOrderBidPriceDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.dto.TrdBidPriceDto;
import com.joyintech.tams.invest.trade.primarybiddingmanage.service.PrimaryBiddingManage003Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 裴家俊
 * @version V1.0
 */
@Component
public class PrimaryBidPriceValidator implements BeanValidator {


    @Autowired
    private PrimaryBiddingManage003Service primaryBiddingManage003;

    /**
     * 平台日期
     */
    @Autowired
    private IPlatformDate platformDateImpl;
    @Override
    public List<ValidMessage> valid(Object value) {
    	TrdBidPriceDto dto = (TrdBidPriceDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (StringUtils.isBlank(dto.getIsWin())){
            errorList.add(new ValidMessage("isWin",
                getMessage("BUSS_COM_E_0038", "是否中标")));
        }
        if(dto.getIsWin().equals("Y")){
	        if (!StringUtil.isNotEmpty(dto.getPayType())) {
	        	errorList.add(new ValidMessage("payType",
	                getMessage("BUSS_COM_E_0038", "缴款方式")));
	        }
	        if (!StringUtil.isNotEmpty(dto.getPayDate())&&!dto.getOperateType().equals("autoAllocation")) {
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
        if (null != bidOrderBidPriceList){
            BigDecimal winPrinAmtSum = BigDecimal.ZERO;
            BigDecimal theWinPrinAmtSum = BigDecimal.ZERO;
            for (BidOrderBidPriceDto bidOrderBidPriceDto : bidOrderBidPriceList) {
                winPrinAmtSum = ArithmeticUtil.doAdd(bidOrderBidPriceDto.getWinPrinAmt(),winPrinAmtSum);
                theWinPrinAmtSum = ArithmeticUtil.doAdd(bidOrderBidPriceDto.getTheWinPrinAmt(),theWinPrinAmtSum);
            }
            if (null != dto.getWinPrinAmt() && winPrinAmtSum.compareTo(theWinPrinAmtSum) != 0){
                errorList.add(new ValidMessage("",
                    getMessage("BUSS_COM_E_0048", "实际中标券面总额之和","理论中标券面总额之和")));
            }
        }
        Map<String,Object> params = new HashMap<>();
        params.put("oFinprodId",dto.getFinprodId());
        //得到债券资产信息
//        List<FinProduct> finProducts = restTemplate.postForList(
//                ServiceApplication.ENTRUST_SERVICE_NAME + "/Etrbondbiddingresult/findFinProductByOFinprodId", params,
//                FinProduct.class);
        List<FinProduct> finProducts = primaryBiddingManage003.findFinProduct(params);
        if (finProducts.size() !=1){
            errorList.add(new ValidMessage("",
                    getMessage("债券一级资产必须关联一个债券资产")));
        }
        if (!finProducts.isEmpty() && finProducts.get(0).getMdate().compareTo(DateUtil.platDateFromCache())<0){
            errorList.add(new ValidMessage("",
                    getMessage("债券资产交割日必须小于资产到期日")));
        }
        return errorList;
    }
}
