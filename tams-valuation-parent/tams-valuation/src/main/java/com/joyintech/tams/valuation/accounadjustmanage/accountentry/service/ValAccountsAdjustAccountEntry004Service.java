/*
 * 文件名：AccountsAdjustAccountEntry004Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry004Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;



/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author weipeng
 * @version 1.0 2018年7月18日
 * @see ValAccountsAdjustAccountEntry004Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValAccountsAdjustAccountEntry004Service extends CommonExportService<ValAccountsAdjustAccountEntry004Dto> {

    public List<ValAccountsAdjustAccountEntry004Dto> searchList(String booksetId,
                                                                String sceneNodeId) {
        
        Map<String,Object> params  = new HashMap<String,Object>();
        params.put("booksetId", booksetId);
        params.put("sceneNodeId", sceneNodeId);
        
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAccountsAdjustAccountEntry004/searchList", params, ValAccountsAdjustAccountEntry004Dto.class);
    }

//    /**
//     * 递归
//     * 
//     * @param listDto
//     * @param booksetId 账套代码
//     * @param sceneNodeId 场景分支节点
//     * @return List<ValAccountsAdjustAccountEntry004Dto>
//     * @see
//     * @since 1.0
//     */
//    public List<ValAccountsAdjustAccountEntry004Dto> getListDto(List<ValAccountsAdjustAccountEntry004Dto> listDto,
//                                                                String booksetId,
//                                                                String sceneNodeId) {
//        // 没有查询结果 终止查询
//        if (listDto.size() == 0) {
//            return listDto;
//        }
//        // 父节点为空 终止查询
//        else if ("".equals(listDto.get(0).getFsceneNodeid())
//                 || StringUtils.isEmpty(listDto.get(0).getFsceneNodeid())) {
//            return listDto;
//        }
//        else {
//            List<ValAccountsAdjustAccountEntry004Dto> newlistDto = mapper.searchList(booksetId,
//                listDto.get(0).getFsceneNodeid());
//            getListDto(newlistDto, booksetId, sceneNodeId);
//            listDto.addAll(newlistDto);
//            return listDto;
//        }
//
//    }
//    
   
}
