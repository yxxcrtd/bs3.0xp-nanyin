package com.joyintech.tams.disclosure.home.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepFtpPath;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.DateCalcUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
//import com.joyintech.tams.disclosure.common.base.entity.RepFtpPath;
//import com.joyintech.tams.disclosure.common.constant.DisclosureDictCodeList;
//import com.joyintech.tams.disclosure.common.util.DateCalcUtil;
//import com.joyintech.tams.disclosure.common.util.ReportGlobalConstant;
//import com.joyintech.tams.disclosure.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.home.dto.InfoAreaDto;
import com.joyintech.tams.disclosure.home.dto.RepFtpPathDto;
import com.joyintech.tams.disclosure.home.dto.RevalInfo;
import com.joyintech.tams.disclosure.home.dto.SubmitInformationRegistrationDto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 系统主页面用Service
 *
 * @author 刘二通
 * @version 2016年10月14日
 * @see DisclosureHomeService
 * @since
 */
@Service
public class DisclosureHomeService extends BaseService<RevalInfo>{
    /**
     * 业务dao
     */
//    @Autowired
//    private DisclosureHomeMapper reportHomeMapper;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DisclosureHomeService.class);
    /**
     * 报表作业监控:生成失败
     */
    String pageIdWork = "ReportWorkMonitor001";
    
    /**
     * 报表确认管理:待确认
     */
    String pageIdConfirm = "ReportConfirmManage001";
    
    /**
     * 报表披露管理:待披露、已披露
     */
    String pageIdReveal = "ReportRevealManage001";
    
    /**
     * 预计披露:T+1、T+2、T+3
     */
    String pageIdEstimate = "EstimateReveal001";
    
    /**
     * 根据查询日期来查询相关的报送信息
     *
     * @param searchDate 画面的查询参数
     * @return 存储数据的Dto
     * @see
     * @since 1.0
     */
    public Map<String, SubmitInformationRegistrationDto> searchReportInfo(String searchDate) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("searchDate", DateCalcUtil.getEndOfDay(searchDate));
        parmMap.put("searchDateYesterday", DateCalcUtil.getEndOfDay(DateUtil.addDay(searchDate, -1)));
        parmMap.put("NO_SUBMIT", "01");

        String isDeptControl = CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_IS_DEPTCODE_CONTROL);
        if(StringUtil.equals(isDeptControl, DictCodeList.是否_否.getValue())) {
//            parmMap.put("deptCodeList", CommonUtil.getAuthorityOnlyDeptListByUserName(null));
            parmMap.put("orgCodeList", CommonUtil.getAuthorityOnlyOrgListByUserName(null));
        }
        else {
            parmMap.put("createDeptList", CommonUtil.getAuthorityDeptByUserName(null));
        }

//        List<SubmitInformationRegistrationDto> dtoList = reportHomeMapper.searchReportInfo(parmMap);
        
        
        List<SubmitInformationRegistrationDto> dtoList = restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/searchReportInfo",
                parmMap, SubmitInformationRegistrationDto.class);
        
        
        
        
        for (SubmitInformationRegistrationDto dto : dtoList) {
            // 这里，重新设置即将延期的最大日期和最小日期
//            dto.setMindate(DateUtil.addDay(searchDate, -1));
            dto.setMindate(searchDate);
            dto.setMaxdate(searchDate);
        }
        Map<String, SubmitInformationRegistrationDto> resultMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(dtoList)) {
            for (SubmitInformationRegistrationDto dto : dtoList) {
                resultMap.put(dto.getReporttype(), dto);
            }
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

        String isDeptControl = CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_IS_DEPTCODE_CONTROL);
        if (StringUtil.equals(isDeptControl, DictCodeList.是否_否.getValue())) {
            // parmMap.put("deptCodeList", CommonUtil.getAuthorityOnlyDeptListByUserName(null));
            parmMap.put("orgCodeList", CommonUtil.getAuthorityOnlyOrgListByUserName(null));
        }
        else {
            parmMap.put("createDeptList", CommonUtil.getAuthorityDeptByUserName(null));
        }

//        List<InfoAreaDto> dataList = reportHomeMapper.searchInfoArea(parmMap);
        List<InfoAreaDto> dataList = restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/searchInfoArea",
                parmMap, InfoAreaDto.class);
        
        
        
        
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
        String isDeptControl = CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_IS_DEPTCODE_CONTROL);
        if (StringUtil.equals(isDeptControl, DictCodeList.是否_否.getValue())) {
            // parmMap.put("deptCodeList", CommonUtil.getAuthorityOnlyDeptListByUserName(null));
            parmMap.put("orgCodeList", CommonUtil.getAuthorityOnlyOrgListByUserName(null));
        }
        else {
            parmMap.put("createDeptList", CommonUtil.getAuthorityDeptByUserName(null));
        }
//        List<InfoAreaDto> dataList = reportHomeMapper.searcheBatchxportTemplates(parmMap);
        
        List<InfoAreaDto> dataList = restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/searcheBatchxportTemplates",
                parmMap, InfoAreaDto.class);
        
        
        
        
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (InfoAreaDto dto : dataList) {
                // 前台把其key当控件ID使用
                resultMap.put(dto.getCountType(), dto);
            }
        }
        return resultMap;
    }

    /**
     * 获取披露地址信息
     * 
     * @see
     * @since 1.0
     */
    public List<RepFtpPath> getdataTableCols() {
//        List<RepFtpPath> pathList = reportHomeMapper.selectAllpath();
//        return pathList;
    	  return restTemplate.postForList(
                  ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/getdataTableCols",
                  null, RepFtpPath.class);
    }

    /**
     * 查询待披露
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public List<RevalInfo> queryPageListDpl(Map<String, Object> parmMap) {
//        List<RepFtpPathDto> pathList = reportHomeMapper.queryDpl(parmMap);
    	parmMap.put("confirmStatusY",DictCodeList.确认状态_已确认.getValue());
    	parmMap.put("confirmStatusW",DictCodeList.确认状态_未确认.getValue());
        List<RepFtpPathDto> pathList= restTemplate.postForList(
              ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/queryPageListDpl",
              parmMap, RepFtpPathDto.class);
        
        List<RevalInfo> revalInfoList = new ArrayList<RevalInfo>();
        if (null == pathList || pathList.size() == 0) {
            return revalInfoList;
        }
        // 生成失败
        RevalInfo revalInfoSB = new RevalInfo();
        revalInfoSB.setFirstCol("生成失败");
        // 待确认
        RevalInfo revalInfoDQR = new RevalInfo();
        revalInfoDQR.setFirstCol("待确认");
        // 待披露
        RevalInfo revalInfoDPL = new RevalInfo();
        revalInfoDPL.setFirstCol("待披露");
        // 合计
        RevalInfo revalInfoHJ = new RevalInfo();
        revalInfoHJ.setFirstCol("合计");
        
        List<String> rolePageIdList = getRolePageId();
        for(RepFtpPathDto path : pathList){
            if(revalInfoSB.getFirstCol().equals(path.getFirstCol())){
                setReportCount(revalInfoSB,revalInfoHJ,path,pageIdWork,rolePageIdList);
            }else if(revalInfoDQR.getFirstCol().equals(path.getFirstCol())){
                setReportCount(revalInfoDQR,revalInfoHJ,path,pageIdConfirm,rolePageIdList);
            }else if(revalInfoDPL.getFirstCol().equals(path.getFirstCol())){
                setReportCount(revalInfoDPL,revalInfoHJ,path,pageIdReveal,rolePageIdList);
            }
        }
        revalInfoList.add(revalInfoSB);
        revalInfoList.add(revalInfoDQR);
        revalInfoList.add(revalInfoDPL);
        revalInfoList.add(revalInfoHJ);
        return revalInfoList;
//    	return restTemplate.postForList(
//                ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/queryPageListDpl",
//                parmMap, RevalInfo.class);
    }

    /**
     * 查询预计披露
     * 
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public List<RevalInfo> queryPageListYjpl(Map<String, Object> parmMap) {
        parmMap = new HashMap<String, Object>();
        List<RevalInfo> revalInfoList= new ArrayList<RevalInfo>();
        
        // T+1
        String T1 = DateUtil.format(DateUtil.addDay(DateUtil.now(), 1), "yyyy-MM-dd");
        // T+2
        String T2 = DateUtil.format(DateUtil.addDay(DateUtil.now(), 2), "yyyy-MM-dd");
        // T+3
        String T3 = DateUtil.format(DateUtil.addDay(DateUtil.now(), 3), "yyyy-MM-dd");
        
        parmMap.put("sysDate",T1);
//        List<RepFtpPathDto> pathListT1 = reportHomeMapper.queryYjpl(parmMap);
        List<RepFtpPathDto> pathListT1 = restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/queryPageListYjpl",
                parmMap, RepFtpPathDto.class);
        
        parmMap.put("sysDate",T2);
//        List<RepFtpPathDto> pathListT2 = reportHomeMapper.queryYjpl(parmMap);
        List<RepFtpPathDto> pathListT2 = restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/queryPageListYjpl",
                parmMap, RepFtpPathDto.class);
        
        parmMap.put("sysDate",T3);
//        List<RepFtpPathDto> pathListT3 = reportHomeMapper.queryYjpl(parmMap);
        List<RepFtpPathDto> pathListT3 = restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/queryPageListYjpl",
                parmMap, RepFtpPathDto.class);
        
        
        
        RevalInfo revalInfoT1 = new RevalInfo();
        RevalInfo revalInfoT2 = new RevalInfo();
        RevalInfo revalInfoT3 = new RevalInfo();
        
        revalInfoT1.setFirstCol(DateUtil.format(DateUtil.addDay(DateUtil.now(), 1), "yyyy/MM/dd"));
        revalInfoT2.setFirstCol(DateUtil.format(DateUtil.addDay(DateUtil.now(), 2), "yyyy/MM/dd"));
        revalInfoT3.setFirstCol(DateUtil.format(DateUtil.addDay(DateUtil.now(), 3), "yyyy/MM/dd"));
        List<String> rolePageIdList = getRolePageId();
        for(RepFtpPathDto pathDto : pathListT1){
            setReportCount(revalInfoT1, null, pathDto,pageIdEstimate,rolePageIdList);
        }
        for(RepFtpPathDto pathDto : pathListT2){
            setReportCount(revalInfoT2, null, pathDto,pageIdEstimate,rolePageIdList);
        }
        for(RepFtpPathDto pathDto : pathListT3){
            setReportCount(revalInfoT3, null, pathDto,pageIdEstimate,rolePageIdList);
        }
        revalInfoList.add(revalInfoT1);
        revalInfoList.add(revalInfoT2);
        revalInfoList.add(revalInfoT3);
        return revalInfoList;
//    	return restTemplate.postForList(
//                ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/queryPageListYjpl",
//                null, RevalInfo.class);
    }

    /**
     * 已披露(已披露\生成失败)
     * 
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public List<RevalInfo> queryListYpl(Map<String, Object> parmMap) {
        List<RevalInfo> revalInfoList= new ArrayList<RevalInfo>();
        List<String> rolePageIdList = getRolePageId();
//        List<RepFtpPathDto> pathList = reportHomeMapper.queryYpl(parmMap);
        
        
        List<RepFtpPathDto> pathList = restTemplate.postForList(
      ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/queryListYpl",
      parmMap, RepFtpPathDto.class);
        if(!StringUtil.isEmptyList(pathList)){
        	RevalInfo revalInfo = new RevalInfo();
        	revalInfo.setFirstCol(DictCodeListExt2.披露状态_已披露.getCodeName());
        	for(RepFtpPathDto path : pathList){
        		setReportCount(revalInfo, null, path,pageIdReveal,rolePageIdList);
        	}
        	revalInfoList.add(revalInfo);
        }
        
//        List<RepFtpPathDto> pathListFail = reportHomeMapper.queryYplFail(parmMap);
        
        List<RepFtpPathDto> pathListFail = restTemplate.postForList(
        	      ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/queryYplFail",
        	      parmMap, RepFtpPathDto.class);
        
        
        if(!StringUtil.isEmptyList(pathListFail)){
        	RevalInfo revalInfo = new RevalInfo();
        	revalInfo.setFirstCol(DictCodeListExt2.披露状态_生成失败.getCodeName());
        	for(RepFtpPathDto path : pathListFail){
        		setReportCount(revalInfo, null, path,pageIdWork,rolePageIdList);
        	}
        	revalInfoList.add(revalInfo);
        }
        
        return revalInfoList;
    	
//    	return restTemplate.postForList(
//                ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/queryListYpl",
//                parmMap, RevalInfo.class);
    }
    
    /**
     * 查询当前用户的界面权限
     * 
     * @return
     * @see
     * @since 1.0
     */
    private List<String> getRolePageId() {
    	//DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<String> rolePageIdList = null;
        String userName = LoginUserUtil.getUserName();
        //userName = userName.contains("admin")?null:userName;//admin用户查询出全部权限
        try {
//            rolePageIdList = reportHomeMapper.queryRolePageId(userName);
            rolePageIdList = restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/disclosureHome/getRolePageId", userName,
                    ArrayList.class);
            
        } catch (Exception e) {
        	logger.error("error:",e);
        } finally {
        	// DynamicDataSourceHolder.clearDataSource();
        }
        if(StringUtil.isEmptyList(rolePageIdList)){
            return new ArrayList<String>();
        }
        return rolePageIdList;
    }

    /**
     * 统计报表数量
     * 
      * @param revalInfo 展示dto
     * @param revalInfoHJ 合计dto
     * @param path 数据源dto
     * @param pageId 界面id
     * @param rolePageIdList 用户界面权限id集合
     * @see
     * @since 1.0
     */
    private void setReportCount(RevalInfo revalInfo,RevalInfo revalInfoHJ,RepFtpPathDto path,String pageId,List<String> rolePageIdList) {
        if(StringUtil.isNotEmpty(path.getId()) && rolePageIdList!=null && StringUtil.isNotEmpty(pageId)){
            BigDecimal reportCountB = path.getReportCount() == null ? BigDecimal.ZERO : path.getReportCount();
            String reportCount = "";
            if(path.getReportCount() == null){
                reportCount = "0";
            }else{
                reportCount = path.getReportCount() + "";
            }
            
            // 用户在界面没有权限时，数据展示为0
            if(!rolePageIdList.contains(pageId)){
                reportCountB = BigDecimal.ZERO;
                reportCount = "0";
            }
            
            switch (path.getId()) {
                case "01":
                    revalInfo.setRepId01(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId01("" + new BigDecimal(revalInfoHJ.getRepId01()==null?"0":revalInfoHJ.getRepId01()).add(reportCountB));}
                    break;
                case "02":
                    revalInfo.setRepId02(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId02("" + new BigDecimal(revalInfoHJ.getRepId02()==null?"0":revalInfoHJ.getRepId02()).add(reportCountB));}
                    break;
                case "03":
                    revalInfo.setRepId03(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId03("" + new BigDecimal(revalInfoHJ.getRepId03()==null?"0":revalInfoHJ.getRepId03()).add(reportCountB));}
                    break;
                case "04":
                    revalInfo.setRepId04(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId04("" + new BigDecimal(revalInfoHJ.getRepId04()==null?"0":revalInfoHJ.getRepId04()).add(reportCountB));}
                    break;
                case "05":
                    revalInfo.setRepId05(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId05("" + new BigDecimal(revalInfoHJ.getRepId05()==null?"0":revalInfoHJ.getRepId05()).add(reportCountB));}
                    break;
                case "06":
                    revalInfo.setRepId06(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId06("" + new BigDecimal(revalInfoHJ.getRepId06()==null?"0":revalInfoHJ.getRepId06()).add(reportCountB));}
                    break;
                case "07":
                    revalInfo.setRepId07(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId07("" + new BigDecimal(revalInfoHJ.getRepId07()==null?"0":revalInfoHJ.getRepId07()).add(reportCountB));}
                    break;
                case "08":
                    revalInfo.setRepId08(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId08("" + new BigDecimal(revalInfoHJ.getRepId08()==null?"0":revalInfoHJ.getRepId08()).add(reportCountB));}
                    break;
                case "09":
                    revalInfo.setRepId09(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId09("" + new BigDecimal(revalInfoHJ.getRepId09()==null?"0":revalInfoHJ.getRepId09()).add(reportCountB));}
                    break;
                case "10":
                    revalInfo.setRepId10(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId10("" + new BigDecimal(revalInfoHJ.getRepId10()==null?"0":revalInfoHJ.getRepId10()).add(reportCountB));}
                    break;
                case "11":
                    revalInfo.setRepId11(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId11("" + new BigDecimal(revalInfoHJ.getRepId11()==null?"0":revalInfoHJ.getRepId11()).add(reportCountB));}
                    break;
                case "12":
                    revalInfo.setRepId12(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId12("" + new BigDecimal(revalInfoHJ.getRepId12()==null?"0":revalInfoHJ.getRepId12()).add(reportCountB));}
                    break;
                case "13":
                    revalInfo.setRepId13(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId13("" + new BigDecimal(revalInfoHJ.getRepId13()==null?"0":revalInfoHJ.getRepId13()).add(reportCountB));}
                    break;
                case "14":
                    revalInfo.setRepId14(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId14("" + new BigDecimal(revalInfoHJ.getRepId14()==null?"0":revalInfoHJ.getRepId14()).add(reportCountB));}
                    break;
                case "15":
                    revalInfo.setRepId15(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId15("" + new BigDecimal(revalInfoHJ.getRepId15()==null?"0":revalInfoHJ.getRepId15()).add(reportCountB));}
                    break;
                case "16":
                    revalInfo.setRepId16(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId16("" + new BigDecimal(revalInfoHJ.getRepId16()==null?"0":revalInfoHJ.getRepId16()).add(reportCountB));}
                    break;
                case "17":
                    revalInfo.setRepId17(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId17("" + new BigDecimal(revalInfoHJ.getRepId17()==null?"0":revalInfoHJ.getRepId17()).add(reportCountB));}
                    break;
                case "18":
                    revalInfo.setRepId18(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId18("" + new BigDecimal(revalInfoHJ.getRepId18()==null?"0":revalInfoHJ.getRepId18()).add(reportCountB));}
                    break;
                case "19":
                    revalInfo.setRepId19(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId19("" + new BigDecimal(revalInfoHJ.getRepId19()==null?"0":revalInfoHJ.getRepId18()).add(reportCountB));}
                    break;
                case "20":
                    revalInfo.setRepId20(reportCount);
                    if(null!=revalInfoHJ){revalInfoHJ.setRepId20("" + new BigDecimal(revalInfoHJ.getRepId20()==null?"0":revalInfoHJ.getRepId20()).add(reportCountB));}
                    break;
                default:
                    break;
            }
        }
    }
}
