package com.joyintech.tams.report.system.instregbankcode.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode002Dto;

/**
 * 机构登记银行代码管理画面
 *
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see SysInstRegBankCodeMService
 * @since 1.0
 */
@Service
public class SysInstRegBankCodeMService extends BaseService<SysInstRegBankCode001Dto>{

    /**
     * 查询列表
     *
     * @return DtPage
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<SysInstRegBankCode001Dto> searchForPage(Map<String, Object> param) {
    	return restTemplate.postForPage(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/SysInstRegBankCode/findPage", param,
	       		SysInstRegBankCode001Dto.class);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName) {
		SvrAjaxResult svrAjaxResult = restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/SysInstRegBankCode/checkConfirmMsgForImportData",
				ExcelUtil.getDataList(fileName),
				SvrAjaxResult.class);

		if(!StringUtil.isEmpty(svrAjaxResult.getMessage())){
			// add by wj 20200826  TISHKBS-1013   用AjaxResult接收上面的微服务调用 会产生一个bug 关于枚举值 序列化的bug
			// 所以 后台传的是确认消息  到这边时 默认为了提示消息
			// 故暂时用一个取巧的方式修改此bug  如果message 不为空 则 转化为 确认消息
			svrAjaxResult.setMessageType(MessageType.确认消息);
		}
		return svrAjaxResult;
	}

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
    	return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/SysInstRegBankCode/importData", 
	       		ExcelUtil.getDataList(fileName),
	       		ExcelValidDto.class);
    }

    /**
     * 导出excel
     *
     * @return 导出
     */
    public List<SysInstRegBankCode001Dto> queryExportList(Map<String, Object> param) {
    	return restTemplate.postForList(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/SysInstRegBankCode/queryExportList", param,
	       		SysInstRegBankCode001Dto.class);
    }

    /**
     * 删除数据
     * <p>
     * 逻辑删除数据，并返回处理结果
     * </p>
     *
     * @return 处理结果
     */
    public void doDelete(String deleteKeys) {
    	restTemplate.postForObject(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/SysInstRegBankCode/doDelete", deleteKeys,
	       		Integer.class);
    }
    
    /**
     * 根据主键信息查询数据
     *
     * @param paramMap 参数map
     * @return 数据
     */
    public SysInstRegBankCode002Dto getInfoData(Map<String,Object> paramMap) {
    	return restTemplate.postForObject(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/SysInstRegBankCode/getInfoData", paramMap,
	       		SysInstRegBankCode002Dto.class);
    }
    
    /**
     * 保存数据
     *
     * @param inputDto dto
     * @return check结果
     * @see
     * @since 1.0
     */
     public void saveData(SysInstRegBankCode002Dto inputDto) {
        restTemplate.postForObject(
    	       	SvrServiceApplication.COMMON_SERVICE_NAME + "/SysInstRegBankCode/saveData", inputDto,
    	       	SysInstRegBankCode002Dto.class);	
     }

     
	public List<SuggestDto> getInstitutionCode(String param) {
     	return restTemplate.postForList(
    	       	SvrServiceApplication.COMMON_SERVICE_NAME + "/SysInstRegBankCode/getInstitutionCode", param,
    	       	SuggestDto.class);
     }

	public List<SuggestDto> getAllInstitutionCode(String param) {
		return restTemplate.postForList(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/SysInstRegBankCode/getAllInstitutionCode", param,
				SuggestDto.class);
	}
        
}
