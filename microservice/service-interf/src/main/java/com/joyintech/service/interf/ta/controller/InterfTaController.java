/*
 * 文件名：InterfTaController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.interf.core.annotation.ThreadPoolExcuted;
import com.joyintech.service.interf.core.controller.ProdController;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.prodmodify.dto.ProdModifyDto;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 统一TA接口对接入口
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月21日
 * @see InterfTaController
 * @since 1.0
 */
@RestController
@ThreadPoolExcuted
public class InterfTaController extends ProdController {

    /**
     * 调用模块
     */
    private static final String CALLER_TA = "caller_ta";

    @RequestMapping("/tzConsumer/ta/prodInfo")
    public List<InterfFinProdDto> prodInfo(@RequestBody Map<String, Object> param) {
        String caller = getCallModel(param);
        setControllerInfo(CALLER_TA, caller);
        // 取得产品列表
        List<InterfFinProdDto> prodList = getDataList(param);
        // 产品预创建接口需要画面ID,其他接口不需要
        if (!"TA150006".equals(caller)) {
            // 调用接口
            excuteService(prodList, true);

            // 返回结果
            return prodList;
        }
        String pageId = getPageId(param);
        List<InterfFinProdDto> resultList = new ArrayList<>();
        for (InterfFinProdDto finProd : prodList) {
            ProdModifyDto dto = new ProdModifyDto(finProd.getFinProdId());
            // 设置内容
            BeanUtil.copyPropertiesIgnoreNull(dto, finProd);
            dto.setPageId(pageId);
            dto.setBusiType(DictCodeList.共通业务类型_收益型产品认申购管理);
            resultList.add(dto);
        }
        // 调用接口
        excuteService(resultList, true);

        // 返回结果
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SendEsbService<?, ?> getService(InterfFinProdDto t) {
        return ServiceMapperUtil.getTAService((String)getControllerInfo(CALLER_TA));
    }

}
