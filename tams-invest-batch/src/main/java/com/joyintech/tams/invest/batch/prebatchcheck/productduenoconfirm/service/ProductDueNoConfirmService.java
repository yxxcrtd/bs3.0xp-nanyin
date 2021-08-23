/**
 * @author wj
 * @version 1.0.0
 * @ClassName ProductDueNoConfirmService.java
 * @Description 批前检查 产品到期未确认service
 * @createTime 2021年05月13日 10:57:00
 */
package com.joyintech.tams.invest.batch.prebatchcheck.productduenoconfirm.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.prebatchcheck.common.dto.SysPrebatchResultDto;
import com.joyintech.tams.invest.batch.prebatchcheck.common.service.PrebatchCheckCommonService;
import com.joyintech.tams.invest.batch.prebatchcheck.productduenoconfirm.dao.ProductDueNoConfirmMapper;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductDueNoConfirmService {

    @Autowired
    private PrebatchCheckCommonService prebatchCheckCommonService;

    @Autowired
    private ProductDueNoConfirmMapper productDueNoConfirmMapper;

    /**
     * @title 执行
     * @description 
     * @author wj 
     * @updateTime 2021/5/13 11:02 
     * @throws 
     */
    public void execute(SysBatchExecInfo batchExecuteInfo, SysPrebatchResultDto dto, String instCode) {

        Map<String,Object> param = new HashMap<>();
        if(StringUtil.isNotEmpty(instCode)){
            // 固定key 勿修改
            param.put("PRE_INST_CODE",instCode);
        }
        //param.put("businessType", BussinessDictCodeList.共同业务类型_产品要素管理.getValue());
        param.put("checkDate",dto.getCheckDate());
        List<SysPrebatchResultDetail> details = new ArrayList<>();
        //  收益型产品  取 到期日
        List<SysPrebatchResultDetail> details1 = productDueNoConfirmMapper.selectProductListSY(param);
        if(!StringUtil.isEmptyList(details1)){
            details.addAll(details1);
        }

        // 净值型  取 费用的 最大计提日
        List<SysPrebatchResultDetail> details2 = productDueNoConfirmMapper.selectProductListJZ(param);
        if(!StringUtil.isEmptyList(details2)){
            details.addAll(details2);
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
     * @updateTime 2021/5/13 11:02 
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
