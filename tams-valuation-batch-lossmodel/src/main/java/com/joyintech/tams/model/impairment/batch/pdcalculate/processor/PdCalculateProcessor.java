package com.joyintech.tams.model.impairment.batch.pdcalculate.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dto.AssetGroupPdCalculateReaderDto;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dto.AssetImpairmentPdCalculateResultDto;
import com.joyintech.tams.model.impairment.batch.pdcalculate.service.BMWPdCalculateProcessService;
import com.joyintech.tams.model.impairment.batch.pdcalculate.service.PdCalculateProcessService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:减值-PD计算批处理Processor
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
public class PdCalculateProcessor extends BaseProcessor<AssetGroupPdCalculateReaderDto, AssetImpairmentPdCalculateResultDto>  implements StepExecutionListener {
    /**
     *  PD计算批处理Service
     */
    @Autowired
    private PdCalculateProcessService pdCalculateProcessService;
    /**
     *  PD计算批处理Service
     */
    @Autowired
    private BMWPdCalculateProcessService bMWPdCalculateProcessService;

    private StepExecution stepExecution;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution=stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    /**
     * 处理dto reader -> result
     * @param readerDto 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public AssetImpairmentPdCalculateResultDto process(AssetGroupPdCalculateReaderDto readerDto) throws Exception {
        // 减值方案系统参数
        String lossType = CacheUtil.getSysParam(DictCode.减值方案类型.getValue());
        // 普华方案处理逻辑
        if (DictCodeListExt3.减值方案类型_普华.getValue().equals(lossType)) {
            return pdCalculateProcessService.execute(readerDto,getParams(),stepExecution);
        }
        // 毕马威方案PD计算逻辑
        else{
            return bMWPdCalculateProcessService.execute(readerDto,getParams(),stepExecution);
        }
    }


}
