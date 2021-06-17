/*
 * 文件名：EtrBondFirstBiddingController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etribrepo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepo001Dto;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoRk004Dto;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoRk005Dto;
import com.joyintech.service.entrust.etribrepo.dto.PtlSecStock;
import com.joyintech.service.entrust.etribrepo.service.EtrIbRepo001Service;
import com.joyintech.tams.common.base.entity.EtrEntrustPledge;
import com.joyintech.tams.common.base.entity.EtrSecStockConfig;
import com.joyintech.tams.component.business.basicdata.dto.BusiFinProductRatingDto;
import com.joyintech.tams.component.business.basicdata.service.BusiProductRatingService;
import com.joyintech.tams.constant.DictCodeList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 场内交易委托
 * 
 * @author zhangnian
 * @version 1.0 2019年10月24日
 * @see EtrIbRepoRk004Controller
 * @since 1.0
 */
@RestController("EtrIbRepoRk004Ctr_dtx")
@RequestMapping("EtrIbRepoRk004")
public class EtrIbRepoRk004Controller {
    @Autowired
    private EtrIbRepo001Service etrIbRepo001Service;
    
    @Autowired(required = false)
    private BusiProductRatingService busiProductRatingService;
    
    /**
     * 获得质押券库
     * @param pageParam
     * @return
     */
    @PostMapping("getAssetName")
    public List<EtrIbRepoRk004Dto> getAssetName(@RequestBody Map<String, Object> pageParam) {
        return etrIbRepo001Service.getAssetName(pageParam);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 获取明细
     * @param dto
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetDetailBySeqno/{page}/{length}")
    public Page<EtrIbRepoRk005Dto> getAssetDetailBySeqno(@RequestBody Map<String, Object> param,
                                                                    @PathVariable("page") int page,
                                                                    @PathVariable("length") int length) throws Exception {
        return etrIbRepo001Service.getAssetDetailBySeqno(page, length, param);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 插入
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody EtrIbRepoRk004Dto dto) {
    	etrIbRepo001Service.insert(dto);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 删除
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody EtrIbRepoRk004Dto dto) {
        return etrIbRepo001Service.delete(dto);
    }
    
    @PostMapping("checkIsExistByPledgeStockName")
    public int checkIsExistByPledgeStockName(@RequestBody Map<String, Object> map){
        return etrIbRepo001Service.checkIsExistByPledgeStockName(map);
    }
    
    @PostMapping("getBusinessInfoByPledgeStockId")
    public EtrIbRepoRk004Dto getBusinessInfoByPledgeStockId(@RequestBody String pledgeStockId) {
        return etrIbRepo001Service.getBusinessInfoByPledgeStockId(pledgeStockId);
    }
    
    @PostMapping("checkIsExistDetail")
    public int checkIsExistDetail(@RequestBody String pledgeStockId) {
        return etrIbRepo001Service.checkIsExistDetail(pledgeStockId);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 插入
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("inserttotal")
    public void inserttotal(@RequestBody EtrIbRepoRk004Dto dto) {
    	//先删
    	List<EtrIbRepo001Dto> F01List = new ArrayList<EtrIbRepo001Dto>();
    	etrIbRepo001Service.inserttotalq(dto.getPledgeStockId());
    	List<String> portfolioId = new ArrayList<String>();
		for (EtrSecStockConfig dd : dto.getEtrSecStockConfig()) {
			etrIbRepo001Service.inserttotal(dd);
			Map<String, Object> map = new HashMap<>();
			portfolioId = dto.getPtlSecStock().stream().map(a -> a.getPortfolioId()).collect(Collectors.toList());
			map.put("portfolioId", portfolioId);
			// map.put("pledgeStockId", dd.getPledgeStockId());
			map.put("windtypeone", dd.getWindtypeone());
			map.put("windtypetwo", dd.getWindtypetwo());
			map.put("mGradeResult", dd.getmGradeResult());
			map.put("secGradeResult", dd.getSecGradeResult());
			map.put("isSecBond", dd.getIsSecBond());
			map.put("isRight", dd.getIsRight());
			map.put("isPpb", dd.getIsPpb());
			List<EtrIbRepo001Dto> finProductList = etrIbRepo001Service.selectF01List(map);
			List<EtrIbRepo001Dto> tempList = new ArrayList<>();
			if (!StringUtil.isEmptyList(finProductList)) {
				if (StringUtil.isNotEmpty(dd.getmGradeResult())) {
					for (EtrIbRepo001Dto dto2 : finProductList) {
						map.put("oCode", dto2.getIssuer());
						map.put("oType", DictCodeList.评级类型_主体评级.getValue());
						// 查询评级类型为主体评级的数据
						List<BusiFinProductRatingDto> ztList = busiProductRatingService.getZtBestRatingByOrgId(map);
						if (!StringUtil.isEmptyList(ztList)) {
							String a = ztList.stream().map(BusiFinProductRatingDto::getGradeResult).min(String::compareTo).get();
							if (dd.getmGradeResult().compareTo(a) >= 0) {//不低于主体评级
								if(StringUtil.isNotEmpty(dd.getSecGradeResult())){
									Map<String, Object> zxparam = new HashMap<String, Object>();
									zxparam.put("oCode", dto2.getFinprodMarketId());
									zxparam.put("oType", DictCodeList.评级类型_债项评级.getValue());
									// 查询评级类型是债项评级的数据
									List<BusiFinProductRatingDto> zxList = busiProductRatingService.getZxBestRatingByMarketId(zxparam);
									if (!StringUtil.isEmptyList(zxList)) {
										String b = zxList.stream().map(BusiFinProductRatingDto::getGradeResult).min(String::compareTo).get();
										if (dd.getSecGradeResult().compareTo(b) >= 0) {//不低于债项评级
											tempList.addAll(finProductList);
										}
									}
								}
							}
						}
						
					}
				}else{
					if (StringUtil.isNotEmpty(dd.getSecGradeResult())) {
						for (EtrIbRepo001Dto dto2 : finProductList) {
							Map<String, Object> zxparam = new HashMap<String, Object>();
							zxparam.put("oCode", dto2.getFinprodMarketId());
							zxparam.put("oType", DictCodeList.评级类型_债项评级.getValue());
							// 查询评级类型是债项评级的数据
							List<BusiFinProductRatingDto> zxList = busiProductRatingService.getZxBestRatingByMarketId(zxparam);
							if (!StringUtil.isEmptyList(zxList)) {
								String b = zxList.stream().map(BusiFinProductRatingDto::getGradeResult).min(String::compareTo).get();
								if (dd.getSecGradeResult().compareTo(b) >= 0) {//不低于债项评级
									tempList.addAll(finProductList);
								}
							}
						}
					}
				}
				if (!StringUtil.isNotEmpty(dd.getmGradeResult()) && !StringUtil.isNotEmpty(dd.getSecGradeResult())) {
					tempList.addAll(finProductList);
				}
			}
			F01List.addAll(tempList);
		}
    	
    	for (PtlSecStock dd: dto.getPtlSecStock()) {
    		com.joyintech.tams.common.base.entity.PtlSecStock d = new com.joyintech.tams.common.base.entity.PtlSecStock();
    		d.setPortfolioId(dd.getPortfolioId());
    		d.setPledgeStockId(dd.getPledgeStockId());
    		d.setPledgeStockName(dd.getPledgeStockName());
    		etrIbRepo001Service.insertptl(d);
        }
    	
    	if(!StringUtil.isEmptyList(F01List)){
    		Map< String, Object> param = new HashMap<>();
    		param.put("portfolioIdList", portfolioId);
    		List<EtrIbRepo001Dto> pageList =etrIbRepo001Service.selectZlglPositionPage(param);
                Iterator<EtrIbRepo001Dto> iterator = F01List.iterator();
                while(iterator.hasNext()){
                	EtrIbRepo001Dto avaDto=  iterator.next();
                    for (EtrIbRepo001Dto pledDto : pageList) {
                        if(StringUtil.equals(avaDto.getPortfolioId(), pledDto.getPortfolioId())&&
                            StringUtil.equals(avaDto.getSecAcctId(), pledDto.getSecAcctId())&&
                            StringUtil.equals(avaDto.getFinprodId(), pledDto.getFinprodId())&&
                            StringUtil.equals(dto.getPledgeStockId(), pledDto.getPledgeStockId())){
                            iterator.remove() ;
                        }
                    }
                }
                if(!StringUtil.isEmptyList(F01List)){
                	F01List =F01List.stream().distinct().collect(Collectors.toList());
            		for(EtrIbRepo001Dto f01:F01List){
            			EtrEntrustPledge entrustPledge = new EtrEntrustPledge();
            			entrustPledge.setPortfolioId(f01.getPortfolioId());
            			entrustPledge.setFinprodId(f01.getFinprodId());
            			entrustPledge.setBranch(f01.getBranch());
            			entrustPledge.setSecAcctId(f01.getSecAcctId());
            			entrustPledge.setScale(f01.getScale());
            			entrustPledge.setPledgeStockId(dto.getPledgeStockId());
            			etrIbRepo001Service.insertEtrEntrustPledge(entrustPledge);
            		}
                }
    		
    	}
    }
    
    @PostMapping("/findAllTask")
    @SuppressWarnings("rawtypes")
    public List<TreeNode> findAllTask(@RequestBody Map<String,Object> params){
    	return etrIbRepo001Service.getTreeNodeList(params);
    }
    
    @PostMapping("getName")
    public EtrIbRepoRk004Dto getName(@RequestBody String pledgeStockId) {
        return etrIbRepo001Service.getName(pledgeStockId);
    }
}