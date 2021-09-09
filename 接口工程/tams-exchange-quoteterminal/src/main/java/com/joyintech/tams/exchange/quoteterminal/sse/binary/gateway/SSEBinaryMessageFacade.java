package com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway;


import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteService;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.annotation.SSEBinaryMessage;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMsgTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.*;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.util.SSEBinaryConverter;
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
public class SSEBinaryMessageFacade implements SSEBinaryGateway {

    private static final Logger logger = LoggerFactory.getLogger(SSEBinaryMessageFacade.class);

    private Map<SSEBinaryMsgTypeEnum, Invoker> invokerMap;

    private final ThreadLocal<QuoteProto.TickBean.Builder> threadLocalBuilder = ThreadLocal.withInitial(QuoteProto.TickBean::newBuilder);

    @Autowired
    private QuoteService quoteService;

    public SSEBinaryMessageFacade() {
        this.invokerMap = new HashMap<>(16);
        initialize(this);
    }

    public void onMessage(Message message) {
        logger.debug("receive message");
        SSEBinaryMsgTypeEnum SSEBinaryMsgTypeEnum = message.getMsgType().getEnumValue();
        Invoker invoker = invokerMap.get(SSEBinaryMsgTypeEnum);
        if (invoker != null) {
            invoker.invoker(message);
        }
    }

    @SSEBinaryMessage(msgType = SSEBinaryMsgTypeEnum.HEARTBEAT)
    @Override
    public void onHeartbeat(SSEBinaryHeartbeat heartbeat) {
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

    @SSEBinaryMessage(msgType = SSEBinaryMsgTypeEnum.LOGON)
    @Override
    public void onLogon(SSEBinaryLogon logon) {
        logger.info("登陆成功 -> {}", logon);
    }

    @SSEBinaryMessage(msgType = SSEBinaryMsgTypeEnum.LOGOUT)
    @Override
    public void onLogout(SSEBinaryLogout logout) {
        if (logout.getSessionStatus() == 0L) {
            logger.info("登出成功 -> {}", logout);
        } else {
            logger.error("登陆失败 -> {}", logout);
        }
    }

    @SSEBinaryMessage(msgType = SSEBinaryMsgTypeEnum.LEVEL1)
    @Override
    public void onLevel1Market(SSEBinaryLevel1MarketSnapshot level1MarketSnapshot) {
        String securityId = level1MarketSnapshot.getSecurityId().getValue();

        QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
        SSEBinaryConverter.convertToTick(level1MarketSnapshot, builder);
        quoteService.addSSETick(builder.build());

        if (securityId.equals("100303") || securityId.equals("100504")) {
            logger.info("level1 -> {}", level1MarketSnapshot);
        }
    }

    private void initialize(SSEBinaryMessageFacade SSEBinaryMessageFacade) {
        Class<? extends com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.SSEBinaryMessageFacade> handlerClass = SSEBinaryMessageFacade.getClass();
        Method[] methods = handlerClass.getMethods();
        this.invokerMap = Arrays.stream(methods)
                .filter(method -> !Modifier.isPrivate(method.getModifiers())
                        && (method.isAnnotationPresent(SSEBinaryMessage.class))
                        && method.getParameters().length == 1)
                .collect(Collectors.toMap(
                        method -> method.getAnnotation(SSEBinaryMessage.class).msgType(),
                        method -> new Invoker(method, SSEBinaryMessageFacade)));
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
            if (!message.getClass().isAssignableFrom(parameterType)) {
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
}
