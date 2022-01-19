/*
 * 文件名：AccountsAdjustAccountEntry001Service.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All
 * Rights Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月13日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.assitsaccount.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dao.ValAssistAccounting001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistAccounting001Dto;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistAccounting002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        int a= valAssistAccounting001Mapper.updateByPrimaryKeySelective(entity);
        int rule=valAssistAccounting001Mapper.mergeSubjectRule(entity);
        return a;
    }





}
