/*
 * 文件名：ProdController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;

/**
 * 产品成立，产品存续期、产品到期模块抽象Controller
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月30日
 * @see ProdController
 * @since 1.0
 */
public abstract class ProdController extends BaseController<InterfFinProdDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doResult(InterfFinProdDto dto, SendEsbService<?, ?> service) {
        dto.setHandleOK(service.isHandleOK());
        dto.setErrMsg(service.getErrMsg());
        dto.setInterfId(service.getInterfId());
    }

    /**
     * 获取 产品列表
     * 
     * @param param 请求参数
     * @return 产品列表
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public List<InterfFinProdDto> getDataList(Map<String, Object> param) {
        List<InterfFinProdDto> resultList = new ArrayList<>();
        if (param != null) {
            for (Map<String, Object> obj : (List<Map<String, Object>>)param.get(
                InterfConstant.DATA_LIST)) {
                resultList.add(BeanUtil.mapToObject(obj, InterfFinProdDto.class));
            }
        }
        return resultList;
    }
}
