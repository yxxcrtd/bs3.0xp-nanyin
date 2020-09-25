/*
 * 文件名：ConfirmProdInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.prodmodify.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.controller.BaseController;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.i448001.service.Interf448001Service;
import com.joyintech.service.interf.prodmodify.dto.ProdModifyDto;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品信息确认接口-448001
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月8日
 * @see ConfirmProdInfoController
 * @since 1.0
 */
@RestController
public class ConfirmProdInfoController extends BaseController<ProdModifyDto> {

    /**
     * 共同业务类型
     */
    private static final String BUSITYPE_448001 = "busiType_448001";

    /**
     * 发送零售
     */
    @Autowired
    private Interf448001Service interf448001Svc;

    /**
     * 产品新建审批通过
     * 
     * @param param 请求参数
     * @return List<String> 新建产品列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/tzConsumer/prodNew")
    public List<InterfFinProdDto> prodNew(@RequestBody Map<String, Object> param) {
        setControllerInfo(BUSITYPE_448001, DictCodeList.共通业务类型_收益型产品认申购管理);
        List<ProdModifyDto> prodDtoList = handleProdList(param);
        return getListFromChild(prodDtoList);
    }

    /**
     * 产品到期日调整审批通过
     * 
     * @param param 请求参数
     * @return List<String> 新建产品列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/tzConsumer/prodModify")
    public List<InterfFinProdDto> prodModify(@RequestBody Map<String, Object> param) {
        setControllerInfo(BUSITYPE_448001, DictCodeList.共通业务类型_到期日调整);
        List<ProdModifyDto> prodDtoList = handleProdList(param);
        return getListFromChild(prodDtoList);
    }

    /**
     * 取得对象产品List
     * 
     * @param param request请求参数
     * @return 对象产品List
     * @see
     * @since 1.0
     */
    private List<ProdModifyDto> handleProdList(Map<String, Object> param) {
        List<InterfFinProdDto> prodList = getDataList(param);
        String pageId = getPageId(param);
        List<ProdModifyDto> resultList = new ArrayList<>();
        for (InterfFinProdDto finProd : prodList) {
            ProdModifyDto dto = new ProdModifyDto(finProd.getFinProdId());
            // 设置内容
            BeanUtil.copyPropertiesIgnoreNull(dto, finProd);
            dto.setPageId(pageId);
            dto.setBusiType((DictCodeList)getControllerInfo(BUSITYPE_448001));
            resultList.add(dto);
        }
        // 调用接口
        excuteService(resultList, true);
        return resultList;
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

    /**
     * 构建返回类型
     * 
     * @param prodList 接口参数-处理结果列表
     * @return 返回类型列表
     * @see
     * @since 1.0
     */
    private List<InterfFinProdDto> getListFromChild(List<ProdModifyDto> prodList) {
        List<InterfFinProdDto> resultList = new ArrayList<>();
        for (ProdModifyDto prod : prodList) {
            InterfFinProdDto dto = new InterfFinProdDto(prod.getFinProdId());
            dto.setProdId(prod.getProdId());
            dto.setHandleOK(prod.isHandleOK());
            dto.setErrMsg(prod.getErrMsg());
            resultList.add(dto);
        }
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SendEsbService<?, ?> getService(ProdModifyDto t) {
        return interf448001Svc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doResult(ProdModifyDto dto, SendEsbService<?, ?> service) {
        dto.setErrMsg(service.getErrMsg());
        dto.setHandleOK(service.isHandleOK());
    }
}
