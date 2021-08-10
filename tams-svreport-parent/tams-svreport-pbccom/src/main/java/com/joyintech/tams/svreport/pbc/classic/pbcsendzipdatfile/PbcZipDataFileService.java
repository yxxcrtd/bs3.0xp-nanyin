/**
 * 文件名：PbcClassicsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.8
 * 修改人：meifaxin
 * 修改时间：2021年8月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.8
 */
 
package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile;

import cn.hutool.core.collection.CollectionUtil;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.tams.common.base.dao.ChbTypeMappingExMapper;
import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportAssetDebtDatDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAssetDebtDatDetailDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.assetposition.constants.PbcAssetPositionConstants;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dao.PbcPrdBaseinfoSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dao.PbcPrdBaseinfoEndSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;
import com.joyintech.tams.svreport.pbc.common.util.SftpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2021年8月6日
 * @see PbcZipDataFileService
 * @since 1.0
 */
@Service
public class PbcZipDataFileService {

	@Autowired
	private ChbTypeMappingExMapper chbTypeMappingExMapper;
    @Autowired
    private PbcReportProdDatMapper pbcReportProdDatMapper;

	@Autowired
	private PbcPrdBaseinfoSubDatMapper pbcPrdBaseinfoSubDatMapper;

	@Autowired
	private PbcPrdBaseinfoEndSubDatMapper pbcPrdBaseinfoEndSubDatMapper;


	@Autowired
	private PbcReportAssetDebtDatDetailMapper pbcReportAssetDebtDatDetailMapper;
	/**
	 * 根据映射表取申请单名称及说明
	 * @param type
	 * @return
	 */
	String [] getRequestNameAndRemark(String type){
		List<ChbTypeMappingEx>   subjectNoMappingList = chbTypeMappingExMapper
				.selectByMappingcode(PbcAssetPositionConstants.MAPPINGCODE_PBCREPORT_DAT_REQUEST_NAME);
		String [] aa=new String [2];
		for(ChbTypeMappingEx single:subjectNoMappingList){
			if(single.getSrcfield1().equals(type)){
				aa =single.getSrcfield().split("\\|");
				return aa;
			}
		}
		return null;
	}

    /**
     * 生成数据文件
     * @see
     * @since 1.0
     */
    public List<String> genDataFile(Map<String,Set<String>> paramMap,Map<String, PbcSendZipDatFile> zipInfoMap) {
    	List<String> zipFileName = new ArrayList<String>();
    	
    	// 是否手工生成标志
        boolean manualFlag = false;
    	if (null != paramMap) {
    		manualFlag = true;
    	}
		for (Map.Entry<String, Set<String>> entry : paramMap.entrySet()) {  //循环datZipCode
			String datZipCode=entry.getKey();
			Set<String> set=entry.getValue();
			//去重
			Set<String> reslut = new HashSet<String>();
			PbcSendZipDatFile pbcSendZipDatFile=zipInfoMap.get(datZipCode);
			for(String datType:set) {  //生成dat文件
				if(DictCodeListExt2.人行报送明细类型_产品基本信息.getValue().equals(datType)){
					String datZipCodePath = genReportPrdBaseInfoFileDat(pbcSendZipDatFile, datType, manualFlag);
					if(StringUtil.isNotEmpty(datZipCodePath)){
						reslut.add(datZipCodePath);
					}
				}else if(DictCodeListExt2.人行报送明细类型_产品终止信息.getValue().equals(datType)){
					String datZipCodePath = genReportPrdEndInfoFileDat(pbcSendZipDatFile,datType,manualFlag);
					if(StringUtil.isNotEmpty(datZipCodePath)){
						reslut.add(datZipCodePath);
					}
				}else if (DictCodeListExt2.人行报送明细类型_募集余额报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_募集兑付报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_只数情况报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_募集余额报表分地区报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_提前及延期兑付报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_到期未兑付报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_存续产品分期限募集余额报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_新发产品分期限募集金额报表.getValue().equals(datType)
					) {
						String datZipCodePath=genReport1_1_6_3_2FileDat(pbcSendZipDatFile,datType,manualFlag);
					reslut.add(datZipCodePath);
				}else if(DictCodeListExt2.人行报送明细类型_资管产品资产负债剩余期限统计.getValue().equals(datType)){
					String datZipCodePath=genReport3_3FileDat(pbcSendZipDatFile,datType,manualFlag);
					reslut.add(datZipCodePath);
				}else if(DictCodeListExt2.人行报送明细类型_资管产品资产负债统计.getValue().equals(datType)){
					String datZipCodePath=genReport2_1FileDat(pbcSendZipDatFile,datType,manualFlag);
					reslut.add(datZipCodePath);
				}
			}
			//生成压缩包
			List<String> zipFileList = genCompressFile(reslut,manualFlag);
			zipFileName.addAll(zipFileList);
		}
        /*手工生成的文件再次压缩   end*/
        return zipFileName;
        
    }


	//产品基本信息dat
	private String genReportPrdBaseInfoFileDat(PbcSendZipDatFile pbcSendZipDatFile, String datType, boolean manualFlag) {
		List<PbcPrdBaseinfoSubDat> pbcPrdBaseinfoSubDats = pbcPrdBaseinfoSubDatMapper.selectByDatZipCode(pbcSendZipDatFile.getDatZipCode());
		if(CollectionUtil.isEmpty(pbcPrdBaseinfoSubDats)){
			return "";
		}
		List<LinkedHashMap<String,String>> dataList =new ArrayList<>();
		for(PbcPrdBaseinfoSubDat subDat :pbcPrdBaseinfoSubDats){
			//组装数据
			LinkedHashMap<String,String> data= new LinkedHashMap<String,String>();
			//产品代码
			data.put("pbcProdId",subDat.getPbcProdId());
			//发行机构代码
			data.put("issuePartyId",subDat.getIssuePartyId());
			//产品名称
			data.put("prodName",subDat.getProdName());
			//产品品种
			data.put("pbcProdType",subDat.getPbcProdType());
			//产品类型
			data.put("ivstorientation",subDat.getIvstorientation());
			//产品品牌
			data.put("prodBrand",subDat.getProdBrand());
			//产品期次
			data.put("prodStage",subDat.getProdStage().toPlainString());
			//发行机构内部产品代码
			data.put("prodId",subDat.getProdId());
			//募集方式
			data.put("raiseType",subDat.getRaiseType());
			//管理方式
			data.put("manageType",subDat.getManageType());
			//运行方式
			data.put("operateMode",subDat.getOperateMode());
			//募集起始日期
			data.put("raiseVdate",subDat.getRaiseVdate() == null ? "" : DateUtil.format(subDat.getRaiseVdate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
			//募集结束日期
			data.put("raiseEnddate",subDat.getRaiseEnddate() == null ? "" : DateUtil.format(subDat.getRaiseEnddate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
			//发行机构提前终止权标识
			data.put("termRightFlag",subDat.getTermRightFlag());
			//客户赎回权标识
			data.put("cusRedeemFlag",subDat.getCusRedeemFlag());
			//产品增信标识
			data.put("creditFlag",subDat.getCreditFlag());
			//境内托管机构代码
			data.put("inDepCode",subDat.getInDepCode());
			//境外托管机构国别
			data.put("outDepCtry",subDat.getOutDepCtry());
			//境外托管机构名称
			data.put("outDepName",subDat.getOutDepName());
			//产品起始日期
			data.put("vdate",subDat.getVdate() == null ? "" : DateUtil.format(subDat.getVdate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
			//产品预计终止日期
			data.put("mdate",subDat.getMdate() == null ? "" : DateUtil.format(subDat.getMdate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
			//受托职责
			data.put("fiduciaryDuty",subDat.getFiduciaryDuty());
			//分级产品标识
			data.put("isConstracte",subDat.getIsConstracte());
			//收益权转让产品标识
			data.put("transferType",subDat.getTransferType());
			//货币市场基金标识
			data.put("isMarketFund",subDat.getIsMarketFund());
			//其他产品标识1
			data.put("other1","");
			//其他产品标识2
			data.put("other2","");
			//其他产品标识3
			data.put("other3","");
			dataList.add(data);
		}
		for (PbcPrdBaseinfoSubDat subDat : pbcPrdBaseinfoSubDats) {
			//组装数据
			LinkedHashMap<String,String> data= new LinkedHashMap<String,String>();
			//产品代码
			data.put("pbcProdId",subDat.getPbcProdId());
			//币种种类1-募集资金币种
			//2-兑付本金币种
			//3-兑付收益币种
			data.put("ccyType","1");
			//币种代码
			data.put("ccyCode",subDat.getRaiseCcy());
			dataList.add(data);
			//组装数据
			LinkedHashMap<String,String> data1= new LinkedHashMap<String,String>();
			//产品代码
			data1.put("pbcProdId",subDat.getPbcProdId());
			//币种种类1-募集资金币种
			//2-兑付本金币种
			//3-兑付收益币种
			data1.put("ccyType","2");
			//币种代码
			data1.put("ccyCode",subDat.getCashPrinCcy());
			dataList.add(data1);
			//组装数据
			LinkedHashMap<String,String> data2 = new LinkedHashMap<String,String>();
			//产品代码
			data2.put("pbcProdId",subDat.getPbcProdId());
			//币种种类1-募集资金币种
			//2-兑付本金币种
			//3-兑付收益币种
			data2.put("ccyType","3");
			//币种代码
			data2.put("ccyCode",subDat.getCashProfCcy());
			dataList.add(data2);
		}
		for (PbcPrdBaseinfoSubDat subDat : pbcPrdBaseinfoSubDats) {
			//组装数据
			LinkedHashMap<String,String> data= new LinkedHashMap<String,String>();
			//产品代码
			data.put("pbcProdId",subDat.getPbcProdId());
			//增信机构类型
			data.put("creditType",subDat.getCreditType());
			dataList.add(data);
		}
		for (PbcPrdBaseinfoSubDat subDat : pbcPrdBaseinfoSubDats) {
			//组装数据
			LinkedHashMap<String,String> data= new LinkedHashMap<String,String>();
			//产品代码
			data.put("pbcProdId",subDat.getPbcProdId());
			//增信形式
			data.put("creditMode",subDat.getCreditMode());
			dataList.add(data);
		}
		return PbcZipDataFileHelper.genFile(pbcSendZipDatFile,datType,dataList);
	}

	//产品终止信息dat
	private String genReportPrdEndInfoFileDat(PbcSendZipDatFile pbcSendZipDatFile, String datType, boolean manualFlag) {
		List<PbcPrdBaseinfoEndSubDat> pbcPrdBaseinfoEndSubDats = pbcPrdBaseinfoEndSubDatMapper.selectByDatZipCode(pbcSendZipDatFile.getDatZipCode());
		if(CollectionUtil.isEmpty(pbcPrdBaseinfoEndSubDats)){
			return "";
		}
		List<LinkedHashMap<String,String>> dataList =new ArrayList<>();
		for (PbcPrdBaseinfoEndSubDat subDat : pbcPrdBaseinfoEndSubDats) {
			//组装数据
			LinkedHashMap<String,String> data= new LinkedHashMap<String,String>();
			//产品代码
			data.put("pbcProdId",subDat.getPbcProdId());
			//产品实际终止日期
			data.put("mDate",subDat.getMdate() == null ? "" : DateUtil.format(subDat.getMdate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
			//兑付客户收益率
			data.put("cusYearYield",subDat.getCusYearYield().toPlainString());
			dataList.add(data);
		}
		for (PbcPrdBaseinfoEndSubDat subDat : pbcPrdBaseinfoEndSubDats) {
			//组装数据
			LinkedHashMap<String,String> data= new LinkedHashMap<String,String>();
			//产品代码
			data.put("pbcProdId",subDat.getPbcProdId());
			//币种代码
			data.put("mDate",subDat.getCcy());
			//兑付客户收益
			data.put("customProfit",subDat.getCustomProfit().toPlainString());
			//兑付客户收益转人民币
			data.put("profitCny",subDat.getProfitCny().toPlainString());
			dataList.add(data);
		}
		return PbcZipDataFileHelper.genFile(pbcSendZipDatFile,datType,dataList);
	}
	//报表2_1
	private String genReport2_1FileDat(PbcSendZipDatFile pbcSendZipDatFile, String datType, boolean manualFlag) {
		List<PbcReportAssetDebtDatDetailDto> debtDatDetailDtos = pbcReportAssetDebtDatDetailMapper.selectByDatZipCode(pbcSendZipDatFile.getDatZipCode(), datType);
		List<LinkedHashMap<String,String>> dataList =new ArrayList<>();
		for(PbcReportAssetDebtDatDetailDto sub :debtDatDetailDtos){
			//组装数据
			LinkedHashMap<String,String> data= new LinkedHashMap<String,String>();
			data.put("date",sub.getTermid());
			data.put("finInstitutionCode",sub.getFinInstitutionCode());
			data.put("prodVariety",sub.getProdVariety());
			dataList.add(data);
		}
		String result=PbcZipDataFileHelper.genFile(pbcSendZipDatFile,datType,dataList);
		return result;
	}

	//报表1_1到1_6,3_1到3_2dat
    private String genReport1_1_6_3_2FileDat(PbcSendZipDatFile pbcSendZipDatFile,String datType,boolean manualFlag) {
		List<PbcReportProdDatDto> subList = pbcReportProdDatMapper.selectByDatZipCode(pbcSendZipDatFile.getDatZipCode(),datType);

        Map<String,List<LinkedHashMap<String,String>>> map=new HashMap<String,List<LinkedHashMap<String,String>>>();
		List<LinkedHashMap<String,String>> dataList =new ArrayList<>();
        for(PbcReportProdDatDto sub :subList){
        	//组装数据
        	LinkedHashMap<String,String> data= new LinkedHashMap<String,String>();
			data.put("date",sub.getTermid());
            data.put("finInstitutionCode",sub.getFinInstitutionCode());
			data.put("prodVariety",sub.getProdVariety());
        	dataList.add(data);
        	map.put(sub.getDatZipCode(),dataList);
        }
		String result=PbcZipDataFileHelper.genFile(pbcSendZipDatFile,datType,dataList);
        return result;
    }




	//报表3_3dat
	private String genReport3_3FileDat(PbcSendZipDatFile pbcSendZipDatFile,String datType,boolean manualFlag) {
		List<PbcReportProdDatDto> subList = pbcReportProdDatMapper.selectByDatZipCode(pbcSendZipDatFile.getDatZipCode(),datType);

		Map<String,List<LinkedHashMap<String,String>>> map=new HashMap<String,List<LinkedHashMap<String,String>>>();
		List<LinkedHashMap<String,String>> dataList =new ArrayList<>();
		for(PbcReportProdDatDto sub :subList){
			//组装数据
			LinkedHashMap<String,String> data= new LinkedHashMap<String,String>();
			data.put("date",sub.getTermid());
			data.put("finInstitutionCode",sub.getFinInstitutionCode());
			data.put("prodVariety",sub.getProdVariety());
			dataList.add(data);
			map.put(sub.getDatZipCode(),dataList);
		}
		String result=PbcZipDataFileHelper.genFile(pbcSendZipDatFile,datType,dataList);
		return result;
	}

    private List<String> genCompressFile(Set<String>  files, boolean manualFlag) {
        String rootdir = SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH");
        String switchs = SvrDataUtil.getSysParam("SVR_PBC_DAT_SWITCH");
    	/*将生成压缩文件的地址存入List并返回 add by gaojian 2019-06-18 begin*/
    	List<String> zipFileList = new ArrayList<String>(16);
    	/*将生成压缩文件的地址存入List并返回 add by gaojian 2019-06-18 end*/
    	for(String folderPath : files){
    		  String zipFileName = folderPath.replace("_", "")+".zip";
    		  zipFileList.add(zipFileName);
    		  File outFile =  new SafelyFile(zipFileName);
    		  File[] allFiles = new SafelyFile(folderPath).listFiles();
    		  if(outFile.exists()){
    			  outFile.delete();
    		  }
    		  try {
    			  // 手工生成的文件在压缩之后删除
    			  if (manualFlag) {
    				  //压缩删除文件
    				  ZipFileUtil.compressZip(outFile, true, allFiles);
    				  //删除文件夹
    				  new SafelyFile(folderPath).delete();
    			  } else {
    				  ZipFileUtil.compressZip(outFile, false, allFiles);//压缩文件
    			  }
//                new File(folderPath).delete();//删除文件夹
              }catch (Exception e) {
            	  e.printStackTrace();
              }
    		  try {
    			  if(StringUtil.equals("open", switchs)){
    				  String userName=SvrDataUtil.getSysParam("RECEPTION_TAMS_NAME");
    				  String ftpIp=SvrDataUtil.getSysParam("RECEPTION_TAMS_IP");
    				  Integer ftpPort=Integer.valueOf(SvrDataUtil.getSysParam("RECEPTION_TAMS_PORT"));
    				  String password=SvrDataUtil.getSysParam("RECEPTION_TAMS_PWD");
    				  if(userName!=null&&ftpIp!=null&&ftpPort!=null&&password!=null){
    					  SftpUtil.SFTPconnect(userName,ftpIp,ftpPort,password);
//	                  	  SftpUtil.SFTPconnect(pbccomUtil.getConfig("DEPOSIT_TAMS_NAME"),pbccomUtil.getConfig("DEPOSIT_TAMS_IP"),Integer.valueOf(pbccomUtil.getConfig("DEPOSIT_TAMS_PORT")),pbccomUtil.getConfig("DEPOSIT_TAMS_PWD"));
    					  SftpUtil.putFile(outFile.getAbsolutePath(), rootdir);
    				  }
    			  }
              }
              catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
    	}
    	return zipFileList;

    }
    

    /**
     * 将手工生成的压缩文件进一步压缩，并返回最终文件路径
     * @param zipFileName 手工生成的文件路径列表
     * @return 最终文件路径
     */
    private String genFinalCompressFile(List<String> zipFileName) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        int size = zipFileName.size();
        // 未生辰数据文件,不需要继续压缩
        if (size < 1) {
        	return "";
        }
        File[] allFiles = new SafelyFile[size];
        // 最终文件路径
    	String finalZipPath = SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH") + "temporary" + File.separator + "手工生成人行数据文件" + dateStr + ".zip";
    	File outFile =  new SafelyFile(finalZipPath);
    	for (int i = 0; i < size; i ++) {
    		allFiles[i] = new SafelyFile(zipFileName.get(i));
    	}
    	// 压缩文件并删除原文件
    	try {
			ZipFileUtil.compressZip(outFile, true, allFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return finalZipPath;
    }



}
