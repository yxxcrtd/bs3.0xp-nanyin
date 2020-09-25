/*
 * 文件名：ProductStartAndEndAnno001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.informationdisclosureform.repfinproductestablish.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.informationdisclosureform.repfinproductestablish.dto.RepFinProductEstablishDto;
import com.joyintech.service.report.informationdisclosureform.repfinproductestablish.service.RepFinProductEstablishService;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品成立公告
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年7月30日
 * @see RepFinProductEstablishController
 * @since 1.0
 */
@RestController
@RequestMapping("/RepFinProductEstablish")
public class RepFinProductEstablishController{
    /**
     * 
     */
    @Autowired(required=false)
    private MstFileCommonService mstFileCommonService;
    /**
     * 
     */
    @Autowired
    private RepFinProductEstablishService repFinProductEstablishService;
    
    /**
     * 
     * 页面查询
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
    public Page<RepFinProductEstablishDto> selectAll(@RequestBody Map<String, Object> pageParam,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repFinProductEstablishService.selectForPage(page, length, pageParam);
    }
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
        return repFinProductEstablishService.getSelectOption(map);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto RepFinProductEstablishDto
     * @return List<RepFinProductEstablishDto>
     * @throws FileNotFoundException FileNotFoundException
     * @see
     * @since 1.0
     */
    @PostMapping("generateBulletin")
    public List<RepFinProductEstablishDto> generateBulletin(@RequestBody RepFinProductEstablishDto dto) throws FileNotFoundException{
        
        List<RepFinProductEstablishDto> listTemp = new ArrayList<RepFinProductEstablishDto>();
        //获取前台dto集合
        List<RepFinProductEstablishDto> selectItem = dto.getSelectedItem();
        //金融产品成立公告生成信息DTO的投资标的比例设值
        for(RepFinProductEstablishDto repFinProductEstablishDto :selectItem){
            //获取投资标的比例根据母产品代码
            String str = repFinProductEstablishService.selectResult(repFinProductEstablishDto.getpFinprodId());
            repFinProductEstablishDto.setInvestTargetRatio(str);
            //
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("finprodId", repFinProductEstablishDto.getpFinprodId());
            map.put("branch", repFinProductEstablishDto.getBranch());
            RepFinProductEstablishDto repFinProductEstablish = repFinProductEstablishService.selectByFinprodIdAndBranch(map);
            //金融产品成立公告生成信息DTO.币种          =       上面取得的币种（数据字典缓存.币种取得名称）
            repFinProductEstablishDto.setCcy(repFinProductEstablish.getCcy());
            //金融产品成立公告生成信息DTO.产品风险等级   =      上面取得的产品风险等级（数据字典缓存.产品风险等级取得名称）
            repFinProductEstablishDto.setRepRiskLevel(repFinProductEstablish.getRepRiskLevel());
            //金融产品成立公告生成信息DTO.收益标识       =   上面取得的收益标识（数据字典缓存.产品收益标识取得名称）                                                                    
            repFinProductEstablishDto.setProfitType(repFinProductEstablish.getProfitFlag());
            repFinProductEstablishDto.setProfitFlag(repFinProductEstablish.getProfitFlag());
            //托管费率（年）    =      上面取得的托管费率（年）（小数点保留2位）                                                                   
            repFinProductEstablishDto.setTrustFee(repFinProductEstablish.getTrustFee());
            //销售手续费率（年）   =      上面取得的销售手续费率（年）（小数点保留2位）                                                                 
            repFinProductEstablishDto.setSaleFee(repFinProductEstablish.getSaleFee());
            //预期收益率（年）     =   上面取得的预期收益率（年）（小数点保留2位）                                                                  
            repFinProductEstablishDto.setYield(repFinProductEstablish.getYield());
            repFinProductEstablishDto.setProdId(repFinProductEstablishDto.getFinprodMarketId());
            repFinProductEstablishDto.setProdName(repFinProductEstablishDto.getFinprodName());
            listTemp.add(repFinProductEstablishDto);
        }
        return  listTemp;   
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
    @GetMapping("/getSysUploadFileInfo")
    public List<SysUploadFileInfo> getSysUploadFileInfo(String bussinessId){
        return mstFileCommonService.getFileInfoByBusiId(bussinessId).getUploadFileList();
    }
    
}
