/*
 * 文件名：AccountsAdjustAccountEntry001Service.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All
 * Rights Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月13日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.assitsaccount.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dao.ValAssistAccounting001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistAccounting001Dto;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistAccounting002Dto;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistHandDto;
import com.joyintech.tams.valuation.common.utils.DataHandleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 殷亚
 * @description 辅助核算配置
 * @date 2022-1-12 15:40
 */
@Service(value = "serviceValAssistAccounting002Service")
@Transactional(rollbackFor = Exception.class)
public class ValAssistAccounting002Service extends CommonExportService<ValAssistAccounting001Dto> {

    /**
     * AccountsAdjustAccountEntry001Mapper
     */
    @Autowired
    private ValAssistAccounting001Mapper valAssistAccounting001Mapper;


    /**
     * @author  殷亚
     * @description update
     * @date 2022-1-17 15:11
     * @return int
     */
    public int update(ValAssistAccounting002Dto entity) {
        String handlist=entity.getHandjson();
        JSONArray jsonArray = JSONArray.parseArray(handlist);
        List<ValAssistHandDto> hands=new ArrayList<ValAssistHandDto>();
        for(int i=0; i<jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            ValAssistHandDto hand = JSONObject.parseObject(object.toJSONString() , ValAssistHandDto.class);// 将string类型直接封装成对象
            hand.setBooksetId(entity.getBooksetId());
            hand.setSubjectInternalCode(entity.getSubjectInternalCode());
            hand.setCreateUser(entity.getCreateUser());
            hand.setCreateDept(entity.getCreateDept());
            hand.setUpdateUser(entity.getUpdateUser());
            hand.setCreateTime(entity.getCreateTime());
            hand.setUpdateTime(entity.getUpdateTime());
            hands.add(hand);
        }
        //数据存在
        if(hands!=null && !hands.isEmpty()){
            List<List<ValAssistHandDto>> list = DataHandleUtil.splitList(hands);
            for(List<ValAssistHandDto> handDtos : list){
                int a= valAssistAccounting001Mapper.mergeSubjectHands(hands);
            }
        }


        int rule=valAssistAccounting001Mapper.mergeSubjectRule(entity);
        return rule;
    }





}
