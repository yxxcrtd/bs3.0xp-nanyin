package com.joyintech.service.risk.fundgoal.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.fundgoal.dao.FundGoalMapper;
import com.joyintech.service.risk.fundgoal.dto.FundGoalDto;
import com.joyintech.tams.common.base.dao.MstHolidayMapper;
import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.tams.common.base.entity.MstHolidayKey;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.redisinit.aop.AssetInputRedisAop;
import com.joyintech.tams.invest.common.redisinit.dto.ImplParaRds;
import com.joyintech.tams.invest.common.redisinit.service.RiskRefreshRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Classname FundGoalService
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/3 13:30 
 * @version 1.0 
 */

@Service("backFundGoalService")
public class FundGoalService extends BaseService<FundGoalDto> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(FundGoalService.class);
    @Autowired
    FundGoalMapper fundGoalMapper;

    @Autowired
    MstHolidayMapper mstHolidayMapper;

    @Autowired
    private RiskRefreshRedisService riskRefreshRedisService;
    /**
     *
     * @Description //资产代码下拉框
     * @Param [finprodMarketId]
     * @return java.util.List<com.joyintech.framework.select.SelectOptionDto>
     * @version 1.0
     * @see
    **/
    public List<SelectOptionDto> getFinprodMarketIdList( Map<String, Object> param) {
        return fundGoalMapper.getFinProdMarketIdList(param);
    }


    /**
     *
     * @Description //分页查询
     * @Param [page, length, param]
     * @return com.joyintech.framework.grid.Page<com.joyintech.service.risk.fundgoal.dto.FundGoalDto>
     * @version 1.0
     * @see
    **/
    public Page<FundGoalDto> selectForPage(int page, int length, Map<String, Object> param) {
        return this.queryForPage(page,length,()->fundGoalMapper.selectForPage(param));
    }

    /**
     *
     * 工作流完结之后的操作
     * <功能详细描述>
     * @param entity 实体
     * @param
     * @see
     * @since 1.0
     */
    public void doComplete(FundGoalDto entity) {
        if(StringUtil.equals(entity.getOperateType(), "DELETE")) {
                fundGoalMapper.deleteByPrimaryKey(entity);
        }else if(StringUtil.equals(entity.getOperateType(), "NEW")){
            BigDecimal add = entity.getbEquityPer().add(entity.getbCreditorRightsPer()).add(entity.getbCommodityDervPer()).add(entity.getbDebtPer());
            entity.setEquityPer(entity.getbEquityPer().divide(add,4,BigDecimal.ROUND_HALF_UP));
            entity.setCommodityDervPer(entity.getbCommodityDervPer().divide(add,4,BigDecimal.ROUND_HALF_UP));
            entity.setCreditorRightsPer(entity.getbCreditorRightsPer().divide(add,4,BigDecimal.ROUND_HALF_UP));
            entity.setDebtPer(entity.getbDebtPer().divide(add,4,BigDecimal.ROUND_HALF_UP));

            entity.setbEquityPer(entity.getbEquityPer().divide(new BigDecimal(100)));
            entity.setbCreditorRightsPer(entity.getbCreditorRightsPer().divide(new BigDecimal(100)));
            entity.setbCommodityDervPer(entity.getbCommodityDervPer().divide(new BigDecimal(100)));
            entity.setbDebtPer(entity.getbDebtPer().divide(new BigDecimal(100)));
            fundGoalMapper.insert(entity);
        }else{
            BigDecimal add = entity.getbEquityPer().add(entity.getbCreditorRightsPer()).add(entity.getbCommodityDervPer()).add(entity.getbDebtPer());
            entity.setEquityPer(entity.getbEquityPer().divide(add,4,BigDecimal.ROUND_HALF_UP));
            entity.setCommodityDervPer(entity.getbCommodityDervPer().divide(add,4,BigDecimal.ROUND_HALF_UP));
            entity.setCreditorRightsPer(entity.getbCreditorRightsPer().divide(add,4,BigDecimal.ROUND_HALF_UP));
            entity.setDebtPer(entity.getbDebtPer().divide(add,4,BigDecimal.ROUND_HALF_UP));

            entity.setbEquityPer(entity.getbEquityPer().divide(new BigDecimal(100)));
            entity.setbCreditorRightsPer(entity.getbCreditorRightsPer().divide(new BigDecimal(100)));
            entity.setbCommodityDervPer(entity.getbCommodityDervPer().divide(new BigDecimal(100)));
            entity.setbDebtPer(entity.getbDebtPer().divide(new BigDecimal(100)));
            fundGoalMapper.updateByPrimaryKeySelective(entity);
        }
        // 刷新Redis参数（混合基金投向）
        ImplParaRds implParaRds=new ImplParaRds();
        implParaRds.setCommonBusinessType(DictCodeListExt2.共通业务类型_基金投向维护.getValue());
        try{
            riskRefreshRedisService.refreshParamRedis(implParaRds);
        }catch (Exception e){
            //异常无需抛出，在内部处理掉
            logger.error("基金投向维护更新指标参数redis异常",e);
        }

    }




    /**
     *
     * @Description //根据主键查询Dto信息
     * @Param [param]
     * @return com.joyintech.service.risk.fundgoal.dto.FundGoalDto
     * @version 1.0
     * @see
    **/
    public FundGoalDto selectByPrimaryKey(Map<String, Object> param){
        return fundGoalMapper.selectPrimaryKey(param);
    }

    public String getCalendarId(Map<String, Object> param) {
        String calendarId = fundGoalMapper.getCalendarId(param).get("calendarId").toString();
        return calendarId;
    }
}
