/*
 * 文件名：InterfRiskRdsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.riskrds.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.component.business.riskrds.constant.InterfUtil;
import com.joyintech.tams.component.business.riskrds.constant.PageFieldToInterfFactory;
import com.joyintech.tams.component.business.riskrds.core.BaseRiskRdsService;
import com.joyintech.tams.component.business.riskrds.dto.EntrustBaseDto;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.dto.TradeBaseDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.client.ClientUtil;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.redisinit.dto.IRiskRds;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IAssetInfo;
import com.joyintech.tams.invest.common.riskinfrds.info.service.AssetInfoRedisService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

import net.sf.ehcache.hibernate.management.impl.BeanUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 接口组件Service
 * @author 裴家俊
 * @version 1.0 2020年1月8日
 * @see InterfRiskRdsService
 * @since 1.0
 */
@Service
public class InterfRiskRdsService<V extends IRiskRds> extends BaseRiskRdsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<String> 申购确认_赎回申请=Arrays.asList(DictCodeList.交易方向_申购确认.getValue(),DictCodeList.交易方向_赎回.getValue(),DictCodeList.交易方向_赎回到账.getValue());

    private List<String> 产品端_认购赎回分红=Arrays.asList(DictCodeList.交易方向_产品认购.getValue(),DictCodeList.交易方向_产品申购.getValue()
            ,DictCodeList.交易方向_产品赎回.getValue(),DictCodeList.交易方向_份额强增.getValue(),DictCodeList.交易方向_份额强减.getValue(),DictCodeList.交易方向_现金分红.getValue()
            ,DictCodeList.交易方向_提前终止.getValue(),DictCodeList.交易方向_提前赎回.getValue(),DictCodeList.交易方向_产品付费.getValue(),DictCodeList.交易方向_税费缴税.getValue()
            ,DictCodeList.交易方向_产品提前赎回.getValue(),DictCodeList.交易方向_产品现金分红.getValue());
    /**
     * 资产基本信息更新Redis服务
     */
    @Autowired
    private AssetInfoRedisService assetInfoSrc;

    @Autowired
    private FinProductAddMapper finProductAddMapper;
    
    @Autowired
    private FinTradeProductMapper finTradeProductMapper;
    
    public ClientData sendRiskRdsDataCommon(Map<String, Object> dataMap,V v) throws Exception {  
        // add by zhangrui1 增加批量接口调用逻辑 start
        String infCode = v.getInfCode();
        boolean isInnerTrd =checkInnerCounter(dataMap);
        //是否是股票的分红送转 add by chendijun 20200523
        boolean isStockFHSZ = (StringUtil.equals((String) dataMap.get(InterfUtil.ps), DictCodeList.股票分红类型_分红送转.getValue()) 
            &&StringUtil.equals((String) dataMap.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_股票.getValue()));
        // 批量接口或者转仓或者是股票分红送转 
        if(CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占批量接口)
                || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占释放批量接口)
                || CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_风险试算批量接口)
                || isInnerTrd
                || isStockFHSZ) {
            // BSTOT-2419 by zhangrui1 Start
            //获取母组合
            exchangePortfolioBySub(dataMap);
            String counterId = (String) CommonUtil.findValue(dataMap, "counterId");
            if (isInnerTrd) {
                counterId = AllTradeCommonUtil.exchangePortfolio(counterId);
                dataMap.put(PageFieldToInterfFactory.INF_COUNTERID, counterId);
            }
            // BSTOT-2419 by zhangrui1 end
            List<Map<String, Object>> list = prepareRiskListEntity(dataMap, v);
            logger.info("==========调用共通方法sendListDataCommon==========");
            return this.sendListDataCommon(list);
        }
        // add by zhangrui1 增加批量接口调用逻辑 end
        InterfRiskRdsDto interfRiskRdsDto=this.prepareRiskEntity(dataMap,v);
        if(!StringUtil.isNotEmpty(interfRiskRdsDto)){
            return null;
        }
        interfRiskRdsDto.setInfCode(v.getInfCode());
        interfRiskRdsDto.setEventType(v.getEventType());
        interfRiskRdsDto.setCcy(StringUtil.isNotEmpty(dataMap.get(InterfUtil.CCY))?dataMap.get(InterfUtil.CCY).toString():BussinessDictCodeListRds.币种_人民币_CNY.getValue());
        //暂时没有预占
        if(CommonUtil.isType(v.getEventType(), BussinessDictCodeListRds.事件类型_投资委托)){
            interfRiskRdsDto.setEventType(BussinessDictCodeListRds.事件类型_投资委托.getValue());
        }
        if(!StringUtil.isNotEmpty(interfRiskRdsDto.getAssetType())&&StringUtil.isNotEmpty(v.getAssetType())){
            interfRiskRdsDto.setAssetType(v.getAssetType());
        }
        if(StringUtil.equals(BussinessDictCodeListRds.接口系统ID_实占释放接口.getValue(),v.getInfCode())){
            interfRiskRdsDto.setRevokeFlg(DictCodeList.是否_是.getValue());
        }
        //暂不处理的交易方向
        if(StringUtil.isNotEmpty(interfRiskRdsDto.getPs())&&申购确认_赎回申请.contains(interfRiskRdsDto.getPs())){
            if(!CommonUtil.isType(v.getEventType(), BussinessDictCodeListRds.事件类型_投资委托)) {
                return null;
            }
        }
        // add by zhangrui1 添加是否内部交易 Start
        interfRiskRdsDto.setIsInnerTrd(isInnerTrd ? BussinessDictCodeListRds.是否_是.getValue()
                : BussinessDictCodeListRds.是否_否.getValue());
        // add by zhangrui1 添加是否内部交易 end
        logger.info("==========调用共通方法sendDataCommon==========");
        //wangyang1 风险二级法人改造 start 
        interfRiskRdsDto.setInstCode(MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName()));
        //wangyang1 风险二级法人改造 end
        return this.sendDataCommon(interfRiskRdsDto);
    }

    /**
     * 保存或提交调用的实占预占逻辑(非画面端)
     * @param t
     * @param v
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> ClientData sendRiskRdsData(T t,V v) throws Exception {
        logger.info("==========后端交易或委托类转换接口:{} start==========",v.getEventType()+v.getInfCode());
        Map<String,Object> dataMap=JsonUtils.object2Map(t);
        ClientData returnCli = this.sendRiskRdsDataCommon(dataMap,v);
        return returnCli;
    }

    /**
     * 某些场景不走AOP单调实占或实占释放，不计算指标
     * @param v
     * @param eventType 事件类型
     * @param infCode  事件代码
     * @param assetType  系统资产大类
     * @param iInfRds  执行的方法
     * @return
     * @throws Exception
     */
    public void checkRiskRdsWithoutCalc(V v,String eventType,String infCode,String assetType,IInfRds<V> iInfRds) throws BusinessException {
        try{
            ClientData returnCli=execRiskRds(v,eventType,infCode,assetType,iInfRds);
            if(StringUtil.isNotEmpty(returnCli)&&StringUtil.isNotEmpty(returnCli.getHead())&&StringUtil.isNotEmpty(returnCli.getHead().get(BussinessConstantRds.std400msgt))){
                if(StringUtil.equals(returnCli.getHead().get(BussinessConstantRds.std400msgt).toString(),BussinessConstantRds.E)&&StringUtil.isNotEmpty(returnCli.getHead().get(BussinessConstantRds.stdrtninfo))){
                    throw new BusinessException(BussinessConstantRds.MSG+returnCli.getHead().get(BussinessConstantRds.stdrtninfo).toString());
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * 检查风险是否超限(投资交易端点提交调用)
     * @param v
     * @param eventType 事件类型
     * @param infCode  事件代码
     * @param assetType  系统资产大类
     * @param iInfRds  执行的方法
     * @return
     * @throws Exception
     */
    public AjaxResult checkRiskRds(V v,String eventType,String infCode,String assetType,IInfRds<V> iInfRds) throws Exception {
        ClientData returnCli=execRiskRds(v,eventType,infCode,assetType,iInfRds);
        if (StringUtil.isNotEmpty(returnCli)) { 
            if(StringUtil.isNotEmpty(returnCli.getHead())){
                if(StringUtil.equals(returnCli.getHead().get(InterfUtil.std400msgt),InterfUtil.E)){
                    return getAjaxResult(returnCli,returnCli.getHead().get(InterfUtil.stdrtninfo).toString(),MessageType.错误消息);
                }else {
                    String statusRisk=StringUtil.isNotEmpty(returnCli.getBody())?StringUtil.isNotEmpty(returnCli.getBody().get("rskStatus"))?returnCli.getBody().get("rskStatus").toString():null:null;

                    //增加软控硬控开关 add by jqq 20201216 start
                    String institutionCode = "";
                    if(MultilevelDataAuthorityUtil.isEnabled()){
                        institutionCode =  MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName());
                    }
                    String isSoft = CacheUtil.getSpecialSysParam("IS_RISKMATH_SOFTCONTROL", institutionCode);
                    if(StringUtil.equals(statusRisk, BussinessDictCodeListRds.指标状态_禁止.getValue())){
                        if("02".equals(isSoft)){//硬控
                            return getAjaxResult(returnCli,InterfUtil.FORBID_MES,MessageType.错误消息);
                        }
                        return null;
                    }
                    //增加软控硬控开关 add by jqq 20201216 end
//                    if(StringUtil.equals(statusRisk, BussinessDictCodeListRds.指标状态_禁止.getValue())){
//                        return getAjaxResult(returnCli,InterfUtil.FORBID_MES,MessageType.错误消息);
//                    }
                }
            }
        }
        return null;
    }

    /**
     * 检查风险是否超限(多组合)
     * @param t
     * @param v
     * @param eventType 事件类型
     * @param infCode  事件代码
     * @param assetType  系统资产大类
     * @param iInfRds  执行的方法
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> Map<String,Object>  baseBatchRiskRds(T t,V v,String eventType,String infCode,String assetType,IInfBatchRds<T,V> iInfRds) throws Exception {
        ClientData returnCli=execBatchRiskRds(t,v,eventType,infCode,assetType,iInfRds);
        Map<String,Object> retMap=new HashMap<>();
        retMap.put(InterfUtil.BACK_PAGE_FLAG,DictCodeList.是否_否.getValue());
        retMap.put(InterfUtil.INF_REMAIN_FLAG,DictCodeList.是否_否.getValue());
        if (StringUtil.isNotEmpty(returnCli)) {
            retMap.put(InterfUtil.RISK_RDS_LIST,returnCli);
            if(StringUtil.isNotEmpty(returnCli.getHead())){
                //针对异常或超违规线情况进行分别判断
                if(StringUtil.equals(returnCli.getHead().get(InterfUtil.std400msgt),InterfUtil.E)){
                    //针对异常情况
                    retMap.put(InterfUtil.BACK_PAGE_FLAG,DictCodeList.是否_是.getValue());
                    retMap.put(InterfUtil.INF_REMAIN_FLAG,DictCodeList.是否_是.getValue());
                    retMap.put(InterfUtil.RISK_BACK_MES,returnCli.getHead().get(InterfUtil.stdrtninfo).toString());
                }else {
                    String statusRisk=StringUtil.isNotEmpty(returnCli.getBody())?StringUtil.isNotEmpty(returnCli.getBody().get("rskStatus"))?returnCli.getBody().get("rskStatus").toString():null:null;
                    if(StringUtil.equals(statusRisk, BussinessDictCodeListRds.指标状态_禁止.getValue())){
                        //针对超违规线情况
                        retMap.put(InterfUtil.BACK_PAGE_FLAG,DictCodeList.是否_是.getValue());
                        retMap.put(InterfUtil.RISK_BACK_MES,InterfUtil.FORBID_MES);
                    }
                }
            }
        }
        return retMap;
    }

    /**
     * 检查风险是否超限(多组合)
     * @param list
     * @param v
     * @param eventType 事件类型
     * @param infCode  事件代码
     * @param infRevCode 释放事件代码
     * @param assetType  系统资产大类
     * @param iInfRds  执行的方法
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> AjaxResult  checkBatchRiskRds(List<T> list,V v,String eventType,String infCode,String infRevCode,String assetType,IInfBatchRds<T,V> iInfRds) throws Exception {
        List<T> revokeList=new ArrayList<>();
        AjaxResult ar=new AjaxResult();
        boolean needBackFlag=false;
        List<Object> listRiskResult=new ArrayList<>();
        Map<String,Object> arAllMap=new HashMap<>();
        for(T t:list) {
            //调用风险实占或预占接口
            Map<String,Object> arMap=baseBatchRiskRds(t,v,eventType, infCode, assetType, iInfRds);
            if (StringUtil.isNotEmpty(arMap)) {
                //有出现不通过或预占实占时的场景，则设置返回画面标识
                if(StringUtil.equals(arMap.get(InterfUtil.BACK_PAGE_FLAG).toString(),DictCodeList.是否_是.getValue())) {
                    needBackFlag = true;
                    arAllMap.put(InterfUtil.BACK_PAGE_FLAG,DictCodeList.是否_是.getValue());
                    arAllMap.put(InterfUtil.INF_REMAIN_FLAG,DictCodeList.是否_否.getValue());
                    arAllMap.put(InterfUtil.RISK_BACK_MES,arMap.get(InterfUtil.RISK_BACK_MES));
                    //有出现不通过的场景，则设置返回画面标识
                    if (StringUtil.equals(arMap.get(InterfUtil.INF_REMAIN_FLAG).toString(), DictCodeList.是否_是.getValue())) {
                        arAllMap.put(InterfUtil.INF_REMAIN_FLAG,DictCodeList.是否_是.getValue());
                        break;
                    }
                }
                listRiskResult.add(arMap.get(InterfUtil.RISK_RDS_LIST));
            }
            revokeList.add(t);
        }
        //若出现超违规线或异常的情况所有已调用实占或预占的组合再调用释放接口
        if(needBackFlag) {
            for (T t : revokeList) {
                execBatchRiskRds(t,v,eventType,infRevCode,assetType,iInfRds);
            }
            arAllMap.put(InterfUtil.RISK_RDS_LIST,listRiskResult);
            return getAjaxResult(arAllMap,StringUtil.isNotEmpty(arAllMap.get(InterfUtil.RISK_BACK_MES))?arAllMap.get(InterfUtil.RISK_BACK_MES).toString():null,MessageType.错误消息);
        }
        return null;
    }

    /**
     * 发送微服务接口
     * @param bodyT
     * @return
     */
    public <T extends IRiskRds> ClientData sendDataCommon(T bodyT) {
        Map<String, Object> header = createHead(bodyT);
        Map<String, Object> body = JsonUtils.object2Map(bodyT);
        ClientData data = new ClientData();
        data.setHead(header);
        data.setBody(body);
        logger.info("==========调用微服务端风险接口 start==========");
        ClientData result = ClientUtil.sendRequestInf(ServiceApplication.RISK_INF_SERVICE_NAME + InterfUtil.RISK_INF_URL,data.convertToMap(),restTemplate);
        logger.info("==========调用微服务端风险接口 end==========");
        return result;
    }



    // add by zhangrui1 调用批量接口逻辑 start
    private ClientData sendRequest(ClientData data) {
        return ClientUtil.sendRequestInf(ServiceApplication.RISK_INF_SERVICE_NAME + InterfUtil.RISK_INF_URL,
                data.convertToMap(), restTemplate);
    }

    @SuppressWarnings("unchecked")
    public ClientData sendListDataCommon(List<Map<String, Object>> list) {
        if (StringUtil.isEmptyList(list)) {
            logger.info("==========空的报文体==========");
            return null;
        }
        // 修改eventID，避免重复  交易编号+交易方向
        list.forEach(map -> {
            String eventId = (String) map.get(PageFieldToInterfFactory.INF_EVENTID);
            if (StringUtil.isEmpty(eventId)) return;
            String ps = (String) map.get(PageFieldToInterfFactory.INF_PS);
            map.put(PageFieldToInterfFactory.INF_EVENTID, eventId + ps); 
        });
        //wangyang1 风险二级法人改造 start  
        for (Map<String, Object> map2 : list) { 
            map2.put("instCode", MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName())); 
        }
        //wangyang1 风险二级法人改造 end
        Map<String, Object> header = createHead(BeanUtil.mapToObject(list.get(0), InterfRiskRdsDto.class));
        Map<String, Object> body = new HashMap<>();
        if (CommonUtil.isType((String) header.get(InterfUtil.stdprocode), BussinessDictCodeListRds.接口系统ID_风险试算批量接口)) {
            body.put(PageFieldToInterfFactory.INF_RSK_CALC_LIST, list);
        }
        else {
            body.put(PageFieldToInterfFactory.INF_RSK_LIST, list);
        }
        ClientData data = new ClientData();
        data.setHead(header);
        data.setBody(body);
        logger.info("==========调用微服务端风险批量接口 start==========");
        ClientData result = sendRequest(data);
        logger.info("==========调用微服务端风险批量接口 end==========");
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> prepareRiskListEntity(Map<String, Object> dataMap, V v) throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        String ps = (String) dataMap.get(InterfUtil.ps);
        if (CommonUtil.isType(ps, DictCodeListExt1.交易方向_债转股)) {
            // 债券
            Map<String, Object> infMap = new HashMap<>();
            dataMap.put(InterfUtil.finprodType2, BussinessDictCodeList.平安资产类型_债券.getValue());
            PageFieldToInterfFactory.mapping(dataMap, infMap);
            infMap.put(InterfUtil.ps, BussinessDictCodeList.交易方向_卖出.getValue());
            doCommon(infMap, v);
            result.add(infMap);
            // 股票
            infMap = new HashMap<>();
            dataMap.put(InterfUtil.finprodType2, BussinessDictCodeList.平安资产类型_股票.getValue());
            PageFieldToInterfFactory.mapping(dataMap, infMap);
            infMap.put(InterfUtil.ps, BussinessDictCodeList.交易方向_买入.getValue());
            doCommon(infMap, v);
            // 金额=转股数*转股价格
            BigDecimal num = (BigDecimal)convert(dataMap.get("conNum"), BigDecimal.class, obj -> new BigDecimal(obj.toString()));
            BigDecimal pri = (BigDecimal)convert(dataMap.get("conPrice"), BigDecimal.class, obj -> new BigDecimal(obj.toString()));
            if (num != null && pri != null) {
                infMap.put(PageFieldToInterfFactory.INF_AMOUNT, num.multiply(pri));
            }
            result.add(infMap);
        }
        else if (CommonUtil.isType(ps, DictCodeListExt2.交易方向_转换申请)) {
            // 基金转入
            Map<String, Object> infMap = new HashMap<>();
            dataMap.put(InterfUtil.finprodType2, BussinessDictCodeList.平安资产类型_基金.getValue());
            dataMap.put(InterfUtil.ps + 2, BussinessDictCodeList.交易方向_卖出.getValue());
            // 市场代码转化成资产代码
            String changeToFinprodName = (String) CommonUtil.findValue(dataMap, "changeToFinprodName");
            String preFix = BussinessDictCodeList.金融产品类型_投管用_基金.getValue();
            changeToFinprodName = StringUtil.startsWith(changeToFinprodName, preFix) 
                    ? changeToFinprodName : (preFix + GlobalConstant.UNDER_LINE + changeToFinprodName);
            dataMap.put("changeToFinprodName", changeToFinprodName);
            PageFieldToInterfFactory.mapping(dataMap, infMap);
            doCommon(infMap, v);
            result.add(infMap);
            // 基金转出
            infMap = new HashMap<>();
            dataMap.put(InterfUtil.ps + 2, BussinessDictCodeList.交易方向_买入.getValue());
            PageFieldToInterfFactory.mapping(dataMap, infMap);
            doCommon(infMap, v);
            result.add(infMap);
        }
        else if (CommonUtil.isType(ps, BussinessDictCodeList.交易方向_卖出)) {
            logger.info("============转仓交易============");
            // 一笔卖出交易
            InterfRiskRdsDto dto = prepareRiskEntity(dataMap,v);
            dto.setCounterId((String) CommonUtil.findValue(dataMap, "counterId"));
            // 设置批量接口ID
            dto.setInfCode(changeInCode(v.getInfCode()));
            // 释放的场合设置撤销FLG为Y
            if (CommonUtil.isType(dto.getInfCode(), BussinessDictCodeListRds.接口系统ID_实占释放批量接口)) {
                dto.setRevokeFlg(BussinessDictCodeList.是否_是.getValue());
            }
            dto.setIsInnerTrd((String) dataMap.get(PageFieldToInterfFactory.INF_ISINNERTRD));
            dto.setEventType(v.getEventType());
            dto.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
            dto.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
            Object ccy = dto.getCcy();
            if (!StringUtil.isNotEmpty(ccy)) {
                dto.setCcy(BussinessDictCodeList.币种_人民币.getValue());
            }
            if (StringUtil.equals(dto.getPortfolioCode(), dto.getCounterId())) {
                logger.info("同一母组合下面的两个子组合交易不进行风控");
                return null;
            }
            result.add(BeanUtil.object2Map(dto));
            // 一笔买入交易
            InterfRiskRdsDto dtoBy = new InterfRiskRdsDto();
            BeanUtil.copyPropertiesIgnoreNull(dtoBy, dto);
            dtoBy.setPortfolioCode(dto.getCounterId());
            dtoBy.setCounterId(dto.getPortfolioCode());
            dtoBy.setPs(BussinessDictCodeList.交易方向_买入.getValue());
            result.add(BeanUtil.object2Map(dtoBy));
        } 
        //分红送转拆分成一笔现金分红一笔送转
        else if (StringUtil.equals(DictCodeList.股票分红类型_分红送转.getValue(),ps)) { 
           
            //现金分红交易
            dataMap.put(InterfUtil.ps, DictCodeList.股票分红类型_现金分红.getValue());
            InterfRiskRdsDto fhDto = periodEntityDev(dataMap, v);
            fhDto.setEventType(v.getEventType());
            fhDto.setInfCode(changeInCode(v.getInfCode()));
            // 释放的场合设置撤销FLG为Y
            if (CommonUtil.isType(fhDto.getInfCode(), BussinessDictCodeListRds.接口系统ID_实占释放批量接口)) {
                fhDto.setRevokeFlg(BussinessDictCodeList.是否_是.getValue());
            }
            result.add(BeanUtil.object2Map(fhDto));
            //送转交易
            dataMap.put(InterfUtil.ps, DictCodeList.股票分红类型_送转.getValue());
            InterfRiskRdsDto szDto = periodEntityDev(dataMap, v);
            szDto.setEventType(v.getEventType());
            szDto.setInfCode(changeInCode(v.getInfCode()));
            // 释放的场合设置撤销FLG为Y
            if (CommonUtil.isType(szDto.getInfCode(), BussinessDictCodeListRds.接口系统ID_实占释放批量接口)) {
                szDto.setRevokeFlg(BussinessDictCodeList.是否_是.getValue());
            }
            result.add(BeanUtil.object2Map(szDto));
        }
        else if(StringUtil.equals(DictCodeList.交易方向_上市流通.getValue(),ps)) {
        	//上市流通可能存在多个组合交易，每个组合需要拆分为原资产卖出和上市资产买入两笔交易
        	if(StringUtil.isNotEmpty(dataMap.get("baseTradeInfoDtos"))) {
        		List<Map> baseTradeInfoDtos = (List<Map>)dataMap.get("baseTradeInfoDtos");
        		String regionFinprodId = (String)dataMap.get("finprodId");
        		String newFinprodId = "F01_"+(String)dataMap.get("appearMarketId");
        		String eventDate = null;
				if(StringUtil.isNotEmpty(dataMap.get("tradeDate"))) {
					Object tradeDate = dataMap.get("tradeDate");
					String type = tradeDate.getClass().toString();
					if("Long".equals( type.substring(type.lastIndexOf('.') + 1, type.length()))) {
						eventDate = DateUtil.format(new Date((long)tradeDate));
					}
					else {
						eventDate = DateUtil.format(DateUtil.parse(tradeDate.toString()));
					}
				}
        		for(Map<String,Object> map:baseTradeInfoDtos) {
        			//获取接口类型
        			String infCode = v.getInfCode();
        			if(CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占批量接口)) {
        				infCode = BussinessDictCodeListRds.接口系统ID_实占批量接口.getValue();
        			}
        			else {
        				infCode = BussinessDictCodeListRds.接口系统ID_实占释放批量接口.getValue();
        			}
        			//获取 组合代码
        			String portfolioId = null,invAim=null,tradeId=null;
        			BigDecimal tradeAmt = BigDecimal.ZERO,shareAmt = BigDecimal.ZERO;
        			if(StringUtil.isNotEmpty(map.get("trdProductDeal"))) {
        				Map<String,Object> trdProductDeal = (Map<String,Object>)map.get("trdProductDeal");
        				//交易金额：市价
        				if(StringUtil.isNotEmpty(trdProductDeal.get("tradeAmt"))) {
        					tradeAmt = new BigDecimal(trdProductDeal.get("tradeAmt").toString());
        				}
        				//交易数量：券面总额
        				if(StringUtil.isNotEmpty(trdProductDeal.get("shareAmt"))) {
        					shareAmt = new BigDecimal(trdProductDeal.get("shareAmt").toString());
        				}
        				invAim = (String)trdProductDeal.get("invAim");
        				tradeId = (String)trdProductDeal.get("tradeId");
        			}
        			if(StringUtil.isNotEmpty(map.get("trdProductDealAdd"))) {
        				Map<String,Object> trdProductDealAdd = (Map<String,Object>)map.get("trdProductDealAdd");
        				portfolioId = (String)trdProductDealAdd.get("portfolioId");
        			}
        			if(StringUtil.isNotEmpty(tradeId)&&StringUtil.isNotEmpty(eventDate)) {
        				//买入
        				 InterfRiskRdsDto infRiskRdsDto=new InterfRiskRdsDto();
        				 infRiskRdsDto.setEventType(v.getEventType());
        				 infRiskRdsDto.setCcy(BussinessDictCodeListRds.币种_人民币_CNY.getValue());
        				 infRiskRdsDto.setInfCode(infCode);
        				 infRiskRdsDto.setEventId(tradeId);
        				 infRiskRdsDto.setEventDate(eventDate);
        				 infRiskRdsDto.setPortfolioCode(portfolioId);
        				 infRiskRdsDto.setAssetCode(newFinprodId);
        				 infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
        				 infRiskRdsDto.setAmount(tradeAmt);
        				 infRiskRdsDto.setTradeNum(shareAmt);
        				 infRiskRdsDto.setDeliveryDate(DateUtil.parse(eventDate));
        				 infRiskRdsDto.setMarketCode(newFinprodId);
        				 infRiskRdsDto.setIsWarnNeedConfirm(DictCodeList.是否_否.getValue());
        			     infRiskRdsDto.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
        			     infRiskRdsDto.setTradeAmtReal(tradeAmt);
        			     infRiskRdsDto.setInvAim(invAim);
        			     infRiskRdsDto.setTradePlace((String)dataMap.get("tradeMarket"));
        			     result.add(BeanUtil.object2Map(infRiskRdsDto));
        			     //卖出
        			     infRiskRdsDto.setAssetCode(regionFinprodId);
        			     infRiskRdsDto.setMarketCode(regionFinprodId);
        			     infRiskRdsDto.setPs(DictCodeList.交易方向_卖出.getValue());
        			     result.add(BeanUtil.object2Map(infRiskRdsDto));
        			}
        		}
        	}
        }
        return result;
    }

    /**
     * 找到对应的批量接口
     * 
     * @param infCode 接口ID
     * @return 对应的批量接口ID
     */
    private String changeInCode(String infCode) {
        if (CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占接口)) {
            return BussinessDictCodeListRds.接口系统ID_实占批量接口.getValue();
        }
        else if (CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_风险试算接口)) {
            return BussinessDictCodeListRds.接口系统ID_风险试算批量接口.getValue();
        }
        else if (CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占释放接口)) {
            return BussinessDictCodeListRds.接口系统ID_实占释放批量接口.getValue();
        }
        return infCode;
    }

    private void doCommon(Map<String, Object> infMap, V v) {
        String infCode = v.getInfCode();
        infMap.put(PageFieldToInterfFactory.INF_CODE, infCode);
        if (CommonUtil.isType(infCode, BussinessDictCodeListRds.接口系统ID_实占释放批量接口)) {
            infMap.put(PageFieldToInterfFactory.INF_REVOKEFLG, BussinessDictCodeList.是否_是.getValue());
        }
        infMap.put(PageFieldToInterfFactory.INF_EVENTTYPE, v.getEventType());
        infMap.put("isWarnNeedConfirm", DictCodeList.是否_否.getValue());
        infMap.put("isApprNeedConfirm", DictCodeList.是否_否.getValue());
        Object ccy = infMap.get(PageFieldToInterfFactory.INF_CCY);
        if (!StringUtil.isNotEmpty(ccy)) {
            infMap.put(PageFieldToInterfFactory.INF_CCY, BussinessDictCodeList.币种_人民币.getValue());
        }
        infMap.put(PageFieldToInterfFactory.INF_EVENTID, removeLasStr((String)infMap.get(PageFieldToInterfFactory.INF_EVENTID)));
    }
    // add by zhangrui1 调用批量接口逻辑 end

    /**
     * 构造接口实体
     * @param dataMap
     * @param v
     * @return
     * @throws Exception
     */
    public InterfRiskRdsDto prepareRiskEntity(Map<String, Object> dataMap,V v) throws Exception {
        if(StringUtil.isNotEmpty(dataMap.get(InterfUtil.finprodId))&&!StringUtil.isNotEmpty(v.getAssetType())){
            Map<String, IAssetInfo> mapAsset = assetInfoSrc.getMapFromRedis(RedisKeyEnum.资产基本信息.key(), Arrays.asList(dataMap.get(InterfUtil.finprodId).toString()));
            for(IAssetInfo iAst:mapAsset.values()){
                v.setAssetType(StringUtil.isNotEmpty(iAst)?iAst.getAssetType():v.getAssetType());
            }
        }
        //定义返回实体
        if(StringUtil.equals(v.getEventType(),BussinessDictCodeListRds.事件类型_投资交易.getValue())){
            if(StringUtil.equals(v.getEventStatus(),BussinessDictCodeListRds.事件类型_投资交易_存续期.getValue())){
                return periodEntityDev(dataMap,v);
            }
            return tradeEntityDev(dataMap,v);
        }else{
            return entrustEntityDev(dataMap,v);
        }

    }

    /**
     * 委托实体封装
     * @param dataMap
     * @param v
     * @return
     * @throws Exception
     */
    private InterfRiskRdsDto entrustEntityDev(Map<String, Object> dataMap,V v) throws Exception {
        //定义返回实体
        InterfRiskRdsDto infRiskRdsDto=new InterfRiskRdsDto();
        EntrustBaseDto finDto=(EntrustBaseDto)JsonUtils.map2entity(dataMap,EntrustBaseDto.class);
        infRiskRdsDto.setEventId(finDto.getEntrustId());
        infRiskRdsDto.setEventDate( DateUtil.format(DateUtil.platDateFromCache(),DateUtil.DEFAULT_DATE_FORMAT_EN));
        //设置母组合代码和子组合代码
        infRiskRdsDto.setPortfolioCode(AllTradeCommonUtil.exchangePortfolio(StringUtil.isNotEmpty(finDto.getPortfolioId())?finDto.getPortfolioId():finDto.getActPortfolioId()));
        infRiskRdsDto.setAssetCode(finDto.getFinprodId());
        infRiskRdsDto.setPs(finDto.getPs());
        infRiskRdsDto.setAmount(finDto.getEntrustAmt());//项目类,基金，计划类申购，存款,拆借
        infRiskRdsDto.setTradeNum(finDto.getEntrustNum());
        infRiskRdsDto.setDeliveryDate(finDto.getMdate());
        infRiskRdsDto.setMarketCode(finDto.getFinprodId());
        infRiskRdsDto.setTradePlace(finDto.getTradeMarket());
        infRiskRdsDto.setIsWarnNeedConfirm(DictCodeList.是否_否.getValue());
        infRiskRdsDto.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
        infRiskRdsDto.setMdate(finDto.getMdate());
        if(StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAmount(finDto.getEntrustNum());//债券为全价总额存款、计划类利率型、项目类资产：本金金额（元）基金（申购）、净值型计划类资产：申购金额（元）货币型计划类资产（申购）：申购金额（元）回购、拆借：本金金额（元）
            infRiskRdsDto.setTradeNum(finDto.getEntrustNum());//债券：券面总额（张）基金（赎回）：赎回份额（份）货币型计划类资产（赎回）：赎回份额（份）正逆回购:首期结算金额
            infRiskRdsDto.setNetPrice(finDto.getPrice());//债券单位净价,其它不填
            //将到期收益率除以100 update by zhangrui1 start
            Object obj = CommonUtil.findValue(dataMap, "matureYield");
            if (obj != null && StringUtil.isNotEmpty(obj)) {
                infRiskRdsDto.setYieldEndRate(ArithmeticUtil.divide(new BigDecimal(obj.toString()),new BigDecimal(100),4));//到期收益率
            }
            //将到期收益率除以100 update by zhangrui1 end
            //可转债
            if(StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_申购.getValue())){
                infRiskRdsDto.setAmount(StringUtil.isNotEmpty(dataMap.get("purchBondTotal"))?dataMap.get("purchBondTotal") instanceof BigDecimal ?(BigDecimal)dataMap.get("purchBondTotal"):new BigDecimal(dataMap.get("purchBondTotal").toString()):null);//可转债为申购券面总额
                infRiskRdsDto.setTradeNum(StringUtil.isNotEmpty(dataMap.get("purchBondTotal"))?dataMap.get("purchBondTotal") instanceof BigDecimal ?(BigDecimal)dataMap.get("purchBondTotal"):new BigDecimal(dataMap.get("purchBondTotal").toString()):null);//可转债为申购券面总额
            }
            //update by  zhangnian  20200326  start
            //债转股指令委托
            else if(StringUtil.equals(finDto.getPs(), DictCodeListExt1.交易方向_债转股.getValue())){
                infRiskRdsDto.setAmount(finDto.getEntrustAmt());
                infRiskRdsDto.setTradeNum(finDto.getEntrustNum());
            }
            //update by  zhangnian  20200326  end
        }
        //基金，计划类
        if(StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_赎回.getValue()) && !StringUtil.equals(DictCodeList.平安资产类型_计划类资产_净值型.getValue(),v.getAssetType())){
            infRiskRdsDto.setAmount(finDto.getEntrustNum());
        }
        if (StringUtil.equals(finDto.getPs(), DictCodeList.回购拆借方向_拆入.getValue())||
                StringUtil.equals(finDto.getPs(), DictCodeList.回购拆借方向_拆出.getValue())) {
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
            infRiskRdsDto.setAssetType(DictCodeList.平安资产类型_拆借.getValue());
            infRiskRdsDto.setAssetType2(finDto.getPs());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
        }
        if(StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(),v.getAssetType())) {
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
            infRiskRdsDto.setAssetType(DictCodeList.平安资产类型_质押式回购.getValue());
            infRiskRdsDto.setAssetType2(finDto.getPs());
            infRiskRdsDto.setAssetRate(StringUtil.isNotEmpty(dataMap.get("price"))?dataMap.get("price") instanceof BigDecimal ?ArithmeticUtil.divide((BigDecimal)dataMap.get("price"),new BigDecimal(100),4):ArithmeticUtil.divide(new BigDecimal(dataMap.get("price").toString()),new BigDecimal(100),4):null);
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
            infRiskRdsDto.setCalendarCode((String)dataMap.get("calendarId"));
            infRiskRdsDto.setDeadline(StringUtil.isNotEmpty(dataMap.get("repoTerm"))?new BigDecimal(dataMap.get("repoTerm").toString()):null);
        }
        if(StringUtil.equals(finDto.getBusiPs(), DictCodeList.回购拆借方向_融入.getValue())||
                StringUtil.equals(finDto.getBusiPs(), DictCodeList.回购拆借方向_融出.getValue())){
            BigDecimal totalAmt=ArithmeticUtil.multiply(StringUtil.isNotEmpty(dataMap.get("totalAmt"))?dataMap.get("totalAmt") instanceof BigDecimal?(BigDecimal) dataMap.get("totalAmt"):new BigDecimal(dataMap.get("totalAmt").toString()):BigDecimal.ZERO,new BigDecimal(10000));
            infRiskRdsDto.setAmount(ArithmeticUtil.multiply(totalAmt,StringUtil.isNotEmpty(dataMap.get("busiRate"))?dataMap.get("busiRate") instanceof BigDecimal?ArithmeticUtil.divide((BigDecimal) dataMap.get("busiRate"),new BigDecimal(100),4):ArithmeticUtil.divide(new BigDecimal(dataMap.get("busiRate").toString()),new BigDecimal(100),4):BigDecimal.ZERO));
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_债券借贷.getValue());
            infRiskRdsDto.setAssetType2(finDto.getPs());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
            infRiskRdsDto.setCalendarCode((String)dataMap.get("calendarId"));
            infRiskRdsDto.setDeadline(StringUtil.isNotEmpty(dataMap.get("termDays"))?new BigDecimal(dataMap.get("termDays").toString()):null);
        }
        if(StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAssetType2(finDto.getPs());
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
            infRiskRdsDto.setAssetRate(StringUtil.isNotEmpty(dataMap.get("price"))?dataMap.get("price") instanceof BigDecimal ?ArithmeticUtil.divide((BigDecimal)dataMap.get("price"),new BigDecimal(100),4):ArithmeticUtil.divide(new BigDecimal(dataMap.get("price").toString()),new BigDecimal(100),4):null);
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
            infRiskRdsDto.setCalendarCode((String)dataMap.get("calendarId"));
            infRiskRdsDto.setDeadline(StringUtil.isNotEmpty(dataMap.get("repoTerm2"))?new BigDecimal(dataMap.get("repoTerm2").toString()):null);
        }
        if(StringUtil.equals(DictCodeListExt2.平安资产类型_一级市场债.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAmount(finDto.getEntrustNum());//债券为全价总额存款、
            infRiskRdsDto.setTradeNum(finDto.getEntrustNum());
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_一级市场债.getValue());
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
        }
        if(StringUtil.equals(DictCodeListExt2.平安资产类型_利率互换.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAmount(finDto.getEntrustAmt() == null ? BigDecimal.ZERO : finDto.getEntrustAmt().multiply(new BigDecimal(10000)));
            infRiskRdsDto.setTradeNum(finDto.getEntrustAmt() == null ? BigDecimal.ZERO : finDto.getEntrustAmt().multiply(new BigDecimal(10000)));
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_利率互换.getValue());
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
        }
        if(StringUtil.equals(DictCodeListExt3.平安资产类型_期权.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAmount(finDto.getEntrustNum());
            infRiskRdsDto.setTradeNum(finDto.getEntrustNum());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
        }
        if(StringUtil.equals(DictCodeListExt2.平安资产类型_外汇掉期.getValue(),v.getAssetType())) {
            infRiskRdsDto.setPs(StringUtil.isNotEmpty(dataMap.get("tradePs"))?dataMap.get("tradePs").toString():infRiskRdsDto.getPs());
            infRiskRdsDto.setAmount(StringUtil.isNotEmpty(dataMap.get("tradeAmt"))?dataMap.get("tradeAmt") instanceof BigDecimal ?(BigDecimal)dataMap.get("tradeAmt"):new BigDecimal(dataMap.get("tradeAmt").toString()):null);
        }
        if(StringUtil.equals(DictCodeListExt2.平安资产类型_股票.getValue(),v.getAssetType())) {
            if(!StringUtil.isNotEmpty(finDto.getEntrustNum()) && StringUtil.isNotEmpty(dataMap.get("price"))){
                infRiskRdsDto.setTradeNum(ArithmeticUtil.divide(finDto.getEntrustAmt(), new BigDecimal((String)dataMap.get("price")), 2)); 
            }
        }
        infRiskRdsDto.setTradeAmtReal(infRiskRdsDto.getAmount());
        return infRiskRdsDto;
    }

    /**
     * 交易实体封装
     * @param dataMap
     * @param v
     * @return
     * @throws Exception
     */
    private InterfRiskRdsDto tradeEntityDev(Map<String, Object> dataMap,V v) throws Exception {
        //定义返回实体
        InterfRiskRdsDto infRiskRdsDto=new InterfRiskRdsDto();
        TradeBaseDto finDto=(TradeBaseDto)JsonUtils.map2entity(dataMap,TradeBaseDto.class);
        finDto.setTotleTradeId(removeLasStr(finDto.getTotleTradeId()));
        finDto.setTradeId(removeLasStr(finDto.getTradeId()));
        infRiskRdsDto.setEventId(StringUtil.isNotEmpty(finDto.getTotleTradeId())?finDto.getTotleTradeId():StringUtil.isNotEmpty(finDto.getTradeId())?finDto.getTradeId():finDto.getBusiCode());
        infRiskRdsDto.setEventDate(DateUtil.format(finDto.getTradeDate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
        //设置母组合代码
        infRiskRdsDto.setPortfolioCode(AllTradeCommonUtil.exchangePortfolio(StringUtil.isNotEmpty(finDto.getPortfolioId())?finDto.getPortfolioId():finDto.getActPortfolioId()));
        infRiskRdsDto.setAssetCode(StringUtil.equals(finDto.getFinprodId(),infRiskRdsDto.getEventId())?null:finDto.getFinprodId());//排除不是标的资产，比如回购，拆借，外汇等
        infRiskRdsDto.setPs(finDto.getPs());
        infRiskRdsDto.setAmount(finDto.getTradeAmt());//项目类,基金，计划类申购,股票
        infRiskRdsDto.setTradeNum(finDto.getShareAmt());
        infRiskRdsDto.setDeliveryDate(finDto.getSettleDate());
        infRiskRdsDto.setMarketCode(StringUtil.equals(finDto.getFinprodId(),infRiskRdsDto.getEventId())?null:finDto.getFinprodId());
        infRiskRdsDto.setTradePlace(finDto.getTradeMarket());
        infRiskRdsDto.setIsWarnNeedConfirm(DictCodeList.是否_否.getValue());
        infRiskRdsDto.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
        infRiskRdsDto.setMdate(finDto.getMdate());
        if(StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAmount(finDto.getFpriceAmt());//债券为全价总额存款、计划类利率型、项目类资产：实付金额（元）基金（申购）、净值型计划类资产：申购金额（元）货币型计划类资产（申购）：申购金额（元）回购、拆借：本金金额（元）
            infRiskRdsDto.setTradeNum(finDto.getTradeAmt());//债券：券面总额（张）基金（赎回）：赎回份额（份）货币型计划类资产（赎回）：赎回份额（份）正逆回购:首期结算金额
            infRiskRdsDto.setNetPrice(finDto.getUnitCprice());//债券单位净价,其它不填
            infRiskRdsDto.setAllPrice(finDto.getUnitFprice());//债券单位全价,其它不填
            //将到期收益率除以100 update by wz 20200430
            infRiskRdsDto.setYieldEndRate(ArithmeticUtil.divide(finDto.getYield(),new BigDecimal(100),4));//到期收益率
            infRiskRdsDto.setDeliveryDate(finDto.getSettleDate());
            //可转债
            if(StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_申购.getValue())){
                infRiskRdsDto.setAmount(finDto.getTradeAmt());//可转债为申购券面总额
                infRiskRdsDto.setTradeNum(finDto.getTradeAmt());//可转债为申购券面总额
            }
            else if(StringUtil.equals(finDto.getPs(), DictCodeListExt3.交易方向_债转股.getValue())){
                infRiskRdsDto.setAmount(finDto.getShareAmt());//可转债为申购券面总额
            }
            if (StringUtil.isNotEmpty(dataMap.get("windtypeone"))
                    && StringUtil.equals(dataMap.get("windtypeone").toString(), DictCodeList.WIND一级分类_含债券一级分类等_优先股.getValue())) {
                infRiskRdsDto.setTradeNum(ArithmeticUtil.divide(finDto.getTradeAmt(),100,10));//可转债为申购券面总额
            }
        }
        //存款类
        if(StringUtil.equals(DictCodeList.平安资产类型_存款.getValue(),v.getAssetType())){
            infRiskRdsDto.setAmount(StringUtil.isNotEmpty(dataMap.get("tradeAmtAndFee"))?dataMap.get("tradeAmtAndFee") instanceof BigDecimal ?(BigDecimal)dataMap.get("tradeAmtAndFee"):new BigDecimal(dataMap.get("tradeAmtAndFee").toString()):null);
        }
        //基金，计划类
        if(StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_赎回.getValue())){
            infRiskRdsDto.setAmount(finDto.getShareAmt());
        }
        if (StringUtil.equals(finDto.getBusiPs(), DictCodeList.回购拆借方向_拆入.getValue())||
                StringUtil.equals(finDto.getBusiPs(), DictCodeList.回购拆借方向_拆出.getValue())) {
            Map priObj=StringUtil.isNotEmpty(dataMap.get("finTradeProduct"))?((Map)dataMap.get("finTradeProduct")):null;
            infRiskRdsDto.setAmount(StringUtil.isNotEmpty(priObj)?priObj.get("prin") instanceof BigDecimal?(BigDecimal) priObj.get("prin"):new BigDecimal(priObj.get("prin").toString()):null);//净值型计划类资产：申购金额（元）货币型计划类资产（申购）：申购金额（元）回购、拆借：本金金额（元）
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
            infRiskRdsDto.setAssetType(DictCodeList.平安资产类型_拆借.getValue());
            infRiskRdsDto.setAssetType2(finDto.getBusiPs());
        }
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(),v.getFinType())||
                StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(),v.getFinType())||
                StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAmount(finDto.getPrinAmt());//回购、拆借：本金金额（元）
            //回购利率
            infRiskRdsDto.setAssetRate(StringUtil.isNotEmpty(dataMap.get("rebackPrice"))?dataMap.get("rebackPrice") instanceof BigDecimal ?ArithmeticUtil.divide((BigDecimal)dataMap.get("rebackPrice"),new BigDecimal(100),4):ArithmeticUtil.divide(new BigDecimal(dataMap.get("rebackPrice").toString()),new BigDecimal(100),4):null);
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
            infRiskRdsDto.setAssetType(DictCodeList.平安资产类型_质押式回购.getValue());
            infRiskRdsDto.setAssetType2(finDto.getPs());
            infRiskRdsDto.setCalendarCode((String)dataMap.get("calendarId"));
            infRiskRdsDto.setDeadline(StringUtil.isNotEmpty(dataMap.get("term"))?new BigDecimal(dataMap.get("term").toString()):null);
        }
        if(StringUtil.equals(finDto.getPs(), DictCodeList.回购拆借方向_融入.getValue())||
                StringUtil.equals(finDto.getPs(), DictCodeList.回购拆借方向_融出.getValue())){
            infRiskRdsDto.setAmount(StringUtil.isNotEmpty(dataMap.get("mTradeAmt"))?dataMap.get("mTradeAmt") instanceof BigDecimal ?(BigDecimal)dataMap.get("mTradeAmt"):new BigDecimal(dataMap.get("mTradeAmt").toString()):null);
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_债券借贷.getValue());
            infRiskRdsDto.setAssetType2(finDto.getPs());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
            infRiskRdsDto.setCalendarCode((String)dataMap.get("calendarId"));
            infRiskRdsDto.setDeadline(StringUtil.isNotEmpty(dataMap.get("term"))?new BigDecimal(dataMap.get("term").toString()):null);
        }
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue(),v.getFinType())||
                StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue(),v.getFinType())||
                StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAssetType(DictCodeList.平安资产类型_买断式回购.getValue());
            infRiskRdsDto.setAssetType2(finDto.getPs());
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
            infRiskRdsDto.setAmount(finDto.getfSettleAmt());//债券为全价总额存款、计划类利率型、项目类资产：实付金额（元）基金（申购）、净值型计划类资产：申购金额（元）货币型计划类资产（申购）：申购金额（元）回购、拆借：本金金额（元）
            infRiskRdsDto.setTradeNum(StringUtil.isNotEmpty(dataMap.get("secTotalPrin"))?dataMap.get("secTotalPrin") instanceof BigDecimal ?(BigDecimal)dataMap.get("secTotalPrin"):new BigDecimal(dataMap.get("secTotalPrin").toString()):null);
            infRiskRdsDto.setDeliveryDate(finDto.getTradeDate());
            //回购利率
            infRiskRdsDto.setAssetRate(StringUtil.isNotEmpty(dataMap.get("repoRate"))?dataMap.get("repoRate") instanceof BigDecimal ?ArithmeticUtil.divide((BigDecimal)dataMap.get("repoRate"),new BigDecimal(100),4):ArithmeticUtil.divide(new BigDecimal(dataMap.get("repoRate").toString()),new BigDecimal(100),4):null);
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
            infRiskRdsDto.setCalendarCode((String)dataMap.get("dealCalId"));
            infRiskRdsDto.setDeadline(StringUtil.isNotEmpty(dataMap.get("repoterm"))?new BigDecimal(dataMap.get("repoterm").toString()):null);
        }
        if(StringUtil.equals(DictCodeListExt2.平安资产类型_一级市场债.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAmount(finDto.getFpriceAmt());//债券为全价总额存款
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_一级市场债.getValue());
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
        }
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue(),v.getFinType())||
                StringUtil.equals(DictCodeListExt2.平安资产类型_利率互换.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAmount(finDto.getPrin() == null ? BigDecimal.ZERO : finDto.getPrin().multiply(new BigDecimal(10000)));//债券为全价总额存款、
            infRiskRdsDto.setTradeNum(finDto.getPrin() == null ? BigDecimal.ZERO : finDto.getPrin().multiply(new BigDecimal(10000)));
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_利率互换.getValue());
            infRiskRdsDto.setPs(DictCodeList.交易方向_买入.getValue());
        }
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_期权.getValue(),v.getFinType())||
                StringUtil.equals(DictCodeListExt3.平安资产类型_期权.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAssetType(DictCodeListExt3.平安资产类型_期权.getValue());
            infRiskRdsDto.setAmount(StringUtil.isNotEmpty(dataMap.get("optFee"))?dataMap.get("optFee") instanceof BigDecimal ?(BigDecimal)dataMap.get("optFee"):new BigDecimal(dataMap.get("optFee").toString()):null);
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
        }
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_掉期.getValue(),v.getFinType())||
                StringUtil.equals(DictCodeListExt2.平安资产类型_外汇掉期.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_外汇掉期.getValue());
            infRiskRdsDto.setAmount(StringUtil.isNotEmpty(dataMap.get("nDealCurAmt"))?dataMap.get("nDealCurAmt") instanceof BigDecimal ?(BigDecimal)dataMap.get("nDealCurAmt"):new BigDecimal(dataMap.get("nDealCurAmt").toString()):null);
        }
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_远期.getValue(),v.getFinType())||
                StringUtil.equals(DictCodeListExt2.平安资产类型_外汇远期.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_外汇远期.getValue());
            infRiskRdsDto.setAmount(finDto.getPrinAmt());
        }
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_即期.getValue(),v.getFinType())||
                StringUtil.equals(DictCodeListExt2.平安资产类型_外汇即期.getValue(),v.getAssetType())) {
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_外汇即期.getValue());
            infRiskRdsDto.setAmount(finDto.getPrinAmt());
        }
        if(StringUtil.equals(DictCodeListExt2.平安资产类型_一级市场债.getValue(),v.getAssetType())){
            if(StringUtil.isNotEmpty(dataMap.get("biddate"))) {
                infRiskRdsDto.setEventDate(DateUtil.format(new Date(Long.parseLong(dataMap.get("biddate").toString())), DateUtil.DEFAULT_DATE_FORMAT_EN));
            }else if(StringUtil.isNotEmpty(dataMap.get("payDate"))){
                infRiskRdsDto.setEventDate(DateUtil.format(new Date(Long.parseLong(dataMap.get("payDate").toString())), DateUtil.DEFAULT_DATE_FORMAT_EN));
            }
            infRiskRdsDto.setEventId(StringUtil.isNotEmpty(dataMap.get("bidId"))?dataMap.get("bidId").toString():StringUtil.EMPTY);
        }
        infRiskRdsDto.setTradeAmtReal(infRiskRdsDto.getAmount());
        return infRiskRdsDto;
    }

    /**
     * 交易实体封装
     * @param dataMap
     * @param v
     * @return
     * @throws Exception
     */
    private InterfRiskRdsDto periodEntityDev(Map<String, Object> dataMap,V v) throws Exception {
        //定义返回实体
        InterfRiskRdsDto infRiskRdsDto=new InterfRiskRdsDto(); 
        TradeBaseDto finDto=(TradeBaseDto)JsonUtils.map2entity(dataMap,TradeBaseDto.class);
        finDto.setBusiCode(removeLasStr(finDto.getBusiCode()));
        finDto.setTotleTradeId(removeLasStr(finDto.getTotleTradeId()));
        infRiskRdsDto.setEventDate(StringUtil.isNotEmpty(finDto.getmDate())?finDto.getmDate():DateUtil.format(finDto.getMdate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
        //设置母组合代码和子组合代码
        infRiskRdsDto.setPortfolioCode(AllTradeCommonUtil.exchangePortfolio(StringUtil.isNotEmpty(finDto.getPortfolioId())?finDto.getPortfolioId():finDto.getActPortfolioId()));
        infRiskRdsDto.setEventId(StringUtil.isNotEmpty(finDto.getBusiCode())?finDto.getBusiCode()+ trimInfStr(infRiskRdsDto.getPortfolioCode()):finDto.getTotleTradeId()+ trimInfStr(infRiskRdsDto.getPortfolioCode()));
        infRiskRdsDto.setAssetCode(finDto.getFinprodId());
        infRiskRdsDto.setAssetType(v.getAssetType());
        infRiskRdsDto.setPs(finDto.getPs());
        infRiskRdsDto.setAmount(finDto.getTradeAmt());//项目类,基金，计划类申购,股票
        infRiskRdsDto.setDeliveryDate(finDto.getSettleDate());
        infRiskRdsDto.setMarketCode(finDto.getFinprodId());
        infRiskRdsDto.setTradePlace(finDto.getTradeMarket());
        infRiskRdsDto.setIsWarnNeedConfirm(DictCodeList.是否_否.getValue());
        infRiskRdsDto.setIsApprNeedConfirm(DictCodeList.是否_否.getValue());
        if(StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),v.getAssetType())||StringUtil.equals(DictCodeList.平安资产类型_项目类资产.getValue(),v.getAssetType())) {
            infRiskRdsDto.setEventId(StringUtil.isNotEmpty(finDto.getBusiCode())?finDto.getBusiCode()+ trimInfStr(infRiskRdsDto.getPortfolioCode())+trimInfStr(finDto.getInvAim()):finDto.getTotleTradeId()+ trimInfStr(infRiskRdsDto.getPortfolioCode())+trimInfStr(finDto.getInvAim()));
            infRiskRdsDto.setAmount(finDto.getFpriceAmt());//债券为全价总额存款、计划类利率型、项目类资产：本金金额（元）基金（申购）、
            infRiskRdsDto.setDeliveryDate(finDto.getSettleDate());
            if(finDto.getPs().equals(DictCodeList.交易方向_行权_赎回.getValue())){
                infRiskRdsDto.setEventId(StringUtil.isNotEmpty(dataMap.get("bizCode"))?dataMap.get("bizCode").toString()+ trimInfStr(infRiskRdsDto.getPortfolioCode())+trimInfStr(finDto.getInvAim()):finDto.getTotleTradeId()+ trimInfStr(infRiskRdsDto.getPortfolioCode())+trimInfStr(finDto.getInvAim()));
                infRiskRdsDto.setAmount(StringUtil.isNotEmpty(dataMap.get("cPriceAmt"))?dataMap.get("cPriceAmt") instanceof BigDecimal ?(BigDecimal)dataMap.get("cPriceAmt"):new BigDecimal(dataMap.get("cPriceAmt").toString()):null);
                if(StringUtil.isNotEmpty(dataMap.get("payDate"))) {
                	if(dataMap.get("payDate") instanceof Date) {
                		infRiskRdsDto.setEventDate(DateUtil.format((Date)dataMap.get("payDate")));
                	}
                	else {
                		//时间戳
                		String type = dataMap.get("payDate").getClass().toString();
                		if("Long".equals(type.substring(type.lastIndexOf(".")+1,type.length()))) {
                			infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("payDate"))));
                		}
                		else {
                			infRiskRdsDto.setEventDate(dataMap.get("payDate").toString());
                		}
                	}
                }
            }
        }
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_存款.getValue(),finDto.getFinprodType2())
                &&StringUtil.equals(finDto.getPs(),DictCodeList.交易方向_中间付息.getValue())){
            infRiskRdsDto.setEventDate(DateUtil.format(finDto.getTradeDate()));
        }
        if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_货币型.getValue(),v.getAssetType())
                ||StringUtil.equals(DictCodeList.平安资产类型_计划类资产_净值型.getValue(),v.getAssetType())
                ||StringUtil.equals(DictCodeList.平安资产类型_基金.getValue(),v.getAssetType())) {
            if(finDto.getPs().equals(DictCodeListExt2.交易方向_份额折算.getValue())){
                if(StringUtil.isNotEmpty(dataMap.get("conversionTime"))) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date(Long.parseLong(dataMap.get("conversionTime").toString())), DateUtil.DEFAULT_DATE_FORMAT_EN));
                }
            }
            //红利再投，除权登记不影响直接退出(撤销时因为撤销的是除权登记交易，所以需要增加判断)
            if(finDto.getPs().equals(DictCodeList.交易方向_红利再投.getValue())
                ||(finDto.getPs().equals(DictCodeList.交易方向_除权登记.getValue())
                    && !StringUtil.equals(BussinessDictCodeListRds.接口系统ID_实占释放接口.getValue(),v.getInfCode()))){
                return null;
            }
            //因为撤销操作使用的是除权登记的交易编号，正向分红交易时使用前置交易编号作为关联
            if(StringUtil.equals(BussinessDictCodeListRds.接口系统ID_实占释放接口.getValue(),v.getInfCode())) {
                if((finDto.getPs().equals(DictCodeList.交易方向_现金分红.getValue())
                    ||(finDto.getPs().equals(DictCodeList.交易方向_除权登记.getValue())))
                    &&StringUtil.isNotEmpty(dataMap.get("arriveDate"))){
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date(Long.parseLong(dataMap.get("arriveDate").toString())), DateUtil.DEFAULT_DATE_FORMAT_EN));
                }
            } 
            else {
                if(finDto.getPs().equals(DictCodeList.交易方向_现金分红.getValue())){
                    if(StringUtil.isNotEmpty(dataMap.get("bTradeId"))) {
                        infRiskRdsDto.setEventId(dataMap.get("bTradeId").toString()+ trimInfStr(infRiskRdsDto.getPortfolioCode()));
                    }
                    if(StringUtil.isNotEmpty(dataMap.get("arriveDate"))) {
                        infRiskRdsDto.setEventDate(DateUtil.format(new Date(Long.parseLong(dataMap.get("arriveDate").toString())), DateUtil.DEFAULT_DATE_FORMAT_EN));
                    }
                }
            }

        }
        if (StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_拆入.getValue())||
                StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue())||
                StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_拆出.getValue())) {
            infRiskRdsDto.setAssetType(DictCodeList.平安资产类型_拆借.getValue());
            infRiskRdsDto.setAssetType2(StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_拆入.getValue())||StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue())?DictCodeList.回购拆借方向_拆入.getValue():DictCodeList.回购拆借方向_拆出.getValue());
            infRiskRdsDto.setPs(DictCodeList.交易方向_到期.getValue());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
        }
        if(StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())||
                StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
            infRiskRdsDto.setAmount(finDto.getFpriceAmt());//回购、拆借：到期金额（元）
            infRiskRdsDto.setAssetType(DictCodeList.平安资产类型_质押式回购.getValue());
            infRiskRdsDto.setAssetType2(StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())?DictCodeList.回购拆借方向_正回购.getValue():DictCodeList.回购拆借方向_逆回购.getValue());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
            if(StringUtil.equals(DictCodeList.交易方向_到期续做.getValue(),finDto.getPs())){
                infRiskRdsDto.setAmount(StringUtil.isNotEmpty(dataMap.get("tradeAmtex"))?dataMap.get("tradeAmtex") instanceof BigDecimal ?(BigDecimal)dataMap.get("tradeAmtex"):new BigDecimal(dataMap.get("tradeAmtex").toString()):finDto.getFpriceAmt());
            }
            List<String> calendars =  finTradeProductMapper.getCalendarByFinprodId((String)dataMap.get("finprodId"));
            if(calendars.size()>0) {
            	infRiskRdsDto.setCalendarCode(calendars.get(0));
            }
            if(StringUtil.isNotEmpty(dataMap.get("mDate"))) {
            	infRiskRdsDto.setMdate(DateUtil.parse((String)dataMap.get("mDate")));
            }
        }
        if(StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_期权.getValue())) {
            infRiskRdsDto.setAssetType(DictCodeListExt3.平安资产类型_期权.getValue());
            infRiskRdsDto.setAmount(finDto.getFpriceAmt());//回购、拆借：到期金额（元）
            infRiskRdsDto.setEventDate(DateUtil.format(finDto.getTradeDate()));
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
        }
        if(StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())||
                StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue())){
            infRiskRdsDto.setAmount(StringUtil.isNotEmpty(dataMap.get("mTradeAmt")) ? dataMap.get("mTradeAmt") instanceof BigDecimal ? (BigDecimal) dataMap.get("mTradeAmt") : new BigDecimal(dataMap.get("mTradeAmt").toString()) : BigDecimal.ZERO);
            infRiskRdsDto.setTradeNum(infRiskRdsDto.getAmount());
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_债券借贷.getValue());
            infRiskRdsDto.setAssetType2(StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())?
                    DictCodeList.回购拆借方向_融入.getValue():DictCodeList.回购拆借方向_融出.getValue());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
            List<String> calendars =  finTradeProductMapper.getCalendarByFinprodId((String)dataMap.get("finprodId"));
            if(calendars.size()>0) {
            	infRiskRdsDto.setCalendarCode(calendars.get(0));
            }
            if(StringUtil.isNotEmpty(dataMap.get("mdate"))) {
                // add by jiangpeng on 20210423 start
                // ZGBSSE-139 债券借贷存续期-融出，待办事项提交报错
                //时间戳
                if (dataMap.get("mdate") instanceof Long) {
                    infRiskRdsDto.setMdate(new Date((long) dataMap.get("mdate")));
                } else {
                    infRiskRdsDto.setMdate((Date) dataMap.get("mdate"));
                }
//            	infRiskRdsDto.setMdate((Date)dataMap.get("mdate"));
                // add by jiangpeng on 20210423 end
            }
        }
        if(StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())||
                StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())) {
            infRiskRdsDto.setAssetType(DictCodeList.平安资产类型_买断式回购.getValue());
            infRiskRdsDto.setAssetType2(StringUtil.equals(finDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())?DictCodeList.回购拆借方向_正回购.getValue():DictCodeList.回购拆借方向_逆回购.getValue());
            infRiskRdsDto.setPs(DictCodeList.交易方向_到期.getValue());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
            List<String> calendars =  finTradeProductMapper.getCalendarByFinprodId((String)dataMap.get("finprodId"));
            if(calendars.size()>0) {
            	infRiskRdsDto.setCalendarCode(calendars.get(0));
            }
            if(StringUtil.isNotEmpty(dataMap.get("mDate"))) {
            	infRiskRdsDto.setMdate(DateUtil.parse((String)dataMap.get("mDate")));
            }
        }
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_利率互换.getValue(),finDto.getFinprodType2())) {
            if(StringUtil.equals(DictCodeList.交易方向_收取.getValue(),finDto.getPs())||StringUtil.equals(DictCodeList.交易方向_支付.getValue(),finDto.getPs())){
                infRiskRdsDto.setAmount(StringUtil.isNotEmpty(dataMap.get("beforeEndAmt")) ? 
                    dataMap.get("beforeEndAmt") instanceof BigDecimal ? (BigDecimal) dataMap.get("beforeEndAmt") : new BigDecimal(dataMap.get("beforeEndAmt").toString()) : BigDecimal.ZERO);
            }
            else {
                BigDecimal fixIntAmt = StringUtil.isNotEmpty(dataMap.get("fixIntAmt"))?new BigDecimal(dataMap.get("fixIntAmt").toString()):BigDecimal.ZERO;
                BigDecimal floatIntAmt = StringUtil.isNotEmpty(dataMap.get("floatIntAmt"))?new BigDecimal(dataMap.get("floatIntAmt").toString()):BigDecimal.ZERO;
                infRiskRdsDto.setAmount(ArithmeticUtil.doAdd(fixIntAmt,floatIntAmt));
            }
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_利率互换.getValue());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
        }
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue(),finDto.getFinprodType2())) {
            infRiskRdsDto.setAssetType(DictCodeListExt2.平安资产类型_股票.getValue());
            if (StringUtil.isNotEmpty(dataMap.get("tradeDateStr"))) {
                infRiskRdsDto.setEventDate(dataMap.get("tradeDateStr").toString());
            }else {
                infRiskRdsDto.setEventDate(DateUtil.format(finDto.getTradeDate()));
            } 
            //分红送转相当于一笔现金分红一笔送转(送转不考虑)
            if(StringUtil.equals(DictCodeList.股票分红类型_现金分红.getValue(),finDto.getPs())
//            ||StringUtil.equals(DictCodeList.股票分红类型_分红送转.getValue(),finDto.getPs())
            ){
                infRiskRdsDto.setAmount(finDto.getFpriceAmt());
//                infRiskRdsDto.setTradeNum(finDto.getFpriceAmt());//现金分红不传份额，不影响股票数量
                infRiskRdsDto.setPs(DictCodeList.交易方向_现金分红.getValue());
            }
            //送转
            if(StringUtil.equals(DictCodeList.股票分红类型_送转.getValue(),finDto.getPs())){
//                infRiskRdsDto.setAmount(finDto.getShareAmt());
                infRiskRdsDto.setTradeNum(finDto.getShareAmt());//送转时只传份额，不传金额，需要影响股票数量，影响实占
                infRiskRdsDto.setPs(DictCodeList.交易方向_红利再投.getValue());
            }
        }
        //自定义现金流
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue(),finDto.getFinprodType2())) {
            if(StringUtil.isNotEmpty(dataMap.get("busiNo"))){
                infRiskRdsDto.setEventId(dataMap.get("busiNo").toString()+ trimInfStr(infRiskRdsDto.getPortfolioCode()));
            }else{
                return null;
            }
            infRiskRdsDto.setAmount(finDto.getTradeAmt());
            infRiskRdsDto.setEventDate(DateUtil.format(finDto.getSettleDate()));
            //无投管大类暂塞金融产品分类，防止接口必填项check
            infRiskRdsDto.setAssetType(DictCodeListExt1.金融产品类型_投管用_自定义现金流.getValue());
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setMarketCode(null);
        }
        //产品端除这些交易方向的排除在外不调实占
        if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue(),finDto.getFinprodType2())){
            //产品端使用的是dealType存储交易方向的，这里需要转换一下
            if(!StringUtil.isNotEmpty(infRiskRdsDto.getPortfolioCode()) && StringUtil.isNotEmpty(finDto.getFinprodId())){ 
                FinProductAdd finProductAdd = finProductAddMapper.selectByPrimaryKey(finDto.getFinprodId());
                if(StringUtil.isNotEmpty(finProductAdd)){ 
                    infRiskRdsDto.setPortfolioCode(finProductAdd.getPortfolioId());
                }
            }
            if(产品端_认购赎回分红.contains(finDto.getPs())){
                //处理产品端各种场景内部实体给接口字段赋值
                setProductInfColumn(infRiskRdsDto,finDto,dataMap);
            } else {
                //退出不调风险接口
                return null;
            }

        }
        // BUG BSTOT-2281 by zhangrui1 start
        // 债券中间付息、还本、到期和相应的撤销，应该用实收本息总额
        repairSet(infRiskRdsDto, dataMap);
        // BUG BSTOT-2281 by zhangrui1 end
        
        infRiskRdsDto.setTradeAmtReal(infRiskRdsDto.getAmount());
        //存款，债券，质押式回购取tradeAmt
        if(CommonUtil.isType(infRiskRdsDto.getAssetType(), DictCodeList.平安资产类型_债券)|| 
        	CommonUtil.isType(infRiskRdsDto.getAssetType(), DictCodeList.平安资产类型_存款)||
            CommonUtil.isType(infRiskRdsDto.getAssetType(), DictCodeList.平安资产类型_质押式回购)){
        	infRiskRdsDto.setTradeAmtReal(finDto.getTradeAmt());
        }
        
        //ZGBSS-451 计划利率资产、项目类资产、买断式回购存续期交易金额取理论值start
        if(CommonUtil.isType(infRiskRdsDto.getAssetType(), DictCodeList.平安资产类型_买断式回购)
        	||CommonUtil.isType(infRiskRdsDto.getAssetType(), DictCodeList.平安资产类型_计划类资产_利率型)
            || CommonUtil.isType(infRiskRdsDto.getAssetType(), DictCodeList.平安资产类型_项目类资产)) {
        	String key = "theoryAmt";
        	if(CommonUtil.isType(infRiskRdsDto.getAssetType(), DictCodeList.平安资产类型_买断式回购)) {
        		key = "fpriceAmt";
        	}
            Object obj = dataMap.get(key);
            if (StringUtil.isNotEmpty(obj)) {
            	infRiskRdsDto.setAmount(new BigDecimal(obj.toString()));
            }
        }
        //ZGBSS-451 计划利率资产、项目类资产、买断式回购存续期交易金额取理论值end
        return infRiskRdsDto;
    }

    // BUG BSTOT-2281 by zhangrui1 start
    /**
     * 针对遗漏的场景进行字段设定
     * 
     * @param dto 
     * @param dataMap
     * @author zhangrui1
     * @see BUG BSTOT-2281
     */
    private void repairSet(InterfRiskRdsDto dto, Map<String, Object> dataMap) {
        // 债券中间付息、还本、到期和相应的撤销，应该用实收本息总额
        if (( CommonUtil.isType(dto.getAssetType(), DictCodeList.平安资产类型_债券)
                || CommonUtil.isType(dto.getAssetType(), DictCodeList.平安资产类型_存款)
//                || CommonUtil.isType(dto.getAssetType(), DictCodeList.平安资产类型_计划类资产_利率型)
//                || CommonUtil.isType(dto.getAssetType(), DictCodeList.平安资产类型_项目类资产)
                )
                && (CommonUtil.isType(dto.getPs(), DictCodeList.交易方向_还本)
                        || CommonUtil.isType(dto.getPs(), DictCodeList.交易方向_到期)
                        || CommonUtil.isType(dto.getPs(), DictCodeList.交易方向_收息))) {
            String key = "fpriceAmt";
            Object obj = dataMap.get(key);
            if (StringUtil.isNotEmpty(obj)) {
                dto.setAmount(new BigDecimal(obj.toString()));
            }
        }
    }
    // BUG BSTOT-2281 by zhangrui1 end

    /**
     * 为批量调用接口获取各交易类型的分配列表
     * @param dataMap
     * @param v
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Map<String, Object>> getBatchAllocation(Map<String, Object> dataMap,V v){
        List<Map<String, Object>> retMap=new ArrayList<Map<String, Object>>();
        if(StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),v.getAssetType())&&StringUtil.isNotEmpty(dataMap.get("bussTrdTransfenpeiDto"))){
            ((List)dataMap.get("bussTrdTransfenpeiDto")).forEach(en->{
                Map<String, Object> dataReMap=new HashMap<>();
                dataReMap.putAll(dataMap);
                dataReMap.putAll((Map)en);
                retMap.add(dataReMap);
            });
        }
        else if(StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(),v.getAssetType())&&StringUtil.isNotEmpty(dataMap.get("bussTrdTransfenpeiDto"))){
            ((List)dataMap.get("bussTrdTransfenpeiDto")).forEach(en->{
                Map<String, Object> dataReMap=new HashMap<>();
                dataReMap.putAll(dataMap);
                dataReMap.putAll((Map)en);
                retMap.add(dataReMap);
            });
        }
        else if(StringUtil.equals(DictCodeList.平安资产类型_基金.getValue(),v.getAssetType())&&StringUtil.isNotEmpty(dataMap.get("bussTrdTransfenpeiDto"))){
            ((List)dataMap.get("bussTrdTransfenpeiDto")).forEach(en->{
                Map<String, Object> dataReMap=new HashMap<>();
                dataReMap.putAll(dataMap);
                dataReMap.putAll((Map)en);
                retMap.add(dataReMap);
            });
        }
        else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_净值型.getValue(),v.getAssetType())&&StringUtil.isNotEmpty(dataMap.get("bussTrdTransfenpeiDto"))){
            ((List)dataMap.get("bussTrdTransfenpeiDto")).forEach(en->{
                Map<String, Object> dataReMap=new HashMap<>();
                dataReMap.putAll(dataMap);
                dataReMap.putAll((Map)en);
                retMap.add(dataReMap);
            });
        }
        else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_利率型.getValue(),v.getAssetType())&&StringUtil.isNotEmpty(dataMap.get("bussTrdTransfenpeiDto"))){
            ((List)dataMap.get("bussTrdTransfenpeiDto")).forEach(en->{
                Map<String, Object> dataReMap=new HashMap<>();
                dataReMap.putAll(dataMap);
                dataReMap.putAll((Map)en);
                retMap.add(dataReMap);
            });
        }else if(StringUtil.equals(DictCodeList.平安资产类型_计划类资产_货币型.getValue(),v.getAssetType())&&StringUtil.isNotEmpty(dataMap.get("bussTrdTransfenpeiDto"))){
            ((List)dataMap.get("bussTrdTransfenpeiDto")).forEach(en->{
                Map<String, Object> dataReMap=new HashMap<>();
                dataReMap.putAll(dataMap);
                dataReMap.putAll((Map)en);
                retMap.add(dataReMap);
            });
        }else if(StringUtil.equals(DictCodeList.平安资产类型_存款.getValue(),v.getAssetType())
            || StringUtil.equals(DictCodeList.平安资产类型_项目类资产.getValue(),v.getAssetType())
            &&StringUtil.isNotEmpty(dataMap.get("bussTrdTransfenpeiDto"))){
            ((List)dataMap.get("bussTrdTransfenpeiDto")).forEach(en->{
                Map<String, Object> dataReMap=new HashMap<>();
                dataReMap.putAll(dataMap);
                dataReMap.putAll((Map)en);
                retMap.add(dataReMap);
            });
        }
        else if(StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(),v.getAssetType())&&StringUtil.isNotEmpty(dataMap.get("bussRepoSecPtl"))){
            Map bussRepoSecPtlMap = JsonUtils.object2Map(dataMap.get("bussRepoSecPtl")) ;
            if(StringUtil.isNotEmpty(bussRepoSecPtlMap.get("pledgeBondList"))){
                ((List)bussRepoSecPtlMap.get("pledgeBondList")).forEach(en->{
                    Map<String, Object> dataReMap=new HashMap<>();
                    dataReMap.putAll(dataMap);
                    dataReMap.putAll((Map)en);
                    retMap.add(dataReMap);
                });
            }
        }
        else if(StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(),v.getAssetType())&&StringUtil.isNotEmpty(dataMap.get("trdOutRightRepoTradePtl"))){
            ((List)dataMap.get("trdOutRightRepoTradePtl")).forEach(en->{
                Map<String, Object> dataReMap=new HashMap<>(); 
                dataReMap.putAll(dataMap);
                dataReMap.putAll((Map)en); 
                if (StringUtil.isNotEmpty(dataReMap.get("firstSettleAmt"))) {
                    dataReMap.put("fSettleAmt", dataReMap.get("firstSettleAmt"));
                }
                retMap.add(dataReMap);
            });
        }
        if(StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),v.getAssetType())&&StringUtil.isNotEmpty(dataMap.get("bussCombDistribtList"))){
            ((List)dataMap.get("bussCombDistribtList")).forEach(en->{
                Map<String, Object> dataReMap=new HashMap<>();
                dataReMap.putAll(dataMap);
                dataReMap.putAll((Map)en);
                retMap.add(dataReMap);
            });
        }
        return retMap;
    }
    
    /**
     * 处理产品端的实体构造
     * @param infRiskRdsDto
     * @param finDto
     * @param dataMap
     * @throws Exception
     */
    private void setProductInfColumn(InterfRiskRdsDto infRiskRdsDto,TradeBaseDto finDto,Map<String, Object> dataMap) throws Exception{
            //收益类型
            String profitType = (String)dataMap.get("profitType");
            infRiskRdsDto.setPs(finDto.getPs());
            infRiskRdsDto.setEventId((String)dataMap.get("tradeId")+ trimInfStr(finDto.getPortfolioId()));
            infRiskRdsDto.setAssetCode(null);
            infRiskRdsDto.setAssetType(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
            infRiskRdsDto.setMarketCode(null);
            //净值型产品
            if(StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), profitType)) {
                if(StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品认购.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("subConfirmDate"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("subDealAmt")));
                    infRiskRdsDto.setTradeNum(new BigDecimal((String)dataMap.get("subDealNum")));
                }
                else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品申购.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("purConfirmDate"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("purDealAmt")));
                    infRiskRdsDto.setTradeNum(new BigDecimal((String)dataMap.get("purDealNum")));
                }
                else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品赎回.getValue())
                        || StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品提前赎回.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("redConfirmDate"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("redDealAmt")));
                    infRiskRdsDto.setTradeNum(new BigDecimal((String)dataMap.get("redDealNum")));
                }
                else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_份额强增.getValue())
                        || StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_份额强减.getValue())) {

                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("addReduceConfirmDate"))));
                    //ZGBSS-49【待办事项】：审批份额强增流程报错 20210118 start
                    if(dataMap.get("addReduceDealAmt") != null) {                    	
                    	infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("addReduceDealAmt")));
                    }
                    if(dataMap.get("addReduceDealNum") != null) {                    	
                    	infRiskRdsDto.setTradeNum(new BigDecimal((String)dataMap.get("addReduceDealNum")));
                    }
                    //ZGBSS-49【待办事项】：审批份额强增流程报错 20210118 end
                    if(infRiskRdsDto.getAmount()==null) {
                    	infRiskRdsDto.setAmount(BigDecimal.ZERO);
                    	infRiskRdsDto.setNotCalcAmount(true);
                    }
                }
                else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_提前终止.getValue())) {

                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("expireConfirmDate"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("expireDealAmt")));
                    infRiskRdsDto.setTradeNum(new BigDecimal((String)dataMap.get("expireDealNum")));
                }
                else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品现金分红.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("actBonusDate"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("bonusCashAmt")));
                }

            }
            //货币型产品
            else if ( StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), profitType)) {
                if(StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品认购.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("subConfirmDate03"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("subDealAmt03")));
                }
                else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品申购.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("purConfirmDate03"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("purDealAmt03")));
                }
                else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品赎回.getValue())
                        || StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品提前赎回.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("redConfirmDate03"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("redDealAmt03")));
                }
                else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_份额强增.getValue())
                        || StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_份额强减.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("addReduceConfirmDate03"))));
                    //ZGBSS-49【待办事项】：货币型产品审批份额强增流程报错 20210126 start
                    if(dataMap.get("addReduceDealAmt03") != null) {                    	
                    	infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("addReduceDealAmt03")));
                    }
                    if(dataMap.get("addReduceDealNum03") != null) {                    	
                    	infRiskRdsDto.setTradeNum(new BigDecimal((String)dataMap.get("addReduceDealNum03")));
                    }
                    //ZGBSS-49【待办事项】：货币型产品审批份额强增流程报错 20210126 end
                    if(infRiskRdsDto.getAmount()==null) {
                    	infRiskRdsDto.setAmount(BigDecimal.ZERO);
                    	infRiskRdsDto.setNotCalcAmount(true);
                    }
                }
                else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_提前终止.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("expireConfirmDate03"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("expireDealAmt03")));
                }
                else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品现金分红.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("bonusCashBonusDate03"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("bonusCashBonusAmt03")));
                }
            }
            //收益型产品
            else if (StringUtil.equals(DictCodeList.收益类型_收益型.getValue(), profitType)) {
                infRiskRdsDto.setEventId((String)dataMap.get("tradeId")+ trimInfStr((String)dataMap.get("ptlId")));
                infRiskRdsDto.setPortfolioCode((String)dataMap.get("ptlId"));
                if(StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品认购.getValue())
                        || StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品申购.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("vdate"))));
                    infRiskRdsDto.setAmount(new BigDecimal((String)dataMap.get("currentSub")));
                }
                else if(StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品提前赎回.getValue())) {
                    infRiskRdsDto.setEventId((String)dataMap.get("totleTradeId")+ trimInfStr((String)dataMap.get("ptlId")));
                    infRiskRdsDto.setEventDate((String)dataMap.get("tradeDate"));
                    infRiskRdsDto.setAmount(ArithmeticUtil.doAdd(new BigDecimal((String)dataMap.get("prinAmt")),new BigDecimal((String)dataMap.get("actCusProfit"))));
                }
                else if(StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品赎回.getValue())) {
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("payDate"))));
                    infRiskRdsDto.setAmount(ArithmeticUtil.doAdd(new BigDecimal((String)dataMap.get("reduceAmt")),new BigDecimal((String)dataMap.get("actCusProfit"))));
                }
            }

            //费用和税收
            if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_产品付费.getValue())) {
                List<BussTrdTransferOrderDto> bussTrdTransferOrder =JsonUtils.json2List(JsonUtils.object2Json(dataMap.get("bussTrdTransferOrder")), BussTrdTransferOrderDto.class);
                if(!StringUtil.isEmptyList(bussTrdTransferOrder) && bussTrdTransferOrder.size()>0) {
                    BussTrdTransferOrderDto order = bussTrdTransferOrder.get(0);
                    infRiskRdsDto.setEventId((String)dataMap.get("tradeId")+ trimInfStr(order.getPortfolioId()));
                    infRiskRdsDto.setEventDate(DateUtil.format(new Date((long)dataMap.get("payDate"))));
                    infRiskRdsDto.setAmount(order.getSettleAmt());
                    infRiskRdsDto.setPortfolioCode(order.getPortfolioId());
                    //计提式确认页面代码用的是finproduId，而不规则费用新建页面是finprodId
                    if(!StringUtil.isNotEmpty(infRiskRdsDto.getMarketCode())) {
                        infRiskRdsDto.setMarketCode((String)dataMap.get("finproduId"));
                    }

//                    if(!StringUtil.isNotEmpty(infRiskRdsDto.getAssetCode())) {
//                        infRiskRdsDto.setAssetCode((String)dataMap.get("finproduId"));
//                    }
                }

            }
            else if (StringUtil.equals(finDto.getPs(), DictCodeList.交易方向_税费缴税.getValue())) {
                List<BussTrdTransferOrderDto> bussTrdTransferOrder =JsonUtils.json2List(JsonUtils.object2Json(dataMap.get("bussTrdTransferOrder")), BussTrdTransferOrderDto.class);
                if(!StringUtil.isEmptyList(bussTrdTransferOrder) && bussTrdTransferOrder.size()>0) {
                    BussTrdTransferOrderDto order = bussTrdTransferOrder.get(0);
                    infRiskRdsDto.setEventId((String)dataMap.get("totleTradeId")+ trimInfStr(order.getPortfolioId()));
                    infRiskRdsDto.setEventDate(order.getSettleDateText());
                    infRiskRdsDto.setAmount(order.getSettleAmt());
                }

            }

    }
    
    /**
     * 查询组合包明细数据
     * @param parameters
     * @return RskPortfolioPackage003Dto
     * @since 1.0
     */
    public List<String> queryPortfolioPackageDtlByBagId(String bagId) {
          List<String>  list = restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionPortf003Point/queryPortfolioPackageDtlByBagId?bagId="+bagId
           ,List.class);
          return list;
    }
     
}
