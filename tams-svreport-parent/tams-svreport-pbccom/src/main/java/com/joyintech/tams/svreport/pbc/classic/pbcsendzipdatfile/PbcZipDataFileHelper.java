/*
 * 文件名：java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年6月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 详细DAT文件生成器
 * @author meifaxin
 * @version 1.0 2021年8月6日
 * @see PbcZipDataFileHelper
 * @since 1.0
 */
@Component
public class PbcZipDataFileHelper {
	public static Map<String,String> DATATYPEMAP= null;

    /**
     * 生成文件
     * @param datas
     * @param manualFlag
     * @return
     */
    public static  String genFile(PbcSendZipDatFile pbcSendZipDatFile,String datType,List<LinkedHashMap<String,String>>datas) {
		init();
		String dataType=DATATYPEMAP.get(datType);
        //压缩包名称
    	String filePath= pbcSendZipDatFile.getDatZipCode();
    	//dat文件写入
    	BufferedWriter writer = null;
		String fullPath="";
    	// 人行dat生成文件编码
    	String SVR_DAT_FILE_ENCODE = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,ReportGlobalConstant.SVR_DAT_FILE_ENCODE);
    	if (StringUtil.isEmpty(SVR_DAT_FILE_ENCODE)) {
    		SVR_DAT_FILE_ENCODE = "UTF-8";
		}
		String rootdir = SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH");
		Date date= DateUtil.parse(DateUtil.getCurrentDate());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(date);
		String datZipPath=rootdir+dateStr + "/";
		try {
            fullPath = datZipPath+filePath;
            createFolder(fullPath,dataType);
            File file = null;

            file = new SafelyFile(fullPath + "/"+ dataType + ".dat");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true), SVR_DAT_FILE_ENCODE));
            
            for (LinkedHashMap<String,String> data:datas ) {
                StringBuffer sb = new StringBuffer();
                for ( Entry<String,String>e:data.entrySet() ) {
                	if(e.getValue()!=null) {
						sb.append(e.getValue()+"|");
                	}else {
                		sb.append("|");
                	}
                }
                writer.write(sb.substring(0,sb.length()-1).toString());//删除最后一个“|”
                writer.flush();
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if ( writer!=null )
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fullPath;
    }
    
    
    public static void createFolder(String folderName,String dataType){
    	//生成文件夹
    	File fp = new SafelyFile(folderName);
        if ( !fp.exists())
           fp.mkdirs();
        //生成各类型的空文件
        	File file = new SafelyFile(folderName + "/"+dataType + ".dat");
        	if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
    }

    //dat文件类型
	public static void init() {
		if (DATATYPEMAP == null) {
			DATATYPEMAP = new HashMap<String, String>();
			DATATYPEMAP.put("PIB", "PIB");
			DATATYPEMAP.put("PIE", "PIE");
			DATATYPEMAP.put("1_1", "MIT1-1");
			DATATYPEMAP.put("1_2", "MIT1-2");
			DATATYPEMAP.put("1_3", "MIT1-3");
			DATATYPEMAP.put("1_4", "MIT1-4");
			DATATYPEMAP.put("1_1", "MIT1-1");
			DATATYPEMAP.put("1_5", "MIT1-5");
			DATATYPEMAP.put("1_6", "MIT1-6");
			DATATYPEMAP.put("2_1", "MIT2-1");
			DATATYPEMAP.put("2_2", "MIT2-2");
			DATATYPEMAP.put("2_3", "MIT2-3");
			DATATYPEMAP.put("2_4", "MIT2-4");
			DATATYPEMAP.put("2_5", "MIT2-5");
			DATATYPEMAP.put("3_1", "MIT3-1");
			DATATYPEMAP.put("3_2", "MIT3-2");
			DATATYPEMAP.put("3_3", "MIT3-3");
			DATATYPEMAP.put("4_1", "MIT4-1");
		}
	}


}
