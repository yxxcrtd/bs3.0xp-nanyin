/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.productinfoquery.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basedata.productinfoquery.dao.ValProductInfoQuery001Mapper;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoAcctDto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoFeeDto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoQuery001Dto;
import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceValProductInfoQuery001Service")
public class ValProductInfoQuery001Service extends CommonExportService<ValProductInfoQuery001Dto> {
	
    /**
     * ValProductInfoQuery001Mapper
     */
    @Autowired
    private ValProductInfoQuery001Mapper mapper;
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValProductInfoQuery001Dto> findPage(int page,int length,Map<String, Object> params) throws Exception {
        return super.queryForPage(page,length,()->this.findAll(params));
    }

    /**
     * 下拉框分页获取产品
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(int page,int length,Map<String,Object> map) {
        return super.queryForPageForSelectReturn(page,length,()->mapper.getFinprodId(map));
    }

    /**
     * 根据产品findprodid获取产品详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public ValProductInfoQuery001Dto queryProductByfinprodId(Map<String, Object> map) {
        String finprodId = (String)map.get("finprodId");
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr = finprodId.split(",");
        }
        map.put("finprodIdArr", finprodIdArr);
        List<ValProductInfoQuery001Dto> list=mapper.queryPageLists(map);
        ValProductInfoQuery001Dto dto=new ValProductInfoQuery001Dto();
        if(list.size()>0){
            dto=list.get(0);
        }
        return dto;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoFeeDto> queryFeeList(Map<String, Object> pageParam) {
        return mapper.queryFeeList(pageParam);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoQuery001Dto> findAll(Map<String, Object> params){
      //获取获取理财产品类型
        String profitType = (String)params.get("profitType");
        String raiseType = (String)params.get("raiseType");
        String issueType = (String)params.get("issueType");
        String investType = (String)params.get("investType");
        String finprodId = (String)params.get("finprodId");
        String[] profitTypeArr = null;
        if(StringUtil.isNotEmpty(profitType)){
            profitTypeArr = profitType.split(",");
        }
        String[] raiseTypeArr = null;
        if(StringUtil.isNotEmpty(raiseType)){
            raiseTypeArr = raiseType.split(",");
        }
        String[] issueTypeArr = null;
        if(StringUtil.isNotEmpty(issueType)){
            issueTypeArr = issueType.split(",");
        }
        String[] investTypeArr = null;
        if(StringUtil.isNotEmpty(investType)){
            investTypeArr = investType.split(",");
        }
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr = finprodId.split(",");
        }
        params.put("profitTypeArr", profitTypeArr);
        params.put("raiseTypeArr", raiseTypeArr);
        params.put("issueTypeArr", issueTypeArr);
        params.put("investTypeArr", investTypeArr);
        params.put("finprodIdArr", finprodIdArr);
        params.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
        params.put("finprodTypeF22", DictCodeListExt1.金融产品类型_理财子产品.getValue());//modify by dongjun on 20190104
        return mapper.queryPageList(params);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoAcctDto> getFinprodAcct(Map<String, Object> map) {
        return mapper.getFinprodAcct(map);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoAcctDto> getSecAccount(Map<String, Object> map) {
        return mapper.getSecAccount(map);
    }
    
    /**
     * 查看费用调节 - 分页
     * @param pageParam
     * @return
     * @throws Exception 
     */
	public List<ValProductInfoAcctDto> findBankdesc(Map<String, Object> map) {
  	     return mapper.findBankdesc(map);
      
  }    
    
    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	public List<ValProductInfoFeeDto> queryfloatFeeList(Map<String, Object> pageParam) {
		return mapper.queryfloatFeeList(pageParam);
	}

    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	public List<ValProductInfoFeeDto> queryfloatFeeList1(Map<String, Object> pageParam) {
		return mapper.queryfloatFeeList1(pageParam);
	}
	
    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	public List<ValProductInfoFeeDto> queryfloatFeeList2(Map<String, Object> pageParam) {
		return mapper.queryfloatFeeList2(pageParam);
	}
	
	/**
     * 查询账套配置表
     * @param finprodId 金融产品代码
     * @param finprodType 金融产品类型
     * @return
     * @see
     * @since 1.0
     */
    public List<BokBooksetExt> queryBooksetExt(Map<String, Object> map){
        return mapper.queryBooksetExt(map);
    }
    
    /**
     * 通过主键 查询账套配置表
     * <一句话功能简述>
     * @param finprodId
     * @param paramCode
     * @return
     */
	public List<BokBooksetExt>  queryBooksetExtByKey(Map<String, Object> map) {
		return mapper.queryBooksetExtByKey(map);
	}
}
