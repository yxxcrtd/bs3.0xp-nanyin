package com.joyintech.exchange.service.quote.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.exchange.service.quote.bean.InterfHttpResultBean;
import com.joyintech.exchange.service.quote.utils.http.HttpClientUtil;
import com.joyintech.exchange.service.quote.utils.http.HttpParameter;
import com.joyintech.exchange.service.quote.utils.http.HttpResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author swwan
 * @date 2021/11/3 - 15:30
 */
@Component
public class InterfHttpRequestUtil {
    private static final Logger logger = LoggerFactory.getLogger(InterfHttpRequestUtil.class);
    protected AtomicLong requestID = new AtomicLong(1);
    /**
     * 访问接口平台的地址
     */
    @Value("${quote.http.host}")
    private String host;
    @Value("${quote.http.url}")
    private String url;
    @Value("${quote.http.gpCode}")
    private String gpCode;
    @Value("${quote.http.triggerCode}")
    private String triggerCode;

    public static void main(String[] args) throws Exception {
        InterfHttpRequestUtil util = new InterfHttpRequestUtil();
        Map<Object, Object> map = MapUtil.builder().put("key", "010303").build();
        logger.info("doRequest map:{}", JSON.toJSONString(map));
        HttpResult result = util.doRequest("http://192.168.10.191:1669/dsp", "/datarouter/http/json/callTrigger", "GZYH", "GZ_HQ_SH", map);
        logger.info("result{}", result.getResult());
    }

    public String getSimpleStockCode(String stockCode) {
        return StringUtil.split(stockCode, GlobalConstant.POINT)[0];
    }

    public long generateReqId() {
        return requestID.incrementAndGet();
    }

    public synchronized String doRequest(String message) {
        Map<Object, Object> map = MapUtil.builder().put("key", message).build();

        try {
            HttpResult result = doRequest(host, url, gpCode, triggerCode, map);
            logger.debug("[行情服务] 调用http接口 param: {}, response: {}", JSON.toJSONString(map), result.getResult());
            InterfHttpResultBean interfHttpResultBean = JsonUtils.getObjectFromJson(result.getResult(), InterfHttpResultBean.class);
            if (ErrorCode.SUCCESS.msg().equals(interfHttpResultBean.getCode())) {
                JSONObject jsonObject = (JSONObject) interfHttpResultBean.getData();
                return jsonObject.getString(message);
            }
        } catch (Exception e) {
            logger.error("[行情服务] 调用http接口异常：errorMsg={}", e.getMessage(), e);
        }

        return null;
    }

    public HttpResult doRequest(String host, String url, String gpCode, String triggerCode, Object message) throws Exception {
        HttpClientUtil clientUtil = HttpClientUtil.create();
        clientUtil.url(host + url);
        HttpParameter parameter = buildHttpParameter(gpCode, triggerCode, message);
        clientUtil.context(JsonUtils.getJsonFromObject(parameter));
        return clientUtil.send();
    }

    private HttpParameter buildHttpParameter(String gpCode, String triggerCode, Object message) {
        HttpParameter parameter = new HttpParameter();
        parameter.setParameter(message);
        parameter.setTrigger(triggerCode);
        parameter.setGpcode(gpCode);
        parameter.setPreseqno(String.valueOf(generateReqId()));
        parameter.setPredate(DateUtil.format(LocalDateTime.now(), "yyyy-mm-dd"));
        parameter.setAsync(false);
        return parameter;
    }
}
