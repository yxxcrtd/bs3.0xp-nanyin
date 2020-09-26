package com.joyintech.tams.svreport.chb.transmonthreport.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.ProdConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.service.SvrCommonExportService;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.system.instregbankcode.dao.SysInstRegBankCode001Mapper;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.svreport.chb.transmonthreport.dao.ChbTransMonthReportDetailMapper;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthAssetPosition;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthAssetRule;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthPrdPosition;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReport;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReportDetail;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReportPrdConstants;


/**
 *  理财产品转型情况月度统计表
 * @author Administrator
 *
 */
@Service
public class ChbTransMonthReport001Service extends SvrCommonExportService<ChbTransMonthReport> {
   
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    private ChbTransMonthReportDetailMapper chbTransMonthReportDetailMapper;
	@Autowired
    private  SysInstRegBankCode001Mapper sysInstRegBankCode001Mapper;
	@Override
    public ResponseEntity<byte[]> export(String pageId, Map<String, Object> selectParam){
    	@SuppressWarnings("unchecked")
        Map<String,Object> data = restTemplate.postForObject(
    	                SvrServiceApplication.CHB_SERVICE_NAME + "/ChbTransMonthReport/createData",selectParam,
    	                Map.class);
    	String termname = data.get("termid").toString();
    	String exceltemplate = "TransMonthreport";
	    String fileName = "理财产品转型情况月度统计表"+"_"+termname+".xls";

		try {
            return exportMonthReportExcel(data,exceltemplate,fileName);
        } catch (IOException e) {   
            logger.error("导出理财产品转型情况月度统计表错误:"+e.getMessage(),e);
            return null;
        }
    
    }
    public Map<Object, Object> createData(Map<String, Object> selectParam) throws ParseException{
    
    	Map<Object,Object> data  = new HashMap<Object,Object>();
        // 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
    	String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
    	
    	String endDate=selectParam.get("enddate").toString();
    	//获取本月第一天
    	String endMonthFirst=endDate+"-1";
    	SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sj.parse(endMonthFirst);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        // 获取本月的最后一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date endMonthEnd = calendar.getTime();

          
          int year = calendar.get(Calendar.YEAR);//获取年份
          int month = calendar.get(Calendar.MONTH) ;//获取月份
          logger.info("生成理财产品转型情况月度统计表："+year+"年"+month+"月");
          
          ChbTransMonthReport monthreport=new ChbTransMonthReport();
          monthreport.setStartdate(d);
          monthreport.setEnddate(endMonthEnd);
          monthreport.setTermid(endDate);
          // 发行机构代码取值
          Object org=selectParam.get("chbTransOrg");
          if(StringUtil.isNotEmpty(org)){
        	  monthreport.setIssuePartyId(org.toString());
          }
          
          //monthreport.setIssuePartyId(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID));
          //monthreport.setIssuePartyName(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_NAME));
         

		List<ChbTransMonthReportDetail> detailList=new ArrayList<ChbTransMonthReportDetail>();
		//产品端
    	List<ChbTransMonthReportDetail> prodList=genTransProdReport(monthreport);
    	detailList.addAll(prodList);
    	//资产端
    	List<ChbTransMonthReportDetail> assetList=genTransAssetReport(monthreport);
    	detailList.addAll(assetList);		
       
		for(ChbTransMonthReportDetail detail : detailList){
			dealProdData(detail,isShowZero);
			data.put(detail.getPropertycode(), divide10000ormul100(detail));
		}
		//多级法人改造 start add by hxy 20200629 
		if(MultilevelDataAuthorityUtil.isEnabled()) {
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("orgCode", monthreport.getIssuePartyId());
		     SysInstRegBankCode001Dto bankDto=sysInstRegBankCode001Mapper.queryForPageWithMode(param);
		    if(null!=bankDto){
				 monthreport.setIssuePartyId(bankDto.getRegistBankCode());
			     monthreport.setIssuePartyName(bankDto.getOrgName()); 
			 }
		}else{
		     //银行模式
		     SysInstRegBankCode001Dto bankDto=sysInstRegBankCode001Mapper.queryForPageWithMode(null);
			 if(null!=bankDto){
				 monthreport.setIssuePartyId(bankDto.getRegistBankCode());
			     monthreport.setIssuePartyName(bankDto.getOrgName()); 
			 }
		    
		}
       //多级法人改造 end 20200629
		 data.put("report", monthreport);
         data.put("termid", endDate);
    	return  data;
    }
   
    public 	List<ChbTransMonthReportDetail>  genTransProdReport(ChbTransMonthReport monthreport){
    	List<ChbTransMonthReportDetail> prodList= new ArrayList<ChbTransMonthReportDetail>();
		ChbTransMonthPrdPosition prod = new ChbTransMonthPrdPosition();
		prod.setStartdate(monthreport.getStartdate());
		prod.setEnddate(monthreport.getEnddate());
		//多级法人改造 start add by hxy 20200618 
		if(MultilevelDataAuthorityUtil.isEnabled()) {
			List<String> institutionlist=new ArrayList<String>();
			String institution=monthreport.getIssuePartyId();
    		institutionlist.add(institution);
		    prod.setInstitutionlist(institutionlist);
		}
		List<ChbTransMonthPrdPosition> listPrdPosition = chbTransMonthReportDetailMapper.selectPrdList(prod);
		// 公募理财产品
		ChbTransMonthReportDetail line1Sub1 =new ChbTransMonthReportDetail();
		line1Sub1.setPropertycode(ChbTransMonthReportPrdConstants.GMLCCP);// 1.1
		prodList.add(line1Sub1);
		// 私募理财产品
		ChbTransMonthReportDetail line1Sub2=new ChbTransMonthReportDetail();
		line1Sub2.setPropertycode(ChbTransMonthReportPrdConstants.SMLCCP);// 1.2
		prodList.add(line1Sub2);
		// 固定收益类
		ChbTransMonthReportDetail line2Sub1 = new ChbTransMonthReportDetail();
		line2Sub1.setPropertycode(ChbTransMonthReportPrdConstants.GDSYL);// 2.1		
		prodList.add(line2Sub1);
		// 权益类
		ChbTransMonthReportDetail line2Sub2 = new ChbTransMonthReportDetail();
		line2Sub2.setPropertycode(ChbTransMonthReportPrdConstants.QYL);//2.2		
		prodList.add(line2Sub2);
		// 商品及金融衍生品类
		ChbTransMonthReportDetail line2Sub3 = new ChbTransMonthReportDetail();
		line2Sub3.setPropertycode(ChbTransMonthReportPrdConstants.SPJJRYSPL);//2.3		
		prodList.add(line2Sub3);
		// 混合类
		ChbTransMonthReportDetail line2Sub4 = new ChbTransMonthReportDetail();
		line2Sub4.setPropertycode(ChbTransMonthReportPrdConstants.HHL);//2.4		
		prodList.add(line2Sub4);
		// 封闭式净值型
		ChbTransMonthReportDetail line3Sub1Sub1 = new ChbTransMonthReportDetail();
		line3Sub1Sub1.setPropertycode(ChbTransMonthReportPrdConstants.FBSJZX);//3.1.1		
		prodList.add(line3Sub1Sub1);
		// 开放式净值型	
		ChbTransMonthReportDetail line3Sub1Sub2 = new ChbTransMonthReportDetail();
		line3Sub1Sub2.setPropertycode(ChbTransMonthReportPrdConstants.KFSJZX);//3.1.2		
		prodList.add(line3Sub1Sub2);
		// 现金管理类产品
		ChbTransMonthReportDetail line3Sub1Sub2a = new ChbTransMonthReportDetail();
		line3Sub1Sub2a.setPropertycode(ChbTransMonthReportPrdConstants.XJGLLCP);// 其中：3.1.2.a	
		prodList.add(line3Sub1Sub2a);
		// 封闭期在半年以上的定期开放式产品		
		ChbTransMonthReportDetail line3Sub1Sub2b = new ChbTransMonthReportDetail();
		line3Sub1Sub2b.setPropertycode(ChbTransMonthReportPrdConstants.FBQZBNYSDDQKFCP);// 其中3.1.2.b	
		prodList.add(line3Sub1Sub2b);
		// 封闭式非净值型		
		ChbTransMonthReportDetail line3Sub2Sub1 = new ChbTransMonthReportDetail();
		line3Sub2Sub1.setPropertycode(ChbTransMonthReportPrdConstants.FBSFJZX);// 3.2.1
		prodList.add(line3Sub2Sub1);
		// 开放式非净值型
		ChbTransMonthReportDetail line3Sub2Sub2 = new ChbTransMonthReportDetail();
		line3Sub2Sub2.setPropertycode(ChbTransMonthReportPrdConstants.KFSFJZX);// 3.2.2
		prodList.add(line3Sub2Sub2);
		// 3个月（含）以内	
		ChbTransMonthReportDetail line4Sub1Sub1 = new ChbTransMonthReportDetail();
		line4Sub1Sub1.setPropertycode(ChbTransMonthReportPrdConstants.FBSSGYYN);// 4.1.1
		prodList.add(line4Sub1Sub1);
		// 3-6个月(含)		
		ChbTransMonthReportDetail line4Sub1Sub2 = new ChbTransMonthReportDetail();
		line4Sub1Sub2.setPropertycode(ChbTransMonthReportPrdConstants.FBSSLGY);// 4.1.2
		prodList.add(line4Sub1Sub2);
		// 6-12个月（含）		
		ChbTransMonthReportDetail line4Sub1Sub3 = new ChbTransMonthReportDetail();
		line4Sub1Sub3.setPropertycode(ChbTransMonthReportPrdConstants.FBSLSGY);// 4.1.3
		prodList.add(line4Sub1Sub3);
		// 1-3年（含）		
		ChbTransMonthReportDetail line4Sub1Sub4 = new ChbTransMonthReportDetail();
		line4Sub1Sub4.setPropertycode(ChbTransMonthReportPrdConstants.FBSYSN);// 4.1.4
		prodList.add(line4Sub1Sub4);
		// 3年以上	
		ChbTransMonthReportDetail line4Sub1Sub5 = new ChbTransMonthReportDetail();
		line4Sub1Sub5.setPropertycode(ChbTransMonthReportPrdConstants.FBSSNYS);// 4.1.5
		prodList.add(line4Sub1Sub5);
		// 每日		
		ChbTransMonthReportDetail line4Sub2Sub1 = new ChbTransMonthReportDetail();
		line4Sub2Sub1.setPropertycode(ChbTransMonthReportPrdConstants.KFSMR);// 4.2.1
		prodList.add(line4Sub2Sub1);
		// 7天（含）以内	
		ChbTransMonthReportDetail line4Sub2Sub2 = new ChbTransMonthReportDetail();
		line4Sub2Sub2.setPropertycode(ChbTransMonthReportPrdConstants.KFSQT);// 4.2.2
		prodList.add(line4Sub2Sub2);
		// 7天-1个月（含）	
		ChbTransMonthReportDetail line4Sub2Sub3 = new ChbTransMonthReportDetail();
		line4Sub2Sub3.setPropertycode(ChbTransMonthReportPrdConstants.KFSQTYGY);// 4.2.3
		prodList.add(line4Sub2Sub3);
		// 1-3个月（含）	
		ChbTransMonthReportDetail line4Sub2Sub4 = new ChbTransMonthReportDetail();
		line4Sub2Sub4.setPropertycode(ChbTransMonthReportPrdConstants.KFSYSY);// 4.2.4
		prodList.add(line4Sub2Sub4);
		// 3-6个月(含)	
		ChbTransMonthReportDetail line4Sub2Sub5 = new ChbTransMonthReportDetail();
		line4Sub2Sub5.setPropertycode(ChbTransMonthReportPrdConstants.KFSSLY);// 4.2.5
		prodList.add(line4Sub2Sub5);
		// 6-12个月（含）	
		ChbTransMonthReportDetail line4Sub2Sub6 = new ChbTransMonthReportDetail();
		line4Sub2Sub6.setPropertycode(ChbTransMonthReportPrdConstants.KFSLSY);// 4.2.6
		prodList.add(line4Sub2Sub6);
		// 1-3年（含）	
		ChbTransMonthReportDetail line4Sub2Sub7 = new ChbTransMonthReportDetail();
		line4Sub2Sub7.setPropertycode(ChbTransMonthReportPrdConstants.KFSYSN);// 4.2.7
		prodList.add(line4Sub2Sub7);
		// 3年以上
		ChbTransMonthReportDetail line4Sub2Sub8 = new ChbTransMonthReportDetail();
		line4Sub2Sub8.setPropertycode(ChbTransMonthReportPrdConstants.KFSSNYS);// 4.2.8
		prodList.add(line4Sub2Sub8);
		ChbTransMonthReportDetail lineSum1Sub3 = new ChbTransMonthReportDetail();
		lineSum1Sub3.setPropertycode(ChbTransMonthReportPrdConstants.SUM_MJFS);// 合计:1.按募集方式划分
		prodList.add(lineSum1Sub3);
		ChbTransMonthReportDetail lineSum2Sub5 = new ChbTransMonthReportDetail();
		lineSum2Sub5.setPropertycode(ChbTransMonthReportPrdConstants.SUM_TZXZ);// 合计:2.按投资性质划分
		prodList.add(lineSum2Sub5);
		ChbTransMonthReportDetail lineSum3Sub3 = new ChbTransMonthReportDetail();
		lineSum3Sub3.setPropertycode(ChbTransMonthReportPrdConstants.SUM_YZMS);// 合计:3.按产品运作模式划分
		prodList.add(lineSum3Sub3);
		ChbTransMonthReportDetail lineSum4Sub1Sub6 = new ChbTransMonthReportDetail();
		lineSum4Sub1Sub6.setPropertycode(ChbTransMonthReportPrdConstants.SUM_FBSCP);// 合计:4.1
		prodList.add(lineSum4Sub1Sub6);
		// 封闭式产品
		ChbTransMonthReportDetail lineSum4Sub2Sub9 = new ChbTransMonthReportDetail();
		lineSum4Sub2Sub9.setPropertycode(ChbTransMonthReportPrdConstants.SUM_KFSCP);// 合计:4.2
		prodList.add(lineSum4Sub2Sub9);
		ChbTransMonthReportDetail lineSum4Sub3 = new ChbTransMonthReportDetail();
		lineSum4Sub3.setPropertycode(ChbTransMonthReportPrdConstants.SUM_QX);// 合计:4.按期限划分
		prodList.add(lineSum4Sub3);
		for(ChbTransMonthReportDetail detail:prodList){
			//初始化赋值
			detail.setOldprodbalance(BigDecimal.ZERO);
			detail.setOldprodjqprice(BigDecimal.ZERO);
			detail.setOldprodbalprice(BigDecimal.ZERO);
			detail.setNewprodbalance(BigDecimal.ZERO);
			detail.setNewprodjqprice(BigDecimal.ZERO);
			detail.setNewprodbalprice(BigDecimal.ZERO);
		}
		for (ChbTransMonthPrdPosition pos : listPrdPosition) {
			// 第1行 1.1 公募理财产品
			if (ProdConstants.RAISEMODE_GM.equals(pos.getRaisemode())) { // 公募
				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
					//老产品-预期收益型
					line1Sub1.setOldprodbalance(line1Sub1.getOldprodbalance().add(pos.getEndamtsumOth()));
					line1Sub1.setOldprodbalprice(line1Sub1.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
					//新产品-净值型、货币型
					line1Sub1.setNewprodbalance(line1Sub1.getNewprodbalance().add(pos.getEndamtsumOth()));
					line1Sub1.setNewprodbalprice(line1Sub1.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}
			}
            // 第二行 1.2 私募理财产品
			else {
				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
					//老产品-预期收益型
					line1Sub2.setOldprodbalance(line1Sub2.getOldprodbalance().add(pos.getEndamtsumOth()));
					line1Sub2.setOldprodbalprice(line1Sub2.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
					//新产品-净值型、货币型
					line1Sub2.setNewprodbalance(line1Sub2.getNewprodbalance().add(pos.getEndamtsumOth()));
					line1Sub2.setNewprodbalprice(line1Sub2.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}
			}

			// 第三行 2.1 固定收益类
			if (ProdConstants.IVSTORIENTATION_GDSY.equals(pos.getIvstorientation())) { // 固定收益类
				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
					//老产品-预期收益型
					line2Sub1.setOldprodbalance(line2Sub1.getOldprodbalance().add(pos.getEndamtsumOth()));
					line2Sub1.setOldprodbalprice(line2Sub1.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
					//新产品-净值型、货币型
					line2Sub1.setNewprodbalance(line2Sub1.getNewprodbalance().add(pos.getEndamtsumOth()));
					line2Sub1.setNewprodbalprice(line2Sub1.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}
			}
			// 第四行 2.2 权益类
			else if (ProdConstants.IVSTORIENTATION_QYL.equals(pos.getIvstorientation())) { // 权益类
				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
					//老产品-预期收益型
					line2Sub2.setOldprodbalance(line2Sub2.getOldprodbalance().add(pos.getEndamtsumOth()));
					line2Sub2.setOldprodbalprice(line2Sub2.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
					//新产品-净值型、货币型
					line2Sub2.setNewprodbalance(line2Sub2.getNewprodbalance().add(pos.getEndamtsumOth()));
					line2Sub2.setNewprodbalprice(line2Sub2.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}
			}

			// 第五行 2.3 商品及金融衍生品类
			else if (ProdConstants.IVSTORIENTATION_SPJRP.equals(pos.getIvstorientation())) { // 商品及金融衍生品类
				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
					//老产品-预期收益型
					line2Sub3.setOldprodbalance(line2Sub3.getOldprodbalance().add(pos.getEndamtsumOth()));
					line2Sub3.setOldprodbalprice(line2Sub3.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
					//新产品-净值型、货币型
					line2Sub3.setNewprodbalance(line2Sub3.getNewprodbalance().add(pos.getEndamtsumOth()));
					line2Sub3.setNewprodbalprice(line2Sub3.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}
			}

			// 第六行 2.4 混合类
			else if (ProdConstants.IVSTORIENTATION_HHL.equals(pos.getIvstorientation())) { // 混合类
				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
					//老产品-预期收益型
					line2Sub4.setOldprodbalance(line2Sub4.getOldprodbalance().add(pos.getEndamtsumOth()));
					line2Sub4.setOldprodbalprice(line2Sub4.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
					//新产品-净值型、货币型
					line2Sub4.setNewprodbalance(line2Sub4.getNewprodbalance().add(pos.getEndamtsumOth()));
					line2Sub4.setNewprodbalprice(line2Sub4.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}
         	}
			// 第七行 3.1.1 封闭式净值型
			if (ProdConstants.OPERATEMODE_FBSJZX.equals(pos.getOperateMode())) { // 封闭式净值型
				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
					//老产品-预期收益型
					line3Sub1Sub1.setOldprodbalance(line3Sub1Sub1.getOldprodbalance().add(pos.getEndamtsumOth()));
					line3Sub1Sub1.setOldprodbalprice(line3Sub1Sub1.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
					//新产品-净值型、货币型
					line3Sub1Sub1.setNewprodbalance(line3Sub1Sub1.getNewprodbalance().add(pos.getEndamtsumOth()));
					line3Sub1Sub1.setNewprodbalprice(line3Sub1Sub1.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}
			}

			// 第8行 3.1.2 开放式净值型
			else if (ProdConstants.OPERATEMODE_KFSJZX.equals(pos.getOperateMode())) { // 开放式净值型
				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
					//老产品-预期收益型
					line3Sub1Sub2.setOldprodbalance(line3Sub1Sub2.getOldprodbalance().add(pos.getEndamtsumOth()));
					line3Sub1Sub2.setOldprodbalprice(line3Sub1Sub2.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
					//新产品-净值型、货币型
					line3Sub1Sub2.setNewprodbalance(line3Sub1Sub2.getNewprodbalance().add(pos.getEndamtsumOth()));
					line3Sub1Sub2.setNewprodbalprice(line3Sub1Sub2.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}
				// 第9行 3.1.2.a 现金管理类产品
				if ("01".equals(pos.getCashmanacate())) { // 现金管理类产品 非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line3Sub1Sub2a.setOldprodbalance(line3Sub1Sub2a.getOldprodbalance().add(pos.getEndamtsumOth()));
						line3Sub1Sub2a.setOldprodbalprice(line3Sub1Sub2a.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line3Sub1Sub2a.setNewprodbalance(line3Sub1Sub2a.getNewprodbalance().add(pos.getEndamtsumOth()));
						line3Sub1Sub2a.setNewprodbalprice(line3Sub1Sub2a.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}										
				
				}

				// 第10行 3.1.2.b 封闭期在半年以上的定期开放式产品
				if ("02".equals(pos.getOpenduration())) { // 封闭期在半年以上的定期开放式产品
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line3Sub1Sub2b.setOldprodbalance(line3Sub1Sub2b.getOldprodbalance().add(pos.getEndamtsumOth()));
						line3Sub1Sub2b.setOldprodbalprice(line3Sub1Sub2b.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line3Sub1Sub2b.setNewprodbalance(line3Sub1Sub2b.getNewprodbalance().add(pos.getEndamtsumOth()));
						line3Sub1Sub2b.setNewprodbalprice(line3Sub1Sub2b.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}		
				}
			}

			// 第11行 3.2.1 封闭式非净值型
			else if (ProdConstants.OPERATEMODE_FBSFJZX.equals(pos.getOperateMode())) { // 封闭式非净值型
				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
					//老产品-预期收益型
					line3Sub2Sub1.setOldprodbalance(line3Sub2Sub1.getOldprodbalance().add(pos.getEndamtsumOth()));
					line3Sub2Sub1.setOldprodbalprice(line3Sub2Sub1.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
					//新产品-净值型、货币型
					line3Sub2Sub1.setNewprodbalance(line3Sub2Sub1.getNewprodbalance().add(pos.getEndamtsumOth()));
					line3Sub2Sub1.setNewprodbalprice(line3Sub2Sub1.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}	
				
			}

			// 第12行 3.2.2 开放式非净值型
			else if (ProdConstants.OPERATEMODE_KFSFJZX.equals(pos.getOperateMode())) { // 开放式非净值型
				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
					//老产品-预期收益型
					line3Sub2Sub2.setOldprodbalance(line3Sub2Sub2.getOldprodbalance().add(pos.getEndamtsumOth()));
					line3Sub2Sub2.setOldprodbalprice(line3Sub2Sub2.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
					//新产品-净值型、货币型
					line3Sub2Sub2.setNewprodbalance(line3Sub2Sub2.getNewprodbalance().add(pos.getEndamtsumOth()));
					line3Sub2Sub2.setNewprodbalprice(line3Sub2Sub2.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
				}	
    		}
			

			if (ProdConstants.OPERATEMODE_FBSJZX.equals(pos.getOperateMode())
					|| ProdConstants.OPERATEMODE_FBSFJZX.equals(pos.getOperateMode())) { // 封闭式

				// 第13行 4.1.1 3个月（含）以内
				if (ProdConstants.TERMTYPE_U1D.equals(pos.getTermtype())
						|| ProdConstants.TERMTYPE_U7D.equals(pos.getTermtype())
						|| ProdConstants.TERMTYPE_U1M.equals(pos.getTermtype())
						|| ProdConstants.TERMTYPE_U3M.equals(pos.getTermtype())) { // 3个月以内
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub1Sub1.setOldprodbalance(line4Sub1Sub1.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub1Sub1.setOldprodbalprice(line4Sub1Sub1.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub1Sub1.setNewprodbalance(line4Sub1Sub1.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub1Sub1.setNewprodbalprice(line4Sub1Sub1.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}	

				}

				// 第14行 4.1.2 3-6个月(含)
				else if (ProdConstants.TERMTYPE_U6M.equals(pos.getTermtype())) { // 封闭式
																					// 3-6个月(含)
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub1Sub2.setOldprodbalance(line4Sub1Sub2.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub1Sub2.setOldprodbalprice(line4Sub1Sub2.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub1Sub2.setNewprodbalance(line4Sub1Sub2.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub1Sub2.setNewprodbalprice(line4Sub1Sub2.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}																// 非保本
			
				}

				// 第15行
				else if (ProdConstants.TERMTYPE_U12M.equals(pos.getTermtype())) { // 封闭式	
					                                                                  // 6-12个月（含）
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub1Sub3.setOldprodbalance(line4Sub1Sub3.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub1Sub3.setOldprodbalprice(line4Sub1Sub3.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub1Sub3.setNewprodbalance(line4Sub1Sub3.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub1Sub3.setNewprodbalprice(line4Sub1Sub3.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}																	
				}

				// 第16行
				else if (ProdConstants.TERMTYPE_U3Y.equals(pos.getTermtype())) { // 封闭式
																					// 1-3年（含）
																					// 非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub1Sub4.setOldprodbalance(line4Sub1Sub4.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub1Sub4.setOldprodbalprice(line4Sub1Sub4.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub1Sub4.setNewprodbalance(line4Sub1Sub4.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub1Sub4.setNewprodbalprice(line4Sub1Sub4.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}	
				}

				// 第17行
				else if (ProdConstants.TERMTYPE_B3Y.equals(pos.getTermtype())) { // 封闭式
																					// 3年以上
																					// 非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub1Sub5.setOldprodbalance(line4Sub1Sub5.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub1Sub5.setOldprodbalprice(line4Sub1Sub5.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub1Sub5.setNewprodbalance(line4Sub1Sub5.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub1Sub5.setNewprodbalprice(line4Sub1Sub5.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}	
				}
			}

			else if (ProdConstants.OPERATEMODE_KFSJZX.equals(pos.getOperateMode())
					|| ProdConstants.OPERATEMODE_KFSFJZX.equals(pos.getOperateMode())) { // 开放式
				// 第18行
				if (ProdConstants.TERMTYPE_U1D.equals(pos.getTermtype())) { // 开放式
																			// 每日
																			// 非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub2Sub1.setOldprodbalance(line4Sub2Sub1.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub1.setOldprodbalprice(line4Sub2Sub1.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub2Sub1.setNewprodbalance(line4Sub2Sub1.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub1.setNewprodbalprice(line4Sub2Sub1.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}	
				}

				// 第19行
				else if (ProdConstants.TERMTYPE_U7D.equals(pos.getTermtype())) { // 开放式
																					// 7天（含）以内
																					// 非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub2Sub2.setOldprodbalance(line4Sub2Sub2.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub2.setOldprodbalprice(line4Sub2Sub2.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub2Sub2.setNewprodbalance(line4Sub2Sub2.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub2.setNewprodbalprice(line4Sub2Sub2.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}
				}

				// 第20行
				else if (ProdConstants.TERMTYPE_U1M.equals(pos.getTermtype())) { // 开放式
																					// 7天-1个月（含）
																					// 非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub2Sub3.setOldprodbalance(line4Sub2Sub3.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub3.setOldprodbalprice(line4Sub2Sub3.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub2Sub3.setNewprodbalance(line4Sub2Sub3.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub3.setNewprodbalprice(line4Sub2Sub3.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}
				}

				// 第21行
				else if (ProdConstants.TERMTYPE_U3M.equals(pos.getTermtype())) { // 开饭式
																					// 1-3个月（含）
																					// 非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub2Sub4.setOldprodbalance(line4Sub2Sub4.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub4.setOldprodbalprice(line4Sub2Sub4.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub2Sub4.setNewprodbalance(line4Sub2Sub4.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub4.setNewprodbalprice(line4Sub2Sub4.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}
				}

				// 第22行
				else if (ProdConstants.TERMTYPE_U6M.equals(pos.getTermtype())) { // 开放式
																					// 3-6个月(含)非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub2Sub5.setOldprodbalance(line4Sub2Sub5.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub5.setOldprodbalprice(line4Sub2Sub5.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub2Sub5.setNewprodbalance(line4Sub2Sub5.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub5.setNewprodbalprice(line4Sub2Sub5.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}
				}

				// 第23行
				else if (ProdConstants.TERMTYPE_U12M.equals(pos.getTermtype())) { // 开放式
																					// 6-12个月（含）
																					// 非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub2Sub6.setOldprodbalance(line4Sub2Sub6.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub6.setOldprodbalprice(line4Sub2Sub6.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub2Sub6.setNewprodbalance(line4Sub2Sub6.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub6.setNewprodbalprice(line4Sub2Sub6.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}
				}

				// 第24行
				else if (ProdConstants.TERMTYPE_U3Y.equals(pos.getTermtype())) { // 开放式
																					// 1-3年（含）
																					// 非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub2Sub7.setOldprodbalance(line4Sub2Sub7.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub7.setOldprodbalprice(line4Sub2Sub7.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub2Sub7.setNewprodbalance(line4Sub2Sub7.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub7.setNewprodbalprice(line4Sub2Sub7.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}
					
				}

				// 第25行
				else if (ProdConstants.TERMTYPE_B3Y.equals(pos.getTermtype())) { // 开放式
																					// 3年以上
																					// 非保本
					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
						//老产品-预期收益型
						line4Sub2Sub8.setOldprodbalance(line4Sub2Sub8.getOldprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub8.setOldprodbalprice(line4Sub2Sub8.getOldprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
						//新产品-净值型、货币型
						line4Sub2Sub8.setNewprodbalance(line4Sub2Sub8.getNewprodbalance().add(pos.getEndamtsumOth()));
						line4Sub2Sub8.setNewprodbalprice(line4Sub2Sub8.getNewprodbalprice().add(pos.getEndamtsumOth().multiply(pos.getProdprice())));
					}
					
				}
			}
			
		}
		// 合计 1.按募集方式划分
		if(null==line1Sub1.getOldprodbalance() || line1Sub1.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line1Sub1.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line1Sub1.setOldprodjqprice(line1Sub1.getOldprodbalprice().divide(line1Sub1.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line1Sub1.getNewprodbalance() || line1Sub1.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line1Sub1.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line1Sub1.setNewprodjqprice(line1Sub1.getNewprodbalprice().divide(line1Sub1.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}
		if(null==line1Sub2.getOldprodbalance() || line1Sub2.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line1Sub2.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line1Sub2.setOldprodjqprice(line1Sub2.getOldprodbalprice().divide(line1Sub2.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line1Sub2.getNewprodbalance() || line1Sub2.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line1Sub2.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line1Sub2.setNewprodjqprice(line1Sub2.getNewprodbalprice().divide(line1Sub2.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}
		lineSum1Sub3.setOldprodbalance(line1Sub1.getOldprodbalance().add(line1Sub2.getOldprodbalance()));
		lineSum1Sub3.setNewprodbalance(line1Sub1.getNewprodbalance().add(line1Sub2.getNewprodbalance()));
		if(null==lineSum1Sub3.getOldprodbalance() || lineSum1Sub3.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum1Sub3.setOldprodjqprice(BigDecimal.ZERO);
        }else{
        	lineSum1Sub3.setOldprodjqprice((line1Sub1.getOldprodbalprice().add(line1Sub2.getOldprodbalprice())).divide(lineSum1Sub3.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==lineSum1Sub3.getNewprodbalance() || lineSum1Sub3.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum1Sub3.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			lineSum1Sub3.setNewprodjqprice((line1Sub1.getNewprodbalprice().add(line1Sub2.getNewprodbalprice())).divide(lineSum1Sub3.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}
		// 合计:2.按投资性质划分
		if(null==line2Sub1.getOldprodbalance() || line2Sub1.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line2Sub1.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line2Sub1.setOldprodjqprice(line2Sub1.getOldprodbalprice().divide(line2Sub1.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line2Sub1.getNewprodbalance() || line2Sub1.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line2Sub1.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line2Sub1.setNewprodjqprice(line2Sub1.getNewprodbalprice().divide(line2Sub1.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}
		if(null==line2Sub2.getOldprodbalance() || line2Sub2.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line2Sub2.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line2Sub2.setOldprodjqprice(line2Sub2.getOldprodbalprice().divide(line2Sub2.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line2Sub2.getNewprodbalance() || line2Sub2.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line2Sub2.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line2Sub2.setNewprodjqprice(line2Sub2.getNewprodbalprice().divide(line2Sub2.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}
		if(null==line2Sub3.getOldprodbalance() || line2Sub3.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line2Sub3.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line2Sub3.setOldprodjqprice(line2Sub3.getOldprodbalprice().divide(line2Sub3.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line2Sub3.getNewprodbalance() || line2Sub3.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line2Sub3.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line2Sub3.setNewprodjqprice(line2Sub3.getNewprodbalprice().divide(line2Sub3.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}
		if(null==line2Sub4.getOldprodbalance() || line2Sub4.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line2Sub4.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line2Sub4.setOldprodjqprice(line2Sub4.getOldprodbalprice().divide(line2Sub4.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line2Sub4.getNewprodbalance() || line2Sub4.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line2Sub4.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line2Sub4.setNewprodjqprice(line2Sub4.getNewprodbalprice().divide(line2Sub4.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}
		lineSum2Sub5.setOldprodbalance(line2Sub1.getOldprodbalance().add(line2Sub2.getOldprodbalance()).add(line2Sub3.getOldprodbalance()).add(line2Sub4.getOldprodbalance()));
		lineSum2Sub5.setNewprodbalance(line2Sub1.getNewprodbalance().add(line2Sub2.getNewprodbalance()).add(line2Sub3.getNewprodbalance()).add(line2Sub4.getNewprodbalance()));
		if(null==lineSum2Sub5.getOldprodbalance() || lineSum2Sub5.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum2Sub5.setOldprodjqprice(BigDecimal.ZERO);
        }else{
        	lineSum2Sub5.setOldprodjqprice((line2Sub1.getOldprodbalprice().add(line2Sub2.getOldprodbalprice()).add(line2Sub3.getOldprodbalprice()).add(line2Sub4.getOldprodbalprice())).divide(lineSum2Sub5.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==lineSum2Sub5.getNewprodbalance() || lineSum2Sub5.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum2Sub5.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			lineSum2Sub5.setNewprodjqprice((line2Sub1.getNewprodbalprice().add(line2Sub2.getNewprodbalprice()).add(line2Sub3.getNewprodbalprice()).add(line2Sub4.getNewprodbalprice())).divide(lineSum2Sub5.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}		
		// 合计:3.按产品运作模式划分
		if(null==line3Sub1Sub1.getOldprodbalance() || line3Sub1Sub1.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub1Sub1.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line3Sub1Sub1.setOldprodjqprice(line3Sub1Sub1.getOldprodbalprice().divide(line3Sub1Sub1.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line3Sub1Sub1.getNewprodbalance() || line3Sub1Sub1.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub1Sub1.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line3Sub1Sub1.setNewprodjqprice(line3Sub1Sub1.getNewprodbalprice().divide(line3Sub1Sub1.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}		
		if(null==line3Sub1Sub2.getOldprodbalance() || line3Sub1Sub2.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub1Sub2.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line3Sub1Sub2.setOldprodjqprice(line3Sub1Sub2.getOldprodbalprice().divide(line3Sub1Sub2.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line3Sub1Sub2.getNewprodbalance() || line3Sub1Sub2.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub1Sub2.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line3Sub1Sub2.setNewprodjqprice(line3Sub1Sub2.getNewprodbalprice().divide(line3Sub1Sub2.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line3Sub1Sub2a.getOldprodbalance() || line3Sub1Sub2a.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub1Sub2a.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line3Sub1Sub2a.setOldprodjqprice(line3Sub1Sub2a.getOldprodbalprice().divide(line3Sub1Sub2a.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line3Sub1Sub2a.getNewprodbalance() || line3Sub1Sub2a.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub1Sub2a.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line3Sub1Sub2a.setNewprodjqprice(line3Sub1Sub2a.getNewprodbalprice().divide(line3Sub1Sub2a.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line3Sub1Sub2b.getOldprodbalance() || line3Sub1Sub2b.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub1Sub2b.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line3Sub1Sub2b.setOldprodjqprice(line3Sub1Sub2b.getOldprodbalprice().divide(line3Sub1Sub2b.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line3Sub1Sub2b.getNewprodbalance() || line3Sub1Sub2b.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub1Sub2b.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line3Sub1Sub2b.setNewprodjqprice(line3Sub1Sub2b.getNewprodbalprice().divide(line3Sub1Sub2b.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}
		if(null==line3Sub2Sub1.getOldprodbalance() || line3Sub2Sub1.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub2Sub1.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line3Sub2Sub1.setOldprodjqprice(line3Sub2Sub1.getOldprodbalprice().divide(line3Sub2Sub1.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line3Sub2Sub1.getNewprodbalance() || line3Sub2Sub1.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub2Sub1.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line3Sub2Sub1.setNewprodjqprice(line3Sub2Sub1.getNewprodbalprice().divide(line3Sub2Sub1.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line3Sub2Sub2.getOldprodbalance() || line3Sub2Sub2.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub2Sub2.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line3Sub2Sub2.setOldprodjqprice(line3Sub2Sub2.getOldprodbalprice().divide(line3Sub2Sub2.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line3Sub2Sub2.getNewprodbalance() || line3Sub2Sub2.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line3Sub2Sub2.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line3Sub2Sub2.setNewprodjqprice(line3Sub2Sub2.getNewprodbalprice().divide(line3Sub2Sub2.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		lineSum3Sub3.setOldprodbalance(line3Sub1Sub1.getOldprodbalance().add(line3Sub1Sub2.getOldprodbalance()).add(line3Sub2Sub1.getOldprodbalance()).add(line3Sub2Sub2.getOldprodbalance()));
		lineSum3Sub3.setNewprodbalance(line3Sub1Sub1.getNewprodbalance().add(line3Sub1Sub2.getNewprodbalance()).add(line3Sub2Sub1.getNewprodbalance()).add(line3Sub2Sub2.getNewprodbalance()));
		if(null==lineSum3Sub3.getOldprodbalance() || lineSum3Sub3.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum3Sub3.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			lineSum3Sub3.setOldprodjqprice((line3Sub1Sub1.getOldprodbalprice().add(line3Sub1Sub2.getOldprodbalprice()).add(line3Sub2Sub1.getOldprodbalprice()).add(line3Sub2Sub2.getOldprodbalprice())).divide(lineSum3Sub3.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==lineSum3Sub3.getNewprodbalance() || lineSum3Sub3.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum3Sub3.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			lineSum3Sub3.setNewprodjqprice((line3Sub1Sub1.getNewprodbalprice().add(line3Sub1Sub2.getNewprodbalprice()).add(line3Sub2Sub1.getNewprodbalprice()).add(line3Sub2Sub2.getNewprodbalprice())).divide(lineSum3Sub3.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		// 合计:4.1 封闭式产品
		if(null==line4Sub1Sub1.getOldprodbalance() || line4Sub1Sub1.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub1Sub1.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub1Sub1.setOldprodjqprice(line4Sub1Sub1.getOldprodbalprice().divide(line4Sub1Sub1.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub1Sub1.getNewprodbalance() || line4Sub1Sub1.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub1Sub1.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub1Sub1.setNewprodjqprice(line4Sub1Sub1.getNewprodbalprice().divide(line4Sub1Sub1.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line4Sub1Sub2.getOldprodbalance() || line4Sub1Sub2.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub1Sub2.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub1Sub2.setOldprodjqprice(line4Sub1Sub2.getOldprodbalprice().divide(line4Sub1Sub2.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub1Sub2.getNewprodbalance() || line4Sub1Sub2.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub1Sub2.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub1Sub2.setNewprodjqprice(line4Sub1Sub2.getNewprodbalprice().divide(line4Sub1Sub2.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line4Sub1Sub3.getOldprodbalance() || line4Sub1Sub3.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub1Sub3.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub1Sub3.setOldprodjqprice(line4Sub1Sub3.getOldprodbalprice().divide(line4Sub1Sub3.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub1Sub3.getNewprodbalance() || line4Sub1Sub3.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub1Sub3.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub1Sub3.setNewprodjqprice(line4Sub1Sub3.getNewprodbalprice().divide(line4Sub1Sub3.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line4Sub1Sub4.getOldprodbalance() || line4Sub1Sub4.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub1Sub4.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub1Sub4.setOldprodjqprice(line4Sub1Sub4.getOldprodbalprice().divide(line4Sub1Sub4.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub1Sub4.getNewprodbalance() || line4Sub1Sub4.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub1Sub4.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub1Sub4.setNewprodjqprice(line4Sub1Sub4.getNewprodbalprice().divide(line4Sub1Sub4.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line4Sub1Sub5.getOldprodbalance() || line4Sub1Sub5.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub1Sub5.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub1Sub5.setOldprodjqprice(line4Sub1Sub5.getOldprodbalprice().divide(line4Sub1Sub5.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub1Sub5.getNewprodbalance() || line4Sub1Sub5.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub1Sub5.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub1Sub5.setNewprodjqprice(line4Sub1Sub5.getNewprodbalprice().divide(line4Sub1Sub5.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}			
		lineSum4Sub1Sub6.setOldprodbalance(line4Sub1Sub1.getOldprodbalance().add(line4Sub1Sub2.getOldprodbalance()).add(line4Sub1Sub3.getOldprodbalance()).add(line4Sub1Sub4.getOldprodbalance()).add(line4Sub1Sub5.getOldprodbalance()));
		lineSum4Sub1Sub6.setNewprodbalance(line4Sub1Sub1.getNewprodbalance().add(line4Sub1Sub2.getNewprodbalance()).add(line4Sub1Sub3.getNewprodbalance()).add(line4Sub1Sub4.getNewprodbalance()).add(line4Sub1Sub5.getNewprodbalance()));
		if(null==lineSum4Sub1Sub6.getOldprodbalance() || lineSum4Sub1Sub6.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum4Sub1Sub6.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			lineSum4Sub1Sub6.setOldprodjqprice((line4Sub1Sub1.getOldprodbalprice().add(line4Sub1Sub2.getOldprodbalprice()).add(line4Sub1Sub3.getOldprodbalprice()).add(line4Sub1Sub4.getOldprodbalprice()).add(line4Sub1Sub5.getOldprodbalprice())).divide(lineSum4Sub1Sub6.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==lineSum4Sub1Sub6.getNewprodbalance() || lineSum4Sub1Sub6.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum4Sub1Sub6.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			lineSum4Sub1Sub6.setNewprodjqprice((line4Sub1Sub1.getNewprodbalprice().add(line4Sub1Sub2.getNewprodbalprice()).add(line4Sub1Sub3.getNewprodbalprice()).add(line4Sub1Sub4.getNewprodbalprice()).add(line4Sub1Sub5.getNewprodbalprice())).divide(lineSum4Sub1Sub6.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		// 合计:4.2 开放式产品	
		if(null==line4Sub2Sub1.getOldprodbalance() || line4Sub2Sub1.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub1.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub2Sub1.setOldprodjqprice(line4Sub2Sub1.getOldprodbalprice().divide(line4Sub2Sub1.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub2Sub1.getNewprodbalance() || line4Sub2Sub1.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub1.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub2Sub1.setNewprodjqprice(line4Sub2Sub1.getNewprodbalprice().divide(line4Sub2Sub1.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line4Sub2Sub2.getOldprodbalance() || line4Sub2Sub2.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub2.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub2Sub2.setOldprodjqprice(line4Sub2Sub2.getOldprodbalprice().divide(line4Sub2Sub2.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub2Sub2.getNewprodbalance() || line4Sub2Sub2.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub2.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub2Sub2.setNewprodjqprice(line4Sub2Sub2.getNewprodbalprice().divide(line4Sub2Sub2.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}			
		if(null==line4Sub2Sub3.getOldprodbalance() || line4Sub2Sub3.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub3.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub2Sub3.setOldprodjqprice(line4Sub2Sub3.getOldprodbalprice().divide(line4Sub2Sub3.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub2Sub3.getNewprodbalance() || line4Sub2Sub3.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub3.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub2Sub3.setNewprodjqprice(line4Sub2Sub3.getNewprodbalprice().divide(line4Sub2Sub3.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line4Sub2Sub4.getOldprodbalance() || line4Sub2Sub4.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub4.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub2Sub4.setOldprodjqprice(line4Sub2Sub4.getOldprodbalprice().divide(line4Sub2Sub4.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub2Sub4.getNewprodbalance() || line4Sub2Sub4.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub4.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub2Sub4.setNewprodjqprice(line4Sub2Sub4.getNewprodbalprice().divide(line4Sub2Sub4.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line4Sub2Sub5.getOldprodbalance() || line4Sub2Sub5.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub5.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub2Sub5.setOldprodjqprice(line4Sub2Sub5.getOldprodbalprice().divide(line4Sub2Sub5.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub2Sub5.getNewprodbalance() || line4Sub2Sub5.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub5.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub2Sub5.setNewprodjqprice(line4Sub2Sub5.getNewprodbalprice().divide(line4Sub2Sub5.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line4Sub2Sub6.getOldprodbalance() || line4Sub2Sub6.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub6.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub2Sub6.setOldprodjqprice(line4Sub2Sub6.getOldprodbalprice().divide(line4Sub2Sub6.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub2Sub6.getNewprodbalance() || line4Sub2Sub6.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub6.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub2Sub6.setNewprodjqprice(line4Sub2Sub6.getNewprodbalprice().divide(line4Sub2Sub6.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line4Sub2Sub7.getOldprodbalance() || line4Sub2Sub7.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub7.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub2Sub7.setOldprodjqprice(line4Sub2Sub7.getOldprodbalprice().divide(line4Sub2Sub7.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub2Sub7.getNewprodbalance() || line4Sub2Sub7.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub7.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub2Sub7.setNewprodjqprice(line4Sub2Sub7.getNewprodbalprice().divide(line4Sub2Sub7.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		if(null==line4Sub2Sub8.getOldprodbalance() || line4Sub2Sub8.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub8.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			line4Sub2Sub8.setOldprodjqprice(line4Sub2Sub8.getOldprodbalprice().divide(line4Sub2Sub8.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==line4Sub2Sub8.getNewprodbalance() || line4Sub2Sub8.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			line4Sub2Sub8.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			line4Sub2Sub8.setNewprodjqprice(line4Sub2Sub8.getNewprodbalprice().divide(line4Sub2Sub8.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		lineSum4Sub2Sub9.setOldprodbalance(line4Sub2Sub1.getOldprodbalance().add(line4Sub2Sub2.getOldprodbalance())
				.add(line4Sub2Sub3.getOldprodbalance()).add(line4Sub2Sub4.getOldprodbalance()).add(line4Sub2Sub5.getOldprodbalance())
				.add(line4Sub2Sub6.getOldprodbalance()).add(line4Sub2Sub7.getOldprodbalance()).add(line4Sub2Sub8.getOldprodbalance()));
		lineSum4Sub2Sub9.setNewprodbalance(line4Sub2Sub1.getNewprodbalance().add(line4Sub2Sub2.getNewprodbalance())
				.add(line4Sub2Sub3.getNewprodbalance()).add(line4Sub2Sub4.getNewprodbalance()).add(line4Sub2Sub5.getNewprodbalance())
				.add(line4Sub2Sub6.getNewprodbalance()).add(line4Sub2Sub7.getNewprodbalance()).add(line4Sub2Sub8.getNewprodbalance()));
		if(null==lineSum4Sub2Sub9.getOldprodbalance() || lineSum4Sub2Sub9.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum4Sub2Sub9.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			lineSum4Sub2Sub9.setOldprodjqprice((line4Sub2Sub1.getOldprodbalprice().add(line4Sub2Sub2.getOldprodbalprice())
					.add(line4Sub2Sub3.getOldprodbalprice()).add(line4Sub2Sub4.getOldprodbalprice()).add(line4Sub2Sub5.getOldprodbalprice())
					.add(line4Sub2Sub6.getOldprodbalprice()).add(line4Sub2Sub7.getOldprodbalprice()).add(line4Sub2Sub8.getOldprodbalprice())
			).divide(lineSum4Sub2Sub9.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==lineSum4Sub2Sub9.getNewprodbalance() || lineSum4Sub2Sub9.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum4Sub2Sub9.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			lineSum4Sub2Sub9.setNewprodjqprice((line4Sub2Sub1.getNewprodbalprice().add(line4Sub2Sub2.getNewprodbalprice())
					.add(line4Sub2Sub3.getNewprodbalprice()).add(line4Sub2Sub4.getNewprodbalprice()).add(line4Sub2Sub5.getNewprodbalprice())
					.add(line4Sub2Sub6.getNewprodbalprice()).add(line4Sub2Sub7.getNewprodbalprice()).add(line4Sub2Sub8.getNewprodbalprice())
			).divide(lineSum4Sub2Sub9.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
		// 合计:4.按期限划分
		lineSum4Sub3.setOldprodbalance(lineSum4Sub1Sub6.getOldprodbalance().add(lineSum4Sub2Sub9.getOldprodbalance()));
		lineSum4Sub3.setNewprodbalance(lineSum4Sub1Sub6.getNewprodbalance().add(lineSum4Sub2Sub9.getNewprodbalance()));
		if(null==lineSum4Sub3.getOldprodbalance() || lineSum4Sub3.getOldprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum4Sub3.setOldprodjqprice(BigDecimal.ZERO);
        }else{
			lineSum4Sub3.setOldprodjqprice((line4Sub1Sub1.getOldprodbalprice().add(line4Sub1Sub2.getOldprodbalprice())
					.add(line4Sub1Sub3.getOldprodbalprice()).add(line4Sub1Sub4.getOldprodbalprice())
					.add(line4Sub1Sub5.getOldprodbalprice()).add(line4Sub2Sub1.getOldprodbalprice()).add(line4Sub2Sub2.getOldprodbalprice())
					.add(line4Sub2Sub3.getOldprodbalprice()).add(line4Sub2Sub4.getOldprodbalprice()).add(line4Sub2Sub5.getOldprodbalprice())
					.add(line4Sub2Sub6.getOldprodbalprice()).add(line4Sub2Sub7.getOldprodbalprice()).add(line4Sub2Sub8.getOldprodbalprice())
			).divide(lineSum4Sub3.getOldprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
        }
		if(null==lineSum4Sub3.getNewprodbalance() || lineSum4Sub3.getNewprodbalance().compareTo(BigDecimal.ZERO)==0){
			lineSum4Sub3.setNewprodjqprice(BigDecimal.ZERO);
		}else{
			lineSum4Sub3.setNewprodjqprice((line4Sub1Sub1.getNewprodbalprice().add(line4Sub1Sub2.getNewprodbalprice())
					.add(line4Sub1Sub3.getNewprodbalprice()).add(line4Sub1Sub4.getNewprodbalprice())
					.add(line4Sub1Sub5.getNewprodbalprice()).add(line4Sub2Sub1.getNewprodbalprice()).add(line4Sub2Sub2.getNewprodbalprice())
					.add(line4Sub2Sub3.getNewprodbalprice()).add(line4Sub2Sub4.getNewprodbalprice()).add(line4Sub2Sub5.getNewprodbalprice())
					.add(line4Sub2Sub6.getNewprodbalprice()).add(line4Sub2Sub7.getNewprodbalprice()).add(line4Sub2Sub8.getNewprodbalprice())
			).divide(lineSum4Sub3.getNewprodbalance(), 12,BigDecimal.ROUND_HALF_UP));
		}	
        return prodList;
    }
    public 	List<ChbTransMonthReportDetail>  genTransAssetReport(ChbTransMonthReport monthreport){
    	List<ChbTransMonthReportDetail> assetList= new ArrayList<ChbTransMonthReportDetail>();
        Map<String,ChbTransMonthReportDetail> assetTransMonthReportDetailMap = new HashMap<String,ChbTransMonthReportDetail>();
        ChbTransMonthAssetPosition asset = new ChbTransMonthAssetPosition();
		asset.setStartdate(monthreport.getStartdate());
		asset.setEnddate(monthreport.getEnddate());
		List<ChbTransMonthAssetPosition> listAssetPosition =new ArrayList<ChbTransMonthAssetPosition>();
		//多级法人改造 start add by hxy 20200618 
		if(MultilevelDataAuthorityUtil.isEnabled()) {
			List<String> institutionlist=new ArrayList<String>();
			String institution=monthreport.getIssuePartyId();
		    institutionlist.add(institution);
		    asset.setInstitutionlist(institutionlist);
		}
		List<ChbTransMonthAssetPosition> listAssetPositionfbottom = chbTransMonthReportDetailMapper.selectAssetList(asset);
		//查询资管计划底层非标
		List<ChbTransMonthAssetPosition> listAssetPositionbottom=chbTransMonthReportDetailMapper.selectAssetListBottom(asset);
		//多级法人改造 end add by hxy 20200618 
		listAssetPosition.addAll(listAssetPositionfbottom);
		listAssetPosition.addAll(listAssetPositionbottom);
		//1.1合计
		ChbTransMonthReportDetail Amt1_1 =new ChbTransMonthReportDetail();
		Amt1_1.setPropertycode(ChbTransMonthReportPrdConstants.AMT1_1);
		assetList.add(Amt1_1);
		assetTransMonthReportDetailMap.put(Amt1_1.getPropertycode(),Amt1_1);
		// 1.1.a信用债
		ChbTransMonthReportDetail Amt1_1_a =new ChbTransMonthReportDetail();
		Amt1_1_a.setPropertycode(ChbTransMonthReportPrdConstants.AMT1_1_A);
		assetList.add(Amt1_1_a);
		assetTransMonthReportDetailMap.put(Amt1_1_a.getPropertycode(),Amt1_1_a);

		//1.1.a.a
		ChbTransMonthReportDetail Amt1_1_a_a =new ChbTransMonthReportDetail();
		Amt1_1_a_a.setPropertycode(ChbTransMonthReportPrdConstants.AMT1_1_A_A);
		assetList.add(Amt1_1_a_a);
		assetTransMonthReportDetailMap.put(Amt1_1_a_a.getPropertycode(),Amt1_1_a_a);

		// 1.1.b 利率债
		ChbTransMonthReportDetail AMT1_1_b =new ChbTransMonthReportDetail();
		AMT1_1_b.setPropertycode(ChbTransMonthReportPrdConstants.AMT1_1_B);
		assetList.add(AMT1_1_b);
		assetTransMonthReportDetailMap.put(AMT1_1_b.getPropertycode(),AMT1_1_b);

		// 1.1.c 固定收益类公募基金
		ChbTransMonthReportDetail Amt1_1_c =new ChbTransMonthReportDetail();
		Amt1_1_c.setPropertycode(ChbTransMonthReportPrdConstants.AMT1_1_C);
		assetList.add(Amt1_1_c);
		assetTransMonthReportDetailMap.put(Amt1_1_c.getPropertycode(),Amt1_1_c);

		//1.2 加权剩余期限
		ChbTransMonthReportDetail Amt1_2 =new ChbTransMonthReportDetail();
		Amt1_2.setPropertycode(ChbTransMonthReportPrdConstants.AMT1_2);
		assetList.add(Amt1_2);
		assetTransMonthReportDetailMap.put(Amt1_2.getPropertycode(),Amt1_2);

		//2.1 通过信托贷款和信托产品
		ChbTransMonthReportDetail AMT2_1 =new ChbTransMonthReportDetail();
		AMT2_1.setPropertycode(ChbTransMonthReportPrdConstants.AMT2_1);
		assetList.add(AMT2_1);
		assetTransMonthReportDetailMap.put(AMT2_1.getPropertycode(),AMT2_1);

		//2.2 通过证券期货经营机构资管产品
		ChbTransMonthReportDetail AMT2_2 =new ChbTransMonthReportDetail();
		AMT2_2.setPropertycode(ChbTransMonthReportPrdConstants.AMT2_2);
		assetList.add(AMT2_2);
		assetTransMonthReportDetailMap.put(AMT2_2.getPropertycode(),AMT2_2);

		//2.3 通过保险资管产品
		ChbTransMonthReportDetail AMT2_3 =new ChbTransMonthReportDetail();
		AMT2_3.setPropertycode(ChbTransMonthReportPrdConstants.AMT2_3);
		assetList.add(AMT2_3);
		assetTransMonthReportDetailMap.put(AMT2_3.getPropertycode(),AMT2_3);

		//2.4 通过委托贷款
		ChbTransMonthReportDetail AMT2_4 =new ChbTransMonthReportDetail();
		AMT2_4.setPropertycode(ChbTransMonthReportPrdConstants.AMT2_4);
		assetList.add(AMT2_4);
		assetTransMonthReportDetailMap.put(AMT2_4.getPropertycode(),AMT2_4);

		//2.5 通过债权融资类产品
		ChbTransMonthReportDetail AMT2_5 =new ChbTransMonthReportDetail();
		AMT2_5.setPropertycode(ChbTransMonthReportPrdConstants.AMT2_5);
		assetList.add(AMT2_5);
		assetTransMonthReportDetailMap.put(AMT2_5.getPropertycode(),AMT2_5);

		//2.6 通过其他形式
		ChbTransMonthReportDetail AMT2_6 =new ChbTransMonthReportDetail();
		AMT2_6.setPropertycode(ChbTransMonthReportPrdConstants.AMT2_6);
		assetList.add(AMT2_6);
		assetTransMonthReportDetailMap.put(AMT2_6.getPropertycode(),AMT2_6);

		//2.7 直接投资
		ChbTransMonthReportDetail AMT2_7 =new ChbTransMonthReportDetail();
		AMT2_7.setPropertycode(ChbTransMonthReportPrdConstants.AMT2_7);
		assetList.add(AMT2_7);
		assetTransMonthReportDetailMap.put(AMT2_7.getPropertycode(),AMT2_7);

		//2.8 合计
		ChbTransMonthReportDetail AMT2_8 =new ChbTransMonthReportDetail();
		AMT2_8.setPropertycode(ChbTransMonthReportPrdConstants.AMT2_8);
		assetList.add(AMT2_8);
		assetTransMonthReportDetailMap.put(AMT2_8.getPropertycode(),AMT2_8);

		//2.9 加权剩余期限
		ChbTransMonthReportDetail AMT2_9 =new ChbTransMonthReportDetail();
		AMT2_9.setPropertycode(ChbTransMonthReportPrdConstants.AMT2_9);
		assetList.add(AMT2_9);		
		assetTransMonthReportDetailMap.put(AMT2_9.getPropertycode(),AMT2_9);

		//3.1 股票类资产合计
		ChbTransMonthReportDetail AMT3_1 =new ChbTransMonthReportDetail();
		AMT3_1.setPropertycode(ChbTransMonthReportPrdConstants.AMT3_1);
		assetList.add(AMT3_1);
		assetTransMonthReportDetailMap.put(AMT3_1.getPropertycode(),AMT3_1);

		
		//4.1 合计
		ChbTransMonthReportDetail AMT4_1 =new ChbTransMonthReportDetail();
		AMT4_1.setPropertycode(ChbTransMonthReportPrdConstants.AMT4_1);
		assetList.add(AMT4_1);
		assetTransMonthReportDetailMap.put(AMT4_1.getPropertycode(),AMT4_1);

		//4.1.a 政府出资产业投资基金
		ChbTransMonthReportDetail AMT4_1_A =new ChbTransMonthReportDetail();
		AMT4_1_A.setPropertycode(ChbTransMonthReportPrdConstants.AMT4_1_A);
		assetList.add(AMT4_1_A);
		assetTransMonthReportDetailMap.put(AMT4_1_A.getPropertycode(),AMT4_1_A);

		//4.1.b 创业投资基金
		ChbTransMonthReportDetail AMT4_1_B =new ChbTransMonthReportDetail();
		AMT4_1_B.setPropertycode(ChbTransMonthReportPrdConstants.AMT4_1_B);
		assetList.add(AMT4_1_B);	
		assetTransMonthReportDetailMap.put(AMT4_1_B.getPropertycode(),AMT4_1_B);

		//4.2 加权剩余期限
		ChbTransMonthReportDetail AMT4_2 =new ChbTransMonthReportDetail();
		AMT4_2.setPropertycode(ChbTransMonthReportPrdConstants.AMT4_2);
		assetList.add(AMT4_2);	
		assetTransMonthReportDetailMap.put(AMT4_2.getPropertycode(),AMT4_2);

		//5.1 合计
		ChbTransMonthReportDetail AMT5_1 =new ChbTransMonthReportDetail();
		AMT5_1.setPropertycode(ChbTransMonthReportPrdConstants.AMT5_1);
		assetList.add(AMT5_1);	
		assetTransMonthReportDetailMap.put(AMT5_1.getPropertycode(),AMT5_1);

		//5.1.a 优先股
		ChbTransMonthReportDetail AMT5_1_A =new ChbTransMonthReportDetail();
		AMT5_1_A.setPropertycode(ChbTransMonthReportPrdConstants.AMT5_1_A);
		assetList.add(AMT5_1_A);	
		assetTransMonthReportDetailMap.put(AMT5_1_A.getPropertycode(),AMT5_1_A);

		//5.1.b 永续债（债性）
		ChbTransMonthReportDetail AMT5_1_B =new ChbTransMonthReportDetail();
		AMT5_1_B.setPropertycode(ChbTransMonthReportPrdConstants.AMT5_1_B);
		assetList.add(AMT5_1_B);		
		assetTransMonthReportDetailMap.put(AMT5_1_B.getPropertycode(),AMT5_1_B);

		//5.1.c 永续债（股性）
		ChbTransMonthReportDetail AMT5_1_C =new ChbTransMonthReportDetail();
		AMT5_1_C.setPropertycode(ChbTransMonthReportPrdConstants.AMT5_1_C);
		assetList.add(AMT5_1_C);	
		assetTransMonthReportDetailMap.put(AMT5_1_C.getPropertycode(),AMT5_1_C);

		//5.1.d 二级资本债
		ChbTransMonthReportDetail AMT5_1_D =new ChbTransMonthReportDetail();
		AMT5_1_D.setPropertycode(ChbTransMonthReportPrdConstants.AMT5_1_D);
		assetList.add(AMT5_1_D);
		assetTransMonthReportDetailMap.put(AMT5_1_D.getPropertycode(),AMT5_1_D);

		//5.2 加权剩余期限
		ChbTransMonthReportDetail AMT5_2 =new ChbTransMonthReportDetail();
		AMT5_2.setPropertycode(ChbTransMonthReportPrdConstants.AMT5_2);
		assetList.add(AMT5_2);		
		assetTransMonthReportDetailMap.put(AMT5_2.getPropertycode(),AMT5_2);

		//6.1 合计
		ChbTransMonthReportDetail AMT6_1 =new ChbTransMonthReportDetail();
		AMT6_1.setPropertycode(ChbTransMonthReportPrdConstants.AMT6_1);
		assetList.add(AMT6_1);		
		assetTransMonthReportDetailMap.put(AMT6_1.getPropertycode(),AMT6_1);

		//6.1.a 拆放同业及买入返售
		ChbTransMonthReportDetail AMT6_1_A =new ChbTransMonthReportDetail();
		AMT6_1_A.setPropertycode(ChbTransMonthReportPrdConstants.AMT6_1_A);
		assetList.add(AMT6_1_A);	
		assetTransMonthReportDetailMap.put(AMT6_1_A.getPropertycode(),AMT6_1_A);

		//6.1.b 现金及银行存款
		ChbTransMonthReportDetail AMT6_1_B =new ChbTransMonthReportDetail();
		AMT6_1_B.setPropertycode(ChbTransMonthReportPrdConstants.AMT6_1_B);
		assetList.add(AMT6_1_B);		
		assetTransMonthReportDetailMap.put(AMT6_1_B.getPropertycode(),AMT6_1_B);

		//6.1.c 其他公募基金
		ChbTransMonthReportDetail AMT6_1_C =new ChbTransMonthReportDetail();
		AMT6_1_C.setPropertycode(ChbTransMonthReportPrdConstants.AMT6_1_C);
		assetList.add(AMT6_1_C);	
		assetTransMonthReportDetailMap.put(AMT6_1_C.getPropertycode(),AMT6_1_C);

		//6.1.d 商品及金融衍生产品
		ChbTransMonthReportDetail AMT6_1_D =new ChbTransMonthReportDetail();
		AMT6_1_D.setPropertycode(ChbTransMonthReportPrdConstants.AMT6_1_D);
		assetList.add(AMT6_1_D);	
		assetTransMonthReportDetailMap.put(AMT6_1_D.getPropertycode(),AMT6_1_D);

		//6.1.e QDII
		ChbTransMonthReportDetail AMT6_1_E =new ChbTransMonthReportDetail();
		AMT6_1_E.setPropertycode(ChbTransMonthReportPrdConstants.AMT6_1_E);
		assetList.add(AMT6_1_E);
		assetTransMonthReportDetailMap.put(AMT6_1_E.getPropertycode(),AMT6_1_E);

		//6.2 加权剩余期限
		ChbTransMonthReportDetail AMT6_2 =new ChbTransMonthReportDetail();
		AMT6_2.setPropertycode(ChbTransMonthReportPrdConstants.AMT6_2);
		assetList.add(AMT6_2);			
		assetTransMonthReportDetailMap.put(AMT6_2.getPropertycode(),AMT6_2);

		//7合计
		ChbTransMonthReportDetail AMT7 =new ChbTransMonthReportDetail();
		AMT7.setPropertycode(ChbTransMonthReportPrdConstants.AMT7);
		assetList.add(AMT7);
		assetTransMonthReportDetailMap.put(AMT7.getPropertycode(),AMT7);

    	//信用债
    	String xyz="1112,1113,1114,1115,1116,1117";

		for(ChbTransMonthAssetPosition pos:listAssetPosition){
			for (ChbTransMonthAssetRule singleRule :ChbTransMonthAssetRule.values()){
	      		// 根据配置规则代码匹配资产头寸中相应属性
        		if (!StringUtil.isEmptyList(singleRule.getRuleCodeList())
        				&& !StringUtil.isEmptyList(singleRule.getRuleValueList())){
        			// 该笔头寸记录是否匹配配置规则
        			boolean isPattern = true;
        			// 判断是否多条件交集处理
        			for(int i = 0; i < singleRule.getRuleCodeList().size(); i++){
        				// 重新初始化是否匹配配置规则标记
	        			isPattern = true;
        				//对债券评级进行特殊处理，即对1.1.a.a PPN等低流动性债券进行特殊处理
	        			if (singleRule.getRuleCodeList().get(i).toUpperCase().equals("RATINGAA-|RATINGNULL")){
	        				if(xyz.contains(pos.getAssettype2()) 
	        						&& ((pos.getRating()!=null &&compareRating(pos.getRating(),singleRule.getRuleValueList().get(i)) < 0 )
	        								  ||pos.getRating() == null)){
	        					isPattern = true;
	        				}else{
	        					isPattern = false;
		        				break;
	        				}
	        			}else{
	        				// 判断是否多条件任取其一，即rulecode和value一一对应以“|”隔开，格式为code1|code2,value1|value2
	        				if (singleRule.getRuleCodeList().get(i).contains(GlobalConstant.VERTICAL_LINE)
	        						|| singleRule.getRuleValueList().get(i).contains(GlobalConstant.VERTICAL_LINE)){
	        					
	        					List<String> ruleCodeList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE,
	        							singleRule.getRuleCodeList().get(i));
	        					List<String> valueList = StringUtil.splitToList(GlobalConstant.VERTICAL_LINE,
	        							singleRule.getRuleValueList().get(i));
	        					isPattern = false;
        						String ruleCode = ruleCodeList.get(0);
	        					for (int k = 0; k < valueList.size() ; k++){
	        						// 如果ruleCode相同但是多条件，则支持只输入一个code，如：code1,value1|value2|value3
	        						// 否则需一一对应以“|”隔开，格式为code1|code2,value1|value2
	        						if (ruleCodeList.size() > 1){
	        							ruleCode = ruleCodeList.get(k);
	        						}
	        						// 支持反向判断！value1
	        						if (valueList.get(k).startsWith("!")){
	        							// 只要有一条满足则该条头寸计入
					        			if (!valueList.get(k).substring(1,valueList.get(k).length())
					        					.equals((String)getGetMethod(pos,ruleCode))){
					        				isPattern = true;
					        				break;
					        			}
	        						} else {
	    		        				// 只有一个条件满足，都计入
	        							if(valueList.get(k).equals("null")){
	        								if (null==((String)getGetMethod(pos,ruleCode))){
	        									isPattern = true;
						        				break;
		        							}
	        							}
	        							if (valueList.get(k).equals((String)getGetMethod(pos,ruleCode))){
					        				isPattern = true;
					        				break;
					        			}
	        						}
	        					}
	        					if (!isPattern){
	        						break;
	        					}
	        				}else {// 多条件同时满足才计入
        						// 支持反向判断！value1
        						if (singleRule.getRuleValueList().get(i).startsWith("!")){
        							// 只要有一条满足则该条头寸不计入
				        			if (singleRule.getRuleValueList().get(i).substring(1,
				        					singleRule.getRuleValueList().get(i).length())
				        					.equals((String)getGetMethod(pos,
				        					singleRule.getRuleCodeList().get(i)))){
				        				isPattern = false;
				        				break;
				        			}
        						} else {
			        				// 只要有一条不满足则该条头寸不计入
				        			if (!singleRule.getRuleValueList().get(i)
				        					.equals((String)getGetMethod(pos,singleRule.getRuleCodeList()
				        							.get(i)))){
				        				isPattern = false;
				        				break;
				        			}
        						}
	        				}
	        			}
	        			
        			}
        			// 值域匹配则累计，值域不匹配则计0
    				// 判断key值是否已存在，已存在则累计，未存在则新建
    				if (assetTransMonthReportDetailMap.containsKey(singleRule.getPropertyCode())){
	        			ChbTransMonthReportDetail detail = assetTransMonthReportDetailMap.get(singleRule.getPropertyCode());
	        			// 非合计值
    					// 合计值单独处理
    	        		if (singleRule.getPropertyName().contains("合计")){
        					// 已存在计入本次头寸值，否则不计入本次头寸值
                			if (isPattern){
    	    					if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
                					//老产品-预期收益型
                					//穿透后期末余额
                					detail.setOldbottombalance((detail.getOldbottombalance()==null?BigDecimal.ZERO:detail.getOldbottombalance()).add(pos.getAssetvalueB()));
                					 //穿透后期末余额*剩余期限
                					detail.setOldbalremainterm((detail.getOldbalremainterm()==null?BigDecimal.ZERO:detail.getOldbalremainterm()).add(pos.getAssetvalueB().multiply(pos.getRemainterm())));
                				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
                					//新产品-净值型、货币型
                					//穿透后期末余额
                					detail.setNewbottombalance((detail.getNewbottombalance()==null?BigDecimal.ZERO:detail.getNewbottombalance()).add(pos.getAssetvalueB()));
                				    //穿透后期末余额*剩余期限
                					detail.setNewbalremainterm((detail.getNewbalremainterm()==null?BigDecimal.ZERO:detail.getNewbalremainterm()).add(pos.getAssetvalueB().multiply(pos.getRemainterm())));
                				}
                			}
    	        		}else{
        					// 已存在计入本次头寸值，否则不计入本次头寸值
                			if (isPattern){
                				if(null!=pos.getProdtype() && "02".equals(pos.getProdtype())){
                					//老产品-预期收益型
                					//穿透后期末余额
                					detail.setOldbottombalance((detail.getOldbottombalance()==null?BigDecimal.ZERO:detail.getOldbottombalance()).add(pos.getAssetvalueB()));
                					 //穿透后期末余额*剩余期限
                					detail.setOldbalremainterm((detail.getOldbalremainterm()==null?BigDecimal.ZERO:detail.getOldbalremainterm()).add(pos.getAssetvalueB().multiply(pos.getRemainterm())));
                				}else if(null!=pos.getProdtype() &&  ("01".equals(pos.getProdtype()) || "03".equals(pos.getProdtype()))){
                					//新产品-净值型、货币型
                					//穿透后期末余额
                					detail.setNewbottombalance((detail.getNewbottombalance()==null?BigDecimal.ZERO:detail.getNewbottombalance()).add(pos.getAssetvalueB()));
                					//穿透后期末余额*剩余期限
                					detail.setNewbalremainterm((detail.getNewbalremainterm()==null?BigDecimal.ZERO:detail.getNewbalremainterm()).add(pos.getAssetvalueB().multiply(pos.getRemainterm())));
                				}
    	    					
                			}
    	        		}
              		
    				} 
        		}
			}
		
			
		}
		 //合计单独计算
		 if(null==Amt1_1.getOldbottombalance() || Amt1_1.getOldbottombalance().compareTo(BigDecimal.ZERO)==0){
	    	   Amt1_2.setOldjqterm(BigDecimal.ZERO);
		  }else{
			   Amt1_2.setOldjqterm(Amt1_1.getOldbalremainterm().divide(Amt1_1.getOldbottombalance(), 6,BigDecimal.ROUND_HALF_UP));
		  }
	      if(null==Amt1_1.getNewbottombalance() || Amt1_1.getNewbottombalance().compareTo(BigDecimal.ZERO)==0){
	    	   Amt1_2.setNewjqterm(BigDecimal.ZERO);
		  }else{
			   Amt1_2.setNewjqterm(Amt1_1.getNewbalremainterm().divide(Amt1_1.getNewbottombalance(), 6,BigDecimal.ROUND_HALF_UP));
		  }
		
		  if(null==AMT2_8.getOldbottombalance() || AMT2_8.getOldbottombalance().compareTo(BigDecimal.ZERO)==0){
	    	   AMT2_9.setOldjqterm(BigDecimal.ZERO);
		  }else{
			  
			   AMT2_9.setOldjqterm(AMT2_8.getOldbalremainterm().divide(AMT2_8.getOldbottombalance(), 6,BigDecimal.ROUND_HALF_UP));
		  }
	      if(null==AMT2_8.getNewbottombalance() || AMT2_8.getNewbottombalance().compareTo(BigDecimal.ZERO)==0){
	    	   AMT2_9.setNewjqterm(BigDecimal.ZERO);
		  }else{
			   AMT2_9.setNewjqterm(AMT2_8.getNewbalremainterm().divide(AMT2_8.getNewbottombalance(), 6,BigDecimal.ROUND_HALF_UP));
		  }
	      if(null==AMT4_1.getOldbottombalance() || AMT4_1.getOldbottombalance().compareTo(BigDecimal.ZERO)==0){
	    	   AMT4_2.setOldjqterm(BigDecimal.ZERO);
		  }else{
			  
			   AMT4_2.setOldjqterm(AMT4_1.getOldbalremainterm().divide(AMT4_1.getOldbottombalance(), 6,BigDecimal.ROUND_HALF_UP));
		  }
	      if(null==AMT4_1.getNewbottombalance() || AMT4_1.getNewbottombalance().compareTo(BigDecimal.ZERO)==0){
	    	   AMT4_2.setNewjqterm(BigDecimal.ZERO);
		  }else{
			   AMT4_2.setNewjqterm(AMT4_1.getNewbalremainterm().divide(AMT4_1.getNewbottombalance(), 6,BigDecimal.ROUND_HALF_UP));
		  }
	      if(null==AMT5_1.getOldbottombalance() || AMT5_1.getOldbottombalance().compareTo(BigDecimal.ZERO)==0){
	    	   AMT5_2.setOldjqterm(BigDecimal.ZERO);
		  }else{
			  
			   AMT5_2.setOldjqterm(AMT5_1.getOldbalremainterm().divide(AMT5_1.getOldbottombalance(), 6,BigDecimal.ROUND_HALF_UP));
		  }
	      if(null==AMT5_1.getNewbottombalance() || AMT5_1.getNewbottombalance().compareTo(BigDecimal.ZERO)==0){
	    	   AMT5_2.setNewjqterm(BigDecimal.ZERO);
		  }else{
			   AMT5_2.setNewjqterm(AMT5_1.getNewbalremainterm().divide(AMT5_1.getNewbottombalance(), 6,BigDecimal.ROUND_HALF_UP));
		  }
	      if(null==AMT6_1.getOldbottombalance() || AMT6_1.getOldbottombalance().compareTo(BigDecimal.ZERO)==0){
	    	   AMT6_2.setOldjqterm(BigDecimal.ZERO);
		  }else{
			  
			  AMT6_2.setOldjqterm(AMT6_1.getOldbalremainterm().divide(AMT6_1.getOldbottombalance(), 6,BigDecimal.ROUND_HALF_UP));
		  }
	      if(null==AMT6_1.getNewbottombalance() || AMT6_1.getNewbottombalance().compareTo(BigDecimal.ZERO)==0){
	    	  AMT6_2.setNewjqterm(BigDecimal.ZERO);
		  }else{
			  AMT6_2.setNewjqterm(AMT6_1.getNewbalremainterm().divide(AMT6_1.getNewbottombalance(), 6,BigDecimal.ROUND_HALF_UP));
		  }
    	 //合计AMT7,还需要加上合计5.1和合计6.1
	      if(null!=AMT5_1.getOldbottombalance() && AMT5_1.getOldbottombalance().compareTo(BigDecimal.ZERO)!=0){
		      AMT7.setOldbottombalance((AMT7.getOldbottombalance()==null?BigDecimal.ZERO:AMT7.getOldbottombalance()).add(AMT5_1.getOldbottombalance()));
           }
	      if(null!=AMT6_1.getOldbottombalance() && AMT6_1.getOldbottombalance().compareTo(BigDecimal.ZERO)!=0){
		      AMT7.setOldbottombalance((AMT7.getOldbottombalance()==null?BigDecimal.ZERO:AMT7.getOldbottombalance()).add(AMT6_1.getOldbottombalance()));
           }
	      if(null!=AMT5_1.getNewbottombalance() && AMT5_1.getNewbottombalance().compareTo(BigDecimal.ZERO)!=0){
		      AMT7.setNewbottombalance((AMT7.getNewbottombalance()==null?BigDecimal.ZERO:AMT7.getNewbottombalance()).add(AMT5_1.getNewbottombalance()));
           }
	      if(null!=AMT6_1.getNewbottombalance() && AMT6_1.getNewbottombalance().compareTo(BigDecimal.ZERO)!=0){
		      AMT7.setNewbottombalance((AMT7.getNewbottombalance()==null?BigDecimal.ZERO:AMT7.getNewbottombalance()).add(AMT6_1.getNewbottombalance()));
           }
    	return assetList;
    }
	public ResponseEntity<byte[]>  exportMonthReportExcel(Map<String,Object> data,String exceltemplate,String fileName) throws IOException {
	    //File depositfile = createFile(data,exceltemplate);
	    File depositfile = TisUtil.createFile(data, exceltemplate, fileName);
		return ExportUtil.export(fileName, depositfile, true);
	}
    /**
     * @Description 导出预览为零的数据显示空还是显示零
     * @param detail
     * @version 1.7 2019年7月4日
     * @param isShowZero 
     */
    private void dealProdData(ChbTransMonthReportDetail detail, String isShowZero) {
    	// 显示成零
		if("01".equals(isShowZero)){
			if(detail.getOldprodbalance()==null){
				detail.setOldprodbalance(BigDecimal.ZERO);
			}
			if(detail.getNewprodbalance()==null){
				detail.setNewprodbalance(BigDecimal.ZERO);
			}
			if(detail.getOldprodjqprice()==null){
				detail.setOldprodjqprice(BigDecimal.ZERO);
            }
			if(detail.getNewprodjqprice()==null){
				detail.setNewprodjqprice(BigDecimal.ZERO);
            }
			if(detail.getOldbottombalance()==null){
				detail.setOldbottombalance(BigDecimal.ZERO);
			}
			if(detail.getNewbottombalance()==null){
				detail.setNewbottombalance(BigDecimal.ZERO);
			}
			if(detail.getOldjqterm()==null){
				detail.setOldjqterm(BigDecimal.ZERO);
			}
			if(detail.getNewjqterm()==null){
				detail.setNewjqterm(BigDecimal.ZERO);
			}
			
		} else if("02".equals(isShowZero)){
			
			if(detail.getOldprodbalance()!=null&&(BigDecimal.ZERO).compareTo(detail.getOldprodbalance())==0){
				detail.setOldprodbalance(null);
			}
			if(detail.getNewprodbalance()!=null&&(BigDecimal.ZERO).compareTo(detail.getNewprodbalance())==0){
				detail.setNewprodbalance(null);
			}
			if(detail.getOldprodjqprice()!=null&&(BigDecimal.ZERO).compareTo(detail.getOldprodjqprice())==0){
				detail.setOldprodjqprice(null);
			}
			if(detail.getNewprodjqprice()!=null&&(BigDecimal.ZERO).compareTo(detail.getNewprodjqprice())==0){
				detail.setNewprodjqprice(null);
			}
			if(detail.getOldbottombalance()!=null&&(BigDecimal.ZERO).compareTo(detail.getOldbottombalance())==0){
				detail.setOldbottombalance(null);
			}
			if(detail.getNewbottombalance()!=null&&(BigDecimal.ZERO).compareTo(detail.getNewbottombalance())==0){
				detail.setNewbottombalance(null);
			}
			if(detail.getOldjqterm()!=null&&(BigDecimal.ZERO).compareTo(detail.getOldjqterm())==0){
				detail.setOldjqterm(null);
			}
			if(detail.getNewjqterm()!=null&&(BigDecimal.ZERO).compareTo(detail.getNewjqterm())==0){
				detail.setNewjqterm(null);
			}
		
		}
	}
    /**
	 * 功能:判断评级信息大小
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月11日
	 * @param s1 待比较评级结果
	 * @param s2 评级比较标准    S2固定只能为AA+或AA-
	 * @return >0 s1>s2 =0 s1 = s2 <0 s1<s2
	 */
	private int compareRating(String s1,String s2){
		if(s1.equals(s2)){
    		return 0;
    	}
    	if(s2.equals("AA+")){
    		if(s1.length()<s2.length()){
    			return -1;
    		}else{
    			if(s1.substring(0, 3).equals(s2)||s1.substring(0, 3).equals("AAA")){
    				return 1;
    			}else{
    				return -1;
    			}
    			
    		}
    	}else{
    		if(s1.length()<s2.length()){
    			return -1;
    		}else{
    			if(s1.substring(0, 3).equals(s2)||s1.substring(0, 3).equals("AAA")||s1.substring(0, 3).equals("AA+")){
    				return 1;
    			}else{
    				return -1;
    			}
    			
    		}
    	}
	}
	/**
	 * 根据属性，获取get方法(不区分大小写)
	 * @param ob 对象
	 * @param name 属性名
	 * @return
	 */
	private Object getGetMethod(Object ob , String name){
		Method[] m = ob.getClass().getMethods();
		for(int i = 0;i < m.length;i++){
			if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
				try {
                    return m[i].invoke(ob);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.error(e.getMessage(),e);
                    return null;
                }
			}
		}
		return null;
	}

	public ChbTransMonthReportDetail divide10000ormul100(ChbTransMonthReportDetail detail) {
		BigDecimal tenthousand = new BigDecimal(10000);
		BigDecimal tenten=new BigDecimal(100);
		if(detail.getOldprodbalance()!=null)
			detail.setOldprodbalance(detail.getOldprodbalance().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		if(detail.getNewprodbalance()!=null)
			detail.setNewprodbalance(detail.getNewprodbalance().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		if(detail.getOldprodjqprice()!=null)
			detail.setOldprodjqprice(detail.getOldprodjqprice().multiply(tenten).setScale(6,BigDecimal.ROUND_HALF_UP));
		if(detail.getNewprodjqprice()!=null)
			detail.setNewprodjqprice(detail.getNewprodjqprice().multiply(tenten).setScale(6,BigDecimal.ROUND_HALF_UP));
		if(detail.getOldbottombalance()!=null)
			detail.setOldbottombalance(detail.getOldbottombalance().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		if(detail.getNewbottombalance()!=null)
			detail.setNewbottombalance(detail.getNewbottombalance().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		if(detail.getOldjqterm()!=null)
			detail.setOldjqterm(detail.getOldjqterm().setScale(6,BigDecimal.ROUND_HALF_UP));
		if(detail.getNewjqterm()!=null)
			detail.setNewjqterm(detail.getNewjqterm().setScale(6,BigDecimal.ROUND_HALF_UP));

		return detail;
	}
	

	/**
     * 账套代码
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getChbTransOrg(Map<String, Object> pageParam) {
        return restTemplate.postForPage(SvrServiceApplication.CHB_SERVICE_NAME+"/ChbTransMonthReport/getChbTransOrg",
                pageParam, SelectOptionDto.class); 
    }
    public Page<SelectOptionDto> getChbTransOrg(int page,int length,Map<String, Object> param) {
        return super.queryForPageForSelectReturn(page,length,()->chbTransMonthReportDetailMapper.getChbTransOrg(param));
    }

}
