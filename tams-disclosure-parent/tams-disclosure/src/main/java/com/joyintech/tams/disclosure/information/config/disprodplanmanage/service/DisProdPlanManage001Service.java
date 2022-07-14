/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.*;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.base.entity.ChbInfoMgrResultConfig;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.common.ChbCheckTableInfo;
import com.joyintech.tams.disclosure.common.DuplicateDataUtil;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage001Dto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.utils.poi.PoiUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.disclosure.common.common.util.OfficeMergeUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * 记账凭证查询service
 * 
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see DisProdPlanManage001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisProdPlanManage001Service extends CommonExportService<DisProdPlanManage001Dto> {

    
//    @Autowired
//    private RepInfoMapper repInfoMapper;
//    @Autowired
//    private ReportModelManage001Mapper reportModelManage001Mapper;
    @Autowired
    private JasparConnectUtilService jasparConnectUtilService;
        /**
         * 日志
         */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage001Service.class);

    private Map<String,List<String>> propertiesMap = new HashMap<>();
    {
        propertiesMap.put("DisProdPlanManage001_1",Lists.newArrayList("产品计划排期导入"));
        propertiesMap.put("DisProdPlanManage001_2",Lists.newArrayList("产品名称","产品主代码","开放起始日","开放起始日","产品冷静期","运作起始日","自动赎回日","实际持有天数"));
    }

    /**
     * <一句话功能简述> 导出估值表
     *
     * @throws IOException
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(List<DisProdPlanManage001Dto> list)
        throws IOException {
        // TODO  字段问题  报错注释
        return null;
           /* for (DisProdPlanManage001Dto disProdPlanManage001Dto : list) {
                createExcel(disProdPlanManage001Dto);
            }

            String exportFilePath = CacheUtil.getSysParam(
                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            String zipFileName = FileNameUtil.createFileName("zip");
            File zipFile = new SafelyFile(exportFilePath + File.separator + zipFileName);
            FileOutputStream stream = null;
            ZipOutputStream  zipOut =null;
            try{
                 stream = new FileOutputStream(zipFile);
                 zipOut = new ZipOutputStream(stream);
                 InputStream input = null;
                 
                 for (DisProdPlanManage001Dto dto : list) {
                     try{
                         zipOut.putNextEntry(new ZipEntry(dto.getName()));
                         input = new FileInputStream(dto.getTemLoad());
                         int tmp = 0;
                         while ((tmp = input.read()) != -1) {
                             zipOut.write(tmp);
                         }
                      }catch (Exception e) {
                          logger.error("error:",e);
                      }finally{
                          if(input!=null){
                              input.close();
                          }
                      } 
                }
                 
                zipOut.flush();
            }catch (Exception e) {
                logger.error("",e);
            }finally{
                if(zipOut!=null){
                    zipOut.close();
                }
                if(stream!=null){
                    stream.close();
                }
            }
            return ExportUtil.export(zipFileName, zipFile, true);*/
    }

    public void createExcel(DisProdPlanManage001Dto dto){
    	// 把前台传来的请求参数属性解析成Map集合
    	Map<String, Object> map = new HashMap<String, Object>();
        UploadFile uploadFile = null;
        try {
            // TODO  字段问题  报错注释
            /*if(dto!=null)
            {
                 uploadFile = jasparConnectUtilService.connectJasparAndExport(dto.getUrl(), dto.getCode(), dto.getFileType(),dto.getCode(),dto.getParam());
            }
            if(uploadFile!=null){
                dto.setName(uploadFile.getSrcName());
                dto.setTemLoad(uploadFile.getTargetFile().getPath());
            }*/

        } catch (Exception e) {
        	logger.error("error:",e);
        }
        
    }
        
        
        /**
         * 从输入流中获取字节数组
         * 
         * @param inputStream
         * @return
         * @throws IOException
         */
        public static byte[] readInputStream(InputStream inputStream) throws IOException {
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
            return bos.toByteArray();
        }

        /**
         * 导出合并文件
         *
         * @return
         * @throws Exception 
         * @see
         * @since 1.0
         */
        public ResponseEntity<byte[]> mergeExport(List<DisProdPlanManage001Dto> list) {
            // TODO  字段问题  报错注释
            return null;
            /*List<String> srcFileList = new ArrayList<String>();
            for (DisProdPlanManage001Dto disProdPlanManage001Dto : list) {
                createExcel(disProdPlanManage001Dto);
                srcFileList.add(disProdPlanManage001Dto.getTemLoad());
            }
            String fileType = list.get(0).getFileType();
            String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            String mergeExportFileName = FileNameUtil.createFileName(fileType);
            File exportFile = new SafelyFile(exportFilePath + File.separator + mergeExportFileName);
            if("docx".equals(fileType)){
                OfficeMergeUtil.mergeWord(exportFilePath + File.separator + mergeExportFileName, srcFileList);
            }else if("xls".equals(fileType) || "xlsx".equals(fileType)){
            	//update by leipng 2.1.9升级报错  目前暂无使用.注释掉
//                OfficeMergeUtil.mergeExcel(exportFilePath + File.separator + mergeExportFileName, srcFileList);
            }
            return ExportUtil.export(mergeExportFileName, exportFile, true);*/
        }

        
        
        public String buildFileName(DisProdPlanManage001Dto disProdPlanManage001Dto){
            // TODO  字段问题  报错注释
            return null;
        	/*String repName = null;
        	if(StringUtils.isNotBlank(disProdPlanManage001Dto.getName())) {
        		repName = disProdPlanManage001Dto.getName();
        	}else {
        		repName = disProdPlanManage001Dto.getCode();
        	}
        	
        	//文件类型
            String fileType =disProdPlanManage001Dto.getFileType();
            //生成的文件名
            //String mergeExportFileName = disProdPlanManage001Dto.getCode()+GlobalConstant.POINT+fileType;
            //如果获取不到就直接替代上去，以避免出现文件名为Null的情况
            if(StringUtils.isBlank((String)disProdPlanManage001Dto.getParam().get("repName"))) {
            	if(StringUtils.isNotBlank(repName)) {
                    disProdPlanManage001Dto.getParam().put("repName", repName);
            	}
            }
            String fileName =(String)disProdPlanManage001Dto.getParam().get("repName")+GlobalConstant.POINT+fileType;
            if(disProdPlanManage001Dto.getParam().get("endDate")!=null){
                fileName=disProdPlanManage001Dto.getParam().get("endDate")+"_"+fileName;
            }
            if(disProdPlanManage001Dto.getParam().get("portnum")!=null){
                fileName=disProdPlanManage001Dto.getParam().get("portnum")+"_"+fileName;
            }
        	
			return fileName;*/
        }
        
        
        public List<Map<String,String>> searchReports(Map<String,Object> params) throws IOException, SAXException, ParserConfigurationException {
        	//后台批处理报表地址单独啦出来
    		String serverUrl = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL);
    		//用户名
    		String serverUser = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME);
    		//密码 
    		String serverPwd = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD);
    		//路径 
    		String path = params.get("folderUri").toString();
    		
    		String url = jasparConnectUtilService.getReportsUrl(serverUrl, serverUser, serverPwd, path);
    		//
    		List<Map<String,String>> result = jasparConnectUtilService.connectJasparAndGenXML(url,false);
    		return result;
        }
        
        public List<DisProdPlanManage001Dto> searchReportsNew(Map<String,Object> params) {

        	List<DisProdPlanManage001Dto> result =restTemplate.postForList(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/DisProdPlanManage001/findAll",
                    params,DisProdPlanManage001Dto.class);
    		
    		
    		return result;
        }
        /**
    	 * 指标库查询service <一句话功能简述> <功能详细描述>
    	 * 
    	 * @param pageParam
    	 * @return
    	 */
    	public Page<DisProdPlanManage001Dto> findByPage(Map<String, Object> pageParam) {
//    		return super.queryForPage(() -> reportModelManage001Mapper.searchReports(pageParam));
    		
    		return restTemplate.postForPage(

                    ServiceApplication.VALUATION_SERVICE_NAME + "/DisProdPlanManage001/findPage",
                    pageParam, DisProdPlanManage001Dto.class);
    		
    		
    	}


    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
/*    public AjaxResult checkConfirmMsgForImportData(String fileName) throws Exception {
        AjaxResult webResult = new AjaxResult();
       *//* String pageId = "DisProdPlanManage001_1";
        if(StringUtil.equals(CacheUtil.getSysParam("CHB_ASSET_INFO_TEMPLATE_FILE"), "01")){
            pageId = "DisProdPlanManage001_2";
        }*//*
        String pageId = "DisProdPlanManage001_2";
        if (!DuplicateDataUtil.checkFileName(fileName,propertiesMap.get(pageId))){
            webResult.setMessage(this.getMessage("ImportFileName"), MessageType.错误消息);
            return webResult;
        }
        List<Map<String, String>> list = dataMapAnalysis(fileName, ChbCheckTableInfo.产品计划排期.getTableName());
        if(list.size()<1){
            webResult.setMessage("请勿上传空文件", MessageType.错误消息);
            return webResult;
        }
        //查过1000条分页查询，按998条分页
        List<List<Map<String, String>>> partitionList = Lists.partition(list, 998);
        *//*int count =0;
        for (List<Map<String, String>> partition:partitionList) {
            int i =assetInfoDetailMapper.selectValidRecords(partition);
            count=count+i;
        }*//*
        *//*if(count>0){
            String[] checkEuplicatedKeys = ChbCheckTableInfo.产品计划排期.getCheckEuplicatedKeys();
            int dup = DuplicateDataUtil.countDuplicatedData(list, checkEuplicatedKeys);
            webResult.setMessage(this.getMessage("SvrDuplicateMessage",dup,count), MessageType.确认消息);
        }*//*
        return webResult;
    }

    *//**
     * excel导入数据整理
     *
     * @param flieName 文件名称
     * @return 检验list
     * @throws Exception 异常
     * @see
     * @since 1.0
     *//*
    public List<Map<String,String>> dataMapAnalysis(String flieName,String tableName)
            throws Exception {
        String prefix = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        if(!StringUtil.startsWith(flieName, prefix)
                && !StringUtil.startsWith(flieName, prefix.replace("/", File.separator))) {
            flieName = prefix + File.separator + flieName;
        }
        com.joyintech.framework.util.ExcelUtil excelUtil = com.joyintech.framework.util.ExcelUtil.build(flieName, null);

        List<List<String>> dataList = excelUtil.readToList(0);
        List<String> headList = dataList.get(1);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tableId", tableName);
        param.put("flag", false);
        List<ChbInfoMgrResultConfig> chbInfoMgrResultConfigList = chbInfoMgrResultConfigMapper.queryCloumnMapping(param);
        Map<String,String> map = new HashMap<>();
        chbInfoMgrResultConfigList.forEach(ele->map.put(ele.getColumnName().trim().replace("（","(").replace("）",")"),ele.getRealColumnId()));
        List<String> columnList = new ArrayList<>();
        for(int i=0;i<headList.size();++i){
            String columnName = headList.get(i);
            String temp = columnName.trim().replace("（","(").replace("）",")");
            columnName= map.get(temp);
            columnList.add(i,columnName);
        }
        List<Map<String,String>> list = new ArrayList<>();
        // 根据资产负债二级类别找一级类别
        Map<String,String> dictAssetType = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("linkDictCode", DictCode.资产_负债类别.getValue());
        List<SysDatadictListLink> listLink = assetInfoDetailMapper.getDataListLinkData(params);
        listLink.forEach(sysDatadictListLink -> {
            if(!((String)sysDatadictListLink.getLinkedItemCode()).equals("L01")){
                dictAssetType.put(sysDatadictListLink.getItemCode(),sysDatadictListLink.getLinkedItemCode());
            }
        });

        // 根据机构所属行业二级类别找一级类别
        Map<String,String> dictIndustry = new HashMap<>();
        params.put("linkDictCode", DictCode.机构所属行业一级分类.getValue());
        List<SysDatadictListLink> industryListLink = assetInfoDetailMapper.getDataListLinkData(params);
        industryListLink.forEach(sysDatadictListLink -> {
            dictIndustry.put(sysDatadictListLink.getItemCode(),sysDatadictListLink.getLinkedItemCode());
        });
        for(int i=2;i<dataList.size();++i){
            Map<String,String> subMap = new HashMap<>();
            List<String> subList = dataList.get(i);
            for(int j=0;j<subList.size();++j){
                if(StringUtil.isEmpty(subMap.get(columnList.get(j)))){
                    subMap.put(columnList.get(j),subList.get(j));
                }
            }
            subMap.put("ASSET_UUID", IDGenerator.generate(36));
            list.add(subMap);
        }
        for (ChbInfoMgrResultConfig config : chbInfoMgrResultConfigList) {
            if(!StringUtils.isEmpty(config.getResultdictCode())){
                for (Map<String, String> m : list) {
                    if(m.get(config.getRealColumnId())!=null){
                        if(config.getRealColumnId().equals("TARGET_TYPE3")){
                            try {
                                StringBuffer sb= new StringBuffer();
                                String[] split = m.get(config.getRealColumnId()).split(",");
                                Stream.of(split).forEach(e->sb.append(e.split(" ")[0]).append(","));
                                sb.deleteCharAt(sb.length() - 1);
                                m.put(config.getRealColumnId(),sb.toString());
                            } catch (Exception e) {
                                logger.error(e.getMessage(), e);
                            }

                        }else{
                            m.put(config.getRealColumnId(),m.get(config.getRealColumnId()).split(" ")[0]);
                        }
                    }

                }
            }
        }
        list.forEach(subMap->{
            subMap.put("ASSET_TYPE",dictAssetType.get(subMap.get("ASSET_TYPE2")));
            subMap.put("CAP_USE_INDU",dictIndustry.get(subMap.get("CAP_USE_INDU_LEVEL2")));
            subMap.put("INDUSTRY_SHARES",dictIndustry.get(subMap.get("INDUSTRY_SHARES2")));
            subMap.put("PRO_INDUSTRY",dictAssetType.get(subMap.get("PRO_INDUSTRY_LEVEL2")));
            subMap.put("INDUSTRY",dictAssetType.get(subMap.get("INDUSTRY_LEVEL2")));
            subMap.put("INDUSTRY1",dictAssetType.get(subMap.get("INDUSTRY_LEVEL2_TYPE")));
            subMap.put("CHB_ISSUER_ID",StringUtil.isNotEmpty(subMap.get("CHB_ISSUER_ID"))?subMap.get("CHB_ISSUER_ID").split(" ")[0]:null);
        });
        return list;
    }*/

/*    public List<ExcelValidDto> importData(String fileName) throws Exception{
        List<Map<String, String>> list = dataMapAnalysis(fileName,ChbCheckTableInfo.产品计划排期.getTableName());
        List<ExcelValidDto> listExcel = new ArrayList<ExcelValidDto>();
        svrAssetInfoFileImpService.importDB(listExcel,list,new InterfFile(),null);
        return listExcel;

    }*/

}
