/*
 * 文件名：RepFinProductPerDeclareService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.report.debtreport.repfinproductperdeclare.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.PaPrdIssueScheduleMapper;
import com.joyintech.tams.common.base.dao.PrdDeclareSchemeDocMapper;
import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.tams.common.base.entity.PrdDeclareSchemeDoc;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.report.debtreport.repfinproductperdeclare.dto.RepFinProductPerDeclareDto;
import com.joyintech.tams.invest.report.debtreport.repfinproductperissregist.dto.RepFinProductPerIssRegistDto;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 个人产品申报登记
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月21日
 * @see RepFinProductPerDeclareService
 * @since 1.0
 */
@Service
public class RepFinProductPerDeclareService extends CommonExportService<RepFinProductPerDeclareDto>{
    
    /**
     * 理财产品排期
     */
    @Autowired
    private PaPrdIssueScheduleMapper paPrdIssueScheduleMapper;
    
    /**
     * 申报方案附件
     */
    private PrdDeclareSchemeDocMapper prdDeclareSchemeDocMapper;
    
    /**
     * 
     * 产品名称下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param){
        return restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME
            + "/RepFinProductPerDeclare/getFinprodMarketId",param,
            SelectOptionDto.class);
    }
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<RepFinProductRegistCodeMngDto>
     * @see
     * @since 1.0
     */
    public Page<RepFinProductPerDeclareDto> select(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductPerDeclare/findPage",
            pageParam, RepFinProductPerDeclareDto.class);
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/report/debtreport/repfinproductperdeclare/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RepFinProductPerDeclareDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return restTemplate.postForPage(
                ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductPerDeclare/selectForExport",
                selectParam, RepFinProductPerDeclareDto.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 
     * 生成申报附件
     * <功能详细描述>
     * @throws Exception 
     * @return ResponseEntity<byte[]>
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> createDeclaration(Map<String, Object> param) throws Exception {
        RepFinProductPerDeclareDto temp = JsonUtils.json2Object((String)param.get("dtoStr"),RepFinProductPerDeclareDto.class);
        List<RepFinProductPerDeclareDto> selectedItem = temp.getSelectedItem();
        //申报附件方案
        RepFinProductPerDeclareDto repFinProductPerDeclare = selectedItem.get(0);
        List<File> arrFile = new ArrayList<File>();
        //获取理财产品排期
        PaPrdIssueSchedule paPrdIssueSchedule = paPrdIssueScheduleMapper.selectByPrimaryKey(selectedItem.get(0).getRepFinprodId());
        //获取申报附件
        List<PrdDeclareSchemeDoc> prdDeclareSchemeDocList = restTemplate.getForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductPerDeclare/getSeqno?schemeId="
            + paPrdIssueSchedule.getSchemeId(),
            PrdDeclareSchemeDoc.class);
        List<File> arrFileTemp = new ArrayList<File>();
        for(PrdDeclareSchemeDoc prdDeclareSchemeDoc:prdDeclareSchemeDocList){
            Map<String, Object> dataMap = new HashMap<String, Object>();
            //获取上传文件
            List<SysUploadFileInfo> sysUploadFileInfoList = restTemplate.getForList(
                ServiceApplication.REPORT_SERVICE_NAME + "/RepFinProductPerDeclare/getSysUploadFileInfo?bussinessId="
                + prdDeclareSchemeDoc.getSeqno(),
                SysUploadFileInfo.class);
            //
            Writer w = null;
            try {
                // 创建配置
                Configuration configuration = new Configuration(Configuration.getVersion());
                // 加载模板目录位置
                String templetFileFullPath = sysUploadFileInfoList.get(0).getPath();
                
                int suffixIndex = templetFileFullPath.lastIndexOf(".");
                //获取文件后缀
                String suffix = templetFileFullPath.substring(suffixIndex + 1);
                
                int splitIndex = templetFileFullPath.lastIndexOf("/");
                //获取文件夹
                String dirPath = templetFileFullPath.substring(0, splitIndex);
                //获取文件名称
                String ftlName = templetFileFullPath.substring(splitIndex + 1);
               
                String exportPath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
                if(suffix.equalsIgnoreCase("ftl")){
                	configuration.setDirectoryForTemplateLoading(new File(dirPath));
                    // 设置默认编码
                    configuration.setDefaultEncoding("UTF-8");
                    
                    
                    //产品名称
                    dataMap.put("prodName", paPrdIssueSchedule.getFinprodAbbr());
                    //产品代码
                    dataMap.put("prodId", paPrdIssueSchedule.getFinprodMarketId());
                    //产品期限
                    dataMap.put("term", DateUtil.getBetweenDayNumber(paPrdIssueSchedule.getVdate(), paPrdIssueSchedule.getMdate()));
                    dataMap.put("term2", dataMap.get("term"));
                    //产品成立日
                    dataMap.put("vdate", DateUtil.format(paPrdIssueSchedule.getVdate()));
                    //产品到期日
                    dataMap.put("mdate", DateUtil.format(paPrdIssueSchedule.getMdate()));
                    //产品认购期
                    if(StringUtil.isNotEmpty(paPrdIssueSchedule.getRaiseStrdate())){
                    	dataMap.put("ssDate", DateUtil.format(paPrdIssueSchedule.getRaiseStrdate()));
                        dataMap.put("seDate", DateUtil.format(paPrdIssueSchedule.getRaiseEnddate()));
                    }
                    else{
                    	dataMap.put("ssDate", "1");
                        dataMap.put("seDate", "1");
                    }
                    //认购起点金额
                    dataMap.put("startAmt", paPrdIssueSchedule.getToffStartLow());
                    //追加金额
                    dataMap.put("addAmt", paPrdIssueSchedule.getLowestAmt());
                    //认购起点金额（底层）/认购起点金额（中层）/认购起点金额（高层）
//                    dataMap.put("lowStartAmt", paPrdIssueSchedule.getToffStartLow());
//                    dataMap.put("middleStartAmt", paPrdIssueSchedule.getToffStartMid());
//                    dataMap.put("highStartAmt", paPrdIssueSchedule.getToffStartHig());
                    //客户收益率（底层）/客户收益率（中层）/客户收益率（高层
//                    dataMap.put("lowYield", paPrdIssueSchedule.getYieldLow());
//                    dataMap.put("middleYield", paPrdIssueSchedule.getYieldMid());
//                    dataMap.put("highYield", paPrdIssueSchedule.getYieldHig());
                    //投资者持有到期理财收益
//                    dataMap.put("intLowAmt", ArithmeticUtil.divide(ArithmeticUtil.multiply(paPrdIssueSchedule.getToffStartLow(),paPrdIssueSchedule.getYieldLow(),new BigDecimal(dataMap.get("term").toString())), new BigDecimal("365"), 4));
//                    dataMap.put("intMiddleAmt", ArithmeticUtil.divide(ArithmeticUtil.multiply(paPrdIssueSchedule.getToffStartMid(),paPrdIssueSchedule.getYieldMid(),new BigDecimal(dataMap.get("term").toString())), new BigDecimal("365"), 4));
//                    dataMap.put("intHighAmt", ArithmeticUtil.divide(ArithmeticUtil.multiply(paPrdIssueSchedule.getToffStartHig(),paPrdIssueSchedule.getYieldHig(),new BigDecimal(dataMap.get("term").toString())), new BigDecimal("365"), 4));
                    //计划发行量
                    dataMap.put("upperLimit", paPrdIssueSchedule.getRaiseMax());
//                    dataMap.put("lowerLimit", paPrdIssueSchedule.getToffStartLow());
                    //收益率测算
//                    dataMap.put("maxYield", repFinProductPerDeclare.getRepHighYield());
                    //销售手续费
//                    dataMap.put("sYield", paPrdIssueSchedule.getSaleFeeRate());
                    //资金保管费
//                    dataMap.put("hYield", paPrdIssueSchedule.getTrustFeeRate());
                    //资产管理费
//                    dataMap.put("amYield", paPrdIssueSchedule.getTrustFeeRate());
                    //预期收益率
                    dataMap.put("maxFYield", repFinProductPerDeclare.getRepHighYield());
                    //客户预期最高年化收益率
//                    dataMap.put("numBasis", "365");
                    //持有到期理财收益
//                    dataMap.put("intAmt", ArithmeticUtil.divide(
//                        ArithmeticUtil.multiply(new BigDecimal("1000000"),new BigDecimal(dataMap.get("maxFYield").toString()),new BigDecimal(dataMap.get("term").toString())),
//                        new BigDecimal("365"),4));
                    //持有到期理财收益
//                    dataMap.put("intAmt2", ArithmeticUtil.divide(
//                        ArithmeticUtil.multiply(new BigDecimal("1000000"),new BigDecimal(2),new BigDecimal(dataMap.get("term").toString())),
//                        new BigDecimal("365"),4));
                    //认购划款日
                    dataMap.put("subPayDate", DateUtil.format(paPrdIssueSchedule.getVdate()));
                    //产品认购
                    dataMap.put("prodSubScribe", DateUtil.format(paPrdIssueSchedule.getMdate()));
                    
                    // 加载模板
                    if (ftlName != null) {
                        Template template = configuration.getTemplate(ftlName, "UTF-8");
                        File f = new File(exportPath+"/"+ftlName);
                        w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
                        template.process(dataMap, w);
                        arrFileTemp.add(new File(exportPath+"/"+ftlName));
                    }
                }
                else{
                	BufferedInputStream bis =null;
                	OutputStream os=null;
                    try {
						bis = new BufferedInputStream(new FileInputStream(templetFileFullPath));
						byte[] bytes = new byte[bis.available()];
						os = new FileOutputStream(exportPath+"/"+ftlName);
						bis.read(bytes);
						os.write(bytes);
						bis.close();
						os.close();
						arrFileTemp.add(new File(exportPath+"/"+ftlName));
					} finally {
	                    if (bis != null) {
	                    	bis.close();
	                    }
	                    if (os != null) {
	                    	os.close();
	                    }
					}
                }
                
            }
            catch (Exception e) {
            	e.printStackTrace();
            }
            finally {
                // 关闭流
                try {
                    if (w != null) {
                        w.close();
                    }
                }
                catch (IOException ex) {
                    // Logger.getLogger(Java2Word.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String strFileName = "申报附件";
        ZipFileUtil.compressZip(new File(CacheUtil.getSysParam(
            GlobalConstant.COM_PROP_ACCESS_EXPORTPATH)+"/"+selectedItem.get(0).getRepFinprodInsId()+strFileName+".zip"), 
            false, arrFileTemp.toArray(new File[arrFile.size()]));
        
        
        //生成附件至浏览器
        return ExportUtil.export(selectedItem.get(0).getRepFinprodInsId()+strFileName+".zip", 
            new FileInputStream(CacheUtil.getSysParam(
                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH)+"/"+selectedItem.get(0).getRepFinprodInsId()+strFileName+".zip"));
    }
}
