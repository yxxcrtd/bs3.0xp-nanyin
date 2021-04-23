package com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.dto.ErrorCodeInfoCompare001Dto;
import com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.dto.IbuIntfErrMsgDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

@Service
public class ErrorCodeInfoCompare001Service extends CommonExportService<ErrorCodeInfoCompare001Dto>{
	
	/**
	 * 错误码信息查询 查询
	 * @param pageParam
	 * @return
	 */
	public List<ErrorCodeInfoCompare001Dto> queryForList(Map<String, Object> pageParam) {
		
        pageParam.put("loginUser", SecurityUtil.getLoginUser().getUserName());
        
        return restTemplate.postForList(
        		ServiceApplication.BANKUPLINK_SERVICE_NAME + "/ErrorCodeInfoCompare/queryForList",
        		pageParam,ErrorCodeInfoCompare001Dto.class);
    }
	
	/**
	 * 错误码信息查询 导出(buildExportParam)
	 */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/bankuplink/basic/errorcodeinfocompare/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * 错误码信息查询 导出(selectPageData)
     */
    @Override
    public Page<ErrorCodeInfoCompare001Dto> selectPageData(Map<String, Object> selectParam) {
        List<ErrorCodeInfoCompare001Dto> resList = queryForList(selectParam);
        return new Page<>(resList);
    }

    /**
     * 错误码信息查询 双击查看
     * @param errCode
     * @return
     */
	public ErrorCodeInfoCompare001Dto selectByErrCode(String errCode) {
		return restTemplate.getForObject(
        		ServiceApplication.BANKUPLINK_SERVICE_NAME + "/ErrorCodeInfoCompare/selectByErrCode?errCode="+
		errCode,ErrorCodeInfoCompare001Dto.class);
	}

	/**
	 * 
	 * @param pageParam
	 * @return
	 */
	public Page<IbuIntfErrMsgDto> selectForPage(Map<String, Object> pageParam) {
		pageParam.put("loginUser", SecurityUtil.getLoginUser().getUserName());
		return restTemplate.postForPage(
	        		ServiceApplication.BANKUPLINK_SERVICE_NAME + "/ErrorCodeInfoCompare/selectForPage",
	        		pageParam,IbuIntfErrMsgDto.class);
	}

}
