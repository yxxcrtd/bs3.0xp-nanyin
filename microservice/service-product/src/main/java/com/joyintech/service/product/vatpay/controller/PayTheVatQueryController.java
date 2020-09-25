package com.joyintech.service.product.vatpay.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.vatpay.service.PayTheVatQueryService;
import com.joyintech.tams.invest.common.dto.common.TrdCommonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: 2.1.0
 * @description:
 * @author: 强成西
 * @create: 2019-09-18 19:41
 **/
@RestController("PayTheVatQueryControllerCtl_dtx")
@RequestMapping("/PayTheVatQueryController")
public class PayTheVatQueryController {

    @Autowired
    private PayTheVatQueryService payTheVatQueryService;

    @Transactional
    @DTXTransaction
    @PostMapping("doCommitEx")
    public String doCommitEx(@RequestBody TrdCommonParam trdCommonParam) {
        return payTheVatQueryService.doCommitEx(trdCommonParam);
    }
}
