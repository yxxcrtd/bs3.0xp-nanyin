package com.joyintech.tams.invest.product.productduration.netproductbonusscheme.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.dto.FinNetProductBonusSchemeDto;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.service.FinNetProductBonusSchemeService;
import com.joyintech.workflow.engine.service.LeaveMarkProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Component
public class FinNetProductBonusSchemeValidator implements BeanValidator {
    /**
     * 计划类资产（净值型）Service
     */
    @Autowired
    private FinNetProductBonusSchemeService finNetProductBonusSchemeService;

    /**
     * 平台日期组件接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    @Autowired(required=false)
    private LeaveMarkProcessService leaveMarkProcessService;

    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    
    @Override
    public List<ValidMessage> valid(Object value) {
        FinNetProductBonusSchemeDto dto = (FinNetProductBonusSchemeDto) value;
        String dealType = dto.getDealType();
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        //定义产品代码
        String finprodId = "";
        if(StringUtil.isNotEmpty(dto.getProdStyle())){
        	finprodId = dto.getProdStyle();
        }
        else{
        	finprodId = dto.getFinprodId();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("finprodId",finprodId);
        map.put("bonusPlanId",dto.getBonusPlanId());
        map.put("delDate", DateUtil.format(dto.getDelDate()));
        if(dealType.equals("bonusScheRegister")){
            //分红方案登记
            if (WorkFlowUtil.isNotFromToDoList()) {
                if (StringUtil.equals(dto.getOperateType(), "delete")) {
                    // 删除check
                	Map<String,Object> param = new HashMap<String,Object>();
                    param.put("finprodId", finprodId);
                    param.put("bonusPlanId", dto.getBonusPlanId());
                    param.put("bTradeId", dto.getbTradeId());
                    param.put("delDate", DateUtil.format(dto.getDelDate()));
                    param.put("validType", "before_del");
                    //确认要撤销的分红方案是否存在
                    if(finNetProductBonusSchemeService.selectBonuScalcFlag(param)==0){
                        errorList.add(new ValidMessage(getMessage("FIN_PROD_C_0082",
                            getMessage("FinNetProductBonusScheme001.finprodMarketId")+":"+dto.getFinprodMarketId()
                            +","+getMessage("FinNetProductBonusScheme001.bonusPlanId")+":"+dto.getBonusPlanId())));
                    }
                    //确认要撤销的分红方案是否已经做过分红交易
//                    if(StringUtil.isNotEmpty(finNetProductBonusSchemeService.IsDividend(param))){
//                        errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0056",
//                            getMessage("FinNetProductBonusScheme001.finprodMarketId")+":"+e.getFinprodMarketId())));
//                    }
                    /**
                     * 分层产品时交易组合不为母组合，此时根据交易编号查询即可 update by xr
                     */
                    Map<String,Object> param2 = new HashMap<String,Object>();
                    param2.put("bTradeId", dto.getbTradeId());

                    if(finNetProductBonusSchemeService.isExistBonusDeal(param2)>0){
                        errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0056",
                            getMessage("FinNetProductBonusScheme001.finprodMarketId")+":"+dto.getFinprodMarketId()
                            +","+getMessage("FinNetProductBonusScheme001.bonusPlanId")+":"+dto.getBonusPlanId())));
                    }
                    
                }
                else if(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit")){
                    if(StringUtil.equals(dto.getBonuScalcFlag(), DictCodeList.分红方案_按单位分红.getValue())&&!StringUtil.isNotEmpty(dto.getUnitBonusVlu())){

                    }
                    if(StringUtil.isNotEmpty(dto.getDelDate())&&StringUtil.isNotEmpty(dto.getBonusDate())&&dto.getDelDate().compareTo(dto.getBonusDate())>0){
                        errorList.add(new ValidMessage("bonusDate",getMessage("BUSS_COM_E_0004",getMessage("FinNetProductBonusScheme002.bonusDate"),
                                getMessage("FinNetProductBonusScheme002.delDate"))));
                    }
                    if(StringUtil.isNotEmpty(dto.getUnitBonusVlu())&&StringUtil.equals(dto.getBonuScalcFlag(), DictCodeList.分红方案_按单位分红.getValue()) &&
                            dto.getUnitBonusVlu().compareTo(BigDecimal.ZERO)<=0){
                        errorList.add(new ValidMessage("unitBonusVlu",getMessage("FIN_PROD_E_0015",
                                getMessage("FinNetProductBonusScheme002.unitBonusVlu"),Integer.toString(0))));
                    }
                    if(!StringUtil.equals(dto.getBonuScalcFlag(), DictCodeList.分红方案_按单位分红.getValue())){
                    	//按总额分红，分红总金额不能为空，且大于0
                    	if(dto.getTotalAmt()==null || dto.getTotalAmt().compareTo(BigDecimal.ZERO)<=0){
                    		errorList.add(new ValidMessage("totalAmt",getMessage("FIN_PROD_E_0015",
                                    getMessage("FinNetProductBonusScheme002.totalAmt"),Integer.toString(0))));
                    	}
                    }
                    if(finNetProductBonusSchemeService.countDelDate(map)>0){
                        errorList.add(new ValidMessage("delDate",getMessage("FIN_PROD_E_0057")));
                    }
                if (StringUtil.isNotEmpty(dto.getBaseDate())
                    && DateUtil.format(dto.getBaseDate()).compareTo(DateUtil.getSysDate()) > 0) {
                    errorList.add(new ValidMessage("baseDate", getMessage("BUSS_COM_E_0003",
                        getMessage("FinNetProductBonusScheme002.baseDate"), DateUtil.getSysDate())));
                }
                if (StringUtil.isNotEmpty(dto.getDelDate())
                    && DateUtil.format(dto.getDelDate()).compareTo(DateUtil.getSysDate()) < 0) {
                    errorList.add(new ValidMessage("delDate", getMessage("BUSS_COM_E_0004",
                        getMessage("FinNetProductBonusScheme002.delDate"), DateUtil.getSysDate())));
                    }
                    if(dto.getBonuScalcFlag().equals(DictCodeList.分红方案_按单位分红.getValue())){
                    	if(StringUtil.equals(dto.getIsNetMormalize(), "Y") 
                    			&& (dto.getTdyUnitValue() == null || dto.getTdyUnitValue().compareTo(BigDecimal.ZERO)<=0)){
                    		//单位分红，净值归一，判断当日参考净值是否大于1，否则给出提示不予保存
                    		 errorList.add(new ValidMessage("净值小于1，不能净值归1,请重新选择!"));
                    	}
                    	
                    	if(StringUtil.equals(dto.getIsNetMormalize(), "N") 
                        		&& (dto.getUnitBonusVlu() == null || StringUtil.equals(dto.getUnitBonusVlu(), "")
                        				|| dto.getUnitBonusVlu().compareTo(BigDecimal.ZERO)<=0)){
                    		//单位分红，净值不归一，则单位分红金额不能为空，且大于0
                            errorList.add(new ValidMessage("unitBonusVlu",getMessage("FW_E_0006",getMessage("FinNetProductBonusScheme002.unitBonusVlu"))));
                        }
                    }
                  //TODO 未分配利润占时未取到值 先注释 lijun modify BS1.9BSBS-308 2019-05-16 start
//                    if(dto.getTdyUnpaidIntincexp()==null){
//                        errorList.add(new ValidMessage("tdyUnpaidIntincexp",getMessage("FIN_PROD_E_0083",getMessage("FinNetProductBonusScheme002.tdyUnpaidIntincexp"))));
//                    }
                  // 未分配利润占时未取到值 先注释 lijun modify BS1.9BSBS-308 2019-05-16 end  
                  //除权日+开放确认日与权益 登记日的比较
                  FinProductAdd finProductAdd  =  finNetProductBonusSchemeService.selectFinProductADD(finprodId);
                  if(StringUtil.isNotEmpty(dto.getDelDate())&&StringUtil.isNotEmpty(dto.getRegDate())){
                	  Long purSpeed = 0L;
            		  if(finProductAdd!=null&&StringUtil.isNotEmpty(finProductAdd.getPurSpeed())){
            			  purSpeed = finProductAdd.getPurSpeed();
            		  }
            		  else{
            			  purSpeed = 1L;
            		  }
            		  if(DateUtil.addDay(dto.getDelDate(), purSpeed.intValue()).compareTo(dto.getRegDate())<=0){
            			  errorList.add(new ValidMessage("除权日+确认天数必须大于权益登记日!"));
            		  }
            	  }
                  
                }
                ProcessMark processMark = new ProcessMark();
                processMark.setBusinessDataPk1(dto.getBonusPlanId());
                processMark.setBusinessDataPk2(dto.getFinprodId());
                List<String> resultList = leaveMarkProcessService.getBusinessDataInFlow(processMark);
                if (!StringUtil.isEmptyList(resultList)) {
                	errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getBonusPlanId())));
                }
            }
            else{
                if(StringUtil.isNotEmpty(dto.getDelDate())&&StringUtil.isNotEmpty(dto.getBonusDate())&&dto.getDelDate().compareTo(dto.getBonusDate())>0){
                    errorList.add(new ValidMessage("bonusDate",getMessage("BUSS_COM_E_0004",getMessage("FinNetProductBonusScheme002.bonusDate"),
                            getMessage("FinNetProductBonusScheme002.delDate"))));
                }
                if(StringUtil.equals(dto.getBonuScalcFlag(), DictCodeList.分红方案_按单位分红.getValue())
                    && dto.getUnitBonusVlu() != null
                        && dto.getUnitBonusVlu().compareTo(BigDecimal.ZERO)<=0){
                    errorList.add(new ValidMessage("unitBonusVlu",getMessage("FIN_PROD_E_0015",
                            getMessage("FinNetProductBonusScheme002.unitBonusVlu"),Integer.toString(0))));
                }
                if(!StringUtil.equals(dto.getBonuScalcFlag(), DictCodeList.分红方案_按单位分红.getValue())
                    && dto.getTotalAmt() != null
                    && dto.getTotalAmt().compareTo(BigDecimal.ZERO)<=0){
                    errorList.add(new ValidMessage("totalAmt",getMessage("FIN_PROD_E_0015",
                            getMessage("FinNetProductBonusScheme002.totalAmt"),Integer.toString(0))));
                }
                if(finNetProductBonusSchemeService.countDelDate(map)>0){
                    errorList.add(new ValidMessage("delDate",getMessage("FIN_PROD_E_0057")));
                }
                if(DateUtil.format(dto.getBaseDate()).compareTo(DateUtil.getSysDate())>0){
                    errorList.add(new ValidMessage("baseDate",getMessage("BUSS_COM_E_0003",
                        getMessage("FinNetProductBonusScheme002.baseDate"), DateUtil.getSysDate())));
                }
            if (DateUtil.format(dto.getDelDate()).compareTo(DateUtil.getSysDate()) < 0) {
                errorList.add(new ValidMessage("delDate",
                    getMessage("BUSS_COM_E_0004",
                        getMessage("FinNetProductBonusScheme002.delDate"), DateUtil.getSysDate())));
                }
                if(StringUtil.equals(dto.getIsNetMormalize(), "N") && dto.getBonuScalcFlag().equals(DictCodeList.分红方案_按单位分红.getValue()) && (dto.getUnitBonusVlu() == null
                    ||StringUtil.equals(dto.getUnitBonusVlu(), "") )){
                    errorList.add(new ValidMessage("unitBonusVlu",getMessage("FW_E_0006",getMessage("FinNetProductBonusScheme002.unitBonusVlu"))));
                }
            }
        } else if(dealType.equals("bonusAmtConfirm")){
                    
                //判断方案是否在流程中
                ProcessMark processMarkb = new ProcessMark();
                processMarkb.setPageId("FinNetProductBonusScheme002");
                processMarkb.setBusinessDataPk1(dto.getBonusPlanId());
                processMarkb.setBusinessDataPk2(dto.getFinprodId());
                processMarkb.setBusinessDataPk3("bonusScheRegister");
                List<String> resultListb = leaveMarkProcessService.getBusinessDataInFlow(processMarkb);
                if (!StringUtil.isEmptyList(resultListb)) {
                    errorList.add(new ValidMessage(this.getMessage("COMMON_E_0021", dto.getBonusPlanId() ,dto.getFinprodId())));
                    return errorList;
                }
           
            //分红方案金额确认
            if (WorkFlowUtil.isNotFromToDoList()) {
            	if(dto.getOperateType()==null){
                	//已经做过分红金额确认的方案，提示不可再确认
                	if(dto.getbTradeId()!=null){
                		errorList.add(new ValidMessage("方案分红金额已经确认过，不能重复确认！"));  
                		return errorList;
                	}
                }else if(dto.getOperateType().equals("submit")){
                    if(dto.getActTotalAmt()==null){
                        errorList.add(new ValidMessage(getMessage("FIN_PROD_C_0081")));
                    }
                    
                    ProcessMark processMark = new ProcessMark();
                    processMark.setPageId("FinNetProductBonusScheme003");
                    processMark.setBusinessDataPk1(dto.getBonusPlanId());
                    processMark.setBusinessDataPk2(dto.getFinprodId());
                    List<String> resultList = leaveMarkProcessService.getBusinessDataInFlow(processMark);
                    if (!StringUtil.isEmptyList(resultList)) {
                    	errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getBonusPlanId())));
                    }
//                    if (finNetProductBonusSchemeService.checkBusinessDataInFlow(dto)) {
//                            errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getBonusPlanId())));
//                    }
                }
            }else{
                if(dto.getOperateType().equals("submit")){
                    if(dto.getActTotalAmt()==null){
                        errorList.add(new ValidMessage(getMessage("FIN_PROD_C_0081")));
                    }
                }
                
            }
        }else if(dealType.equals("bonusCash") || dealType.equals("bonusShareConfirm")){
        	
        	if(StringUtil.equals(dto.getOperateType(), "delete")||StringUtil.equals(dto.getOperateType(), "revoke")){
            	//撤销交易不能有后续交易 
            	List<TrdFinProductDealTotal> list = finNetProductBonusSchemeService.queryAfterTrdFinProductDealTotal(dto);
            	if(list!=null && list.size()>0){
            		errorList.add(new ValidMessage("有后续交易，不能撤销!"));
            	}
            	//产品红利再投撤销时，确认日不能小于平台日 yzg 20191210 start
            	if(dto.getConfirmDate()!=null && dto.getConfirmDate().compareTo(DateUtil.platDateFromCache())<0){
                    errorList.add(new ValidMessage("仅允许撤销确认日大于等于平台日期的交易！"));
                }
            	//产品红利再投撤销时，确认日不能小于平台日 yzg 20191210 end
            	return errorList;
            }
        	
            //现金红利下发
            //查询分红方式，现金分红和混合模式 --支持现金分红   红利下发和混合模式---支持红利再投
            Map<String,Object> param0 = new HashMap<String,Object>();
            param0.put("finprodId", finprodId);
            String bonusMode = finNetProductBonusSchemeService.queryBonusMode(param0);
            if(bonusMode==null){
            	//分红方式支持提示
                errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0085")));
                return errorList;
            }
            if(dealType.equals("bonusCash")){
                if(bonusMode!=null && bonusMode.equals("02")){
                    errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0086","现金分红")));
                    return errorList;
                }
                //分红金额不为空，并且大于0
                if(dto.getOperateType()!=null && (dto.getOperateType().equals("submit") || dto.getOperateType().equals("save"))){
                    if(dto.getBonusCashAmt()==null || dto.getBonusCashAmt().compareTo(BigDecimal.ZERO)<=0){
                        errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0087")));
                    }
                }
                if(dto.getOperateType()!=null && (dto.getOperateType().equals("submit") || dto.getOperateType().equals("save"))){
	                if (bussTrdTransferOrderService != null) {
	                	//划款指令不能为空
	                    List<ValidMessage> list = bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder());
	                    errorList.addAll(list);
	                }
                }
            }else if(dealType.equals("bonusShareConfirm")){
                if(bonusMode!=null && bonusMode.equals("01")){
                    errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0086","红利再投")));
                    return errorList;
                }
                if(dto.getOperateType()!=null && (dto.getOperateType().equals("submit") || dto.getOperateType().equals("save"))){
                    //分红金额和份额不为空，并且大于0
                    if(dto.getBonusShareAmt()==null || dto.getBonusShareAmt().compareTo(BigDecimal.ZERO)<=0){
                        errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0087")));
                    }
                    if(dto.getBonusShareNum()==null || dto.getBonusShareNum().compareTo(BigDecimal.ZERO)<=0){
                        errorList.add(new ValidMessage(getMessage("FIN_PROD_E_0088")));
                    }
                }
                if(StringUtil.isNotEmpty(dto.getOperateType())){
                    if(dto.getConfirmDate()==null || dto.getConfirmDate().compareTo(iPlatformDate.getSysDate())<0){
                        errorList.add(new ValidMessage("confirmDate","确认日不能为空，并且不能小于平台日"));
                    }
                }
                //净值日不能为空，必须小于等于确认日 yzg 20200108
                if(StringUtil.equals(dto.getOperateType(), "save")||StringUtil.equals(dto.getOperateType(), "submit")) {
	                if(StringUtil.isNotEmpty(dto.getVluRefDate())) {
	                	if(DateUtil.compareDate(dto.getVluRefDate(), dto.getConfirmDate()) > 0) {
	                		errorList.add(new ValidMessage("vluRefDate","净值日不能大于确认日"));
	                	}
	                }else {
	                	errorList.add(new ValidMessage("vluRefDate","请输入净值日"));
	                }
                }
            }
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("finprodId", finprodId);
            param.put("bonusPlanId", dto.getBonusPlanId());
            param.put("bTradeId", dto.getBonusPlanId());
            param.put("validType", "before_bonusDeal");
            //只有进行过分红金额确认的方案才能进行红利下发
            if(finNetProductBonusSchemeService.isExistBonusDeal(param)<1){
            	if(dealType.equals("bonusCash")){
            		errorList.add(new ValidMessage("请先确认分红金额，再进行红利下发"));
            	}else if(dealType.equals("bonusShareConfirm")){
            		errorList.add(new ValidMessage("请先确认分红金额，再进行红利再投"));
            	}
                
            }
            //只有未进行过分红下发和红利再投才能继续操作
            param.put("bTradeId", dto.getbTradeId());
            param.put("validType", "before_del");
            //modify by wm 2019-07-08 支持多次分红下发合红利再投 start
//            if(finNetProductBonusSchemeService.isExistBonusDeal(param)>=1){
//                errorList.add(new ValidMessage("方案分红金额已经确认过，不能重复确认！")); 
//                return errorList;
//            }
            BigDecimal surplusAmt = finNetProductBonusSchemeService.isExceedBonusDeal(param);
            if(surplusAmt!=null&&surplusAmt.compareTo(BigDecimal.ZERO)==0){
                errorList.add(new ValidMessage("方案分红金额已经确认过，不能重复确认！")); 
                return errorList;
            }
            //modify by wm 2019-07-08 支持多次分红下发合红利再投 end
            if (WorkFlowUtil.isNotFromToDoList()) {
            	if(dto.getOperateType()!=null && (dto.getOperateType().equals("submit") || dto.getOperateType().equals("save"))){
            		if(dealType.equals("bonusCash")){
    	            	ProcessMark processMark = new ProcessMark();
    	                processMark.setPageId("FinNetProductBonusScheme004");
    	                processMark.setBusinessDataPk1(dto.getBonusPlanId());
    	                processMark.setBusinessDataPk2(dto.getFinprodId());
    	                List<String> resultList = leaveMarkProcessService.getBusinessDataInFlow(processMark);
    	                if (!StringUtil.isEmptyList(resultList)) {
    	                	errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getBonusPlanId())));
    	                }
                	}else if(dealType.equals("bonusShareConfirm")){
                		ProcessMark processMark = new ProcessMark();
    	                processMark.setPageId("FinNetProductBonusScheme005");
    	                processMark.setBusinessDataPk1(dto.getBonusPlanId());
    	                processMark.setBusinessDataPk2(dto.getFinprodId());
    	                List<String> resultList = leaveMarkProcessService.getBusinessDataInFlow(processMark);
    	                if (!StringUtil.isEmptyList(resultList)) {
    	                	errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getBonusPlanId())));
    	                }
                	}
            	}            	
            }else{
                
            }
        }       
        return errorList;
    }
}
