/*
 * 文件名：ValBokBalanceReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.valfileupload.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.valfileupload.dao.ValFileUploadMapper;
import com.joyintech.tams.valuation.batch.valfileupload.dto.ValFileUploadReaderDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 批处理文件上传 reader
 * 
 * @author weipeng
 * @version 1.0 2018年12月20日
 * @see ValFileUploadReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValFileUploadReader extends BaseReader<ValFileUploadReaderDto> {
    
    @Autowired
    private ValFileUploadMapper valFileUploadMapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValFileUploadReaderDto> createList() {
        Map<String,Object> params = getParams();
        // 上传状态为 02：未上传
        params.put("uploadStatus", GlobalConstant.TYPE_02);
        List<ValFileUploadReaderDto> listDto = new ArrayList<>();
//        listDto = valFileUploadMapper.selectBokFileGenTodo(params);
        return listDto;
    }

}
