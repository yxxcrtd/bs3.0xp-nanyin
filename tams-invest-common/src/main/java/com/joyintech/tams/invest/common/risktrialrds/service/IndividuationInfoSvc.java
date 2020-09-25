package com.joyintech.tams.invest.common.risktrialrds.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.IColBaseCond;
import com.joyintech.tams.invest.common.risktrialrds.dto.SupervisionTargetDto;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 处理个性化参数
 * @author 裴家俊
 * @version 1.0 2019年10月2日
 * @see IndividuationInfoSvc
 * @since 1.0
 */
@Service
public class IndividuationInfoSvc<T extends IColBaseCond> {

	/**
	 * 判断是否设置特殊阈值
	 * @param mapOther
	 * @param key
	 * @param point
	 */
	public  void dealIndSpecialStage(Map<String,Object> mapOther, String key, SupervisionTargetDto point,Class<T> cls){
		List<T> rskFilterList=dealIndividuationInfo(mapOther,key,point,cls);
		setYuzhiCondition(rskFilterList,point);
	}

	/**
	 * 判断是否设置特殊阈值
	 * @param rskIndSpecialStageList
	 * @param point
	 */
	public  boolean dealIndSpecialStageAst(List<T> rskIndSpecialStageList, SupervisionTargetDto point){
		List<T> rskFilterList=rskIndSpecialStageList.stream().filter(item->item.getFinprodId().equals(point.getFinprodId())).collect(Collectors.toList());
		return setYuzhiCondition(rskFilterList,point);
	}

	/**
	 * 判断是否设置特殊阈值
	 * @param rskIndSpecialStageList
	 * @param point
	 */
	public  boolean dealIndAstDel(List<T> rskIndSpecialStageList, SupervisionTargetDto point){
		List<T> rskFilterList=rskIndSpecialStageList.stream().filter(item->item.getFinprodId().equals(point.getFinprodId())).collect(Collectors.toList());
		if(StringUtil.isEmptyList(rskFilterList))
			return false;
		else
			return true;
	}

	/**
	 * 过滤个性化条件信息
	 * @param mapOther
	 * @param key
	 * @param point
	 * @param cls
	 * @return
	 */
	public  List<T> dealIndividuationInfo(Map<String,Object> mapOther, String key, SupervisionTargetDto point,Class<T> cls){
		List<T> rskIndividuationInfoList=RiskUtil.getMapOtherObj(mapOther,key,cls);
		List<T> rskFilterList=rskIndividuationInfoList.stream().filter(item->item.getPrtBagId().equals(point.getPortfolioId())&&item.getIndId().equals(point.getIndId())).collect(Collectors.toList());
		return rskFilterList;
	}

	/**
	 * 赋新的阈值
	 * @param rskFilterList
	 * @param point
	 */
	private boolean setYuzhiCondition(List<T> rskFilterList,SupervisionTargetDto point){
		boolean newColCond=false;
		for(T dto:rskFilterList){
			if(!StringUtil.isEmpty(dto.getFoulCond())){
				point.setFoulCond(dto.getFoulCond());
				newColCond=true;
			}
			if(!StringUtil.isEmpty(dto.getApproveCond())){
				point.setApproveCond(dto.getApproveCond());
				newColCond=true;
			}
			if(!StringUtil.isEmpty(dto.getWarnCond())){
				point.setWarnCond(dto.getWarnCond());
				newColCond=true;
			}
		}
		return newColCond;
	}
}
