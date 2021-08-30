package com.joyintech.tams.model.valuation.batch.incomemethodmodel.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.ProductAssetsIncomeMethodReaderDto;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.ProductAssetsIncomeMethodResultDto;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.service.ProductAssetsIncomeMethodProcessorService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:收益法估值批处理Processor
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
public class ProductAssetsIncomeMethodProcessor extends BaseProcessor<ProductAssetsIncomeMethodReaderDto, ProductAssetsIncomeMethodResultDto> implements StepExecutionListener {
    private StepExecution stepExecution;

    /**
     *  收益法估值批处理Service
     */
    @Autowired
    private ProductAssetsIncomeMethodProcessorService productAssetsIncomeMethodProcessorService;

    /**
     * 处理dto reader -> result
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public ProductAssetsIncomeMethodResultDto process(ProductAssetsIncomeMethodReaderDto item) throws Exception {
        return productAssetsIncomeMethodProcessorService.convertResultFromReader(item,getParams(),stepExecution);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution=stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
