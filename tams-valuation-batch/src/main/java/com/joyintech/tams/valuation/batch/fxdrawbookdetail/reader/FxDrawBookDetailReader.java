package com.joyintech.tams.valuation.batch.fxdrawbookdetail.reader;
/**
 * @author yanya
 * @create 2019-12-12 10:59
 */

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.fxdrawbookdetail.service.FxDrawBookDetailReaderService;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OverdueInterestCalBookReader
 * @Description: 逾期罚息计提账务处理reader
 * @Author yanya
 * @Date 2019/12/23
 * @Version V1.0
 **/
@Service
@Scope("prototype")
public class FxDrawBookDetailReader extends BaseReader<DrawBaseInfoSelectComResult> {

    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 逾期罚息计提类读入Service
     */
    @Autowired
    private FxDrawBookDetailReaderService fxDrawBookDetailReaderService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DrawBaseInfoSelectComResult> createList() {

        Map<String, Object> serviceParamMap = new HashMap<String, Object>();
        serviceParamMap.put("BATCHINFO", this.getBatchExecuteInfo());
        serviceParamMap.put("BATCHDEL", this.getBatchExecuteDetail());
        serviceParamMap.put("BATCHTASK", this.getBatchTask());
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        serviceParamMap.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        serviceParamMap.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        serviceParamMap.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        serviceParamMap.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        serviceParamMap.put("taskId", this.getBatchExecuteInfo().getTaskId());
        serviceParamMap.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        if (this.getParams() != null) {
            this.getParams().putAll(serviceParamMap);
        }
        // 获取reader返回值
        List<DrawBaseInfoSelectComResult> listDrawBaseInfoSelectComResult = fxDrawBookDetailReaderService.execute(serviceParamMap);
        // 返回处理结果
        return listDrawBaseInfoSelectComResult;

    }
}
