package com.joyintech.tams.invest.entrust.etribrepo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉 资产系列管理
 * 
 * @author wl
 * @version 1.0 2021年2月7日
 * @see EtrIbRepoRk005Controller
 * @since 1.0
 */

@Controller
@PageId("EtrIbRepoRk005")
@RequestMapping("/invest/entrust/etribrepo/EtrIbRepoRk005")
public class EtrIbRepoRk005Controller extends BaseController {
	
	
	
    @Override
    public String init(Model model) {
    	this.setSelectOptionToModel(model, DictCode.组合类型,DictCode.收益类型
                ,DictCode.募集方式,DictCode.投资性质
                ,DictCode.开放类型,DictCode.是否,DictCode.尾数处理方式);
        return "invest/entrust/etribrepo/EtrIbRepoRk005";
    }
}
