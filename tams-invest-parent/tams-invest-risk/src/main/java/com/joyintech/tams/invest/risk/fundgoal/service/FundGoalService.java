package com.joyintech.tams.invest.risk.fundgoal.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.MstHolidayKey;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.fundgoal.dto.FundGoalDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname FundGoalService
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/3 11:15 
 * @version 1.0 
 */
@Service
public class FundGoalService extends CommonExportService<FundGoalDto> implements BaseProcessService<FundGoalDto> {

    @Autowired
    private DateCalService dateCalService;

    public  List<SelectOptionDto> getFinprodMarketIdList(Map<String,Object> param) {
        return restTemplate.postForList(
                ServiceApplication.RISK_SERVICE_NAME + "/FundGoal/getFinprodMarketIdList",param,
                SelectOptionDto.class);
    }

    @Override
    public void doComplete(FundGoalDto fundGoalDto, OperationType operationType) {
        restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME+"/FundGoal/doComplete/" + operationType, fundGoalDto, Integer.class);
    }

    /**
     *
     * @Description //分页查询
     * @Param [parameters]
     * @return com.joyintech.framework.grid.Page<com.joyintech.tams.invest.risk.fundgoal.dto.FundGoalDto>
     * @version 1.0
     * @see
    **/
    public Page<FundGoalDto> selectForPage(Map<String, Object> parameters) {
        return restTemplate.postForPage(
                ServiceApplication.RISK_SERVICE_NAME + "/FundGoal/findPage" , parameters,
                FundGoalDto.class);
    }



    /**
     *
     * @Description //根据资产代码查询Dto
     * @Param [map]
     * @return com.joyintech.tams.invest.risk.fundgoal.dto.FundGoalDto
     * @version 1.0
     * @see
    **/
    public FundGoalDto selectByPrimaryKey(Map<String, Object> map) {
        return restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/FundGoal/selectByPrimaryKey" , map,
                FundGoalDto.class);
    }


    /**
     *
     * @Description //获取交易日历
     * @Param [map]
     * @return boolean
     * @version 1.0
     * @see
     **/
    public String getCalendarId(Map<String,Object> map){
        return restTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/FundGoal/getCalendarId" , map,
                String.class);
    }

    /**
     *
     * @Description //判断是否为节假日
     * @Param [map]
     * @return boolean
     * @version 1.0
     * @see
     **/
    public Date getNextPubDate(Map<String,Object> map){
        String calendarId = this.getCalendarId(map);
        Date sysDate = DateUtil.platDateFromCache();
        Date nexPubDate = DateUtil.addMonthDate(sysDate,3);
        while (dateCalService.isDealCalendarHoliday(nexPubDate,getCalendarId(map))){
            nexPubDate = DateUtil.addDay(nexPubDate,1);
        }
        return nexPubDate;
    }

}
