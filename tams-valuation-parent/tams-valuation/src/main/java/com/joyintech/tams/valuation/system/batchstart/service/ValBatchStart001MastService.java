package com.joyintech.tams.valuation.system.batchstart.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.entity.SysBatchTask;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.system.batchstart.dao.ValBatchStart001Mapper;
import com.joyintech.tams.common.base.dao.BokBooksetDateMapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokBooksetDate;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ValBatchStart001MastService extends BaseService<SysBatchTask> {

    @Autowired
    private ValBatchStart001Mapper valBatchStart001Mapper;

    @Autowired
    private BaseCommonService baseCommonService;

    @Autowired
    private BokBooksetDateMapper valBokBooksetDateMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.MASERT)
    public SysBatchgrpExecInfo getSysBatchgrpExecInfo(Date start, Date end, List<String> prodList,
                                                      Map<String, String> dateMap) {
        String startDate = dateMap.get("startDate");
        String endDate = dateMap.get("endDate");
        SysBatchgrpExecInfo executeDetail = new SysBatchgrpExecInfo();
        // 获取所有账套
        List<BokBookset> booksetList = valBatchStart001Mapper.selectValBokBookset(start, end,
            prodList);
        // 开始日为空
        if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            String sysDate = baseCommonService.getSysDateString();
            startDate = sysDate;
            endDate = sysDate;
            // 画面没有选择日期
            executeDetail.setOtherParam2("N");
        }
        else {
            // 画面选择日期
            executeDetail.setOtherParam2("Y");
            if (booksetList != null && booksetList.size() > 0) {
                for (BokBookset var : booksetList) {
                    Date date1 = null;
                    if (start.compareTo(var.getStartDate()) >= 0) {
                        date1 = start;
                    }
                    else {
                        date1 = var.getStartDate();
                    }
                    // 更新账套日期表
                    BokBooksetDate record = new BokBooksetDate();
                    record.setBooksetId(var.getBooksetId());
                    record.setBooksetDate(date1);
                    // 切账套会计日期
                    valBokBooksetDateMapper.updateByPrimaryKeySelective(record);
                    //切通道子账套的会计日期
                    valBatchStart001Mapper.updateByBookRe(record);
                }
            }
        }
        dateMap.put("startDate", startDate);
        dateMap.put("endDate", endDate);
        return executeDetail;
    }
}
