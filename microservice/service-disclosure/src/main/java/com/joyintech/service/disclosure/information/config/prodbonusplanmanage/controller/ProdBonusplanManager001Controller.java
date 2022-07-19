/*
 * 文件名：ReportTemplate001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.disclosure.information.config.prodbonusplanmanage.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.service.disclosure.information.config.prodbonusplanmanage.service.ProdBonusplanManager001Service;
import com.joyintech.service.disclosure.information.config.prodbonusplanmanage.service.ProdBonusplanManager002Service;
import com.joyintech.service.disclosure.information.config.proelementadj.dto.ProElementAdjDto;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 报表模板管理 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author lishicheng
 * @version 1.0 2018年11月9日
 * @see ProdBonusplanManager001Controller
 * @since 1.0
 */
@RestController(value = "serviceProdBonusplanManager001Controller")
@RequestMapping("/ProdBonusplanManager001")
public class ProdBonusplanManager001Controller extends BaseController {

    @Autowired
    private ProdBonusplanManager001Service prodBonusplanManager001Service;
    @Autowired
    private ProdBonusplanManager002Service prodBonusplanManager002Service;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager001Controller.class);


    @PostMapping("/findAll")
    public List<ProdBonusplanDto> findAll(@RequestBody Map<String, Object> param) {
//	        return valAssetsFundQuery001Service.findPage(RequestUtil.getPageParam());
        return prodBonusplanManager001Service.searchReportsNew(param);
    }


    @SuppressWarnings("finally")
    @RequestMapping("/searchReports")
    @ResponseBody
    public AjaxResult searchReports() {
        AjaxResult value = new AjaxResult();
        try {
            List<ProdBonusplanDto> result = prodBonusplanManager001Service
                    .searchReportsNew(RequestUtil.getPageParam());
            value.setData(result);
        } catch (Exception e) {
            value.setMessage("查询报表模板:" + e.getMessage(), MessageType.错误消息);
            logger.error("error:", e);
        } finally {
            //return value;
        }
        return value;
    }

    /**
     * 左侧栏查询 <一句话功能简述> <功能详细描述>
     *
     * @return
     * @throws Exception
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ProdBonusplanDto> findPage(@RequestBody Map<String, Object> param,
                                           @PathVariable("page") int page, @PathVariable("length") int length) {
        Page<ProdBonusplanDto> page1 = prodBonusplanManager001Service.findByPage(page, length, param);
        return page1;
    }

    @PostMapping("/delete")
    public void delete(@RequestBody ProdBonusplanDto entity) {
       /* String ids = entity.getBonusPlanId();
        List<String> idList = new ArrayList();
        if (StringUtils.isNotEmpty(ids)) {
            if (StringUtils.contains(ids, ",")) {
                idList = Arrays.asList(ids.split(",")).stream().collect(Collectors.toList());
            } else {
                idList.add(ids);
            }
        }
        idList.forEach(i -> {
            ProdBonusplanDto dto = new ProdBonusplanDto();
            dto.setBonusPlanId(i);
            dto.setDataStatus(DictCodeListExt3.信披_数据状态_删除.getValue());
            prodBonusplanManager002Service.update(dto);
        });*/

        String bonusPlanId = entity.getBonusPlanId();
        List<String> idList = new ArrayList();
        if (StringUtils.isNotEmpty(bonusPlanId)) {
            ProdBonusplanDto dto = new ProdBonusplanDto();
            dto.setBonusPlanId(bonusPlanId);
            dto.setDataStatus(DictCodeListExt3.信披_数据状态_删除.getValue());
            prodBonusplanManager002Service.update(dto);
        }
    }



    @PostMapping("/selectByProdId")
    public ProdBonusplanDto selectByProdId(@RequestBody String prodId) {
        return prodBonusplanManager001Service.selectByProdId(prodId);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody ProdBonusplanDto dto) {
        String type = dto.getOperationType();
        if (StringUtil.equals(type, "NEW")) {
            prodBonusplanManager002Service.insert(dto);
        }
        else {
            prodBonusplanManager002Service.update(dto);
        }
    }




}
