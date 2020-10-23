package com.joyintech.service.asset.common.controller;

import com.joyintech.service.asset.common.service.BussFinProductCreditService;
import com.joyintech.tams.common.base.entity.FinProductCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 增信信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年10月11日
 * @see BussFinProductCreditController
 * @since 1.0
 */
@RestController("backBussFinProductCreditController")
@RequestMapping("/BussFinProductCredit")
public class BussFinProductCreditController {
    @Autowired
    private BussFinProductCreditService bussFinProductCreditService;

    @GetMapping("getFinProductCreditByProductId")
    public List<FinProductCredit> getFinProductCreditByProductId(String finprodId){
        return bussFinProductCreditService.getFinProductCreditByProductId(finprodId);
    }
}
