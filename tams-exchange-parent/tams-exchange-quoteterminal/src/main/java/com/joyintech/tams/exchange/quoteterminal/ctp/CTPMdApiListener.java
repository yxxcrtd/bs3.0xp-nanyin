package com.joyintech.tams.exchange.quoteterminal.ctp;

import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteService;
import net.jctp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * create By MAXX on 2020/12/28
 */
public class CTPMdApiListener implements MdApiListener {

    private static final Logger logger = LoggerFactory.getLogger(CTPMdApiListener.class);
    private QuoteService quoteService;

    public CTPMdApiListener(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    private ThreadLocal<QuoteProto.TickBean.Builder> threadLocalBuilder = ThreadLocal.withInitial(QuoteProto.TickBean::newBuilder);

    private boolean isErrorRspInfo(CThostFtdcRspInfoField pRspInfo, String prefix) {
        // 如果ErrorID != 0, 说明收到了错误的响应
        boolean ret = Objects.nonNull(pRspInfo) && (pRspInfo.ErrorID != 0);
        if (ret) {
            logger.error(prefix + " | {}", pRspInfo.ErrorMsg);
        }
        return ret;
    }

    //当客户端与交易托管系统建立起通信连接时（还未登录前），该方法被调用。
    // 本方法在完成初始化后调用，可以在其中完成用户登录任务。
    @Override
    public void OnFrontConnected() {
        String prefix = "rsp OnFrontConnected";
        logger.debug(prefix + " | OnFrontConnected");
    }

    /***
     * @Description: 当客户端与交易托管系统通信连接断开时，该方法被调用。
     *              当发生这个情况后，API会自动重新连接，客户端可不做处理。
     *              自动重连地址，可能是原来注册的地址，也可能是注册的其它可用的通信地址，它由程序自动选择。
     * @Param: [i]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/12/29
     */
    @Override
    public void OnFrontDisconnected(int i) {
        String prefix = "rsp OnFrontDisconnected";
        logger.error(prefix + " | OnFrontDisconnected: {}", i);
    }

    //心跳超时警告
    @Override
    public void OnHeartBeatWarning(int i) {
        String prefix = "rsp OnHeartBeatWarning";
        logger.warn(prefix + " | OnHeartBeatWarning: {}", i);
    }

    //登录请求响应
    @Override
    public void OnRspUserLogin(CThostFtdcRspUserLoginField cThostFtdcRspUserLoginField, CThostFtdcRspInfoField cThostFtdcRspInfoField, int i, boolean b) {
        String prefix = "rsp OnRspUserLogin";
        if (!this.isErrorRspInfo(cThostFtdcRspInfoField, prefix) && Objects.nonNull(cThostFtdcRspUserLoginField)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(cThostFtdcRspUserLoginField), i, b);
        }
    }

    //登出请求响应
    @Override
    public void OnRspUserLogout(CThostFtdcUserLogoutField cThostFtdcUserLogoutField, CThostFtdcRspInfoField cThostFtdcRspInfoField, int i, boolean b) {
    }

    //针对用户请求的出错通知
    @Override
    public void OnRspError(CThostFtdcRspInfoField cThostFtdcRspInfoField, int i, boolean b) {
        String prefix = "rsp OnRspError";
        boolean ret = Objects.nonNull(cThostFtdcRspInfoField) && (cThostFtdcRspInfoField.ErrorID != 0);
        if (ret) {
            logger.error(prefix + " | {},{},{}", cThostFtdcRspInfoField.ErrorMsg, i, b);
        }
    }

    /*** 
     * @Description: 订阅行情应答
     * @Param: [cThostFtdcSpecificInstrumentField, cThostFtdcRspInfoField, i, b] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/12/29 
     */
    @Override
    public void OnRspSubMarketData(CThostFtdcSpecificInstrumentField cThostFtdcSpecificInstrumentField, CThostFtdcRspInfoField cThostFtdcRspInfoField, int i, boolean b) {
        String prefix = "rsp OnRspSubMarketData";
        if (!this.isErrorRspInfo(cThostFtdcRspInfoField, prefix) && Objects.nonNull(cThostFtdcSpecificInstrumentField)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(cThostFtdcSpecificInstrumentField), i, b);
        }
    }

    /*** 
     * @Description: 取消订阅行情应答
     * @Param: [cThostFtdcSpecificInstrumentField, cThostFtdcRspInfoField, i, b] 
     * @return: void
     * @Author: MAXX
     * @Date: 2020/12/29 
     */
    @Override
    public void OnRspUnSubMarketData(CThostFtdcSpecificInstrumentField cThostFtdcSpecificInstrumentField, CThostFtdcRspInfoField cThostFtdcRspInfoField, int i, boolean b) {
        String prefix = "rsp OnRspUnSubMarketData";
        if (!this.isErrorRspInfo(cThostFtdcRspInfoField, prefix) && Objects.nonNull(cThostFtdcSpecificInstrumentField)) {
            logger.debug(prefix + " | {},{},{}", JsonUtils.getJsonFromObject(cThostFtdcSpecificInstrumentField), i, b);
        }
    }

    /*** 
     * @Description: 深度行情通知
     * @Param: [cThostFtdcDepthMarketDataField]
     * @return: void
     * @Author: MAXX
     * @Date: 2020/12/29 
     */
    @Override
    public void OnRtnDepthMarketData(CThostFtdcDepthMarketDataField cThostFtdcDepthMarketDataField) {
        String prefix = "rsp OnRtnDepthMarketData";
        if (Objects.nonNull(cThostFtdcDepthMarketDataField)) {
            logger.debug(prefix + " | {} ", JsonUtils.getJsonFromObject(cThostFtdcDepthMarketDataField));
            QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
            CTPConverter.convertToTick(cThostFtdcDepthMarketDataField, builder);
            quoteService.addTick(builder.build());
        }
    }

    //订阅询价应答
    @Override
    public void OnRspSubForQuoteRsp(CThostFtdcSpecificInstrumentField cThostFtdcSpecificInstrumentField, CThostFtdcRspInfoField cThostFtdcRspInfoField, int i, boolean b) {
    }

    //取消订阅询价应答
    @Override
    public void OnRspUnSubForQuoteRsp(CThostFtdcSpecificInstrumentField cThostFtdcSpecificInstrumentField, CThostFtdcRspInfoField cThostFtdcRspInfoField, int i, boolean b) {
    }

    //询价通知
    @Override
    public void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField cThostFtdcForQuoteRspField) {
    }
}
