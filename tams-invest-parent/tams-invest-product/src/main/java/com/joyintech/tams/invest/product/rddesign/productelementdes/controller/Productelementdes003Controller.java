package com.joyintech.tams.invest.product.rddesign.productelementdes.controller;
/**
 * 产品要素设计-查询
 * @author jiangyunlong
 * @date 2019-9-25
 */

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.product.rddesign.productelementdes.dto.Productelementdes001Dto;
import com.joyintech.tams.invest.product.rddesign.productelementdes.service.ProductelementdesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@PageId("Productelementdes003")
@RequestMapping("/invest/product/rddesign/productelementdes/productelementdes003")
public class Productelementdes003Controller extends BaseController {

    /**
     * 产品报价管理查询service
     */
    @Autowired
    private ProductelementdesService productelementdesService;
     @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
     String requirementId=getParam("requirementId");
     Productelementdes001Dto entity=findDto(requirementId);
    model.addAttribute("entity",entity);
        return super.init(model);
    }
    /**
     * 查看需求信息
     * @return
     */
    public Productelementdes001Dto findDto(String requirementId){

        return productelementdesService.findSinglePage(requirementId);
    }

}
