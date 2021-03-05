/*
 * 文件名：FinProductBuild002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.build.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt3;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.PtlValParam;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.core.dto.FileCommonDto;
import com.joyintech.tams.component.business.portfolio.dto.FundAccountInfoDto;
import com.joyintech.tams.component.business.portfolio.dto.SecuritiesAccountInfoDto;
import com.joyintech.tams.component.business.prod.BussFinProductFeeInfoService;
import com.joyintech.tams.component.business.prod.BussFinProductOpenPlanService;
import com.joyintech.tams.component.business.prod.BussFinProductPayPlanService;
import com.joyintech.tams.component.business.prod.BussFinProductRevalDocumentService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFundAccountInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductOpenPlanDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductPayPlanDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductRevalDocumentDto;
import com.joyintech.tams.component.business.report.prod.BussRepFinProductInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.build.dto.PtlPortfolioInfoDto;
import com.joyintech.tams.invest.product.productelement.scheduling.dto.PrdIssueScheduleDto;
import com.joyintech.tams.invest.product.productelement.scheduling.service.FinProductScheduling002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 产品新建管理
 * 
 * @author zhangliang
 * @version 1.0 2018年10月12日
 * @see FinProductBuild002Service
 * @since 1.0
 */
@Service
public class FinProductBuild002Service extends BaseService<FinProductBuild002Dto> implements BaseProcessService<FinProductBuild002Dto> {

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductScheduling002Service finProductScheduling002Service;

    /**
     * {@linkplain}
     */
    @Autowired
    private BussRepFinProductInfoService bussRepFinProductInfoService;

    /**
     * {@linkplain}
     */
    @Autowired
    private BussFinProductFeeInfoService bussFinProductFeeInfoService;

    /**
     * 信息披露业务组件Service
     */
    @Autowired(required = false)
    private BussFinProductRevalDocumentService bussFinProductRevalDocumentService;

    /**
     * 开放计划组件
     */
    @Autowired
    private BussFinProductOpenPlanService bussFinProductOpenPlanService;
    
    /**
     * 付息计划组件
     */
    @Autowired
    private BussFinProductPayPlanService bussFinProductPayPlanService;

    /**
     * {@linkplain}
     */
    @Autowired
    private CommonBusinessService commonBusinessService;
    
    /**
     * 附件处理接口
     */
    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;
    
    @Autowired(required = false)
    private FinProductBuild002Service finProductBuild002Service;

    /**
     * 处理其他信息（待处理的组合信息、费用信息）
     * 
     * @param entity 产品信息
     * @param ptlFlag 组合标识
     * @see
     * @since 1.0
     */
    private void insertOtherData(FinProductBuild002Dto entity, boolean ptlFlag) {
        if (ptlFlag) {
            // 未选组合时生成一条待处理的组合信息 (组合代码 = 市场代码 组合名称 = 金融产品简称)
            WorkFlowParameter<PtlPortfolioInfoDto> params = new WorkFlowParameter<PtlPortfolioInfoDto>();
            params.setPageId("PtlManagementInfo002");
            params.setElementId("PtlManagementInfo002_btn_save");
            params.setBusinessCode(entity.getFinprodMarketId());
            params.setServiceName("PtlManagementInfo001Service");
            params.setBusinessType(DictCodeList.共通业务类型_组合管理.getValue());
            // 设置流程业务说明, 组合名称 + "(" + 组合名称 + ")" + "-" + "组合代码" + "(" + "组合代码"+")"
            params.setComment(item -> this.getMessage("PtlManagementInfo001.portfolioName") + "("
                                      + item.getPortfolioName() + ")" + "-"
                                      + this.getMessage("PtlManagementInfo001.portfolioId") + "("
                                      + item.getPortfolioId() + ")");

            try {
                PtlPortfolioInfoDto ptlPortfolioInfoDto = new PtlPortfolioInfoDto();
                BeanUtil.copyProperties(ptlPortfolioInfoDto, entity);
                ptlPortfolioInfoDto.setPortfolioId(entity.getFinprodMarketId());
                ptlPortfolioInfoDto.setPortfolioName(entity.getFinprodAbbr());
                ptlPortfolioInfoDto.setPortfolioType(DictCodeListExt1.组合类型_投资类组合.getValue());
                ptlPortfolioInfoDto.setProfitType(entity.getProfitType());
                ptlPortfolioInfoDto.setOpenType(entity.getOperationType());
                ptlPortfolioInfoDto.setRaiseType(entity.getIssueType());
                ptlPortfolioInfoDto.setInvestNature(entity.getInvestNature());
                ptlPortfolioInfoDto.setVdate(entity.getVdate());
                ptlPortfolioInfoDto.setMdate(entity.getMdate());
				//update by xr TISHKBS-624 新建产品基本信息产品收益标识没有和组合中的是否保本关联 start
				if(entity.getProfitFlag().equals(DictCodeList.产品收益标识_非保本浮动收益.getValue())){//fixed BS-861bug add by zxp
                    ptlPortfolioInfoDto.setIsGuarantee("N");
                }else{
                    ptlPortfolioInfoDto.setIsGuarantee("Y");
                }
				//update by xr TISHKBS-624 新建产品基本信息产品收益标识没有和组合中的是否保本关联 end
                PtlValParam ptlValParam = new PtlValParam();
                BeanUtil.copyProperties(ptlValParam, entity.getBussFinProductValuationDto());
                ptlPortfolioInfoDto.setPtlValParam(ptlValParam);

                // 二级法人改造 modify by wj  20200619 五个特殊参数 修改 为特殊取值方法
                //String userName = CacheUtil.getSysParam(BussinessConstant.PORTFOLIO_HANDLE_USERID);
                String userName = "";
                if(MultilevelDataAuthorityUtil.isEnabled()){
                    userName = CacheUtil.getSpecialSysParam(BussinessConstant.PORTFOLIO_HANDLE_USERID, MultilevelDataAuthorityUtil.getOrganizationCode(entity.getCreateUser()));
                }else{
                    userName = CacheUtil.getSysParam(BussinessConstant.PORTFOLIO_HANDLE_USERID);
                }
                List<String> userIdList = new ArrayList<String>();
                userIdList.add(userName);
                /*add by liupengfei for 多级法人改造 默认选中 上级管理员用户和本级管理员 start*/
                if(MultilevelDataAuthorityUtil.isEnabled()) {
                    Map<String, Object> param = new HashMap<>();
                    String createInst = MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
                    List<String> resourcesTreeInstCodeList = CommonUtil.getParentInstitution(createInst);
                    // 修复npe bug modify by wj ----- 20200715
                    if (resourcesTreeInstCodeList == null) {
                        resourcesTreeInstCodeList = new ArrayList<>();
                    }
                    param.put("resourcesTreeInstCodeList", resourcesTreeInstCodeList);
                    List<String> userNameList = restTemplate.postForObject(
                            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getAdminUserList",
                            param, List.class);
                    if (!StringUtil.isEmptyList(userNameList)) {
                        userIdList.addAll(userNameList);
                    }
                    userIdList.add(entity.getCreateUser());
                }
                /*add by liupengfei for 多级法人改造 默认选中 上级管理员用户和本级管理员 end*/
                ptlPortfolioInfoDto.setUserId(userIdList);
                
              //组装资金账户信息
                List<FundAccountInfoDto> fundAccountInfoList = new ArrayList<FundAccountInfoDto>();
                List<BussFinProductFundAccountInfoDto> prod_accountinfo = entity.getBussFinProductFundAccountInfoDto();
                if(prod_accountinfo != null  && prod_accountinfo.size() > 0){
                    for (BussFinProductFundAccountInfoDto temp_account : prod_accountinfo) {
                        FundAccountInfoDto fundAccountInfo = new FundAccountInfoDto();
                        fundAccountInfo.setPortfolioId(ptlPortfolioInfoDto.getPortfolioId());
                        fundAccountInfo.setAcctType(temp_account.getAcctType());
                        fundAccountInfo.setAcctNo(temp_account.getAcctNo().concat("_"+temp_account.getCcy()));
                        fundAccountInfo.setAcctName(temp_account.getAcctName());
                        fundAccountInfo.setBankDep(temp_account.getBankDep());
                        fundAccountInfo.setLargePayno(temp_account.getLargePayno());
                        fundAccountInfo.setCcy(temp_account.getCcy());
                        fundAccountInfo.setCreateDept(temp_account.getCreateDept());
                        fundAccountInfo.setCreateTime(temp_account.getCreateTime());
                        fundAccountInfo.setCreateUser(temp_account.getCreateUser());
                        fundAccountInfo.setUpdateTime(temp_account.getUpdateTime());
                        fundAccountInfo.setUpdateUser(temp_account.getUpdateUser());
                        fundAccountInfoList.add(fundAccountInfo);
                    }
                }
                ptlPortfolioInfoDto.setFundAccountInfoDto(fundAccountInfoList);
                //证券账户信息
                List<SecuritiesAccountInfoDto> securitiesAccountInfoList = new ArrayList<SecuritiesAccountInfoDto>();
                ptlPortfolioInfoDto.setSecuritiesAccountInfoDto(securitiesAccountInfoList);
 
                WorkFlowUtil.startProcess(params, null, null, userName, OperationType.NEW, false,
                    ptlPortfolioInfoDto);
            }
            catch (Exception e) {
                e.printStackTrace();// SpringContextUtil.getTextValue("COMMON_I_0005")
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProductBuild002Dto entity, OperationType type) {
        if (type == OperationType.DELETE) {
          	if(StringUtil.isNotEmpty(entity.getFinprodMarketId())){
     	       restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                  + "/FinProductCommon/deleteBokBookSet?finprodId="+ entity.getFinprodMarketId(),
                  Integer.class);
      	}
        	//add by tuqirong 2019-03-26 start 删除附件
        	List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(entity.getFinprodId());
            if(files.size()>0) {
                files.forEach(uploadFile -> {
                    fileAttachmentProcess.remove(uploadFile.getFileNo());
                });
                
            }
          //add by tuqirong 2019-03-26 end
        	//update zhangweiqi 解决不能删除的问题
        	if(!StringUtils.isNotEmpty(entity.getFinprodId())) 
        		entity.setFinprodId(commonBusinessService != null ? commonBusinessService.getFinProdId(
                    entity.getFinprodMarketId(), DictCodeListExt1.金融产品类型_投管用_理财产品.getValue(),
                    null) : IDGenerator.generate());
            restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                      + "/FinProductCommon/deleteFinProductCommon?finprodId="
                                      + entity.getFinprodId(),
                Integer.class);

            if (entity.getBussRepFinProductInfoDto() != null) {
                bussRepFinProductInfoService.delete(entity.getFinprodId());
            }
        }
        else{
        	if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.PRODUCTT_IS_MERGER), DictCodeList.是否_否.getValue())) {
        		//当分开时，金融产品代码为F16+行内标识码
            	if(type == OperationType.NEW){
            		// 生成金融产品代码
                    entity.setFinprodId(commonBusinessService != null ? commonBusinessService.getFinProdId(entity.getFinprodMarketId(),
                        DictCodeListExt1.金融产品类型_投管用_理财产品.getValue(), null) : IDGenerator.generate());
                    entity.getBussRepFinProductInfoDto().setIsMerger(DictCodeList.是否_否.getValue());
            	}
            	else{
                	// 生成金融产品代码
            		if(StringUtil.isEmpty(entity.getFinprodId())){
                        entity.setFinprodId(commonBusinessService != null ? commonBusinessService.getFinProdId(entity.getFinprodMarketId(),
                                DictCodeListExt1.金融产品类型_投管用_理财产品.getValue(), null) : IDGenerator.generate());
            		}
                    entity.getBussRepFinProductInfoDto().setIsMerger(DictCodeList.是否_否.getValue());
            	}
        	}
        	else{
            	// 生成金融产品代码
        		entity.setFinprodId(commonBusinessService != null ? commonBusinessService.getFinProdId(
                      entity.getFinprodMarketId(), DictCodeListExt1.金融产品类型_投管用_理财产品.getValue(),
                      null) : IDGenerator.generate());

        		if(StringUtil.isEmpty(entity.getRepFinprodInsId())) {
                    entity.setRepFinprodInsId(entity.getFinprodMarketId());
                }
        	}
            // 生成金融产品代码
//            entity.setFinprodId(commonBusinessService != null ? commonBusinessService.getFinProdId(
//                entity.getFinprodMarketId(), DictCodeListExt1.金融产品类型_投管用_理财产品.getValue(),
//                null) : IDGenerator.generate());
            // 分支序号
            entity.setBranch(Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            // 画面Id
            entity.setPageId("FinProductBuild002");
            // 组合代码
            boolean ptlFlag = false;
            if (!StringUtil.isNotEmpty(entity.getPtlId())) {
                ptlFlag = true;
                entity.setPtlId(entity.getFinprodMarketId());
            }
            // 循环生成付费计划信息
            if (entity.getBussFinProductFeeInfoDto() != null) {
                if (!StringUtil.isEmptyList(
                    entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList())) {
                    // 一体化对应etliu start
//                    List<? extends DataDictItem> feeTypeItemList = SysDataUtil.getDataDictItem(
//                        DictCode.费用类型.getValue());
                    List<? extends DataDictItem> feeTypeItemList = SysDataUtil.getDataDictItem(
                        DictCode.费用品种类型.getValue());
                    List<? extends DataDictItem> basicItemList = SysDataUtil.getDataDictItem(
                        DictCode.计费基数_费用品种.getValue());
                    List<? extends DataDictItem> basisItemList = SysDataUtil.getDataDictItem(
                        DictCode.计息基础.getValue());
                    for (BussFinProductFeeInfoDto bussFinProductFeeInfoDto : entity.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList()) {
                        // 处理费用类型、计费基础、计息基础
                        feeTypeItemList.forEach(item -> {
                            if (StringUtil.equals(item.getItemValue(),
                                bussFinProductFeeInfoDto.getFeeType())) {
                                bussFinProductFeeInfoDto.setFeeType(item.getItemCode());
                            }
                        });
                        basicItemList.forEach(item -> {
                            if (StringUtil.equals(item.getItemValue(),
                                bussFinProductFeeInfoDto.getBasicBill())) {
                                bussFinProductFeeInfoDto.setBasicBill(item.getItemCode());
                            }
                        });
                        basisItemList.forEach(item -> {
                            if (StringUtil.equals(item.getItemValue(),
                                bussFinProductFeeInfoDto.getBasis())) {
                                bussFinProductFeeInfoDto.setBasis(item.getItemCode());
                            }
                        });

                        // 不规则费用不生成付费计划
                     // 一体化对应etliu start
//                        if (!StringUtil.equals(bussFinProductFeeInfoDto.getPayFeeRule(),
//                            DictCodeList.付费规则_不规则.getValue())) {
                        if (!StringUtil.equals(DictCodeList.收费类型_不规则费用.getValue(), bussFinProductFeeInfoDto.getChargeType()) && !StringUtil.equals(bussFinProductFeeInfoDto.getPayFeeRule(), DictCodeList.频率_不规则.getValue())) {
                         // 一体化对应etliu end
                            bussFinProductFeeInfoDto.setDealCalId(entity.getCalendarId());
                            bussFinProductFeeInfoDto.setVdate(entity.getVdate());
                            bussFinProductFeeInfoDto.setMdate(entity.getMdate());
                            bussFinProductFeeInfoDto.setProfitType(entity.getProfitType());
                            bussFinProductFeeInfoDto.setFinCashPlanList(
                                bussFinProductFeeInfoService.generatePayFeePlan(
                                    bussFinProductFeeInfoDto));
                        }
                    }
                }
            }
            
            // 生成开放计划
            if (entity.getBussFinProductOpenPlanDto() != null){
                BussFinProductOpenPlanDto bussFinProductOpenPlanDto = entity.getBussFinProductOpenPlanDto();
                bussFinProductOpenPlanDto.setVdate(entity.getVdate());
                bussFinProductOpenPlanDto.setMdate(entity.getMdate());
                bussFinProductOpenPlanDto.setDealMode(entity.getDealMode());
                bussFinProductOpenPlanDto.setTradCalender(entity.getCalendarId());
                bussFinProductOpenPlanDto.setRaiseStrDate(entity.getFirstSaleVdate());
                bussFinProductOpenPlanDto.setRaiseEndDate(entity.getFirstSaleMdate());
                bussFinProductOpenPlanDto.setPurConfDays(entity.getPurSpeed());
                bussFinProductOpenPlanDto.setIsCycle(entity.getIsCycle());
                bussFinProductOpenPlanDto.setCloseTerm(entity.getCloseDays());
                entity.getBussFinProductOpenPlanDto().setFinOpenPlanList(bussFinProductOpenPlanService.genOpenPlanList(bussFinProductOpenPlanDto));
            }
            
            // 付息计划
            if (entity.getBussFinProductPayPlanDto() != null){
                BussFinProductPayPlanDto bussFinProductPayPlanDto = entity.getBussFinProductPayPlanDto();
                
                // 生成付息计划 modify by wj 20200520 申购同频时 付息计划 由开放计划 生成。
                if(StringUtils.equals(DictCodeListExt1.付息周期_申购同频.getValue(),bussFinProductPayPlanDto.getPayCycle())){
                	bussFinProductPayPlanDto.setVdate(entity.getVdate());
	                bussFinProductPayPlanDto.setMdate(entity.getMdate());
	                bussFinProductPayPlanDto.setCalendarId(entity.getCalendarId());
	                
	                List<FinOpenPlan> openPlanList = entity.getBussFinProductOpenPlanDto().getFinOpenPlanList();
	                List<FinCashPlan> list = new ArrayList<FinCashPlan>();
                	
	                FinCashPlan fcp = new FinCashPlan();
	                fcp.setVdate(entity.getVdate());
	                for (FinOpenPlan finOpenPlan : openPlanList) {
	                	Date openVdate = finOpenPlan.getOpenVdate();
	                	
	                	fcp.setMdate(openVdate);
	                	fcp.setPayDate(openVdate);
	                	fcp.setMdateUnadjust(openVdate);
	                	list.add(fcp);
	                	
	                	fcp = new FinCashPlan();
	                	fcp.setVdate(openVdate);
					}
	                entity.getBussFinProductPayPlanDto().setCashPlanList(list);

                }else{
                
	                bussFinProductPayPlanDto.setVdate(entity.getVdate());
	                bussFinProductPayPlanDto.setMdate(entity.getMdate());
	                bussFinProductPayPlanDto.setCalendarId(entity.getCalendarId());
	                entity.getBussFinProductPayPlanDto().setCashPlanList(bussFinProductPayPlanService.getProductInterestPlanData(bussFinProductPayPlanDto));
                
                }
            }

            // 处理信息披露   前台信息披露查看或修改模式带出的是文件名称，此处转成对应Id
            if (entity.getBussFinProductRevalDocumentDto() != null) {
                List<BussFinProductRevalDocumentDto> finProductRevalDocumentDtoList = entity.getBussFinProductRevalDocumentDto().getFinProductRevalDocumentDtoList();
                if (!StringUtil.isEmptyList(finProductRevalDocumentDtoList)) {
                    for (BussFinProductRevalDocumentDto bussFinProductRevalDocumentDto : finProductRevalDocumentDtoList) {
                        List<SelectOptionDto> fileNameList = bussFinProductRevalDocumentService.selectByFileType(
                            bussFinProductRevalDocumentDto.getFileType(), null);
                        if (!StringUtil.isEmptyList(fileNameList)){
                            fileNameList.forEach(item -> {
                                if (StringUtil.equals(item.getText(),
                                    bussFinProductRevalDocumentDto.getFileName())) {
                                    bussFinProductRevalDocumentDto.setFileName(item.getId());
                                }
                            });
                        }
                        
                    }
                }
            }
            
            if (type == OperationType.NEW) {
            	//add by tuqirong 2019-03-26 satrt 更新附件信息
            	List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(entity.getFjId());
                if(files.size()>0) {
                    List<String> fileNo = new ArrayList<>();
                    files.forEach(uploadFile -> {
                        fileNo.add(uploadFile.getFileNo());
                    });
                    fileAttachmentProcess.updateBussinessNo(fileNo, entity.getFinprodId());
                }
                //add by tuqirong 2019-03-26 end
                
                entity.setIsFhxgcp(entity.getBussRepFinProductInfoDto().getIsFhxgcp());
                entity.setIsYllccp(entity.getBussRepFinProductInfoDto().getIsYllccp());
                restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                           + "/FinProductCommon/insertFinProductCommon",
                    entity, Integer.class);

                // 中债报送信息登表
                if (entity.getBussRepFinProductInfoDto() != null) {
                    //报送信息存值 募集起始日期 yjm start 20190801
//                    entity.getBussRepFinProductInfoDto().setRepRaisePstrdate(entity.getFirstSaleVdate());
//                    entity.getBussRepFinProductInfoDto().setRepRaisePenddate(entity.getFirstSaleVdate());
                    //发行机构代码
                    // update by shengqiqiang 产品新建时登记银行代码从 机构登记银行代码管理 功能接入  2020.12.8 start
//                    if(CommonUtil.isBAmodel()){
//                        entity.getBussRepFinProductInfoDto().setRepIssuePartyId(CommonUtil.getInstitutionCodeByLoginUser_ms());
//                    } else {
                    entity.getBussRepFinProductInfoDto().setRepIssuePartyId(CommonUtil.selectBankCode());
//                    }
                    //entity.getBussRepFinProductInfoDto().setRepIssuePartyId(CacheUtil.getSysParam("SVR_ISSUE_PARTY_ID"));
                    // update by shengqiqiang 产品新建时登记银行代码从 机构登记银行代码管理 功能接入  2020.12.8 end
                    
                    //报送信息存值 募集起始日期 yjm end 20190801
                    entity.getBussRepFinProductInfoDto().setRepFinprodId(entity.getFinprodId());
                    entity.getBussRepFinProductInfoDto().setRepFinprodName(entity.getFinprodName());
                    if(StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.PRODUCTT_IS_MERGER), DictCodeList.是否_否.getValue())){
                        entity.getBussRepFinProductInfoDto().setRepFinprodInsId(entity.getRepFinprodInsId());
                    }
                    else{
                        entity.getBussRepFinProductInfoDto().setRepFinprodInsId(entity.getFinprodMarketId());
                    }
                    entity.getBussRepFinProductInfoDto().setRepRaiseVdate(entity.getFirstSaleVdate());
                    entity.getBussRepFinProductInfoDto().setRepRaiseEnddate(
                        entity.getFirstSaleMdate());
                    entity.getBussRepFinProductInfoDto().setRepPrdVdate(entity.getVdate());
                    entity.getBussRepFinProductInfoDto().setRepPrdMdate(entity.getMdate());
                    //modify by wm 申报登记表此字段存值错误 2019-12-05 start
//                  entity.getBussRepFinProductInfoDto().setRepProfitType(entity.getProfitType());
                    entity.getBussRepFinProductInfoDto().setRepProfitType(entity.getProfitFlag());
                  //modify by wm 申报登记表此字段存值错误 2019-12-05 end
                    entity.getBussRepFinProductInfoDto().setRepExpMaxYield(
                        entity.getBussRepFinProductInfoDto().getRepHighYield());
                    entity.getBussRepFinProductInfoDto().setRepExpMinYield(
                        entity.getBussRepFinProductInfoDto().getRepLowYield());
                    entity.getBussRepFinProductInfoDto().setRepRaiseAmtPlan(entity.getBussFinProductSaleParamDto().getRaiseAmtPlan());
                    //募集币种
                    entity.getBussRepFinProductInfoDto().setRepRaiseCcy(entity.getCcy());
                    //起点销售金额
                    entity.getBussRepFinProductInfoDto().setRepSaleAmtStr(entity.getBussFinProductSaleParamDto().getToffStart());
                    //募集金额存元（原万元）yzg 20191114 start 页面改为元 20191126
                    //entity.getBussRepFinProductInfoDto().setRepRaiseAmtPlan(ArithmeticUtil.multiply(entity.getBussFinProductSaleParamDto().getRaiseAmtPlan(), new BigDecimal(10000)));
                    //募集金额存元（原万元）yzg 20191114 end 页面改为元 20191126
                    //开放计划信息
                    if(null != entity.getBussFinProductOpenPlanDto()){
                        //节假日是否开放
                        entity.getBussRepFinProductInfoDto().setRepHolidayOnFlag(entity.getBussFinProductOpenPlanDto().getHdayOpenFlag());
                        //开放计划
                        if(!StringUtil.isEmptyList(entity.getBussFinProductOpenPlanDto().getFinOpenPlanList())){
                          //首次开放周期起始日
                            entity.getBussRepFinProductInfoDto().setRepFirstOpenDay(entity.getBussFinProductOpenPlanDto().getFinOpenPlanList().get(0).getOpenDateStr());
                        } else {
                            //起息日
                            entity.getBussRepFinProductInfoDto().setRepFirstOpenDay(entity.getVdate());
                        }
                    }
                    /**20190909 add by chendijun start **/
                    //产品销售地区
                    entity.getBussRepFinProductInfoDto().setRepSaleArea(entity.getBussFinProductSaleParamDto().getSaleArea());
                    /**20190909 add by chendijun end **/
                    //增加募集方式
                    entity.getBussRepFinProductInfoDto().setRepIssueType(entity.getIssueType());
                    /**20191203 add by taoran start **/
                    //增加产品投资性质
                    entity.getBussRepFinProductInfoDto().setRepInvestNature(entity.getInvestNature());
                    //增加是否现金管理类
                    entity.getBussRepFinProductInfoDto().setRepIsCashManage(entity.getIsCashManage());
                    /**20191203 add by taoran end **/
                    bussRepFinProductInfoService.insert(entity.getBussRepFinProductInfoDto());

                    // 二级法人权限处理 WY
                    if(MultilevelDataAuthorityUtil.isEnabled() &&   StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.PRODUCTT_IS_MERGER), DictCodeList.是否_是.getValue())  ) {
                        //创建机构代码
                        String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(entity.getCreateUser());
                        entity.setCreateInst(createInst);
                        // 有查询权限的机构代码
                        List<String> searchOrgCodeList = null;
                        // 有编辑权限的机构代码
                        List<String> editOrgCodeList = null;
                        // 新建或修改时重新设置二级法人权限，删除时则清空
                        if (!OperationType.DELETE.equals(type)) {
                            //获取所有父级机构。  拥有查看权限
                            searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                            // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                            editOrgCodeList = Arrays.asList(entity.getCreateInst());
                        }
                        // 记录二级法人权限
                        insertDataAuthTypeInst(entity.getBussRepFinProductInfoDto().getRepFinprodId(), searchOrgCodeList, editOrgCodeList, DictCodeListExt3.共通业务类型_产品申报登记要素管理.getValue());
                    }

                }

              this.insertOtherData(entity, ptlFlag);  //edit by dongenkuo 20190311 创建组合放在service层，此处注释掉
            }
            else if (type == OperationType.MODIFY) {
            	entity.setIsFhxgcp(entity.getBussRepFinProductInfoDto().getIsFhxgcp());
                entity.setIsYllccp(entity.getBussRepFinProductInfoDto().getIsYllccp());
                restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                           + "/FinProductCommon/updateFinProductCommon",
                    entity, Integer.class);

                // 中债报送信息登表
                if (entity.getBussRepFinProductInfoDto() != null) {
                    //报送信息存值 募集起始日期 yjm start 20190801
//                    entity.getBussRepFinProductInfoDto().setRepRaisePstrdate(entity.getFirstSaleVdate());
//                    entity.getBussRepFinProductInfoDto().setRepRaisePenddate(entity.getFirstSaleVdate());
                    //发行机构代码
                    entity.getBussRepFinProductInfoDto().setRepIssuePartyId(CacheUtil.getSysParam("SVR_ISSUE_PARTY_ID"));
                    //报送信息存值 募集起始日期 yjm end 20190801
                    entity.getBussRepFinProductInfoDto().setRepFinprodId(entity.getFinprodId());
                    entity.getBussRepFinProductInfoDto().setRepFinprodName(entity.getFinprodName());
                    if(StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.PRODUCTT_IS_MERGER), DictCodeList.是否_否.getValue())){
                        entity.getBussRepFinProductInfoDto().setRepFinprodInsId(entity.getRepFinprodInsId());
                    }
                    else{
                        entity.getBussRepFinProductInfoDto().setRepFinprodInsId(entity.getFinprodMarketId());
                    }
                    entity.getBussRepFinProductInfoDto().setRepRaiseVdate(entity.getFirstSaleVdate());
                    entity.getBussRepFinProductInfoDto().setRepRaiseEnddate(
                        entity.getFirstSaleMdate());
                    entity.getBussRepFinProductInfoDto().setRepPrdVdate(entity.getVdate());
                    entity.getBussRepFinProductInfoDto().setRepPrdMdate(entity.getMdate());
                    entity.getBussRepFinProductInfoDto().setRepFinprodName(entity.getFinprodName());
                    //modify by wm 申报登记表此字段存值错误 2019-12-05 start
//                  entity.getBussRepFinProductInfoDto().setRepProfitType(entity.getProfitType());
                  entity.getBussRepFinProductInfoDto().setRepProfitType(entity.getProfitFlag());
                  //modify by wm 申报登记表此字段存值错误 2019-12-05 end
                    entity.getBussRepFinProductInfoDto().setRepExpMaxYield(
                        entity.getBussRepFinProductInfoDto().getRepHighYield());
                    entity.getBussRepFinProductInfoDto().setRepExpMinYield(
                        entity.getBussRepFinProductInfoDto().getRepLowYield());
                    entity.getBussRepFinProductInfoDto().setRepRaiseAmtPlan(entity.getBussFinProductSaleParamDto().getRaiseAmtPlan());
                    //募集币种
                    entity.getBussRepFinProductInfoDto().setRepRaiseCcy(entity.getCcy());
                    //起点销售金额
                    entity.getBussRepFinProductInfoDto().setRepSaleAmtStr(entity.getBussFinProductSaleParamDto().getToffStart());
                    //销售地区
                    entity.getBussRepFinProductInfoDto().setRepSaleArea(entity.getBussFinProductSaleParamDto().getSaleArea());
                    //募集金额存元（原万元）yzg 20191114 start 页面改为元 20191126
                    //entity.getBussRepFinProductInfoDto().setRepRaiseAmtPlan(ArithmeticUtil.multiply(entity.getBussFinProductSaleParamDto().getRaiseAmtPlan(), new BigDecimal(10000)));
                    //募集金额存元（原万元）yzg 20191114 end 页面改为元 20191126
                    //开放计划信息
                    if(null != entity.getBussFinProductOpenPlanDto()){
                        //节假日是否开放
                        entity.getBussRepFinProductInfoDto().setRepHolidayOnFlag(entity.getBussFinProductOpenPlanDto().getHdayOpenFlag());
                        //开放计划
                        if(!StringUtil.isEmptyList(entity.getBussFinProductOpenPlanDto().getFinOpenPlanList())){
                          //首次开放周期起始日
                            entity.getBussRepFinProductInfoDto().setRepFirstOpenDay(entity.getBussFinProductOpenPlanDto().getFinOpenPlanList().get(0).getOpenDateStr());
                        } else {
                            //起息日
                            entity.getBussRepFinProductInfoDto().setRepFirstOpenDay(entity.getVdate());
                        }
                    }
                    //增加募集方式
                    entity.getBussRepFinProductInfoDto().setRepIssueType(entity.getIssueType());
                    //增加产品投资性质
                    entity.getBussRepFinProductInfoDto().setRepInvestNature(entity.getInvestNature());
                    bussRepFinProductInfoService.update(entity.getBussRepFinProductInfoDto());
                }

              this.insertOtherData(entity, ptlFlag);  //edit by dongenkuo 20190311 创建组合放在service层，此处注释掉
            }
            
            // 清除费用添加时的缓存
            CacheUtil.clearAppCache(entity.getFeeNewAddId());
        } 
         // 二级法人权限处理
         if(MultilevelDataAuthorityUtil.isEnabled()) {
             //创建机构代码
             String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(entity.getCreateUser());
             entity.setCreateInst(createInst);
    
             // 有查询权限的机构代码
             List<String> searchOrgCodeList = null;
             // 有编辑权限的机构代码
             List<String> editOrgCodeList = null;
             // 新建或修改时重新设置二级法人权限，删除时则清空
             if (!OperationType.DELETE.equals(type)) {
                 //获取所有父级机构。  拥有查看权限
                 searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                 // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                 editOrgCodeList = Arrays.asList(entity.getCreateInst());
             }
             // 记录二级法人权限
             insertDataAuthTypeInst(entity.getFinprodId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_产品要素管理.getValue());
             insertDataAuthTypeInst(entity.getFinprodMarketId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_账套管理.getValue());
             insertDataAuthTypeInst(BussinessConstant.BOK_BANK_PREFIX+entity.getFinprodMarketId(), searchOrgCodeList, editOrgCodeList,DictCodeList.共通业务类型_账套管理.getValue());
         }
    }

    /**
     * 根据金融产品代码查询产品排期信息
     * 
     * @param finprodId 金融产品代码
     * @return 产品排期信息
     * @see
     * @since 1.0
     */
    public FinProductBuild002Dto queryProdScheduleByFinprodId(String finprodId) {
        FinProductBuild002Dto finProductBuild002Dto = new FinProductBuild002Dto();
        // 根据金融产品代码查询产品排期信息
        PrdIssueScheduleDto prdIssueScheduleDto = finProductScheduling002Service.queryIssueInfoByFinprodId(
            finprodId);
        if (prdIssueScheduleDto != null) {
            // 设置产品基本信息
            finProductBuild002Dto.setFinprodMarketId(prdIssueScheduleDto.getFinprodMarketId());
            finProductBuild002Dto.setFinprodName(prdIssueScheduleDto.getFinprodName());
            finProductBuild002Dto.setFinprodAbbr(prdIssueScheduleDto.getFinprodAbbr());
            finProductBuild002Dto.setEntrustType(prdIssueScheduleDto.getIssueMode());
            finProductBuild002Dto.setEntruster(prdIssueScheduleDto.getBranchBank());
            finProductBuild002Dto.setFirstSaleVdate(prdIssueScheduleDto.getRaiseStrdate());
            finProductBuild002Dto.setFirstSaleMdate(prdIssueScheduleDto.getRaiseEnddate());
            finProductBuild002Dto.setVdate(prdIssueScheduleDto.getVdate());
            finProductBuild002Dto.setMdate(prdIssueScheduleDto.getMdate());
            finProductBuild002Dto.setCcy(prdIssueScheduleDto.getCcy());
        }

        return finProductBuild002Dto;
    }

    /**
     * 根据金融产品代码查询产品信息
     * 
     * @param finprodId 金融产品代码
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public FinProductBuild002Dto queryProdInfoByFinprodId(String finprodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("branch", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryProdInfoByFinprodId",
            map, FinProductBuild002Dto.class);
    }
    /**
     * 查询机构
     *
     * @param map map
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryTrusteeId(Map<String, Object> map) {     	
    	return restTemplate.postForPage(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryTrusteeId",map,
                SelectOptionDto.class);
    }
    
    //add by tuqirong 2019-03-26 start 添加附件信息
    private void updateFiles(FinProductBuild002Dto entity) {
        List<FileCommonDto> files = entity.getFileCommonList();
        if(files.size()>0) {
            List<String> fileNo = new ArrayList<>();
            files.forEach(fileCommonDto -> {
                fileNo.add(fileCommonDto.getFileNo());
            });
            fileAttachmentProcess.updateBussinessNo(fileNo, entity.getFjId());
        }
    }
    
    @Override
    public void doInsert(FinProductBuild002Dto entity, OperationType type) {
    	updateFiles(entity);
    }
    
    @Override
    public void doUpdate(FinProductBuild002Dto entity) {
    	updateFiles(entity);
    }
    
    @Override
    public void doDelete(String workflowId) {
    	FinProductBuild002Dto finPlanProfitAssetDto = finProductBuild002Service.getBussinessEntityByWorkflowId(workflowId);
        List<UploadFile> files = fileAttachmentProcess.fetchByBusinessCode(finPlanProfitAssetDto.getFjId());
        if(files.size()>0) {
            files.forEach(uploadFile -> {
                fileAttachmentProcess.remove(uploadFile.getFileNo());
            });
        }
    }
    //add by tuqirong 2019-03-26 end
    /*
    * 根据金融产品代码查询产品信息
    * 
    * @param finprodId 金融产品代码
    * @return 产品信息
    * @see
    * @since 1.0
    */
   public FinProductBuild002Dto queryProdInfoByProdId(String finprodId) {
       Map<String, Object> map = new HashMap<String, Object>();
       map.put("finprodId", finprodId);
       map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
       map.put("branch", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
       return restTemplate.postForObject(
           ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/queryProdInfoByProdId",
           map, FinProductBuild002Dto.class);
   }
}
