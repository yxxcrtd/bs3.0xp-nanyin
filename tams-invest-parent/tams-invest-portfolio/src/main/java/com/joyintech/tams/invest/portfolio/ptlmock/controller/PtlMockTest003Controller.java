package com.joyintech.tams.invest.portfolio.ptlmock.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTest003Dto;
import com.joyintech.tams.invest.portfolio.ptlmock.service.PtlMockTest003Service;

/**
 *  模拟试算可选资产
 * @author yjm 
 * @version 1.0 2020/04/13
 * @see PtlMockTest003Controller
 * @since 1.0
 */
@Controller
@PageId("PtlMockTest003")
@RequestMapping("/invest/portfolio/ptlmock/PtlMockTest003")
public class PtlMockTest003Controller extends BaseController {
    @Autowired
    private PtlMockTest003Service ptlMockTest003Service;
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益类型,
                DictCode.组合类型, DictCode.平安资产类型);
        // 组合到期日区间默认为平台日至10个自然日后
        model.addAttribute("mdatepStr",iPlatformDate.getSysDateStr());
        model.addAttribute("mdatepEnd", DateUtil.addDay(iPlatformDate.getSysDateStr(), 10));
        model.addAttribute("selectedFinprodId",getParam("selectedFinprodId"));
        return super.init(model);
    }
    
    /**
     * 分页查询
     * @return Page<MstInterestRateQuotationInfoDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlMockTest003Dto> findPage()
        throws Exception {
        return ptlMockTest003Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 资产代码下拉框
     */  
    @RequestMapping("getFinprodId") 
    @ResponseBody
    public List<SelectOptionDto> getFinprodId(@RequestParam Map<String, Object> param) {
        return ptlMockTest003Service.getFinprodId(param);
    }
}
