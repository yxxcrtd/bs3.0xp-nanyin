package com.joyintech.tams.invest.batch.stockonlinevoting.processor;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.invest.batch.stockonlinevoting.dto.StockOnlineVotingDto;
import com.joyintech.tams.invest.batch.stockonlinevoting.dto.StockOnlineVotingResultDto;
import com.joyintech.tams.invest.batch.stockonlinevoting.service.StockOnlineVotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**
 * 股票网络投票基础数据导入 Processor
 */
@Service
@Scope("prototype")
public class StockOnlineVotingProcessor extends BaseProcessor<StockOnlineVotingDto, StockOnlineVotingResultDto> {


    @Autowired
    private StockOnlineVotingService stockOnlineVotingService;

    @Override
    public StockOnlineVotingResultDto process(StockOnlineVotingDto stockOnlineVotingDto) throws Exception {
        return stockOnlineVotingService.process(stockOnlineVotingDto);
    }
}
