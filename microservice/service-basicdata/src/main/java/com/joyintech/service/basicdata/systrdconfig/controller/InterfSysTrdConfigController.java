package com.joyintech.service.basicdata.systrdconfig.controller;

import com.joyintech.service.basicdata.systrdconfig.dto.*;
import com.joyintech.service.basicdata.systrdconfig.service.InterfSysTrdConfigService;
import com.joyintech.service.basicdata.systrdconfig.service.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.joyintech.framework.util.StringUtil;

/**
 * @Classname InterfSysTrdConfigController
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/10/13 11:29 
 * @version 1.0 
 */
@RestController("InterfSysTrdConfigCtr_dtx")
@RequestMapping("/InterfSysTrdConfig")
public class InterfSysTrdConfigController{

    @Autowired
    private InterfSysTrdConfigService interfSysTrdConfigService;

    @Autowired
    private StockInfoService stockInfoService;

    /**
     *
     * @Description //查询最小价差
     * @Param [map]
     * @return com.joyintech.service.basicdata.systrdconfig.dto.InterfSysTrdConfigDto
     * @version 1.0
     * @see
    **/
    @PostMapping("getMinPrice")
    public Map<String,Object> getMinPrice(@RequestBody Map<String,Object> map){
        return interfSysTrdConfigService.getMinPrice(map);
    }

    /**
     *
     * @Description //查询最小单位，交易上下限数据
     * @Param [map]
     * @return com.joyintech.service.basicdata.systrdconfig.dto.InterfSysTrdConfigDto
     * @version 1.0
     * @see
    **/

    @PostMapping("getMinTrdUnitLimit")
    public Map<String,Object> getMinTrdUnitLimit(@RequestBody Map<String,Object> map){
        return interfSysTrdConfigService.getMinTrdUnitLimit(map);
    }

    /**
     *
     * @Description //查询交易时间段
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @version 1.0
     * @see
    **/
    @PostMapping("getTrdTimeConfig")
    public Map<String,Object> getTrdTimeConfig(@RequestBody Map<String,Object> map){
        return interfSysTrdConfigService.getTrdTimeConfig(map);
    }

    /**
     *
     * @Description //查询申报信息
     * @Param [map]
     * @return java.util.List<com.joyintech.service.basicdata.systrdconfig.dto.TrdOthCodeInfoDto>
     * @version 1.0
     * @see
    **/
    @PostMapping("getTrdOthCodeInfo")
    public Map<String,Object> getTrdOthCodeInfo(@RequestBody Map<String,Object> map){
        return interfSysTrdConfigService.getTrdOthCodeInfoDto(map);
    }


    /**
     * 查询交易对手信息
     * @param params
     * @return
     */
    @PostMapping("selectMstCounterParty")
    public List<Map<String, Object>> selectMstCounterParty(@RequestBody Map<String,Object> params){
        return interfSysTrdConfigService.selectMstCounterParty(params);
    }

    /**
     * 查询交易对手席位信息
     * @param params
     * @return
     */
    @PostMapping("selectCounterSeatId")
    public List<Map<String, Object>> selectCounterSeatId(@RequestBody Map<String,Object> params){
        return interfSysTrdConfigService.selectCounterSeatId(params);
    }

    /**
     * 查询对手方交易员信息
     * @param params
     * @return
     */
    @PostMapping("selectCounterTraderId")
    public List<Map<String, Object>> selectCounterTraderId(@RequestBody Map<String,Object> params){
        return interfSysTrdConfigService.selectCounterTraderId(params);
    }

    /**
     * 新增交易对手席位
     * @param params
     * @return
     */
    @PostMapping("insertCounterSeatId")
    public Map<String, Object> insertCounterSeatId(@RequestBody Map<String,Object> params){
        return interfSysTrdConfigService.insertCounterSeatId(params);
    }

    /**
     * 新增对手方交易员
     * @param params
     * @return
     */
    @PostMapping("insertCounterTraderId")
    public Map<String, Object> insertCounterTraderId(@RequestBody Map<String,Object> params){
        return interfSysTrdConfigService.insertCounterTraderId(params);
    }

    @PostMapping("selectStockInfo")
    public Map<String,Object> selectStockInfo(@RequestBody Map<String,Object> params){
        return stockInfoService.selectStockInfo(params);
    }


    /**
     *
     * @Description //折算率
     * @Param [map]
     * @version 1.0
     * @see
     **/
    @PostMapping("getMoreConvrate")
    public Map<String,Object> getMoreConvrate(@RequestBody Map<String,Object> map){
        Map<String,Object> temp =  interfSysTrdConfigService.getMoreConvrate(map);

            List<StandardCouponReConversionRateDto> lst = (List<StandardCouponReConversionRateDto>)temp.get ("data");
            if(lst.size () == 0) {
                if (StringUtil.isNotEmpty (map.get ("acctNo"))) {
                    return interfSysTrdConfigService.getAllConvrate (map);
                }
            }
        return temp;
    }


    /**
     *
     * @Description // 新股申购、配股配债额度查询
     * @Param [params]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @version 1.0
     * @see
    **/
    @PostMapping("getPurchQuotaInfo")
    public Map<String,Object> getPurchQuotaInfo(@RequestBody Map<String,Object> params){
        return interfSysTrdConfigService.getPurchQuotaInfo(params);
    }
}
