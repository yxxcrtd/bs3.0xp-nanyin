package com.joyintech.tams.report.submitmaintenance.agentinstitution.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.dto.AgentInstitutionDto;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.dto.AgentInstitutionUploadDto;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.dto.AgentUploadFileInfo;
import com.joyintech.tams.report.submitmaintenance.agentinstitution.service.AgentInstitution001Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("AgentInstitution003")
@RequestMapping("/report/submitmaintenance/agentinstitution/AgentInstitution003")
public class AgentInstitution003Controller extends BaseController {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AgentInstitution001Service agentInstitution001Service;
    /**
    *
    * {@inheritDoc}
    */
   @Override
   @RequestMapping(PAGE_INIT)
   public String init(Model model) {
       String dataArrStr = getParam("dataArrStr");
       if(StringUtil.isNotEmpty(dataArrStr)){
           model.addAttribute("dataArrStr", dataArrStr);
       }
       return super.init(model);
   }

    /**
     * 附件批量上传校验
     * @param
     * @return 处理结果
     * @throws IOException 异常信息
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody AgentInstitutionUploadDto dto) throws IOException {
        AjaxResult res=new AjaxResult();
        try {
            //所有被勾选数据的代销机构代码
            List<String> agentInstitutionCodeList=new ArrayList<String>();
            for(AgentInstitutionDto agentInstitutionDto:dto.getUploadArrStr()){
                agentInstitutionCodeList.add(agentInstitutionDto.getAgentInstitutionCode());
            }
            List<Map<String, Object>> fileList=new ArrayList<>();
            List<AgentUploadFileInfo> fileArray=new ArrayList<AgentUploadFileInfo>();
            //获取所有上传附件
            fileArray=dto.getFileArray();
            for(AgentUploadFileInfo file:fileArray){
                Map<String, Object> map=new HashMap<String, Object>();
                map.put("srcName", (String)file.getSrcName());
                map.put("fileNo", (String)file.getFileNo());
                map.put("targetFileSTR", (String)file.getTargetFile());
                map.put("agentInstitutionCodeList", agentInstitutionCodeList);
                fileList.add(map);
            }
            res=agentInstitution001Service.FileUploadBatch(fileList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new BusinessException(e);
        }
        return res;

    }
}
