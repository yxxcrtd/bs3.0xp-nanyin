/**
 * 文件名：PbcClassicsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenxc
 * 修改时间：2019年6月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.svreport.pbc.classic.pbcNewDat.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.pbc.classic.pbcNewDat.dao.PbcNewPrdDatFileMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dto.PbcPrdBaseinfoSubNew;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.dto.PbcPrdBaseinfoEndSubNew;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 *人行单产品保本基本公共类 
 * @author chenxc
 * @version 1.0 2019年6月10日
 * @see PbcDatUtil
 * @since 1.0
 */
@Component
public class PbcDatUtil {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(PbcDatUtil.class);

	/**
	 *
	 * @param zipType  是否压缩	1:分配不生成压缩文件； 2：生成压缩文件
	 * @param zipModel 压缩方式	1:按照业务日期进行压缩；  2：整体压缩
	 * @param prdList
	 * @param endList
	 * @return  prdList = List<产品基本信息DTO>
	 *          endList = List<产品终止信息DTO>
	 *          zipList = List<新人行报送文件管理表DTO>
	 */
    public static Map<String, ArrayList> genDatZipList(String zipType, String zipModel,
												List<PbcPrdBaseinfoSubNew> prdList, List<PbcPrdBaseinfoEndSubNew> endList){
		Map<String,ArrayList> resultMap = new HashMap<>();

		/**1、根据入参.zipModel,分别进行以下处理：*/
		Map<Date,List<PbcPrdBaseinfoSubNew>> baseMap = new HashMap<>();
		Map<Date,List<PbcPrdBaseinfoEndSubNew>> endMap = new HashMap<>();
		/*****1.1、入参.zipModel= 		1:按照业务日期进行压缩
		 按日期分配后的.list<产品基本信息DTO>	同业务日期  产品基本信息  --->  产品起始日期
		 按日期分配后的.list<产品终止信息DTO>	同业务日期	产品终止信息   --->  产品实际终止日期
		 */
		if(DictCodeListExt3.是否压缩_按照业务日期.getValue().equals(zipModel)){
			baseMap = prdList.stream().collect(Collectors.groupingBy(PbcPrdBaseinfoSubNew::getVdate));
			endMap = endList.stream().collect(Collectors.groupingBy(PbcPrdBaseinfoEndSubNew::getMdate));
		}

			/*****2、进行数据压缩处理（对同一业务日期的数据进行压缩）**/

			Map<String,String> dataSearch = new HashMap<>();
			dataSearch.put("zipType", DictCodeListExt2.人行报送类型_产品信息.getValue());
			dataSearch.put("sendStatus",DictCodeListExt2.报送状态_未报送.getValue());
			dataSearch.put("zipStatus",DictCodeListExt3.zip包状态_已使用.getValue());

		for(Map.Entry<Date,List<PbcPrdBaseinfoSubNew>> baseList :baseMap.entrySet()){
			dataSearch.put("statusDate", DateUtil.format(baseList.getKey()));
			int  num = 0;
			/***2.1、查询是否存在【可编辑】的压缩文件**/
					PbcNewPrdDatFileMapper pbcNewPrdDatFileMapper = SpringContextUtil.getBean(PbcNewPrdDatFileMapper.class);
					String zipCode = pbcNewPrdDatFileMapper.getMaxZipCode(dataSearch);
					if(StringUtil.isNotEmpty(zipCode)){

					}

				}


		return resultMap;
	}



	public static void  main(String args[] ) {
//		genDatZipList();
	}
}
