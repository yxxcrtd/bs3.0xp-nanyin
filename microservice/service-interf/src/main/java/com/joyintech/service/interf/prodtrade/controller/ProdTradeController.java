package com.joyintech.service.interf.prodtrade.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.interf.core.annotation.ThreadPoolExcuted;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.controller.ProdController;
import com.joyintech.service.interf.core.service.ProdAliveService;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.i291002.service.Interf291002Service;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品成立同步、产品申赎同步、产品到期同步
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see ProdTradeController
 * @since 1.0
 */
@RestController
@ThreadPoolExcuted
public class ProdTradeController extends ProdController {

    /**
     * 交易类型：成立存续到期
     */
    private static final String TYPE_PROD = "type_prod";

    /**
     * 净值型产品申购赎回确认数据查询
     */
    private Interf291002Service inf291002Svc;

    /**
     * 产品成立同步
     * 
     * @param param 请求参数
     * @return List<String> 成立产品列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/tzConsumer/prodFound")
    public List<InterfFinProdDto> prodFound(@RequestBody Map<String, Object> param) {
        setControllerInfo(TYPE_PROD, InterfDictCodeList.交易类型_成立);
        return handleProdList(param);
    }

    /**
     * 产品存续期交易同步方法
     * 
     * @param param 请求参数
     * @return List<String> 成立产品列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/tzConsumer/prodAlive")
    public List<InterfFinProdDto> prodAlive(@RequestBody Map<String, Object> param) {
        setControllerInfo(TYPE_PROD, InterfDictCodeList.交易类型_存续);
        return handleProdList(param);
    }

    /**
     * 产品存续期交易同步方法
     * 
     * @param param 请求参数
     * @return List<String> 成立产品列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/tzConsumer/prodEnd")
    public List<InterfFinProdDto> prodEnd(@RequestBody Map<String, Object> param) {
        setControllerInfo(TYPE_PROD, InterfDictCodeList.交易类型_到期);
        return handleProdList(param);
    }

    /**
     * 取得对象产品List
     * 
     * @param param request请求参数
     * @return 对象产品List
     * @see
     * @since 1.0
     */
    private List<InterfFinProdDto> handleProdList(Map<String, Object> param) {
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
//        if (DictCodeList.销售部门_统一TA.getValue().equals(t.getSaleDept())
//            && BussinessDictCodeList.处理模式_净值型.getValue().equals(t.getType())) {
//            return inf291002Svc;
//        }
        ProdAliveService<?, ?> service = null;
        InterfDictCodeList type = (InterfDictCodeList)getControllerInfo(TYPE_PROD);
        if (InterfDictCodeList.交易类型_成立.equals(type)) {
            service = ServiceMapperUtil.getProdStrService(t.getType() + t.getSaleDept());
            service.setPinganBusiType(DictCodeListExt1.平安业务类型_产品成立.getValue());
        }
        else if (InterfDictCodeList.交易类型_存续.equals(type)) {
            service = ServiceMapperUtil.getProdAlvService(t.getType() + t.getSaleDept());
            service.setPinganBusiType(DictCodeListExt1.平安业务类型_产品申购.getValue());
        }
        else if (InterfDictCodeList.交易类型_到期.equals(type)) {
            service = ServiceMapperUtil.getProdEndService(t.getType() + t.getSaleDept());
            service.setPinganBusiType(DictCodeListExt1.平安业务类型_产品到期.getValue());
        }
        return service;
    }
}
