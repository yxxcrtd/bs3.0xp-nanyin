/*
 * 文件名：TradeRdsCommonAop.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.common.aop;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.riskrds.constant.InterfUtil;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IAssetInfo;
import com.joyintech.tams.invest.common.riskinfrds.info.service.AssetInfoRedisService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.tams.invest.common.risktrialrds.RiskTrialComm;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 交易撤销时调用风险逻辑aop
 *
 * @author 裴家俊
 * @version 1.0 2020年1月7日
 * @see TradeRdsCommonAop
 * @since 1.0
 */
@Component
@Aspect
public class TradeRdsCommonAop {
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(TradeRdsCommonAop.class);

    /**
     * 资产基本信息更新Redis服务
     */
    @Autowired
    private AssetInfoRedisService assetInfoSrc;

    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;
    
    @Autowired
    private RiskTrialComm riskTrialComm;
    
    private List<String> 通道费=Arrays.asList("ChannelFeePay002Service");
	/*@Pointcut("execution(* com.joyintech.workflow.engine.service.ProcessStartService.start(..))")
    protected void startPoint() {

    }*/

    /**
     * 退回时在待办中再提交
     */
    @Pointcut("execution(* com.joyintech.tams.invest.trade..*.*.doUpdate(..))")
    protected void updatePoint() {

    }

    /**
     * 撤销操作
     */
    @Pointcut("execution(* com.joyintech.tams.invest.trade..*.*.doComplete(..))")
    protected void revokePoint() {

    }

    /**
     * 退回操作
     */
    @Pointcut("execution(* com.joyintech.tams.invest.trade..*.*.doBack(..))")
    protected void backPoint() {
    }

    @After("updatePoint()")
    public void updateTradeMethod(JoinPoint joinPoint) throws BusinessException {
        if(!CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)||!StringUtil.isNotEmpty(joinPoint.getArgs())){
            return;
        }
        Object[] args = joinPoint.getArgs();
        Map<String,Object> mapTrd=JsonUtils.object2Map(args[0]);
        if (StringUtil.isNotEmpty(mapTrd.get("operateType"))&&mapTrd.get("operateType").equals("submit")) {
            //如果是撤销的退回再提交直接退去，不需要调实占
            if(!checkRevokeAction(mapTrd.get("workflowId"))){
                return;
            }
        	revokeBackRiskCommon(mapTrd,BussinessDictCodeListRds.接口系统ID_实占接口.getValue(),joinPoint);
        }
    }

    @After("revokePoint()")
    public void revokeTradeMethod(JoinPoint joinPoint) throws BusinessException {
        if(!CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)||!StringUtil.isNotEmpty(joinPoint.getArgs())||joinPoint.getArgs().length<=1){
            return;
        }
        Object[] args = joinPoint.getArgs();
        OperationType operationType = (OperationType) args[1];
        Map<String,Object> mapTrd=JsonUtils.object2Map(args[0]);
        if (operationType.equals(OperationType.REVOKE)||operationType.equals(OperationType.DELETE)) {
            revokeBackRiskCommon(mapTrd,BussinessDictCodeListRds.接口系统ID_实占释放接口.getValue(),joinPoint);
        }
    }
    @After("backPoint()")
    public void backTradeMethod(JoinPoint joinPoint) throws BusinessException {
        if(!CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)||!StringUtil.isNotEmpty(joinPoint.getArgs())){
            return;
        }
        Object[] args = joinPoint.getArgs();
        Map<String,Object> mapTrd=JsonUtils.object2Map(args[0]);
        if(StringUtil.isNotEmpty(mapTrd)&&StringUtil.isNotEmpty(mapTrd.get("operateType"))){
            if(StringUtil.equals(mapTrd.get("operateType").toString(),"delete")||StringUtil.equals(mapTrd.get("operateType").toString(),"revoke")){
                return;
            }
        }
       //如果是撤销的退回再提交直接退去，不需要调实占
        if(!checkRevokeAction(mapTrd.get("workflowId"))){
            return;
        }
        revokeBackRiskCommon(mapTrd,BussinessDictCodeListRds.接口系统ID_实占释放接口.getValue(),joinPoint);
    }

    private void revokeBackRiskCommon(Map<String,Object> mapTrd,String infCode,JoinPoint joinPoint) throws BusinessException {
    	  //通道费支付去除
        if(通道费.contains(joinPoint.getTarget().getClass().getSimpleName())){
            return;
        }
		setEachAstType(mapTrd,joinPoint);
        String assetType=StringUtil.EMPTY;
        //买断式回购中finprodId与资产大类不匹配
        if(StringUtil.isNotEmpty(mapTrd.get("elementId"))&&mapTrd.get("elementId").toString().startsWith("TrdOutRightRepoTrade00")) {
        	assetType = "F10";
        }
        else if(StringUtil.isNotEmpty(mapTrd.get(InterfUtil.finprodId))){
            Map<String, IAssetInfo> mapAsset = assetInfoSrc.getMapFromRedis(RedisKeyEnum.资产基本信息.key(), Arrays.asList(mapTrd.get(InterfUtil.finprodId).toString()));
            if(StringUtil.isNotEmpty(mapAsset)&&StringUtil.isNotEmpty(mapAsset.values())) {
                for (IAssetInfo iAst : mapAsset.values()) {
                    assetType = StringUtil.isNotEmpty(iAst)?iAst.getAssetType():assetType;
                }
            }
        }
        //质押式回购和拆借交易无标的，无法从标的中获取资产大类
        else if(StringUtil.isNotEmpty(mapTrd.get("inputValue1"))) {
        	assetType = mapTrd.get("inputValue1").toString();
        }
        InterfRiskRdsDto interfRiskRdsDto = new InterfRiskRdsDto();
        interfRiskRdsDto.setEventType(BussinessDictCodeListRds.事件类型_投资交易.getValue());
        interfRiskRdsDto.setInfCode(infCode);
        interfRiskRdsDto.setAssetType(StringUtil.isNotEmpty(assetType)?assetType:StringUtil.EMPTY);
        interfRiskRdsDto.setFinType(StringUtil.isNotEmpty(mapTrd.get(InterfUtil.finprodType2))?mapTrd.get(InterfUtil.finprodType2).toString():StringUtil.EMPTY);
        try {
            ClientData result = interfRiskRdsService.sendRiskRdsData(mapTrd, interfRiskRdsDto);
            if(StringUtil.isNotEmpty(result)&&StringUtil.isNotEmpty(result.getHead())&&StringUtil.isNotEmpty(result.getHead().get(InterfUtil.std400msgt))){
                if(StringUtil.equals(result.getHead().get(InterfUtil.std400msgt).toString(),InterfUtil.E)&&StringUtil.isNotEmpty(result.getHead().get(InterfUtil.stdrtninfo))){
                    throw new BusinessException(InterfUtil.MSG+result.getHead().get(InterfUtil.stdrtninfo).toString());
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * 设置每个交易品种过来的类型
     * @param mapTrd
     * @param joinPoint
     */
    private void setEachAstType(Map<String,Object> mapTrd,JoinPoint joinPoint) throws BusinessException {
        //买断式回购(由于退回再提交时无法识别买断式)
        if(!StringUtil.isNotEmpty(mapTrd.get(InterfUtil.finprodType2))&&joinPoint.getTarget().getClass().getSimpleName().contains("TrdOutRightRepoTrade002Service")){
            if(StringUtil.isNotEmpty(mapTrd.get(InterfUtil.ps))&&StringUtil.equals(mapTrd.get(InterfUtil.ps).toString(),DictCodeList.回购拆借方向_正回购.getValue())){
                mapTrd.put(InterfUtil.finprodType2, DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
            }else{
                mapTrd.put(InterfUtil.finprodType2, DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
            }
        }
    	//期权
        if(!StringUtil.isNotEmpty(mapTrd.get(InterfUtil.finprodType2))&&joinPoint.getTarget().getClass().getSimpleName().contains("TrdOptionTrade002Service")){
            mapTrd.put(InterfUtil.finprodType2, DictCodeListExt3.金融产品类型_投管用_期权.getValue());
        }
        //即期
        if(!StringUtil.isNotEmpty(mapTrd.get(InterfUtil.finprodType2))&&joinPoint.getTarget().getClass().getSimpleName().contains("TrdSpotexInvestTrade002Service")){
            mapTrd.put(InterfUtil.finprodType2, DictCodeListExt1.金融产品类型_投管用_即期.getValue());
        }
        //掉期
        if(!StringUtil.isNotEmpty(mapTrd.get(InterfUtil.finprodType2))&&joinPoint.getTarget().getClass().getSimpleName().contains("TrdFxswapInvestTrade002Service")){
            mapTrd.put(InterfUtil.finprodType2, DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
        }
        //远期
        if(!StringUtil.isNotEmpty(mapTrd.get(InterfUtil.finprodType2))&&joinPoint.getTarget().getClass().getSimpleName().contains("TrdForwexInvestTrade002Service")){
            mapTrd.put(InterfUtil.finprodType2, DictCodeListExt1.金融产品类型_投管用_远期.getValue());
        }
        //债转股
        if(joinPoint.getTarget().getClass().getSimpleName().contains("TrdBondToStock002Service")) {
            try {
                if (StringUtil.isNotEmpty(mapTrd.get("baseTotalTradeInfoDto"))) {
                    BaseTotalTradeInfoDto baseTotalTradeInfoDto = (BaseTotalTradeInfoDto) JsonUtils.map2entity((Map) mapTrd.get("baseTotalTradeInfoDto"), BaseTotalTradeInfoDto.class);
                    if (StringUtil.isNotEmpty(baseTotalTradeInfoDto) && StringUtil.isNotEmpty(baseTotalTradeInfoDto.getTrdFinProductDealTotal())) {
                        mapTrd.put(InterfUtil.ps, baseTotalTradeInfoDto.getTrdFinProductDealTotal().getPs());
                    }
                }
            }catch (Exception e){
                throw new BusinessException(e);
            }
        }
    }
    /**
     * 如果是撤销的退回再提交直接退去，不需要调实占
     * @param workflowId
     * @param <T>
     * @return
     */
    private <T> boolean checkRevokeAction(T workflowId){
        if(StringUtil.isNotEmpty(workflowId)) {
            String actionType = riskTrialComm.selectWorkflowActionType(workflowId.toString());
            if (actionType.equals("revoke")||actionType.equals("delete")) {
                return false;
            }
        }
        return true;
    }
}
