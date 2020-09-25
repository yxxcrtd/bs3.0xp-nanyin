/*
 * 文件名：RepFinProductExpirationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.informationdisclosureform.repfinproductexpiration.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.informationdisclosureform.repfinproductexpiration.dto.RepFinProductExpirationDto;
import com.joyintech.service.report.informationdisclosureform.repfinproductexpiration.service.RepFinProductExpirationService;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品到期公告
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月14日
 * @see RepFinProductExpirationController
 * @since 1.0
 */
@RestController
@RequestMapping("/RepFinProductExpiration")
public class RepFinProductExpirationController {
    
    /**
     * 自定义service
     */
    @Autowired
    private RepFinProductExpirationService repFinProductExpirationService;
    
    /**
     * 
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;
    
    /**
     * 
     * 获取组合代码下拉列表
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String,Object> map)
        throws Exception {
        map.put("manageObjType", DictCodeListExt1.管理对象类型_金融产品.getValue());
        map.put("profitType", DictCodeList.收益类型_收益型.getValue());
        return repFinProductExpirationService.getSelectOption(map);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam pageParam
     * @param page page
     * @param length length
     * @return Page<RepFinProductEstablishDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RepFinProductExpirationDto> selectAll(@RequestBody Map<String, Object> pageParam,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repFinProductExpirationService.selectForPage(page, length, pageParam);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto RepFinProductExpirationDto
     * @return List<RepFinProductExpirationDto>
     * @see
     * @since 1.0
     */
    @PostMapping("generateBulletin")
    public List<RepFinProductExpirationDto> generateBulletin(@RequestBody RepFinProductExpirationDto dto){
        
        List<RepFinProductExpirationDto> listTemp = new ArrayList<RepFinProductExpirationDto>();
        
        //获取前台dto集合
        List<RepFinProductExpirationDto> selectItem = dto.getSelectedItem();
        //金融产品成立公告生成信息DTO的投资标的比例设值
        for(RepFinProductExpirationDto repFinProductExpirationDto :selectItem){
            //获取投资标的比例根据母产品代码
            String str = repFinProductExpirationService.selectResult(repFinProductExpirationDto.getpFinprodId());
            repFinProductExpirationDto.setInvestTargetRatio(str);
            //
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("finprodId", repFinProductExpirationDto.getFinprodId());
            map.put("branch", repFinProductExpirationDto.getBranch());
            RepFinProductExpirationDto repFinProductExpiration = repFinProductExpirationService.selectByFinprodIdAndBranch(map);
            //金融产品成立公告生成信息DTO.币种          =       上面取得的币种（数据字典缓存.币种取得名称）
            repFinProductExpirationDto.setCcy(repFinProductExpiration.getCcy());
            //金融产品成立公告生成信息DTO.产品风险等级   =      上面取得的产品风险等级（数据字典缓存.产品风险等级取得名称）
            repFinProductExpirationDto.setRepRiskLevel(repFinProductExpiration.getRepRiskLevel());
            //金融产品成立公告生成信息DTO.收益标识       =   上面取得的收益标识（数据字典缓存.产品收益标识取得名称）                                                                    
            repFinProductExpirationDto.setProfitType(repFinProductExpiration.getProfitFlag());
            repFinProductExpirationDto.setProfitFlag(repFinProductExpiration.getProfitFlag());
            //托管费率（年）    =      上面取得的托管费率（年）（小数点保留2位）                                                                   
            repFinProductExpirationDto.setTrustFee(repFinProductExpiration.getTrustFee());
            //销售手续费率（年）   =      上面取得的销售手续费率（年）（小数点保留2位）                                                                 
            repFinProductExpirationDto.setSaleFee(repFinProductExpiration.getSaleFee());
            //预期收益率（年）     =   上面取得的预期收益率（年）（小数点保留2位）                                                                  
            repFinProductExpirationDto.setYield(repFinProductExpiration.getYield());
            //产品兑付日   = 上面取得的产品兑付日                          
            repFinProductExpirationDto.setPayDay(repFinProductExpiration.getPayDay());
            repFinProductExpirationDto.setProdId(repFinProductExpirationDto.getFinprodMarketId());
            repFinProductExpirationDto.setProdName(repFinProductExpirationDto.getFinprodName());
            listTemp.add(repFinProductExpirationDto);
        }
        return listTemp;
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param bussinessId String
     * @return List<SysUploadFileInfo>
     * @see
     * @since 1.0
     */
    @PostMapping("/getSysUploadFileInfo")
    public List<SysUploadFileInfo> getSysUploadFileInfo(String bussinessId){
        return mstFileCommonService.getFileInfoByBusiId(bussinessId).getUploadFileList();
    }

}
