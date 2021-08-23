package com.joyintech.tams.invest.portfolio.ptlmock.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
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
import com.joyintech.tams.systemtools.interf.IPlatformDate;
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
                DictCode.组合类型);
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.平安资产类型, null);
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions();
            for (int index = options.size() - 1; index >= 0; index--) {
                SelectOptionDto option = options.get(index);
                // 去除不需要的字典类型
                if (StringUtil.equals(option.getId(), DictCodeList.平安资产类型_计划类资产_利率型.getValue())) {
                    options.remove(option);
                }
            }
            model.addAttribute("PINAN_ASSET_TYPE", JsonUtils.objectArray2Json(options.toArray()));
        }
        // 组合到期日区间默认为平台日至10个自然日后
        model.addAttribute("mdatepStr",iPlatformDate.getSysDateStr());
        model.addAttribute("mdatepEnd", DateUtil.addDay(iPlatformDate.getSysDateStr(), 10));
        model.addAttribute("selectedFinprodId",getParam("selectedFinprodId"));
        return super.init(model);
    }
    
    /**
     * 分页查询
     * @return Page<MstInterestRateQuotationInfoDto>泛型的page实体类
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<PtlMockTest003Dto> findPage(){
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
