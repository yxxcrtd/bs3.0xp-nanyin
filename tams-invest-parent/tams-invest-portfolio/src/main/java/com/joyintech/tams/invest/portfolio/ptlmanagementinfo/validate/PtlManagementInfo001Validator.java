/*
 * 文件名：PtlManagementInfo001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlmanagementinfo.validate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfo004Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.portfolio.dto.FundAccountInfoDto;
import com.joyintech.tams.component.business.portfolio.dto.SecuritiesAccountInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfoDto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo001Service;

/**
 * 组合信息验证
 * @author fanshuanghu
 * @version 1.0 2018年9月6日
 * @see PtlManagementInfo001Validator
 * @since 1.0
 */
@Transactional
@Component
public class PtlManagementInfo001Validator implements BeanValidator{
    /**
     * 组合service001
     */
    @Autowired
    private PtlManagementInfo001Service ptlManagementInfo001Service;

	@Autowired
	private PtlPortfolioMapper ptlPortfolioMapper;

    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlManagementInfoDto dto = (PtlManagementInfoDto)value;
        if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
        	if(StringUtil.isNotEmpty(dto.getPortfolioId()) && dto.getPortfolioId().getBytes().length > 25){
        		errorList.add(new ValidMessage(getMessage("FW_E_0007", getMessage("PtlManagementInfo001.portfolioId"),"25")));
        	}
            //组合开始日必须小于组合结束日
            if(dto.getVdate() != null && dto.getMdate() != null && dto.getVdate().compareTo(dto.getMdate())>0){
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0003",
                    getMessage("PtlManagementInfo001.vDate")+DateUtil.format(dto.getVdate()),
                    getMessage("PtlManagementInfo001.mDate")+DateUtil.format(dto.getMdate()))));
            }
            //资金账户不能为空
//            if(dto.getFundAccountInfoDto() == null || dto.getFundAccountInfoDto().isEmpty()){
//                errorList.add(new ValidMessage("fundAccountInfoDto",getMessage("FW_E_0006",getMessage("PtlManagementInfo001.funInfo"))));
//            }
            //资金账户进行验证
            funDtoValid(dto,errorList);
            //证券账户验证
            secDtoValid(dto,errorList);
            //校验组合代码重复性
            if(StringUtil.isNotEmpty(dto.getPortfolioId())){
                if(ptlPortfolioMapper.selectByPrimaryKey(dto.getPortfolioId()) != null){
                    errorList.add(new ValidMessage("portfolioId",getMessage("PTL_COM_E_0009")));
                }
            }
            //验证是否分配用户
            if (null == dto.getUserId()){
                errorList.add(new ValidMessage(getMessage("PTL_COM_E_0011")));
            }else {
                // 银行模式  会默认选中组合审批人 也提示报错。
                if( dto.getUserId().size()==1){
                    // 如果仅有一条 并且为 组合审批人的话 依然提示需要分配用户
                    String institutionCode = null;
                    if(MultilevelDataAuthorityUtil.isEnabled()){
                        institutionCode = MultilevelDataAuthorityUtil.getOrganizationCode(dto.getCreateUser());
                    }
                    if(StringUtil.equals(dto.getUserId().get(0) , CacheUtil.getSpecialSysParam(BussinessConstant.PORTFOLIO_HANDLE_USERID,institutionCode))){
                        errorList.add(new ValidMessage(getMessage("PTL_COM_E_0040")));
                    }
                }
            }

        }else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
            //组合开始日必须大于组合结束日（修改界面）
            if (StringUtil.isNotEmpty(dto.getMdate())){
                if(dto.getVdate() != null &&dto.getVdate().compareTo(dto.getMdate())>0){
                    errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0003",
                            getMessage("PtlManagementInfo001.vDate")+DateUtil.format(dto.getVdate()),
                            getMessage("PtlManagementInfo001.mDate")+DateUtil.format(dto.getMdate()))));
                }
            }
            //金融产品信息不能重复（修改界面）
//            if((StringUtil.equals(dto.getOperateType(), "save") || StringUtil.equals(dto.getOperateType(), "submit")) &&
//                (dto.getFundAccountInfoDto() == null || dto.getFundAccountInfoDto().isEmpty())){
//                errorList.add(new ValidMessage("fundAccountInfoDto",getMessage("FW_E_0006",getMessage("PtlManagementInfo001.funInfo"))));
//            }
            funDtoValid(dto,errorList);
            secDtoValid(dto,errorList);

            // TISHKBS-999  TISHKBS-986 编辑时  也校验
            //验证是否分配用户
            if (StringUtil.isEmptyList(dto.getUserId())){
                errorList.add(new ValidMessage(getMessage("PTL_COM_E_0011")));
            }else {
                // 银行模式  会默认选中组合审批人 也提示报错。
                if( dto.getUserId().size()==1){
                    // 如果仅有一条 并且为 组合审批人的话 依然提示需要分配用户
                    String institutionCode = null;
                    if(MultilevelDataAuthorityUtil.isEnabled()){
                        institutionCode = MultilevelDataAuthorityUtil.getOrganizationCode(dto.getCreateUser());
                    }
                    if(StringUtil.equals(dto.getUserId().get(0) , CacheUtil.getSpecialSysParam(BussinessConstant.PORTFOLIO_HANDLE_USERID,institutionCode))){
                        errorList.add(new ValidMessage(getMessage("PTL_COM_E_0040")));
                    }
                }
            }

        }
       else if(StringUtil.equals(dto.getOperateType(), "delete")){
           if(dto.getSelectedItem() != null ){
                boolean flg = ptlManagementInfo001Service.deleteCheck(dto);
                    if(!flg){
                        errorList.add(new ValidMessage("此组合存在交易不可删除"));
                    }
        		else{
        			funDtoValid(dto,errorList);
                    secDtoValid(dto,errorList);
                    addValid(dto,errorList);
        		}
           }
       }
      return errorList;
    }

    public void funDtoValid(PtlManagementInfoDto dto,ArrayList<ValidMessage> errorList){
        if(dto.getFundAccountInfoDto() == null || dto.getFundAccountInfoDto().isEmpty()){
            if(dto.getOperateType() != null && (StringUtil.equals(dto.getOperateType(),"save") ||
                    StringUtil.equals(dto.getOperateType(),"submit"))){
                errorList.add(new ValidMessage("fundAccountInfoDto",getMessage("FW_E_0006",getMessage("PtlManagementInfo001.funInfo"))));
            }
        }else{
            boolean hasSecManageAcct = false;
            Iterator<FundAccountInfoDto> it = dto.getFundAccountInfoDto().iterator();
            Set<String> set = new HashSet<>();
            Set<String> acctTypeSet = new HashSet<>();
            while(it.hasNext()){
                FundAccountInfoDto nextDto = it.next();
                acctTypeSet.add(nextDto.getAcctType()+nextDto.getCcy());
                if(StringUtil.isNotEmpty(nextDto.getAcctType()) && StringUtil.equals(nextDto.getAcctType(), DictCodeListExt1.资金账户类型_产品托管户.getValue())){
                    hasSecManageAcct = true;
                }
                String nextId = nextDto.getAcctNo();
                set.add(nextId);
            }
            //判断资金账户类型是否重复 因为产品端新建可以添加多个券商资金账户，所以校验放开 yjm 20190926
            /*if(acctTypeSet.size() < dto.getFundAccountInfoDto().size()){
                errorList.add(new ValidMessage(getMessage("BUSS_COM_E_0006",getMessage("FundAccountInfo.acctType"))));
            }*/
            //账户信息不能重复
            //update by leipeng 现状：产品要素界面挂资金账户，不同的账户类型挂了同一个资金账户，组合审批时，提示资金账户重复                                    期望：如果不同的账户类型挂了同一个资金账户，在建产品要素的时候提示资金账户重复，不要在组合审批的时候提示
//            if(set.size() < dto.getFundAccountInfoDto().size()){
//                errorList.add(new ValidMessage("fundAccountInfoDto",getMessage("BUSS_COM_E_0006",getMessage("PtlManagementInfo001.funInfo"))));
//            }
            //必须存在托管账户
            if(!hasSecManageAcct){
                errorList.add(new ValidMessage(getMessage("PtlManagementInfo001.selectAcct")));
            }
        }
    }

    public void secDtoValid(PtlManagementInfoDto dto,ArrayList<ValidMessage> errorList){
        if(dto.getFundAccountInfoDto() == null || dto.getFundAccountInfoDto().isEmpty()){
        }else{
            Iterator<SecuritiesAccountInfoDto> it = dto.getSecuritiesAccountInfoDto().iterator();
            Set<String> set = new HashSet<>();
            while(it.hasNext()){
                String nextId = it.next().getSecAcctId();
                set.add(nextId);
            }
            //证券账户不能重复
            if(set.size() < dto.getSecuritiesAccountInfoDto().size()){
                errorList.add(new ValidMessage("securitiesAccountInfoDto",getMessage("BUSS_COM_E_0006",getMessage("PtlManagementInfo001.secInfo"))));
            }
        }
    }

    /**
     * 验证删除产品组合时是否有子组合，以及是否有新建流程
     * @param dto
     * @param errorList
     */
    public void addValid(PtlManagementInfoDto dto,ArrayList<ValidMessage> errorList){
        List<PtlManagementInfoDto> selectedItem = dto.getSelectedItem();
        for (PtlManagementInfoDto ptlManagementInfoDto : selectedItem) {
            if (StringUtil.equals(ptlManagementInfoDto.getPortfolioType(),DictCodeListExt1.组合类型_投资类组合.getValue())) {
                List<PtlManagementInfo004Dto> childPortfolios = ptlManagementInfo001Service.getChildPortfolios(ptlManagementInfoDto.getPortfolioId());
                if (!childPortfolios.isEmpty()){
                    errorList.add(new ValidMessage(getMessage("PTL_COM_E_0021")));
                }

                //判断流程中是否存在相同的数据
                WflValidInputParameter param = new WflValidInputParameter();
                param.setBusinessDataPk1(ptlManagementInfoDto.getPortfolioId());
                param.setPageId("PtlManagementInfo004");
                if (null != mstWorkflowCommonService &&
                        mstWorkflowCommonService.isInWflProcess(param)) {
                    errorList.add(new ValidMessage(this.getMessage("PTL_COM_E_0022")));
                }
            }else if (StringUtil.equals(ptlManagementInfoDto.getPortfolioType(),DictCodeListExt1.组合类型_子组合.getValue())){
                //判断流程中是否存在相同的数据
                WflValidInputParameter param = new WflValidInputParameter();
                param.setBusinessDataPk1(ptlManagementInfoDto.getPortfolioId());
                param.setPageId("PtlManagementInfo004");

                if (null != mstWorkflowCommonService &&
                        mstWorkflowCommonService.isInWflProcess(param)) {
                    errorList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0041")));
                }
            }

        }
    }
}
