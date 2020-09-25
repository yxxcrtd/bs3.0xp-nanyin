/**
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019/12/9 0009
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.customerrelationtree.agencynegativeinf.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.system.sysdate.controller.SysDate001Controller;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.risk.customerrelationtree.agencynegativeinf.dto.AgencyNegativeInf001Dto;
import com.joyintech.tams.invest.risk.customerrelationtree.agencynegativeinf.service.AgencyNegativeInf001Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 机构负面信息查询
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019/12/9 0009
 * @see AgencyNegativeInf001Controller
 * @since 1.0
 */
@Controller
@PageId("AgencyNegativeInf001")
@RequestMapping("/invest/risk/customerrelationtree/agencynegativeinf/AgencyNegativeInf001")
public class AgencyNegativeInf001Controller extends BaseController {

    @Autowired(required = false)
    private PlatformDate platformDate;      //平台日

    @Autowired
    private AgencyNegativeInf001Service agencyNegativeInf001Service;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.客户性质);   //对象类型
        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType")) && StringUtil.equals(BussinessDictCodeList.提醒类型_小分类_舆情提醒.getValue(), map.get("remType"))){
            model.addAttribute("orgId", map.get("orgId"));
            model.addAttribute("remType", map.get("remType"));
            if(StringUtil.isNotEmpty(map.getOrDefault("quaryDate","").toString())){
                model.addAttribute("queryDate",DateUtil.addDay(map.get("quaryDate").toString(),0));
            }
        }else{
            model.addAttribute("queryDate", new SimpleDateFormat("yyyy-MM-dd").format(platformDate.getSysDate()));  //平台日
        }
        return super.init(model);
    }

    /**
     * 查询机构负面信息
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public Page<AgencyNegativeInf001Dto> selectForPage() throws Exception {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        String objTypes = map.get("objTypes").toString();
        if(objTypes != null && objTypes.length()>0){
            map.put("objTypes", Arrays.asList(objTypes.split(",")));
        }else{
            map.put("objTypes", null);
        }
        Page<AgencyNegativeInf001Dto> page =  agencyNegativeInf001Service.selectForPage(map);
        return page;
    }

    /**
     * 下拉选择机构
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/fuzzyQueryOrgByIdOrName")
    public List<SelectOptionDto> fuzzyQueryOrgByIdOrName() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> pageMap = RequestUtil.getPageParam();
        Object search = pageMap.get("search");
        if (search != null) {
            paramMap.put("orgStr", StringUtils.trim(search.toString()));
        }
        return agencyNegativeInf001Service.fuzzyQueryOrgByIdOrName(paramMap);
    }

}
