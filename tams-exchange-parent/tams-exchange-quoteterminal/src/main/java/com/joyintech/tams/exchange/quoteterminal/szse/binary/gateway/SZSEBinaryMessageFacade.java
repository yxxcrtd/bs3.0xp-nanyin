package com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway;


import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteService;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.annotation.SZSEBinaryMessage;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryMsgType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.*;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.util.SZSEBinaryConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SZSEBinaryMessageFacade implements SZSEBinaryGateway {

    private static final Logger logger = LoggerFactory.getLogger(SZSEBinaryMessageFacade.class);

    private Map<SZSEBinaryMsgType, Invoker> invokerMap;

    private ThreadLocal<QuoteProto.TickBean.Builder> threadLocalBuilder = ThreadLocal.withInitial(QuoteProto.TickBean::newBuilder);

    @Autowired
    private QuoteService quoteService;

    public SZSEBinaryMessageFacade() {
        this.invokerMap = new HashMap<>(16);
        initialize(this);
    }

    public void onMessage(Message message) {
        SZSEBinaryMsgType binaryMsgType = message.getMsgType().getEnumValue();
        if (!SZSEBinaryMsgType.isHeartBeat(binaryMsgType.getCode()) && binaryMsgType.getCode() != SZSEBinaryMsgType.LEVEL1.getCode()) {
            logger.info("receive message msgType [{}]", JsonUtils.getJsonFromObject(message));
        }
        Invoker invoker = invokerMap.get(binaryMsgType);
        if (invoker != null) {
            invoker.invoker(message);
        }
    }

    @SZSEBinaryMessage(msgType = SZSEBinaryMsgType.CHANNEL_HEARTBEAT)
    @Override
    public void onChannelHeartbeat(SZSEBinaryMarketChannelHeartbeat channelHeartbeat) {
        logger.info("channel heartbeat -> {}", channelHeartbeat);
    }

    @SZSEBinaryMessage(msgType = SZSEBinaryMsgType.HEARTBEAT)
    @Override
    public void onHeartbeat(SZSEBinaryHeartbeat heartbeat) {
        logger.debug("receive heartbeat ... {}", heartbeat);
    }

    @Override
    public void connected(String message) {
        logger.info("连接成功 -> {}", message);
    }

    @Override
    public void disconnected(String message) {
        logger.info("连接断开 -> {}", message);
    }

    @SZSEBinaryMessage(msgType = SZSEBinaryMsgType.LOGON)
    @Override
    public void onLogon(SZSEBinaryLogon logon) {
        logger.info("登陆成功 -> {}", logon);
    }

    @SZSEBinaryMessage(msgType = SZSEBinaryMsgType.LOGOUT)
    @Override
    public void onLogout(SZSEBinaryLogout logout) {
        logger.info("登出成功 -> {}", logout);
    }

    @SZSEBinaryMessage(msgType = SZSEBinaryMsgType.LEVEL1)
    @Override
    public void onLevel1Market(SZSEBinaryLevel1MarketSnapshot level1MarketSnapshot) {
        String securityId = level1MarketSnapshot.getSecurityId().getValue();

        QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
        SZSEBinaryConverter.convertToTick(level1MarketSnapshot, builder);
        quoteService.addTick(builder.build());

        if(securityId.equals("100303") || securityId.equals("100504")){
            logger.info("level1 -> {}", level1MarketSnapshot);
        }
    }


    private static class Invoker {
        private static final Logger logger = LoggerFactory.getLogger(Invoker.class);

        private final Method method;

        private final Object target;

        private final Class<?> parameterType;

        private Invoker(Method method, Object target) {
            this.method = method;
            this.parameterType = method.getParameterTypes()[0];
            this.target = target;
        }

        private void invoker(Object message) {
            if(!message.getClass().isAssignableFrom(parameterType)) {
                logger.warn("当前参数[{}] 与 {}.{}({})中的参数不一致,必须为自身类或子类。currentMessage -> {}"
                        , message.getClass().getName(), target.getClass().getName(),
                        method.getName(), parameterType.getName(), message);
                return;
            }
            try {
                method.setAccessible(true);
                method.invoke(target, message);
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error("invoker error: ", e);
            }
        }
    }

    private void initialize(SZSEBinaryMessageFacade SZSEBinaryMessageFacade) {
        Class<? extends SZSEBinaryMessageFacade> handlerClass = SZSEBinaryMessageFacade.getClass();
        Method[] methods = handlerClass.getMethods();
        this.invokerMap = Arrays.stream(methods)
                .filter(method -> !Modifier.isPrivate(method.getModifiers())
                        && (method.isAnnotationPresent(SZSEBinaryMessage.class))
                        && method.getParameters().length == 1)
                .collect(Collectors.toMap(
                        method -> method.getAnnotation(SZSEBinaryMessage.class).msgType(),
                        method -> new Invoker(method, SZSEBinaryMessageFacade)));
    }
}
