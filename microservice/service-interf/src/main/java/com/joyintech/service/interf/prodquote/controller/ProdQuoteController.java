/*
 * 文件名：ProdQuoteController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodquote.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.interf.core.annotation.ThreadPoolExcuted;
import com.joyintech.service.interf.core.controller.ProdController;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.prodquote.service.Interf150005Service;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;

/**
 * 产品报价接口
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月9日
 * @see ProdQuoteController
 * @since 1.0
 */
@RestController
@ThreadPoolExcuted
public class ProdQuoteController extends ProdController {

    /**
     * 报价接口150005-详细处理
     */
    @Autowired
    private Interf150005Service interf150005Service;

    /**
     * 产品报价
     * 
     * @param param 请求参数
     * @return List<InterfFinProdDto> 成立产品列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/tzConsumer/prodQuote")
    public List<InterfFinProdDto> prodQuote(@RequestBody Map<String, Object> param) {
        // 取得产品列表
        List<InterfFinProdDto> prodList = getDataList(param);

        // 调用接口
        excuteService(prodList, true);

        // 返回结果
        return prodList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SendEsbService<?, ?> getService(InterfFinProdDto t) {
        return interf150005Service;
    }

}
