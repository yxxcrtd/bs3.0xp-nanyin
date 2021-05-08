/*
 * 文件名：InvestorsHoldInfoRegist001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.chb.assetinfodetail.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbAssetInfoErrodataMapper;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dao.AssetInfoDetailMapper;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 资产基本信息维护Service
 */
@Service(value="svrAssetInfoDetailService")
@Transactional(rollbackFor = Exception.class)
public class AssetInfoDetail001Service extends CommonExportService<AssetInfoDetailDto> {

	/**
	 * 资产要素登记mapper
	 */
	@Autowired
	private AssetInfoDetailMapper assetInfoDetailMapper;

	@Autowired
	private ChbAssetInfoErrodataMapper chbAssetInfoErrodataMapper;



    private Logger logger = LoggerFactory.getLogger(getClass());


	/**
	 * 查询页面数据
	 *
	 * @param pageParam
	 *            查询条件
	 * @return 结果列表
	 * @see
	 * @since 1.6.0
	 */
	public Page<AssetInfoDetailDto> searchForPage(Map<String, Object> pageParam) {
        Page<AssetInfoDetailDto> page;
        //资产资产编号
		String assetCode = (String)pageParam.get("assetCode");
		if(StringUtil.isNotEmpty(assetCode)){
			pageParam.put("assetCodes", StringUtil.splitToList(GlobalConstant.COMMA,assetCode));
		}

		//资产/负债类别
		String assetType = (String)pageParam.get("assetType");
		if(StringUtil.isNotEmpty(assetType)){
			pageParam.put("assetTypes", StringUtil.splitToList(GlobalConstant.COMMA,assetType));
		}

		//资产/负债类别二级
		String assetType2 = (String)pageParam.get("assetType2");
		if(StringUtil.isNotEmpty(assetType2)){
			pageParam.put("assetType2s", StringUtil.splitToList(GlobalConstant.COMMA,assetType2));
		}

		//资产一级分类
		String orgAssetType1 = (String)pageParam.get("orgAssetType1");
		if(StringUtil.isNotEmpty(orgAssetType1)){
			pageParam.put("orgAssetType1s", StringUtil.splitToList(GlobalConstant.COMMA,orgAssetType1));
		}

		//资产二级分类
		String orgAssetType2 = (String)pageParam.get("orgAssetType2");
		if(StringUtil.isNotEmpty(orgAssetType2)){
			pageParam.put("orgAssetType2s", StringUtil.splitToList(GlobalConstant.COMMA,orgAssetType2));
		}


		page = queryForPage(AssetInfoDetailMapper.class,"selectChbInfoByParam",pageParam);
        return page;
	}

	/**
	 * 数据赋值 marge模式
	 * @param dto
	 * @return
	 */
	public Integer doSave(AssetInfoDetailDto dto){
		int  flag = 0;
		List<ChbAssetInfo> assetInfo = new ArrayList<>();
		//数据保存.使用marge模式.避免出现老数据仍然出现的问题
		if(StringUtil.isNotEmpty(dto)){
			assetInfo.add(dto);
			AssetInfoDetailDto assetInfoDetailDto = assetInfoDetailMapper.queryAssetInfo(dto.getAssetCode());
			if (StringUtil.isNotEmpty(assetInfoDetailDto)){
				flag = assetInfoDetailMapper.updateBatch(assetInfo);
			}else {
				flag = assetInfoDetailMapper.insertBatch(assetInfo);
			}
		}
		/**
		 * 如果是待处理数据,则将错误信息表里面的数据删除
		 */
		if(DictCodeListExt3.中债_处理状态_待处理.getValue().equals(dto.getDataStatus())){
			assetInfoDetailMapper.deleteErrorMsg(dto.getAssetCode());

		}

		return flag;
	}


	/**
	 * 数据删除
	 */
	public Integer doDelete(AssetInfoDetailDto dto){
		int  flag = 0;

		/***如果是资产基本信息,则修改状态为D*/
		if(DictCodeListExt3.中债_处理状态_已处理.getValue().equals(dto.getDataStatus())){
			assetInfoDetailMapper.updateChbAssetType(dto.getAssetUuid());
		}
		/**
		 * 如果是待处理数据,则将错误信息表里面的数据删除
		 */
		if(DictCodeListExt3.中债_处理状态_待处理.getValue().equals(dto.getDataStatus())){
			assetInfoDetailMapper.deleteError(dto.getAssetUuid());
		}

		return flag;
	}

	/**
	 * 根据uuid获取资产信息
	 * @param UUid
	 * @return
	 */
	public AssetInfoDetailDto getAssetInfoByUUid(String UUid){
		return assetInfoDetailMapper.getAssetInfoDetail(UUid);
	}

	/**
	 * 导入文件之前检查文件中的数据在数据库中是否已经存在 如果已经存在，需要提示用户进行确认是否覆盖数据库
	 *
	 * @return 检查结果
	 * @see
	 * @since 1.0
	 */
	public AjaxResult checkConfirmMsgForImportData(List<AssetInfoDetailDto> dataList, String orgCode, String deptCode) {
		AjaxResult webResult = new AjaxResult();
		if (!StringUtil.isEmptyList(dataList)){
			if (CollectionUtils.isNotEmpty(dataList)) {
//				    List assetCodes = dataList.stream().map(assetInfoDetailDto -> assetInfoDetailDto.getAssetCode()).collect(Collectors.toList());
//				    Map<String,Object> map = new HashMap<>();
//				    map.put("assetCodes",assetCodes);
//				    List<AssetInfoDetailDto> data = assetInfoDetailMapper.selectChbInfoByParam(map);
//					Map<String, List<AssetInfoDetailDto>> assetInfoMap = dataList.stream().collect(
//							JoyinCollectors.groupingBy(
//									x -> x.getAssetCode()));
//					 Collections.sort(data, Comparator.comparing(AssetInfoDetailDto::getAssetCode));
//					// 将序号放入list中
					List<String> excelNumList = new ArrayList<String>();
//					List<Integer> excelNumIntList = new ArrayList<Integer>();
//					for (AssetInfoDetailDto dto : data) {
//						List<AssetInfoDetailDto> listTemp = assetInfoMap.get(dto);
//                        excelNumList.add(listTemp.get(0).getAssetCode());
//					}
					webResult.setMessage(
							this.getMessage("CHB_ISCOVER_EQU",
									StringUtil.joinString(excelNumList, GlobalConstant.COMMA)),
							MessageType.确认消息);
				}
				else {
					// 防止在进行导入前check时进行了两次的弹框
					webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
				}
			}
			else{
				// 防止在进行导入前check时进行了两次的弹框
				webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
			}
		return webResult;
	}
	public List<ExcelValidDto> importData(List<AssetInfoDetailDto> dataList){
		List<ExcelValidDto> checkResult = new ArrayList<>();
		List<ChbAssetInfo> assetInfo = new ArrayList<>();
		assetInfo.addAll(dataList);
		if(!StringUtil.isEmptyList(dataList)){
			if(CollectionUtils.isNotEmpty(dataList)){
				assetInfoDetailMapper.insertBatch(assetInfo);
				return checkResult;
			}
		}
		checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0),
				this.getMessage("REPORT_COMMON_FILE_E_0016")));
		return       checkResult;
	}

}
