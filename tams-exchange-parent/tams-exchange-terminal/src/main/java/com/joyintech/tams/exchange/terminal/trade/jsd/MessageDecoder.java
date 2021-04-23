package com.joyintech.tams.exchange.terminal.trade.jsd;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.MessageBean;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.MessageManager;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req.ReqData;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.IRespHeader;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespHeader;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

public class MessageDecoder extends ByteToMessageDecoder {

    private final Logger logger = LoggerFactory.getLogger(MessageDecoder.class);

    private Splitter splitter = Splitter.on("|");

    private Charset charset = Charset.forName("gbk");

    private final int MIN_HEADER_LEN = 4;

    private RespProtocol respProtocol;

    private MessageManager messageManager;

    private JSDTradeApi jsdTradeApi;

    public MessageDecoder(MessageManager messageManager, JSDTradeApi jsdTradeApi){
        this.messageManager = messageManager;
        this.jsdTradeApi = jsdTradeApi;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        in.markReaderIndex();
        String message = in.toString(charset);
        logger.info("receive message:{}", message);
        List<String> messageContents = Lists.newArrayList(splitter.split(message));
        if (messageContents.size() < MIN_HEADER_LEN)
            in.resetReaderIndex();
        while (Strings.isNullOrEmpty(messageContents.get(0)) || !"A".equals(messageContents.get(0).replace(" ", ""))) {

            messageContents = messageContents.subList(1, messageContents.size());

            if (messageContents.size() < MIN_HEADER_LEN) {
                in.resetReaderIndex();
                return;
            }
        }

        int reqId = Integer.valueOf(messageContents.get(2));
        logger.info("reqId is: {}", reqId);
        MessageBean messageBean = messageManager.getMessage(reqId);
        if (Objects.isNull(messageBean)) {
            jsdTradeApi.msgTag = 0;
            logger.error("reqId:{} is not in message", reqId);
            return;
        }
        if (messageBean.isHandledZeroPackage()) {
            if ('1' != messageBean.getIsTotal() || messageBean.getCount() + 1 < messageBean.getTotalCount()) {//没有返回合计
                // 处理数据包
                /*if ("N".equals(messageContents.get(3))) {
                    Field[] fields = messageManager.getField(RespHeader.class);
                    if (fields.length > messageContents.size()) {
                        logger.debug("receive message is not complete");
                        in.resetReaderIndex();
                        return;
                    }
                    RespHeader respHeader = RespHeader.initHeader(messageContents);
                    logger.info("返回数据非首包成功失败标志为N");

                } else {*/
                Field[] fields = messageManager.getField(messageBean.getProtocolInfo().respDataClass());
                if (fields.length > messageContents.size()) {
                    logger.debug("receive message is not complete");
                    in.resetReaderIndex();
                    return;
                }
                Object dataBody = messageManager.getObjectFromStrings(messageContents,
                        messageBean.getProtocolInfo().respDataClass());
                respProtocol.reqId = reqId;
                respProtocol.dataMessages.add(dataBody);
                /*}*/


                messageBean.setCount(messageBean.getCount() + 1);
                if (messageBean.getCount() >= messageBean.getTotalCount()) {
                    list.add(respProtocol);

                    messageManager.removeMessage(reqId);
                    jsdTradeApi.msgTag = 0;
                } else {
                    channelHandlerContext.writeAndFlush(new ReqData(jsdTradeApi.source, String.valueOf(messageBean.getReqId()),
                            "0", jsdTradeApi.yybId, jsdTradeApi.entrustType).toString());
                }
            } else {//处理合计数据
                Field[] fields = messageManager.getField(messageBean.getProtocolInfo().respTotalClass());
                if (fields.length > messageContents.size()) {
                    logger.debug("receive message is not complete");
                    in.resetReaderIndex();
                    return;
                }
                Object dataBody = messageManager.getObjectFromStrings(messageContents,
                        messageBean.getProtocolInfo().respTotalClass());
                respProtocol.totalMessage = dataBody;
                respProtocol.reqId = reqId;
                messageBean.setCount(messageBean.getCount() + 1);
                if (messageBean.getCount() >= messageBean.getTotalCount()) {
                    list.add(respProtocol);
                    messageManager.removeMessage(reqId);
                    jsdTradeApi.msgTag = 0;
                } else {
                    channelHandlerContext.writeAndFlush(new ReqData(jsdTradeApi.source, String.valueOf(messageBean.getReqId()),
                            "0", jsdTradeApi.yybId, jsdTradeApi.entrustType).toString());
                }
            }


        } else {
            RespHeader respHeader = RespHeader.initHeader(messageContents);
            if (Objects.isNull(respHeader))
                in.resetReaderIndex();
            respProtocol = new RespProtocol();
            if (respHeader.isSuccess) {
                // 处理首包
                messageContents = messageContents.subList(4, messageContents.size());
                Field[] fields = messageManager.getField(messageBean.getProtocolInfo().respHeaderClass());
                if (fields.length > messageContents.size()) {
                    logger.debug("receive header message is not complete");
                    in.resetReaderIndex();
                    return;
                }

                respProtocol.header = (IRespHeader) messageManager.getObjectFromStrings(messageContents,
                        messageBean.getProtocolInfo().respHeaderClass());
                respProtocol.reqId = reqId;
                logger.info("respProtocol.header:{}", respProtocol.header.getClass().getName());
                if (respProtocol.header.getResponseCount() > 0) {
                    respProtocol.dataMessages = Lists.newArrayListWithCapacity(respProtocol.header.getResponseCount());
                    messageBean.setTotalCount(respProtocol.header.getResponseCount());
                    messageBean.setHandledZeroPackage(true);
                    //                    String code = messageBean.getMessageInfo().functionCode() == "61"? "0":"00";
                    channelHandlerContext.writeAndFlush(new ReqData(jsdTradeApi.source, String.valueOf(messageBean.getReqId()),
                            "0", jsdTradeApi.yybId, jsdTradeApi.entrustType).toString());
                } else {
                    list.add(respProtocol);
                    messageManager.removeMessage(reqId);
                    jsdTradeApi.msgTag = 0;
                }
            } else {
                respProtocol.errorCode = respHeader.errorCode;
                respProtocol.errorMsg = respHeader.errorMsg;
                respProtocol.reqId = reqId;
                list.add(respProtocol);
                messageManager.removeMessage(reqId);
                jsdTradeApi.msgTag = 0;
            }
        }
        //todo 极端情况下能会有问题
        in.readBytes(in.readableBytes());

    }
}
