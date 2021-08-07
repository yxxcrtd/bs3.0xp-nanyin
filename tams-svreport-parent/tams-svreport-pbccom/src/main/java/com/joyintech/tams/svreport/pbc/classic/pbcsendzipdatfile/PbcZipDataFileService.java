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
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.tams.common.base.dao.ChbTypeMappingExMapper;
import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dao.PbcReportProdDatMapper;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.assetposition.constants.PbcAssetPositionConstants;
import com.joyintech.tams.svreport.pbc.common.util.SftpUtil;
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
    public String genDataFile(Map<String,Set<String>> paramMap,Map<String, PbcSendZipDatFile> zipInfoMap) {
    	List<String> zipFileName = new ArrayList<String>();
    	
    	// 是否手工生成标志
        boolean manualFlag = false;
    	if (null != paramMap) {
    		manualFlag = true;
    	}
		for (Map.Entry<String, Set<String>> entry : paramMap.entrySet()) {
			String keyVal=entry.getKey();
			Set<String> set=entry.getValue();
			if (DictCodeListExt2.人行报送明细类型_募集余额报表.getValue().equals(keyVal) ) {
				for(String datZipCode:set) {
					PbcSendZipDatFile pbcSendZipDatFile=zipInfoMap.get(datZipCode);
					zipFileName.addAll(genReport1_6File(pbcSendZipDatFile,manualFlag));
				}
			}
		}
        String finalZipFile = "";
        /*手工生成的文件再次压缩   begin*/
        if (manualFlag) {
        	finalZipFile = genFinalCompressFile(zipFileName);
        }
        /*手工生成的文件再次压缩   end*/
        return finalZipFile;
        
    }
    //报表1_1到1_6zip
    public List<String> genReport1_6File(PbcSendZipDatFile pbcSendZipDatFile, boolean manualFlag) {
    	// 人行单产品报表DAT文件明细表关联List
    	Map<String,Object> fileDataMap = new HashMap<String,Object>();
    	//记录序列号
    	Map<String,List<String>> seqMap = new HashMap<String,List<String>>();
    	//生成dat文件
    	List<String> files1_1 = genReport1_6FileDat(pbcSendZipDatFile,seqMap,manualFlag,fileDataMap);
        //去重
        Set<String> reslut = new HashSet<String>(); 
        reslut.addAll(files1_1);
        //生成压缩包
        List<String> zipFileList = genCompressFile(reslut,manualFlag);    
        return zipFileList;
    }
    //报表1_1到1_6dat
    private List<String> genReport1_6FileDat(PbcSendZipDatFile pbcSendZipDatFile,Map<String,List<String>> seqMap, boolean manualFlag,Map<String,Object> fileDataMap) {
    	List<String> result = new ArrayList<String>();
		List<PbcReportProdDatDto> subList = pbcReportProdDatMapper.selectByDatZipCode(pbcSendZipDatFile.getDatZipCode());
        //不同的zip包
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
		result.addAll(
				PbcZipDataFileHelper.genFile(pbcSendZipDatFile,dataList,manualFlag));
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
