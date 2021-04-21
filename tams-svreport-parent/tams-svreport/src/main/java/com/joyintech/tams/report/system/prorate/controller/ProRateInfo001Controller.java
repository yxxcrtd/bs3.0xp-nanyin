package com.joyintech.tams.report.system.prorate.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.report.system.prorate.service.ProrateService;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@PageId("ProRateInfo001")
@RequestMapping("/svreport/system/prorate/ProRateInfo001")
public class ProRateInfo001Controller extends BaseController {

    @Autowired
    private ProrateService prorateService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 查询列表
     *
     * @return DtPage
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ProRateInfo001Dto> search() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return prorateService.searchForPage(param);
    }

    /**
     *
     * 产品代码下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getProdID")
    public List<SelectOptionDto> getProdID() {
        Map<String, Object> param = RequestUtil.getPageParam();
        List<ChbPrdBaseinfo> list = prorateService.getProdinfo(param);
        List<SelectOptionDto> resultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ChbPrdBaseinfo chbPrdBaseinfo : list) {
                SelectOptionDto resultDto = new SelectOptionDto();
                resultDto.setId(chbPrdBaseinfo.getProdId());
                resultDto.setText(chbPrdBaseinfo.getProdName());
                resultList.add(resultDto);
            }
        }
        return resultList;
    }

    /**
     *
     * 信披费用类型下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPrdFeeType")
    public List<SelectOptionDto> getPrdFeeType() {
        Map<String , Object> map =new HashMap<>();
        map.put("code", "DIS_PRD_FEE_TYPE_2");
        List<SysDataDict> list = prorateService.getSysDataDict(map);
        List<SelectOptionDto> resultList = new ArrayList<>();
        commenData(list,resultList);
        return resultList;
    }

    /**
     *
     * 数据状态下拉框取得值
     *
     * @return 下拉框值
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getDataStatus")
    public List<SelectOptionDto> getDataStatus() {
        Map<String , Object> map =new HashMap<>();
        map.put("code", "MAINTAIN_STATUS");
        List<SysDataDict> list = prorateService.getSysDataDict(map);
        List<SelectOptionDto> resultList = new ArrayList<>();
        commenData(list,resultList);
        return resultList;
    }

    public void commenData(List<SysDataDict> list,List<SelectOptionDto> resultList){
        if (CollectionUtils.isNotEmpty(list)) {
            for (SysDataDict sysDataDict : list) {
                SelectOptionDto resultDto = new SelectOptionDto();
                resultDto.setId(sysDataDict.getItemCode());
                resultDto.setText(sysDataDict.getItemValue());
                resultList.add(resultDto);
            }
        }
    }
}
