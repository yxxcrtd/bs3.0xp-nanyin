/*
 * 文件名：ValBokBalanceReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valscheme.reader;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.valscheme.dao.ValSchemeMapper;
import com.joyintech.tams.valuation.batch.valscheme.dto.ValSchemeTodoDto;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;

/**
 * 估值表生成 reader
 * 
 * @author yangzhi
 * @version 1.0 2018年5月28日
 * @see ValSchemeReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValSchemeReader extends BaseReader<ValSchemeTodoDto>{
    
    /**
     *  余额表生成处理mapper
     */
    @Autowired
    private ValSchemeMapper mapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValSchemeTodoDto> createList() {
        Map<String,Object> params = getParams();
        params.put("pyeildCode", ValDictCodeList.账套配置表参数项_万份收益小数位位数.getValue());
        //根据参数查询对应待处理的账套
        List<ValSchemeTodoDto> listDto = mapper.selectValSchemeTaskTodo(params);
        return listDto;
    }

}
