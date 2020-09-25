package com.joyintech.tams.invest.entrust.etrbondtostock.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrbondtostock.dto.EtrBondToStockDto;
import com.joyintech.tams.invest.entrust.etrconbond.dto.EtrConBondDto;
import com.joyintech.tams.invest.entrust.etrstock.dto.EtrStockDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author gaopeng
 * @version 1.0 2019年10月24日
 * @see EtrBondToStock001Service
 * @since 1.0
 */
@Service
public class EtrBondToStockService extends CommonExportService<EtrBondToStockDto> implements BaseProcessService<EtrBondToStockDto>{
    /**
     * 共通组件
     */
    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;
    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;
    
    /**
     * 
     * 债转股委托分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
	public  Page<EtrBondToStockDto> selectForPage(Map<String, Object> pageParam) {
        if (StringUtil.isNotEmpty((String)pageParam.get("entrustDate"))) {
            List<String> dateList = StringUtil.splitToList(" ", (String)pageParam.get("entrustDate"));
            pageParam.put("startDate", dateList.get(0));
            pageParam.put("endDate", dateList.get(2));
        }
		pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBondToStock/findPage",
        		pageParam, EtrBondToStockDto.class);
	}

    /**
     * 
     * 持仓分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
	public  Page<EtrBondToStockDto> findOtherPage(Map<String, Object> pageParam) {
		pageParam.put("sysDate",DateUtil.format(DateUtil.platDateFromCache()));
		pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
		Page<EtrBondToStockDto> pagelist= restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBondToStock/findOtherPage",
        		pageParam, EtrBondToStockDto.class);
		return pagelist;
	}
	
    public String getCounterIdByFinprodId(String finprodId){
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME +
                "/EtrBondToStock/getCounterIdByFinprodId", finprodId, String.class);
    }
	
	
    /**
     * {@inheritDoc}
     */
	@Override
	public void doComplete(EtrBondToStockDto entity, OperationType type) {
	     restTemplate.postForObject(
	             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBondToStock/doComplete",entity,
	             Integer.class);
	}
	
    /**
     * 为调授信 封装信息
     * @param etrBondToStockDto
     * @return
     */
    public TrdProductDeal getTrdProductDeal(EtrBondToStockDto etrBondToStockDto) throws InvocationTargetException, IllegalAccessException {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        BeanUtil.copyProperties(trdProductDeal, etrBondToStockDto);
        //如果画面交易对手未填，则取债券标的的发行人
        if (StringUtil.isNotEmpty(etrBondToStockDto.getEtrInvestPersonInfo().getCounterId())){
            trdProductDeal.setCounterId(etrBondToStockDto.getEtrInvestPersonInfo().getCounterId());
        }else {
            String counterId = this.getCounterIdByFinprodId(etrBondToStockDto.getShareFinprodId());
            trdProductDeal.setCounterId(counterId);
        }
        trdProductDeal.setTradeDate(etrBondToStockDto.getVdate()); // 画面确认日期=交易日期
        trdProductDeal.setShareAmt(etrBondToStockDto.getStocketrPriceTotal().divide(BigDecimal.valueOf(100),2));
        trdProductDeal.setCpriceAmt(etrBondToStockDto.getStocketrPriceTotal());
        trdProductDeal.setFpriceAmt(etrBondToStockDto.getStocketrPriceTotal());
        trdProductDeal.setPrinAmt(etrBondToStockDto.getStocketrPriceTotal());
        trdProductDeal.setIntAmt(BigDecimal.ZERO);
        trdProductDeal.setTradeAmt(etrBondToStockDto.getStocketrPriceTotal());
        return trdProductDeal;
    }

    /**
     * 
     * 获取转债代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                                       + "/EtrBondToStock/getFinprodId" , map,
                                       SelectOptionDto.class);

    }
    
    /**
     * 
     * 获取正股代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getShareId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                                       + "/EtrBondToStock/getShareId" , map,
                                       SelectOptionDto.class);

    }
    

    /**
     * 获取债券持仓
     * @param pageParam 画面参数
     * @return
     */
    @SuppressWarnings("unchecked")
	public Map<String, Object> getPositionInfo(Map<String, Object> pageParam) {
    	return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                + "/EtrBondToStock/getPositionInfo" , pageParam,
                Map.class);
    }
    
    /**
     * 导出
     *
     * @param exportParam 导出信息
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        if (exportParam.getSelectParam().get("isPosi") != null && Boolean.parseBoolean((String) exportParam.getSelectParam().get("isPosi"))) {
            exportParam.config("/com/joyintech/tams/invest/entrust/etrbondtostock/conf/export_confPosi.xml").dataSource(
                    DataSourceType.MASERT);
        }
        else {
            exportParam.config("/com/joyintech/tams/invest/entrust/etrbondtostock/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<EtrBondToStockDto> selectPageData(Map<String, Object> selectParam) {
        if (selectParam.get("isPosi") != null && Boolean.parseBoolean((String) selectParam.get("isPosi"))) {
            return findOtherPage(selectParam);
        }
        else {
            return selectForPage(selectParam);
        }
    }
	
    /**
     * 获取新建页面转债代码的联想文本框 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodIdForAdd(Map<String, Object> map) {
    	map.put("sysDate",DateUtil.format(DateUtil.platDateFromCache()));
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                                       + "/EtrBondToStock/getFinprodIdForAdd" , map,
                                       SelectOptionDto.class);

    }
	
    /**
     * 校验方法 <功能详细描述>
     *
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrBondToStockDto dto) {
        dto.setCloseWay(BussinessDictCodeList.委托关闭方式__人工.getValue());
        return restTemplate.postForList(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBondToStock/check", dto,
            ValidMessage.class);
    }
    
    /**
     * 组合可用现金查询
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        Date sysDate = DateUtil.platDateFromCache();
        param.put("sysDate",DateUtil.format(sysDate));
        String calId = (String)param.get("calId");
        if(StringUtil.isNotEmpty(calId)){
            List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(sysDate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(sysDate)), 15),calId);
            Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(sysDate)), 1, holidayList, "1");
            param.put("sysDateT1",DateUtil.format(tradeDay));
        }
         return restTemplate.postForObject(
             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBondToStock/getProAvailableCash",param,
             Map.class);
    }
    
    
    /**
    *
    * 删除可转债转股委托
    * <功能详细描述>
    * @param entrustId
    * @return
    * @see
    * @since 1.0
    */
   public boolean deleteDelegate(String entrustId) {
       return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBondToStock/delete?entrustId="+entrustId,
           boolean.class);
   }
   
   /**
    *@title: selectLimitAmt
    *@description: 查询授权额度
    *@author: junYin
    *@date: 2019/06/04 0:32
    *@param: * @param dto 1
    *@return: java.math.BigDecimal
    *@throws:
    */
   /*public BigDecimal selectLimitAmt(EtrBondToStockDto dto){
       Map<String, Object> map = new HashMap<>();
       map.put("userName", SecurityUtil.getLoginUser().getUserName());
       map.put("assetType", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
       map.put("assetSecType", dto.getAssetType1());
       map.put("tradeMarket", dto.getTradeMarket());
       map.put("ps",dto.getPs());
       return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/common/SelectLimitAmt/selectLimitAmt", map, BigDecimal.class);
   }
   */
   /**
    * 修改/双击查看详情查询信息 <功能详细描述>
    *
    * @param entrustId
    * @return
    * @see
    * @since 1.0
    */
   public EtrBondToStockDto selectByKey(String entrustId,String finprodId,String portfolioId,String sysDate) {
       if(!StringUtil.isNotEmpty(entrustId)){
           entrustId="";
       }
       if(!StringUtil.isNotEmpty(finprodId)){
           finprodId="";
       }
       if(!StringUtil.isNotEmpty(portfolioId)){
           portfolioId="";
       }
       return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                        + "/EtrBondToStock/selectByKey?entrustId=" + entrustId+"&finprodId="+finprodId+"&portfolioId="+portfolioId+"&sysDate="+sysDate,
       EtrBondToStockDto.class);
   }
   
   /**
    * 撤回委托 <功能详细描述>
    *
    * @param dto 实体类
    * @return boolean
    * @see
    * @since 1.0
    */
   public boolean recallDelegate(EtrBondToStockDto dto) {
       return restTemplate.postForObject(
           ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBondToStock/recall", dto,
           boolean.class);
   }
   /**
    * 关闭委托
    *
    * @param
    * @return
    * @see
    * @since 1.0
    */
   public boolean closeDelegate(String entrustId,String closeWay) {
       return restTemplate.getForObject(
           ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBondToStock/close?entrustId="+entrustId+"&closeWay="+closeWay,
           boolean.class);
   }
   
   /**
    * 判断是否证券账户
    *
    * @param
    * @return
    * @see
    * @since 1.0
    */
   public boolean getYesOrNo(String secAcctId) {
	   return restTemplate.getForObject(
	           ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrBondToStock/yesOrNo?secAcctId="+secAcctId,
	           boolean.class);
	}
}
