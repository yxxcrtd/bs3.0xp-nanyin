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

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.pbc.classic.pbcNewDat.dao.PbcNewPrdDatFileMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dao.PbcPrdBaseinfoSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dto.PbcPrdBaseinfoSubNew;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dao.PbcPrdBaseinfoEndSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.dto.PbcPrdBaseinfoEndSubNew;
import com.joyintech.tams.systemtools.util.CommonUtil;
import groovy.util.MapEntry;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
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
	 * @param zipModel 压缩方式	1:按照业务日期进行压缩； 2：整体压缩
	 * @param srcModel 调用来源是否批处理   01：是批处理调用   ； 02：报送文件管理页面调用
	 * @param prdList
	 * @param endList
	 * @return  prdList = List<产品基本信息DTO>
	 *          endList = List<产品终止信息DTO>
	 *          zipList = List<新人行报送文件管理表DTO>
	 */
    public static Map<String, ArrayList> genDatZipList(String zipType, String zipModel,String srcModel,String OrgCode,
												List<PbcPrdBaseinfoSubDat> prdList, List<PbcPrdBaseinfoEndSubDat> endList) throws UnsupportedEncodingException {
		Map<String,ArrayList> resultMap = new HashMap<>();
		float maxsize = 1024*1024*Float.parseFloat(CacheUtil.getSysParam("ZIP_DATA_FILEMAXSIZE"));

		/**1、根据入参.zipModel,分别进行以下处理：*/
		Map<Date,List<PbcPrdBaseinfoSubDat>> baseMap = new HashMap<>();
		Map<Date,List<PbcPrdBaseinfoEndSubDat>> endMap = new HashMap<>();
		Set<Date> dateSet = new HashSet<>();

		/*****1.1、入参.zipModel= 		1:按照业务日期进行压缩
		 按日期分配后的.list<产品基本信息DTO>	同业务日期  产品基本信息  --->  产品起始日期
		 按日期分配后的.list<产品终止信息DTO>	同业务日期	产品终止信息   --->  产品实际终止日期
		 */
		if(DictCodeListExt3.是否压缩_按照业务日期.getValue().equals(zipModel)){
			baseMap = prdList.stream().collect(Collectors.groupingBy(PbcPrdBaseinfoSubDat::getVdate));
			endMap = endList.stream().collect(Collectors.groupingBy(PbcPrdBaseinfoEndSubDat::getMdate));
			//获取所有开始终止的业务日期
			dateSet.addAll(baseMap.keySet());
			dateSet.addAll(endMap.keySet());
		}else{
			//1.2、【1.1】以外的情况下，调用2的处理   默认传入当前日期
			Date date = new Date();
			dateSet.add(date);
			baseMap.put(date,prdList);
			endMap.put(date,endList);
		}
		/*****2、进行数据压缩处理（对同一业务日期的数据进行压缩）**/

		Map<String,Object> dataSearch = new HashMap<>();
		dataSearch.put("zipType", DictCodeListExt2.人行报送类型_产品信息.getValue());
		dataSearch.put("sendStatus",DictCodeListExt2.报送状态_未报送.getValue());
		dataSearch.put("zipStatus",DictCodeListExt3.zip包状态_已使用.getValue());
		//获取mapper
		PbcNewPrdDatFileMapper pbcNewPrdDatFileMapper = SpringContextUtil.getBean(PbcNewPrdDatFileMapper.class);
		PbcPrdBaseinfoSubDatMapper pbcPrdBaseinfoSubDatMapper = SpringContextUtil.getBean(PbcPrdBaseinfoSubDatMapper.class);
		PbcPrdBaseinfoEndSubDatMapper pbcPrdBaseinfoEndSubDatMapper = SpringContextUtil.getBean(PbcPrdBaseinfoEndSubDatMapper.class);

		//循环日期-如果有
		for(Date date :dateSet){
			int  num = 1;
			List<PbcPrdBaseinfoSubDat> prdDatList = baseMap.get(date);
			List<PbcPrdBaseinfoEndSubDat> prdEndDatList = endMap.get(date);
				//※、当入参.srcModel = '02：报送文件管理页面调用',进行【2.1、2.2】的处理，否则直接开始 【2.3】的处理
				if(DictCodeList.是否_中债_否.getValue().equals(srcModel)){
					dataSearch.put("statusDate", DateUtil.format(date));
					/***2.1、查询是否存在【可编辑】的压缩文件 ,并且取出最大的编号**/
					String zipCode = pbcNewPrdDatFileMapper.getMaxZipCode(dataSearch);
					if(StringUtil.isNotEmpty(zipCode)){
						//上面取出的.最大的压缩编号的
						//上面取出的.最大的压缩编号的.3位置顺序号
						num = Integer.parseInt(zipCode.substring(zipCode.length()-3,zipCode.length()));

						/****2、2 取得在DAT表中，已经分配了zip号的记录**/
						dataSearch.put("zipCode",zipCode);
						//2.2.1、取得项目 产品基本信息
						prdDatList.addAll(pbcPrdBaseinfoSubDatMapper.searchPage(dataSearch));
						//2.2.2、取得项目 产品基本信息
						prdEndDatList.addAll(pbcPrdBaseinfoEndSubDatMapper.searchPage(dataSearch));
					}
				}


			//根据产品代码将数据重新分组  合并数据以传入的为主
			Map<String,PbcPrdBaseinfoSubDat> prodDatList = null!=prdDatList?prdDatList.stream().collect(Collectors.toMap(p->p.getProdId(), p-> p,(key1,key2)->key1)):new HashMap<>();
			Map<String,PbcPrdBaseinfoEndSubDat> prodEndDatList = null!=prdEndDatList?prdEndDatList.stream().collect(Collectors.toMap(p->p.getProdId(), p-> p,(key1,key2)->key1)):new HashMap<>();

			Map<String,Map<String,Object>> samePrd = new HashMap();
			//2.3、对数据进行分组
			if(prodDatList.entrySet().containsAll(prodEndDatList.keySet())){
				//以产品基本信息进行遍历
				for(Map.Entry<String,PbcPrdBaseinfoSubDat> set : prodDatList.entrySet()){
					//产品基本信息与终止信息同时有数据
					if(null!=prodEndDatList.get(set.getKey())){
						Map<String,Object> reuslt = new HashMap<>();
						//获取基本信息
						reuslt.put("prod",set.getValue());
						//获取终止信息
						reuslt.put("end",prodEndDatList.get(set.getKey()));
						//复制到samePrd中
						//2.3.1产品基本信息、终止信息有在一起的数据
						samePrd.put(set.getKey(),reuslt);

						//删除数据
						//2.3.2、只有产品基本信息的  去除放在【2.3.1】中的数据，其它产品基本信息、 保留在原变量中
						//2.3.3、只有产品终止信息的  去除放在【2.3.1】中的数据，其它产品终止信息、 保留在原变量中
						prodEndDatList.remove(prodEndDatList.get(set.getKey()));
						prodDatList.remove(set);
					}
				}
			}

			int count = 0;
			String zipFileCode = genZipCode(DictCodeListExt2.人行报送类型_产品信息.getValue(),date,num,OrgCode);
			//同组数据优先处理
			if(null!=samePrd){
				for(Map.Entry<String,Map<String,Object>> dto : samePrd.entrySet()){
					count +=buildResult(resultMap,(PbcPrdBaseinfoSubDat)dto.getValue().get("prod"),(PbcPrdBaseinfoEndSubDat)dto.getValue().get("end"),zipFileCode,DateUtil.format(date));
					//重置数据并且重新生成zipFileCode
					if(count>=maxsize){
						reBuildDat(count,num,zipFileCode,date,OrgCode,resultMap,true);
					}
				}
			}
			//产品基本信息
			if(null!=prodDatList){
				for(Map.Entry<String,PbcPrdBaseinfoSubDat> dto : prodDatList.entrySet()){
					count +=buildResult(resultMap,(PbcPrdBaseinfoSubDat)dto.getValue(),null,zipFileCode,DateUtil.format(date));
					//重置数据并且重新生成zipFileCode
					if(count>=maxsize){
						reBuildDat(count,num,zipFileCode,date,OrgCode,resultMap,true);

					}
				}
			}

			//产品终止信息遍历
			if(null!=prodEndDatList){
				for(Map.Entry<String,PbcPrdBaseinfoEndSubDat> dto : prodEndDatList.entrySet()){
					count +=buildResult(resultMap,null,(PbcPrdBaseinfoEndSubDat)dto.getValue(),zipFileCode,DateUtil.format(date));
					//重置数据并且重新生成zipFileCode
					if(count>=maxsize){
						reBuildDat(count,num,zipFileCode,date,OrgCode,resultMap,true);

					}
				}
			}
			reBuildDat(count,num,zipFileCode,date,OrgCode,resultMap,false);
		}


		return resultMap;
	}



	public static void reBuildDat(int count,int maxCode,String zipFileCode,Date date,String OrgCode,Map<String,ArrayList> resultMap,boolean isend){
    	//重置数据
		count = 0;
		maxCode++;
		zipFileCode = genZipCode(DictCodeListExt2.人行报送类型_产品信息.getValue(),date,maxCode,OrgCode);

		List<PbcSendZipDatFile> result = (List<PbcSendZipDatFile>)resultMap.get("zipList");
		//获取当前数组中所有可编辑数据
		List<PbcSendZipDatFile> doList = result.stream().filter(i-> DictCodeListExt3.zip包状态_可编辑.getValue().equals(i.getDatZipStatus())).collect(Collectors.toList());
		//将所有数据进行最后处理
		for(PbcSendZipDatFile dataFile: doList){
			//填入数据
			dataFile.setPibDetailCnt(doList.size());
			dataFile.setOrgCode(OrgCode);
			//是否是最后数据进行修改
			if(isend){
				dataFile.setDatZipStatus(DictCodeListExt3.zip包状态_已使用.getValue());
			}
		}

	}

	/**
	 * 获取zip文件
	 * @param date 执行日期
	 * @param zipNum 顺序吗
	 * @param orgCode 机构代码
	 * @return
	 */
	public static String genZipCode(String pCode,Date date,int zipNum,String orgCode){
		//PI+14位金融机构法人标准代码+1位产品品种代码+ yyyyMMdd（业务日期）+变量.3位顺序号.zip
		return pCode+ "1" + CommonUtil.selectPbcBankCode(orgCode) + DateUtil.format(date,"yyyyMMdd") + StringUtils.leftPad(zipNum+"", 3, "0");
	}
	/**
	 * 数据组装
	 * @param resultMap 返回信息
	 * @param prodDat 产品基本信息
	 * @param prodEndDat 产品终止信息
	 * @param zipCode zip编号
	 * @param Date 日期字段
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static int buildResult(Map<String, ArrayList> resultMap,
								  PbcPrdBaseinfoSubDat prodDat, PbcPrdBaseinfoEndSubDat prodEndDat, String zipCode, String Date) throws UnsupportedEncodingException {
		//初始化zip文件
		if(null==resultMap.get("zipList")){
			resultMap.put("zipList",new ArrayList());
		}
		int size = 0;
		//产品基本信息压入
		if(null!=prodDat){
			PbcSendZipDatFile zipFile = new PbcSendZipDatFile();
			zipFile.setDatZipCode(zipCode);
			zipFile.setDatZipDatType(DictCodeListExt2.人行报送明细类型_产品基本信息.getValue());
			zipFile.setDatZipType(DictCodeListExt2.人行报送类型_产品信息.getValue());
			zipFile.setDatZipStatus(DictCodeListExt3.zip包状态_可编辑.getValue());
			zipFile.setStatusVdate(Date);
			zipFile.setZipSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
			size = prodDat.toPbcString("|").getBytes("GBK").length;
			if(null==resultMap.get("prdList")){
				resultMap.put("prdList",new ArrayList());
			}
			//补充数据
			resultMap.get("prdList").add(prodDat);
			resultMap.get("zipList").add(zipFile);
		}
		//产品终止信息压入
		if(null!=prodEndDat) {
			PbcSendZipDatFile zipFile = new PbcSendZipDatFile();
			zipFile.setDatZipCode(zipCode);
			zipFile.setDatZipDatType(DictCodeListExt2.人行报送明细类型_产品终止信息.getValue());
			zipFile.setDatZipType(DictCodeListExt2.人行报送类型_产品信息.getValue());
			zipFile.setDatZipStatus(DictCodeListExt3.zip包状态_可编辑.getValue());
			zipFile.setStatusVdate(Date);
			zipFile.setZipSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
			size += prodEndDat.toPbcString("|").getBytes("GBK").length;
			if(null==resultMap.get("endList")){
				resultMap.put("endList",new ArrayList());
			}
			resultMap.get("endList").add(prodEndDat);
			resultMap.get("zipList").add(zipFile);
		}
		return size;
	}

	public static void  main(String args[] ) {
		Set<Date> date = new HashSet<>();
		date.add(DateUtil.parse("2021-05-03"));
		date.add(DateUtil.parse("2021-05-04"));

		Set<Date> date1 = new HashSet<>();
		date.add(DateUtil.parse("2021-05-03"));

		date.addAll(date1);
	}
}
