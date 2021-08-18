/*
 * 文件名：RskRelatedParty001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.namelistmanage.relatedparty.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.risk.namelistmanage.relatedparty.dto.RskRelatedPartyDto;
import com.joyintech.tams.invest.risk.namelistmanage.relatedparty.service.RskRelatedParty001Service;

/**
 * 关联方管理
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月14日
 * @see RskRelatedParty001Controller
 * @since 1.0
 */
@Controller
@PageId("RskRelatedParty001")
@RequestMapping("/invest/risk/namelistmanage/relatedparty/RskRelatedParty001")
public class RskRelatedParty001Controller extends BaseController {

    @Autowired
    private RskRelatedParty001Service relatedParty001Service;

    public Page<SuggestDto> getInstituteName() {
        Map<String, Object> map = new HashMap<>();
        String param = getParam("param");
        map.put("customerId", param);
        return null;
    }

    /**
     * 市场机构页面模糊查询 <功能详细描述>
     * 
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskRelatedPartyDto> findPage() {
        return relatedParty001Service.findPage(RequestUtil.getPageParam());
    }

    /**
     * 市场日历信息查询
     *
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("queryInstituteName")
    public Page<SuggestDto> queryInstituteName() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return relatedParty001Service.queryInstituteName(param);
    }

    /**
     * 市场日历信息查询
     *
     * @return 处理结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody RskRelatedPartyDto dto) {
        relatedParty001Service.delete(dto);
        return new AjaxResult(getMessage("COMMON_I_0011"));
    }

    /**
     * 导入
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     * @throws FileNotFoundException 异常
     */
    @ResponseBody
    @RequestMapping("importFile")
    public List<ExcelValidDto> importData(String fileName)
        throws FileNotFoundException {
        return relatedParty001Service.importData(fileName);
    }

    /**
     * 导出
     * 
     * @return 返回导出报错信息
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() {
        return relatedParty001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
}
