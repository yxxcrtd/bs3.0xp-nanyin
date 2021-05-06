package com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dao.AssetMarketDataMapper;
import com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dto.AssetMarketDataResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.MarketPriceDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.SchemeDetailDto;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetFinProduct;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetSearchDto;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;

@Service
public class AssetMarketDataService extends BaseService{
    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;
    
    @Autowired
    private FinProductCommonBatchService finProductCommonBatchService;
    
    @Autowired
    private AssetMarketDataMapper assetMarketDataMapper;
    
    @Value("${server.port:8080}")
    private String serverPort;
    
    
    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode){
        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            // 固定key 勿修改
            param.put("PRE_INST_CODE",instCode);
        }

        param.put("checkDate",dto.getCheckDate());
        //1. 获取组合-资产的持仓信息
        List<AssetMarketDataResultDto> details = assetMarketDataMapper.getAssetMarketDataResult(param);
        //2. 根据查询结果组个进行校验是否存在行情数据
        Iterator<AssetMarketDataResultDto> detailIterator = details.iterator();
        String happenDateStr = DateUtil.format(dto.getCheckDate());
        while(detailIterator.hasNext()) {
        	String urlHead = "http://127.0.0.1:"+serverPort;
        	AssetMarketDataResultDto resultDto = detailIterator.next();
        	//2.1 股票和计划类净值型资产可以直接访问finProductCommonBatchService
        	if(StringUtil.equals(resultDto.getSecType(), DictCodeListExt1.证券品种_股票.getValue())) {
        		 Map<String, Object> data = finProductCommonBatchService.getStockMarket(dto.getCheckDate(),resultDto.getBooksetId(),
        				 resultDto.getFinprodId(),resultDto.getTradeMarket(),resultDto.getInvAim());
        		 if(dto.getCheckDate().equals(data.get("valDate"))){
        			 detailIterator.remove();
        		 }
        	}
        	else if(StringUtil.equals(resultDto.getSecType(), DictCodeListExt1.证券品种_计划类资产_净值型.getValue())) {
        		MarketPriceDto pDto = finProductCommonBatchService.getFundMarketPriceForPlanAsset(dto.getCheckDate(), resultDto.getFinprodId(), resultDto.getBooksetId());
        		if(pDto!=null) {
        			Date valDate = pDto.getValDate();
        			if(valDate==null) {
        				valDate = pDto.getSchemeValDate();
        			}
        			if(dto.getCheckDate().equals(valDate)) {
            			detailIterator.remove();	
        			}
        		}
        	}
        	//2.2 债券，基金，计划类货币型需要通过http访问本机服务
        	else if(StringUtil.equals(resultDto.getSecType(), DictCodeListExt1.证券品种_债券.getValue())) {
        		String url = urlHead+"/FinproductCommon/getBondMarketPrice?happenDate="+happenDateStr
        				+"&booksetId="+resultDto.getBooksetId()+"&finProdId="+resultDto.getFinprodId()+"&invAim="+resultDto.getInvAim();
        		Date valDate = restTemplate.getForObject(url, Date.class);
        		if(dto.getCheckDate().equals(valDate)) {
        			detailIterator.remove();
        		}
        	}
        	else if(StringUtil.equals(resultDto.getSecType(), DictCodeListExt1.证券品种_基金.getValue())) {
        		String url =urlHead+"/FinproductCommon/getFundMarketPrice?happenDate="+happenDateStr
        				+"&booksetId="+resultDto.getBooksetId()+"&finProdId="+resultDto.getFinprodId();
        		Date valDate = restTemplate.getForObject(url, Date.class);
        		if(dto.getCheckDate().equals(valDate)) {
        			detailIterator.remove();
        		}
        	}
        	else if(StringUtil.equals(resultDto.getSecType(), DictCodeListExt1.证券品种_计划类资产_货币型.getValue())) {
        		String url = urlHead+"/FinproductCommon/queryFinProductValuation?happenDate="+happenDateStr
        				+"&booksetId="+resultDto.getBooksetId()+"&finprodMarketId="+resultDto.getFinprodId().substring(3)+"&calenderId="+resultDto.getCalenderId();
        		Date valDate = restTemplate.getForObject(url, Date.class);
        		if(dto.getCheckDate().equals(valDate)) {
        			detailIterator.remove();
        		}
        	}
        	else {
        		//不是需要校验的数据
        		detailIterator.remove();
        	}
        }
        
        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }
        
        
        // 判断结果集
        List<SysPrebatchResultDetail> finalDetails = new ArrayList<>(details);
        prebatchCheckCommonService.insertBatchResultDetail(dto,finalDetails);
    }
    
    private void setResultDetail(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, SysPrebatchResultDetail item) {

        item.setResultSeqNo(dto.getSeqNo());
        item.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
        item.setTaskId(batchExecuteInfo.getTaskId());

        item.setSeqNo(IDGenerator.generate());
        item.setCreateUser(dto.getCreateUser());
        item.setUpdateUser(dto.getUpdateUser());
    }
}