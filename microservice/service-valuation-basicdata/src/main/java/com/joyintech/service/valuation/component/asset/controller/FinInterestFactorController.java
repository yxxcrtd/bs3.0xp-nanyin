package com.joyintech.service.valuation.component.asset.controller;

import com.joyintech.service.valuation.component.asset.dto.FinInterestFactorDto;
import com.joyintech.service.valuation.component.asset.service.FinInterestFactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FinInterestFactorController
 * @Description: 计息要素组件微服务
 * @Author
 * @Date 2021/3/4 0004
 * @Version V1.0
 **/

@RestController(value="serviceFinInterestFactorController")
@RequestMapping("/FinInterestFactor")
public class FinInterestFactorController {

    @Autowired
    private FinInterestFactorService finInterestFactorService;

    /**
     * 修改初始化
     * <功能详细描述>
     * @param finprodId
     * @param vdate
     * @param cashType
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getFinInterestFactorByProductId")
    public FinInterestFactorDto getFinInterestFactorByProductId(String finprodId, String vdate, String cashType) throws Exception {
        return finInterestFactorService.getFinInterestFactorByProductId(finprodId,vdate,cashType);
    }
}
