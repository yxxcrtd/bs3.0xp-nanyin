/*
 * 文件名：InterfSendCommonReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.common.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.interf.core.InterfBaseReader;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendReaderEntity;

/**
 * 接口发送Reader
 * @author 王波
 * @version 1.0 2018年11月19日
 * @see InterfSendCommonReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class InterfSendCommonReader extends InterfBaseReader<SendReaderEntity> {

    @Override
    public List<SendReaderEntity> createList() {
        List<SendReaderEntity> list = new ArrayList<>();
        SendReaderEntity entity = new SendReaderEntity();
        String bookIds = (String)getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        entity.setBooksetList(Arrays.asList(bookIds.split(",")));
        InterfExecuteInfo execInfo = (InterfExecuteInfo)getParams().get("executeInfo");
        entity.setExeDate(execInfo.getInterfDate());
        list.add(entity);
        return list;
    }
    
}
