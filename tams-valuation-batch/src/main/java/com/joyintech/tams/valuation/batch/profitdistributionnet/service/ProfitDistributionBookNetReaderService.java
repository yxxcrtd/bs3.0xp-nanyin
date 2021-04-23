/*
 * 文件名：ProfitDistributionBookNetReaderService.java
 * 版权：Copyright 2020-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.1
 * 修改人：WUJIE
 * 修改时间：2020年4月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.1
 */
package com.joyintech.tams.valuation.batch.profitdistributionnet.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.profitdistributionnet.dao.ProfitDistributionBookNetMapper;
import com.joyintech.tams.valuation.batch.profitdistributionnet.dto.ProfitDistributionNetReaderDto;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 净值型产品利润分配批处理 - reader相关业务处理
 * 
 * @author WUJIE
 * @version 1.0 2020年4月1日
 * @see ProfitDistributionBookNetReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProfitDistributionBookNetReaderService {

    /**
     * reader方法的 Service
     */
    @Autowired
    private ProfitDistributionBookNetMapper profitDistributionBookNetMapper;
    
    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 获取数据库数据
     * 
     * @param param 传递参数
     * @return List<ProfitDistributionNetReaderDto>
     * @see
     * @since 1.0
     */
    public List<ProfitDistributionNetReaderDto> execute(Map<String, Object> param) {

    	//取净值型产品的净值归一的分红方案
    	param.put("acctMode", DictCodeList.收益类型_净值型.getValue());
    	param.put("IS_NET_MORMALIZE", DictCodeList.是否_是.getValue());
        
        List<ProfitDistributionNetReaderDto> dtos = profitDistributionBookNetMapper.searchProfitDistribution(param);
        
        if (dtos != null && dtos.size() > 0) {
        	
        	List<ProfitDistributionNetReaderDto> resultList = dtos.stream().collect(Collectors.collectingAndThen(
        			Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> {
                        // 根据账套代码、账务明细代码去重
                        return  o.getBooksetId() + "," + o.getBokDetailId();
                    }))), ArrayList::new));
        	
            // 获取分录信息
            Set<String> bookSetIds = new HashSet<String>();
            resultList.forEach(item -> {
                if (StringUtil.isNotEmpty(item.getBooksetId())) {
                    bookSetIds.add(item.getBooksetId());
                }
            });
            Map<String, TreeNode> nodes = bookCommonLogic.commonForGetTreeNodeMap(bookSetIds, resultList.get(0).getTaskId());
            for (ProfitDistributionNetReaderDto currDTO : resultList) {
                currDTO.setBookSetTreeNode(nodes.get(currDTO.getBooksetId()));
            }
        }
        
        return dtos;
        
    }

}
