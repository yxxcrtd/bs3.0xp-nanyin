package com.joyintech.service.pbc.classic.pbcreportfilemanage.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDataFile;
import com.joyintech.tams.svreport.pbc.classic.pbcreportfilemanage.service.PbcReportFileManageService;

/**
 * <p>Title: PbcReportFileManageController<／p>
 * <p>Description: 人行报送文件管理Controller<／p>
 * <p>Company: 安徽兆尹信息科技股份有限公司<／p> 
 * @author gaojian
 * @date 2019年6月12日
 */

@RestController
@RequestMapping("/PbcReportFileManage")
public class PbcReportFileManageController {
	/**
	 * 人行报送文件管理service
	 */
	@Autowired
	private PbcReportFileManageService pbcReportFileManageService;
	
	/**
	 * 生成文件标识码下拉框初始值
	 * @return 文件标识码下拉框初始值
	 */
	@PostMapping("getFileCode")
	public List<SelectOptionDto> getFileCode() {
		return pbcReportFileManageService.getFileCode();
	}
	
	/**
	 * 获取平台日期前一日 (用于生成日期初始化)
	 * @return 平台日期前一日
	 */
	@PostMapping("getYesterDayDate")
    public AjaxResult getYesterDayDate(){
        return pbcReportFileManageService.getYesterDayDate();
    }
	
	/**
     * 根据页面条件查询结果
     * @param param 页面参数
     * @return 根据页面条件查询结果
     */
	@PostMapping("findPage/{page}/{length}")
    public Page<PbcDataFile> findPage(@RequestBody Map<String, Object> param) {
        return pbcReportFileManageService.findPage(param);
    }
    
	/**
	 * 下载压缩文件
	 * @param param 页面参数
	 * @return 压缩文件
	 */
    @PostMapping("download")
    public ResponseEntity<byte[]> download(@RequestBody Map<String, Object> param) {
        return pbcReportFileManageService.download(param);
    }

    /**
     * 手工处理：批量处理业务表的发送状态
     * @param param
     * @throws IOException 
     */
    @PostMapping("doManul")
    public void doManul(@RequestBody Map<String, Object> param) throws IOException {
        pbcReportFileManageService.doManul(param);
    }
    
    /**
     * 检查选择的压缩文件是否已手工处理
     * @param param
     * @return 未手工处理的压缩包列表
     * @throws IOException
     */
    @PostMapping("checkFileSend")
    public List<PbcDataFile> checkFileSend(@RequestBody Map<String, Object> param) throws IOException {
        return pbcReportFileManageService.checkFileSend(param);
    }
}
