package com.joyintech.tams.valuation.batch.unitfycaccountant.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.unitfycaccountant.entity.UnitfycaccountantInfoDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfo;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Scope("prototype")
public class UnitfycaccountantInfoProcessor extends BaseProcessor<NeedOperateEntity, UnitfycaccountantInfoDto> {

    @Override
    public UnitfycaccountantInfoDto process(NeedOperateEntity item)
            throws Exception {
        // 读取需要处理的任务模块
        String UNITFYCACCOUNTANT_MODEL_CODE = (String)CacheUtil.getSysParam("UNITFYCACCOUNTANT_MODEL_CODE");
        String[] modelCodes = null;
        if (UNITFYCACCOUNTANT_MODEL_CODE != null) {
            modelCodes = UNITFYCACCOUNTANT_MODEL_CODE.split(",");
        }
        if (modelCodes == null || modelCodes.length == 0) {
            return null;
        }
        UnitfycaccountantInfoDto dto = new UnitfycaccountantInfoDto();
        List<SysUnaccountantInfoDetail> list = new ArrayList<>();
        SysUnaccountantInfo sysUcaccountantInfo = new SysUnaccountantInfo();

        // 产品组ID
        String prdGrpSeqNo = this.getBatchExecuteDetail().getPrdGroupSeqNo();

        Long orderNo = 1l;
        String seqno = IDGenerator.generate();
        sysUcaccountantInfo.setSeqNo(seqno);
        sysUcaccountantInfo.setTaskName("system" + "一键做账" + DateUtil.now());
        sysUcaccountantInfo.setPrdGroupSeqNo(prdGrpSeqNo);
        sysUcaccountantInfo.setStatus("0");
        sysUcaccountantInfo.setStartTime(DateUtil.now());
        sysUcaccountantInfo.setBusiDate(DateUtil.now());

        for(String modelCode : modelCodes) {
            SysUnaccountantInfoDetail e = new SysUnaccountantInfoDetail();
            e.setSeqNo(seqno);
            e.setBusiDate(DateUtil.now());
            e.setModelCode(modelCode);
            e.setOrderNo(orderNo ++);
            e.setStatus("0");
            list.add(e);
        }
        dto.setSysUcaccountantInfo(sysUcaccountantInfo);
        dto.setList(list);
        return dto;
    }
}
