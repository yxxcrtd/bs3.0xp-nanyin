/*
 * 文件名：ResetBaseRuleInfoWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.mproddivreindealgen.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.dao.BokDealAccountingDetailMapper;
import com.joyintech.tams.common.base.dao.BokDealAccountingMapper;
import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.PtlSecPositionFlowMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.dao.TrdAsynHandleStatusMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealGenResult;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 *  writer
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月16日
 * @see MProdDivReinDealGenWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MProdDivReinDealGenWriter extends BaseWriter<NeedToWriteEntity>{

	  @Autowired
	    private BokDealAccountingDetailMapper bokDealAccountingDetailMapper;
	    
	    @Autowired
	    private BokDealAccountingMapper bokDealAccountingMapper;
	    
	    @Autowired
	    private TrdProductDealMapper trdProductDealMapper;
	    
	    @Autowired
	    private TrdProductDealAddMapper trdProductDealAddMapper;
	    
	    @Autowired
	    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
	    
	    @Autowired
	    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;
	    
	    @Autowired
	    private PtlTradeMapper ptlTradeMapper;
	    
	    @Autowired
	    private TrdAsynHandleStatusMapper trdAsynHandleStatusMapper;
	    
	    @Autowired
		private PtlSecPositionFlowMapper ptlSecPositionFlowMapper;
    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        @SuppressWarnings("unchecked")
        List<MProdDivReinDealGenResult> resultList = (List<MProdDivReinDealGenResult>)items;
        if (!StringUtil.isEmptyList(resultList)) {
            for (MProdDivReinDealGenResult mProdDivReinDealGenResult : resultList) {
               if(!StringUtil.isEmptyList(mProdDivReinDealGenResult.getTrdProductDeals())){
            	   for(TrdProductDeal deal :mProdDivReinDealGenResult.getTrdProductDeals()){
            		   trdProductDealMapper.insert(deal);
            	   }
            	   
               }
               if(!StringUtil.isEmptyList(mProdDivReinDealGenResult.getTrdProductDealAdds())){
            	   for(TrdProductDealAdd deal :mProdDivReinDealGenResult.getTrdProductDealAdds()){
            		   trdProductDealAddMapper.insert(deal);
            	   }
            	   
               }
               if(!StringUtil.isEmptyList(mProdDivReinDealGenResult.getBokDealAccountings())){
            	   for(BokDealAccounting deal :mProdDivReinDealGenResult.getBokDealAccountings()){
            		   bokDealAccountingMapper.insert(deal);
            	   }
            	   
               }
               if(!StringUtil.isEmptyList(mProdDivReinDealGenResult.getBokDealAccountingDetails())){
            	   for(BokDealAccountingDetail deal :mProdDivReinDealGenResult.getBokDealAccountingDetails()){
            		   bokDealAccountingDetailMapper.insert(deal);
            	   }
            	   
               }
               if(mProdDivReinDealGenResult.getTrdFinProductDealTotal()!=null){
            	   trdFinProductDealTotalMapper.insert(mProdDivReinDealGenResult.getTrdFinProductDealTotal());
               }
               if(mProdDivReinDealGenResult.getPaTrdFinProductDealTotal()!=null){
            	   paTrdFinProductDealTotalMapper.insert(mProdDivReinDealGenResult.getPaTrdFinProductDealTotal());
               }
               if(mProdDivReinDealGenResult.getPtlTrade()!=null){
            	   ptlTradeMapper.insert(mProdDivReinDealGenResult.getPtlTrade());
               }
               //增加交易异步处理状态存储
               if(mProdDivReinDealGenResult.getTrdAsynHandleStatus()!=null){
            	   trdAsynHandleStatusMapper.insert(mProdDivReinDealGenResult.getTrdAsynHandleStatus());
               }
               //组合持仓流水表插入
               if(!StringUtil.isEmptyList(mProdDivReinDealGenResult.getPtlSecPositionFlows())){
            	   for (PtlSecPositionFlowDto ptlSecPositionFlowDto : mProdDivReinDealGenResult.getPtlSecPositionFlows()) {
            		   ptlSecPositionFlowMapper.insert(ptlSecPositionFlowDto);
            	   }
               }
            }
        }
    }

}