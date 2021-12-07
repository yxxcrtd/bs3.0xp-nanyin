/**
 * @author sjj
 * @version 1.0.0
 * @ClassName CfetsNoSettleTask.java
 * @Description 批前检查 未自动交收
 * @createTime 2021年12月3日 10:57:00
 */
package com.joyintech.tams.invest.batch.prebatchcheck.cfetsnosettle.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.batch.prebatchcheck.cfetsnosettle.dao.CfetsNoSettleMapper;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CfetsNoSettleTaskService {

    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;

    @Autowired
    private CfetsNoSettleMapper cfetsNoSettleMapper;

    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode) {

        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            // 固定key 勿修改
            param.put("PRE_INST_CODE",instCode);
        }
        param.put("checkDate",dto.getCheckDate());
        List<SysPrebatchResultDetail> details = new ArrayList<>();
        //SysPrebatchResultDetail sysPrebatchResultDetail =new SysPrebatchResultDetail();

        if (StringUtil.equals(CacheUtil.getSysParam("auto_settle_flag"),"N")){
            //平安业务类型
            param.put("PINAN_BUSINESS_TYPE_09", DictCodeListExt1.平安业务类型_债券类资产买入.getValue());
            param.put("PINAN_BUSINESS_TYPE_10", DictCodeListExt1.平安业务类型_债券类资产卖出.getValue());
            param.put("PINAN_BUSINESS_TYPE_11", DictCodeListExt1.平安业务类型_债券类资产行权.getValue());
            param.put("PINAN_BUSINESS_TYPE_35", DictCodeListExt1.平安业务类型_债券中间收息.getValue());
            param.put("PINAN_BUSINESS_TYPE_36", DictCodeListExt1.平安业务类型_债券中间还本.getValue());
            param.put("PINAN_BUSINESS_TYPE_37", DictCodeListExt1.平安业务类型_债券到期.getValue());
            param.put("PINAN_BUSINESS_TYPE_20", DictCodeListExt1.平安业务类型_正回购.getValue());
            param.put("PINAN_BUSINESS_TYPE_51", DictCodeListExt1.平安业务类型_逆回购到期.getValue());
            param.put("PINAN_BUSINESS_TYPE_52", DictCodeListExt1.平安业务类型_正回购到期.getValue());
            param.put("PINAN_BUSINESS_TYPE_74", DictCodeListExt1.平安业务类型_买断式正回购.getValue());
            param.put("PINAN_BUSINESS_TYPE_75", DictCodeListExt1.平安业务类型_买断式逆回购.getValue());
            param.put("PINAN_BUSINESS_TYPE_76", DictCodeListExt1.平安业务类型_买断式正回购到期.getValue());
            param.put("PINAN_BUSINESS_TYPE_77", DictCodeListExt1.平安业务类型_买断式逆回购到期.getValue());
            param.put("PINAN_BUSINESS_TYPE_105", DictCodeListExt2.平安业务类型_债券借贷融入.getValue());
            param.put("PINAN_BUSINESS_TYPE_106", DictCodeListExt2.平安业务类型_债券借贷融出.getValue());
            param.put("PINAN_BUSINESS_TYPE_107", DictCodeListExt2.平安业务类型_债券借贷融入到期.getValue());
            param.put("PINAN_BUSINESS_TYPE_108", DictCodeListExt2.平安业务类型_债券借贷融出到期.getValue());

            param.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
            param.put("isCalcN", BussinessDictCodeList.是否_否.getValue());
            //交收状态
            param.put("DELIVERY_STATUS_01", DictCodeListExt3.交收状态_未交收.getValue());

            List<SysPrebatchResultDetail> details1 = cfetsNoSettleMapper.selectAllSettleToDoList(param);
            if(!StringUtil.isEmptyList(details1)){
                details.addAll(details1);
            }
        }

        if(!StringUtil.isEmptyList(details)){
            details.forEach(item -> setResultDetail(batchExecuteInfo,dto,item));
        }

        prebatchCheckCommonService.insertBatchResultDetail(dto,details);
    }

    /**
     * @title 组装
     * @description 
     * @author wj 
     * @updateTime 2021/5/13 11:03 
     * @throws 
     */
    private void setResultDetail(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, SysPrebatchResultDetail item) {
        item.setResultSeqNo(dto.getSeqNo());
        item.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
        item.setTaskId(batchExecuteInfo.getTaskId());

        item.setSeqNo(IDGenerator.generate());
        item.setCreateUser(dto.getCreateUser());
        item.setUpdateUser(dto.getUpdateUser());

    }
}
