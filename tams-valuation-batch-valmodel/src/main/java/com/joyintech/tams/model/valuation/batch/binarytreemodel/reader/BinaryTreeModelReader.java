package com.joyintech.tams.model.valuation.batch.binarytreemodel.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.BinaryTreeModelReaderEntity;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.service.BinaryTreeModelReaderService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 二叉树模型计算批处理Reader
 */
@Service
public class BinaryTreeModelReader extends BaseReader <BinaryTreeModelReaderEntity> implements StepExecutionListener {

    @Autowired
    private BinaryTreeModelReaderService binaryTreeModelReaderService;

    private StepExecution stepExecution;
    @Override
    public List<BinaryTreeModelReaderEntity> createList() {
        List<BinaryTreeModelReaderEntity> result = binaryTreeModelReaderService.createReaderList(getParams(), stepExecution);
        return result;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }

}
