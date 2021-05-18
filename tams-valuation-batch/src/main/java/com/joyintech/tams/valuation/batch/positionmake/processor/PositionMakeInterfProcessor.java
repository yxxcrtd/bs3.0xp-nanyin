package com.joyintech.tams.valuation.batch.positionmake.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.positionmake.dao.PositionMakeWithoutDispatchMapper;
import com.joyintech.tams.valuation.batch.positionmake.dto.PositionMakeInterfWriterResult;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 头寸生成接口 - processor
 * 
 * @author Administrator
 *
 */
@Service
@Scope("prototype")
public class PositionMakeInterfProcessor extends BaseProcessor<NeedOperateEntity, PositionMakeInterfWriterResult> {
	
	@Autowired
    private PositionMakeWithoutDispatchMapper positionMakeWithoutDispatchMapper;

	@SuppressWarnings("unchecked")
	@Override
	public PositionMakeInterfWriterResult process(NeedOperateEntity items) throws Exception {

		Map<String, Object> params = this.getParams();
        if (MapUtils.isEmpty(params)) {
            return null;
        }
		 
		String seqNo = (String)params.get("seqNo");
        Date happenDate = (Date)params.get(BookConstant.HAPPEN_DATE);
        List<String> booksetIds = new ArrayList<String>();
        if(this.getParams().get(GlobalConstant.BOOKSETS)!=null){
        	booksetIds = (List<String>)this.getParams().get(GlobalConstant.BOOKSETS);
        }
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("booksetIds", booksetIds);
        queryMap.put("cdate", DateUtil.format(happenDate));
        
        //查询本次同步的组合代码
        List<String> portfolioIdList = positionMakeWithoutDispatchMapper.getPortfolioIds(queryMap);
        //查询本次同步的产品代码
        List<String> prodList = positionMakeWithoutDispatchMapper.getProdIds(queryMap);
        
        PositionMakeInterfWriterResult result = new PositionMakeInterfWriterResult();
        result.setCdate(happenDate);
        result.setSeqNo(seqNo);
        result.setPortfolioIdList(portfolioIdList);
        result.setProdList(prodList);
        result.setProdId(booksetIds.toString());
		return result;
	}

}
