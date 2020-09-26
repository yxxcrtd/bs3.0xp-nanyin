///*
// * 文件名：ValInvest2JixuAsyncService.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管管理系统V2.0
// * 修改人：admin
// * 修改时间：2019年11月7日
// * 修改内容：新建
// * 系统名称：理财资管管理系统V2.0
// */
//
//package com.joyintech.tams.valuation.api.service.impl;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import com.jixu.common.type.ResultContainer;
//import com.jixu.joyin2jixu.service.Joyin2JixuInterface;
//
///**
// * 〈一句话功能简述〉
// * 〈功能详细描述〉
// * @author admin
// * @version 1.0 2019年11月7日
// * @see ValInvest2JixuAsyncService
// * @since 1.0
// */
//@Service
//public class ValInvest2JixuAsyncService {
//
//    private static final Logger logger = LoggerFactory.getLogger(ValInvest2JixuAsyncService.class);
//
//    @Autowired
//    private Joyin2JixuInterface joyin2JixuInterface;
//
//    /**
//     * 异步处理几许
//     *
//     * @param jsonMsg 参数
//     * @return 空（因为异步，所以返回时空）
//     * @see
//     * @since 1.0
//     */
//    @Async
//    public ResultContainer sendInfoAsync(String jsonMsg) {
//        logger.info("调用【jixu】接口异步处理开始-----------------------");
//        ResultContainer resultContainer = new ResultContainer();
//        try {
//            resultContainer = joyin2JixuInterface.executeJixuMessage(jsonMsg);
//            if (!resultContainer.isCorrectFlag()) {
//                logger.info("调用【jixu】接口错误字段：" + resultContainer.getErrorCode());
//                logger.info("调用【jixu】接口错误信息描述：" + resultContainer.getErrorMsg());
//                logger.debug("调用【jixu】接口错误字段：" + resultContainer.getErrorCode());
//                logger.debug("调用【jixu】接口错误信息描述：" + resultContainer.getErrorMsg());
//            }
//        }
//        catch (Exception e) {
//            resultContainer.setCorrectFlag(false);
//            e.printStackTrace();
//            logger.info("调用【jixu】接口发生异常:-----------------------");
//            logger.info(e.getMessage());
//        }
//        logger.info("调用【jixu】接口异步处理结束-----------------------");
//        return resultContainer;
//    }
//}
