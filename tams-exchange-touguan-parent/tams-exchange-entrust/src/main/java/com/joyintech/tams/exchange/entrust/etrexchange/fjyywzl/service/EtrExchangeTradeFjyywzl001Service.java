package com.joyintech.tams.exchange.entrust.etrexchange.fjyywzl.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinProductConvertibleMapper;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.FinProductConvertible;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dao.EtrExchangeTradeCommonMapper;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.PsOptionsRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.common.service.EtrExchangeTradeCommon001Service;
import com.joyintech.tams.exchange.entrust.etrexchange.fjyywzl.dto.EtrExchangeTradeFjyywzlZqzyDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.TrdOthCodeInfoDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EtrExchangeTradeFjyywzl001Service extends CommonExportService<EtrExchangeTradeFjyywzlZqzyDto> {

    @Autowired
    private EtrExchangeTradeCommon001Service etrExchangeTradeCommon001Service;

    @Autowired
    private EtrExchangeTradeCommonMapper etrExchangeTradeCommonMapper;

    @Autowired
    private FinProductConvertibleMapper finProductConvertibleMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Page<EtrExchangeTradeFjyywzlZqzyDto> findZqzyPositionsPage(Map<String, Object> param) {
        try {
            String userId = SecurityUtil.getLoginUser().getUserName();
            if (StringUtils.isNotEmpty(userId)) {
                logger.info("请求用户【{}】", "userId = " + userId);
                param.put("userId", SecurityUtil.getLoginUser().getUserName());
                return restTemplate.postForPage(
                        ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/findZqzyPositionsPage", param,
                        EtrExchangeTradeFjyywzlZqzyDto.class);
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }

    /**
     * 查询上交所和深交所标准券数量
     *
     * @param pageParam 页面参数
     * @return 上交所和深交所标准券数量
     * @see
     * @since 1.0
     */
    public Map<String, String> loadSecNum(Map<String, Object> pageParam) {
        Map<String, String> map = (Map<String, String>)restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/loadSecNum",
                pageParam, Map.class);
        return map;
    }

    /**
     * 获取交易方向
     *
     * @param  request 画面参数 SelectDto selectDto
     * @return 偏离度
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPsSelectOptions(PsOptionsRequest request,SelectDto selectDto) {
        Map<String,Object> param= JsonUtils.object2Map(request);
        List<SelectOptionDto> selectOptionDtos=new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        Date sysDate = DateUtil.platDateFromCache();
        param.put("date", DateUtil.format(sysDate));
        List<TrdOthCodeInfoDto> list = etrExchangeTradeCommon001Service.getTrdOthCodeInfos(param);
        if (null != list && list.size() > 0) {
            for (TrdOthCodeInfoDto dto : list) {
                SelectOptionDto selectOptionDto= new SelectOptionDto();
                if (!StringUtils.equals(dto.getOthCodeType(), DictCodeListExt3.金融产品其他代码类型_配股代码.getValue())
                        && !StringUtils.equals(dto.getOthCodeType(), DictCodeListExt3.金融产品其他代码类型_配债代码.getValue())) {
                    // 只能选择申购，其他委托方向不可选
                    String ps = OthTypeMapToPs(dto.getOthCodeType());
                    if (StringUtils.equals(ps, DictCodeList.交易方向_申购.getValue())) {
                        map.clear();
                        map.put(ps, dto.getDeclaredPrice());
                        selectOptionDtos = selectDto.getOptions().stream().filter(option -> {
                            return StringUtil.equals(option.getId(),ps) ;
                        }).collect(Collectors.toList());
                        for (SelectOptionDto option : selectOptionDtos){
                            option.setObject(map.get(option.getId()));
                        }
                        return selectOptionDtos;
                    } else if (StringUtils.equals(ps, DictCodeListExt3.交易方向_质押券入库.getValue())) {
                        String converseRatio = etrExchangeTradeCommon001Service.selectStdsecConverseRatio(param);
                        if (StringUtil.isNotEmpty(converseRatio)) {
                            map.put(DictCodeListExt3.交易方向_质押券入库.getValue(), converseRatio);
                            map.put(DictCodeListExt3.交易方向_质押券出库.getValue(), converseRatio);
                        }
                    } else {
                        map.put(ps, dto.getDeclaredPrice());
                    }
                }else{
                    if (StringUtils.equals(dto.getOthCodeType(), DictCodeListExt3.金融产品其他代码类型_配股代码.getValue())){
                        map.put(DictCodeListExt2.交易方向_配股.getValue(), dto.getDeclaredPrice());
                    }
                    if (StringUtils.equals(dto.getOthCodeType(), DictCodeListExt3.金融产品其他代码类型_配债代码.getValue())) {
                        map.put(DictCodeListExt3.交易方向_配债.getValue(), dto.getDeclaredPrice());
                    }
                }
            }
        }

        // add by etliu 20211207 start
        // 交易所债券非交易类业务迁移
        if (!map.containsKey(DictCodeListExt3.交易方向_质押券入库.getValue())) {
            String converseRatio = etrExchangeTradeCommon001Service.selectStdsecConverseRatio(param);
            if (StringUtil.isNotEmpty(converseRatio)) {
                map.put(DictCodeListExt3.交易方向_质押券入库.getValue(), converseRatio);
                map.put(DictCodeListExt3.交易方向_质押券出库.getValue(), converseRatio);
            }
        }

        String finprodId = (String) param.get("finprodId");
        if (!map.containsKey(DictCodeList.交易方向_行权_回售.getValue())) {
            List<FinExercisePlan> exercisePlanList = this.selectFinExercisePlanData(finprodId, sysDate);
            if (!StringUtil.isEmptyList(exercisePlanList)) {
                map.put(DictCodeList.交易方向_行权_回售.getValue(), exercisePlanList.get(0).getExercisePrice());
            }
        }
        if (!map.containsKey(DictCodeListExt1.交易方向_债转股.getValue())) {
            FinProductConvertible finProductConvertible = this.selectConverseShareData(finprodId, sysDate);
            if (finProductConvertible != null) {
                map.put(DictCodeListExt1.交易方向_债转股.getValue(), finProductConvertible.getConPrice());
            }
        }
        selectOptionDtos = selectDto.getOptions().stream().filter(option -> {
                return map.containsKey(option.getId());
        }).collect(Collectors.toList());
        for (SelectOptionDto option : selectOptionDtos){
            option.setObject(map.get(option.getId()));
        }
        return selectOptionDtos;
    }

    /**
     * 申报类型-》交易方向的映射
     * @param othCodeType
     * @return
     */
    private String OthTypeMapToPs(String othCodeType) {
        if (org.apache.commons.lang.StringUtils.equals(othCodeType, DictCodeListExt3.金融产品其他代码类型_可转债信用申购代码.getValue())) {
            return DictCodeList.交易方向_申购.getValue();
        }else if(org.apache.commons.lang.StringUtils.equals(othCodeType, DictCodeListExt3.金融产品其他代码类型_回售代码.getValue())){
            return DictCodeList.交易方向_行权_回售.getValue();
        }else if(org.apache.commons.lang.StringUtils.equals(othCodeType, DictCodeListExt3.金融产品其他代码类型_可转债转股代码.getValue())){
            return DictCodeListExt1.交易方向_债转股.getValue();
        }else if(org.apache.commons.lang.StringUtils.equals(othCodeType, DictCodeListExt3.金融产品其他代码类型_质押代码.getValue())){
            return DictCodeListExt3.交易方向_质押券入库.getValue();
        }
        return null;
    }

    /**
     *
     * @Description //查询行权回售数据
     * @Param [map]
     * @return 行权回售数据
     * @version 1.0
     * @see
     **/
    public List<FinExercisePlan> selectFinExercisePlanData(String finprodId, Date paramDate){
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        map.put("sysDate", paramDate);
        map.put("exerciseType", DictCodeList.行权方式_回售.getValue());
        return etrExchangeTradeCommonMapper.selectFinExercisePlanData(map);
    }

    /**
     * 查询转股信息
     * @param finprodId finprodId
     * @param paramDate paramDate
     * @return 转股信息
     */
    public FinProductConvertible selectConverseShareData(String finprodId, Date paramDate) {
        if (StringUtils.isNotEmpty(finprodId)) {
            FinProductConvertible finProductConvertible = finProductConvertibleMapper.selectByPrimaryKey(finprodId);
            if (finProductConvertible != null && finProductConvertible.getConVdate() != null
                    && DateUtil.compareDate(finProductConvertible.getConVdate(), paramDate) <= 0) {
                return finProductConvertible;
            }
        }
        return null;
    }
}
