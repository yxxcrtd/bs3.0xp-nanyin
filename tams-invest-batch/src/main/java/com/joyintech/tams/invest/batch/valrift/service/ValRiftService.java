/*
 * 文件名：ValRiftService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.valrift.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.tams.common.base.dao.PtlSecPierceValutionMapper;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.valrift.dao.ValRiftMapper;
import com.joyintech.tams.invest.batch.valrift.dto.PtlSecPierceValutionDto;
import com.joyintech.tams.invest.batch.valrift.dto.PtlSecValutionDto;
import com.joyintech.tams.invest.batch.valrift.dto.SubPtlNode;
import com.joyintech.tams.invest.batch.valrift.dto.ValtblAssetSubDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 *  估值穿透
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月25日
 * @see ValRiftService
 * @since 1.0
 */
@Service
public class ValRiftService {

    @Autowired
    private ValRiftMapper valRiftMapper;
    
    @Autowired
    private PtlSecPierceValutionMapper ptlSecPierceValutionMapper;
    
    /**
     * 穿透处理
     * <功能详细描述>
     * @param currentDate
     * @param productIdList
     * @see
     * @since 1.0
     */
    public void run(Date currentDate, List<String> productIdList){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        paramMap.put("cdate", sdf.format(currentDate));
        paramMap.put("productIdList", productIdList);
        //穿透组合
        List<PtlSecValutionDto> ptlList = selectPtlIdForTask(paramMap);
        for(PtlSecValutionDto ptl : ptlList){
            paramMap.put("portfolioId", ptl.getPortfolioId());
            List<PtlSecPierceValutionDto> result = new ArrayList<PtlSecPierceValutionDto>();
            //组合估值资产
            List<PtlSecValutionDto> ptlValList = selectPtlValAsset(paramMap);
            for(PtlSecValutionDto ptlVal : ptlValList){
                String portfolioId = StringUtil.isNotEmpty(ptlVal.getFinprodMarketId()) ? ptlVal.getFinprodMarketId() :
                        ptlVal.getPortfolioId();
                paramMap.put("cdate", DateUtil.format(currentDate));
                paramMap.put("portfolioId", portfolioId);
                List<ValtblAssetSubDto> positionList = valRiftMapper.selectValtblAssetSub(paramMap);//底层资产
                if (positionList != null && !positionList.isEmpty()) {
                    // 新建计划类标的信息 生成组合 市场代码作为组合代码
                    List<ValtblAssetSubDto> leafAssetPlan = dealRiftByPtlId(portfolioId, currentDate, ptlVal.getShareAmt(),
                        positionList);
                    if (null != leafAssetPlan && !leafAssetPlan.isEmpty()) {
                        for (ValtblAssetSubDto valtblAssetSubDto : leafAssetPlan) {
                            PtlSecPierceValutionDto ptlSecPierceValutionDto = new PtlSecPierceValutionDto();
                            ptlSecPierceValutionDto.setPortfolioId(ptlVal.getPortfolioId());
                            ptlSecPierceValutionDto.setCdate(currentDate);
                            ptlSecPierceValutionDto.setFinprodId(valtblAssetSubDto.getFinprodId());
                            ptlSecPierceValutionDto.setSubCode(valtblAssetSubDto.getSubCode());
                            ptlSecPierceValutionDto.setPositionRatio(valtblAssetSubDto.getPositionRatio());
                            ptlSecPierceValutionDto.setShareAmt(valtblAssetSubDto.getSecurityQuantity());
                            ptlSecPierceValutionDto.setDscCostAmt(valtblAssetSubDto.getSecurityCost());
                            //应计利息为空
                            if(StringUtil.startsWith(valtblAssetSubDto.getSubCode(),"1204")
                            || StringUtil.startsWith(valtblAssetSubDto.getSubCode(),"1203")//基金红利
                            ){
                                ptlSecPierceValutionDto.setTdyIntincexp(valtblAssetSubDto.getSecurityCost());
                            }else{
                                ptlSecPierceValutionDto.setTdyIntincexp(BigDecimal.ZERO);
                            }
                            //应计利息为空
                            ptlSecPierceValutionDto.setMarketValue(valtblAssetSubDto.getSmv());
                            result.add(ptlSecPierceValutionDto);
                        }
                    }
                }
                else {
                    PtlSecPierceValutionDto ptlSecPierceValutionDto = new PtlSecPierceValutionDto();
                    BeanUtil.copyPropertiesIgnoreNull(ptlSecPierceValutionDto, ptlVal);
                    result.add(ptlSecPierceValutionDto);
                }
            }
            //计算穿透后指标---modify by 裴家俊 2019/04/03---start--------
            List<PtlSecPierceValutionDto> resultRep = calValIndex(result);
            //删除当日跑批数据
            deleteValRift(currentDate,ptl);
            //入库
            writeValRift(resultRep);
            //计算穿透后指标---modify by 裴家俊 2019/04/03---end--------
        }
    }
    
    /**
     * 计算穿透后指标
     * <功能详细描述>
     * @param result
     * @see
     * @since 1.0
     */
    private List<PtlSecPierceValutionDto> calValIndex(List<PtlSecPierceValutionDto> result){
    	List<PtlSecPierceValutionDto> resultRep = new ArrayList<PtlSecPierceValutionDto>();
        Map<String,PtlSecPierceValutionDto> mapAsset = new HashMap<String,PtlSecPierceValutionDto>();
        for(PtlSecPierceValutionDto bean : result){
            if(StringUtil.isNotEmpty(bean.getFinprodId())){
                    if(mapAsset.containsKey(bean.getFinprodId())){
                        PtlSecPierceValutionDto ptlSecPierceValutionDto  = (PtlSecPierceValutionDto)mapAsset.get(bean.getFinprodId());
                        //数量
                        if(StringUtil.isNotEmpty(ptlSecPierceValutionDto.getShareAmt())){
                            if(StringUtil.isNotEmpty(bean.getShareAmt())){
                                bean.setShareAmt(ArithmeticUtil.doAdd(ArithmeticUtil.multiply(ptlSecPierceValutionDto.getShareAmt(),ptlSecPierceValutionDto.getPositionRatio()),
                                        ArithmeticUtil.multiply(bean.getShareAmt(),bean.getPositionRatio())));
                            }else{
								//update by xr for TISHKBS-405 	
                                bean.setShareAmt(ArithmeticUtil.multiply(ptlSecPierceValutionDto.getShareAmt(),ptlSecPierceValutionDto.getPositionRatio()));
                            }
                        }
                        //摊销总成本
                        if(StringUtil.isNotEmpty(ptlSecPierceValutionDto.getDscCostAmt())
                                &&StringUtil.isNotEmpty(bean.getDscCostAmt())){
                            if(!StringUtil.startsWith(ptlSecPierceValutionDto.getSubCode(),"1204")
                            && !StringUtil.startsWith(ptlSecPierceValutionDto.getSubCode(),"1203")
                            ){
                                if(!StringUtil.startsWith(bean.getSubCode(),"1204")
                                && !StringUtil.startsWith(bean.getSubCode(),"1203")
                                ){
                                    bean.setDscCostAmt(ArithmeticUtil.doAdd(ArithmeticUtil.multiply(ptlSecPierceValutionDto.getDscCostAmt(),ptlSecPierceValutionDto.getPositionRatio()),
                                            ArithmeticUtil.multiply(bean.getDscCostAmt(),bean.getPositionRatio())));
                                }else{
									//update by xr for TISHKBS-405 	
                                    bean.setDscCostAmt(ArithmeticUtil.multiply(ptlSecPierceValutionDto.getDscCostAmt(),ptlSecPierceValutionDto.getPositionRatio()));
                                }
                            }
                        }
                        //add by zhagnnian 20191014 start
                        //利息
                        if(StringUtil.isNotEmpty(ptlSecPierceValutionDto.getTdyIntincexp())
                                ){
                            if(StringUtil.isNotEmpty(bean.getTdyIntincexp())){

                                if(StringUtil.startsWith(bean.getSubCode(),"1204")
                                || StringUtil.startsWith(bean.getSubCode(),"1203") //基金
                                ){
                                    bean.setTdyIntincexp(ArithmeticUtil.doAdd(ArithmeticUtil.multiply(ptlSecPierceValutionDto.getTdyIntincexp(),ptlSecPierceValutionDto.getPositionRatio()),
                                            ArithmeticUtil.multiply(bean.getTdyIntincexp(),bean.getPositionRatio())));
                                }else{
									//update by xr for TISHKBS-405 	
                                    bean.setTdyIntincexp(ArithmeticUtil.multiply(ptlSecPierceValutionDto.getTdyIntincexp(),ptlSecPierceValutionDto.getPositionRatio()));
                                }
                            }else{
									//update by xr for TISHKBS-405 	
                                    bean.setTdyIntincexp(ArithmeticUtil.multiply(ptlSecPierceValutionDto.getTdyIntincexp(),ptlSecPierceValutionDto.getPositionRatio()));
                            }
                        }
                        //add by zhagnnian 20191014 end
                        //市值
                        if(StringUtil.isNotEmpty(ptlSecPierceValutionDto.getMarketValue())
                                &&StringUtil.isNotEmpty(bean.getMarketValue())){
                            if(!StringUtil.startsWith(ptlSecPierceValutionDto.getSubCode(),"1204")
                            && !StringUtil.startsWith(ptlSecPierceValutionDto.getSubCode(),"1203")
                            ){
                                if(!StringUtil.startsWith(bean.getSubCode(),"1204")
                                && !StringUtil.startsWith(bean.getSubCode(),"1203")
                                ){
                                    bean.setMarketValue(ArithmeticUtil.doAdd(ArithmeticUtil.multiply(ptlSecPierceValutionDto.getMarketValue(),ptlSecPierceValutionDto.getPositionRatio()),
                                            ArithmeticUtil.multiply(bean.getMarketValue(),bean.getPositionRatio())));
                                }else{
									//update by xr for TISHKBS-405 	
                                    bean.setMarketValue(ArithmeticUtil.multiply(ptlSecPierceValutionDto.getMarketValue(),ptlSecPierceValutionDto.getPositionRatio()));
                                }
                            }
                        }
                        bean.setPositionRatio(new BigDecimal(1));//重置持仓比例
                        mapAsset.put(bean.getFinprodId(), bean);
                }else{
                    //市值单独计算
                    // 市值= 摊销总成本+isnull(公允价值变动,当日应计利息)
                    /*bean.setMarketValue(ArithmeticUtil.doAdd(ArithmeticUtil.doAdd(null!=bean.getDscCostAmt()?bean.getDscCostAmt():BigDecimal.ZERO,null!=bean.getTdyFloatIngpl()?bean.getTdyFloatIngpl():BigDecimal.ZERO),null!=bean.getTdyIntincexp()?bean.getTdyIntincexp():BigDecimal.ZERO));*/
                    mapAsset.put(bean.getFinprodId(), bean);
                }
            }
        }
        //-----add by 裴家俊  2019/04/03----重新构造避免重复金融产品代码---start----
    	for (Entry<String, PtlSecPierceValutionDto> entry : mapAsset.entrySet()) {
			if (entry.getValue().getShareAmt() != null
					) {
				entry.getValue().setShareAmt(ArithmeticUtil.multiply(entry.getValue().getShareAmt()
						,entry.getValue().getPositionRatio()));
			}
			if (entry.getValue().getDscCostAmt() != null ) {
				entry.getValue().setDscCostAmt(ArithmeticUtil.multiply(entry.getValue().getDscCostAmt()
						,entry.getValue().getPositionRatio()));
			}
            //市值单独计算
            // 市值= 摊销总成本+isnull(公允价值变动,当日应计利息)
			if (entry.getValue().getMarketValue() == null) {
				entry.getValue().setMarketValue(ArithmeticUtil.multiply(ArithmeticUtil.doAdd(ArithmeticUtil.doAdd(null!=entry.getValue().getDscCostAmt()?entry.getValue().getDscCostAmt():BigDecimal.ZERO,null!=entry.getValue().getTdyFloatIngpl()?entry.getValue().getTdyFloatIngpl():BigDecimal.ZERO),null!=entry.getValue().getTdyIntincexp()?entry.getValue().getTdyIntincexp():BigDecimal.ZERO)
						,entry.getValue().getPositionRatio()));
			}else{
                entry.getValue().setMarketValue(ArithmeticUtil.multiply(entry.getValue().getMarketValue()
                        ,entry.getValue().getPositionRatio()));
            }
			if (entry.getValue().getTdyIntincexp() != null ) {
				entry.getValue().setTdyIntincexp(ArithmeticUtil.multiply(entry.getValue().getTdyIntincexp()
						,entry.getValue().getPositionRatio()));
			}
			resultRep.add(entry.getValue());
		}
    	return resultRep;
    	//-----add by 裴家俊  2019/04/03----重新构造避免重复金融产品代码---end----
    }
    
    /**
     * 处理穿透底层资产---add by 裴家俊---2019/04/03-----
     * <功能详细描述>
     * @param leafAssetPlan
     * @param portfolioId
     * @param vdate
     * @param shareAmt 理财产品数量
     * @param positionList
     * @see
     * @since 1.0
     */
    private List<ValtblAssetSubDto> dealRiftByPtlId(String portfolioId, Date vdate, BigDecimal shareAmt, List<ValtblAssetSubDto> positionList){
        SubPtlNode node = new SubPtlNode(portfolioId);
        ValtblAssetSubDto valAssetEntry=new ValtblAssetSubDto();
        valAssetEntry.setFinprodMarketId(portfolioId);
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("cdate", DateUtil.format(vdate));
        paramMap.put("portfolioId", node.getCode());
        //实收资本
        BigDecimal valCapital = valRiftMapper.selectValCapital(paramMap);
        List<ValtblAssetSubDto> leafAssetPlan = new ArrayList<>();
        if(null!=valCapital&&valCapital!= BigDecimal.ZERO){
            node.setPositionRatio(ArithmeticUtil.divide(ArithmeticUtil.multiply(node.getPositionRatio(),StringUtil.isNotEmpty(shareAmt)?shareAmt:BigDecimal.ZERO), valCapital));
        }
        
        if(null!=positionList&&!positionList.isEmpty()){
            for(ValtblAssetSubDto valtblAssetSubDto : positionList){
                valtblAssetSubDto.setPositionRatio(node.getPositionRatio());
                if(StringUtil.isEmpty(valtblAssetSubDto.getFinprodId())){
                    valtblAssetSubDto.setFinprodId(Long.toString(node.getNextVirtAssetObjectId()));
                }
                valtblAssetSubDto.setPtlId(portfolioId);
                leafAssetPlan.add(valtblAssetSubDto); // 添加到List中等待处理
            }
        }
        return leafAssetPlan;
    }
    
  /*  private void dealRiftByPtlId(List<ValtblAssetSubDto> leafAssetPlan,String portfolioId,Date vdate,BigDecimal shareAmt){
        SubPtlQueue subPtlQueue = new SubPtlQueue();
        SubPtlNode node = new SubPtlNode(portfolioId);
        while(null!=node){
            Map<String,Object> paramMap = new HashMap<String,Object>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            paramMap.put("cdate", sdf.format(vdate));
            paramMap.put("portfolioId", node.getCode());
            List<ValtblAssetSubDto> positionList = valRiftMapper.selectValtblAssetSub(paramMap);//底层资产
            if(null!=positionList&&!positionList.isEmpty()){
                for(ValtblAssetSubDto valtblAssetSubDto : positionList){
                	//-------add by 裴家俊  避免买了自身这个组合资产，陷入死循环-----start----------
                	if(StringUtil.isNotEmpty(valtblAssetSubDto.getFinprodMarketId())&&StringUtil.equals(portfolioId, valtblAssetSubDto.getFinprodMarketId())){
                		continue;
                	}
                	//-------add by 裴家俊  避免买了自身这个组合资产，陷入死循环-----end----------
                    this.addSubNode(valtblAssetSubDto,vdate,subPtlQueue,node,shareAmt);
                    valtblAssetSubDto.setPositionRatio(node.getPositionRatio());
                    if(StringUtil.isEmpty(valtblAssetSubDto.getFinprodId())){
                        valtblAssetSubDto.setFinprodId(Long.toString(node.getNextVirtAssetObjectId()));
                    }
                    if(StringUtil.equals(valtblAssetSubDto.getHasChildren(), DictCodeList.是否_否.getValue())){
                        valtblAssetSubDto.setPtlId(portfolioId);
                        leafAssetPlan.add(valtblAssetSubDto); // 添加到List中等待处理
                    }
                }
            }
            // 执行下一个
            node = subPtlQueue.pop();
        }
    }*/
    
    /**
     * 迭代node
     * <功能详细描述>
     * @param valtblAssetSubDto
     * @param vdate
     * @param subPtlQueue
     * @param node
     * @param shareAmt 理财产品数量
     * @see
     * @since 1.0
     */
/*    private void addSubNode(ValtblAssetSubDto valtblAssetSubDto,Date vdate,SubPtlQueue subPtlQueue,SubPtlNode parentNode,BigDecimal shareAmt){
        String finprodId = valtblAssetSubDto.getFinprodMarketId();//更改为市场代码
        if(StringUtil.isNotEmpty(finprodId)){
            Map<String,Object> paramMap = new HashMap<String,Object>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            paramMap.put("cdate", sdf.format(vdate));
            paramMap.put("portfolioId", finprodId); //计划类资产市场代码作为组合代码入库
            List<ValtblAssetSubDto> positionList = valRiftMapper.selectValtblAssetSub(paramMap);
            if(null!=positionList&&!positionList.isEmpty()){
                valtblAssetSubDto.setHasChildren(DictCodeList.是否_是.getValue());
            }
            //实收资本
            BigDecimal valCapital = valRiftMapper.selectValCapital(paramMap);
            if(null!=valCapital&&valCapital!= BigDecimal.ZERO){
                SubPtlNode node = new SubPtlNode(finprodId);
                node.setPositionRatio(ArithmeticUtil.divide(ArithmeticUtil.multiply(parentNode.getPositionRatio(),shareAmt), valCapital));
                subPtlQueue.push(node);
            }
        }
    }*/
    /**
     * 获取参加任务的组合数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public List<PtlSecValutionDto> selectPtlIdForTask(Map<String,Object> paramMap){
        return valRiftMapper.selectPtlIdForTask(paramMap);
    }
    
    /**
     * 获取组合估值资产
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public List<PtlSecValutionDto> selectPtlValAsset(Map<String,Object> paramMap){
        return valRiftMapper.selectPtlValAsset(paramMap);
    }
    
    /**
     * 保存穿透后的估值数据
     * <功能详细描述>
     * @param result
     * @see
     * @since 1.0
     */
    private void  writeValRift(List<PtlSecPierceValutionDto> result){
        for(PtlSecPierceValutionDto ptlSecPierceValutionDto : result){
            ptlSecPierceValutionMapper.insert(ptlSecPierceValutionDto);
        }
    }
    
    /**
     * 删除已跑批的穿透数据
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    private void deleteValRift(Date currentDate,PtlSecValutionDto ptl){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("portfolioId", ptl.getPortfolioId());
        paramMap.put("cdate", currentDate);
        valRiftMapper.deleteValRift(paramMap);
    }
}
