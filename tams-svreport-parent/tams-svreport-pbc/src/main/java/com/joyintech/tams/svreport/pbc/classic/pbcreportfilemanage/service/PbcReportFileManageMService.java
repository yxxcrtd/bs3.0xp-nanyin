package com.joyintech.tams.svreport.pbc.classic.pbcreportfilemanage.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDataFile;
import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.service.PbcPrdAccountSubService001;
import com.joyintech.tams.svreport.pbc.classic.prdaccountendsub.service.PbcPrdAccountEndSubService001;
import com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service.PbcPrdAssetDebtSubService001;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.service.PbcPrdBaseinfoSubService001;
import com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.service.PbcPrdCashRaiseSubService001;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.serivce.PrdBaseinfoEndSubService001;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.service.PbcPrdInitRaiseSubService001;

/**
 * <p>Title: PbcReportFileManageMService<／p>
 * <p>Description: 人行报送文件管理微服务业务逻辑层<／p>
 * <p>Company: 安徽兆尹信息科技股份有限公司<／p> 
 * @author gaojian
 * @date 2019年6月17日
 */
@Service
public class PbcReportFileManageMService extends BaseService<PbcDataFile> {
	/**
	 * 生成文件标识码下拉框初始值
	 * 
	 * @return 文件标识码下拉框初始值
	 */
//	@SuppressWarnings("unchecked")
//	public List<SelectOptionDto> getFileCode() {
//		return restTemplate.postForObject(
//	               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcReportFileManage/getFileCode",null,
//	               List.class);
//	}
	
	/**
	 * 获取平台日期前一日 (用于生成日期初始化)
	 * @return 平台日期前一日
	 */
//	public AjaxResult getYesterDayDate(){
//		return restTemplate.postForObject(
//	               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcReportFileManage/getYesterDayDate",null,
//	               SvrAjaxResult.class);
//	}
	
	/**
	 * 根据页面传入条件查询结果
	 * 
	 * @param param 页面查询条件
	 * @return 查询结果
	 */
	public Page<PbcDataFile> findPage(Map<String, Object> param) {
		return restTemplate.postForPage(
	               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcReportFileManage/findPage",param,
	               PbcDataFile.class);
	}
	

	/**
	 * 手工处理：批量处理业务表的发送状态
	 * @param param
	 * @throws IOException 
	 */
	public void doManul(Map<String, Object> param) throws IOException {
		if (null == param.get("sendKeyList")) {
			return;
		}
		restTemplate.postForObject(SvrServiceApplication.PBC_SERVICE_NAME + "/PbcReportFileManage/doManul", param,
				void.class);
	}

	/**
	 * 检查选择的压缩文件是否已手工处理
	 * @param param
	 * @return 未手工处理的压缩包列表
	 */
	public List<PbcDataFile> checkFileSend(Map<String, Object> param) {
		return restTemplate.postForList(SvrServiceApplication.PBC_SERVICE_NAME + "/PbcReportFileManage/checkFileSend", param,
				PbcDataFile.class);
	}

}
