package com.joyintech.tams.invest.batch.assetintdatecarryover.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.invest.batch.assetintdatecarryover.dto.AssetIntdateCarryForwardReaderResult;
import com.joyintech.tams.invest.batch.assetintdatecarryover.dto.AssetIntdateCarryForwardResult;
import com.joyintech.tams.invest.batch.assetintdatecarryover.service.AssetIntdateCarryForwardProcessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 资产理论日结转Processor
 * @author WUJIE
 *
 */
@Service
public class AssetIntdateCarryForwardProcessor extends BaseProcessor<AssetIntdateCarryForwardReaderResult, AssetIntdateCarryForwardResult> {
    
	@Autowired
    private AssetIntdateCarryForwardProcessorService processorService;

    @Override
    public AssetIntdateCarryForwardResult process(AssetIntdateCarryForwardReaderResult item) throws Exception {
	    AssetIntdateCarryForwardResult result = processorService.execute(item,getParams());
	   	if(result == null){
	   		result = new AssetIntdateCarryForwardResult();
	   	}
        return result;
    }
}
