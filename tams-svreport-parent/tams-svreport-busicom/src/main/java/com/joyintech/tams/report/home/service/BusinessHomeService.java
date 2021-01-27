package com.joyintech.tams.report.home.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.home.dao.BusinessHomeMapper;
import com.joyintech.tams.report.home.dto.BusinessHomeDto;
import com.joyintech.tams.report.home.dto.HomeCellDto;
import com.joyintech.tams.report.home.dto.VbusinessHomeDto;

/**
 * @Description 业务首页
 * @author jiangxiao
 * @version 1.7 2019年5月16日
 * @since 1.7
 */
@Service
public class BusinessHomeService {
	@Autowired
	private BusinessHomeMapper businessHomeMapper;
	
	private static final String 产品登记编码_PAGEID = "ProRegistCodeManage001";
	private static final String 识别标识合并_PAGEID = "SvrInvestorMerger001";
	
	public List<VbusinessHomeDto> selectBusinessHomeInfo(String searchDate, List<String> list){
		//查询系统配置的接口
		List<String> interflist = businessHomeMapper.selectInterfType();
		Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("searchDate", searchDate);
//        String isDeptControl = SvrDataUtil.getSysParam(ReportGlobalConstant.SYS_PARA_IS_DEPTCODE_CONTROL);
//        if(StringUtil.equals(isDeptControl, DictCodeList.是否_中债_否.getValue())) {
//            parmMap.put("orgCodeList", CommonUtil.getAuthorityOnlyOrgListByUserName(null));
//        } else {
//            parmMap.put("createDeptList", CommonUtil.getAuthorityDeptByUserName(null));
//        }
		//查询接口监控表数据
        List<BusinessHomeDto> businessinfolist = businessHomeMapper.selectBusinessHomeInfo(parmMap);
        //查询数据检查的数据
        List<BusinessHomeDto> checkdatalist = businessHomeMapper.selectDateCheckInfo(parmMap);
        //查询产品登记编码统计数
        int prodRegistnum = 0;
        //查询识别标识合并统计数
        int mergenum = 0;
        if(list.contains(产品登记编码_PAGEID)){
        	prodRegistnum = businessHomeMapper.selectRegistnum(parmMap);
        } else {
        	prodRegistnum = -1;
        }
        if(list.contains(识别标识合并_PAGEID)){
        	mergenum = businessHomeMapper.selectMergenum(parmMap);
        } else {
        	mergenum = -1;
        }
        //接口文件总数
        int accessFiletotal = 0;
        //数据检查总数
        int checkDatatotal = 0;
        //数据维护总数
        int processedDatatotal = 0;
        int chbEquHold = businessHomeMapper.selectChbEquHold();
        int chbInvest = businessHomeMapper.selectChbInvestTrade(searchDate);
        int chbEqudet = businessHomeMapper.selectChbEqudet(searchDate);
        businessHomeMapper.selectChbEquHold();
		for (BusinessHomeDto businessinfo : businessinfolist) {
			// 如果系统存在该接口
			if (businessinfo.getInterfFileType() != null && interflist.contains(businessinfo.getInterfFileType())) {
				for (BusinessHomeDto checkdata : checkdatalist) {
					if (businessinfo.getInterfFileType().equals(checkdata.getInterfFileType())) {
						// businessinfo.setDatanum(checkdata.getDatanum());
						// 检查通过c
						businessinfo.setCheckSuccessnum(businessinfo.getDatanum() - checkdata.getCheckfailnum());
						// 检查不通过
						businessinfo.setCheckfailnum(checkdata.getCheckfailnum());
						// 待处理
						businessinfo.setProcessednum(checkdata.getCheckfailnum());
					}
				}
				checkDatatotal = checkDatatotal + businessinfo.getDatanum();
				accessFiletotal = accessFiletotal + businessinfo.getAccessFilenum();
			}
		}
		for (BusinessHomeDto checkdata : checkdatalist) {
			if(checkdata.getInterfFileType() != null && interflist.contains(checkdata.getInterfFileType())){
				processedDatatotal = processedDatatotal + checkdata.getCheckfailnum();
			}
		}
		processedDatatotal = processedDatatotal+chbEquHold+chbInvest+chbEqudet;
        List<VbusinessHomeDto> vlist = dealVdto(businessinfolist,interflist,chbEquHold,chbInvest,chbEqudet);
        VbusinessHomeDto divdto = new VbusinessHomeDto();
        divdto.setReportCode("TOTAL");
        divdto.setProdRegistnum(prodRegistnum);
        divdto.setMergenum(mergenum);
        divdto.setAccessFiletotal(accessFiletotal);
        divdto.setCheckDatatotal(checkDatatotal);
        divdto.setProcessedDatatotal(processedDatatotal);
        vlist.add(divdto);
		return vlist;
	}
	
	public List<VbusinessHomeDto> dealVdto(List<BusinessHomeDto> list,List<String> interflist,int chbEquHold,int chbInvest,int chbEqudet){
		List<VbusinessHomeDto> vdtolist = new ArrayList<VbusinessHomeDto>();
		for (BusinessHomeDto homedto : list) {
			VbusinessHomeDto vdto = new VbusinessHomeDto();
			if (homedto.getInterfFileType() != null && interflist.contains(homedto.getInterfFileType())) {
				if(InterfDefine.投资者信息接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.投资者信息接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_投资者身份信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_投资者身份信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_投资者身份信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_投资者身份信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("td-debt-link-disabled",homedto.getCheckfailnum(),"EquityInfo001","REPORT_002004","svreport/submitmaintenance/equityinfo/EquityInfo001/init","6",InterfDefine.投资者信息接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",chbEquHold,"EquityInfo001","REPORT_001003001","svreport/submitmaintenance/equityinfo/EquityInfo001/init","7",InterfDefine.投资者信息接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.投资者持有信息接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.投资者持有信息接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_投资者持有信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_投资者持有信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_投资者持有信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_投资者持有信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("td-debt-link-disabled",homedto.getCheckfailnum(),"InvestorsHoldInfo001","REPORT_002004","svreport/submitmaintenance/investorsholdinfo/InvestorsHoldInfo001/init","6",InterfDefine.投资者持有信息接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",chbInvest,"InvestorsHoldInfo001","REPORT_001003004","svreport/submitmaintenance/investorsholdinfo/InvestorsHoldInfo001/init","7",InterfDefine.投资者持有信息接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.投资者交易信息接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.投资者交易信息接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_投资者交易信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_投资者交易信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_投资者交易信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_投资者交易信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.投资者交易信息接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.投资者交易信息接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.交易对手接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.交易对手接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_交易对手信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_交易对手信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_交易对手信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_交易对手信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.交易对手接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.交易对手接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.产品基本信息接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.产品基本信息接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_产品基本要素信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_产品基本要素信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_产品基本要素信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_产品基本要素信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.产品基本信息接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.产品基本信息接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.产品开放计划接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.产品开放计划接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_产品开放计划信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_产品开放计划信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_产品开放计划信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_产品开放计划信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.产品开放计划接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.产品开放计划接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.产品收益率净值接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.产品收益率净值接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_产品收益率净值信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_产品收益率净值信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_产品收益率净值信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_产品收益率净值信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.产品收益率净值接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.产品收益率净值接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.产品费用交易接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.产品费用交易接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_产品费用交易信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_产品费用交易信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_产品费用交易信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_产品费用交易信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.产品费用交易接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.产品费用交易接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.资产要素信息接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.资产要素信息接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_资产要素信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_资产要素信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_资产要素信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_资产要素信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.资产要素信息接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.资产要素信息接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.投资交易信息接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.投资交易信息接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_投资交易信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_投资交易信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_投资交易信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_投资交易信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.投资交易信息接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.投资交易信息接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.资管计划底层资产持仓接口数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.资管计划底层资产持仓接口数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_资管计划底层资产持仓信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_资管计划底层资产持仓信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_资管计划底层资产持仓信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_资管计划底层资产持仓信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.资管计划底层资产持仓接口数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.资管计划底层资产持仓接口数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.资产基础指标数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.资产基础指标数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_资产基础指标信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_资产基础指标信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_资产基础指标信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_资产基础指标信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.资产基础指标数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.资产基础指标数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.科目余额数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.科目余额数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_科目余额信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_科目余额信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_科目余额信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_科目余额信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.科目余额数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.科目余额数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.债券评级数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.债券评级数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_债券评级信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_债券评级信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_债券评级信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_债券评级信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.债券评级数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.债券评级数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.节假日数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.节假日数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_节假日信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_节假日信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_节假日信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_节假日信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.节假日数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.节假日数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.汇率信息数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.汇率信息数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_汇率行情信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_汇率行情信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_汇率行情信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_汇率行情信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.汇率信息数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.汇率信息数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
				if(InterfDefine.市场机构数据.getCode().equals(homedto.getInterfFileType())){
					vdto.setReportCode(InterfDefine.市场机构数据.getName());
					List<HomeCellDto> cells = new ArrayList<>();
					HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_市场机构信息.getValue(),"1");
					cells.add(cell1);
					HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_市场机构信息.getValue(),"1");
					cells.add(cell2);
					HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_市场机构信息.getValue(),"1");
					cells.add(cell3);
					HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_市场机构信息.getValue(),"1");
					cells.add(cell4);
					HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
					cells.add(cell5);
					HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.市场机构数据.getCode(),"1");
					cells.add(cell6);
					HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.市场机构数据.getCode(),"1");
					cells.add(cell7);
					vdto.setCells(cells);
					vdtolist.add(vdto);
				}
			}
			if(InterfDefine.投资者明细信息接口数据.getCode().equals(homedto.getInterfFileType())){
				vdto.setReportCode(InterfDefine.投资者明细信息接口数据.getName());
				List<HomeCellDto> cells = new ArrayList<>();
				HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_投资者明细信息.getValue(),"1");
				cells.add(cell1);
				HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_投资者明细信息.getValue(),"1");
				cells.add(cell2);
				HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_投资者明细信息.getValue(),"1");
				cells.add(cell3);
				HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_投资者明细信息.getValue(),"1");
				cells.add(cell4);
				HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
				cells.add(cell5);
				HomeCellDto cell6= dealCellDto("td-debt-link-disabled",homedto.getCheckfailnum(),"SvrEquityBusinessDetails001","REPORT_002004","svreport/submitmaintenance/businessdetailed/SvrEquityBusinessDetails001/init","6",InterfDefine.投资者明细信息接口数据.getCode(),"1");
				cells.add(cell6);
				HomeCellDto cell7= dealCellDto("debt-info-link",chbEqudet,"SvrEquityBusinessDetails001","REPORT_001003001","svreport/submitmaintenance/businessdetailed/SvrEquityBusinessDetails001/init","7",InterfDefine.投资者明细信息接口数据.getCode(),"1");
				cells.add(cell7);
				vdto.setCells(cells);
				vdtolist.add(vdto);
			}
			// add by zhangliang 20210115 增加接口类型
			if(InterfDefine.产品申报信息接口数据.getCode().equals(homedto.getInterfFileType())){
				vdto.setReportCode(InterfDefine.产品申报信息接口数据.getName());
				List<HomeCellDto> cells = new ArrayList<>();
				HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_产品申报登记信息.getValue(),"1");
				cells.add(cell1);
				HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_产品申报登记信息.getValue(),"1");
				cells.add(cell2);
				HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_产品申报登记信息.getValue(),"1");
				cells.add(cell3);
				HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_产品申报登记信息.getValue(),"1");
				cells.add(cell4);
				HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
				cells.add(cell5);
				HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.产品申报信息接口数据.getCode(),"1");
				cells.add(cell6);
				HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.产品申报信息接口数据.getCode(),"1");
				cells.add(cell7);
				vdto.setCells(cells);
				vdtolist.add(vdto);
			}
			if(InterfDefine.产品分红交易接口数据.getCode().equals(homedto.getInterfFileType())){
				vdto.setReportCode(InterfDefine.产品分红交易接口数据.getName());
				List<HomeCellDto> cells = new ArrayList<>();
				HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_产品分红登记信息.getValue(),"1");
				cells.add(cell1);
				HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_产品分红登记信息.getValue(),"1");
				cells.add(cell2);
				HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_产品分红登记信息.getValue(),"1");
				cells.add(cell3);
				HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_产品分红登记信息.getValue(),"1");
				cells.add(cell4);
				HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
				cells.add(cell5);
				HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.产品分红交易接口数据.getCode(),"1");
				cells.add(cell6);
				HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.产品分红交易接口数据.getCode(),"1");
				cells.add(cell7);
				vdto.setCells(cells);
				vdtolist.add(vdto);
			}
			if(InterfDefine.周期型产品余额接口数据.getCode().equals(homedto.getInterfFileType())){
				vdto.setReportCode(InterfDefine.周期型产品余额接口数据.getName());
				List<HomeCellDto> cells = new ArrayList<>();
				HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_周期型产品余额.getValue(),"1");
				cells.add(cell1);
				HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_周期型产品余额.getValue(),"1");
				cells.add(cell2);
				HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_周期型产品余额.getValue(),"1");
				cells.add(cell3);
				HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_周期型产品余额.getValue(),"1");
				cells.add(cell4);
				HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
				cells.add(cell5);
				HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.周期型产品余额接口数据.getCode(),"1");
				cells.add(cell6);
				HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.周期型产品余额接口数据.getCode(),"1");
				cells.add(cell7);
				vdto.setCells(cells);
				vdtolist.add(vdto);
			}
			if(InterfDefine.监管区域客户组别接口数据.getCode().equals(homedto.getInterfFileType())){
				vdto.setReportCode(InterfDefine.监管区域客户组别接口数据.getName());
				List<HomeCellDto> cells = new ArrayList<>();
				HomeCellDto cell1= dealCellDto("debt-info-link",homedto.getAccessFilenum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","1",DictCodeListExt2.上游接口文件类型_监管区域客户组别信息.getValue(),"1");
				cells.add(cell1);
				HomeCellDto cell2= dealCellDto("debt-info-link",homedto.getExceptionnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","2",DictCodeListExt2.上游接口文件类型_监管区域客户组别信息.getValue(),"1");
				cells.add(cell2);
				HomeCellDto cell3= dealCellDto("debt-info-link",homedto.getNormalnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","3",DictCodeListExt2.上游接口文件类型_监管区域客户组别信息.getValue(),"1");
				cells.add(cell3);
				HomeCellDto cell4= dealCellDto("debt-info-link",homedto.getDatanum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","4",DictCodeListExt2.上游接口文件类型_监管区域客户组别信息.getValue(),"1");
				cells.add(cell4);
				HomeCellDto cell5= dealCellDto("debt-link-disabled",homedto.getCheckSuccessnum(),"ReportUpInterfMonitor001","REPORT_004007","svreport/system/upinterfmonitor/ReportUpInterfMonitor001/init","5","1","1");
				cells.add(cell5);
				HomeCellDto cell6= dealCellDto("debt-info-link",homedto.getCheckfailnum(),"ErrorMessage001","REPORT_002004","chb/errormessage/ErrorMessage001/init","6",InterfDefine.监管区域客户组别接口数据.getCode(),"1");
				cells.add(cell6);
				HomeCellDto cell7= dealCellDto("debt-info-link",homedto.getProcessednum(),"SvrOperationalInfo001","REPORT_001005","common/infomanagement/SvrOperationalInfo001/init","7",InterfDefine.监管区域客户组别接口数据.getCode(),"1");
				cells.add(cell7);
				vdto.setCells(cells);
				vdtolist.add(vdto);
			}
		}
		return vdtolist;
	}
	
	private HomeCellDto dealCellDto(String cellClass,int cellText, String cellPageid, String layId, String dataType,
			String dataSubtype, String dataNum, String dataRepCode) {
		HomeCellDto dto = new HomeCellDto();
		dto.setCellClass(cellClass);
		dto.setCellText(cellText+"");
		dto.setCellPageid(cellPageid);
		dto.setLayId(layId);
		dto.setDataType(dataType);
		dto.setDataSubtype(dataSubtype);
		dto.setDataNum(dataNum);
		dto.setDataRepCode(dataRepCode);
		return dto;
	}

	@Transactional
    @DataSource(DataSourceType.OTHER)
	public List<String> selectMenulist() {
		List<String> menulist = Stream.of(产品登记编码_PAGEID,识别标识合并_PAGEID).collect(toList());
		return businessHomeMapper.selectMenulist(menulist);
	}
}
