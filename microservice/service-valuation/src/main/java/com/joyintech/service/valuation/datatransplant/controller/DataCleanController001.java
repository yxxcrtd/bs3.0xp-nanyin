/*
 * 文件名：DataCleanController001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.datatransplant.controller;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.datatransplant.dto.DataCleanParameterDto;
import com.joyintech.service.valuation.datatransplant.service.DataCleanService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 数据移植 - 数据清空
 * 
 * @version 1.0 2018年11月9日
 * @see DataCleanController001
 * @since 1.0
 */
//@RestController(value="serviceDataCleanController001")
@RestController("dataCleanControllerCtl_dtx")
@RequestMapping("/valuation/datatransplant/DataClean001")
public class DataCleanController001 extends BaseController {

    @Autowired
    private ValAssetBook001Service valAssetBook001Service;

    @Autowired
    private DataCleanService dataCleanService;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // 数据移植 - 模版类型
        this.setSelectOptionToModel(model, DictCode.数据移植模版类型);

        // 所属账套
        model.addAttribute("FINPRODID_LIST", JsonUtils.objectArray2Json(valAssetBook001Service.getBooksetIdForSelect().toArray()));

        return super.init(model);
    }

    /**
     * 移植数据清空
     * 
     * @param dataCleanParameterDto 删除条件参数
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping(value = "/deleteDate")
    @ResponseBody
    public AjaxResult deleteDate(DataCleanParameterDto dataCleanParameterDto) {
        // 校验条件【账套代码】不能为空
        if (dataCleanParameterDto == null || dataCleanParameterDto.getBooksetIdArr() == null || dataCleanParameterDto.getBooksetIdArr().length == 0) {
            return new AjaxResult("请选择所属账套", MessageType.警告消息);
        }

        // 校验条件【模版类型】不能为空
        if (dataCleanParameterDto == null || dataCleanParameterDto.getModelType() == null || dataCleanParameterDto.getModelType().trim().length() == 0) {
            return new AjaxResult("请选择模版类型", MessageType.警告消息);
        }

        // 校验条件【删除日期】不能为空
        if (dataCleanParameterDto == null || dataCleanParameterDto.getDeleteDateBegin() == null || dataCleanParameterDto.getDeleteDateEnd() == null) {
            return new AjaxResult("请选择删除日期", MessageType.警告消息);
        }

        // 插入创建用户和更新用户标识，用于删除
        dataCleanParameterDto.setCreateUser(GlobalConstant.数据移植账户);
        dataCleanParameterDto.setUpdatUser(GlobalConstant.数据移植账户);

        // 根据模版类型，进行对应表删除
        String modelType = dataCleanParameterDto.getModelType();
        if (DictCodeListExt1.数据移植模版类型_头寸模版.getValue().equals(modelType)) {
            dataCleanService.deleteForPosition(dataCleanParameterDto);
        }
        else if (DictCodeListExt1.数据移植模版类型_增值税模版.getValue().equals(modelType)) {
            dataCleanService.delteForValueAddedTax(dataCleanParameterDto);
        }
        else if (DictCodeListExt1.数据移植模版类型_账务余额模版.getValue().equals(modelType)
//        		||DictCodeListExt1.数据移植模版类型_账务余额在途债券模版.getValue().equals(modelType)
        		) {
            dataCleanService.deleteForAccountsBlance(dataCleanParameterDto);
        }
        else if(DictCodeListExt1.数据移植模版类型_增值税_90号文模版.getValue().equals(modelType)){
        	dataCleanService.delteForValueAddedTax90(dataCleanParameterDto);
        }

        return new AjaxResult("删除成功", MessageType.提示消息);
    }

}
