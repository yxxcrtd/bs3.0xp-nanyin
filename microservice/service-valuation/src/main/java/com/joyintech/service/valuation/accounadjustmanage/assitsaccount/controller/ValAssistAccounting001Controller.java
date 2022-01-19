/*
 * 文件名：Accountset001Controller.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2016年12月26日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.assitsaccount.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistAccounting001Dto;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.service.ValAssistAccounting001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author  殷亚
 * @description 辅助核算配置 微服务
 * @date 2022-1-12 15:23   
 */
@RestController("serviceValAssistAccounting001Controller")
@RequestMapping("ValAssistAccounting001")
public class ValAssistAccounting001Controller extends BaseController {

    /**
     * ValAssistAccounting001Service
     */
    @Autowired
    private ValAssistAccounting001Service valAssistAccounting001Service;


    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @PostMapping("/findBooksets")
    public List<SelectOptionDto> findBooksets() {
        return valAssistAccounting001Service.findBooksets();
    }



    /**
     * 检索数据
     * 
     * param booksetId 账套代码
     * @return AccountSetDto list
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValAssistAccounting001Dto> findPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length) {

        return valAssistAccounting001Service.findPage(page, length, param);
    }


}
