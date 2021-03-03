package com.joyintech.tams.svreport.chbsd.employeeinfo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chbsd.employeeinfo.dto.SdEmployeeInfoSvrDto;

@Repository
public interface SdEmployeeInfoSvrMapper {
	/**
	 * 查询从业人员sub表list
	 * @return
	 */
	public List<SdEmployeeInfoSvrDto> selcteSendingData();

}
