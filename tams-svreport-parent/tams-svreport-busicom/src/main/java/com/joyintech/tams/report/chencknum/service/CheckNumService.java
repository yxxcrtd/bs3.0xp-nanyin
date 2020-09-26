package com.joyintech.tams.report.chencknum.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.chencknum.dao.CheckNumMapper;
import com.joyintech.tams.report.chencknum.dto.TotalAmountInfoDto;
import com.joyintech.tams.report.common.XmlParseUtil;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfo001Service;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfo001Service;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;


/**
 * 投资者信息维护Service
 * <p>
 * 投资者信息维护业务逻辑处理
 * </p>
 * 
 * @author 汪敏
 * @version 1.0 2017年9月16日
 * @see CheckNumService
 * @since 1.0
 */
@Service("checkNumService")
@Transactional(rollbackFor = Exception.class)
public class CheckNumService {
	/**
	 * 共通权益人信息mapper
	 */
	@Autowired
	private CheckNumMapper checkNumMapper;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * EquityInfoRegist001Service
	 */
	@Autowired
	private EquityInfo001Service equityInfo001Service;
	/**
	 * InvestorsHoldInfoRegist001Service
	 */
	@Autowired 
	private InvestorsHoldInfo001Service investorsHoldInfo001Service;

	public List<Map<String, String>> getCheckNumInterf(String fileName, String tmpPath) throws Exception {
		logger.info("文件解析开始...");
		List<Map<String, String>> equityList = XmlParseUtil.parse(new FileInputStream(new File(tmpPath + fileName)));
		for(Map<String, String> map:equityList){
			map.remove(null);
		}
		if (equityList == null) {
			return null;
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("statusListStr", DictCodeListExt2.报送数据状态_新建.getValue() + GlobalConstant.COMMA + DictCodeListExt2.报送数据状态_修改.getValue());
			List<EquityInfoRegist001Dto> needSendList = equityInfo001Service.getNeedSendList(param);
			
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("sendStatus", DictCodeListExt2.报送状态_未报送.getValue());
			map1.put("status", DictCodeListExt2.报送数据状态_新建.getValue() + GlobalConstant.COMMA + DictCodeListExt2.报送数据状态_修改.getValue());
			List<InvestorsHoldInfoRegist001Dto> needSendHoldList = investorsHoldInfo001Service.selectAllList2(map1);
			
			for (Map<String, String> map : equityList) {
				TotalAmountInfoDto totalAmountInfoDto = new TotalAmountInfoDto();
				totalAmountInfoDto.setReportDate(map.get("REPORT_DATE"));
				totalAmountInfoDto.setInterfaceType(map.get("INTERFACE_TYPE"));
				totalAmountInfoDto.setFileName(map.get("FILE_NAME"));
				totalAmountInfoDto.setFileAmount(map.get("FILE_AMOUNT"));
				// 销售报送文件数目入库
				List<TotalAmountInfoDto> totalAmountInfoDtoList = checkNumMapper.queryTotalAmountInfo(totalAmountInfoDto);
				if (totalAmountInfoDtoList.size() != 0) {
					TotalAmountInfoDto totalAmountInfoDto1 = totalAmountInfoDtoList.get(0);
					if (StringUtils.isNotEmpty(totalAmountInfoDto1.getFileCount())
							&& totalAmountInfoDto1.getFileCount().equals(map.get("FILE_AMOUNT"))
							&& StringUtils.isNotEmpty(totalAmountInfoDto1.getFileInData())
							&& totalAmountInfoDto1.getFileInData().equals(map.get("FILE_AMOUNT"))) {
						map.put("HALD_FLAG", "00");
						map.put("IS_EQUAL", "1");
						totalAmountInfoDto1.setHaldFlag("00");
						totalAmountInfoDto1.setIsEqual("1");
					} else {
						map.put("HALD_FLAG", "00");
						map.put("IS_EQUAL", "2");
						totalAmountInfoDto1.setHaldFlag("00");
						totalAmountInfoDto1.setIsEqual("2");
					}
					if(StringUtils.isNotEmpty(totalAmountInfoDto1.getFileInData())){
						map.put("SUCCESS_AMOUNT", totalAmountInfoDto1.getFileInData());
					}else{
						map.put("SUCCESS_AMOUNT", "0");
					}
					totalAmountInfoDto1.setFileAmount(totalAmountInfoDto.getFileAmount());
					totalAmountInfoDto1.setUpdateTime(new Date());
					if("1".equals(map.get("INTERFACE_TYPE"))){
						totalAmountInfoDto1.setAmount(needSendList.size() + "");
					}else if("2".equals(map.get("INTERFACE_TYPE"))){
						totalAmountInfoDto1.setAmount(needSendHoldList.size() + "");
					}
					checkNumMapper.updateTotalAmountInfo(totalAmountInfoDto1);
				} else {
					map.put("HALD_FLAG", "11");
					map.put("IS_EQUAL", "2");
					map.put("SUCCESS_AMOUNT", "0");
					totalAmountInfoDto.setHaldFlag("11");
					totalAmountInfoDto.setIsEqual("2");
					totalAmountInfoDto.setCreateTime(new Date());
					if(StringUtils.isEmpty(totalAmountInfoDto.getId())){
						totalAmountInfoDto.setId(UUID.randomUUID().toString());
					}
					checkNumMapper.insertTotalAmountInfo(totalAmountInfoDto);
				}
			}
		}
		return equityList;
	}
}
