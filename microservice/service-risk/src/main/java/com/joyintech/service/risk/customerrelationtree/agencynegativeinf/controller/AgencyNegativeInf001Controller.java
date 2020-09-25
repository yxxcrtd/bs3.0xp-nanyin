/**
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019/12/9 0009
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.customerrelationtree.agencynegativeinf.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.customerrelationtree.agencynegativeinf.dto.AgencyNegativeInf001Dto;
import com.joyintech.service.risk.customerrelationtree.agencynegativeinf.service.AgencyNegativeInf001Service;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 机构负面信息查询
 * @author 汪海
 * @version 1.0 2019/12/9 0009
 * @see AgencyNegativeInf001Controller
 * @since 1.0
 */
@RestController("agencyNegativeInf001Ctl_dtx")
@RequestMapping("/AgencyNegativeInf001")
public class AgencyNegativeInf001Controller {

    @Autowired
    private AgencyNegativeInf001Service agencyNegativeInf001Service;

    /**
     * 查询机构负面信息
     * @return
     * @throws Exception
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<AgencyNegativeInf001Dto> selectForPage(@RequestBody Map<String, Object> param,
                                                       @PathVariable("page") int page,
                                                       @PathVariable("length") int length)
            throws Exception {
        return agencyNegativeInf001Service.queryForPage(page, length, param);
    }

    /**
     * 下拉选择中模糊查询机构代码和名称
     * @param param
     * @return
     */
    @PostMapping("fuzzyQueryOrgByIdOrName")
    public List<SelectOptionDto> fuzzyQueryOrgByIdOrName(@RequestBody Map<String, Object> param){
        List<SelectOptionDto> resultList = new ArrayList<>();
        Object orgStr = param.get("orgStr");
        if(orgStr==null){
            orgStr = "";
        }
        List<AgencyNegativeInf001Dto> dataList= agencyNegativeInf001Service.fuzzyQueryByOrgIdOrName(orgStr.toString());
        if (CollectionUtils.isNotEmpty(dataList)) {
            dataList.forEach(p -> {
                SelectOptionDto option = new SelectOptionDto();
                option.setId(p.getOrgId());
                option.setText(p.getOrgName());
                resultList.add(option);
            });
        }
        return resultList;
    }
}
