package com.joyintech.service.product.vatpay.service;

import com.joyintech.tams.invest.common.dto.common.TrdCommonParam;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-09-18 19:42
 **/
@Service
public class PayTheVatQueryService {

    @Autowired
    private MapperDelegateService mapperDelegateService;

    public String doCommitEx(TrdCommonParam trdCommonParam) {
        mapperDelegateService.insertAutoClassifyBatch(trdCommonParam.getStlPayOrdersDtos(),
                trdCommonParam.getTrdFinProductDealTotal(),
                trdCommonParam.getTrdProductDeals(),
                trdCommonParam.getPaDeals(),
                trdCommonParam.getDealAdds(),
                trdCommonParam.getTrdTradeFeeDetails(),
                trdCommonParam.getPaTrdFinProductDealTotal()
        );
        return "Success";
    }
}
