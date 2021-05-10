package com.joyintech.service.basicdata.marketdata.exchangerateinfo.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.exchangerateinfo.dto.ComExchangeQuotationInfoDto;
import com.joyintech.service.basicdata.marketdata.exchangerateinfo.service.ComExchangeQuotationInfoService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;

/**
 * 汇率行情 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhao
 * @version 1.0 2018年8月9日
 * @see ComExchangeQuotationInfoController
 * @since 1.0
 */
@RestController("serviceComExchangeQuotationInfo")
@RequestMapping("/ComExchangeQuotationInfo")
public class ComExchangeQuotationInfoController {

    /**
     * 汇率行情service
     */
    @Autowired
    private ComExchangeQuotationInfoService comExchangeQuotationInfoService;
    
//    @Autowired
//    private ComExchangeRateQuotationInfoImportDataService comExchangeRateQuotationInfoImportDataService;

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
    public Page<ComExchangeQuotationInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam,
                                                           @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return comExchangeQuotationInfoService.selectForPage(page, length, pageParam);
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
    public List<ComExchangeQuotationInfoDto> selectAllList(@RequestBody Map<String, Object> pageParam) {
        return comExchangeQuotationInfoService.selectAllList(pageParam);
    }

    /**
     * 保存操作 <功能详细描述>
     * 
     * @param dto 参数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("/doComplete")
    public void doComplete(@RequestBody ComExchangeQuotationInfoDto dto) {
        comExchangeQuotationInfoService.doComplete(dto);
    }


    // add by zwq 20210222 增加基准利率行情导入功能 start
    /**
     *
     * 插入数据
     * @param equityList 参数
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/mergeInfoList")
    public void mergeInfoList(@RequestBody List<ComExchangeQuotationInfoDto> equityList){
        for (ComExchangeQuotationInfoDto dto:equityList){
            //设置基本币种，单位币种等
            String exchargeCode = dto.getExchargeCode();
            if (exchargeCode != null) {
                List<String> splitToList = StringUtil.splitToList("/", exchargeCode);
                dto.setBasisCyy(splitToList.get(0));
                dto.setQuotCyy(splitToList.get(1));
            }
            dto.setInputType(DictCodeListExt1.录入方式_系统导入.getValue());
        }
        comExchangeQuotationInfoService.mergeInfoList(equityList);
    }
    // add by zwq 20210222 增加基准利率行情导入功能 end
}
