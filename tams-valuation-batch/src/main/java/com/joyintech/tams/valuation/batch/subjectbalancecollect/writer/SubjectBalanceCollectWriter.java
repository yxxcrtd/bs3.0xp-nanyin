/*
 * 文件名：SubjectBalanceCollectWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.subjectbalancecollect.writer;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.dao.BokBalanceMapper;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.dto.SubjectBalanceBatch;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.dto.SubjectBalanceGenerateResult;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see SubjectBalanceCollectWriter
 * @since 1.0
 */
@Service
public class SubjectBalanceCollectWriter extends BaseWriter<NeedToWriteEntity>{
    
	private static final Logger logger = LoggerFactory.getLogger(SubjectBalanceCollectWriter.class);
	
    /**
     * 科目余额汇总mapper
     */
    @Autowired
    private BokBalanceMapper valBokBalanceMapper;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items) throws Exception {
        //获取process返回组合的list值 - 记账凭证list
        List<SubjectBalanceGenerateResult> resultList = (List<SubjectBalanceGenerateResult>) items;
        
        //循环科目余额list，对每个记账凭证进行操作
        for(SubjectBalanceGenerateResult result : resultList){
            //入库科目余额数据 
            List<SubjectBalanceBatch> listBatches = result.getSubjectBalanceBatchs();
            if(CollectionUtils.isNotEmpty(listBatches)){
                listBatches.forEach(item ->{
                	logger.debug(item.getSubjectNo()+":"+item.getbCcy()+":"+item.getoCcy()+":"+item.getBooksetId());
                    //循环录入库中
                    valBokBalanceMapper.insert(item);
                });
            }
            
        }
        
    }

}
