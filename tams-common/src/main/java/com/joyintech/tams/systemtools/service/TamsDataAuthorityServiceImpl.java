/*
 * 文件名：TamsDataAuthorityServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2019年3月1日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.tams.systemtools.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.framework.security.data.GridColumnAuthInfo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.AbstractDataAuthorityService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 数据权限接口实现
 * 
 * @author 王波
 * @version 1.0 2019年3月1日
 * @see TamsDataAuthorityServiceImpl
 * @since 1.0
 */
@Component
@Primary
public class TamsDataAuthorityServiceImpl extends AbstractDataAuthorityService {
    
    public static final String SESSION_DEPT_CODE = "deptCode";


    public static Boolean enabled = null;

	/**
     * {@inheritDoc}
     */
    @Override
    public List<String> getDeptCodeList() {
        if (SecurityUtil.hasAuthenticated()) {
            List<String> tempResultList = (List<String>) SecurityUtil.getFromSession(SESSION_DEPT_CODE);
            if (!StringUtil.isEmptyList(tempResultList)) {
                return tempResultList;
            }

            return CommonUtil.getAuthorityDeptByUserName(null);
        }
    
        return null;
    }

    /**
     * 不做数据权限的sql
     * {@inheritDoc}
     */
    @Override
    public List<String> ignoreMapper() {
        List<String> ignoreList = defaultIgnore();
        
        if (!SecurityUtil.hasAuthenticated()
            || (SecurityUtil.hasAuthenticated() && SecurityUtil.getLoginUser().isAdmin())) {
            // 如果未登录或者登录用户为admin，则所有的sql都不需要进行权限过滤
            ignoreList.add("com.joyintech.**.*");
            return ignoreList;
        }
        else {
            // 如果当前用户操作来自，非查询画面，或者为删除按钮以及行内操作按钮，则对所有SQL不做数据权限控制
            HttpServletRequest request = RequestUtil.getCurrentRequest();
            if(null != request) {
                String pageIdForLog = request.getParameter("pageIdForLog");
                String elementIdForLog = request.getParameter("elementIdForLog");
                if(StringUtil.isNotEmpty(pageIdForLog)
                    && (!StringUtil.contains(pageIdForLog, "001") || StringUtil.startsWith(elementIdForLog, "TABLE_ROW_") || StringUtil.endsWith(elementIdForLog, "_delete"))) {
                    ignoreList.add("com.joyintech.**.*");
                }
            }
            
            // 以下SQL中不需要添加数据权限控制。
            // 框架的代码不需要做权限控制
            ignoreList.add("com.joyintech.system.**");
            ignoreList.add("com.joyintech.tools.**");
            ignoreList.add("com.joyintech.workflow.**");
            
            // 平安忽略数据权限控制的mapper
            investIgnoreListDeal(ignoreList);
            
            // 微服务忽略数据权限控制的mapper
            microserviceIgnoreList(ignoreList);
            
            ignoreList.add("com.joyintech.tams.systemtools.dao.**");
            ignoreList.add("com.joyintech.tams.common.base.dao.**");
            
            ignoreList.add("com.joyintech.tams.report.home.dao.ReportHomeMapper.*");
            ignoreList.add("com.joyintech.tams.report.common.dao.BaseCommonMapper.**");
            ignoreList.add("com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryProdid");
            ignoreList.add("com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryAllRegistCode");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfo001Mapper.queryEquityInfoList");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.exchangerate.dao.Exchangerate001Mapper.selectDataCount");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.selectAllHoldInfoData");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.selectAllSubHoldInfoData");
            ignoreList.add("com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper.isNotSubmitP");
            ignoreList.add("com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper.selectIsExsit");
            ignoreList.add("com.joyintech.tams.report.system.instregbankcode.dao.SysInstRegBankCode001Mapper.selectDataCount");
            ignoreList.add("com.joyintech.tams.report.system.instregbankcode.dao.SysInstRegBankCode002Mapper.selectRegistBankCodeDataCount");
            ignoreList.add("com.joyintech.tams.report.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper.isNotSubmitP");
            ignoreList.add("com.joyintech.tams.report.submitexamination.delayverification.dao.DelayVerification001Mapper.*");
            ignoreList.add("com.joyintech.tams.report.maintenance.dao.EquityBaseInfo001Mapper.getEquityBaseList");
            ignoreList.add("com.joyintech.tams.report.maintenance.dao.EquityBaseInfo001Mapper.queryProdInfo");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.businessdetailed.dao.EquityBusiDetails001Mapper.isDefend");
            ignoreList.add("com.joyintech.tams.report.common.dao.ViewCompareDetailMapper.selectDetail");
            ignoreList.add("com.joyintech.tams.report.submitexamination.checkreport.dao.CheckReport001Mapper.queryPrdNameList");
            ignoreList.add("com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryPrdRegList");
            ignoreList.add("com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.selectProdIsUsed");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfo001Mapper.checkDetailData");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfo001Mapper.checkHoldData");
            ignoreList.add("com.joyintech.tams.report.queryanalysis.dao.ProdShareCount001Mapper.searchPage");
            ignoreList.add("com.joyintech.tams.report.submitexamination.delayverification.dao.DelayVerification001Mapper.readConfig");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.getProdidByPrdRegistCode");
            ignoreList.add("com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.selectProdInfo");
            ignoreList.add("com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper.getAllRegistBankCodeSet");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.selectStatusEInvestholdInfoData");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.selectAllInvestholdInfoData");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.selectStatusAInvestholdInfoData");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.getSubmitFeedbackStatusList");
            ignoreList.add("com.joyintech.tams.report.interf.cnbond.dao.CnBondInterfMapper.selectOrgCodeByRegistBankCode");
            ignoreList.add("com.joyintech.tams.report.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper.selectExistsByIndentifyMark");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.countInvestorByIndentifyMark");
//            ignoreList.add("com.joyintech.tams.report.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper");
            ignoreList.add("com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper.selectAlreadySubmitDataCount");
            ignoreList.add("com.joyintech.tams.svreport.chb.monthreport.dao.ChbMonthReportDetailMapper.*");
            ignoreList.add("com.joyintech.tams.svreport.pbc.assetdebt.dao.**");
            ignoreList.add("com.joyintech.tams.svreport.pbc.prod.dao.PbcReportProdDetailMapper.*");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfoDistinctMapper.*");
            ignoreList.add("com.joyintech.tams.report.investormerger.dao.ChbInvestholdInfoCheckDtoMapper.*");
            
            // db2 专用
            ignoreList.add("com.joyintech.tams.report.submitexamination.delayverification.dao.DelayVerification001Mapper_db2.searchDate");
            //ignoreList.add("com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper_db2.selectAllData");
            ignoreList.add("com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper_db2.isNotSubmitP");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfo001Mapper_db2.queryEquityInfoList");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfo001Mapper_db2.checkHoldDataSubmit");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfo001Mapper_db2.updateBatchEquityInfo");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.equityinfo.dao.EquityInfo001Mapper_db2.updateBatchEquityInfoExcel");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper_db2.selectAllHoldInfoData");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper_db2.selectAllSubHoldInfoData");
            ignoreList.add("com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper_db2.selectIsExsit");
            ignoreList.add("com.joyintech.tams.report.maintenance.dao.EquityBaseInfo001Mapper_db2.getEquityBaseList");
            ignoreList.add("com.joyintech.tams.report.submitmaintenance.businessdetailed.dao.EquityBusiDetails001Mapper_db2.insertExcelEquityRegist");
            ignoreList.add("com.joyintech.tams.report.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper_db2.queryProdid");
            ignoreList.add("com.joyintech.tams.report.queryanalysis.dao.ProdShareCount001Mapper_db2.searchPage");
            
            
            /*****信息披露****/
            ignoreList.add("com.joyintech.tams.disclosure.information.*");
            ignoreList.add("com.joyintech.tams.disclosure.home.dao.ReportHomeMapper.*");
            ignoreList.add("com.joyintech.tams.disclosure.common.base.dao.**");
            ignoreList.add("com.joyintech.tams.disclosure.common.dao.BaseCommonMapper.**");
            ignoreList.add("com.joyintech.tams.disclosure.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryProdid");
            ignoreList.add("com.joyintech.tams.disclosure.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryAllRegistCode");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.equityinfo.dao.EquityInfo001Mapper.queryEquityInfoList");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.exchangerate.dao.Exchangerate001Mapper.selectDataCount");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.selectAllHoldInfoData");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.selectAllSubHoldInfoData");
            ignoreList.add("com.joyintech.tams.disclosure.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper.isNotSubmitP");
            ignoreList.add("com.joyintech.tams.disclosure.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper.selectIsExsit");
            ignoreList.add("com.joyintech.tams.disclosure.system.instregbankcode.dao.SysInstRegBankCode001Mapper.selectDataCount");
            ignoreList.add("com.joyintech.tams.disclosure.system.instregbankcode.dao.SysInstRegBankCode002Mapper.selectRegistBankCodeDataCount");
            ignoreList.add("com.joyintech.tams.disclosure.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper.isNotSubmitP");
            ignoreList.add("com.joyintech.tams.disclosure.submitexamination.delayverification.dao.DelayVerification001Mapper.searchDate");
            ignoreList.add("com.joyintech.tams.disclosure.maintenance.dao.EquityBaseInfo001Mapper.getEquityBaseList");
            ignoreList.add("com.joyintech.tams.disclosure.maintenance.dao.EquityBaseInfo001Mapper.queryProdInfo");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.businessdetailed.dao.EquityBusiDetails001Mapper.isDefend");
            ignoreList.add("com.joyintech.tams.disclosure.common.dao.ViewCompareDetailMapper.selectDetail");
            ignoreList.add("com.joyintech.tams.disclosure.submitexamination.checkreport.dao.CheckReport001Mapper.queryPrdNameList");
            ignoreList.add("com.joyintech.tams.disclosure.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.queryPrdRegList");
            ignoreList.add("com.joyintech.tams.disclosure.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.selectProdIsUsed");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.equityinfo.dao.EquityInfo001Mapper.checkDetailData");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.equityinfo.dao.EquityInfo001Mapper.checkHoldData");
            ignoreList.add("com.joyintech.tams.disclosure.queryanalysis.dao.ProdShareCount001Mapper.searchPage");
            ignoreList.add("com.joyintech.tams.disclosure.submitexamination.delayverification.dao.DelayVerification001Mapper.readConfig");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.getProdidByPrdRegistCode");
            ignoreList.add("com.joyintech.tams.disclosure.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper.selectProdInfo");
            ignoreList.add("com.joyintech.tams.disclosure.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper.getAllRegistBankCodeSet");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.selectStatusEInvestholdInfoData");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.selectAllInvestholdInfoData");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.selectStatusAInvestholdInfoData");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.getSubmitFeedbackStatusList");
            ignoreList.add("com.joyintech.tams.disclosure.interf.cnbond.dao.CnBondInterfMapper.selectOrgCodeByRegistBankCode");
            ignoreList.add("com.joyintech.tams.disclosure.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper.selectExistsByIndentifyMark");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper.countInvestorByIndentifyMark");
//        ignoreList.add("com.joyintech.tams.disclosure.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper");
            ignoreList.add("com.joyintech.tams.disclosure.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper.selectAlreadySubmitDataCount");
            //infoDisclosureReadAndGenMapper.getRepRuleInfo
            //信息披露增加
            ignoreList.add("com.joyintech.tams.infodisclosure.infodisclosurereadandgen.dao.InfoDisclosureReadAndGenMapper.getRepRuleInfo");
            ignoreList.add("com.joyintech.tams.infodisclosure.infodisclosurereadandgen.dao.InfoDisclosureReadAndGenMapper.getProdList");
            ignoreList.add("com.joyintech.tams.infodisclosure.reportDeal.dao.ReportDealMapper.*");
            
            // db2 专用
            ignoreList.add("com.joyintech.tams.disclosure.submitexamination.delayverification.dao.DelayVerification001Mapper_db2.searchDate");
            //ignoreList.add("com.joyintech.tams.disclosure.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper_db2.selectAllData");
            ignoreList.add("com.joyintech.tams.disclosure.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper_db2.isNotSubmitP");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.equityinfo.dao.EquityInfo001Mapper_db2.queryEquityInfoList");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.equityinfo.dao.EquityInfo001Mapper_db2.checkHoldDataSubmit");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.equityinfo.dao.EquityInfo001Mapper_db2.updateBatchEquityInfo");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.equityinfo.dao.EquityInfo001Mapper_db2.updateBatchEquityInfoExcel");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper_db2.selectAllHoldInfoData");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper_db2.selectAllSubHoldInfoData");
            ignoreList.add("com.joyintech.tams.disclosure.submitmanagement.investorsholdinforegist.dao.InvestorsHoldInfoRegist001Mapper_db2.selectIsExsit");
            ignoreList.add("com.joyintech.tams.disclosure.maintenance.dao.EquityBaseInfo001Mapper_db2.getEquityBaseList");
            ignoreList.add("com.joyintech.tams.disclosure.submitmaintenance.businessdetailed.dao.EquityBusiDetails001Mapper_db2.insertExcelEquityRegist");
            ignoreList.add("com.joyintech.tams.disclosure.system.proregistcodemanage.dao.ProRegistCodeManage001Mapper_db2.queryProdid");
            ignoreList.add("com.joyintech.tams.disclosure.queryanalysis.dao.ProdShareCount001Mapper_db2.searchPage");
            ignoreList.add("com.joyintech.tams.infodisclosure.ftppath.dao.FtpPathMapper.getReportCategory");
            
            // 产品代码下拉框
            ignoreList.add("com.joyintech.tams.infodisclosure.getprdinfoselect.dao.GetPrdInfoSelectMapper.*");
            
            ignoreList.add("com.joyintech.service.valuation.**");
        }
        
        
        return ignoreList;
    }

    @Override
    public List<GridColumnAuthInfo> getGridColumn() {
        return null;
    }

    /**
     * 平安忽略数据权限控制的mapper
     * <功能详细描述>
     * @param ignoreList 待忽略的列表
     * @see
     * @since 1.0
     */
    private void investIgnoreListDeal(List<String> ignoreList) {
        ignoreList.add("com.joyintech.tams.invest.common.risktrial.dao.SupervisionCalcDao");
        ignoreList.add("com.joyintech.tams.invest.common.risktrial.dao.RiskTrialCommMapper.*");
    // 一体化对应etliu start
        ignoreList.add("com.joyintech.tams.invest.common.component.base.core.impl.dao.PlatformDateImplMapper.*");
     // 一体化对应etliu end
        
        ignoreList.add("com.joyintech.tams.invest.remind.dao.PaActiveRemind001Mapper.*");
        ignoreList.add("com.joyintech.tams.component.business.common.dao.DateCalcMapper.*");
        // 一体化对应etliu start
        ignoreList.add("com.joyintech.interf.**.*");
        ignoreList.add("com.joyintech.tams.valuation.**.*");
        ignoreList.add("com.joyintech.valuation.**.*");
        ignoreList.add("com.joyintech.tams.invest.common.valuationinterf.**.*");
        // 一体化对应etliu end
    }
    
    /**
     * 微服务忽略数据权限控制的mapper
     * <功能详细描述>
     * @param ignoreList 待忽略的列表
     * @see
     * @since 1.0
     */
    private void microserviceIgnoreList(List<String> ignoreList) {
        // 除了资金账户管理和证券账户管理，其他的都不加数据权限
        ignoreList.add("com.joyintech.common.**");
        ignoreList.add("com.joyintech.tams.component.base.**");
        ignoreList.add("com.joyintech.tams.component.business.**");
        ignoreList.add("com.joyintech.service.interf.**");
        ignoreList.add("com.joyintech.service.trade.**");
        ignoreList.add("com.joyintech.service.period.**");
        ignoreList.add("com.joyintech.service.asset.**");
        ignoreList.add("com.joyintech.service.common.**");
        ignoreList.add("com.joyintech.service.document.**");
        ignoreList.add("com.joyintech.service.entrust.**");
        ignoreList.add("com.joyintech.service.portfolio.**");
        ignoreList.add("com.joyintech.service.product.**");
        ignoreList.add("com.joyintech.service.report.**");
        ignoreList.add("com.joyintech.service.risk.**");
        ignoreList.add("com.joyintech.service.settle.**");
        ignoreList.add("com.joyintech.service.basicdata.exchangerateinfo.**");
        ignoreList.add("com.joyintech.service.basicdata.mstbondquotationinfo.**");
        ignoreList.add("com.joyintech.service.basicdata.mstcalendartype.**");
        ignoreList.add("com.joyintech.service.basicdata.mstcoinfundquotationinfo.**");
        ignoreList.add("com.joyintech.service.basicdata.mstcustomernature.**");
        ignoreList.add("com.joyintech.service.basicdata.mstfeevarieties.**");
        ignoreList.add("com.joyintech.service.basicdata.mstindexquotationinfo.**");
        ignoreList.add("com.joyintech.service.basicdata.mstinterestratequotationinfo.**");
        ignoreList.add("com.joyintech.service.basicdata.mstmarketcalendarinfo.**");
        ignoreList.add("com.joyintech.service.basicdata.mstmarketinstitutions.**");
        ignoreList.add("com.joyintech.service.basicdata.mstnaturalperson.**");
        ignoreList.add("com.joyintech.service.basicdata.mstnetassetquotationinfo.**");
        ignoreList.add("com.joyintech.service.basicdata.mstopicsparamante.**");
        ignoreList.add("com.joyintech.service.basicdata.mstperformancebenchmark.**");
        ignoreList.add("com.joyintech.service.basicdata.msttradeopponent.**");
        ignoreList.add("com.joyintech.service.basicdata.yieldcurveinfo.**");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean enabled() {
        return StringUtil.equals(DictCodeList.是否_是.getValue(), CacheUtil.getSysParam(GlobalConstant.DATA_AUTHORITY));
    }

    @Override
    public List<String> getOrgCodeList() {
        return null;
    }
}
