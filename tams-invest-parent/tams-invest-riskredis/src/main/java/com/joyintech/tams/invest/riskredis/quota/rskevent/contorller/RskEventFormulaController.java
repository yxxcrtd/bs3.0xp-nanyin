/*
 * 文件名：RskEventFormulaController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.riskredis.quota.rskevent.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEventFormulaDto;
import com.joyintech.tams.invest.riskredis.quota.rskevent.service.RskEventFormulaService;

/**
 * 风控事项关联参数配置-公式配置
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月10日
 * @see RskEventFormulaController
 * @since 1.0
 */
@Controller
@PageId("RskEventFormula")
@RequestMapping("/invest/riskredis/quota/rskevent/RskEventFormula")
public class RskEventFormulaController extends BaseController {

    @Autowired
    private RskEventFormulaService formulaService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        // 初始化选项字典数据
        this.setSelectOptionToModel(model, BussinessDictCodeRds.操作符,
            BussinessDictCodeRds.风险事件运算关系对象);
        List<SelectOptionDto> paramList = formulaService.getParamList();
        model.addAttribute("IND_PARAM", JsonUtils.objectArray2Json(paramList.toArray()));
        RskEventFormulaDto dto = new RskEventFormulaDto();
        String param = getParam("customVal");
        if (StringUtil.isNotEmpty(param)) {
            String[] arr = StringUtil.split(param);
            if (arr != null && arr.length == 3) {
                dto.setParaCode(arr[0]);
                dto.setOprType(arr[1]);
                dto.setRelItem(arr[2]);
            }
        }
        model.addAttribute("entity", dto);
        return "invest/riskredis/quota/rskevent/RskEventFormula";
    }

}
