package com.joyintech.tams.invest.batch.assetintdatecarryover.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.invest.batch.assetintdatecarryover.dto.AssetIntdateCarryForwardResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *  资产理论日结转Writer
 */
@Service
public class AssetIntdateCarryForwardWriter extends BaseWriter<AssetIntdateCarryForwardResult> {
	
	@Autowired
    private FinTradeProductMapper finTradeProductMapper;
	
    @Override
    public void write(List<? extends AssetIntdateCarryForwardResult> items) throws Exception {
    	for(AssetIntdateCarryForwardResult dto:items){
        	for(FinTradeProduct ftp :dto.getFinTradeProductList()){
        		finTradeProductMapper.deleteByPrimaryKey(ftp);
        		finTradeProductMapper.insert(ftp);
        	}
        }
    }
}


