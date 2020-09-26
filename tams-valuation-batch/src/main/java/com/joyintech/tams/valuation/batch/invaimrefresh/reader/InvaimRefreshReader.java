/*
 * 文件名：InvaimRefreshReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.invaimrefresh.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.invaimrefresh.dao.InvaimRefreshMapper;
import com.joyintech.tams.valuation.batch.invaimrefresh.dto.DealInvaimInfoDto;
import com.joyintech.tams.valuation.batch.invaimrefresh.dto.InvaimRefreshReaderResultDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 投资目的刷新 - reader
 * 
 * @author yangjian
 * @version 1.0 2018年9月8日
 * @see InvaimRefreshReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class InvaimRefreshReader extends BaseReader<InvaimRefreshReaderResultDto> {

    @Autowired
    private InvaimRefreshMapper invaimRefreshMapper;

    @Override
    public List<InvaimRefreshReaderResultDto> createList() {
    	
    	//获取task传递过来的参数
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        
        //获取当前要处理的交易信息及获取对应估值方案信息的数据
        Map<String, Object> sqlmap = new HashMap<String, Object>();
        sqlmap.put("seqNo", thisParams.get("seqNo"));
        sqlmap.put("booksetdate", thisParams.get(BookConstant.BOOKSET_DATE));
        sqlmap.put("taskId", thisParams.get("taskId"));
        sqlmap.put(BookConstant.IS_SUB_BOOKSET, thisParams.get(BookConstant.IS_SUB_BOOKSET));
        List<DealInvaimInfoDto> dealInfo = invaimRefreshMapper.getDealInfoList(sqlmap);
        
        //定义投资目的需要修改的交易list
        List<DealInvaimInfoDto> needChangeDealList = new ArrayList<DealInvaimInfoDto>();
        
        //循环交易数据，获取交易数据的一般投资目的和特殊投资目的
        for(DealInvaimInfoDto dto : dealInfo){
        	//定义传递参数
        	Map<String, Object> sqlmapnow = new HashMap<>();
        	sqlmapnow.put("finprodid", dto.getFinprodId());//金融产品代码
        	sqlmapnow.put("schemeId", dto.getSchemeId());//方案代码
        	sqlmapnow.put("secDetailType", dto.getSecDetailType());//资产细分类
        	sqlmapnow.put("tradeDate", dto.getTradeDate());//交易日
        	
        	//1.一般投资目的（关联估值方案明细,资产关联）（取起息日离交易日最近的一条）
        	String assetInvaim = invaimRefreshMapper.getNormalInvaimWithSchemeId(sqlmapnow);
        	dto.setAssetInvaim(assetInvaim);
        	
        	//2.一般投资目的（关联估值方案明细,细分类关联）（取起息日离交易日最近的一条）
        	String secDetailTypeInvaim = invaimRefreshMapper.getNormalInvaimWithSecDetailType(sqlmapnow);
        	dto.setSecDetailTypeInvaim(secDetailTypeInvaim);
        	
        	//3.特殊投资目的（关联特殊资产估值方案明细,资产关联）(已确认区间只有一条，故无需过滤）
        	String specialInvaim = invaimRefreshMapper.getSpecialInvaimWithSchemeId(sqlmapnow);
        	dto.setSpecialInvaim(specialInvaim);
        	
        	//获取需要修改成的投资目的（最终投资目：优先取的顺序：特殊.资产.投资目的 > 一般.细分类.投资目的 > 一般.资产.投资目的）
        	String changeInvaim = null;
        	if(assetInvaim!=null && assetInvaim.length()>0)changeInvaim = assetInvaim;//一般.资产.投资目的不为空，最终投资目的赋值
        	if(secDetailTypeInvaim!=null && secDetailTypeInvaim.length()>0)changeInvaim = secDetailTypeInvaim;//一般.细分类.投资目的，最终投资目的赋值
        	if(specialInvaim!=null && specialInvaim.length()>0)changeInvaim = specialInvaim;//特殊.资产.投资目的，最终投资目的赋值
        	dto.setChangeInvaim(changeInvaim);
        	
        	//添加需要修改投资目的的交易，即 最终投资目的!= 原始投资目的
        	if(changeInvaim!=null && !changeInvaim.equals(dto.getOrgDealInvaim())){
        		needChangeDealList.add(dto);
        	}
        }
        
        
    
        //定义返回值
        List<InvaimRefreshReaderResultDto> returnList =  new ArrayList<InvaimRefreshReaderResultDto>();
        
        //当前存在的账套
        Set<String> booksetIdSet = new HashSet<String>();
        for(DealInvaimInfoDto dto : needChangeDealList){
        	booksetIdSet.add(dto.getBooksetId());
        }
            
        //根据账套进行分类
        for(String booksetIdNow : booksetIdSet){
			//定义当前账套下的交易关联出来的投资目的信息
			List<DealInvaimInfoDto> thisBookSetIdDealList = new ArrayList<DealInvaimInfoDto>();
			
        	//获取当前账套下的交易数据
			for(DealInvaimInfoDto dto : needChangeDealList){
				if(booksetIdNow.equals(dto.getBooksetId())){
					thisBookSetIdDealList.add(dto);
				}
			}
			
        	//定义实体类
			InvaimRefreshReaderResultDto dto = new InvaimRefreshReaderResultDto();
			dto.setBooksetId(booksetIdNow);
			dto.setDealInvaimIntoDtoList(thisBookSetIdDealList);
			returnList.add(dto);
        }

        return returnList;
    }

}
