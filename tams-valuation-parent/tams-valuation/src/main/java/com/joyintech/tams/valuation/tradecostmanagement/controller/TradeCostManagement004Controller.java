package com.joyintech.tams.valuation.tradecostmanagement.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.tradecostmanagement.service.TradeCostManagement002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 交易费用管理 双击明细
 *
 * @Author wangXiaoYong
 * @create 2019/11/19
 */
@Controller
@PageId("TradeCostManagement004")
@RequestMapping("/valuation/tradecostmanagement/TradeCostManagement004")
public class TradeCostManagement004Controller extends BaseController {

    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        //一次性费用类型 B05佣金  B13账户维护费  B99其他费用  B06交易手续费  B08结算手续费
        //币种 登记托管机构
        //是否待摊
        List<SelectOptionDto> feeTypeOptions = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto1 = new SelectOptionDto();dto1.setId(DictCodeListExt1.一次性费用类型_佣金.getValue());dto1.setText(DictCodeListExt1.一次性费用类型_佣金.getCodeName());feeTypeOptions.add(dto1);
        SelectOptionDto dto2 = new SelectOptionDto();dto2.setId(DictCodeListExt1.一次性费用类型_交易手续费.getValue());dto2.setText(DictCodeListExt1.一次性费用类型_交易手续费.getCodeName());feeTypeOptions.add(dto2);
        SelectOptionDto dto3 = new SelectOptionDto();dto3.setId(DictCodeListExt1.一次性费用类型_结算手续费.getValue());dto3.setText(DictCodeListExt1.一次性费用类型_结算手续费.getCodeName());feeTypeOptions.add(dto3);
        SelectOptionDto dto4 = new SelectOptionDto();dto4.setId(DictCodeListExt1.一次性费用类型_账户维护费.getValue());dto4.setText(DictCodeListExt1.一次性费用类型_账户维护费.getCodeName());feeTypeOptions.add(dto4);
        SelectOptionDto dto5 = new SelectOptionDto();dto5.setId(DictCodeListExt1.一次性费用类型_其他费用.getValue());dto5.setText(DictCodeListExt1.一次性费用类型_其他费用.getCodeName());feeTypeOptions.add(dto5);
        model.addAttribute("FEE_TYPE", JsonUtils.objectArray2Json(feeTypeOptions.toArray()));
        setSelectOptionToModel(model, DictCode.登记托管机构,DictCode.币种,DictCode.是否);
        model.addAttribute("FEE_TYPE_B08", DictCodeListExt1.一次性费用类型_结算手续费.getValue());
        model.addAttribute("FEE_TYPE_B13", DictCodeListExt1.一次性费用类型_账户维护费.getValue());
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        model.addAllAttributes(pageParam);
        return super.init(model);
    }


}
