package com.joyintech.tams.model.valuation.batch.binarytreemodel.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.BinaryTreeModelReaderEntity;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.BinaryTreeModelResultDto;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.service.BinaryTreeModelProcessorService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BinaryTreeModelProcessor extends BaseProcessor<BinaryTreeModelReaderEntity, BinaryTreeModelResultDto> implements StepExecutionListener {
    /**
     *  逾期资产结转批处理Service
     */
    @Autowired
    private BinaryTreeModelProcessorService binaryTreeModelProcessorService;

    private StepExecution stepExecution;

    /**
     *  处理dto reade -> result
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public BinaryTreeModelResultDto process(BinaryTreeModelReaderEntity item) throws Exception {
        return binaryTreeModelProcessorService.excute(item,getParams(), stepExecution);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

}
