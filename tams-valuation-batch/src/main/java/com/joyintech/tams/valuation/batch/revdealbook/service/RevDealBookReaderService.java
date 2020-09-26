/*
 * 文件名：RevDealBookReaderReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.revdealbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.batch.revdealbook.dao.RevDealBookMapper;
import com.joyintech.tams.valuation.batch.revdealbook.dto.RevDealBookReaderDto;

/**
 * 撤销交易批处理  - reader相关业务处理
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see RevDealBookReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RevDealBookReaderService {

    /**
     * reader方法的 Service
     */
    @Autowired
    private RevDealBookMapper revDealBookMapper;
    
    /**
     *  获取数据库数据
     * @param param 传递参数
     * @return List<BookDetailCompareIntoReadResult>
     * @see
     * @since 1.0
     */
    public List<RevDealBookReaderDto> execute(Map<String, Object> param) {
        
        List<RevDealBookReaderDto> listRevDealBookReaderDto = new ArrayList<RevDealBookReaderDto>();
        RevDealBookReaderDto revDealBookReaderDto = new RevDealBookReaderDto();
        //获取撤销交易
        revDealBookReaderDto.setListRevDealBookDetailDto(revDealBookMapper.searchRevDeal(param));
        
        listRevDealBookReaderDto.add(revDealBookReaderDto);

        return listRevDealBookReaderDto;
    }

}
