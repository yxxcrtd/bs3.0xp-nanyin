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

import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.tams.common.base.dao.ChbTypeMappingExMapper;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportDebtTermDatDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportDebtTermDatDetail;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.common.util.SftpUtil;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


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
	private  ChbTypeMappingExMapper chbTypeMappingExMapper;
    @Autowired
    private PbcReportProdDatMapper pbcReportProdDatMapper;
	@Autowired
	private PbcReportDebtTermDatDetailMapper pbcReportDebtTermDatDetailMapper;
	@Autowired
	private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;
	@Autowired
	private PbcMonthReportMapper pbcMonthReportMapper;
    private  List<ChbTypeMappingEx> chbTypeMappingExList=null;

    /**
     * 生成数据文件
     * @see
     * @since 1.0
     */
    public String genDataFile(Map<String,Set<String>> paramMap,Map<String, PbcSendZipDatFile> zipInfoMap,Set<String>  zipCodeSet,Set<String>  reportuuidSet) {
    	List<String> zipFileName = new ArrayList<String>();
		chbTypeMappingExList=initReportConfig();
		//去重
		Set<String> reslut = new HashSet<String>();
    	//zipDat更新数据
		List<PbcSendZipDatFile> zipDatFileList=new ArrayList<>();
		//pbcReport更新数据
		List<String> pbcReportUuidList=new ArrayList<>();
		Date date= DateUtil.parse(DateUtil.getCurrentDate());
		for (Map.Entry<String, Set<String>> entry : paramMap.entrySet()) {  //循环datZipCode
			String datZipCode=entry.getKey();
			Set<String> set=entry.getValue();
			PbcSendZipDatFile pbcSendZipDatFile=zipInfoMap.get(datZipCode);
			String datZipCodePath="";
			for(String datType:set) {  //生成dat文件
				if(DictCodeListExt2.人行报送明细类型_产品基本信息.getValue().equals(datType)){

				}else if(DictCodeListExt2.人行报送明细类型_产品终止信息.getValue().equals(datType)){

				}else if (DictCodeListExt2.人行报送明细类型_募集余额报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_募集兑付报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_只数情况报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_募集余额报表分地区报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_提前及延期兑付报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_到期未兑付报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_存续产品分期限募集余额报表.getValue().equals(datType)
						||DictCodeListExt2.人行报送明细类型_新发产品分期限募集金额报表.getValue().equals(datType)
					) {
					datZipCodePath=genReport1_1_6_3_2FileDat(pbcSendZipDatFile,datType);
					reslut.add(datZipCodePath);
				}else if(DictCodeListExt2.人行报送明细类型_新发产品分期限募集金额报表.getValue().equals(datType)){
					datZipCodePath=genReport3_3FileDat(pbcSendZipDatFile,datType);
					reslut.add(datZipCodePath);
				}
			}
			//添加zip路径
			if(DictCodeListExt2.人行报送类型_统计模板数据.getValue().equals(pbcSendZipDatFile.getDatZipType())) {
				PbcSendZipDatFile zipDatFile = getPbcSendZipDatFile(datZipCode,datZipCodePath,date);
				zipDatFileList.add(zipDatFile);
				pbcReportUuidList.add(pbcSendZipDatFile.getReportuuid());
			}
		}
		//生成压缩包
		List<String> zipFileList = genCompressFile(reslut);
		zipFileName.addAll(zipFileList);

		String finalZipFile = genFinalCompressFile(zipFileName);
		//更新数据状态
		updateFileDatAndPbcReport(zipDatFileList,pbcReportUuidList,date);
		return finalZipFile;

    }
    //报表1_1到1_6,3_1到3_2dat
    private String genReport1_1_6_3_2FileDat(PbcSendZipDatFile pbcSendZipDatFile,String datType) {
		List<PbcReportProdDatDto> subList = pbcReportProdDatMapper.selectByDatZipCode(pbcSendZipDatFile.getDatZipCode(),datType);

        Map<String,List<LinkedHashMap<String,String>>> map=new HashMap<String,List<LinkedHashMap<String,String>>>();
		List<LinkedHashMap<String,String>> dataList =new ArrayList<>();
		List<ChbTypeMappingEx> exList=getReportRowAndColumnNum(datType);
		for(PbcReportProdDatDto sub :subList){  //循环报表数据
			String propertycode=sub.getPropertyCode();
			//查询属性对应的匹配关系数据
			List<ChbTypeMappingEx> propertyCodeFiledList=exList.stream().filter(
					propertyCodeEx -> propertycode.equals(propertyCodeEx.getSrcfield())).collect(Collectors.toList());
			for(ChbTypeMappingEx ex:propertyCodeFiledList) {
					Object   filedValue=null;
					//get[源属性1]()   取得报表的对应单元的数值
					try {
						Field field = sub.getClass().getDeclaredField(ex.getSrcfield1());
						//设置对象的访问权限，保证对private的属性的访问
						field.setAccessible(true);
						filedValue= field.get(sub);
					} catch (Exception e) {
						return null;
					}
				     //数值为空或者0的情况下跳过
					if(null==filedValue||filedValue.equals("0")){
                         continue;
					}
					//组装数据
					LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
					data.put("date", DateUtil.getLastDayOfThisMonth(sub.getTermid()));
					data.put("issuePartyId", sub.getIssuePartyId());
					data.put("prodVariety", sub.getProdVariety());
					data.put("rowColumn", getRowAndColumn(ex.getObjfield1(),ex.getObjfield2()));
					data.put("filedValue", filedValue.toString());
					dataList.add(data);
			}
		}
		//生成dat文件
		String result=PbcZipDataFileHelper.genFile(pbcSendZipDatFile,datType,dataList);
		//更新数据状态
        return result;
    }

	//报表3_3dat
	private String genReport3_3FileDat(PbcSendZipDatFile pbcSendZipDatFile,String datType) {
		List<PbcReportDebtTermDatDetail> subList = pbcReportDebtTermDatDetailMapper.selectByDatZipCode(pbcSendZipDatFile.getDatZipCode(),datType);

		Map<String,List<LinkedHashMap<String,String>>> map=new HashMap<String,List<LinkedHashMap<String,String>>>();
		List<LinkedHashMap<String,String>> dataList =new ArrayList<>();
		List<ChbTypeMappingEx> exList=getReportRowAndColumnNum(datType);
		for(PbcReportDebtTermDatDetail sub :subList){  //循环报表数据
			String propertycode=sub.getPropertycode();
			//查询属性对应的匹配关系数据
			List<ChbTypeMappingEx> propertyCodeFiledList=exList.stream().filter(
					propertyCodeEx -> propertycode.equals(propertyCodeEx.getSrcfield())).collect(Collectors.toList());
			for(ChbTypeMappingEx ex:propertyCodeFiledList) {
				Object   filedValue=null;
				//get[源属性1]()   取得报表的对应单元的数值
				try {
					Field field = sub.getClass().getDeclaredField(ex.getSrcfield1());
					//设置对象的访问权限，保证对private的属性的访问
					field.setAccessible(true);
					filedValue= field.get(sub);
				} catch (Exception e) {
					return null;
				}
				//数值当为空、或者0的情况下
				if(null==filedValue||filedValue.equals("0")){
					continue;
				}
				//组装数据
				LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
				data.put("date", DateUtil.getLastDayOfThisMonth(sub.getTermid()));
				data.put("issuePartyId", sub.getIssuePartyId());
				data.put("prodVariety", sub.getProdVariety());
				data.put("rowColumn", getRowAndColumn(ex.getObjfield1(),ex.getObjfield2()));
				data.put("filedValue", filedValue.toString());
				dataList.add(data);
			}
		}
		String result=PbcZipDataFileHelper.genFile(pbcSendZipDatFile,datType,dataList);
		return result;
	}

    private List<String> genCompressFile(Set<String>  files) {
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
				  ZipFileUtil.compressZip(outFile, false, allFiles);//压缩文件
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

	/**
	 * 报表根据类型和列获取行号和列号
	 * @param reportType
	 * @return
	 */
	private List<ChbTypeMappingEx>  getReportRowAndColumnNum(String reportType){
		// 根据类型筛选
		List<ChbTypeMappingEx> exList = chbTypeMappingExList.stream()
				.filter(ex -> reportType.equals(ex.getMappingcode()))
				.collect(Collectors.toList());
		//行号-列号
		return exList;
	}

	/**
	 * 报表配置初始化
	 * @return
	 */
	private  List<ChbTypeMappingEx>  initReportConfig(){
		List<ChbTypeMappingEx>  HoldTypeMappingList = chbTypeMappingExMapper.selectByMappingname(DictCodeListExt2.人行报送类型_统计模板数据.getValue());
		return HoldTypeMappingList;
	}


    //行编号-列编号，例如：300000-A
	private String getRowAndColumn(String row,String column){
		StringBuilder strBuilder=new StringBuilder();
		strBuilder.append(row).append("-").append(column);
		return strBuilder.toString();
	}

	//初始化配置表数据
	private String  initChbExData(){
		String[] reportTypeList={"1_1","1_2","1_3","1_4","1_5","1_6",
				"2_1","2_2","2_3","2_4","2_5","3_1","3_2","3_3"};
		List<String> deleteSqlList=new ArrayList<>();
		for(String reportType:reportTypeList ) {
			File file = new File("D:\\pj\\yth\\tams\\tams-svreport-parent\\tams-svreport\\src\\main\\resources\\tpl\\pbc\\PbcDatReport_"+reportType+".xls");
			try {
				List<ChbTypeMappingEx> list = PbcZExcelDefineUtil.readToList(reportType, file, 0);
				for (ChbTypeMappingEx chbTypeMappingEx : list) {
					ChbTypeMappingEx typeMappingEx = chbTypeMappingExMapper.selectBySrcfieldSrcfield1(chbTypeMappingEx.getSrcfield(), chbTypeMappingEx.getSrcfield1());
					if (typeMappingEx == null) {
						chbTypeMappingExMapper.insertSelective(chbTypeMappingEx);
					}
					String sql="delete from CHB_TYPE_MAPPING_EX where Srcfield='"+chbTypeMappingEx.getSrcfield()+"' and Srcfield1='"+chbTypeMappingEx.getSrcfield1()+"'";
					deleteSqlList.add(sql);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return JsonUtils.object2Json(deleteSqlList);
	}

	//数据报送文件管理表PBC_SEND_ZIP_DAT_FILE
    private PbcSendZipDatFile getPbcSendZipDatFile(String ZipCode,String datZipPath,Date date){
		PbcSendZipDatFile datFile=new PbcSendZipDatFile();
		datFile.setDatZipCode(ZipCode);
		datFile.setDatZipPath(datZipPath);
		datFile.setDatZipStatus(DictCodeListExt3.zip包状态_已使用.getValue());
		datFile.setReturnZipSendStatus(DictCodeListExt2.人行报送反馈状态_人行通过.getValue());
		datFile.setSubmitTime(date);
		datFile.setZipDownloadTime(date);
		datFile.setZipSendStatus(DictCodeListExt2.报送状态_手工处理.getValue());
		datFile.setDatZipType(DictCodeListExt2.人行报送类型_统计模板数据.getValue());
		return datFile;
	}
	//更新数据状态
	private  void updateFileDatAndPbcReport(List<PbcSendZipDatFile> datFileList,List<String> pbcReportUuidList,Date date){
		if(!CollectionUtils.isEmpty(datFileList)){
			//更新PBC_SEND_ZIP_DAT_FILE
			pbcSendZipDatFileMapper.updateByZipCodeAndZipType(datFileList,DictCodeListExt2.人行报送类型_统计模板数据.getValue());
		}
	    //更新报表
		if(!CollectionUtils.isEmpty(pbcReportUuidList)) {
			pbcMonthReportMapper.updatePbcReportSendInfo(DictCodeListExt2.报送状态_手工处理.getValue(), date, DictCodeListExt2.人行报送反馈状态_人行通过.getValue(), pbcReportUuidList);
		}
		}





}
