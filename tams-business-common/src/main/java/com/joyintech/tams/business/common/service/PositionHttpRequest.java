package com.joyintech.tams.business.common.service;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.exception.PositionException;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.util.HttpUtil;
import com.joyintech.tams.business.common.util.PositionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

//@PropertySource("classpath:config.properties")
@Component
@Lazy(value=false)
public class PositionHttpRequest {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PositionHttpRequest.class);

    public final static String CODE_BUSI_RK = "300";    // 返回业务信息拒绝，余额不足，软控，前端确认
    public final static String CODE_BUSI_YK = "310";    // 返回业务信息拒绝，余额不足，硬控，前端提示

    private static String positionUrl;
    
    @Value("${positionUrl}")
    public void setPositionUrl(String url) {
        PositionHttpRequest.positionUrl = url;
    }

    /**
     * 头寸调用方法，需处理异常，可使用PositionUtil.ajaxErrorReturn解析异常
     * 返回码 300|。。。  表示余额不足，需前端确认
     * 返回码 410|。。。  表示通信息或网络超时
     * 返回码 420|。。。  头寸引擎返回不成功，具体参考返回信息
     * @param req
     * @throws PositionException
     */
    public static void send(Object req) throws PositionException{
        //
        logger.info("发送头寸报文:"+JsonUtils.object2Json(req));
        String res = sendPrivate(req);
        logger.info("头寸返回数据:"+res);
        Map msgMap = JsonUtils.json2Object(res, Map.class);
        if(msgMap == null){
            throw new PositionException("头寸返回数据为空！");
        }
        
        if (!StringUtil.equals("success", msgMap.get("code") + "")){
            if(StringUtil.equals("FL0007", msgMap.get("code"))){
                // 头寸引擎提示现金不足时需要再校验原投管现金
                // 返回true，表示现金够，重新发送
                boolean isEnough = PositionUtil.checkForFL0007(req, msgMap);
                if (!isEnough){
                    // 软控时，加上原投管现金仍不足时需前端确认
                    throw new PositionException(CODE_BUSI_RK + "|" + JsonUtils.object2Json(msgMap));
                }
            }else if(StringUtil.equals("CT0001", msgMap.get("code"))){
                // 头寸引擎提示现金不足时需要再校验原投管现金
                // 返回true，表示现金够，重新发送
                boolean isEnough = PositionUtil.checkForFL0007(req, msgMap);
                if (!isEnough){
                    // 确控时，加上原投管现金仍不足时提示前端
                    throw new PositionException(CODE_BUSI_YK + "|" + JsonUtils.object2Json(msgMap));
                }
            }else {
                revokeCheck(msgMap, req);
            }
        }
    }

    /**
     * 校验是否需要头寸冲正
     * @param message
     * @param req
     */
    private static void revokeCheck(Map message, Object req) {
        if (StringUtil.equals("DS0000", message.get("code")) || 
                StringUtil.equals("DS0001", message.get("code")) ||
                StringUtil.equals("DS0002", message.get("code")) ||
                StringUtil.equals("DS0003", message.get("code")) ||
                StringUtil.equals("DS0004", message.get("code")) ||
                StringUtil.equals("DS0005", message.get("code")) ||
                StringUtil.equals("DS0006", message.get("code")) ||
                StringUtil.equals("DS0008", message.get("code")) ||
                StringUtil.equals("DS0009", message.get("code")) ||
                StringUtil.equals("DS0010", message.get("code")) ||
                StringUtil.equals("CT0001", message.get("code"))){
            throw new PositionException("头寸更新异常：" + message);
        } else{
            String jsonStr = JsonUtils.object2Json(req);
            logger.info("发送头寸报文:"+jsonStr);
            BaseMap reqMap = JsonUtils.json2Object(jsonStr, BaseMap.class);
            String errorMsg = message != null?message.get("message") + "":"头寸返回数据为空";
            revoke(reqMap.getString("totalTradeId"), reqMap.getString("modelType"), errorMsg);
        }
    }

    /**
     * 正常撤回、退回
     * @param totalId
     * @param modelType
     */
    public static void sendRevoke(String totalId, String modelType,String interfaceType){
        BaseMap bm = BaseMap.newMap()
                .put("totalTradeId", totalId)
                .put("modelType", modelType)
                .put("userId", LoginUserUtil.getUserName())
                .put("interfaceType", interfaceType);
        logger.info("发送头寸报文:"+JsonUtils.object2Json(bm));
        String msg = cancel(bm);
        Map message = JsonUtils.json2Object(msg, Map.class);
        if (message == null) {
            throw new PositionException("头寸返回数据为空！");
        }
        if (!StringUtil.equals("success", message.get("code"))){
            if(StringUtil.equals("FL0007", message.get("code"))){
                throw new PositionException(CODE_BUSI_RK + "|" + message.get("message"));
            }else {
                throw new PositionException(message.get("message") + "");
            }
        }
    }

    /**
     * 异常冲正
     * @param totalId
     * @param modelType
     * @param e
     */
    public static void revoke(String totalId, String modelType, Exception e){
        BaseMap bm = BaseMap.newMap()
                .put("totalTradeId", totalId)
                .put("modelType", modelType)
                .put("userId", LoginUserUtil.getUserName())
                .put("interfaceType", "Bond001");
        logger.info("发送头寸报文:"+JsonUtils.object2Json(bm));
        cancel(bm);
        throw new PositionException(e.getMessage());
    }

    public static void revoke(String totalId, String modelType, String errorMsg){
        BaseMap bm = BaseMap.newMap()
                .put("totalTradeId", totalId)
                .put("modelType", modelType)
                .put("userId", LoginUserUtil.getUserName())
                .put("interfaceType", "Bond001");
        logger.info("发送头寸报文:"+JsonUtils.object2Json(bm));
        try {
            cancel(bm);
        } catch (Exception e) {
            errorMsg += "\n" + e.getMessage();
        }
        // 页面端根据异常信息返回前端
        throw new PositionException(errorMsg);
    }
    public static void deleteMethod(String totalId, String modelType,String interfaceType) throws PositionException{
        //
        BaseMap bm = BaseMap.newMap()
                .put("totalTradeId", totalId)
                .put("modelType", modelType)
                .put("userId", LoginUserUtil.getUserName())
                .put("interfaceType", interfaceType);//"Bond001"
        logger.info("发送头寸报文:"+JsonUtils.object2Json(bm));
        String res = cancel(bm);
        Map msgMap = JsonUtils.json2Object(res, Map.class);
        if(msgMap == null){
            throw new PositionException("头寸返回数据为空！");
        }
    }
    private static String sendPrivate(Object param) {
        return httpPostRequest(positionUrl + "/position/endpoint/updatePosition", param);
    }

    public static void doDeleteByWorkFlowId(String revokeId, String modelType,String busiType,String ps) throws PositionException{
        //
        BaseMap bm = BaseMap.newMap()
                .put("workFlowId", revokeId)
                .put("modelType", modelType)
                .put("userId", LoginUserUtil.getUserName())
                .put("busiType", busiType)
                .put("ps", ps)
                ;
        logger.info("发送头寸报文:"+JsonUtils.object2Json(bm));
        String res = delete(bm);
        Map msgMap = JsonUtils.json2Object(res, Map.class);
        if(msgMap == null){
            throw new PositionException("头寸返回数据为空！");
        }
    }

    public static void dorevoke(String revokeId, String modelType,String busiType,String ps) throws PositionException{
        //
        BaseMap bm = BaseMap.newMap()
                .put("revokeId", revokeId)
                .put("modelType", modelType)
                .put("userId", StringUtil.isEmpty(LoginUserUtil.getUserName())?"system":LoginUserUtil.getUserName())
                .put("busiType", busiType)
                .put("ps", ps)
                ;
        logger.info("发送头寸报文:"+JsonUtils.object2Json(bm));
        String res = revoke(bm);
        Map msgMap = JsonUtils.json2Object(res, Map.class);
        if(msgMap == null){
            throw new PositionException("头寸返回数据为空！");
        }
    }
    /**
     * 作废
     * @param param
     * @return
     */
    private static String delete(Object param) {
        return httpPostRequest(positionUrl + "/position/endpoint/delete", param);
    }

    /**
     * 作废
     * @param param
     * @return
     */
    private static String revoke(Object param) {
        return httpPostRequest(positionUrl + "/position/endpoint/revoke", param);
    }
    /**
     * 冲正撤销
     * @param param
     * @return
     */
    private static String cancel(Object param) {
        return httpPostRequest(positionUrl + "/position/endpoint/cancel", param);
    }
    
    /**
     * 从头寸引擎查询头寸余额
     * @param param
     * @return
     */
    @Deprecated
    public static String query(Object param) {
        return httpPostRequest(positionUrl + "/position/search/balance/etrSecPositionRule", param);
    }

    /**
     * 转换为json
     * @param url
     * @param param
     * @return
     */
    private static String httpPostRequest(String url, Object param){
        return HttpUtil.doPost(url, JsonUtils.object2Json(param));
    }
    /**
     * 头寸调用方法，需处理异常，可使用PositionUtil.ajaxErrorReturn解析异常
     * 返回码 300|。。。  表示余额不足，需前端确认
     * 返回码 410|。。。  表示通信息或网络超时
     * 返回码 420|。。。  头寸引擎返回不成功，具体参考返回信息
     * @param req
     * @throws PositionException
     */
    public static void batchClose(Object req) throws PositionException{
        //
        logger.info("发送头寸报文:"+JsonUtils.object2Json(req));
        String res = batchClosePrivate(req);
        Map msgMap = JsonUtils.json2Object(res, Map.class);
        if(msgMap == null){
            throw new PositionException("头寸返回数据为空！");
        }

     /*   if (!StringUtil.equals("success", msgMap.get("code") + "")){
            if(StringUtil.equals("FL0007", msgMap.get("code"))){
                throw new PositionException(CODE_BUSI_REFUSE + "|" + JsonUtils.object2Json(msgMap));
            }else {
               // revokeCheck(msgMap, req);
                throw new PositionException("头寸返回数据为空！");
            }
        }*/
    }
    private static String batchClosePrivate(Object param) {
        return httpPostRequest(positionUrl + "/position/endpoint/batchClose", param);
    }
}
