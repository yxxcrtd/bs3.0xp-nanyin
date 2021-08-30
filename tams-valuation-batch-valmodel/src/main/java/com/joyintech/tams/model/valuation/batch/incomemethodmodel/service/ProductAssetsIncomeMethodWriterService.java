package com.joyintech.tams.model.valuation.batch.incomemethodmodel.service;//package com.joyintech.tams.model.valuation.batch.incomemethodmodel.service;

import com.joyintech.calculation.CalculatorFactory;
import com.joyintech.calculation.bonds.PaperIrReset;
import com.joyintech.calculation.bonds.SecIntSchedule;
import com.joyintech.calculation.bonds.constant.*;
import com.joyintech.calculation.shell.AccurIntForBookCalculator;
import com.joyintech.calculation.shell.param.AccurIntCalInParameter;
import com.joyintech.calculation.shell.param.AccurIntCalOutParameter;
import com.joyintech.calculation.shell.param.SecInfoModel;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.valuation.batch.calculation.constant.GlobalConstant;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dao.IncomeMethodMapper;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.*;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.dto.modelDto.*;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import com.joyintech.tams.valuation.component.batch.service.ModValuationCommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:收益法估值批处理Service
 * @auther: yuzj
 * @date: 2021/7/6
 * @param:
 * @return:
 */
@Service
public class ProductAssetsIncomeMethodWriterService {

    private final String BATCH_LOG_RESULTS = "logResults";

    /**
     * @Description:保存上下文日志信息
     * @auther: yuzj
     * @date: 2021/7/27
     * @param:
     * @return:
     */
    public  void  sendlogResults(List<ModValBatchLogResult> bBatchLogResultList, StepExecution stepExecution){
        if(CollectionUtils.isNotEmpty(bBatchLogResultList)){
            if( stepExecution.getJobExecution().getExecutionContext().get("BATCH_LOG_RESULTS") !=null){
                List<ModValBatchLogResult> oldLogResultList = (List<ModValBatchLogResult>)stepExecution.getJobExecution().getExecutionContext().get("BATCH_LOG_RESULTS");
                oldLogResultList.addAll(bBatchLogResultList);
                stepExecution.getJobExecution().getExecutionContext().put(BATCH_LOG_RESULTS,oldLogResultList);
            }else{
                stepExecution.getJobExecution().getExecutionContext().put(BATCH_LOG_RESULTS,bBatchLogResultList);
            }
        }
    }

}
