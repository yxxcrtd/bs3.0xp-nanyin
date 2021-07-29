package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.entity.SysInstitution;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.ChbOrgRegistBankCode;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.report.system.instregbankcode.dao.SysInstRegBankCode001Mapper;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportConstants;
import com.joyintech.tams.svreport.pbc.common.service.PbcExportService;
import com.joyintech.tams.svreport.pbc.prod.dao.PbcReportProdDetailMapper;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcMonthPrdPostionDto;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcReportProdDetailDto;
import com.joyintech.tams.svreport.pbc.report.dao.PbcReportTemplateMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.svreport.pbc.report.dto.PbcReportTemplateDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 功能:人行报送明细总入口
 * @since 1.6.0
 * @version 1.6.0
 * @作者：meifaxin
 * @创建时间：2021年7月29日
 * @修改记录：
 */
@Service
public abstract class PbcReportProdDatTotalService extends PbcExportService  {
    
    @Autowired
    private PbcSendZipDatFileMapper  pbcSendZipDatFileMapper;
	@Autowired
	private PbcReportTemplateMapper pbcReportTemplateMapper;
	@Autowired
    private SysInstRegBankCode001Mapper sysInstRegBankCode001Mapper;
    
    public abstract void generateReport(Date generateDate,Date reportStartDate,Date reportEndDate,
            List<PbcMonthPrdPostionDto> prodPositionList,boolean isEdit);
    
    @Override
    public Map<Object, Object> createData(Map<String,Object> param){
    	Map<Object,Object> data  = new HashMap<Object,Object>();
    	if(param.get("reportuuid")==null){
    		return data;
    	}
    	String reportuuid = param.get("reportuuid").toString();
		String reporttype=param.get("reporttype").toString();
		if(reporttype.equals(DictCodeListExt2.人行报送明细类型_产品基本信息.getValue())){
			Map<Object,Object> pbcReport  = new HashMap<Object,Object>();
			pbcReport.put("rowIndex","1");
			pbcReport.put("prodId","C00001121000058");
			pbcReport.put("issuePartyId","C00001");
			data.put("report", pbcReport);
		}else {
			PbcSendZipDatFile pbcReport = pbcSendZipDatFileMapper.selectByPrimaryKey(reportuuid);
			// 如果报表为空则返回data
			if (pbcReport == null) {
				return data;
			}
			data.put("report", pbcReport);
			// 导出excel或预览时，值为0的单元格显示成0还是显示成空，读取系统参数
			String isShowZero = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SHOW_ZERO);
			List<PbcReportProdDetailDto> listDetail = null;//pbcReportProdDetailMapper.selectByReportuuid(reportuuid);
			if (!CollectionUtils.isEmpty(listDetail)) {
				for (PbcReportProdDetailDto detail : listDetail) {
					dealProdData(detail, isShowZero);
					// 处理数据
					if (!DictCodeListExt2.人行报表名称_资管产品只数情况统计表.getValue().equals(pbcReport.getDatZipDatType())) {
						// 处理数据
						dealAreaDateListForExport(detail);
					}
					data.put(detail.getPropertyCode(), detail);
				}
			}
		}
    	return  data;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public Map<Object, Object> createDataMS(Map<String,Object> param){
    	return createData(param);
//    	return restTemplate.postForObject(
//        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReportProd/createDataProd",param,
//        		Map.class);
    }

    
	/**
	 * 功能:处理查询出的产品报表明细数据
	 * @since 1.6.0
	 * @version 1.6.0
	 * @param detail
	 * @return 
	 * PbcReportAssetDebtDetailDto
	 */
	public void dealAreaDateListForExport(PbcReportProdDetailDto detail) {
		BigDecimal tenthousand = new BigDecimal(10000);
		// 人民币
		if(detail.getCny() != null){
			detail.setCny(detail.getCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//外币
//		if(detail.getForeignCcy() != null){
//			detail.setForeignCcy(detail.getForeignCcy().divide(tenthousand));
//		}
		//美元折人民币
		if(detail.getUsdToCny() != null){
			detail.setUsdToCny(detail.getUsdToCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//欧元折人民币
		if(detail.getEurToCny() != null){
			detail.setEurToCny(detail.getEurToCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//英镑折人民币
		if(detail.getGbpToCny() != null){
			detail.setGbpToCny(detail.getGbpToCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//日元折人民币
		if(detail.getJpyToCny() != null){
			detail.setJpyToCny(detail.getJpyToCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//其他币种(外币)折人民币
		if(detail.getOthToCny() != null){
			detail.setOthToCny(detail.getOthToCny().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
		//合计
		if(detail.getTotal() != null){
			detail.setTotal(detail.getTotal().divide(tenthousand, 6, BigDecimal.ROUND_HALF_UP));
		}
	}



	
	
	
	/**
	 * 数据计算
	 * @param dto
	 * @param ccy
	 * @param value
	 * @param rateMap
	 * @param generateDate
	 * @param source
	 */
    public void setDetailValue(PbcReportProdDetailDto dto,String ccy,BigDecimal value,Map<String, BigDecimal> rateMap,Date generateDate,String source){
        BigDecimal cny = ChbCcyConvertUtil.exchangeMoney(
        		ccy,DictCodeListExt1.币种_中债_人民币.getValue(),generateDate,value,rateMap,source);
        if(DictCodeListExt1.币种_中债_人民币.getValue().equals(ccy)){//人民币
            dto.setCny(dto.getCny().add(cny));
        }else if(DictCodeListExt1.币种_中债_美元.getValue().equals(ccy)){//美元
            dto.setUsdToCny((dto.getUsdToCny().add(cny)));
        }else if(DictCodeListExt1.币种_中债_欧元.getValue().equals(ccy)){//欧元
            dto.setEurToCny((dto.getEurToCny().add(cny)));
        }else if(DictCodeListExt1.币种_中债_英镑.getValue().equals(ccy)){//英镑
            dto.setGbpToCny((dto.getGbpToCny().add(cny)));
        }else if(DictCodeListExt1.币种_中债_日元.getValue().equals(ccy)){//日元
            dto.setJpyToCny((dto.getJpyToCny().add(cny)));
        }else{ //其他币种
            dto.setOthToCny((dto.getOthToCny().add(cny)));
        }
        //合计
        dto.setTotal(dto.getTotal().add(cny));
    }
    
    
    /**
   	 * 数据计算 (多法人处理) 同时计算汇总数据以及当前数据所属部门的数据
   	 * @param dto   汇总数据
   	 * @param deptDto  部门数据
   	 * @param ccy
   	 * @param value
   	 * @param rateMap
   	 * @param generateDate
   	 * @param source
   	 */
       public void setDetailValueAndDept(Map<String,PbcReportProdDetailDto> dto,Map<String,PbcReportProdDetailDto> deptDto,String code,
       												String ccy,BigDecimal value,Map<String, BigDecimal> rateMap,Date generateDate,String source){
       	//汇总数据计算
       	setDetailValue(dto.get(code), ccy, value, rateMap, generateDate, source);
       	//部门数据计算
       	if(null!=deptDto&&null!=deptDto.get(code)){
       		
           	setDetailValue(deptDto.get(code), ccy, value, rateMap, generateDate, source);
       	}

       }
    
    

    /**
     * @Description 导出预览为零的数据显示空还是显示零
     * @param detail
     * @version 1.7 2019年7月4日
     * @param isShowZero 
     */
    private void dealProdData(PbcReportProdDetailDto detail, String isShowZero) {
    	// 显示成零
		if("01".equals(isShowZero)){
			if(detail.getCny()==null){
				detail.setCny(BigDecimal.ZERO);
			}
			if(detail.getUsdToCny()==null){
				detail.setUsdToCny(BigDecimal.ZERO);
			}
			if(detail.getEurToCny()==null){
				detail.setEurToCny(BigDecimal.ZERO);
			}
			if(detail.getGbpToCny()==null){
				detail.setGbpToCny(BigDecimal.ZERO);
			}
			if(detail.getJpyToCny()==null){
				detail.setJpyToCny(BigDecimal.ZERO);
			}
			if(detail.getOthToCny()==null){
				detail.setOthToCny(BigDecimal.ZERO);
			}
			if(detail.getTotal()==null){
				detail.setTotal(BigDecimal.ZERO);
			}
			if(detail.getProdcounts()==null){
				detail.setProdcounts(new Long(0));
			}
		} else if("02".equals(isShowZero)){
			if(detail.getCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getCny())==0){
				detail.setCny(null);
			}
			if(detail.getUsdToCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getUsdToCny())==0){
				detail.setUsdToCny(null);
			}
			if(detail.getEurToCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getEurToCny())==0){
				detail.setEurToCny(null);
			}
			if(detail.getGbpToCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getGbpToCny())==0){
				detail.setGbpToCny(null);
			}
			if(detail.getJpyToCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getJpyToCny())==0){
				detail.setJpyToCny(null);
			}
			if(detail.getOthToCny()!=null&&(BigDecimal.ZERO).compareTo(detail.getOthToCny())==0){
				detail.setOthToCny(null);
			}
			if(detail.getTotal()!=null&&(BigDecimal.ZERO).compareTo(detail.getTotal())==0){
				detail.setTotal(null);
			}
			if(detail.getProdcounts()!=null&&(new Long(0)).compareTo(detail.getProdcounts())==0){
				detail.setProdcounts(null);
			}
		}
	}


    
    public String[] getRegistCode(String orgCode){
        //String issuePartyId = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID);
		String issuePartyId =  CommonUtil.selectBankCode();
        //String  issuePartyName = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_NAME);
		String  issuePartyName = CommonUtil.selectBankCodeName();
        // 发行机构代码取值
        if(null!=orgCode){
        	Map<String,Object> param = new HashMap<>();
        	List<String> orgCodeList = new ArrayList<>();
        	orgCodeList.add(orgCode);
        	param.put("orgCodeList", orgCodeList);
        	List<SysInstRegBankCode001Dto> list = sysInstRegBankCode001Mapper.queryForPage(param);
        	
        	if(null!=list&&list.size()>0){
        		issuePartyId = list.get(0).getRegistBankCode();
        		SysInstitution dtp = (SysInstitution) CommonUtil.queryCode(GlobalConstant.SYS_INSTITUTION_KEY,orgCode);
        		issuePartyName =  null!=dtp?dtp.getInstitutionName():issuePartyName;
        	}
        }
		return new String[]{issuePartyId,issuePartyName};
	}
    
}
