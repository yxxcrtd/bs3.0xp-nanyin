/**
 * @author wj
 * @version 1.0.0
 * @ClassName PrebatchCheckCommonService.java
 * @Description 批前检查 批前检查  公共service
 * @createTime 2021年05月13日 10:57:00
 */
package com.joyintech.tams.invest.batch.prebatchcheck.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dao.PrebatchCheckCommonMapper;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchTaskAddDto;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * @title  // 查询错误信息前//执行任务前 执行此方法
     * @description
     * @author wj
     * @updateTime 2021/5/13 10:59
     * @throws
     */
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

    /**
     * @title 入表
     * @description 
     * @author wj 
     * @updateTime 2021/5/13 10:59 
     * @throws 
     */
    public void insertBatchResultDetail(List<SysPrebatchResultDetail> details) {
        prebatchCheckCommonMapper.insertBatchResultDetail(details);

    }

    /**
     * @title 入表 更新
     * @description 
     * @author wj 
     * @updateTime 2021/5/13 11:00 
     * @throws 
     */
    public void insertBatchResultDetail(SysPrebatchResultDto dto, List<SysPrebatchResultDetail> details) {
        if(!StringUtil.isEmptyList(details)){
            if(StringUtil.equals("00",dto.getStopFlag())){
                // 是否卡批  是的话 则为错误  00不卡批则为预警
                dto.setTaskStatus(DictCodeListExt3.批前检查_执行状态_预警.getValue());
            }else{

                dto.setTaskStatus(DictCodeListExt3.批前检查_执行状态_错误.getValue());
            }

            dto.setDetailCount(details.size());

            int singleInsert = 50;
            for(int i=0;i*singleInsert<details.size();i++) {
            	 int endIndex = (i+1)*singleInsert;
            	 if(endIndex>details.size()) {
            		 endIndex = details.size();
            	 }
            	 prebatchCheckCommonMapper.insertBatchResultDetail(details.subList(i*singleInsert, endIndex));	
            }

        }else{
            dto.setTaskStatus(DictCodeListExt3.批前检查_执行状态_成功.getValue());
        }
    }


    /**
     *
     * @param batchExecuteInfo
     * @return
     */
    public List<SysPrebatchResultDto> selectAllInstAndPackageDto(SysBatchExecInfo batchExecuteInfo) {

        List<SysPrebatchResultDto> results = new ArrayList<>();
        SysPrebatchTaskAddDto taskInfo = prebatchCheckCommonMapper.selectTaskList(batchExecuteInfo.getTaskId());
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            List<String> instCodeLists = prebatchCheckCommonMapper.selectAllInst();

            if (!StringUtil.isEmptyList(instCodeLists)) {
                for (String instCode : instCodeLists) {
                    SysPrebatchResultDto result =  packageResultDto(batchExecuteInfo,taskInfo);
                    result.setInstitutionCode(instCode);
                    results.add(result);
                }
            }
        }else{

            SysPrebatchResultDto result =  packageResultDto(batchExecuteInfo,taskInfo);
            results.add(result);
        }

        if (!StringUtil.isEmptyList(results)){
            prebatchCheckCommonMapper.insertBatchPrebatchResultList(results);
        }
        return results;

    }

    /**
     * @title 组装
     * @description 
     * @author wj 
     * @updateTime 2021/5/13 11:00 
     * @throws 
     */
    private SysPrebatchResultDto packageResultDto(SysBatchExecInfo batchExecuteInfo, SysPrebatchTaskAddDto taskInfo) {
        SysPrebatchResultDto result = new SysPrebatchResultDto();
        result.setSeqNo(IDGenerator.generate());
        result.setTaskId(batchExecuteInfo.getTaskId());
        result.setBeanName(taskInfo == null ? "" : taskInfo.getBeanName());
        result.setStopFlag(taskInfo == null ? "00" : taskInfo.getStopFlag());
        result.setStartMode(batchExecuteInfo.getStartMode());
        result.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
        result.setCheckDate(batchExecuteInfo.getExecuteDate());
        result.setExecuteDate(new Date());
        result.setTaskStatus(DictCodeListExt3.批前检查_执行状态_执行中.getValue());
        result.setStartTime(new Date());
        result.setCreateUser(GlobalConstant.SYSTEM_USER);
        result.setUpdateUser(GlobalConstant.SYSTEM_USER);
        return result;

    }

    /**
     * @title 删除
     * @description
     * @author wj
     * @updateTime 2021/5/13 11:00
     * @throws
     */
    public void deleteResultAndDetailList(Map<String, Object> param) {
        prebatchCheckCommonMapper.deleteResultDetailList(param);
        prebatchCheckCommonMapper.deleteResultList(param);
    }
}
