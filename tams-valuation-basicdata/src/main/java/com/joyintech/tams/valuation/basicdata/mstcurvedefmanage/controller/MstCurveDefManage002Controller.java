
package com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.dto.MstCurveDefManageDto;
import com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.service.MstCurveDefManage001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 收益率曲管理详情
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author songjun
 * @version 1.0 2021-07-12
 * @see MstCurveDefManage002Controller
 * @since 1.0
 */
@Controller
@PageId("MstCurveDefManage002")
@RequestMapping("/valuation/basicdata/mstcurvedefmanage/MstCurveDefManage002")
public class MstCurveDefManage002Controller extends BaseController{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    @Autowired
    private MstCurveDefManage001Service mstCurveDefManage001Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益率曲线类型);
        this.setSelectOptionToModel(model, DictCode.估值来源);
        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("curveCode",this.getParam("curveCode"));
        MstCurveDefManageDto dto = mstCurveDefManage001Service.selectByKey(queryParam);
        model.addAttribute("entity",dto);
        return super.init(model);
    }

}
