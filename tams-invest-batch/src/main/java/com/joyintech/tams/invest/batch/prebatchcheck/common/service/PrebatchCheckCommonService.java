package com.joyintech.tams.invest.batch.prebatchcheck.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dao.PrebatchCheckCommonMapper;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PrebatchCheckCommonService extends BaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PrebatchCheckCommonMapper prebatchCheckCommonMapper;

    // 更新状态为启动 结束 异常 等状态  时间 等
    public void updateSysPrebatchResult(SysPrebatchResultDto dto){
        prebatchCheckCommonMapper.updateSysPrebatchResult(dto);
    }


    public SysPrebatchResultDto selectSysPrebatchResult(SysPrebatchResultDto paramdto) {
        return prebatchCheckCommonMapper.selectSysPrebatchResult(paramdto);
    }

    // 查询错误信息前//执行任务前 执行此方法
    public SysPrebatchResultDto selAndUpdPrebatchResult(SysPrebatchResultDto paramdto){
        SysPrebatchResultDto dto = selectSysPrebatchResult(paramdto);

        if(dto==null || StringUtil.isEmpty(dto.getSeqNo())){
            logger.info("此次批前检查未找到该启动任务id。");
            return null;
        }

        dto.setTaskStatus(DictCodeListExt3.批前检查_执行状态_执行中.getValue());
        dto.setStartTime(new Date());

        // 先将 启动时间 及 启动状态更新为 启动中 并记录开始时间
        updateSysPrebatchResult(dto);

        return dto;

    }

    public void insertBatchResultDetail(List<SysPrebatchResultDetail> details) {
        prebatchCheckCommonMapper.insertBatchResultDetail(details);

    }
}
