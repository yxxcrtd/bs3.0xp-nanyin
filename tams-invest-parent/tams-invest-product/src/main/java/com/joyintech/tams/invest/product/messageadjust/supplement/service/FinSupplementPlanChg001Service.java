package com.joyintech.tams.invest.product.messageadjust.supplement.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinFloatAccrualScheMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.calculator.AdjustedTradeDayCal;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.supplement.dto.FinSupplementPlanChg001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class FinSupplementPlanChg001Service extends CommonExportService<FinSupplementPlanChg001Dto>  implements BaseProcessService<FinSupplementPlanChg001Dto>{
    @Autowired
    private FinFloatAccrualScheMapper  finFloatAccrualScheMapper;
    @Autowired
    private DateCalService dateCalService;
    @Autowired
     private FinProductMapper finProductMapper;
    
    @Override
    public void doComplete(FinSupplementPlanChg001Dto finSupplementPlanChg001Dto, OperationType operationType) {
        
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            if(!StringUtil.isEmptyList(finSupplementPlanChg001Dto.getDataList())){
                
                //删除旧的回补计划数据
                Map<String,Object> param = new HashMap<String,Object>();
                List<String> cashIdList = new ArrayList<String>();
                for(FinSupplementPlanChg001Dto dto:finSupplementPlanChg001Dto.getDataList()) {
                    if(!cashIdList.contains(dto.getCashId())) {
                        cashIdList.add(dto.getCashId());
                    }
                }
                param.put("cashIdList", cashIdList);
                
                restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                    + "/FinSupplementPlanChg/deleteAccrualSche",param,boolean.class);
                //插入新的回补数据
                for(FinSupplementPlanChg001Dto dto:finSupplementPlanChg001Dto.getDataList()){
                    finFloatAccrualScheMapper.insert(dto);
                }
            } 
        } else {
            
        }

    }
    
    
    public Page<SelectOptionDto> getFinprodIds(Map<String,Object> param){
        if(!StringUtil.isNotEmpty(param.get("initValue"))){
            User user = SecurityUtil.getLoginUser();
            param.put("userName", user.getUserName());
        }
        param.put("ISSUE_MODE_02", BussinessDictCodeList.开放类型_开放式.getValue());
        param.put("CASH_TYPE_1_06", DictCodeListExt1.现金流一级类型_回补.getValue());
         
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME
                    + "/FinSupplementPlanChg/getFinprodIds",param,SelectOptionDto.class);
    }
     
    public Page<FinSupplementPlanChg001Dto> findPage(Map<String, Object> param){
        String layFinprodId = (String)param.get("layFinprodId");
        if(!StringUtil.isBlank(layFinprodId)){
            List<String> layFinprodIdList =  StringUtil.splitToList(",", layFinprodId);
            param.put("layFinprodIdList", layFinprodIdList);
        }
        param.put("CASH_TYPE_1_06", DictCodeListExt1.现金流一级类型_回补.getValue());
        
        Page<FinSupplementPlanChg001Dto> page=restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinSupplementPlanChg/findPage",param,FinSupplementPlanChg001Dto.class);
        return page;
    }

    public String getAccrDate(@RequestParam Map<String, Object> params) {
          // 交易日历取得
        
        String calendarId="01";
        if(params.get("finprodId")!=null) {
            calendarId= finProductMapper.selectByPrimaryKey(params.get("finprodId").toString()).getCalendarId(); 
        }
              
        List<String> dealCalendarHolidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(params.get("accrStrDate").toString()),
                    DateUtil.parse(params.get("accrEndDate").toString()), calendarId);
            // 回补日
           
        String datestr="";
        String tdayRule=restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME 
            + "/FinSupplementPlanChg/getcycleTdayRule",params,String.class);
        if (StringUtil.equals(BussinessDictCodeList.营业日准则_向后调整.getValue(),tdayRule)) {
            String anapleroticDateStr = AdjustedTradeDayCal.getAdjustedTradeDay(
                    params.get("accrEndDate").toString(), tdayRule,dealCalendarHolidayList);
            datestr=anapleroticDateStr;
        }
        else {
            datestr=params.get("accrEndDate").toString();
        }
        return datestr;
    }
     
     
        /**
         * 产品款型下拉框
         * <一句话功能简述>
         * <功能详细描述>
         * @param param
         * @return
         * @see
         * @since 1.0
         */
    public Page<SelectOptionDto> queryProdStyleInfo(Map<String, Object> param) {
        String finprodMarketId = (String)param.get("finprodMarketId");
        if(!StringUtil.isBlank(finprodMarketId)){
            param.put("finprodMarketId", finprodMarketId);
            param.put("style", "01");
            return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/getProdStyle", param,
                SelectOptionDto.class); 
        } 
        else {
            return null;
        }

    }
        
        /**
         * 拆分计划
         * <一句话功能简述>
         * <功能详细描述>
         * @param finSupplementPlanChg001Dto
         * @return
         * @see
         * @since 1.0
         */
    public List<FinSupplementPlanChg001Dto> splitPlan(FinSupplementPlanChg001Dto finSupplementPlanChg001Dto) {
        List<FinSupplementPlanChg001Dto> list = new ArrayList<FinSupplementPlanChg001Dto>();
        if(!StringUtil.isEmptyList(finSupplementPlanChg001Dto.getDataList())) {
           //拆分的计划只会有一条
            FinSupplementPlanChg001Dto splitDto = finSupplementPlanChg001Dto.getDelList().get(0);
                
            FinSupplementPlanChg001Dto newDto1 = new FinSupplementPlanChg001Dto();
            FinSupplementPlanChg001Dto newDto2 = new FinSupplementPlanChg001Dto();
            BeanUtil.copyPropertiesIgnoreNull(newDto1,splitDto);
            BeanUtil.copyPropertiesIgnoreNull(newDto2,splitDto);
                
            newDto1.setAccrDate(splitDto.getNewAccrDate());
            newDto1.setAccrEndDate(splitDto.getNewAccrDate());
            newDto1.setIsAvailable(DictCodeList.是否_否.getValue());
                
            newDto2.setAccrStrDate(splitDto.getNewAccrDate());
            newDto2.setIsAvailable(DictCodeList.是否_否.getValue());
                
            int index = 0;//重新计算rn
            for(int i=0;i<finSupplementPlanChg001Dto.getDataList().size();i++) {
                FinSupplementPlanChg001Dto dto = finSupplementPlanChg001Dto.getDataList().get(i);
                if(dto.getRn().compareTo(splitDto.getRn())==0) {
                    newDto1.setRn(new BigDecimal(++index));
                    list.add(newDto1);
                       
                    newDto2.setRn(new BigDecimal(++index));
                    list.add(newDto2);
                        
                } 
                else {
                    dto.setRn(new BigDecimal(++index));
                    list.add(dto);
                        
                }
            } 
               
        }
        return list;
    }
    
    /**
     * 合并计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<FinSupplementPlanChg001Dto> joinPlan(FinSupplementPlanChg001Dto finSupplementPlanChg001Dto) {
        List<FinSupplementPlanChg001Dto> newList = new ArrayList<FinSupplementPlanChg001Dto>();
        Map<BigDecimal, Object> map = new HashMap<BigDecimal, Object>();
        if(!StringUtil.isEmptyList(finSupplementPlanChg001Dto.getDataList())) {
            if(!StringUtil.isEmptyList(finSupplementPlanChg001Dto.getDelList())){
                //从全量计划中删选出不需要合并的计划
                FinSupplementPlanChg001Dto newDto=new FinSupplementPlanChg001Dto();
                
                //合并计划
                BeanUtil.copyPropertiesIgnoreNull(newDto,finSupplementPlanChg001Dto.getDelList().get(0));
                for(int i=0;i<finSupplementPlanChg001Dto.getDelList().size();i++){
                    FinSupplementPlanChg001Dto delDto = finSupplementPlanChg001Dto.getDelList().get(i);
                    map.put(delDto.getRn(), delDto);
                    if(i==0){ 
                        newDto.setAccrStrDate(delDto.getAccrStrDate());
                        newDto.setValDate(delDto.getAccrEndDate());    
                    }
                    if(i==finSupplementPlanChg001Dto.getDelList().size()-1){
                        newDto.setAccrEndDate(delDto.getAccrEndDate());
                        newDto.setAccrDate(delDto.getAccrDate());
                    } 
                }
                
                newDto.setIsAvailable(DictCodeList.是否_否.getValue());
                for(FinSupplementPlanChg001Dto dto:finSupplementPlanChg001Dto.getDataList()) {
                    if(!StringUtil.isNotEmpty(map.get(dto.getRn()))){
                        newList.add(dto) ; 
                    }
                }
                
                newList.add(newDto);
                //重新排序
                newList.sort((dtoA, dtoB) -> dtoA.getRn().compareTo(dtoB.getRn()));
                //重新生成rn
                for(int i= 0;i<newList.size();i++) {
                    newList.get(i).setRn(ArithmeticUtil.doAdd(new BigDecimal(i),BigDecimal.ONE));
                }

            }
        }
 
        return newList; 
    }
}
