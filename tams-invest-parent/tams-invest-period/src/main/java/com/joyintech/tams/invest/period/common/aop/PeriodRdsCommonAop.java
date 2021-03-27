/*
 * 文件名：PeriodRdsCommonAop.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.common.aop;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.component.business.riskrds.constant.InterfUtil;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.redisinit.core.AopBaseService;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IAssetInfo;
import com.joyintech.workflow.engine.constant.OperationType;
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
 * 存续期提交与撤销时调用风险逻辑aop
 *
 * @author 裴家俊
 * @version 1.0 2020年1月7日
 * @see PeriodRdsCommonAop
 * @since 1.0
 */
@Component
@Aspect
public class PeriodRdsCommonAop extends AopBaseService<InterfRiskRdsDto> {
    
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PeriodRdsCommonAop.class);


    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    
    private List<String> 利率互换_付息_融入券_逾期计息要素维护=Arrays.asList("TrdIRSDuration004Service","TrdBondLendTargetDuration002Service","TrdOverdueCalcuPenaltyService","TrdOptionDuration002Service");
 
   
    /**
     * 提交与撤销操作
     */
    @Pointcut("execution(* com.joyintech.tams.invest.period..*.*.doComplete(..))")
    protected void completePoint() {

    }

    @After("completePoint()")
    public void completePeriodMethod(JoinPoint joinPoint) throws BusinessException {
        if(!CommonUtil.isType(CacheUtil.getSysParam("pageRedisFlag"), BussinessDictCodeListRds.是否_是)||!StringUtil.isNotEmpty(joinPoint.getArgs())||joinPoint.getArgs().length<=1){
            return;
        }
        //利率互换_付息_融入券去除
        if(利率互换_付息_融入券_逾期计息要素维护.contains(joinPoint.getTarget().getClass().getSimpleName())){
            return;
        }
        logger.info("==========={}类调用实占或释放接口开始=============",joinPoint.getTarget().getClass().getSimpleName());
        //构造调用场景与交易类型
        InterfRiskRdsDto interfRiskRdsDto = new InterfRiskRdsDto();
        interfRiskRdsDto.setEventType(BussinessDictCodeListRds.事件类型_投资交易.getValue());
        interfRiskRdsDto.setEventStatus(BussinessDictCodeListRds.事件类型_投资交易_存续期.getValue());
        //调用共通方法包括处理封装实体及调用接口逻辑
        completePeriodCommon(joinPoint,interfRiskRdsDto,(param,entry)->interfRiskRdsService.sendRiskRdsData(param, entry));
        logger.info("==========={}类调用实占或释放接口结束=============",joinPoint.getTarget().getClass().getSimpleName());

      /*  Object[] args = joinPoint.getArgs();
        OperationType operationType = (OperationType) args[1];
        Map<String,Object> mapTrd=JsonUtils.object2Map(args[0]);
        String assetType=StringUtil.EMPTY;
        if(StringUtil.isNotEmpty(mapTrd.get(InterfUtil.finprodId))){
            Map<String, IAssetInfo> mapAsset = assetInfoSrc.getMapFromRedis(RedisKeyEnum.资产基本信息.key(), Arrays.asList(mapTrd.get(InterfUtil.finprodId).toString()));
            if(StringUtil.isNotEmpty(mapAsset)&&StringUtil.isNotEmpty(mapAsset.values())) {
                for (IAssetInfo iAst : mapAsset.values()) {
                    assetType = StringUtil.isNotEmpty(iAst)?iAst.getAssetType():assetType;
                }
            }
            if (!StringUtil.isNotEmpty(mapTrd.get("mDate")) && !StringUtil.isNotEmpty(mapTrd.get("mdate"))) {
                FinProduct finProduct = finProductMapper.selectByPrimaryKey(mapTrd.get(InterfUtil.finprodId).toString());
                mapTrd.put("mdate", finProduct.getMdate());
            }
        } 
        InterfRiskRdsDto interfRiskRdsDto = new InterfRiskRdsDto();
        interfRiskRdsDto.setEventType(BussinessDictCodeListRds.事件类型_投资交易.getValue());
        interfRiskRdsDto.setEventStatus(BussinessDictCodeListRds.事件类型_投资交易_存续期.getValue());
        interfRiskRdsDto.setAssetType(assetType);
        revokeBackRiskCommon(mapTrd,joinPoint,en->{
            interfRiskRdsDto.setInfCode(operationType.equals(OperationType.REVOKE)?BussinessDictCodeListRds.接口系统ID_实占释放接口.getValue():BussinessDictCodeListRds.接口系统ID_实占接口.getValue());
            if(StringUtil.isNotEmpty(en)) {
                mapTrd.putAll(JsonUtils.object2Map(en));
            }
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
        });*/
    }

    /**
     * 构造调用风险实体
     * @param mapTrd
     * @param joinPoint
     * @param var
     * @throws BusinessException
     */
    @Override
    protected void assembleCalEntity(Map<String, Object> mapTrd, JoinPoint joinPoint) throws BusinessException {
       try {
            //债券借贷
            if(joinPoint.getTarget().getClass().getSimpleName().contains("TrdSecLendDuration002Service")){
                if(StringUtil.isNotEmpty(mapTrd.get("trdFinProductDealTotal"))) {
                    WflTrdFinProductDealTotal wflTrdFinProductDealTotal = (WflTrdFinProductDealTotal) JsonUtils.map2entity((Map)mapTrd.get("trdFinProductDealTotal"), WflTrdFinProductDealTotal.class);
                    setBasicTradeInfo(mapTrd, wflTrdFinProductDealTotal.getPs(), wflTrdFinProductDealTotal.getFinprodType2());
                }
            }
            else if(StringUtil.isNotEmpty(mapTrd.get("baseTotalTradeInfoDto"))) {
                    BaseTotalTradeInfoDto baseTotalTradeInfoDto = (BaseTotalTradeInfoDto) JsonUtils.map2entity((Map)mapTrd.get("baseTotalTradeInfoDto"),BaseTotalTradeInfoDto.class);
                    if (StringUtil.isNotEmpty(baseTotalTradeInfoDto) && StringUtil.isNotEmpty(baseTotalTradeInfoDto.getTrdFinProductDealTotal())) {
                        String ps = baseTotalTradeInfoDto.getTrdFinProductDealTotal().getPs();
                        if(joinPoint.getTarget().getClass().getSimpleName().contains("TrdFundDurationNew001Service")){
                            ps = (String) mapTrd.get("ps");
                        }
                        setBasicTradeInfo(mapTrd,ps,baseTotalTradeInfoDto.getTrdFinProductDealTotal().getFinprodType2());
                    }
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
       
    }

    /**
     * 设置每个交易品种过来的类型(分配的明细列表)
     * @param mapTrd
     * @param joinPoint
     */
    @Override
    protected Object setEachAstType(Map<String,Object> mapTrd,JoinPoint joinPoint){
         //股票
        if(joinPoint.getTarget().getClass().getSimpleName().contains("TrdStockBonus002Service")){
            return mapTrd.get("dividDetailInfoList");
        }
        //债券行权
        if(joinPoint.getTarget().getClass().getSimpleName().contains("TrdBondDuration003Service")){
            return mapTrd.get("trdBondPtlSecExerciseInfos");
        }
        return mapTrd.get("finAssetIntInfoList");
    }


}
