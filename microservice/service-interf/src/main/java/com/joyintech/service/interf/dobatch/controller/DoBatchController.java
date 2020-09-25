/*
 * 文件名：DoBatchController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.dobatch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.ThreadPoolExcuted;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.controller.BaseController;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.dobatch.dto.BatchResultDto;
import com.joyintech.service.interf.i448103.service.Interf448103Service;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.service.interf.prodtrade.service.ProdFoundService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 处理定时批量任务（发送ESB报文用）
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月7日
 * @see DoBatchController
 * @since 1.0
 */
@RestController
@ThreadPoolExcuted
public class DoBatchController extends BaseController<InterfFinProdDto> {

    /**
     * 返回值
     */
    private static final String RESULT = "result";

    /**
     * 成立存续期到期产品查询Service
     */
    @Autowired
    private ProdFoundService prodService;

    /**
     * 成立存续到期类型
     */
    private static final String TYPE = "type";

    /**
     * 处理入口
     * 
     * @param dto 请求参数
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/batchConsumer/prodBatch")
    public BatchResultDto prodBatch(@RequestBody BatchResultDto dto) {
        setControllerInfo(RESULT, dto);
        setType();
        excuteService(getReusetProdList(dto), true);
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doResult(InterfFinProdDto dto, SendEsbService<?, ?> service) {
        BatchResultDto result = (BatchResultDto)getControllerInfo(RESULT);
        result.setResult(service.isHandleOK());
        result.setErrMsg(service.getErrMsg());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SendEsbService<?, ?> getService(InterfFinProdDto t) {
        BatchResultDto result = (BatchResultDto)getControllerInfo(RESULT);
        if (StringUtil.equals(result.getBatchName(), "prodQuoteSendTask")) {
            return ServiceMapperUtil.getApplicationContext().getBean(Interf448103Service.class);
        }
        ProdAliveService<?, ?> service = ServiceMapperUtil.getProdBatchService(
            result.getBatchName());
        // 产品成立
        if (StringUtil.equals(result.getBatchName(), "prodFoundNSearchTask")) {
            service.setPinganBusiType(DictCodeListExt1.平安业务类型_产品成立.getValue());
        }
        // 产品到期
        else if (StringUtil.equals(result.getBatchName(), "prodEndNDaySearchTask")) {
            service.setPinganBusiType(DictCodeListExt1.平安业务类型_产品到期.getValue());
        }
        // 产品存续
        else {
            service.setPinganBusiType(DictCodeListExt1.平安业务类型_产品申购.getValue());
        }
        return service;
    }

    /**
     * 设置交易类型
     * 
     * @see
     * @since 1.0
     */
    private void setType() {
        BatchResultDto result = (BatchResultDto)getControllerInfo(RESULT);
        InterfDictCodeList type = null;
        // 产品成立
        if (StringUtil.equals(result.getBatchName(), "prodFoundNSearchTask")) {
            type = InterfDictCodeList.交易类型_成立;
        }
        // 产品到期
        else if (StringUtil.equals(result.getBatchName(), "prodEndNDaySearchTask")) {
            type = InterfDictCodeList.交易类型_到期;
        }
        // 产品存续
        else {
            type = InterfDictCodeList.交易类型_存续;
        }
        setControllerInfo(TYPE, type);
    }

    /**
     * 取得产品信息列表
     * 
     * @param dto 请求参数
     * @return 产品信息列表
     * @see
     * @since 1.0
     */
    private List<InterfFinProdDto> getReusetProdList(BatchResultDto dto) {
        if (dto == null) {
            return new ArrayList<>();
        }
        List<String> prodIdList = dto.getProdList();
        if (StringUtil.isEmptyList(prodIdList)) {
            return new ArrayList<>();
        }
        BatchResultDto result = (BatchResultDto)getControllerInfo(RESULT);
        InterfDictCodeList type = (InterfDictCodeList)getControllerInfo(TYPE);
        List<InterfFinProdDto> prodList = new ArrayList<>();
        // 报价定时任务
        if (StringUtil.equals(result.getBatchName(), "prodQuoteSendTask")) {
            for (String prodId : prodIdList) {
                InterfFinProdDto prodDto = new InterfFinProdDto();
                prodDto.setFinProdId(prodId);
                prodList.add(prodDto);
            }
        }
        // 产品成立存续到期定时任务
        else {
            prodList.addAll(prodService.getProdFoundAliveEndInfo(type, getSysDate(), prodIdList));
        }
        return prodList;
    }
}
