package com.joyintech.service.svrcom.submitmaintenance.exchangerateinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.dto.SvrMstExchangeQuotationInfoDto;
import com.joyintech.tams.report.submitmaintenance.exchangerateinfo.service.SvrMstExchangeQuotationInfoService;

/**
 * 汇率行情 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see MstExchangeQuotationInfo001Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/SvrMstExchangeQuotationInfo")
public class SvrMstExchangeQuotationInfoController {

    /**
     * 汇率行情service
     */
    @Autowired
    private SvrMstExchangeQuotationInfoService svrMstExchangeQuotationInfoService;
    
    /**
     * 汇率行情分页查询 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 返回查询结果
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectForPage/{page}/{length}")
    public Page<SvrMstExchangeQuotationInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam) throws Exception{
        return svrMstExchangeQuotationInfoService.selectForPage(pageParam);
    }
    /**
     * 查询所有数据验证删除是否存在 <功能详细描述>
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectAllList")
    public List<SvrMstExchangeQuotationInfoDto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return svrMstExchangeQuotationInfoService.selectAllList(pageParam);
    }
    /**
     * 
     * 保存操作
     * <功能详细描述>
     * @param map 参数
     * @see
     * @since 1.0
     */
    @RequestMapping("/doComplete")
    public void doComplete(@RequestBody SvrMstExchangeQuotationInfoDto dto){
        svrMstExchangeQuotationInfoService.doComplete(dto);
    }
    /**
     * 
     * 保存操作
     * <功能详细描述>
     * @param map 参数
     * @see
     * @since 1.0
     */
    @RequestMapping("/selectForExport")
    public List<SvrMstExchangeQuotationInfoDto> selectForExport(@RequestBody Map<String, Object> pageParam){
        return svrMstExchangeQuotationInfoService.selectForExport(pageParam);
    }
}
