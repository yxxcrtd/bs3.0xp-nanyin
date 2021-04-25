package com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.batch.prebatchcheck.productduenoconfirm.dao.ProductDueNoConfirmMapper;
import com.joyintech.tams.invest.batch.prebatchcheck.tradedurnoconfirm.dao.TradeDurationNoConfirmMapper;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradeDurationNoConfirmService {

    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;

    @Autowired
    private TradeDurationNoConfirmMapper tradeDurationNoConfirmMapper;

    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode) {

        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            // 固定key 勿修改
            param.put("PRE_INST_CODE",instCode);
        }
        //param.put("businessType", BussinessDictCodeList.共同业务类型_产品要素管理.getValue());
        param.put("checkDate",dto.getCheckDate());
        List<SysPrebatchResultDetail> details = new ArrayList<>();
        // 债券 存续期 待处理
        List<SysPrebatchResultDetail> details1 = tradeDurationNoConfirmMapper.selectZQDurationList(param);
        if(!StringUtil.isEmptyList(details1)){
            details.addAll(details1);
        }

        //质押式回购存续期  待处理


        //存款存续期 待处理


        //拆借存续期 待处理



        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }

        prebatchCheckCommonService.insertBatchResultDetail(dto,details);

    }

    private void setResultDetail(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, SysPrebatchResultDetail item) {
        item.setResultSeqNo(dto.getSeqNo());
        item.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
        item.setTaskId(batchExecuteInfo.getTaskId());

        item.setSeqNo(IDGenerator.generate());
        item.setCreateUser(dto.getCreateUser());
        item.setUpdateUser(dto.getUpdateUser());

    }
}
