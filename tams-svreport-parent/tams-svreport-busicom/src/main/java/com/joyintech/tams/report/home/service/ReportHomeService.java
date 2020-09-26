package com.joyintech.tams.report.home.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.home.dao.ReportHomeMapper;
import com.joyintech.tams.report.home.dto.InfoAreaDto;
import com.joyintech.tams.report.home.dto.SubmitInformationRegistrationDto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 系统主页面用Service
 *
 * @author 刘二通
 * @version 2016年10月14日
 * @see ReportHomeService
 * @since
 */
@Service
public class ReportHomeService{
    /**
     * 业务dao
     */
    @Autowired
    private ReportHomeMapper reportHomeMapper;
    /**
     * 根据查询日期来查询相关的报送信息
     *
     * @param searchDate 画面的查询参数
     * @return 存储数据的Dto
     * @see
     * @since 1.0
     */
	private static final String G06报表_PAGEID = "SvrG06MonthReport001";
	private static final String 人行报表_PAGEID = "PbcMonthReport001";
	private static final String 投资者身份信息_PAGEID = "EquityInfoRegist001";
	private static final String 投资者持有信息_PAGEID = "InvestorsHoldInfoRegist001";
	private static final String 投资者明细信息_PAGEID = "SvrEquityBusiInfoRegist001";
	private static final String 发行登记_PAGEID = "SvrProdPersonIssue001";
	private static final String 存续期登记_PAGEID = "SvrProdDurationReg001";
	private static final String 募集总量登记_PAGEID = "SvrRaiseTotalRegist001";
	private static final String 申报登记_PAGEID = "SvrPerProDeclareReg001";
	private static final String 终止登记_PAGEID = "SvrProdTerminationRegist001";
	private static final String 资产要素_PAGEID = "SvrAssetInfoRegist001";
	private static final String 资产估值_PAGEID = "SvrChbAstValueRegist001";
	private static final String 底层持仓_PAGEID = "SvrChbBotAstPositionRegist001";
	private static final String 投资交易_PAGEID = "SvrInvestTradeRegist001";
	private static final String 产品月报_PAGEID = "SvrMonthReport001";
	private static final String 资产池基本信息_PAGEID = "PbcPrdAccountSub";
	private static final String 产品基本信息_PAGEID = "PbcPrdBaseinfoSub";
	private static final String 产品起始募集信息_PAGEID = "PbcPrdInitRaiseSub";
	private static final String 资产池终止信息_PAGEID = "PbcPrdAccountEndSub";
	private static final String 产品终止信息_PAGEID = "PbcPrdBaseinfoEndSub";
	private static final String 产品募集信息_PAGEID = "PbcPrdCashRaiseSub";
	private static final String 资产负债信息_PAGEID = "PbcPrdAssetDebtSub";
	
    
    public Map<String, SubmitInformationRegistrationDto> searchReportInfo(String searchDate,List<String> reportlist) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("searchDate", ToolsUtil.getCurrentTime(DateUtil.parse(searchDate)));
        parmMap.put("NO_SUBMIT", DictCodeListExt2.报送状态_未报送.getValue());
//        String isDeptControl = CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_IS_DEPTCODE_CONTROL);
//        if(StringUtil.equals(isDeptControl, DictCodeList.是否_中债_否.getValue())) {
//            parmMap.put("orgCodeList", CommonUtil.getAuthorityOnlyOrgListByUserName(null));
//        }
//        else {
//            parmMap.put("createDeptList", CommonUtil.getAuthorityDeptByUserName(null));
//        }
        List<SubmitInformationRegistrationDto> dtoList = new ArrayList<SubmitInformationRegistrationDto>();
        if(CollectionUtils.isNotEmpty(reportlist)){
        	for(String report : reportlist){
        		if(DictCodeListExt2.报表名称_产品发行登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbPrdIssueSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_产品申报登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbPrdDeclareSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_人行月度统计报表.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectPbcReport(parmMap));
        		} else if (DictCodeListExt2.报表名称_募集总量登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbPrdRaisetotSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_资管计划底层持仓信息登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbBotAstPosSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_产品存续期登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbPrdDealSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_产品终止登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbPrdEndSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_银行月度统计表.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbMonthReport(parmMap));
        		} else if (DictCodeListExt2.报表名称_资产要素信息登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbAssetInfoSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_资产交易信息登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbInvestTradeSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_资产估值信息登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbAstValSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_投资者身份信息登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbEquSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_投资者持有信息登记.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbInvestholdInfoSub(parmMap));
        		} else if ("28".equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbEqudetSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_资产池基本信息.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectPbcPrdAccountSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_产品基本信息.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectPbcPrdBaseinfoSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_产品起始募集信息.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectPbcInitProdRaisingSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_产品资金募集信息.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectPbcProdRaisingSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_资产池终止信息.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectPbcPrdAccountEndSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_产品终止信息.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectPbcPrdBaseinfoEndSub(parmMap));
        		} else if (DictCodeListExt2.报表名称_资产负债信息.getValue().equals(report)){
        			dtoList.addAll(reportHomeMapper.selectPbcReportAsset(parmMap));
        		} else if ("25".equals(report)){
        			dtoList.addAll(reportHomeMapper.selectChbG06(parmMap));
        		}
        	}
        }
//        List<SubmitInformationRegistrationDto> dtoList = reportHomeMapper.searchReportInfo(parmMap);
        List<SubmitInformationRegistrationDto> checkList = reportHomeMapper.searchReportCheckNum(parmMap);
        // 报送提醒
        int submitRemindnum = 0;
        // 报送检查
        int submitChecknum = 0;
        // 报送完成
        int submitSuccessnum = 0;
        // 报送异常反馈
        int interfBacknum = 0;
        // 删除模版数据
        int deletenum = 0;
        Map<String, SubmitInformationRegistrationDto> resultMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(dtoList)) {
            for (SubmitInformationRegistrationDto dto : dtoList) {
            	if(reportlist.contains(dto.getReporttype())){
            		for(SubmitInformationRegistrationDto checkdto : checkList){
                		if((dto.getReporttype()).equals(checkdto.getReporttype())){
                			// 预警
                			dto.setWarningnum(checkdto.getWarningnum());
                			// 不通过
                			dto.setFailnum(checkdto.getFailnum());
                		}
                	}
                    resultMap.put(dto.getReporttype(), dto);
                    // 报送提醒
                    submitRemindnum = submitRemindnum + dto.getDelaynum()+dto.getDelayingnum()+dto.getSendnum();
                    // 报送完成
                    submitSuccessnum = submitSuccessnum + dto.getManualnum()+dto.getInterfPassnum();
                    // 报送异常反馈
                    interfBacknum = interfBacknum + dto.getInterfBacknum();
                    // 删除模版数据
                    deletenum = deletenum + dto.getDeletenum();
                }
            }
            	
            for(SubmitInformationRegistrationDto checkdto : checkList){
            	if(reportlist.contains(checkdto.getReporttype())){
	    			// 报送检查
	    			submitChecknum = submitChecknum + checkdto.getSubmitChecknum();
            	}
        	}
            SubmitInformationRegistrationDto totaldto = new SubmitInformationRegistrationDto();
            totaldto.setSubmitChecknum(submitChecknum);
            totaldto.setSubmitRemindnum(submitRemindnum);
            totaldto.setSubmitSuccessnum(submitSuccessnum);
            totaldto.setInterfBacknum(interfBacknum);
            totaldto.setDeletenum(deletenum);
            resultMap.put("total", totaldto);
        }
        return resultMap;
    }

    /**
     * <一句话功能简述> 统计主页信息表各区域的数据
     *
     * @return 存储各区域的Map
     * @see
     * @since 1.0
     */
    public Map<String, InfoAreaDto> searchInfoArea() {
        Map<String, InfoAreaDto> resultMap = new HashMap<>();
        Map<String, Object> parmMap = new HashMap<>();

        String isDeptControl = SvrDataUtil.getSysParam(ReportGlobalConstant.SYS_PARA_IS_DEPTCODE_CONTROL);
        if (StringUtil.equals(isDeptControl, DictCodeList.是否_中债_否.getValue())) {
            // parmMap.put("deptCodeList", CommonUtil.getAuthorityOnlyDeptListByUserName(null));
            parmMap.put("orgCodeList", CommonUtil.getAuthorityOnlyOrgListByUserName(null));
        }
        else {
            parmMap.put("createDeptList", CommonUtil.getAuthorityDeptByUserName(null));
        }
        parmMap.put("dataTimeEnd", DateUtil.getCurrentDate());
        List<InfoAreaDto> dataList = reportHomeMapper.searchInfoArea(parmMap);
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (InfoAreaDto dto : dataList) {
                // 前台把其key当控件ID使用
                resultMap.put(dto.getCountType(), dto);
            }
        }
        return resultMap;
    }

    /**
     * 取得可批量导出删除模板的数据 <功能详细描述>
     *
     * @return 查询到的存储了查询结果的map
     * @see
     * @since 1.0
     */
    public Map<String, InfoAreaDto> searcheBatchxportTemplates() {
        Map<String, InfoAreaDto> resultMap = new HashMap<>();
        Map<String, Object> parmMap = new HashMap<>();
        String isDeptControl = SvrDataUtil.getSysParam(ReportGlobalConstant.SYS_PARA_IS_DEPTCODE_CONTROL);
        if (StringUtil.equals(isDeptControl, DictCodeList.是否_中债_否.getValue())) {
            // parmMap.put("deptCodeList", CommonUtil.getAuthorityOnlyDeptListByUserName(null));
            parmMap.put("orgCodeList", CommonUtil.getAuthorityOnlyOrgListByUserName(null));
        }
        else {
            parmMap.put("createDeptList", CommonUtil.getAuthorityDeptByUserName(null));
        }
        List<InfoAreaDto> dataList = reportHomeMapper.searcheBatchxportTemplates(parmMap);
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (InfoAreaDto dto : dataList) {
                // 前台把其key当控件ID使用
                resultMap.put(dto.getCountType(), dto);
            }
        }
        return resultMap;
    }
    
    
    @Transactional
    @DataSource(DataSourceType.OTHER)
    public List<String> selectMenulist(){
    	List<String> menulist1 = new ArrayList<String>();
    	List<String> menulist = reportHomeMapper.selectMenulist();
    	if(!CollectionUtils.isEmpty(menulist)){
    		for(String menu : menulist){
    			switch(menu){
    			case 申报登记_PAGEID: menulist1.add(DictCodeListExt2.报表名称_产品申报登记.getValue());
    			break;
    			case 发行登记_PAGEID: menulist1.add(DictCodeListExt2.报表名称_产品发行登记.getValue());
    			break;
    			case 募集总量登记_PAGEID: menulist1.add(DictCodeListExt2.报表名称_募集总量登记.getValue());
    			break;
    			case 存续期登记_PAGEID: menulist1.add(DictCodeListExt2.报表名称_产品存续期登记.getValue());
    			break;
    			case 终止登记_PAGEID: menulist1.add(DictCodeListExt2.报表名称_产品终止登记.getValue());
    			break;
    			case 资产要素_PAGEID: menulist1.add(DictCodeListExt2.报表名称_资产要素信息登记.getValue());
    			break;
    			case 投资交易_PAGEID: menulist1.add(DictCodeListExt2.报表名称_资产交易信息登记.getValue());
    			break;
    			case 资产估值_PAGEID: menulist1.add(DictCodeListExt2.报表名称_资产估值信息登记.getValue());
    			break;
    			case 底层持仓_PAGEID: menulist1.add(DictCodeListExt2.报表名称_资管计划底层持仓信息登记.getValue());
    			break;
    			case 投资者身份信息_PAGEID: menulist1.add(DictCodeListExt2.报表名称_投资者身份信息登记.getValue());
    			break;
    			case 投资者持有信息_PAGEID: menulist1.add(DictCodeListExt2.报表名称_投资者持有信息登记.getValue());
    			break;
    			case 投资者明细信息_PAGEID: menulist1.add("28");
    			break;
    			case 人行报表_PAGEID: menulist1.add(DictCodeListExt2.报表名称_人行月度统计报表.getValue());
    			break;
    			case 产品月报_PAGEID: menulist1.add(DictCodeListExt2.报表名称_银行月度统计表.getValue());
    			break;
    			case G06报表_PAGEID: menulist1.add("25");
    			break;
    			case 资产池基本信息_PAGEID:menulist1.add(DictCodeListExt2.报表名称_资产池基本信息.getValue());
    			break;
    			case 产品基本信息_PAGEID:menulist1.add(DictCodeListExt2.报表名称_产品基本信息.getValue());
    			break;
    			case 产品起始募集信息_PAGEID:menulist1.add(DictCodeListExt2.报表名称_产品起始募集信息.getValue());
    			break;
    			case 资产池终止信息_PAGEID:menulist1.add(DictCodeListExt2.报表名称_资产池终止信息.getValue());
    			break;
    			case 产品终止信息_PAGEID:menulist1.add(DictCodeListExt2.报表名称_产品终止信息.getValue());
    			break;
    			case 产品募集信息_PAGEID:menulist1.add(DictCodeListExt2.报表名称_产品资金募集信息.getValue());
    			break;
    			case 资产负债信息_PAGEID:menulist1.add(DictCodeListExt2.报表名称_资产负债信息.getValue());
    			}
    		}
    	}
    	return menulist1;
    }
}
