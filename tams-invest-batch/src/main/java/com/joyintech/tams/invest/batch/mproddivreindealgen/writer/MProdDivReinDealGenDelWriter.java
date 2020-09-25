/*
 * 文件名：ResetBaseRuleInfoDelWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.mproddivreindealgen.writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BokDealAccountingDetailMapper;
import com.joyintech.tams.common.base.dao.BokDealAccountingMapper;
import com.joyintech.tams.common.base.dao.PaTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.PtlTradeMapper;
import com.joyintech.tams.common.base.dao.TrdAsynHandleStatusMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetailKey;
import com.joyintech.tams.common.base.entity.BokDealAccountingKey;
import com.joyintech.tams.common.base.entity.PtlTradeKey;
import com.joyintech.tams.common.base.entity.TrdAsynHandleStatusKey;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dao.MProdDivReinDealGenMapper;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealDelDto;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealGenResult;
import com.joyintech.tams.invest.batch.resetbaseruleinfo.writer.ResetBaseRuleInfoDelWriter;

/**
 * DelWriter
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月16日
 * @see ResetBaseRuleInfoDelWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MProdDivReinDealGenDelWriter extends BaseWriter<NeedToWriteEntity> {

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
    private MProdDivReinDealGenMapper mProdDivReinDealGenMapper;
    
    @Autowired
    private TrdAsynHandleStatusMapper trdAsynHandleStatusMapper;
    
    /*@Autowired(required = false)
    private PinganBatchPlatformDateImpl platformDateService;*/
    
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
    	@SuppressWarnings("unchecked")
    	List<MProdDivReinDealGenResult> resultList = (List<MProdDivReinDealGenResult>)items;
    	if (!StringUtil.isEmptyList(resultList)) {
             for (MProdDivReinDealGenResult mProdDivReinDealGenResult : resultList) {
            	 List<MProdDivReinDealDelDto>  delDtos=  mProdDivReinDealGenResult.getDelDtos();
            	 
            	 if (!StringUtil.isEmptyList(delDtos)) {
            		 for(MProdDivReinDealDelDto dto:delDtos){
            			 
            			 BokDealAccountingDetailKey bokDealAccountingDetailKey = new BokDealAccountingDetailKey();
            			 bokDealAccountingDetailKey.setBooksetId(dto.getBooksetId());
            			 bokDealAccountingDetailKey.setBusiNo(dto.getBusiNo());
            			 bokDealAccountingDetailKey.setBusiTable(dto.getBusiTable());
            			 bokDealAccountingDetailMapper.deleteByPrimaryKey(bokDealAccountingDetailKey);
            			 BokDealAccountingKey bokDealAccountingKey = new BokDealAccountingKey();
            			 bokDealAccountingKey.setBooksetId(dto.getBooksetId());
            			 bokDealAccountingKey.setBusiNo(dto.getBusiNo());
            			 bokDealAccountingKey.setBusiTable(dto.getBusiTable());
            			 bokDealAccountingMapper.deleteByPrimaryKey(bokDealAccountingKey);
            			 trdProductDealAddMapper.deleteByPrimaryKey(dto.getTradeId());
            			 trdProductDealMapper.deleteByPrimaryKey(dto.getTradeId());
            			 trdFinProductDealTotalMapper.deleteByPrimaryKey(dto.getTotleTradeId());
            			 paTrdFinProductDealTotalMapper.deleteByPrimaryKey(dto.getTotleTradeId());
            			 PtlTradeKey ptlTradeKey = new PtlTradeKey();
            			 ptlTradeKey.setBusiNo(dto.getTotleTradeId());
            			 ptlTradeKey.setBusiTable(dto.getPlBusiTable());
            			 ptlTradeKey.setPortfolioId(dto.getPortfolioId());
            			 ptlTradeMapper.deleteByPrimaryKey(ptlTradeKey);
            			 //删除组合持仓流水
            			 Map<String,Object> paramMap = new HashMap<String,Object>();
            			 paramMap.put("portfolidId", dto.getPortfolioId());
            			 paramMap.put("tradeId", dto.getTradeId());
            			 mProdDivReinDealGenMapper.deletePtlSecPositionFlow(paramMap);
            			 
            			//增加交易异步处理状态存储
            			TrdAsynHandleStatusKey trdAsynHandleStatusKey = new TrdAsynHandleStatusKey();
            			trdAsynHandleStatusKey.setTradeId(dto.getTotleTradeId());
            			trdAsynHandleStatusKey.setTrdTableType("01");
                      	trdAsynHandleStatusMapper.deleteByPrimaryKey(trdAsynHandleStatusKey);
            		 }
            	 }
                 
             }
         }
    }
    
}