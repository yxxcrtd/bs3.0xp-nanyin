package com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.dto.ValBokBookDetailDelDto;
import com.joyintech.tams.valuation.batch.common.bokbookdetaildelete.dto.ValBokBookDetailDelResult;

/**
 * 账务明细删除批处理 - 处理
 * 
 * @author chengzhong
 * @version 1.0 2018年5月7日
 * @see ValBokBookDetailDelProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValBokBookDetailDelProcessor extends BaseProcessor<NeedOperateEntity, ValBokBookDetailDelResult> {

    @Override
    public ValBokBookDetailDelResult process(NeedOperateEntity items)
        throws Exception {
        // 将获取的实体类放入result中
        ValBokBookDetailDelResult result = ((ValBokBookDetailDelDto)items).toResult();
        result.setProdId("ALL");
        return result;
    }
}
