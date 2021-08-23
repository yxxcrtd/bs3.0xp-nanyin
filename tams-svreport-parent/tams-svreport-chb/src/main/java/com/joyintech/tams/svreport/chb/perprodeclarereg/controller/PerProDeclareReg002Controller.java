package com.joyintech.tams.svreport.chb.perprodeclarereg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProdDeclareregDto;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.UploadFileInfo;
import com.joyintech.tams.svreport.chb.perprodeclarereg.service.PerProDeclareReg001Service;
import com.joyintech.framework.base.BaseController;

/**
 * 九大附件批量上传
 * 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2021年05月18日
 * @see PerProDeclareReg002Controller
 * @since 1.0
 */

@Controller
@PageId("SvrPerProDeclareReg002")
@RequestMapping("/report/chb/perprodeclarereg/PerProDeclareReg002")
public class PerProDeclareReg002Controller extends BaseController{
	@Autowired
	private PerProDeclareReg001Service perProDeclareReg001Service;
	/**
	 * 日志
	 */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        String dataArrStr = getParam("dataArrStr");
        if(StringUtil.isNotEmpty(dataArrStr)){
            model.addAttribute("dataArrStr", dataArrStr);
        }
        return super.init(model);
    }
	
	/**
	 * 九大附件批量上传校验
	 * @param map  入参
	 * @return 处理结果
	 * @throws IOException 异常信息
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public AjaxResult save(@RequestBody PerProdDeclareregDto dto) throws IOException {
		AjaxResult res=new AjaxResult();
		try {
			//所有被勾选数据的产品代码
			List<String> prodList=new ArrayList<String>();
			for(PerProDeclareRegDto perProDeclareRegDto:dto.getUploadArrStr()){
				prodList.add(perProDeclareRegDto.getProdId());
			}
			List<Map<String, Object>> fileList=new ArrayList<>();
			List<UploadFileInfo> fileArray=new ArrayList<UploadFileInfo>();
			//获取所有上传附件
			fileArray=dto.getFileArray();
			for(UploadFileInfo file:fileArray){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("srcName", (String)file.getSrcName());
				map.put("fileNo", (String)file.getFileNo());
				map.put("targetFileSTR", (String)file.getTargetFile());
				map.put("prodList", prodList);
				fileList.add(map);
			}
			res=perProDeclareReg001Service.nineFileUploadBatch(fileList);
			
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
			throw new BusinessException(e);
        }
		return res;
		
	}

}
