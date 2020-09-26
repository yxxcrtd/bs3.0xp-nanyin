/*
 * 文件名：FileUpload002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.fileupload.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joyintech.framework.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.fileupload.service.DataFileUpload002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 文件上传
 * 
 * @author zhangwei
 * @version 1.0 2018年11月14日
 * @see FileUpload002Controller
 * @since 1.0
 */
@Controller
@PageId("DataFileUpload002")
@RequestMapping("/valuation/fileupload/DataFileUpload002")
public class DataFileUpload002Controller extends BaseController {

    @Autowired
    DataFileUpload002Service dataFileUpload002Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.文件类型);
        return super.init(model);
    }

    /**
     * 上传验证
     * @param dto
     * @param result
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check() throws Exception {
        String msg = StringUtils.EMPTY;
        
        String finprodId = StringUtils.EMPTY; //  产品代码
        String valDateString = null; // 估值日期
        String fileType = getParam("fileType"); // 文件类型

        // 上传文件格式是否正确
        Boolean fileNameIsRight = true;
        AjaxResult ar = new AjaxResult();

        try {
            // 1. 验证文件命名是否规范： 估值表_A130F0003_20170706.xls
            String fileNameOnly = getParam("fileNameOnly"); // 文件名
            String fileName = fileNameOnly.substring(0, fileNameOnly.lastIndexOf(".")); // 去后缀文件名

//            finprodId = fileName.split("_")[1]; //  产品代码
//            valDateString = fileName.split("_")[2]; // 估值日期

            // 根据【文件标识_产品代码_估值日期】格式：将文件名用“_”转化为数组
            String[] nameArr = fileName.split("_");
            // 根据【文件标识_产品代码_估值日期】格式：估值日期 = 数组最后一个元素
            valDateString = nameArr[nameArr.length - 1];
            // 根据【文件标识_产品代码_估值日期】格式：产品代码 = “首元素”和“最后一个元素”之间的元素组合
            for(int index = 0 ; index < nameArr.length; index ++){
                if(index==0 || index == nameArr.length - 1){
                    continue;
                }

                //组建产品代码
                if(index == 1){
                    finprodId = nameArr[index];
                }else{
                    finprodId = finprodId + "_" + nameArr[index];
                }

            }

            //当日期和产品代码为空，抛出异常
            if(valDateString == null || valDateString.trim().length()==0 || finprodId==null || finprodId.trim().length()==0){
                // 文件格式不正确
                fileNameIsRight = false;
            }

            // 判断时间是否正确
            if(DateUtil.parse(valDateString) == null){
                // 文件格式不正确
                fileNameIsRight = false;
            }

        }catch (Exception e) {
            // 文件格式不正确
            fileNameIsRight = false;
        }
        finally {
            // 当文件格式不正确给予提示
            if(!fileNameIsRight){
                String fileTypeName ="";
                if(DictCodeList.文件类型_余额表.getValue().equals(fileType)
                        || DictCodeList.文件类型_余额表对账文件.getValue().equals(fileType)){
                    fileTypeName ="余额表_";
                }
                else if(DictCodeList.文件类型_估值表.getValue().equals(fileType)
                        || DictCodeList.文件类型_估值表对账文件.getValue().equals(fileType)){
                    fileTypeName ="估值表_";
                }
                else{
                    fileTypeName ="文件类型_";
                }

                msg = "上传失败，请检查文件名！<br/> 示例："+fileTypeName+"DK2LK180001_20181118";
                ar = new AjaxResult(msg, MessageType.错误消息);
            }
        }
        
        // 2. 验证对账文件是否重复
        String valBalFlag = StringUtils.EMPTY; // 01 估值，02 余额
        if(DictCodeList.文件类型_估值表.getValue().equals(fileType) 
            || DictCodeList.文件类型_估值表对账文件.getValue().equals(fileType) ) {
            valBalFlag = "01";
        }
        if(DictCodeList.文件类型_余额表.getValue().equals(fileType) 
            || DictCodeList.文件类型_余额表对账文件.getValue().equals(fileType) ) {
            valBalFlag = "02";
        }
        Map<String, Object> map = Maps.newHashMap();
        map.put("fileType", fileType);
        map.put("valBalFlag", valBalFlag);
        map.put("finprodId", finprodId);
        map.put("valDateString", valDateString);

        int count = dataFileUpload002Service.countByCondition(map);
        if(count > 0) {
            msg = "当前文件已存在，请删除已存在记录再上传！";
            return new AjaxResult(msg, MessageType.错误消息);
        }
        
        return ar;
    }
    
    /**
     * 文件上传
     *
     * @param request 请求
     * @param response 响应
     * @param model ModelAndView
     * @return 保存文件信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws Exception {
        AjaxResult res = new AjaxResult();
        String fileType = request.getParameter("fileType");
        if (StringUtils.isNotEmpty(fileType)) {
            try {
                dataFileUpload002Service.readFile(request);
                int num = dataFileUpload002Service.insertFileTransferInfo(request);
                if (num > 0) {
                    res = new AjaxResult(getMessage("VAL_EXCELIMP_E_0022"), MessageType.提示消息);
                }
                else {
                    String msg = "上传信息入库失败";
                    res = new AjaxResult(msg, MessageType.错误消息);
                }
            }
            catch (Exception e) {
                String msg = "上传失败：" + e.getMessage();
                res = new AjaxResult(msg, MessageType.错误消息);
                return res;
            }
        }
        else {
            String msg = "文档类型不能为空";
            res = new AjaxResult(msg, MessageType.错误消息);
            return res;
        }
        return res;
    }

}
