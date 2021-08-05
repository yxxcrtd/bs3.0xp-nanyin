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

import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.system.institution.dto.SysInstitutionDto;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportAssetDebtDetailDto;
import com.joyintech.tams.svreport.pbc.assetposition.constants.PbcAssetPositionConstants;
import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDataFile;
import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDataFileDetail;
import com.joyintech.tams.svreport.pbc.classic.data.dto.PbcDatfileDetailRelation;
import com.joyintech.tams.svreport.pbc.classic.data.service.PbcClassicsDataFileHelper;
import com.joyintech.tams.svreport.pbc.classic.pbcNewDat.dao.PbcNewPrdDatFileMapper;
import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dto.PbcPrdAccountSub;
import com.joyintech.tams.svreport.pbc.classic.prdaccountendsub.dto.PbcPrdAccountEndSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dao.PbcPrdBaseinfoSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dao.PbcPrdBaseinfoSubNewMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dto.PbcPrdBaseinfoSubNew;
import com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.dto.PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dto.PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dao.PbcPrdBaseinfoEndSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.dao.PbcPrdBaseinfoEndSubNewMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubnew.dto.PbcPrdBaseinfoEndSubNew;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dto.PbcInitProdRaisingSub;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.common.util.SftpUtil;
import com.joyintech.tams.svreport.pbc.report.dto.PbcLoanDetailInfoSub;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2019年6月10日
 * @see PbcNewPrdDatFileService
 * @since 1.0
 */
@Service
public class PbcNewPrdDatFileService {


	@Autowired
	private PbcPrdBaseinfoSubNewMapper pbcPrdBaseinfoSubNewMapper;
	@Autowired
	private PbcPrdBaseinfoEndSubNewMapper pbcPrdBaseinfoEndSubNewMapper;

	@Autowired
	private PbcPrdBaseinfoSubDatMapper pbcPrdBaseinfoSubDatMapper;
	@Autowired
	private PbcPrdBaseinfoEndSubDatMapper pbcPrdBaseinfoEndSubDatMapper;

	@Autowired
	private PbcNewPrdDatFileMapper pbcNewPrdDatFileMapper;

    /**
     * 生成数据文件
     * @param reportType，报表类型，如果为空，则生成所有
     * @param listProds，产品列表，如果为空，则生成所有
     * @param genDate，生成日期，如果为空，则为当前平台时间
     * @see
     * @since 1.0
     */
    public void genDataFile(String reportType,  List<String> listProds,Date genDate, Map<String, Object> param,String OrgCode) throws UnsupportedEncodingException {
		String sendStatus = DictCodeListExt2.报送状态_未报送.getValue();
		Map<String,Object> searchParam = new HashMap<>();
		searchParam.put("sendStatusList",Arrays.asList(sendStatus));
		searchParam.put("prodIdList",listProds);
    	/****1、产品基本信息处理*/
    	List<PbcPrdBaseinfoSubDat> prdList = getPrdBaseinfoList(searchParam);
    	/***2、取得产品终止信息待处理数据*/
		List<PbcPrdBaseinfoEndSubDat> endList = getPrdEndList(searchParam);


		/**3、调用共通处理【DAT生成共通处理-产品端】**/
		Map<String, ArrayList> resultMap = PbcDatUtil.genDatZipList(DictCodeListExt3.是否压缩_不生成压缩文件.getValue(),"1", DictCodeList.是否_中债_否.getValue(),OrgCode,
				prdList,endList);

		/***4、数据入库*/
			/***5.1、清除DAT表中的未报送数据*/
			deleteDatData(listProds,OrgCode);
			/***5.2、文件管理表入库*/
			if(null!=resultMap.get("zipList")){
				pbcNewPrdDatFileMapper.insertBatchZipData(resultMap.get("zipList"));
			}
			/**5.2、报送信息入库*/
			if(null!=resultMap.get("prdList")){
				pbcPrdBaseinfoSubDatMapper.insertBatch(resultMap.get("prdList"));
			}
			if(null!=resultMap.get("endList")){
				pbcPrdBaseinfoEndSubDatMapper.insertBatch(resultMap.get("endList"));
			}
    }

	/**
	 * 1.1、取得当当日发生变化的登记表数据
	 * ※如果重新跑批、已经在dat管理页面编辑的数据会被清除
	 * @param searchParam
	 * @return
	 */
	public List<PbcPrdBaseinfoSubDat> getPrdBaseinfoList(Map<String,Object> searchParam){
		List<PbcPrdBaseinfoSubDat> list = pbcPrdBaseinfoSubNewMapper.searchDat(searchParam);
		return list;
	}

	/**
	 * 2.1、取得当当日发生变化的登记表数据
	 * @param searchParam
	 * @return
	 */
	public List<PbcPrdBaseinfoEndSubDat> getPrdEndList(Map<String,Object> searchParam){
		List<PbcPrdBaseinfoEndSubDat> list = pbcPrdBaseinfoEndSubNewMapper.searchDat(searchParam);
		return list;
	}

	/**
	 * 5.1、清除DAT表中的未报送数据
	 */
	public void deleteDatData(List<String> listProds,String OrgCode){
		//删除所有未报送基本信息
		pbcNewPrdDatFileMapper.deletePrdBatchByProdId(listProds);
		//删除所有未报送终止信息
		pbcNewPrdDatFileMapper.deleteEndBatchByProdId(listProds);

		/**根据机构将zipSend表中的数据进行修改**/
		pbcNewPrdDatFileMapper.updateZipSendCount(OrgCode,listProds);
		/**删除所有不包含未报送数据的dat文件*/
		pbcNewPrdDatFileMapper.deleteZipData(OrgCode);

	}

}
