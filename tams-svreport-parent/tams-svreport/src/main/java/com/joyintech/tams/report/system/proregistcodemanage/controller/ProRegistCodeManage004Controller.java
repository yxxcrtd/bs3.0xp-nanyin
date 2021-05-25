package com.joyintech.tams.report.system.proregistcodemanage.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.report.system.proregistcodemanage.dto.ProRegistCodeManage001Dto;
import com.joyintech.tams.report.system.proregistcodemanage.service.ProRegistCodeManage001Service;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@PageId("ProRegistCodeManage004")
@RequestMapping("/svreport/system/proregistcodemanage/ProRegistCodeManage004")
public class ProRegistCodeManage004Controller extends BaseController {

    @Autowired
    private ProRegistCodeManage001Service proRegistCodeManage001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("checkAndSave")
    public AjaxResult checkAndSave(@RequestBody @Validated({
            ValidateGroupEdit.class}) ProRegistCodeManage001Dto dto, BindingResult result) {
        AjaxResult webResult = new AjaxResult();
        StringBuffer stringBuffer=new StringBuffer();
        //查询所有申报通过的产品,取出登记编码
        // 校验上传文件的文件名是否与该条数据记录对应的产品登记编码一致，若不一致则禁止上传，并给出提示“文件名与所选记录不匹配！上传失败！
        List<String> proCodes=proRegistCodeManage001Service.selectProCodeList();
        if(CollectionUtils.isNotEmpty(dto.getFileArray())){
            for(UploadFile uploadFile:dto.getFileArray()){
                //文件名称
                String filename=uploadFile.getSrcName().substring(0,uploadFile.getSrcName().indexOf("."));
                if(!proCodes.contains(filename)){
                    stringBuffer.append("产品登记编码"+filename+"在系统中不存在!上传失败");
                }
            }
            webResult.setMessage(stringBuffer.toString(), MessageType.错误消息);
            if(stringBuffer.length()!=0){
                return webResult;
            }else{
                for(UploadFile uploadFile:dto.getFileArray()){
                    //文件名称
                    String filename=uploadFile.getSrcName().substring(0,uploadFile.getSrcName().indexOf("."));
                    //删除已存在的数据重新插入
                    proRegistCodeManage001Service.deleteBySeqNo(filename);
                    //插入数据
                    SysUploadFileInfo sysUploadFileInfo=new SysUploadFileInfo();
                    sysUploadFileInfo.setSeqNo(filename);
                    sysUploadFileInfo.setFileName(uploadFile.getSrcName());
                    sysUploadFileInfo.setPath(uploadFile.getFileName());
                    sysUploadFileInfo.setCreateTime(new Date());
                    sysUploadFileInfo.setUpdateTime(new Date());
                    proRegistCodeManage001Service.insertSysUploadFileInfo(sysUploadFileInfo);
                }

            }
        }else{
            webResult.setMessage("请上传产品登记通知书备案.pdf");
        }
        webResult.setMessage("保存成功");
        return webResult;
    }
}
