/*
 * 文件名：BaseRiskRdsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.riskrds.core;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.riskrds.constant.InterfUtil;
import com.joyintech.tams.component.business.riskrds.constant.PageFieldToInterfFactory;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.redisinit.dto.IRiskRds;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 基础接口组件Service
 * @author 裴家俊
 * @version 1.0 2020年1月8日
 * @see BaseRiskRdsService
 * @since 1.0
 */
public abstract class BaseRiskRdsService<V extends IRiskRds> extends BaseService {

    public interface IInfRds<V>{
       ClientData receive(V v) throws Exception;
    }

    public interface IInfBatchRds<T,V>{
        ClientData receive(T t,V v) throws Exception;
    }

    /**
     * 创建报文头格式
     * @param v
     * @return
     */
    public  <V extends IRiskRds> Map<String, Object> createHead(V v) {
        if (!StringUtil.isNotEmpty(v)) {
            return new HashMap<>();
        }
        Map<String, Object> header = new HashMap<>();
        header.put(InterfUtil.stdprocode,v.getInfCode());
        header.put(InterfUtil.stdmsgtype,InterfUtil.MES_TYPE);
        header.put(InterfUtil.std400bsys,StringUtil.defaultString(CacheUtil.getSysParam(InterfUtil.SYS_CODE),InterfUtil.systemcode));
        header.put(InterfUtil.std400aqid,StringUtil.defaultString(CacheUtil.getSysParam(InterfUtil.SYS_CODE),InterfUtil.systemcode));
        // 设置 发起方流水号
        String sysDate = DateUtil.getSysDate();
        sysDate = StringUtil.remove(sysDate, "-");
        long time = System.currentTimeMillis();
        String tail = "00000" + time;
        header.put(InterfUtil.stdtermtrc,StringUtil.defaultString(CacheUtil.getSysParam(InterfUtil.SYS_CODE),InterfUtil.systemcode)+ sysDate+tail);
        // 设置 发起方系统日期
        String curTime = DateUtil.getCurrentDateTime();
        String curDt = StringUtil.substring(curTime, 0, 10);
        String curhms = StringUtil.substring(curTime, 11);
        header.put(InterfUtil.std400rbdt,StringUtil.remove(curDt, "-"));
        // 设置 发起方系统时间戳
        header.put(InterfUtil.std400rsts, DateUtil.getCurrentDateTime());
        // 设置 发起方系统时间
        header.put(InterfUtil.std400rtim,StringUtil.remove(curhms, ":"));
        return header;
    }

    /**
     * 执行风险接口调用
     * @param v
     * @param eventType
     * @param infCode
     * @param assetType
     * @param iInfRds
     * @return
     * @throws Exception
     */
    public ClientData execRiskRds(V v,String eventType,String infCode,String assetType,IInfRds<V> iInfRds) throws Exception {
        return iInfRds.receive(execBase(v,eventType,infCode,assetType));
    }

    public <T> ClientData execBatchRiskRds(T t,V v,String eventType,String infCode,String assetType,IInfBatchRds<T,V> iInfRds) throws Exception {
        return iInfRds.receive(t,execBase(v,eventType,infCode,assetType));
    }

    /**
     * 赋值数据到基类
     * @param v
     * @param eventType
     * @param infCode
     * @param assetType
     * @return
     */
    protected V execBase(V v,String eventType,String infCode,String assetType){
        v.setInfCode(infCode);
        v.setAssetType(assetType);
        v.setEventType(eventType);
        if(StringUtil.equals(eventType,BussinessDictCodeListRds.事件类型_投资交易_存续期.getValue())){
            v.setEventType(BussinessDictCodeListRds.事件类型_投资交易.getValue());
            v.setEventStatus(BussinessDictCodeListRds.事件类型_投资交易_存续期.getValue());
        }
        return v;
    }

    // add by zhangrui1 调用批量接口逻辑 start
    @SuppressWarnings("unchecked")
    protected   <T> T convert(Object obj, Class<T> beanClass, Function<Object, T> consumer) {
        if (obj == null) {
            return null;
        }
        else if (obj.getClass() == beanClass) {
            return (T) obj;
        }
        else if (consumer != null) {
            return consumer.apply(obj);
        }
        return null;
    }
    // add by zhangrui1 调用批量接口逻辑 end

    /**
     * 当撤销时交易编号带R，需要去除方便找到预占实占编号
     * @param str
     * @return
     */
    public String removeLasStr(String str){
        if (StringUtil.isNotEmpty(str)&&str.lastIndexOf('R') == str.length() - 1) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 组装字符(拼上冒号)
     * @param str
     * @return
     */
    public String trimInfStr(String str){
        if(StringUtil.isNotEmpty(str)){
            return GlobalConstant.COLON+str;
        }
        return StringUtil.EMPTY;
    }

    /**
     * 判断是否是内部交易对手
     * @param dataMap
     * @return
     */
    protected boolean checkInnerCounter(Map<String, Object> dataMap){
        Object counterObj=CommonUtil.findValue(dataMap, "counterId");
        if(!StringUtil.isNotEmpty(counterObj)){
            return false;
        }
        String counterId = (String) counterObj;
        boolean isInnerTrd = AllTradeCommonUtil.isPortfolioId(counterId);
        String isInnerTrdStr = isInnerTrd ? BussinessDictCodeListRds.是否_是.getValue()
                : BussinessDictCodeListRds.是否_否.getValue();
        dataMap.put(PageFieldToInterfFactory.INF_ISINNERTRD, isInnerTrdStr);
        return isInnerTrd;
    }

    /**
     * 获取母组合代码
     * @param dataMap
     * @return
     */
    protected void exchangePortfolioBySub(Map<String, Object> dataMap){
        String pKey = "portfolioId";
        String actPKey = "actPortfolioId";
        String portfolioId = (String) CommonUtil.findValue(dataMap, pKey);
        String actPortfolioId = (String) CommonUtil.findValue(dataMap, actPKey);
        dataMap.put(pKey, AllTradeCommonUtil.exchangePortfolio(StringUtil.isNotEmpty(portfolioId) ? portfolioId : actPortfolioId));
    }
    /**
     * 获取返回的信息
     * @param message
     * @return
     */
    public AjaxResult getAjaxResult(Object returnCli,String message,MessageType messageType){
        AjaxResult ar = new AjaxResult();
        ar.setData(returnCli);
        List<ValidMessage> warnList = new ArrayList<>();
        warnList.add(new ValidMessage(message));
        ar.setErrorList(warnList);
        ar.setMessageType(messageType);
        //ar.setStatuCode(HttpStatus.OK.value());
        return ar;
    }

}