/*
 * 文件名：InterCotroller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.esbreceive.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.eai.data.Array;
import com.dc.eai.data.CompositeData;
import com.dc.eai.data.Field;
import com.dcfs.esb.client.converter.PackUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.cache.InterfCacheUtil;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.esbreceive.dto.EsbReqRspDto;

/**
 * 接收并处理ESB报文Controller
 * 
 * @author zhangrui1
 * @version 1.0 2018年9月3日
 * @see InterfCotroller
 * @since 1.0
 */
@RestController
@RequestMapping("/esbConsumer")
public class InterfCotroller {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 接收ESB请求
     * 
     * @param param 请求
     * @return EsbReqRspDto 结果
     * @see
     * @since 1.0
     */
    @PostMapping("searchOrReceive")
    public EsbReqRspDto searchOrReceive(@RequestBody EsbReqRspDto param) {
        logger.info("====searchOrReceive start====");
        String reqStr = param.getReqStr();
        String rspStr = null;
        CompositeData reqData = null;
        CompositeData rspData = null;
        int step = 0;
        String uuid = IDGenerator.generate();
        try {
            // 2、拆报文
            step++ ;
            reqData = PackUtil.unpackXmlStr(reqStr.trim());

            logger.info("====receive====");
            logger.info(reqStr);

            // 3、服务处理
            step++ ;
            rspData = this.doDispatcher(reqData, uuid);
            if (rspData == null) {
                throw new NullPointerException("响应数据为空");
            }

            // 4、组包
            step++ ;
            rspStr = PackUtil.packXmlStr(rspData);

            logger.info("====send====");
            logger.info(rspStr);
        }
        catch (Exception e) {

            if (rspData == null) {
                rspData = new CompositeData();
            }

            // 错误码
            String code = new StringBuffer(
                InterfCacheUtil.getSysParam(InterfConstant.INF_CONSUMER_ID)).append(
                    InterfConstant.ESB_RET_CODE_0099000).append(step).toString();

            // 错误信息
            String msg = new StringBuffer("ERROR: ").append(e.toString()).toString();
            rspStr = doException(rspData, code, msg);
        }
        param.setRspStr(rspStr);
        logger.info("====searchOrReceive end====");
        return param;
    }

    /**
     * 功能 :调用调度器进行处理，里面的逻辑由服务系统实现
     * 
     * @param reqData 请求数据
     * @param uuid 处理识别号
     * @return CompositeData 应答数据
     * @throws Exception 异常
     */
    private CompositeData doDispatcher(CompositeData reqData, String uuid) {
        CompositeData sysHead = reqData.getStruct(InterfConstant.SYS_HEAD);

        // 服务代码
        String serviceCode = (String)sysHead.getField(InterfConstant.SERVICE_CODE).getValue();
        // 服务场景
        String serviceScene = (String)sysHead.getField(InterfConstant.SERVICE_SCENE).getValue();
        String tranCode = serviceCode + "-" + serviceScene;
        return ServiceMapperUtil.getDoEsbService(tranCode).doReceivedData(reqData, uuid);
    }

    /**
     * 功能 : 异常处理
     * 
     * @param rspData 应答数据
     * @param code 结果码
     * @param msg 错误信息
     * @return String 组包字符串
     */
    private String doException(CompositeData rspData, String code, String msg) {
        try {
            CompositeData sysHead = rspData.getStruct(InterfConstant.SYS_HEAD);
            Array ret = sysHead.getArray(InterfConstant.RET);
            CompositeData ret0 = ret.getStruct(0);
            Field field = ret0.getField(InterfConstant.RET_CODE);
            field.setValue(code);
            field = ret0.getField(InterfConstant.RET_MSG);
            field.setValue(msg);

            return PackUtil.packXmlStr(rspData);
        }
        catch (Exception ex) {
            return StringUtil.EMPTY;
        }
    }
}
