package com.joyintech.tams.svreport.pbc.classic.pbcreportfilemanage.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDataFile;
import com.joyintech.tams.svreport.pbc.classic.pbcreportfilemanage.service.PbcReportFileManageMService;
import com.joyintech.tams.svreport.pbc.classic.pbcreportfilemanage.service.PbcReportFileManageService;

/**
 * <p>Title: PbcReportFileManageController<／p>
 * <p>Description: 人行报送文件管理Controller<／p>
 * <p>Company: 安徽兆尹信息科技股份有限公司<／p> 
 * @author gaojian
 * @date 2019年6月12日
 */
@Controller
@PageId("PbcReportFileManage001")
@RequestMapping("/pbc/classic/pbcreportfilemanage/PbcReportFileManage001")
public class PbcReportFileManage001Controller extends BaseController{
	
	@Autowired
	private PbcReportFileManageMService pbcReportFileManageMService;
	
	@Autowired
	private PbcReportFileManageService pbcReportFileManageService;
	
	/**
	 * 页面初始化
	 */
	@Override
    @RequestMapping(PAGE_INIT)
	public String init(Model model) {
		return super.init(model);
	}
	
	/**
	 * 生成文件标识码下拉框初始值
	 * @return 文件标识码下拉框初始值
	 */
	@ResponseBody
	@RequestMapping("/getFileCode")
	public List<SelectOptionDto> getFileCode() {
		return pbcReportFileManageService.getFileCode();
	}
	
	/**
	 * 获取平台日期前一日 (用于生成日期初始化)
	 * @return 平台日期前一日
	 */
	@RequestMapping("/getYesterDayDate")
    @ResponseBody
    public AjaxResult getYesterDayDate(){
        return pbcReportFileManageService.getYesterDayDate();
    }
	
	/**
     * 根据页面条件查询结果
     * @return 根据页面条件查询结果
     */
    @ResponseBody
    @RequestMapping("/findPage")
    public Page<PbcDataFile> findPage() {
    	Map<String, Object> param = RequestUtil.getPageParam();
        return pbcReportFileManageMService.findPage(param);
    }
    
    /**
     * 检查选择的压缩文件是否存在
     * @return 不存在的文件清单
     */
    @ResponseBody
    @RequestMapping("/checkFileExists")
    public AjaxResult checkFileExists() {
    	Map<String, Object> param = RequestUtil.getPageParam();
        return pbcReportFileManageService.checkFileExists(param);
    }
    
    /**
     * 检查选择的压缩文件是否已手工处理
     * @return 
     */
    @ResponseBody
    @RequestMapping("/checkFileSend")
	public AjaxResult checkFileSend() {
		Map<String, Object> param = RequestUtil.getPageParam();
		AjaxResult result = new AjaxResult();
		List<PbcDataFile> unSendZipList = pbcReportFileManageMService.checkFileSend(param);
		if (!CollectionUtils.isEmpty(unSendZipList)) {
			result.setData(unSendZipList);
		}
		return result;
	}
    
    /**
     * 下载压缩文件
     * @return 压缩文件
     * @throws IOException 
     */
    @ResponseBody
    @RequestMapping("/download")
	public ResponseEntity<byte[]> download() throws IOException {
		Map<String, Object> param = RequestUtil.getPageParam();
		ResponseEntity<byte[]> responseEntity = pbcReportFileManageService.download(param);

		// 如果是手工报送，下载成功后修改对应业务表.报送状态=已报送。 zhangyf 20191107 start
		if (null != responseEntity && !StringUtils.isEmpty(param.get("isManually"))
				&& Boolean.parseBoolean((String) param.get("isManually"))) {
			// 修改对应业务表.报送状态
			pbcReportFileManageMService.doManul(param);
		}
		// 如果是手工报送，下载成功后修改对应业务表.报送状态=已报送。 zhangyf 20191107 end
		
		return responseEntity;
	}
    
}
