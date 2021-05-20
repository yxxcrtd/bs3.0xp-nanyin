package com.joyintech.tams.svreport.chb.perprodeclarereg.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import com.joyintech.tams.svreport.chb.perprodeclarereg.service.PerProDeclareReg001Service;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

@Controller
@PageId("SvrPerProDeclareReg002")
@RequestMapping("/chb/perprodeclarereg/PerProDeclareReg002")
public class PerProDeclareReg002Controller extends BaseController {

    @Autowired
    private PerProDeclareReg001Service perProDeclareReg001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("checkAndSave")
    public AjaxResult checkAndSave(@RequestBody @Validated({
            ValidateGroupEdit.class}) PerProDeclareRegDto dto, BindingResult result) {
        AjaxResult webResult = new AjaxResult();
        StringBuffer stringBuffer=new StringBuffer();
        //取出上传的文件名称
        List<String> fileNames =new ArrayList<>();
        if(CollectionUtils.isNotEmpty(dto.getFileArray())){
            for(UploadFile uploadFile:dto.getFileArray()){
                //文件名称
                String filename=uploadFile.getSrcName().substring(0,uploadFile.getSrcName().indexOf("."));
                fileNames.add(filename);
            }
        }
        //查询产品申报登记chb_prd_declare_sub 表,报送状态:未报送 数据状态:新建 产品代码in(filename)
        List<PerProDeclareRegDto> dtoList =perProDeclareReg001Service.queryPrdDeclareList(fileNames);
        Map<String,String> regCodeMap=new HashMap<String, String>();
        //取出产品申报的产品代码
        if(dtoList.size()>0){
            for(PerProDeclareRegDto po:dtoList){
                regCodeMap.put(po.getProdId(),po.getRegistSeqno());
            }
        }
        if(CollectionUtils.isNotEmpty(dto.getFileArray())){
            for(UploadFile uploadFile:dto.getFileArray()){
                //文件名称
                String filename=uploadFile.getSrcName().substring(0,uploadFile.getSrcName().indexOf("."));
                if(!regCodeMap.containsKey(filename)){
                    stringBuffer.append("文件"+filename+"无法匹配到申报登记数据,上传失败;");
                }
            }
            webResult.setMessage(stringBuffer.toString());
            if(stringBuffer.length()!=0){
                return webResult;
            }else{
                for(UploadFile uploadFile:dto.getFileArray()){
                    //文件名称
                    String filename=uploadFile.getSrcName().substring(0,uploadFile.getSrcName().indexOf("."));
                    String FILE_NO = IDGenerator.generate(32);
                    perProDeclareReg001Service.updateChbPrdDeclare(FILE_NO,regCodeMap.get(filename));
                    //删除已存在的数据重新插入
//                    perProDeclareReg001Service.deleteBySeqNo(FILE_NO);
                    //插入数据
                    SysUploadFileInfo sysUploadFileInfo=new SysUploadFileInfo();
                    sysUploadFileInfo.setSeqNo(FILE_NO);
                    sysUploadFileInfo.setFileName(uploadFile.getSrcName());
                    sysUploadFileInfo.setPath(uploadFile.getFileName());
                    sysUploadFileInfo.setCreateTime(new Date());
                    sysUploadFileInfo.setUpdateTime(new Date());
                    perProDeclareReg001Service.insertSysUploadFileInfo(sysUploadFileInfo);
                }

            }
        }else{
            webResult.setMessage("请上传.zip文件");
        }
        webResult.setMessage("保存成功");
        return webResult;
    }

}
