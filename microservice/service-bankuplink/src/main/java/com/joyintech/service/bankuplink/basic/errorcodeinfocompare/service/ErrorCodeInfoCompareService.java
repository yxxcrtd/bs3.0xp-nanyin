package com.joyintech.service.bankuplink.basic.errorcodeinfocompare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.background.component.common.pack.common.base.BaseService;
import com.joyintech.background.component.common.pack.common.grid.Page;
import com.joyintech.background.component.uplink.interbank.business.dto.IbuIntfErrMsgDto;
import com.joyintech.service.bankuplink.basic.errorcodeinfocompare.dto.ErrorCodeInfoCompareDto;
import com.joyintech.service.bankuplink.ibu.service.IbuComponentCommonService;

@Service
public class ErrorCodeInfoCompareService extends BaseService{
	
    /**
     * 共通组件Service
     */
    @Autowired
    private IbuComponentCommonService ibuComponentCommonService;
	
	/**
	 * 错误码信息查询 分页查询
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public List<ErrorCodeInfoCompareDto> queryForList(Map<String, Object> pageParam) throws Exception {
		
		List<ErrorCodeInfoCompareDto> dtoList = new ArrayList<>();
        //查询条件转换
		List<IbuIntfErrMsgDto> list = ibuComponentCommonService.queryForList(pageParam);
		list.forEach(x -> {
			ErrorCodeInfoCompareDto errorCodeInfoCompareDto = new ErrorCodeInfoCompareDto();
			errorCodeInfoCompareDto.setErrCode(x.getErrCode());//错误码
			errorCodeInfoCompareDto.setErrMsg(x.getErrMsg());//错误信息
			
			dtoList.add(errorCodeInfoCompareDto);
         });
		
		return dtoList;
	}

	/**
	 * 错误码信息查询 双击查看
	 * @param errCode
	 * @return
	 */
	public ErrorCodeInfoCompareDto selectByErrCode(String errCode) {
		
		ErrorCodeInfoCompareDto dto = new ErrorCodeInfoCompareDto();
		//查询条件转换
		IbuIntfErrMsgDto dto1 = ibuComponentCommonService.selectByErrCode(errCode);
		dto.setErrCode(dto1.getErrCode());
		dto.setErrMsg(dto1.getErrMsg());
		
		return dto;
	}
	
	/**
	 * 错误码信息查询 分页查询
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<IbuIntfErrMsgDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
		return ibuComponentCommonService.selectForPage(page,length,pageParam);
		}

}
