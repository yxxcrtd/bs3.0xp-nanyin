package com.joyintech.service.chb.assetinfodetail.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetail001Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 资产要素登记服务
 *
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see AssetInfoDetailController
 * @since 1.0
 */
@RestController
@RequestMapping("/AssetInfoDetail")
public class AssetInfoDetailController {

	/**
	 * 资产要素service
	 */
    @Autowired
    private AssetInfoDetail001Service assetInfoDetail001Service;
    
    @SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());
    
	
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
    @PostMapping("findPage/{page}/{length}")
	public Page<AssetInfoDetailDto> searchForPage(@RequestBody Map<String, Object> param) {
		return assetInfoDetail001Service.searchForPage(param);
	}

	/**
	 * 数据维护
	 * @return
	 */
	@PostMapping("doSave")
	public Integer doSave(@RequestBody AssetInfoDetailDto dto) {
		return assetInfoDetail001Service.doSave(dto);
	}

	/**
	 * 数据维护
	 * @return
	 */
	@PostMapping("doDelete")
	public Integer doDelete(@RequestBody AssetInfoDetailDto dto) {
		return assetInfoDetail001Service.doDelete(dto);
	}

	@SuppressWarnings("unchecked")
	@PostMapping("checkConfirmMsgForImportData")
	public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param)  {
		List<AssetInfoDetailDto> dataList = (List<AssetInfoDetailDto>) param.get("dataList");
		String orgCode=param.get("orgCode")==null?null:param.get("orgCode").toString();
		String deptCode=param.get("deptCode")==null?null:param.get("deptCode").toString();
		return assetInfoDetail001Service.checkConfirmMsgForImportData(dataList,orgCode,deptCode);
	}

	@SuppressWarnings("unchecked")
	@PostMapping("importData")
	public List<ExcelValidDto> importData(@RequestBody List<AssetInfoDetailDto> dataList ){
		return this.assetInfoDetail001Service.importData(dataList);
	}

}
