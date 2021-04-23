/*
 * 文件名：SubjectBalanceCollectReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dao.SubjectBalanceAgainCollectMapper;
import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto.BokBalanceAgainCollectLogDto;
import com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto.BooksetTodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;

/**
 * 科目余额汇总批处理 reader
 * @author lby
 * @version 1.0 2020年12月29日
 * @see SubjectBalanceAgainCollectReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SubjectBalanceAgainCollectReader extends BaseReader<BooksetTodoDto>{
    
    /**
     *  科目余额汇总mapper
     */
    @Autowired
    private SubjectBalanceAgainCollectMapper subjectBalanceAgainCollectMapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<BooksetTodoDto> createList() {
        //查询待处理账套信息
        List<BooksetTodoDto> listDto = subjectBalanceAgainCollectMapper.selectBooksetTodo(this.getParams());
        listDto.forEach(item->{
            Map<String,Object> map = new HashMap<>();
            BokBalanceAgainCollectLogDto balanceAgainCollectLogDto = new BokBalanceAgainCollectLogDto();
            map.put("booksetId",item.getBooksetId());
            map.put("startBookDate",item.getStartBookDate());
            //科目余额  删除  删除条件：账套代码 = 批处理任务信息.账套代码  AND 余额日期 >= 批处理任务信息.汇总开始日
            subjectBalanceAgainCollectMapper.deleteBalanceByKey(map);
            //科目余额重汇信息记录表	更新 更新字段：科目余额重汇信息记录表.汇总结束日 = 批处理任务信息.汇总开始日 -1
            balanceAgainCollectLogDto.setCdate(DateUtil.addDay(item.getStartBookDate(), -1));
            balanceAgainCollectLogDto.setBooksetId(item.getBooksetId());
            subjectBalanceAgainCollectMapper.updateLog(balanceAgainCollectLogDto);
        });
        return listDto;
    }

}
