package com.joyintech.tams.invest.batch.prebatchcheck.processproduct.service;


import com.jixu.framework.msg.UUIDGenerator;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.batch.prebatchcheck.processproduct.dao.ProcessProductMapper;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcessProductService {

    @Autowired
    private ProcessProductMapper processProductMapper;

    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;

    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode){

        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            // 固定key 勿修改
            param.put("PRE_INST_CODE",instCode);
        }

        param.put("businessType", BussinessDictCodeList.共同业务类型_产品要素管理.getValue());

        param.put("checkDate",dto.getCheckDate());

        List<SysPrebatchResultDetail> details = processProductMapper.selectProcessProductList(param);
        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }

        // 判断结果集

        if(!StringUtil.isEmptyList(details)){
            if(StringUtil.equals("00",dto.getStopFlag())){
                // 是否卡批  是的话 则为错误  00不卡批则为预警
                dto.setTaskStatus(DictCodeListExt3.批前检查_执行状态_预警.getValue());
            }else{

                dto.setTaskStatus(DictCodeListExt3.批前检查_执行状态_错误.getValue());
            }

            dto.setDetailCount(details.size());

            prebatchCheckCommonService.insertBatchResultDetail(details);

        }else{
            dto.setTaskStatus(DictCodeListExt3.批前检查_执行状态_成功.getValue());
        }



    }

    /**
     * 将错误结果  组装成入表数据
     * @param batchExecuteInfo
     * @param dto
     * @param item
     */
    private void setResultDetail(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, SysPrebatchResultDetail item) {

        item.setResultSeqNo(dto.getSeqNo());
        item.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
        item.setTaskId(batchExecuteInfo.getTaskId());

        // 入表时 入得就是key 转换成名字 可以查询时再转换
        //item.setNextUser(prebatchCheckCommonService.setNextUserName(item.getN));
        item.setSeqNo(IDGenerator.generate());
        item.setCreateUser(dto.getCreateUser());
        item.setUpdateUser(dto.getUpdateUser());
    }


}
