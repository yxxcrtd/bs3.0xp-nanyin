package com.joyintech.tams.invest.batch.assetintdatecarryover.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.assetintdatecarryover.dto.AssetIntdateCarryForwardReaderResult;
import com.joyintech.tams.invest.batch.assetintdatecarryover.service.AssetIntdateCarryForwardReaderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 交易类标的生成批处理READER
 * @author WUJIE
 *
 */
@Service
public class AssetIntdateCarryForwardReader extends BaseReader <AssetIntdateCarryForwardReaderResult>{
    @Autowired
    private AssetIntdateCarryForwardReaderService readerService;

    @Override
    public List<AssetIntdateCarryForwardReaderResult> createList() {
    	List<AssetIntdateCarryForwardReaderResult> result = readerService.execute(this.getParams());
        return result;
    }
}
