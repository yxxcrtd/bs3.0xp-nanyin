package com.joyintech.invest.interf.common.core.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.cache.InvestInterfCacheUtil;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.contants.InfDictCodeList;
import com.joyintech.invest.interf.common.core.contants.InterfColumns;
import com.joyintech.invest.interf.common.exception.InterfException;

/**
 * 投管接口发送服务基类
 *
 * @author 王波
 * @version 1.0 2020年01月16日
 * @see InterfSendBaseService
 * @since 1.0
 */
public abstract class InterfSendBaseService<V> {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 信息码:成功
     */
    protected static final String AAAAAAA = "AAAAAAA";

    /**
     * 接口ID
     */
    private String infId;
    
    @Autowired
    private InvestInterfCommonService interfCommonService;
    
    
	/**
     * 服务线程数据
     */
    private ThreadLocal<Map<String, Object>> serviceInfo = ThreadLocal.withInitial(HashMap::new);

    @Autowired
    private void init() {
        INF inf = getINF();
        if (inf != null) {
            infId = inf.value();
        }
    }

    /**
     * 取得INF注解
     *
     * @return INF注解实例
     * @since 1.0
     */
    private INF getINF() {
        return getClass().getAnnotation(INF.class);
    }


    /**
     * 取得 接口ID
     *
     * @return 接口ID
     * @see
     * @since 1.0
     */
    public String getInfId() {
        return infId;
    }

    /**
     * @description 接口执行方法
     *
     * @param request 请求数据
     * @return 处理结果（应答数据）
     * @see
     * @since 1.0
     */
    public Map<String, Object> execute(Map<String, Object> request) {
        // 2.2.1 初始化数据
        ClientData response = new ClientData();
      

        try {

            beforeHandle( request, response);

            handle(request, response);
            //事件处理结束
			afterHandle(request, response);
            
        }
        catch (Exception e) {
            // 2.2.5 异常处理
            doException(response, "处理异常", e);
        }
        String rspStr = JsonUtils.object2Json(response);
        getLogger().debug("应答报文:{}", rspStr);
        return response.convertToMap();
    }

    /**
     * @description 接口执行方法
     *
     * @param request 请求数据
     * @param response 应答数据
     * @return
     * @exception InterfException 接口异常
     * @see
     * @since 1.0
     */
    protected  Map<String, Object> handle(Map<String, Object> request, ClientData response)
            throws InterfException {
    	// 1. 转换为对应的DTO, 转换为标准输出报文
    	ClientData data = resolveData(request, response);
    	// 2.  报文入库（共通）
    	interfCommonService.writeLog(data.convertToMap(), InfDictCodeList.发送标识_发送.getValue());
    	
    	
    	// 3. 调用util，输入报文,返回标准结构
    	Map<String, Object> res = interfCommonService.sendToUitility(data.convertToMap());
    
    	//返回结果
    	return res;
    }

    /**
     * 將请求数据转化为标准接口请求数据
     * @param request
     * @param response
     * @return
     */
    protected abstract ClientData resolveData(Map<String, Object> request, ClientData response);

    

    /**
     * @description: [预留] 数据处理前
     *
     * @param request 请求数据
     * @param response 应答数据
     * @see
     * @since 1.0
     */
    protected abstract void beforeHandle(Map<String, Object> request, ClientData response);


    /**
     * @description 条件check
     *
     * @param obj 接口输入
     * @param response 返回数据
     * @return check结果
     * @see
     * @since 1.0
     */
    protected abstract boolean checkWithCond(V obj, ClientData response);

    /**
              *  结束处理
     *
     * @param request 请求数据
     * @param response 应答数据
     * @see
     * @since 1.0
     */
    protected abstract void afterHandle(Map<String, Object> request, ClientData response);
    
    /**
     * 设置 服务线程数据
     *
     * @param key 主键
     * @param info 数据
     * @see
     * @since 1.0
     */
    public void setServiceInfo(String key, Object info) {
        getServiceInfo().put(key, info);
    }

    /**
     * 取得 服务线程数据
     *
     * @return 服务线程数据
     * @see
     * @since 1.0
     */
    private Map<String, Object> getServiceInfo() {
        return serviceInfo.get();
    }

    /**
     * 异常处理
     *
     * @param msg 异常信息
     * @param e 异常 {@link Exception}
     * @see
     * @since 1.0
     */
    protected void doException(ClientData response, String msg, Exception e) {
        String errMsg = msg;
        if (e instanceof InterfException) {
            errMsg = errMsg + "【" + StringUtil.substring(e.getMessage(), 0, 60) + "】";
        }
        getLogger().error(errMsg, e);
        setRtnCode(response, InfDictCodeList.信息码_拒绝.getValue(), -1);
        setRtnMsg(response, errMsg);
    }

    /**
     * 设置返回消息
     *
     * @param response 应答数据
     * @param msg 返回消息
     * @see
     * @since 1.0
     */
    protected void setRtnMsg(ClientData response, String msg) {
        response.setHeadField(InterfColumns.COM_RTNMSG.fieldName(), msg);
    }

    /**
     * 设置返回码
     *
     * @param response 应答数据
     * @param type 信息类型
     * @param code 返回码
     * @see
     * @since 1.0
     */
    protected void setRtnCode(ClientData response, String type, int code) {
        String rtnCode = AAAAAAA;
        String msgType = InfDictCodeList.信息类型_正常.getValue();
        if (code != 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(10000 + code);
            // 系统标识
            String sysFlag = InvestInterfCacheUtil.getSysParam(InterfColumns.COM_SYS_FLAG.fieldName());
            rtnCode = sysFlag + type + (code < 0 ? builder.toString() : builder.substring(1));
            msgType = code < 0 ? InfDictCodeList.信息类型_错误.getValue() : msgType;
        }
        // 信息类型
        response.setHeadField(InterfColumns.COM_STD400MSGT.fieldName(), msgType);
        // 信息码
        response.setHeadField(InterfColumns.COM_RTNCODE.fieldName(), rtnCode);
    }



    /**
     * 取得日志对象
     *
     * @return 日志对象
     * @see
     * @since 1.0
     */
    protected Logger getLogger() {
        return logger;
    }

}
