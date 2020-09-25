package com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.dto.FinProductFeaAna001Dto;
import com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.service.FinProductFeaAna001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 可行性分析
 *
 * @author guyunfei
 * @version 1.0 2019年09月24日
 * @see FinProductFeaAna001Controller
 * @since 1.0
 */
@PageId("FinProductFeaAna001")
@Controller
@RequestMapping("/invest/product/rddesign/feasibilityanalysis/FinProductFeaAna001")
public class FinProductFeaAna001Controller extends BaseController {

    @Autowired
    private FinProductFeaAna001Service finProductFeaAna001Service;



    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.可行性状态,DictCode.可行需求分类);
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.有效状态, null);
        List<SelectOptionDto> options = selectDto.getOptions();
        List<SelectOptionDto> optionsNew = new ArrayList<SelectOptionDto>();
        for (SelectOptionDto dto : options) {
            if (StringUtil.equals(DictCodeListExt1.有效状态_待处理.getValue(), dto.getId())
                    || StringUtil.equals(DictCodeListExt1.有效状态_有效.getValue(), dto.getId())) {
                optionsNew.add(dto);
            }
        }
        model.addAttribute(DictCode.有效状态.getValue(),
                JsonUtils.objectArray2Json(optionsNew.toArray()));
        model.addAttribute("effective",DictCodeListExt1.有效状态_待处理.getCodeName());
        model.addAttribute("analyse_status",DictCodeListExt2.可行性状态_待定.getCodeName());
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProductFeaAna001Dto> selectForPage(){
        return finProductFeaAna001Service.selectForPage(RequestUtil.getPageParam());
    }


    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return finProductFeaAna001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

}
