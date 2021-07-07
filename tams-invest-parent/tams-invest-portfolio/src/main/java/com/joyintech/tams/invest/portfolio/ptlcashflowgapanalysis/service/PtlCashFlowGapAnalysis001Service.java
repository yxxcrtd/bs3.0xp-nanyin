/*
 * 文件名：PtlManagementInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.portfolio.ptlcashflowgapanalysis.service;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlcashflowgapanalysis.dto.PtlCashFlowGapAnalysis001Dto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo001Service;

import net.sf.jxls.transformer.XLSTransformer; 
 

/**
 * 单一发行人持仓分析Service
 * @author pancz
 * @version 1.0 2018年9月6日
 * @see PtlManagementInfo001Service
 * @since 1.0
 */
@Service
public class PtlCashFlowGapAnalysis001Service extends CommonExportService<PtlCashFlowGapAnalysis001Dto> {
	@Autowired(required = false) 
	private IPlatformDate iPlatformDate;
	
	/**
     * 模糊查询组合代码
     * @param param 模糊查询参数
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String,Object> map){
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlCashFlowGapAnalysis/getPortfolioId",map, SelectOptionDto.class);
    }
	
	/**
	 * 获取发行人信息
	 * 
	 * @param paraMap
	 * @return
	 * @throws Exception
	 */
	public List<SelectOptionDto> getFundAccount(Map<String,Object> map)
			throws Exception {
		map.put("userId", SecurityUtil.getLoginUser().getUserName());
		List<SelectOptionDto> fundAccountSelect = restTemplate.postForList(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlCashFlowGapAnalysis/getFundAccount",map,
				SelectOptionDto.class);
		return fundAccountSelect;
	}
	
	/**
     * 查询画面查询方法
     *
     * @param map 画面参数
     * @return 调用微服务
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlCashFlowGapAnalysis001Dto> selectForPage(Map<String, Object> map){
    	map.put("sysDate",iPlatformDate!=null?iPlatformDate.getSysDateStr():DateUtil.format(DateUtil.now()));
    	map.put("userId", SecurityUtil.getLoginUser().getUserName());
        Page<PtlCashFlowGapAnalysis001Dto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlCashFlowGapAnalysis/findPage",
                map, PtlCashFlowGapAnalysis001Dto.class);
        return page;
    }
 
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("cDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
        if(("ccy".equals(propName)) && value != null && value.equals("CNY")){
            cell.setCellValue("人民币");
        }else if (("ccy".equals(propName)) && value != null && value.equals("HKD")){
        	cell.setCellValue("港元");
        }else if (("ccy".equals(propName)) && value != null && value.equals("USD")){
        	cell.setCellValue("美元");
        }else if (("ccy".equals(propName)) && value != null && value.equals("EUR")){
        	cell.setCellValue("欧元");
        }else if (("ccy".equals(propName)) && value != null && value.equals("JPY")){
        	cell.setCellValue("日元");
        }else if (("ccy".equals(propName)) && value != null && value.equals("MOP")){
        	cell.setCellValue("澳门元");
        }else if (("ccy".equals(propName)) && value != null && value.equals("CHF")){
        	cell.setCellValue("瑞士法郎");
        }else if (("ccy".equals(propName)) && value != null && value.equals("GBP")){
        	cell.setCellValue("英镑");
        }else if (("ccy".equals(propName)) && value != null && value.equals("RUB")){
        	cell.setCellValue("俄罗斯卢布");
        }else if (("ccy".equals(propName)) && value != null && value.equals("CAD")){
        	cell.setCellValue("加拿大元");
        }else if (("ccy".equals(propName)) && value != null && value.equals("DKK")){
        	cell.setCellValue("丹麦克朗");
        }else if (("ccy".equals(propName)) && value != null && value.equals("AUD")){
        	cell.setCellValue("澳大利亚元");
        }else if (("ccy".equals(propName)) && value != null && value.equals("SEK")){
        	cell.setCellValue("瑞士克朗");
        }else if (("ccy".equals(propName)) && value != null && value.equals("SGD")){
        	cell.setCellValue("新加坡元");
        }else if (("ccy".equals(propName)) && value != null && value.equals("TWD")){
        	cell.setCellValue("新台币");
        }
//        if(("cashFlow".equals(propName)) && value != null){
//            cell.setCellValue(value.toString());
//        }
//        if(("inCashflow".equals(propName)) && value != null){
//            cell.setCellValue(value.toString());
//        }
//        if(("outCashflow".equals(propName)) && value != null){
//            cell.setCellValue(value.toString());
//        }
//        if(("netCashflow".equals(propName)) && value != null){
//            cell.setCellValue(value.toString());
//        }
    }
    
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	String tab = (String) exportParam.getSelectParam().get("tab");
    	if(StringUtil.equals("0", tab)){
    		//明细导出
    		exportParam.config("/com/joyintech/tams/invest/portfolio/ptlcashflowgapanalysis/conf/export_conf_detail.xml").dataSource(
                    DataSourceType.MASERT);
    	}else if(StringUtil.equals("1", tab)){
    		//汇总导出
    		exportParam.config("/com/joyintech/tams/invest/portfolio/ptlcashflowgapanalysis/conf/export_conf_total.xml").dataSource(
                    DataSourceType.MASERT);
    	}
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlCashFlowGapAnalysis001Dto> selectPageData(Map<String, Object> selectParam) {
    	return selectForPage(selectParam);
    }
    
    /**
     * 导出所有数据
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<PtlCashFlowGapAnalysis001Dto> selectListData(Map<String, Object> map) {
            map.put("sysDate",iPlatformDate!=null?iPlatformDate.getSysDateStr():DateUtil.format(DateUtil.now()));
            map.put("userId", SecurityUtil.getLoginUser().getUserName());
            List<PtlCashFlowGapAnalysis001Dto> page = restTemplate.postForList(
                    ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlCashFlowGapAnalysis/selectForList",
                    map, PtlCashFlowGapAnalysis001Dto.class);
            return page;
    }
    
    /**
     * <导出转移定价审批表>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> detailExport(Map<String, Object> selectParam) {
        Map<Object, Object> data = new HashMap<Object, Object>(); 
        List<PtlCashFlowGapAnalysis001Dto> list = this.selectListData(selectParam);
        List<Map<String, Object>> lists = getColumnMap(list);  
        data.put("lists", lists);  
        String sum = getColumnMapSum(list); 
        data.put("sum", sum);  
        String dateStr = DateUtil.format(DateUtil.now(),DateUtil.DEFAULT_DATE_FORMAT_EN);
        String termname =  "现金流缺口分析("+dateStr+")";
        data.put("termname", termname);
        return exportExcel(data, "PtlCashFlowGapAnalysisExport");
    }
     
    /**
     * 获取excel列表数据
     * 
     * @param prodDto
     * @param assetDto
     * @return
     * @see
     * @since 1.0
     */
    public List<Map<String, Object>> getColumnMap(List<PtlCashFlowGapAnalysis001Dto> daoList){
        List<Map<String, Object>> lists = new ArrayList<>();
        for(PtlCashFlowGapAnalysis001Dto dto:daoList){
            Map<String, Object> info = new HashMap<String, Object>(); 
            //日期
            info.put("cDate",DateUtil.format(dto.getcDate(), DateUtil.DEFAULT_DATE_FORMAT_EN)); 
            //组合代码 
            info.put("portfolioId",dto.getPortfolioId()); 
            //资金账户
            info.put("fundAccount",dto.getFundAccount()); 
            //现金流类型
            info.put("cashFlowTypeStr",dto.getCashFlowTypeStr()); 
            //资产标的
            info.put("assetSign",dto.getAssetSign()); 
            //现金流
            info.put("cashFlow",dto.getCashFlow()); 
            //币种
            info.put("ccyStr",dto.getCcyStr());  
            lists.add(info);
        }  
        return lists;
    }
   
    /**
     * 获取excel列表数据
     * 
     * @param prodDto
     * @param assetDto
     * @return
     * @see
     * @since 1.0
     */
    public String getColumnMapSum(List<PtlCashFlowGapAnalysis001Dto> daoList){
        BigDecimal Sum = BigDecimal.ZERO;
        for(PtlCashFlowGapAnalysis001Dto dto:daoList){ 
            Sum = ArithmeticUtil.doAdd(Sum,dto.getCashFlow());
        }  
        return Sum.toString();
    }
    
    /**
     * 生成excel方法
     * @param data
     * @param exceltemplate
     * @return
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(Map<Object, Object> data,
                                                         String exceltemplate) {
        String termname = data.get("termname").toString();
        String flieName =  termname +".xlsx";
        File depositfile = createFile(data, exceltemplate);
        return ExportUtil.export(flieName, depositfile, true);
    }
     
    /**
     * 创建file
     * @param data
     * @param exceltemplate
     * @return
     * @see
     * @since 1.0
     */
    public File createFile(Map<Object, Object> data, String exceltemplate) {
        String termname = data.get("termname").toString();
        String flieName =  termname + ".xlsx";
        File depositfile = null;
        OutputStream os = null;
        try {
            // 关联模板
            XLSTransformer transformer = new XLSTransformer();  
            // 传放地址
            String depositfilePath = CacheUtil.getSysParam(
                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            depositfile = new File(depositfilePath + File.separator + flieName);
            if (!depositfile.getParentFile().exists()) {
                depositfile.getParentFile().mkdirs();
            }
            String templateFile = exceltemplate + ".xlsx";
            // 模板输入流
            InputStream stream  = CommonExportService.class.getResourceAsStream(
                    "/tpl/" + templateFile);
            Workbook workbook = transformer.transformXLS(stream, data);
            os = new BufferedOutputStream(new FileOutputStream(depositfilePath + File.separator + flieName));
            workbook.write(os); 
        }
        catch (Exception e) { 
            throw new RuntimeException(e);
        }finally {
            if(os != null){
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return depositfile;
    } 
    
}
