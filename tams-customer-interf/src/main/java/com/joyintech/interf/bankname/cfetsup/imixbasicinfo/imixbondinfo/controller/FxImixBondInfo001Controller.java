package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixbondinfo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixbondinfo.dto.FxImixBondInfoDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixbondinfo.service.FxImixBondInfo001Service;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@PageId("FxImixBondInfo001")
@RequestMapping("/interf/cfetsup/imixbasicinfo/imixbondinfo/FxImixBondInfo001")
public class FxImixBondInfo001Controller extends BaseController {

    @Autowired
    private FxImixBondInfo001Service fxImixBondInfo001Service;

    /**
     * 是否特殊债
     */
    @ResponseBody
    @RequestMapping("/getSpecialdebt")
    public List<SelectOptionDto> getSpecialdebt() {

        List<SelectOptionDto> listtype = new ArrayList<SelectOptionDto>();

        SelectOptionDto dto1 = new SelectOptionDto();
        dto1.setId(DictCodeListExt1.是否_是.getValue());
        dto1.setText(DictCodeListExt1.是否_是.getCodeName());

        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setId(DictCodeListExt1.是否_否.getValue());
        dto2.setText(DictCodeListExt1.是否_否.getCodeName());

        listtype.add(dto1);
        listtype.add(dto2);

        return listtype;
    }

    /**
     * 债券代码联想下拉框
     *
     * @return 债券代码列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("querySecId")
    public Page<SelectOptionDto> querySecId() {
        Map<String,Object> map= RequestUtil.getPageParam();
        //测试暂时用理财产品
        map.put("finprodType", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return fxImixBondInfo001Service.querySecId(map);
    }

    /**
     * 查询
     *
     */
    @ResponseBody
    @RequestMapping("/selectForPage")
    public Page<FxImixBondInfoDto> selectForPage(){
        Page<FxImixBondInfoDto>  page = fxImixBondInfo001Service.selectForPage(RequestUtil.getPageParam());
        return page;
    }



}
