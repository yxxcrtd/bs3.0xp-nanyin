/*
 * 文件名：RepBottomAssetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repbottomasset.controller;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joyintech.tams.common.base.entity.RepBottomAsset;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repbottomasset.service.RepBottomAssetService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 底层资产/负债信息报送信息
 * @author fanshuanghu
 * @version 1.0 2018年12月27日
 * @see RepBottomAssetController
 * @since 1.0
 */
@RestController
@RequestMapping("/RepBottomAsset")
public class RepBottomAssetController {
    @Autowired
    private RepBottomAssetService repBottomAssetService;
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<RepBottomAsset> findPage(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return repBottomAssetService.findPage(page, length, param);
    }
    
    /**
     * 获取对应资管及委外资产行内资产/负债编码（资管计划代码）
     * @return 对应资管及委外资产行内资产/负债编码（资管计划代码）集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getRepAssetCode")
    public List<SelectOptionDto> getRepAssetCode(@RequestBody Map<String,Object> map){
        return repBottomAssetService.getRepAssetCode(map);
    }
    /**
     * 获取底层资产行内资产/负债编码（底层资产代码）
     * @return 底层资产行内资产/负债编码（底层资产代码）集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getRepBottomCode2")
    public List<SelectOptionDto> getRepBottomCode2(@RequestBody Map<String,Object> map){
        return repBottomAssetService.getRepBottomCode2(map);
    }
}
