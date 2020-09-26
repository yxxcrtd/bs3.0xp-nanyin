/*
 * 文件名：RevDealBookReaderReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.profitdistribution.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.TreeNode;
import com.joyintech.tams.valuation.batch.profitdistribution.dao.ProfitDistributionBookMapper;
import com.joyintech.tams.valuation.batch.profitdistribution.dto.ProfitDistributionReaderDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 利润分配批处理 - reader相关业务处理
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see ProfitDistributionBookReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProfitDistributionBookReaderService {

    /**
     * reader方法的 Service
     */
    @Autowired
    private ProfitDistributionBookMapper profitDistributionBookMapper;
    
    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 获取数据库数据
     * 
     * @param param 传递参数
     * @return List<BookDetailCompareIntoReadResult>
     * @see
     * @since 1.0
     */
    public List<ProfitDistributionReaderDto> execute(Map<String, Object> param) {

        param.put("profitType", DictCodeList.收益类型_货币型.getValue());
        List<ProfitDistributionReaderDto> dtos = profitDistributionBookMapper.searchProfitDistribution(param);
        
        if (dtos != null && dtos.size() > 0) {
            // 获取分录信息
            Set<String> bookSetIds = new HashSet<String>();
            dtos.forEach(item -> {
                if (StringUtil.isNotEmpty(item.getBooksetId())) {
                    bookSetIds.add(item.getBooksetId());
                }
            });
            Map<String, TreeNode> nodes = bookCommonLogic.commonForGetTreeNodeMap(bookSetIds, dtos.get(0).getTaskId());
            for (ProfitDistributionReaderDto currDTO : dtos) {
                currDTO.setBookSetTreeNode(nodes.get(currDTO.getBooksetId()));
            }
        }
        
        return dtos;
        
    }

}
