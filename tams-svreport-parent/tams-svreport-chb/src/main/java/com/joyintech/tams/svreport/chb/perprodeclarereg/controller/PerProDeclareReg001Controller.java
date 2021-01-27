package com.joyintech.tams.svreport.chb.perprodeclarereg.controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.base.dao.SysUploadFileInfoMapper;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.system.common.listener.UploadListener;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import com.joyintech.tams.svreport.chb.perprodeclarereg.service.PerProDeclareReg001Service;
import com.joyintech.tams.svreport.chb.perprodeclarereg.service.PerProDeclareRegMService;
import com.joyintech.tams.svreport.chbsd.prddeclaresub.service.SdProDeclareBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.joyintech.component.svrreport.chb.dto.ChbPrdDeclareSubDto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@PageId("SvrPerProDeclareReg001")
@RequestMapping("/chb/perprodeclarereg/PerProDeclareReg001")
public class PerProDeclareReg001Controller extends BaseController{
	@Autowired
	private PerProDeclareReg001Service perProDeclareReg001Service;
	@Autowired
	private PerProDeclareRegMService perProDeclareRegMService;
	@SuppressWarnings("unused")
    @Autowired
	private UploadListener  uploadListener;

	@Autowired
	private SdProDeclareBatchService sdProDeclareBatchService;

	@Autowired
	private SysUploadFileInfoMapper sysUploadFileInfoMapper;
	/**
	 * 日志
	 */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 页面初始化
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
    	Map<String, Object> map = RequestUtil.getPageParam();
		if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
			model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
			if (StringUtil.isNotEmpty(map.get("status"))) {
				model.addAttribute("status", map.get("status").toString().trim());
			}
			if(StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))){
            	model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
            }
			model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
			model.addAttribute("isMain", "01");
		}
        super.setSelectOptionToModel(model, 
        		DictCode.报送状态,
        		DictCode.报送反馈状态,
        		DictCode.报送数据状态,
        		DictCode.产品运作模式);
        
        return super.init(model);
    }
    
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   @ResponseBody
   @RequestMapping("findPage")
   public Page<PerProDeclareRegDto> selectAllData() {
       Page<PerProDeclareRegDto> page = perProDeclareRegMService.selectForPage(RequestUtil.getPageParam());
       return page;
   }
   
   @ResponseBody
   @RequestMapping("export")
   public ResponseEntity<byte[]> export() {
   	Map<String, Object> pageParam = RequestUtil.getPageParam();
   	if(pageParam.containsKey("keyList")){
         List<PerProDeclareRegDto> dtoList = perProDeclareRegMService.selectAllList(RequestUtil.getParameters(getRequest()));
         File outFile = perProDeclareReg001Service.makeFile(
             "/com/joyintech/tams/svreport/chb/perprodeclarereg/conf/export_conf.xml", dtoList);
         return ExportUtil.export(
             this.getMessage(this.getPageId()) + GlobalConstant.OPEN_PARENTHESIS
                                  + DateUtil.format(new Date()) + GlobalConstant.CLOSE_PARENTHESIS
                                  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
             outFile, true);
   	}
   	else{
           return perProDeclareReg001Service.export(this.getPageId(), RequestUtil.getPageParam());
   	}

   }
   
   
   
   @ResponseBody
	@RequestMapping("doManual")
	public AjaxResult doUpdate(@RequestBody Map<String, Object> map) {
	   perProDeclareRegMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
   
   
   /**
	 * 取消手工处理功能
	 *
	 * @return 提示信息
	 * @param map
	 *            条件map
	 * @seea
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelMannul", method = RequestMethod.POST)
	public AjaxResult cancelMannul(@RequestBody Map<String, Object> map) {
		String result = perProDeclareRegMService.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	@ResponseBody
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public AjaxResult uploadFile(@RequestParam Map<String, Object> map) throws IOException {
		AjaxResult res=new AjaxResult();
		try {
            res =perProDeclareReg001Service.uploadFile(map);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
			throw e;
        }
		return res;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	public AjaxResult downloadFile(@RequestParam Map<String, Object> map)  {
//	   System.out.println(map);
	    //perProDeclareReg001Service.downloadFile(map);
		return new AjaxResult("下载成功");
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deltFile", method = RequestMethod.POST)
	public AjaxResult deltFile(@RequestParam Map<String, Object> map)  {
	    
	    perProDeclareReg001Service.deltFile(map);
		return new AjaxResult("删除成功");
	}
	
	@ResponseBody
	@RequestMapping(value = "/getFileList", method = RequestMethod.POST)
	public String getFileList(@RequestParam Map<String, Object> map)  {
		String res;
        try {
            res = perProDeclareReg001Service.getFileList(map);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
		return res;
		
	}
	
	/**start  中债一二期直连代码  add  by  ***  20201009*/
	/***
	 * 报送按钮的检查
	 * @return
	 */
    @ResponseBody
    @RequestMapping("submitBeforeCheck")
    public Map<String,Object> submitBeforeCheck() {
    	 Map<String, Object> param = RequestUtil.getPageParam();
    	 return  perProDeclareRegMService.submitBeforeCheck(param);
    }

    /**
     * 中债直连报送
     *
     * @param map
     *            传入需要报送的主键
     * @return 反馈信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(value = "/submitChb", method = RequestMethod.POST)
    public AjaxResult doSubmit(@RequestBody Map<String, Object> param) throws Exception {

		List<PerProDeclareRegDto> proDeclareList=new ArrayList<PerProDeclareRegDto>();
		// 选择行数据时
		if (param.containsKey("keyList")) {
			String keyList = param.get("keyList").toString();
			try {
				proDeclareList = JsonUtils.json2List(keyList,
						PerProDeclareRegDto.class);
			}
			catch (IOException e) {
				// e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}else{
			Page<PerProDeclareRegDto> page = perProDeclareReg001Service.selectForPage(RequestUtil.getPageParam(), false);
			if(page!=null&&page.getRoot()!=null&&page.getRoot().size()>0){
				proDeclareList = page.getRoot();
			}
			//查询数据
		}

		//报文拼接：2、进行数据结构转化，把[1]中取得的List，转化为报文拼接组件中调用方法的数据结构类
		List<ChbPrdDeclareSubDto> dataList = new ArrayList<ChbPrdDeclareSubDto>();
		for(PerProDeclareRegDto sdDto:proDeclareList){
			ChbPrdDeclareSubDto target = new ChbPrdDeclareSubDto();
			BeanUtil.copyPropertiesIgnoreNull(target, sdDto);

			// add by zhangliang 20201016 产品申报登记发送接口需要把九大附件的路径传送进去 start
			// 实际申报登记实际生产上每日数据量不大，此处暂不考虑性能影响
			if (StringUtil.isNotEmpty(sdDto.getFileNo())){
				SysUploadFileInfo sysUploadFileInfo = sysUploadFileInfoMapper.selectByPrimaryKey(sdDto.getFileNo());
				if (sysUploadFileInfo != null){
					target.setAttachFilePath(sysUploadFileInfo.getPath());
				}
			}
			// add by zhangliang 20201016 产品申报登记发送接口需要把九大附件的路径传送进去 end
			target.setProdStage(sdDto.getProdStage() != null ? sdDto.getProdStage().intValue() : null);

			dataList.add(target);
		}

		sdProDeclareBatchService.sendBatchCommon(dataList);

        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0018"));
    }

	/**end  中债一二期直连代码  add  by  ***  20201009*/

	/**
	 *  查询行内标识码
	 * @param param 入参
	 * @return 行内标识码列表
	 */
	@ResponseBody
	@RequestMapping("queryProdInsIdList")
	public List<SelectOptionDto> queryProdInsIdList(){
		return perProDeclareRegMService.queryProdInsIdList(RequestUtil.getPageParam());
	}
}
